package com.tjli.test.datastruct;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class TestSerialize implements Serializable {

	private String memberString = "";

	private Integer memberInt = 4;

	public String getMemberString() {
		return memberString;
	}

	public void setMemberString(String memberString) {
		this.memberString = memberString;
	}

	public Integer getMemberInt() {
		return memberInt;
	}

	public void setMemberInt(Integer memberInt) {
		this.memberInt = memberInt;
	}

	public static void main(String[] args) {

		try {
			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream("d:/tjliaa.txt"));

			TestSerialize ts = new TestSerialize();
			ts.setMemberInt(31415);
			ts.setMemberString("hehe++");
			oos.writeObject(ts);
			oos.close();
		} catch (Exception e) {

		}
	}

}
