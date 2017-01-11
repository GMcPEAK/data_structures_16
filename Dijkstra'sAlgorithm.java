import java.io.*;
import java.util.Scanner;
import java.util.*;
/**
 * This is an implementation of Dijksrtra's algorithm. It uses a text file (called "graph.txt") of a matrix, with the first number
 * to indicate the dimensions of the following matrix. It makes use of a Binary Seach Tree to denotate the visited nodes, a Stack
 * to keep track of the nodes, and a MinHeap.
 * 
 * @author Grady McPeak 
 * @version 1
 */
public class Djikstras
{
    /**
     * The main method.
     * @param String args [] - command line argument
     */
    public static void main (String args []) {
        System.out.println ("Welcome to Downtown Djikstratown!");
        int size = getGraphSize();
        int [][] graph = makeGraph(size);
        int [] sae = new int [2]; 
        input(sae, graph, size);
    }
    
    /**
     * Finds the tentative costs of traveling to each node
     * @param int [][] graph - the graph you are traversing, represented as a matrix
     * @param int [] sae - the start and end of the path the user wishes to travel
     * @param int size - the dimensions of the graph, represented by one integer, because every graph is a square
     * @return graph - the matrix that represents the graph - updated with new tentative costs
     */
    public static int[][] setTentCosts (int[][] graph, int[] sae, int size) {
        int start = sae [0]; //current / "From" node
        int end = sae [1];
        int tentCost = 0;
        MinHeap heap = new MinHeap();
        heap.insert (start, 0);
        DBST <Integer> visited = new DBST <Integer> ();
        visited.insert (heap.peekNode());
        
        for (int x = start; x != end;) {
            for (int y = 0; y < size; y++) {
                if (y == start) {
                    y++;
                }
                
                try {
                    if (visited.exists(y) == true) {
                        try {
                            if (graph[x][y] != -1) {
                                graph[x][y] = graph[x][y] + heap.peek();
                                heap.insert (graph[x][y], y);
                            }
                            else { 
                                heap.insert (y, Integer.MAX_VALUE);
                            }
                        }
                        catch (Exception e) {
                        }
                    }
                }
                catch (Exception NullPointerException) {
                    try {
                        if (graph[x][y] != -1) {
                            graph[x][y] = graph[x][y] + heap.peek();
                            heap.insert (graph[x][y], y);
                        }
                        else { 
                            heap.insert (Integer.MAX_VALUE, y);
                        }
                    }
                    catch (Exception e) {
                    }
                }
            }
            visited.insert (heap.peekNode());
            heap.delete();
            x = heap.peekNode();
        } 
        
        return graph;
    }
    
    /**
     * Creates the graph as a matrix represented by a 2D array
     * @param - size - the dimensions of the graph, represented as one int due to it beng a square
     * @return graph - the graph to traverse, represented as a matrix
     */
    public static int [][] makeGraph (int size) {
        int [][] graph = new int [size][size];
        try {
            Scanner scan;
            scan = new Scanner(new BufferedReader(new FileReader("graph.txt")));
            scan.nextInt();
        
            for (int y = 0; y < size; y++){
                for(int x = 0; x < size; x++){
                    graph [y][x] = scan.nextInt();
                }
            }
            
            System.out.println ("File reading complete!"); 
        }
        catch (Exception e) {
            System.out.println ("There was an error trying to read your graph file.");
        } 
        
        return graph;
    }
    
    /**
     * Determines the size of the graph
     * @param - n/a
     * @return - size - the size of the matrix
     */
    public static int getGraphSize () {
        int size = 0;
        try {
            Scanner scan;
            scan = new Scanner(new BufferedReader(new FileReader("graph.txt")));
            size = scan.nextInt();
        }
        catch (Exception e) {
            System.out.println ("There was an error trying to determine the size of your graph." + e.getMessage());
        }
        
        return size;
    }
    
