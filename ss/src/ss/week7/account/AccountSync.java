package ss.week7.account;

public class AccountSync {
	public static void main(String[] args) throws Throwable{
		Account account = new Account();
		MyThread mythread1 = new MyThread(-100,15,account);
		MyThread mythread2 = new MyThread(100,15,account);
		//Thread thread = new Thread(mythread1);
		mythread1.start();
		mythread2.start();
		mythread1.join();
		mythread2.join();
		System.out.println(account.getBalance());
		//vraag 6 van 7.18
		// MyThread body blijft hetzelfde
		//In AccountSync moet er een nieuwe Thread gemaakt worden
		// want MyThread extends Thread niet meer
		// Thread voert start() uit voor de MyThread als een soort wrapper
		// Hierbij moet de MyThread meegegeven worden aan de Thread constructor
	}
}
