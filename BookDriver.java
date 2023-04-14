// -----------------------------------------------------
// Assignment 4
// Part: BookDriver class
// Written by: Noah Burns (40237138) & Mouhamed Coundoul (40248237)
// -----------------------------------------------------

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author Noah Burns (40237138) & Mouhamed Coundoul (40248237)
 *
 */
public class BookDriver {
	
	/**
	 * main method to execute all functions of CircularBookList class
	 * @param args 
	 */
	public static void main(String[] args) {
		
		ArrayList<Book> arrLst = new ArrayList<>();
		CircularBookList bkLst = new CircularBookList();
		
		String filePath = "Books.txt";
		BufferedReader br = null;
		String line;
		
		try {
			br = new BufferedReader(new FileReader(filePath));
			
			while ((line = br.readLine()) != null) {
				
				Book book = Book.fromRecord(line);
				
                if (book.getYear() >= 2024) {
                    arrLst.add(book);
                    
                }
                else {
                    bkLst.addToEnd(book);
                }
			}
			br.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	try {
        FileWriter writer = null;
        for (Book book : arrLst) {
            if (book.getYear() >= 2024) {
                if (writer == null) {
                    writer = new FileWriter("YearErr.txt");
                }
                writer.write(book.toString() + "\n");
            }
        }
        if (writer != null) {
            writer.close();
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    bkLst.displayContent();
    
    Scanner scanner = new Scanner(System.in);
    int choice = 0;
    
    do {
        System.out.println("Please select an option:");
        System.out.println("\t1) Give me a year # and I would extract all records of that year and store them in a file for that year;");
        System.out.println("\t2) Ask me to delete all consecutive repeated records;");
        System.out.println("\t3) Give me an author name and I will create a new list with the records of this author and display them;");
        System.out.println("\t4) Give me an ISBN number and a Book object, and I will insert Node with the book before the record with this ISBN;");
        System.out.println("\t5) Give me 2 ISBN numbers and a Book object, and I will insert a Node between them, if I find them!");
        System.out.println("\t6) Give me 2 ISBN numbers and I will swap them in the list for rearrangement of records; of course if they exist!");
        System.out.println("\t7) Tell me to COMMIT! Your command is my wish. I will commit your list to a file called Updated_Books;");
        System.out.println("\t8) Tell me to STOP TALKING. Remember, if you do not commit, I will not!");
        System.out.print("Choice: ");
        
        choice = scanner.nextInt();
        scanner.nextLine(); // consume the newline character
        
        switch (choice) {
            case 1:
                System.out.print("Please provide the year: ");
                int yearChoice = scanner.nextInt();
                bkLst.storeRecordsByYear(yearChoice);
                System.out.println("Please select another option");
                System.out.println("===========================================================================");
                bkLst.displayContent();
                break;
            case 2:
                bkLst.delConsecutiveRepeatedRecords();
                System.out.println("Here are the results after deleting concecutive repeating records");
                System.out.println("===========================================================================");
                bkLst.displayContent();
                break;
            case 3:
            	System.out.print("Please provide the author's name: ");
            	String autChoice = scanner.nextLine();
            	CircularBookList extractedList = bkLst.extractAuthList(autChoice);

            	if (extractedList == null) {
            	    System.out.println("No books found for author " + autChoice);
            	} else {
            	    if (extractedList == bkLst) {
            	        System.out.println("The extracted list is the same as the original list");
            	    } else {
            	        System.out.println("A new list was created for author " + autChoice);
            	    }
            	    System.out.println("Here are the results after storing records by author's name");
            	    System.out.println("===========================================================================");
            	    extractedList.displayContent();
            	}

                break;
            case 4:
                System.out.print("Please provide the ISBN you wish wish to add a book before: ");
                long isbnChoice = scanner.nextLong();
                scanner.nextLine();

                Book newBook = new Book("Star Wars", "George Lucas", 35.99, 1234567, "SFI", 1976);
                
                if (bkLst.insertBefore(isbnChoice, newBook)) {
                 	
                 	System.out.println("Here are the results after swapping Books with given ISBNs");
                    System.out.println("===========================================================================");
                 }
                 else {
                 	System.out.println("The book couldn't be added before the given ISBN");
                    System.out.println("===========================================================================");
                 }
                
                bkLst.displayContent();
                break;
            case 5:
            	 System.out.print("Please provide the first ISBN you wish to add a Book between: ");
                 long isbnChoice1 = scanner.nextLong();
                 System.out.print("Please provide the second ISBN you wish to add a Book between: ");
                 long isbnChoice2 = scanner.nextLong();
      
                 Book newB = new Book("Game of Thrones", "George R.R. Martin", 40.99, 987654321, "FIC", 1999);
                 
                 if (bkLst.insertBetween(isbnChoice1, isbnChoice2, newB)) {
                 	
                 	System.out.println("Here are the results after swapping Books with given ISBNs");
                    System.out.println("===========================================================================");
                 }
                 else {
                 	System.out.println("The book couldn't be added between the two given ISBNs");
                    System.out.println("===========================================================================");
                 }
                
                 bkLst.displayContent();
                break;
            case 6:
            	System.out.print("Please provide the first ISBN you wish to swap with: ");
                long isbnChoice3 = scanner.nextLong();
                System.out.print("Please provide the second ISBN you wish to swao with: ");
                long isbnChoice4 = scanner.nextLong();
                
                if (bkLst.swap(isbnChoice3, isbnChoice4)) {
                	
                	System.out.println("Here are the results after swapping Books with given ISBNs");
                    System.out.println("===========================================================================");
                }
                else {
                	System.out.println("The two book objects couldn't be swapped");
                    System.out.println("===========================================================================");
                }
                	
                bkLst.displayContent();
                break;
            case 7:
               bkLst.commit();
                break;
            case 8:
                System.out.println("Goodbye!");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        
    } while (choice != 8);
    scanner.close();
	}
	
}
