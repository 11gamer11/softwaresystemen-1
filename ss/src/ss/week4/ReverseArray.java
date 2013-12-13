package ss.week4;

public class ReverseArray {
	public static int[] reverse(int[] array){
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
		for(int i=0;i<=array.length-1; i++){
			System.out.print(array[i]+",");
		}
	}
}
