package ss.week6;

import java.util.Scanner;

public class Words {
	static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args){
		String[] words;
		System.out.println("Give sentence:");
		
		while (in.hasNextLine()){
			String input = in.nextLine();
			words = input.split(" ");
			if (words[0].equals("end")){
				System.out.println("End of programme");
				System.exit(0);
			}
			for(int i=0; i<words.length; i++){
				System.out.println("Word "+(i+1)+": "+words[i]);
			}
		}
	}
	
	/*public static void main(String[] args){
		String word = "";
		int n=0;
		System.out.println("Give sentence(with spaces)");
		while (in.hasNext()){
			word = in.next();
			n=n+1;
			if (word.equals("end")){
				System.out.println("End of programme");
				System.exit(0);
			}else{
			System.out.println("Word "+n+": "+word);}
		}
	}*/
}
