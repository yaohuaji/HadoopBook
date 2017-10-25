package com.partition;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.partition.HashPartitioner;

public class KeyPartitioner extends HashPartitioner<Text, NullWritable>{
	@Override
	public int getPartition(Text key, NullWritable value, int numReduceTasks) {
		//按照第一个数字进行均匀分发
		return (key.toString().split(" ")[0].hashCode() & Integer.MAX_VALUE) % numReduceTasks;
	}
}
