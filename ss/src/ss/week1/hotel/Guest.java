package ss.week1.hotel;

public class Guest {
	
	public String name;
	public Room room;
	
	public Guest(String n){
		this.name = n;
	}

	public boolean checkin(Room r){
		if(this.room==null && r.getGuest()==null){
			this.room = r;
			r.setGuest(this);
			return true;
		}
		return false;
	}

	public boolean checkout(){
		if(this.room!=null && this.room.getGuest()==this){
			this.room.setGuest(null);
			this.room = null;
			return true;
		}
		return false;
	}
	
	public String getName(){
		return this.name;
	}

	public Room getRoom(){
		return this.room;
	}
	
	public String toString(){
		return "Guest: "+getName();
	}
	
}
