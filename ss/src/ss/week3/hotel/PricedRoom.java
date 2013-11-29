package ss.week3.hotel;

import ss.week2.hotel.Room;

public class PricedRoom extends Room implements Bill.Item{

	public PricedRoom(int no) {
		super(no);
	}

}
