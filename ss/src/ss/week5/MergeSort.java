package ss.week5;

import java.util.LinkedList;
import java.util.List;

public class MergeSort {
	public static LinkedList<Integer> mergeSort(LinkedList<Integer> a){
		int h=a.size();
		int m=0;
		int l=0;
		LinkedList<Integer> list = new LinkedList<Integer>();
		if(h<=1){
			return a;
		}
		else {
			m = (h-l)/2;
			
			List<Integer> x = a.subList(l, m);
			List<Integer> y = a.subList(m,h);
			
			LinkedList<Integer> startlist = new LinkedList<Integer>(x); 
			LinkedList<Integer> endlist = new LinkedList<Integer>(y);
			LinkedList<Integer> f = mergeSort(startlist);
			LinkedList<Integer> s = mergeSort(endlist);
			LinkedList<Integer> r = new LinkedList<Integer>();
			int fi = 0;
			int si = 0;
			
			while(fi < f.size() && si < s.size()){
				if(f.get(fi) < s.get(si)){
					r.add(r.size(), f.get(fi));
					fi++;
				}
				else{
					r.add(r.size(),s.get(si));
					si++;
				}
			}
			if(fi<f.size()){
				r.addAll(r.size(), f.subList(fi,f.size())); 
			}
			else if(si<s.size()){
				r.addAll(r.size(),s.subList(si, s.size()));
			}
			return r;
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
