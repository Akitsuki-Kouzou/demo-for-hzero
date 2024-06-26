CREATE DATABASE tutorial2train;
USE tutorial2train;
-- Table Countries --
CREATE TABLE 
	countries(COUNTRY_ID CHAR(2) NOT NULL,
	COUNTRY_NAME VARCHAR(40),
	REGION_ID INT);
DESCRIBE countries;
INSERT INTO countries(
	COUNTRY_ID, 
    COUNTRY_NAME, 
    REGION_ID
    )
VALUES 
	('CA', 'Canada', 2), 
    ('GE', 'Germany', 1),
	('UK', 'United Kingdom', 1), 
    ('US', 'United States of America', 2);
SELECT * FROM countries;
ALTER TABLE countries ADD PRIMARY KEY(COUNTRY_ID);

-- Table Departments --
CREATE TABLE 
	departments(DEPARTMENT_ID INT(4) NOT NULL,
	DEPARTMENT_NAME VARCHAR(30) NOT NULL,
	MANAGER_ID INT(6),
	LOCATION_ID INT(4));
DESCRIBE departments;
INSERT INTO departments(
	DEPARTMENT_ID, 
    DEPARTMENT_NAME, 
    MANAGER_ID, 
    LOCATION_ID)
VALUES 
	(10, 'Administration', 200, 1700), 
    (20, 'Marketing', 201, 1800), 
    (50, 'Shipping', 124, 1500),
    (60, 'IT', 103, 1400), 
    (80, 'Sales', 149, 2500), 
    (90, 'Executive', 100, 1700),
	(110, 'Accounting', 205, 1700), 
    (190, 'Contracting', NULL, '1700');
SELECT * FROM departments;
ALTER TABLE departments ADD PRIMARY KEY(DEPARTMENT_ID);

-- Table Departments --
CREATE TABLE employees(
	EMPLOYEE_ID NUMERIC(6) NOT NULL,
	FIRST_NAME VARCHAR(20),
	LAST_NAME VARCHAR(25) NOT NULL,
	EMAIL VARCHAR(25) NOT NULL,
	PHONE_NUMBER VARCHAR(20),
	HIRE_DATE DATE NOT NULL,
	JOB_ID VARCHAR(10) NOT NULL,
	SALARY NUMERIC(8, 2),
	COMMISION_PCT NUMERIC(2, 2),
	MANAGER_ID NUMERIC(6),
	DEPARTMENT_ID NUMERIC(4));
DESCRIBE employees;
INSERT INTO employees(
	EMPLOYEE_ID, 
    FIRST_NAME, 
    LAST_NAME, 
    EMAIL, 
    PHONE_NUMBER, 
    HIRE_DATE, 
    JOB_ID, 
    SALARY, 
    COMMISION_PCT, 
    MANAGER_ID, 
    DEPARTMENT_ID)
