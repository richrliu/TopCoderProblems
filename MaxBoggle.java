import java.util.HashMap;
import java.util.HashSet;

public class MaxBoggle{
	
	public long maxPoints(String[] matrix, String[] words){
		long points = 0;
		HashMap<String, long[][]> startingLetters = new HashMap<String, long[][]>(letterLocations(matrix, words));
		HashMap<String, long[][]> segments = new HashMap<String, long[][]>();
		HashSet<String> wordSet = new HashSet<String>();
		for (String s:words){
			wordSet.add(s);
			segments.putAll(segmentLocations(s, startingLetters));
		}
		for (String word:wordSet){
			points+=score(word)*getCountOfGrid(segments.get(word));
		}
		return points % (long)1E13;
	}
	
	//-- given a matrix and a letter, create a grid of locations of letters
	public long[][] createGrid(String[] matrix, String letter){
		long[][] outputter = new long[4][4];
		for (int row=0; row<4; row++){
			for (int col=0; col<4; col++){
				if (matrix[row].substring(col,col+1).equals(letter))
					outputter[row][col]++;
			}
		}
		return outputter;
	}
	
	//-- create a set of all necessary letters
	public HashSet<String> getAllLettersFromWords(String[] words){
		HashSet<String> outputter = new HashSet<String>();
		for (String word: words){
			for (int i=0; i<word.length(); i++){
				outputter.add(word.substring(i, i+1));
			}
		}
		return outputter;
	}
	
	//-- get grids for starting locations for all letters, Map letter -> grid
	public HashMap<String, long[][]> letterLocations(String[] matrix, String[] words){
		HashSet<String> letters = new HashSet<String>(getAllLettersFromWords(words));
		HashMap<String, long[][]> outputter = new HashMap<String, long[][]>();
		for (String letter:letters){
			outputter.put(letter, createGrid(matrix, letter));
		}
		return outputter;
	}
	
	//-- sum the adjacent numbers at a certain location
	public long borderCount(long[][] grid, int r, int c){
		long count = 0;
		for (int currR=r-1; currR<=r+1; currR++){
			for (int currC=c-1; currC<=c+1; currC++){
				if ((currR>=0 && currC>=0 && currR<=3 && currC<=3) && ((currR!=r) || (currC!=c))){
					count += (grid[currR][currC]) % (long)1E13;
				}
			}
		}
		return count % (long)1E13;
	}
	
	//-- return score as long
	public long score(String s){
		return s.length()*s.length() % (long)1E13;
	}
	
	//-- given the grids for one letter, then another letter, output the grid for the connected ordered segment
	public long[][] consolidate(long[][] oldGrid, long[][] newGrid){
		long[][] outputter = new long[4][4];
		for(int r=0; r<=3; r++){
			for(int c=0; c<=3; c++){
				long previous = newGrid[r][c];
				outputter[r][c] = borderCount(oldGrid, r, c)*previous % (long)1E13;
			}
		}
		return outputter;
	}
	
	//-- from startingLocations, map each possible segment -> its grid -- memoization
	public HashMap<String, long[][]> segmentLocations(String word, HashMap<String, long[][]> letterGrid){
		for(int i=1; i<word.length(); i++){
			String segment = word.substring(0, i+1);
			String prevLetter = word.substring(i, i+1);
			long[][] tempGrid = letterGrid.get(segment.substring(0,i));
			long[][] outputter = consolidate(tempGrid, letterGrid.get(prevLetter));
			letterGrid.put(segment, outputter);
		}
		return letterGrid;
	}
	
	//-- count how many times a word/segment appears in a grid by summing its counts
	public long getCountOfGrid(long[][] grid){
		long count = 0;
		for (int i=0; i<=3; i++){
			for (int j=0; j<=3; j++){
				count += grid[i][j];
			}
		}
		return count % (long)1E13;
	}
}