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
    
    public HashSet<String> readDictionary(FileResource file) {
    	HashSet<String> dictionaryWords = new HashSet<String>();
    	for (String word : file.lines()) {
    		String x = word.toLowerCase();
    		if (!dictionaryWords.contains(x)) {
    			dictionaryWords.add(x);
    		}
    	}
    	return dictionaryWords;
    }
    
    public int countWords(String message, HashSet<String> dictionary) {
    	int count = 0;
    	
    	String y = message.toLowerCase();
    	String[] x = y.split("\\W+");
    	
    	for (int i = 0; i < x.length; i++) {
    		if (dictionary.contains(x[i]) && !x[i].isEmpty()) {
    			count += 1;
    		}
    	}
    	return count;
    }
 
    // This method should try all key lengths from 1 to 100 (use your tryKeyLength method to try one 
    // particular key length) to obtain the best decryption for each key length in that range. 
    // For each key length, your method should decrypt the message (using VigenereCipher’s decrypt method as before), 
    // and count how many of the “words” in it are real words in English, 
    // based on the dictionary passed in (use the countWords method you just wrote). 
    // This method should figure out which decryption gives the largest count of real words, 
    // and return that String decryption. Note that there is nothing special about 100; 
    // we will just give you messages with key lengths in the range 1–100. 
    // If you did not have this information, you could iterate all the way to encrypted.length(). 
    // Your program would just take a bit longer to run.
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {

    	int[] wordCountTracker = new int[101];
    	for (int i = 1; i < 100; i++) {
    		VigenereCipher vigenereC = new VigenereCipher( tryKeyLength(encrypted, i, 'e') );
    		String currentDecryption = vigenereC.decrypt(encrypted);
    		wordCountTracker[i] = countWords(currentDecryption, dictionary);
    	}
    	
    	// This will find the index of the biggest key length that found the most english words.
    	int maxIndex = 0;
    	int currentMax = 0;
    	for (int i = 1; i < wordCountTracker.length; i++) {
    		if (wordCountTracker[i] > currentMax) {
    			maxIndex = i;
    			currentMax = wordCountTracker[i];
    		}
    	}
    	VigenereCipher vigenereC = new VigenereCipher( tryKeyLength(encrypted, maxIndex, 'e') );
    	return vigenereC.decrypt(encrypted);
    }
    
    public void breakVigenere () {
    	FileResource resource = new FileResource("messages/secretmessage2.txt");
    	String battleMessage = resource.asString();
    	
    	FileResource dictionaryFile = new FileResource("dictionaries/English.txt");
    	HashSet<String> dictionary = readDictionary(dictionaryFile);    	
    	
    	// 5, 11, 20, 19, 4 are the keys for this message
    	int[] foundKeys = tryKeyLength(battleMessage, 38, 'e');
    	VigenereCipher vc = new VigenereCipher(foundKeys);
    	String battleMessageDecrypt = vc.decrypt(battleMessage);
    	
    	
    	
    	int ans = countWords(battleMessageDecrypt, dictionary);
    	//System.out.println(battleMessageDecrypt);
    	System.out.println(ans);
    	
    }
 
    public void breakVigenereUsingLanguage() {
    	FileResource messageFile = new FileResource("messages/secretmessage2.txt");
    	String message = messageFile.asString();
  
    	FileResource dictionaryFile = new FileResource("dictionaries/English.txt");
    	HashSet<String> dictionary = readDictionary(dictionaryFile);
    	
    	String decryptedMessage = breakForLanguage(message, dictionary);
    	System.out.println(decryptedMessage);
    	int ans = countWords(decryptedMessage, dictionary);
    	System.out.println(ans);

    	// 57
    	// 31658
    	// The Tragedy of Hamlet, Prince of Denmark
    	// 4485
    	
    }
}