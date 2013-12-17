package ss.week4;

public class IntSet {
	private boolean[] isIn;
	
	//@requires maxElement>=0;
	public IntSet (int maxElement){
		isIn = new boolean[maxElement];
	}
	
	public void addElement(int value){
		isIn[value] = true;
	}
	
	public void deleteElement(int value){
		isIn[value] = false;
	}
	
	public boolean checkElement(int value){
		return isIn[value];
	}
	
	public IntSet checkUnion(IntSet intset){
		
		boolean[] set2 = intset.isIn;
		
		int longest;
		if(this.isIn.length > set2.length){
			longest = this.isIn.length;
		}else{
			longest = set2.length;
		}
		IntSet unionedSet = new IntSet(longest);
		
		for(int i=0; i<this.isIn.length; i++){
			if(this.isIn[i]){
				unionedSet.addElement(i);
			}
		}
		
		for(int i=0; i<set2.length; i++){
			if(set2[i]){
				unionedSet.addElement(i);
			}
		}
		return unionedSet;
	}
}
