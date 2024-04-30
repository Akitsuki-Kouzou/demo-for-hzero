function generateEmployeeTable(employees) {
  const employeeTable = document.getElementById("employeeTable");
  employeeTable.innerHTML = `
      <table>
          <tr>
              <th>Employee Code</th>
              <th>Employee Name</th>
              <th>Joining Date</th>
              <th>Department Code</th>
              <th>Position Code</th>
              <th>Actions</th>
          </tr>
          ${employees
            .map(
              (employee) => `
              <tr>
                  <td>${employee.employeeCode}</td>
                  <td>${employee.employeeName}</td>
                  <td>${employee.joiningDate}</td>
                  <td>${employee.departmentCode}</td>
                  <td>${employee.positionCode}</td>
                  <td class="action-buttons">
                      <button id="openEditEmployeeBtn" onclick="openEditEmployeePopup(${employee.employeeId})">Edit</button>
                      <button onclick="deleteEmployee(${employee.employeeId})">Delete</button>
                  </td>
              </tr>
          `
            )
            .join("")}
      </table>
  `;
}

// Function to generate department table
function generateDepartmentTable(departments) {
  const departmentTable = document.getElementById("departmentTable");
  departmentTable.innerHTML = `
      <table>
          <tr>
              <th>Department Code</th>
              <th>Department Name</th>
              <th>Actions</th>
          </tr>
          ${departments
            .map(
              (department) => `
              <tr>
                  <td>${department.departmentCode}</td>
                  <td>${department.departmentName}</td>
                  <td class="action-buttons">
                      <button id="editDepartmenBtn" onclick="openEditDepartmentPopup(${department.departmentId})">Edit</button>
                      <button onclick="deleteDepartment(${department.departmentId})">Delete</button>
                  </td>
              </tr>
          `
            )
            .join("")}
      </table>
  `;
}

// Function to generate position table
function generatePositionTable(positions) {
  const positionTable = document.getElementById("positionTable");
  positionTable.innerHTML = `
      <table>
          <tr>
              <th>Position Code</th>
              <th>Position Name</th>
              <th>Department Code</th>
              <th>Actions</th>
          </tr>
          ${positions
            .map(
              (position) => `
              <tr>
                  <td>${position.positionCode}</td>
                  <td>${position.positionName}</td>
                  <td>${position.positionDepartmentCode}</td>
                  <td class="action-buttons">
                      <button id="editPositionBtn" onclick="openEditPositionPopup(${position.positionId})">Edit</button>
                      <button onclick="deletePosition(${position.positionId})">Delete</button>
                  </td>
              </tr>
          `
            )
            .join("")}
      </table>
  `;
}

// Function to initialize the application by fetching data and generating tables
async function initializeApp() {
  try {
    // Fetch data from API
    const employeesResponse = await fetch(
      "http://localhost:8080/api/employees"
    );
    const departmentsResponse = await fetch(
      "http://localhost:8080/api/departments"
    );
    const positionsResponse = await fetch(
      "http://localhost:8080/api/positions"
    );

    // Check if responses are successful
    if (
      !employeesResponse.ok ||
      !departmentsResponse.ok ||
      !positionsResponse.ok
    ) {
      throw new Error("Failed to fetch data");
    }

    // Parse JSON data
    const employees = await employeesResponse.json();
    const departments = await departmentsResponse.json();
    const positions = await positionsResponse.json();

    // Generate tables with fetched data
    generateEmployeeTable(employees);
    generateDepartmentTable(departments);
    generatePositionTable(positions);
  } catch (error) {
    console.error("Error:", error.message);
  }
}

// Placeholder functions for edit and delete operations
function deleteEmployee(id) {
  console.log("Deleting employee with ID:", id);
}

