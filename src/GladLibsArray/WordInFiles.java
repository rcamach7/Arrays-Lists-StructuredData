package GladLibsArray;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import edu.duke.DirectoryResource;
import edu.duke.FileResource;

public class WordInFiles {

	private HashMap<String, ArrayList<String>> wordMap;
	
	public WordInFiles() {
		wordMap = new HashMap<String, ArrayList<String>>();
	}
	
	private void addWordsFromFile(File myFile) {
		FileResource resource = new FileResource(myFile);
		for (String word : resource.words()) {
			if (!wordMap.containsKey(word)) {
				ArrayList<String> fileNames = new ArrayList<String>();
				fileNames.add(myFile.getName());
				
				wordMap.put(word, fileNames);
			} else {
				ArrayList<String> fileNames = new ArrayList<String>();
				fileNames = wordMap.get(word);
				fileNames.add(myFile.getName());
				
				wordMap.put(word, fileNames);
			}
		}
	}
	
	public void buildWordFileMap() {
		DirectoryResource resource = new DirectoryResource();
		for (File file : resource.selectedFiles()) {
			addWordsFromFile(file);
		}
	}
	
	public void printHashMap() {
		for (String word : wordMap.keySet()) {
			System.out.print(word + " ");
			ArrayList<String> currentList = wordMap.get(word);
			for (int i = 0; i < currentList.size(); i++) {
				System.out.print(currentList.get(i) + " ");
			}
			System.out.println();
		}
	}
	
	public int maxNumber() {
		int currentMax = 0;
		for (String word: wordMap.keySet()) {
			if (wordMap.get(word).size() > currentMax) {
				currentMax = wordMap.get(word).size();
			}
		}
		return currentMax;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WordInFiles app = new WordInFiles();
		app.tester();
	}
	
	public void tester() {
		buildWordFileMap();
		printHashMap();
		System.out.println("Maximum times a word appears in different files: " + maxNumber());
	}
}
