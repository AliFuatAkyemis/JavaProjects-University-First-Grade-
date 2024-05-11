import java.nio.file.attribute.AclEntry;
import java.util.ArrayList;
//@author Ali Fuat Akyemiş - 20210808043
//@since 08.04.2023 - 17:16
public class Assignment02_20210808043 {
	public static void main(String[] args) {
		Store s = new Store("Migros", "www.migros.com.tr");
		
		Customer c = new Customer("CSE 102");
		
		ClubCustomer cc = new ClubCustomer("Club CSE 102", "05551234567");
//		s.addCustomer(c);
		s.addCustomer(cc);
		
		Product p = new Product(123456L, "Computer", 20, 1000.00);
		FoodProduct fp = new FoodProduct(456789L, "Snickers", 100, 2, 250, true, true, true, false);
		CleaningProduct cp = new CleaningProduct(31654L, "Mop", 28, 99, false, "Multi-room");
		
		s.addProduct(p);
		s.addProduct(fp);
		s.addProduct(cp);
		
		System.out.println(s.getInventorySize());
//		System.out.println(s.getProduct("shoes"));
		
		System.out.println(cp.purchase(2));
		s.getProduct("Computer").addToInventory(3);
//		System.out.println(fp.purchase(200));
		
		c.addToCart(p, 2);
//		c.addToCart(s.getProduct("Snickers"), -2);
		c.addToCart(s.getProduct("Snickers"), 1);
		System.out.println("Total due - " + c.getTotalDue());
		System.out.println("\n\nReceipt:\n" + c.receipt());
		
		System.out.println("Total due - " + c.getTotalDue());
//		System.out.println("After paying: " + c.pay(2000));		
		System.out.println("After paying: " + c.pay(2020));		
		System.out.println("Total due - " + c.getTotalDue());
		System.out.println("\n\nReceipt 1:\n" + c.receipt());
		
//		Customer c2 = s.getCustomer("05551234568");
		cc.addToCart(s.getProduct("Snickers"), 2);
		cc.addToCart(s.getProduct("Snickers"), 1);
		System.out.println("\n\nReceipt 2:\n" + cc.receipt());
		
		Customer c3 = s.getCustomer("05551234567");
		c3.addToCart(s.getProduct("Snickers"), 10);
		System.out.println("\n\nReceipt 3:\n" + cc.receipt());
		
		System.out.println(((ClubCustomer)c3).pay(26, false));
		
		c3.addToCart(s.getProduct(31654L), 3);
		System.out.println(c3.getTotalDue());
		System.out.println(c3.receipt());
		System.out.println(cc.pay(3*99, false));
		
		c3.addToCart(s.getProduct(31654L), 3);
		System.out.println(c3.getTotalDue());
		System.out.println(c3.receipt());
		System.out.println(cc.pay(3*99, true));
		
			
		
		
//		Store s = new Store("Migros","www.migros.com");
//		
//		Customer c = new Customer("CSE 102");
//		System.out.println(c);
//		
//		ClubCustomer cc = new ClubCustomer("Club CSE 102", "0555124567");
//		cc.addPoints(20);
//		cc.addPoints(30);
//		System.out.println(cc.getPhone());
//		System.out.println(cc);
//		
//		
//		Product p = new Product("1234", "Computer", 20, 1000.00);
//		FoodProduct fp = new FoodProduct("3456", "Snickers", 100, 2, 250, true, true, true, false);
//		CleaningProduct cp = new CleaningProduct("5678", "Mop", 28, 99, false, "Multi-room");
//		
//		s.addProduct(p);
//		s.addProduct(fp);
//		
//		
//		for(int i = 0; i < s.getInventorySize(); i++)
//			System.out.println(s.getProduct(i));
//		s.addProduct(cp);
//		
//		s.addProduct(new Product("4321", "iPhone", 50, 99.00));
//		
//		System.out.println(s.getProductIndex(new FoodProduct("8888", "Apples", 500, 1, 50, false, false, false, false)));
//		
//		System.out.println(cp.purchase(2));
//		if(fp.containsGluten())
//			System.out.println("My wife cannot eat or drink " + fp.getName());
//		else
//			System.out.println("My wife can eat or drink " + fp.getName());
//		
//		if(fp.containsPeanuts())
//			System.out.println("My friend cannot eat or drink " + fp.getName());
//		else
//			System.out.println("My friend can eat or drink " + fp.getName());
//		s.getProduct(0).addToInventory(3);
//		
//		for(int i = 0; i < s.getInventorySize(); i++) {
//			Product cur = s.getProduct(i);
//			System.out.println(cur);
//			for(int j = i + 1; j < s.getInventorySize(); j++) {
//				if(cur.equals(s.getProduct(j)))
//					System.out.println(cur.getName() + " is the same price as " + s.getProduct(j).getName());
//			}
//			//fix total due problems and let it go pls...
//		}
			
			
	}

}

