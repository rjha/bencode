package com.yuktix.bencode.generate;

import java.util.List;

import com.yuktix.bencode.ds.IBencodeType;

public class Generator {
	
	public static String generate(IBencodeType element) {
		return element.bencode();
	}
	
	public static String generate(List<IBencodeType> elements) {
		StringBuilder sb = new StringBuilder() ;
		for(IBencodeType element : elements) {
			sb.append(element.bencode());
		}
		
		return sb.toString();
	}
	
}
