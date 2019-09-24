package com.sgokhan;

import java.util.ArrayList;
import java.util.List;

public class TestMain {

	public static void main(String[] args) {

		List<String> words = new ArrayList<String>();
		
		
		words.add("1|AAAAAAA|1|33.5");
		words.add("15|BBB|22222|45645");
		words.add("452|||");
		
		//Organizer a= new Organizer(words);
		
		String str="15|BBB|22222|45645\r\n" + 
				"asdasdasd|asdasd\r\n" + 
				"asdasdsd|sada|asdasda";
		
		
		Organizer c= new Organizer();

		c.setBETWEEN_TWO_WORD_SPACE(2);
		c.setCOMPLETE_WITH_CHAR(' ');
		c.setPRINTING_DELIMITER("||");
		c.setPREPARE_DELIMITER("|");
		
		System.out.println(c.toString(str));
		
				
	}

}
