import java.util.Arrays;
import java.util.HashSet;

public class WordLinksMin {
	int lowest;
	int count;

	public int[] minLinks(String[] words, String from, String to){
		HashSet<String> dict = new HashSet<String>(Arrays.asList(words));
		lowest = 10000000;
		count = 0;
		explore(dict, from, to, 0);
		if (lowest!=10000000){
			int[] outputter = {lowest, count};
			return outputter;
		}
		else{
			int[] nada = {0,0};
			return nada;
		}
	}
	
	public void explore(HashSet<String> dict, String from, String to, int links){
		if ((links+2)>lowest) return;
		if(isStep(from,to) && links > 0) {
			if ((links+2) == lowest){
				count++;
			}
			else{
				lowest = links+2;
				count = 1;
			}
		}
		for(String s : dict){
			if(isStep(from,s)){
				HashSet<String> copy = new HashSet<String>(dict);
				copy.remove(s);
				explore(copy, s, to, links+1);
			}
		}
	}
	
	public boolean isStep(String word1, String word2){
		int different = 0;
		char[] char1 = word1.toCharArray();
		char[] char2 = word2.toCharArray();
		for (int i=0; i<word1.length(); i++){
			if (char1[i] != char2[i]){
				different++;
			}
			if (different>1) return false;
		}
		return (different == 1);
	}
	

}