function openEditEmployeePopup(employeeId) {
  console.log("Editing employee with ID: ", employeeId);

  // Fetch the employee details using employeeId
  fetch(`http://localhost:8080/api/employees/${employeeId}`)
    .then((response) => {
      if (!response.ok) {
        throw new Error("Failed to fetch employee details");
      }
      return response.json();
    })
    .then((employee) => {
      // Populate the popup fields with the fetched employee details
      document.getElementById("editEmployeeId").value = employee.employeeId;
      document.getElementById("editEmployeeCode").value = employee.employeeCode;
      document.getElementById("editEmployeeName").value = employee.employeeName;
      document.getElementById("editJoiningDate").value = employee.joiningDate;
      document.getElementById("editDepartmentCode").value =
        employee.departmentCode;
      document.getElementById("editPositionCode").value = employee.positionCode;
      document.getElementById("editEmployeePopup").style.display = "block";
    })
    .catch((error) => {
      console.error("Error:", error.message);
    });
}

// Function to open edit department popup
function openEditDepartmentPopup(departmentId) {
  // Fetch the department details using departmentId
  fetch(`http://localhost:8080/api/departments/${departmentId}`)
    .then((response) => {
      if (!response.ok) {
        throw new Error("Failed to fetch department details");
      }
      return response.json();
    })
    .then((department) => {
      // Populate the popup fields with the fetched department details
      document.getElementById("editDepartmentId").value =
        department.departmentId;
      document.getElementById("editDepartmentCode").value =
        department.departmentCode;
      document.getElementById("editDepartmentName").value =
        department.departmentName;
      document.getElementById("editDepartmentPopup").style.display = "block";
    })
    .catch((error) => {
      console.error("Error:", error.message);
    });
}

// Function to open edit position popup
function openEditPositionPopup(positionId) {
  // Fetch the position details using positionId
  fetch(`http://localhost:8080/api/positions/${positionId}`)
    .then((response) => {
      if (!response.ok) {
        throw new Error("Failed to fetch position details");
      }
      return response.json();
    })
    .then((position) => {
      // Populate the popup fields with the fetched position details
      document.getElementById("editPositionId").value = position.positionId;
      document.getElementById("editPositionCode").value = position.positionCode;
      document.getElementById("editPositionName").value = position.positionName;
      document.getElementById("editPositionDepartmentCode").value =
        position.positionDepartmentCode;
      console.log(position.positionDepartmentCode);
      document.getElementById("editPositionPopup").style.display = "block";
    })
    .catch((error) => {
      console.error("Error:", error.message);
    });
}

// Function to update employee data, employeeId
async function updateEmployee(event) {
  event.preventDefault();
  const form = event.target;
  const formData = new FormData(form);
  const employeeData = {};
  formData.forEach((value, key) => {
    employeeData[key] = value;
  });

  console.log(employeeData);

  const employeeId = formData.get("editEmployeeId");
  console.log("Employee ID : ", employeeId);

  try {
    const response = await fetch(
      `http://localhost:8080/api/employees/${employeeId}`,
      {
        method: "PUT", // Assuming PUT method is used for updating employee data
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(employeeData),
      }
    );

    if (!response.ok) {
      throw new Error("Failed to update employee");
    }

    closePopup("editEmployeePopup");
    initializeApp(); // Refresh employee table
  } catch (error) {
    console.error("Error:", error.message);
  }
}

// Function to update department data
async function updateDepartment(event) {
  event.preventDefault();
  const form = event.target;
  const formData = new FormData(form);
  const departmentData = {};
  formData.forEach((value, key) => {
    departmentData[key] = value;
  });

  const departmentId = formData.get("departmentId");

  try {
    const response = await fetch(
      `http://localhost:8080/api/departments/${departmentId}`,
      {
        method: "PUT", // Assuming PUT method is used for updating department data
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(departmentData),
      }
    );

    if (!response.ok) {
      throw new Error("Failed to update department");
    }

    closePopup("editDepartmentPopup");
    initializeApp(); // Refresh department table
  } catch (error) {
    console.error("Error:", error.message);
  }
}

