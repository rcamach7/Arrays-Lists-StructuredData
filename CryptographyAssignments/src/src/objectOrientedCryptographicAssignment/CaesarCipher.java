package src.objectOrientedCryptographicAssignment;

public class CaesarCipher {

	private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private String alphabetLowerCase = alphabet.toLowerCase();
	private String encryptAlphabet;
	private int encryptKey;
	
	public CaesarCipher (int key) {
		encryptAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
		encryptKey = key;
	}
	
	public String encrypt(String message) {
		StringBuilder answerBuilder = new StringBuilder(message);
		for (int i = 0; i < message.length(); i++) {
			char currentChar  = message.charAt(i);
			
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
	
	public String decrypt(String input) {
		CaesarCipher cc = new CaesarCipher(26 - encryptKey);
		return cc.encrypt(input);
	}
	
}
