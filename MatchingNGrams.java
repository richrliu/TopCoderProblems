import java.util.ArrayList;
import java.util.List;

public class MatchingNGrams {
	
	public int totalMatches(String[] ngrams) {
		int count = 0;
		//-- for word i
		for(int i =0; i<ngrams.length; i++){
			String wordOne = ngrams[i];
			//-- for word pair i, j
			for(int j=i+1; j<ngrams.length; j++){
				String wordTwo = ngrams[j];
				//-- compare the two, increase count if needed.
				if(validMapping(wordOne,wordTwo)){
					count++;
				}
			}
		}
		return count;
	}
	
	//-- do the two words fit the given criteria?
	public boolean validMapping(String wordOne, String wordTwo){
		int i = 0;
		for(char c: wordOne.toCharArray()){
			if(!compareList(indexes(wordOne,c),indexes(wordTwo,wordTwo.charAt(i)))){
				return false;
			}
			i++;
		}
		return true;
	}
	
	//-- check for criteria
	public boolean compareList(List<Integer> one, List<Integer> two){
		if(one.size() != two.size()){
			return false;
		}
		for(int i = 0; i<one.size(); i++){
			//-- if mapping of index of wordOne different than of wordTwo, not a match
			if(one.get(i)!= two.get(i)){
				return false;
			}
		}
		return true;
	}
	
	//-- get all the indexes of a character c in a word
	public List<Integer> indexes(String word, char c){
		List<Integer> indexes = new ArrayList<Integer>();
		int index = word.indexOf(c);
		while(index>=0){
			indexes.add(index);
			index = word.indexOf(c, index+1);
		}
		return indexes;
	
	}
	
}