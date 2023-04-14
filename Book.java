// -----------------------------------------------------
// Assignment 4
// Part: Book class
// Written by: Noah Burns (40237138) & Mouhamed Coundoul (40248237)
// -----------------------------------------------------

import java.util.Objects;

/**
 * 
 * @author Noah Burns (40237138) & Mouhamed Coundoul (40248237)
 *
 */
public class Book {
	
	private String title;
	private String author;
	private double price;
	private long ISBN;
	private String genre;
	private int year;
	
	/**
	 * Default Constructor
	 */
	public Book() {
		
	}
	
	/**
	 * Parameterized Constructor
	 * @param title of the book
	 * @param author of the book
	 * @param price of the book
	 * @param ISBN of the book
	 * @param genre of the book
	 * @param year of the book
	 */
	public Book(String title, String author, double price, long ISBN, String genre, int year) {
		this.title = title;
		this.author = author;
		this.price = price;
		this.ISBN = ISBN;
		this.genre = genre;
		this.year = year;
	}
	
	/**
	 * title getter
	 * @return title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * title setter
	 * @param title of the book
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * author getter
	 * @return author
	 */
	public String getAuthor() {
		return author;
	}
	
	/**
	 * author setter
	 * @param author of the book
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	
	/**
	 * price getter
	 * @return price
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * price setter
	 * @param price of the book
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	/**
	 * ISBN getter
	 * @return ISBN
	 */
	public long getISBN() {
		return ISBN;
	}
	
	/**
	 * ISBN setter
	 * @param ISBN of the book
	 */
	public void setISBN(long ISBN) {
		this.ISBN = ISBN;
	}
	
	/**
	 * genre getter
	 * @return genre
	 */
	public String getGenre() {
		return genre;
	}
	
	/**
	 * genre setter
	 * @param genre of the book
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	/**
	 * year getter
	 * @return year
	 */
	public int getYear() {
		return year;
	}
	
	/**
	 * year setter
	 * @param year of the book
	 */
	public void setYear(int year) {
		this.year = year;
	}
	
	/**
	 * method to make make into a String
	 */
	public String toString() {
		return (title  + ", " + author + ", " + 
				price + ", " + ISBN + ", " + 
				genre + ", " + year);
	}
	
	/**
	 * boolean method which checks if two book object are the same
	 * @return true if they are the same, false if they are not
	 */
	@Override
	public boolean equals(Object o) {
	    if (o == this) return true;
	    if (!(o instanceof Book)) {
	        return false;
	    }
	    Book b = (Book) o;
	    return Objects.equals(this.title, b.title)
	            && Objects.equals(this.author, b.author)
	            && Objects.equals(this.ISBN, b.ISBN)
	            && Objects.equals(this.genre, b.genre)
	            && this.year == b.year
	            && this.price == b.price;
	}

	/**
	 * static method which take the line read from the file 
	 * splits it into 6 fields and created a new Book object
	 * @param line of the file
	 * @return the new Book object
	 */
	public static Book fromRecord(String line) {
		
	    String[] fields = line.split(",");
	    
	    String title = fields[0];
	    String author = fields[1];
	    double price = Double.parseDouble(fields[2]);
	    long ISBN = Long.parseLong(fields[3]);
	    String genre = fields[4];
	    int year = Integer.parseInt(fields[5]);
	    
	    return new Book(title, author, price, ISBN, genre, year);
	}
	
}
