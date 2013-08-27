package com.yuktix.bencode.ds;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class StringType implements IBencodeType{
	
	private byte[] bytes ;
	
	public StringType(byte[] bytes) {
		this.bytes = bytes ;
	}
	
	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	@Override
	public String toString() {
		String str = new String(this.bytes, StandardCharsets.US_ASCII);
		return  str  ;
	}

	@Override
	public void bencode(OutputStream os) throws IOException {
		// string lenght is coded in base-10 ASCII
		os.write(Integer.toString(this.bytes.length).getBytes("US-ASCII"));
		os.write(':');
		os.write(this.bytes);
	}
}
