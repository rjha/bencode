package com.yuktix.bencode.scan;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import com.yuktix.bencode.ds.DictionaryType;
import com.yuktix.bencode.ds.IBencodeType;
import com.yuktix.bencode.ds.StringType;

public class DictionaryScanner extends CompositeObject implements IScanner{
	
	private String key ;
	private HashMap<String,IBencodeType> map ;
	
	public DictionaryScanner() {
		this.key = null ;
		this.map = new HashMap<String,IBencodeType>();
	}
	
	public HashMap<String, IBencodeType> getMap() {
		return map;
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
