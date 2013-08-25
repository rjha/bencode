package com.yuktix.bencode;

import java.io.ByteArrayInputStream;


public class Test {
	public static void main(String[] args) throws Exception {
		// String text = "4:spam4:abcdi12345678e5:Helloi32ed4:name6:rajeev2:d1d4:email16:rjha94@gmail.comee" ;
		String text = "d3:cow3:moo4:spam4:eggse" ;
		text = "d9:publisher3:bob17:publisher-webpage15:www.example.com18:publisher.location4:homee"; 
		
		ByteArrayInputStream is = new ByteArrayInputStream(text.getBytes("US-ASCII"));
		StreamScanner scanner = new StreamScanner();
		scanner.scan(is);
		scanner.print();
	} 
}
