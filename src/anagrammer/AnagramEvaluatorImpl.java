/*
Nathan Engle
8/23/2021
Problem Description: Write a program that can be executed from a command window or terminal which accepts a word and prints all possible combinations of letters.
	Allow the program to filter results through a dictionary file to display only valid words
*/

package anagrammer;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AnagramEvaluatorImpl implements AnagramEvaluator {

	@Override
	public List<String> evaluate(String anagram, String option) {
		
		anagram = anagram.toUpperCase(); // convert original word to uppercase for comparing to the dictionary
		Set<String> anagrams = getPermutations(anagram);
		List<String> listedAnagrams = new ArrayList<>();
		
		if(option.equalsIgnoreCase("nf")) {
			for(String anag : anagrams) {
				listedAnagrams.add(anag);
			}
		}
		else if(option.equalsIgnoreCase("words")) {
			Set<String> words = new AnagramDataReaderImpl().readData(); //load dictionary
			for(String anag : anagrams) {
				if(words.contains(anag)) {
					listedAnagrams.add(anag);
				}
			}
		}
		
		listedAnagrams.sort(null); //sort the list alphabetically
		return listedAnagrams;
	}
	
	
	/*
	 * Credit to Pankaj of JournalDev for their method of recursively finding all permutations of a string
	 * See lines 12 - 30 in the following link:
	 * https://github.com/journaldev/journaldev/blob/master/CoreJavaProjects/String-Programs/src/main/java/com/journaldev/java/string/StringFindAllPermutations.java
	 * 
	 * This method pops off the first character of a string, saving it in a char variable. Then the substring is passed recursively to the getPermutations method.
	 * This repeats until the string is of length 0. The string is rearranged in the for loop at the end of the method.
	 * Permutations are stored in a HashSet to avoid duplicates.
	 */
	public Set<String> getPermutations(String orig) {
		Set<String> permutations = new HashSet<>();
		if(orig == null) {
			return null;
		}
		if(orig.length() == 0) {
			permutations.add("");
			return permutations;
		}
		char firstChar = orig.charAt(0); //save first character of string
		String remaining = orig.substring(1); //remove first character
		Set<String> permutationsOfRemaining = getPermutations(remaining); // find permutations of the new string with the first letter removed
		
		for(String s : permutationsOfRemaining) { //add permutations of substring to permutations of orig string
			for(int i = 0; i <= s.length(); i++) {
				String start = s.substring(0, i);
				String end = s.substring(i);
				String anagrammedString = start + firstChar + end;
				permutations.add(anagrammedString);
			}
		}
		return permutations;
	}

}
