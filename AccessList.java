public class AccessList {

	/**
	 * @param args
	 */
	public String mayAccess(int[] rights, int minPermission){
		
		String result = "";
		
		for (int i=0; i<rights.length; i++){
			if (rights[i] < minPermission){
				result+="D";
			}
			else{
				result+="A";
			}
		}
		
		return result;
		
	}

}
