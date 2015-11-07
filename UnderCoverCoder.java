import java.util.HashMap;

public class UnderCoverCoder {
	
	//-- intakes a string or String[], returns map of character to # of times it appears
	public HashMap<Character, Integer> mapper(String... inputter){
		HashMap<Character, Integer> numOfLetters = new HashMap<Character, Integer>();
		for (String s : inputter){
			s = s.toLowerCase();
			char[] letterArray = s.toCharArray();
			
			for (char c : letterArray){
				int previousCount = numOfLetters.get(c) == null? 0:numOfLetters.get(c);
				numOfLetters.put(c, ++previousCount);
			}
			
		}
		return numOfLetters;
	}
	
	public int totalNotes(String[] clips, String[] notes) {
		//-- create a "bank" of all available letters
		HashMap<Character, Integer> bank = mapper(clips);
		int count = 0;
		
		for (String s : notes){
			boolean missingLetter = false;
			//-- create new map of characters->integers to compare against bank
			HashMap<Character, Integer> temp = mapper(s);
			//-- for every character in the note being compared
			for (char c : temp.keySet()){
				//-- spaces dont count
				if (c == ' '){
					continue;
				}
				//-- how many times does char c appear in the bank?
				int bankCount = bank.get(c) ==null? 0 : bank.get(c);
				//-- if this is less than required for message, break, otherwise: continue
				if (temp.get(c)<=bankCount){
					continue;
				}
				else{
					missingLetter = true;
					break;
				}
			}
			//-- keep the counter
			if (!missingLetter){
				count++;
			}
		}
		return count;
		
	}
}