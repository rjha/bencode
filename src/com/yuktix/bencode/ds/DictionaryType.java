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
	
	
}
