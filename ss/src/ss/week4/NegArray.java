package ss.week4;

public class NegArray {
	int j = 0;
	public int negreturn(int[] array){
		for(int i=0; i<(array.length); i++){
			if(array[i]<0){
				j++;
			}
		}
		return j;
	}
	
	public static void main(String[] args){
		NegArray test = new NegArray();
		int[] array = {1,2,3,-1,-55,2,-63,0};
		System.out.println(test.negreturn(array));
	}
}
