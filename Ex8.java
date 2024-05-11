
public class Ex8_20210808043 {
	
}

//Interfaces:
interface Sellable {
	String getName();
	double getPrice();
}

interface Package<T> {
	T extract();
	boolean pack(T item);
	boolean isEmpty();
}

interface Wrappable extends Sellable {
	
}

//Class:
abstract class Product implements Sellable {
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
		return getClass().getName() + " (" + name + "," + price + ")";
	}
	
}

class Mirror extends Product {
	private int width;
	private int height;
	
	public Mirror(int width,int height) {
		super("mirror", 2);
		this.width = width;
		this.height = height;
	}
	
	public int getArea() {
		return width * height;
	}
	
	public <T> T reflect(T item) {
		System.out.println("The item that reflected: " + item);
		return item;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getPrice() {
		// TODO Auto-generated method stub
		return 0;
	}	
	
}

class Paper extends Product implements Wrappable {
	private String note;
	
	public Paper(String Note) {
		super("A4", 3);
		this.note = note;
	}
	
	public void setNote(String note) {
		this.note = note;
	}
	
	public String getNote() {
		return this.note;
	}
	
}

class Matroschka<T extends Wrappable> extends Product implements Wrappable, Package<T> {
	private T item;
	
	public Matroschka(T item) {
		super("Doll", 5 + item.getPrice());
		this.item = item;
	}

	@Override
	public T extract() {
		if (!isEmpty()) {
			T item = this.item;
			this.item = null;
			return item;
		}
		return null;
	}

	@Override
	public boolean pack(T item) {
		if (isEmpty()) {
			this.item = item;
			return true;
		}
		return false;
	}

	@Override
	public boolean isEmpty() {
		if (item == null) return true;
		return false;
	}
	
}

class Box<T extends Sellable> implements Package<T> {
	private T item;
	private boolean seal;
	
	public Box() {
		this.item = null;
		this.seal = false;
	}
	
	public Box(T item) {
		this.item = item;
		this.seal = true;
	}
	
	@Override
	public T extract() {
		if (!isEmpty()) {
			T item = this.item;
			this.item = null;
			this.seal = false;
			return item;
		}
		return null;
	}

	@Override
	public boolean pack(T item) {
		if (isEmpty()) {
			this.item = item;
			return true;
		}
		return false;
	}

	@Override
	public boolean isEmpty() {
		if (item == null) return true;
		return false;
	}
	
	public String toString() {
		return getClass().getName() + " {" + item + "} Seal: " + seal;
	}
	
}
