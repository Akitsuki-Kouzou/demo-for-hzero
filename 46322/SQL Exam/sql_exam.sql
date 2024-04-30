/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : sql_exam

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 29/04/2024 15:52:18
*/

CREATE DATABASE sqlexam;
USE sqlexam;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `c_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `c_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '',
  `t_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`c_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('01', 'yuwen', '02');
INSERT INTO `course` VALUES ('02', 'shuxue', '01');
INSERT INTO `course` VALUES ('03', 'yingyu', '03');

-- ----------------------------
-- Table structure for score
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score`  (
  `s_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `c_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '',
  `s_score` int(3) NULL DEFAULT NULL,
  PRIMARY KEY (`s_id`, `c_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of score
-- ----------------------------
INSERT INTO `score` VALUES ('01', '01', 80);
INSERT INTO `score` VALUES ('01', '02', 90);
INSERT INTO `score` VALUES ('01', '03', 99);
INSERT INTO `score` VALUES ('02', '01', 70);
INSERT INTO `score` VALUES ('02', '02', 60);
INSERT INTO `score` VALUES ('02', '03', 80);
INSERT INTO `score` VALUES ('03', '01', 80);
INSERT INTO `score` VALUES ('03', '02', 80);
INSERT INTO `score` VALUES ('03', '03', 80);
INSERT INTO `score` VALUES ('04', '01', 50);
INSERT INTO `score` VALUES ('04', '02', 30);
INSERT INTO `score` VALUES ('04', '03', 20);
INSERT INTO `score` VALUES ('05', '01', 76);
INSERT INTO `score` VALUES ('05', '02', 87);
INSERT INTO `score` VALUES ('06', '01', 31);
INSERT INTO `score` VALUES ('06', '03', 34);
INSERT INTO `score` VALUES ('07', '02', 89);
INSERT INTO `score` VALUES ('07', '03', 98);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `s_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `s_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '',
  `s_brith` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '',
  `s_sex` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '',
  PRIMARY KEY (`s_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('01', 'zhaolei', '1990-01-01', 'M');
INSERT INTO `student` VALUES ('02', 'qiandian', '1990-12-21', 'M');
INSERT INTO `student` VALUES ('03', 'sunfeng', '1990-05-20', 'M');
INSERT INTO `student` VALUES ('04', 'liyun', '1990-08-06', 'M');
INSERT INTO `student` VALUES ('05', 'zhoumei', '1991-12-01', 'F');
INSERT INTO `student` VALUES ('06', 'wulan', '1992-03-01', 'F');
INSERT INTO `student` VALUES ('07', 'zhengzhu', '1989-07-01', 'F');
INSERT INTO `student` VALUES ('08', 'wangju', '1990-01-20', 'F');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `t_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `t_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '',
  PRIMARY KEY (`t_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('01', 'zhangsan');
INSERT INTO `teacher` VALUES ('02', 'lisi');
INSERT INTO `teacher` VALUES ('03', 'wangwu');

SET FOREIGN_KEY_CHECKS = 1;

-- 1
SELECT * FROM student;
SELECT * FROM score;
SELECT * FROM course;

SELECT 
	st.s_id, 
    st.s_name, 
    sc.c_id
FROM student st
JOIN score sc ON st.s_id = sc.s_id;
-- WHERE sc.s_score 

SELECT sc.s_score, sc.c_id
FROM score sc, (
	SELECT sc.s_score
	FROM score sc
	WHERE sc.c_id = '01'
) AS numberone;

SELECT(
	SELECT sc.s_score
	FROM score sc
	WHERE sc.c_id = '01'
)AS numberone;

-- 3
SELECT 
	st.s_id, 
    st.s_name,
    avg(sc.s_score)
FROM student st
LEFT JOIN score sc ON st.s_id = sc.s_id
GROUP BY 
	st.s_id, 
    st.s_name
HAVING AVG(s_score) >= 60;

-- 4
SELECT 
	st.s_id, 
    st.s_name,
    avg(sc.s_score)
FROM student st
JOIN score sc ON st.s_id = sc.s_id
GROUP BY 
	st.s_id, 
    st.s_name
HAVING AVG(s_score) < 60;

-- 5
SELECT 
	st.s_id, 
    st.s_name, 
    COUNT(sc.c_id) AS TOTAL_COURSES,
    SUM(sc.s_score) AS TOTAL_GRADES
FROM student st
JOIN score sc ON st.s_id = sc.s_id
GROUP BY
	st.s_id, 
    st.s_name;

-- 6
SELECT * FROM teacher;
SELECT COUNT(*) AS 'Teacher with surname Li'
FROM teacher
WHERE t_name LIKE 'li%';

-- 7
SELECT st.s_name, t.t_name
FROM student st
JOIN score sc ON st.s_id = sc.s_id
JOIN course c ON sc.c_id = c.c_id
JOIN teacher t ON c.t_id = t.t_id
WHERE t.t_name = 'zhangsan';

-- 8
SELECT st.s_name, t.t_name
FROM student st
RIGHT JOIN score sc ON st.s_id = sc.s_id
RIGHT JOIN course c ON sc.c_id = c.c_id
RIGHT JOIN teacher t ON c.t_id = t.t_id
WHERE t.t_name <> 'zhangsan';

-- 9
SELECT st.s_id, c.c_id
FROM student st
JOIN score sc ON st.s_id = sc.s_id
JOIN course c ON c.c_id = sc.c_id
WHERE c.c_id = 01 OR c.c_id = 02;

-- 10
SELECT DISTINCT st.s_id, c.c_id
FROM student st
JOIN score sc ON st.s_id = sc.s_id
JOIN course c ON c.c_id = sc.c_id
WHERE c.c_id = 01 AND c.c_id <> 02;

-- 11
SELECT st.s_id
FROM student st
JOIN score sc ON st.s_id = sc.s_id
JOIN course c ON sc.c_id = c.c_id
GROUP BY st.s_id
HAVING COUNT(sc.c_id) < 3;

-- 12
SELECT sc1.s_id
FROM score sc1
JOIN score sc2 ON sc1.s_id = sc2.s_id
WHERE sc1.s_id = sc2.s_id;


-- 13

-- 14
SELECT st.s_name
FROM student st
JOIN score sc ON st.s_id = sc.s_id
JOIN course c ON sc.c_id = c.c_id
JOIN teacher t ON c.t_id = t.t_id
WHERE t.t_id <> 'zhangsan';

-- 17
SELECT * FROM score;
SELECT * FROM student;
SELECT * FROM course;

SELECT *
FROM score sc
JOIN student st ON sc.s_id = st.s_id
JOIN course c ON sc.c_id = c.c_id;

SELECT
	s_id,
    s_name;

-- 18
SELECT
	c.c_id,
	c.c_name,
    MAX(sc.s_score),
    MIN(sc.s_score),
    AVG(sc.s_score)
FROM score sc
JOIN course c ON sc.c_id = c.c_id
GROUP BY 
	c.c_id,
	c.c_name;

-- 19
SELECT sc.s_id
FROM score sc, (
	SELECT sc.s_id, sc.c_id, SUM(s_score)
	FROM score sc
	GROUP BY sc.s_id, sc.c_id
) AS ranking,
ORDER BY ranking;

-- 19
SELECT st.s_id
FROM student st, (
	SELECT sc.c_id, SUM(s_score) AS totalscore
	FROM score sc
	GROUP BY sc.c_id
) AS totaldesc
ORDER BY totalscore;

SELECT st.s_id,
    (SELECT sc.c_id, SUM(s_score) AS totalscore
	FROM score sc
	GROUP BY sc.c_id
    ) AS totaldesc
FROM student st
JOIN student st ON sc.s_id = st.s_id
JOIN course c ON sc.c_id = c.c_id;

ORDER BY totalscore;

SELECT st.st_id
FROM student st
SELECT sc.c_id, SUM(s_score) AS totalscore
FROM score sc
GROUP BY sc.c_id
ORDER BY totalscore;

-- 20 
SELECT 
	st.s_id,
    st.s_name,
    SUM(sc.s_score) AS total_scores
FROM student st
JOIN score sc ON st.s_id = sc.s_id
GROUP BY
	st.s_id,
    st.s_name
ORDER BY total_scores DESC;