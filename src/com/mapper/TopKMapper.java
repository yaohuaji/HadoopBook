package com.mapper;

import java.io.IOException;
import java.util.TreeMap;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reduce.TopKReducer;

public class TopKMapper extends Mapper<LongWritable, Text, IntWritable, Text>{
//	public static Logger log = LoggerFactory.getLogger(TopKMapper.class);
	public static int K = 10;
	private TreeMap<Integer,String> map = new TreeMap<Integer,String>();
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, IntWritable, Text>.Context context)
			throws IOException, InterruptedException {
		System.out.println("value == " + value.toString());
		map.put(Integer.parseInt(value.toString().split(",")[0]), value.toString());
		if(map.size() > K) {
			map.remove(map.firstKey());
		}
	}
	
	@Override
	protected void cleanup(Mapper<LongWritable, Text, IntWritable, Text>.Context context)
			throws IOException, InterruptedException {
		for(Integer num : map.keySet()) {
			context.write(new IntWritable(num), new Text(map.get(num)));
			System.out.println("num == " + num + " value = " + map.get(num));
		}
	}
}
