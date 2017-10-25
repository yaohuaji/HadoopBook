package com.comparator;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class SortComparator extends WritableComparator {
	protected SortComparator() {
		super(Text.class,true);
	}
	
	@Override
	public int compare(WritableComparable key1, WritableComparable key2) {
		System.out.println("key1 == " + key1 + "    key2 ==" + key2 + "  in compator ");
		//第一个数字相同则比较第二个数子
		if(Integer.parseInt(key1.toString().split(" ")[0]) == Integer.parseInt(key2.toString().split(" ")[0])) {
			if(Integer.parseInt(key1.toString().split(" ")[1]) > Integer.parseInt(key2.toString().split(" ")[1])) {
				return 1;
			}else if(Integer.parseInt(key1.toString().split(" ")[1]) < Integer.parseInt(key2.toString().split(" ")[1])) {
				return -1;
			}else if(Integer.parseInt(key1.toString().split(" ")[1]) == Integer.parseInt(key2.toString().split(" ")[1])){
				return 0;
			}
			//第一个数字不同就比较第一个数字
		}else {
			if(Integer.parseInt(key1.toString().split(" ")[0]) > Integer.parseInt(key2.toString().split(" ")[0])) {
				return 1;
			}else if(Integer.parseInt(key1.toString().split(" ")[0]) < Integer.parseInt(key2.toString().split(" ")[0])) {
				return -1;
			}
		}
		return 0;
	}
}
