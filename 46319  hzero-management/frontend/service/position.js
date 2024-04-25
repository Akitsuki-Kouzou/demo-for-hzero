document.addEventListener("DOMContentLoaded", function () {
  const tableBody = document.querySelector(".table tbody");
  let positions = [];
  let departments = [];
  fetch("http://localhost:8080/api/positions")
    .then((response) => response.json())
    .then((data) => {
      console.log("Fetched Data:", data);
      positions = data;
      renderTable(positions);
    })
    .catch((error) => console.error("Error fetching positions:", error));

  const searchInput = document.getElementById("searchBar");
  searchInput.addEventListener("input", () => {
    const searchTerm = searchInput.value.trim();
    if (searchTerm !== "") {
      const fuse = new Fuse(positions, {
        keys: ["position_code", "position_name", "department_code"],
      });
      const results = fuse.search(searchTerm);
      const filteredPositions = results.map((result) => result.item);
      renderTable(filteredPositions);
    } else {
      renderTable(positions);
    }
  });

  function renderTable(positionsToDisplay) {
    tableBody.innerHTML = "";
    positionsToDisplay.forEach((position) => {
      tableBody.appendChild(createTableRow(position));
    });
  }

  function createTableRow(position) {
    const row = document.createElement("tr");

    const codeCell = document.createElement("td");
    codeCell.textContent = position.position_code;
    row.appendChild(codeCell);

    const nameCell = document.createElement("td");
    nameCell.textContent = position.position_name;
    row.appendChild(nameCell);

    const departmentCodeCell = document.createElement("td");
    departmentCodeCell.textContent = position.department_code;
    row.appendChild(departmentCodeCell);

    const actionsCell = document.createElement("td");
    actionsCell.classList.add("flex", "gap-5");

    // Add update button
    const updateButton = document.createElement("button");
    updateButton.textContent = "Update";
    updateButton.classList.add("btn", "btn-info");
    updateButton.addEventListener("click", (e) => {
      e.preventDefault();
      // Implement your update logic here (e.g., open an update modal)
      const updateModal = document.getElementById("updatePos");
      updateModal.checked = true;

      const updatePositionCodeInput = document.getElementById("updatePosCode");
      const updatePositionNameInput = document.getElementById("updatePosName");
      const updateSelect = document.getElementById("updateSelect");

      updateSelect.innerHTML =
        "<option disabled selected>Select Department Code</option>"; // Reset options

      departments.forEach((department) => {
        const option = document.createElement("option");
        option.value = department.departmentCode;
        option.textContent = department.departmentCode;
        updateSelect.appendChild(option);
      });

      updatePositionCodeInput.value = position.position_code;
      updatePositionNameInput.value = position.position_name;
    });
    actionsCell.appendChild(updateButton);

    // Add delete button
    const deleteButton = document.createElement("button");
    deleteButton.textContent = "Delete";
    deleteButton.classList.add("btn", "btn-error");
    deleteButton.addEventListener("click", (e) => {
      e.preventDefault();
      const shouldDelete = confirm(
        "Are you sure you want to delete this Position?"
      );
      if (shouldDelete) {
        fetch(`http://localhost:8080/api/positions/${position.position_code}`, {
          method: "DELETE",
        })
          .then((response) => {
            if (response.ok) {
              console.log(position.position_code);
              console.log("Position deleted successfully");
              row.remove();
            } else {
              // Assuming your backend returns a specific error message or status code for this scenario
              if (
                response.status === 500 ||
                response.statusText === "Conflict"
              ) {
                alert(
                  "Can't delete this position. There are employees registered with this position."
                );
              } else {
                throw new Error("Error deleting Position");
              }
            }
          })
          .catch((error) => {
            console.error("Error deleting Position:", error);
            alert(
              "An error occurred while deleting the position. Please try again."
            ); // Generic fallback
          });
      }
    });
    actionsCell.appendChild(deleteButton);

    row.appendChild(actionsCell);

    return row;
  }

  const departmentSelect = document.getElementById("addSelect"); // Use your new select ID
  const updateDepartmentSelect = document.getElementById("updateSelect");
  fetchDepartmentCodes();

  function fetchDepartmentCodes() {
    fetch("http://localhost:8080/api/departments")
      .then((response) => response.json())
      .then((data) => {
        departments = data; // Assign fetched data to the global variable
        populateDepartmentSelect(departments, departmentSelect); // Populate 'addSelect'
      })
      .catch((error) => console.error("Error fetching departments:", error));
  }

  function populateDepartmentSelect(departments) {
    departmentSelect.innerHTML =
      "<option disabled selected>Select Department Code</option>";

    departments.forEach((department) => {
      const option = document.createElement("option");
      option.value = department.departmentCode;
      option.textContent = department.departmentCode;
      departmentSelect.appendChild(option);
    });
  }

  const newPositionSubmitButton = document.getElementById("newPositionSubmit");
  newPositionSubmitButton.addEventListener("click", function (event) {
    event.preventDefault();
    const positionCode = document
      .getElementById("positionCodeInput")
      .value.trim();
    const positionName = document
      .getElementById("positionNameInput")
      .value.trim();
    const departmentCode = document.getElementById("addSelect").value;

    // Validation
    if (
      positionCode === "" ||
      positionName === "" ||
      departmentCode === "Select Department Code"
    ) {
      alert("Please fill in all fields and select a department.");
      return;
    }

    // If validation passes, proceed with your POST request logic ...
    const positionData = {
      position_code: positionCode,
      position_name: positionName,
      department_code: departmentCode,
    }; // Assuming these property names match your backend

    fetch("http://localhost:8080/api/positions", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(positionData),
    })
      .then((response) => {
        if (response.ok) {
          location.reload();
          console.log("Position added successfully");
          // Optionally: clear the form fields, display success message, or reload the page
        } else {
          throw new Error("Error adding position");
        }
      })
      .catch((error) => {
        console.error("Error adding position:", error);
        // Display a user-friendly error message
      });
  });

  const confirmUpdatePosButton = document.getElementById("confirmUpdatePos");
  confirmUpdatePosButton.addEventListener("click", function () {
    const positionCode = document.getElementById("updatePosCode").value.trim();
    const positionName = document.getElementById("updatePosName").value.trim();
    const departmentCode = document.getElementById("updateSelect").value;

    if (
      positionCode === "" ||
      positionName === "" ||
      departmentCode === "Select Department Code"
    ) {
      alert("Please fill in all fields and select a department.");
      return;
    }

    const positionData = {
      position_code: positionCode,
      position_name: positionName,
      department_code: departmentCode,
    };

    fetch(`http://localhost:8080/api/positions/${positionCode}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(positionData),
    })
      .then((response) => {
        if (response.ok) {
          location.reload();
          console.log("Position updated successfully");
          // Optionally: Refresh the table, close the modal, display a success message
        } else {
          throw new Error("Error updating position");
        }
      })
      .catch((error) => {
        console.error("Error updating position:", error);
        // Display a user-friendly error message
      });
  });
});
