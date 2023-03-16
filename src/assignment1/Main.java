package assignment1;

import java.io.File;  // Import the File class
import java.io.FileWriter;
import java.io.IOException;  // Import the IOException class to handle errors
import java.util.*;

public class Main {
	
	//Putting some repeating code here so that the main function
	//Isn't too crowded
	public static void createBestF() {
		try {
		      File myObj = new File("best.dat");
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		      } else {
		        System.out.println("'best.dat' already exists.");
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	public static void createWorstF() {
		try {
		      File myObj = new File("worst.dat");
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		      } else {
		        System.out.println("'worst.dat' already exists.");
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	public static void createTestF() {
		try {
		      File myObj = new File("test.dat");
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		      } else {
		        System.out.println("'test.dat' already exists.");
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	public static void initializeFiles(int LCRmsg, int HSmsg) {
		//Writing initial values on the files
				try {
			      FileWriter myWriter = new FileWriter("best.dat");
			      myWriter.write("0 " + LCRmsg + " 0 " + HSmsg);
			      myWriter.close();
			      System.out.println("Successfully wrote to the file.");
			    } catch (IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			    }
				
				try {
				      FileWriter myWriter = new FileWriter("worst.dat");
				      myWriter.write("0 " + LCRmsg + " 0 " + HSmsg);
				      myWriter.close();
				      System.out.println("Successfully wrote to the file.");
				    } catch (IOException e) {
				      System.out.println("An error occurred.");
				      e.printStackTrace();
				    }
				
				try {
				      FileWriter myWriter = new FileWriter("test.dat");
				      myWriter.write("0 " + LCRmsg + " 0 " + HSmsg);
				      myWriter.close();
				      System.out.println("Successfully wrote to the file.");
				    } catch (IOException e) {
				      System.out.println("An error occurred.");
				      e.printStackTrace();
				    }
	}
	
	public static void writeBestF(int LCRRound, int LCRmsg, int HSRound, int HSmsg) {
		try {
		      FileWriter myWriter = new FileWriter("best.dat", true);
		      myWriter.write("\n" + LCRRound + " " + LCRmsg + " " + HSRound + " "+ HSmsg);
		      myWriter.close();
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	public static void writeWorstF(int LCRRound, int LCRmsg, int HSRound, int HSmsg) {
		try {
		      FileWriter myWriter = new FileWriter("worst.dat", true);
		      myWriter.write("\n" + LCRRound + " " + LCRmsg + " " + HSRound + " "+ HSmsg);
		      myWriter.close();
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	public static void writeTestF(int LCRRound, int LCRmsg, int HSRound, int HSmsg) {
		try {
		      FileWriter myWriter = new FileWriter("test.dat", true);
		      myWriter.write("\n" + LCRRound + " " + LCRmsg + " " + HSRound + " "+ HSmsg);
		      myWriter.close();
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}

	//Start of main
	public static void main(String[] args) {
		int LCRRound = 0;
		int HSRound = 0;
		int LCRmsg = 0;
		int HSmsg = 0;
		int totSimulation = 0;
		
		//Initial creation of the data files
		//All data files hold 4 values in each line structured as follows:
		//(Rounds of LCR, Messages sent in LCR, Rounds of HS, Messages sent in HS)
		createBestF();
		createWorstF();
		createTestF();
		
		
		//User-inputed values for starting # of processes
		Scanner scan = new Scanner(System.in);
	    System.out.print("Enter Starting amount of Processes (at least 4 preferred): ");

	    int numP = scan.nextInt();
	    
	    //User-inputed number for adding how many processes should be added in each iteration
	    System.out.print("\nEnter value to step by: ");
	    
	    int numStep = scan.nextInt();
	    
	    //Up to how many Processes are allowed
	    System.out.print("\nEnter the max amount of processes: ");
	    
	    int maxP = scan.nextInt();
	    
	    // Closing Scanner after the use
	    scan.close();
	   
		Network test = new Network(numP);
		
		initializeFiles(LCRmsg, HSmsg);
		
		//Running both algorithms and getting the Best, Worst and Random samples.
		while(test.size() <= maxP) {
			//Testing for sorted network
			test.sortNetwork();
			LCRmsg = test.LCRElectLeader();
			LCRRound = test.round;
			test.resetNetwork();
			HSmsg = test.HSElectLeader();
			HSRound = test.round;
			totSimulation += 2;
			
			writeBestF(LCRRound, LCRmsg, HSRound, HSmsg);
			
			//Testing for reversed sorted network
			test.reverse();
			LCRmsg = test.LCRElectLeader();
			LCRRound = test.round;
			test.resetNetwork();
			HSmsg = test.HSElectLeader();
			HSRound = test.round;
			totSimulation += 2;
			
			
			writeWorstF(LCRRound, LCRmsg, HSRound, HSmsg);
			
			for(int i = 0; i < 20; i++) {
				//Testing for randomized network
				test.shuffleNetwork();
				LCRmsg = test.LCRElectLeader();
				LCRRound = test.round;
				test.resetNetwork();
				HSmsg = test.HSElectLeader();
				HSRound = test.round;
				totSimulation += 2;
				
				writeTestF(LCRRound, LCRmsg, HSRound, HSmsg);
			}
			
			for(int i = 1; i < numStep + 1; i++) {
				test.add(new Process(test.size() + 1));
			}
		}
		
		System.out.println("\nTotal number of Simulations: " + totSimulation);
		test.resetNetwork();
	}

}
