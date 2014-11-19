package com.al.markovchain;

import junit.framework.TestCase;

public class TestMarkovChain extends TestCase{
	
	MarkovChain mc = MarkovChain.getInstance();
	public void testUploadFile()
	{
		mc.selectTeacher(MarkovChain.SHAKESPEARE);
		mc.dump();
		mc.selectTeacher(MarkovChain.PUSHKIN);
		mc.dump();
		mc.selectTeacher(MarkovChain.TAGORE);
		mc.dump();
		mc.selectTeacher(MarkovChain.WHITMAN);
		mc.dump();
	}
	
	public void testWritePoem()
	{
		System.out.println(mc.makePoem("you", "me"));
		System.out.println(mc.makePoem("I", "you"));
	}

}
