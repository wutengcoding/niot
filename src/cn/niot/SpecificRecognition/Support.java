package cn.niot.SpecificRecognition;

//import java.io.BufferedWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import cn.niot.util.FunctionResult;
import cn.niot.util.JdbcUtilsEmulation;
import cn.niot.util.SpecificResult;
import cn.niot.dao.DaoEmulation;

public class Support {
	/**
	 * @param type
	 * @return
	 */
	//public static double[] CenterVector;
	public static HashMap<String,double[]> mapTrainingSet=new HashMap<String,double[]>();
	public static HashMap<String,double[]> mapStandardTrainingSet=new HashMap<String,double[]>();
	public static HashMap<String,double[]> mapMaxVec=new HashMap<String,double[]>();
	public static HashMap<String,double[]> mapMinVec=new HashMap<String,double[]>();
	
	
	
	public static void getStaticCenterVector() throws Exception {
		// TODO Auto-generated method stub
	
		if(mapTrainingSet.size()==0)
		{
			makeTrainingSetMap();
		}
	}

	/**
	 * @param typeSet 
	 * @param typeSet 
	 * @throws Exception 
	 * 
	 */
	public static void makeTrainingSetMap() throws Exception {
		// TODO Auto-generated method stub

        ArrayList TypeSet=DaoEmulation.getTypeSet();
		Iterator it = TypeSet.iterator();
		String resInfo = "";
		while (it.hasNext()) {
			int Length = 0;
			String type = it.next().toString();
			String splitPoints = DaoEmulation.getSplitPoints(type);
			String tablename = DaoEmulation.getTablenale(type);
			if (splitPoints != null && tablename != null) {
				if (splitPoints.startsWith("##")) {
					String[] temp = splitPoints.split(";");
					Length = Integer.parseInt(temp[1]);
				} else {
					String[] splitPoint = splitPoints.split(",");
					Length = splitPoint.length - 1;
				}

				String[] splitFeatures = new String[Length];
				double[] features = new double[Length];

				double[] Result = new double[Length];
				int size=0;
				try {

					ArrayList<String> dataSet=DaoEmulation.getDataSet(tablename, 1000, 10000);
					Iterator ds=dataSet.iterator();
					
					while (ds.hasNext()) {
					String code = ds.next().toString();
					splitFeatures = Split(code, splitPoints);
					features = transTo64(splitFeatures);
					for (int i = 0; i < features.length; i++) {
						Result[i] = Result[i] + features[i];

					}
					size++;

				}

				} catch (Exception e) {
					
					
					System.out.println("ExceptiongetTrainingcenter fromDB");
				}
				for (int i = 0; i < features.length; i++) {
					Result[i] = Result[i] / size;

				}

				mapTrainingSet.put(type, Result);

			}
		}

	}

	/**
	 * @param resInfo
	 * @param string
	 */
	/*public static void Report(String resInfo, String path) {
		// TODO Auto-generated method stub
		try{
			File f = new File(path);
			BufferedWriter output = new BufferedWriter(new FileWriter(f,
				true));
			output.append(resInfo);
			output.append("\n");
			
			output.flush();
			output.close();}catch(Exception e)
			{
				e.printStackTrace();
			}
	}*/
	public static void Report(String resInfo, String path) {
		// TODO Auto-generated method stub
		try{
			File f = new File(path);
			BufferedWriter output = new BufferedWriter(new FileWriter(f,
				true));
			output.append(resInfo);
			output.append("\n");
			
			output.flush();
			output.close();}catch(Exception e)
			{
				e.printStackTrace();
			}
	}

