public class DNAgcPercent {
    public double percentage(String dnaStrand) {
    	
    	int count = 0;
    	
    	//get count
    	for (int i=0; i<dnaStrand.length(); i++){
    		if (dnaStrand.charAt(i) == 'g'){
    			count++;
    		}
    		else if (dnaStrand.charAt(i) == 'c'){
    			count++;
    		}
    	}
    	double percent;
    	//protect against NaN
        if (count == 0){
        	percent = 0;
        }
        //calculate percent
        else{
        	percent = (double)count/dnaStrand.length();
        }
    	return percent;
    }
}