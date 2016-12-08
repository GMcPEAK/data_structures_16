import java.util.*;
/**
 * This class is a binary search tree that contains a nested BSTNode class specialized for BST's.
 * Conatins methods to insert, in-order print, and search for a value
 * 
 * @author Grady McPeak 
 * @version One
 */
public class BST <T> {
    BSTNode <T> root;
    
    /**
     * @param (T insertMe) - the value to be inserted into the tree
     * @return - N/A
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
     * @param - n/a
     * @return - n/a
     */
    public void inOrderPrint() {
        BSTNode curr = root;
        inOrderPrintHelper (curr);
    }
    
    /**
     * @param (curr) - a pointer used to traverse the BST
     * @return - n/a
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
        System.out.println (match);
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
     * @param n/a
     * @return n/a
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
     * @return n/a
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
     * @param n/a
     * @return n/a
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