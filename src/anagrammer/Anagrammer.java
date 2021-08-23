/*
Nathan Engle
8/23/2021
Problem Description: Write a program that can be executed from a command window or terminal which accepts a word and prints all possible combinations of letters.
	Allow the program to filter results through a dictionary file to display only valid words
*/

package anagrammer;

import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Anagrammer {
	public static void main(String[] args) {
		//create all options		
		Option noFilter = new Option("nf", "filter-words", false, "output all anagram values with no filter");
		Option filterWords = new Option("words", "filter-words", false, "output only values that are known words");
		Option help = new Option("h", "help", false, "displays the help for this program");
		
		//anagram option has an argument, so Option.builder is used
		Option anagram = Option.builder("a")
				.longOpt("anagram")
				.desc("supplies the anagram to evaluate")
				.hasArg()
				.argName("word")
				.build();
		
		//add all created options to new Options object
		Options options = new Options();
		options.addOption(noFilter);
		options.addOption(filterWords);
		options.addOption(help);
		options.addOption(anagram);

		CommandLineParser parser = new DefaultParser();
		try {
			CommandLine line = parser.parse(options, args);
			//display help
			if(line.hasOption("h")) {
				HelpFormatter formatter = new HelpFormatter();
				formatter.printHelp("anagrammer", options);
			}
			
			//if anagram option chosen, go on to determine filter option
			else if(line.hasOption("a")) {
				String word = line.getOptionValue("a"); //pass cli argument to variable to be passed to the evaluator method
				List<String> listedAnagrams; //create list to hold found anagrams returned from eval method
				
				if(line.hasOption("nf")) {
					listedAnagrams = new AnagramEvaluatorImpl().evaluate(word, "nf");
				}
				
				//words is the default filter, so it is not checked and simply assumed
				else {
					listedAnagrams = new AnagramEvaluatorImpl().evaluate(word, "words");
				}
				//print anagram list and number of anagrams found
				for(String anag : listedAnagrams) {
					System.out.println(anag);
				}
				System.out.println("-- " + listedAnagrams.size() + " value(s) found");
			}
			//if anagram option is omitted and user is not asking for help, display the following message
			else {
				System.out.println("Missing required option: -a");
			}
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		
	}
}
