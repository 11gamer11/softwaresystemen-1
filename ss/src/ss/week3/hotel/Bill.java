package ss.week3.hotel;

import java.io.PrintStream;

public class Bill {
	
	private PrintStream output;
	private double totalPrice;

	public static interface Item {
		public double getAmount();
	}
	
	public Bill(PrintStream outStream){
		this.output = outStream;
		this.totalPrice = 0;
	}
	
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
