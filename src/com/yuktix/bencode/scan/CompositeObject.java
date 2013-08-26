package com.yuktix.bencode.scan;

import java.io.IOException;
import java.io.InputStream;
import com.yuktix.bencode.ds.IBencodeType;

public abstract class CompositeObject {
	
	public abstract void add(IBencodeType data) throws IOException;
	
	public void scan(CompositeObject parent,InputStream is) throws IOException {

		int i ;
		IScanner scanner ;
		boolean done = false ;
		
		while( (i = is.read()) >= 0 ) {
			byte b = (byte) i ;
			switch(b) {
				case 'e' :
					done = true ;
					break ;
					
				case 'i' :
					scanner = new IntegerScanner();
					scanner.scan(this,is);
					break ;
				case 'd' :
					scanner = new DictionaryScanner();
					scanner.scan(this,is);
					break ;
				case 'l' :
					scanner = new ListScanner();
					scanner.scan(this,is);
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
					scanner = new StringScanner(i);
					scanner.scan(this,is);
					break ;
				default:
					throw new IOException("unexpected character in stream " + Character.forDigit(b, 10));
			}
			
			if(done)
				break ;
		}
		
	}
}
