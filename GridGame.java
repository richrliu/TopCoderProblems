import java.util.*;

public class GridGame {
	int rows;
	int cols;
	
	// data structure to store the grid
	HashMap<String[][], Integer> gridWins = new HashMap<String[][], Integer>();
	
	public boolean canPlaceAt(int r, int c, String[][] grid) { 
		// returns true if you can place an X at row, col
//		System.out.println("here");
		if (grid[r][c].equals("X")) return false;
		if (c-1>=0){
			if (grid[r][c-1].equals("X")) {
//				System.out.println("f1");
				return false;
			}
		}
		if (c+1<=3){
			if (grid[r][c+1].equals("X")) {
//				System.out.println("f2");
				return false;
			}
		}
		if (r-1>=0){
			if (grid[r-1][c].equals("X")) {
//				System.out.println("f3");
				return false;
			}
		}
		if (r+1<=3){
			if (grid[r+1][c].equals("X")) {
//				System.out.println("f4");
				return false;
			}
		}
//		System.out.println("true");
		return true;
	}
	
	public int winningMoves(String[] gridInput) { 
		// store input into your grid return wins(); 
		// recursive helper function
		rows = 4;
		cols = 4;
		
		String[][] grid = gridderToGrid(gridInput);
//		for (String[] arr: grid){
//			System.out.println(Arrays.asList(arr));
//		}
		return wins(grid);
		
	}
	
	public String[][] gridderToGrid(String[] gridder){
		String[][] grid = new String[rows][cols];
		for(int r=0; r<rows; r++) {
			for(int c=0; c<cols; c++) {
				grid[r][c] = gridder[r].charAt(c) == 'X'? "X":".";
			}
		}
		return grid;
	}
	
	public int wins(String[][] grid) {
//		String[][] grid = new String[rows][cols];
//		for(int r=0; r<rows; r++) {
//			for(int c=0; c<cols; c++) {
//				grid[r][c] += gridder[r].charAt(c);
//			}
//		}
		if (gridWins.containsKey(grid)) return gridWins.get(grid);
		int count=0;
		for(int r=0; r<4; r++) {
			for(int c=0; c<4; c++) {
				if(canPlaceAt(r,c, grid)) {
					// place X if(wins() == 0) // increment count
					grid[r][c] = "X";
//					int prevCount = gridWins.get(grid) == null? 0:gridWins.get(grid);
					if (wins(grid) == 0){
						count++;
					}
					grid[r][c] = "."; // backtrack
				}
			}
		}
		return count;
	}
	
	public static void main(String[] args){
		GridGame x = new GridGame();
		String[] a = {".X..", "X..X", "..X.", "X..X" };
		System.out.println(x.winningMoves(a));
	}

}
