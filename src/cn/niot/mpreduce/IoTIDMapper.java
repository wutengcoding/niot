package cn.niot.mpreduce;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import cn.niot.service.IDstrRecognition;

/**
 * @author dgq
 *
 */
public class IoTIDMapper extends Mapper<LongWritable, Text, Text, Text> {
	@Override
	public void map(LongWritable key, Text value, Context context)
	throws IOException, InterruptedException {
		String strDemiliter = "{] ";
		String line = value.toString();


		int ndelimiter = line.indexOf(strDemiliter);
		String IDstr = line.substring(0, ndelimiter);
		String IDtype = line.substring(ndelimiter + 3, line.length() - 1);

		String IDRecoRst = "";
		//IDRecoRst = "Value: " + IDtype;

		// now implement the IoTID recognition algorithm
		IDstr = IDstr.replace(" ", "");
		IDstrRecognition.readDao(0);
		HashMap<String, Double> typeProbability = IDstrRecognition.IoTIDRecognizeAlg(IDstr);

		Iterator<String> iterator_t = typeProbability.keySet().iterator();
		while (iterator_t.hasNext()) {
			Object key_IDstd = iterator_t.next();
			IDRecoRst = IDRecoRst + strDemiliter + String.valueOf(key_IDstd);
		}
		
		//test only
		
		context.write(new Text(IDstr + strDemiliter + IDtype), new Text(IDRecoRst));
		return;


	}
}