	/**
	 * @param iotidcode
	 * @param splitPoints
	 * @return
	 */
	// 分为两种情况：1.定长和尾部不定长 2.前端和中间部分不定长
	public static String[] Split(String iotidcode, String splitPoints) {
		// TODO Auto-generated method stub
		String[] Feature;
		if (splitPoints.startsWith("##")) {
			String[] Split = splitPoints.split(";");
			String splitFunctionName=Split[0].substring(2);
			int splitLength=Integer.parseInt(Split[1]);
			Feature = new String[splitLength];
			
			try {
				Class SplitFuctionClass = Class
						.forName("cn.niot.SpecificRecognition.SplitFunction");// specify the
				
				
					Object[] Args = new Object[1];
					Args[0] = iotidcode;
					Class[] c = new Class[1];
					c[0] = String.class;
					Method method = SplitFuctionClass.getMethod(splitFunctionName, c);
					Object FeatureTemp = method.invoke(null, Args);
					Feature=(String[])FeatureTemp;
					
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
	
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
			    e.printStackTrace();
				//Result.ResultFlag = ERR;
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//Result.ResultFlag = ERR;
			}
			

		} else {
			String[] Split = splitPoints.split(",");
			Feature = new String[Split.length - 1];
			for (int i = 0; i < Split.length - 1; i++) {
				int beginIndex = Integer.valueOf(Split[i]);
				int endIndex = Integer.valueOf(Split[i + 1]);
				if (i != (Split.length - 2))
					Feature[i] = iotidcode.substring(beginIndex, endIndex);
				else
					Feature[i] = iotidcode.substring(beginIndex, iotidcode
							.length());
			}
		}
		return Feature;
	}

	
	public static double[] transTo64(String[] testFeature) {
		// TODO Auto-generated method stub
		int flag = 1;
		/*
		 * flag=0:正向位图，64，归一 
		 * flag=1：正向位图，0.64，非归一 
		 */
		double[] Result = new double[testFeature.length];
		if (flag == 0) {
			for (int i = 0; i < testFeature.length; i++) {
				String temp = testFeature[i];
				double res = 0;
				for (int j = 0; j < temp.length(); j++) {
					int num = BitMap64(temp.charAt(j));
					res = res + num * Math.pow(64, temp.length() - 1 - j);

				}
				// 归一化处理
				res = res / Math.pow(64, temp.length());
				Result[i] = res;
			}
		} else if (flag == 1) {
			for (int i = 0; i < testFeature.length; i++) {
				String temp = testFeature[i];
				double res = 0;
				for (int j = 0; j < temp.length(); j++) {
					int num = BitMap64(temp.charAt(j));

					res = res + num * Math.pow(0.64, j);

				}
				Result[i] = res;
			}
		} 

		return Result;
	}

	// flag=1:正向位图；64-非归一
	// flag=2：反向位图；64-非归一
	public static double[] transTo64Standard(String[] testFeature) {

		int flag = 1;
		double[] Result = new double[testFeature.length];
		if (flag == 1) {
			for (int i = 0; i < testFeature.length; i++) {
				String temp = testFeature[i];
				double res = 0;
				for (int j = 0; j < temp.length(); j++) {
					int num = BitMap64(temp.charAt(j));
					res = res + num * Math.pow(64, temp.length() - 1 - j);

				}
				Result[i] = res;
			}
		} else if (flag == 2) {
			for (int i = 0; i < testFeature.length; i++) {
				String temp = testFeature[i];
				double res = 0;
				for (int j = 0; j < temp.length(); j++) {
					int num = BitMap64(temp.charAt(j));
					res = res + num * Math.pow(64, temp.length() - 1 - j);

				}
				Result[i] = res;
			}
		}

		return Result;
	}

	/**
	 * @param charAt
	 * @return
	 */
	/*
	 * 0-9,a-z,A-Z,-,其他
	 */
	public static int BitMap64(char c) {
		// TODO Auto-generated method stub
		int res=0;
		if(c>=48&&c<=57)
			res=(int)(c-48);
		else if(c>=97&&c<=122)
			res=(int)(c-87);
		else if(c>=65&&c<=90)
			res=(int)(c-29);
		else if(c=='-')
			res=62;
		else
			res=63;
		return res;
	}
	/*
	 * A-Z,a-z,0-9,-,其他
	 */
	public static int ReverseBitMap64(char c) {
		// TODO Auto-generated method stub
		
	int res=0;
		if(c>='a'&&c<='z')
			res=(int)(c-97);
		else if(c>='A'&&c<='Z')
			res=(int)(c-39);
		else if(c>='0'&&c<='9')
			res=(int)(c+4);
		else if(c=='-')
			res=62;
		else
			res=63;
			
		return res;
	}

	public static String WriteSpecificDebugInfo(String testType, SpecificResult result,
			String flag, String iotidcode) {
		
		//写入格式：
		/*!!!OK   iotidcode
		 * type1:   0.035
		 * type2:   0.17
		 * type3:   0.024
		 * 
		 * type1  
		 */
		String resInfo="";
		resInfo=resInfo+"!!!"+flag+"   "+iotidcode+"   "+testType+"\n";
		Iterator it=result.ResultDis.keySet().iterator();
		while(it.hasNext())
		{
			String type=it.next().toString();
			double dis=result.ResultDis.get(type);
			//System.out.println(type+":   "+dis);
			resInfo=resInfo+type+":   "+dis+"\n";
		}
		resInfo=resInfo+"\n";
		
		it=result.ResultFeature.keySet().iterator();
		String res="";
		while(it.hasNext())
		{
			String type=it.next().toString();
			double[] testFeature_64=result.ResultFeature.get(type);
			res=res+type+":   ";
			for(int i=0;i<testFeature_64.length;i++)
			{
				res=res+testFeature_64[i]+"  ";
			}
			res=res+"\n";
			//System.out.println(type+":   "+dis);
			
		}
		
		resInfo=resInfo+res+"\n";
		//Support.Report(resInfo,path);
		return resInfo;
		
	}

