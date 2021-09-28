import java.io.File;
import java.util.*;

import edu.duke.DirectoryResource;
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
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary, char mostCommonLetter) {

    	int[] wordCountTracker = new int[101];
    	for (int i = 1; i < 100; i++) {
    		VigenereCipher vigenereC = new VigenereCipher( tryKeyLength(encrypted, i, mostCommonLetter) );
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
    	VigenereCipher vigenereC = new VigenereCipher( tryKeyLength(encrypted, maxIndex, mostCommonLetter) );
    	return vigenereC.decrypt(encrypted);
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary) {
    	char answer = 'a';
    	
    	HashMap<Character, Integer> characterFrequency = new HashMap<Character, Integer>();
    	for (String word : dictionary) {
    		for (int i = 0; i < word.length(); i++) {
    			if (!characterFrequency.containsKey(word.charAt(i))) {
    				characterFrequency.put(word.charAt(i), 1);
    			} else {
    				characterFrequency.put(word.charAt(i), characterFrequency.get(word.charAt(i)) + 1);
    			}
    		}	
    	}
    	int max = Collections.max(characterFrequency.values());
    	for (Character c : characterFrequency.keySet()) {
    		if (characterFrequency.get(c) == max) {
    			answer = c;
    		}
    	}	
    	return answer;
    }
    
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
    	// Basically, we are already given languages set up. I need to iterate over each language, and use the count word
    	HashMap<String, Integer> languageWordCount = new HashMap<String, Integer>();
    	
    	for (String currentLanguage : languages.keySet()) {
    		char mostCommonChar = mostCommonCharIn(languages.get(currentLanguage));
    		String decrypted = breakForLanguage(encrypted, languages.get(currentLanguage), mostCommonChar);
    		
    		int validWords = countWords(decrypted, languages.get(currentLanguage));
    		languageWordCount.put(currentLanguage, validWords);	  		
    	}
    	// Prints out language and how many valid words existed in said language
    	for (String language : languageWordCount.keySet()) {
    		System.out.println(language + " : " + languageWordCount.get(language) + " valid words.");
    	}
    	// Prints out decryption using language with highest valid word count found.
    	int max = Collections.max(languageWordCount.values());
    	for (String language : languageWordCount.keySet()) {
    		if (languageWordCount.get(language) == max) {
    			char mostCommonChar = mostCommonCharIn(languages.get(language));
    			System.out.println(breakForLanguage(encrypted, languages.get(language), mostCommonChar) + "\n " + language);
    		}
    	}
    }
    
    public void testBreakForAllLangs() {
    	HashMap<String, HashSet<String>> languageAndDictionaries = new HashMap<String, HashSet<String>>();
    	
    	DirectoryResource resource = new DirectoryResource();
    	for (File f : resource.selectedFiles()) {
    		FileResource file = new FileResource(f);
    		String name = f.getName().substring(0, f.getName().length() - 4);
    		
    		HashSet<String> currentDictionary = readDictionary(file);
    		languageAndDictionaries.put(name, currentDictionary);
    		System.out.println("Done proccesing " + name + " dictionary.");
    	}
    	
    	FileResource messageFile = new FileResource("VigenereTestData/athens_keyflute.txt");
    	String message = messageFile.asString();
    	breakForAllLangs(message, languageAndDictionaries);
    }
    
    public void testMostCommonCharIn() {
    	FileResource dictionaryFile = new FileResource("dictionaries/Danish.txt");
    	HashSet<String> dictionary = readDictionary(dictionaryFile);    	
    	
    	char mostCommon = mostCommonCharIn(dictionary);
    	System.out.println(mostCommon);
    }
    
    public void breakVigenere () {
    	FileResource resource = new FileResource("messages/secretmessage2.txt");
    	String battleMessage = resource.asString();
    	
    	FileResource dictionaryFile = new FileResource("dictionaries/English.txt");
    	HashSet<String> dictionary = readDictionary(dictionaryFile);    	
    	
    	int[] foundKeys = tryKeyLength(battleMessage, 38, 'e');
    	VigenereCipher vc = new VigenereCipher(foundKeys);
    	String battleMessageDecrypt = vc.decrypt(battleMessage);
    	
    	
    	
    	int ans = countWords(battleMessageDecrypt, dictionary);
    	//System.out.println(battleMessageDecrypt);
    	System.out.println(ans);
    	
    }
 
}