package com.yuktix.bencode.scan;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import com.yuktix.bencode.ds.DictionaryType;
import com.yuktix.bencode.ds.IBencodeType;
import com.yuktix.bencode.ds.StringType;

public class DictionaryScanner extends CompositeObject implements IScanner{
	
	private byte[] key ;
	private HashMap<byte[],IBencodeType> map ;
	
	public DictionaryScanner() {
		this.key = null ;
		this.map = new HashMap<byte[],IBencodeType>();
	}

	@Override
	public void scan(CompositeObject parent,InputStream is) throws IOException {
		
		super.scan(parent, is);
		if(parent != null)
			parent.add(new DictionaryType(this.map));
		
	}
	
	@Override
	public void add(IBencodeType data) throws IOException {
		
		if( (data instanceof StringType) && (this.key == null)) {
			StringType st = (StringType) data ;
			this.key = st.getBytes() ;
		} else {
			if(this.key == null ) {
				throw new IOException("expected key : found null");
			}
			
			this.map.put(this.key, data);
			this.key = null ;
		}
	}

}
