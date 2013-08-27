package com.yuktix.bencode.ds;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

import com.yuktix.bencode.util.ByteArrayComparator;

public class DictionaryType implements IBencodeType{

	private TreeMap<byte[],IBencodeType> sortedMap ;

	public DictionaryType(HashMap<String,IBencodeType> map) {
		this.setMap(map) ;
	}
	
	public TreeMap<byte[],IBencodeType> getMap() {
		return sortedMap;
	}
	
	public void setMap(HashMap<String,IBencodeType> map) {
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
		Set<String> keys = map.keySet() ;
		
		for(String key : keys) {
			this.sortedMap.put(key.getBytes(StandardCharsets.US_ASCII), map.get(key)) ;
		}
		
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[Dictionary:\n");
		
		Iterator<byte[]> sortedKeys = this.sortedMap.navigableKeySet().iterator() ;
		
		while(sortedKeys.hasNext()) {
			
			byte[] bkey = sortedKeys.next();
			String key = new String(bkey,StandardCharsets.US_ASCII);
			
			sb.append("\tkey:"+ key);
			sb.append("\tvalue:" );
			sb.append(this.sortedMap.get(bkey).toString());
			sb.append("\n");
			
		}
		
		sb.append("] \n");
		return sb.toString() ;
	}

	@Override
	public String bencode() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("d");
		
		Iterator<byte[]> sortedKeys = this.sortedMap.navigableKeySet().iterator() ;
		
		while(sortedKeys.hasNext()) {
			byte[] bkey = sortedKeys.next();
			String key = new String(bkey,StandardCharsets.US_ASCII);
			sb.append(bkey.length);
			sb.append(":");
			sb.append(key);
			sb.append(this.sortedMap.get(bkey).bencode());
		}
		
		sb.append("e");
		return sb.toString();
	}
	
}
