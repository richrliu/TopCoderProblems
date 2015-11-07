import java.util.ArrayList;
import java.util.HashMap;

public class CricketWorldCup {
	
	public String[] standings(String[] teams, String[] eliminatedBy) {
		//-- create Map: country -> # of wins
		HashMap<String, Integer> winCounts = new HashMap<String, Integer>();
		//-- create Map: country -> who beat that country
        HashMap<String, String> lostTo = new HashMap<String, String>();
        ArrayList<String> currentOrder = new ArrayList<String>();
        String[] outputter = new String[teams.length];
        String winner = "";
        for (String s:eliminatedBy){
        	Integer prevCount = winCounts.get(s) == null? 0:winCounts.get(s);
    		winCounts.put(s, ++prevCount);
        }
        for (int i=0; i<teams.length; i++){
        	if (winCounts.get(teams[i]) == null) winCounts.put(teams[i], 0);
        	if (eliminatedBy[i].equals("")) winner = teams[i];
        	lostTo.put(teams[i], eliminatedBy[i]);
        }
        //-- first, find winner and add that country to the current ordering
        //--   then, decrement # of wins, find country that lost to winner w/ that # of wins
        //--   then, decrement # of wins, go along the current ordering and find country that lost to each
        currentOrder.add(winner);
        int currentWins = winCounts.get(winner)-1;
        while (currentWins>=0){
        	currentOrder.addAll(getOrder(teams, currentWins, winCounts, lostTo, currentOrder));
        	currentWins--;
        }
        for (int i=0; i<teams.length; i++){
        	outputter[i] = currentOrder.get(i);
        }
        return outputter;
        
	}
	
	//-- find a country based on # of wins, and who they lost to
	public String findCountry(String[] teams, int currentWins, HashMap<String, Integer> winCounts, HashMap<String, String> lostTo, String beatenBy){
		String outputter = "";
		for (String s:teams){
			if (winCounts.get(s) == currentWins && lostTo.get(s).equals(beatenBy)) outputter = s;
		}
		return outputter;
	}
	
	public ArrayList<String> getOrder(String[] teams, int currentWins, HashMap<String, Integer> winCounts,
			HashMap<String, String> lostTo, ArrayList<String> currentOrder){
		ArrayList<String> outputter = new ArrayList<String>();
		for (int i=0; i<currentOrder.size(); i++){
			outputter.add(findCountry(teams, currentWins, winCounts, lostTo, currentOrder.get(i)));
		}
		return outputter;
	}
	
}
