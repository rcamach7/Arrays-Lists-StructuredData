import java.util.*;

import edu.duke.FileResource;

public class VigenereBreaker {
	
    public String sliceString(String message, int whichSlice, int totalSlices) {
    	StringBuilder slicedString = new StringBuilder();
    	for (int i = whichSlice; i < message.length(); i+= totalSlices) {
    		slicedString.append(message.charAt(i));
    	}
        return slicedString.toString();
    }
    
    public int[] tryKeyLength(String encrypted, int keyLength, char mostCommon) {
        int[] key = new int[keyLength];
        
        // Break down the encrypted message into the parts that get encrypted individually and add them to an array
        ArrayList<String> brokenDownEncryptionMessage = new ArrayList<String>();
        for (int i = 0; i < keyLength; i++) {
        	String currentPart = sliceString(encrypted, i, keyLength);
        	brokenDownEncryptionMessage.add(currentPart);
        }
        
        // Obtain the key for each part of the message previously broken by using the CaesarBreaker class
        // then passing that particular section of the encrypted message to it
        CaesarCracker caesarC = new CaesarCracker(mostCommon);
        for (int i = 0; i < brokenDownEncryptionMessage.size(); i++) {
        	int currentKey = caesarC.getKey(brokenDownEncryptionMessage.get(i));
        	key[i] = currentKey;
        }
        return key;
    }
    
    public void breakVigenere () {
    	FileResource file = new FileResource("VigenereTestData/athens_keyflute.txt");
    	String encryptedMessage = file.asString();
    	int[] keys = tryKeyLength(encryptedMessage, 5, 'e');
    	
    	VigenereCipher vigenereC = new VigenereCipher(keys);
    	String decryptedMessage = vigenereC.decrypt(encryptedMessage);
    	System.out.println(decryptedMessage);
    }
    
}
