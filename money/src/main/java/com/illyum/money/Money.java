package com.illyum.money;


/**
 * This is only for demonstrative purposes not a production version
 */
public class Money implements Comparable<Money> {
	
	private final int _dollars;
	private final int _cents;	
	
	private final int _asCents;
	
	public int dollars(){
		return _dollars;
	}
	
	public int cents(){
		return _cents;
	}
	
	public int asCents(){
		return _asCents;
	}

	public Money add(
			Money money
	) {
		return new Amount()
			.dollars(_dollars + money._dollars)
			.cents(_cents + money._cents)
			.build();
	}
	
	public Money sub(
			Money money
	) {
		return new Amount()
		.dollars(_dollars - money._dollars)
		.cents(_cents - money._cents)
		.build();
	}
	
	public static class Amount{
		private int _dollars;
		private int _cents;
		
		public Amount() {			
		}
		
		public Amount dollars(int dollars){
			_dollars = dollars;			
			return this;
		}
		
		public Amount cents(int cents){
			_cents = cents;			
			return this;
		}
		
		public Money build(){
			return new Money(this);
		}
	}
	
	private Money(
		Amount amount
	) {
		_dollars = amount._dollars;
		_cents = amount._cents;
		
		// Sincerely as the dollar and cents amounts are immutable
		// we can calculate the as cents view
		_asCents = _dollars * 100 + _cents;
	}

	@Override
	public int compareTo(
			Money other
	) {
		// compare as cents
		if(asCents() < other.asCents()){
			return -1;
		}
		
		if(asCents() > other.asCents()){
			return 1;
		}
		
		return 0; // are equals
	}
	
	@Override
	public String toString() {		
		return String.format("$ %d", asCents());
	}	
}