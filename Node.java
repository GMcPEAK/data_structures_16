/**
 * 
 */

public class Node <T>
{
    T value;
    Node next;
    Node prev;
    
    /**
     * Constructor
     */
    public Node (T v) {
        value = v;
    }

    public T getValue () {
        return value;
    }
  
    public void setValue (T v) {
        value = v;
    }
    
    public Node getNext () {
        return next;
    }
    
    public void setNext (Node n) {
        next = n;
    }
    
    public void setPrev (Node n) {
        prev = n;
    }
    
    public Node getPrev () {
        return prev;
    }
}