class Product {
	private Long id;
	private String name;
	private int quantity;
	private double price;
	
	public Product() {
		
	}
	
	public Product(Long id, String name, int quantity, double price) {
		this.id = id;
		this.name = name;
		if (quantity >= 0) this.quantity = quantity;
		else throw new InvalidAmountException(quantity);
		if (price >= 0) this.price = price;
		else throw new InvalidPriceException(price);
	}

	public void setId(Long id) {this.id = id;}
	
	public Long getId() {return id;}
	
	public void setName(String name) {this.name = name;}
	
	public String getName() {return name;}
	
	public void setPrice(double price) {
		if (price < 0) throw new InvalidPriceException();
		else this.price = price;
	}
	
	public double getPrice() {return price;}
	
	public int remaining() {return quantity;}
	
	public int addToInventory(int amount) {
		if (amount >= 0) this.quantity += amount;
		else throw new InvalidAmountException(amount);
		return this.quantity;
	}
	
	public double purchase(int amount) {
		if (amount > this.quantity) throw new InvalidAmountException(amount, this.quantity);
		else if (amount < 0) throw new InvalidAmountException(amount);
		else {
			this.quantity -= amount;
			return (amount*this.price);
		}
	}
	
	public String toString() {return "Product "+this.name+" has "+this.quantity+" remaining";}
	
	public boolean equals(Product product) {
		if (this.price == product.getPrice()) return true;
		return false;
	}
	
}

class FoodProduct extends Product {
	private int calories;
	private boolean dairy;
	private boolean eggs;
	private boolean peanuts;
	private boolean gluten;
	
	public FoodProduct (Long id, String name, int quanitity, double price, int calories, boolean dairy, boolean eggs, boolean peanuts, boolean gluten) {
		super(id, name, quanitity, price);
		if (calories < 0) throw new InvalidAmountException();
		else this.calories = calories;
		this.dairy = dairy;
		this.eggs = eggs;
		this.peanuts = peanuts;
		this.gluten = gluten;
	}
	
	public void setCalories(int calories) {
		if (calories < 0) throw new InvalidAmountException();
		else this.calories = calories;
	}
	
	public int getCalories() {return calories;}
	
	public boolean containsDairy() {return this.dairy;}
	
	public boolean containsEggs() {return this.eggs;}
	
	public boolean containsPeanuts() {return this.peanuts;}
	
	public boolean containsGluten() {return this.gluten;}
	
}

class CleaningProduct extends Product {
	private boolean Liquid;
	private String WhereToUse;
	
	public CleaningProduct (Long id, String name, int quantity, double price, boolean isLiquid, String WhereToUse) {
		super(id, name, quantity, price);
		this.Liquid = isLiquid;
		this.WhereToUse = WhereToUse;
	}
	
	public void setWhereToUse(String WhereToUse) {this.WhereToUse = WhereToUse;}
	
	public String getWhereToUse() {return this.WhereToUse;}
	
	public boolean isLiquid() {return this.Liquid;}
} 

