CREATE DATABASE StuInfoSystem;

USE StuInfoSystem;

CREATE TABLE stu (
  stuId     		VARCHAR(30)       PRIMARY KEY,
  stuName   		NVARCHAR(50)      NOT NULL,
  stuSex    		NVARCHAR(1)       DEFAULT 'M',
  stuAge    		INT,      
  stuNationality    NVARCHAR(30),
  stuMajor   		NVARCHAR(40)
 );
 
INSERT INTO stu VALUES ('sp001', 'David', 'M', 26, 'China', 'EE');
INSERT INTO stu VALUES ('sp002', 'Lucy',  'F', 23, 'America', 'BE');
INSERT INTO stu VALUES ('sp003', 'Tom',   'M', 25, 'Britain', 'CS');
INSERT INTO stu VALUES ('sp004', 'Jane',  'F', 21, 'France', 'CE');
INSERT INTO stu VALUES ('sp005', 'Alex',  'M', 22, 'Russia', 'EE');
