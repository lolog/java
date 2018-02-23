package net.asserts;

import org.junit.Test;

/**
 * java -ea App, VM options
 * @author lolog
 */
public class App 
{
	
	@Test
	public void testAssertTrue() {
		assert true;
		System.out.println("True");
	}
	
	@Test
	public void testAssertFalse() {
		assert false;
		System.out.println("False");
	}
}
