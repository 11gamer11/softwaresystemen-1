package ss.week3.hotel;

import ss.week2.hotel.Room;
import ss.week2.hotel.Password;
import ss.week3.hotel.Bill.Item;

public class PricedRoom extends Room implements Bill.Item{
	private double roomprice;
	//@requires no>0;
	//@requires roomprice>0;
	//@requires safeprice>0;
	//@ensures getAmount() == roomprice;
	//@ensures ((PricedSafe) getSafe()).getAmount()==safeprice;
	public PricedRoom(int no, double roomprice, double safeprice) {
		super(no, new PricedSafe(new Password(),safeprice));
		this.roomprice = roomprice;
		assert ((PricedSafe) super.getSafe()).getAmount()==safeprice;
		assert this.roomprice == roomprice;
	}
	
	//@requires getAmount()>0;
	//@pure
	public double getAmount(){
		return (this.roomprice);
	}
	
	//@pure
	public String toString(){
		String string = super.toString();
		return (string+"/nRoomprice:"+this.roomprice);
	}

}
