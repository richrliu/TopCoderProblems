import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

public class OlympicSort {
	
	public  String[] standings(String[] results){
		//-- create hashmaps to store medal counts
		HashMap<String, Integer> goldCounts = new HashMap<String, Integer>(getGoldCounts(results));
		HashMap<String, Integer> silverCounts = new HashMap<String, Integer>(getSilverCounts(results));
		HashMap<String, Integer> bronzeCounts = new HashMap<String, Integer>(getBronzeCounts(results));
		//-- alphabetical list of countries
		TreeSet<String> countries = new TreeSet<String>();
		countries.addAll(goldCounts.keySet());
		countries.addAll(silverCounts.keySet());
		countries.addAll(bronzeCounts.keySet());
		HashMap<String, Integer> points = new HashMap<String, Integer>(); //-- countries - points
		ArrayList<Integer> pointsList = new ArrayList<Integer>(); //-- points of alphabetized countries
		for (String s:countries){ //-- get medal counts, assign points
			Integer pointsGold = goldCounts.get(s) == null? 0:goldCounts.get(s);
			Integer pointsSilver = silverCounts.get(s) == null? 0:silverCounts.get(s);
			Integer pointsBronze = bronzeCounts.get(s) == null? 0:bronzeCounts.get(s);
			Integer addedPoints = 1000000*pointsGold + 1000*pointsSilver + 1*pointsBronze;
			points.put(s, addedPoints);
			pointsList.add(addedPoints);
		}
		//-- create array of countries from high to low points values (then ab order)
		String[] countriesArray = countries.toArray(new String[countries.size()]);
		String[] countriesArrayOrdered = new String[countries.size()];
		String[] outputter = new String[countries.size()];
		Integer[] pointsArray = pointsList.toArray(new Integer[pointsList.size()]);
		int highest = findMaxPoint(pointsArray);
		int counter = 0;
		while (highest != -1){
			for (int i=0; i<pointsArray.length; i++){
				if (pointsArray[i] == highest){
					countriesArrayOrdered[counter] = countriesArray[i];
					pointsArray[i] = -1;
					counter++; 
					if (findMaxPoint(pointsArray) != highest){
						highest = findMaxPoint(pointsArray);
					}
					break;
				}
				
			}
		}
		//-- create output array
		for (int i=0; i<outputter.length; i++){
			if (outputter[i] == null){
				int goldMedals = goldCounts.get(countriesArrayOrdered[i]) == null? 0:goldCounts.get(countriesArrayOrdered[i]);
				int silverMedals = silverCounts.get(countriesArrayOrdered[i])==null? 0:silverCounts.get(countriesArrayOrdered[i]);
				int bronzeMedals = bronzeCounts.get(countriesArrayOrdered[i])==null? 0:bronzeCounts.get(countriesArrayOrdered[i]);
				outputter[i]  = countriesArrayOrdered[i] + " " + goldMedals 
						+ " " + silverMedals
						+ " " + bronzeMedals;
			}
		}
		return outputter;
	}
	
	//-- find max integer in array of ints
	public  int findMaxPoint(Integer[] list){
		int original = list[0];
		for (Integer i:list){
			if (i>original){
				original = i;
			}
		}
		return original;
	}
	
	//-- following functions create maps for medal counts for gold, sil, bronze
	public  HashMap<String, Integer> getGoldCounts(String[] s){
		HashMap<String, Integer> outputter= new HashMap<String, Integer>();
		for (String p : s){
			String word = p.substring(0,3);
			Integer previousCount = outputter.get(word) == null? 0:outputter.get(word);
			outputter.put(word, ++previousCount);
		}
		return outputter;
	}
	
	public  HashMap<String, Integer> getSilverCounts(String[] s){
		HashMap<String, Integer> outputter= new HashMap<String, Integer>();
		for (String p : s){
			String word = p.substring(4, 7);
			Integer previousCount = outputter.get(word) == null? 0:outputter.get(word);
			outputter.put(word, ++previousCount);
		}
		return outputter;
	}
	
	public  HashMap<String, Integer> getBronzeCounts(String[] s){
		HashMap<String, Integer> outputter= new HashMap<String, Integer>();
		for (String p : s){
			String word = p.substring(8);
			Integer previousCount = outputter.get(word) == null? 0:outputter.get(word);
			outputter.put(word, ++previousCount);
		}
		return outputter;
	}

}
