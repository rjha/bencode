package com.yuktix.bencode.ds;

import java.util.HashMap;
import java.util.Set;

public class DictionaryType implements IBencodeType{
	private HashMap<String,IBencodeType> map ;
	public DictionaryType(HashMap<String,IBencodeType> map) {
		this.setMap(map) ;
	}
	
	public HashMap<String,IBencodeType> getMap() {
		return map;
	}
	
	public void setMap(HashMap<String,IBencodeType> map) {
		this.map = map;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[Dictionary:\n");
		Set<String> keys = map.keySet() ;
		
		for(String key : keys) {
			sb.append("\tkey:"+ key);
			sb.append("\tvalue:" );
			sb.append(map.get(key).toString());
			sb.append("\n");
		}
		
		sb.append("] \n");
		
		return sb.toString() ;
	}

	@Override
	public String bencode() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("d");
		// @todo sort dictionary keys on byte order
		// from the spec: Keys must be strings and appear in sorted order 
		// (sorted as raw strings, not alphanumerics). 
		// The strings should be compared using a binary comparison,
		// not a culture-specific "natural" comparison
		
		Set<String> keys = map.keySet() ;
		
		for(String key : keys) {
			
			sb.append(key.length());
			sb.append(":");
			sb.append(key);
			sb.append(map.get(key).bencode());
		}
		
		sb.append("e");
		return sb.toString();
	}
	
	
}
