/*************************************************************************
 * Author: lily-tian 
 * Date: 20 Aug 2016
 * 
 * Compilation: javac GradeLevel.java 
 * Execution: java GradeLevel 
 * Dependencies: StdIn.java, StdOut.java, In.java, Out.java
 * 
 * Description: [...]
 * 
 *************************************************************************/

import java.util.HashMap;
import java.util.regex.*;

public class GradeLevel {

	public HashMap<String, Integer> wordrank;
	public HashMap<String, Integer> worduse;
	public int wc;
	public int totalwc;
	public int avgrank;
	public String common;
	public String rare;

	public GradeLevel(String s) {
		this.wordrank = new HashMap<String, Integer>();
		this.worduse = new HashMap<String, Integer>();
		s = s.toUpperCase().replaceAll("\\p{P}", "").replaceAll("\\s+", " ");
		String[] words = s.split(" ");
		allFreq();
		avgRank(words);
		avgUse(words);
	}

	// retrieves word frequency
	private void allFreq() {
		In in = new In("wordfreq.txt");
		while (!in.isEmpty()) {
			String s = in.readLine().replaceAll("\"", "");
			String[] wf = s.split(" ");
			this.wordrank.put(wf[0], Integer.parseInt(wf[3]));
			this.worduse.put(wf[0], (int) Double.parseDouble(wf[1]) / 1000);
		}
	}

	// returns average ranking of words
	private void avgRank(String[] words) {
		int wordcount = 0;
		int totalrank = 0;
		int rarerank = 0;
		int commonrank = 11000;
		for (int i = 0; i < words.length; i++) {
			if (this.wordrank.containsKey((words[i]))) {
				int rank = this.wordrank.get(words[i]);
				totalrank += rank;
				wordcount++;
				if (rank < commonrank) {
					commonrank = rank;
					this.common = words[i];
				}
				if (rank > rarerank) {
					rarerank = rank;
					this.rare = words[i];
				}
			}
			totalwc++;
		}
		this.wc = wordcount;
		int avgrank = totalrank / wordcount;
		String message = "The average word rank is: ";
		StdOut.println(message + avgrank);
	}

	// returns average usage of words
	private void avgUse(String[] words) {
		int wordcount = 0;
		int totaluse = 0;
		for (int i = 0; i < words.length; i++) {
			if (this.worduse.containsKey((words[i]))) {
				int rank = this.worduse.get(words[i]);
				totaluse += rank;
				wordcount++;
			}
			totalwc++;
		}
		this.wc = wordcount;
		int avguse = totaluse / wordcount;
		String message = "The average word usage is: ";
		StdOut.println(message + avguse);
	}

	public void wc() {
		System.out.println(wc + " words out of " + totalwc + " found.");
	}

	public void highlow() {
		String message1 = "The most common word is: \"";
		String message2 = "\". The most rare word is: \"";
		System.out.println(message1 + common + message2 + rare + "\".");
	}

	// writes out encrypted copy of file
	public static void main(String[] args) {

		// reads in file
		StdOut.print("Please enter name of file: ");
		In in = new In(StdIn.readString());
		String s = in.readAll();

		// gets grade level
		GradeLevel gl = new GradeLevel(s);

		// gives grade level
		gl.wc();
		gl.highlow();

	}
}
