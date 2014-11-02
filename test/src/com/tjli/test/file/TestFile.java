package com.tjli.test.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class TestFile {
	public static void main(String[] args) {
		
		try
		{
//			BufferedReader line = new BufferedReader(new InputStreamReader(System.in));
//			
//			String path = line.readLine();
//			
//			File file = new File(path);
//			
//			File[] files = file.listFiles();
//			
//			for(int i = 0; i < files.length; i++)
//				System.out.println(files[i].getName());
			
			
			
			File file = new File("d:/tjliaa1.txt");
			
			if (!file.exists())
			{
				file.createNewFile();
			}
			
			
			
			InputStreamReader reader = new InputStreamReader(new FileInputStream("d:/tjliaa1.txt"));
			
			char[] buffer = new char[1024];
			int length = reader.read(buffer, 0, 1);
			
			reader.close();
			
			System.out.println(new String(buffer));
			
			//write file

			
			OutputStreamWriter os = new OutputStreamWriter(new FileOutputStream("d:/tjliaa1.txt", true));
			
			os.write("hehe");
			os.close();
			
			
			file.delete();

		}
		catch(Exception e)
		{
			
		}
		
	}

}
