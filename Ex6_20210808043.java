import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
//@author Ali Fuat Akyemiş - 20210808043
//@since 30.04.2023 - 06:01
public class Ex6_20210808043 {
	public static void main(String[] args) {
		
	}

}

//Classes:
abstract class Product implements Comparable {
	private String name;
	private double price;
	
	public Product(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return this.name;
	}

	public double getPrice() {
		return this.price;
	}
	
	public String toString() {
		return " [name= " + name + ", price= " + price + "]";
	}

	@Override
	public int compareTo(Object o) {
		return 0;
	}
	
}

abstract class Book extends Product {
	private String author;
	private int pageCount;
	
	public Book(String name, double price, String author, int pageCount) {
		super(name, price);
		this.author = author;
		this.pageCount = pageCount;
	}

	public String getAuthor() {
		return this.author;
	}

	public int getPageCount() {
		return this.pageCount;
	}
	
}

class ReadingBook extends Book {
	private String genre;
	
	public ReadingBook(String name, double price, String author, int pageCount, String genre) {
		super(name, price, author, pageCount);
		this.genre = genre;
	}

	public String getGenre() {
		return this.genre;
	}	
	
}

class ColoringBook extends Book implements Colorable {
	private String color;
	
	public ColoringBook(String name, double price, String author, int pageCount, String color) {
		super(name, price, author, pageCount);
		this.color = color;
	}

	public String getColor() {
		return this.color;
	}

	@Override
	public void paint(String color) {
		System.out.println("The book is " + color + " right now...");
		this.color = color;
	}
	
}

class ToyHorse extends Product implements Rideable {
	
	public ToyHorse(String name, double price) {
		super(name, price);
	}

	@Override
	public void ride() {
		System.out.println("You are riding right now...");
	}
	
}

class Bicycle extends Product implements Colorable, Rideable {
	private String color;

	public Bicycle(String name, double price, String color) {
		super(name, price);
		this.color = color;
	}
	
	public String getColor() {
		return this.color;
	}
	
	@Override
	public int compareTo(Object o) {
		return 0;
	}

	@Override
	public void ride() {
		System.out.println("You are riding right now...");
		
	}

	@Override
	public void paint(String color) {
		System.out.println("The bicycle is " + color + " right now...");
		this.color = color;
	}
	
}

class User {
	private String username;
	private String email;
	private Paymentmethod payment;
	private ArrayList<Product> products = new ArrayList<Product>();
	
	public User(String username, String email) {
		this.username = username;
		this.email = email;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setPayment(Paymentmethod payment) {
		this.payment = payment;
	}
	
	public Product getProduct(int index) {
		return products.get(index);
	}
	
	public void removeProduct(int index) {
		products.remove(index);
	}
	
	public void addProduct(Product product) {
		products.add(product);
	}
	
	public void purchase() {
		double sum = 0;
		for (int i = 0;i < products.size();i++) {
			sum += products.get(i).getPrice();
		}
		payment.pay(sum);
	}
	
}

class CreditCard implements Paymentmethod {
	private long cardNumber;
	private String cardHolderName;
	private java.util.Date expirationDate;
	private int cvv;
	private boolean isCardGot = false;
	
	public CreditCard(long cardNumber, String cardHolderName, java.util.Date expirationDate, int cvv) {
		this.cardNumber = cardNumber;
		this.cardHolderName = cardHolderName;
		this.expirationDate = expirationDate;
		this.cvv = cvv;
		isCardGot = true;
	}

	@Override
	public boolean pay(double amount) {
		if (isCardGot) return true;
		return false;
	}
	
}

class PayPal implements Paymentmethod {
	private String username;
	private Password password;
	private boolean isLoggedIn = false;
	
	public PayPal(String username, Password password) {
		this.username = username;
		this.password = password;
		isLoggedIn = true;
	}
	
	@Override
	public boolean pay(double amount) {
		if (isLoggedIn) return true;
		return false;
	}
	
}

class Password {
	private int password;
	
	public Password() {
		
	}
	
	public Password(int password) {
		this.password = password;
	}
}

class AESEncryption {
	
	public AESEncryption() {
		
	}

    public static byte[] encrypt(String message, String secretKey) throws Exception {
        Key key = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
    }

    public static String decrypt(byte[] encryptedMessage, String secretKey) throws Exception {
        Key key = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return new String(cipher.doFinal(encryptedMessage), StandardCharsets.UTF_8);
    }

}

//Interfaces:
interface Colorable {
	void paint(String color);
}

interface Rideable {
	void ride();
}

interface Paymentmethod {
	boolean pay(double amount);
}