VALUES 
	(100, 'Steven', 'King', 'SKING', '515.123.4567', '1987-06-17',  'AD_PRES', 24000, NULL, NULL, 90),
	(101, 'Neena', 'Kochhar', 'NKOCHHAR', '515.123.4568', '1889-09-21', 'AD_VP', 17000, NULL, 100, 90),
	(102, 'Lex', 'De Haan', 'LDEHAAN', '515.123.4569', '1993-01-13', 'AD_VP', 17000, NULL, 100, 90),
	(103, 'Alexander', 'Hunold', 'AHUNOLD', '590.423.4567', '1990-01-03', 'IT_PROG', 9000, NULL, 102, 60),
	(104, 'Bruce', 'Ernst', 'BERNST', '590.423.4568', '1991-05-21', 'IT_PROG', 6000, NULL, 103, 60),
	(107, 'Diana', 'Lorentz', 'DLORENTZ', '590.423.5567', '1999-02-07', 'IT_PROG', 4200, NULL, 103, 60),
	(124, 'Kewin', 'Mourgos', 'KMOURGOS', '650.123.5234', '1999-11-16', 'ST_MAN', 5800, NULL, 100, 50),
	(141, 'Trenna', 'Rajs', 'TRAJS', '650.121.8009', '1995-10-17',  'ST_CLERK', 3500, NULL, 124, 50),
	(142, 'Curtis', 'Davies', 'CDAVIES', '650.121.2994', '1997-01-29', 'ST_CLERK', 3100, NULL, 124, 50),
	(143, 'Randall', 'Matos', 'RMATOS', '650.121.2874', '1998-03-15', 'ST_CLERK', 2600, NULL, 124, 50),
	(144, 'Peter', 'Vargas', 'PVARGAS', '650.121.2004', '1998-07-09', 'ST_CLERK', 2500, NULL, 124, 50),
	(149, 'Eleni', 'Zlotkey', 'EZLOTKEY', '011.44.1344.429018', '2000-01-29', 'SA_MAN', 10500, 0.2, 100, 80),
	(174, 'Ellen', 'Abel', 'EABEL', '011.44.1644.429267', '1996-05-11', 'SA_REP', 11000, 0.3, 149, 80),
	(176, 'Jonathon', 'Taylor', 'JTAYLOR', '011.44.1644.429265', '1998-03-24', 'SA_REP', 8600, 0.2, 149, 80),
	(178, 'Kimberely', 'Grant', 'KGRANT', '011.44.1644.429263', '1999-05-24', 'SA_REP', 7000, 0.15, 149, NULL),
	(200, 'Jennifer', 'Whalen', 'JWHALEN', '515.123.4444', '1987-09-17', 'AD_ASST', 4400, NULL, 101, 10),
	(201, 'Michael', 'Hartstein', 'MHARTSTE', '515.123.5555', '1996-02-17', 'MK_MAN', 13000, NULL, 100, 20),
	(202, 'Pat', 'Fay', 'PFAY', '603.123.6666', '1997-08-17', 'MK_REP', 6000, NULL, 201, 20),
	(205, 'Shelley', 'Higgins', 'SHIGGINS', '515.123.8080', '1994-07-07', 'AC_MGR', 12000, NULL, 101, 110),
	(206, 'William', 'Gietz', 'WGIETZ', '515.123.8181', '1994-06-07', 'AC_ACCOUNT', 8300, NULL, 205, 110);
SELECT * FROM employees;
ALTER TABLE employees ADD PRIMARY KEY (EMPLOYEE_ID);
UPDATE employees SET FIRST_NAME = 'Kevin'
WHERE EMPLOYEE_ID = 124;

-- Table Jobs --
CREATE TABLE jobs(
	JOB_ID VARCHAR(10) NOT NULL, 
	JOB_TITLE VARCHAR(35) NOT NULL,
	MIN_SALARY NUMERIC(6),
	MAX_SALARY NUMERIC(6));
DESCRIBE jobs;
INSERT INTO jobs (
	JOB_ID, 
    JOB_TITLE, 
    MIN_SALARY, 
    MAX_SALARY) 
VALUES
	('AD_PRES', 'President', 20000, 40000),
	('AD_VP', 'Administration Vice President', 15000, 30000),
	('AD_ASST', 'Administration Assistant', 3000, 6000),
	('AC_MGR', 'Accounting Manager', 8200, 16000),
	('AC_ACCOUNT', 'Public Accountant', 4200, 9000),
	('SA_MAN', 'Sales Manager', 10000, 20000),
	('SA_REP', 'Sales Representative', 6000, 12000),
	('ST_MAN', 'Stock Manager', 5500, 8500),
	('ST_CLERK', 'Stock Clerk', 2000, 5000),
	('IT_PROG', 'Programmer', 4000, 10000),
	('MK_MAN', 'Marketing Manager', 9000, 15000),
	('MK_REP', 'Marketing Representative', 4000, 9000);
SELECT * FROM jobs;
ALTER TABLE jobs ADD PRIMARY KEY(JOB_ID);

