package ss.week3;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

public class StrongChecker extends BasicChecker{

	public boolean acceptable(String suggestion) {
		boolean basic = super.acceptable(suggestion);
		boolean strong = (suggestion.substring(0,1).matches("[a-zA-Z]")&&(suggestion.substring(suggestion.length()-1).matches("[0-9]")));
		
		return basic && strong;
	}
	
	public String generatePass() {
		char letter = (char)(new Random().nextInt(26) + 'a');
		String password = letter + new BigInteger(130, new SecureRandom()).toString(32) + (int) (Math.random()*10);
		if(this.acceptable(password)){
			return password;
		}else{
			return this.generatePass();
		}
	}
	
}
