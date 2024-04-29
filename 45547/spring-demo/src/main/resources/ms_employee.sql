-- Create ms_employee table
CREATE TABLE ms_employee(
		employee_code VARCHAR(10) PRIMARY KEY NOT NULL,
    	employee_name TEXT NOT NULL,
    	join_date DATE NOT NULL,
    	department_code VARCHAR(10),
    	position_code VARCHAR(10)
)

-- Query All data in ms_employee table
SELECT * FROM ms_employee;