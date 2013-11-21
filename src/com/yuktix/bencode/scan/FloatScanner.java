package com.yuktix.bencode.scan;

import java.io.IOException;
import java.io.InputStream;
import com.yuktix.bencode.ds.FloatType;

class FloatScanner implements IScanner{
	
	private int length ;
	private float value ;
	private StringBuilder sb ;
	
	public FloatScanner() {
		this.length = 0 ;
		this.sb = new StringBuilder();
	}
	
	public void scan(CompositeObject parent,InputStream is) throws IOException {
			
		boolean negative = false;
		boolean done = false ;
		
		// first byte
		int b = is.read();
		switch(b) {
		
			case '-' :
				negative = true ;
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
				sb.append((char)b);
				this.length++ ;
				break ;
			default :
				throw new IOException("unexpected character in float : " + Character.forDigit(b, 10));
		}
		
		// next bytes
		while ((b = is.read()) >= 0) {
			
			if((this.length == 0) && negative && (b == 'e')) {
				throw new IOException("Abrupt end of number");
			}
			
			switch(b) {
				case 'e' :
					done = true ;
					break ;
				case '.' :
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
					sb.append((char)b);
					this.length++ ;
					break ;
			}
			
			if(done)
				break;
		}
		
		this.value = Float.parseFloat(sb.toString());
		// convert integers to floats
		this.value = (negative) ? (this.value * -1.0f) : (this.value *1.0f) ;
		
		if(parent != null) {
			parent.add(new FloatType(this.value));
		}
	}

}
