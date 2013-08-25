package com.yuktix.bencode;

import java.io.IOException;
import java.io.InputStream;

public class StreamScanner extends CompositeObject implements IScanner{
	
	public StreamScanner() {

	}
	
	public void scan(InputStream is) throws IOException {
		// i means IntegerScanner/ else StringScanner
		int i ;
		IScanner scanner ;
		
		while( (i = is.read()) >= 0 ) {
			byte b = (byte) i ;
			switch(b) {
				case 'i' :
					scanner = new IntegerScanner(this);
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
	public void addString(byte[] s) {
		System.out.println("string => " + new String(s));
		
	}

	@Override
	public void addInteger(long i) {
		System.out.println("integer => " + i);
		
	}

	
}