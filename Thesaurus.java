import java.util.Collection;
import java.util.*;
import java.util.Set;
import java.util.TreeSet;

/**
 * Name: Richard Liu
 * 
 * Instructions: Complete all of the TODO's
 */
/*
 * http://www.cs.duke.edu/csed/newapt/thesaurus.html
 */
public class Thesaurus {
  /**
   * Converts the elements of a String to a set. The format of a n-word String should be
   * "word1 word2 word3... wordn" That is, each word should be separated by exactly one space and
   * there should be no leading or trailing spaces.
   * 
   * @param s words with individual spaces separating words
   * @return elements of s as a Set
   */
  public Set<String> sToSet(String s) {
	  // TODO: complete sToSet
	  String[] stringer = s.split(" ");
	  Set<String> setter = new TreeSet<String>();
	  for (int i=0; i<stringer.length; i++){
		  setter.add(stringer[i]);
	  }
	  return setter;
  }
  
  public static Set<String> saToSet(String[] s){
	  Set<String> setter = new TreeSet<String>();
	  for (int i=0; i<s.length;i++){
		  setter.add(s[i]);
	  }
	  return setter;
  }

  /**
   * Converts the elements in a collection to a space-separated list. That is, if the collection
   * contains [A, B, C, D], the method should return "A B C D". There should be no leading or
   * trailing spaces
   * 
   * @return the elements of elems as a space-separated String
   */
  public String collToS(Collection<String> elems) {
    // TODO: complete collToS
	  String [] stringer = new String[elems.size()];
	  String outputter = "";
	  elems.toArray(stringer);
	  for (int i=0; i<stringer.length; i++){
		  outputter += stringer[i];
		  if (! (i==(stringer.length-1))){
			  outputter+=" ";
		  }
	  }
	  return outputter;
  }


  /**
   * Returns the number of elements contained in both sets. The sets passed in should not be
   * changed.
   * 
   * @param a a set of words
   * @param b another set of words
   * @return number of elements in common to a and b
   */
  public int numInCommon(Set<String> a, Set<String> b) {
    // TODO: complete numInCommon
	  Set<String> intersection = new TreeSet<String>(a);
	  intersection.retainAll(b);
	  int count = intersection.size();
	  return count;
  }


  /**
   * Creates a new set that is the union of the given sets. The union of two sets is the set that
   * contains all of the elements of both sets.
   * 
   * Important: The sets passed in should not be modified.
   *
   * @param a a set of words
   * @param b another set of words
   * @return union of sets a and b
   */
  public static Set<String> union(Set<String> a, Set<String> b) {
    // TODO: complete union
	  a.addAll(b);
	  return a;
  }

  /**
   * Creates an edited version of Thesaurus entries.
   * 
   * If any two entries have 2 or more words in common then they should be combined into a single
   * entry. The final Thesaurus must contain no pair of entries that have 2 or more words in common.
   * Each entry must contain no duplicates. The words within each element of the returned value must
   * be in alphabetical order, and the elements must appear in alphabetical order
   * 
   * @param entry each element is a list of words that are all synonyms
   * @return edited version of Thesaurus entries
   */
  public String[] edit(String[] entry) {
    // TODO: Convert entries from array of Strings to ArrayList of Sets
	  List<Set<String>> sets = new ArrayList<Set<String>>();
	  for (int i=0; i<entry.length; i++){
		  sets.add(sToSet(entry[i]));
	  }
    /*
     * TODO: Keep merging entries until nothing gets merged
     * 
     * Pseudocode:
     * 
     * While merging still needed
     *   Let n be the number of elements in entrySet
     *   For every pair (i,j) where 0 <= i,j < n and i < j
     *      if entrySet[i] and entrySet[j] have >= 2 in common
     *          merge entrySet[i] and entrySet[j]
     */
	  boolean mergingFinished = false;
	  while (!mergingFinished){
		  boolean interrupted = false;
		  int n = sets.size();
		  for (int i=0; i<n; i++){
			  for (int j=i+1; j<n; j++){
				  if (numInCommon(sets.get(i), sets.get(j))>1){ //if theres 2 or more in common
					  Set x = new TreeSet<String>(sets.get(i));
					  Set y = new TreeSet<String>(sets.get(j));
					  sets.add(union(sets.get(i),sets.get(j)));
					  sets.remove(x);
					  sets.remove(y);
					  interrupted = true; //if we made edits, stop iterating and restart the process
					  break;
				  }
			  }
			  if (interrupted){
				  break;
			  }
			  if (i== (n-1)){ //if we reach end of the loop, then we can stop iterating
				  mergingFinished = true;
			  }
		  }
	  }

    // Convert list of Sets to an array of Strings
	  String[] finalArray = new String[sets.size()];
	  for (int i=0; i<sets.size(); i++){
		  finalArray[i] = collToS(sets.get(i));
	  }
    
    // Sort entries in alphabetical order

	  Arrays.sort(finalArray);
	  return finalArray;
  }

  public static void main(String[] args) {
    Thesaurus t = new Thesaurus();
    String[] input = {"ape monkey wrench", "wrench twist strain", "monkey twist frugue strain"};
    String[] input2 = {"wrench twist strain", "monkey twist frugue strain","x"};
    System.out.println(union(saToSet(input),saToSet(input2)));
    /*
     * TODO: add more tests below for the methods you wrote (Note: later you learn a better way to
     * test through JUnit)
     */
  }
}
