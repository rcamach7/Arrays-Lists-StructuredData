package CryptographyAssignments;

public class CaesarCipherTwo {

	private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private String alphabetLowerCase = alphabet.toLowerCase();
	
	private String encryptAlphabetOne;
	private String encryptAlphabetTwo;
	private int encryptKey;
	private int encryptKey2;
	
	public CaesarCipherTwo (int key1, int key2) {
		encryptAlphabetOne = alphabet.substring(key1) + alphabet.substring(0, key1);
		encryptAlphabetTwo = alphabet.substring(key2) + alphabet.substring(0, key2);
		encryptKey = key1;
		encryptKey2 = key2;
	}
	
	public String encrypt(String input, int key) {
		String encryptAlphabet = null;
		if (key == encryptKey) {
			encryptAlphabet = encryptAlphabetOne;
		} 
		if (key == encryptKey2){
			encryptAlphabet = encryptAlphabetTwo;
		}
		
		StringBuilder answerBuilder = new StringBuilder(input);
		for (int i = 0; i < input.length(); i++) {
			char currentChar  = input.charAt(i);
			
			// Checks if i'th position contains a uppercase alphabet character - and encrypts if so
			int indexFinderUpercase = alphabet.indexOf(currentChar);		
			if (indexFinderUpercase != -1) {
				char newChar = encryptAlphabet.charAt(indexFinderUpercase);
				answerBuilder.setCharAt(i, newChar);
			}
			// Checks if i'th position contains a lowercase letter - and encrypts if so
			int indexFinderLowerCase = alphabetLowerCase.indexOf(currentChar);
			if (indexFinderLowerCase != -1) {
				char newChar = Character.toLowerCase(encryptAlphabet.charAt(indexFinderLowerCase));
				answerBuilder.setCharAt(i, newChar);
			}
		}
		return answerBuilder.toString();
	}	
	
	public String encryptTwoKeys(String message) {
		StringBuilder answerBuilder = new StringBuilder(message);
		
		for (int i = 0; i < message.length(); i++) {
			if (i % 2 == 0) { // even numbers use first key
				String encString = encrypt( String.valueOf(answerBuilder.charAt(i) ) , encryptKey);
				char newChar = encString.charAt(0) ;
				answerBuilder.setCharAt(i, newChar);
			} else { // odd numbers use second key
				String encString = encrypt( String.valueOf(answerBuilder.charAt(i)) , encryptKey2);
				char newChar = encString.charAt(0) ;
				answerBuilder.setCharAt(i, newChar);
			}
		}
		return answerBuilder.toString();	
	}
	
	public String decrypt(String message) {
		
		CaesarCipherTwo cc = new CaesarCipherTwo(26 - encryptKey, 26 - encryptKey2);
		return cc.encryptTwoKeys(message);
		
	}
	
}
