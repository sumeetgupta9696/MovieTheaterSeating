# MovieTheaterSeating

### Overview

Implement an algorithm for assigning seats within a movie theater to
fulfill reservation requests. Assume the movie theater has the seating
arrangement of 10 rows x 20 seats, as illustrated to the right.

Your homework assignment is to design and write a seat assignment
program to maximize both customer satisfaction and customer
safety. For the purpose of public safety, assume that a buffer of three
seats and/or one row is required.

### Input File Contents

```
R001 2
R002 4
R003 4
R004 3
```
In the input file content the first column is reservation identifier with format R#### and second column is number of tickets to be reserved for that order. The order if reservation follows the same order as the order in input file.

### Output File Contents Expected

```
R001 F1,F2
R002 F6,F7,F8,F9
R003 F13,F14,F15,F16
R004 D1,D2,D3
```
The output file should contain first column as reservation identifier and second column as the seats number if they are reserved.

### Instructions

1. Clone the repo and go to "src" folder.
2. Compile the Movie and Helper java files using below commands:

> javac movieTheaterSeating\ *.java

3. Run the program with command line arguements including input filepath and output filepath. Below is the sample command:

> java movieTheaterSeating.Movie <Input filepath> <Output filepath>

4. Check the Output filepath for generated output.

### Assumptions
  
  1. As per given information, I have defaulted the theater arrangement for 10 rows and 20 seats which can be changed as per convenience. The rows are named starting from A till J and seats are columned numbering 1 to 20. So seat number are like A1, B1,..., J1.
  2. The customer satisfaction is assumed to have priority given to middle seats and reservtaion should be made in group seated together in single row but can be split if middle rows have available seats. The rows in front of screen and last from screen have less priority then middle ones.
  3. For the purpose of public safety, I have assumned that a buffer of three seats and one row is required.
  4. Seats are allotted starting from the row's left seats available to increase the number of persons who can be seated based on public safety purpose.
  5. If the theater is unable to reserve seats for the entire group in a single row, the group will be divided. We will then begin filling the rows with the largest capacity first, reducing the size of the group. Once the group can be crammed into a single row, we'll go with the row closest to the center.
