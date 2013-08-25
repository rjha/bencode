package com.yuktix.bencode;

import java.io.IOException;
import java.io.InputStream;

public class StringScanner implements IScanner{
	
	private CompositeObject parent ;
	private long value ;
	private boolean isData ;
	private int length ;
	private int peek ;
	
	public StringScanner(CompositeObject parent, int b) {
		this.parent = parent ;
		this.isData = false;
		this.length = 0 ;
		this.peek = b ;
		
	}
	
	public void scan(InputStream is) throws IOException {
		// first byte
		getNumeric(this.peek);
		
		int i ;
		while( ((i = is.read()) >= 0) && !this.isData ) {
			getNumeric(i);
		}
		
		System.out.println("length =>" + this.length);
		
		byte[] bytes = new byte[this.length];
		is.read(bytes);
		
		if(parent != null)
			parent.addString(bytes);
	}
	
	private void getNumeric(int i) throws IOException {
		int digit ;
		byte b = (byte) i ;
		
		switch(b) {
			case ':' :
				if(this.length == 0 ) {
					throw new IOException("string length is unspecified") ;
				}
				
				this.isData = true ;
				break ;
			case '0' :
			case '1' :
			case '2' :
			case '3' :
			case '4' :
			case '5' :
			case '6' :
			case '7' :
			case '8' :
			case '9' :
				digit = b - '0';
				this.length = (this.length * 10) + digit;
				break ;
		}
		
	}

	public long getValue() {
		return this.value;
	}

}
