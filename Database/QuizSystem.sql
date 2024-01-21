CREATE DATABASE QuizSystem
GO

USE QuizSystem
GO

-- Creating Roles Table with IDENTITY
CREATE TABLE Roles (
    RoleID INT IDENTITY(1,1) PRIMARY KEY,
    Role NVARCHAR(255)
);

INSERT INTO Roles (Role) VALUES ('student');
INSERT INTO Roles (Role) VALUES ('admin');
INSERT INTO Roles (Role) VALUES ('teacher');

-- Creating Users Table with IDENTITY
CREATE TABLE Users (
    UserID INT IDENTITY(1,1) PRIMARY KEY,
    UserName NVARCHAR(255),
    RoleID INT,
    Email NVARCHAR(255),
    Password NVARCHAR(255),
    FOREIGN KEY (RoleID) REFERENCES Roles(RoleID)
);

-- Thêm thông tin cho người dùng với vai trò là student
INSERT INTO Users (UserName, RoleID, Email, Password)
VALUES ('student1', 1, 'student1@example.com', 'password1');

INSERT INTO Users (UserName, RoleID, Email, Password)
VALUES ('student', 1, 'student@example.com', '1');

INSERT INTO Users (UserName, RoleID, Email, Password)
VALUES ('student2', 1, 'student2@example.com', 'password2');

INSERT INTO Users (UserName, RoleID, Email, Password)
VALUES ('student3', 1, 'student3@example.com', 'password3');

-- Thêm thông tin cho người dùng với vai trò là admin
INSERT INTO Users (UserName, RoleID, Email, Password)
VALUES ('admin1', 2, 'admin1@example.com', 'adminpassword');

-- Thêm thông tin cho người dùng với vai trò là teacher
INSERT INTO Users (UserName, RoleID, Email, Password)
VALUES ('teacher1', 3, 'teacher1@example.com', 'teacherpassword');


-- Creating Classes Table with IDENTITY
CREATE TABLE Classes (
    ClassID INT IDENTITY(1,1) PRIMARY KEY,
    ClassName NVARCHAR(255)
);

-- Creating QuestionSets Table with IDENTITY
CREATE TABLE QuestionSets (
    QuestionSetID INT IDENTITY(1,1) PRIMARY KEY,
    UserID INT,
    QuestionSetName NVARCHAR(255),
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);

-- Creating Questions Table with IDENTITY
CREATE TABLE Questions (
    QuestionID INT IDENTITY(1,1) PRIMARY KEY,
    QuestionSetID INT,
	QuestionText NVARCHAR(1000),
    AnswerText NVARCHAR(1000),
	FOREIGN KEY (QuestionSetID) REFERENCES QuestionSets(QuestionSetID)
);

--SELECT QuestionSetID, UserID, QuestionSetName FROM QuestionSets
--SELECT QuestionID, QuestionSetID, QuestionText, AnswerText FROM Questions

-- Creating Tests Table with IDENTITY
CREATE TABLE Tests (
    TestID INT IDENTITY(1,1) PRIMARY KEY,
    ClassID INT,
    QuestionSetID INT,
    Time TIME,
    DateTaken DATE,
    FOREIGN KEY (ClassID) REFERENCES Classes(ClassID),
    FOREIGN KEY (QuestionSetID) REFERENCES QuestionSets(QuestionSetID)
);

-- Creating QuestionInTest Table
CREATE TABLE QuestionInTest (
	TestID INT,
    QuestionID INT,
    FOREIGN KEY (TestID) REFERENCES Tests(TestID),
    FOREIGN KEY (QuestionID) REFERENCES Questions(QuestionID)
);

-- Creating TestAttempts Table with IDENTITY
CREATE TABLE TestAttempts (
    TestAttemptID INT IDENTITY(1,1) PRIMARY KEY,
    TestID INT,
    StudentID INT,
    Marks INT,
    Grade NVARCHAR(255),
    FOREIGN KEY (TestID) REFERENCES Tests(TestID),
    FOREIGN KEY (StudentID) REFERENCES Users(UserID)
);

-- Creating Review Table with IDENTITY
CREATE TABLE Review (
    ReviewID INT IDENTITY(1,1) PRIMARY KEY,
    QuestionID INT,
    ReviewDate DATE,
    NextReviewDate DATE,
    Ease NVARCHAR(255),
    Interval INT,
    FOREIGN KEY (QuestionID) REFERENCES Questions(QuestionID)
);


