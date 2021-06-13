package com.shashank.stringcalculator;

public class StringCalculator {
	
	private  String delimiters = null;
	private static int count = 0;
	
	public int calculate(String input) throws Exception {
		if(hasCustomDelimeter(input)) {
		input = getDelimeter(input);
		input = input.replace("\n", "");
		}else if(hasDelimeter(input)) {
			input = input.replace("//", "");
			delimiters=Character.toString(input.charAt(0));
			input = input.replace("\n", "");
		}
		else {
			input = input.replace("//", "");
			delimiters = ",|\n";
		}
		String[] numbers = input.split(delimiters);
		if(isEmpty(input)) {
			return 0;
		}if(input.length() == 1) {
			return stringToInt(input);
		}
		else {
		return add(numbers);
	}
	}


	private boolean hasDelimeter(String input) {
		return input.startsWith("//");
	}


	private String getDelimeter(String input) {
		while(input.contains("[")) {
			delimiters =findDelimeter(input);
			input = input.replaceFirst("\\[", "");
			input = input.replaceFirst("\\]", "");
			input = input.replace(delimiters, ",");
			delimiters = ",|\n";
			input = input.replace("//", "");
		}
		return input;
	}
	
	
	private int add(String[] numbers) throws Exception {
		return calculateValue(numbers);
	}
	private boolean hasCustomDelimeter(String input) {
		return input.startsWith("//")&&input.contains("[");
	}
	private String findDelimeter(String input) {
		int start = input.indexOf("[");
		int end = input.indexOf("]");
		String delimeter = input.substring(start+1, end);
		input = input.replace("[", "");
		input = input.replace("]", "");
		return delimeter;
	}
	private int calculateValue(String[] numbers) {
		count =0;
		int sum = 0;
		String negativeString = "";
		for(String current : numbers) {
			if(!(current.isEmpty())){
			if(stringToInt(current)<0) {
				if(negativeString.equals(""))
        			negativeString = current;
        		else
        			negativeString += ("," + current);
        	}
			
			if(stringToInt(current)>1000) {
				continue;
			}
			sum+=stringToInt(current);
			count++;
			}
		}
		if(!negativeString.equals("")){
			throw new IllegalArgumentException("Negatives not allowed: " + negativeString);
		}
		
		return sum;
	}
	public int getAddCount()  {
		return count;
	}
	
	private boolean isEmpty(String input) {
		return input.isEmpty();
	}
	
    
private int stringToInt(String input) {
	return Integer.parseInt(input);
}


}
