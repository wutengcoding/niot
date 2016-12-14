package cn.niot.service;

import java.text.DateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.reflect.*;

import net.sf.json.JSONObject;

import cn.niot.dao.*;
import cn.niot.util.FunctionResult;
import cn.niot.util.RecoUtil;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.File;
import java.text.SimpleDateFormat;

public class CreateIoTIDSample {
	static String ERR = "ERR";
	static String OK = "OK";
	static String EMPTY_STRING = "";
	static int LENGTH_RULE_INDEX = 0;

	static HashMap<String, String[]> hashMapTypeToFunctionRules=new HashMap<String, String[]>();// 类型对应函数规则
	static HashMap<String, ArrayList<String>> hashMapTypeToByteRules=new  HashMap<String, ArrayList<String>>();// 类型对应字节规则
	static HashMap<String, String> hashMapTypeToLengthRule=new HashMap<String, String>();// 类型对应长度规则
	static HashMap<String, Integer> hashMapTypeSampleNumber=new HashMap<String, Integer>();// 类型对应规则
	static String FUNCTION_SUFFIX = "_Random";

	/*
	 * Function: read meta data relating to IoTIDs and their rules from table
	 * "iotid" Input:
	 * 
	 * @param type: a flag used to hint which database reading method is used.
	 * Output: NONE creator: Guangqing Deng time: 2014年7月7日
	 */
	//modified by sq
	//2014-8-28
	public static void readDao(int type) {
		hashMapTypeToLengthRule = new HashMap<String, String>();// 类型对应长度规则
		hashMapTypeToByteRules = new HashMap<String, ArrayList<String>>();// 类型对应字节规则
		// hashMapTypeToFunctionRules = new HashMap<String,
		// ArrayList<String>>();// 类型对应函数规则
		hashMapTypeToFunctionRules = new HashMap<String, String[]>();// 类型对应函数规则

		RecoDaoRandom dao = RecoDaoRandom.getRecoDao();
		/*
		 * if lengthRule in table "IoTID" is "15-16", then the corresponding
		 * rule in the hashMapTypeToRules is "IoTIDLength)(?#PARA=15-16){]"
		 * 
		 * if byterule in table "IoTID" is "1,0,0,0,0,240,239,219,48", then the
		 * corresponding rule in the hashMapTypeToRules is
		 * "IoTIDByte)(?#PARA=1,0,0,0,0,240,239,219,48){]"
		 */
		// initiate the data structure of hashMapTypeToRules and
		// hashMapTypeSampleNumber according to table "iotidcode"
		String flag = dao.DBreadIoTIDTypesAndRules(hashMapTypeSampleNumber,
				hashMapTypeToLengthRule, hashMapTypeToByteRules,
				hashMapTypeToFunctionRules);
		
		if (flag != OK) {
			System.out.println("Failed in dao,DBREAD");
						return;
		}
	}

