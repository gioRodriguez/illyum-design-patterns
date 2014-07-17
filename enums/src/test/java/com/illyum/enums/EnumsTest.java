package com.illyum.enums;

import org.junit.Test;

/**
 * The following code shows you an example of how to use the Java's enum in an
 * effective way, so please refer to Joshua Bloch Effective Java c. 6 p. 147 - 158
 */
public class EnumsTest {

	// ----- Int enum pattern - severely deficient! -----

	public static final int APPLE_FUJI = 0;
	public static final int APPLE_PIPPIN = 1;
	public static final int APPLE_GRANNY_SMITH = 2;

	public static final int ORANGE_NAVEL = 0;
	public static final int ORANGE_TEMPLE = 1;
	public static final int ORANGE_BLOOD = 2;

	private int citrusFlavored(
			int apple, // apple value
			int orange, // orange value
			int appleMix // apple value
	) {
		return 1;
	}

	@Test
	public void testIntEnumPattern() {
		// arrange

		// act
		citrusFlavored(
				APPLE_FUJI,
				APPLE_GRANNY_SMITH,
				APPLE_PIPPIN
		);

		// assert
	}

	// The compiler won't complain if you pass an apple to a method that expects
	// an orange :(
	
	// Luckily, as release 1.5, the Java language provides an alternative that
	// avoids the shortcomings of the int and string enum patterns and provide
	// many added benefits. It is the enum types
	
	public enum Apple {
		FUJI,
		PIPPIN,
		GRANNY_SMITH
	};

	public enum Orange {
		NAVEL,
		TEMPLE,
		BLOOD
	};

	private int citrusFlavored(
			Apple apple,
			Orange orange,
			Apple appleMix
	) {
		return 1;
	}

	@Test
	public void testWithEnums() {
		// arrange
		
		// act
		citrusFlavored(
				Apple.FUJI,
				Orange.BLOOD, // enums provide compile-time safety
				Apple.PIPPIN
		);

		// assert
	}

	// Java's enums types are full-fledged classes, this allow to the enums
	// evolve into a full-featured abstraction
	
	/**
	 * Enum type wit data and behavior
	 */
	public enum Planet {
		MERCURY	(3.302e+23, 2.439e6),
		VENUS		(4.869e+24, 6.052e6),
		EARH		(5.975e+24, 6.378e6),
		MARS		(6.419e+23, 3.393e6),
		JUPITER	(1.899e+27, 7.149e7),
		SATURN 	(5.685e+26, 6.027e7),
		URANUS	(8.683e+25, 2.556e7),
		NEPTUNE (1.024e+26, 2.477e7);
		
		private final double _mass;						// in kilograms
		private final double _radius;					// in meters
		private final double _surfaceGravity;	// in m / s^2
		
		// Universal gravitational constant in m^3 / kg s^2
	 	private static final double G = 6.67300E-11;
		
		private Planet(double mass, double radius) {
			_mass = mass;
			_radius = radius;
			_surfaceGravity = G * mass / (radius * radius);
		}
		
		public double mass(){ return _mass; }
		public double radius(){ return _radius; }
		public double surfaceGravity(){ return _surfaceGravity; }
		
		public double surfaceWeight(double mass){
			return mass * _surfaceGravity;
		}
	}
	
	@Test
	public void weightTable() throws Exception {
		// arrange
		double earthWeight = 90;
		double mass = earthWeight / Planet.EARH.surfaceGravity();
		
		// act
		// print a table of the object's weight on all the planets
		System.out.println("\nweightTable\n");
		for (Planet planet : Planet.values()) {
			System.out.printf("Weight on %s is %f%n", planet, planet.surfaceWeight(mass));
		}
		
		// assert
	}
	
	// While the Planet enum is simple, it is surprisingly powerful
	// The techniques demonstrated in the Planet example are sufficient for most enum types,
	// but some times you need more. Sometimes you need to associate fundamentally
	// different behavior with each constant, For example, suppose you are writing an enum type
	// to represent the operations an a basic four-function calculator and you want to
	// provide a method to perform the arithmetic operation represented by each constant
	
	/**
	 * Enum type that switches on its own value - questionable
	 */
	public enum Operation_switch{
		PLUS, MINUS, TIMES, DIVIDE;
		
		// Do the arithmetic op represented by this constant
		double apply(double x, double y){
			switch(this){
				case PLUS: return x + y;
				case MINUS: return x - y;
				case TIMES: return x * y;
				case DIVIDE: return x / y;
			}
			
			throw new AssertionError("Unknown op: " + this);
		}
	}
	
	// This code works, but is isn't very pretty. It won't compile without the throw statement.
	// Worse, the code is fragile. If you add a new enum constant but forget to add a corresponding
	// case to the switch, the enum will still compile, but it will fail at runtime when you try
	// to apply the new operation
	
	// Luckily, there is a better way to associate a different behavior with each
	// enum constant
	
