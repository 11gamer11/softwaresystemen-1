package ss.week3;

public class Format {
	
	public static void printLine(String text, double amount){
		String f = String.format("%-50s%10.2f", text, amount);
		System.out.println(f);
	}
	
	public static void main(String[] args){
		printLine("It's over", 9000);
		printLine("Amount cooler:", 20);
		printLine("Gigawatts:", 1.21);
		printLine("The answer to life, the universe, and everything:", 42);
	}
	
}
