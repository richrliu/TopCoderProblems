import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeMap;

public class PathSort {
	public String[] sortPath(String[] dire) {
		//-- see which has most slashes(deepest directory)
		HashMap<String, Integer> freqCount = new HashMap<String, Integer>();
		TreeMap<String, Integer> slashesCount = new TreeMap<String, Integer>();
		for (String s:dire){
			slashesCount.put(s, countSlashes(s));
			int prevCount = freqCount.get(s)==null? 0:freqCount.get(s);
			freqCount.put(s, ++prevCount);
		}
		TreeMap<Integer, ArrayList<String>> directoriesByFreq = new TreeMap<Integer, ArrayList<String>>();
		for (String s : slashesCount.keySet()){
			Integer counter = slashesCount.get(s);
			ArrayList<String> prevVal = directoriesByFreq.get(counter)==null? new ArrayList<String>():directoriesByFreq.get(counter);
			prevVal.add(s); 
			Collections.sort(prevVal, String.CASE_INSENSITIVE_ORDER);
			directoriesByFreq.put(counter, prevVal);
		}
		ArrayList<String> outputter = new ArrayList<String>();
		for (Integer i : directoriesByFreq.keySet()){
			for (String s:directoriesByFreq.get(i)){
				int remaining = freqCount.get(s);
				while (remaining>0){
					outputter.add(s);
					remaining--;
				}
			}
		}
		String[] outputArray = new String[outputter.size()];
		int index=0;
		for (String s:outputter){
			outputArray[index++] = s;
		}
		return outputArray;
	}
	
	public static Integer countSlashes(String s){
		Integer count = 0;
		for (int i=0; i<s.length(); i++){
			if (s.charAt(i) == '/'){
				count++;
			}
		}
		return count;
	}
}