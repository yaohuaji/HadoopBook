package com.mapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class BeerMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	private Map<String,Integer> map = new HashMap<>();
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		System.out.println("value == " + value);
		String values [] = value.toString().split(" ");
		for(int i = 1;i < values.length-1;i++) {
			for(int j = i+1;j < values.length;j++) {
				String k = values[i] + "-" +values[j];
				if(map.containsKey(k)) {
					map.put(k, map.get(k) + 1);
				}else {
					map.put(k, 1);
				}
			}
		}
	}
	
	@Override
	protected void cleanup(Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		System.out.println("length == " + map.size());
		for(String key : map.keySet()) {
			context.write(new Text(key), new IntWritable(map.get(key)));
			System.out.println("key == " + key + "value == " + map.get(key));
		}
	}
}
