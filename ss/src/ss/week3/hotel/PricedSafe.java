package ss.week3.hotel;

public class PricedSafe extends ss.week2.hotel.Safe implements Bill.Item{

	private double price;

	public PricedSafe(String safePassword, double price) {
		super(safePassword);
		this.price = price;
	}

	public double getAmount() {
		return this.price;
	}
	
	public String toString(){
		return super.toString()+"\nSafe-price:"+getAmount();
	}
	
	
	
}