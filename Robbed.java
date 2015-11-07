
public class Robbed {
	int[] rdelta = {-1, 0, 0, 1};
	int[] cdelta = {0, -1, 1, 0};

	public int countRoutes(String[] maze){
		int count = 0;
		int[] rc = findPos(maze);
		int r = rc[0];
		int c = rc[1];
		int rowCash = rc[2];
		int colCash = rc[3];
		char[][] matrix = new char[maze.length][maze[0].length()];
		for (int i=0; i<maze.length; i++){
			for (int j=0; j<maze[0].length(); j++){
				matrix[i][j] = maze[i].charAt(j);
			}
		}
		char[][] matrixCopy = matrix;
		
		for (int k=0; k<rdelta.length; k++){
			if ((r+rdelta[k])>=0 && (r+rdelta[k])<maze.length && (c+cdelta[k])>=0 && (c+cdelta[k])<maze[0].length()){
				if (distance(r+rdelta[k], c+cdelta[k], rowCash, colCash) <= distance(r, c, rowCash, colCash)){
					//-- check win conditions
					if (maze[r+rdelta[k]].charAt(c+cdelta[k])=='C') {
						count++;
					}
					else if (maze[r+rdelta[k]].charAt(c+cdelta[k]) == 'X'){
						continue;
					}
					else{
						matrixCopy[r][c] = '.';
						matrixCopy[r+rdelta[k]][c+cdelta[k]] = 'R';
						count += countRoutes(matrixCopy);
						matrixCopy[r][c] = 'R';
						matrixCopy[r+rdelta[k]][c+cdelta[k]] = '.';
					}
				}
			}
		}
		return count;
	}
	
	public int countRoutes(char[][] maze){
		int count = 0;
		char[][] mazeCopy = maze;
		int[] rc = findPos(maze);
		int r = rc[0];
		int c = rc[1];
		int rowCash = rc[2];
		int colCash = rc[3];
		
		for (int k=0; k<rdelta.length; k++){
			if ((r+rdelta[k])>=0 && (r+rdelta[k])<maze.length && (c+cdelta[k])>=0 && (c+cdelta[k])<maze[0].length){
				if (distance(r+rdelta[k], c+cdelta[k], rowCash, colCash) <= distance(r, c, rowCash, colCash)){
					//-- check win conditions
					if (maze[r+rdelta[k]][c+cdelta[k]]=='C') {
						count++;
					}
					else if (maze[r+rdelta[k]][c+cdelta[k]] == 'X'){
						continue;
					}
					else{
						mazeCopy[r][c] = '.';
						mazeCopy[r+rdelta[k]][c+cdelta[k]] = 'R';
						count +=countRoutes(mazeCopy);
						mazeCopy[r][c] = 'R';
						mazeCopy[r+rdelta[k]][c+cdelta[k]] = '.';
					}
				}
			}
		}
		return count;
	}
	
	public int[] findPos(String[] maze){
		int[] rc = new int[4];
		for (int i=0; i<maze.length; i++){
			for (int j=0; j<maze[0].length(); j++){
				if (maze[i].charAt(j) == 'R'){
					rc[0] = i;
					rc[1] = j;
				}
				if (maze[i].charAt(j) == 'C'){
					rc[2] = i;
					rc[3] = j;
				}
			}
		}
		return rc;
	}
	
	public int[] findPos(char[][] maze){
		int[] rc = new int[4];
		for (int i=0; i<maze.length; i++){
			for (int j=0; j<maze[0].length; j++){
				if (maze[i][j] == 'R'){
					rc[0] = i;
					rc[1] = j;
				}
				if (maze[i][j] == 'C'){
					rc[2] = i;
					rc[3] = j;
				}
			}
		}
		return rc;
	}
	
	public double distance(int r1, int c1, int r2, int c2){
		return Math.sqrt((c2-c1)*(c2-c1) + (r2-r1)*(r2-r1));
	}

}
