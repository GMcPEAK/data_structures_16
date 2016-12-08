import java.io.*;
import java.util.*;
/**
 * Write a description of class PolishCalculator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PolishCalculator
{
    public void main (String args []) { 
        Stack <String> tokens = new Stack <String> ();
        try {
            Scanner scan;
            scan = new Scanner(new BufferedReader(new FileReader("polish.txt")));
            
            while (scan.hasNext()) {
                tokens.push (scan.next());
            }
            
            String operand1 = null;
            String operand2 = null;
            String operator = null;
            ArrayList <String> extra = new ArrayList <String> ();
            
            while (tokens.peek() != null) {
                if (tokens.peek().equals("+") || tokens.peek().equals ("-") || tokens.peek().equals ("/") || tokens.peek().equals ("*")){
                    operator = tokens.pop ();
              
                    if (operator.equals ("+")) {
                        tokens.push ((Integer.parseInt(operand1) + Integer.parseInt(operand2)) + "");
                        operand1 = null;
                        operand2 = null;
                        operator = null;
                    }
                    else if (operator.equals ("-")) {
                        tokens.push ((Integer.parseInt(operand1) - Integer.parseInt(operand2)) + "");
                        operand1 = null;
                        operand2 = null;
                        operator = null;                        
                    }
                    else if (operator.equals ("*")) {
                        tokens.push ((Integer.parseInt(operand1) * Integer.parseInt(operand2)) + "");
                        operand1 = null;
                        operand2 = null;
                        operator = null;
                    }
                    else if (operator.equals ("/")) {
                        tokens.push ((Integer.parseInt(operand1) / Integer.parseInt(operand2)) + "");
                        operand1 = null; 
                        operand2 = null;
                        operator = null;
                    }
                }
                else if (operand2.equals(null) && operand1.equals (null)){
                    while (tokens.peek() != ("+") || tokens.peek() !=("-") || tokens.peek() !=("/") || tokens.peek() != ("*")){
                        ArrayList.add(tokens.pop());
                    }
                }
                else {
                    operand2 =  (tokens.pop ());
                    operand1 =  (tokens.pop ());
                }
            }
            System.out.println ("The answer is " + operand2 + ".");
        }
        catch (Exception e) {
            System.out.println("Error! " + e.getMessage());
        }
    }
}