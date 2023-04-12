import java.io.*;
import java.util.LinkedList;

public class CircularBookList {
	
	private Node head;
	
	private class Node {
		
		private Book b;
		private Node next;
		
		public Node(Book b) {
		    this.b = b;
		    this.next = this; // set the next reference to itself
		}

		public Node(Book b, Node next) {
		    this.b = b;
		    this.next = next != null ? next : this; // set the next reference to the provided node or to itself
		}

		
		public Book getB() {
			return b;
		}
		public void setB(Book b) {
			this.b = b;
		}
		public Node getNext() {
			return next;
		}
		public void setNext(Node next) {
			this.next = next;
		}

	}
	
	public CircularBookList() {
		head = null;
	}
	
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
	
	public void storeRecordsByYear(int yr) {
		
		String fileName = yr + ".txt";
		File file = new File(fileName);
		FileWriter fw = null;
		
		try {
			
		    fw = new FileWriter(file);
			Node current = head;
			
			while (current != head) {
				
				if (current.b.getYear() == yr) {
					
					fw.write(current.b.toString());
				}
				
				current = current.next;
			}
			fw.close();
			
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	
	public boolean insertBefore(long ISBN, Book b) {
		boolean t = false;
	    if (head == null) {
	        t = false;
	    }
	    
	    else if (head.next == head) { // check if there is only one node in the list
	    	Node newNode = new Node(b);
	        newNode.next = head;
	        head.next = newNode;
	        head = newNode; // update the head reference
	        t = true;
	    } 
	    
	    else {
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



	
	public boolean insertBetween(long ISBN1, long ISBN2, Book b) {
	    if (head == null) {
	        return false;
	    }
	    
	    Node current = head;
	    
	    while (current != head) {
	    	
	        if (current.b.getISBN() == ISBN1 && current.next.b.getISBN() == ISBN2) {
	            Node newNode = new Node(b, current.next);
	            current.next = newNode;
	            return true;
	        }
	        current = current.next;
	    }
	    
	    return false;
	}
	
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

	
	public CircularBookList extractAuthList(String author) {
		
		CircularBookList newList = new CircularBookList();
		
		if (head == null) {
			return newList;
		}
		
		Node current = head;
		
		do {
			if (current.b.getAuthor().equals(author)) {
				newList.addToEnd(current.b);
			}
			current = current.next;
		} while (current != head);
    
		return newList;
	}


	
	public boolean swap(long ISBN1, long ISBN2) {
		
		if (head == null) {
			return false;
		}
		
		Node current = head;
		Node current2 = head;
		boolean recordsSwap = false;
		
		while (current != head & current2 != head) {
			
			if (current.b.getISBN() == ISBN1 && current2.b.getISBN() == ISBN2) {
				Node temp = current;
				current = current2;
				current2 = temp;
				recordsSwap = true;
			}
			else {
				current = current.next;
				current2 = current2.next;
			}
		}
		
		return recordsSwap;
	}
	
	public void commit() {
		
	    try {
	    	
	        PrintWriter writer = new PrintWriter(new File("Update_Books.txt"));
	        
	        if(head == null) {
	            writer.write("Empty list");
	        } 
	        else  {
	           Node current = head;
	            
	           while (current != head) {
	        	   
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
