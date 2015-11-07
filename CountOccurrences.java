
public class CountOccurrences {

	/**
	 * @param args
	 */
	public int numberOccurrences(int number, int digit) {
		// TODO Auto-generated method stub
		int count = 0;
		while (number>0){
			int lastDigit;
			lastDigit = number % 10;
			if (lastDigit == digit){
				count++;
			}
			number = (number-lastDigit)/10;
		}
		return count;
	}

}
