import java.util.ArrayList;
//@author Ali Fuat Akyemiş - 20210808043
//@since 28.03.2023 - 01:02
public class Assignment01_20210808043 {
	public static void main(String[] args) {
		Store s = new Store("Migros","www.migros.com");
		
		Customer c = new Customer("CSE 102");
		System.out.println(c);
		
		ClubCustomer cc = new ClubCustomer("Club CSE 102", "0555124567");
		cc.addPoints(20);
		cc.addPoints(30);
		System.out.println(cc.getPhone());
		System.out.println(cc);
		
		
		Product p = new Product("1234", "Computer", 20, 1000.00);
		FoodProduct fp = new FoodProduct("3456", "Snickers", 100, 2, 250, true, true, true, false);
		CleaningProduct cp = new CleaningProduct("5678", "Mop", 28, 99, false, "Multi-room");
		
		s.addProduct(p);
		s.addProduct(fp);
		
		
		for(int i = 0; i < s.getInventorySize(); i++)
			System.out.println(s.getProduct(i));
		s.addProduct(cp);
		
		s.addProduct(new Product("4321", "iPhone", 50, 99.00));
		
		System.out.println(s.getProductIndex(new FoodProduct("8888", "Apples", 500, 1, 50, false, false, false, false)));
		
		System.out.println(cp.purchase(2));
		if(fp.containsGluten())
			System.out.println("My wife cannot eat or drink " + fp.getName());
		else
			System.out.println("My wife can eat or drink " + fp.getName());
		
		if(fp.containsPeanuts())
			System.out.println("My friend cannot eat or drink " + fp.getName());
		else
			System.out.println("My friend can eat or drink " + fp.getName());
		s.getProduct(0).addToInventory(3);
		
		for(int i = 0; i < s.getInventorySize(); i++) {
			Product cur = s.getProduct(i);
			System.out.println(cur);
			for(int j = i + 1; j < s.getInventorySize(); j++) {
				if(cur.equals(s.getProduct(j)))
					System.out.println(cur.getName() + " is the same price as " + s.getProduct(j).getName());
			}
			
		}
			
			
	}

}

class Product {
	private String id;
	private String name;
	private int quantity;
	private double price;
	
	public Product(String id, String name, int quantity, double price) {
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}

	public void setId(String id) {this.id = id;}
	
	public String getId() {return id;}
	
	public void setName(String name) {this.name = name;}
	
	public String getName() {return name;}
	
	public void setPrice(double price) {this.price = price;}
	
	public double getPrice() {return price;}
	
	public int remaining() {return quantity;}
	
	public int addToInventory(int amount) {
		if (amount >= 0) {
			this.quantity += amount;
		}
		return this.quantity;
	}
	
	public double purchase(int amount) {
		if ((amount > this.quantity) || (amount < 0)) return 0;
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
	
	public FoodProduct (String id, String name, int quanitity, double price, int calories, boolean dairy, boolean eggs, boolean peanuts, boolean gluten) {
		super(id, name, quanitity, price);
		this.calories = calories;
		this.dairy = dairy;
		this.eggs = eggs;
		this.peanuts = peanuts;
		this.gluten = gluten;
	}
	
	public void setCalories(int calories) {this.calories = calories;}
	
	public int getCalories() {return calories;}
	
	public boolean containsDairy() {return this.dairy;}
	
	public boolean containsEggs() {return this.eggs;}
	
	public boolean containsPeanuts() {return this.peanuts;}
	
	public boolean containsGluten() {return this.gluten;}
	
}

class CleaningProduct extends Product {
	private boolean Liquid;
	private String WhereToUse;
	
	public CleaningProduct (String id, String name, int quantity, double price, boolean isLiquid, String WhereToUse) {
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
	
	public void setPhone(String phone) {this.phone = phone;}
	
	public String getPhone() {return this.phone;}
	
	public int getPoints() {return this.points;}
	
	public void addPoints(int points) {if (!(points < 0)) this.points += points;}
	
	public String toString() {return this.getName()+" has "+this.points;}
	
}

class Store {
	private String name;
	private String website;
	ArrayList<Product> products = new ArrayList<Product>();
	
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
	
	public Product getProduct(int index) {
		if ((index >= 0) && (index < products.size())) return products.get(index);
		return null;
	}
	
	public int getProductIndex(Product product) {return products.indexOf(product);}
	
	public int getInventorySize() {return products.size();}
	
}