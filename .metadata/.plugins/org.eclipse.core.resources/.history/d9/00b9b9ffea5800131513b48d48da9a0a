package ss.week3.hotel;
<<<<<<< HEAD

import java.io.PrintStream;

public class Bill {
	
	private PrintStream output;
	private double totalPrice;

	public static interface Item {
=======
import java.io.PrintStream;

public class Bill {
	public PrintStream output;
	public double totalPrice;
	
	public static interface Item{
>>>>>>> d36f4fd308c40be3f7d8a9384bdeb53c575f1316
		public double getAmount();
	}
	
	public Bill(PrintStream outStream){
		this.output = outStream;
		this.totalPrice = 0;
	}
	
<<<<<<< HEAD
	public void close(){
		this.output.format("%-50s%10.2f\n", "Total:", this.getSum());
	}
	
	public double getSum(){
		return this.totalPrice;
	}
	
	public void newItem(Bill.Item item){
		double price = item.getAmount();
		this.totalPrice += price;
		this.output.format("%-50s%10.2f\n", "Item Added:", price);
	}
	
}
=======
	//Print sum of the bill on output stream
	public void close(){
		this.output.format("%-50s%10.2f", this.getSum());
	}
	
	//Returns sum of bill
	public double getSum(){
		return totalPrice;
	}
	
	//Add item to bill
	public void newItem(Bill.Item item){
		double price = item.getAmount();
		this.totalPrice += price;
		this.output.format("%-50s%10.2f", price);
	}

}
>>>>>>> d36f4fd308c40be3f7d8a9384bdeb53c575f1316
