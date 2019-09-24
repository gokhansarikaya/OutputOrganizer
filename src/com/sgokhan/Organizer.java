package com.sgokhan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Organizer {
	
	private static String str="";
	//private static List<String> words = new ArrayList<String>();
	private  List<Integer> residue = new ArrayList<Integer>();
	private  int SPACE_BETWEEN_TWO_WORDS = 1;
	private  String PRINTING_DELIMITER = "";
	private  String PREPARE_DELIMITER = "|";
	private  char COMPLETE_WITH_CHAR = ' ';
	
	
	public  void setBETWEEN_TWO_WORD_SPACE (int BETWEEN_TWO_WORD_SPACE) {
		if (BETWEEN_TWO_WORD_SPACE >=0 ) {
			this.SPACE_BETWEEN_TWO_WORDS=BETWEEN_TWO_WORD_SPACE;
		}
	}
	
	public  void setPRINTING_DELIMITER (String PRINTING_DELIMITER) {
		if (PRINTING_DELIMITER !=null) {
			this.PRINTING_DELIMITER=PRINTING_DELIMITER;
		}
	}
	
	public  void setPREPARE_DELIMITER (String PREPARE_DELIMITER) {
		if (PREPARE_DELIMITER !=null) {
			this.PREPARE_DELIMITER=PREPARE_DELIMITER;
		}
	}
	
	public  void setCOMPLETE_WITH_CHAR (char COMPLETE_WITH_CHAR) {
		if ( String.valueOf(COMPLETE_WITH_CHAR).length() == 1 ) {
			this.COMPLETE_WITH_CHAR=COMPLETE_WITH_CHAR;
		}
	}
	
	public String toString(List<String> input) {
		calculateresidue(input);
		printschema(input);
		return str;
	}
	
	public String toString(String input) {
		List<String> items = Arrays.asList(input.split("\\s*\n\\s*"));
		calculateresidue(items);
		printschema(items);
		return str;
	}
	
	private  void printschema(List<String> words) {
		for (int i = 0; i < words.size(); i++) {

			int startpoint = 0;
			boolean onlyone=true;
			for (int j = 0; j < residue.size(); j++) {

					int endpoint = words.get(i).indexOf(PREPARE_DELIMITER, startpoint);
					if (endpoint != -1) {
						str = str + words.get(i).substring(startpoint, endpoint);
						completewithspace(i,j,startpoint,endpoint,words);
						startpoint = endpoint + 1;
	
					} else if(onlyone) {
						str = str + words.get(i).substring(startpoint, words.get(i).length());
						onlyone=false;
					}
			}
			str = str + "\n";
		}
	}
	
	private  void completewithspace(int i,int j, int start, int end,List<String> input) {
		for (int k = 0; k < residue.get(j) - input.get(i).substring(start, end).length() + SPACE_BETWEEN_TWO_WORDS; k++) {
			str = str + COMPLETE_WITH_CHAR;
		}
		str = str + PRINTING_DELIMITER;
	}
	

	private void calculateresidue(List<String> wordarray) {
		// For each element in array
		for (int i = 0; i < wordarray.size(); i++) {

			int previndex = 0;
			int delimited = 0;

			// for each delimited part in element-------
			while (wordarray.get(i).indexOf(PREPARE_DELIMITER, previndex) != -1) {

				if (residue.size() == delimited) {
					residue.add(0);
				}

				if (wordarray.get(i).indexOf(PREPARE_DELIMITER, previndex) - previndex > residue.get(delimited)) {
					residue.set(delimited, wordarray.get(i).indexOf(PREPARE_DELIMITER, previndex) - previndex);
				}

				previndex = wordarray.get(i).indexOf(PREPARE_DELIMITER, previndex) + 1;
				delimited++;
			}
			// just for last delimiter in element
			if (residue.size() == delimited) {
				residue.add(0);
			}
			if ((wordarray.get(i).length() - previndex) > residue.get(delimited)) {
				residue.set(delimited, wordarray.get(i).length() - previndex);
			}
		}
	}

}

