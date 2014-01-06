package ss.week5;

public class IntSet {
	private boolean[] isIn;
	
	//@requires maxElement>=0;
	public IntSet (int maxElement){
		isIn = new boolean[maxElement];
	}
	
	public boolean isIn (int value){
		return (value<isIn.length && value>=0);
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
	
	public IntSet setDifference(IntSet intset){
		boolean[] set2 = intset.isIn;
		
		int longest;
		if(this.isIn.length > set2.length){
			longest = this.isIn.length;
		}else{
			longest = set2.length;
		}
		IntSet differenceSet = new IntSet(longest);
		
		//@loop_invariant i>=0;
		//@loop_invariant i<this.isIn.length;
		//@loop_invariant \forall int j; j < i; differenceSet.isIn[j] == (this.isIn[j] && !set2[j]);
		for(int i=0; i<this.isIn.length; i++){
			/*if(!this.isIn[i] && i<set2.length && !set2[i]){
				differenceSet.addElement(i);
			}*/
			
			if(this.isIn[i] && !set2[i]) {
				differenceSet.addElement(i);
			}
		}
		if(this.isIn.length<set2.length){
			//@loop_invariant k>=isIn.length;
			//@loop_invariant k<set2.length;
			//@loop_invariant \forall int j; (j >= isIn.length && j<set2.length); differenceSet.isIn(k);
			for(int k= isIn.length;k<set2.length; k++){
				differenceSet.addElement(k);
			}
		}
		
		return differenceSet;
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
		
		//@loop_invariant i>=0;
		//@loop_invariant i<this.isIn.length;
		for(int i=0; i<this.isIn.length; i++){
			if(this.isIn[i]){
				unionedSet.addElement(i);
			}
		}
		
		//@loop_invariant i>=0;
		//@loop_invariant i<set2.length;
		for(int i=0; i<set2.length; i++){
			if(set2[i]){
				unionedSet.addElement(i);
			}
		}
		return unionedSet;
	}
}
