package movieTheaterSeating;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Movie {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
        if(args.length != 2) {
            System.out.println("Please give path of an input file and output file in same order");
            System.exit(0);
        }
        
        Helper hp = new Helper(10, 20);
        
        try(FileReader file = new FileReader(args[0])){
            BufferedReader inputFile = new BufferedReader(file);
            PrintWriter outputFile = new PrintWriter(args[1]);
            String currentLine;
            while ((currentLine = inputFile.readLine()) != null) {
                String[] input = currentLine.split(" ");
                String reservation = hp.reserveSeats(Integer.parseInt(input[1]));
                if (reservation == null) {
                	outputFile.append(input[0] + " " + "FULL" + "\n");
                    System.out.println("Sorry no seats left" + input[0]);
                } else {
                	outputFile.append(input[0] + " " + reservation + "\n");
                }
            }
            outputFile.close();
        } catch(IOException e){
            System.out.println("Input file error");
            System.exit(0);
        }
	}

}

