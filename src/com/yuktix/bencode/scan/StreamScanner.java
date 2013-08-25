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
	
	public void scan(InputStream is) throws IOException {
		
		int i ;
		IScanner scanner ;
		
		while( (i = is.read()) >= 0 ) {
			byte b = (byte) i ;
			switch(b) {
				case 'i' :
					scanner = new IntegerScanner(this);
					scanner.scan(is);
					break ;
				case 'd' :
					scanner = new DictionaryScanner(this);
					scanner.scan(is);
					break ;
				case '1' :
				case '2' :
				case '3' :
				case '4' :
				case '5' :
				case '6' :
				case '7' :
				case '8' :
				case '9' :
					scanner = new StringScanner(this,i);
					scanner.scan(is);
					break ;
				default:
					throw new IOException("unexpected character in stream " + Character.forDigit(b, 10));
			}
		}
		
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
