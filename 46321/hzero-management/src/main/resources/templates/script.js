// scripts.js
document.addEventListener('DOMContentLoaded', function () {
    const addForm = document.getElementById('itemForm');
    const employeesDiv = document.getElementById('employees');

    addForm.addEventListener('submit', function (event) {
        event.preventDefault();
        const depName = document.getElementById('itemName').value;

        fetch('http://localhost/api/management/department/', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(depName)
        })
        .then(response => response.json())
        // .then(employee => {
        //     const employeeDiv = document.createElement('div');
        //     employeeDiv.innerHTML = `
        //         <p><strong>Name:</strong> ${employee.name}</p>
        //         <p><strong>Position:</strong> ${employee.position}</p>
        //     `;
        //     employeesDiv.appendChild(employeeDiv);
        // })
        .catch(error => employeesDiv.innerHTML = "<b>Error</b>");
    });

    // You would need similar fetch calls for other CRUD operations
});
