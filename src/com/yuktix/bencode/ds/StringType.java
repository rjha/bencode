package com.yuktix.bencode.ds;

import java.nio.charset.StandardCharsets;

public class StringType implements IBencodeType{
	private String value ;
	
	public StringType(byte[] bytes) {
		this.value = new String(bytes, StandardCharsets.US_ASCII);
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "[string:" + this.value + "]" ;
	}

	@Override
	public String bencode() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.value.length());
		sb.append(":");
		sb.append(this.value);
		return sb.toString();
	}
}
