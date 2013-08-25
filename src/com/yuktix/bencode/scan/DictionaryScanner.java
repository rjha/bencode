package com.yuktix.bencode.scan;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import com.yuktix.bencode.ds.DictionaryType;
import com.yuktix.bencode.ds.IBencodeType;
import com.yuktix.bencode.ds.StringType;

public class DictionaryScanner extends CompositeObject implements IScanner{
	
	private CompositeObject parent;
	private String key ;
	private HashMap<String,IBencodeType> map ;
	
	public DictionaryScanner(CompositeObject parent) {
		this.parent = parent ;
		this.key = null ;
		this.map = new HashMap<String,IBencodeType>();
	}
	
	public void scan(InputStream is) throws IOException {

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
			
			if(done)
				break ;
		}
		
		if(parent != null)
			parent.add(new DictionaryType(this.map));
	}

	@Override
	public void add(IBencodeType data) throws IOException {
		
		if( (data instanceof StringType) && (this.key == null)) {
			this.key = data.toString() ;
		} else {
			if(this.key == null ) {
				throw new IOException("expected key : found null");
			}
			
			this.map.put(this.key, data);
			this.key = null ;
		}
	}

}
