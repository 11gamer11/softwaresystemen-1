package ss.week7.threads;

public class SynchronizedCell implements IntCell{
	private int value=0;
	@Override
	public synchronized void setValue(int val) {
		//als waarde nog hetzelfde is dan moet het nog uitgelezen worden
		while (this.value == val){
			//ga slapen tot de waarde is gelezen
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println("Now read");
				this.value = val;
			}
		}
		this.value = val;
	}

	@Override
	public synchronized int getValue() {
		notifyAll();
		return value;
	}
}