-- Table Grades --
CREATE TABLE job_grades(
	GRADE_LEVEL VARCHAR(3),
	LOWEST_SAL NUMERIC,
	HIGHEST_SAL NUMERIC);
INSERT INTO job_grades(
	GRADE_LEVEL, 
	LOWEST_SAL, 
    HIGHEST_SAL)
VALUES 
	('A', 1000, 2999), 
	('B', 3000, 5999),
	('C', 6000, 9999), 
	('D', 10000, 14999),
	('E', 15000, 24999), 
	('F', 25000, 40000);
SELECT * FROM job_grades;
ALTER TABLE job_grades ADD PRIMARY KEY(GRADE_LEVEL);

-- Table Job History --
CREATE TABLE job_history(
	EMPLOYEE_ID NUMERIC(6) NOT NULL,
	START_DATE DATE NOT NULL,
	END_DATE DATE NOT NULL,
	JOB_ID VARCHAR(10) NOT NULL,
	DEPARTMENT_ID NUMERIC(4));
DESCRIBE job_history;
INSERT INTO job_history (
	EMPLOYEE_ID, 
    START_DATE, 
    END_DATE, 
    JOB_ID, 
    DEPARTMENT_ID) 
VALUES 
	(102, '1993-01-13', '1998-07-24', 'IT_PROG', 60),
	(101, '1989-09-21', '1993-10-27', 'AC_ACCOUNT', 110),
	(101, '1993-10-28', '1997-03-15', 'AC_MGR', 110),
	(201, '1996-02-17', '1999-12-19', 'MK_REP', 20),
	(114, '1998-03-24', '1999-12-31', 'ST_CLERK', 50),
	(122, '1999-01-01', '1999-12-31', 'ST_CLERK', 50),
	(200, '1987-09-17', '1993-06-17', 'AD_ASST', 90),
	(176, '1998-03-24', '1998-12-31', 'SA_REP', 80),
	(176, '1999-01-01', '1999-12-31', 'SA_MAN', 80),
	(176, '1994-07-01', '1999-12-31', 'AC_ACCOUNT', 90);
INSERT INTO job_history (
	EMPLOYEE_ID, 
    START_DATE, 
	END_DATE, 
    JOB_ID, 
    DEPARTMENT_ID) 
VALUES (200, '1994-07-01', '1998-12-31', 'AC_ACCOUNT', 90);
SELECT * FROM job_history;

-- Table Locations -- 
CREATE TABLE locations(
	LOCATION_ID NUMERIC(4),
	STREET_ADDRESS VARCHAR(40),
	POSTAL_CODE VARCHAR(12),
	CITY VARCHAR(30) NOT NULL,
	STATE_PROVINCE VARCHAR(25),
	COUNTRY_ID CHAR(2));
DESCRIBE locations;
INSERT INTO locations (
	LOCATION_ID, 
    STREET_ADDRESS, 
    POSTAL_CODE, 
    CITY, 
    STATE_PROVINCE, 
    COUNTRY_ID)
VALUES
	(1400, '2014 Jabberwocky Rd', '26192', 'Southlake', 'Texas', 'US'),
	(1500, '2011 Interiors Blvd', '99236', 'South San Francisco', 'California', 'US'),
	(1700, '2004 Charade Rd', '98199', 'Seattle', 'Washington', 'US'),
	(1800, '460 Bloor St. W.', 'M5S 1X8', 'Toronto', 'Ontario', 'CA'),
	(2500, 'The Oxford Science Park', 'OX9 9ZB', 'Oxford', 'Oxford', 'UK');
ALTER TABLE locations ADD PRIMARY KEY(LOCATION_ID);

-- Table Regions --
CREATE TABLE regions(
	REGION_ID NUMERIC NOT NULL,
	REGION_NAME VARCHAR(25));
DESCRIBE regions;
SELECT * FROM regions;
INSERT INTO regions(
	REGION_ID, 
	REGION_NAME)
VALUES 
	(1, 'Europe'), 
	(2, 'Americas'),
	(3, 'Asia'), 
    (4, 'Middle East and Africa');
