SELECT * FROM student;
SELECT * FROM course;
SELECT * FROM score;

-- Query 1 
SELECT t.*, s.s_score, s.c_id 
FROM student t
JOIN score s ON t.s_id = s.c_id
JOIN (SELECT st.*, sc.s_score, sc.c_id
FROM student st
JOIN score sc ON st.s_id = sc.s_id
WHERE sc.c_id = '02') AS sub ON sub.s_id = t.s_id
WHERE s.c_id = '01' AND s.s_score > sub.s_score; 

-- Query 2 
select st1.*, sc1.s_score AS `score01`, sub.s_score AS `score02`
from student st1
JOIN score sc1 ON st1.s_id = sc1.s_id
JOIN (SELECT st.s_id, sc.s_score, sc.c_id
FROM student st
JOIN score sc ON st.s_id = sc.s_id
WHERE sc.c_id = '02') AS sub ON sub.s_id = st1.s_id 
WHERE sc1.c_id = '01' AND sc1.s_score < sub.s_score;

-- Query 3
SELECT st.s_id AS `student_number`, st.s_name AS `student_name`, AVG(sc.s_score) AS `average_score`
FROM student st
JOIN score sc ON st.s_id = sc.s_id
GROUP BY `student_number`, `student_name`
HAVING `average_score` >= 60;

-- Query 4
SELECT st.s_id AS `student_number`, st.s_name AS `student_name`, AVG(sc.s_score) AS `average_score`
FROM student st
LEFT JOIN score sc ON st.s_id = sc.s_id
GROUP BY `student_number`, `student_name`
HAVING `average_score` < 60 OR `average_score` IS NULL; 

-- Query 5 
SELECT st.s_id AS `student_number`, st.s_name AS `student_name`, COUNT(sc.c_id) AS `total_course`,
SUM(sc.s_score) AS `total_grades`
FROM student st
LEFT JOIN score sc ON st.s_id = sc.s_id
GROUP BY `student_number`, `student_name`;

-- Query 6
SELECT COUNT(t.t_id) AS `number_teacher_with_li` 
FROM teacher t
WHERE t.t_name LIKE 'li%';

-- Query 7
SELECT st.*, t.t_name
FROM student st
JOIN score sc ON st.s_id = sc.s_id
JOIN course cr ON cr.c_id = sc.c_id
JOIN teacher t ON t.t_id = cr.t_id
WHERE t.t_name = "zhangsan";

-- Query 8
SELECT st1.*
FROM student st1
WHERE st1.s_id NOT IN 
(SELECT st.s_id
FROM student st
JOIN score sc ON st.s_id = sc.s_id
JOIN course cr ON cr.c_id = sc.c_id
JOIN teacher t ON t.t_id = cr.t_id
WHERE t.t_name = "zhangsan");

-- Query 9
SELECT st1.* 
FROM student st1
JOIN score sc1 ON st1.s_id = sc1.s_id
WHERE sc1.c_id = '01' AND st1.s_id IN (
SELECT st.s_id
FROM student st
JOIN score sc ON st.s_id = sc.s_id
WHERE sc.c_id = '02'); 

-- Query 10 
SELECT st1.* 
FROM student st1
JOIN score sc1 ON st1.s_id = sc1.s_id
WHERE sc1.c_id = '01' AND st1.s_id NOT IN (
SELECT st.s_id
FROM student st
JOIN score sc ON st.s_id = sc.s_id
WHERE sc.c_id = '02');

-- Query 11
SELECT st1.*
FROM student st1
LEFT JOIN score sc1 ON st1.s_id = sc1.s_id
GROUP BY st1.s_id
HAVING COUNT(sc1.c_id) != (SELECT COUNT(*) from course);

-- Query 12
SELECT DISTINCT st.*
FROM student st
JOIN score sc ON st.s_id = sc.s_id
WHERE sc.c_id IN (
SELECT sc1.c_id
FROM score sc1
WHERE sc1.s_id = '01');

-- Query 13
SELECT st.*
FROM student st
JOIN score sc ON st.s_id = sc.s_id
WHERE sc.c_id IN (
SELECT sc1.c_id
FROM score sc1
WHERE sc1.s_id = '01')
GROUP BY st.s_id
HAVING COUNT(st.s_id) = (SELECT COUNT(sc1.c_id)
FROM score sc1
WHERE sc1.s_id = '01');

-- Query 14
SELECT st1.*
FROM student st1
WHERE st1.s_id NOT IN 
(SELECT st.s_id
FROM student st
JOIN score sc ON st.s_id = sc.s_id
JOIN course cr ON cr.c_id = sc.c_id
JOIN teacher t ON t.t_id = cr.t_id
WHERE t.t_name = "zhangsan"); 

SELECT cr.c_id
FROM course cr
JOIN teacher t ON cr.t_id = t.t_id
WHERE t.t_name = "zhangsan"; 


-- Query 15



-- Query 16
SELECT st.*
FROM student st
JOIN score sc ON st.s_id = sc.s_id
WHERE sc.c_id = '01' AND
sc.s_score < 60
ORDER BY sc.s_score; 

-- Query 18
SELECT cr.c_id, cr.c_name, MAX(sc.s_score) AS `Highest Score`, MIN(sc.s_score) AS `Lowest Score`,
AVG(sc.s_score) AS `Average Score`, 
(CASE
	WHEN AVG(sc.s_score) >= 60 THEN 'Passing Rate'
    WHEN AVG(sc.s_score) >= 70 AND AVG(sc.s_score) < 80 THEN 'Medium Rate'
    WHEN AVG(sc.s_score) >= 80 AND AVG(sc.s_score) < 90 THEN 'Good Rate'
    WHEN AVG(sc.s_score) >= 90 THEN 'Excellent Rate'
	ELSE 'No Rate'
END) AS `Rate`
FROM score sc
JOIN course cr ON sc.c_id = cr.c_id
GROUP BY  cr.c_id, cr.c_name;

-- Query 19
SELECT st.*,sc.s_score,sc.c_id
FROM student st
JOIN score sc ON st.s_id = sc.s_id
ORDER BY sc.c_id, sc.s_score DESC;

-- Query 20
SELECT st.s_id, SUM(sc.s_score) AS `Total Score`
FROM student st
JOIN score sc ON st.s_id = sc.s_id
GROUP BY st.s_id
ORDER BY `Total Score` DESC