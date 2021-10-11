package GladLibsArray.data;
import java.util.*;

import edu.duke.FileResource;
import edu.duke.URLResource;

public class GladLib {
	private ArrayList<String> adjectiveList;
	private ArrayList<String> nounList;
	private ArrayList<String> colorList;
	private ArrayList<String> countryList;
	private ArrayList<String> nameList;
	private ArrayList<String> animalList;
	private ArrayList<String> timeList;
	private ArrayList<String> verbList;
	private ArrayList<String> fruitList;
	
	private ArrayList<String> usedWordsList;
	private Random myRandom;
	private static String dataSourceDirectory = "data";
	
	public GladLib(){
		initializeFromSource(dataSourceDirectory);
		myRandom = new Random();
	}
	
	public GladLib(String source){
		initializeFromSource(source);
		myRandom = new Random();
	}
	
	private void initializeFromSource(String source) {
		adjectiveList= readIt(source + "/adjective.txt");	
		nounList = readIt(source + "/noun.txt");
		colorList = readIt(source + "/color.txt");
		countryList = readIt(source + "/country.txt");
		nameList = readIt(source + "/name.txt");		
		animalList = readIt(source + "/animal.txt");
		timeList = readIt(source + "/timeframe.txt");	
		verbList = readIt(source + "/verb.txt");
		fruitList = readIt(source + "/fruit.txt");
		usedWordsList = new ArrayList<String>();
	}
	
	private String randomFrom(ArrayList<String> source){
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}
	
	private String getSubstitute(String label) {
		if (label.equals("country")) {
			return randomFrom(countryList);
		}
		if (label.equals("color")){
			return randomFrom(colorList);
		}
		if (label.equals("noun")){
			return randomFrom(nounList);
		}
		if (label.equals("name")){
			return randomFrom(nameList);
		}
		if (label.equals("adjective")){
			return randomFrom(adjectiveList);
		}
		if (label.equals("animal")){
			return randomFrom(animalList);
		}
		if (label.equals("timeframe")){
			return randomFrom(timeList);
		}
		if (label.equals("verb")){
			return randomFrom(verbList);
		}
		if (label.equals("fruit")){
			return randomFrom(fruitList);
		}		
		if (label.equals("number")){
			return ""+myRandom.nextInt(50)+5;
		}
		return "**UNKNOWN**";
	}
	
	private String processWord(String word){
		int first = word.indexOf("<");
		int last = word.indexOf(">",first);
		if (first == -1 || last == -1){
			return word;
		}
		String prefix = word.substring(0,first);
		String suffix = word.substring(last+1);
				
		boolean repeatFound= true;
		String substituteWord = "";
		while (repeatFound) {
			substituteWord = getSubstitute(word.substring(first+1,last));
			if (!usedWordsList.contains(substituteWord)) {
				usedWordsList.add(substituteWord);
				repeatFound = false;
			}
		}		
		return prefix + substituteWord + suffix;
	}
	
	private void printOut(String fullStory, int lineWidth){
		int charsWritten = 0;
		for(String w : fullStory.split("\\s+")){
			if (charsWritten + w.length() > lineWidth){
				System.out.println();
				charsWritten = 0;
			}
			System.out.print(w+" ");
			charsWritten += w.length() + 1;
		}
		System.out.println("_"+ usedWordsList.size() + " words were replaced._");
	}
	
	private String fromTemplate(String source){
		String story = "";
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		return story;
	}
	
	private ArrayList<String> readIt(String source){
		ArrayList<String> list = new ArrayList<String>();
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		return list;
	}
	
	public void makeStory(){
		usedWordsList.clear();
	    System.out.println("\n");
		String story = fromTemplate("data/madtemplate2.txt");
		printOut(story, 75);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GladLib app = new GladLib();
		app.makeStory();
	}

}
