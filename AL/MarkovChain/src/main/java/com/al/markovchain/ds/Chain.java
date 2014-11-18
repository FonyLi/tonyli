package com.al.markovchain.ds;

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
	
	public String generate(String begin, String end)
	{
		if(begin == null)
			return null;
		
		StringBuffer sb = new StringBuffer();
		
		int wordCount = 0;
		
		if(!chain.containsKey(begin))
			return null;
		
		if(begin.equalsIgnoreCase(end))
			return begin;
		
		String previous = begin;
		String current = getNext(previous);
		
		if(current == null)
			return null;		
		
		String preAndCurrent = previous + " " + current + "";
		
		sb.append(preAndCurrent + " ");
		
		int lastCharIndex = -1;
		
		while(current != null && !current.equalsIgnoreCase(end) && wordCount < MAX_OUTPUT)
		{
			previous = current;
			current = getNext(preAndCurrent);
			
			preAndCurrent = previous + " " + current;

			sb.append(current);
			
			//deal with punctuation specially
			//if there is a punctuation, end this line			
			lastCharIndex = current.length() - 1;
			if(current.charAt(lastCharIndex) >= '!' && current.charAt(lastCharIndex) <= '?')
				sb.append("\n");
			else
				sb.append(" ");
			
			wordCount++;
		}
				
		return sb.toString();
	}
	
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
