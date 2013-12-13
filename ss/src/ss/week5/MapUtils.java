package ss.week5;

import java.util.*;

public class MapUtils {

	public static boolean isOneOnOne(Map<Integer,Integer> f){
		Collection<Integer> v = f.values();
		Iterator<Integer> i = v.iterator();
		
		Map<Integer,Boolean> bList = new HashMap<Integer,Boolean>();
		boolean duplicates = false;
		
		while(i.hasNext()){
			int value = i.next();
			if(bList.containsKey(value) && bList.get(value)){
				duplicates = true;
				break;
			}else{
				bList.put(value, true);
			}
		}
		return !duplicates;
	}
	
	//@ensures (\forall int key; a.containsKey(key); b.containsKey(a.get(key))) == \result;
	//@pure;
	public static boolean compatible(Map<Integer,Integer> a, Map<Integer,Integer> b){
		
		Set<Integer> keys = a.keySet();
		Iterator<Integer> k = keys.iterator();
		
		boolean compatible = true;
		
		while(k.hasNext()){
			int key = k.next();
			int value = a.get(key);
			if(!b.containsKey(value)){
				compatible = false;
			}
		}
		return compatible;
	}
	
	//@requires compatible(a,b);
	//@ensures \forall int key; a.containsKey(key); \result.containsKey(key) && \result.get(key) == b.get(a.get(key));
	public static Map<Integer, Integer> compose(Map<Integer,Integer> a, Map<Integer,Integer> b){
		
		Set<Integer> keys = a.keySet();
		Iterator<Integer> i = keys.iterator();
		Map<Integer,Integer> h = new HashMap<Integer,Integer>();
		
		while(i.hasNext()){
			int key = i.next();
			int value = b.get(a.get(key));
			h.put(key, value);
		}
		
		return h;
	}
	
	public static void debug(String string){
		System.out.println(string);
	}
	
	public static void main(String[] args){
		
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		
		map.put(1, 83);
		map.put(2, 43);
		map.put(5, 8008135);
		map.put(10, 13173);
		map.put(602384, 7007);
		map.put(9766, 2);
		map.put(42, 20);
		map.put(6356, 9001);
		map.put(7983, 1337);
		
		if(MapUtils.isOneOnOne(map)){
			System.out.println("LOL ONEONONE1!!1!");
		}else{
			System.out.println("Fail.");
		}
		
	}
	
}
