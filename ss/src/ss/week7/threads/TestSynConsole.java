package ss.week7.threads;
/**
 * @author Sophie
 *
 */
public class TestSynConsole extends Thread {
	/**.
	 * voert de method sum uit
	 */
	public void run() {
		sum();
	}
	/**
	 * @return som van twee getallen die worden gevraagd via t console
	 */
	private synchronized String sum() {
		String thread = this.getName();
		int getal1 = SynConsole.readInt("get number 1?");
		int getal2 = SynConsole.readInt("get number 2?");
		return (thread + ": " + getal1 + "+" + getal2 + "=" + (getal1+getal2));
	}
	
	TestSynConsole(String name){
		this.setName(name);
	}
	
	public static void main(String[] args) throws InterruptedException{
		TestSynConsole console1 = new TestSynConsole("Thread A");
		TestSynConsole console2 = new TestSynConsole("Thread B");
		System.out.println(console1.sum());
		try {
			console1.join();
		}
		catch (InterruptedException e){ 
			throw e;			
		}
		System.out.println(console2.sum());
	}
}
