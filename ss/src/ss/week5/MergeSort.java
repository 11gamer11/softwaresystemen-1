package ss.week5;

import java.util.LinkedList;
import java.util.List;

public class MergeSort {
	public static LinkedList<Integer> mergeSort(LinkedList<Integer> hetHeeeeeeleLangeLijstjeDatGegevenWordtOmdatTwanHetPerSeWil){
		int highBorder=hetHeeeeeeleLangeLijstjeDatGegevenWordtOmdatTwanHetPerSeWil.size();
		int middleBorder=0;
		int lowBorder=0;
		if(highBorder<=1){
			return hetHeeeeeeleLangeLijstjeDatGegevenWordtOmdatTwanHetPerSeWil;
		}
		else {
			middleBorder = (highBorder-lowBorder)/2;
			
			List<Integer> x = hetHeeeeeeleLangeLijstjeDatGegevenWordtOmdatTwanHetPerSeWil.subList(lowBorder, middleBorder);
			List<Integer> y = hetHeeeeeeleLangeLijstjeDatGegevenWordtOmdatTwanHetPerSeWil.subList(middleBorder,highBorder);
			
			LinkedList<Integer> startlist = new LinkedList<Integer>(x); 
			LinkedList<Integer> endlist = new LinkedList<Integer>(y);
			LinkedList<Integer> firstLijst = mergeSort(startlist);
			LinkedList<Integer> secondLijst = mergeSort(endlist);
			LinkedList<Integer> resultLijst = new LinkedList<Integer>();
			int firstIndex = 0;
			int secondIndex = 0;
			
			while(firstIndex < firstLijst.size() && secondIndex < secondLijst.size()){
				if(firstLijst.get(firstIndex) < secondLijst.get(secondIndex)){
					resultLijst.add(resultLijst.size(), firstLijst.get(firstIndex));
					firstIndex++;
				}
				else{
					resultLijst.add(resultLijst.size(),secondLijst.get(secondIndex));
					secondIndex++;
				}
			}
			if(firstIndex<firstLijst.size()){
				resultLijst.addAll(resultLijst.size(), firstLijst.subList(firstIndex,firstLijst.size())); 
			}
			else if(secondIndex<secondLijst.size()){
				resultLijst.addAll(resultLijst.size(),secondLijst.subList(secondIndex, secondLijst.size()));
			}
			return resultLijst;
		}
	}
	
	public static void main (String[] args){
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.add(2);
		list.add(4);
		list.add(6);
		list.add(-5);
		list.add(54);
		list.add(30580);
		list.add(-2849);
		list.add(1);
		list.add(0);
		System.out.println(mergeSort(list));
	}
}
