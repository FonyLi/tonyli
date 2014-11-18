package com.al.markovchain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.StreamTokenizer;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.al.markovchain.ds.Chain;

public class MarkovChain {
	private static final Logger LOGGER = Logger.getLogger(MarkovChain.class);
	
	private Chain chain = new Chain();
	
	static
	{
		//load log4j config
		PropertyConfigurator.configure("config/log4j.properties");
	}
	
	public void clearChain()
	{
		chain.clearChain();
	}
	
	public String transform(File file, String prefix, String suffix)
	{
		try
		{
			StreamTokenizer	st = new StreamTokenizer(new BufferedReader(new FileReader(file)));
			
			//ignore quotation marks
			st.ordinaryChar('\'');
			st.ordinaryChar('\"');
			
			//include all punctuation marks and numbers
			st.wordChars('!', '?');

			String previous = null;
			String current = null;
			String next = null;
						
			while(st.nextToken() != StreamTokenizer.TT_EOF)
			{
				if(st.sval == null)
				{
					continue;
				}				
				
				previous = current;
				current = next;

				
				next = st.sval.toLowerCase();
				
				if(current == null) //reading the first word, do not do anything
				{
					continue;
				}

				chain.add(previous, current, next);	
			}
			
			//chain.dumpRules();
			return chain.generate(prefix, suffix);
		}
		catch(Exception e)
		{
			LOGGER.error("", e);
		}
		
		return null;
	}
	
	public static void main(String[] argv)
	{
		
		MarkovChain markovChain = new MarkovChain();
		
		File file = new File("data/words.txt");
		
		String result = markovChain.transform(file, "you", "me");
		
		System.out.println(result);
	}

}
