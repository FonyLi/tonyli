package com.tjli.test.algorithm;

public class LongState {

	public boolean isConfusing() {
		   try {
		     return true;
		   } finally {
		     return false;
		   }
		}
	
	public static void main(String[] args) {
		LongState longState = new LongState(null);
		System.out.println(longState.isConfusing());
		
		String a = "hehe";
		String b = "food".substring(0,3);
		
		System.out.println("A".hashCode());
		System.out.println("B".hashCode());
		System.out.println("C".hashCode());
		System.out.println("AA".hashCode());
		System.out.println("AB".hashCode());
		System.out.println("AC".hashCode());
		System.out.println("ACfdsafdsfsaferwfdsfsfdsfsf".hashCode());
	}
	
	public LongState(State state)
	{
		if(state == null)
			return;
		
		this.state = state.toLong();
		
		this.previousStepIndex = state.getPreviousStepIndex();
		this.previousMove = state.getPreviousMove();
	}
	
	private long state = 0;
	private int previousStepIndex = 0;
	
	private Moves previousMove = null;
	
	public int getPreviousStepIndex() {
		return previousStepIndex;
	}

	public void setPreviousStepIndex(int previousStepIndex) {
		this.previousStepIndex = previousStepIndex;
	}

	public long getState() {
		return state;
	}

	public void setState(long state) {
		this.state = state;
	}

	public Moves getPreviousMove() {
		return previousMove;
	}

	public void setPreviousMove(Moves previousMove) {
		this.previousMove = previousMove;
	}
	
	
}
