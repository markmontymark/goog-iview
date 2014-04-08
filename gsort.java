import java.util.*;

public class gsort {

	private class PrecedenceComparator<String> implements Comparator<String>{
		private Precedence precedence;
		public PrecedenceComparator(Precedence p) {
			precedence = p;
		}
		@Override
		public int compare(Object a,Object b) {
			java.lang.String aStr = (java.lang.String)a;
			java.lang.String bStr = (java.lang.String)b;
			int[] asInts = precedence.getInts(aStr);
			int[] bsInts = precedence.getInts(bStr);
			int asIntsLength = asInts.length;
			int bsIntsLength = bsInts.length;
			for(int i = 0; i < asIntsLength; i++)
				if( i >= bsIntsLength )
					return 1;
				else if( asInts[i] < bsInts[i])	
					return -1;
				else if( asInts[i] > bsInts[i])	
					return 1;
			return 0;
		}
	}

	private class Precedence {

		private Map<String,Integer> indices;

		public Precedence(String input) {
			indices = new HashMap<String,Integer>();
			String[] chars = input.split("");
			for(int i = 0; i <chars.length; i++)
				indices.put( chars[i], new Integer(i) );
		}

		public int getIndex(String s){
			if( ! indices.containsKey(s) )
				return Integer.MAX_VALUE;
			return ( (Integer) indices.get(s)).intValue();
		}

		public int[] getInts(java.lang.String s) {
			int [] retval = new int[s.length()];
			String[] chars = s.split("");
			for(int i = 0; i <chars.length; i++)
				retval[i] = getIndex(chars[i]);
			return retval;
		}
	}

	public List<String> sortBy( List<String> strings, String precedence)
	{
		Precedence p = new Precedence(precedence);
		Collections.sort( strings, new PrecedenceComparator<String>(p));
		return strings;
	}

	public static void main(String[] args)
	{
		List<String> strs = new ArrayList<String>();
		strs.add("asdf");
		strs.add("aaasdf");
		strs.add("fdsa");
		strs.add("qwer");
		strs.add("irewq");
		gsort gsorter = new gsort();
		gsorter.sortBy(strs,"abcdefghijklmnopqrstuvwxyz");
		for(int i = 0; i < strs.size(); i++)
			System.out.println(strs.get(i));
	}
}
