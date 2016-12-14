package cn.niot.service;

import java.util.HashMap;

public class NormalIDstdCollisionDetect {
	public static HashMap<String, Double> evaluateCollisionTwoIDs(){
		double TotalCode = 100;
		double p1 = 0;
		double p2 = 0;
		double Count1 = 0;
		double Count2 = 0;
		HashMap<String, Double> res = new HashMap<String, Double>();
		for (int i = 0; i < TotalCode; i++){
			CompareCode13 StreetCode = new CompareCode13();
			char [] IDstr1 = StreetCode.generateRandomStreetCode();
			char [] IDstr2 = StreetCode.generateRandomEAN13();
			HashMap<String, Double> typeProbability1 = IDstrRecognition.IoTIDRecognizeAlg(String.valueOf(IDstr1));
			if (typeProbability1.containsKey("EAN-13")){
				Count1 = Count1 + 1;
			}
			
			p1 = Count1 / TotalCode;
			
			HashMap<String, Double> typeProbability2 = IDstrRecognition.IoTIDRecognizeAlg(String.valueOf(IDstr2));
			if (typeProbability2.containsKey("GB/T 23705-2009_2")){
				Count2 = Count2 + 1;
			}
			p2 = Count2 / TotalCode;
		}
		res.put("街巷或小区编码", 1 / p1 - 1);//GB/T 23705-2009_2
		res.put("公共部分", 1.0);
		res.put("商品条码", 1 / p2 - 1);//EAN-13
	
		return res;		
	}
}
