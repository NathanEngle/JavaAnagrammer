/*
Nathan Engle
8/23/2021
Problem Description: Write a program that can be executed from a command window or terminal which accepts a word and prints all possible combinations of letters.
	Allow the program to filter results through a dictionary file to display only valid words
*/
package anagrammer;

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class AnagramDataReaderImpl implements AnagramDataReader {

	@Override
	public Set<String> readData() {
		Set<String> words = new HashSet<>();
		
		//old method. dictionary file was placed in the user directory and read from there
			//find file in user directory
				//File f = new File (System.getProperty("user.home") + File.separator + "anagram_data.txt" + File.separator);
		
		//dictionary file is now included in the resources folder within the Anagrammer project
		File f1 = new File("resources/anagram_data.txt");
		try (Scanner fileScanner = new Scanner(f1)){
			while(fileScanner.hasNextLine()) {
				words.add(fileScanner.nextLine());
			}
		} catch (Exception e) {
			System.out.println(e.getCause());
		}
		//return set regardless of reading success. empty or not
		return words;
	}

}
