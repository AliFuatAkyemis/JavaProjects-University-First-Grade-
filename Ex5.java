import java.util.ArrayList;

public class Ex5_20210808043 {
	public static void main(String[] args) throws Exception {
		Customer customer = new Customer("Ali Fuat");
		Account account1 = new Account("123456", 25);
		Account account2 = new Account("123457", 26);
		Account account3 = new Account("123458", 30);
		Account account4 = new Account("123459", 22);
		customer.addAccount(account1);
		customer.addAccount(account2);
		customer.addAccount(account3);
		customer.addAccount(account4);
		System.out.println();
		customer.transfer("123456", "123457", -2);
	}

}

class Account {
	private String accountNumber;
	private double balance;
	
	public Account(String accountNumber, double balance) {
		this.accountNumber = accountNumber;
		if (balance < 0) throw new InsufficientFundsException(balance);
		else this.balance = balance;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void deposit(double amount) throws Exception {
		if (amount < 0) throw new InvalidTransactionException(amount);
		else this.balance += amount;
	}
	
	public void withdraw(double amount) throws Exception {
		if (amount < 0) throw new InvalidTransactionException(amount);
		else if (this.balance < amount) throw new InsufficientFundsException(this.balance, amount);
		else this.balance -= amount;
	}
	
	public String toString() {
		return "Account: " + this.accountNumber + ", Balance: " + this.balance;
	}
	
}

class Customer {
	private String name;
	private ArrayList <Account> accounts = new ArrayList<Account>();
	
	public Customer(String name) {
		this.name = name;
	}
	
	public Account getAccount(String accountNumber) {
		boolean isFound = false;
		for (int i = 0;i < accounts.size();i++) {
			if (accountNumber.equals(accounts.get(i).getAccountNumber())) {
				isFound = true;
				return accounts.get(i);
			}
		}
		if (!isFound) throw new AccountNotFoundException(accountNumber);
		return null;
	}
	
	public void addAccount(Account account) {
		try {
			getAccount(account.getAccountNumber());
			throw new AccountAlreadyExistsException(account.getAccountNumber());
		} catch (AccountNotFoundException e) {
			accounts.add(account);
		} finally {
			System.out.println("Added account: " + account.getAccountNumber() + " with " + account.getBalance());
		}
	}
	
	public void removeAccount(String accountNumber) {
		boolean isFound = false;
		for (int i = 0;i < accounts.size();i++) {
			if (accountNumber.equals(accounts.get(i).getAccountNumber())) {
				accounts.remove(i);
				isFound = true;
			}
		}
		if (!isFound) throw new AccountNotFoundException(accountNumber);
		
	}
	
	public void transfer(String fromAccount, String toAccount, double amount) throws Exception {
		try {
			for (int i = 0;i < accounts.size();i++) {
				if (fromAccount.equals(accounts.get(i).getAccountNumber())) {
					accounts.get(i).withdraw(amount);
				}
				if (toAccount.equals(accounts.get(i).getAccountNumber())) {
					accounts.get(i).deposit(amount);
				}
				
			}
		} catch (InvalidTransactionException e) {
			throw new InvalidTransactionException(e, "cannot transfer funds from account " + fromAccount + " to account " + toAccount);
		}
	}
	
	public String toString() {
		String returning = "Customer " + name + ":\n";
		for (int i = 0;i < accounts.size();i++) {
			returning += ("\t"+accounts.get(i));
		}
		return returning;
	}
	
	public void list() {
		for (int i = 0;i < accounts.size();i++) {
			System.out.println("Added account: " + accounts.get(i).getAccountNumber() + " with " + accounts.get(i).getBalance());
		}
	}
	
}

class InsufficientFundsException extends RuntimeException {
	public InsufficientFundsException(double balance) {
		super("Wrong balance: " + balance);
	}
	public InsufficientFundsException(double balance, double amount) {
		super("Required amount is " + amount + " but only " + balance + " remaining");
	}
}

class AccountAlreadyExistsException extends RuntimeException {
	public AccountAlreadyExistsException(String accountNumber) {
		super("Account number " + accountNumber + " already exists");
	}
}

class AccountNotFoundException extends RuntimeException {
	public AccountNotFoundException(String accountNumber) {
		super("Account number " + accountNumber + " already exists");
	}
}

class InvalidTransactionException extends Exception {
	public InvalidTransactionException(double amount) {
		super("Invalid amount: " + amount);
	}
	public InvalidTransactionException(InvalidTransactionException e, String message) {
		super(message + ":\n\t" + e.getMessage());
	}
}
