/*
Nathan Engle
8/23/2021
Problem Description: Write a program that can be executed from a command window or terminal which accepts a word and prints all possible combinations of letters.
	Allow the program to filter results through a dictionary file to display only valid words
*/

package anagrammer;

import java.util.Set;

public interface AnagramDataReader {
	/**
	* Returns a Set containing all words read from the anagram data text file.
	*
	* @return the Set or empty Set if no words are found.
	*/
	Set<String> readData();
}