// Function to update position data
async function updatePosition(event) {
  event.preventDefault();
  const form = event.target;
  const formData = new FormData(form);
  const positionData = {};
  formData.forEach((value, key) => {
    positionData[key] = value;
  });

  console.log(formData);

  const positionId = formData.get("positionId");

  try {
    const response = await fetch(
      `http://localhost:8080/api/positions/${positionId}`,
      {
        method: "PUT", // Assuming PUT method is used for updating position data
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(positionData),
      }
    );

    if (!response.ok) {
      throw new Error("Failed to update position");
    }

    closePopup("editPositionPopup");
    initializeApp(); // Refresh position table
  } catch (error) {
    console.error("Error:", error.message);
  }
}

// Function to close popup
function closePopup(popupId) {
  document.getElementById(popupId).style.display = "none";
}

// Function to create employee
async function createEmployee(event) {
  event.preventDefault();
  const form = event.target;
  const formData = new FormData(form);
  const employeeData = {};
  formData.forEach((value, key) => {
    employeeData[key] = value;
  });

  try {
    const response = await fetch("http://localhost:8080/api/employees", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(employeeData),
    });

    if (!response.ok) {
      throw new Error("Failed to create employee");
    }

    closeForm("EmployeeForm");
    initializeApp(); // Refresh employee table
  } catch (error) {
    console.error("Error:", error.message);
  }
}

// Function to create department
async function createDepartment(event) {
  event.preventDefault();
  const form = event.target;
  const formData = new FormData(form);
  const departmentData = {};
  formData.forEach((value, key) => {
    departmentData[key] = value;
  });

  try {
    const response = await fetch("http://localhost:8080/api/departments", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(departmentData),
    });

    if (!response.ok) {
      throw new Error("Failed to create department");
    }

    closeForm("DepartmentForm");
    initializeApp(); // Refresh department table
  } catch (error) {
    console.error("Error:", error.message);
  }
}

// Function to create position
async function createPosition(event) {
  event.preventDefault();
  const form = event.target;
  const formData = new FormData(form);
  const positionData = {};
  formData.forEach((value, key) => {
    positionData[key] = value;
  });

  try {
    const response = await fetch("http://localhost:8080/api/positions", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(positionData),
    });

    if (!response.ok) {
      throw new Error("Failed to create position");
    }

    closeForm("PositionForm");
    initializeApp(); // Refresh position table
  } catch (error) {
    console.error("Error:", error.message);
  }
}

// Function to delete employee
async function deleteEmployee(id) {
  try {
    const response = await fetch(`http://localhost:8080/api/employees/${id}`, {
      method: "DELETE",
    });

    if (!response.ok) {
      throw new Error("Failed to delete employee");
    }

    initializeApp(); // Refresh employee table
  } catch (error) {
    console.error("Error:", error.message);
  }
}

// Function to delete department
async function deleteDepartment(id) {
  try {
    const response = await fetch(
      `http://localhost:8080/api/departments/${id}`,
      {
        method: "DELETE",
      }
    );

    if (!response.ok) {
      throw new Error("Failed to delete department");
    }

    initializeApp(); // Refresh department table
  } catch (error) {
    console.error("Error:", error.message);
  }
}

// Function to delete position
async function deletePosition(id) {
  try {
    const response = await fetch(`http://localhost:8080/api/positions/${id}`, {
      method: "DELETE",
    });

    if (!response.ok) {
      throw new Error("Failed to delete position");
    }

    initializeApp(); // Refresh position table
  } catch (error) {
    console.error("Error:", error.message);
  }
}

// Function to open specific form
function openForm(formName) {
  document.getElementById(formName).style.display = "block";
}

// Function to close specific form
function closeForm(formName) {
  document.getElementById(formName).style.display = "none";
}

