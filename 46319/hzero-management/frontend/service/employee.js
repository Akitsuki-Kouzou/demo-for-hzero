document.addEventListener("DOMContentLoaded", function () {
  const tableBody = document.querySelector(".table tbody");
  let employees = [];
  let positions = [];

  fetch("http://localhost:8080/api/positions")
    .then((response) => response.json())
    .then((data) => {
      positions = data;
      console.log("Fetched Positions:", positions); // Inspect the data
      populatePositionSelect(positionSelect);
    })
    .catch((error) => console.error("Error fetching positions:", error));

  fetch("http://localhost:8080/api/employees")
    .then((response) => response.json())
    .then((data) => {
      employees = data;
      renderTable(employees);
    })
    .catch((error) => console.error("Error fetching employees:", error));

  const searchInput = document.getElementById("searchBar");
  searchInput.addEventListener("input", () => {
    const searchTerm = searchInput.value.trim();
    if (searchTerm !== "") {
      const fuse = new Fuse(employees, {
        keys: [
          "employee_code",
          "employee_name",
          "joining_date",
          "department_code",
          "position_code",
        ],
      });
      const results = fuse.search(searchTerm);
      const filteredEmployees = results.map((result) => result.item);
      renderTable(filteredEmployees);
    } else {
      renderTable(employees);
    }
  });

  function renderTable(employeesToDisplay) {
    tableBody.innerHTML = ""; // Clear the table body
    employeesToDisplay.forEach((employee) => {
      tableBody.appendChild(createTableRow(employee));
    });
  }

  function createTableRow(employee) {
    const row = document.createElement("tr");

    const codeCell = document.createElement("td");
    codeCell.textContent = employee.employee_code; // Use the property names from your JSON
    row.appendChild(codeCell);

    const nameCell = document.createElement("td");
    nameCell.textContent = employee.employee_name;
    row.appendChild(nameCell);

    const joiningDateCell = document.createElement("td");
    joiningDateCell.textContent = employee.joining_date;
    row.appendChild(joiningDateCell);

    const positionCell = document.createElement("td");
    positionCell.textContent = employee.position_code; // Assuming this holds the position name
    row.appendChild(positionCell);

    const departmentCell = document.createElement("td");
    departmentCell.textContent = employee.department_code; // Assuming department_code contains the name
    row.appendChild(departmentCell);

    // ... Add cells for joiningDate, department, position

    const actionsCell = document.createElement("td");
    actionsCell.classList.add("flex", "gap-5");

    const updateButton = document.createElement("button");
    updateButton.textContent = "Update";
    updateButton.classList.add("btn", "btn-info");
    updateButton.setAttribute("for", "updateEmp");
    updateButton.addEventListener("click", () => {
      // Placeholder - Open the update modal and populate fields
      const updateModal = document.getElementById("updateEmp");
      updateModal.checked = true;
      const updateID = document.getElementById("updateID");
      const updateName = document.getElementById("updateName");
      const updateDate = document.getElementById("updateDate");

      updateID.value = employee.employee_code;
      updateName.value = employee.employee_name;
      updateDate.value = employee.joining_date;
      const updatePosSelect = document.getElementById("updatePosSelect");
      const updateDeptInput = document.getElementById("updateDeptCode");
      populatePositionSelect(updatePosSelect);
      updatePosSelect.addEventListener("change", function () {
        const selectedPositionCode = this.value;
        const selectedOption = this.querySelector(
          `option[value="${selectedPositionCode}"]`
        );
        const departmentCode = selectedOption.dataset.department_code;

        updateDeptInput.value = departmentCode;
        console.log("Selected Option:", selectedOption);
        console.log("Department Code (from dataset):", departmentCode);
      });
      console.log("Update button clicked for employee:", employee);
    });
    actionsCell.appendChild(updateButton);

    const deleteButton = document.createElement("button");
    deleteButton.textContent = "Delete";
    deleteButton.classList.add("btn", "btn-error");
    // Add an event listener for the delete button here
    deleteButton.addEventListener("click", (e) => {
      //   e.preventDefault();
      // Implement your delete logic here (e.g., confirmation, DELETE request)
      const shouldDelete = confirm(
        "Are you sure you want to delete this Employee?"
      );
      if (shouldDelete) {
        // Send DELETE Request
        fetch(`http://localhost:8080/api/employees/${employee.employee_code}`, {
          // Assuming departmentCode is unique
          method: "DELETE",
        })
          .then((response) => {
            if (response.ok) {
              console.log(employees.employee_code);
              console.log("Employee deleted successfully");
              // Remove the row from the table (optional, you can reload the page too)
              row.remove();
            } else {
              throw new Error("Error deleting Employee");
            }
          })
          .catch((error) => {
            console.error("Error deleting Employee:", error);
            // Display an error message to the user
          });
      }
    });
    actionsCell.appendChild(deleteButton);

    row.appendChild(actionsCell);

    return row;
  }

  function populatePositionSelect(selectElement) {
    // Make it reusable
    selectElement.innerHTML =
      "<option disabled selected>Select Position Code</option>";

    positions.forEach((position) => {
      const option = document.createElement("option");
      option.value = position.position_code;
      option.textContent = position.position_code;
      option.dataset.department_code = position.department_code;
      selectElement.appendChild(option);
    });
  }
  // populatePositionSelect();
  const positionSelect = document.getElementById("addPosSelect");
  const departmentSelect = document.getElementById("addDeptCode");

  positionSelect.addEventListener("change", function () {
    const selectedPositionCode = this.value;
    const selectedOption = this.querySelector(
      `option[value="${selectedPositionCode}"]`
    );
    const departmentCode = selectedOption.dataset.department_code;

    departmentSelect.value = departmentCode;
    console.log("Selected Option:", selectedOption);
    console.log("Department Code (from dataset):", departmentCode);
  });

  // Add submit button listener (assuming you have a button to submit the employee form)
  const submitButton = document.getElementById("addNewEmp"); // Adjust ID if needed
  submitButton.addEventListener("click", function (event) {
    event.preventDefault(); // Prevent default form submission

    const employee_name = document.getElementById("addName").value.trim();
    const joining_date = document.getElementById("addDate").value;
    const department_code = document.getElementById("addDeptCode").value;
    const position_code = document.getElementById("addPosSelect").value;

    const employee_code = generateEmployeeCode();

    const employeeData = {
      employee_code, // Shorthand since the property and variable names now match
      employee_name,
      joining_date,
      department_code,
      position_code,
    };

    fetch("http://localhost:8080/api/employees", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(employeeData),
    })
      .then((response) => {
        if (response.ok) {
          console.log("Employee added successfully");
          location.reload();
          // Optionally: clear the form fields, display success message
        } else {
          throw new Error("Error adding employee");
        }
      })
      .catch((error) => {
        console.error("Error adding employee:", error);
        // Display a user-friendly error message
      });

    function generateEmployeeCode() {
      const prefix = "HD";
      const randomPart = Math.floor(Math.random() * 90000) + 10000; // 5-digit random number
      return prefix + randomPart;
    }
  });

  const updateModal = document.getElementById("updateEmp");
  const updateSubmitButton = document.getElementById("confirmUpdateEmp");

  updateSubmitButton.addEventListener("click", function (event) {
    event.preventDefault();

    const employee_code = document.getElementById("updateID").value;
    const employee_name = document.getElementById("updateName").value.trim();
    const joining_date = document.getElementById("updateDate").value;
    const department_code = document.getElementById("updateDeptCode").value;
    const position_code = document.getElementById("updatePosSelect").value;

    // Validation
    if (
      employee_name === "" ||
      joining_date === "" ||
      department_code === "" ||
      position_code === "Select Position Code"
    ) {
      alert("Please fill in all required fields.");
      return;
    }

    const updatedEmployeeData = {
      employee_code,
      employee_name,
      joining_date,
      department_code,
      position_code,
    };

    fetch(`http://localhost:8080/api/employees/${employee_code}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(updatedEmployeeData),
    })
      .then((response) => {
        if (response.ok) {
          console.log("Employee updated successfully");
          updateModal.checked = false; // Close the modal
          location.reload();
          // ... (Optionally: update the table row, display success message)
        } else {
          throw new Error("Error updating employee");
        }
      })
      .catch((error) => {
        console.error("Error updating employee:", error);
        // Display a user-friendly error message
      });
  });

  document
    .getElementById("searchBarFuzzy")
    .addEventListener("keyup", function (event) {
      if (event.key === "Enter") {
        // Check if the key pressed is "Enter"
        updateSearch();
      }
    });

  function updateSearch() {
    let searchTerm = document.getElementById("searchBarFuzzy").value;
    let newUrl =
      "http://127.0.0.1:6969/employee.html?search=" +
      encodeURIComponent(searchTerm);
    window.location.href = newUrl;
  }

  function extractSearchTermFromUrl() {
    const urlParams = new URLSearchParams(window.location.search);
    const searchTerm = urlParams.get("search");
    return searchTerm;
  }

  function initiateSearch() {
    const searchTerm = extractSearchTermFromUrl();
    if (searchTerm) {
      // Only fetch if a search term exists
      let searchUrl =
        "http://localhost:8080/api/employees/search?name=" +
        encodeURIComponent(searchTerm);
      fetch(searchUrl)
        .then((response) => response.json())
        .then((employees) => renderTable(employees))
        .catch((error) => console.error("Error fetching data:", error));
    } else {
      // Handle case when there's no search term (e.g., display initial employees)
      fetchAndRenderInitialEmployees(); // You might need to implement this
    }
  }
  window.addEventListener("popstate", () => {
    initiateSearch();
  });
});
