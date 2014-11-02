package com.tjli.test.algorithm;


public class State {
	
	public static final int breadth = 4;
	private static final int totalCount = breadth * breadth;
	
	private int[][] state = new int[breadth][breadth];

	private int previousStepIndex = 0;
	
	private Moves previousMove = null;
	
	public int getPreviousStepIndex() {
		return previousStepIndex;
	}

	public void setPreviousStepIndex(int previousStepIndex) {
		this.previousStepIndex = previousStepIndex;
	}
	
	public State()
	{
		for (int i = 0; i < breadth; i++)
		{
			for (int j = 0; j < breadth; j++)
			{
				state[i][j] = j * breadth + i;
				
			}
		}
	}
	
	public State(LongState longState)
	{
		init(longState.getState());
		
		previousStepIndex = longState.getPreviousStepIndex();
		
		previousMove = longState.getPreviousMove();
	}
	
	public State(long longValue)
	{
		init(longValue);
	}
	
	private void init(long longValue)
	{
		for (int j = 0; j < breadth; j++)
		{
			for (int i = 0; i < breadth; i++)
			{			
				state[i][j] = (int)(longValue >> ((j * breadth + i) * 4)) & 0xF; //only get 4 bits
			}
		}
	}
	
	/**
	 * @return a long value stands for the state.
	 */
	public long toLong()
	{
		long longValue = 0;
		
		for (int j = 0; j < breadth; j++)
		{
			for (int i = 0; i < breadth; i++)
			{		
//				System.out.println("before " + longValue);
				longValue |= ((long)(state[i][j])) << ((j * breadth + i) * 4);
				
//				System.out.println("state[i][j] = " + state[i][j]);
//				System.out.println("(j * breadth + i) * 4 = " + (j * breadth + i) * 4);
//				
//				System.out.println("state[i][j] << ((j * breadth + i) * 4) = " + ((long)state[i][j] << ((j * breadth + i) * 4)));
//				System.out.println("after " + longValue);
//				
//				System.out.println();
			}
		}
		
		return longValue;
		
	}

	public void expectedState(int x1, int y1, int x2, int y2)
	{		
//		int swapDate = state[x1][y1];
//		
//		state[x1][y1] = state[x2][y2];
//		state[x2][y2] = swapDate;
//		state[0][0] = 12;
//		state[0][1] = 0;
//		state[0][2] = 4;
//		state[0][3] = 8;
				
		state[0][0] = 0;
		state[0][1] = 4;
		state[0][2] = 8;
		state[0][3] = 12;
		
		state[1][0] = 3;
		state[1][1] = 5;
		state[1][2] = 9;
		state[1][3] = 13;
		
		state[2][0] = 10;
		state[2][1] = 6;
		state[2][2] = 15;
		state[2][3] = 14;
		
		state[3][0] = 11;
		state[3][1] = 7;
		state[3][2] = 2;
		state[3][3] = 1;
		
	}
	
	/**
	 * check if otherState has the same state matrix with this
	 * @param otherState
	 * @return
	 */
	public boolean equal(State otherState)
	{
		int[][] other = otherState.state;
		if (other == null )
			return false;
		
		for (int i = 0; i < breadth; i++)
		{
			for (int j = 0; j < breadth; j++)
			{
				if (state[i][j] != other[i][j])
					return false;
			}
		}
		
		return true;
	}
	
	public void moveHorizontal(int x, int towards)
	{
		int[] tempHorizontalLine = new int[4];

		for (int i = 0; i < breadth; i++)
		{
			tempHorizontalLine[i] = state[x][i];
		}
		
		for (int i = 0; i < breadth; i++)
		{
			state[x][i] = tempHorizontalLine[(i - towards + breadth) % breadth]; //rotate the the value chain
		}
		
		previousMove = new Moves();
		
		previousMove.xy = "x " + x;
		previousMove.move = towards;
	}
	
	public void moveVertical(int y, int towards)
	{
		int[] tempVerticalLine = new int[4];

		for (int i = 0; i < breadth; i++)
		{
			tempVerticalLine[i] = state[i][y];
		}
		
		for (int i = 0; i < breadth; i++)
		{
			state[i][y] = tempVerticalLine[(i - towards + breadth) % breadth]; //rotate the the value chain
		}
		
		previousMove = new Moves();
		
		previousMove.xy = "y " + y;
		previousMove.move = towards;
	}
	
	/* (non-Javadoc)
	 * only copy the state but not previousMove
	 * @see java.lang.Object#clone()
	 */
	public State clone()
	{
		State otherState = new State();
		
		for (int i = 0; i < breadth; i++)
		{
			for (int j = 0; j < breadth; j++)
			{
				otherState.state[i][j] = this.state[i][j];
			}
		}
		
		return otherState;
	}
	
	public void print()
	{
		if (previousMove != null)
			System.out.println(previousMove.print());
		
		for (int j = 0; j < breadth; j++)
		{
			for (int i = 0; i < breadth; i++)
			{
				System.out.print(this.state[i][j]);
				
				if (this.state[i][j] < 10)
					System.out.print("  ");
				else
					System.out.print(" ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public Moves getPreviousMove() {
		return previousMove;
	}

	public void setPreviousMove(Moves previousMove) {
		this.previousMove = previousMove;
	}
	
	
}
