import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
//@author Ali Fuat Akyemiş - 20210808043
//@since 03.04.2023 - 18.43
public class Ex4_20210808043 {
	public static void main(String[] args) {
		
	}
}

class Computer {
	
	protected CPU cpu;
	protected RAM ram;
	
	public Computer (CPU cpu, RAM ram) {
		this.cpu = cpu;
		this.ram = ram;
	}
	
	public CPU getCpu() {
		return this.cpu;
	}
	
	public RAM getRam() {
		return this.ram;
	}
	
	public void run() {
		int sum = 0;
		for (int i = 1, j = 1;i < this.ram.getCapacity() && j < this.ram.getCapacity();i++,j++) {
			cpu.compute(sum, cpu.compute(ram.getValue(i-1, j-1), ram.getValue(i, j)));
		}
		ram.setValue(0, 0, sum);
	}
	
	public String toString() {
		return "Computer: " + cpu + " " + ram;
	}
	
}

class Laptop extends Computer {
	
	private int milliAmp;
	private int battery;
	
	public Laptop (CPU cpu, RAM ram, int milliAmp) {
		super(cpu, ram);
		this.milliAmp = milliAmp;
		this.battery = (milliAmp/10)*3;
	}
	
	public int batteryPercentage() {
		return (this.battery/this.milliAmp)*100;
	}
	
	public void charge() {
		while(this.battery <= 90) {
			this.battery += milliAmp*0.02;
		}
	}
	
	public void run() {
		if (batteryPercentage() >= 5) {
			super.run();
			this.battery -= milliAmp*0.03;
		} else {
			charge();
		}
	}
	
	public String toString() {
		return super.toString() + " " + this.battery;
	}
	
}

class Desktop extends Computer {
	
	private java.util.ArrayList<String> peripherals;
	
	public Desktop(CPU cpu, RAM ram, String[] varargsPeripherals) {
		super(cpu, ram);
		this.peripherals = (ArrayList<String>) Arrays.asList(varargsPeripherals);
	}

	public void run() {
		int sum = 0;
		for (int i = 0;i < super.getRam().getCapacity();i++) {
			for (int j = 0;j < super.getRam().getCapacity();j++) {
				super.getCpu().compute(sum, super.getRam().getValue(i, j));
			}
		}
		super.getRam().setValue(0, 0, sum);
	}
	
	public void plugIn(String peripheral) {
		peripherals.add(peripheral);
	}
	
	public String plugOut() {
		String lastPeripheral = peripherals.get(peripherals.size() - 1);
		peripherals.remove(peripherals.size()-1);
		return lastPeripheral;
	}
	
	public String plugOut(int index) {
		String peripheral = peripherals.get(index);
		peripherals.remove(index);
		return peripheral;
	}
	
	public String toString() {
		String peripheralsString = " ";
		for (int i = 0;i < peripherals.size();i++) {
			peripheralsString += peripherals.get(i);
		}
		return super.toString() + peripheralsString;
	}
		
}

class CPU {
	
	private String name;
	private double clock;
	
	public CPU (String name, double clock) {
		this.name = name;
		this.clock = clock;
	}
	
	public String getName() {
		return this.name;
	}
	
	public double getClock() {
		return this.clock;
	}
	
	public int compute(int a, int b) {
		return a+b;
	}

	public String toString() {
		return "CPU: " + name + " " + clock + "Ghz";
	}
	
}

class RAM {
	
	private String type;
	private int capacity;
	private int[][] memory;
	
	public RAM (String type, int capacity) {
		this.type = type;
		this.capacity = capacity;
		initMemory();
	}
	
	public String getType() {
		return this.type;
	}
	
	public int getCapacity() {
		return this.capacity;
	}
	
	private void initMemory() {
		memory = new int[this.capacity][this.capacity];
		Random rand = new Random();
		int upperbound = 11;
		for (int i = 0;i < memory.length;i++) {
			for (int j = 0;j < memory[0].length;j++) {	
				memory[i][j] = rand.nextInt(upperbound);
			}
		}
		
	}
	
	private boolean check(int i, int j) {
		if ((i < memory.length) && (j < memory[0].length)) return true;
		return false;
	}
	
	public int getValue(int i, int j) {
		if (check(i,j)) return memory[i][j];
		return -1;
	}
	
	public void setValue(int i, int j, int value) {
		if (check(i,j)) memory[i][j] = value;
	}
	
	public String toString() {
		return "RAM: " + type + " " + capacity + "GB";
	}
	
}