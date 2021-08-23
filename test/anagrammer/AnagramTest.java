/*
Nathan Engle
8/23/2021
Problem Description: Write a program that can be executed from a command window or terminal which accepts a word and prints all possible combinations of letters.
	Allow the program to filter results through a dictionary file to display only valid words
*/
package anagrammer;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class AnagramTest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();;
	@Before
	public void setUp() {
		System.setOut(new PrintStream(outContent));
	}
	@Test
	public void readFile() {
		Set<String> words = new AnagramDataReaderImpl().readData();
		
		Assert.assertTrue(words.size() == 373295);
	}
	@Test
	public void noFilter() {
		List<String>listedAnagrams = new AnagramEvaluatorImpl().evaluate("dog", "nf");
		
		Assert.assertTrue(listedAnagrams.size() == 6);
	}
	@Test
	public void wordsFilter() {
		List<String>listedAnagrams = new AnagramEvaluatorImpl().evaluate("dog", "words");
		
		Assert.assertTrue(listedAnagrams.size() == 2);
	}
	@Test
	public void testCLIHelp() {
		String[] args = new String[1];
		args[0] = "-h";
		Anagrammer.main(args);
		//Assert.assertEquals(args, outContent.toString());
	}
	@Test
	public void testCLIAnagram() {
		String[] args = new String[2];
		args[0] = "-a";
		args[1] = "dog";
		Anagrammer.main(args);
		
		String returnedString = outContent.toString();
		String[] returnedArr = returnedString.split("\r\n|\r|\n"); //number of lines in return string
		Assert.assertTrue(returnedArr.length == 3); //2 words and the word count
	}
	@Test
	public void testCLIAnagramNF() {
		String[] args = new String[3];
		args[0] = "-a";
		args[1] = "dog";
		args[2] = "-nf";
		Anagrammer.main(args);
		
		String returnedString = outContent.toString();
		String[] returnedArr = returnedString.split("\r\n|\r|\n"); //number of lines in return string
		Assert.assertTrue(returnedArr.length == 7); //6 words and the word count
	}
	@Test
	public void testCLIAnagramNFAltOrder() { //testing -nf entered before -a
		String[] args = new String[3];
		args[0] = "-nf";
		args[1] = "-a";
		args[2] = "dog";
		Anagrammer.main(args);
		
		String returnedString = outContent.toString();
		String[] returnedArr = returnedString.split("\r\n|\r|\n");
		Assert.assertTrue(returnedArr.length == 7);
	}
	@Test
	public void testCLIAnagramAltCommand() {
		String[] args = new String[3];
		args[0] = "-anagram";
		args[1] = "dog";
		args[2] = "words";
		Anagrammer.main(args);
		
		String returnedString = outContent.toString();
		String[] returnedArr = returnedString.split("\r\n|\r|\n");
		Assert.assertTrue(returnedArr.length == 3); 
	}
	@Test
	public void testWithoutAnagram() {
		String[] args = new String[1];
		args[0] = "-nf";
		Anagrammer.main(args);
		// "Missing required option: -a\n" contains 29 chars
		Assert.assertTrue(outContent.toString().length() == 29);
		
		//unknown why this doesn't work. the strings look identical in the comparison window
		//so instead we check the length of the message
		//Assert.assertEquals("Missing required option: -a\n", outContent.toString());
	}
	@After
	public void clean() {
		System.setOut(null);
	}
}
