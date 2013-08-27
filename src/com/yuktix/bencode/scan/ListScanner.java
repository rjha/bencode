package com.yuktix.bencode.scan;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.yuktix.bencode.ds.IBencodeType;
import com.yuktix.bencode.ds.ListType;

class ListScanner extends CompositeObject implements IScanner{
	
	private List<IBencodeType> list ;
	
	public ListScanner() {
		this.list = new ArrayList<IBencodeType>();
	}
	
	@Override
	public void scan(CompositeObject parent,InputStream is) throws IOException {
		super.scan(parent, is);
		if(parent != null)
			parent.add(new ListType(this.list));
	}
	
	@Override
	public void add(IBencodeType data) throws IOException {
		this.list.add(data);
	}

}
