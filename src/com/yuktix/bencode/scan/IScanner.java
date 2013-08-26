package com.yuktix.bencode.scan;

import java.io.IOException;
import java.io.InputStream;

public interface IScanner {
	void scan(CompositeObject parent,InputStream is) throws IOException; 
	
}