	/**
	 * Enum type with constant-specific method implementation
	 */
	public enum Operation_specific_method{
		PLUS 		{ double apply(double x, double y) {return x + y; } },
		MINUS 	{ double apply(double x, double y) {return x - y; } },
		TIMES 	{ double apply(double x, double y) {return x * y; } },
		DIVIDE 	{ double apply(double x, double y) {return x / y; } };
		
		abstract double apply(double x, double y);
	}
	
	// In the unlikely event that you forget add the apply method, the compiler
	// will remind you
	
	// Constant-specific method implementations can be combined with 
	// constant-specific data
	
	/**
	 * Enum type with constant-specific class bodies and data
	 */
	public enum Operation{
		PLUS("+") 		
			{ double apply(double x, double y) {return x + y; } },
		MINUS("-") 	
			{ double apply(double x, double y) {return x - y; } },
		TIMES("*") 	
			{ double apply(double x, double y) {return x * y; } },
		DIVIDE("/") 	
			{ double apply(double x, double y) {return x / y; } };
		
		private final String _symbol;
		private Operation(String symbol) {
			_symbol = symbol;
		}
		
		@Override
		public String toString() { return _symbol; }
		
		abstract double apply(double x, double y);
	}
	
	@Test
	public void arithmeticExpressions() throws Exception {
		// arrange
		double x = 10;
		double y = 2;
		
		// act
		System.out.println("\narithmeticExpressions\n");
		for (Operation op : Operation.values()) {
			System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
		}
		
		// assert
	}
	
	// A disadvantage of constant-specific method implementation is that they make
	// harder to share code among enum constants. For example, consider an enum
	// representing the days of the week in a payroll package. This enum has a method
	// that calculate a worker's pay for a that day given the worker's base salary (per hour)
	// and the number of hours worked on that day.
	// On the five weekdays, any time worked in excess of a normal shift generate overtime pay;
	// on the two weekend days, all work generates overtime pay
	// For brevity's sale, the code in this example uses double, but note that double
	// is not an appropriate data type for a payroll application
	
	/**
	 * Enum that switches on its value to share code - questionable
	 */
	public enum PayrollDay_switch {
		MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;
		
		private static final int HOURS_PER_SHIFT = 8;
		
		double pay(double hoursWorked, double payRate){
			double basePay = hoursWorked * payRate;
			
			double overtimePay; // Calculate overtime pay
			switch (this) {
				case SATURDAY: case SUNDAY:
					overtimePay = hoursWorked * payRate / 2;
					break;
				default: // Weekdays
					overtimePay = hoursWorked <= HOURS_PER_SHIFT ?
							0 : (hoursWorked - HOURS_PER_SHIFT) * payRate / 2;
			}
			
			return basePay + overtimePay;
		}
	}
	
	// This code is undeniably concise, but it is dangerous from a maintenance perspective.
	// Suppose you add an element to the enum, perhaps a special value to represent a vacation
	// day, but forget to add a corresponding case to the switch statement. The program still
	// compile, but the pay method sill silently pay the worker the same amount for a vacation
	// day as for an ordinary weekday.
	
	// What you really want is to be forced to choose an overtime pay strategy each time you
	// add an enum constant. Luckily, there is a nice way to achieve this. The idea is to move the
	// overtime pay computation into a private nested enum, and to pass an instance of this strategy
	// enum, to the constructor for the PayrollDay enum
	
	/**
	 * Strategy enum pattern
	 */
	public enum PayrollDay {
		MONDAY		(PayType.WEEKDAY),
		TUESDAY		(PayType.WEEKDAY),
		WEDNESDAY	(PayType.WEEKDAY),
		THURSDAY	(PayType.WEEKDAY),
		FRIDAY		(PayType.WEEKDAY),
		
		SATURDAY	(PayType.WEEKEND),
		SUNDAY		(PayType.WEEKEND);
		
		private final PayType _payType;
		private PayrollDay(PayType payType) {
			_payType = payType;
		}
		
		double pay(double hoursWorked, double payRate){
			return _payType.pay(hoursWorked, payRate);
		}
		
		/**
		 * The strategy enum type
		 */
		private enum PayType {
			WEEKDAY {
				@Override
				double overtimePay( double hrs, double payRate) {
					return hrs <= HOURS_PER_SHIFT ? 0 :
						(hrs - HOURS_PER_SHIFT) * payRate / 2;
				}
			},
			WEEKEND {
				@Override
				double overtimePay( double hrs, double payRate) {
					return hrs * payRate / 2;
				}
			};
			
			private static final int HOURS_PER_SHIFT = 8;
			
			abstract double overtimePay(double hrs, double payRate);
			
			double pay(double hoursWorked, double payRate){
				double basePay = hoursWorked * payRate;
				return basePay + overtimePay(hoursWorked, payRate);
			}
		}
	}
	
	// While this pattern is less concise than the switch statement, it is
	// safer and more flexible.
	
	
	// So when should you use enums? Anytime you need a fixed set of constants. Also
	// includes other sets for which you know all the possible values at compile time,
	// such as choices on a menu, operation code, and command line flags.
	// In summary, the advantages of enum types over int constants are compelling.
}
