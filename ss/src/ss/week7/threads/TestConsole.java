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
	private String sum() {
		String thread = this.getName();
		int getal1 = Console.readInt("get number 1?");
		int getal2 = Console.readInt("get number 2?");
		return (thread + ": " + getal1 + "+" + getal2 + "=" + (getal1+getal2));
	}
	
	TestConsole(String name){
		this.setName(name);
	}
	
	public static void main(String[] args){
		TestConsole console1 = new TestConsole("Thread A");
		TestConsole console2 = new TestConsole("Thread B");
		System.out.println(console1.sum());
		System.out.println(console2.sum());
	}
}
