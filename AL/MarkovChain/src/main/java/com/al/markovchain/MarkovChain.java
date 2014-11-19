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
	
	public static final String SHAKESPEARE = "William Shakespeare";
	public static final String PUSHKIN = "Aleksandr Pushkin";
	public static final String TAGORE = "Rabindranath Tagore";
	public static final String WHITMAN = "Walt Whitman";
	
	private static final String SHAKESPEARE_POEMS = "data/shakespeare.txt";
	private static final String PUSHKIN_POEMS = "data/pushkin.txt";
	private static final String TAGORE_POEMS = "data/tagore.txt";
	private static final String WHITMAN_POEMS = "data/whitman.txt";
	
	static
	{
		//load log4j config
		PropertyConfigurator.configure("config/log4j.properties");
	}
	
	private static MarkovChain instance = new MarkovChain();
	
	public static synchronized MarkovChain getInstance()
	{
		if(instance == null)
			instance = new MarkovChain();
		
		return instance;
	}
	
	public void clearChain()
	{
		chain.clearChain();
	}
	
	/**
	 * upload a file from local disk to create the "next" work set.
	 * @param file
	 */
	private void uploadFile(File file)
	{
		chain.clearChain();
		
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
			
		}
		catch(Exception e)
		{
			LOGGER.error("", e);
		}		
	}
	
	/**
	 * @param prefix
	 * @param suffix
	 * @return
	 */
	public String makePoem(String prefix, String suffix)
	{
		return chain.generate(prefix, suffix);
	}
	
	/**
	 * the whole process of uploading file and making poem.
	 * @param file
	 * @param prefix
	 * @param suffix
	 * @return
	 */
	public String transform(File file, String prefix, String suffix)
	{
		uploadFile(file);
		return makePoem(prefix, suffix);
	}
	
	/**
	 * upload a file from local disk according to the teacher's name user assigned.
	 * @param teacherName
	 */
	public void selectTeacher(String teacherName)
	{
		String poemWordsFileName = null;
		//TODO:
		//we make use a list to store all preloaded teachers.
		//but for current version. we just load them as new ones each time.
		switch(teacherName)
		{
		case SHAKESPEARE:
			poemWordsFileName = SHAKESPEARE_POEMS;
			break;
		case PUSHKIN:
			poemWordsFileName = PUSHKIN_POEMS;
			break;
		case TAGORE:
			poemWordsFileName = TAGORE_POEMS;
			break;
		case WHITMAN:
			poemWordsFileName = WHITMAN_POEMS;
			break;
		default:
			poemWordsFileName = SHAKESPEARE_POEMS;

		}
		
		uploadFile(new File(poemWordsFileName));		
	}
	
	/**
	 * for test
	 */
	public void dump()
	{
		if(chain != null)
			chain.dumpRules();
	}
	
	/**
	 * for tests
	 * @param argv
	 */
	public static void main(String[] argv)
	{
		MarkovChain markovChain = new MarkovChain();
		
		File file = new File("data/words.txt");
		
		String result = markovChain.transform(file, "you", "me");
		
		System.out.println(result);
	}

}
