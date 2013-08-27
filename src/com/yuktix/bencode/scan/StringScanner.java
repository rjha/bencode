package com.yuktix.bencode.scan;

import java.io.IOException;
import java.io.InputStream;

import com.yuktix.bencode.ds.StringType;

class StringScanner implements IScanner{
	
	private int length ;
	private int peek ;
	
	public StringScanner(int b) {
		this.length = 0 ;
		this.peek = b ;
	}
	
	public void scan(CompositeObject parent,InputStream is) throws IOException {
		
		boolean separator = false ;
		getNumeric(this.peek);
		
		// string length is numeric part
		int i ;
		while((i = is.read()) >= 0) {
			separator = getNumeric(i);
			if(separator) break ;
		}
		
		if(!separator) {
			throw new IOException("unexpected : string length and data separator not found!") ;
		}
		
		byte[] bytes = new byte[this.length];
		int actual = is.read(bytes);
		
		if(actual != this.length) {
			throw new IOException("unexpected end of string") ;
		}
		
		if(parent != null)
			parent.add(new StringType(bytes));
	}
	
	private boolean getNumeric(int i) throws IOException {
		
		boolean flag = false;
		int digit ;
		byte b = (byte) i ;
		
		switch(b) {
			case ':' :
				if(this.length == 0 ) {
					throw new IOException("unexpected : string length is not specified") ;
				}
				
				flag = true ;
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
		
		return flag;
	}

}
