import edu.duke.FileResource;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// You must give key and message to generate a Caesar encryptes message. 
		// FileResource file = new FileResource("VigenereTestData/athens_keyflute.txt");
		
//		CaesarCipher caesar = new CaesarCipher(10);
//		System.out.println(caesar.encrypt(file.asString()));
		
		// CaesarCracker assumes the most common letter in the given statement
		// is 'e' (since its most common in english in genearl) or you can pass
		// onto it the most common letter based on your analysis of the message given. 
//		CaesarCracker caesarC = new CaesarCracker('t');
//		System.out.println(caesarC.decrypt(message));
		
		// Takes a array of keys and uses those to encrypt
//		int[] keys = {1, 2, 5};
//		VigenereCipher vigenere = new VigenereCipher(keys);
//		System.out.println( vigenere.encrypt(message) );
		
		VigenereBreaker vigenereB = new VigenereBreaker();
		vigenereB.breakVigenere();
	
	}

}
