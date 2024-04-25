document.addEventListener("DOMContentLoaded", function () {
  const tableBody = document.querySelector(".table tbody");
  let departments = [];

  fetch("http://localhost:8080/api/departments")
    .then((response) => response.json())
    .then((data) => {
      departments = data;
      renderTable(departments);
    })
    .catch((error) => console.error("Error fetching departments:", error));

  const searchInput = document.getElementById("searchBar");
  searchInput.addEventListener("input", () => {
    const searchTerm = searchInput.value.trim();

    if (searchTerm !== "") {
      const fuse = new Fuse(departments, {
        keys: ["departmentCode", "departmentName"],
      });
      const results = fuse.search(searchTerm);
      const filteredDepartments = results.map((result) => result.item);
      renderTable(filteredDepartments);
    } else {
      renderTable(departments);
    }
  });

  function renderTable(departmentsToDisplay) {
    tableBody.innerHTML = ""; // Clear the table body
    departmentsToDisplay.forEach((department) => {
      tableBody.appendChild(createTableRow(department));
    });
  }
  function createTableRow(department) {
    const row = document.createElement("tr");

    const codeCell = document.createElement("td");
    codeCell.textContent = department.departmentCode;
    row.appendChild(codeCell);

    const nameCell = document.createElement("td");
    nameCell.textContent = department.departmentName;
    row.appendChild(nameCell);

    const actionsCell = document.createElement("td");
    actionsCell.classList.add("flex", "gap-5");

    const updateButton = document.createElement("button");
    updateButton.textContent = "Update";
    updateButton.classList.add("btn", "btn-info");
    // updateButton.setAttribute('for', 'updateDept'); // Assuming your update modal has  id="updateEmp"
    updateButton.addEventListener("click", () => {
      // Placeholder - Open the update modal and populate fields
      const updateModal = document.getElementById("updateDept");
      updateModal.checked = true; // Open the modal
      let updateDeptCode = document.getElementById("updateDeptCode"); // Adjust the ID if needed
      let updateDeptName = document.getElementById("updateDeptName");
      updateDeptCode.value = department.departmentCode;
      updateDeptName.value = department.departmentName;
      console.log("Update button clicked for department:", department);
    });
    actionsCell.appendChild(updateButton);

    const deleteButton = document.createElement("button");
    deleteButton.textContent = "Delete";
    deleteButton.classList.add("btn", "btn-error");
    deleteButton.addEventListener("click", function () {
      const shouldDelete = confirm(
        "Are you sure you want to delete this department?"
      );
      if (shouldDelete) {
        // Send DELETE Request
        fetch(
          `http://localhost:8080/api/departments/${department.departmentCode}`,
          {
            // Assuming departmentCode is unique
            method: "DELETE",
          }
        )
          .then((response) => {
            if (response.ok) {
              console.log("Department deleted successfully");
              row.remove();
            } else {
              // Assuming your backend returns a specific error message or status code for this scenario
              if (
                response.status === 500 ||
                response.statusText === "Conflict"
              ) {
                alert(
                  "Can't delete this Department. There are positions registered with this department."
                );
              } else {
                throw new Error("Error deleting Department");
              }
            }
          })
          .catch((error) => {
            console.error("Error deleting Department:", error);
            alert(
              "An error occurred while deleting the department. Please try again."
            ); // Generic fallback
          });
      }
    });
    actionsCell.appendChild(deleteButton);

    row.appendChild(actionsCell);

    return row;
  }

  const submitButton = document.getElementById("newDepartmentSubmit");
  submitButton.addEventListener("click", function (event) {
    event.preventDefault(); // Prevent default form submission

    // Collect form data
    const departmentCode = document.getElementById("departmentCodeInput").value; // Adjust the input ID
    const departmentName = document.getElementById("departmentNameInput").value; // Adjust the input ID

    const departmentData = {
      departmentCode: departmentCode,
      departmentName: departmentName,
    };
    if (departmentCode === "" || departmentName === "") {
      alert("Please fill in both the department code and department name.");
      return; // Stop submission if there's an empty field
    }

    // Send POST Request
    fetch("http://localhost:8080/api/departments", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(departmentData),
    })
      .then((response) => {
        if (response.ok) {
          location.reload();
          console.log("Department added successfully");
          // Optionally: clear the form fields or display a success message
        } else {
          throw new Error("Error adding department");
        }
      })
      .catch((error) => {
        console.error("Error adding department:", error);
        // Display an error message to the user
      });
  });

  const confirmUpdateButton = document.getElementById("confirmUpdateDept");
  confirmUpdateButton.addEventListener("click", function (e) {
    const departmentCode = document.getElementById("updateDeptCode").value;
    const departmentName = document.getElementById("updateDeptName").value;
    const departmentData = {
      departmentCode: departmentCode,
      departmentName: departmentName,
    };

    // Send PUT Request
    fetch(`http://localhost:8080/api/departments/${departmentCode}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(departmentData),
    })
      .then((response) => {
        if (response.ok) {
          console.log("Department updated successfully");
          location.reload(); // Refresh the page
        } else {
          throw new Error("Error updating department");
        }
      })
      .catch((error) => {
        console.error("Error updating department:", error);
        // Display an error message to the user
      });
  });
});
