package com.shashank.stringcalculator;

import static org.junit.Assert.*; 

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestCalculator1 {
	private StringCalculator calculator;
	@Before
	public void init() {
		calculator = new StringCalculator();
	}

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Test
    public void testNegativeNumber() throws Exception{
    	try {
			calculator.calculate("-1,2");
		}
		catch (IllegalArgumentException e){
			assertEquals(e.getMessage(), "Negatives not allowed: -1");
		}
	}
	
	@Test
	public void testmultipleNegetive() throws Exception{

		try {
			calculator.calculate("2,-4,3,-5");
		}
		catch (IllegalArgumentException e){
			assertEquals(e.getMessage(), "Negatives not allowed: -4,-5");
		}
    }
	
	@Test
	public void emptystringreturnzero() throws Exception {
		assertEquals(calculator.calculate(""), 0);
	}
	@Test
	public void singlevaluereplied() throws Exception {
		assertEquals(calculator.calculate("1"), 1);
	}
	@Test
	public void twonumbercommaDelimitedretrunsum() throws Exception {
		assertEquals(calculator.calculate("1,2"), 3);
	}
	@Test
    public void multiplenumberReturnsum() throws Exception{
    	assertEquals(calculator.calculate("1\n2,3"), 6);
    }
	@Test
	public void twonumberNewLineDelimitedretrunsum() throws Exception {
		assertEquals(calculator.calculate("1\n2"), 3);
	}

	@Test
	public void ignoreGreaterthan1000() throws Exception {
		assertEquals(calculator.calculate("10,10,1001"), 20);
	}
	@Test
	public void ignoreDelimiters() throws Exception{
		assertEquals(calculator.calculate("//;\n1;2"), 3);
	}
	@Test
	public void ignoreDifferentDelimiters() throws Exception{
		assertEquals(calculator.calculate("//[***]\n1***2***3"), 6);
	}
   @Test
   public void ignoreMultipleDelimiters() throws Exception{
    	assertEquals(calculator.calculate("//[*][%]\n1*2%3"), 6);
    }
   @Test
   public void ignoreMultipleDelimiterwithlengthlongerthan1char() throws Exception{
	   assertEquals(calculator.calculate("//[**][%%]\n1**2%%3"), 6);
   }
   @Test
   public void countAddInvoked() throws Exception{
	   calculator.calculate("1\n2,3"); 
	   assertEquals(calculator.getAddCount(), 3);
   }
}