ALTER TABLE regions ADD PRIMARY KEY(REGION_ID);

-- 1
SELECT * FROM employees 
WHERE YEAR(HIRE_DATE) > 1997 AND JOB_ID = 'ST_CLERK';

-- 2
SELECT 
	LAST_NAME, 
    JOB_ID, 
    SALARY, 
    COMMISION_PCT 
FROM employees
WHERE COMMISION_PCT IS NOT NULL ORDER BY SALARY DESC;

-- 3
SELECT CONCAT('The salary of ', LAST_NAME, ' after a 10% raise is ', ROUND(SALARY+(SALARY*0.1))) AS 'New salary'
FROM employees WHERE COMMISION_PCT IS NULL;

-- 4
SELECT 
	e.LAST_NAME, 
    SUM(timestampdiff(YEAR, jh.START_DATE, jh.END_DATE)) + FLOOR(SUM(timestampdiff(MONTH, jh.START_DATE, jh.END_DATE) - timestampdiff(YEAR, jh.START_DATE, jh.END_DATE)*12) / 12) AS 'YEARS', 
    MOD(SUM(timestampdiff(MONTH, jh.START_DATE, jh.END_DATE) - timestampdiff(YEAR, jh.START_DATE, jh.END_DATE)*12),12) AS 'MONTHS'
FROM employees e
JOIN job_history jh ON e.EMPLOYEE_ID = jh.EMPLOYEE_ID
GROUP BY e.LAST_NAME;

-- 5
SELECT LAST_NAME 
FROM employees
WHERE 
	LAST_NAME LIKE 'J%' OR
	LAST_NAME LIKE 'K%' OR
	LAST_NAME LIKE 'L%' OR
	LAST_NAME LIKE 'M%';

-- 6
SELECT 
	LAST_NAME, 
    ROUND(SALARY),
CASE
	WHEN COMMISION_PCT IS NOT NULL THEN 'Yes' 
    ELSE 'No' 
END AS COM
FROM employees;

-- 7
SELECT 
	d.DEPARTMENT_NAME, 
    d.LOCATION_ID,
	e.LAST_NAME, 
    e.JOB_ID, 
    e.SALARY
FROM employees e
LEFT JOIN departments d ON d.DEPARTMENT_ID = d.DEPARTMENT_ID
WHERE d.LOCATION_ID = 1800;

-- 8
SELECT COUNT(*) AS 'COUNT(*)'
FROM employees
WHERE LAST_NAME LIKE '%n';

SELECT COUNT(*) AS 'COUNT(*)'
FROM employees
WHERE RIGHT(LAST_NAME, 1) = 'n';

-- 9
SELECT 
	d.DEPARTMENT_ID, 
    d.DEPARTMENT_NAME,
    d.LOCATION_ID, 
    COUNT(e.EMPLOYEE_ID) AS 'COUNT(E.EMPLOYEE_ID)'
FROM departments d
LEFT JOIN employees e ON d.DEPARTMENT_ID = e.DEPARTMENT_ID
GROUP BY 
	d.DEPARTMENT_ID, 
	d.DEPARTMENT_NAME,
	d.LOCATION_ID;

-- 10
SELECT JOB_ID 
FROM employees
WHERE DEPARTMENT_ID = 10 OR DEPARTMENT_ID = 20;

-- 11
SELECT 
	e.JOB_ID, 
    COUNT(*) AS FREQUENCY
FROM employees e
JOIN departments d ON e.DEPARTMENT_ID = d.DEPARTMENT_ID
WHERE d.DEPARTMENT_NAME IN ('Administration', 'Executive')
GROUP BY e.JOB_ID
ORDER BY COUNT(*) DESC;

-- 12
SELECT 
	LAST_NAME, 
    HIRE_DATE
FROM employees
WHERE DAY(HIRE_DATE) < 16;

-- 13
SELECT 
	LAST_NAME, 
	ROUND(SALARY), 
    ROUND(SALARY/1000) AS THOUSANDS
