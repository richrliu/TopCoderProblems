public class RoundCountry {
    public int minBorders(int[] x, int[] y, int[] r, 
                            int x1, int y1, int x2, int y2) {
        // you write code here
    	
    	int[] startIsInCircle = new int[x.length];
    	int[] endIsInCircle = new int[x.length];
    	int count = 0;
    	
    	for (int i=0; i<x.length; i++){
    		if ((x1-x[i])*(x1-x[i]) + (y1-y[i])*(y1-y[i]) < r[i]*r[i]){
    			startIsInCircle[i] = 1;
    		}
    		else{
    			startIsInCircle[i] = 0;
    		}
    		if ((x2-x[i])*(x2-x[i]) + (y2-y[i])*(y2-y[i]) < r[i]*r[i]){
    			endIsInCircle[i] = 1;
    		}
    		else{
    			endIsInCircle[i] = 0;
    		}
    		
    		if (startIsInCircle[i]-endIsInCircle[i] != 0){
    			count++;
    		}
    	}
    	
    	return count;
    }
 }