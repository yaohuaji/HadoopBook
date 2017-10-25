package com.reduce;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class SecondaryReducer extends Reducer<Text, IntWritable, NullWritable, Text> {

	protected void reduce(Text key, Iterable<Text> values, Reducer<Text, IntWritable, NullWritable, Text>.Context context)
			throws IOException, InterruptedException {
		for(Text  value : values) {
			System.out.println("value == " + value + "in reducer");
			context.write(NullWritable.get(), value);
		}
	}
}