	/*
	 * Function:generate a certain number of samples for each IDTypeInput:NONE
	 * Output:a HashMap. The index of the HashMap is the IDType the value of the
	 * index are the samples related to that typecreator:Guangqing Deng
	 * time:2014年7月25日
	 */
     // modified by sq
	// 2014-08-28
	public static void generateIoTIDSamples() {
        readDao(0);
        int TypeCount=0;
        //用于类型-sample所保存的数据库表名 的映射
        HashMap<String,String> TypetoFileName=new  HashMap<String,String>();
		Iterator iterator_Count = hashMapTypeSampleNumber.keySet().iterator();
		while (iterator_Count.hasNext()) {
			//for 进度显示
			TypeCount++;
			String IDType = iterator_Count.next().toString();
			//String IDType="GA/T_556.10-2007";
			String LengthRule = hashMapTypeToLengthRule.get(IDType);
			ArrayList<String> ByteRuleSet = hashMapTypeToByteRules.get(IDType);
			String[] FunctionRuleSet = hashMapTypeToFunctionRules.get(IDType);

			Integer Number = hashMapTypeSampleNumber.get(IDType);

			ArrayList<String> ArraySamples = new ArrayList<String>();
			
			//产生一个类型的Number个标识
			
			for (int i = 0; i < Number; i++) {
				
				String Result = generateOneIoTIDInstance(IDType, LengthRule,
						ByteRuleSet, FunctionRuleSet);
	               ArraySamples.add(Result);
			}
			
			//将生成的标识输出到E盘sample文件夹，文件格式为.csv
			
			//将类型名转换为可作为文件名的字符转
			String fileName = IDType;
		    String regex="[^0-9,A-Z,a-z]";//去掉非数字和字母
			fileName = fileName.replaceAll(regex, "");
			
			
			TypetoFileName.put(IDType, fileName);
		    String path="e://sample//"+fileName+".csv";
		    //String path="e://sampletest//"+fileName+".txt";
			try{
			File f = new File(path);
			BufferedWriter output = new BufferedWriter(new FileWriter(f,
				true));
			
			//BufferedWriter output = new BufferedWriter(new StreamWriter(new FileOutputStream("myFile.txt"),Charset.forName("UTF-8").newEncoder())));  
			Iterator itSample = ArraySamples.iterator();
			String code="";
			// dgq
			
			int i = 1;
			while(itSample.hasNext()){
			code=itSample.next().toString();
			output.append(code + "," + i++);
			
			output.append("\n");
			}
			/*while(itSample.hasNext()){
				code=itSample.next().toString();
				output.append(code);
				output.append("\n");
				}*/
			output.flush();
			output.close();}catch(Exception e)
			{
				e.printStackTrace();
			}
			System.out.println("Finished "+TypeCount+"  "+IDType);
			
			//释放本类型的hashmap
			RecoDaoRandom.ClearHashMap();
			//break;
		}
		
		//将映射写入csv文件
		try {
			File f1 = new File("e://sample//TypetoFileName.csv");
			BufferedWriter output = new BufferedWriter(new FileWriter(f1, true));
			Iterator iterator = TypetoFileName.keySet().iterator();
			String Type = "";
			String FileName = "";
			while (iterator.hasNext()) {
				Type = iterator.next().toString();
				FileName = TypetoFileName.get(Type);
				output.append(Type);
				output.append(',');
				output.append(FileName);
				output.append("\n");
			}
			output.flush();
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}
	/*public static HashMap<String, ArrayList<String>> generateIoTIDSamples() {
		HashMap<String, ArrayList<String>> hashMapTypeToSamples = new HashMap<String, ArrayList<String>>();// 类型-标识样本

		Iterator iterator_Count = hashMapTypeSampleNumber.keySet().iterator();
		while (iterator_Count.hasNext()) {
			String IDType = iterator_Count.next().toString();

			String LengthRule = hashMapTypeToLengthRule.get(IDType);
			ArrayList<String> ByteRuleSet = hashMapTypeToByteRules.get(IDType);
			String[] FunctionRuleSet = hashMapTypeToFunctionRules.get(IDType);

			Integer Number = hashMapTypeSampleNumber.get(IDType);

			ArrayList<String> ArraySamples = new ArrayList<String>();
			for (int i = 0; i < Number; i++) {
				String Result = generateOneIoTIDInstance(IDType, LengthRule,
						ByteRuleSet, FunctionRuleSet);
				ArraySamples.add(Result);
			}
			hashMapTypeToSamples.put(IDType, ArraySamples);
			//IDType.txt:每一列是一个sample.
			//释放hashMap 
		}
		return hashMapTypeToSamples;
	}*/

	/*
	 * Function:generate a single sample according to the given type and rules
	 * Input:
	 * 
	 * @param IoTID: represents the Type of the sample
	 * 
	 * @param LengthRule:indicates the length of the sample
	 * 
	 * @param IoTIDByteRuleSet: the byte rules rules related to that type
	 * 
	 * @param IoTIDFunctionRuleSet: the function rules related to that type
	 * Output:the code sample creator:Guangqing Deng time:2014年7月25日
	 */
	//modified by sq
	//2014-08-28
	public static String generateOneIoTIDInstance(String IoTID,
			String LengthRule, ArrayList<String> IoTIDByteRuleSet,
			String[] IoTIDFunctionRuleSet) {
		String IoTIDInstance = EMPTY_STRING;
		
		FunctionResult RuleResult = new FunctionResult();
	
		// first, calculate the length of the coming IoTID code
		RuleResult = generateStringByOneRule(LengthRule, RuleResult);
		//if (RuleResult.ResultFlag == ERR) {
		if(RuleResult.ResultFlag.equals(ERR)){
			return EMPTY_STRING;
		}
		
		
		if(IoTIDByteRuleSet!=null)
		{
		for (int i = 0; i < IoTIDByteRuleSet.size(); i++) {
			String Rule = IoTIDByteRuleSet.get(i).toString();
			RuleResult = generateStringByOneRule(Rule, RuleResult);
			if (RuleResult.ResultFlag.equals(ERR)) {
				return EMPTY_STRING;
			}
		}
			
		}
		
		
	
		if(IoTIDFunctionRuleSet!=null){
		for (int i = 0; i <IoTIDFunctionRuleSet.length ; i++) {
			String Rule = IoTIDFunctionRuleSet[i];
			RuleResult = generateStringByOneRule(Rule, RuleResult);
			//if (RuleResult.ResultFlag == ERR) {
			if(RuleResult.ResultFlag.equals(ERR)){
				return EMPTY_STRING;
			}
			
		}  
		}
		
		//将结果转为字符串
		try
		{
			HashMap<Integer, String> FunctionResult = new HashMap<Integer, String>();
			FunctionResult=new HashMap<Integer, String>();
			FunctionResult=RuleResult.FunctionResult;
			Iterator it = FunctionResult.keySet().iterator();
			char CodeResult[]=new char[FunctionResult.size()];
			while (it.hasNext()) {
				Integer key = (Integer) it.next();
				CodeResult[key]=FunctionResult.get(key).charAt(0);
			}
			//IoTIDInstance=CodeResult.toString();
			//IoTIDInstance=Arrays.toString(CodeResult);
			String res="";
		
			for(int i=0;i<CodeResult.length;i++)
			{
				res=res+CodeResult[i];
			}
			IoTIDInstance=res;
		}catch(Exception e)
		{
			System.out.println("HasSomeEmptyBit");
		}
		
		return IoTIDInstance;
	}

	// (match(splitRules[0], splitParameter[0], s))
	// match(String maxRule, String parameter, String input)

	/*
	 * Function:generate a part of the sample according to one rule that related
	 * to the given type Input:
	 * 
	 * @param Rule:the given rule,which could be IoTIDByte,IoTIDLength or
	 * IoTIDFunction
	 * 
	 * @param curIoTID:part of the result that generated by the rules before
	 * this,since one sample is generated by several rules and one single rule
	 * can only determine one or several bits of the sample Output:part of the
	 * samples creator:Guangqing Deng time:2014年7月25日
	 */
	//modified by sq on 2014-08-28
	private static FunctionResult generateStringByOneRule(String Rule,
			FunctionResult InPutResult) {
		/*
		 * if lengthRule in table "IoTID" is "15-16", then the corresponding
		 * rule in the hashMapTypeToRules is "IoTIDLength)(?#PARA=15-16){]"
		 * 
		 * if byterule in table "IoTID" is "1,0,0,0,0,240,239,219,48", then the
		 * corresponding rule in the hashMapTypeToRules is
		 * "IoTIDByte)(?#PARA=1,0,0,0,0,240,239,219,48){]"
		 * 
		 * (?#ALGNAME=MrpCheck)(?#PARA=0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15){]
		 */
		
	
		String[] splitRules = Rule.split("\\)\\(\\?\\#PARA=");// 提取规则名
		String[] splitParameter = splitRules[1].split("\\)\\{\\]");// 提取参数
		String RuleName = splitRules[0] + FUNCTION_SUFFIX;
		String RuleParameter = splitParameter[0];
		Object ResultTemp = "";
		FunctionResult Result = new FunctionResult();
		Result=InPutResult;
		try {
			Class ruleFunctionClass = Class
					.forName("cn.niot.randomization.RuleRandom");// specify the
			
			if(RuleName.equals("IoTIDLength" + FUNCTION_SUFFIX))
			{
				Object[] Args = new Object[1];
				Args[0] = RuleParameter;
				Class[] c = new Class[1];
				c[0] = String.class;
				Method method = ruleFunctionClass.getMethod(RuleName, c);
				ResultTemp = method.invoke(null, Args);
				Result = (FunctionResult) ResultTemp;
				Result.FunctionType = "Length";
			}
			else 
			{
				Object[] Args = new Object[2];
				Class[] c = new Class[2];
                Args[0] = Result;
				Args[1] = RuleParameter;
				c[0] = FunctionResult.class;
				c[1] = String.class;
				Method method = ruleFunctionClass.getMethod(RuleName, c);
				ResultTemp = method.invoke(null, Args);
				Result = (FunctionResult) ResultTemp;
				Result.FunctionType = "Byte";
				if (RuleName.equals("IoTIDByte")) {
					Result.FunctionType = "Byte";
					
				} else {
					Result.FunctionType = "Function";
				
			}
			/*
			if (RuleName.equals("IoTIDByte" + FUNCTION_SUFFIX)
					|| RuleName.equals("IoTIDLength" + FUNCTION_SUFFIX)) {
				Object[] Args = new Object[1];
				Args[0] = RuleParameter;
				Class[] c = new Class[1];
				c[0] = String.class;
				Method method = ruleFunctionClass.getMethod(RuleName, c);
				ResultTemp = method.invoke(null, Args);
				Result = (FunctionResult) ResultTemp;
				if (RuleName.equals("IoTIDByte")) {
					Result.FunctionType = "Byte";
				} else {
					Result.FunctionType = "Length";
				}
			} else {
				Object[] Args = new Object[2];
				Class[] c = new Class[2];

				Args[0] = curIoTID;
				Args[1] = RuleParameter;

				

				c[0] = String.class;
				c[1] = String.class;

				Method method = ruleFunctionClass.getMethod(RuleName, c);
				ResultTemp = method.invoke(null, Args);
				Result = (FunctionResult) ResultTemp;

				Result.FunctionType = "Function";
			}*/
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			Result.ResultFlag = ERR;
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			Result.ResultFlag = ERR;
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			Result.ResultFlag = ERR;
			System.out.println("RuleFunction.java file can not find "
					+ RuleName + " method,error");
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			Result.ResultFlag = ERR;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			Result.ResultFlag = ERR;
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			Result.ResultFlag = ERR;
		}
		return Result;
	}
}
