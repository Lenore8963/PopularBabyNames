# PopularBabyNames
This Java mini project provides an analysis of baby names from the United States based on the popularity and rank of each name over the years. The dataset used in this project is in CSV format and contains the name, gender, and the number of babies born with that name for each year.

This is a Coursera course assignment.
Course:
Java Programming: Solving Problems with Software
https://www.coursera.org/learn/java-programming?specialization=object-oriented-programming

## Features
The BabyBirths class includes the following methods:

- totalBirths(FileResource fr): Calculates and prints the total number of births, number of male and female babies, and the number of unique names for each gender.
- getFileParser(int year): Returns a CSV parser for a specific year.
- getRecordByRank(int year, int rank, String gender): Returns a CSV record by rank, year, and gender.
- getRank(int year, String name, String gender): Returns the rank of a name in a specific year and gender.
- getName(int year, int rank, String gender): Returns a name based on the rank, year, and gender.
- whatIsNameInYear(String name, int year, int newYear, String gender): Prints the equivalent name in the new year based on the rank, year, and gender.
- yearOfHighestRank(String name, String gender): Returns the year with the highest rank for a given name and gender.
- getAverageRank(String name, String gender): Returns the average rank of a name across all years.
- getTotalBirthsRankedHigher(int year, String name, String gender): Returns the total number of babies born with names ranked higher than the given name in a specific year and gender.
- test(): Runs test cases to verify the functionality of the methods.

## Dependencies
This project depends on the following libraries:

- edu.duke
- org.apache.commons.csv

## Usage

- Compile the BabyBirths class:
```java
javac BabyBirths.java
```
- Run the BabyBirths class:
```java
java BabyBirths
```
The test() method contains a set of test cases to verify the functionality of the implemented methods. You can modify the test() method to test different names, years, and genders.

## Dataset
The dataset used in this project is stored in the us_babynames directory, organized by year. Each file is named yobYYYY.csv, where YYYY is the year of the data. You can obtain them from the Social Security Administration's baby names dataset (https://www.ssa.gov/oact/babynames/limits.html).
