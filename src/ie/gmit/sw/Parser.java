package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;


public class Parser {

	private static final String IGNORE_FILE_NAME = "ignorewords.txt";
	
	private static Scanner scan = new Scanner(System.in);
	
	private Set<String> ignoreSet = new HashSet<>();
	private Map<String, Integer> map = new HashMap<>();
	private Queue<Word> wordQ = new PriorityQueue<>();

	public static String getIgnoreFileName() {
		return IGNORE_FILE_NAME;
	}
	
	public Queue<Word> getWordQueue() {
		return wordQ;
	}
	
	public int numUniqueWordsRead() {
		return map.size();
	}
	
	// Add each word in the ignorewords.txt file (if it exists) to a Set (Use a set since there's no need for duplicates).
	// Hash tables (Map or Set) store objects at arbitrary locations and offer an average O(1) for insertion.
	public void parseIgnoreFile() {
		try {
			BufferedReader ignoreFile = new BufferedReader(new FileReader(IGNORE_FILE_NAME));
			String next = null;

			while ((next = ignoreFile.readLine()) != null) {

				// skip blank lines
				if (next.isEmpty())
					continue;

				ignoreSet.add(next.toLowerCase()); // Adding to a HashSet is O(1)
			}
			ignoreFile.close();
		} catch (IOException e) {
			// no ignorewords.txt file found
			System.out.printf("No Ignore file was found. Proceed anyway <Y for Yes / N for No> ? ");

			if (Character.toUpperCase(scan.next().charAt(0)) != 'Y')
				System.exit(0);
		}
	}
	
	public void parseInput(InputStream string) throws Exception {

		BufferedReader inputFile = new BufferedReader(new InputStreamReader(string));
		String next = null;

		// Runs in O(n^2)
		while ((next = inputFile.readLine()) != null) {

			// Add every word in the line to an array, using spaces as delimiters.
			String[] words = next.toLowerCase().split(" ");
			processWords(words);
		}
		//Sort Map
		sortMap();
		//Close Stream
		string.close();
	}
	
	public void parseURL(String url) throws Exception {

		
	 URL u = new URL(url);
	 BufferedReader in = new BufferedReader(new InputStreamReader(u.openStream()));
		String next = null;
		
		// Runs in O(n^2)
	 while ((next = in.readLine()) != null) {

		 // Add every word in the line to an array, using spaces as delimiters.
		 String[] words = next.toLowerCase().split(" ");
		 processWords(words);
		}
	    //Sorts Map
		sortMap();
		
			
	}
	
	
	private void processWords(String[] words) {
		for (String word : words) {
			word = removeUneeded(word);
			// Searching a HashSet is O(1).
			if (!ignoreSet.contains(word)) { 
				// Only add words that aren't in the IgnoreFile Set					
				if (!word.isEmpty())
					updateMap(word);
			}
		}
	}
	//Remove All Un-needed Punctuation
	private String removeUneeded(String word) {		
		
		return word.replaceAll("(?:(?<!\\S)\\p{Punct}+)|(?:\\p{Punct}+(?!\\S))", "");
	}

	// HashMap implementation provides constant-time performance for the get and put.
	private void updateMap(String word) {
		int freq;
		
		// If the word already exists in the Map, its frequency is incremented.
		if (map.containsKey(word)) { // calling containsKey() is O(1)
			freq = map.get(word); // get the current frequency of the word
			map.put(word, ++freq);
		}
		else // If it's the first occurrence of this word. Set the frequency to 1.
			map.put(word, 1); 
	}
	
	// Add each word in the Map to a PriorityQueue. 
	// Words are sorted each time a new element is added based on their frequencies
	//by using the compareTo() method defined in Word.
	private void sortMap() {
		Set<String> keys = map.keySet(); // Add each key contained in the Map to a Set. O(1)
	
		// Iterate over all the keysets , instantiate a new Word object, and add it into a PriorityQueue. 
		// Adding to a PriorityQueue has a time complexity of O(log n) 
		for(String key : keys)
			wordQ.offer(new Word(key, map.get(key)));
	}
}
