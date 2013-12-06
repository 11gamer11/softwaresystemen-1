package ss.week4;

public class Exponent implements Function{
	public double n;
	public double c;
	
	public double apply(double arg) {
		return c*Math.pow(arg, n);
	}

	public Function derivative() {
		Function exponent = new Exponent(n-1,n*c);
		return exponent;
	}
	
	public Exponent(double n, double c){
		this.n = n;
		this.c = c;
	}
	
	public String toString(){
		return ("f(x)=x^"+n);	
	}

}
