package ss.week2.hotel;

import ss.week3.BasicChecker;
import ss.week3.Checker;

/**
 * Class password manager
 * @author Sophie
 *
 */
public class Password {
	/**
	 * The standard initial password.
	 */
	private String currentpass;
	private String initPass;
	@SuppressWarnings("unused")
	private Checker checker;
	public static final String INITIAL = "asdf23dcmy234";
	
	public Password(){
		this(new BasicChecker());
	}
	
	public Password(Checker c){
		this.checker = c;
		this.initPass = c.generatePass();
		this.currentpass = this.initPass;
	}
	
	/**
	 * Test if a given string is an acceptable password
	 * @param suggestion - possible new password
	 * @return - true if password is acceptable otherwise false
	 */
	public boolean acceptable(String suggestion){
		return !(suggestion.length()<6 || suggestion.contains(" "));
	}
	
	/**
	 * Changes this password.
	 * Only if old password is the same as current password and if new password is acceptable.
	 * @param oldpass - old password
	 * @param newpass - new password
	 */
	public boolean setWord(String oldpass, String newpass){
		if(testWord(oldpass) && acceptable(newpass)){
			currentpass = newpass;
			return true;
		}
		return false;
	}
	
	/**
	 * Tests if a given word is equal to the current password.
	 * @param test - testing password
	 */
	public boolean testWord(String test){
		return (test == currentpass);
	}
}
