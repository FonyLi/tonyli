package com.tjli.test.algorithm;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class RoomEscape {
	
	/**
	 * one OneLayerState is the states set that come from the original position by moving the same some step.
	 * @author tjli
	 *
	 */
	
	private class OneLayerState
	{
		public ArrayList<LongState> states = new ArrayList<LongState>();
	}
	
	//all possible steps.
	private ArrayList<OneLayerState> states = new ArrayList<OneLayerState>();
	
	private Set<Long> longStates = new HashSet<Long>();
	
	private static int currentStep = 0;
	
	private static int sameCount = 0;
	
	private static boolean canFind = true;
	
	public RoomEscape()
	{
	}
	
	public static void main(String[] args) {
		RoomEscape escape = new RoomEscape();
		
		try
		{
			
//			File file = new File("e:/test.txt");
//			
//			if (!file.exists())
//				file.createNewFile();
			
			PrintStream out = new PrintStream("e:/test.txt");
		    System.setOut(out);
	    
		    escape.changePosition(0, 0, 0, 3);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void changePosition(int x1, int y1, int x2, int y2)
	{
		OneLayerState oneLayerState = new OneLayerState();
		oneLayerState.states.add(new LongState(new State()));
		
		//push the original state into states.
		states.add(oneLayerState);
		
		State state = new State();
		
		System.out.println("original matrix");
		
		state.print();
		
		state.expectedState(x1, y1, x2, y2);
		
		System.out.println("expected matrix");
		
		state.print();
		
		int MAX_STEP = 20;
		
		
		boolean isGetRoute = false;

		System.out.println("currentStep " + currentStep);
		
		while(currentStep++ < MAX_STEP)
		{
			System.out.println("this  layer state number " + states.get(states.size() - 1).states.size());
			System.out.println("sameCount " + sameCount);
			
			System.out.println();
			
			sameCount = 0;
			
			isGetRoute = getNextLayerStates(state);
			
			//if already found the espected state or can't find it.
			if (isGetRoute || !canFind)
			{
				break;
			}
			
			//actually, it is next layer
			System.out.println("currentStep " + currentStep);
			
			//printCurrentLayer();
		}
		
		if (isGetRoute)
			printRoute();
	}
	
	/**
	 * @param 	expectedState
	 * @return 	true if already found the expectedState
	 *  		false means didn't find
	 */
	private boolean getNextLayerStates(State expectedState)
	{
		OneLayerState lastLayerState = states.get(states.size() - 1);// get the last states array
		
		OneLayerState newLayerState = new OneLayerState();
		
		for (int i = 0; i < lastLayerState.states.size(); i++)
		{
			for (int j = 0; j < State.breadth; j++)
			{
				for (int k = 1; k < State.breadth; k++) //k = 0 means don't move at all, which doesn't make sense.
				{
					//get last state
					State newState = new State(lastLayerState.states.get(i).getState());
					
					//move it horizontally to the new state
					newState.moveHorizontal(j, k);
					
					//record the previous state index
					newState.setPreviousStepIndex(i);
					
//					//debug
//					if (i % 100000 == 0 && i != 0)
//					{
//						System.out.println("i " + i);
//						newState.print();
//					}
					
					if (ifExist(newState))
					{
						//count same count.
						sameCount++;

						continue;
					}
						
					
					newLayerState.states.add(new LongState(newState));
					
					if (newState.equal(expectedState))
					{
						states.add(newLayerState);
						return true; // find it
					}
				}
			}
		}
		
		for (int i = 0; i < lastLayerState.states.size(); i++)
		{
			for (int j = 0; j < State.breadth; j++)
			{
				for (int k = 1; k < State.breadth; k++) //k = 0 means don't move at all, which doesn't make sense.
				{
					//get last state
					State newState = new State(lastLayerState.states.get(i).getState());
					
					//move it vertically to the new state
					newState.moveVertical(j, k);
					
					//record the previous state index
					newState.setPreviousStepIndex(i);
					
					//debug
//					if (i % 100000 == 0 && i != 0)
//					{
//						System.out.println("i " + i);
//						newState.print();
//					}
					
					if (ifExist(newState))
					{
						sameCount++;

						continue;
					}
					
					newLayerState.states.add(new LongState(newState));

					if (newState.equal(expectedState))
					{
						states.add(newLayerState);
						return true; // find it
					}
				}
			}
		}
		
		if (newLayerState.states == null || newLayerState.states.size() == 0)
		{
			System.out.println("can't find.");
			canFind = false;
		}
		
		states.add(newLayerState);
		
		return false;
	}
	
	/**
	 * if the state already exists in the states.
	 * @param newState
	 * @return
	 */
	private boolean ifExist(State newState)
	{
//		long longState = newState.getLong();
//		if(longStates.contains(newState.getLong()))
//		{
//			return true;
//		}
		
		
		
//		for (int i = states.size() - 1; i >= 0 ; i--)
//		{
//			OneLayerState oneLayerState = states.get(i);
//			
//			for (int j = 0; j < oneLayerState.states.size(); j++)
//			{
//				if (newState.equal(oneLayerState.states.get(j)))
//						return true;
//			}
//		}
		
		//return false;
		
		return !longStates.add(newState.toLong());
	}
	
	private void printRoute()
	{
		ArrayList<State> realStates = new ArrayList<State>();
		
		int thisLayer = states.size() - 1;
		
		OneLayerState lastLayer = states.get(thisLayer);
		
		State lastState = new State(lastLayer.states.get(lastLayer.states.size() - 1));
		
		int previousIndex = 0;
		
		while(true)
		{
			thisLayer--;
			realStates.add(lastState);
			
			previousIndex = lastState.getPreviousStepIndex();
			
			if (thisLayer >= 0)
			{
				//get previous layer 
				
				lastLayer = states.get(thisLayer);
				
				//get previous state
				lastState = new State(lastLayer.states.get(previousIndex));
			}
			else
				break;
			
		}
		
		Collections.reverse(realStates);
		
		for(int i = 0; i < realStates.size(); i++)
		{
			realStates.get(i).print();
		}
	}
	
	private void printCurrentLayer()
	{
		ArrayList<LongState> realStates = states.get(states.size() - 1).states;
		
		for(int i = 0; i < realStates.size(); i++)
		{
			new State(realStates.get(i)).print();
		}
	}

}
