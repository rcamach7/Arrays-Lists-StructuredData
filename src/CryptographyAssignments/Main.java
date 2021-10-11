package src.CryptographyAssignments;
import edu.duke.FileResource;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main app = new Main();
		app.simpleTestsTwo();
	}
	
	public void simpleTestsTwo() {
		String message = "this is the last test for week one";
		CaesarCipherTwo cc = new CaesarCipherTwo(8, 21);
		System.out.println("Message: " + message +
						   "\nEncrypted Message: " + cc.encryptTwoKeys(message) + 
						   "\nDecrypted Message: " + cc.decrypt(cc.encryptTwoKeys(message)));
	}
	
	public void simpleTests() {
		String message = "This is the last test for week one";
		CaesarCipher cc = new CaesarCipher(18);
		System.out.println("Message: " + message +
						   "\nEncrypted Message: " + cc.encrypt(message) + 
						   "\nDecrypted Message: " + cc.decrypt(cc.encrypt(message)));
	}
	
	public int indexOfMax(int[] lengthsArray) {
		int indexOfCurrentMax = 0;
		for (int i = 0; i < lengthsArray.length; i++) {
			if (lengthsArray[indexOfCurrentMax] < lengthsArray[i]) {
				indexOfCurrentMax = i;
			}
		}
		return indexOfCurrentMax;
	}
	
	public void countWordLengths(FileResource resource, int[] counts) {	
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
	}

	public String halfOfString (String message, int start) {

		StringBuilder halfStringBuilder = new StringBuilder();
		String subString = message.substring(start);
		
		for (int i = 0; i < subString.length(); i++) {
			if (i % 2 == 0) {
				halfStringBuilder.append(subString.charAt(i));
			} 
		}
		return halfStringBuilder.toString();
	}
}
