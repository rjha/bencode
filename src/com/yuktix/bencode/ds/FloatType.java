package com.yuktix.bencode.ds;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class FloatType implements IBencodeType {
	private float value ;
	
	public FloatType(float f) {
		this.value = f ;
	}
	
	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return Float.toString(value);
	}

	@Override
	public void bencode(OutputStream os) throws IOException {
		os.write('f');
		os.write(Float.toString(value).getBytes(StandardCharsets.US_ASCII));
		os.write('e');
		
	}
}
