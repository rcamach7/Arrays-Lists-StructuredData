import java.util.ArrayList;
import edu.duke.FileResource;

public class WordFrequency {

	private ArrayList<String> myWords;
	private ArrayList<Integer> myFrequency;
	
	public WordFrequency() {
		myWords = new ArrayList<String>();
		myFrequency = new ArrayList<Integer>();
	}
	
	public void findUnique() {
		
		FileResource resource = new FileResource("/Users/rcamach7/Library/Mobile Documents/com~apple~CloudDocs/ATA Workspace/ArraysListsStructuredDataCourse/GladLibsArrayListsW2/src/data/likeit.txt");	
		for (String s: resource.words()) {
			s = s.toLowerCase();
			int index = myWords.indexOf(s);
			if (index == -1) {
				myWords.add(s);
				myFrequency.add(1);
			} else {
				int value = myFrequency.get(index);
				myFrequency.set(index, value + 1);
			}
		}	
	}
	
	public int findIndexOfMax() {
		int maxIndex = 0;
		for (int i = 0; i < myFrequency.size(); i++) {
			if (myFrequency.get(i) > myFrequency.get(maxIndex)){
				maxIndex = i;
			}
		}
		return maxIndex;
	}
	
	public String printMostFrequentWord () {
		int max = findIndexOfMax();
		return myWords.get(max) + " " + myFrequency.get(max);
	}
	
	public int getSize() {
		return myWords.size();
	}
	
	public void printSidebySide() {
		for (int i =0; i < myWords.size(); i++) {
			System.out.println(myFrequency.get(i) + ": " + myWords.get(i));
		}
	}
	
	// Tester Methods and Main
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WordFrequency app = new WordFrequency();
		app.tester();
	}
	
	public void tester() {
		WordFrequency app = new WordFrequency();
		app.findUnique();
		System.out.println("Total number of unique words: " + app.getSize());
		app.printSidebySide();
		System.out.println("Most frequent word is: " + app.printMostFrequentWord());
	}	
}
