package cn.niot.mpreduce;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class IoTIDRoot {
	public static void main(String[] args) throws Exception {
//		if (args.length != 2) {
//		System.err.println("Usage: IoTID collision detecting <input path> <output path>");
//		System.exit(-1);
//	}	
		try {
		System.out.println("IoTID Recognition for 805 types!");
		Job job = new Job();
		job.setJarByClass(IoTIDRoot.class);
		job.setJobName("IoTID Recognition for 805 types!");
		//FileInputFormat.addInputPath(job, new Path(args[0]));
		//FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		//FileInputFormat.addInputPath(job, new Path("hdfs://cluster04:9000/iot/oneIoTID.txt"));
		FileInputFormat.addInputPath(job, new Path("hdfs://cluster04:9000/iot/iotid805.txt"));
		FileOutputFormat.setOutputPath(job, new Path("hdfs://cluster04:9000/iot/IoTID805Rst"));
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

//public class MaxTemperature {
//	public static void main(String[] args) throws Exception {
//		if (args.length != 2) {
//			System.err.println("Usage: MaxTemperature <input path> <output path>");
//			System.exit(-1);
//		}
//		Job job = new Job();
//		job.setJarByClass(MaxTemperature.class);
//		job.setJobName("Max temperature");
//		FileInputFormat.addInputPath(job, new Path(args[0]));
//		FileOutputFormat.setOutputPath(job, new Path(args[1]));
//		job.setMapperClass(MaxTemperatureMapper.class);
//		job.setReducerClass(MaxTemperatureReducer.class);
//		job.setOutputKeyClass(Text.class);
//		job.setOutputValueClass(IntWritable.class);
//		System.exit(job.waitForCompletion(true) ? 0 : 1);
//	}
//}
