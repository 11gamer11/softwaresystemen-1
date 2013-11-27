package ss.week3;

public class StrongChecker extends BasicChecker{

	public boolean acceptable(String suggestion) {
		boolean basic = super.acceptable(suggestion);
		boolean strong = (suggestion.substring(0,1).matches("[a-zA-Z]")&&(suggestion.substring(suggestion.length()-1).matches("[0-9]")));
		
		return basic && strong;
	}
	
}
