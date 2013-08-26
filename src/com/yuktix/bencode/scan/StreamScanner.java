package com.yuktix.bencode.scan;

import java.util.ArrayList;
import java.util.List;

import com.yuktix.bencode.ds.IBencodeType;

public class StreamScanner extends CompositeObject implements IScanner{
	
	private List<IBencodeType> elements ;
	
	public StreamScanner() {
		this.elements = new ArrayList<IBencodeType>();
	}
	
	@Override
	public void add(IBencodeType data) {
		this.elements.add(data);
	}
	
	public void print() {
		for(IBencodeType element : elements) {
			System.out.print(element.toString());
		}
	}
}