// Function to open tab
function openTab(evt, tabName) {
  const tabcontent = document.getElementsByClassName("tabcontent");
  for (let i = 0; i < tabcontent.length; i++) {
    tabcontent[i].style.display = "none";
  }
  const tablinks = document.getElementsByClassName("tablinks");
  for (let i = 0; i < tablinks.length; i++) {
    tablinks[i].className = tablinks[i].className.replace(" active", "");
  }
  document.getElementById(tabName).style.display = "block";
  evt.currentTarget.className += " active";
  // Call respective function to generate table when tab is opened
  if (tabName === "Employees") {
    initializeApp();
    // generateEmployeeTable(employees)
  } else if (tabName === "Departments") {
    initializeApp();
    // generateDepartmentTable(departments)
  } else if (tabName === "Positions") {
    initializeApp();
    // generatePositionTable(positions)
  }
}

// Fetch department codes from the API
async function fetchDepartmentCodes() {
  try {
    const response = await fetch("http://localhost:8080/api/departments");
    if (!response.ok) {
      throw new Error("Failed to fetch department codes");
    }
    const departments = await response.json();
    return departments.map((department) => ({
      code: department.departmentCode,
      name: department.departmentName,
    }));
  } catch (error) {
    console.error("Error:", error.message);
    return [];
  }
}

// Fetch position codes from the API
async function fetchPositionCodes() {
  try {
    const response = await fetch("http://localhost:8080/api/positions");
    if (!response.ok) {
      throw new Error("Failed to fetch position codes");
    }
    const positions = await response.json();
    return positions.map((position) => ({
      code: position.positionCode,
      name: position.positionName,
    }));
  } catch (error) {
    console.error("Error:", error.message);
    return [];
  }
}

// Function to populate dropdown options for department code and position code
async function populateDropdownOptions() {
  const departmentCodes = await fetchDepartmentCodes();
  const positionCodes = await fetchPositionCodes();

  // Populate department code dropdown options
  const departmentCodeDropdown = document.querySelectorAll(
    'select[name="departmentCode"]'
  );
  departmentCodeDropdown.forEach((dropdown) => {
    departmentCodes.forEach((department) => {
      const option = new Option(department.name, department.code);
      dropdown.add(option);
    });
  });

  // Populate position code dropdown options
  const positionCodeDropdown = document.querySelectorAll(
    'select[name="positionCode"]'
  );
  positionCodeDropdown.forEach((dropdown) => {
    positionCodes.forEach((position) => {
      const option = new Option(position.name, position.code);
      dropdown.add(option);
    });
  });
}

// Function to populate department code dropdown in the edit position form
async function populateDepartmentDropdown() {
  try {
    const response = await fetch("http://localhost:8080/api/departments");
    if (!response.ok) {
      throw new Error("Failed to fetch department data");
    }
    const departments = await response.json();
    const dropdown = document.getElementById("editPositionDepartmentCode");
    dropdown.innerHTML = ""; // Clear existing options
    departments.forEach((department) => {
      const option = document.createElement("option");
      option.value = department.departmentCode;
      option.text = department.departmentCode;
      dropdown.appendChild(option);
    });
  } catch (error) {
    console.error("Error:", error.message);
  }
}

function searchTable() {
  // Get input value
  const input = document.getElementById("searchInput").value.toLowerCase();

  // Get table rows
  const rows = document.querySelectorAll("table tr");

  // Loop through rows
  rows.forEach((row) => {
    const cells = row.querySelectorAll("td");
    let found = false;

    // Loop through cells in the row
    cells.forEach((cell) => {
      const text = cell.textContent.toLowerCase();
      // Check if cell text contains input value
      if (text.includes(input)) {
        found = true;
      }
    });

    // Toggle row display based on search result
    if (found) {
      row.style.display = "";
    } else {
      row.style.display = "none";
    }
  });
}

// Call populateDropdownOptions() when the page loads
window.onload = function () {
  // Call the function to populate the department code dropdown
  populateDepartmentDropdown();
  populateDropdownOptions();
};

// Open default tab
document.getElementsByClassName("tablinks")[0].click();
