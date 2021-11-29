package com.example.demo;

public class SearchHelper {
	
	public static int getSearchParameters(String title, int year) {
		if ( !searchTitle(title) && !searchYear(year) ) {
			return 0;
		} 
		else if (!searchTitle(title) && searchYear(year)) {
			return 1;
		}
		else if (searchTitle(title) && !searchYear(year)) {
			return 2;
		}
		else {
			return 3;
		}
	}
	
	public static boolean searchTitle(String title) {
		if (title.equals("null")) {
			return false;
		} else {
			return true;
		}
	}
	
	public static boolean searchYear(int year) {
		if (year == 0 ) {
			return false;
		} else {
			return true;
		}
	}
}
