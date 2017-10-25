package com.mapper;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;


//仅仅将value作为key输出，代表一整行数据 
public class SeconadryMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, NullWritable>.Context context)
			throws IOException, InterruptedException {
		
		String filePath = ((FileSplit)context.getInputSplit()).getPath().toString();
		System.out.println("输入路径为：---------------------------》》》》" + filePath);
		System.out.println("value == " + value + " in mapper");
		context.write(value, NullWritable.get());
	}
}