    /**
     * Asks the user if they want to quit the program, and if not, 
     * * what nodes they want to find the shortest path between
     * @param int [] i - the start node and goal node of the traversal
     * @param int [][] graph - the graph, represented by a matrix
     * @param int size - the dimensions of the graph
     * @return - n/a
     */
    public static void input (int [] i, int [][] graph, int size) {
        while (1<2) {
            try {
                System.out.println ("What node to start at? (-1 to quit)");
                Scanner scan;
                scan = new Scanner(System.in);
                int x = scan.nextInt();
            
                if (x <= -1) {
                    quit();
                }
                else {
                    i [0] = x;
                }
            
                System.out.println ("What node to end at?");
                scan = new Scanner(System.in);
                i [1] = scan.nextInt();
            }
            catch (Exception e) {
            }
        
            graph = setTentCosts(graph, i, size);
            findPath(graph, i, size);
        }
    }
    
    
    /**
     * Once the tentative calculated have been calculated, this method actually determines the shortest path
     * @param int [][] graph - the matrix
     * @param int [] i - the indices of the start node and goal node of the graph
     */
    public static void findPath (int[][] graph, int [] i, int size) {
        MinHeap heap = new MinHeap ();
        Stack <Integer> path = new Stack <Integer> ();
        DBST <Integer> visited = new DBST <Integer> ();
        path.push (i[1]);
        
        for (int x = i[1]; x != i[0];) {
            visited.insert (x);
            
            int a = Integer.MAX_VALUE;
            int b = -1;
            for (int y = 0; y < size; y++) {
                int z = graph [x][y];
                
                try {
                    if (z > 0) {
                        if (visited.exists(y) == false) {
                            if (z < a) {
                                a = z;
                                b = y;
                            }
                        }
                    }
                }
                catch (Exception e) {
                    if (z < a) {
                        a = z;
                        b = y;
                    }
                }
            }
            x = b;
            path.push (x);
        } 
        
        String way = "";
        
        while (path.peek() != null) {
            way = way + path.pop() + " -> ";
        }
        
        way = way + "done!";
        
        System.out.println ("The path is:");
        System.out.println (way);
    }
    
    /**
     * Quits the program
     */
    public static void quit () {
        System.out.println ("Goodbye!");
        System.exit(0);
    }
    
    /**
     * This class is a binary search tree that contains a nested BSTNode class specialized for BST's.
     * Conatins methods to insert, in-order print, and search for a value
     * 
     * @author Grady McPeak 
     * @version One
     */
    public static class DBST <T> {
        BSTNode <T> root;
        
        /**
         * @param (T insertMe) - the value to be inserted into the tree
         */
        public void insert(T insertMe) {
            BSTNode curr = root;
            BSTNode <T> newLeaf = new BSTNode <T> (insertMe);
            try {
                root.getValue();
                insertHelper (newLeaf, curr);
            }
            catch (Exception NullPointerException) {
                root = new BSTNode <T> (insertMe);
            }
        }
        
        /**
         */
        public void inOrderPrint() {
            BSTNode curr = root;
            inOrderPrintHelper (curr);
        }
        
        /**
         * @param (curr) - a pointer used to traverse the BST
         */
        private void inOrderPrintHelper (BSTNode <T> curr) {
            if (curr != null) {
                inOrderPrintHelper(curr.getLeft());
                System.out.println (curr.getValue());
                inOrderPrintHelper (curr.getRight());
            }
        }
        
        /**
         * @param (T checkMe) - the value to be searched for
         * @return match - a true/false as to wether or not there is a match
         */
        public boolean exists(T checkMe) {
            BSTNode curr = root;
            BSTNode checkNode = new BSTNode <T> (checkMe);
            boolean match = false;
            if ((existsHelper(curr, checkNode).getC().compareTo(checkNode.getC()) == 0)){
                match = true;
            }
            return match;
        }
        
        /**
         * @param (curr) - a pointer with which the BST may be traversed
         * @param (checkNode) - a node containing the value to compare
         * @return - a BSTNode, used for recursion and also for when an answer has been found
         */
        private BSTNode existsHelper (BSTNode <T> curr, BSTNode <T> checkNode) {               
            if (curr == null || curr.getC().compareTo(checkNode.getC()) == 0) {
                return (curr);
            }
            
            if (curr.getC().compareTo(checkNode.getC()) > 0) {
                return existsHelper (curr.getLeft(), checkNode);
            }
            
            return existsHelper (curr.getRight(), checkNode);
        }
        
