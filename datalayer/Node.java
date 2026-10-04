package datalayer;

public class Node<T> {

    public T data;
    public Node<T> next;

    public Node(T data) {
        this.data = data;
        this.next = null;
    } // Creating Nodes in te constructor = Initializes Data and Node.next to null
    
}
