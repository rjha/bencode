package com.yuktix.bencode.ds;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

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
		return "[int:" + this.value + "]"  ;
	}

	@Override
	public void bencode(OutputStream os) throws IOException {
		os.write('i');
		os.write(Long.toString(value).getBytes(StandardCharsets.US_ASCII));
		os.write('e');
		
	}
}
