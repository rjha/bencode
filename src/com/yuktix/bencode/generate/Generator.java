package com.yuktix.bencode.generate;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import com.yuktix.bencode.ds.IBencodeType;

public class Generator {
	
	public static void generate(OutputStream os,IBencodeType element) throws IOException {
		element.bencode(os);
	}
	
	public static void generate(OutputStream os, List<IBencodeType> elements) throws IOException {
		
		for(IBencodeType element : elements) {
			element.bencode(os);
		}
		
	}
	
}
