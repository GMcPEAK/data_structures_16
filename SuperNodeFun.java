import java.io.*;
import java.util.Scanner;
/**
 * The following program is a homework assignment that reads a text file called "nums.txt" and puts all the
 * numbers into individual nodes, and then prints all the numbers
 * from the nodes in order they appear.
 * 
 * @author Grady McPeak 
 * @version One
 */
public class SuperNodeFun
{
    public static void main (String args []) {
        Node head = new Node (0);

        try {
            Scanner scan; //readig file
            scan = new Scanner(new BufferedReader(new FileReader("nums.txt")));
            scan.useDelimiter(",");
            
            while(scan.hasNext()) {
                head.setNext (new Node (scan.nextInt())); //putting number into node
                head = head.getNext ();
                
                readNodes(head);
            }
        }
        catch (Exception e) {
            System.out.println ("WAT" + e.getMessage());
        }
    }
    
    public static void readNodes(Node head) {
        Node curr = head;
        while(curr != null) {
            System.out.println(curr.getValue()); //prints out numbers
            curr = curr.getNext();
        }
    }
}