FROM employees;

-- 14
SELECT 
	e.LAST_NAME,
    m.LAST_NAME AS MANAGER,
    ROUND(m.SALARY),
    (SELECT grade_level 
     FROM job_grades WHERE lowest_sal <= m.salary AND highest_sal >= m.salary) AS GRA
FROM employees e
JOIN employees m ON e.MANAGER_ID = m.EMPLOYEE_ID
WHERE m.SALARY >= 15000
ORDER BY m.SALARY DESC;

-- NOTES FOR THE SUBQUERY's IDEA
-- select grade_level from job_grades
-- where lowest_sal <= 24000
-- and highest_sal >= 24000;

-- 15    
SELECT 
	d.DEPARTMENT_ID, 
    d.DEPARTMENT_NAME, 
    COUNT(e.EMPLOYEE_ID) OVER(PARTITION BY d.DEPARTMENT_ID) AS EMPLOYEES,
	ROUND(AVG(e.SALARY) OVER(PARTITION BY d.DEPARTMENT_ID)) AS AVG_SAL, 
    e.LAST_NAME, 
    ROUND(e.SALARY), 
    e.JOB_ID
FROM departments d
LEFT JOIN employees e ON d.DEPARTMENT_ID = e.DEPARTMENT_ID
GROUP BY 
	d.DEPARTMENT_ID,
	d.DEPARTMENT_NAME,
	e.LAST_NAME, 
	e.SALARY, 
	e.JOB_ID,
	e.EMPLOYEE_ID
ORDER BY d.DEPARTMENT_ID;

-- 16
SELECT d.DEPARTMENT_ID, ROUND(MIN(e.SALARY)) AS 'MIN_SALARY'
FROM departments d,
( 	SELECT DEPARTMENT_ID, AVG(SALARY) AS average
	FROM employees
	GROUP BY DEPARTMENT_ID
	ORDER BY average DESC
    LIMIT 1
) AS highest,
employees e 
WHERE d.DEPARTMENT_ID = e.DEPARTMENT_ID AND d.DEPARTMENT_ID = highest.DEPARTMENT_ID
GROUP BY d.DEPARTMENT_ID;

-- 17                       
SELECT 
	DEPARTMENT_ID, 
    DEPARTMENT_NAME, 
    MANAGER_ID, 
    LOCATION_ID
FROM departments;

-- 18
-- Include fewer than 3 employees --
SELECT 
	d.DEPARTMENT_ID, 
	d.DEPARTMENT_NAME, 
	COUNT(e.DEPARTMENT_ID) AS `COUNT(*)`
FROM departments d
LEFT JOIN employees e ON d.DEPARTMENT_ID = e.DEPARTMENT_ID
GROUP BY d.DEPARTMENT_ID, d.DEPARTMENT_NAME
HAVING `COUNT(*)`< 3 AND `COUNT(*)` != 0 ;

-- 18
-- Has the highest number of employees --
SELECT 
	d.DEPARTMENT_ID, 
    d.DEPARTMENT_NAME, 
    COUNT(e.EMPLOYEE_ID) AS `COUNT(*)`
FROM departments d
JOIN employees e ON d.DEPARTMENT_ID = e.DEPARTMENT_ID
GROUP BY 
	d.DEPARTMENT_ID, 
    d.DEPARTMENT_NAME
HAVING COUNT(e.EMPLOYEE_ID) = (
    SELECT MAX(emp_count)
    FROM (
        SELECT COUNT(EMPLOYEE_ID) AS emp_count
        FROM employees
        GROUP BY DEPARTMENT_ID
    ) AS emp_counts
);

-- 18
-- Has the lowest number of employees --
SELECT 
	d.DEPARTMENT_ID, 
    d.DEPARTMENT_NAME, 
    COUNT(e.EMPLOYEE_ID) AS EMPLOYEE_COUNT
