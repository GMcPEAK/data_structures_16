import java.util.*;
/**
 * A MinHeap of integers, with an ability to delete the root, add new nodes, and peek at the top node.
 * 
 * @author Grady McPeak
 * @version One
 */
public class MinHeap <T extends Comparable <T>>
{
    private ArrayList <T> heap;
    
    /**
     * @param val - the data to add to the list
     * @return - n/a
     */
    public void insert (T val) { // insert another
        heap.add (val);
        Collections.sort (heap);
    }
    
    /**
     * @param - n/a
     * @return ret - the top of the heap, which is removed from the heap and returned
     */
    public T delete () { // delete top of heap
        T ret = heap.get(0);
        heap.remove (0);
        return ret;
    }
    
    /**
     * @param - n/a
     * @return heap.get(0) - the top data point of the heap
     */
    public T peek () { // peek at top of heap
        return heap.get(0);
    }
}