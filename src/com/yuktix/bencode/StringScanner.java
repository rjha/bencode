package com.yuktix.bencode;

import java.io.IOException;
import java.io.InputStream;

import com.yuktix.bencode.ds.StringType;

public class StringScanner implements IScanner{
	
	private CompositeObject parent ;
	private int length ;
	private int peek ;
	
	public StringScanner(CompositeObject parent, int b) {
		this.parent = parent ;
		this.length = 0 ;
		this.peek = b ;
		
	}
	
	public void scan(InputStream is) throws IOException {
		// first byte
		boolean flag ;
		getNumeric(this.peek);
		
		int i ;
		while((i = is.read()) >= 0) {
			flag = getNumeric(i);
			if(flag) break ;
		}
		
		byte[] bytes = new byte[this.length];
		is.read(bytes);
		
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
					throw new IOException("string length is unspecified") ;
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
