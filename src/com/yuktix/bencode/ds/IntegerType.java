package com.yuktix.bencode.ds;

public class IntegerType implements IBencodeType {
	private long value ;
	
	public IntegerType(long i) {
		this.value = i ;
	}
	
	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "[int:" + this.value + "] \n"  ;
	}
}
