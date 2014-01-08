package ss.week5;

public class ReverseArray {
	public static int[] reverse(int[] array){
		//@loop_invariant i>=0;
		//@loop_invariant i<=array.length/2;
		//@loop_invariant (\forall int j; (j>=0 && j<=array.length/2); (array[j] == \old(array)[array.length-j-1]));
		for(int i=0; (i<=array.length/2); i++){
			int a = array[i];
			int b = array[array.length-i-1];
			array[i]=b;
			array[array.length-i-1]=a;
		}
		return array;
	}
	
	public static void main(String[] args){
		int[] array = {1,2,3,4,5,6,-7};
		reverse(array);
		//@loop_invariant i>=0;
		//@loop_invariant i<=array.length-1;
		for(int i=0;i<=array.length-1; i++){
			System.out.print(array[i]+",");
		}
	}
}
