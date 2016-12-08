import java.io.*;
import java.util.*;

/**
 * This program can determine if a text file has an equal number of parentheses and end parentheses
 * using a Generic Stack
 * 
 * @author Grady McPeak 
 * @version One
 */
public class ParenthesesCheck
{
    /**
     * This is the main method, where all the claculations are done. For more code, see the 
     * "Stack" class.
     * 
     * @param - args - Command Line argument
     * @return - none
     */
    public static void main (String args []) {
        Stack <Integer> parenCount = new Stack <Integer> (); 
        //will be used to keep track of how many parentheses and end parentheses there are
        
        try {
            boolean isEqual = true;
            
            ArrayList<Character> letters = new ArrayList<Character> ();
            Scanner scan;
            scan = new Scanner(new BufferedReader(new FileReader("parenthesestext.txt")));
            String text = scan.nextLine(); 
            
            for (int i = 0; i < text.length(); i++) { 
                letters.add (text.charAt (i)); //ArrayList of all the characters
            }
            
            for (char d : letters) {
                String str = Character.toString (d);
                if (str.equals("(")) {
                    if (isEqual == false) {
                        //if there's aleady a closing parenthesis without an open one,
                        //DONT WORRY because it's already wrong. 
                    }
                    else {
                        parenCount.push (1); //tallying open parentheses
                    }
                }
         
                if (str.equals(")")){
                    if (parenCount.pop() == null) {
                        isEqual = false; 
                        //checking to see if there is a correspondig open parenthesis
                    }
                    else {
                        parenCount.pop();
                        //tallying end parenthesis
                    }
                }
            }
            
            if ((isEqual != false) && (parenCount.pop() == null)) {
                //checking if there are enough closing parentheses to match the open ones
                isEqual = true;
            }
            else {
                isEqual = false;
            }
            
            if (isEqual == true) {
                System.out.println ("Equal number of parentheses.");
            }
            else {
                System.out.println ("Unequal number of parentheses.");
            }
        }
            catch (Exception e) {
            System.out.println("WAT: " + e.getMessage());
            System.exit(0); //immediately quits
        }
    }
}