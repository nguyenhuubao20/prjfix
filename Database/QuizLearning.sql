CREATE DATABASE QuizLearning
GO

USE QuizLearning
GO

-- Create Users Table
CREATE TABLE Users (
    UserID INT PRIMARY KEY IDENTITY,
    Username NVARCHAR(50) NOT NULL,
    Password NVARCHAR(50) NOT NULL -- Store hashed passwords in practice
);

-- Create Decks Table
CREATE TABLE Decks (
    DeckID INT PRIMARY KEY IDENTITY,
    UserID INT FOREIGN KEY REFERENCES Users(UserID),
    DeckName NVARCHAR(100),
    DailyLimit INT -- New column to store daily limit for each deck
);

-- Create Cards Table
CREATE TABLE Cards (
    CardID INT PRIMARY KEY IDENTITY,
    DeckID INT FOREIGN KEY REFERENCES Decks(DeckID),
    CorrectOption NVARCHAR(MAX)
);

-- Create WrongOptions Table
CREATE TABLE WrongOptions (
    WrongOptionID INT PRIMARY KEY IDENTITY,
    CardID INT FOREIGN KEY REFERENCES Cards(CardID),
    WrongOption NVARCHAR(MAX)
);

-- Create Repetitions Table
CREATE TABLE Reviews (
    ReviewID INT PRIMARY KEY IDENTITY,
    CardID INT FOREIGN KEY REFERENCES Cards(CardID),
    ReviewDate DATE,
    Ease INT, -- typically a scale to measure how well the user remembered the card
    NextReviewDate DATE,
    Interval INT -- days until the card needs to be reviewed again
);
