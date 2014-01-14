package ss.week6;
import java.util.Scanner;

public class Hallo {
	static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args){
		String line="";
		System.out.println("Give your name");
		while (in.hasNext()){
			line = in.nextLine();
			System.out.println("Hello "+ line);
		}
	}
	
}
