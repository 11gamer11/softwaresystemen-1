package ss.week3;

public class BasicChecker implements Checker {

	public boolean acceptable(String suggestion) {
		return !(suggestion.length()<6 || suggestion.contains(" "));
	}
	
}
