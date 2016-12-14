package cn.niot.service;

import java.text.DateFormat;
import java.util.*;
import java.lang.reflect.*;
import java.util.Map.Entry;

import net.sf.json.JSONObject;

import cn.niot.dao.*;
import cn.niot.util.JdbcUtils;
import cn.niot.util.RecoUtil;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.File;
import java.text.SimpleDateFormat;

public class IDstrRecognition {
	static long sum=0;
	static String DEBUG = "OFF";// the value of DEBUG can be "ON" or "OFF"
	static String DEBUG_RES = "OFF";// the value of DEBUG_RES can be "ON" or
									// "OFF"
	static String DEBUG_LINE = "OFF";// the value of DEBUG_LINE can be "ON" or
										// "OFF"
	static String DEBUG_TIME = "OFF";// the value of DEBUG_TIME can be "ON" or
										// "OFF"
	static int line = 0;

	static HashMap<String, Double> rmvRuleSet = new HashMap<String, Double>();// rmvRuleSet<规则名，权重>;
	static HashMap<String, Double> rmvIDSet = new HashMap<String, Double>();// rmvIDSet<类型名，先验概率>;
	static HashMap<String, ArrayList<String>> hashMapTypeToRules = new HashMap<String, ArrayList<String>>();// 类型对应规则
	static HashMap<String, ArrayList<String>> hashMapRuleToTypes  = new HashMap<String, ArrayList<String>>();// 规则对应类型;

	static HashMap<String, Double> rmvRuleSetTemp = new HashMap<String, Double>();// rmvRuleSet<规则名，权重>;
	static HashMap<String, Double> rmvIDSetTemp = new HashMap<String, Double>();// rmvIDSet<类型名，先验概率>;
	static HashMap<String, ArrayList<String>> hashMapTypeToRulesTemp = new HashMap<String, ArrayList<String>>();// 类型对应规则
	static HashMap<String, ArrayList<String>> hashMapRuleToTypesTemp  = new HashMap<String, ArrayList<String>>();// 规则对应类型;
	
    
	HashMap<String, Double> rmvRuleSet1 = new HashMap<String, Double>();// rmvRuleSet<规则名，权重>;
	HashMap<String, Double> rmvIDSet1 = new HashMap<String, Double>();// rmvIDSet<类型名，先验概率>;
	HashMap<String, ArrayList<String>> hashMapTypeToRules1 = new HashMap<String, ArrayList<String>>();// 类型对应规则
	HashMap<String, ArrayList<String>> hashMapRuleToTypes1  = new HashMap<String, ArrayList<String>>();// 规则对应类型;
	public static void readDao(int type) {
		RecoDao dao = RecoDao.getRecoDao();
		if (type == 0) {
			if (0 == hashMapRuleToTypes.size()) {
				hashMapTypeToRules = dao.DBreadTypeAndRules(rmvRuleSet, rmvIDSet,
						hashMapRuleToTypes);
			}	
		} else {
			hashMapTypeToRules = dao.HibernateDBreadTypeAndRules(rmvRuleSet,
					rmvIDSet, hashMapRuleToTypes);
		}
	}

