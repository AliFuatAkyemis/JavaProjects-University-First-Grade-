
public class Ex3_20210808043 {
	
}

class Author {
	private String name;
	private String surname;
	private String mail;
	
	public Author(String name, String surname, String mail) {
		this.name = name;
		this.surname = surname;
		this.mail = mail;
	}
	
	public Author() {
		this.name = "Name";
		this.surname = "Surname";
		this.mail = "Mail";
	}
	
	
	
	public void setName(String name) {this.name = name;}

	public String getName() {return this.name;}
	
	public void setSurname(String surname) {this.surname = surname;}
	
	public String getSurname() {return this.surname;}
	
	public void setMail(String mail) {this.mail = mail;}
	
	public String getMail() {return this.mail;}
	
}

class Book {
	private String isbn;
	private String title;
	private Author author = new Author();
	private double price;
	
	public Book(String isbn, String title, Author author, double price) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.price = price;
	}
	
	public Book(String isbn, String title, Author author) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.price = 15.25;
	}
	
	public String getIsbn() {return this.isbn;}
	
	public String getTitle() {return this.title;}
	
	public Author getAuthor() {return this.author;}
	
	public void setPrice(double price) {this.price = price;}
	
	public double getPrice() {return this.price;}
	
}

class EBook extends Book {
	private String downloadUrl;
	private double sizeMb;
	
	public EBook(String isbn, String title, Author author, double price, String downloadUrl, double sizeMb) {
		super(isbn, title, author, price);
		this.downloadUrl = downloadUrl;
		this.sizeMb = sizeMb;
	}
	
	public EBook(String isbn, String title, Author author, String downloadUrl, double sizeMb) {
		super(isbn, title, author);
		this.downloadUrl = downloadUrl;
		this.sizeMb = sizeMb;
	}
	
	public String getDownloadUrl() {return this.downloadUrl;}
	
	public double getSizeMb() {return this.sizeMb;}
	
}

class PaperBook extends Book {
	private int shippingWeight;
	private boolean inStock;
	
	public PaperBook(String isbn, String title, Author author, double price, int shippingWeight, boolean intStock) {
		super(isbn, title, author, price);
		this.shippingWeight = shippingWeight;
		this.inStock = inStock;
	}
	
	public PaperBook(String isbn, String title, Author author, boolean intStock) {
		super(isbn, title, author);
		this.shippingWeight = (int) ((Math.random() * 11) + 5);
		this.inStock = inStock;
	}
	
	public int getShippingWeight() {return this.shippingWeight;}
	
	public void setInStock(boolean inStock) {this.inStock = inStock;}
	
	public boolean getInStock() {return this.inStock;}
	
}
