package src.cryptographicAssignments;

// You will write a program to transform words from a file into another form, such as replacing
// vowels with an asterix. 
public class WordPlay {

	public boolean isVowel(Character  ch) {
		// Write a method isVowel that has one Char parameter named ch. This method returns true if ch is a vowel 
		// (one of 'a', 'e', 'i', 'o', or 'u' or the uppercase versions) and false otherwise. 
		String vowel  = "aeiou";
		ch = Character.toLowerCase(ch);
		for (int i = 0; i < vowel.length(); i++) {
			if (ch.equals(vowel.charAt(i))) {
				return true;
			}
		}		
		return false;
	}
	
	public void testIsVowel() {
		System.out.println(isVowel('O'));
	}
	
	public String replaceVowels(String name, Character ch) {
		// Write a method replaceVowels that has two parameters, a String named phrase and a Char named ch. 
		// This method should return a String that is the string phrase with all the vowels
		// (uppercase or lowercase) replaced by ch.
		StringBuilder replacedVowel = new StringBuilder();
		for (int i = 0; i < name.length(); i++) {
			if (isVowel(name.charAt(i))) {
				replacedVowel.append(ch);
			} else {
				replacedVowel.append(name.charAt(i));
			}
		}
		return replacedVowel.toString();
	}
	
	public void testReplaceVowels () {
		System.out.println(replaceVowels("Hello World", '*'));
	}

	public String emphasize(String phrase, Character ch) {
		// This method should return a String that is the string phrase but with the Char 
		// (upper- or lowercase) replaced by
	    // ‘*’ if it is in an odd number location in the string (e.g. the first character has an odd number location but an even index, it is at index 0), or
	    // ‘+’ if it is in an even number location in the string (e.g. the second character has an even number location but an odd index, it is at index 1).
		StringBuilder answerBuilder = new StringBuilder();
		for (int i = 0; i < phrase.length(); i++) {
			if ((i % 2) == 0 && phrase.charAt(i) == ch) {
				answerBuilder.append('*');
			} else if ((i % 2) != 0 && phrase.charAt(i) == ch){
				answerBuilder.append('+');
			} else {
				answerBuilder.append(phrase.charAt(i));
			}
		}
		return answerBuilder.toString();
	}
	
	public void testEmphasize() {
		System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
	}
	
	public static void main(String[] args) {
		WordPlay wordPlay = new WordPlay();
		//wordPlay.testIsVowel();
		//wordPlay.testReplaceVowels();
		wordPlay.testEmphasize();
	}
	
	// Testing branch feature of git

}
