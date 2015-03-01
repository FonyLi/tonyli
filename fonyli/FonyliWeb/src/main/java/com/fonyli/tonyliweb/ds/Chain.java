package com.fonyli.tonyliweb.ds;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Chain {
	
	private Map<String, Set<String>> chain = new HashMap<String, Set<String>>();
	
	private static final int MAX_OUTPUT = 1000;
	
	public void clearChain()
	{
		chain.clear();
	}
	
	/**
	 * generate a poem stat with begin and end with end.
	 * if the whole length is larger than MAX_OUTPUT, just stop and return.
	 * @param begin
	 * @param end
	 * @return
	 */
	public String generate(String begin, String end)
	{
		//check input para's availability
		if(begin == null)
			return null;
		
		StringBuffer sb = new StringBuffer();
		
		int wordCount = 0;
		
		if(!chain.containsKey(begin))
			return null;
		
		if(begin.equalsIgnoreCase(end))
			return begin;
		
		//set the previous and current
		String previous = begin;
		
		//for the first word, get the next
		String current = getNext(previous);
		
		if(current == null)
			return null;		
		
		String preAndCurrent = previous + " " + current + "";
		
		sb.append(preAndCurrent + " ");
		
		int lastCharIndex = -1;
		
		boolean isFinish = false;
		
		//for the rest, get the next of "previous current" randomly.
		while(current != null && !current.equalsIgnoreCase(end) && wordCount < MAX_OUTPUT)
		{
			previous = current;
			current = getNext(preAndCurrent);
			
			if(current == null)
				break;
			
			preAndCurrent = previous + " " + current;

			sb.append(current);
			
			if(current.equals(end))
			{
				isFinish = true;
				break;
			}
			
			
			//deal with punctuation specially
			//if there is a punctuation, end this line
			lastCharIndex = current.length() - 1;
			if(current.charAt(lastCharIndex) >= '!' && current.charAt(lastCharIndex) <= '?')
				sb.append("<br />");
			else
				sb.append(" ");
			
			wordCount++;
		}
		
		if(!isFinish)
			sb.append(end);
				
		return sb.toString();
	}
	
	/**
	 * get next of current or "previous current"
	 * @param current
	 * @return
	 */
	private String getNext(String current)
	{
		if(!chain.containsKey(current))
			return null;
		
		Set<String> nextOptions = chain.get(current);
		
		if(nextOptions == null || nextOptions.size() == 0)
			return null;
		
		Object[] nextArray = nextOptions.toArray();
		
		return (String)(nextArray[(int)(Math.random() * nextArray.length)]); //get one from nextArray randomly
	}
	
	/**
	 * for internal debug use.
	 * dump all the mapping rules of getting the next.
	 */
	public void dumpRules()
	{
		if(chain == null)
			return;
		
		Iterator it = chain.keySet().iterator();
		Iterator subIt = null;
		
		String key = null;
		while(it.hasNext())
		{
			key = (String)it.next();
			System.out.print(key + " --> ");
			
			subIt = (chain.get(key)).iterator();
			
			while(subIt.hasNext())
				System.out.print(subIt.next() + ", ");
			
			System.out.println();
		}
	}
	
	/**
	 * The function des two things.
	 * 1 add next to current's next set.
	 * 2 add next to "previous current"'s next set if previous is not null
	 * @param previous
	 * @param current
	 * @param next
	 */
	public void add(String previous, String current, String next)
	{
		//assume the input is "aaa bbb ccc aaa ddd"
		// the map is :
		// aaa -> {bbb, ddd}
		// bbb -> {ccc}
		// aaa bbb -> {ccc}
		// bbb ccc -> {ddd}
		// ccc -> {ddd}
		// ccc aaa -> {ddd}
		// ddd -> {null}
		
		//it means the key is not just a string of "XXX XXX"(two words connected by one space),
		//but also a single word
		//because user will input one single word(but not two) as the prefix
		
		//one is nextSet of current
		addOneWord(current, next);
		
		//it is not the first time. add suffix to the nextSet of "previous current"
		if(previous != null)
		{
			String preAndCurrent = previous + " " + current;
			
			addOneWord(preAndCurrent, next);
		}
	}
	
	private void addOneWord(String current, String next)
	{
		Set<String> currentSet = null;
		
		if(chain.containsKey(current))
			currentSet = chain.get(current);
		else
		{
			currentSet = new HashSet<String>();
			chain.put(current, currentSet);
		}
		
		currentSet.add(next);
	}
	
}
