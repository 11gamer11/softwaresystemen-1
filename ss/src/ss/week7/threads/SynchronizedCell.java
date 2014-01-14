package ss.week7.threads;

public class SynchronizedCell implements IntCell{
	private int value=0;
	private boolean hasBeenSet;
	@Override
	public synchronized void setValue(int val) {
		//als waarde nog hetzelfde is dan moet het nog uitgelezen worden
		while (hasBeenSet){
			try {
				wait();
			}
			catch (InterruptedException e){
			}
		}
		this.value = val;
		hasBeenSet = true;
		notifyAll();
	}

	@Override
	public synchronized int getValue() {
		while (!hasBeenSet){
			try {
				wait();
			}
			catch (InterruptedException e){
			}
		}
		hasBeenSet = false;
		notifyAll();
		return value;
	}
}
