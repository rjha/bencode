package com.yuktix.bencode;


public abstract class CompositeObject {
	
	public abstract void addString(byte[] s);
	public abstract void addInteger(long value);
	
}
