import java.util.*;
import java.io.*;

/**
 * This program takes in a text file called "maze.txt" and reports the most efficient way to solve the maze.
 * Notes: 
 *  must be at most a 100x100 maze
 *  the start must be marked with "@"
 *  the paths must be marked with dots "." and the walls with octothorpes ("#")
 *  the endpoint must be marked with an American dollar sign ("$")
 * 
 * @author Grady McPeak
 * @version Three
 */
public class MazeSolver
{
    static Stack <String> visited = new Stack <String> ();
    static int [][] maze = new int [100][100];
    
    /**
     * @param - args [] - command line argument
     * @return - n/a
     */
    public static void main (String [] args) {
        String currSpot = "";
        int [] startCoords = new int [2];
        startCoords = makeMaze(currSpot, startCoords);
        solveMaze(currSpot, startCoords);
        printSolution();
    }
    
    /**
     * @param - currSpot - the point in the maze the program is currently at (used here to mark the end point)
     * @param - startCoords - the x-value and y-value of the endpoint (program solves from end to beginning)
     * @return - startCoords
     */
    public static int[] makeMaze (String currSpot, int [] startCoords) {
        System.out.println ("Here is your maze: ");
        
        Scanner scan;
        String currLine = "";
        
        try { 
           scan = new Scanner(new BufferedReader(new FileReader("maze.txt")));  
           
           for (int count1 = 0; scan.hasNextLine() == true; count1++) {
               currLine = scan.nextLine();
               System.out.println (currLine);
               
               for (int count2 = 0; count2 <= currLine.length(); count2++) {
                   try{
                   
                       if (currLine.substring(count2, count2+1).equals ("$")) { // turns maze of string into 
                                                                                // maze of int
                        maze [count1][count2] = 0;
                       
                        currSpot = (count1 + "," + count2 + " ");
                        startCoords [0] = count1;
                        startCoords [1] = count2;
                       }
                       else if (currLine.substring(count2, count2 +1).equals ("#")) {
                        maze [count1][count2] = 1;
                       }
                       else if (currLine.substring(count2, count2 +1).equals (".")) {
                        maze [count1][count2] = 2;
                       }
                       else if (currLine.substring(count2, count2 +1).equals ("@")) {
                        maze [count1][count2] = 3;
                       }
                       else {
                        maze [count1][count2] = 1;
                       }
                   }
                   catch (Exception e) {
                       maze [count1] [count2] = 1;
                   }
               }
               
               if (scan.hasNextLine() == false) {
                   for (int count3 = 0; count3 <= currLine.length(); count3++) {
                       maze[count1+1] [count3] = 1; //makes extra walls at ends in case of empty edges
                   }
               }
           }
        }
        catch (Exception e) {
            System.out.print ("Error: " + e.getMessage() + ".");
        }
        
        System.out.println ("");
        
        return (startCoords);
    }
    
    /**
     * @param - currSpot - the coordinates of the location of the program in the maze
     * @param - startCoords - the x and y values of the start
     * @return - n/a
     */    
    public static void solveMaze (String currSpot, int [] startCoords) {
        int x = startCoords[0];
        int y = startCoords[1];
        currSpot = x + "," + y + " ";
        visited.push(currSpot);
        
        while (maze [x][y] != 3) {
            for (int count = 0; count < (currSpot.length()); count++){
                if (visited.peek().substring (count, count+1).equals(",") == true) {
                    x = Integer.parseInt(currSpot.substring(0, count));
                    y = Integer.parseInt(currSpot.substring(count+1, currSpot.length()-1));
                }
            }
            
            try {
                
                while (maze [x+1][y] != 1) { //going down
                    maze[x][y] = 1;
                    x++;
                    currSpot = x + "," + y + " ";
                    visited.push(currSpot);
                }
                
                while (maze [x-1][y] != 1) { //going up
                    maze[x][y] = 1;
                    x = x-1;
                    currSpot = x + "," + y + " ";
                    visited.push(currSpot);
                }
                
                while (maze [x][y+1] != 1) { //going right
                    maze[x][y] = 1;
                    y++;
                    currSpot = x + "," + y + " ";
                    visited.push(currSpot);
                }
                
                while (maze [x][y-1] != 1) { //going left
                    maze[x][y] = 1;
                    y = y-1;
                    currSpot = x + "," + y + " ";
                    visited.push(currSpot);
                }
                
                if  (isDeadEnd (currSpot, x, y) == true) {
                    currSpot = backTrack (currSpot);
                }
                
            }
            catch (Exception e) { //reaches end of array
                if (maze[x][y] == 3) {
                }
                else {
                    maze[x][y] = 1;
                
                    visited.pop();
                
                    for (int count = 0; count < (visited.peek().length()); count++){
                        if (visited.peek().substring (count, count+1).equals(",") == true) {
                            x = Integer.parseInt(visited.peek().substring(0, count));
                            y = Integer.parseInt(visited.peek().substring(count+1, visited.peek().length()-1));
                        }
                    }
                
                    currSpot = x + "," + y + " ";
                }
            }
        }
    }
    
    /**
     * @param - currSpot - the coordinates of the location of the program in the maze
     * @param x - the x-value of the current spot in the maze
     * @param y - the y-value of the current spot in the maze
     */
    public static boolean isDeadEnd (String currSpot, int x, int y) { 
        boolean yes = false;
        
        if (maze [x+1][y] == 1) {
            if (maze [x-1][y] == 1) {
                if (maze [x][y+1] == 1) {
                   if (maze [x][y-1] == 1) {
                       if (maze[x][y] != 3) {
                           yes = true;
                       }
                   }
                }
            }
        }
        
        return yes;
    }
    
    /**
     * @param - currSpot - the coordinates of the location of the program in the maze
     * @return - currSpot - the coordinates of the location of the program in the maze
     */
    public static String backTrack (String currSpot) {
        visited.pop();
        
        int x = 0;
        int y = 0;
        
        int i = 0;
        int j = 0;
        
        for (int count = 0; count < (currSpot.length()); count++)
        {
            if (currSpot.substring (count, count+1).equals(",") == true) {
                x = Integer.parseInt(currSpot.substring(0, count));
                y = Integer.parseInt(currSpot.substring(count+1, currSpot.length()-1));
            }
        }
        
        
        for (int count = 0; count < (visited.peek().length()); count++)
        {
            if (visited.peek().substring (count, count+1).equals(",") == true) {
                i = Integer.parseInt(visited.peek().substring(0, count));
                j = Integer.parseInt(visited.peek().substring(count+1, visited.peek().length()-1));
            }
        }
        
        maze [i] [j] = 2;
        maze [x][y] = 1;
        currSpot = i + "," + j + " ";
        
        return (currSpot);
    }
    
    /**
     * @param - n/a
     * @return - n/a
     */
    public static void printSolution () {        
        System.out.println ("The answer is as follows: ");
        
        while (visited.peek() != null) {
            System.out.println (visited.pop());
        }
    }
}