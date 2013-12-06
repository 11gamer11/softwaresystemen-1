package ss.week2.hotel;

public class Hotel {
	
	private String name;
	private Password password;
	private Room room1;
	private Room room2;
	
	
	public Hotel(String name){
		this.name = name;
		this.password = new Password();
		this.room1 = new Room(101, new Safe(this.password));
		this.room2 = new Room(102, new Safe(this.password));
	}
	
	public Room checkIn(String password, String name){
		
		// If hotel full, return null
		if(this.getFreeRoom() == null){return null;}
		
		// If password is correct
		if(this.password.testWord(password)){
			// If the guest is not checked in already
			if((room1.getGuest() == null) && (room2.getGuest() == null) || (room1.getGuest() == null) && (room2.getGuest().getName() != name) || (room1.getGuest().getName() != name) && (room2.getGuest() == null) || (room1.getGuest().getName() != name) && (room2.getGuest().getName() != name)){
				Room room = this.getFreeRoom();
				Guest guest = new Guest(name);
				guest.checkin(room);
				return room;
			}// else, guest already checked in
		}// else, password wrong
		
		// something went wrong, return null
		return null;
		
	}
	
	public void checkOut(String name){
		if(room1.getGuest() != null && room1.getGuest().getName().equals(name)){
			room1.getGuest().checkout();
			room1.getSafe().deactivate();
		}
		if(room2.getGuest() != null && room2.getGuest().getName().equals(name)){
			room2.getGuest().checkout();
			room2.getSafe().deactivate();
		}
	}
	
	//@pure
	public Room getFreeRoom(){
		if(room1.getGuest() == null){
			//System.out.println("Room 1 free.");
			//System.out.println("Return: "+room1.toString());
			return room1;
		}else if(room2.getGuest() == null){
			//System.out.println("Room 2 free.");
			//System.out.println("Return: "+room2.toString());
			return room2;
		}
		//System.out.println("No free rooms.");
		//System.out.println("Return: null");
		return null;
	}
	
	//@pure
	public Room getRoom(String name){
		if(room1.getGuest() != null && room1.getGuest().getName() == name){
			//System.out.println("Guest found in: "+room1.toString());
			return room1;
		}
		if(room2.getGuest() != null && room2.getGuest().getName() == name){
			//System.out.println("Guest found in: "+room2.toString());
			return room2;
		}
		return null;
	}
	
	//@pure
	public Password getPassword(){
		return this.password;
	}
	
	//@pure
	public String toString(){
		
		Guest room1Guest = room1.getGuest();
		Guest room2Guest = room2.getGuest();
		String room1GuestString = "None";
		String room2GuestString = "None";
		if(room1Guest != null){room1GuestString = room1Guest.toString();}
		if(room2Guest != null){room2GuestString = room2Guest.toString();}
		
		String description = "Hotel: "+getName()+"\n"
				+ " " + room1.toString() + "\n"
				+ "  " + room1GuestString + "\n"
				+ "  " + room1.getSafe().toString() + "\n"
				+ " " + room2.toString() + "\n"
				+ "  " + room2GuestString + "\n"
				+ "  " + room2.getSafe().toString() + "\n";
		return description;
	}
	
	//@pure
	public String getName(){
		return this.name;
	}
}
