# LCRHSLeader-Election
Created by Chang-Woo Ham for the University of Liverpool's COMP 212 Distributed Systems class.

IDE Used: Eclipse IDE.

# Description:
In this program, the user is able to emulate the execution of the LCR and HS leader election algorithms on a network (Represented by a Circular Doubly Linked List of Nodes). The user enters in the number of nodes they want to start with, and the program will return the rounds and messages of the best, worst, and random case of each algorithm. In the end, the user is able to get a data plot in which he/she could use to make a plot of the relationships between the number of messages and number of rounds.

# How to Run the Program after downloading the files:
1) Go in to the src directory and open the main.java file
2) Run main.java, and the terminal should say that 3 files are created or already existing.
3) Then the terminal will ask the user to input your starting number of Processes
4) Then it will ask the user to input the 'Step Count', AKA how many new Processes should be added after each iteration
5) Lastly it will ask the user to enter the Max amount of Processes the program should go up to.
6) After this, the user will see all the results of HS and LCR algorithms for a Clockwise increasing, CounterClockwise increasing, and randomized Network of processes.
7) At the end the User will see how many iterations were executed, and the 3 data files should update as well.

# How to create the Graph in gnuplot using the data files
1) When you first open gnuplot, enter "cd 'directory-where-the-data-files-are-located'". (Ex: cd C:\Users\JohnSmith\eclipse-workspace\Assignment1)
2) Then enter this line: "plot "best.dat" using 1:2 title 'BestCase LCR' with lines, "best.dat" using 3:4 title 'Best and worstcase HS' with lines, 'worst.d
at' using 1:2 title 'WorstCase LCR' with lines, 'test.dat' using 1:2 title 'Random LCR' with lines, 'test.dat' using 3:4 title 'Random HS' 
with lines"
3) You can also enter: 'set xlabel "Number of Rounds"' and 'set ylabel "Number of Messages Sent"' if you want to see the labels 

# Other Notes
1) In each iteration, the program writes only 1 data value for the "best.dat" and "worst.dat", however for random it shuffles the Network 20 times, so 20 data values go in for one iteration.
2) For the sake of time, I recommend these input values: Starting number: 5 (should be at least 3), Step Count: 5, Max Amount: 1000 (Can go up to 2000 if you like).
