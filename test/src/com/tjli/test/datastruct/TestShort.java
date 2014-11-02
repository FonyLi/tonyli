package com.tjli.test.datastruct;

public class TestShort {

	public static boolean aa() {
		return true;
	}

	public static void main(String[] args) {
		
		long longValue = 8;
		int intValue = 8;
		long l2 = longValue << 32;
		System.out.println(l2);
		
		long l3 = ((long)intValue) << 32;
		
		System.out.println(l3);
		
		// short a = 1;
		// a = a + 1;

		// short a = 1;
		// a += 1;
		//
		// int bb = 0;
		//
		// int x = 5;
		// int y = 4;
		// y = x++;
		//
		// System.out.println(y);
		//
		// y = x;
		//
		// System.out.println(y);
		//
		// boolean flag; int i=0;
		// do {
		// flag = false;
		//
		// System.out.println( i++ );
		//
		// flag = i < 10;
		//
		// int aaaa = 0;
		//
		// if (flag)
		// {
		// aa ();
		// continue;
		// }
		// else
		// {
		// ;
		// }
		//
		//
		// } while ( aa() ? true:false );

		int i = 1, j = -1;
		switch ((short) i) {

		case 0:
		case 1:
			j = 1;

		case 2:
			j = 2;

		default:
			j = 0;
		}
		System.out.println("j= " + j);

	}

}
