package ss.week5;

import java.util.*;

public class MapUtils {
	public static boolean isOneOnOne(Map<Integer, Integer> f){
		
		int numberOfKeys = f.size();
		int k = 0;
		int min = Collections.min(f.values());
		int max = Collections.max(f.values());
		
		List<Integer> list = new ArrayList<Integer>(f.values());
		Collections.sort(list);
		
		Iterator iterator = list.iterator();
		Iterator iterator2 = list.iterator();
		//aannemen dat collection gesorteerd is want ik weet niet hoe het anders moet
		for(int j=min; j<=max; j=(int) iterator.next()){
			for(int i=(int) iterator.next(); i<=max; i=(int) iterator2.next()){
				if(f.containsValue(j)){
					return false;
				}
			}
		}
		return true;
	}
}