class Customer {
	private String name;
	protected ArrayList<Product> cartProducts = new ArrayList<Product>();
	protected ArrayList<Integer> cartCounts = new ArrayList<Integer>();
	
	public void addToCart(Product product, int count) {
		try {
			product.purchase(count);
			cartProducts.add(product);
			cartCounts.add(count);
			receipt();
		} catch (InvalidAmountException e) {
			System.out.println("ERROR: InvalidAmountException: " + count);
		}
	}
	
	public String receipt() {
		String receipt = "";
		for (int i = 0;i < cartProducts.size();i++) {
			receipt += cartProducts.get(i).getName() + " - " + cartProducts.get(i).getPrice() + " x ";
			receipt += cartCounts.get(i) + " = " + (cartProducts.get(i).getPrice()*cartCounts.get(i)) + "\n";
		}
		receipt += "-".repeat(40);
		receipt += "\nTotal due - " + getTotalDue();
		return receipt;
	}
	
	public double getTotalDue() {
		double totalDue = 0;
		for (int i = 0;i < cartCounts.size();i++) totalDue += (cartProducts.get(i).getPrice()*cartCounts.get(i));
		return totalDue;
	}
	
	public String displaymentTotalDue() {
		return "Total due - " + getTotalDue();
	}
	
	public double pay(double amount) {
		double change;
		if (amount >= getTotalDue()) {
			System.out.println("Thank you for shopping with us");
			change = Math.abs(getTotalDue()-amount);
			cartProducts.removeAll(cartProducts);
			cartCounts.removeAll(cartCounts);
		} else throw new InsufficientFundsException(getTotalDue(), amount);
		return change;
		
	}
	
	public Customer (String name) {this.name = name;}
	
	public void setName(String name) {this.name = name;}
	
	public String getName() {return this.name;}
	
	public String toString() {return this.name;}
}

class ClubCustomer extends Customer {
	private String phone;
	private int points;
	
	public ClubCustomer (String name, String phone) {
		super(name);
		this.phone = phone;
		this.points = 0;
	}
	
	public double pay(double amount, boolean usePoints) {
		double reduceAmount = 0;
		double change;
		if (usePoints) reduceAmount = (this.points*0.01);
		if (amount >= (getTotalDue()-reduceAmount)) {
			this.points = 0;
			System.out.println("Thank you for shopping with us...");
			this.points = (int) (getTotalDue()-reduceAmount);
			change = Math.abs(getTotalDue()-reduceAmount-amount);
			cartProducts.removeAll(cartProducts);
			cartCounts.removeAll(cartCounts);
			
		} else throw new InsufficientFundsException((getTotalDue()-reduceAmount), amount);
		return change;
	}
	
	public void setPhone(String phone) {this.phone = phone;}
	
	public String getPhone() {return this.phone;}
	
	public int getPoints() {return this.points;}
	
	public void addPoints(int points) {if (!(points < 0)) this.points += points;}
	
	public String toString() {return this.getName()+" has "+this.points;}
	
}

class Store {
	private String name;
	private String website;
	private ArrayList<ClubCustomer> clubCustomers = new ArrayList<ClubCustomer>();
	private ArrayList<Product> products = new ArrayList<Product>();
	
	public Store(String name, String website) {
		this.name = name;
		this.website = website;
		
	}
	
	public void setName(String name) {this.name = name;}
	
	public String getName() {return this.name;}
	
	public void setWebsite(String website) {this.website = website;}
	
	public String getWebsite() {return this.website;}
	
	public void addProduct(Product product) {products.add(product);}
	
	public void addProduct(Product product, int index) {products.add(index, product);}
	
	public Product getProduct(Long ID) {
		Product product = null;
		boolean isFound = false;	
		for (int i = 0;i < products.size();i++) {
			if (ID.intValue() == products.get(i).getId().intValue()) {
				isFound = true;
				product = products.get(i);
			}
			
		}
		if (isFound) return product;
		else throw new ProductNotFoundException(ID);
	}
	
