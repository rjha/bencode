package com.yuktix.bencode.ds;

public class StringType implements IBencodeType{
	private String value ;
	
	public StringType(String s) {
		this.value = s ;
	}
	
	public StringType(byte[] bytes) {
		this.value = new String(bytes);
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "[string:" + this.value + "]\n" ;
	}
}
