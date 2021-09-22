package cryptographicAssignments;

public class CaesarCipher {
	
	public String encrypt(String input, int key) {	
		// Write the method encrypt that has two parameters, a String named input and an int named key. 
		// This method returns a String that has been encrypted using the Caesar Cipher algorithm explained 
		// in the videos.
		
		String alphabetString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String alphabetLowerCase = alphabetString.toLowerCase();
		String encryptKey = alphabetString.substring(key)
							+ alphabetString.substring(0, key);
		
		StringBuilder answerBuilder = new StringBuilder(input);
		for (int i = 0; i < input.length(); i++) {
			char currentChar  = input.charAt(i);
			
			// Checks if i'th position contains a uppercase alphabet character - and encrypts if so
			int indexFinderUpercase = alphabetString.indexOf(currentChar);		
			if (indexFinderUpercase != -1) {
				char newChar = encryptKey.charAt(indexFinderUpercase);
				answerBuilder.setCharAt(i, newChar);
			}
			// Checks if i'th position contains a lowercase letter - and encrypts if so
			int indexFinderLowerCase = alphabetLowerCase.indexOf(currentChar);
			if (indexFinderLowerCase != -1) {
				char newChar = Character.toLowerCase(encryptKey.charAt(indexFinderLowerCase));
				answerBuilder.setCharAt(i, newChar);
			}
		}
		return answerBuilder.toString();
	}
	
	public void testCaesar() {
		String encrypted = encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 17);
		System.out.println(encrypted);
	}
	
	public String encryptTwoKeys(String input, int key1, int key2) {
		// Write the method encryptTwoKeys that has three parameters, a String named input, 
		// and two integers named key1 and key2. This method returns a String that has been 
		// encrypted using the following algorithm. Parameter key1 is used to encrypt every 
		// other character with the Caesar Cipher algorithm, starting with the first character, 
		// and key2 is used to encrypt every other character, starting with the second character. '
		
		// For example, the call encryptTwoKeys(“First Legion”, 23, 17) should return 
		// “Czojq Ivdzle”. Note the ‘F’ is encrypted with key 23, the first ‘i’ with 17, 
		// the ‘r’ with 23, and the ‘s’ with 17, etc. Be sure to test this method. 
		StringBuilder answerBuilder = new StringBuilder(input);
		
		for (int i = 0; i < input.length(); i++) {
			if (i % 2 == 0) { // even numbers use first key
				String encString = encrypt( String.valueOf(answerBuilder.charAt(i) ) , key1);
				char newChar = encString.charAt(0) ;
				answerBuilder.setCharAt(i, newChar);
			} else { // odd numbers use second key
				String encString = encrypt( String.valueOf(answerBuilder.charAt(i)) , key2);
				char newChar = encString.charAt(0) ;
				answerBuilder.setCharAt(i, newChar);
			}
		}
		return answerBuilder.toString();
	}
	public void testEncryptTwoKeys() {
		System.out.println(encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21));
	}
	
	public static void main(String[] args) {
		CaesarCipher cs = new CaesarCipher();
		cs.testEncryptTwoKeys();
		cs.testCaesar();
	}
	
}
