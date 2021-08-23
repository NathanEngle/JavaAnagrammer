/*
Nathan Engle
8/23/2021
Problem Description: Write a program that can be executed from a command window or terminal which accepts a word and prints all possible combinations of letters.
	Allow the program to filter results through a dictionary file to display only valid words
*/

package anagrammer;

import java.util.List;

public interface AnagramEvaluator {
	/**
	* Returns a list of words derived from the supplied anagram.
	*
	* @param anagram the anagram to evaluate.
	* @param option the command line option. Values are “nf” or “words”. If null,
	* default to “words”.
	* @return the list of words derived from the supplied anagram.
	*/
	List<String> evaluate(String anagram, String option);
}
