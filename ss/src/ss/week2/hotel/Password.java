package ss.week2.hotel;
/**
 * Class password manager
 * @author Sophie
 *
 */
public class Password {
	/**
	 * The standard initial password.
	 */
	public static final String INITIAL = "INITIAL";
	private String currentpass;
	
	/**
	 * Constructs a Password with the initial word provided in INITIAL.
	 */
	public Password(){
		this.currentpass = INITIAL;
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
