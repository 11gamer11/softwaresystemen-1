package ss.week3.hotel;
import java.io.PrintStream;

public class Bill {
	public PrintStream output;
	public double totalPrice;
	
	public static interface Item{
		public double getAmount();
	}
	
	public Bill(PrintStream outStream){
		this.output = outStream;
		this.totalPrice = 0;
	}
	
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
