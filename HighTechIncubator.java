import java.util.TreeSet;
import java.util.Set;
public class HighTechIncubator {
	public String[] shameList(String[] location1, String[] location2, String[] location3) {
		//-- use TreeSets because of intersection, and alphabetical sorting
		TreeSet<String> masterSet = new TreeSet<String>();
		TreeSet<String> set1 = new TreeSet<String>(lister(location1));
		TreeSet<String> set2 = new TreeSet<String>(lister(location2));
		TreeSet<String> set3 = new TreeSet<String>(lister(location3));
		
		//-- find intersections - we only require intersections of pairs
		TreeSet<String> temp12 = new TreeSet<String>(intersection(set1, set2));
		TreeSet<String> temp23 = new TreeSet<String>(intersection(set2, set3));
		TreeSet<String> temp13 = new TreeSet<String>(intersection(set1, set3));
		
		//-- essentially the unions of the three intersections
		masterSet.addAll(temp12);
		masterSet.addAll(temp23);
		masterSet.addAll(temp13);
		
		//-- Set to string.
		String[] finale = masterSet.toArray(new String[masterSet.size()]);
		
		return finale;
		
	}
	
	//-- converts list to set
	public TreeSet<String> lister(String[] list){
		TreeSet<String> setter = new TreeSet<String>();
		for (String s : list){
			setter.add(s);
		}
		return setter;
	}
	
	//-- returns intersection of two sets as TreeSet
	public TreeSet<String> intersection(Set<String> a, Set<String> b) {
		  TreeSet<String> intersection = new TreeSet<String>(a);
		  intersection.retainAll(b);
		  return intersection;
		  }
	
}