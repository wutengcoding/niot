package cn.niot.SpecificRecognition;

public class SplitFunction {
	
	//SplitPoints:##GBT285322012;5
	public static String[]GBT285322012(String code)
	{
		int splitLength=5;
		int beginIndex=0;
		int endIndex=0;
		
		String[] Feature=new String[splitLength];
		//处理最后6位
		endIndex=code.length();
		beginIndex=endIndex-6;
		Feature[4]=code.substring(beginIndex, endIndex);
		
		//处理倒数第七位
		endIndex=beginIndex;
		beginIndex=beginIndex-1;
		Feature[3]=code.substring(beginIndex, endIndex);
		
		//处理倒数第8位
		endIndex=beginIndex;
		beginIndex=beginIndex-1;
		Feature[2]=code.substring(beginIndex, endIndex);
		
		//处理倒数第9,10位
		endIndex=beginIndex;
		beginIndex=beginIndex-2;
		Feature[1]=code.substring(beginIndex, endIndex);
		
		
		//处理第一部分，不定长
		endIndex=beginIndex;
		beginIndex=0;
		Feature[0]=code.substring(beginIndex, endIndex);
		
		return Feature;
	}
	
	public static String[]GBT1569411995(String code)
	{
		int splitLength=4;
		int code_length=code.length();
		String[] Feature=new String[splitLength];
		
		//处理倒数第1位
		Feature[0]=code.substring(0, 1);
		//处理倒数第2-6位
		Feature[1]=code.substring(1, 6);
		
		//处理倒数第7-位
		Feature[2]=code.substring(7, code_length-1);
		//处理最后1位
		Feature[splitLength-1]=code.substring(code_length-1, code_length);
		
		
		return Feature;
	}


}
