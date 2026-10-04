package datalayer;

public class CustomLinkedList<T extends Identifiable> { 
    
    // A Custom LinkList of Types (T) that extends <<indentifiable>> interface (EX: Product extends Identifiable, allows CustomLinkedList<Product> sampleList = new CustomLinkedList<>());
    // If a Type does not emplement <<Identifiable>> (EX Account, does not allow CustomLinkedList<Accoount> sampleList = new CustomLinkedList<>())

    private Node<T> head;
    private int size;

    public CustomLinkedList() {
        System.out.println("Succesfulyt created a new CustomLinkedList");
        head = null; // Once a linkedlist is created the head is null
        size = 0; // starts at 0 to indicate an empty list
    }
    
    public void insertData(T data) {

        Node<T> newNode = new Node<>(data);

        if (isEmpty()) {
            head = newNode;
        } else {
            Node<T> current = head; // Create pointer starting from head
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }

        size++;

    } // inserts at Tail
    
    public void insertAtStart(T data) {

        Node<T> newNode = new Node<>(data);

        if (head != null) {
            head = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        
        size++;

    } // Inserts at Start

    public boolean insertAtIndex(int index, T data) {

        if (index < 0) return false;
        if (index > size) return false;

        Node<T> newNode = new Node<>(data);

        if (index == 0) {
            insertAtStart(data);
        } else {
            Node<T> current = head;
            for (int i = 0; i < index -1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
            size++;
        }

        return true;

    } // returns true if succesful, and false if a incorrect index is written

    public boolean deletAtIndex(int index) {

        if (index < 0) return false;
        if (index > size) return false;

        if (index == 0) {
            head = head.next;
        } else {
            Node<T> current = head;
            Node<T> target;
            for (int i = 0; i < index -1; i++) {
                current = current.next;
            }
            target = current.next;
            current.next = target.next;
        }

        size--;

        return true;

    } // deletes data at a index, 

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    } // returns the size 

    public void display() {
        Node<T> current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    } // Displays data starting from head

    public T search(String targetId) {
        Node<T> current = head;
        while (current!= null) {
            if (current.data.getId().equals(targetId)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    } // searches data by its id returns the obj

    public Node<T> getHead() {
        return head;
    }
}
