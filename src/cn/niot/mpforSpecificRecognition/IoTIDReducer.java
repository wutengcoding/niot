package cn.niot.mpforSpecificRecognition;
import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class IoTIDReducer extends Reducer<Text, Text, Text, Text> {
	public void reduce(Text key, Iterable<Text> values,
			Context context)
	throws IOException, InterruptedException {
		String result = "";
		//把所有相同key的结果拼接起来
		for (Text value : values) {
			result = result + value;
		}
		context.write(key, new Text(result));
	}
}

//public class IoTIDReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
//	public void reduce(Text key, Iterable<IntWritable> values,
//			Context context)
//	throws IOException, InterruptedException {
//		int maxValue = Integer.MIN_VALUE;
//		for (IntWritable value : values) {
//			maxValue = Math.max(maxValue, value.get());
//		}
//		context.write(key, new IntWritable(maxValue));
//	}
//}
