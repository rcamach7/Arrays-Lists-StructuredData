
import java.util.*;

public class CodonAssignment { 
	private HashMap<String, Integer> codonTracker;
	
	public CodonAssignment() {
		codonTracker = new HashMap<String, Integer>();
	}
	
	public void buildCodonMap(int start, String dna) {
		// This method will build a new map of codons mapped to their counts from the string dna with the reading 
		// frame with the position start (a value of 0, 1, or 2). You will call this method several times, 
		// so make sure your map is empty before building it.
		for (int i = start; i < dna.length(); i = i + 3) {
			if (i + 3 > dna.length()) {
				return;
			}
			String currentCodon = dna.substring(i, i + 3);
			if (!codonTracker.containsKey(currentCodon)) {
				codonTracker.put(currentCodon, 1);
			} else {
				codonTracker.put(currentCodon, codonTracker.get(currentCodon) + 1);
			}
		}
	}
	
	public String getMostCommonCodon() {
		// This method returns a String, the codon in a reading frame that has the largest count. 
		// If there are several such codons, return any one of them. This method assumes the HashMap 
		// of codons to counts has already been built.
		int maxValue = 0;
		String mostCommonCodon = "";
		for (String codon : codonTracker.keySet()) {
			if (codonTracker.get(codon) > maxValue) {
				maxValue = codonTracker.get(codon);
				mostCommonCodon = codon + " with " + maxValue;
			}
		}
		return mostCommonCodon;
	}
	
	public void printCodonCounts(int start, int end) {
		System.out.println("Most common codon is " + getMostCommonCodon() );
		System.out.println("Counts of codons between " + start + " and " + end + " inclusive are: ");
		for (String codon: codonTracker.keySet()) {
			if (codonTracker.get(codon) >= start && codonTracker.get(codon) <= end) {
				System.out.println(codon + "\t" + codonTracker.get(codon));
			}
		}
	}

	public static void main(String[] args) {
		CodonAssignment app = new CodonAssignment();
		app.test();
	}
	
	public void test() {
		buildCodonMap(1, "CGTTCAAGTTCAA");
		printCodonCounts(0, 3);
	}

}
