/**
 * 
 */
package cn.niot.SpecificRecognition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import cn.niot.dao.DaoEmulation;
import cn.niot.util.SpecificResult;
import cn.niot.SpecificRecognition.Support.*;
/**
 * @author SQ
 *2014-10-15
 */
public class SpecificRecognize {


	private static final double MaxValue = 65535;

	/**
	 * @param result
	 * @return
	 */


	public static String Select(SpecificResult result) {
		// TODO Auto-generated method stub
		Iterator it=result.ResultDis.keySet().iterator();
		String minType=it.next().toString();
		while(it.hasNext())
		{
			String type_temp=it.next().toString();
			if(result.ResultDis.get(type_temp)<result.ResultDis.get(minType))
			{
				minType=type_temp;
			}
		}
		return minType;
	}
	
	public static ArrayList<String> SelectTop3(HashMap<String, Double> input) {
		ArrayList<String> res = new ArrayList<String>();
		HashMap<String, Double> input1=new HashMap<String, Double> ();
		input1=input;
		Iterator it = input1.keySet().iterator();
		String minType = it.next().toString();
		while (it.hasNext()) {
			String type_temp = it.next().toString();
			if (input1.get(type_temp) < input1.get(minType)) {
				minType = type_temp;
				
			}
			
		}
            res.add(minType);
			input1.remove(minType);
		if (!input1.isEmpty()) {
			it = input1.keySet().iterator();
			minType = it.next().toString();
			while (it.hasNext()) {
				String type_temp = it.next().toString();
				if (input1.get(type_temp) < input1.get(minType)) {
					minType = type_temp;
					
				}
				
			}
			res.add(minType);
			input1.remove(minType);
		}
		if (!input1.isEmpty()) {
			it = input1.keySet().iterator();
			minType = it.next().toString();
			while (it.hasNext()) {
				String type_temp = it.next().toString();
				if (input1.get(type_temp) < input1.get(minType)) {
					minType = type_temp;
				
				}
				
			}
                res.add(minType);
				input1.remove(minType);
		}

		return res;
	}
	

	/**
	 * @param iotidcode
	 * @param typeSet
	 * @return
	 * @throws Exception 
	 */
	public static SpecificResult SpecificRecognize(String iotidcode,
			ArrayList<String> typeSet) throws Exception {
		//HashMap<String, Double> result=new HashMap<String, Double>();
		SpecificResult result=new SpecificResult();
		int flag=1;
		//静态切分特征；计算距离
		if(flag==1)
		{
			result=Distance(iotidcode,typeSet);
		}
		
		//静态切分特征；分类
		if(flag==2)
		{
			
		}
		
		//不切分特征；ANN
		if(flag==3)
		{
			
		}
		return result;
	}

	/**
	 * @param iotidcode
	 * @param typeSet
	 * @return
	 * @throws Exception 
	 */
	public static SpecificResult Distance(String iotidcode,
			ArrayList<String> typeSet) throws Exception {
		//HashMap<String, Double> result=new HashMap<String, Double>();
		SpecificResult result=new SpecificResult();
		int flag=1;
		
		/*
		 * 训练集向量中心：均值
		 * 特征转换选择（Transto64()flag选择）：64-归一；0.64非归一
		 * 位图：0-9，a-z,A-Z
		 */
		if(flag==1)
		{
			result=EuclidDistancWithStaticCenter(iotidcode,typeSet);
		}
		

		/*
		 * 训练集向量中心：均值
		 * 特征转换：64-标准化
		 * 位图选择(Transto64Standard()flag选择)：正向位图；反向位图
		 */
		/*if(flag==2)
		{
			result=EuclidDistancWithStaticStandardCenter(iotidcode,typeSet);
		}*/
		
		return result;
	}

	public static HashMap<String, Double> EuclidDistancWithStaticStandardCenter(
			String iotidcode, ArrayList<String> typeSet) throws Exception {
		HashMap<String, Double> Result = new HashMap<String, Double>();
		
		// 求静态标准中心向量，得到向量，最大向量，最小向量 HashMap<Type,double[]>
		Support.getStaticStandardCenterVector();
		// 对于每一个类型，求在该类型下：1.iotidcode的标准向量 2.距离
		Iterator it = typeSet.iterator();
		//String path = "E:/Distance/Test.txt";
		String path = "hdfs://cluster04:9000/iot/detailInfo.txt";

		while (it.hasNext()) {

			String type = it.next().toString();
			String splitPoints = DaoEmulation.getSplitPoints(type);
			String[] testFeature = Support.Split(iotidcode, splitPoints);
			double[] testFeature_64 = Support.transTo64Standard(testFeature);
			double[] trainingFeatureCenter = Support.mapStandardTrainingSet
					.get(type);
			double[] minVec = Support.mapMinVec.get(type);
			double[] maxVec = Support.mapMaxVec.get(type);
			for (int i = 0; i < testFeature_64.length; i++) {
				if (maxVec[i] != minVec[i]) {
					testFeature_64[i] = (testFeature_64[i] - minVec[i])
							/ (maxVec[i] - minVec[i]);
				} else {
					testFeature_64[i]=trainingFeatureCenter[i];

				}
			}
			Support.WriteTestFeatureInfo(type, testFeature_64, path);
			double dis = DistanceVector2Vector_Euclidean(testFeature_64,
					trainingFeatureCenter);
			Result.put(type, dis);
		}
		return Result;
	}

	public static SpecificResult EuclidDistancWithStaticCenter(String iotidcode,
			ArrayList<String> typeSet) throws Exception {
		
		SpecificResult Result=new SpecificResult();
		Iterator it=typeSet.iterator();
		
		//对于每一个类型，求iotid与之的中心距离
		while(it.hasNext())
		{
			
			String type=it.next().toString();
			String splitPoints=DaoEmulation.getSplitPoints(type);
			String[] testFeature=Support.Split(iotidcode,splitPoints);
			double[] testFeature_64=Support.transTo64(testFeature);
			double[] trainingFeatureCenter=Support.mapTrainingSet.get(type);
			
			double dis=DistanceVector2Vector_Euclidean(testFeature_64,trainingFeatureCenter);
		
			Result.ResultDis.put(type, dis);
			Result.ResultFeature.put(type, testFeature_64);
			
		}
		
		return Result;
		
	}

	public static double DistanceVector2Vector_Euclidean(double[] testFeatureInt,
			double[] trainingFeatureCenter) {
		// TODO Auto-generated method stub
		double dis=0;
	
		
		for(int i=0;i<testFeatureInt.length;i++)
		{
			double temp=testFeatureInt[i]-trainingFeatureCenter[i];
			dis=dis+temp*temp;
		}
		
		dis=Math.sqrt(dis);
		return dis;
	}






}
