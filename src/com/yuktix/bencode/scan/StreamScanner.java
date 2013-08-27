package com.yuktix.bencode.scan;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.yuktix.bencode.ds.IBencodeType;

public class StreamScanner extends CompositeObject implements IScanner{
	
	private List<IBencodeType> elements ;
	
	public StreamScanner() {
		this.elements = new ArrayList<IBencodeType>();
	}
	
	public List<IBencodeType> getElements() {
		return elements;
	}
	
	public void munch(InputStream is) throws IOException {
		this.scan(null, is);
	}
	
	@Override
	public void add(IBencodeType data) {
		this.elements.add(data);
	}
	
	public void print() {
		for(IBencodeType element : elements) {
			System.out.println(element.toString());
		}
	}
}
