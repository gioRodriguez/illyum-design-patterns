package com.illyum.money;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

/**
 * The following code shows you an examples of how to do monetery calculations in the Java in an
 * effective way, so please refer to 
 * Joshua Bloch. Effective Java. Avoid float and double if exact answers are required c. 8 p. 224 - 227
 */
public class MoneyTest {

	// float and double do not provide exact results and should not be used where
	// exact results are required.
	
	// The float and double types are particularly illsuited for monetary calculations
	// because it is impossible to represent 0.1 (or any other negative power of ten)
	// as a float or double exactly.
	
	@Test
	public void minusWithDoubleTest() {
		// arrange		
		// Suppose that you have $1.03 in your pocket, 
		double currentAmount = 1.03;
		
		// and you spent 42 cents. How much money do you have left?
		// 1.03 - .42 = 0.61
		double expectedAmount = 0.61;
				
		
		// act		
		double actualAmount  = currentAmount - .42;
		
		System.out.println("\nminusTest");
		System.out.println(actualAmount); 
		
		// assert
		assertTrue(actualAmount == expectedAmount); // this must be true but is it not :(
	}

	// You might think that the problem could be solved merely by rounding results prior
	// to printing, but unfortunately this does not always work.
	
	// Suppose that you hace a dollar un your pocket, and you see a shelf wit a row of delicious
	// candies priced at 10c, 20c, 30c, and so forth, up to a dollar. You buy one of each candy on
	// the shelf. How many candies do you buy, and how much change do you get?

	@Test
	public void buyCandiesWithDoubleTest(){
		// arrange
		double founds = 1.00;
		int itemsBought = 0;
		
		// act
		for(double price = .10; founds >= price; price += .10){
			founds -= price;
			itemsBought++;
		}
		
		System.out.println("\nbuyCandiesWithDoubleTest");
		System.out.println(itemsBought + " items bought.");
		System.out.println("Change: $" + founds);
		
		// assert
		assertTrue(4 == itemsBought); // wrong answer :(
		assertTrue(0 == founds); // wrong answer :(
	}
	
	// The right way to solve this problem is to use BogDecimal, int or long for monetary calculations
	
	@Test
	public void buyCandiesWithBigDecimalTest(){
		// arrange
		final BigDecimal TENT_CENTS = new BigDecimal(".10");
		
		BigDecimal founds = new BigDecimal("1.00");
		int itemsBought = 0;
		
		// act
		for(BigDecimal price = TENT_CENTS; founds.compareTo(price) >= 0; price = price.add(TENT_CENTS)){
			founds = founds.subtract(price);
			itemsBought++;			
		}
		
		System.out.println("\nbuyCandiesWithBigDecimalTest");
		System.out.println(itemsBought + " items bought.");
		System.out.println("Change: $" + founds);
		
		// assert
		assertTrue(4 == itemsBought); // correct answer :)
		assertTrue(0 == founds.intValue()); // correct answer :)
	}
	
	// The are, however, two disadvantages to using BigDecimal: it's less convenient that using a primitive
	// arithmetic type, and it's slower.
	
	// an alternative to using BigDecimal is to use int o long, depending on the amount involved,
	// and keep track of the decimal point yourself
	
	// Do all computation in cents instead of dollars.
	
	@Test
	public void buyCandiesWithIntUsingCentsTest(){
		// arrange
		int founds = 100;
		int itemsBought = 0;
		
		// act
		for(int price = 10; founds >= price; price += 10){
			founds -= price;
			itemsBought++;
		}
		
		System.out.println("\nbuyCandiesWithIntUsingCentsTest");
		System.out.println(itemsBought + " items bought.");
		System.out.println("Money left over: " + founds + " cents");
		
		// assert
		assertTrue(4 == itemsBought); // correct answer :)
		assertTrue(0 == founds); // correct answer :)
	}
	
	
	// Don't use float or double for any calculations that require an exact answer. Use BigDecimal if you
	// what the system to keep track of the decimal point also give you a full control over rounding.
	
	// If performance is the essence you don't mind keeping track of the decimal point yourself, and
	// the quantities aren't too big, use int or long. If the quantities don't exceed nime decimal digits,
	// you can use int; if they don't exceed eighteen digits you can use long. If the quantities might
	// exceed eighteen digits, you must use BigDecimal.
	
	
	// Under some circumstances maybe could be worthwhile create your own Money type
	
	@Test
	public void buyCandiesWithMoneyTest(){
		// arrange
		final Money TENT_CENTS = new Money.Amount().cents(10).build();
		
		Money founds = new Money.Amount().dollars(1).build();
		int itemsBought = 0;
		
		// act
		for(Money price = TENT_CENTS; founds.compareTo(price) >= 0; price = price.add(TENT_CENTS)){
			founds = founds.sub(price);
			itemsBought++;			
		}
		
		System.out.println("\nbuyCandiesWithMoneyTest");
		System.out.println(itemsBought + " items bought.");
		System.out.println("Change: " + founds);
		
		// assert
		assertTrue(4 == itemsBought); // correct answer :)
		assertTrue(0 == founds.asCents()); // correct answer :)
	}
}








