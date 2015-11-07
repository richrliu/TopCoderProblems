import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeMap;

public class Huffman {
	
	public TreeMap<Integer, Character> myBank;
	public HashMap<String, Character> translator;

	public String translate(String encoded, String[] dictionary){
		myBank = new TreeMap<Integer, Character>();
		translator = new HashMap<String, Character>();
		Integer index = 0;
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for (int i=0; i<dictionary.length; i++){
			translator.put(dictionary[i], alphabet.charAt(i));
		}
		while (!encoded.equals("")){
			String code = whichCode(encoded, dictionary);
			if (code.equals("")) break;
			myBank.put(index, translator.get(code));
			encoded = updateString(encoded, code);
			index++;
		}
		String outputter = "";
		for (Integer i:myBank.keySet()){
			outputter += myBank.get(i);
		}
		return outputter;
	}
	
	public String whichCode(String word, String[] checkMe){
		ArrayList<String> lister = new ArrayList<String>(Arrays.asList(checkMe));
		int index = 1;
		while (index<=word.length()){
			if (lister.contains(word.substring(0, index))) return word.substring(0, index);
			index++;
		}
		return "";
	}
	
	public String updateString(String word, String removeMe){
		int index = word.indexOf(removeMe);
		int size = removeMe.length();
		if (index == -1) return word;
		String outputter = "";
		outputter += word.substring(0, index);
		if (index+size <= word.length()) outputter += word.substring(index + size);
		return outputter;
	}
	
//	public static void main(String[] args){
//		Huffman test = new Huffman();
//		String encoded = "00011011001011001101111010110010110010110";
//		String[] dictionary = {"110","011","10","0011","00011","111","00010","0010","010","0000"};
//		System.out.println(test.translate(encoded, dictionary));
//	}

}
