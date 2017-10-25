package com.main;


import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import com.mapper.BeerMapper;
import com.reduce.BeerReducer;

public class BeerMain {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		String input = "hdfs://master:9000/user/test/beerInput";
		String output = "hdfs://master:9000/user/test/beerOutput";
		
		Configuration conf = new Configuration();
		Job job = new Job(conf,"BeerJob");
		job.setMapperClass(BeerMapper.class);
		job.setReducerClass(BeerReducer.class);
		job.setMapOutputKeyClass(Text.class);
    	job.setMapOutputValueClass(IntWritable.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		FileInputFormat.setInputPaths(job, new Path(input));
		FileOutputFormat.setOutputPath(job, new Path(output));
		
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}

	
	
	
