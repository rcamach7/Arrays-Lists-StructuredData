package src.cryptographicAssignments;
import edu.duke.FileResource;


public class WordLengths {
	// You will write a program to figure out the most common word length of words from a file. 
	// To solve this problem you will need to keep track of how many words from a file are of each possible length.
	// You should group all words of length 30 or more together,
	// and you should not count basic punctuation that are the first or last characters of a group of characters.
	
	public void countWordLengths(FileResource resource, int[] counts) {
		// This method should read in the words from resource and count the number of words of each length
		// for all the words in resource, storing these counts in the array counts.
		// For example, after this method executes, counts[k] should contain the number of words of length k.
		
		for (String word: resource.words()) {
			int wordLength = word.length(); // total word length - below we will reduce if word has any non letters at start or end of word
			
			boolean isLetterStart = Character.isLetter( word.charAt(0) );
			if ( !isLetterStart ) {
				wordLength = wordLength - 1;
			}
			boolean isLetterEnd = Character.isLetter( word.charAt(word.length() - 1) );
			if ( !isLetterEnd ) {
				wordLength = wordLength - 1;
			}
			
			// Now that we have the correct size - we will pass it on to our counter array 
			if (wordLength >= 30) {
				counts[30] += 1;
			} else {
				counts[wordLength] += 1; 
			}
		}
		
		for (int i = 1; i < counts.length; i++) {
			System.out.println(counts[i] + " words of length " + i);
		}
		
		// * If a word has a non-letter as the first or last character, it should not be counted as part of the word length. 
		// * For any words equal to or larger than the last index of the counts array, 
		// count them as the largest size represented in the counts array.
		// * You may want to consider using the Character.isLetter method that returns true if a character is a letter, 
		// and false otherwise. For example, Character.isLetter(‘a’) returns true, and Character.isLetter(‘-’) returns false. 
		
	}
	
	public int indexOfMax(int[] lengthsArray) {
		// This method returns the index position of the largest element in values. 
		// Then add code to the method testCountWordLengths to call indexOfMax to determine the most 
		// common word length in the file. 
		// * For example, calling indexOfMax after calling countWordLengths 
		// on the file smallHamlet.txt should return 3.
		
		int indexOfCurrentMax = 0;
		for (int i = 0; i < lengthsArray.length; i++) {
			if (lengthsArray[indexOfCurrentMax] < lengthsArray[i]) {
				indexOfCurrentMax = i;
			}
		}
		
		return indexOfCurrentMax;
	}
	
	public void testCountWordLengths() {
		// This method should call countWordLengths with a file and then print the number of words of each length.
		// Test it on the small file smallHamlet.txt shown below.
		FileResource resource = new FileResource("/Users/ricardo_pc/Library/Mobile Documents/com~apple~CloudDocs/ATA Workspace/course 3/weekOneAssignments/src/weekOneAssignments/data/lotsOfWords.txt");
		int[] counts = new int[31];
		
		countWordLengths(resource, counts);
		System.out.println("The most common length of words in this file is words of length : " + indexOfMax(counts) );
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WordLengths wordLengths = new WordLengths();
		wordLengths.testCountWordLengths();
	}

}
