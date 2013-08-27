package com.yuktix.bencode.ds;

import java.io.IOException;
import java.io.OutputStream;

public interface IBencodeType {
	String toString() ;	
	void bencode(OutputStream os) throws IOException;
}