	public Product getProduct(String name) {
		Product product = null;
		boolean isFound = false;
		for (int i = 0;i < products.size();i++) {
			if (name == products.get(i).getName()) {
				isFound = true;
				product = products.get(i);
			}
			
		}
		if (isFound) return product;
		else throw new ProductNotFoundException(name);
	}
	
	public void addCustomer(ClubCustomer customer) {
		clubCustomers.add(customer);
	}
	
	public ClubCustomer getCustomer(String phone) {
		int customerIndex = 0;
		boolean isFound = false;
		for(int i = 0;i < clubCustomers.size();i++) {
			if (phone == clubCustomers.get(i).getPhone()) {
				isFound = true;
				customerIndex = i;
			}
		}
		if (isFound) return clubCustomers.get(customerIndex);
		else throw new CustomerNotFoundException(phone);
	}
	
	public void removeProduct(Long ID) {
		if ((ID.intValue()) < products.size()) products.remove((ID.intValue()));
		else throw new ProductNotFoundException(ID);
	}
	
	public void removeProduct(String name) {
		int productIndex = 0;
		boolean isFound = false;
		for(int i = 0;i < products.size();i++) {
			if(name == products.get(i).getName()) {
				isFound = true;
				productIndex = i;
			}
		}
		if (isFound) products.remove(productIndex);
		else throw new ProductNotFoundException(name);
	}
	
	public void removeCustomer(String phone) {
		int customerIndex = 0;
		boolean isFound = false;
		for(int i = 0;i < clubCustomers.size();i++) {
			if (phone == clubCustomers.get(i).getPhone()) {
				isFound = true;
				customerIndex = i;
			}
		}
		if (isFound) clubCustomers.remove(customerIndex);
		else throw new CustomerNotFoundException();
	}
	
	public int getProductIndex(Product product) {return products.indexOf(product);}
	
	public int getInventorySize() {return products.size();}
	
}

class CustomerNotFoundException extends IllegalArgumentException {
	
	private String phone;
	
	public CustomerNotFoundException() {
		
	}
	
	public CustomerNotFoundException(String phone) {
		this.phone = phone;
	}
	
	public String toString() {
		return "CustomerNotFoundException: " + this.phone;
	}
	
}

class InsufficientFundsException extends RuntimeException {
	
	private double total;
	private double payment;
	
	public InsufficientFundsException() {
		
	}
	
	public InsufficientFundsException(double total, double payment) {
		this.total = total;
		this.payment = payment;
	}
	
	public String toString() {
		return "InsufficientFundsException " + this.total + " due, but only " + this.payment + " given";
	}
	
}

class InvalidAmountException extends RuntimeException {
	
	private int amount;
	private int quantity;
	private boolean check = true;
	
	public InvalidAmountException() {
		
	}
	
	public InvalidAmountException(int amount) {
		this.amount = amount;
		check = false;
	}
	
	public InvalidAmountException(int amount, int quantity) {
		this.amount = amount;
		this.quantity = quantity;
	}
	
	public String toString() {
		String returning = "InvalidAmountException: " + this.amount;
		if (check) returning += (" was requested, but only " + this.quantity + " remaining");
		return returning;
	}
	
}

class InvalidPriceException extends RuntimeException {
	
	private double price;
	
	public InvalidPriceException() {
		
	}
	
	public InvalidPriceException(double price) {
		this.price = price;
	}
	
	public String toString() {
		return "InvalidPriceException: " + this.price;
	}
	
}

class ProductNotFoundException extends IllegalArgumentException {
	
	private Long ID;
	private String name;
	
	public ProductNotFoundException(Long ID) {
		this.ID = ID;
		this.name = null;
	}
	
	public ProductNotFoundException(String name) {
		this.name = name;
		this.ID = new Long((int) (Math.random()*(2000-1000+1)+1000));
	}
	
	public String toString() {
		if(this.name == null) return "ProductNotFoundException: ID - " + this.ID;
		else return "ProductNotFoundException: Name - " + this.name;
	}
	
}



