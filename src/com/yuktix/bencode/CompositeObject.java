package com.yuktix.bencode;

import java.io.IOException;
import com.yuktix.bencode.ds.IBencodeType;


public abstract class CompositeObject {
	public abstract void add(IBencodeType data) throws IOException;
}
