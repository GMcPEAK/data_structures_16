import java.util.*;
/**
 * A MinHeap of integers, with an ability to delete the root, add new nodes, and peek at the top node.
 * 
 * @author Grady McPeak
 * @version Two
 */
public class MinHeap {
    ArrayList <MHNode> heap = new ArrayList <MHNode> ();
    
    /**
     * @param v - the data to add to the list
     * @param n - the new node's number
     * @return - n/a
     */
    public void insert (int v, int n) { // insert another
        heap.add (new MHNode (v, n));
        insertionSort();
    }
    
    /**
     * @param - n/a
     * @return - n/a
     */
    public void insertionSort () {
        int j;                  // the number of items sorted so far
        MHNode key;                // the item to be inserted
        int i;

        for (j = 0; j < heap.size(); j++)
        {
            key = heap.get(j);
            for(i = j - 1; (i >= 0) && (key.getCost() < heap.get(i).getCost()); i = (i-1))// larger values are moving up
            {
                heap.set (i+1, heap.get(i));
            }
            heap.set(i + 1, key);// Put the key in its proper location
        }
    }
    
    /**
     * @param - n/a
     * @return ret - the top of the heap, which is removed from the heap and returned
     */
    public MHNode delete () { // delete top of heap
        if (heap.size() == 0) {
            return null;
        } else {
            MHNode ret = heap.remove (0);
            insertionSort();
            return ret;
        }
    }
    
    /**
     * @param n/a
     * @return heap.get(0).getNodeNum() - the top node number
     */
    public int peekNode () {
        if (heap.size() == 0) {
            return 0;
        }
        else {
            return heap.get(0).getNodeNum();
        }
    }
    
    /**
     * @param - n/a
     * @return heap.get(0).getCost() - the top node of the heap's tentative distance
     */
    public int peek () { // peek at top of heap's cost
        return heap.get(0).getCost();
    }
    
    /**
     * A nested class that contains the node number and the cost of getting to that node.
     * 
     * @author Grady McPeak
     * @version Two
     */
    private class MHNode  {
        Integer nodeNum = 0;
        Integer cost = 0;
        
        /**
        * Constructor
        */
        public MHNode (int x, int y) {
            cost = x;
            nodeNum = y;
        }
        
        /**
         * @param - n/a
         * @return - cost - the cost to travel from one node to another
         */
        public Integer getCost() {
            if (cost == null) {
                return null;
            }
            else {
                return cost;
            }
        }
        
        /**
         * @param - n/a
         * @return - nodeNum - the number the node is labeled as in the heap
         */
        public Integer getNodeNum() {
            if (nodeNum == null) {
                return null;
            }
            else {
                return nodeNum;
            }
        }
    }
}
