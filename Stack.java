/**
 * Simple stack class. The stack is made of nodes in a linked list.
 * 
 * @author Grady McPeak 
 * @version One
 */
public class Stack <T>
{
    Node <T> top;
    
    /**
     * This is a basic "push" method. It takes in new data and puts it a the beginning of the linked list
     * @param data - any type of  dtat to add to the stack
     * @return - none
     */
    public void push (T data) {
        Node <T> newTop = new Node <T> (data);
        newTop.setNext (top);
        top = newTop;
    }
    
    /**
     * This is a basic "pop" method that removes the node at the top of the linked list from the list and returns its value
     * @param - none
     * @return - popped.getValue() - the data contained in the popped node
     */
    public T pop () {
        if (top == null) {
            return (null);
        }
        else {
            Node <T> popped = top;
        
            top = top.getNext();
        
            popped.setNext(null);
        
            return (popped.getValue());
        }
    }
    
    /**
     * This "peek" method just returns the top node's data
     * @param - none
     * @return - popped.getValue() - the data contained in the node at the top of the stack
     */
    public T peek () {
        if (top == null) {
            return (null);
        }
        else {
            return (top.getValue());
        }
    }
}