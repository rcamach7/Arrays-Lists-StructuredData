import edu.duke.FileResource;

public class App {

	public static void main(String[] args) {
		VigenereBreaker vigenereB = new VigenereBreaker();
		
		// Break using tryKeyLength (Knowing the length of they key)
//		vigenereB.breakVigenere();
		
		// Breaking by knowing the language (also we iterate over X amount of key lenghts
		vigenereB.breakVigenereUsingLanguage();
		
	}

}