        /**
         * @param (leaf) - a new node to be added to the tree
         * @param (curr) - a pointer to traverse the tree
         */
        private void insertHelper (BSTNode <T> leaf, BSTNode <T> curr) {
            try  {
                curr.getValue();
                
                if (leaf.getC().compareTo(curr.getC()) <= 0) {
                    if ((curr.getLeft()) == null) {
                        curr.setLeft(leaf);
                    }
                    else {
                        curr = curr.getLeft ();
                        insertHelper (leaf, curr);
                    }
                }
                else if (leaf.getC().compareTo(curr.getC()) > 0) {
                    if ((curr.getRight()) == null) {
                        curr.setRight(leaf);
                    }
                    else {
                        curr = curr.getRight ();
                        insertHelper (leaf, curr);
                    }
                }
            }
            catch (Exception NullPointerException) {
                curr = leaf;
            }
        }
        
        /**
         * Balances the BST
         */
        public void balance () {
            ArrayList <T> list = new ArrayList <T> ();
            BSTNode curr = root;
            list = fillAL (curr, list);
            root = null;
            balHelp (list);
        }
        
        /**
         * @param list - an arraylist with all the values in the BST included
         */
        private void balHelp (List <T> list) {
            if (list.size() > 2) {
                insert(list.get(list.size()/2));
                balHelp (list.subList (0, list.size()/2));
                balHelp (list.subList (((list.size()/2)), list.size()));
            }
            else if (list.size() == 2){
                insert (list.get(1));
                list.remove (1);
                insert (list.get(0));
                list.remove (0);
            }
            else if (list.size() == 1){
                insert (list.get(0));
                list.remove (0);
            }
        }
        
        /**
         * @param curr- a pointer with which to traverse the BST
         * @param list - the arraylist in which all the values of the BST are added
         */
        private ArrayList fillAL (BSTNode <T> curr, ArrayList <T> list) {
            if (curr != null) {
                fillAL(curr.getLeft(), list);
                list.add (curr.getValue());
                fillAL (curr.getRight(), list);
            }
            
            return list;
        }
        
        /**
         * In-order prints the BST
         */
        public void printTree() {
            BSTNode curr = root;
            ArrayList <String> list = new ArrayList <String> ();
            int element = 0;
            
            list = printTreeHelper (curr, list, element);
            
            for (int x = 0; x < list.size(); x++) {
                System.out.println (list.get(x));
            }
        }
        
        /**
         * @param curr - a pointer with which to traverse the BST
         * @param list - the values in the BST inseted into an arraylist
         * @param el - used to keep track of what element of "list" the computer is working with
         * @return list - the values of the BST, in one arraylist. used to re-insert them into the tree later
         */
        private ArrayList <String> printTreeHelper (BSTNode curr, ArrayList <String> list, int el) {
            if (curr != null) {
                if (el < list.size()) {
                    String s = list.get(el) + " " + curr.getValue();
                    list.set (el, s);
                }
                else {
                    String s = "" + curr.getValue();
                    list.add(s);
                }
                printTreeHelper(curr.getLeft(), list, el+1);
                printTreeHelper (curr.getRight(), list, el+1);
            }
            
            return list;
        }
        
        /**
         * This class is a node specifically designed for a binary search tree
         * 
         * @author Grady McPeak 
         * @version One
         */
        public class BSTNode <T> {
            T value;
            BSTNode left;
            BSTNode right;
        
            /**
            * Constructor
            */
            public BSTNode (T v) {
                value = v;
            }
    
            public T getValue () {
                if (value == null) {
                    return null;
                }
                else {
                    return value;
                }
            }
      
            public void setValue (T v) {
                value = v;
            }
        
            public BSTNode getLeft () {
                return left;
            }
        
            public void setLeft (BSTNode n) {
                left = n;
            }
        
            public void setRight (BSTNode n) {
                right = n;
            }
        
            public BSTNode getRight () {
                return right;
            }
            
            Comparable getC() {
                return (Comparable) value;
            }
        }
    }
}
