package com.reduce;

import java.io.IOException;
import java.util.TreeMap;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TopKReducer extends Reducer<IntWritable, Text, NullWritable, Text>{
	TreeMap<Integer, String> map = new TreeMap<>();
	public static final int K = 10;
	@Override
	protected void reduce(IntWritable key, Iterable<Text> values,
			Reducer<IntWritable, Text, NullWritable, Text>.Context context) throws IOException, InterruptedException {
			/*map.put(key.get(),values.iterator().next().toString());
			if(map.size() > K) {
				map.remove(map.firstKey());
			}*/
		
		context.write(NullWritable.get(), new Text(values.iterator().next().toString()));
	}
	
	/*@Override
	protected void cleanup(Reducer<IntWritable, Text, NullWritable, Text>.Context context)
			throws IOException, InterruptedException {
		for(Integer key : map.keySet()) {
			context.write(NullWritable.get(), new Text(map.get(key)));
		}
	}*/
}
