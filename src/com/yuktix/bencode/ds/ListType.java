package com.yuktix.bencode.ds;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.ListIterator;

public class ListType implements IBencodeType {
	
	private List<IBencodeType> list ;
	
	public ListType(List<IBencodeType> list) {
		this.list = list ;
	}

	public List<IBencodeType> getList() {
		return list;
	}
	
	public void setList(List<IBencodeType> list) {
		this.list = list;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[List: ");
		ListIterator<IBencodeType> iter = this.list.listIterator() ;
		
		while(iter.hasNext()) {
			sb.append(" " );
			sb.append(iter.next().toString());
		}
		
		sb.append("] \n");
		
		return sb.toString() ;
	}

	@Override
	public void bencode(OutputStream os) throws IOException {
		
		os.write('l');
		
		ListIterator<IBencodeType> iter = this.list.listIterator() ;
		while(iter.hasNext()) {
			iter.next().bencode(os);
		}
		
		os.write('e');
		
	}
	
}
