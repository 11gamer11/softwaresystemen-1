package ss.week1;

public class TestThreeWayLamp {

	public static void main(String[] args) {
		
		ThreeWayLamp lamp = new ThreeWayLamp();
		
		System.out.println("Initial State: "+lamp.getState());
		lamp.setState(ThreeWayLamp.State.LOW);
		System.out.println("Low State: "+lamp.getState());
		lamp.setState(ThreeWayLamp.State.MED);
		System.out.println("Medium State: "+lamp.getState());
		lamp.setState(ThreeWayLamp.State.HIGH);
		System.out.println("High State: "+lamp.getState());
		lamp.setState(ThreeWayLamp.State.OFF);
		System.out.println("Off State: "+lamp.getState());
		
	}

}
