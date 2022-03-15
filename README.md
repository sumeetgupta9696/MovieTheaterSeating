# MovieTheaterSeating
Overview

Implement an algorithm for assigning seats within a movie theater to
fulfill reservation requests. Assume the movie theater has the seating
arrangement of 10 rows x 20 seats, as illustrated to the right.

Your homework assignment is to design and write a seat assignment
program to maximize both customer satisfaction and customer
safety. For the purpose of public safety, assume that a buffer of three
seats and/or one row is required.

Input File Contents

R001 2
R002 4
R003 4
R004 3

Output File Contents Expected

R001 F1,F2
R002 F6,F7,F8,F9
R003 F13,F14,F15,F16
R004 D1,D2,D3

Instructions

1. Clone the repo and go to "src" folder.
2. Compile the Movie and Helper java files using below commands:

javac movieTheaterSeating\*.java

3. Run the program with command line arguements including input filepath and output filepath. Below is the sample command:

java movieTheaterSeating.Movie <Input filepath> <Output filepath>

4. Check the Output filepath for generated output.

Assumptions
