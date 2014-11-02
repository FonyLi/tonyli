package com.tjli.test.threadold;

public class TestThread2 {

	public static void main(String[] args) {
		
		new Thread(new Runner2()).start();
		
		Runner2 runner = new Runner2();
		runner.start();
	}

}
