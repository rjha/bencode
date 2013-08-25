package mybencode;

import java.io.IOException;
import java.io.InputStream;

public class IntegerScanner implements IScanner{
	
	private IScanner parent ;
	InputStream is ;
	private long value ;
	private int length ;
	
	
	public IntegerScanner(IScanner parent, InputStream is) {
		this.parent = parent ;
		this.is  = is ;
		this.length = 0 ;
	}
	
	public void scan() throws IOException {
		
		boolean negative = false;
		boolean padding = false ;
		boolean done = false ;
	
		int digit = 0 ;
		
		// first byte
		int b = is.read();
		switch(b) {
			case '-' :
				negative = true ;
				break ;
			case '0' :
				// i0e is valid number
				padding = true ;
				this.value = 0 ;
				this.length++ ;
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
				digit = b - '0';
				this.value = (this.value * 10) + digit;
				this.length++ ;
				break ;
			default :
				throw new IOException("unexpected character in integer : " + Character.forDigit(b, 10));
		}
		
		// next bytes
		while (((b = is.read()) >= 0) && !done) {
			if(padding && (this.length == 1) && (b != 'e')) {
				throw new IOException("found zero padding of integer");
			}
			
			if((this.length == 0) && negative && (b == 'e')) {
				throw new IOException("Abrupt end of negative number");
			}
			
			switch(b) {
				case 'e' :
					done = true ;
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
					this.value = (this.value * 10) + digit;
					this.length++ ;
					break ;
					
			}
		}
		
		if (negative) {
			this.value *= -1;
		}
		
		// control back to parent
		if(parent != null) {
			parent.ping(this);
		}
		
	}

	public long getValue() {
		return this.value;
	}

	public int getLength() {
		return length;
	}

	@Override
	public void ping(IScanner child) {
		// TODO Auto-generated method stub
		
	}

}