	public static void WriteTestFeatureInfo(String type,
			double[] testFeature_64, String path) throws Exception {
		String res="";
		res=res+type+":   ";
		for(int i=0;i<testFeature_64.length;i++)
		{
			res=res+testFeature_64[i]+"  ";
		}
		res=res+"\n";
		Support.Report(res, path);
		// TODO Auto-generated method stub
		
	}

	public static boolean IsEmulated(String type) {
		// TODO Auto-generated method stub
		
		
		String tablename = DaoEmulation.getTablenale(type);
		String SplitPoint = DaoEmulation.getSplitPoints(type);
		if(tablename!=null&&SplitPoint!=null)
			return true;
		else
		  return false;
	}

	public static void getStaticStandardCenterVector() throws Exception {
		// TODO Auto-generated method stub

		if(mapStandardTrainingSet.size()==0)
		{
			makeStandardTrainingSetMap();
		}
		
		
	}

	public static void makeStandardTrainingSetMap() throws Exception {
		// TODO Auto-generated method stub
		ArrayList<String> TypeSet=DaoEmulation.getTypeSet();
		Iterator it = TypeSet.iterator();
		String resInfo = "";
		while (it.hasNext()) {
			int Length=0;
			String type = it.next().toString();
			String splitPoints = DaoEmulation.getSplitPoints(type);
			String tablename = DaoEmulation.getTablenale(type);
			if (splitPoints != null&&tablename!=null) {
				if(splitPoints.startsWith("##"))
				{
					String[]temp=splitPoints.split(";");
					Length=Integer.parseInt(temp[1]);
				}
				else
				{
					String[] splitPoint = splitPoints.split(",");
					Length=splitPoint.length - 1;
				}
			
				String[] splitFeatures=new String[Length];
				double[] features=new double[Length];
				
				double[] Result = new double[Length];
				double[] maxResult = new double[Length];
				double[] minResult = new double[Length];
				for(int i=0;i<minResult.length;i++)
				{
					minResult[i]=0x7f; 
				}
				Connection con = JdbcUtilsEmulation.getConnectionEmulation();
				int size = 0;
				try {

					PreparedStatement stmt;
					ResultSet result;
					stmt = con.prepareStatement("select * from " + tablename
							+ " limit 1000,10000;");
					result = stmt.executeQuery();

					while (result.next()) {
						String code = result.getString("code");
						splitFeatures = Split(code, splitPoints);
						features = transTo64Standard(splitFeatures);
						for (int i = 0; i < features.length; i++) {
							Result[i] = Result[i] + features[i];
							if(features[i]>maxResult[i])
							{
								maxResult[i]=features[i];
							}
							if(features[i]<minResult[i])
							{
								minResult[i]=features[i];
							}
                        }
						size++;

					}

				} catch (SQLException e) {
					System.out.println(e.toString());
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (int i = 0; i < features.length; i++) {
					Result[i] = Result[i] / size;
					if(maxResult[i]!=minResult[i])
					{
					Result[i]=(Result[i]-minResult[i])/(maxResult[i]-minResult[i]);
					}
					else
					{
						Result[i]=0.5;
					}

				}

				mapStandardTrainingSet.put(type, Result);
				mapMaxVec.put(type, maxResult);
				mapMinVec.put(type, minResult);
				
				

				// 保存中心向量信息
				resInfo="";
				resInfo = resInfo+type + "_CENTER:";
				for (int i = 0; i < Result.length; i++) {
					resInfo =resInfo+Result[i] + "  ";
				}
				resInfo = resInfo + "\n";
				Report(resInfo, "hdfs://cluster04:9000/iot/centerInfo.txt");
			}
		}
	}

	public static String WriteStaticInfo(String testType, int oKCount,
			int top3Count,int errCount)  {
		// TODO Auto-generated method stub
		String res=testType+":   "+oKCount+"   "+top3Count+"   "+errCount+"\n";
		return res;
		
	}

	public static HashMap<String, Double> ComputeResultProb(
			SpecificResult result) {
		SpecificResult Result=new SpecificResult();
		HashMap<String, Double> DynamictypeProbability=new HashMap<String, Double>();
		// TODO Auto-generated method stub
		Result=result;
		Iterator itDynamic=Result.ResultDis.keySet().iterator();
		double sum=0;
		while(itDynamic.hasNext())
		{
			String resType=itDynamic.next().toString();
			sum=sum+1/Result.ResultDis.get(resType);
		}
		
		itDynamic=Result.ResultDis.keySet().iterator();
		while(itDynamic.hasNext())
		{
			String resType=itDynamic.next().toString();
			double prob=(1/Result.ResultDis.get(resType))/sum;
			
			DynamictypeProbability.put(resType, prob);
		}
		
		return DynamictypeProbability;
		
	}

	
}
