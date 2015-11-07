import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

public class OldKing{
	
	int maxBoardSize;
	int maxTotalBoard;
	String startPos;
	String[] alpha = {"a","b","c","d","e","f","g","h"};
	HashSet<String> visitedPos = new HashSet<String>();
	ArrayList<String> realAlpha = new ArrayList<String>();
	TreeSet<String> paths = new TreeSet<String>();
	
	
	public String kingPath(int n, String initSquare){
		startPos = initSquare;
		maxBoardSize = n;
		maxTotalBoard = 11*n;
		for (int i=0; i<n; i++){
			realAlpha.add(alpha[i]);
		}
		visitedPos.add(initSquare);
		
		getPaths(initSquare, initSquare, new TreeSet<String>());
		
		String outputter = getMaxSet(paths);
		if (outputter.length() > 40){
			int len = outputter.length();
			StringBuilder temp = new StringBuilder(outputter.substring(0,20));
			temp.append("...");
			temp.append(outputter.substring(len-20));
			outputter = temp.toString();
		}
		
		return outputter;
	}
	
	public String getMaxSet(TreeSet<String> paths){
		int pathLen = 0;
		String path = "";
		for (String pathh: paths){
			if (pathh.length() >= pathLen){
				pathLen = pathh.length();
				path = pathh;
			}
		}
		return path;
	}
	
	public String getMaxList(ArrayList<String> paths){
		int pathLen = 0;
		String path = "";
		for (String pathh: paths){
			if (pathh.hashCode() >= pathLen){
				pathLen = pathh.hashCode();
				path = pathh;
			}
		}
		return path;
	}
	
	public void getPaths(String startPos, String path, TreeSet<String> visited){
		visited.add(startPos);
		ArrayList<String> adjPos = new ArrayList<String>(adjPos(startPos, visited));
		if (adjPos.isEmpty()) {
			return;
		}
		String pos = getMaxList(adjPos);
		
		StringBuilder pather = new StringBuilder(path);
		pather.append("-");
		pather.append(pos);
		paths.add(pather.toString());
		
		getPaths(pos, pather.toString(), visited);
//		for (String pos: adjPos){
//			StringBuilder pather = new StringBuilder(path);
////			TreeSet<String> tempVisited = new TreeSet<String>(visited);
//			pather.append("-");
//			pather.append(pos);
//			paths.add(pather.toString());
//			getPaths(pos, pather.toString(), visited);
//		}
	}
	
	public ArrayList<String> adjPos(String pos, TreeSet<String> visited){
//		System.out.println("adjPos)");
		ArrayList<String> outputter = new ArrayList<String>();
		int posNum = integerRep(pos);
		if (isValidPos(posNum+10, visited)) outputter.add(posRep(posNum+10));
		if (isValidPos(posNum-10, visited)) outputter.add(posRep(posNum-10));
		if (isValidPos(posNum+1, visited)) outputter.add(posRep(posNum+1));
		if (isValidPos(posNum-1, visited)) outputter.add(posRep(posNum-1));
//		System.out.println("***");
//		System.out.println("currently at: " + pos);
//		System.out.println(outputter);
//		System.out.println("***");
		return outputter;
	}
	
	public int integerRep(String pos){
		int encoded = 0;
		for (int i=0; i<maxBoardSize; i++){
			encoded+=10;
			if (pos.substring(0,1).equals(alpha[i])) break;
		}
		encoded+= Integer.parseInt(pos.substring(1));
		return encoded;
	}
	
	public String posRep(int n){
		String pos = "";
		pos += alpha[n/10 -1];
		pos += n%10;
		return pos;
	}
	
	public boolean isValidPos(String pos, TreeSet<String> visited){
//		System.out.println("isvalidpos" + pos);
		if (integerRep(pos) >  maxTotalBoard|| integerRep(pos) < 11) return false;
		if (integerRep(pos) % 10 > maxBoardSize || integerRep(pos) % 10 < 1) return false;
		if (visited.contains(pos)) return false;
		return true;
	}
	
	public boolean isValidPos(int posNum, TreeSet<String> visited){
//		System.out.println("isvalidpos" + posNum);
		if (posNum >  maxTotalBoard|| posNum < 11) return false;
		if (posNum % 10 > maxBoardSize || posNum % 10 < 1) return false;
		if (visited.contains(posRep(posNum))) return false;
//		System.out.println("valid: " + posRep(posNum));
		return true;
	}
	
	public static void main(String[] args){
		OldKing x = new OldKing();
		System.out.println(x.kingPath(8, "h1"));
	}
	
}