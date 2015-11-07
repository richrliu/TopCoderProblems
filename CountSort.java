import java.util.TreeMap;

public class CountSort {
	public String[] countSorter(String[] wordList) {
		//-- fill out the map
		TreeMap<String, Integer> frequencies = new TreeMap<String, Integer>();
		for (String s : wordList){
			int prevCount = frequencies.get(s)==null? 0:frequencies.get(s);
			frequencies.put(s, ++prevCount);
		}
		//-- get correct order
		String[] outputter = new String [frequencies.keySet().size()];
		int index = 0;
		while (index<outputter.length){
			String highest = getMaxKey(frequencies);
			outputter[index] = getMaxKey(frequencies);
			frequencies.remove(highest);
			index++;
		}
		return outputter;
	}
	
	public static String getMaxKey(TreeMap<String, Integer> inputter){
		int counter = -1;
		String key = "";
		for (String s:inputter.keySet()){
			if (inputter.get(s)>counter){
				counter = inputter.get(s);
				key = s;
			}
		}
		return key;
	}
}