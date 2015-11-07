import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SubstringSort {
	
	public String[] sortSubstrings(String[] wordList){
		int[] points = new int[wordList.length];
		String[] outputter = new String[wordList.length];
		ArrayList<String> sortThis = new ArrayList<String>(Arrays.asList(wordList));
		
		for(int i=0; i<wordList.length; i++){
			ArrayList<String> temp = new ArrayList<String>(sortThis);
			temp.remove(wordList[i]);
			points[i] = compareToElse(wordList[i], temp);
		}
		
		int maxPoints = findMaxPoint(points);
		int counter = 0;
		while (maxPoints >= 0){
			String temp = "";
			for (int i=0; i<points.length; i++){
				if (points[i] == maxPoints){
					temp = wordList[i];
					outputter[counter] = temp;
					points[i] = -1;
					counter++; 
					if (findMaxPoint(points) != maxPoints){
						maxPoints = findMaxPoint(points);
					}
					break;
				}
			}
		}
		return outputter;
	}
	
	public static int findMaxPoint(int[] list){
		int original = list[0];
		for (Integer i:list){
			if (i>original){
				original = i;
			}
		}
		return original;
	}
	
	public static int compareToElse(String word, ArrayList<String> otherWords){
		int count = 0;
		for (String s:otherWords){
			if (compareWords(word, s)<0){
				count++;
			}
		}
		return count;
	}
	
	public static int compareWords (String word1, String word2){
		ArrayList<String> list1 = new ArrayList<String>();
		ArrayList<String> list2 = new ArrayList<String>();
		list1.addAll(vowelSplit(word1));
		list2.addAll(vowelSplit(word2));
		if (compareLists(list1, list2)==1 || compareLists(list1, list2)==-1) 
			return compareLists(list1, list2);
		else{
			if (word1.length() == word2.length()){
				return compareLists(vowelSplitUnsorted(word1), vowelSplitUnsorted(word2));
			}
			else{
				if (word1.length() > word2.length()) return 1;
				else if (word1.length() < word2.length()) return -1;
				else return 0;
			}
		}
	}
	
	public static int compareLists (ArrayList<String> list1, ArrayList<String> list2){
		if (list1.size() == 0 && list2.size() != 0){
			return -1;
		}
		else if(list2.size() == 0 && list1.size() != 0){
			return 1;
		}
		else if(list1.size() == 0 && list2.size() == 0){
			return 0;
		}
		String syl1 = list1.get(0);
		String syl2 = list2.get(0);
		if (syl1.compareTo(syl2)>0) return 1;
		else if (syl1.compareTo(syl2)<0) return -1;
		else{
			list1.remove(syl1);
			list2.remove(syl2);
			return compareLists(list1, list2);
		}
	}
	
	public static ArrayList<String> vowelSplit(String word){
		ArrayList<Character> vowels = new ArrayList<Character>();
		vowels.add('a'); vowels.add('e'); vowels.add('i'); vowels.add('o'); vowels.add('u');
	
		ArrayList<String> outputter = new ArrayList<String>();
		int counter = 0;
		boolean hasVowel = false;
		
		for (int i=0; i<word.length()-1; i++){
			if (vowels.contains(word.charAt(i))){
				if (!vowels.contains(word.charAt(i+1))){
					outputter.add(word.substring(0, i+1));
					hasVowel = true;
					counter = i;
					break;
				}
			}
		}
		if (hasVowel){
			word = word.substring(counter+1);
			outputter.addAll(vowelSplit(word));
		}
		else{
			word = word.substring(counter);
			outputter.add(word);
		}
		Collections.sort(outputter);
		return outputter;
	}
	
	public static ArrayList<String> vowelSplitUnsorted(String word){
		ArrayList<Character> vowels = new ArrayList<Character>();
		vowels.add('a'); vowels.add('e'); vowels.add('i'); vowels.add('o'); vowels.add('u');
	
		ArrayList<String> outputter = new ArrayList<String>();
		int counter = 0;
		boolean hasVowel = false;
		
		for (int i=0; i<word.length()-1; i++){
			if (vowels.contains(word.charAt(i))){
				if (!vowels.contains(word.charAt(i+1))){
					outputter.add(word.substring(0, i+1));
					hasVowel = true;
					counter = i;
					break;
				}
			}
		}
		if (hasVowel){
			word = word.substring(counter+1);
			outputter.addAll(vowelSplit(word));
		}
		else{
			word = word.substring(counter);
			outputter.add(word);
		}
		return outputter;
	}
}
