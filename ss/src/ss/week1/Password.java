package ss.week1;

public class Password {

	public static final String INITIAL = "af8923sdf3c2";
	public String password;
	
	public Password(){
		this.password = INITIAL;
	}
	
	public boolean acceptable(String suggestion){
		return (suggestion.length()>=6 && !suggestion.contains(" "));
	}
	
	public boolean setWord(String oldPass, String newPass){
		if(acceptable(newPass) && testWord(oldPass)){
			this.password = newPass;
			return true;
		}
		return false;
	}
	
	public boolean testWord(String test){
		return (password == test);
	}
	
}
