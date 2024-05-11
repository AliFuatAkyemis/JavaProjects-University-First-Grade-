import java.util.ArrayList;

public class Ex9_20210808043 {
	
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
	double getPriority();
}

interface Wrappable extends Sellable {
	
}

interface Common<T> {
	boolean isEmpty();
	T peek();
	int size();
}

interface Stack<T> extends Common<T> {
	boolean push(T item);
	T pop();
}

interface Node<T> {
	int DEFAULT_CAPACITY = 2;
	
	void setNext(T item);
	T getNext();
	double getPriority();
}

interface PriorityQueue<T> extends Common<T> {
	int FLEET_CAPACITY = 3;
	
	boolean enqueue(T item);
	T dequeue();
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

	@Override
	public double getPriority() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}

class Box<T extends Sellable> implements Package<T> {
	private T item;
	private boolean seal;
	private int distanceToAddress;
	
	public Box() {
		this.item = null;
		this.seal = false;
	}
	
	public Box(T item, int distanceToAddress) {
		this.item = item;
		this.seal = true;
		this.distanceToAddress = distanceToAddress;
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

	@Override
	public double getPriority() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}

class Container implements Stack<Box>, Node<Container>, Comparable<Container> {
	private int top;
	private int size;
	private Object[] boxes = new Object[size];
	private double priority;
	private Container next;
	
	public Container() {
		this.top = -1;
		this.next = null;
		this.priority = 0;
	}
	
	public String toString() {
		return "Container with priority: " + priority;
	}
	
	public Object[] getBoxes() {
		return boxes;
	}

	@Override
	public boolean isEmpty() {
		if (boxes.length == 0) return true;
		return false;
	}

	@Override
	public Box peek() {
		for (int i = 0;i < boxes.length;i++) {
			if (boxes[i] instanceof Box) return (Box) boxes[i];
		}
		return null;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public int compareTo(Container o) {
		if (o.size() > this.size) return 1;
		return 0;
	}

	@Override
	public void setNext(Container item) {
		item.priority++;
	}

	@Override
	public Container getNext() {
		return (Container) boxes[(int) priority];
	}

	@Override
	public double getPriority() {
		return this.priority;
	}

	@Override
	public boolean push(Box item) {
		ArrayList<Object> arrList = new ArrayList<Object>();
		for (int i = 0;i < boxes.length;i++) {
			arrList.add(boxes[i]);
		}
		arrList.add(item);
		boxes = arrList.toArray();
		return true;
	}
	
	public boolean pull(Box item) {
		ArrayList<Object> arrList = new ArrayList<Object>();
		for (int i = 0;i < boxes.length;i++) {
			arrList.add(boxes[i]);
		}
		arrList.remove(item);
		boxes = arrList.toArray();
		return true;
	}

	@Override
	public Box pop() {
		Object[] boxes_new = new Object[boxes.length-1];
        Object lastObject = boxes[boxes.length-1];
		for (int i = 0;i < boxes.length-1;i++) {
        	boxes_new[i] = boxes[i];
        }
		boxes = boxes_new;
		return (Box) lastObject;
	}
	
}

class CargoFleet implements PriorityQueue<Container> {
	private Container head;
	private int size;
	
	public CargoFleet() {
		this.head = null;
		this.size = 0;
	}

	@Override
	public boolean isEmpty() {
		if (size == 0) return true;
		return false;
	}

	@Override
	public Container peek() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		return this.size();
	}

	@Override
	public boolean enqueue(Container item) {
		if (item.size() <= FLEET_CAPACITY) {
			head.push(item.peek());
			size++;
			return true;
		}
		return false;
	}

	@Override
	public Container dequeue() {
		head.pop();
		size--;
		return this.head;
	}
}

class CargoCompany {
	private Container stack;
	private CargoFleet queue;
	
	public CargoCompany() {
		this.stack = null;
		this.queue = null;
	}
	
	public <T> void add(T box) {
		for (int i = 0;i < stack.getBoxes().length-1;i++) {
			stack.push((Box) box);
		}
		ship(queue);
	}
	
	private void ship(CargoFleet fleet) {
		for (int i = 0;i <= 3;i++) {
			if (!fleet.isEmpty()) {
				fleet.dequeue();
			}
		}
		empty(stack);
	}
	
	private void empty(Container container) {
		if (!container.isEmpty()) {
			System.out.println(deliver(container.pop()));
		}
	}
	
	private <T> Sellable deliver(T box) {
		return ((Box) box).extract();
	}
}