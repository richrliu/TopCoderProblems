import java.util.ArrayList;
import java.util.Arrays;

public class WordLinks {

	public String isLinked(String[] inner, String from, String to){
		int links = 0;
		if (isStep(from, to) && links > 0) return "ladder";
		for (String s : inner){
			if (isStep(from, s)){
				ArrayList<String> copy = new ArrayList<String>(Arrays.asList(inner));
				copy.remove(s);
				String[] temp = new String[copy.size()];
				for (int i = 0; i<temp.length; i++){
					temp[i] = copy.get(i);
				}
				if (isLinked(temp, s, to, links+1).equals("ladder")){
					return "ladder";
				}
			}
		}
		return "none";
	}
	
	//-- overload with links parameter -- required for recursion
	public String isLinked(String inner[], String from, String to, int links){
		if(isStep(from,to) && links > 0) {
			return "ladder";
		}
		for(String s : inner){
			if(isStep(from,s)){
				ArrayList<String> copy = new ArrayList<String>(Arrays.asList(inner));
				copy.remove(s);
				String[] temp = new String[copy.size()];
				for (int i = 0; i<temp.length; i++){
					temp[i] = copy.get(i);
				}
				if(isLinked(temp, s, to, links+1).equals("ladder")) {
					return "ladder";
				}
			}
		}
		return "none";
	}
	
	//-- check if words differ by exactly one letter
	public boolean isStep(String word1, String word2){
		int different = 0;
		for (int i=0; i<word1.length(); i++){
			if (word1.toCharArray()[i] != word2.toCharArray()[i]){
				different++;
			}
		}
		if (different == 1) return true;
		return false;
	}
	
}
