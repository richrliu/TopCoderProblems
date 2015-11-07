public class PizzaToppings
   {
	public boolean arrayContainsString(String[] array, String needle){
		boolean contains = false;
		for (int i=0;i<array.length;i++){
			if (array[i].equals(needle)){
				contains = true;
			}
		}
		return contains;
	}

	public int whichToppings(String[] menu, String[] favorites){
    	  boolean hasMyFave = false;
    	  int count = -1;
    	  for (int i=0;i<favorites.length;i++){ //iterate over combinations
        	  count++;
			  String[] current = favorites[i].split(" "); 
    		  for (int j=0;j<current.length;j++){ //iterate over ingredients within combinations
    			  if (!arrayContainsString(menu, current[j])){ //stop iterating if they have a favorite
    				  break;
    			  }
    			  if (j == current.length-1){ //if finished iterating
    				  hasMyFave = true;
    			  }
    			  
    		  }
        	  if (hasMyFave){
				  break;
			  }
        	  
    	  }
    	  if (!hasMyFave){  //doesnt have any fave combinations
    		  count = -1;
    	  }
		  return count;
      }
   }