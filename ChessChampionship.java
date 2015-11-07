public class ChessChampionship {
     public int[] points(String[] games) {
         // you write code here
    	 int[] points = new int[games.length];
    	 for (int k=0;k<games.length;k++){
    		 points[k]=0;
    	 }
    	 // read the "table", win is 3 points for white, D is 1 point for each, L is 3 points for black
    	 // i is white and j is black
    	 
    	 for (int i=0; i<games.length; i++){
    		 for (int j=0; j<games[i].length(); j++){
    			 if (games[i].charAt(j)=='W'){
    				 points[i]+=3;
    			 }
    			 else if (games[i].charAt(j)=='D'){
    				 points[i]++;
    				 points[j]++;
    			 }
    			 else if (games[i].charAt(j)=='L'){
    				 points[j]+=3;
    			 }
    		 }
    		 
    	 }
    	 
    	 return points;
    	 
     }
 }