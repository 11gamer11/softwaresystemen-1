package ss.week4;

public class Array {
	void negreturn(int[] array){
		for(int i=0; i<(array.length-1); i++){
			if(array[i]<0){
				System.out.println(array[i]);
			}
		}
	}
	
	public static void main(int[] test, String[] args){
		Array klasse = new Array(); 
		klasse.negreturn(test);
	}
}