	public static HashMap<String, Double> IoTIDRecognizeAlg(String s) {
		rmvRuleSetTemp = (HashMap<String, Double>)rmvRuleSet.clone();// rmvRuleSet<规则名，权重>;
		System.out.println("Total Rules: "+rmvRuleSetTemp.size());
		rmvIDSetTemp = (HashMap<String, Double>)rmvIDSet.clone();// rmvIDSet<类型名，先验概率>;
		hashMapTypeToRulesTemp = (HashMap<String, ArrayList<String>>)hashMapTypeToRules.clone();// 类型对应规则
		hashMapRuleToTypesTemp  = (HashMap<String, ArrayList<String>>)hashMapRuleToTypes.clone();// 规则对应类型;
		
		HashMap<String, Double> typeProbability = new HashMap<String, Double>();

		long timeDaoBegin = 0, timeDao = 0, timeSortRulesBegin = 0, timeSortRules = 0, timeMatchBegin = 0, timeMatch = 0, timeSubtractionBegin = 0, timeSubtraction = 0, timeUnionBegin = 0, timeUnion = 0;

		ArrayList<String> rulesList;
		sortRules(); //Temporarily moved by dgq, 2014-04-21
		//*
		int count=0;
		int Success_count=0;
		int Failcount=0;
		while (rmvIDSetTemp.size() != 0 && rmvRuleSetTemp.size() != 0) {
			count++;
			if ("ON" == DEBUG_TIME) {
				timeSortRulesBegin = System.currentTimeMillis();
			}
			// Temporarily moved by dgq, 2014-04-21
			if ("ON" == DEBUG_TIME) {
				timeSortRules += (System.currentTimeMillis() - timeSortRulesBegin);
			}
			String maxRule = getMax();// Temporarily moved by dgq, 2014-04-21
			// String maxRule = getMax_dgq();// Temporarily added by dgq,
			// 2014-04-21
			String[] splitRules = maxRule.split("\\)\\(\\?\\#PARA=");// 提取规则名
			String[] splitParameter = splitRules[1].split("\\)\\{\\]");// 提取参数
			if ("ON" == DEBUG) {
				System.out.print("matching " + splitRules[0] + "("
						+ splitParameter[0] + ").");
			}
			if ("ON" == DEBUG_TIME) {
				timeMatchBegin = System.currentTimeMillis();
			}
			
			if (match(splitRules[0], splitParameter[0], s)) {
				// intersection(rmvIDSet, hashMapRuleToTypesTemp.get(maxRule));
				Success_count++;
				if ("ON" == DEBUG_TIME) {
					timeMatch += (System.currentTimeMillis() - timeMatchBegin);
				}
				if ("ON" == DEBUG) {
					System.out.println("OK");
				}
				rmvRuleSetTemp.remove(maxRule);
			} else {
				Failcount++;
				subtraction(rmvIDSetTemp, hashMapRuleToTypesTemp.get(maxRule));
				union(maxRule);
				if ("ON" == DEBUG_TIME) {
					timeMatch += (System.currentTimeMillis() - timeMatchBegin);
				}

				if ("ON" == DEBUG) {
					System.out.println("ERR");
				}
				if ("ON" == DEBUG_TIME) {
					timeSubtractionBegin = System.currentTimeMillis();
				}

				if ("ON" == DEBUG_TIME) {
					//subtraction(rmvIDSet, hashMapRuleToTypesTemp.get(maxRule));
					timeSubtraction += (System.currentTimeMillis() - timeSubtractionBegin);
				}
				if ("ON" == DEBUG_TIME) {
					timeUnionBegin = System.currentTimeMillis();
				}

				if ("ON" == DEBUG_TIME) {
					//union(maxRule);
					timeUnion += (System.currentTimeMillis() - timeUnionBegin);
					
				}
			}	

		}	
		System.out.println("Success:"+Success_count);
		System.out.println("Failed:"+Failcount);
		System.out.println("Total:"+count);
		if ("ON" == DEBUG_TIME)
			System.out.println("读数据库用时：" + timeDao + ",SortRules用时："
					+ timeSortRules + ",Match用时：" + timeMatch
					+ ",Subtraction用时：" + timeSubtraction + ",Union用时："
					+ timeUnion);
		Date now = new Date();
		DateFormat d1 = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG);
		if ("ON" == DEBUG) {
			System.out.print("DEBUG: " + d1.format(now) + ":");
		}

