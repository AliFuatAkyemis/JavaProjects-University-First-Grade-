
public class Phys2_HW_20210808043 {
	public static void main(String[] args) {
		System.out.println("Question 1 : ");
		System.out.println("V0 : " + stickElecPot(1, 1, 1, 1));
		System.out.println("Question 2 : ");
		System.out.println("V1 : " + dotElecPot(1, 1, 1, 1, 1));
		System.out.println("Question 3 : ");
		System.out.println("V2 : " + dotElecPot(1, 1, 1, 1, 2));
		System.out.println("Question 4 : ");
		System.out.println("V4 : " + dotElecPot(1, 1, 1, 1, 4));
		System.out.println("Question 5 : ");
		System.out.println("V6 : " + dotElecPot(1, 1, 1, 1, 6));
		System.out.println("Question 6 : ");
		System.out.println("V8 : " + dotElecPot(1, 1, 1, 1, 8));
		System.out.println("Question 7 : ");
		System.out.println("VN : " + dotElecPot(1, 1, 1, 1, 2000000000));
	}
	
	public static double dotElecPot(double L/*Up/Down side length of shape*/, double x/*Horizontal distance between p point and charges*/, double Q/*The value of each charges*/, double p/*P point's charge*/, int N/*Number of chargers*/) {
		double k = (8.99 * Math.pow(10, -9));
		double yLength = (L / (N -1));
		double sumPot = 0;
		if (N == 1) return ((k * Q * p) / x);
		for (int i = 0;i < (N / 2);i++) {
			sumPot += ((k * (Q / ((double) N)) * p) / Math.hypot(yLength, x));
			yLength += ((2 * L) / (N - 1));
		}
		return sumPot * 2;
	}
	
	public static double stickElecPot(double L/*Up/Down side length of the stick*/, double x/*Horizontal distance between p point and the stick*/, double Q/*The charge of stick*/, double p/*P point's charge*/) {
		double k = (8.99 * Math.pow(10, -9));
		double sumPot = (((k * Q)/2L) * (Math.log(((Math.hypot(L, x) + L) / (Math.hypot(L, x) - L)))));
		
		return sumPot;
	}
	
}
