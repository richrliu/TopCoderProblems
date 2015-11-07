
public class BSTpermutations {

	public long howMany(int[] values){
		long n = values.length;
		return count(n);
	}
	
	public long count(long n){
		long[] counter = new long[(int) (n+1)];
		counter[0] = 1;
		counter[1] = 1;
		
		//-- starting from num=2, fill out counter[num]
		for (long num=2; num<=n; num++){
			long ret = 0;
			for (long l=0; l<num; l++){ //-- calculate ret
				long left = counter[(int) l];
				long right = counter[(int) (num-l-1)];
				ret+=left*right;
			}
			counter[(int)num] = ret;
		}
		return counter[(int)n];
	}

}
