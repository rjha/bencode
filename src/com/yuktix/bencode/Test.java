package com.yuktix.bencode;

import java.io.ByteArrayInputStream;


public class Test {
	public static void main(String[] args) throws Exception {
		String text = "4:spam4:abcdi12345678e5:Helloi32e" ;
		ByteArrayInputStream is = new ByteArrayInputStream(text.getBytes("US-ASCII"));
		StreamScanner scanner = new StreamScanner();
		scanner.scan(is);
	} 
}
