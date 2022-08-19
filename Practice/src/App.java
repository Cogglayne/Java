import java.util.*;
public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        DoublyLinkedList list = new DoublyLinkedList();
        list.push("hello");
        list.showData();
        Stack<String> stk = new Stack<>();
        Queue<String> q = new LinkedList<>();
        PriorityQueue<String> pq = new PriorityQueue<>();
        ArrayList<String> myList = new ArrayList<>();
    }
}
 class Node {
    String val;
    Node prev;
    Node next;

    public Node(String val) {
        this.val = val;
        this.next = null;
        this.prev = null;
    }
}

class DoublyLinkedList {
    Node head;
    Node tail;
    int length;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        length = 0;
    }

    void push(String val) {
        Node node = new Node(val);
        if (this.length == 0) {
            this.head = node;
            this.tail = node;
        } else {
            this.tail.next = node;
            node.prev = this.tail;
            this.tail = node;
        }
        this.length++;
    }

    void unshift(String val) {
        Node node = new Node(val);
        if (this.length == 0) {
            this.head = node;
            this.tail = node;
        } else {
            this.head.prev = node;
            node.next = this.head;
            this.head = node;
        }
        this.length++;
    }

   void showData() {
        //intialize a new node current that will point to head    
        Node current = head;
        //Check whether the doubly linked list is empty or not  
        if (head == null) {
            //Print a statement and pass the control flow into the main() method  
            System.out.println("List is empty");
            return;
        }
        //Print a statement  
        System.out.println("Nodes of doubly linked list: ");
        //Iterate the doubly linked list using while  
        while (current != null) {
            //Print tha data on that particular node and then increment the pointer for indicating next node    
            System.out.print(current.val + "\n");
            current = current.next;
        }
    }
}