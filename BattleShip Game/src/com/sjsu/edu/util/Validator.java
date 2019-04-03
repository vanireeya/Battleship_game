package com.sjsu.edu.util;

public class Validator {
	
	public static boolean validateChoice(String c) {
		return c.equals("1") || c.equals("2");
	}
	
	public static boolean validateGridSize(String s) {
		try { 
	      int val = Integer.parseInt(s);
	      return val >= 5;
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	}
	
	public static boolean isValidIntegerInput(String input) {
		  try { 
			  Integer.parseInt(input);
			  return true;
		    } catch(NumberFormatException e) { 
		        return false; 
		    } catch(NullPointerException e) {
		        return false;
		    }
	}
}
