import java.util.*;


public class BigWord {

	/**
	 * @param args
	 */
	public static String replaceUppercase (String input){
		//replaces a String's uppercase letters with lowercase
		
		char[] inputCharArray = input.toCharArray();
		String lowercaseLets = "abcdefghijklmnopqrstuvwxyz";
		String uppercaseLets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		char[] lowercaseChars = lowercaseLets.toCharArray();
		char[] uppercaseChars = uppercaseLets.toCharArray();
		String outputter = "";
		for (int i=0; i< input.length(); i++){
			for (int j=0; j<25; j++){
				if (inputCharArray[i]==uppercaseChars[j]){
					inputCharArray[i] = lowercaseChars[j];
				}
			}
			outputter += inputCharArray[i];
		}
		
		return outputter;
	}

	public static int getIndex(Set<String> set1, String value) {
		// gets the "index" of a String needle in a set haystack
		   int result = 0;
		   for (Object entry:set1) {
		     if (entry.equals(value)) {
		    	 return result;
		     }
		     result++;
		   }
		   return -1;
		 }
	
	public static Set<String> union(Set<String> a, Set<String> b) {
		    // TODO: complete union
			  a.addAll(b);
			  return a;
	}
	
	
	
	public static Set<String> saToSet(String[] s){
		//adds all elements in String array to a set
		
		  Set<String> setter = new TreeSet<String>();
		  for (int i=0; i<s.length;i++){
			  setter.add(s[i]);
		  }
		  return setter;
	  }
	
	public static Set<String> sToSet(String s) {
		  // TODO: complete sToSet
		  String[] stringer = s.split(" ");
		  Set<String> setter = new TreeSet<String>();
		  for (int i=0; i<stringer.length; i++){
			  setter.add(stringer[i]);
		  }
		  return setter;
	  }
	
	public int getMaxIndex(int[] inputter){
		//for an int array, returns index of the highest int
		int highest = 0;
		for (int i=1; i<inputter.length; i++){
			if (inputter[i] > inputter[highest]){
				highest = i;
			}
		}
		return highest;
	}
	
	public String getStringAt(Set<String> inputSet, int index){
		// gets the String at a set's "index"
		String[] inputArray = inputSet.toArray(new String[inputSet.size()]);
		return inputArray[index];
	}
	
	public String most (String[] sentences) {
		// TODO Auto-generated method stub
	
		String wordstring = "";
		for (int i=0; i<sentences.length; i++){
			wordstring += sentences[i];
			if (! (i==(sentences.length-1))){
				wordstring += " ";
			}
		}
		String[] wordsplit = wordstring.split(" "); 
		//we now have list of words in a single string, and in a string array separated by word

		Set<String> sentencesSet = sToSet(wordstring);
		//we now have a set of words (union)
		
		int count[] = new int[sentencesSet.size()];
		
		// count how many times each word appears (index of count corresponds with "index" of sentencesSet
		for (int i=0; i<wordsplit.length; i++){
			wordsplit[i] = replaceUppercase(wordsplit[i]);
			if (sentencesSet.contains(wordsplit[i])){
				count[getIndex(sentencesSet, wordsplit[i])]++;
			}
		}
		
		//get the index with the highest count
		int highest = getMaxIndex(count);
		
		return getStringAt(sentencesSet, highest);
		
		
	}
	

}
