import java.util.Objects;

public class Book {
	
	private String title;
	private String author;
	private double price;
	private long ISBN;
	private String genre;
	private int year;
	
	public Book() {
		
	}
		
	public Book(String title, String author, double price, long ISBN, String genre, int year) {
		this.title = title;
		this.author = author;
		this.price = price;
		this.ISBN = ISBN;
		this.genre = genre;
		this.year = year;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public long getISBN() {
		return ISBN;
	}
	public void setISBN(long ISBN) {
		this.ISBN = ISBN;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	
	public String toString() {
		return (title  + ", " + author + ", " + 
				price + ", " + ISBN + ", " + 
				genre + ", " + year);
	}
	
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
