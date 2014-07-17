package construction;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.illyum.construction.bean.NutricionFactsBean;
import com.illyum.construction.builder.NutricionFacts;
import com.illyum.construction.telescoping.NutricionFactsTelescopingConstructor;

/**
 * Examples taken from Java Effective 2nd Edition Joshua Bloch 
 * Chapter 2 Creating and Destroying Object p. 5 -16
 * 
 * Static factories and constructor share a limitation: they do not scale well
 * to large number of parameters, so the following patterns are options 
 * commonly used to face up whit this limitation
 */
public class NutricionFactsTest {
	private static final int CALORIES = 150;
	private static final int NULL_VALUE = 0;
	private static final int FAT = 3;
	private static final int SERVINGS = 2;
	private static final int SERVING_SIZE = 1;

	// ------------- telescoping constructor pattern - does not scale well! -----------------
	
	@Test
	public void telescopingWithServingSizeAndServings() {
		// arrange
		NutricionFactsTelescopingConstructor nutricionFacts = new NutricionFactsTelescopingConstructor(
				SERVING_SIZE, 
				SERVINGS
		);
		
		// act
		
		// assert
		assertEquals(SERVING_SIZE, nutricionFacts.getServingSize());
		assertEquals(SERVINGS, nutricionFacts.getServings());
	}
	
	@Test
	public void telescopingWithServingSize_Servings_FatAndNotCalories() {
		// arrange
		NutricionFactsTelescopingConstructor nutricionFacts = new NutricionFactsTelescopingConstructor(
				SERVING_SIZE, 
				SERVINGS,
				NULL_VALUE,
				FAT
		);
		
		// act
		
		// assert
		assertEquals(SERVING_SIZE, nutricionFacts.getServingSize());
		assertEquals(SERVINGS, nutricionFacts.getServings());
		assertEquals(NULL_VALUE, nutricionFacts.getCalories());
		assertEquals(FAT, nutricionFacts.getFat());
	}
	
	// the telescoping constructor pattern works, but it is hard to write
	// client code when there are many parameters, and harder still to read
	// With a few amount of parameters this may not seem so bad, but it 
	// quickly gets out of hand as the number of parameters increases
	
	
	
	// ------------- java bean pattern - allows inconsistency, mandates mutability ------------------
	
	@Test
	public void beanWithServingSizeAndServings() {
		// arrange
		NutricionFactsBean nutricionFacts = new NutricionFactsBean();
		
		// act
		nutricionFacts.setServingSize(SERVING_SIZE);
		nutricionFacts.setServings(SERVINGS);		
		
		// assert
		assertEquals(SERVING_SIZE, nutricionFacts.getServingSize());
		assertEquals(SERVINGS, nutricionFacts.getServings());
	}
	
	@Test
	public void beanWithServingSize_Servings_FatAndNotCalories() {
		// arrange
		NutricionFactsBean nutricionFacts = new NutricionFactsBean();
		
		// act
		nutricionFacts.setServingSize(SERVING_SIZE);
		nutricionFacts.setServings(SERVINGS);
		nutricionFacts.setFat(FAT);
		
		// assert
		assertEquals(SERVING_SIZE, nutricionFacts.getServingSize());
		assertEquals(SERVINGS, nutricionFacts.getServings());
		assertEquals(FAT, nutricionFacts.getFat());
	}
	
	// this pattern has none of the disadvantages of the telescoping constructor pattern
	// It is easy, if a bit wordy, to create instances, and easy to read the resulting code
	// 
	// Unfortunately, the JavaBeans pattern has serious disadvantages of its own. 
	//
	// Because construction is split across multiple calls, a JavaBean may be in an inconsistent state
	// partway through its construction.
	//
	// The JavaBeans pattern plecudes the possibility of making a class immutable, which is required
	// for guaranteed the constructions of side effects functions
	
	
	
	// ------------- builder pattern ----------------------
	// this third alternative combines the safety
	// of the telescoping constructor pattern with the readability of JabaBeans pattern

	@Test
	public void builderWithServingSizeAndServings() {
		// arrange
		NutricionFacts nutricionFacts = new NutricionFacts
				.Builder(SERVING_SIZE, SERVINGS)
				.build();
		
		// act
		
		// assert
		assertEquals(SERVING_SIZE, nutricionFacts.getServingSize());
		assertEquals(SERVINGS, nutricionFacts.getServings());
	}
	
	@Test
	public void builderWithServingSize_Servings_FatAndNotCalories() {
		// arrange
		NutricionFacts nutricionFacts = new NutricionFacts
				.Builder(SERVING_SIZE, SERVINGS)
				.fat(FAT)
				.calories(CALORIES)
				.build();
		
		// act
		
		// assert
		assertEquals(SERVING_SIZE, nutricionFacts.getServingSize());
		assertEquals(SERVINGS, nutricionFacts.getServings());
		assertEquals(FAT, nutricionFacts.getFat());
		assertEquals(CALORIES, nutricionFacts.getCalories());
	}
	
	// the client code is easy to write and, more importantly, to read!
	//
	// Although the implementation requires a bit of duplication 
	// the advantages gained are good enough for pay this trade of
	//
	// the builder pattern simulates optional parameters as found in Ada and Python
}
