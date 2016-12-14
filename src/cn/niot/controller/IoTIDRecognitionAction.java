package cn.niot.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map.Entry;

import mypack.TestHibernate;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import cn.unitTest.RuleFuncTest;
import cn.niot.SpecificRecognition.SpecificRecognize;
import cn.niot.SpecificRecognition.Support;
import cn.niot.dao.DaoEmulation;
import cn.niot.dao.RecoDao;
import cn.niot.dao.RecoDaoRandom;
import cn.niot.service.*;
import cn.niot.util.RecoUtil;
import cn.niot.util.SpecificResult;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ActionContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.hadoop.io.Text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

import java.util.Date;
import java.util.Calendar;

import java.text.SimpleDateFormat;

import cn.niot.mpreduce.*;

/**
 * 
 * @Title: RespCode.java
 * @Package cn.niot.zt
 * @Description:
 * @author Zhang Tao
 * @date 2013-12-3
 * @version V1.0
 */

public class IoTIDRecognitionAction extends ActionSupport {

	private String code;

	private String status;

	private String data;

	private String statement;

	private String extraData;

	private String Msg;

	public String getData() {
		return data;
	}

	public String getStatus() {
		return status;
	}

	public String getStatement() {
		return statement;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getExtraData() {
		return extraData;
	}

	public String replaceBlank(String str) {

		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\\t|\\r|\\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

	public String execute() throws Exception {
		int nflag = 0;
		if (1 == nflag) {
			IDstrRecognition.readDao(0);
			IDstrRecognition.testAndTestID();
			System.out.println("The end of this run!!!!\n");

			return SUCCESS;
		}

		// added by sq on 2014-08-28
		if (2 == nflag) {
			CreateIoTIDSample.generateIoTIDSamples();
			System.out.println("The end of generrate!!!!\n");
			// Runtime runtime = Runtime.getRuntime();
			// Process proc = runtime.exec("shutdown -s -t 0");
			// System.exit(0);
			return SUCCESS;
		}
		if (3 == nflag) {
			// 记录开始时间
			Date nowbegin = new Date();
			SimpleDateFormat dateFormatbegin = new SimpleDateFormat(
					"yyyy/MM/dd HH:mm:ss");
			String begin = dateFormatbegin.format(nowbegin);

			// 开始识别
			IDstrRecognition.readDao(0);
			IDstrRecognition.testAndTestIDRandom();
			System.out.println("The end of TestRandom!!!!\n");

			// 记录结束时间
			Date nowfinish = new Date();
			SimpleDateFormat dateFormatfinish = new SimpleDateFormat(
					"yyyy/MM/dd HH:mm:ss");
			String finish = dateFormatfinish.format(nowfinish);

			// 将起始时间写入数据库
			String filedic = "/home/hadoop/eclipseJavaEE/dgq/debugnFlag3/TimeInfo.txt";
			File f6 = new File(filedic);
			BufferedWriter output = new BufferedWriter(new FileWriter(f6, true));
			output.append("Begin_Time:" + "  " + begin);
			output.append("\n");
			output.append("Finish_Time:" + "  " + finish);
			output.append("\n");
			output.flush();
			output.close();
			// Runtime runtime = Runtime.getRuntime();
			// Process proc = runtime.exec("shutdown -s -t 0");
			// System.exit(0);
			return SUCCESS;

		}

		// 统计两个类型

		if (4 == nflag) {

			try {
				int devision = 4;
				int reminder = 1;
				HashMap<String, Double> typeProbability = new HashMap<String, Double>();
				Map<String, Map<Set<String>, List<String>>> type_Samples = new HashMap<String, Map<Set<String>, List<String>>>();
				HashMap<String, String> tableNametoTypeName = new HashMap<String, String>();
				HashMap<String, String> typeNametoTableName = new HashMap<String, String>();
				HashMap<String, Integer> tablenametoID = new HashMap<String, Integer>();
				// 读数据库，建立3张HashMap
				// tableNametoTypeName，typeNametoTableName，tablenametoID
				RecoDaoRandom.TableNametoTypeNametoId(tableNametoTypeName,
						tablenametoID, typeNametoTableName);
				// 建文件 存入已经完成的类型名
				String filePath = "d:" + File.separator + "sample"
						+ File.separator + "data1.txt";
				;
				List<String> typeName_hasdone = RecoDaoRandom
						.readTxtFile(filePath);
				List<String> typeName_list = new ArrayList<String>(
						tableNametoTypeName.values());
				List<String> typeNametoCOPY = new ArrayList<String>(Arrays
						.asList(new String[typeName_list.size()]));
				Collections.copy(typeNametoCOPY, typeName_list);
				typeNametoCOPY.removeAll(typeName_hasdone);
				for (String typeName : typeNametoCOPY) {

					String tableName = typeNametoTableName.get(typeName);
					int id = tablenametoID.get(tableName);
					if (id % devision != reminder) {
						continue;
					}
					// 读标识码，码的长度大于等于6
					List<String> Samples = RecoDaoRandom
							.testSamples_list(tableName);
					Map<Set<String>, List<String>> Samples_types = new HashMap<Set<String>, List<String>>();
					List<String> list = new ArrayList<String>(Arrays
							.asList(new String[typeName_list.size()]));
					Collections.copy(list, typeName_list);
					list.remove(typeName);
					for (String Sample : Samples) {
						if (Sample != null) {
							String IoTcode = IoTcode = replaceBlank(Sample);
							if (IoTcode != null) {
								IDstrRecognition.readDao(0);
								typeProbability = IDstrRecognition
										.IoTIDRecognizeAlg(IoTcode);//
								Samples_types = IDstrRecognition.two_types(
										typeProbability, Samples_types,
										typeName, list, Sample);
							}
						}

					}
					// System.out.println(Samples_types.size());
					// for(Entry<Set<String>, List<String>> _set :
					// Samples_types.entrySet()){
					// System.out.println(_set.getKey()+"  :  "+_set.getValue());
					// }
					// type_Samples.put(typeName, Samples_types);
					IDstrRecognition._outPut_(typeName, Samples_types);
					// 将识别完成的类型写入文件
					RecoDaoRandom.updateContent(filePath, typeName, true);
					System.out.println(typeName + "  finished!");
				}
				// System.out.println(type_Samples.size());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return SUCCESS;
		}

		if (nflag == 5) {
			HashMap<String, String> typetoTablename = RecoDaoRandom
					.TypetoTableName();
			Iterator<String> iterator = typetoTablename.keySet().iterator();
			int type_count = 0;
			while (iterator.hasNext()) {

				int sample_count = 0;
				// 查询映射表，找到该类型的samples所保存的表
				String type = iterator.next();
				String tableName = typetoTablename.get(type);

				// 读入某一类型的所有随机样本
				HashMap<Integer, String> Samples = new HashMap<Integer, String>();
				Samples = RecoDaoRandom.testSamples(tableName);
				Iterator<Integer> iterator1 = Samples.keySet().iterator();
				while (iterator1.hasNext()) {
					sample_count++;

					int index = iterator1.next();
					String code = Samples.get(index);
					HashMap<String, Double> typeProbability = IDstrRecognition
							.IoTIDRecognizeAlg(code);

					if (sample_count % 5000 == 0) {
						System.out.println("The type " + type_count
								+ " finished " + sample_count + "samples ");
					}
				}

				type_count++;

			}
		}

		// 统计805中类型的所有标识的识别情况
		if (7 == nflag) {
			try {
				int devision = 4;
				int reminder = 0;
				// String filePath = "d:" + File.separator + "sample5"
				// + File.separator + "typeName_hasdone.txt";
				String filePath = "/home/hadoop/test" + File.separator
						+ "sample5" + File.separator + "typeName_hasdone.txt";

				String filePath1 = "/home/hadoop/test" + File.separator
						+ "sample5" + File.separator + "sampleTypetoTppes.txt";

				HashMap<String, Double> typeProbability = new HashMap<String, Double>();
				HashMap<String, String> tableNametoTypeName = new HashMap<String, String>();
				HashMap<String, String> typeNametoTableName = new HashMap<String, String>();
				HashMap<String, Integer> tablenametoID = new HashMap<String, Integer>();
				// 读数据库，建立3张HashMap
				// tableNametoTypeName，typeNametoTableName，tablenametoID
				RecoDaoRandom.TableNametoTypeNametoId_(tableNametoTypeName,
						tablenametoID, typeNametoTableName);
				// 该文件记录已经完成的类型

				List<String> typeName_hasdone = RecoDaoRandom
						.readTxtFile(filePath);
				List<String> typeName_list = new ArrayList<String>(
						tableNametoTypeName.values());
				List<String> typeNametoCOPY = new ArrayList<String>(Arrays
						.asList(new String[typeName_list.size()]));
				Collections.copy(typeNametoCOPY, typeName_list);
				typeNametoCOPY.removeAll(typeName_hasdone);
				// 未完成的类型识别
				for (String typeName : typeNametoCOPY) {
					String tableName = typeNametoTableName.get(typeName);
					int id = tablenametoID.get(tableName);
					if (id % devision != reminder) {
						continue;
					}
					// 读标识码
					List<String> Samples = RecoDaoRandom
							.testSamples_list_(tableName);
					// System.out.println(Samples.size());
					// Map<Set<String>, List<String>> Samples_types = new
					// HashMap<Set<String>, List<String>>();
					Map<HashMap<Integer, String>, List<String>> Sample_types = new HashMap<HashMap<Integer, String>, List<String>>();
					for (int i = 0; i < Samples.size(); i++) {

						String Sample = Samples.get(i);
						if (Sample != null) {
							// Sample 会重复
							HashMap<Integer, String> num_Sample = new HashMap<Integer, String>();
							String code = replaceBlank(Sample);
							if (code != null) {
								IDstrRecognition.readDao(0);
								typeProbability = IDstrRecognition
										.IoTIDRecognizeAlg(code);//
								Set<String> types_set = typeProbability
										.keySet();
								num_Sample.put(i, Sample);
								// System.out.println(Sample);
								Sample_types.put(num_Sample,
										new ArrayList<String>(types_set));
							}
						}
					}
					// 结果输出
					System.out.println(Sample_types.size());

					RecoDaoRandom.updateContent_(filePath1, Sample_types, true,
							typeName);
					// 将识别完成的类型写入文件
					RecoDaoRandom.updateContent(filePath, typeName, true);
					System.out.println(typeName + "  finished!");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return SUCCESS;

		}
		
		

		// if (8 == nflag) {
		// IoTIDMapper mapTest;
		// mapTest.map(key, value, context);
		//
		// return SUCCESS;
		// }
		
		//test for 精确识别
		//计算一个冲突域
		//added by SQ on 1118
		
		
		if (nflag == 9) {
			// 导入测试集码的类型；模拟Mapper()
			ArrayList<String> testtypeSet = new ArrayList<String>();
			testtypeSet = DaoEmulation.getTypeSet1();
			Iterator it_type = testtypeSet.iterator();
			
			//下同Mapper()一样
			while (it_type.hasNext()) {
				String testType = it_type.next().toString();
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

					String path_detail="E:/Distance/Test.txt";
					Support.Report(reduceValue, path_detail);

					// 判断是否为top3
					ArrayList<String> top3 = new ArrayList<String>();
					HashMap<String,Double> input=new HashMap<String,Double>();
					input=Result.ResultDis;
					top3 = SpecificRecognize.SelectTop3(input);
					if (top3.contains(testType))
						Top3_count++;

					
					
				}
				String StatInfo = Support.WriteStaticInfo(testType, OK_count,
						Top3_count, ERR_count);
				String path_stat="E:/Distance/Stat.txt";
				Support.Report(StatInfo, path_stat);
				break;
			}

		}
		
		//模拟MP
		if (nflag == 15) {
			// 读入文件，模拟Mapper
			String path = "E:/iotidSpecific.txt";
			File f1 = new File(path);
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new FileReader(f1));
				String tempString = null;
				// 一次读入一行，直到读入null为文件结束
				while ((tempString = reader.readLine()) != null) {
                   
					String strDemiliter = "{] ";
					int ndelimiter = tempString.indexOf(strDemiliter);
					String iotidcode = tempString.substring(0, ndelimiter);
					String testType = tempString.substring(ndelimiter + 3,
							tempString.length());
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

					if (typeProbability.size() != 0) {

						try {
							Result = SpecificRecognize.SpecificRecognize(
									iotidcode, typeSet);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							System.out
									.println("@@@@@@@@@:Exception In SpecificReco;");
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
						String flag_top3 = "";

						if (testType.equals(MinType)) {
							flag_top1 = "OK";
							// OK_count++;
						} else {
							flag_top1 = "ERR";
							// ERR_count++;
						}
						String reduceValue = "";
						// reduceValue =
						// Support.WriteSpecificDebugInfo(testType, Result,
						// flag, iotidcode);

						// 判断是否为top3
						ArrayList<String> top3 = new ArrayList<String>();
						HashMap<String, Double> input = new HashMap<String, Double>();
						input = Result.ResultDis;
						top3 = SpecificRecognize.SelectTop3(input);
						if (top3.contains(testType))
							// Top3_count++;
							flag_top3 = "OK";
						else
							flag_top3 = "ERR";

						reduceValue = testType + "  " + iotidcode + "  "
								+ flag_top1 + "  " + flag_top3;

						// context.write(new Text(testType), new
						// Text(reduceValue));
						String path_stat = "E:/Distance/Stat.txt";
						Support.Report(reduceValue, path_stat);

					} else {
						String reduceValue = "";
						// reduceValue =
						// Support.WriteSpecificDebugInfo(testType, Result,
						// flag, iotidcode);
						String flag_top1 = "ERR";
						String flag_top3 = "ERR";
						reduceValue = testType + "  " + iotidcode + "  "
								+ flag_top1 + "  " + flag_top3;
						// context.write(new Text(testType), new
						// Text(reduceValue));
						String path_stat = "E:/Distance/Stat.txt";
						Support.Report(reduceValue, path_stat);
					}

				}
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e1) {
					}
				}
			}

		}
		//单个类型测试
		if(nflag==11)
		{
			
			    String testType="JingdongCommodity";
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
					//String iotidcode = "5927231149843630703";
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

					String path_detail="E:/Distance/Test.txt";
					Support.Report(reduceValue, path_detail);

					// 判断是否为top3
					ArrayList<String> top3 = new ArrayList<String>();
					HashMap<String,Double> input=new HashMap<String,Double>();
					try{
					input=Result.ResultDis;
					top3 = SpecificRecognize.SelectTop3(input);
					}catch(Exception e)
					{
						System.out
						.println("!!!!!!!!!!!!!!!!!!!!!ExceptionIN top3SELECT!!!");
					}
					if (top3.contains(testType))
						Top3_count++;

					
					
				}
				String StatInfo = Support.WriteStaticInfo(testType, OK_count,
						Top3_count, ERR_count);
				String path_stat="E:/Distance/Stat.txt";
				Support.Report(StatInfo, path_stat);
				
			

		}
		
		//演示版本：动态识别;只测102个类型以内
		if(nflag==10)
		{
			String IoTcode = null;
			if (this.code != null) {
				IoTcode = replaceBlank(this.code);

			}
			
			
			if (IoTcode != null) {
				IDstrRecognition.readDao(0);
				//第一步：静态识别
				HashMap<String, Double> typeProbability = IDstrRecognition
						.IoTIDRecognizeAlg(IoTcode);
				
				// 将静态识别结果集存入ArrayList
				SpecificResult Result = new SpecificResult();
				ArrayList<String> typeSet = new ArrayList<String>();
				Iterator itTypes = typeProbability.keySet().iterator();
				while (itTypes.hasNext()) {
					String type = itTypes.next().toString();
					// 检验是否完成仿真生成
					if (Support.IsEmulated(type))
						typeSet.add(type);
				}
				
				// 第二步：精确识别：
				// 得到静态中心向量
				try {
					Support.getStaticCenterVector();
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				try {
					Result = SpecificRecognize
							.SpecificRecognize(IoTcode, typeSet);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					// e1.printStackTrace();
				}
				
				//第三步：结果显示
				HashMap<String, Double> ShortName_Probability = new HashMap<String, Double>();
				HashMap<String, Double> DynamictypeProbability=new HashMap<String, Double>();
				DynamictypeProbability=Support.ComputeResultProb(Result);
				
				
				JSONObject jsonObjectRes = IDstrRecognition.getTwoNamesByIDCode(
						DynamictypeProbability, ShortName_Probability);
				this.extraData = (jsonObjectRes.toString()).replace("\"", "\'");

				int len = ShortName_Probability.size();
				if (RecoUtil.NO_ID_MATCHED == len) {
					this.status = String.valueOf(RecoUtil.NO_ID_MATCHED);
					// this.status = "1";
				} else if (RecoUtil.ONE_ID_MATCHED == len) {
					Iterator iterator = ShortName_Probability.keySet().iterator();
					while (iterator.hasNext()) {
						Object key = iterator.next();
						this.data = String.valueOf(key);
						this.status = String.valueOf(RecoUtil.ONE_ID_MATCHED);
					}
				} else {
					this.status = String.valueOf(len);

					JSONArray jsonArray = new JSONArray();
					Iterator iterator2 = ShortName_Probability.keySet().iterator();
					while (iterator2.hasNext()) {
						Object key = iterator2.next();
						JSONObject jsonObject = new JSONObject();
						double probability = ShortName_Probability.get(key);
						jsonObject.put("codeName", String.valueOf(key));
						jsonObject.put("probability", String.valueOf(probability));
						if (!jsonArray.add(jsonObject)) {
							System.out.println("ERROR! jsonArray.add(jsonObject)");
						}
						this.data = jsonArray.toString();
					}
				}

			}

			System.out.println("\nthis.data:   " + this.data);
			System.out.println("\nthis.extraData:   " + this.extraData);
			return SUCCESS;
		
		}
		
		if(nflag==0)
		{
		String IoTcode = null;
		if (this.code != null) {
			IoTcode = replaceBlank(this.code);

		}

		if (IoTcode != null) {
			IDstrRecognition.readDao(0);
			HashMap<String, Double> typeProbability = IDstrRecognition
					.IoTIDRecognizeAlg(IoTcode);
			IDstrRecognition.IoTIDRecognizeAlg1();

			// added by dgq on 2014-04-29, to remove those items with
			// probability of 0.0
			// Iterator iterator_IDPro = typeProbability.keySet().iterator();
			// while (iterator_IDPro.hasNext()) {
			// String key_IDstd = iterator_IDPro.next().toString();
			// double probability = typeProbability.get(key_IDstd);
			// if (0 >= probability) {
			// iterator_IDPro.remove();
			// }
			// }

			// // added by dgq on 2014-04-29, to remove those items with
			// // probability of 0.0
			// Iterator iterator_IDPro = typeProbability.keySet().iterator();
			// double sumPro = 0;
			// while (iterator_IDPro.hasNext()) {
			// String key_IDstd = iterator_IDPro.next().toString();
			// double probability = typeProbability.get(key_IDstd);
			// if (0 >= probability) {
			// //iterator_IDPro.remove();
			// probability = 0.1;
			// typeProbability.put(key_IDstd, probability);
			// }
			// sumPro = sumPro + probability;
			// }
			//			
			// iterator_IDPro = typeProbability.keySet().iterator();
			// while (iterator_IDPro.hasNext()) {
			// String key_IDstd = iterator_IDPro.next().toString();
			// double probability = typeProbability.get(key_IDstd);
			// typeProbability.put(key_IDstd, probability / sumPro);
			// }

			HashMap<String, Double> ShortName_Probability = new HashMap<String, Double>();
			JSONObject jsonObjectRes = IDstrRecognition.getTwoNamesByIDCode(
					typeProbability, ShortName_Probability);
			this.extraData = (jsonObjectRes.toString()).replace("\"", "\'");

			int len = ShortName_Probability.size();
			if (RecoUtil.NO_ID_MATCHED == len) {
				this.status = String.valueOf(RecoUtil.NO_ID_MATCHED);
				// this.status = "1";
			} else if (RecoUtil.ONE_ID_MATCHED == len) {
				Iterator iterator = ShortName_Probability.keySet().iterator();
				while (iterator.hasNext()) {
					Object key = iterator.next();
					this.data = String.valueOf(key);
					this.status = String.valueOf(RecoUtil.ONE_ID_MATCHED);
				}
			} else {
				this.status = String.valueOf(len);

				JSONArray jsonArray = new JSONArray();
				Iterator iterator2 = ShortName_Probability.keySet().iterator();
				while (iterator2.hasNext()) {
					Object key = iterator2.next();
					JSONObject jsonObject = new JSONObject();
					double probability = ShortName_Probability.get(key);
					jsonObject.put("codeName", String.valueOf(key));
					jsonObject.put("probability", String.valueOf(probability));
					if (!jsonArray.add(jsonObject)) {
						System.out.println("ERROR! jsonArray.add(jsonObject)");
					}
					this.data = jsonArray.toString();
				}
			}

		}

		System.out.println("\nthis.data:   " + this.data);
		System.out.println("\nthis.extraData:   " + this.extraData);
		return SUCCESS;
		}
		
		return SUCCESS;
	}

	public static void main(String[] args) throws Exception {
		try {
			int devision = 4;
			int reminder = 0;
			// String filePath = "d:" + File.separator + "sample5"
			// + File.separator + "typeName_hasdone.txt";
			String filePath = "/home/hadoop/test" + File.separator + "sample5"
					+ File.separator + "typeName_hasdone.txt";

			String filePath1 = "/home/hadoop/test" + File.separator + "sample5"
					+ File.separator + "sampleTypetoTppes.txt";

			int count = 0;

			HashMap<String, Double> typeProbability = new HashMap<String, Double>();
			HashMap<String, String> tableNametoTypeName = new HashMap<String, String>();
			HashMap<String, String> typeNametoTableName = new HashMap<String, String>();
			HashMap<String, Integer> tablenametoID = new HashMap<String, Integer>();
			// 读数据库，建立3张HashMap
			// tableNametoTypeName，typeNametoTableName，tablenametoID
			RecoDaoRandom.TableNametoTypeNametoId_(tableNametoTypeName,
					tablenametoID, typeNametoTableName);
			// 该文件记录已经完成的类型

			List<String> typeName_hasdone = RecoDaoRandom.readTxtFile(filePath);
			List<String> typeName_list = new ArrayList<String>(
					tableNametoTypeName.values());
			List<String> typeNametoCOPY = new ArrayList<String>(Arrays
					.asList(new String[typeName_list.size()]));
			Collections.copy(typeNametoCOPY, typeName_list);
			typeNametoCOPY.removeAll(typeName_hasdone);
			// 未完成的类型识别
			for (String typeName : typeNametoCOPY) {
				String tableName = typeNametoTableName.get(typeName);
				int id = tablenametoID.get(tableName);
				if (id % devision != reminder) {
					continue;
				}
				// 读标识码
				List<String> Samples = RecoDaoRandom
						.testSamples_list_(tableName);
				// System.out.println(Samples.size());
				// Map<Set<String>, List<String>> Samples_types = new
				// HashMap<Set<String>, List<String>>();
				Map<HashMap<Integer, String>, List<String>> Sample_types = new HashMap<HashMap<Integer, String>, List<String>>();
				for (int i = 0; i < Samples.size(); i++) {

					String Sample = Samples.get(i);
					if (Sample != null) {
						// Sample 会重复
						HashMap<Integer, String> num_Sample = new HashMap<Integer, String>();
						String code = Sample.replace(" ", "");
						if (code != null) {
							IDstrRecognition.readDao(0);
							typeProbability = IDstrRecognition
									.IoTIDRecognizeAlg(code);//
							System.out.println(typeProbability);

							if (count % 100 == 0) {
								System.out.println(count);
								count++;
							}
							Set<String> types_set = typeProbability.keySet();
							num_Sample.put(i, Sample);
							// System.out.println(Sample);
							Sample_types.put(num_Sample, new ArrayList<String>(
									types_set));
						}
					}
				}
				// 结果输出
				System.out.println(Sample_types.size());

				RecoDaoRandom.updateContent_(filePath1, Sample_types, true,
						typeName);
				// 将识别完成的类型写入文件
				RecoDaoRandom.updateContent(filePath, typeName, true);
				System.out.println(typeName + "  finished!");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
}
