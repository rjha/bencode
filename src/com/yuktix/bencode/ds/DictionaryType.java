package com.yuktix.bencode.ds;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

import com.yuktix.bencode.util.ByteArrayComparator;

public class DictionaryType implements IBencodeType{

	private TreeMap<byte[],IBencodeType> sortedMap ;

	public DictionaryType(HashMap<byte[],IBencodeType> map) {
		this.setMap(map) ;
	}
	
	public TreeMap<byte[],IBencodeType> getMap() {
		return sortedMap;
	}
	
	public void setMap(HashMap<byte[],IBencodeType> map) {
		// sort dictionary keys on byte order
		// from the spec: 
		// ---------------
		// Keys must be strings and appear in sorted order 
		// (sorted as raw strings, not alphanumerics). 
		// The strings should be compared using a binary comparison,
		// not a culture-specific "natural" comparison
		// ----------------
		// 
		
		this.sortedMap = new TreeMap<byte[],IBencodeType>(new ByteArrayComparator());
		Set<byte[]> keys = map.keySet() ;
		
		for(byte[] key : keys) {
			this.sortedMap.put(key, map.get(key)) ;
		}
		
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[Dictionary:\n");
		
		Iterator<byte[]> sortedKeys = this.sortedMap.navigableKeySet().iterator() ;
		
		while(sortedKeys.hasNext()) {
			
			byte[] bkey = sortedKeys.next();
			// dictionary keys are us-ascii
			sb.append("\tkey:"+ new String(bkey, StandardCharsets.US_ASCII));
			sb.append("\tvalue:" );
			sb.append(this.sortedMap.get(bkey).toString());
			sb.append("\n");
		}
		
		sb.append("] \n");
		return sb.toString() ;
	}

	@Override
	public void bencode(OutputStream os) throws IOException {
		
		os.write('d');
		Iterator<byte[]> sortedKeys = this.sortedMap.navigableKeySet().iterator() ;
		
		while(sortedKeys.hasNext()) {
			byte[] bkey = sortedKeys.next();
			// string length encoded in base10-ascii
			os.write(Integer.toString(bkey.length).getBytes("US-ASCII"));
			os.write(':');
			os.write(bkey);
			// value
			this.sortedMap.get(bkey).bencode(os);
			
		}
		
		os.write('e');
		
	}
	
}
