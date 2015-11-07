public class FileSystem {
	
      public long findUsed(int[] fileBytes, int blockBytes) {
    	  long spaceNeeded = 0;
    	  for (int i=0; i<fileBytes.length; i++){
        	  int quotient = (int) Math.floor(fileBytes[i]/blockBytes);
        	  
        	  if (quotient*blockBytes == fileBytes[i]){ //-- exactly a multiple
        		  spaceNeeded += fileBytes[i];
        	  }
        	  else if (quotient*blockBytes < fileBytes[i]){ //-- not a multiple indicates floor function used
        		  spaceNeeded += (quotient+1)*blockBytes;
        	  }
        	  
    	  }
    	  return spaceNeeded;
    	  
      }
      
}