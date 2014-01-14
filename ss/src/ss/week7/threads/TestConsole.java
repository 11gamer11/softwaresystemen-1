package ss.week7.threads;
/**
 * @author Sophie
 *
 */
public class TestConsole extends Thread {
	/**.
	 * voert de method sum uit
	 */
	public void run() {
		sum();
	}
	/**
	 * @return som van twee getallen die worden gevraagd via t console
	 */
	private void sum() {
		int getal1;
		int getal2;
		String thread = this.getName();
		synchronized (Console.class) {
			getal1 = Console.readInt(thread+": get number 1?");
			getal2 = Console.readInt(thread+": get number 2?");
		}
		System.out.println(thread + ": " + getal1 + "+" + getal2 + "=" + (getal1+getal2));
		
	}
	
	TestConsole(String name){
		this.setName(name);
	}
	
	public static void main(String[] args){
		TestConsole console1 = new TestConsole("Thread A");
		TestConsole console2 = new TestConsole("Thread B");
		console1.start();
		console2.start();
		
	}
}
