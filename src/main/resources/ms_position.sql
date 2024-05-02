-- Create ms_position table
CREATE TABLE ms_position(
		position_code VARCHAR(10) PRIMARY KEY NOT NULL,
    	position_name TEXT NOT NULL,
    	department_code VARCHAR(10) NOT NULL
)

-- Query All data in ms_position table
SELECT * FROM ms_position;