package GladLibsArray;
import java.util.ArrayList;
import edu.duke.FileResource;

public class CharacterInPlay {

	private ArrayList<String> characters;
	private ArrayList<Integer> counts;
	
	public CharacterInPlay() {
		characters = new ArrayList<String>();
		counts = new ArrayList<Integer>();
	}
	
	public void update(String person) {
		// This method should update the two ArrayLists, adding the character’s name if it is not 
		// already there, and counting this line as one speaking part for this person.
		boolean uniqueCharacter = true;
		for (int i = 0; i < characters.size(); i++) {
			if (characters.get(i).equals(person)) {
				uniqueCharacter = false;
				counts.set(i, counts.get(i) + 1);
			}
		}
		if (uniqueCharacter) {
			characters.add(person);
			counts.add(1);
		}
	}
	
	public void findAllCharacters() {
		// Opens a file, and reads the file line-by-line. For each line, if there is a period on 
		// the line, extract the possible name of the speaking part, and call update to count it 
		// as an occurrence for this person. Make sure you clear the appropriate instance variables
		// before each new file.
		FileResource resource = new FileResource("/Users/rcamach7/Library/Mobile Documents/com~apple~CloudDocs/ATA Workspace/ArraysListsStructuredDataCourse/GladLibsArrayListsW2/src/data/likeit.txt");
		
		for (String line : resource.lines()) {
			if (line.contains(".")) {
				update(line.substring(0, line.indexOf('.') ));
			}
		}
	}
	
	public void charactersWithNumParts(int num1, int num2) {
		for (int i = 0; i < counts.size(); i++) {
			if (counts.get(i) > num1 &&  counts.get(i) < num2) {
				System.out.println(characters.get(i) + " " + counts.get(i));
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CharacterInPlay app = new CharacterInPlay();
		app.myTest();
	}

	public void myTest() {
		CharacterInPlay c = new CharacterInPlay();
		c.findAllCharacters();
		c.charactersWithNumParts(5, 100);
	}
}
