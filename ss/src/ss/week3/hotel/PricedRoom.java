package ss.week3.hotel;

import ss.week2.hotel.Room;
import ss.week2.hotel.Password;

public class PricedRoom extends Room implements Bill.Item{
	public PricedRoom(int no, double roomprice, double safeprice) {
		super(no, new PricedSafe(new Password(),safeprice));		
	}

	@Override
	public double getAmount() {
		// TODO Auto-generated method stub
		return 0;
	}

}
