package ss.week3;

import java.math.BigInteger;
import java.security.SecureRandom;

public class BasicChecker implements Checker {

	public boolean acceptable(String suggestion) {
		return !(suggestion.length()<6 || suggestion.contains(" "));
	}

	public String generatePass() {
		String password = new BigInteger(130, new SecureRandom()).toString(32);
		if(this.acceptable(password)){
			return password;
		}else{
			return this.generatePass();
		}
	}
}
