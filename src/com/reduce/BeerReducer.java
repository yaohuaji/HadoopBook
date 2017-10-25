package com.reduce;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.log4j.Logger;

public class BeerReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
	static Logger logger = Logger.getLogger(BeerReducer.class);
	private Map<String,Integer> map = new HashMap<>();
	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
		Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
		String [] res = key.toString().split("-");
		String k = res[1] + "-" + res[0];
		if(map.containsKey(k)) {
			map.put(k, map.get(k) + Integer.parseInt(values.iterator().next().toString()));
		}else {
			map.put(key.toString(), Integer.parseInt(values.iterator().next().toString()));
		}
	}
	
	@Override
	protected void cleanup(Reducer<Text, IntWritable, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		for(String key : map.keySet()) {
			context.write(new Text(key), new IntWritable(map.get(key)));
		}
	}
}
