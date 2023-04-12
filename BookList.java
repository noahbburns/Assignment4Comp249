import java.io.*;
import java.text.FieldPosition;
import java.util.Scanner;

public class BookList {

    private Node head;

    // Initializing the head of the linkedList to null
    public BookList (){
        this.head = null;
    }

    public BookList(Node head) {
        this.head = head;
    }

    /**
     * @param b Represents the book data of the Node that will become the new first Node.
     * If the head node has no reference then the new node ia assigned to the reference of the old head node.
     * The reference of to the next node of the head node changed to the head node which results to it pointing to itself
     */
    public void addToStart(Book b){

        if (head == null){
            head = new Node(b, head);
            head.next = head; // Makes the new Node reference to itself
        }
        else
            this.head = new Node(b, head);
    }



    /**
     * @param yr
     * @throws FileNotFoundException
     */

    public void storeRecordsByYear(int yr) throws FileNotFoundException{

        PrintWriter writeCorrectYear = null;
        Scanner checkEmpty = null;

        File yearFile = new File(yr + ".txt");

        writeCorrectYear = new PrintWriter(new FileOutputStream(yearFile));

        Node firstReferenceNode = head;
            
            while (head != null){

                if (head.b.getYear() == yr)
                    writeCorrectYear.println(head.b);
                head = head.next;
            }
            writeCorrectYear.close();
            checkEmpty = new Scanner( new FileInputStream(yearFile));

        if (checkEmpty.next() == null)
            yearFile.delete();

            head = firstReferenceNode;
    }

    // public boolean insertBefore(long isbn, Book b){

    //     return 
    // }


    public int count (){ // Counts the numbers of node in the linkedList

        int count = 0;
        Node position = head;

        while (position != null){
            position = position.next;
            count++;
        }
        return count;
    }



    
    // The Node Class 
    private class Node {

        private Book b; // The current book data
        private Node next; // Holds the reference/link of the next Book Node

        public Node(){
            this.b = null;
            this.next =null;
        }

        public Node (Node nextBookLink){ // Changes the reference of the next Node to passed the Node
            this.b = b;
            this.next = nextBookLink;
        }


        public Node (Book bookData, Node nextBookLink){
            this.b = bookData;
            this.next = nextBookLink;
        }
    } // End of Node Class


    


    
    
}
