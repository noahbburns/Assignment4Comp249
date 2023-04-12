import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class BookDriver {
	

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
            if (book.getYear() == -1) {
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
                System.out.println("Here are the results after storing records by year");
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
                scanner.skip("[\r\n]+"); // consume any remaining whitespace characters
                scanner.nextLine();

                Book newBook = new Book("Star Wars", "George Lucas", 35.99, 1234567, "SFI", 1976);

                
                bkLst.insertBefore(isbnChoice, newBook);
                System.out.println("Here are the results after inserting a new Book after the given ISBN");
                System.out.println("===========================================================================");
                bkLst.displayContent();
                break;
            case 5:
            	 System.out.print("Please provide the two ISBN numbers you wish to add a Book between: ");
                 long isbnChoice1 = scanner.nextLong();
                 long isbnChoice2 = scanner.nextLong();
      
                 Book newB = new Book("Star Wars", "George Lucas", 35.99, 1234567, "SFI", 1976);
                 
                 bkLst.insertBetween(isbnChoice1, isbnChoice2, newB);
                 System.out.println("Here are the results after inserting a new Book between given ISBNs");
                 System.out.println("===========================================================================");
                 bkLst.displayContent();
                break;
            case 6:
            	System.out.print("Please provide the two ISBN numbers and the Book object (seperated by a space): ");
                isbnChoice1 = scanner.nextLong();
                isbnChoice2 = scanner.nextLong();
                bkLst.swap(isbnChoice1, isbnChoice2);
                System.out.println("Here are the results after swapping Books with given ISBNs");
                System.out.println("===========================================================================");
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

// 1574670913 
