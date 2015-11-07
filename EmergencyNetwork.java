import java.util.ArrayList;
import java.util.HashMap;

public class EmergencyNetwork {

	public int lagTime(int[] bosses){
		HashMap<Integer, ArrayList<Integer>> employees = new HashMap<Integer, ArrayList<Integer>>(employees(bosses));
		return minTimeGivenEmployee(0, employees);
	}
	
	//-- Map each worker -> ArrayList of his employees
	public HashMap<Integer, ArrayList<Integer>> employees(int[] bosses){
		HashMap<Integer, ArrayList<Integer>> outputter = new HashMap<Integer, ArrayList<Integer>>();
		for (int i=1; i<bosses.length; i++){
			if (outputter.get(bosses[i]) == null){
				ArrayList<Integer> temp = new ArrayList<Integer>();
				temp.add(i);
				outputter.put(bosses[i], temp);
			}
			else{
				ArrayList<Integer> temp = new ArrayList<Integer>(outputter.get(bosses[i]));
				temp.add(i);
				outputter.put(bosses[i], temp);
			}
		}
		return outputter;
	}
	
	//-- find the maximum integer in ArrayList
	public Integer listMax(ArrayList<Integer> input){
		int max = -1;
		for (Integer i:input){
			if (i>max) max = i;
		}
		return max;
	}
	
	//-- remove the maximum integer from ArrayList
	public ArrayList<Integer> removeMax(ArrayList<Integer> input){
		int max = -1;
		int maxIndex = 0;
		ArrayList<Integer> outputter = new ArrayList<Integer>(input);
		for (int i=0; i<outputter.size(); i++){
			if (outputter.get(i)>max){
				max = outputter.get(i);
				maxIndex = i;
			}
		}
		outputter.remove(maxIndex);
		return outputter;
	}
	
	//-- increment every integer in ArrayList by 1
	public ArrayList<Integer> incrementAllByOne(ArrayList<Integer> input){
		ArrayList<Integer> outputter = new ArrayList<Integer>();
		for (int i=0; i<input.size(); i++){
			outputter.add(input.get(i)+1);
		}
		return outputter;
	}
	
	//-- given an ArrayList of "propagation times" of subordinates, what is the boss's propagation time?
	public Integer minTimeGivenTimes(ArrayList<Integer> input){
		ArrayList<Integer> temp = new ArrayList<Integer>(input);
		int time = 0;
		while (!temp.isEmpty()){
			temp = incrementAllByOne(temp);
			if (listMax(temp)>time) time = listMax(temp);
			temp = removeMax(temp);
		}
		return time;
	}
	
	//-- given an ArrayList of employees, get propagation times of each employee, and then find the propagation time using previous method
	public Integer minTimeGivenEmployee(Integer emp, HashMap<Integer, ArrayList<Integer>> employees){
		if (employees.get(emp)==null) return 0;
		else{
			ArrayList<Integer> subordinates = new ArrayList<Integer>(employees.get(emp));
			ArrayList<Integer> subtimes = new ArrayList<Integer>();
			for (Integer i:subordinates){
				subtimes.add(minTimeGivenEmployee(i, employees));
			}
			return minTimeGivenTimes(subtimes);
		}
	}

}