		double totalProbabity = 0;
		if (rmvIDSetTemp.size() == 0) {
			if ("ON" == DEBUG) {
				System.out.println(s + " doesn't belong any Type.");
			}
		} else {
			if ("ON" == DEBUG_RES) {
				System.out.print(s + " belong to:");
			}
			Iterator<String> iterator = rmvIDSetTemp.keySet().iterator();
			while (iterator.hasNext()) {
				Object key = iterator.next();
				totalProbabity = totalProbabity + rmvIDSetTemp.get(key);
				if ("ON" == DEBUG_RES) {
					System.out.print("DEBUG_RES" + (String) key + " ");
				}
			}
			if ("ON" == DEBUG_RES) {
				System.out.println("DEBUG_RES" + "");
			}
		}
		if ("ON" == DEBUG_LINE) {
			line = line + 1;
			System.out.println(line);
		}
		Iterator<String> iterator2 = rmvIDSetTemp.keySet().iterator();
		Date today = new Date();
		SimpleDateFormat f = new SimpleDateFormat("HH");
		String time = f.format(today);
		while (iterator2.hasNext()) {
			Object key2 = iterator2.next();
			double probability = 0;
			if (totalProbabity != 0){
				probability = rmvIDSetTemp.get(key2) / totalProbabity;
			}			
			typeProbability.put(String.valueOf(key2), probability);
		}
		return typeProbability;
	}
	
	//Traverse with original version
	public static void IoTIDRecognizeAlg1() {
		HashMap<String, Double> rmvRuleSetTemp1 = new HashMap<String, Double>();// rmvRuleSet<规则名，权重>;
		HashMap<String, Double> rmvIDSetTemp1 = new HashMap<String, Double>();// rmvIDSet<类型名，先验概率>;
		
		rmvRuleSetTemp1 = (HashMap<String, Double>)rmvRuleSet.clone();// rmvRuleSet<规则名，权重>;
		rmvIDSetTemp1 = (HashMap<String, Double>)rmvIDSet.clone();// rmvIDSet<类型名，先验概率>;
		int level=0;
		int right_level=0;
		int left_level=0;
	   
		Traverse(rmvRuleSetTemp1,rmvIDSetTemp1,level,left_level);
		System.out.println("Total Nodes:"+sum);
		
	}
	
	//DP
	public static void IoTIDRecognizeAlg2() {
		HashMap<String, Double> rmvRuleSetTemp1 = new HashMap<String, Double>();// rmvRuleSet<规则名，权重>;
		HashMap<String, Double> rmvIDSetTemp1 = new HashMap<String, Double>();// rmvIDSet<类型名，先验概率>;
		
		rmvRuleSetTemp1 = (HashMap<String, Double>)rmvRuleSet.clone();// rmvRuleSet<规则名，权重>;
		rmvIDSetTemp1 = (HashMap<String, Double>)rmvIDSet.clone();// rmvIDSet<类型名，先验概率>;
		int level=0;
		int right_level=0;
		int left_level=0;
	   
		Traverse(rmvRuleSetTemp1,rmvIDSetTemp1,level,left_level);
		System.out.println("Total Nodes:"+sum);
		
	}


	public static  void Traverse(HashMap<String, Double> rmvRuleSetTemp1,
			HashMap<String, Double> rmvIDSetTemp1,int level,int left_level) {
		// TODO Auto-generated method stub
		HashMap<String, Double> rmvRuleSetTemp2 = new HashMap<String, Double>();// rmvRuleSet<规则名，权重>;
		HashMap<String, Double> rmvIDSetTemp2 = new HashMap<String, Double>();// rmvIDSet<类型名，先验概率>;
		rmvRuleSetTemp2=(HashMap<String, Double>)rmvRuleSetTemp1.clone();
		rmvIDSetTemp2=(HashMap<String, Double>)rmvIDSetTemp1.clone();
		
		HashMap<String, Double> rmvRuleSetTemp3 = new HashMap<String, Double>();// rmvRuleSet<规则名，权重>;
		HashMap<String, Double> rmvIDSetTemp3 = new HashMap<String, Double>();// rmvIDSet<类型名，先验概率>;
		rmvIDSetTemp3=(HashMap<String, Double>)rmvIDSetTemp1.clone();
		rmvRuleSetTemp3=(HashMap<String, Double>)rmvRuleSetTemp1.clone();
		
		level++;
		//System.out.println("LEVEL:"+level);
		if (rmvIDSetTemp1.size() != 0 && rmvRuleSetTemp1.size() != 0) {
		
			rmvRuleSetTemp1=sortRules1(rmvRuleSetTemp1,rmvIDSetTemp1); 
			String maxRule = getMax1(rmvRuleSetTemp1);

			
			//匹配不成功 
			rmvIDSetTemp3=subtraction1(rmvIDSetTemp3, hashMapRuleToTypesTemp.get(maxRule));
			rmvRuleSetTemp3=union1(maxRule,rmvIDSetTemp3,rmvRuleSetTemp3);
			Traverse(rmvRuleSetTemp3,rmvIDSetTemp3,level,left_level);
			
			
			//匹配成功
			left_level++;
			rmvRuleSetTemp2.remove(maxRule);
			Traverse(rmvRuleSetTemp2,rmvIDSetTemp2,level,left_level);
			
			
			
			}
		
		else
		{
	
			//System.out.println("level:"+level);
			//sum=sum+level;
			sum=sum+1;
			return;
		}
		
		
	}

	

	private static HashMap<String, Double> union1(String maxRule,
			HashMap<String, Double> rmvIDSetTemp3,
			HashMap<String, Double> rmvRuleSetTemp3) {
		Iterator<String> iter = rmvIDSetTemp3.keySet().iterator();// IoT ID set

		//ArrayList<String> arrayList = new ArrayList<String>(); 
		ArrayList<String> arrayList_Rules;
		HashSet<String> arrayList = new HashSet<String>();

		while (iter.hasNext()) {
			String ID_key = (String) iter.next();

			arrayList_Rules = new ArrayList<String>();
			arrayList_Rules = hashMapTypeToRulesTemp.get(ID_key);

			for (String rule : arrayList_Rules) {
				arrayList.add(rule);
			}
		}

		Iterator<String> iterator = rmvRuleSetTemp3.keySet().iterator();

		while (iterator.hasNext()) {
			String Rule_key = iterator.next();

			if (!arrayList.contains((String)Rule_key)) { 
				// rmvRuleSet.remove(Rule_key);
				iterator.remove();
			}
		}
		rmvRuleSetTemp3.remove(maxRule);
		return rmvRuleSetTemp3;
		
	}

	private static HashMap<String, Double> subtraction1(
			HashMap<String, Double> rmvIDSetTemp1, ArrayList<String> arrayList) {
		Iterator<String> iterator = rmvIDSetTemp1.keySet().iterator();
		

		while (iterator.hasNext()) {
			String temp = (String) iterator.next();

			if (arrayList.indexOf(temp) >= 0) { // ��arrayList���ҵ���
				// rmvIDSetTemp.remove(temp);
				iterator.remove();
			}
		}
		
		return rmvIDSetTemp1;
	}

	private static String getMax1(HashMap<String, Double> rmvRuleSetTemp1) {
		Set<String> keySet = rmvRuleSetTemp1.keySet();
		Iterator ikey = keySet.iterator();
		String maxName = (String) ikey.next();
		String nextName = "";
		double max = rmvRuleSetTemp1.get(maxName);
		double next = 0;

		while (ikey.hasNext()) {
			nextName = (String) ikey.next();
			next = rmvRuleSetTemp1.get(nextName);
			String[] functionHead=nextName.split("\\)\\(\\?\\#PARA=");
			if(functionHead[0].equals("IoTIDLength")){
				return nextName;
			}
			if(functionHead[0].equals("IoTIDByte")){
				return nextName;
			}
			if (next > max) {
				max = next;
				maxName = nextName;
			}
		}
		return maxName;
	}

	public static JSONObject getTwoNamesByIDCode(
			HashMap<String, Double> HashMapID2Probability,
			HashMap<String, Double> ShortName_Probability) {
		Iterator iterator_t = HashMapID2Probability.keySet().iterator();
		HashMap<String, String> IDCode_ChineseName = new HashMap<String, String>();
		HashMap<String, String> IDCode_ShortName = new HashMap<String, String>();
		ShortName_Probability.clear();

		RecoDao dao = new RecoDao();
		int nAppendedIndex = 0;
		while (iterator_t.hasNext()) {
			String key_IDstd = iterator_t.next().toString();
			double probability = HashMapID2Probability.get(key_IDstd);

			String ChineseName = dao.getIDDetail(key_IDstd);
			IDCode_ChineseName.put(key_IDstd, ChineseName);

			char[] ShortName = new char[RecoUtil.DISPLAYLENGTH];
			int nIndex = 0;
			for (int i = 0; i < key_IDstd.length(); i++) {
				char charTemp = key_IDstd.charAt(i);
				if ((charTemp >= '0' && charTemp <= '9')
						|| (charTemp >= 'a' && charTemp <= 'z')
						|| (charTemp >= 'A' && charTemp <= 'Z')) {
					ShortName[nIndex] = charTemp;
					nIndex++;
					if (nIndex >= RecoUtil.DISPLAYLENGTH) {
						break;
					}
				}
			}
			String CurShortName = (String.valueOf(ShortName)).trim();
			if (ShortName_Probability.containsKey(CurShortName)) {
				String ResShortName = CurShortName
						+ String.valueOf(nAppendedIndex);
				nAppendedIndex++;
				IDCode_ShortName.put(key_IDstd, ResShortName);
				ShortName_Probability.put(ResShortName, probability);
			} else {
				IDCode_ShortName.put(key_IDstd, CurShortName);
				ShortName_Probability.put(CurShortName, probability);
			}
		}
		// ////////////////////////////////////////////////////////////////////
		JSONObject jsonObjectRes = new JSONObject();

		Iterator iterator_temp = HashMapID2Probability.keySet().iterator();
		while (iterator_temp.hasNext()) {
			String key_IDstd = iterator_temp.next().toString();
			String ChineseName = (IDCode_ChineseName.get(key_IDstd)).toString();
			String ShortName = (IDCode_ShortName.get(key_IDstd)).toString();
			JSONObject jsonObject2 = new JSONObject();
			jsonObject2.put("fullName", ChineseName);
			jsonObject2.put("codeNum", key_IDstd);
			jsonObjectRes.put(ShortName, jsonObject2);
		}
		return jsonObjectRes;
	}

	private static boolean match(String maxRule, String parameter, String input) {
		try {
			String[] arg = new String[2];
			arg[0] = parameter;
			arg[1] = input;
			Object result = "";
			Object[] argOther = new Object[4];
			Class[] c = new Class[4];
			Class ruleFunctionClass = Class
					.forName("cn.niot.rule.RuleFunction");
			//System.out.println("Rule:"+maxRule+"     "+"para"+parameter+"   "+input);

			if (maxRule.equals("IoTIDByte")) {
				argOther[0] = input;
				argOther[1] = parameter;
				argOther[2] = "";
				argOther[3] = "";
				c[0] = argOther[0].getClass();
				c[1] = argOther[1].getClass();
				c[2] = argOther[2].getClass();
				c[3] = argOther[3].getClass();
			} else if (maxRule.equals("IoTIDLength")) {
				argOther[0] = input;
				argOther[1] = 0;
				argOther[2] = parameter;
				argOther[3] = 0;
				c[0] = String.class;
				c[1] = int.class;
				c[2] = String.class;
				c[3] = int.class;
			} else {
				argOther[0] = input.toCharArray();
				argOther[1] = input.length();
				String[] splitString = parameter.split(",");
				int[] index = new int[splitString.length];
				for (int i = 0; i < splitString.length; i++) {
					index[i] = Integer.parseInt(splitString[i]);
				}
				argOther[2] = index;
				argOther[3] = index.length;
				c[0] = char[].class;
				c[1] = int.class;
				c[2] = int[].class;
				c[3] = int.class;
			}
			Method method = ruleFunctionClass.getMethod(maxRule, c);
			result = method.invoke(null, argOther);
			if (result == "OK")
				return true;
			if (result == "ERR")
				return false;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("RuleFunction.java file can not find " + maxRule
					+ " method,error");
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	// some comments added by dengguangqing, on 2014-04-21
	// get the rule with the largest weight
	private static String getMax() {
		Set<String> keySet = rmvRuleSetTemp.keySet();
		Iterator ikey = keySet.iterator();
		String maxName = (String) ikey.next();
		String nextName = "";
		double max = rmvRuleSetTemp.get(maxName);
		double next = 0;

		while (ikey.hasNext()) {
			nextName = (String) ikey.next();
			next = rmvRuleSetTemp.get(nextName);
			String[] functionHead=nextName.split("\\)\\(\\?\\#PARA=");
			if(functionHead[0].equals("IoTIDLength")){
				return nextName;
			}
			if(functionHead[0].equals("IoTIDByte")){
				return nextName;
			}
			if (next > max) {
				max = next;
				maxName = nextName;
			}
		}
		return maxName;
	}

	//some comments added by dengguangqing, on 2014-04-21
	// some comments added by dengguangqing, on 2014-04-21
	// get one rule randomly
	private static String getMax_dgq() {
		Set<String> keySet = rmvRuleSetTemp.keySet();
		Iterator ikey = keySet.iterator();
		String nextName = "";

		while (ikey.hasNext()) {
			nextName = (String) ikey.next();
			break;
		}
		return nextName;
	}

	private static void sortRules() {
		// TODO Auto-generated method stub
		double p = 0.0;
		Set<String> rulekeySet = rmvRuleSetTemp.keySet();
		Iterator<String> ruleikey = rulekeySet.iterator();
		while (ruleikey.hasNext()) {
			p = 0.0;
			String ruleName = (String) ruleikey.next();
			Set<String> idkeySet = rmvIDSetTemp.keySet();
			Iterator<String> idikey = idkeySet.iterator();
			/*
			 * while (idikey.hasNext()) { String idName = idikey.next(); if
			 * (hashMapRuleToTypesTemp.get(ruleName).indexOf(idName) >= 0 &&
			 * rmvIDSet.containsKey(idName)) { p = p + (double)
			 * rmvIDSet.get(idName); } }
			 */
			ArrayList<String> IDs = hashMapRuleToTypesTemp.get(ruleName);
			double sum_p = 0;
			for (int i = 0; i < IDs.size(); i++) {
				String s = IDs.get(i).toString();
				double currentP = rmvIDSetTemp.get(s);
				sum_p += currentP;

			}

			/*
			 * if (p == 0 || p == 1) { System.out.println("ERROR!  " + ruleName
			 * + " p is 0 or 1,error!"); }
			 */
			if (p > 1 || p < 0) {
				System.out.println("ERROR!  " + ruleName
						+ " p is not in 1~0 range,error!");
			}
			// rmvRuleSetTemp.put(ruleName, w(p));// p!=0 or 1
			rmvRuleSetTemp.put(ruleName, w(sum_p));// p!=0 or 1
		}
	}
	
	private static HashMap<String, Double> sortRules1(HashMap<String, Double> rmvRuleSetTemp1, HashMap<String, Double> rmvIDSetTemp1) {
		// TODO Auto-generated method stub
		double p = 0.0;
		Set<String> rulekeySet = rmvRuleSetTemp1.keySet();
		Iterator<String> ruleikey = rulekeySet.iterator();
		while (ruleikey.hasNext()) {
			p = 0.0;
			String ruleName = (String) ruleikey.next();
			Set<String> idkeySet = rmvIDSetTemp1.keySet();
			Iterator<String> idikey = idkeySet.iterator();
			/*
			 * while (idikey.hasNext()) { String idName = idikey.next(); if
			 * (hashMapRuleToTypesTemp.get(ruleName).indexOf(idName) >= 0 &&
			 * rmvIDSet.containsKey(idName)) { p = p + (double)
			 * rmvIDSet.get(idName); } }
			 */
			ArrayList<String> IDs = hashMapRuleToTypesTemp.get(ruleName);
			double sum_p = 0;
			for (int i = 0; i < IDs.size(); i++) {
				String s = IDs.get(i).toString();
				if(rmvIDSetTemp1.containsKey(s)){
					double currentP = rmvIDSetTemp1.get(s);
					sum_p += currentP;
				}


			}

			/*
			 * if (p == 0 || p == 1) { System.out.println("ERROR!  " + ruleName
			 * + " p is 0 or 1,error!"); }
			 */
			if (p > 1 || p < 0) {
				System.out.println("ERROR!  " + ruleName
						+ " p is not in 1~0 range,error!");
			}
			// rmvRuleSetTemp.put(ruleName, w(p));// p!=0 or 1
			rmvRuleSetTemp1.put(ruleName, w(sum_p));// p!=0 or 1
		}
		
		return rmvRuleSetTemp1;
	}

	private static double w(double p) {
		double q = 1 - p;
		return p * Math.log(1 / p) / Math.log(2) + q * Math.log(1 / q)
				/ Math.log(2);
	}

	// comments added by dengguangqing, 2014-04-21
	// remove those IDs in rmvIDSet relating to the rule which fails in matching
	private static void subtraction(HashMap<String, Double> rmvIDSetTemp,
			ArrayList<String> arrayList) {
		Iterator<String> iterator = rmvIDSetTemp.keySet().iterator();

		while (iterator.hasNext()) {
			String temp = (String) iterator.next();

			if (arrayList.indexOf(temp) >= 0) { // ��arrayList���ҵ���
				// rmvIDSetTemp.remove(temp);
				iterator.remove();
			}
		}
	}

	// some comments added by dengguangqing, 2014-04-21
	// update rmvRuleSet according to new rmvIDSet and currently already matched
	// rule
	private static void union(String delRule) {
		Iterator<String> iter = rmvIDSetTemp.keySet().iterator();// IoT ID set

		//ArrayList<String> arrayList = new ArrayList<String>(); 
		ArrayList<String> arrayList_Rules;
		HashSet<String> arrayList = new HashSet<String>();

		while (iter.hasNext()) {
			String ID_key = (String) iter.next();

			arrayList_Rules = new ArrayList<String>();
			arrayList_Rules = hashMapTypeToRulesTemp.get(ID_key);

			for (String rule : arrayList_Rules) {
				arrayList.add(rule);
			}
		}

		Iterator<String> iterator = rmvRuleSetTemp.keySet().iterator();

		while (iterator.hasNext()) {
			String Rule_key = iterator.next();

			if (!arrayList.contains((String)Rule_key)) { 
				// rmvRuleSet.remove(Rule_key);
				iterator.remove();
			}
		}
		rmvRuleSetTemp.remove(delRule);
	}

	public static void testAndTestID() throws IOException {
		//rmvRuleSetTemp = (HashMap<String, Double>)rmvRuleSet.clone();// rmvRuleSet<规则名，权重>;
		//rmvIDSetTemp = (HashMap<String, Double>)rmvIDSet.clone();// rmvIDSet<类型名，先验概率>;
		
		hashMapTypeToRulesTemp = (HashMap<String, ArrayList<String>>)hashMapTypeToRules.clone();// 类型对应规则
		
		//hashMapRuleToTypesTemp  = (HashMap<String, ArrayList<String>>)hashMapRuleToTypes.clone();// 规则对应类型;
		long timeRuleMatchBegin = System.currentTimeMillis();
		HashMap<String, String> testHashMap = new HashMap<String, String>();
		testHashMap = RecoDao.test();
			
		//added by dengguangqing 20140826
		//testHashMap.clear();
		//testHashMap.put("GA/T_556.10-2007","贵公发152524996503");
		
		Iterator<String> iterator1 = testHashMap.keySet().iterator();
		int i = 0;
		int k = 0;
		while (iterator1.hasNext()) {
			timeRuleMatchBegin = System.currentTimeMillis();
			Object testID = iterator1.next();
			String test = testHashMap.get(testID);
//					
//			System.out.println(k);
//			k = k + 1;
//			System.out.println(hashMapTypeToRulesTemp.size());
//			System.out.println(testID + "  " + test + "\n");		
			
			ArrayList<String> s = hashMapTypeToRulesTemp.get(testID);
			if (s == null){
				System.out.println("the iotid is not conlcuded!\n" + "the ID is: " + testID + "  "  + "the Instance is: "+ test + "\n");
				continue;
			}
			String resFlag = "OK";
			String res = "";
			int size = s.size();
			
			for (i = 0; i < size; i++) {
				String temp = s.get(i);
				String[] splitRules = temp.split("\\)\\(\\?\\#PARA=");// 提取规则名
				String[] splitParameter = splitRules[1].split("\\)\\{\\]");// 提取参数
				if (!match(splitRules[0], splitParameter[0], test)) {
					resFlag = "ERR";
					res = res + "ERROR!  IoTID:" + testID + "  InputIDstr:"
							+ test + "  Function:" + splitRules[0]
							+ "  FuncPara:" + splitParameter[0] + "\n";

				} else {
					res = res + "OK!  IoTID:" + testID + "  InputIDstr:" + test
							+ "  Function:" + splitRules[0] + "  FuncPara:"
							+ splitParameter[0] + "\n";

				}
			}
			if (resFlag == "OK") {
				File f = new File("e://DebugResultOK.txt");
				BufferedWriter output = new BufferedWriter(new FileWriter(f,
						true));
				output.append(res);
				output.append("\n");
				output.flush();
				output.close();

				// ////////////////////////////////
				File f1 = new File("e://DebugResultOKID.txt");
				BufferedWriter output1 = new BufferedWriter(new FileWriter(f1,
						true));
				output1.append(testID.toString()+":  " + test);
				output1.append("\n");
				output1.flush();
				output1.close();				
			} else {
				File f = new File("e://DebugResultERROR.txt");
				BufferedWriter output = new BufferedWriter(new FileWriter(f,
						true));
				output.append(res);
				output.append("\n");
				output.flush();
				output.close();

				// ////////////////////////////////
				File f1 = new File("e://DebugResultERRORID.txt");
				BufferedWriter output1 = new BufferedWriter(new FileWriter(f1,
						true));
				output1.append(testID.toString()+":  " + test);
				output1.append("\n");
				output1.flush();
				output1.close();
			}
			
			long timeRuleMatchEnd = System.currentTimeMillis() - timeRuleMatchBegin;
			File ftime = new File("e://IDrecognitionTime.txt");
			BufferedWriter output1 = new BufferedWriter(new FileWriter(ftime,true));
			output1.append(testID.toString() + ": " + String.valueOf(timeRuleMatchEnd) + "\n");
			output1.flush();
			output1.close();
		}
		
	}
	
	public static void testAndTestIDRandom() throws IOException {
		// 复制类型-规则集
		hashMapTypeToRulesTemp = (HashMap<String, ArrayList<String>>) hashMapTypeToRules
				.clone();// 类型对应规则
		//读入类型-samples表名
		HashMap<String, String> typetoTablename = RecoDaoRandom
				.TypetoTableName();
		Iterator<String> iterator = typetoTablename.keySet().iterator();
		//统计信息
		int num_OK=0;
		int num_ERR=0;
        //打印进度信息
		int count=0;
		// 对每一个类型分别进行测试
		while (iterator.hasNext()) {
		//while (true) {
			File f = new File("/home/hadoop/eclipseJavaEE/dgq/debugnFlag3/DebugResultERROR.txt");
			File f1 = new File("/home/hadoop/eclipseJavaEE/dgq/debugnFlag3/DebugResultALL.txt");
			File f3 = new File("/home/hadoop/eclipseJavaEE/dgq/debugnFlag3/RightType.txt");
			File f4 = new File("/home/hadoop/eclipseJavaEE/dgq/debugnFlag3/WrongType.txt");
			File f5 = new File("/home/hadoop/eclipseJavaEE/dgq/debugnFlag3/RightTypeNum.txt");
			
			int OK_TYPE_NUM=0;
			// 查询映射表，找到该类型的samples所保存的表
			String type = iterator.next();
			String tableName = typetoTablename.get(type);
			//String type="GA/T_556.10-2007";
			//String tableName="GAT556102007";

			// 读入某一类型的所有随机样本
			HashMap<Integer, String> Samples = new HashMap<Integer, String>();
			Samples = RecoDaoRandom.testSamples(tableName);

			String TYPEFLAG="OK";
			// 对每一个样本进行测试
			try{
			Iterator<Integer> iterator1 = Samples.keySet().iterator();
			ArrayList<String> rules = hashMapTypeToRulesTemp.get(type);
			if (rules == null) {
				System.out.println("the iotid is not conlcuded!\n"
						+ "the ID is: " + type);
			}
			while (iterator1.hasNext()) {
				
				int index = iterator1.next();
				String code = Samples.get(index);

				String resFlag = "OK";
				String res = "";
				
				int size = rules.size();

				// 对这个类型的所有规则进行测试
				for (int i = 0; i < size; i++) {
					String temp = rules.get(i);
					String[] splitRules = temp.split("\\)\\(\\?\\#PARA=");// 提取规则名
					String[] splitParameter = splitRules[1].split("\\)\\{\\]");// 提取参数
					if (!match(splitRules[0], splitParameter[0], code)) {
						resFlag = "ERR";
						TYPEFLAG="ERR";
						res = res + "ERROR!  IoTID:" + type + "  InputIDstr:"
								+ code + "  Function:" + splitRules[0]
								+ "  FuncPara:" + splitParameter[0] + "\n";
					}
					
				}
				

				// 写入详细错误信息
				if (resFlag.equals("ERR")) {
					num_ERR++;
					BufferedWriter output = new BufferedWriter(new FileWriter(
							f, true));
					output.append(res);
					output.append("\n");
					output.flush();
					output.close();
				}
				else
				{
					num_OK++;
					OK_TYPE_NUM++;
				}

				BufferedWriter output1 = new BufferedWriter(new FileWriter(f1,
						true));
				output1.append(resFlag + "  " + code.toString() + ":  " + type);
				output1.append("\n");
				output1.flush();
				output1.close();

			}

		}catch(Exception e)
		{
			e.printStackTrace();
		}
		//记录整个类型的情况
		if(TYPEFLAG.equals("OK")){
		BufferedWriter output3 = new BufferedWriter(new FileWriter(f3,
				true));
		output3.append(type);
		output3.append("\n");
		output3.flush();
		output3.close();
		
		//记录这个类型正确的数量
		BufferedWriter output5 = new BufferedWriter(new FileWriter(f5,
				true));
		output5.append(type+":"+"   "+OK_TYPE_NUM);
		output5.append("\n");
		output5.flush();
		output5.close();
		}
		else
		{
			BufferedWriter output4 = new BufferedWriter(new FileWriter(f4,
					true));
			output4.append(type);
			output4.append("\n");
			output4.flush();
			output4.close();
		}
		
		System.out.println("Finished"+count+"   "+type);
		count++;
		Samples=null;
		System.gc();
		OK_TYPE_NUM=0;
		//break;
		
		}
		
		
		File f2 = new File("e://debug//statics.txt");
		BufferedWriter output2 = new BufferedWriter(new FileWriter(
				f2, true));
		output2.append(String.valueOf(num_ERR));
		output2.append("\n");
		output2.append(String.valueOf(num_OK));
		output2.append("\n");
		output2.flush();
		output2.close();
	}
	
	public static Map<Set<String>, List<String>> two_types(HashMap<String, Double> typeProbability,
			Map<Set<String>, List<String>> Samples_types, String type,
			List<String> list,String code ) {
		Set<String> types_set = new HashSet<String>();
		List<String> Samples_list=new ArrayList<String>();
		types_set = typeProbability.keySet();
		// Set<String> types_two = new TreeSet<String>();
		// System.out.println(this.code);
		Map<Set<String>, List<String>> Samples_types1 = new HashMap<Set<String>, List<String>>();
		for (String types_another : list) {
			Set<String> types_two = new TreeSet<String>();
			if (types_set.contains(types_another)) {

				types_two.add(type);
				types_two.add(types_another);

				if (Samples_types.containsKey(types_two)) {
					Samples_types.get(types_two).add(code);
				} else {
					Samples_list = new ArrayList<String>();
					Samples_list.add(code);
					Samples_types.put(types_two, Samples_list);
				}
			}
		}
		return Samples_types;
	}
	
	//结果输出
	public static void _outPut_(String typeName,Map<Set<String>, List<String>> Samples_types) {
				String path = "d:"+File.separator+"sample"+File.separator+"test.csv";
				
			try {
					File f = new File(path);
					 if(!(f.isFile() && f.exists())){
						f.createNewFile();
					}
					BufferedWriter output = new BufferedWriter(new FileWriter(f,
							true));
						  if(Samples_types.size()==0){
						   output.append(typeName+ ","+" "+"," +"10000");
						   output.append("\n");
					 }else{
						 for(Entry<Set<String>, List<String>> _set : Samples_types.entrySet()){
							Set<String> set_ =_set.getKey();
							 set_.remove(typeName);
							 output.append(typeName+","+set_.toString().replace("[", "").replace("]", "")+","+_set.getValue().size());
								output.append("\n");
						 }
						 }
					output.flush();
					output.close();
				} catch (Exception e) {
					e.printStackTrace();
				}	
			}
	
	public static void _outPut_(Map<String, Map<Set<String>, List<String>>> type_Samples) {
		String path = "/home/hadoop"+File.separator+"sample"+File.separator+"test.csv";
		try {
			File f = new File(path);
			BufferedWriter output = new BufferedWriter(new FileWriter(f,
					true));
			output.append("Source,Target,Weight");
			 output.append("\n");
			for(Entry<String, Map<Set<String>, List<String>>> set :type_Samples.entrySet() ){
				String type=set.getKey();
				 Map<Set<String>, List<String>> value=set.getValue();
				  if(value.size()==0){
				   output.append(type+ ","+" "+"," +"10000");
				   output.append("\n");
			 }else{
				// output.append(type);
				// output.append("\n");
				 for(Entry<Set<String>, List<String>> _set : value.entrySet()){
					// output.append(_set.getKey().toString().replace("[", "").replace("]", "") + "," +_set.getValue().toString().replace("[", "").replace("]", "")  );
					Set<String> set_ =_set.getKey();
					 set_.remove(type);
					 System.out.println(type);
					 output.append(type+","+set_.toString().replace("[", "").replace("]", "")+","+_set.getValue().size());
						output.append("\n");
				 }
				 }

			}
			
			output.flush();
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println("Finished "+TypeCount+"  "+IDType);

		// *****************
		/**for(Entry<String, Map<Set<String>, List<String>>> set :type_Samples.entrySet() ){
			String type=set.getKey();
			 Map<Set<String>, List<String>> value=set.getValue();
			 System.out.println(type);
			 if(value.size()==0){
			 }
			 for(Entry<Set<String>, List<String>> _set : value.entrySet()){
				 System.out.println(_set.getKey()+"  :  "+_set.getValue()); 
			 }
		}
		*/
		System.out.println(type_Samples.size());
	}

}
