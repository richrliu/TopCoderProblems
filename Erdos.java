import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.TreeSet;


public class Erdos {
	
	String[] myPubs;
	HashSet<String> allNames = new HashSet<String>();
	HashSet<Mathematician> allMathematicians = new HashSet<Mathematician>();
	
	class Mathematician implements Comparable<Mathematician>{
		public HashSet<Mathematician> myColleagues = new HashSet<Mathematician>();
		public String myName;
		public int myErdosNum = -1;
		
		public Mathematician (){
		}
		
		public Mathematician (String nombre){
			myName = nombre;
		}
		
		public void fillColleagues(){
			for (String pub: myPubs){
				if (Arrays.asList(pub.split(" ")).contains(myName)){
					String[] temp = pub.split(" ");
					for (String namee: temp){
						if (!namee.equals(myName)){
							for (Mathematician m: allMathematicians){
								if (m.myName.equals(namee)) myColleagues.add(m);
							}
						}
					}
				}
			}
		}
		
		public void setErdosNum(){
			int minNum = 9999;
//			System.out.println("I am " + myName);
			for (Mathematician m: myColleagues){
				if (m.myErdosNum >= 0 && minNum > m.myErdosNum) minNum = m.myErdosNum;
//				System.out.println(m.myName + m.myErdosNum);
			}
			if (minNum == 9999) return;
			if (myName.equals("ERDOS")){
				myErdosNum = 0;
				return;
			}
			myErdosNum = minNum+1;
//			System.out.println("My new num:" + myErdosNum);
//			System.out.println();
		}
		
		public boolean equals(Mathematician n){
			return n.myName == this.myName;
		}
		
		public int hashCode(){
			return this.myName.hashCode();
		}
		
		public int compareTo(Mathematician x){
			return this.myName.compareTo(x.myName);
		}
	}

	public String[] number(String[] pubs){
		this.myPubs = pubs;
		fillNames();
		fillMathematicianNames();
		for (Mathematician m: allMathematicians){
			m.fillColleagues();
		}
		Mathematician Erdos = new Mathematician();
		for (Mathematician m: allMathematicians){
			if (m.myName.equals("ERDOS")){
				Erdos = m;
				m.myErdosNum = 0;
			}
		}
		propogateNums(Erdos);
		for (Mathematician m: allMathematicians){
			m.setErdosNum();
		}
		for (Mathematician m: allMathematicians){
			m.setErdosNum();
		}
		for (Mathematician m: allMathematicians){
			m.setErdosNum();
		}
		ArrayList<Mathematician> tempOutputter = new ArrayList<Mathematician>(allMathematicians);
		Collections.sort(tempOutputter);
		String[] outputter = new String[tempOutputter.size()];
		for (int i=0; i<tempOutputter.size(); i++){
			outputter[i] = tempOutputter.get(i).myName;
			if (tempOutputter.get(i).myErdosNum >=0) 
				outputter[i] = outputter[i] + " " + tempOutputter.get(i).myErdosNum;
		}
		return outputter;
	}
	
	public boolean requiresCheck(ArrayList<Mathematician> list){
		for (Mathematician m: list){
			if (m.myErdosNum < 0) return false;
		}
		return true;
	}
	
	public void propogateNums(Mathematician x){
		if (x.myErdosNum < 0) x.setErdosNum();
		for (Mathematician m: x.myColleagues){
			if (m.myErdosNum < 0) propogateNums(m);
		}
	}
	
	public void fillNames(){
		for (String pub: myPubs){
			String[] temp = pub.split(" ");
			for (String name: temp){
				allNames.add(name);
			}
		}
	}
	
	public void fillMathematicianNames(){
		for (String name: allNames){
			allMathematicians.add(new Mathematician(name));
		}
	}
	
	public static void main(String[] args){
		String[] pubs = {"ERDOS B", "A B C", "B A E", "D F" };
		Erdos x = new Erdos();
		System.out.println(Arrays.asList(x.number(pubs)));
	}

}
