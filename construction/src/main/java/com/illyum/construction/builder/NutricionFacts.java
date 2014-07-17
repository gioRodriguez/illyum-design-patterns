package com.illyum.construction.builder;

public class NutricionFacts {
	private final int _servingSize;
	private final int _servings;
	private final int _calories;
	private final int _fat;
	private final int _sodium;
	private final int _carbohydrate;

	public static class Builder {
		// required parameters
		private int _servingSize;
		private int _servings;

		// optional parameters - initialized to default values
		private int _calories;
		private int _fat;
		private int _sodium;
		private int _carbohydrate;

		public Builder(int servingSize, int servings) {
			_servingSize = servingSize;
			_servings = servings;
		}

		public Builder calories(int val) {
			_calories = val;
			return this;
		}

		public Builder fat(int val) {
			_fat = val;
			return this;
		}

		public Builder carbohydrate(int val) {
			_carbohydrate = val;
			return this;
		}

		public Builder sodium(int val) {
			_sodium = val;
			return this;
		}

		public NutricionFacts build() {
			return new NutricionFacts(this);
		}
	}

	private NutricionFacts(Builder builder) {
		_servingSize = builder._servingSize;
		_servings = builder._servings;
		_calories = builder._calories;
		_fat = builder._fat;
		_sodium = builder._sodium;
		_carbohydrate = builder._carbohydrate;
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
