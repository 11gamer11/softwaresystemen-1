package ss.week3.hotel;

import ss.week2.hotel.Password;

public class PricedSafe extends ss.week2.hotel.Safe implements Bill.Item{

	private double price;

	public PricedSafe(Password safePassword, double price) {
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