FROM departments d
JOIN employees e ON d.DEPARTMENT_ID = e.DEPARTMENT_ID
GROUP BY 
	d.DEPARTMENT_ID, 
	d.DEPARTMENT_NAME
HAVING COUNT(e.EMPLOYEE_ID) = (
    SELECT MIN(emp_count)
    FROM (
        SELECT COUNT(EMPLOYEE_ID) AS emp_count
        FROM employees
        GROUP BY DEPARTMENT_ID
    ) AS emp_counts
);

-- 19
SELECT 
    e.EMPLOYEE_ID,
    e.LAST_NAME,
    e.SALARY,
    e.DEPARTMENT_ID,
    d.avg_salary AS AVERAGE_DEPARTMENT_SALARY
FROM 
    employees e
JOIN (
    SELECT 
        DEPARTMENT_ID, 
        AVG(SALARY) AS avg_salary
    FROM employees
    GROUP BY DEPARTMENT_ID
) d ON e.DEPARTMENT_ID = d.DEPARTMENT_ID
ORDER BY e.EMPLOYEE_ID;

-- 20
SELECT 
    LAST_NAME,
    DAYNAME(HIRE_DATE) AS DAY
FROM employees
WHERE DAYOFWEEK(HIRE_DATE) = (
        SELECT DAYOFWEEK(HIRE_DATE)
        FROM employees
        GROUP BY DAYOFWEEK(HIRE_DATE)
        ORDER BY COUNT(*) DESC
        LIMIT 1
    );

-- 21    
SELECT 
	LAST_NAME,
    DATE_FORMAT(HIRE_DATE, '%M %d') AS BIRTHDAY
FROM employees
ORDER BY MONTH(HIRE_DATE) ASC, DAY(HIRE_DATE) ASC;

-- 22    
SELECT DISTINCT (JOB_ID)
FROM employees
WHERE
	(YEAR(HIRE_DATE) = "1990" AND MONTH(HIRE_DATE) <= 6)
    OR (YEAR(HIRE_DATE) = "1991" AND MONTH(HIRE_DATE) <= 6);

-- 23
SELECT 	
    '05% raise' AS RAISE,
	EMPLOYEE_ID,
    ROUND(SALARY),
    ROUND(SALARY * 1.05) AS NEW_SALARY
FROM employees
WHERE DEPARTMENT_ID IN (10, 50, 110)
UNION ALL
SELECT 
    '10% raise' AS RAISE,
    EMPLOYEE_ID,
    ROUND(SALARY),
    ROUND(SALARY * 1.10) AS NEW_SALARY
FROM employees
WHERE DEPARTMENT_ID = 60
UNION ALL
SELECT 
    '15% raise' AS RAISE,
	EMPLOYEE_ID,
    ROUND(SALARY),
    ROUND(SALARY * 1.15) AS NEW_SALARY
FROM employees
WHERE DEPARTMENT_ID IN (20, 80)
UNION ALL
SELECT 
	'no raise' AS RAISE,
    EMPLOYEE_ID,
    ROUND(SALARY),
    ROUND(SALARY) AS NEW_SALARY
FROM employees
WHERE DEPARTMENT_ID = 90;

-- 26
SELECT
	LAST_NAME,
    MONTH(HIRE_DATE) AS `EXTRACT(MONTHFROMHIRE_DATE)`,
    HIRE_DATE
FROM employees
WHERE MONTH(HIRE_DATE) = 01;

-- 27
SELECT
	l.CITY,
    d.DEPARTMENT_NAME AS DNAME,
    e.JOB_ID AS JOB,
    SUM(e.SALARY) AS 'SUM(E.SALARY)'
FROM employees e
JOIN departments d ON e.DEPARTMENT_ID = d.DEPARTMENT_ID
JOIN locations l ON d.LOCATION_ID = l.LOCATION_ID
WHERE d.DEPARTMENT_ID > 80
GROUP BY
	l.CITY,
    DNAME,
    JOB
WITH ROLLUP;

