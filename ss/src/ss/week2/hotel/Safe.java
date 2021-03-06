package ss.week2.hotel;

import ss.week1.Password;

public class Safe {
	public Password password;
	public enum State {OFF,ON};
	private State state = State.OFF;
	private OpenState safeOpen = OpenState.CLOSED;
	public enum OpenState {OPENED, CLOSED};
	
	public Safe(Password safePassword){
		this.password = safePassword;
		assert this.password == safePassword;
	}
	
	//Activates safe if password is correct.
	public void activate(String passwordTest){
		if (password.testWord(passwordTest)){
			this.state = State.ON;
			assert this.state == State.ON;
		}
	}
	
	//Deactivates safe and closes it.
	public void deactivate(){
		close();
		this.state = State.OFF;
		assert this.state == State.OFF;
	}
	
	//Opens safe if password is correct and safe is activated.
	public void open(String passwordTest){
		if (password.testWord(passwordTest)){
			if (this.state == State.ON){
				this.safeOpen = OpenState.OPENED;
				assert this.safeOpen == OpenState.OPENED;
			}
		}
	}
	
	//Closes safe.
	public void close(){
		this.safeOpen = OpenState.CLOSED;
	}
	
	//Checks if safe is active.
	//@pure;
	public boolean isActive(){
		return this.state == State.ON;
	}
	
	//Check if safe is open.
	//@pure;
	public boolean isOpen(){
		return this.safeOpen == OpenState.OPENED;
	}
	
	//Returns password object.
	//@pure;
	public Password getPassword(){
		return this.password;
	}
	
	public static void main (String[] args){
		SafeTest test = new SafeTest();
		test.runTest("password");
	}
	
	public String toString(){
		return "Safe:"+state;
	}

}