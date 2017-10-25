package com.main;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import com.comparator.SortComparator;
import com.mapper.SeconadryMapper;
import com.partition.KeyPartitioner;
import com.reduce.SecondaryReducer;

public class SecondMain {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		String inputPath = "hdfs://master:9000/user/test/secondInput";
		String outputPath = "hdfs://master:9000/user/test/secondOutput";
		Configuration conf = new Configuration();
		Job job = new Job(conf,"secondarysort");
		job.setMapperClass(SeconadryMapper.class);
		job.setReducerClass(SecondaryReducer.class);
		job.setPartitionerClass(KeyPartitioner.class);
		job.setSortComparatorClass(SortComparator.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(NullWritable.class);
		job.setOutputKeyClass(NullWritable.class);
		job.setOutputValueClass(Text.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		FileInputFormat.addInputPath(job, new Path(inputPath));
		FileOutputFormat.setOutputPath(job, new Path(outputPath));
		
		job.setNumReduceTasks(1);
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
