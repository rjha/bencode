package com.yuktix.bencode.test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import com.yuktix.bencode.ds.IBencodeType;
import com.yuktix.bencode.ds.StringType;
import com.yuktix.bencode.generate.Generator;
import com.yuktix.bencode.scan.StreamScanner;


public class Test {
	public static void main(String[] args) throws Exception {
		test_generate();
	} 

	public static void test_generate() {
		List<IBencodeType> elements = new ArrayList<IBencodeType>();
		elements.add(new StringType("rajeev"));
		elements.add(new StringType("jha"));
		String result = Generator.generate(elements) ;
		System.out.println(result);
		
	}
	
	public static void test_scan() throws Exception{
			// String text = "4:spam4:abcdi12345678e5:Helloi32ed4:name6:rajeev2:d1d4:email16:rjha94@gmail.comee" ;
			String text = "d3:cow3:moo4:spam4:eggse" ;
			text = "d9:publisher3:bob17:publisher-webpage15:www.example.com18:publisher.location4:homee"; 
			text = "d4:dictd4:name6:rajeevee";
			ByteArrayInputStream is = new ByteArrayInputStream(text.getBytes("US-ASCII"));
			StreamScanner scanner = new StreamScanner();
			scanner.scan(is);
			scanner.print();
	}

}
