package ss.week1;

/**
 * @author Kevin Alberts
 *
 */

public class ThreeWayLamp {
	
	public enum State {OFF,LOW,MED,HIGH};
	
	private State state = State.OFF;

	//@ ensures newState == getState();
	public void setState(State newState){
		this.state = newState;
		assert this.state == newState;
	}
	
	//@pure
	public State getState(){
		return this.state;
	}
	
}