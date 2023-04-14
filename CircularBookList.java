// -----------------------------------------------------
// Assignment 4
// Part: CircularBookList class
// Written by: Noah Burns (40237138) & Mouhamed Coundoul (40248237)
// -----------------------------------------------------

import java.io.*;
/**
 * 
 * @author Noah Burns (40237138) & Mouhamed Coundoul (40248237)
 *
 */
public class CircularBookList {
	
	private Node head;
	
	/**
	 * 
	 * @author Noah Burns (40237138) & Mo
	 *
	 */
	private class Node {
		
		private Book b;
		private Node next;
		
		/**
		 * Parameterized Constructor
		 * @param b is a Book object
		 */
		public Node(Book b) {
		    this.b = b;
		    this.next = this; // set the next reference to itself
		}
		
		/**
		 * Parameterized Constructor
		 * @param b is Book object
		 * @param next is the next Node
		 */
		public Node(Book b, Node next) {
		    this.b = b;
		    this.next = next != null ? next : this; // set the next reference to the provided node or to itself
		}
	}
	
	/**
	 * Default Constructor
	 */
	public CircularBookList() {
		head = null;
	}
	
	/**
	 * void method which adds a new node containing the new book to the start
	 * @param b is a Book object
	 */
	public void addToStart(Book b) {
	    Node newNode = new Node(b);
	    if (head == null) {
	        head = newNode;
	        head.next = head;
	    } else {
	        newNode.next = head.next;
	        head.next = newNode;
	    }
	}
	
	
	/**
	 * void method which add a new node containing the new book to the end
	 * @param b is a Book object
	 */
	public void addToEnd(Book b) {
		
		if (head == null) {
			head = new Node(b, head);
			head.next = head;
		}
		else {
			Node t = head;
			while (t.next != head) {
				t = t.next;
			}
			t.next = new Node(b, head);
		}
		
	}
	
	/**
	 * void method which creates a file containing all books matching 
	 * the given year, if the year does not exists the file is deleted 
	 * @param yr is the inputed year
	 * @return true if the year file can be created, false if it cannot
	 */
	public void storeRecordsByYear(int yr) {

	    String fileName = yr + ".txt";
	    File file = new File(fileName);
	    FileWriter fw = null;

	    try {

	        fw = new FileWriter(file);
	        Node current = head;
	        boolean recordsFound = false;

	       do {

	            if (current.b.getYear() == yr) {
	                fw.write(current.b.toString());
	                recordsFound = true;
	            }

	            current = current.next;
	            
	        }  while (current != head);
	        fw.close();

	        if (!recordsFound) {
	            file.delete();
	            System.out.println("No year match, could not create file");
	        }
	        else
	        	System.out.println("You have succesfully created a file containg book(s) with the year " + yr);

	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	/**
	 * boolean method which creates a new node with a book 
	 * if the given ISBN is found and inserts it before that node
	 * @param ISBN is the inputed ISBN
	 * @param b is a Book object
	 * @return true if the node can be added before and false otherwise
	 */
	public boolean insertBefore(long ISBN, Book b) {
	    boolean t = false;
	    if (head == null) {
	        t = false;
	    } else if (head.b.getISBN() == ISBN) { // check if ISBN matches head node
	        Node newNode = new Node(b);
	        newNode.next = head;
	        Node current = head;
	        while (current.next != head) {
	            current = current.next;
	        }
	        current.next = newNode;
	        head = newNode;
	        t = true;
	    } else {
	        Node current = head;
	        do {
	            if (current.next.b.getISBN() == ISBN) {
	                Node newNode = new Node(b);
	                newNode.next = current.next;
	                current.next = newNode;
	                t = true;
	                break;
	            }
	            current = current.next;
	        } while (current != head);
	    }
	    return t;
	}

	/**
	 * boolean method which finds two ISBNs and inserts a new node containing
	 * a book in between them
	 * @param ISBN1 is the first given ISBN
	 * @param ISBN2 is the second given ISBN
	 * @param b is a Book object
	 * @return true if the new book can be added between both ISBNs and false otherwise
	 */
	public boolean insertBetween(long ISBN1, long ISBN2, Book b) {
		
		boolean t = false;
		
	    if (head == null) {
	        t = false;
	    }
	    
	    Node current = head;
	    
        do {
            if (current.b.getISBN() == ISBN1 && current.next.b.getISBN() == ISBN2 && current.next != null) {
                Node newNode = new Node(b);
                newNode.next = current.next;
                current.next = newNode;
                t = true;
                break;
            }
            current = current.next;
        } while (current != head);
	    
	    return t;
	}
	
	/**
	 * void method which displays the content of the circular book list
	 */
	public void displayContent() {
		
		if (head == null) {
			System.out.println("The list is empty");
			return;
		}
		
		
		Node current = head;
		
		while (current.next != head) {
			System.out.println(current.b + " ==>");
			current = current.next;
		}
		System.out.println("==> head");
	}
	
	/**
	 * boolean method which deleted consecutive repeated records
	 * @return true if there are multiple consecutive repeating records false otherwise
	 */
	public boolean delConsecutiveRepeatedRecords() {
		
		if (head == null) {
			return false;
		}
		
		Node current = head;
		boolean recordsDel = false;
		
		while (current.next != head && current.next != null) {
			
			if (current.b.equals(current.next.b)) {
				current.next = current.next.next;
				recordsDel = true;
			}
			else {
				current = current.next;
			}
		}
		
		return recordsDel;
	}

	/**
	 * creates a new list of the given author name
	 * @param author given author name
	 * @return the new list containing only given author
	 */
	public CircularBookList extractAuthList(String author) {
		
		CircularBookList newList = new CircularBookList();
		
		if (head == null) {
			return newList;
		}
		
		Node current = head;
		
		do {
			if (current.b.getAuthor().equalsIgnoreCase(author)) {
				newList.addToEnd(current.b);
			}
			current = current.next;
		} while (current != head);
    
		return newList;
	}

	/**
	 * boolean method which swaps two books 
	 * @param ISBN1 is the first given ISBN
	 * @param ISBN2 is the second given ISbN
	 * @return true if the swap can be completed, false if it cannot
	 */
	public boolean swap(long ISBN1, long ISBN2) {
	    if (head == null) {
	        return false;
	    }
	    
	    Node current = head;
	    Node node1 = null;
	    Node node2 = null;
	    
	    while (current.next != head) {
	        if (current.b.getISBN() == ISBN1) {
	            node1 = current;
	        }
	        if (current.b.getISBN() == ISBN2) {
	            node2 = current;
	        }
	        current = current.next;
	    }
	    
	    // check the last node
	    if (current.b.getISBN() == ISBN1) {
	        node1 = current;
	    }
	    if (current.b.getISBN() == ISBN2) {
	        node2 = current;
	    }
	    
	    if (node1 != null && node2 != null) {
	        Book temp = node1.b;
	        node1.b = node2.b;
	        node2.b = temp;
	        return true;
	    } else {
	        return false;
	    }
	}

	/**
	 * void method which writes all remaining book in a file
	 */
	public void commit() {
		
	    try {
	    	
	        PrintWriter writer = new PrintWriter(new File("Update_Books.txt"));
	        
	        if(head == null) {
	            writer.write("Empty list");
	        } 
	        else  {
	           Node current = head;
	            
	           while (current.next != head) {
	        	   
	                writer.write(current.b.toString() + " ==>\n");
	                current = current.next;
	            } 
	        }
	        
	        writer.close();
	        System.out.println("List has been committed to Update_Books.txt");
	    } catch (FileNotFoundException e) {
	        System.out.println("File not found");
	    }
	}

}
