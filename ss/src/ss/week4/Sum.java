package ss.week4;

public class Sum implements Function{

	public double apply(double arg) {
		return 0;
	}

	public Function derivative() {
		return null;
	}
	
	public Sum(Function g, Function h){
		
	}
	
	public String toString(){
		return ("f(x)=g(x)+h(x)");
	}
	
}
