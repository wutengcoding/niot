package cn.niot.mpforSpecificRecognition;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

//import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import cn.niot.SpecificRecognition.SpecificRecognize;
import cn.niot.SpecificRecognition.Support;
import cn.niot.dao.DaoEmulation;
import cn.niot.service.IDstrRecognition;
import cn.niot.util.SpecificResult;

/**
 * @author SQ
 * 
 */
public class IoTIDMapper extends Mapper<LongWritable, Text, Text, Text> {
	@Override
	/*public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		String testType = value.toString();
		int OK_count = 0;
		int ERR_count = 0;
		int Top3_count = 0;
		String tablename = DaoEmulation.getTablenale(testType);
		ArrayList<String> TestSet = new ArrayList<String>();
		TestSet = DaoEmulation.getTestSet(tablename);
		Iterator it = TestSet.iterator();
		ArrayList<String> typeSet = new ArrayList<String>();
		// 对于测试集中的每一个代码，进行静态识别，精确识别，结果统计
		IDstrRecognition.readDao(0);
		// 得到静态中心向量
		try {
			Support.getStaticCenterVector();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		while (it.hasNext()) {

			typeSet.clear();
			String iotidcode = it.next().toString();
			System.out.println(iotidcode);

			// 第一步：静态识别：
			
			SpecificResult Result = new SpecificResult();
			HashMap<String, Double> typeProbability = IDstrRecognition
					.IoTIDRecognizeAlg(iotidcode);

			// 将静态识别结果集存入ArrayList
			Iterator itTypes = typeProbability.keySet().iterator();
			while (itTypes.hasNext()) {
				String type = itTypes.next().toString();
				// 检验是否完成仿真生成
				if (Support.IsEmulated(type))
					typeSet.add(type);
			}

			// 第二步：精确识别：
			if(typeProbability.size()!=0)
			{

			try {
				Result = SpecificRecognize
						.SpecificRecognize(iotidcode, typeSet);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				System.out.println("@@@@@@@@@:Exception In SpecificReco;");
				// e1.printStackTrace();
			}

			// 判断是否为唯一正确结果
			String MinType = "";
			try {
				MinType = SpecificRecognize.Select(Result);
			} catch (Exception e) {
				System.out
						.println("!!!!!!!!!!!!!!!!!!!!!ExceptionIN SELECT!!!");
			}
			String flag = "";

			if (testType.equals(MinType)) {
				flag = "OK";
				OK_count++;
			} else {
				flag = "ERR";
				ERR_count++;
			}
			String reduceValue = "";
			reduceValue = Support.WriteSpecificDebugInfo(testType, Result,
					flag, iotidcode);
			context.write(new Text(testType), new Text(reduceValue));
			

			// 判断是否为top3
			ArrayList<String> top3 = new ArrayList<String>();
			HashMap<String,Double> input=new HashMap<String,Double>();
			input=Result.ResultDis;
			top3 = SpecificRecognize.SelectTop3(input);
			if (top3.contains(testType))
				Top3_count++;

			}
			else
			{
				ERR_count++;
			}
			
		}
		String StatInfo = Support.WriteStaticInfo(testType, OK_count,
				Top3_count, ERR_count);
		context.write(new Text("StatInfo:"), new Text(StatInfo));
        return;
		
	}*/

	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		
		String line = value.toString();
		String strDemiliter = "{] ";
		int ndelimiter = line.indexOf(strDemiliter);
		String iotidcode = line.substring(0, ndelimiter);
		String testType = line.substring(ndelimiter + 3, line.length());
		ArrayList<String> typeSet = new ArrayList<String>();
		// 对于测试集中的每一个代码，进行静态识别，精确识别，结果统计
		IDstrRecognition.readDao(0);
		// 得到静态中心向量
		try {
			Support.getStaticCenterVector();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	

			typeSet.clear();

			// 第一步：静态识别：
			try{
			SpecificResult Result = new SpecificResult();
			HashMap<String, Double> typeProbability = IDstrRecognition
					.IoTIDRecognizeAlg(iotidcode);

			// 将静态识别结果集存入ArrayList
			try{
			Iterator itTypes = typeProbability.keySet().iterator();
			while (itTypes.hasNext()) {
				String type = itTypes.next().toString();
				// 检验是否完成仿真生成
				if (Support.IsEmulated(type))
					typeSet.add(type);
			}
			}catch(Exception e)
			{
				System.out.println("Excetion in staticRecognition!!!!!!!!!!!!!!!!!!!!!!!!");
			}

			// 第二步：精确识别：
			
			if (typeProbability.size() != 0) {
				try{

				try {
					Result = SpecificRecognize.SpecificRecognize(iotidcode,
							typeSet);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("@@@@@@@@@:Exception In SpecificReco;");
					// e1.printStackTrace();
				}

				// 判断是否为唯一正确结果
				String MinType = "";
				try {
					MinType = SpecificRecognize.Select(Result);
				} catch (Exception e) {
					System.out
							.println("!!!!!!!!!!!!!!!!!!!!!ExceptionIN SELECT!!!");
				}
				String flag_top1 = "";
				String flag_top3="";

				if (testType.equals(MinType)) {
					flag_top1 = "OK";
					//OK_count++;
				} else {
					flag_top1 = "ERR";
					//ERR_count++;
				}
				String reduceValue = "";
				//reduceValue = Support.WriteSpecificDebugInfo(testType, Result,
						//flag, iotidcode);
				
				// 判断是否为top3
				ArrayList<String> top3 = new ArrayList<String>();
				HashMap<String, Double> input = new HashMap<String, Double>();
				input = Result.ResultDis;
				top3 = SpecificRecognize.SelectTop3(input);
				if (top3.contains(testType))
					//Top3_count++;
					flag_top3="OK";
				else
					flag_top3="ERR";
				reduceValue=testType+"  "+iotidcode+"  "+flag_top1+"  "+flag_top3+"\n";
				
				context.write(new Text(testType), new Text(reduceValue));

				}catch(Exception e)
				{
					System.out.println("Dynamic Wrong!!!!!!!!!!!!!!");
				}
			} else {
				String reduceValue = "";
				//reduceValue = Support.WriteSpecificDebugInfo(testType, Result,
						//flag, iotidcode);
				String flag_top1 = "ERR";
				String flag_top3="ERR";
				reduceValue=testType+"  "+iotidcode+"  "+flag_top1+"  "+flag_top3+"\n";
				context.write(new Text(testType), new Text(reduceValue));
			}
			}catch(Exception e)
			{
				System.out.println("Wrong with Dynamic!!!");
			}

		
		return;

	}
	
	
}
