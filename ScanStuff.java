/**
 * This program reads a file called "nums.txt", a text file of a series of integers separated by commas,
 * and adds the numbers together. Then, the program prints the sum.
 * @author Grady McPeak
 */

import java.io.*;
import java.util.Scanner;

public class ScanStuff {
    /**
      * @param agrs - Command Line Argument
      * @return - nothing
      */
     
    public static void main(String [] args) {
        try {
            int output = 0;
            
            Scanner scan;
            scan = new Scanner(new BufferedReader(new FileReader("nums.txt")));

            scan.useDelimiter(","); //separates by a comma

            while(scan.hasNext()) {
                output = output + (scan.nextInt());
            }
            
            System.out.println ("The sum is " + output);
        }
        catch (Exception e) {
            System.out.println("WAT: " + e.getMessage());
            System.exit(0); //immediately quits
        }
    }
}