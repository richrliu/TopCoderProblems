
public class DNAMutate {

	/**
	 * @param args
	 */
	public String mutate(String dna) {
		// TODO Auto-generated method stub
		String[] letterArray = dna.split("(?!^)");
		String result = "";
		for (int i=0; i<(letterArray.length); i++){
			result += letterArray[letterArray.length-1-i];
		}
		
		return result;
	}

}
