package src.VigenereCipher;


public class App {

	public static void main(String[] args) {
		VigenereBreaker vigenereB = new VigenereBreaker();
		
		// Break using tryKeyLength (Knowing the length of they key)
//		vigenereB.breakVigenere();
		
		// Breaking by knowing the language (also we iterate over X amount of key lenghts
//		vigenereB.breakVigenereUsingLanguage();
		
		// Test method that pulls most common character in a given dictionary
//		vigenereB.testMostCommonCharIn();
		
		// Test method that breaks for all languages
		vigenereB.testBreakForAllLangs();
	}

}