SELECT 
	d.DEPARTMENT_ID,
    e.JOB_ID,
    e.MANAGER_ID,
    MAX(e.SALARY),
    MIN(e.SALARY)
FROM employees e
JOIN departments d ON e.DEPARTMENT_ID = d.DEPARTMENT_ID
GROUP BY
	d.DEPARTMENT_ID,
    e.JOB_ID,
    e.MANAGER_ID
ORDER BY d.DEPARTMENT_ID;

SELECT
	d.DEPARTMENT_ID AS news,
    j.JOB_ID,
    d.MANAGER_ID,
    MAX(e.SALARY),
    MIN(e.SALARY)
FROM (
	SELECT
		d.DEPARTMENT_ID,
		j.JOB_ID,
		d.MANAGER_ID,
		MAX(e.SALARY),
		MIN(e.SALARY)
	FROM departments d
	RIGHT JOIN employees e ON d.DEPARTMENT_ID = e.DEPARTMENT_ID
	JOIN jobs j ON e.JOB_ID = j.JOB_ID
	GROUP BY
		d.DEPARTMENT_ID,
		j.JOB_ID,
		d.MANAGER_ID
	ORDER BY d.DEPARTMENT_ID
) AS newdepartment
UNION ALL
SELECT
	NULL AS DEPARTMENT_ID,
	j.JOB_ID,
    e.MANAGER_ID,
    MAX(e.SALARY),
    MIN(e.SALARY)
FROM jobs j
LEFT JOIN employees e ON j.JOB_ID = e.JOB_ID
GROUP BY
	j.JOB_ID,
    e.MANAGER_ID;


-- 29
SELECT 
	LAST_NAME,
    ROUND(SALARY)
FROM employees
ORDER BY SALARY DESC
LIMIT 3;

-- 30
SELECT
	e.EMPLOYEE_ID,
    e.LAST_NAME
FROM 
	employees e,
    departments d,
    locations l
WHERE e.DEPARTMENT_ID = d.DEPARTMENT_ID AND d.LOCATION_ID = l.LOCATION_ID
AND l.STATE_PROVINCE = 'California';

-- 31
DELETE FROM job_history
WHERE EMPLOYEE_ID = (
	SELECT jh.EMPLOYEE_ID
    FROM 
		employees e, 
        job_history jh
    WHERE jh.EMPLOYEE_ID = e.EMPLOYEE_ID
		AND START_DATE = (
			SELECT MIN(START_DATE)
            FROM job_history jh
            WHERE jh.EMPLOYEE_ID = e.EMPLOYEE_ID
        )
        AND 3 > (
			SELECT COUNT(*)
            FROM job_history jh
            WHERE jh.EMPLOYEE_ID = e.EMPLOYEE_ID
            GROUP BY jh.EMPLOYEE_ID
            HAVING COUNT(*) >=2)
);


-- 32

-- 33
WITH MAX_SAL_CALC AS (
    SELECT 
		j.JOB_TITLE,
        MAX(e.SALARY) AS JOB_TOTAL
        FROM employees e, jobs j
        WHERE e.job_ID = j.JOB_ID
        GROUP BY j.JOB_TITLE
)
SELECT 
	JOB_TITLE, 
    JOB_TOTAL
FROM MAX_SAL_CALC
WHERE job_total > (
	SELECT MAX(JOB_TOTAL) * 0.5
    FROM MAX_SAL_CALC
);

-- 34a
SELECT
	EMPLOYEE_ID,
    LAST_NAME,
    HIRE_DATE,
    SALARY
FROM employees
WHERE MANAGER_ID = (
	SELECT EMPLOYEE_ID
    FROM employees
    WHERE LAST_NAME = 'De Haan'
);

-- 34b
SELECT
	EMPLOYEE_ID,
    LAST_NAME,
    HIRE_DATE,
    SALARY
FROM employees
WHERE MANAGER_ID = (
	SELECT EMPLOYEE_ID
    FROM EMPLOYEES
    WHERE LAST_NAME = 'De Haan'
);

