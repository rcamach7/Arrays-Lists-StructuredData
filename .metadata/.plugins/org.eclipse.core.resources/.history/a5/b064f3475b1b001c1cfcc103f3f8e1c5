package cryptographicAssignments;

public class StatisticalDecryption {

	
	public int[] countOccurences(String encryptedMessageString) {
		// count occurences of each letter in the message we are decrypting
		String alphabetString = "abcdefghijklmnopqrstuvwxyz";
		int[] counts = new int[26];
		// counter
		for (int i = 0; i < encryptedMessageString.length(); i++) {
			char currentChar = Character.toLowerCase(encryptedMessageString.charAt(i));
			int indexOf = alphabetString.indexOf(currentChar);
			if (indexOf != -1) {
				counts[indexOf] += 1;
			}
		}
		return counts;
	}
	
	
	public int maxIndex (int[] frequency) {
		// return the index that has the largest value
		int maxIndex = 0;
		for (int i = 0; i < frequency.length; i++) {
			if (frequency[i] > frequency[maxIndex]) {
				maxIndex = i;
			}
		}
		return maxIndex;
	}
	
	public String decrypt(String encryptedString) {
		CaesarCipher caesarCipher = new CaesarCipher();
		int[] frequency = countOccurences(encryptedString);
		int maxIndex = maxIndex(frequency);
		// The reason we use 4 is because we are assuming the most common letter used is e, and the index of e is 4 in our alphabet
		int decryptionKey = maxIndex - 4;
		if (maxIndex < 4 ) {
			decryptionKey = 26 - ( 4 - maxIndex);
		}
		return caesarCipher.encrypt(encryptedString, 26 - decryptionKey);
	}
	
	public void testDecrypt() {
		CaesarCipher caesarCipher = new CaesarCipher();
		String messageString = "Someone enters the pee in essence eee";
		String encryptedMessageString = caesarCipher.encrypt(messageString, 10);
		
		System.out.println("This is the message: " + messageString +  "\nNow encrypted: " + encryptedMessageString);
		System.out.println( "Now decrypted (by sending the encrypted message): " + decrypt(encryptedMessageString)  );
	}
	
	public String halfOfString (String message, int start) {
		// This method should return a new String that is every other character from message starting with 
		// the start position. For example, the call halfOfString(“Qbkm Zgis”, 0) 
		// returns the String “Qk gs” and the call halfOfString(“Qbkm Zgis”, 1) returns the String “bmZi”. 
		// Be sure to test this method with a small example.
		StringBuilder halfStringBuilder = new StringBuilder();
		String subString = message.substring(start);
		
		for (int i = 0; i < subString.length(); i++) {
			if (i % 2 == 0) {
				halfStringBuilder.append(subString.charAt(i));
			} 
		}
		return halfStringBuilder.toString();
	}
	
	public void testHalfOfString() {
		String myString = "Qbkm Zgis";
		System.out.println("Original String: " + myString);
		System.out.println("First half: " + halfOfString(myString, 0) + "\nSecond half: " + halfOfString(myString, 1));
	}

	public int getKey(String s) {
		// This method should call countLetters to get an array of the letter frequencies in String s 
		// and then use maxIndex to calculate the index of the largest letter frequency, which is the 
		// location of the encrypted letter ‘e’, which leads to the key, which is returned.		
		int[] letterFrequency = countOccurences(s);
		int maxIndex = maxIndex(letterFrequency);
		
		int decryptionKey = maxIndex - 4;
		if (maxIndex < 4 ) {
			decryptionKey = 26 - ( 4 - maxIndex);
		}
		
		return decryptionKey;
	}
	
	public String decryptTwoKeys(String encrytedString) {
		// This method attempts to determine the two keys used to encrypt the message, 
		// prints the two keys, and then returns the decrypted String with those two keys. 
		
		String firstHalfString = halfOfString(encrytedString, 0);
		int firstKey = getKey(firstHalfString);
		String secondHalfString = halfOfString(encrytedString, 1);
		int secondKey = getKey(secondHalfString);
	
		System.out.println("First Key: " + firstKey + "\nSecond Key: " + secondKey);

		CaesarCipher caesarCipher = new CaesarCipher();
		String decryptedString = caesarCipher.encryptTwoKeys(encrytedString, 26 - firstKey, 26 - secondKey);
		return decryptedString;
	}
	
	public void testDecryptTwoKeys() {
 		CaesarCipher caesarCipher = new CaesarCipher();
		String encryptedWithTwoKeysString = caesarCipher.encryptTwoKeys("Just a test string with lots of eeeeeeeeeeeeeeeees", 2, 20);
		
		System.out.println("Just a test string with lots of eeeeeeeeeeeeeeeees");
		System.out.println ( decryptTwoKeys(encryptedWithTwoKeysString));
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StatisticalDecryption xDecryption = new StatisticalDecryption();
		// xDecryption.testDecrypt();
		// xDecryption.testHalfOfString();
		xDecryption.testDecryptTwoKeys();
	}

}
