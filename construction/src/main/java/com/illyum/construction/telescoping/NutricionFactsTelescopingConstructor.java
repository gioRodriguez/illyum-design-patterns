package com.illyum.construction.telescoping;

public class NutricionFactsTelescopingConstructor {
	private final int _servingSize;	// (mL)							required
	private final int _servings;		// (per container)	required
	private final int _calories;		//									optional
	private final int _fat;					// (g)							optional
	private final int _sodium;			// (mg)							optional
	private final int _carbohydrate;// (g)							optional

	public NutricionFactsTelescopingConstructor(
			int servingSize,
			int servings
	) {
		this(servingSize, servings, 0);
	}
	
	public NutricionFactsTelescopingConstructor(
			int servingSize,
			int servings,
			int calories
	) {
		this(servingSize, servings, calories, 0);
	}
	
	public NutricionFactsTelescopingConstructor(
			int servingSize,
			int servings,
			int calories,
			int fat
	) {
		this(servingSize, servings, calories, fat, 0);
	}
	
	public NutricionFactsTelescopingConstructor(
			int servingSize,
			int servings,
			int calories,
			int fat,
			int sodium
	) {
		this(servingSize, servings, calories, fat, sodium, 0);
	}
	
	public NutricionFactsTelescopingConstructor(
			int servingSize,
			int servings,
			int calories,
			int fat,
			int sodium,
			int carbohydrate
	) {
		_servingSize = servingSize;
		_servings = servings;
		_calories = calories;
		_fat = fat;
		_sodium = sodium;
		_carbohydrate = carbohydrate;
	}

	public int getServingSize() {
		return _servingSize;
	}

	public int getServings() {
		return _servings;
	}

	public int getCalories() {
		return _calories;
	}

	public int getFat() {
		return _fat;
	}

	public int getSodium() {
		return _sodium;
	}

	public int getCarbohydrate() {
		return _carbohydrate;
	}

}
