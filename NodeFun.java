public class NodeFun {
    public static void main (String []args) {
        /**
         * create a simple list
         */
        Node head = new Node(22);
        
        head.setNext(new Node(3));
        
        head.getNext().setNext(new Node (7));
        
        //This will get tedious reall quick, so let's make a current point
        Node curr = head.getNext().getNext();
        
        System.out.println(curr.getValue());
        
        //MANIPULATING A CURENT POINTER
        curr.setNext(new Node (2)); // fourth node
        
        //here's the MAGIC
        curr = curr.getNext();
        curr.setNext (new Node(663));
        curr = curr.getNext(); // curr is now 663
        curr.setNext (new Node (123));
        
        //lets loop through the whole list
        
        curr = head;
        while (curr != null) {
            System.out.println(curr.getValue());
            curr = curr.getNext();
        }
    }
}
