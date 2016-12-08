import java.io.*;
import java.util.Scanner;
/**
 * "Infinite Adder" is a program with the ability to add two integers as long as the computer's memory could hold, as long
 * as they are in text files labeled "num1.txt" and "num2.txt" with each digit separated by a comma and in reverse order.
 * 
 * @author Grady McPeak 
 * @version 1.0
 */
public class InfiniteAdder {
    public static void main (String args []) {
        /**
         * @param args - Command line argument
         */
        Node headOne = new Node (0);
        headOne = readFirstNumber (headOne);
        
        Node headTwo = new Node (0);
        headTwo = readSecondNumber(headTwo);
        
        Node bigSumHead = new Node (0);
        bigSumHead = findBigSum (headOne, headTwo, bigSumHead);
        
        printSum (bigSumHead);
    }
    
    public static Node readFirstNumber (Node headOne) {
        /**
         * This method reads from the file "num1.txt" and puts each digit into a node of a linked list
         * @param - headOne - the head of the firswt linked list of digits
         * @return - headOne - (see above)
         */
        
        try {
            Node pointer = headOne;
            
            Scanner scan; //reading file
            scan = new Scanner(new BufferedReader(new FileReader("num1.txt")));
            scan.useDelimiter(",");
            
            while(scan.hasNext()) { 
                pointer.setNext (new Node (scan.nextInt())); //putting number into node
                pointer = pointer.getNext ();
            }
        }
        catch (Exception e) {
            System.out.println ("Error! System failure! Program don't work! Milk store's best cow died!" + e.getMessage());
        }
        
        return (headOne);
    }
    
    public static Node readSecondNumber (Node headTwo) {
        /**
         * This method reads from the file "num2.txt" and puts each digit into a node of a linked list
         * @param - headTwo - the second linked list's head
         * @return - headTwo (see above)
         */
        
        try {
            Node pointer = headTwo;
            
            Scanner scan; //readig file
            scan = new Scanner(new BufferedReader(new FileReader("num2.txt")));
            scan.useDelimiter(",");
            
            while(scan.hasNext()) { 
                pointer.setNext (new Node (scan.nextInt())); //putting number into node
                pointer = pointer.getNext ();
            }
        }
        catch (Exception e) {
            System.out.println ("Error! System failure! Program don't work! Milk store's best cow died!" + e.getMessage());
        }
        
        return (headTwo);
    }
    
    public static Node findBigSum (Node headOne, Node headTwo, Node bigSumHead) { 
        /**
         * This method adds the two numbers together and puts their digits into a single linked list
         * @param headOne - the head of the first linked list of digits
         * @param headTwo - the head of the second linked list of digits
         * @param bigSumHead - the head of the linked list conatining the digits of the sum of the two big numbers
         * @return bigSumHead - (see above)
         */
        
        Node pointerOne = headOne;
        Node pointerTwo = headTwo;
        Node sumPointer = bigSumHead;
        int carryOver = 0;
        
        while(pointerOne != null || pointerTwo != null) {
            
            sumPointer.setNext (new Node (pointerOne.getValue() + pointerTwo.getValue() + carryOver));
            
            if ((pointerOne.getValue() + pointerTwo.getValue()) >= 10) {
                sumPointer = sumPointer.getNext();
                int number = (sumPointer.getValue() - 10);
                carryOver = 1;
                sumPointer.setValue(number);
                pointerOne = pointerOne.getNext();
                pointerTwo = pointerTwo.getNext();
            }
            else {
                pointerOne = pointerOne.getNext();
                pointerTwo = pointerTwo.getNext();
                sumPointer = sumPointer.getNext();                
            }
        }
        
        if (carryOver == 1) {
            sumPointer.setNext (new Node (1));
        }
        
        return (bigSumHead);
    }
    
    public static void printSum (Node bigSumHead) { 
        /**
         * This method prints the sum of the two large numbers in the terminal window.
         * @param - bigSumHead - the head of a linked list of the digits of the sum
         * @return - (none)
         */
        
        /**
         * Go to last node in list. Link the list backwards.
         */
        Node sumReader = bigSumHead.getNext();
        Node sumPrinter = new Node (0);
        Node previous = sumReader;
        
        while (sumReader.getNext() != null) { 
            sumReader = sumReader.getNext();
            sumReader.setPrev(previous);
            previous = sumReader;
        }
        
        /**
         * print out digits
         */
        
        while (sumReader.getPrev() != null) {
            System.out.print (sumReader.getValue());
            sumReader = sumReader.getPrev();
        }
    }
}