package src.cryptographicAssignments;
import edu.duke.FileResource;

public class CommonWords {

	public String[] getCommon() {
		FileResource resource = new FileResource("/Users/ricardo_pc/Library/Mobile Documents/com~apple~CloudDocs/ATA Workspace/course 3/weekOneAssignments/src/weekOneAssignments/data/common.txt");
		String[] commonStrings = new String[20];
		
		int index = 0;
		for (String s : resource.words()) {
			commonStrings[index]  = s;
			index += 1;
		}
		return commonStrings;
	}
	
	public void countWords(FileResource resource, String[] common, int[] counts) {
		for (String word: resource.words()) {
			word = word.toLowerCase();
			int index = indexOf(common, word);
			if (index != -1) {
				counts[index] += 1;
			}
		}
	}
	
	public int indexOf(String[] list, String word) {
		// Takes a list of words and a word and look for the word in the list and return the index of where it occurs in the list. If not return -1
		for (int i = 0; i < list.length; i++) {
			if (list[i].equals(word)) {
				return i;
			}
		}
		return -1;
	}
	
	public void countShakespearce() {
		String[] playsStrings = {"caesar.txt", "errors.txt", "hamlet.txt", "likeit.txt", "macbeth.txt", "romeo.txt" };
		
		String[] common = getCommon();
		int[] counts = new int[common.length];
		for (int i = 0; i < playsStrings.length; i++) {
			FileResource resource = new FileResource("/Users/ricardo_pc/Library/Mobile Documents/com~apple~CloudDocs/ATA Workspace/course 3/weekOneAssignments/src/weekOneAssignments/data/" + playsStrings[i]);
			countWords(resource, common, counts);
			System.out.println("done with " + playsStrings[i]);
		}
		// Print out results
		for(int i = 0; i < common.length; i++) {
			System.out.println(common[i] + "\t" + counts[i]);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CommonWords c = new CommonWords();
		c.countShakespearce();
	}

}
