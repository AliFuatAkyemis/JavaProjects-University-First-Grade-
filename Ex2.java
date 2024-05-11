import java.util.Date;

public class Ex2_20210808043 {
	
}

class Ticket {
	private City from = new City();
	private City to = new City();
	private java.util.Date date = new Date();
	private int seat;
	
	public Ticket(String from, String to, java.util.Date date, int seat) {
		this.from.setName(from);
		this.to.setName(to);
		this.date = date;
		this.seat = seat;
	}
	
	public Ticket(String from, String to, int seat) {
		this.from.setName(from);
		this.to.setName(to);
		this.seat = seat;
		this.date.setDate(date.getDate()+1);
	}
	
	public City getFrom() {return this.from;}
	
	public City getTo() {return this.to;}
	
	public void setDate(java.util.Date date) {this.date = date;}
	
	public java.util.Date getDate() {return this.date;}
	
	public void setSeat(int seat) {this.seat = seat;}
	
	public int getSeat() {return this.seat;}
		
}

class City {
	private String postalCode;
	private String name;
	
	public City(String postalCode, String name) {
		this.postalCode = postalCode;
		this.name = name;
	}
	
	public City() {
		this.postalCode = "00000";
		this.name = "Name";
	}
	
	public void setPostalCode(String postalCode) {this.postalCode = postalCode;}
	
	public String getPostalCode() {return this.postalCode;}
	
	public void setName(String name) {this.name = name;}
	
	public String getName() {return this.name;}
}

class Person {
	private String name;
	private String surname;
	private String phoneNumber;
	
	public Person(String name, String surname, String phoneNumber) {
		this.name = name;
		this.surname = surname;
		this.phoneNumber = phoneNumber;
	}
	
	public String getName() {return this.name;}
	
	public String getSurname() {return this.surname;}
	
	public String getPhoneNumber() {return this.phoneNumber;}
	
	public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
	
}
