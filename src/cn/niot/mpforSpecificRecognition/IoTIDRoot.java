package cn.niot.mpforSpecificRecognition;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class IoTIDRoot {
	public static void main(String[] args) throws Exception {

		try {
			
		Configuration conf=new Configuration();
		System.out.println("IoTID SpecificRecognition!"); 
		
		Job job = new Job(conf,"IoTID SpecificRecognition!!");
		job.setJarByClass(IoTIDRoot.class);
		//job.setJobName("IoTID SpecificRecognition!!");
		FileInputFormat.addInputPath(job, new Path("hdfs://cluster04:9000/iot/iotidSpecific.txt"));
		FileOutputFormat.setOutputPath(job, new Path("hdfs://cluster04:9000/iot/iotidSpecficRst"));
		job.setMapperClass(IoTIDMapper.class);
		job.setReducerClass(IoTIDReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		System.exit(job.waitForCompletion(true) ? 0 : 1);
		return;
		} catch (Exception e) {
			System.out.println("error!!!!!");
		}


	}
}


