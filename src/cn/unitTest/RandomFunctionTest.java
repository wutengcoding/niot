package cn.unitTest;
import cn.niot.randomization.*;
import java.sql.*;
import java.lang.*;
import java.util.*;
import java.util.regex.*;
import cn.niot.util.*;
import java.io.*;
import org.junit.Test;

import com.google.common.base.Strings;

import cn.niot.randomization.RuleRandom;
import cn.niot.util.FunctionResult;

public class RandomFunctionTest {


    //SQ
	@Test
	//GB_11643-1999
	//(?#ALGNAME=AdminDivision)(?#PARA=0,1,2,3,4,5){]
	//(?#ALGNAME=MonthDate)(?#PARA=10,11,12,13){]
	//(?#ALGNAME=MOD112)(?#PARA=0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16;17){]
	public void testGB_116431999()
	{  //将数据库原始信息拷贝过来
		String LengthRule="18";
		String RawByteRule="6,254,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;16,255,3,0,0,0,0,0,0;";
	    FunctionResult res=new FunctionResult();
	    //函数规则入口参数
	    String para1="0,1,2,3,4,5";
	    String para2="10,11,12,13";
	    String para3="0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16;17";
	    
	    res = RuleRandom.IoTIDLength_Random(LengthRule);
	    
	    //将数据库中原始若干字节规则分别调用IoTIDByte_Random
	    String ByteRule="";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
            res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

	   
	    res=RuleRandom.AdminDivision_Random(res, para1);
	    res=RuleRandom.MonthDate_Random(res, para2);
	    res=RuleRandom.MOD112_Random(res, para3);
	    
	    //此处使用DisplayRandomCode()的版本二
	    RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	@Test
	//logistic
	//(?#ALGNAME=LogisticsCheck)(?#PARA=0,1,2,3,4;5){]
	public void testlogistic()
	{
		//数据库原始信息
		String LengthRule="6";
		String RawByteRule="0,255,3,0,0,0,0,0,0;1,255,3,0,0,0,0,0,0;2,255,3,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;";
	    FunctionResult res=new FunctionResult();
	    
	    String para="0,1,2,3,4;5";
	    res = RuleRandom.IoTIDLength_Random(LengthRule);
	    
	    String ByteRule="";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
            res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		
		 res=RuleRandom.LogisticsCheck_Random(res, para);
		   
		 RuleRandom.DisplayRandomCode(res.FunctionResult);
		
	}
	
	@Test
	//MedSupervise
	//(?#ALGNAME=MedAppCode)(?#PARA=15){]
	public void testMedSupervise()
	{
		//数据库原始信息
		String LengthRule="20";
		String RawByteRule="0,0,1,0,0,0,0,0,0;1,255,3,0,0,0,0,0,0;2,255,3,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;16,255,3,0,0,0,0,0,0;17,255,3,0,0,0,0,0,0;18,255,3,0,0,0,0,0,0;19,255,3,0,0,0,0,0,0;";
	    FunctionResult res=new FunctionResult();
	    
	    String para="15";
	    res = RuleRandom.IoTIDLength_Random(LengthRule);
	    
	    String ByteRule="";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
            res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		
		 res=RuleRandom.MedAppCode_Random(res, para);
		   
		 RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	@Test
	//SY/T_6032-1994
	//(?#ALGNAME=GassCompany)(?#PARA=0,1,2,3,4,5,6){]
	public void testSYT60321994()
	{
		//数据库原始信息
		String LengthRule="7";
		
	    FunctionResult res=new FunctionResult();
	    
	    String para="0,1,2,3,4,5,6";
	    res = RuleRandom.IoTIDLength_Random(LengthRule);
	    
	 
		
		 res=RuleRandom.GassCompany_Random(res, para);
		   
		 RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	@Test
	//JGJ/T_246-2012
	//(?#ALGNAME=AdminDivision)(?#PARA=0,1,2,3,4,5){]
	//(?#ALGNAME=HouseCode_CheckTwelBitCode)(?#PARA=9,10,11,12,13,14,15,16,17,18,19,20){]
	//(?#ALGNAME=HouseCode_CheckUnitCode)(?#PARA=21,22,23,24){]
	//(?#ALGNAME=HouseCode_CheckCode)(?#PARA=0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24;25){]
	public void testJGJT2462012()
	{
		String LengthRule="26";
		String RawByteRule="6,15,0,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;;";
	    FunctionResult res=new FunctionResult();
	    
	    String para1="0,1,2,3,4,5";
	    String para2="9,10,11,12,13,14,15,16,17,18,19,20";
	    String para3="21,22,23,24";
	    String para4="0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24;25";
	    
	    res = RuleRandom.IoTIDLength_Random(LengthRule);
	    
	    String ByteRule="";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
            res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		
		 res=RuleRandom.AdminDivision_Random(res, para1);
		 res=RuleRandom.HouseCode_CheckTwelBitCode_Random(res, para2);
		 res=RuleRandom.HouseCode_CheckUnitCode_Random(res, para3);
		 res=RuleRandom.HouseCode_CheckCode_Random(res, para4);
		   
		 RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	
	@Test
	//GA_398.1-2002
	//(?#ALGNAME=AdminDivision)(?#PARA=1,2,3,4,5,6){]
	//(?#ALGNAME=Month)(?#PARA=14,15){]
	//(?#ALGNAME=FourByteDecimalnt)(?#PARA=16,17,18,19){]
	public void testGA39812002()
	{
		String LengthRule="20";
		String RawByteRule="0,0,0,0,0,16,0,0,0;7,255,255,255,255,255,255,255,63;8,255,255,255,255,255,255,255,63;9,255,255,255,255,255,255,255,63;10,254,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;";
	    FunctionResult res=new FunctionResult();
	    
	    String para1="1,2,3,4,5,6";
	    String para2="14,15";
	    String para3="16,17,18,19";
	    
	    res = RuleRandom.IoTIDLength_Random(LengthRule);
	    
	    String ByteRule="";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
            res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		
		 res=RuleRandom.AdminDivision_Random(res, para1);
		 res=RuleRandom.Month_Random(res, para2);
		 res=RuleRandom.FourByteDecimalnt_Random(res, para3);
		   
		 RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	@Test
	//productpwd
	//(?#ALGNAME=First2CharsofAdminDivision)(?#PARA=0,1){]
	//(?#ALGNAME=TwoByteDecimalnt)(?#PARA=4,5){]
	//(?#ALGNAME=TwoByteDecimalnt)(?#PARA=8,9){]
	public void testproductpwd()
	{
		String LengthRule="10";
		String RawByteRule="2,255,3,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;";
	    FunctionResult res=new FunctionResult();
	    
	    String para1="0,1";
	    String para2="4,5";
	    String para3="8,9";
	    
	    res = RuleRandom.IoTIDLength_Random(LengthRule);
	    
	    String ByteRule="";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
            res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		
		 res=RuleRandom.First2CharsofAdminDivision_Random(res, para1);
		 res=RuleRandom.TwoByteDecimalnt_Random(res, para2);
		 res=RuleRandom.TwoByteDecimalnt_Random(res, para3);
		   
		 RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	@Test
	//WS_218-2002
	//(?#ALGNAME=FiveByteDecimalnt)(?#PARA=3,4,5,6,7){]
	//(?#ALGNAME=MOD112)(?#PARA=0,1,2,3,4,5,6,7;8){]
	//(?#ALGNAME=AdminDivision)(?#PARA=9,10,11,12,13,14){]
	//(?#ALGNAME=WirtschaftsTypCode)(?#PARA=15,16){]
	public void testWS2182002()
	{
		String LengthRule="22";
		String RawByteRule="0,0,0,0,0,0,0,8,0;1,0,0,0,0,128,0,0,0;2,0,0,0,0,0,0,0,16;17,0,0,0,0,240,239,31,0;18,254,3,0,0,0,0,0,0;19,255,3,0,0,0,0,0,0;20,255,3,0,0,0,0,0,0;21,6,2,0,0,0,0,0,0;";
	    FunctionResult res=new FunctionResult();
	    
	    String para1="3,4,5,6,7";
	    String para2="0,1,2,3,4,5,6,7;8";
	    String para3="9,10,11,12,13,14";
	    String para4="15,16";
	    
	    res = RuleRandom.IoTIDLength_Random(LengthRule);
	    
	    String ByteRule="";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
            res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		
		 res=RuleRandom.FiveByteDecimalnt_Random(res, para1);
		 res=RuleRandom.MOD112_Random(res, para2);
		 res=RuleRandom.AdminDivision_Random(res, para3);
		 res=RuleRandom.WirtschaftsTypCode_Random(res, para4);
		   
		 RuleRandom.DisplayRandomCode(res.FunctionResult);
		
	}
	
	@Test
	/**!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!结果待定
	 * 
	 */
    //DL/T_700.2-1999_48
	//DL/T_700.1-1999_51
	public void testDLT7001199951()
	{
		String LengthRule="12,13";
		String RawByteRule="0,30,2,0,0,0,0,0,0;1,30,0,0,0,0,0,0,0;2,254,3,0,0,0,0,0,0;3,6,0,0,0,0,0,0,0;";
	    FunctionResult res=new FunctionResult();
	    
	    String para1="4,5";
	    String para2="6,-1";
	    
	    res = RuleRandom.IoTIDLength_Random(LengthRule);
	    
	    String ByteRule="";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
            res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		
		 res=RuleRandom.TwoByteDecimalnt_Random(res, para1);
		 res=RuleRandom.ParamCode6_Random(res, para2);
		 
		 RuleRandom.DisplayRandomCode(res.FunctionResult);
		
   }
	
   @Test
   //LS/T_1704.1-2004
   public void testLST170412004()
   {
	    String LengthRule="8";
	    FunctionResult res=new FunctionResult();
	    
	    String para1="0,1,2,3,4,5,6,7";
	   
	    res = RuleRandom.IoTIDLength_Random(LengthRule);
	    
	    res=RuleRandom.GrainsIndex_Random(res, para1);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
		
	   
   }
   
	// JT/T_437-2001
	// (?#ALGNAME=OneTO08)(?#PARA=1,2){]
	// (?#ALGNAME=Port)(?#PARA=3,4,5){]
	@Test
	public void testJTT4372001() {
		
		String LengthRule = "6";
		String ByteRule = "0,4,0,0,0,0,0,0,0";
		FunctionResult res = new FunctionResult();

		String para1 = "1,2";
		String para2 = "3,4,5";

		res = RuleRandom.IoTIDLength_Random(LengthRule);
		res = RuleRandom.IoTIDByte_Random(res, ByteRule);

		res = RuleRandom.OneTO08_Random(res, para1);
		res = RuleRandom.Port_Random(res, para2);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
   
	// GB/T_14721-2010
	// (?#ALGNAME=ForestTypes)(?#PARA=0,1,2,3,4){]
	@Test
	public void testGBT147212010() {
		String LengthRule = "5";

		FunctionResult res = new FunctionResult();

		String para1 = "0,1,2,3,4";

		res = RuleRandom.IoTIDLength_Random(LengthRule);

		res = RuleRandom.ForestTypes_Random(res, para1);

		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GB/T_28422-2012_3
	//(?#ALGNAME=ChineseCharRegex)(?#PARA=0,1,2,3,4,5,6,7){]
	//(?#ALGNAME=First2CharsofAdminDivision)(?#PARA=8,9){]
	@Test
	public void testGBT2842220123()
	{
		String LengthRule="16";
		String RawByteRule="10,255,255,0,0,240,3,0,0;11,255,255,0,0,240,3,0,0;12,1,0,0,0,0,0,0,0;13,1,0,0,0,0,0,0,0;14,1,0,0,0,0,0,0,0;15,14,0,0,0,0,0,0,0;";
	    FunctionResult res=new FunctionResult();
	    
	    String para1="0,1,2,3,4,5,6,7";
	    String para2="8,9";
	    
	    res = RuleRandom.IoTIDLength_Random(LengthRule);
	    
	    String ByteRule="";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
            res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		
		 res=RuleRandom.ChineseCharRegex_Random(res, para1);
		 res=RuleRandom.First2CharsofAdminDivision_Random(res, para2);
		 
		 RuleRandom.DisplayRandomCode(res.FunctionResult);
		
	}
	
	//数据库异常值
	//DL/T_396-2010
	//6,6,0,0,0,0,0,0,0;
	//7
	//(?#ALGNAME=VoltageClass)(?#PARA=0,1,2,3,4,5,6){]
	@Test
	public void testDLT3962010() {
		String LengthRule = "7";
		String RawByteRule = "6,6,0,0,0,0,0,0,0";
		FunctionResult res = new FunctionResult();

		String para1 = "0,1,2,3,4,5,6";
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		res = RuleRandom.IoTIDByte_Random(res, RawByteRule);

		res = RuleRandom.VoltageClass_Random(res, para1);
		
        RuleRandom.DisplayRandomCode(res.FunctionResult);

	}
	
	//GB/T_28422-2012_6
	//(?#ALGNAME=TwobytleWeekCode)(?#PARA=2,3){]
	//(?#ALGNAME=ClassOfCardCode)(?#PARA=4,5){]
	@Test
	public void testGBT2842220126()
	{
		String LengthRule="16";
		String RawByteRule="0,255,3,0,0,0,0,0,0;1,255,3,0,0,0,0,0,0;6,255,255,0,0,240,3,0,0;7,255,255,0,0,240,3,0,0;8,255,255,0,0,240,3,0,0;9,255,255,0,0,240,3,0,0;10,255,255,0,0,240,3,0,0;11,255,255,0,0,240,3,0,0;12,255,255,0,0,240,3,0,0;13,255,255,0,0,240,3,0,0;14,255,255,0,0,240,3,0,0;15,255,255,0,0,240,3,0,0;";
	    FunctionResult res=new FunctionResult();
	    
	    String para1="2,3";
	    String para2="4,5";
	    
	    res = RuleRandom.IoTIDLength_Random(LengthRule);
	    
	    String ByteRule="";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
            res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		
		 res=RuleRandom.TwobytleWeekCode_Random(res, para1);
		 res=RuleRandom.ClassOfCardCode_Random(res, para2);
		 
		 RuleRandom.DisplayRandomCode(res.FunctionResult);
		
	}
	
	//GB/T_16835-1997
	//(?#ALGNAME=NormalAndShortCycleSpeciality)(?#PARA=0,1,2,3,4,5){]
	@Test
	public void testGBT168351997()
	{
		String LengthRule="6";
	    FunctionResult res=new FunctionResult();
	    
	    String para1="0,1,2,3,4,5";
	    res = RuleRandom.IoTIDLength_Random(LengthRule);
	    

		
		 res=RuleRandom.NormalAndShortCycleSpeciality_Random(res, para1);
		 
		 RuleRandom.DisplayRandomCode(res.FunctionResult);
		
		
	}

	//EAN-8
	//(?#ALGNAME=PrefixofRetailCommodityNumber)(?#PARA=0,1,2){]
	//(?#ALGNAME=CheckCodeForCommodityCode)(?#PARA=0,1,2,3,4,5,6;7){]
	@Test
	public void testEAN8() {
		String LengthRule = "8";
		String RawByteRule = "3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();

		String para1 = "0,1,2";
		String para2 = "0,1,2,3,4,5,6;7";

		res = RuleRandom.IoTIDLength_Random(LengthRule);

		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.PrefixofRetailCommodityNumber_Random(res, para1);
		res=RuleRandom.CheckCodeForCommodityCode_Random(res, para2);
		RuleRandom.DisplayRandomCode(res.FunctionResult);

	}
	
	//EAN-13
	//(?#ALGNAME=PrefixofRetailCommodityNumber)(?#PARA=0,1,2){]
	//(?#ALGNAME=CheckCodeForCommodityCode)(?#PARA=0,1,2,3,4,5,6,7,8,9,10,11;12){]
	@Test
	public void testEAN13() {
		String LengthRule = "13";
		String RawByteRule = "3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();

		String para1 = "0,1,2";
		String para2 = "0,1,2,3,4,5,6,7,8,9,10,11;12";

		res = RuleRandom.IoTIDLength_Random(LengthRule);

		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.PrefixofRetailCommodityNumber_Random(res, para1);
		res=RuleRandom.CheckCodeForCommodityCode_Random(res, para2);
		RuleRandom.DisplayRandomCode(res.FunctionResult);

	}
	
	//CID
	//(?#ALGNAME=DistrictNo)(?#PARA=2,3,4,5){]
	//(?#ALGNAME=CIDRegex)(?#PARA=18,-1){]
	@Test
	public void testCID()
	{
		String LengthRule = "18-156";
		String RawByteRule = "0,255,3,0,0,0,0,0,0;1,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;16,255,3,0,0,0,0,0,0;17,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();

		String para1 = "2,3,4,5";
		String para2 = "18,-1";

		res = RuleRandom.IoTIDLength_Random(LengthRule);

		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.DistrictNo_Random(res, para1);
		res=RuleRandom.CIDRegex_Random(res, para2);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//cpc
	//(?#ALGNAME=CPCTwoByte)(?#PARA=0,1){]
	//(?#ALGNAME=CountryRegionCodeforCPC)(?#PARA=2,3,4,5){]
	//(?#ALGNAME=AdminDivision)(?#PARA=6,7,8,9,10,11){]
	//(?#ALGNAME=MonthDate)(?#PARA=54,55,56,57){]
	@Test
	public void testcpc()
	{
		String LengthRule = "256";
		String RawByteRule = "12,8,0,0,0,0,0,0,0;13,4,0,0,0,0,0,0,0;14,4,0,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;16,255,3,0,0,0,0,0,0;17,255,3,0,0,0,0,0,0;18,255,3,0,0,0,0,0,0;19,255,3,0,0,0,0,0,0;20,255,3,0,0,0,0,0,0;21,255,3,0,0,0,0,0,0;22,255,3,0,0,0,0,0,0;23,255,3,0,0,0,0,0,0;24,255,3,0,0,0,0,0,0;25,255,3,0,0,0,0,0,0;26,255,3,0,0,0,0,0,0;27,255,3,0,0,0,0,0,0;28,255,3,0,0,0,0,0,0;29,255,3,0,0,0,0,0,0;30,255,3,0,0,0,0,0,0;31,255,3,0,0,0,0,0,0;32,255,3,0,0,0,0,0,0;33,255,3,0,0,0,0,0,0;34,255,3,0,0,0,0,0,0;35,255,3,0,0,0,0,0,0;36,255,3,0,0,0,0,0,0;37,255,3,0,0,0,0,0,0;38,255,3,0,0,0,0,0,0;39,255,3,0,0,0,0,0,0;40,255,3,0,0,0,0,0,0;41,255,3,0,0,0,0,0,0;42,255,3,0,0,0,0,0,0;43,255,3,0,0,0,0,0,0;44,255,3,0,0,0,0,0,0;45,255,3,0,0,0,0,0,0;46,255,3,0,0,0,0,0,0;47,255,3,0,0,0,0,0,0;48,255,3,0,0,0,0,0,0;49,255,3,0,0,0,0,0,0;50,255,3,0,0,0,0,0,0;51,255,3,0,0,0,0,0,0;52,255,3,0,0,0,0,0,0;53,255,3,0,0,0,0,0,0;58,255,3,0,0,0,0,0,0;59,255,3,0,0,0,0,0,0;60,255,3,0,0,0,0,0,0;61,255,3,0,0,0,0,0,0;62,255,3,0,0,0,0,0,0;63,255,3,0,0,0,0,0,0;64,255,3,0,0,0,0,0,0;65,255,3,0,0,0,0,0,0;66,255,3,0,0,0,0,0,0;67,255,3,0,0,0,0,0,0;68,255,3,0,0,0,0,0,0;69,255,3,0,0,0,0,0,0;70,255,3,0,0,0,0,0,0;71,255,3,0,0,0,0,0,0;72,255,3,0,0,0,0,0,0;73,255,3,0,0,0,0,0,0;74,255,3,0,0,0,0,0,0;75,255,3,0,0,0,0,0,0;76,255,3,0,0,0,0,0,0;77,255,3,0,0,0,0,0,0;78,255,3,0,0,0,0,0,0;79,255,3,0,0,0,0,0,0;80,255,3,0,0,0,0,0,0;81,255,3,0,0,0,0,0,0;82,255,3,0,0,0,0,0,0;83,255,3,0,0,0,0,0,0;84,255,3,0,0,0,0,0,0;85,255,3,0,0,0,0,0,0;86,255,3,0,0,0,0,0,0;87,255,3,0,0,0,0,0,0;88,255,3,0,0,0,0,0,0;89,255,3,0,0,0,0,0,0;90,255,3,0,0,0,0,0,0;91,255,3,0,0,0,0,0,0;92,255,3,0,0,0,0,0,0;93,255,3,0,0,0,0,0,0;94,255,3,0,0,0,0,0,0;95,255,3,0,0,0,0,0,0;96,255,3,0,0,0,0,0,0;97,255,3,0,0,0,0,0,0;98,255,3,0,0,0,0,0,0;99,255,3,0,0,0,0,0,0;100,255,3,0,0,0,0,0,0;101,255,3,0,0,0,0,0,0;102,255,3,0,0,0,0,0,0;103,255,3,0,0,0,0,0,0;104,255,3,0,0,0,0,0,0;105,255,3,0,0,0,0,0,0;106,255,3,0,0,0,0,0,0;107,255,3,0,0,0,0,0,0;108,255,3,0,0,0,0,0,0;109,255,3,0,0,0,0,0,0;110,255,3,0,0,0,0,0,0;111,255,3,0,0,0,0,0,0;112,255,3,0,0,0,0,0,0;113,255,3,0,0,0,0,0,0;114,255,3,0,0,0,0,0,0;115,255,3,0,0,0,0,0,0;116,255,3,0,0,0,0,0,0;117,255,3,0,0,0,0,0,0;118,255,3,0,0,0,0,0,0;119,255,3,0,0,0,0,0,0;120,255,3,0,0,0,0,0,0;121,255,3,0,0,0,0,0,0;122,255,3,0,0,0,0,0,0;123,255,3,0,0,0,0,0,0;124,255,3,0,0,0,0,0,0;125,255,3,0,0,0,0,0,0;126,255,3,0,0,0,0,0,0;127,255,3,0,0,0,0,0,0;128,255,3,0,0,0,0,0,0;129,255,3,0,0,0,0,0,0;130,255,3,0,0,0,0,0,0;131,255,3,0,0,0,0,0,0;132,255,3,0,0,0,0,0,0;133,255,3,0,0,0,0,0,0;134,255,3,0,0,0,0,0,0;135,255,3,0,0,0,0,0,0;136,255,3,0,0,0,0,0,0;137,255,3,0,0,0,0,0,0;138,255,3,0,0,0,0,0,0;139,255,3,0,0,0,0,0,0;140,255,3,0,0,0,0,0,0;141,255,3,0,0,0,0,0,0;142,255,3,0,0,0,0,0,0;143,255,3,0,0,0,0,0,0;144,255,3,0,0,0,0,0,0;145,255,3,0,0,0,0,0,0;146,255,3,0,0,0,0,0,0;147,255,3,0,0,0,0,0,0;148,255,3,0,0,0,0,0,0;149,255,3,0,0,0,0,0,0;150,255,3,0,0,0,0,0,0;151,255,3,0,0,0,0,0,0;152,255,3,0,0,0,0,0,0;153,255,3,0,0,0,0,0,0;154,255,3,0,0,0,0,0,0;155,255,3,0,0,0,0,0,0;156,255,3,0,0,0,0,0,0;157,255,3,0,0,0,0,0,0;158,255,3,0,0,0,0,0,0;159,255,3,0,0,0,0,0,0;160,255,3,0,0,0,0,0,0;161,255,3,0,0,0,0,0,0;162,255,3,0,0,0,0,0,0;163,255,3,0,0,0,0,0,0;164,255,3,0,0,0,0,0,0;165,255,3,0,0,0,0,0,0;166,255,3,0,0,0,0,0,0;167,255,3,0,0,0,0,0,0;168,255,3,0,0,0,0,0,0;169,255,3,0,0,0,0,0,0;170,255,3,0,0,0,0,0,0;171,255,3,0,0,0,0,0,0;172,255,3,0,0,0,0,0,0;173,255,3,0,0,0,0,0,0;174,255,3,0,0,0,0,0,0;175,255,3,0,0,0,0,0,0;176,255,3,0,0,0,0,0,0;177,255,3,0,0,0,0,0,0;178,255,3,0,0,0,0,0,0;179,255,3,0,0,0,0,0,0;180,255,3,0,0,0,0,0,0;181,255,3,0,0,0,0,0,0;182,255,3,0,0,0,0,0,0;183,255,3,0,0,0,0,0,0;184,255,3,0,0,0,0,0,0;185,255,3,0,0,0,0,0,0;186,255,3,0,0,0,0,0,0;187,255,3,0,0,0,0,0,0;188,255,3,0,0,0,0,0,0;189,255,3,0,0,0,0,0,0;190,255,3,0,0,0,0,0,0;191,255,3,0,0,0,0,0,0;192,255,3,0,0,0,0,0,0;193,255,3,0,0,0,0,0,0;194,255,3,0,0,0,0,0,0;195,255,3,0,0,0,0,0,0;196,255,3,0,0,0,0,0,0;197,255,3,0,0,0,0,0,0;198,255,3,0,0,0,0,0,0;199,255,3,0,0,0,0,0,0;200,255,3,0,0,0,0,0,0;201,255,3,0,0,0,0,0,0;202,255,3,0,0,0,0,0,0;203,255,3,0,0,0,0,0,0;204,255,3,0,0,0,0,0,0;205,255,3,0,0,0,0,0,0;206,255,3,0,0,0,0,0,0;207,255,3,0,0,0,0,0,0;208,255,3,0,0,0,0,0,0;209,255,3,0,0,0,0,0,0;210,255,3,0,0,0,0,0,0;211,255,3,0,0,0,0,0,0;212,255,3,0,0,0,0,0,0;213,255,3,0,0,0,0,0,0;214,255,3,0,0,0,0,0,0;215,255,3,0,0,0,0,0,0;216,255,3,0,0,0,0,0,0;217,255,3,0,0,0,0,0,0;218,255,3,0,0,0,0,0,0;219,255,3,0,0,0,0,0,0;220,255,3,0,0,0,0,0,0;221,255,3,0,0,0,0,0,0;222,255,3,0,0,0,0,0,0;223,255,3,0,0,0,0,0,0;224,255,3,0,0,0,0,0,0;225,255,3,0,0,0,0,0,0;226,255,3,0,0,0,0,0,0;227,255,3,0,0,0,0,0,0;228,255,3,0,0,0,0,0,0;229,255,3,0,0,0,0,0,0;230,255,3,0,0,0,0,0,0;231,255,3,0,0,0,0,0,0;232,255,3,0,0,0,0,0,0;233,255,3,0,0,0,0,0,0;234,255,3,0,0,0,0,0,0;235,255,3,0,0,0,0,0,0;236,255,3,0,0,0,0,0,0;237,255,3,0,0,0,0,0,0;238,255,3,0,0,0,0,0,0;239,255,3,0,0,0,0,0,0;240,255,3,0,0,0,0,0,0;241,255,3,0,0,0,0,0,0;242,255,3,0,0,0,0,0,0;243,255,3,0,0,0,0,0,0;244,255,3,0,0,0,0,0,0;245,255,3,0,0,0,0,0,0;246,255,3,0,0,0,0,0,0;247,255,3,0,0,0,0,0,0;248,255,3,0,0,0,0,0,0;249,255,3,0,0,0,0,0,0;250,255,3,0,0,0,0,0,0;251,255,3,0,0,0,0,0,0;252,255,3,0,0,0,0,0,0;253,255,3,0,0,0,0,0,0;254,255,3,0,0,0,0,0,0;255,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();

		String para1 = "0,1";
		String para2 = "2,3,4,5";
		String para3 = "6,7,8,9,10,11";
		String para4 = "54,55,56,57";

		res = RuleRandom.IoTIDLength_Random(LengthRule);

		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.CPCTwoByte_Random(res, para1);
		res=RuleRandom.CountryRegionCodeforCPC_Random(res, para2);
		res=RuleRandom.AdminDivision_Random(res, para3);
		res=RuleRandom.MonthDate_Random(res, para4);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//PhoneNumber
	//(?#ALGNAME=MobilePhoneNum)(?#PARA=0,1,2,3,4,5,6){]
	@Test
	public void testPhoneNumber()
	{
		String LengthRule = "11";
		String RawByteRule = "7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();

		String para1 = "0,1,2,3,4,5,6";
		
        res = RuleRandom.IoTIDLength_Random(LengthRule);

		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.MobilePhoneNum_Random(res, para1);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
		
	}
	
	/**
	 * 0812
	 */
	// LS/T_1700-2004_1
	// (?#ALGNAME=GrainAdministrative)(?#PARA=0,1,2,3,4,5){]
	@Test
	public void testLST170020041() {
		String LengthRule = "6";
		FunctionResult res = new FunctionResult();
		String para1 = "0,1,2,3,4,5";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
		res = RuleRandom.GrainAdministrative_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GB/T_13861-2009
	//(?#ALGNAME=HarmfulFactor)(?#PARA=0,1,2,3,4,5){]
	@Test
	public void testGBT138612009()
	{
		String LengthRule = "6";
		FunctionResult res = new FunctionResult();
		String para1 = "0,1,2,3,4,5";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
		res = RuleRandom.HarmfulFactor_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GA_435-2003_1
	//13
	//0,0,0,0,0,240,47,0,16;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;
	//(?#ALGNAME=OneTO07)(?#PARA=1,2){]
	//(?#ALGNAME=AdminDivision)(?#PARA=3,4,5,6,7,8){]
	@Test
	public void testGA43520031()
	{
		String LengthRule = "13";
		String RawByteRule = "0,0,0,0,0,240,47,0,16;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();

		String para1 = "1,2";
		String para2 = "3,4,5,6,7,8";
		
        res = RuleRandom.IoTIDLength_Random(LengthRule);

		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.OneTO07_Random(res, para1);
		res = RuleRandom.AdminDivision_Random(res, para2);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	

	//GA/T_974.30-2011
	//(?#ALGNAME=FireFightrescuePlan)(?#PARA=0,1,2,3,4){]
	@Test
	public void testGAT974302011()
	{
		String LengthRule = "5";
		FunctionResult res = new FunctionResult();
		String para1 = "0,1,2,3,4,5";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
		res = RuleRandom.FireFightrescuePlan_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GB/T_23733
	//15
	//0,0,0,0,0,0,16,0,0;1,0,0,0,0,0,0,64,0;2,0,0,0,0,0,0,0,4;3,0,0,0,0,64,0,0,0;4,0,0,0,0,0,0,128,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;
	//(?#ALGNAME=TwoByteDecimalnt)(?#PARA=12,13){]
	//(?#ALGNAME=StandardMusicCheckCode)(?#PARA=0,1,2,3,4,5,6,7,8,9,10,11,12,13;14){]
	@Test
	public void testGBT23733()
	{
		String LengthRule = "15";
		String RawByteRule = "0,0,0,0,0,0,16,0,0;1,0,0,0,0,0,0,64,0;2,0,0,0,0,0,0,0,4;3,0,0,0,0,64,0,0,0;4,0,0,0,0,0,0,128,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();

		String para1 = "12,13";
		String para2 = "0,1,2,3,4,5,6,7,8,9,10,11,12,13;14";
		
        res = RuleRandom.IoTIDLength_Random(LengthRule);

		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.TwoByteDecimalnt_Random(res, para1);
		res = RuleRandom.StandardMusicCheckCode_Random(res, para2);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GB/T_9649.9-2009
	//2,0,0,0,0,240,255,255,63;3,0,0,0,0,240,255,255,63;4,0,0,0,0,240,255,255,63;5,0,0,0,0,240,255,255,63;
	//6
	//(?#ALGNAME=DZClassify)(?#PARA=0,1){]
	@Test
	public void testGBT964992009()
	{
		String LengthRule = "6";
		String RawByteRule = "2,0,0,0,0,240,255,255,63;3,0,0,0,0,240,255,255,63;4,0,0,0,0,240,255,255,63;5,0,0,0,0,240,255,255,63;";
		FunctionResult res = new FunctionResult();

		String para1 = "0,1";
		
		
        res = RuleRandom.IoTIDLength_Random(LengthRule);

		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.DZClassify_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GB/T_18127-2009_1
	//(?#ALGNAME=PrefixofRetailCommodityNumber)(?#PARA=1,2,3){]
	//(?#ALGNAME=CheckCodeForCommodityCode)(?#PARA=0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16;17){]
	@Test
	public void testGBT1812720091()
	{
		String LengthRule = "18";
		String RawByteRule = "0,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;16,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();

		String para1 = "1,2,3";
		String para2 = "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16;17";
		
		
        res = RuleRandom.IoTIDLength_Random(LengthRule);

		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.PrefixofRetailCommodityNumber_Random(res, para1);
		res = RuleRandom.CheckCodeForCommodityCode_Random(res, para2);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	
	// GB/T_18127-2009_2
	// (?#ALGNAME=PrefixofRetailCommodityNumber)(?#PARA=1,2,3){]
	// (?#ALGNAME=CheckCodeForCommodityCode)(?#PARA=0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16;17){]
    @Test
    public void testGBT1812720092()
    {
    	String LengthRule = "18";
		String RawByteRule = "0,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;16,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();

		String para1 = "1,2,3";
		String para2 = "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16;17";
		
		
        res = RuleRandom.IoTIDLength_Random(LengthRule);

		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.PrefixofRetailCommodityNumber_Random(res, para1);
		res = RuleRandom.CheckCodeForCommodityCode_Random(res, para2);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
    //GB/T_18127-2009_3
    //(?#ALGNAME=PrefixofRetailCommodityNumber)(?#PARA=1,2,3){]
    //(?#ALGNAME=CheckCodeForCommodityCode)(?#PARA=0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16;17){]
    @Test
    public void testGBT1812720093()
    {
    	String LengthRule = "18";
		String RawByteRule = "0,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;16,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();

		String para1 = "1,2,3";
		String para2 = "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16;17";
		
		
        res = RuleRandom.IoTIDLength_Random(LengthRule);

		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.PrefixofRetailCommodityNumber_Random(res, para1);
		res = RuleRandom.CheckCodeForCommodityCode_Random(res, para2);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
    //GB/T_18127-2009_4
    //(?#ALGNAME=PrefixofRetailCommodityNumber)(?#PARA=1,2,3){]
    //(?#ALGNAME=CheckCodeForCommodityCode)(?#PARA=0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16;17){]
    @Test
    public void testGBT1812720094()
    {
    	String LengthRule = "18";
		String RawByteRule = "0,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;16,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();

		String para1 = "1,2,3";
		String para2 = "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16;17";
		
		
        res = RuleRandom.IoTIDLength_Random(LengthRule);

		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.PrefixofRetailCommodityNumber_Random(res, para1);
		res = RuleRandom.CheckCodeForCommodityCode_Random(res, para2);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
    //LS/T_1700-2004_2
    //(?#ALGNAME=AdminDivision)(?#PARA=1,2,3,4,5,6){]
    @Test
    public void testLST170020042()
    {
    	String LengthRule = "9";
		String RawByteRule = "0,128,0,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();

		String para1 = "1,2,3,4,5,6";
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.AdminDivision_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
   
    }
    
    //HJ_608-2011_2
    //(?#ALGNAME=AdminDivision)(?#PARA=0,1,2,3,4,5){]
    @Test
    public void testHJ60820112()
    {
    	String LengthRule = "12";
		String RawByteRule = "6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,0,252,251,254,15,0,0,0;";
		FunctionResult res = new FunctionResult();

		String para1 = "0,1,2,3,4,5";
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.AdminDivision_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
    //YC/T_257.3-2008
    //(?#ALGNAME=AdminDivision)(?#PARA=2,3,4,5,6,7){]
    //(?#ALGNAME=Month)(?#PARA=12,13){]
    @Test
    public void testYCT25732008()
    {
    	String LengthRule = "16";
		String RawByteRule = "0,30,0,0,0,0,0,0,0;1,14,2,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();

		String para1 = "2,3,4,5,6,7";
		String para2="12,13";
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.AdminDivision_Random(res, para1);
		res = RuleRandom.Month_Random(res, para2);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
    //YC/T_257.1-2008_4
    //(?#ALGNAME=AdminDivision)(?#PARA=0,1,2,3,4,5){]
    @Test
    public void testYCT257120084()
    {
    	String LengthRule = "10";
		String RawByteRule = "6,0,0,0,0,48,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();

		String para1 = "0,1,2,3,4,5";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.AdminDivision_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
  
    }
    
    //YC/T_257.1-2008_2
    //(?#ALGNAME=AdminDivision)(?#PARA=0,1,2,3,4,5){]
    @Test
    public void testYCT257120082()
    {
    	String LengthRule = "12";
		String RawByteRule = "6,6,0,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();

		String para1 = "0,1,2,3,4,5";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.AdminDivision_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
    //YC/T_257.1-2008_3
    @Test
    public void testYCT257120083()
    {
    	String LengthRule = "8";
		String RawByteRule = "0,30,0,0,0,0,0,0,0;1,255,3,0,0,0,0,0,0;2,255,3,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
   //GB/T_19251-2003_EANUCC-8
    //(?#ALGNAME=PrefixofRetailCommodityNumber)(?#PARA=6,7,8){]
    //(?#ALGNAME=CheckCodeForCommodityCode)(?#PARA=0,1,2,3,4,5,6,7,8,9,10,11,12;13){]
    @Test
    public void testGBT192512003EANUCC8()
    {
    	String LengthRule = "14";
		String RawByteRule = "0,1,0,0,0,0,0,0,0;1,1,0,0,0,0,0,0,0;2,1,0,0,0,0,0,0,0;3,1,0,0,0,0,0,0,0;4,1,0,0,0,0,0,0,0;5,1,0,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="6,7,8";
		String para2="0,1,2,3,4,5,6,7,8,9,10,11,12;13";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.PrefixofRetailCommodityNumber_Random(res, para1);
		res = RuleRandom.CheckCodeForCommodityCode_Random(res, para2);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
    //Ucode
    //(?#ALGNAME=CountUcode)(?#PARA=1,2,3,4){]
    @Test
    public void testUcode()
    {
    	String LengthRule = "32";
		String RawByteRule = "0,1,0,0,0,0,0,0,0;5,0,126,0,0,240,1,0,0;6,255,255,0,0,240,3,0,0;7,255,255,0,0,240,3,0,0;8,255,255,0,0,240,3,0,0;9,255,255,0,0,240,3,0,0;10,255,255,0,0,240,3,0,0;11,255,255,0,0,240,3,0,0;12,255,255,0,0,240,3,0,0;13,255,255,0,0,240,3,0,0;14,255,255,0,0,240,3,0,0;15,255,255,0,0,240,3,0,0;16,255,255,0,0,240,3,0,0;17,255,255,0,0,240,3,0,0;18,255,255,0,0,240,3,0,0;19,255,255,0,0,240,3,0,0;20,255,255,0,0,240,3,0,0;21,255,255,0,0,240,3,0,0;22,255,255,0,0,240,3,0,0;23,255,255,0,0,240,3,0,0;24,255,255,0,0,240,3,0,0;25,255,255,0,0,240,3,0,0;26,255,255,0,0,240,3,0,0;27,255,255,0,0,240,3,0,0;28,255,255,0,0,240,3,0,0;29,255,255,0,0,240,3,0,0;30,255,255,0,0,240,3,0,0;31,255,255,0,0,240,3,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="1,2,3,4";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.CountUcode_Random(res, para1);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
    //isbn
    //(?#ALGNAME=ISBN13)(?#PARA=0,1,2,3,4,5,6,7,8,9,10,11;12){]
    @Test
    public void testisbn()
    {
    	String LengthRule = "13";
		String RawByteRule = "0,0,2,0,0,0,0,0,0;1,128,0,0,0,0,0,0,0;2,0,3,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="0,1,2,3,4,5,6,7,8,9,10,11;12";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.ISBN13_Random(res, para1);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
    //GB/T_19251-2003_UCC-12
    //(?#ALGNAME=PrefixofRetailCommodityNumber)(?#PARA=2,3,4){]
    //(?#ALGNAME=CheckCodeForCommodityCode)(?#PARA=0,1,2,3,4,5,6,7,8,9,10,11,12;13){]
    @Test
    public void testGBT192512003UCC12()
    {
    	String LengthRule = "14";
		String RawByteRule = "0,1,0,0,0,0,0,0,0;1,1,0,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="2,3,4";
		String para2="0,1,2,3,4,5,6,7,8,9,10,11,12;13";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.PrefixofRetailCommodityNumber_Random(res, para1);
		res = RuleRandom.CheckCodeForCommodityCode_Random(res, para2);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
    //UPC-A
    //(?#ALGNAME=PrefixofRetailCommodityNumber)(?#PARA=0,1,2){]
    //(?#ALGNAME=CheckCodeForCommodityCode)(?#PARA=0,1,2,3,4,5,6,7,8,9,10;11){]
    @Test
    public void testUPCA()
    {
    	String LengthRule = "12";
		String RawByteRule = "3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="0,1,2";
		String para2="0,1,2,3,4,5,6,7,8,9,10;11";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.PrefixofRetailCommodityNumber_Random(res, para1);
		res = RuleRandom.CheckCodeForCommodityCode_Random(res, para2);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
    //UPC-E
    //(?#ALGNAME=PrefixofRetailCommodityNumber)(?#PARA=0,1,2){]
    //(?#ALGNAME=CheckCodeForCommodityCode)(?#PARA=0,1,2,3,4,5,6;7){]
    @Test
    public void testUPCE()
    {
    	String LengthRule = "8";
		String RawByteRule = "3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="0,1,2";
		String para2="0,1,2,3,4,5,6;7";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.PrefixofRetailCommodityNumber_Random(res, para1);
		res = RuleRandom.CheckCodeForCommodityCode_Random(res, para2);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
    //EPC_64_2
    @Test
    public void testEPC642()
    {
    	String LengthRule = "16";
		String RawByteRule = "0,255,255,0,0,240,3,0,0;1,255,255,0,0,240,3,0,0;2,255,255,0,0,240,3,0,0;3,255,255,0,0,240,3,0,0;4,255,255,0,0,240,3,0,0;5,255,255,0,0,240,3,0,0;6,255,255,0,0,240,3,0,0;7,255,255,0,0,240,3,0,0;8,255,255,0,0,240,3,0,0;9,255,255,0,0,240,3,0,0;10,255,255,0,0,240,3,0,0;11,255,255,0,0,240,3,0,0;12,255,255,0,0,240,3,0,0;13,255,255,0,0,240,3,0,0;14,255,255,0,0,240,3,0,0;15,255,255,0,0,240,3,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    
    }
    //EPC_64_1
    @Test
    public void testEPC641()
    {
    	String LengthRule = "64";
		String RawByteRule = "0,3,0,0,0,0,0,0,0;1,3,0,0,0,0,0,0,0;2,3,0,0,0,0,0,0,0;3,3,0,0,0,0,0,0,0;4,3,0,0,0,0,0,0,0;5,3,0,0,0,0,0,0,0;6,3,0,0,0,0,0,0,0;7,3,0,0,0,0,0,0,0;8,3,0,0,0,0,0,0,0;9,3,0,0,0,0,0,0,0;10,3,0,0,0,0,0,0,0;11,3,0,0,0,0,0,0,0;12,3,0,0,0,0,0,0,0;13,3,0,0,0,0,0,0,0;14,3,0,0,0,0,0,0,0;15,3,0,0,0,0,0,0,0;16,3,0,0,0,0,0,0,0;17,3,0,0,0,0,0,0,0;18,3,0,0,0,0,0,0,0;19,3,0,0,0,0,0,0,0;20,3,0,0,0,0,0,0,0;21,3,0,0,0,0,0,0,0;22,3,0,0,0,0,0,0,0;23,3,0,0,0,0,0,0,0;24,3,0,0,0,0,0,0,0;25,3,0,0,0,0,0,0,0;26,3,0,0,0,0,0,0,0;27,3,0,0,0,0,0,0,0;28,3,0,0,0,0,0,0,0;29,3,0,0,0,0,0,0,0;30,3,0,0,0,0,0,0,0;31,3,0,0,0,0,0,0,0;32,3,0,0,0,0,0,0,0;33,3,0,0,0,0,0,0,0;34,3,0,0,0,0,0,0,0;35,3,0,0,0,0,0,0,0;36,3,0,0,0,0,0,0,0;37,3,0,0,0,0,0,0,0;38,3,0,0,0,0,0,0,0;39,3,0,0,0,0,0,0,0;40,3,0,0,0,0,0,0,0;41,3,0,0,0,0,0,0,0;42,3,0,0,0,0,0,0,0;43,3,0,0,0,0,0,0,0;44,3,0,0,0,0,0,0,0;45,3,0,0,0,0,0,0,0;46,3,0,0,0,0,0,0,0;47,3,0,0,0,0,0,0,0;48,3,0,0,0,0,0,0,0;49,3,0,0,0,0,0,0,0;50,3,0,0,0,0,0,0,0;51,3,0,0,0,0,0,0,0;52,3,0,0,0,0,0,0,0;53,3,0,0,0,0,0,0,0;54,3,0,0,0,0,0,0,0;55,3,0,0,0,0,0,0,0;56,3,0,0,0,0,0,0,0;57,3,0,0,0,0,0,0,0;58,3,0,0,0,0,0,0,0;59,3,0,0,0,0,0,0,0;60,3,0,0,0,0,0,0,0;61,3,0,0,0,0,0,0,0;62,3,0,0,0,0,0,0,0;63,3,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    
    }
    
    //GB/T_16827-1997
    //(?#ALGNAME=CheckCodeForCommodityCode)(?#PARA=0,1,2,3,4,5,6,7,8,9,10,11;12){]
    @Test
    public void testGBT168271997()
    {
    	String LengthRule = "15";
		String RawByteRule = "0,0,2,0,0,0,0,0,0;1,128,0,0,0,0,0,0,0;2,128,0,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;13,63,2,0,0,0,0,0,0;14,126,2,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		String para1="0,1,2,3,4,5,6,7,8,9,10,11;12";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.CheckCodeForCommodityCode_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    	
    }
    
    //GB_12904-2008
    //(?#ALGNAME=CheckCodeForCommodityCode)(?#PARA=0,1,2,3,4,5,6;7){]
    @Test
    public void testGB129042008()
    {
    	String LengthRule = "8";
		String RawByteRule = "0,64,0,0,0,0,0,0,0;1,0,2,0,0,0,0,0,0;2,63,0,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		String para1="0,1,2,3,4,5,6;7";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.CheckCodeForCommodityCode_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
    //EPC_96_1
    @Test
    public void testEPC961()
    {
    	String LengthRule = "96";
		String RawByteRule = "0,1,0,0,0,0,0,0,0;1,1,0,0,0,0,0,0,0;2,2,0,0,0,0,0,0,0;3,1,0,0,0,0,0,0,0;4,1,0,0,0,0,0,0,0;5,1,0,0,0,0,0,0,0;6,1,0,0,0,0,0,0,0;7,3,0,0,0,0,0,0,0;8,3,0,0,0,0,0,0,0;9,3,0,0,0,0,0,0,0;10,3,0,0,0,0,0,0,0;11,3,0,0,0,0,0,0,0;12,3,0,0,0,0,0,0,0;13,3,0,0,0,0,0,0,0;14,3,0,0,0,0,0,0,0;15,3,0,0,0,0,0,0,0;16,3,0,0,0,0,0,0,0;17,3,0,0,0,0,0,0,0;18,3,0,0,0,0,0,0,0;19,3,0,0,0,0,0,0,0;20,3,0,0,0,0,0,0,0;21,3,0,0,0,0,0,0,0;22,3,0,0,0,0,0,0,0;23,3,0,0,0,0,0,0,0;24,3,0,0,0,0,0,0,0;25,3,0,0,0,0,0,0,0;26,3,0,0,0,0,0,0,0;27,3,0,0,0,0,0,0,0;28,3,0,0,0,0,0,0,0;29,3,0,0,0,0,0,0,0;30,3,0,0,0,0,0,0,0;31,3,0,0,0,0,0,0,0;32,3,0,0,0,0,0,0,0;33,3,0,0,0,0,0,0,0;34,3,0,0,0,0,0,0,0;35,3,0,0,0,0,0,0,0;36,3,0,0,0,0,0,0,0;37,3,0,0,0,0,0,0,0;38,3,0,0,0,0,0,0,0;39,3,0,0,0,0,0,0,0;40,3,0,0,0,0,0,0,0;41,3,0,0,0,0,0,0,0;42,3,0,0,0,0,0,0,0;43,3,0,0,0,0,0,0,0;44,3,0,0,0,0,0,0,0;45,3,0,0,0,0,0,0,0;46,3,0,0,0,0,0,0,0;47,3,0,0,0,0,0,0,0;48,3,0,0,0,0,0,0,0;49,3,0,0,0,0,0,0,0;50,3,0,0,0,0,0,0,0;51,3,0,0,0,0,0,0,0;52,3,0,0,0,0,0,0,0;53,3,0,0,0,0,0,0,0;54,3,0,0,0,0,0,0,0;55,3,0,0,0,0,0,0,0;56,3,0,0,0,0,0,0,0;57,3,0,0,0,0,0,0,0;58,3,0,0,0,0,0,0,0;59,3,0,0,0,0,0,0,0;60,3,0,0,0,0,0,0,0;61,3,0,0,0,0,0,0,0;62,3,0,0,0,0,0,0,0;63,3,0,0,0,0,0,0,0;64,3,0,0,0,0,0,0,0;65,3,0,0,0,0,0,0,0;66,3,0,0,0,0,0,0,0;67,3,0,0,0,0,0,0,0;68,3,0,0,0,0,0,0,0;69,3,0,0,0,0,0,0,0;70,3,0,0,0,0,0,0,0;71,3,0,0,0,0,0,0,0;72,3,0,0,0,0,0,0,0;73,3,0,0,0,0,0,0,0;74,3,0,0,0,0,0,0,0;75,3,0,0,0,0,0,0,0;76,3,0,0,0,0,0,0,0;77,3,0,0,0,0,0,0,0;78,3,0,0,0,0,0,0,0;79,3,0,0,0,0,0,0,0;80,3,0,0,0,0,0,0,0;81,3,0,0,0,0,0,0,0;82,3,0,0,0,0,0,0,0;83,3,0,0,0,0,0,0,0;84,3,0,0,0,0,0,0,0;85,3,0,0,0,0,0,0,0;86,3,0,0,0,0,0,0,0;87,3,0,0,0,0,0,0,0;88,3,0,0,0,0,0,0,0;89,3,0,0,0,0,0,0,0;90,3,0,0,0,0,0,0,0;91,3,0,0,0,0,0,0,0;92,3,0,0,0,0,0,0,0;93,3,0,0,0,0,0,0,0;94,3,0,0,0,0,0,0,0;95,3,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    
    }
    
    //EPC_96_2
    @Test
    public void testEPC962()
    {
    	String LengthRule = "24";
		String RawByteRule = "0,4,0,0,0,0,0,0,0;1,3,0,0,0,0,0,0,0;2,255,255,0,0,240,3,0,0;3,255,255,0,0,240,3,0,0;4,255,255,0,0,240,3,0,0;5,255,255,0,0,240,3,0,0;6,255,255,0,0,240,3,0,0;7,255,255,0,0,240,3,0,0;8,255,255,0,0,240,3,0,0;9,255,255,0,0,240,3,0,0;10,255,255,0,0,240,3,0,0;11,255,255,0,0,240,3,0,0;12,255,255,0,0,240,3,0,0;13,255,255,0,0,240,3,0,0;14,255,255,0,0,240,3,0,0;15,255,255,0,0,240,3,0,0;16,255,255,0,0,240,3,0,0;17,255,255,0,0,240,3,0,0;18,255,255,0,0,240,3,0,0;19,255,255,0,0,240,3,0,0;20,255,255,0,0,240,3,0,0;21,255,255,0,0,240,3,0,0;22,255,255,0,0,240,3,0,0;23,255,255,0,0,240,3,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
    //parcel
    //(?#ALGNAME=AdminDivision)(?#PARA=0,1,2,3,4,5){]
    //(?#ALGNAME=TwoByteDecimalnt)(?#PARA=17,18){]
    @Test
    public void testparcel()
    {
    	String LengthRule = "9";
		String RawByteRule = "6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,0,0,0,0,0,36,0,32;13,0,0,0,0,240,3,64,28;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;16,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="0,1,2,3,4,5";
		String para2="17,18";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.AdminDivision_Random(res, para1);
		res = RuleRandom.TwoByteDecimalnt_Random(res, para2);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    	
    }
    
    //GA_398.16-2002
    //(?#ALGNAME=AdminDivision)(?#PARA=1,2,3,4,5,6){]
    //(?#ALGNAME=Month)(?#PARA=14,15){]
    //(?#ALGNAME=FourByteDecimalnt)(?#PARA=16,17,18,19){]
    @Test
    public void testGA398162002()
    {
    	String LengthRule = "20";
		String RawByteRule = "0,0,0,0,0,128,0,0,0;7,255,255,255,255,255,255,255,63;8,255,255,255,255,255,255,255,63;9,255,255,255,255,255,255,255,63;10,254,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="1,2,3,4,5,6";
		String para2="14,15";
		String para3="16,17,18,19";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.AdminDivision_Random(res, para1);
		res = RuleRandom.Month_Random(res, para2);
		res = RuleRandom.FourByteDecimalnt_Random(res, para3);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    	
    }
    
 
    //HJ/T_417-2007
    //(?#ALGNAME=EnvironmentalInformation)(?#PARA=8,-1){]
    @Test
    public void testHJT4172007()
    {
    	int k=0;
    	while(k<100){
    	//String LengthRule = "9,-1";
    	String LengthRule = "9";
		String RawByteRule = "0,255,3,0,0,0,0,0,0;1,254,3,0,0,0,0,0,0;2,255,3,0,0,0,0,0,0;3,254,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,254,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,254,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="8,-1";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.EnvironmentalInformation_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);k++;}
    }
    
    
    
    //LS/T_1709-2004
    //(?#ALGNAME=GainsDiseases)(?#PARA=0,1,2,3,4){]
    @Test
    public void testLST17092004()
    {
    	String LengthRule = "5";
		FunctionResult res = new FunctionResult();
	    String para1="0,1,2,3,4";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        res = RuleRandom.GainsDiseases_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
    //YC/T_213.3-2006
    //14
    //(?#ALGNAME=TabaccoMachineProduct)(?#PARA=2,3,4,5,6){]
    //(?#ALGNAME=TabaccoMachineProducer)(?#PARA=12,13){]
    @Test
    public void testYCT21332006()
    {
    	String LengthRule = "14";
		String RawByteRule = "0,8,0,0,0,0,0,0,0;1,4,0,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="2,3,4,5,6";
		String para2="12,13";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.TabaccoMachineProduct_Random(res, para1);
		res = RuleRandom.TabaccoMachineProducer_Random(res, para2);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
 
    
	// GB/T_23730.1-2009
	// 21
	// (?#ALGNAME=MOD3736)(?#PARA=4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19;20){]
    @Test
    public void testGBT2373012009()
    {   
    	
    	String LengthRule = "21";
		String RawByteRule = "0,0,0,0,0,0,16,0,0;1,0,0,0,0,0,0,64,0;2,0,0,0,0,16,0,0,0;3,0,0,0,0,0,0,2,0;4,255,3,0,0,240,3,0,0;5,255,3,0,0,240,3,0,0;6,255,3,0,0,240,3,0,0;7,255,3,0,0,240,3,0,0;8,255,3,0,0,240,3,0,0;9,255,3,0,0,240,3,0,0;10,255,3,0,0,240,3,0,0;11,255,3,0,0,240,3,0,0;12,255,3,0,0,240,3,0,0;13,255,3,0,0,240,3,0,0;14,255,3,0,0,240,3,0,0;15,255,3,0,0,240,3,0,0;16,255,3,0,0,240,3,0,0;17,255,3,0,0,240,3,0,0;18,255,3,0,0,240,3,0,0;19,255,3,0,0,240,3,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19;20";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.MOD3736_Random(res, para1);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    	}
    
    //industrycommerce
    //(?#ALGNAME=BusinessAdminis)(?#PARA=0,1,2,3,4,5){]
    //(?#ALGNAME=BussManaCheck)(?#PARA=0,1,2,3,4,5,6,7,8,9,10,11,12,13,14){]
    @Test
    public void testindustrycommerce()
    {  
    	
    	String LengthRule = "15";
		String RawByteRule = "6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="0,1,2,3,4,5";
		String para2="0,1,2,3,4,5,6,7,8,9,10,11,12,13;14";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.BusinessAdminis_Random(res, para1);
		res = RuleRandom.BussManaCheck_Random(res, para2);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
		
    }
    
    //GB/T_25071-2010_2
    //(?#ALGNAME=JadejewelryMaterialclassif)(?#PARA=0,1,2,3,4,5){]
    @Test
    public void testGBT2507120102()
    {
    	String LengthRule = "6";
		
		FunctionResult res = new FunctionResult();
		String para1="0,1,2,3,4,5";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        res = RuleRandom.JadejewelryMaterialclassif_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
		
    }
    
    //CH/Z_9002-2007
	//15-16
	//(?#ALGNAME=AdminDivision)(?#PARA=0,1,2,3,4,5){]
	//(?#ALGNAME=TwoOrThree)(?#PARA=13,-1){]
    @Test
    public void testCHZ90022007()
    {

    	String LengthRule = "15-16";
		String RawByteRule = "6,15,0,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="0,1,2,3,4,5";
		String para2="13,-1";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.AdminDivision_Random(res, para1);
		res = RuleRandom.TwoOrThree_Random(res, para2);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
		
    }
    
    //JG/T_151-2003
	//(?#ALGNAME=ConstructionProducts)(?#PARA=0,1,2,3,4){]
    @Test
    public void testJGT1512003()
    {
    	String LengthRule = "5";
		
		FunctionResult res = new FunctionResult();
		String para1="0,1,2,3,4,5";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        

		res = RuleRandom.ConstructionProducts_Random(res, para1);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
		
    }
    
    //JT/T_307.5-1999
    //(?#ALGNAME=AdminDivision)(?#PARA=2,3,4,5,6,7){]
    //(?#ALGNAME=A2EOrNull)(?#PARA=7,-1){]
    @Test
    public void testJTT30751999()
    {
    	String LengthRule = "9";
		String RawByteRule = "0,12,0,0,0,0,0,0,0;1,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="2,3,4,5,6,7";
		String para2="7,-1";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.AdminDivision_Random(res, para1);
		res = RuleRandom.A2EOrNull_Random(res,para2);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
    //JT/T_132-2003_38
    //(?#ALGNAME=AdminDivision)(?#PARA=9,10,11,12,13,14){]
    @Test
    public void testJTT132200338()
    {
    	String LengthRule = "15";
		String RawByteRule = "0,0,0,0,0,0,4,0,0;1,0,0,0,0,0,0,64,56;2,255,3,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,0,0,0,0,128,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="9,10,11,12,13,14";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.AdminDivision_Random(res, para1);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
    //GA/T_852.3-2009
    //25
    //(?#ALGNAME=MonthDate)(?#PARA=18,19,20,21){]
    @Test
    public void testGAT85232009()
    {
    	String LengthRule = "25";
		String RawByteRule = "0,0,0,0,0,0,40,0,0;1,255,3,0,0,0,0,0,0;2,255,3,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;16,255,3,0,0,0,0,0,0;17,255,3,0,0,0,0,0,0;22,255,3,0,0,0,0,0,0;23,255,3,0,0,0,0,0,0;24,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="18,19,20,21";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.MonthDate_Random(res, para1);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
    //GB/T_23833-2009_1-4
    //(?#ALGNAME=PrefixofRetailCommodityNumber)(?#PARA=1,2,3){]
    //(?#ALGNAME=CheckCodeForCommodityCode)(?#PARA=0,1,2,3,4,5,6,7,8,9,10,11,12;13){]
    //(?#ALGNAME=GraiSerialNo)(?#PARA=14,16,-1){]
    @Test
    public void testGBT23833200914()
    {
    	
    	String LengthRule = "15-30";
		String RawByteRule = "0,1,0,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="1,2,3";
		String para2="0,1,2,3,4,5,6,7,8,9,10,11,12;13";
		String para3="14,16,-1";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.PrefixofRetailCommodityNumber_Random(res, para1);
		res = RuleRandom.CheckCodeForCommodityCode_Random(res, para2);
		res = RuleRandom.GraiSerialNo_Random(res, para3);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
    //epay
    //(?#ALGNAME=TwoByteDecimalnt)(?#PARA=2,3){]
    //(?#ALGNAME=TwoByteDecimalnt)(?#PARA=7,8){]
    @Test
    public void testepay()
    {
    	String LengthRule = "9";
		String RawByteRule = "0,254,3,0,0,0,0,0,0;1,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="2,3";
		String para2="7,8";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.TwoByteDecimalnt_Random(res, para1);
		res = RuleRandom.TwoByteDecimalnt_Random(res, para2);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
    //CJ/T_214-2007_1
    //(?#ALGNAME=AdminDivision)(?#PARA=0,1,2,3,4,5){]
    //(?#ALGNAME=Bigcode)(?#PARA=6,7){]
    //(?#ALGNAME=TwoByteDecimalnt)(?#PARA=8,9){]
    @Test
    public void testCJT21420071()
    {
    	String LengthRule = "10";
		
		FunctionResult res = new FunctionResult();
		String para1="0,1,2,3,4,5";
		String para2="6,7";
		String para3="8,9";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        res = RuleRandom.AdminDivision_Random(res, para1);
		res = RuleRandom.Bigcode_Random(res, para2);
		res = RuleRandom.TwoByteDecimalnt_Random(res, para3);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
	// CJ/T_214-2007_2
	// (?#ALGNAME=AdminDivision)(?#PARA=0,1,2,3,4,5){]
	// (?#ALGNAME=Littlecode)(?#PARA=6,7){]
	// (?#ALGNAME=TwoByteDecimalnt)(?#PARA=8,9){]
	@Test
	public void testCJT21420072() {
		String LengthRule = "10";

		FunctionResult res = new FunctionResult();
		String para1 = "0,1,2,3,4,5";
		String para2 = "6,7";
		String para3 = "8,9";

		res = RuleRandom.IoTIDLength_Random(LengthRule);
		res = RuleRandom.AdminDivision_Random(res, para1);
		res = RuleRandom.Littlecode_Random(res, para2);
		res = RuleRandom.TwoByteDecimalnt_Random(res, para3);

		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//LS/T_1710-2004
	//(?#ALGNAME=GainStoreHouse)(?#PARA=0,1,2,3,4,5,6,7){]
	@Test
	public void testLST17102004()
	{
		String LengthRule = "8";

		FunctionResult res = new FunctionResult();
		String para1 = "0,1,2,3,4,5,6,7";
		

		res = RuleRandom.IoTIDLength_Random(LengthRule);
		res = RuleRandom.GainStoreHouse_Random(res, para1);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GB/T_13923-2006
	//(?#ALGNAME=GeographicInfoCode)(?#PARA=0,1,2,3,4,5){]
	@Test
	public void testGBT139232006()
	{
		String LengthRule = "6";

		FunctionResult res = new FunctionResult();
		String para1 = "0,1,2,3,4,5";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
		res = RuleRandom.GeographicInfoCode_Random(res, para1);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
		
	}
	
	//GB/T_15514-2008
	@Test
	public void testGBT155142008()
	{
		String LengthRule = "5";
		String RawByteRule = "0,0,0,0,0,64,0,0,0;1,0,0,0,0,0,0,2,0;2,0,0,0,0,240,255,255,63;3,0,0,0,0,240,255,255,63;4,0,0,0,0,240,255,255,63;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
    
	//GA_557.12-2005
	//(?#ALGNAME=AdminDivision)(?#PARA=0,1,2,3,4,5){]
	//(?#ALGNAME=MonthDate)(?#PARA=12,13,14,15){]
	@Test
	public void testGA557122005()
	{
		String LengthRule = "20";
		String RawByteRule = "6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;16,255,3,0,0,0,0,0,0;17,255,3,0,0,0,0,0,0;18,255,3,0,0,0,0,0,0;19,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="0,1,2,3,4,5";
		String para2="12,13,14,15";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.AdminDivision_Random(res, para1);
		res = RuleRandom.MonthDate_Random(res, para2);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GB/T_23794-2009
	@Test
	public void testGBT237942009()
	{
		String LengthRule = "6";
		String RawByteRule = "0,0,0,0,0,0,0,8,0;1,0,0,0,0,0,0,16,0;2,1,0,0,0,0,0,0,0;3,126,0,0,0,0,0,0,0;4,1,0,0,0,0,0,0,0;5,254,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GB/T_25071-2010_1
	//(?#ALGNAME=JadejewelryClass)(?#PARA=0,1,2,3,4){]
	@Test
	public void testGBT2507120101()
	{
		String LengthRule = "5";

		FunctionResult res = new FunctionResult();
		String para1 = "0,1,2,3,4";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
		res = RuleRandom.JadejewelryClass_Random(res, para1);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GA_431-2004_1
	//(?#ALGNAME=OneTO05)(?#PARA=1,2){]
	//(?#ALGNAME=AdminDivision)(?#PARA=3,4,5,6,7,8){]
	//(?#ALGNAME=FourByteDecimalnt)(?#PARA=9,10,11,12){]
	@Test
	public void testGA43120041()
	{
		String LengthRule = "13";
		String RawByteRule = "0,0,0,0,0,240,47,0,16;";
		FunctionResult res = new FunctionResult();
		String para1="1,2";
		String para2="3,4,5,6,7,8";
		String para3="9,10,11,12";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.OneTO05_Random(res, para1);
		res = RuleRandom.AdminDivision_Random(res, para2);
		res = RuleRandom.FourByteDecimalnt_Random(res, para3);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GB/T_20336-2006/ISO_6261:1995
	@Test
	public void testGBT203362006ISO62611995()
	{
		String LengthRule = "10";
		String RawByteRule = "0,0,0,0,0,240,47,64,0;1,255,3,0,0,0,0,0,0;2,255,3,0,0,0,0,0,0;3,0,0,0,0,0,238,251,31;4,0,0,0,0,64,0,73,0;5,0,0,0,0,240,201,237,6;6,0,0,0,0,0,194,88,21;7,0,0,0,0,240,7,10,0;8,0,0,0,0,0,128,32,0;9,0,0,0,0,240,201,237,6;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GB/T_21381-2008_3
	//(?#ALGNAME=AdminDivision)(?#PARA=0,1,2,3,4,5){]
	@Test
	public void testGBT2138120083()
	{
		String LengthRule = "13";
		String RawByteRule = "6,255,3,0,0,240,255,255,63;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="0,1,2,3,4,5";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.AdminDivision_Random(res, para1);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GB/T_21381-2008_2
	//(?#ALGNAME=AdminDivision)(?#PARA=0,1,2,3,4,5){]
	@Test
	public void testGBT2138120082()
	{
		String LengthRule = "13";
		String RawByteRule = "6,255,3,0,0,240,255,255,63;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="0,1,2,3,4,5";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.AdminDivision_Random(res, para1);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GB/T_21381-2008_1
	//(?#ALGNAME=AdminDivision)(?#PARA=0,1,2,3,4,5){]
	@Test
	public void testGBT2138120081()
	{
		String LengthRule = "10";
		String RawByteRule = "6,255,3,0,0,240,255,255,63;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="0,1,2,3,4,5";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.AdminDivision_Random(res, para1);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
		
	}
	
	//GB/T_21381-2008_5
	//(?#ALGNAME=AdminDivision)(?#PARA=0,1,2,3,4,5){]
	@Test
	public void testGBT2138120085()
	{
		String LengthRule = "16";
		String RawByteRule = "6,255,3,0,0,240,255,255,63;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="0,1,2,3,4,5";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.AdminDivision_Random(res, para1);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GB/T_21381-2008_4
	//(?#ALGNAME=AdminDivision)(?#PARA=0,1,2,3,4,5){]
	@Test
	public void testGBT2138120084()
	{
		String LengthRule = "17";
		String RawByteRule = "6,255,3,0,0,240,255,255,63;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,0,0,0,0,240,1,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;16,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="0,1,2,3,4,5";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.AdminDivision_Random(res, para1);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//LS/T_1701-2004_2
	//(?#ALGNAME=AdminDivision)(?#PARA=1,2,3,4,5,6){]
	@Test
	public void testLST170120042()
	{
		String LengthRule = "11";
		String RawByteRule = "0,8,0,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="1,2,3,4,5,6";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.AdminDivision_Random(res, para1);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GB/T_26319-2010_3
	@Test
	public void testGBT2631920103()
	{
		String LengthRule = "5";
		String RawByteRule = "0,255,3,0,0,0,0,0,0;1,255,3,0,0,0,0,0,0;2,255,3,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//HJ_520-2009
	//(?#ALGNAME=Wastewater)(?#PARA=5,-1){]
	@Test
	public void testHJ5202009()
	{
		String LengthRule = "6,-1";
		String RawByteRule = "0,254,3,0,0,0,0,0,0;1,255,3,0,0,0,0,0,0;2,254,3,0,0,0,0,0,0;3,254,3,0,0,0,0,0,0;4,254,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="5,-1";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.Wastewater_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	/**
	 * 0814
	 */
	//待测
	//JT/T_307.4-1998
	//(?#ALGNAME=HighwayMaintenance4)(?#PARA=0,1,2,3,4,5,6,7,8,9){]
	@Test
	public void testJTT30741998()
	{
		String LengthRule = "10";
		String para1="0,1,2,3,4,5,6,7,8,9";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        res=RuleRandom.HighwayMaintenance4_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
		
	}
	

	//DL/T_700.1-1999_3
	//(?#ALGNAME=OneTO15)(?#PARA=3,4){](?#ALGNAME=TwoByteDecimalnt)(?#PARA=5,6){]
	@Test
	public void testDLT700119993()
	{
		String LengthRule = "17";
		String RawByteRule = "0,2,0,0,0,0,0,0,0;1,1,0,0,0,0,0,0,0;2,2,0,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;16,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="3,4";
		String para2="5,6";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.OneTO15_Random(res, para1);
		res = RuleRandom.TwoByteDecimalnt_Random(res, para2);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GB/T_15161-1994
	//(?#ALGNAME=TreeDiseaseCode)(?#PARA=0,1,2,3,4,5){]
	@Test
	public void testGBT151611994()
	{
		String LengthRule = "6";
		String para1="0,1,2,3,4,5";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        res=RuleRandom.TreeDiseaseCode_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GB/T_10302-2010
	//(?#ALGNAME=RailwayStationCode)(?#PARA=0,1,2,3,4){]
	@Test
	public void testGBT103022010()
	{
		String LengthRule = "5";
		String para1="0,1,2,3,4";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        res=RuleRandom.RailwayStationCode_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//DL/T_700.1-1999_1
	//(?#ALGNAME=TwoByteDecimalnt)(?#PARA=4,5){]
	@Test
	public void testDLT700119991()
	{
		String LengthRule = "17";
		String RawByteRule = "0,2,0,0,0,0,0,0,0;1,255,3,0,0,0,0,0,0;2,255,3,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;16,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="4,5";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.TwoByteDecimalnt_Random(res, para1);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//DL/T_700.1-1999_2
	//(?#ALGNAME=TwoByteDecimalnt)(?#PARA=5,6){]
	@Test
	public void testDLT700119992()
	{
		String LengthRule = "13";
		String RawByteRule = "0,2,0,0,0,0,0,0,0;1,2,0,0,0,0,0,0,0;2,128,0,0,0,0,0,0,0;3,1,0,0,0,0,0,0,0;4,14,0,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="5,6";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.TwoByteDecimalnt_Random(res, para1);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);

		
	}
	
	//GB/T_28028-2011
	@Test
	public void testGBT280282011()
	{
		String LengthRule = "12";
		String RawByteRule = "0,255,1,0,0,0,0,0,0;1,31,0,0,0,0,0,0,0;2,127,0,0,0,0,0,0,0;3,31,0,0,0,0,0,0,0;4,85,0,0,0,0,0,0,0;5,30,0,0,0,0,0,0,0;6,127,0,0,0,0,0,0,0;7,31,0,0,0,0,0,0,0;8,85,0,0,0,0,0,0,0;9,30,0,0,0,0,0,0,0;10,127,0,0,0,0,0,0,0;11,31,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		RuleRandom.DisplayRandomCode(res.FunctionResult);

	}
	
	//GB/T_10114-2003
	//(?#ALGNAME=AdminDivision)(?#PARA=0,1,2,3,4,5){]
	@Test
	public void testGBT101142003()
	{
		String LengthRule = "9";
		String RawByteRule = "6,15,0,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="0,1,2,3,4,5";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.AdminDivision_Random(res, para1);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//YC/T_257.2-2008
	//(?#ALGNAME=AdminDivision)(?#PARA=0,1,2,3,4,5){]
	@Test
	public void testYCT25722008()
	{
		String LengthRule = "14";
		String RawByteRule = "6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="0,1,2,3,4,5";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.AdminDivision_Random(res, para1);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GA_300.1-2001
	//(?#ALGNAME=AdminDivision)(?#PARA=0,1,2,3,4,5){]
	@Test
	public void testGA30012001()
	{
		String LengthRule = "9";
		String RawByteRule = "6,254,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="0,1,2,3,4,5";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.AdminDivision_Random(res, para1);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GB/T_17296-2009
	@Test
	public void testGBT172962009()
	{
		String LengthRule = "8";
		String RawByteRule = "0,0,0,0,0,240,143,1,0;1,255,3,0,0,0,0,0,0;2,255,3,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GB/T_28422-2012_1
	@Test
	public void testGBT2842220121()
	{
		String LengthRule = "8";
		String RawByteRule = "0,255,255,0,0,240,3,0,0;1,255,255,0,0,240,3,0,0;2,255,255,0,0,240,3,0,0;3,255,255,0,0,240,3,0,0;4,255,255,0,0,240,3,0,0;5,255,255,0,0,240,3,0,0;6,255,255,0,0,240,3,0,0;7,255,255,0,0,240,3,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GA_557.4-2005
	@Test
	public void testGA55742005()
	{
		String LengthRule = "9";
		String RawByteRule = "0,3,0,0,0,0,0,0,0;1,3,0,0,0,0,0,0,0;2,3,0,0,0,0,0,0,0;3,3,0,0,0,0,0,0,0;4,3,0,0,0,0,0,0,0;5,3,0,0,0,0,0,0,0;6,3,0,0,0,0,0,0,0;7,3,0,0,0,0,0,0,0;8,3,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GB/T_28422-2012_4
	@Test
	public void testGBT2842220124()
	{
		String LengthRule = "16";
		String RawByteRule = "0,255,255,0,0,240,3,0,0;1,255,255,0,0,240,3,0,0;2,255,255,0,0,240,3,0,0;3,255,255,0,0,240,3,0,0;4,255,255,0,0,240,3,0,0;5,255,255,0,0,240,3,0,0;6,255,255,0,0,240,3,0,0;7,255,255,0,0,240,3,0,0;8,255,255,0,0,240,3,0,0;9,255,255,0,0,240,3,0,0;10,255,255,0,0,240,3,0,0;11,255,255,0,0,240,3,0,0;12,255,255,0,0,240,3,0,0;13,255,255,0,0,240,3,0,0;14,255,255,0,0,240,3,0,0;15,255,255,0,0,240,3,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GB/T_28422-2012_2
	//(?#ALGNAME=First2CharsofAdminDivision)(?#PARA=0,1){]
	@Test
	public void testGBT2842220122()
	{
		String LengthRule = "16";
		String RawByteRule = "2,255,255,0,0,240,3,0,0;3,255,255,0,0,240,3,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,255,0,0,240,3,0,0;7,255,255,0,0,240,3,0,0;8,255,255,0,0,240,3,0,0;9,255,255,0,0,240,3,0,0;10,255,255,0,0,240,3,0,0;11,255,255,0,0,240,3,0,0;12,255,255,0,0,240,3,0,0;13,255,255,0,0,240,3,0,0;14,255,255,0,0,240,3,0,0;15,255,255,0,0,240,3,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="0,1";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.First2CharsofAdminDivision_Random(res, para1);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//LY/T_2022-2012
	@Test
	public void testLYT20222012()
	{
		String LengthRule = "10";
		String RawByteRule = "0,255,3,0,0,0,0,0,0;1,254,3,0,0,0,0,0,0;2,255,3,0,0,0,0,0,0;3,254,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,254,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,254,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//product
	//(?#ALGNAME=TwoByteDecimalnt)(?#PARA=0,1){]
	//(?#ALGNAME=TwoByteDecimalnt)(?#PARA=6,7){]
	@Test
	public void testproduct()
	{
		String LengthRule = "8";
		String RawByteRule = "1,255,3,0,0,0,0,0,0;2,255,3,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="0,1";
		String para2="6,7";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.TwoByteDecimalnt_Random(res, para1);
		res = RuleRandom.TwoByteDecimalnt_Random(res, para2);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GA/T_694-2007
	@Test
	public void testGAT6942007()
	{
		String LengthRule = "18";
		String RawByteRule = "0,0,0,0,0,0,4,0,0;1,0,0,0,0,16,0,0,0;2,255,3,0,0,240,255,255,63;3,255,3,0,0,240,255,255,63;4,255,3,0,0,240,255,255,63;5,255,3,0,0,240,255,255,63;6,255,3,0,0,240,255,255,63;7,255,3,0,0,240,255,255,63;8,255,3,0,0,240,255,255,63;9,255,3,0,0,240,255,255,63;10,255,3,0,0,240,255,255,63;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,240,255,255,63;14,255,3,0,0,240,255,255,63;15,255,3,0,0,240,255,255,63;16,255,3,0,0,240,255,255,63;17,255,3,0,0,240,255,255,63;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GB/T_15775-2011
	@Test
	public void testGBT157752011()
	{
		String LengthRule = "11";
		String RawByteRule = "0,14,0,0,0,0,0,0,0;1,255,3,0,0,0,0,0,0;2,255,3,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,33,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GA_689-2007_5
	//(?#ALGNAME=MonthDate)(?#PARA=10,11,12,13){]
	@Test
	public void testGA68920075()
	{
		String LengthRule = "17";
		String RawByteRule = "0,255,255,219,254,243,239,251,31;1,255,3,0,0,0,0,0,0;2,255,3,0,0,0,0,0,0;3,255,255,219,254,243,239,251,31;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;16,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="10,11,12,13";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.MonthDate_Random(res, para1);
		
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//JT/T_415-2000_64
	@Test
	public void testJTT415200064()
	{
		String LengthRule = "9";
		String RawByteRule = "0,30,0,0,0,0,0,0,0;1,255,3,0,0,0,0,0,0;2,62,0,0,0,0,0,0,0;3,30,0,0,0,0,0,0,0;4,0,0,0,0,240,255,255,63;5,0,0,0,0,240,255,255,63;6,0,0,0,0,240,255,255,63;7,0,0,0,0,240,255,255,63;8,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GB/T_9649.17-2009
	//(?#ALGNAME=DZClassify710)(?#PARA=0,1){]
	@Test
	public void testGBT9649172009()
	{
		String LengthRule = "6";
		String RawByteRule = "2,0,0,0,0,240,255,255,63;3,0,0,0,0,240,255,255,63;4,0,0,0,0,240,255,255,63;5,0,0,0,0,240,255,255,63;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
        String para1="0,1";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		res=RuleRandom.DZClassify710_Random(res, para1);

		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//LS/T_1711-2004
	//(?#ALGNAME=FoodAccount)(?#PARA=0,1,2,3,4,5){]
	@Test
	public void testLST17112004()
	{
		String LengthRule = "6";
		FunctionResult res = new FunctionResult();
		String para1="0,1,2,3,4,5";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
       
		res = RuleRandom.FoodAccount_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	// GB/T_22124.2-2010
	// (?#ALGNAME=GeneralManufacturingProcess)(?#PARA=0,1,2,3,4,5,6,7,8){]
	@Test
	public void testGBT2212422010()
	{
		String LengthRule = "9";
		FunctionResult res = new FunctionResult();
		String para1="0,1,2,3,4,5,6,7,8";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
       
		res = RuleRandom.GeneralManufacturingProcess_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//JB/T_5992.9-92
	//(?#ALGNAME=Machinery9)(?#PARA=0,1,2,3,4){]
	@Test
	public void testJBT5992992()
	{
		String LengthRule = "5";
		FunctionResult res = new FunctionResult();
		String para1="0,1,2,3,4";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
       
		res = RuleRandom.Machinery9_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	 //YC/T_391-2011_5
    //(?#ALGNAME=tabaccoC)(?#PARA=5,6,7){]
    //(?#ALGNAME=TwoByteDecimalnt)(?#PARA=8,9){]
    //(?#ALGNAME=TwoByteDecimalnt)(?#PARA=10,11){]
	@Test
	public void testYCT39120115()
	{
		String LengthRule = "12";
		String RawByteRule = "0,32,0,0,0,0,0,0,0;1,4,0,0,0,0,0,0,0;2,0,0,0,0,240,239,251,63;3,0,0,0,0,240,239,251,63;4,0,0,0,0,240,239,251,63;";
		FunctionResult res = new FunctionResult();
		String para1="5,6,7";
		String para2="8,9";
		String para3="10,11";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.tabaccoC_Random(res, para1);
		res = RuleRandom.TwoByteDecimalnt_Random(res, para2);
		res=RuleRandom.TwoByteDecimalnt_Random(res, para3);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//YC/T_391-2011_6
	//(?#ALGNAME=TwoByteDecimalnt)(?#PARA=6,7){]
    //(?#ALGNAME=TobaccoTech)(?#PARA=8,9){]
    //(?#ALGNAME=TwoByteDecimalnt)(?#PARA=10,11){]
	@Test
	public void testYCT39120116()
	{
		String LengthRule = "12";
		String RawByteRule = "0,32,0,0,0,0,0,0,0;1,4,0,0,0,0,0,0,0;2,0,0,0,0,240,239,251,63;3,0,0,0,0,240,239,251,63;4,0,0,0,0,240,239,251,63;5,0,1,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="6,7";
		String para2="8,9";
		String para3="10,11";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.TwoByteDecimalnt_Random(res, para1);
		res=RuleRandom.TobaccoTech_Random(res, para2);
		res=RuleRandom.TwoByteDecimalnt_Random(res, para3);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//JT/T_132-2003_77
	//(?#ALGNAME=HighwayDatabase6)(?#PARA=0,1){]
	//(?#ALGNAME=TwoByteDecimalnt)(?#PARA=3,4){]
	//(?#ALGNAME=AdminDivision)(?#PARA=5,6,7,8,9,10){]
	@Test
	public void testJTT132200377()
	{
		String LengthRule = "11";
		String RawByteRule = "2,254,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="0,1";
		String para2="3,4";
		String para3="5,6,7,8,9,10";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.HighwayDatabase6_Random(res, para1);
		res=RuleRandom.TwoByteDecimalnt_Random(res, para2);
		res=RuleRandom.AdminDivision_Random(res, para3);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//数据库错误，无法测试
	//HY/T_094-2006
	//(?#ALGNAME=CoastalAdminAreaId)(?#PARA=0,1,2,3,4,5){]
	@Test
	public void testHYT0942006()
	{
		String LengthRule = "6";
		FunctionResult res = new FunctionResult();
		String para1="0,1,2,3,4,5";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
       
		res = RuleRandom.CoastalAdminAreaId_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//已解决
	//待解决异常0f 0820被打回
	//JT/T_444-2001_1
	//(?#ALGNAME=OneTO11)(?#PARA=1,2){]
	//(?#ALGNAME=HighwayTransportation)(?#PARA=3,4,5){]
	//(?#ALGNAME=HighWayTransportBandC)(?#PARA=6,-1){]
	@Test
	public void testJTT44420011()
	{
		String LengthRule = "7,-1";
		String RawByteRule = "0,8,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="1,2";
		String para2="3,4,5";
		String para3="6,-1";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.OneTO11and90_Random(res, para1);
		res=RuleRandom.HighwayTransportation_Random(res, para2);
		res=RuleRandom.HighWayTransportBandC_Random(res, para3);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//命名错误
	//DL/T_700.1-1999_39
	//(?#ALGNAME=TwoByteDecimalnt)(?#PARA=1,2){]
	@Test
	public void testDLT7001199939()
	{
		String LengthRule = "12";
		String RawByteRule = "0,14,0,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="1,2";
	
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.TwoByteDecimalnt_Random(res, para1);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GB/T_17152-2008
	@Test
	public void testGBT171522008()
	{
		String LengthRule = "6";
		String RawByteRule = "0,126,0,0,0,0,0,0,0;1,3,0,0,0,0,0,0,0;2,255,3,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GB/T_10630-1997
	@Test
	public void testGBT106301997()
	{
		String LengthRule = "8";
		String RawByteRule = "0,0,0,0,0,0,2,0,0;1,0,0,0,0,208,238,64,36;2,0,0,0,0,240,255,255,63;3,0,0,0,0,240,255,255,63;4,0,0,0,0,240,255,255,63;5,0,0,0,0,240,255,255,63;6,255,3,0,0,0,0,0,0;7,254,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GA_398.11-2002
	//(?#ALGNAME=First2CharsofAdminDivision)(?#PARA=0,1){]
	//(?#ALGNAME=FourByteDecimalnt)(?#PARA=4,5,6,7){]
	@Test
	public void testGA398112002()
	{
		String LengthRule = "8";
		String RawByteRule = "2,255,3,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="0,1";
		String para2="4,5,6,7";
	
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.First2CharsofAdminDivision_Random(res, para1);
		res=RuleRandom.FourByteDecimalnt_Random(res, para2);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GA_398.12-2002
	//(?#ALGNAME=AdminDivision)(?#PARA=1,2,3,4,5,6){]
	//(?#ALGNAME=Month)(?#PARA=14,15){]
	//(?#ALGNAME=FourByteDecimalnt)(?#PARA=16,17,18,19){]
	@Test
	public void testGA398122002()
	{
		String LengthRule = "20";
		String RawByteRule = "0,0,0,0,0,0,0,32,0;7,255,255,255,255,255,255,255,63;8,255,255,255,255,255,255,255,63;9,255,255,255,255,255,255,255,63;10,254,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="1,2,3,4,5,6";
		String para2="14,15";
		String para3="16,17,18,19";
	
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.AdminDivision_Random(res, para1);
		res=RuleRandom.Month_Random(res, para2);
		res=RuleRandom.FourByteDecimalnt_Random(res, para3);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GA/T_373-2001_3
	//(?#ALGNAME=AdminDivision)(?#PARA=0,1,2,3,4,5){]
	//(?#ALGNAME=FourByteDecimalnt)(?#PARA=6,7,8,9){]
	@Test
	public void testGAT37320013()
	{
		String LengthRule = "17";
		String RawByteRule = "10,0,0,0,0,96,8,9,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;16,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="0,1,2,3,4,5";
		String para2="6,7,8,9";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.AdminDivision_Random(res, para1);
		res=RuleRandom.FourByteDecimalnt_Random(res, para2);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GA/T_373-2001_2
	//(?#ALGNAME=AdminDivision)(?#PARA=0,1,2,3,4,5){]
	//(?#ALGNAME=FourByteDecimalnt)(?#PARA=6,7,8,9){]
	//(?#ALGNAME=FourByteDecimalnt)(?#PARA=11,12,13,14){]
	@Test
	public void testGAT37320012()
	{
		String LengthRule = "15";
		String RawByteRule = "10,0,0,0,0,0,0,2,0;";
		FunctionResult res = new FunctionResult();
		String para1="0,1,2,3,4,5";
		String para2="6,7,8,9";
		String para3="11,12,13,14";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.AdminDivision_Random(res, para1);
		res=RuleRandom.FourByteDecimalnt_Random(res, para2);
		res=RuleRandom.FourByteDecimalnt_Random(res, para3);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GA/T_373-2001_1
	//(?#ALGNAME=AdminDivision)(?#PARA=0,1,2,3,4,5){]
	//(?#ALGNAME=FourByteDecimalnt)(?#PARA=6,7,8,9){]
	@Test
	public void testGAT37320011()
	{
		String LengthRule = "10";
		
		FunctionResult res = new FunctionResult();
		String para1="0,1,2,3,4,5";
		String para2="6,7,8,9";
	
		res = RuleRandom.IoTIDLength_Random(LengthRule);
       
		res = RuleRandom.AdminDivision_Random(res, para1);
		res=RuleRandom.FourByteDecimalnt_Random(res, para2);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//YC/T_391-2011_3
	//(?#ALGNAME=FourByteDecimalnt)(?#PARA=8,9,10,11){]
	@Test
	public void testYCT39120113()
	{
		String LengthRule = "12";
		String RawByteRule = "0,16,0,0,0,0,0,0,0;1,32,0,0,0,0,0,0,0;2,0,0,0,0,240,239,251,63;3,0,0,0,0,240,239,251,63;4,0,0,0,0,240,239,251,63;5,1,0,0,0,0,0,0,0;6,1,0,0,0,0,0,0,0;7,1,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="8,9,10,11";
	
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res=RuleRandom.FourByteDecimalnt_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//YC/T_391-2011_4
	//(?#ALGNAME=TwoByteDecimalnt)(?#PARA=6,7){]
	//(?#ALGNAME=FourByteDecimalnt)(?#PARA=8,9,10,11){]
	@Test
	public void testYCT39120114()
	{
		String LengthRule = "12";
		String RawByteRule = "0,16,0,0,0,0,0,0,0;1,64,0,0,0,0,0,0,0;2,0,0,0,0,240,239,251,63;3,0,0,0,0,240,239,251,63;4,0,0,0,0,240,239,251,63;5,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="6,7";
		String para2="8,9,10,11";
	
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
        res=RuleRandom.TwoByteDecimalnt_Random(res, para1);
		res=RuleRandom.FourByteDecimalnt_Random(res, para2);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//已修改数据库
	//字节规则少一位，无法测试;已提出解决方案
	//JT/T_132-2003_76
	//(?#ALGNAME=AdminDivision)(?#PARA=11,12,13,14,15,16){]
	@Test
	public void testJTT132200376()
	{
		String LengthRule = "17";
		String RawByteRule = "0,0,0,0,0,0,4,0,0;1,0,0,0,0,0,0,64,56;2,255,3,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,0,0,0,0,0,2,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="11,12,13,14,15,16";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
        res=RuleRandom.AdminDivision_Random(res, para1);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GA_658.3-2006
	@Test
	public void testGA65832006()
	{
		String LengthRule = "9";
		String RawByteRule = "0,3,0,0,0,0,0,0,0;1,3,0,0,0,0,0,0,0;2,3,0,0,0,0,0,0,0;3,3,0,0,0,0,0,0,0;4,1,0,0,0,0,0,0,0;5,1,0,0,0,0,0,0,0;6,1,0,0,0,0,0,0,0;7,1,0,0,0,0,0,0,0;8,3,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
      
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//不能被正确识别，原因不明
	//WrongName
	//DL/T_700.1-1999_15
	//DL/T_700.2-1999_12
	//9,11,12,13,14
	//(?#ALGNAME=OneTO15)(?#PARA=0,1){]
	//(?#ALGNAME=TwoByteDecimalnt)(?#PARA=2,3){]
	//(?#ALGNAME=TwoByteDecimalnt)(?#PARA=4,5){]
	//(?#ALGNAME=ParamCode43)(?#PARA=6,-1){]
	@Test
	public void testDLT7001199915()
	{
		String LengthRule = "9,11,12,13,14";
		
		FunctionResult res = new FunctionResult();
		String para1="0,1";
		String para2="2,3";
		String para3="4,5";
		String para4="6,-1";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
       
        res=RuleRandom.OneTO15_Random(res, para1);
        res=RuleRandom.TwoByteDecimalnt_Random(res, para2);
        res=RuleRandom.TwoByteDecimalnt_Random(res, para3);
        res=RuleRandom.ParamCode43_Random(res, para4);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//construction
	//(?#ALGNAME=projectbuild)(?#PARA=0,1){]
	@Test
	public void testconstruction()
	{
		String LengthRule = "6";
		String RawByteRule = "2,255,3,0,0,0,0,0,0;3,15,0,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="0,1";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
        res=RuleRandom.projectbuild_Random(res, para1);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GA_776-2008
	@Test
	public void testGA7762008()
	{
		String LengthRule = "5";
		String RawByteRule = "0,0,0,0,0,240,127,0,0;1,255,3,0,0,0,0,0,0;2,255,3,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
      
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//JT/T_132-2003_63
	//(?#ALGNAME=AdminDivision)(?#PARA=10,11,12,13,14,15){]
	@Test
	public void testJTT132200363()
	{
		String LengthRule = "16";
		String RawByteRule = "0,0,0,0,0,0,4,0,0;1,0,0,0,0,0,0,64,56;2,255,3,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,0,0,0,0,0,128,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="10,11,12,13,14,15";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
        res=RuleRandom.AdminDivision_Random(res, para1);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GB/T_9649.28-2009
	@Test
	public void testGBT9649282009()
	{
		String LengthRule = "6";
		String RawByteRule = "0,0,0,0,0,240,255,255,63;1,0,0,0,0,240,255,255,63;2,0,0,0,0,240,255,255,63;3,0,0,0,0,240,255,255,63;4,0,0,0,0,240,255,255,63;5,0,0,0,0,240,255,255,63;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
      
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//HY/T_075-2005
	//8
	//(?#ALGNAME=OceanInfoMid)(?#PARA=2,3){]
	//(?#ALGNAME=OceanInfoMid)(?#PARA=4,5){]
	//(?#ALGNAME=TwoByteDecimalnt)(?#PARA=6,7){]
	@Test
	public void testHYT0752005()
	{
		String LengthRule = "8";
		String RawByteRule = "0,126,0,0,0,0,0,0,0;1,254,1,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
        String para1="2,3";
        String para2="4,5";
        String para3="6,7";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		res=RuleRandom.OceanInfoMid_Random(res, para1);
		res=RuleRandom.OceanInfoMid_Random(res, para2);
		res=RuleRandom.TwoByteDecimalnt_Random(res, para3);
      
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GB/T_16558.2-2009
	//(?#ALGNAME=MaintenanceSystemPTwo)(?#PARA=0,1,2,3,4,5,6,7,8,9,10,11,12,13){]
	@Test
	public void testGBT1655822009()
	{
		String LengthRule = "14";
		FunctionResult res = new FunctionResult();
		String para1="0,1,2,3,4,5,6,7,8,9,10,11,12,13";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        res=RuleRandom.MaintenanceSystemPTwo_Random(res, para1);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//SB/T_10570-2010
	//(?#ALGNAME=Plus)(?#PARA=24){]
	@Test
	public void testSBT105702010()
	{
		String LengthRule = "26";
		String RawByteRule = "0,0,0,0,0,48,0,0,0;1,255,3,0,0,0,0,0,0;2,255,3,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;16,255,3,0,0,0,0,0,0;17,255,3,0,0,0,0,0,0;18,255,3,0,0,0,0,0,0;19,255,3,0,0,0,0,0,0;20,255,3,0,0,0,0,0,0;21,0,0,0,0,0,128,32,0;22,255,3,0,0,0,0,0,0;23,0,0,0,0,96,0,128,0;25,255,3,0,0,240,255,255,63;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
        String para1="24";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		res=RuleRandom.Plus_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//JT/T_132-2003_16
	//(?#ALGNAME=HighwayDatabase71)(?#PARA=0,1,2,3){]
	@Test
	public void testJTT132200316()
	{
		String LengthRule = "6";
		String RawByteRule = "4,78,0,0,0,0,0,0,0;5,62,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
        String para1="0,1,2,3";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		res=RuleRandom.HighwayDatabase71_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);

	}

	//JT/T_132-2003_17
	//(?#ALGNAME=HighwayDatabase70)(?#PARA=1,2){]
	@Test
	public void testJTT132200317()
	{
		String LengthRule = "10";
		String RawByteRule = "0,0,0,0,0,240,255,3,0;3,0,0,0,0,240,31,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
        String para1="1,2";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		res=RuleRandom.HighwayDatabase70_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);

	}
	
	//GA_690.3-2007
	//(?#ALGNAME=AdminDivision)(?#PARA=0,1,2,3,4,5){]
	//(?#ALGNAME=ExplosiveCivilian)(?#PARA=6,7){]
	@Test
	public void testGA69032007()
	{
		String LengthRule = "13";
		String RawByteRule = "8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
        String para1="0,1,2,3,4,5";
        String para2="6,7";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		res=RuleRandom.AdminDivision_Random(res, para1);
		res=RuleRandom.ExplosiveCivilian_Random(res, para2);
		RuleRandom.DisplayRandomCode(res.FunctionResult);

	}
	
	//GB/T_7635.1-2002
	//(?#ALGNAME=ProductThreeByte)(?#PARA=5,6,7){]
	@Test
	public void testGBT763512002()
	{
		String LengthRule = "8";
		String RawByteRule = "0,31,0,0,0,0,0,0,0;1,254,3,0,0,0,0,0,0;2,255,3,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;4,254,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
        String para1="5,6,7";
        
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		res=RuleRandom.ProductThreeByte_Random(res, para1);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);

	}
	
	//DL/T_397-2010
	//(?#ALGNAME=ElectricPowerGeography)(?#PARA=0,1,2,3,4,5,6){]
	@Test
	public void testDLT3972010()
	{
		String LengthRule = "7";

		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
      
        String para1="0,1,2,3,4,5,6";
        res=RuleRandom.ElectricPowerGeography_Random(res, para1);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//LS/T_1705-2004
	//(?#ALGNAME=GrainEstablishment)(?#PARA=0,1,2,3,4,5,6){]
	@Test
	public void testLST17052004()
	{
		String LengthRule = "7";

		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
      
        String para1="0,1,2,3,4,5,6";
        res=RuleRandom.GrainEstablishment_Random(res, para1);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	
	}
	//已解决
	//字节规则少一位，无法测试
	//GB/T_17969.8-2010_2
	@Test
	public void testGBT17969820102()
	{
		String LengthRule = "32";
		String RawByteRule = "0,255,255,0,0,240,3,0,0;1,255,255,0,0,240,3,0,0;2,255,255,0,0,240,3,0,0;3,255,255,0,0,240,3,0,0;4,255,255,0,0,240,3,0,0;5,255,255,0,0,240,3,0,0;6,255,255,0,0,240,3,0,0;7,255,255,0,0,240,3,0,0;8,255,255,0,0,240,3,0,0;9,255,255,0,0,240,3,0,0;10,255,255,0,0,240,3,0,0;11,255,255,0,0,240,3,0,0;12,255,255,0,0,240,3,0,0;13,255,255,0,0,240,3,0,0;14,255,255,0,0,240,3,0,0;15,255,255,0,0,240,3,0,0;16,255,255,0,0,240,3,0,0;17,255,255,0,0,240,3,0,0;18,255,255,0,0,240,3,0,0;19,255,255,0,0,240,3,0,0;20,255,255,0,0,240,3,0,0;21,255,255,0,0,240,3,0,0;22,255,255,0,0,240,3,0,0;23,255,255,0,0,240,3,0,0;24,255,255,0,0,240,3,0,0;25,255,255,0,0,240,3,0,0;26,255,255,0,0,240,3,0,0;27,255,255,0,0,240,3,0,0;28,255,255,0,0,240,3,0,0;29,255,255,0,0,240,3,0,0;30,255,255,0,0,240,3,0,0;31,255,255,0,0,240,3,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);

	}
	
	//字节规则少一位，无法测试
	//GB/T_16828-2007_1
	//(?#ALGNAME=CheckCodebarcode)(?#PARA=0,1,2,3,4,5,6,7,8,9,10,11;12){]
	@Test
	public void testGBT1682820071()
	{  
		String LengthRule = "13";
		String RawByteRule = "0,64,0,0,0,0,0,0,0;1,0,2,0,0,0,0,0,0;2,63,0,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="0,1,2,3,4,5,6,7,8,9,10,11;12";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		res=RuleRandom.CheckCodebarcode_Random(res, para1);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	    }

	//GB/T_23733-2009
	//(?#ALGNAME=TwoByteDecimalnt)(?#PARA=12,13){]
	//(?#ALGNAME=MusicCheck)(?#PARA=4,5,6,7,8,9,10,11,12,13;14){]
    @Test
    public void testGBT237332009()
    {   
    	
    	String LengthRule = "15";
		String RawByteRule = "0,0,0,0,0,0,16,0,0;1,0,0,0,0,0,0,64,0;2,0,0,0,0,0,0,0,4;3,0,0,0,0,64,0,0,0;4,0,0,0,0,0,0,128,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="12,13";
		String para2="4,5,6,7,8,9,10,11,12,13;14";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		res=RuleRandom.TwoByteDecimalnt_Random(res, para1);
		res=RuleRandom.MusicCheck_Random(res, para2);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
    //JT/T_307.3-1998
	//(?#ALGNAME=HighwayMaintenance3)(?#PARA=0,1,2,3,4,5,6,7,8,9){]
    @Test
    public void testJTT30731998()
    {
    	String LengthRule = "10";
		FunctionResult res = new FunctionResult();
		String para1="0,1,2,3,4,5,6,7,8,9";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        
		res=RuleRandom.HighwayMaintenance3_Random(res, para1);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
    //JB/T_5992.2-92
    //(?#ALGNAME=Machinery2)(?#PARA=0,1,2,3,4){]
    @Test
    public void testJBT5992292()
    {
    	String LengthRule = "5";
		FunctionResult res = new FunctionResult();
		String para1="0,1,2,3,4";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        
		res=RuleRandom.Machinery2_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
  //LS/T_1712-2004
  //(?#ALGNAME=FoodTrade)(?#PARA=0,1,2,3,4,5){]
    @Test
    public void testFoodTrade()
    {
    	String LengthRule = "6";
		FunctionResult res = new FunctionResult();
		String para1="0,1,2,3,4,5";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        
		res=RuleRandom.FoodTrade_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
    //GB/T_9649.21-2009
    //(?#ALGNAME=DZClassify)(?#PARA=0,1){]
    @Test
    public void testGBT9649212009()
    {
    	String LengthRule = "6";
		String RawByteRule = "2,0,0,0,0,240,255,255,63;3,0,0,0,0,240,255,255,63;4,0,0,0,0,240,255,255,63;5,0,0,0,0,240,255,255,63;";
		FunctionResult res = new FunctionResult();
		String para1="0,1";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		res=RuleRandom.DZClassify_Random(res, para1);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
     //LS/T_1707.3-2004
	//(?#ALGNAME=GainsEquipment)(?#PARA=0,1,2,3){]
    @Test
    public void testLST170732004()
    {
    	String LengthRule = "4";
		FunctionResult res = new FunctionResult();
		String para1="0,1,2,3";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        
		res=RuleRandom.GainsEquipment_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
    //JT/T_132-2003_52
    @Test
    public void testJTT132200352()
    {
    	String LengthRule = "2";
		String RawByteRule = "0,30,2,0,0,0,0,0,0;1,30,2,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
    //GB/T_917-2009
	//(?#ALGNAME=NationalTrunkHighway)(?#PARA=2,-1){]
    @Test
    public void testGBT9172009()
    {
    	int kk=0;
    	while(kk<100){
    	String LengthRule = "2-5";
		String RawByteRule = "0,0,0,0,0,0,4,64,56;1,254,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="2,-1";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		res=RuleRandom.NationalTrunkHighway_Random(res, para1);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);kk++;}
    }
    
    //WS_364.5-2011_8
    @Test
    public void testWS364520118()
    {
    	String LengthRule = "1";
		String RawByteRule = "0,62,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
    //WS_364.5-2011_11
    @Test
    public void testWS3645201111()
    {
    	String LengthRule = "1";
		String RawByteRule = "0,126,2,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
    //JT/T_132-2003_49
    @Test
    public void testJTT132200349()
    {
    	String LengthRule = "1";
		String RawByteRule = "0,255,2,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
    //JT/T_132-2003_48
    @Test
    public void testJTT132200348()
    {
    	String LengthRule = "1";
		String RawByteRule = "0,62,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
    //JT/T_132-2003_44
    @Test
    public void testJTT132200344()
    {
    	String LengthRule = "1";
		String RawByteRule = "0,254,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
    //JT/T_132-2003_43
    @Test
    public void testJTT132200343()
    {
    	String LengthRule = "1";
		String RawByteRule = "0,254,2,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
    //JT/T_132-2003_42
    @Test
    public void testJTT132200342()
    {
    	String LengthRule = "1";
		String RawByteRule = "0,254,2,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
    //JT/T_132-2003_41
    @Test
    public void testJTT132200341()
    {
    	String LengthRule = "1";
		String RawByteRule = "0,7,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
    //WS_364.10-2011_11
    @Test
    public void testWS36410201111()
    {

    	String LengthRule = "1";
		String RawByteRule = "0,14,2,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
    //WS_364.10-2011_10
    @Test
    public void testWS36410201110()
    {
    	String LengthRule = "1";
		String RawByteRule = "0,62,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
    //GA/T_974.53-2011
	//(?#ALGNAME=FireInfocamp)(?#PARA=0,1){]
    @Test
    public void testGAT974532011()
    {
    	String LengthRule = "2";
		FunctionResult res = new FunctionResult();
		String para1="0,1";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        res=RuleRandom.FireInfocamp_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
    //JT/T_415-2000_52
	//(?#ALGNAME=RoadTransportation21)(?#PARA=0,1){]
    @Test
    public void testJTT415200052()
    {
        String LengthRule = "2";
		FunctionResult res = new FunctionResult();
		String para1="0,1";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        res=RuleRandom.RoadTransportation21_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
    //WS_364.10-2011_14
    @Test
    public void testWS36410201114()
    {

    	String LengthRule = "1";
		String RawByteRule = "0,62,2,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
    //WS_364.10-2011_17
	//(?#ALGNAME=InfectiousDiseases)(?#PARA=0,1,2,3){]
    @Test
    public void testWS36410201117()
    {
    	String LengthRule = "4";
		FunctionResult res = new FunctionResult();
		String para1="0,1,2,3";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        res=RuleRandom.InfectiousDiseases_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
    //WS_364.10-2011_19
    @Test
    public void testWS36410201119()
    {
    	String LengthRule = "1";
		String RawByteRule = "0,62,2,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
    //WS_364.10-2011_18
    @Test
    public void testWS36410201118()
    {
    	String LengthRule = "1";
		String RawByteRule = "0,62,2,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
    //GB/T_919-2002_2
    @Test
    public void testGBT91920022()
    {
    	String LengthRule = "1";
		String RawByteRule = "0,30,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
    //GB/T_919-2002_1
    @Test
    public void testGBT91920021()
    {
    	String LengthRule = "1";
		String RawByteRule = "0,0,0,0,0,0,4,80,56;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
     //GB/T_9649.16-2009
	//(?#ALGNAME=MineralRegex)(?#PARA=2,-1){]
    @Test
    public void testGBT9649162009()
    {

    	String LengthRule = "2,-1";
		String RawByteRule = "0,0,0,0,0,0,64,0,0;1,0,0,0,0,64,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
        String para1="2,-1";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		RuleRandom.MineralRegex_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    

	// GA/T_974.69-2011
	// (?#ALGNAME=FireInfoori)(?#PARA=0,1,2){]
    @Test
    public void testGAT974692011()
    {
    	String LengthRule = "3";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String para1="0,1,2";
       
		RuleRandom.FireInfoori_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
    //JT/T_415-2000_28
    @Test
    public void testJTT415200028()
    {
    	String LengthRule = "1";
		String RawByteRule = "0,14,2,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    

	//JT/T_415-2000_29
	//(?#ALGNAME=RoadTransportation50)(?#PARA=0,1){]
    @Test
    public void testJTT415200029()
    {
        String LengthRule = "2";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String para1="0,1";
        RuleRandom.RoadTransportation50_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
    //JT/T_415-2000_24
    @Test
    public void testJTT415200024()
    {
    	String LengthRule = "1";
		String RawByteRule = "0,62,2,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
    //JT/T_415-2000_25
    @Test
    public void testJTT415200025()
    {

    	String LengthRule = "1";
		String RawByteRule = "0,6,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
    //JT/T_415-2000_26
	//(?#ALGNAME=RoadTransportation53)(?#PARA=0,1,2){]
    @Test
    public void testJTT415200026()
    {
    	String LengthRule = "3";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String para1="0,1,2";
        RuleRandom.RoadTransportation53_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
   
    }
    
    // JT/T_415-2000_27
	// (?#ALGNAME=TwobyteCode06and90)(?#PARA=0,1){]
    @Test
    public void testJTT415200027()
    {
    	String LengthRule = "2";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String para1="0,1";
        RuleRandom.TwobyteCode06and90_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
    //JT/T_415-2000_20
    @Test
    public void testJTT415200020()
    {
    	String LengthRule = "1";
		String RawByteRule = "0,126,2,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
    //JT/T_415-2000_21
    @Test
    public void testJTT415200021()
    {
    	String LengthRule = "1";
		String RawByteRule = "0,126,2,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
    //JT/T_415-2000_22
    @Test
    public void testJTT415200022()
    {
    	String LengthRule = "1";
		String RawByteRule = "0,62,2,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
     //JT/T_415-2000_23
	//(?#ALGNAME=TwobytleCode08and90)(?#PARA=0,1){]
    @Test
    public void testJTT415200023()
    {
    	String LengthRule = "1";
		FunctionResult res = new FunctionResult();
		String para1="0,1";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        RuleRandom.TwobytleCode08and90_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
    //JT/T_415-2000_62
    @Test
    public void testJTT415200062()
    {
    	String LengthRule = "3";
		String RawByteRule = "0,6,0,0,0,0,0,0,0;1,254,0,0,0,0,0,0,0;2,62,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
    //JT/T_415-2000_63
    @Test
    public void testJTT415200063()
    {
    	String LengthRule = "3";
		String RawByteRule = "0,6,0,0,0,0,0,0,0;1,14,0,0,0,0,0,0,0;2,6,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
    //GB/T_27610-2011
	//(?#ALGNAME=Wasteproducts)(?#PARA=0,1,2,3){]
    @Test
    public void testGBT276102011()
    {
    	String LengthRule = "4";
		FunctionResult res = new FunctionResult();
		String para1="0,1,2,3";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        res=RuleRandom.Wastewater_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
    //JT/T_132-2003_65
    //(?#ALGNAME=HighwayDatabase18)(?#PARA=0,1){]
    @Test
    public void testJTT132200365()
    {
    	String LengthRule = "2";
		FunctionResult res = new FunctionResult();
		String para1="0,1";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        res=RuleRandom.HighwayDatabase18_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
     // JT/T_132-2003_66
	// (?#ALGNAME=HighwayDatabase17)(?#PARA=0,1){]
    @Test
    public void testJTT132200366()
    {
    	String LengthRule = "2";
		FunctionResult res = new FunctionResult();
		String para1="0,1";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        res=RuleRandom.HighwayDatabase17_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
    //YC/T_210.3-2006
	//(?#ALGNAME=CodeTobacco)(?#PARA=0,1,2){]
    @Test
    public void testYCT21032006()
    {
    	String LengthRule = "3";
		FunctionResult res = new FunctionResult();
		String para1="0,1,2";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        res=RuleRandom.CodeTobacco_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
    }
    
	// WS_364.4-2011_3
	// (?#ALGNAME=ModeofProduction)(?#PARA=0,-1){]
	@Test
	public void testWS364420113() {
		
		String LengthRule = "1-2";
		FunctionResult res = new FunctionResult();
		String para1 = "0,-1";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
		res = RuleRandom.ModeofProduction_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//WS_364.4-2011_2
	//(?#ALGNAME=TerminationofPregnancy)(?#PARA=0,-1){]
	@Test
	public void testWS364420112()
	{
		String LengthRule = "1-2";
		FunctionResult res = new FunctionResult();
		String para1 = "0,-1";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
		res = RuleRandom.TerminationofPregnancy_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//JT/T_132-2003_69
	@Test
	public void testJTT132200369()
	{
		String LengthRule = "1";
		String RawByteRule = "0,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//JT/T_415-2000_18
	@Test
	public void testJTT415200018()
	{
		String LengthRule = "1";
		String RawByteRule = "0,254,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//JT/T_415-2000_57
	@Test
	public void testJTT415200057()
	{
		String LengthRule = "1";
		String RawByteRule = "0,6,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	//YC/T_210.2-2006
	//(?#ALGNAME=TobaccoLeafForm)(?#PARA=0,1,2){]
	@Test
	public void testYCT21022006()
	{
		String LengthRule = "3";
		FunctionResult res = new FunctionResult();
		String para1="0,1,2";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        res=RuleRandom.TobaccoLeafForm_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//JT/T_132-2003_36
	@Test
	public void testJTT132200336()
	{
		String LengthRule = "1";
		String RawByteRule = "0,126,2,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//JT/T_132-2003_37
	@Test
	public void testJTT132200337()
	{
		String LengthRule = "1";
		String RawByteRule = "0,14,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//JT/T_132-2003_35
	//(?#ALGNAME=HighwayDatabase46)(?#PARA=0,1){]
	@Test
	public void testJTT132200335()
	{
		String LengthRule = "2";
		FunctionResult res = new FunctionResult();
		String para1="0,1";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        res=RuleRandom.HighwayDatabase46_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//JT/T_132-2003_33
	@Test
	public void testJTT132200333()
	{
		String LengthRule = "3";
		String RawByteRule = "0,126,2,0,0,0,0,0,0;1,62,2,0,0,0,0,0,0;2,14,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//JT/T_132-2003_30
	@Test
	public void testJTT132200330()
	{
		String LengthRule = "1";
		String RawByteRule = "0,254,2,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//JT/T_132-2003_31
	@Test
	public void testJTT132200331()
	{
		String LengthRule = "1";
		String RawByteRule = "0,14,2,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//JT/T_132-2003_39
	@Test
	public void testJTT132200339()
	{
		String LengthRule = "1";
		String RawByteRule = "0,63,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	
	//WS_364.17-2011
	//(?#ALGNAME=HealthSupervisionObject)(?#PARA=0,-1){]
	@Test
	public void testHealthSupervisionObject()
	{
		String LengthRule = "2-8";
		FunctionResult res = new FunctionResult();
		String para1="0,-1";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        res=RuleRandom.HealthSupervisionObject_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GA/T_556.4-2007
	//(?#ALGNAME=FianceManage)(?#PARA=0,1){]
	@Test
	public void testGAT55642007()
	{
		String LengthRule = "2";
		FunctionResult res = new FunctionResult();
		String para1="0,1";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        res=RuleRandom.FianceManage_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	

	//GA/T_974.8-2011
	//(?#ALGNAME=FireInfoStstion)(?#PARA=0,1){]
	@Test
	public void testGAT97482011()
	{

		String LengthRule = "2";
		FunctionResult res = new FunctionResult();
		String para1="0,1";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        res=RuleRandom.FireInfoStstion_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	// WS_364.3-2011_6
	// (?#ALGNAME=OneTO17NO99)(?#PARA=0,1){]
	@Test
	public void testWS364320116()
	{
		String LengthRule = "2";
		FunctionResult res = new FunctionResult();
		String para1="0,1";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        res=RuleRandom.OneTO17NO99_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//WS_364.3-2011_2
	//(?#ALGNAME=DileveryPlace)(?#PARA=0,-1){]
	@Test
	public void testWS364320112()
	{
		
		String LengthRule = "1-2";
		FunctionResult res = new FunctionResult();
		String para1="0,-1";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        res=RuleRandom.DileveryPlace_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GB/T_25066-2010
	//(?#ALGNAME=InformationSafe)(?#PARA=0,1,2,3){]
	@Test
	public void testGBT250662010()
	{
		String LengthRule = "4";
		FunctionResult res = new FunctionResult();
		String para1="0,1,2,3";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        res=RuleRandom.InformationSafe_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GB/T_16300-1996
	//(?#ALGNAME=ClassificationOfCivilAviation)(?#PARA=0,1,2,3){]
	@Test
	public void testGBT163001996()
	{
		String LengthRule = "4";
		FunctionResult res = new FunctionResult();
		String para1="0,1,2,3";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        res=RuleRandom.ClassificationOfCivilAviation_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GB/T_4761-2008
	//(?#ALGNAME=FamilyRelationship)(?#PARA=0,-1){]
	@Test
	public void testGBT47612008()
	{
		String LengthRule = "1-4";
		FunctionResult res = new FunctionResult();
		String para1="0,-1";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        res=RuleRandom.FamilyRelationship_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//WS_364.4-2011_5
	//(?#ALGNAME=OneTO14)(?#PARA=0,1){]
	@Test
	public void testWS364420115()
	{
		String LengthRule = "2";
		FunctionResult res = new FunctionResult();
		String para1="0,1";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        res=RuleRandom.OneTO14_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GB/T_17295-2008
	//(?#ALGNAME=MeasureUnit)(?#PARA=0,-1){]
	@Test
	public void testGBT172952008()
	{
		String LengthRule = "1-3";
		FunctionResult res = new FunctionResult();
		String para1="0,-1";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        res=RuleRandom.MeasureUnit_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//WS_364.6-2011_12
	//(?#ALGNAME=ChildrenExcrement)(?#PARA=0,-1){]
	@Test
	public void testWS3646201112()
	{
		String LengthRule = "1-2";
		FunctionResult res = new FunctionResult();
		String para1="0,-1";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        res=RuleRandom.ChildrenExcrement_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//WS_364.11-2011_6
	@Test
	public void testWS3641120116()
	{
		String LengthRule = "1";
		String RawByteRule = "0,14,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	// QC/T_775-2007
	@Test
	public void testQCT7752007() {
		String LengthRule = "3";
		String RawByteRule = "0,0,0,0,0,0,0,1,0;1,2,0,0,0,0,0,0,0;2,0,12,1,0,8,0,0,0;";
		FunctionResult res = new FunctionResult();

		res = RuleRandom.IoTIDLength_Random(LengthRule);
		String ByteRule = "";

		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//WS_364.3-2011_7
	@Test
	public void testWS364320117()
	{
		String LengthRule = "1";
		String RawByteRule = "0,62,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//WS_364.3-2011_4
	@Test
	public void testWS364320114()
	{
		String LengthRule = "1";
		String RawByteRule = "0,126,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//WS_364.3-2011_5
	@Test
	public void testWS364320115()
	{
		String LengthRule = "1";
		String RawByteRule = "0,126,2,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//JT/T_415-2000_4
	@Test
	public void testJTT41520004()
	{
		String LengthRule = "1";
		String RawByteRule = "0,62,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//JT/T_415-2000_6
	@Test
	public void testJTT41520006()
	{
		String LengthRule = "2";
		String RawByteRule = "0,254,3,0,0,0,0,0,0;1,1,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//JT/T_132-2003_11
	@Test
	public void testJTT132200311()
	{
		String LengthRule = "1";
		String RawByteRule = "0,62,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//WS_364.11-2011_1
	//(?#ALGNAME=OneTO07)(?#PARA=0,1){]
	@Test
	public void testWS3641120111()
	{
		String LengthRule = "2";
		FunctionResult res = new FunctionResult();
		String para1="0,1";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        res=RuleRandom.OneTO07_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//WS_364.11-2011_8
	//(?#ALGNAME=OneTO07)(?#PARA=0,1){]
	@Test
	public void testWS3641120118()
	{
		String LengthRule = "2";
		FunctionResult res = new FunctionResult();
		String para1="0,1";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        res=RuleRandom.OneTO07_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//JT/T_415-2000_40
	@Test
	public void testJTT415200040()
	{
		String LengthRule = "1";
		String RawByteRule = "0,6,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//WS_364.4-2011_1
	@Test
	public void testWS364420111()
	{
		String LengthRule = "1";
		String RawByteRule = "0,14,2,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//WS_364.4-2011_4
	@Test
	public void testWS364420114()
	{
		String LengthRule = "1";
		String RawByteRule = "0,30,2,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GB/T_4880.1-2005
	@Test
	public void testGBT488012005()
	{
		String LengthRule = "2";
		String RawByteRule = "0,0,252,255,255,15,0,0,0;1,0,252,255,255,15,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GB/T_26319-2010_2
	@Test
	public void testGBT2631920102()
	{
		String LengthRule = "4";
		String RawByteRule = "0,255,3,0,0,0,0,0,0;1,255,3,0,0,0,0,0,0;2,255,3,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GB/T_23833-2009_5
	//(?#ALGNAME=PrefixofRetailCommodityNumber)(?#PARA=0,1,2){]
	//(?#ALGNAME=GraiSerialNo)(?#PARA=7,23,-1){]
	@Test
	public void testGBT2383320095()
	{
		String LengthRule = "8-30";
		String RawByteRule = "3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="0,1,2";
		String para2="7,23,-1";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		res=RuleRandom.PrefixofRetailCommodityNumber_Random(res, para1);
		res=RuleRandom.GraiSerialNo_Random(res, para2);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GB/T_23833-2009_6
	//9-30
	//(?#ALGNAME=PrefixofRetailCommodityNumber)(?#PARA=0,1,2){]
	//(?#ALGNAME=GraiSerialNo)(?#PARA=8,22,-1){]
	@Test
	public void testGBT2383320096()
	{
		String LengthRule = "9-30";
		String RawByteRule = "3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="0,1,2";
		String para2="8,22,-1";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		res=RuleRandom.PrefixofRetailCommodityNumber_Random(res, para1);
		res=RuleRandom.GraiSerialNo_Random(res, para2);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GB/T_23833-2009_7
	//10-30
	//(?#ALGNAME=PrefixofRetailCommodityNumber)(?#PARA=0,1,2){]
	//(?#ALGNAME=GraiSerialNo)(?#PARA=9,21,-1){]
	@Test
	public void testGBT2383320097()
	{
		String LengthRule = "10-30";
		String RawByteRule = "3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="0,1,2";
		String para2="9,21,-1";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		res=RuleRandom.PrefixofRetailCommodityNumber_Random(res, para1);
		res=RuleRandom.GraiSerialNo_Random(res, para2);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//JT/T_132-2003_28
	@Test
	public void testJTT132200328()
	{
		String LengthRule = "1";
		String RawByteRule = "0,30,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GB/T_23833-2009_8
	//11-30
	//(?#ALGNAME=PrefixofRetailCommodityNumber)(?#PARA=0,1,2){]
	//(?#ALGNAME=GraiSerialNo)(?#PARA=10,20,-1){]
	
	
	//GA/T_974.28-2011
	//(?#ALGNAME=FireForceCode)(?#PARA=0,1,2,3){]
	@Test
	public void testGAT974282011()
	{
		String LengthRule = "4";
		FunctionResult res = new FunctionResult();
		String para1="0,1,2,3";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        res=RuleRandom.FireForceCode_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	// GA/T_974.46-2011
	// (?#ALGNAME=FireTrainCode)(?#PARA=0,1,2,3,4,5){]
	@Test
	public void testGAT974462011()
	{
		String LengthRule = "6";
		FunctionResult res = new FunctionResult();
		String para1="0,1,2,3,4,5";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        res=RuleRandom.FireTrainCode_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	
	/**
	 * 0821
	 * 缺少函数的!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	 * 都没测!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	 * 
	 */
	
	//GA_431-2004_2
	//(?#ALGNAME=OneTO05)(?#PARA=1,2){]
	//(?#ALGNAME=AdminDivision)(?#PARA=3,4,5,6,7,8){]
	//(?#ALGNAME=FourByteDecimalnt)(?#PARA=9,10,11,12){]
	//(?#ALGNAME=MonthDate)(?#PARA=17,18,19,20){]
	//(?#ALGNAME=ThreeByteDecimalnt)(?#PARA=21,22,23){]
	
	@Test
	public void testGA43120042()
	{
		String LengthRule = "24";
		String RawByteRule = "0,0,0,0,0,240,47,0,16;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;16,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="1,2";
		String para2="3,4,5,6,7,8";
		String para3="9,10,11,12";
		String para4="17,18,19,20";
		String para5="21,22,23";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		res=RuleRandom.OneTO05_Random(res, para1);
		res=RuleRandom.AdminDivision_Random(res, para2);
		res=RuleRandom.FourByteDecimalnt_Random(res, para3);
		res=RuleRandom.MonthDate_Random(res, para4);
		res=RuleRandom.ThreeByteDecimalnt_Random(res,para5);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	
	//LS/T_1701-2004_1
	//(?#ALGNAME=GrainEnterprise)(?#PARA=0,1){]
	//(?#ALGNAME=First4CharsofAdminDivisionforCiga)(?#PARA=3,4,5,6){]
	
	@Test
	public void testLST170120041()
	{
		String LengthRule = "7";
		String RawByteRule = "2,1,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="0,1";
		String para2="3,4,5,6";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
        String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		res=RuleRandom.GrainEnterprise_Random(res, para1);
		res=RuleRandom.First4CharsofAdminDivisionforCiga_Random(res, para2);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	
	//GB/T_14043-2005_2
	//(?#ALGNAME=Slash)(?#PARA=2){]
	//(?#ALGNAME=Hyphen)(?#PARA=8){]
	//(?#ALGNAME=ZeroTO14)(?#PARA=9,10){]
	//(?#ALGNAME=Hyphen)(?#PARA=11){]
	//(?#ALGNAME=Hyphen)(?#PARA=14){]
	//(?#ALGNAME=Hyphen)(?#PARA=16){]
	
	
	@Test
	public void testGBT1404320052()
	{
		String LengthRule = "21";
		String RawByteRule = "0,0,0,0,0,0,4,0,0;1,0,0,0,0,32,0,0,0;3,0,0,0,0,0,0,128,0;4,0,1,0,0,0,0,0,0;5,2,0,0,0,0,0,0,0;6,1,0,0,0,0,0,0,0;7,2,0,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,254,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;17,4,0,0,0,0,0,0,0;18,1,0,0,0,0,0,0,0;19,1,0,0,0,0,0,0,0;20,4,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="2";
		String para2="8";
		String para3="9,10";
		String para4="11";
		String para5="14";
		String para6="16";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		res=RuleRandom.Slash_Random(res, para1);
		res=RuleRandom.Hyphen_Random(res, para2);
		res=RuleRandom.ZeroTO14_Random(res, para3);
		res=RuleRandom.Hyphen_Random(res, para4);
		res=RuleRandom.Hyphen_Random(res,para5);
		res=RuleRandom.Hyphen_Random(res,para6);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	
	//GB/T_14043-2005_1
	//(?#ALGNAME=Slash)(?#PARA=2){]
	//(?#ALGNAME=Hyphen)(?#PARA=8){]
	//(?#ALGNAME=ZeroTO14)(?#PARA=9,10){]
	//(?#ALGNAME=Hyphen)(?#PARA=11){]
	//(?#ALGNAME=Hyphen)(?#PARA=14){]
	//(?#ALGNAME=Hyphen)(?#PARA=16){]
	
	@Test
	public void testGBT1404320051()
	{
		String LengthRule = "21";
		String RawByteRule = "0,0,0,0,0,0,4,0,0;1,0,0,0,0,32,0,0,0;3,0,0,0,0,0,0,128,0;4,0,1,0,0,0,0,0,0;5,1,0,0,0,0,0,0,0;6,0,2,0,0,0,0,0,0;7,0,1,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,254,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;17,4,0,0,0,0,0,0,0;18,1,0,0,0,0,0,0,0;19,1,0,0,0,0,0,0,0;20,8,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="2";
		String para2="8";
		String para3="9,10";
		String para4="11";
		String para5="14";
		String para6="16";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		res=RuleRandom.Slash_Random(res, para1);
		res=RuleRandom.Hyphen_Random(res, para2);
		res=RuleRandom.ZeroTO14_Random(res, para3);
		res=RuleRandom.Hyphen_Random(res, para4);
		res=RuleRandom.Hyphen_Random(res,para5);
		res=RuleRandom.Hyphen_Random(res,para6);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	
	
	//GB/T_14043-2005_4
	//(?#ALGNAME=Hyphen)(?#PARA=7){]
	//(?#ALGNAME=ZeroTO14)(?#PARA=8,9){]
	//(?#ALGNAME=Hyphen)(?#PARA=10){]
	//(?#ALGNAME=Hyphen)(?#PARA=13){]
	//(?#ALGNAME=Hyphen)(?#PARA=15){]
	
	
	@Test
	public void testGBT1404320054()
	{
		String LengthRule = "20";
		String RawByteRule = "0,0,0,0,0,0,16,0,0;1,0,0,0,0,0,0,64,0;2,0,0,0,0,0,0,4,0;3,128,0,0,0,0,0,0,0;4,8,0,0,0,0,0,0,0;5,64,0,0,0,0,0,0,0;6,0,1,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,254,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;16,2,0,0,0,0,0,0,0;17,0,2,0,0,0,0,0,0;18,0,1,0,0,0,0,0,0;19,0,2,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		String para1="7";
		String para2="8,9";
		String para3="10";
		String para4="13";
		String para5="15";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
        String ByteRule = "";
       
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		res=RuleRandom.Hyphen_Random(res, para1);
		res=RuleRandom.ZeroTO14_Random(res, para2);
		res=RuleRandom.Hyphen_Random(res, para3);
		res=RuleRandom.Hyphen_Random(res,para4);
		res=RuleRandom.Hyphen_Random(res,para5);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	
	//GA/T_556.6-2007
	//(?#ALGNAME=AdminDivision)(?#PARA=0,1,2,3,4,5){]
	//(?#ALGNAME=FinancialCode)(?#PARA=6,7){]
	//(?#ALGNAME=FourByteDecimalnt)(?#PARA=8,9,10,11){]
	
	
	@Test
	public void testGAT55662007()
	{
		String LengthRule = "12";
		FunctionResult res = new FunctionResult();
		String para1="0,1,2,3,4,5";
		String para2="6,7";
		String para3="8,9,10,11";
	
		res = RuleRandom.IoTIDLength_Random(LengthRule);
		res=RuleRandom.AdminDivision_Random(res, para1);
		res=RuleRandom.FinancialCode_Random(res, para2);
		res=RuleRandom.FourByteDecimalnt_Random(res, para3);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	
	//GA_435-2003_2
	//(?#ALGNAME=OneTO07)(?#PARA=1,2){]
	//(?#ALGNAME=AdminDivision)(?#PARA=3,4,5,6,7,8){]
	//(?#ALGNAME=MonthDate)(?#PARA=17,18,19,20){]
	//(?#ALGNAME=ThreeByteDecimalnt)(?#PARA=21,22,23){]
	@Test
	public void testGA43520032()
	{
		String LengthRule = "24";
		FunctionResult res = new FunctionResult();
		String para1="1,2";
		String para2="3,4,5,6,7,8";
		String para3="17,18,19,20";
		String para4="21,22,23";
		
	    res = RuleRandom.IoTIDLength_Random(LengthRule);
		
	    String RawByteRule = "0,0,0,0,0,240,47,0,16;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;16,255,3,0,0,0,0,0,0;";
        String ByteRule = "";
        String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		
		res=RuleRandom.OneTO07_Random(res, para1);
		res=RuleRandom.AdminDivision_Random(res, para2);
		res=RuleRandom.MonthDate_Random(res, para3);
		res=RuleRandom.ThreeByteDecimalnt_Random(res, para4);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	
	
	//YC/T_213.4-2006
	//(?#ALGNAME=TabaccoStandardPart)(?#PARA=2,3,4,5,6){]
	//(?#ALGNAME=TabaccoMachineProducer)(?#PARA=12,13){]

	@Test
	public void testYCT21342006() {
		String LengthRule = "14";
		FunctionResult res = new FunctionResult();
		String para1 = "2,3,4,5,6";
		String para2 = "12,13";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
		
		String RawByteRule = "0,8,0,0,0,0,0,0,0;1,64,0,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;";
		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.TabaccoStandardPart_Random(res, para1);
		res = RuleRandom.TabaccoMachineProducer_Random(res, para2);
	
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//JT/T_444-2001_10
	//(?#ALGNAME=OneTO11and90)(?#PARA=1,2){]
	//(?#ALGNAME=HighwayTransportation)(?#PARA=3,4,5){]
	//(?#ALGNAME=HighwayTransportation4c6)(?#PARA=8,9){]
	

	@Test
	public void testJTT444200110()
	{
		String LengthRule = "11";
		FunctionResult res = new FunctionResult();
		String para1 = "1,2";
		String para2 = "3,4,5";
		String para3="8,9";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
		
		String RawByteRule = "0,8,0,0,0,0,0,0,0;6,0,0,0,0,0,4,64,56;7,158,3,0,0,0,0,0,0;10,30,0,0,0,0,0,0,0;";
		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.OneTO11and90_Random(res, para1);
		res = RuleRandom.HighwayTransportation_Random(res, para2);
		res = RuleRandom.HighwayTransportation4c6_Random(res, para3);
	
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	
	//JT/T_444-2001_11
	//(?#ALGNAME=OneTO11and90)(?#PARA=1,2){]
	//(?#ALGNAME=HighwayTransportation)(?#PARA=3,4,5){]

	@Test
	public void testJTT444200111()
	{
		
		String LengthRule = "10";
		FunctionResult res = new FunctionResult();
		String para1 = "1,2";
		String para2 = "3,4,5";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
		
		String RawByteRule = "0,8,0,0,0,0,0,0,0;6,0,0,0,0,0,4,64,56;7,30,2,0,0,0,0,0,0;8,62,1,0,0,0,0,0,0;9,30,0,0,0,0,0,0,0;";
		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.OneTO11and90_Random(res, para1);
		res = RuleRandom.HighwayTransportation_Random(res, para2);
	
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	
	//JT/T_444-2001_12
	//(?#ALGNAME=OneTO11and90)(?#PARA=1,2){]
	//(?#ALGNAME=HighwayTransportation)(?#PARA=3,4,5){]
	

	@Test
	public void testJTT444200112()
	{
		String LengthRule = "9";
		FunctionResult res = new FunctionResult();
		String para1 = "1,2";
		String para2 = "3,4,5";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
		
		String RawByteRule = "0,8,0,0,0,0,0,0,0;6,0,0,0,0,0,4,64,56;7,30,2,0,0,0,0,0,0;8,3,0,0,0,0,0,0,0;";
		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.OneTO11and90_Random(res, para1);
		res = RuleRandom.HighwayTransportation_Random(res, para2);
	
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	
	//JT/T_444-2001_13
	//(?#ALGNAME=OneTO11and90)(?#PARA=1,2){]
	//(?#ALGNAME=HighwayTransportation)(?#PARA=3,4,5){]

	@Test
	public void testJTT444200113()
	{
		String LengthRule = "11";
		FunctionResult res = new FunctionResult();
		String para1 = "1,2";
		String para2 = "3,4,5";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
		
		String RawByteRule = "0,8,0,0,0,0,0,0,0;6,0,0,0,0,0,4,64,56;7,126,2,0,0,0,0,0,0;8,30,2,0,0,0,0,0,0;9,30,0,0,0,0,0,0,0;10,3,0,0,0,0,0,0,0;";
		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.OneTO11and90_Random(res, para1);
		res = RuleRandom.HighwayTransportation_Random(res, para2);
	
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//已解决
	//BUG!!!!!!!!ASK!!!!!!!!!!!!!!!!!!!!!!!
	//HJ_608-2011_1
	//(?#ALGNAME=MOD11)(?#PARA=0,1,2,3,4,5,6,7){]

	@Test
	public void testHJ60820111()
	{
		String LengthRule = "12";
		FunctionResult res = new FunctionResult();
		String para1 = "0,1,2,3,4,5,6,7;8";
	
		res = RuleRandom.IoTIDLength_Random(LengthRule);
		
		String RawByteRule = "0,255,3,0,0,240,255,255,63;1,255,3,0,0,240,255,255,63;2,255,3,0,0,240,255,255,63;3,255,3,0,0,240,255,255,63;4,255,3,0,0,240,255,255,63;5,255,3,0,0,240,255,255,63;6,255,3,0,0,240,255,255,63;7,255,3,0,0,240,255,255,63;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;";
		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.MOD11_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GB/T_26819-2011_6
	//(?#ALGNAME=CountryRegionCode)(?#PARA=0,1,2){]
	//(?#ALGNAME=AdminDivision)(?#PARA=5,6,7,8,9,10){]
	//(?#ALGNAME=MonthDate)(?#PARA=15,16,17,18){]
	//(?#ALGNAME=MOD112)(?#PARA=5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22){]

	@Test
	public void testGBT2681920116()
	{
		String LengthRule = "23";
		FunctionResult res = new FunctionResult();
		String para1 = "0,1,2";
		String para2 = "5,6,7,8,9,10";
		String para3 = "15,16,17,18";
		String para4 = "5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21;22";
	
		res = RuleRandom.IoTIDLength_Random(LengthRule);
		
		String RawByteRule = "3,2,0,0,0,0,0,0,0;4,1,0,0,0,0,0,0,0;11,254,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;19,255,3,0,0,0,0,0,0;20,255,3,0,0,0,0,0,0;21,255,3,0,0,0,0,0,0;";
		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.CountryRegionCode_Random(res, para1);
		res = RuleRandom.AdminDivision_Random(res, para2);
		res = RuleRandom.MonthDate_Random(res, para3);
		res = RuleRandom.MOD112_Random(res, para4);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	// GB/T_26819-2011_4
	// (?#ALGNAME=CountryRegionCode)(?#PARA=0,1,2){]
	@Test
	public void testGBT2681920114() {
		String LengthRule = "13";
		FunctionResult res = new FunctionResult();
		String para1 = "0,1,2";

		res = RuleRandom.IoTIDLength_Random(LengthRule);

		String RawByteRule = "3,16,0,0,0,0,0,0,0;4,1,0,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;";
		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.CountryRegionCode_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	
	//GB/T_26819-2011_5
	//(?#ALGNAME=CountryRegionCode)(?#PARA=0,1,2){]
	@Test
	public void testGBT2681920115()
	{
		String LengthRule = "14";
		FunctionResult res = new FunctionResult();
		String para1 = "0,1,2";

		res = RuleRandom.IoTIDLength_Random(LengthRule);

		String RawByteRule = "3,8,0,0,0,0,0,0,0;4,1,0,0,0,0,0,0,0;5,0,0,0,0,0,8,1,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;";
		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.CountryRegionCode_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GB/T_26819-2011_2
	//(?#ALGNAME=CountryRegionCode)(?#PARA=0,1,2){]
	@Test
	public void testGBT2681920112()
	{
		int k=0;
		
		String LengthRule = "5";
		FunctionResult res = new FunctionResult();
		String para1 = "0,1,2";

		res = RuleRandom.IoTIDLength_Random(LengthRule);

		String RawByteRule = "3,0,2,0,0,0,0,0,0;4,1,0,0,0,0,0,0,0;";
		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.CountryRegionCode_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GB/T_26819-2011_3
	//(?#ALGNAME=CountryRegionCode)(?#PARA=0,1,2){]
	@Test
	public void testGBT2681920113()
	{
		String LengthRule = "14";
		FunctionResult res = new FunctionResult();
		String para1 = "0,1,2";

		res = RuleRandom.IoTIDLength_Random(LengthRule);

		String RawByteRule = "3,32,0,0,0,0,0,0,0;4,1,0,0,0,0,0,0,0;5,0,0,0,0,128,4,72,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;";
		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.CountryRegionCode_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	/**
	 * 0811下午
	 */
	//GB/T_26819-2011_1
	//(?#ALGNAME=MOD11)(?#PARA=0,1,2,3,4,5,6,7,8){]
	//(?#ALGNAME=CreditIdentifiers)(?#PARA=13,-1){]
	@Test
	public void testGBT2681920111()
	{
		String LengthRule = "14-15";
		FunctionResult res = new FunctionResult();
		String para1 = "0,1,2,3,4,5,6,7;8";
		String para2="13,-1";

		res = RuleRandom.IoTIDLength_Random(LengthRule);

		String RawByteRule = "0,255,3,0,0,240,255,255,63;1,255,3,0,0,240,255,255,63;2,255,3,0,0,240,255,255,63;3,255,3,0,0,240,255,255,63;4,255,3,0,0,240,255,255,63;5,255,3,0,0,240,255,255,63;6,255,3,0,0,240,255,255,63;7,255,3,0,0,240,255,255,63;9,254,3,0,0,240,255,255,63;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;";
		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.MOD11_Random(res, para1);
		res = RuleRandom.CreditIdentifiers_Random(res, para2);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	
    //数据库未改
	//GA/T_556.10-2007
	//(?#ALGNAME=VehicleNOWJ)(?#PARA=0){]
	//(?#ALGNAME=FinancialCode)(?#PARA=9,10){]
	//(?#ALGNAME=ChineseGongFa)(?#PARA=1,2){]
	@Test
	public void testGAT556102007()
	{
		String LengthRule = "15";
		FunctionResult res = new FunctionResult();
		String para1 = "0";
		String para2="1,2";
		String para3="9,10";
		String para4="3,4,5,6,7,8";
		

		res = RuleRandom.IoTIDLength_Random(LengthRule);

		String RawByteRule = "11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,254,3,0,0,0,0,0,0;";
		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.VehicleNOWJ_Random(res, para1);
		res=RuleRandom.ChineseGongFa_Random(res, para2);
		res=RuleRandom.AdminDivision_Random(res, para4);
		res = RuleRandom.FinancialCode_Random(res, para3);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	

	//GA/T_396-2002_4
	//(?#ALGNAME=AdminDivision1)(?#PARA=0,1,2,3,4,5,6,7,8){]
	//(?#ALGNAME=ThreeByteDecimalnt)(?#PARA=9,10,11){]
	@Test
	public void testGAT39620024()
	{
		String LengthRule = "12";
		FunctionResult res = new FunctionResult();
		String para1 = "0,1,2,3,4,5,6,7,8";
		String para2="9,10,11";
        
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		res = RuleRandom.AdminDivision1_Random(res, para1);
		res = RuleRandom.ThreeByteDecimalnt_Random(res, para2);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//JT/T_444-2001_6
	//(?#ALGNAME=OneTO11and90)(?#PARA=1,2){]
	//(?#ALGNAME=HighwayTransportation)(?#PARA=3,4,5){]
	//(?#ALGNAME=HighwayTransportation4b1)(?#PARA=6,7){]
	//(?#ALGNAME=RoadTransportation5)(?#PARA=8,9,10){]
	//(?#ALGNAME=HighwayTransportation4b7)(?#PARA=12,13,14){]
	@Test
	public void testJTT44420016(){
	String LengthRule = "16";
	FunctionResult res = new FunctionResult();
	String para1 = "1,2";
	String para2 = "3,4,5";
	String para3 = "6,7";
	String para4 = "8,9,10";
	String para5 = "12,13,14";

	res = RuleRandom.IoTIDLength_Random(LengthRule);
	
	String RawByteRule = "0,8,0,0,0,0,0,0,0;11,6,0,0,0,0,0,0,0;15,2,0,0,0,0,0,0,0;";
	String ByteRule = "";
	String[] Byterules = RawByteRule.split(";");
	for (int i = 0; i < Byterules.length; i++) {
		ByteRule = Byterules[i];
		res = RuleRandom.IoTIDByte_Random(res, ByteRule);
	}

	res = RuleRandom.OneTO11and90_Random(res, para1);
	res = RuleRandom.HighwayTransportation_Random(res, para2);
	res = RuleRandom.HighwayTransportation4b1_Random(res, para3);
	res = RuleRandom.RoadTransportation5_Random(res, para4);
	res = RuleRandom.HighwayTransportation4b7_Random(res, para5);
	RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//数据库异常值
	//JT/T_444-2001_7
	//(?#ALGNAME=OneTO11and90)(?#PARA=1,2){]
	//(?#ALGNAME=HighwayTransportation)(?#PARA=3,4,5){]
	//(?#ALGNAME=HighwayTransportation4b1)(?#PARA=6,7){]
	//(?#ALGNAME=RoadTransportation5)(?#PARA=8,9,10){]
	//(?#ALGNAME=HighwayTransportation4b7)(?#PARA=11,12,13){]
	//(?#ALGNAME=HighwayTransportation4b9)(?#PARA=14,15){]
	@Test
	public void testJTT44420017()
	{
		String LengthRule = "17";
		FunctionResult res = new FunctionResult();
		String para1 = "1,2";
		String para2 = "3,4,5";
		String para3 = "6,7";
		String para4 = "8,9,10";
		String para5 = "11,12,13";
		String para6="14,15";

		res = RuleRandom.IoTIDLength_Random(LengthRule);
		
		String RawByteRule = "0,8,0,0,0,0,0,0,0;16,2,0,0,0,0,0,0,0;";
		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.OneTO11and90_Random(res, para1);
		res = RuleRandom.HighwayTransportation_Random(res, para2);
		res = RuleRandom.HighwayTransportation4b1_Random(res, para3);
		res = RuleRandom.RoadTransportation5_Random(res, para4);
        res = RuleRandom.HighwayTransportation4b7_Random(res, para5);
        res = RuleRandom.HighwayTransportation4b9_Random(res, para6);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//JT/T_444-2001_4
	//(?#ALGNAME=OneTO11and90)(?#PARA=1,2){]
	//(?#ALGNAME=HighwayTransportation)(?#PARA=3,4,5){]
	//(?#ALGNAME=HighwayTransportation4b1)(?#PARA=6,7){]
	//(?#ALGNAME=RoadTransportation5)(?#PARA=8,9,10){]
	//(?#ALGNAME=HighwayTransportation4b7)(?#PARA=12,13,14){]
	//(?#ALGNAME=HighwayTransportation4c3)(?#PARA=16,17,18,19){]
	@Test
	public void testJTT44420014()
	{
		String LengthRule = "22";
		FunctionResult res = new FunctionResult();
		String para1 = "1,2";
		String para2 = "3,4,5";
		String para3 = "6,7";
		String para4 = "8,9,10";
		String para5 = "12,13,14";
		String para6="16,17,18,19";

		res = RuleRandom.IoTIDLength_Random(LengthRule);
		
		String RawByteRule = "0,8,0,0,0,0,0,0,0;11,6,0,0,0,0,0,0,0;15,14,0,0,0,0,0,0,0;20,31,2,0,0,0,0,0,0;21,2,0,0,0,0,0,0,0;";
		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.OneTO11and90_Random(res, para1);
		res = RuleRandom.HighwayTransportation_Random(res, para2);
		res = RuleRandom.HighwayTransportation4b1_Random(res, para3);
		res = RuleRandom.RoadTransportation5_Random(res, para4);
		res = RuleRandom.HighwayTransportation4b7_Random(res, para5);
		res = RuleRandom.HighwayTransportation4c3_Random(res, para6);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//JT/T_444-2001_5
	//(?#ALGNAME=OneTO11and90)(?#PARA=1,2){]
	//(?#ALGNAME=HighwayTransportation)(?#PARA=3,4,5){]
	//(?#ALGNAME=HighwayTransportation4b1)(?#PARA=6,7){]
	//(?#ALGNAME=RoadTransportation5)(?#PARA=8,9,10){]
	@Test
	public void testJTT44420015()
	{

		String LengthRule = "19";
		FunctionResult res = new FunctionResult();
		String para1 = "1,2";
		String para2 = "3,4,5";
		String para3 = "6,7";
		String para4 = "8,9,10";
		

		res = RuleRandom.IoTIDLength_Random(LengthRule);
		
		String RawByteRule = "0,8,0,0,0,0,0,0,0;11,6,0,0,0,0,0,0,0;12,14,0,0,0,0,0,0,0;13,103,0,0,0,0,0,0,0;14,159,1,0,0,0,0,0,0;15,7,0,0,0,0,0,0,0;16,6,0,0,0,0,0,0,0;17,31,2,0,0,0,0,0,0;18,2,0,0,0,0,0,0,0;";
		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.OneTO11and90_Random(res, para1);
		res = RuleRandom.HighwayTransportation_Random(res, para2);
		res = RuleRandom.HighwayTransportation4b1_Random(res, para3);
		res = RuleRandom.RoadTransportation5_Random(res, para4);
	
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GB/T_7407-2008
	//(?#ALGNAME=CountryRegionCode)(?#PARA=0,1){]
	@Test
	public void testGBT74072008()
	{
		String LengthRule = "5";
		FunctionResult res = new FunctionResult();
		String para1 = "0,1";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
		
		String RawByteRule = "2,0,0,0,0,240,255,255,63;3,0,0,0,0,240,255,255,63;4,0,0,0,0,240,255,255,63;";
		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.CountryRegionCode_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//0820被打回异常，缺少字节规则第三位
	//GB/T_4942.1-2006/IEC_60034-5:2000
	//(?#ALGNAME=ProtectionDegreeRegex)(?#PARA=4,-1){]
	@Test
	public void testGBT494212006IEC6003452000()
	{
		String LengthRule = "4-30";
		FunctionResult res = new FunctionResult();
		String para1 = "4,-1";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
		
		String RawByteRule = "0,0,0,0,0,0,16,0,0;1,0,0,0,0,0,0,8,0;2,127,0,0,0,0,0,0,8;3,255,1,0,0,0,0,0,8;";
		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.ProtectionDegreeRegex_Random(res, para1);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//YC/T_190-2005
	//(?#ALGNAME=CigaOrgCode)(?#PARA=0,1){]
	//(?#ALGNAME=First4CharsofAdminDivisionforCiga)(?#PARA=2,3,4,5){]
    @Test
	public void testYCT1902005()
	{
		String LengthRule = "8";
		FunctionResult res = new FunctionResult();
		String para1 = "0,1";
		String para2="2,3,4,5";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
		
		String RawByteRule = "6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;";
		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.CigaOrgCode_Random(res, para1);
		res = RuleRandom.First4CharsofAdminDivisionforCiga_Random(res, para2);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	/**
	 * 0822
	 */
	
	//GA/T_556.7-2007
	//(?#ALGNAME=AdminDivision)(?#PARA=0,1,2,3,4,5){]
	//(?#ALGNAME=FinancialCode)(?#PARA=6,7){]
	//(?#ALGNAME=FourByteDecimalnt)(?#PARA=8,9,10,11){]
    @Test
	public void testGAT55672007()
	{
		String LengthRule = "12";
		FunctionResult res = new FunctionResult();
		String para1 = "0,1,2,3,4,5";
		String para2="6,7";
		String para3="8,9,10,11";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
		
		res = RuleRandom.AdminDivision_Random(res, para1);
		res = RuleRandom.FinancialCode_Random(res, para2);
		res = RuleRandom.FourByteDecimalnt_Random(res, para3);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//fixedpart
	//(?#ALGNAME=Hyphen)(?#PARA=12){]
	//(?#ALGNAME=CarProductCompnent)(?#PARA=13,14,15,16){]
	//(?#ALGNAME=TwoByteDecimalnt)(?#PARA=19,20){]
	@Test
	public void testfixedpart()
	{
		String LengthRule = "22";
		FunctionResult res = new FunctionResult();
		String para1 = "12";
		String para2="13,14,15,16";
		String para3="19,20";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
		
		String RawByteRule = "0,0,0,0,0,240,255,255,63;1,0,0,0,0,240,255,255,63;2,224,0,0,0,0,0,0,0;3,255,255,255,255,255,255,255,63;4,255,255,255,255,255,255,255,63;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;17,255,3,0,0,0,0,0,0;18,255,3,0,0,0,0,0,0;21,0,0,0,0,0,2,0,0;";
		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.Hyphen_Random(res, para1);
		res = RuleRandom.CarProductCompnent_Random(res, para2);
		res = RuleRandom.TwoByteDecimalnt_Random(res, para3);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GB/T_28923.1-2012_2
	//(?#ALGNAME=TwobytleCode07)(?#PARA=2,3){]
	@Test
	public void testGBT2892312012_2()
	{
		String LengthRule = "4";
		FunctionResult res = new FunctionResult();
		String para1 = "2,3";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
		
		String RawByteRule = "0,1,0,0,0,0,0,0,0;1,8,0,0,0,0,0,0,0;";
		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.TwobytleCode07_Random(res, para1);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GA_658.6-2006
	//(?#ALGNAME=OneTO09)(?#PARA=0,1){]
	@Test
	public void testGA65862006()
	{
		String LengthRule = "2";
		FunctionResult res = new FunctionResult();
		String para1 = "0,1";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
		res = RuleRandom.OneTO09_Random(res, para1);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//WS_364.10-2011_13
	//(?#ALGNAME=OneTO13)(?#PARA=0,1){]
	@Test
	public void testWS36410201113()
	{
		String LengthRule = "2";
		FunctionResult res = new FunctionResult();
		String para1 = "0,1";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
		res = RuleRandom.OneTO13_Random(res, para1);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//CY/T_58.2-2009
	//(?#ALGNAME=ThreeByteDecimalnt)(?#PARA=10,11,12){]
	//(?#ALGNAME=TwoByteDecimalnt)(?#PARA=13,14){]
	//(?#ALGNAME=MrpCheck)(?#PARA=0,1,2,3,4,5,6,7,8,9,10,11,12,13,14;15){]
	@Test
	public void testCYT5822009()
	{
		String LengthRule = "16";
		FunctionResult res = new FunctionResult();
		String para1 = "10,11,12";
		String para2="13,14";
		String para3="0,1,2,3,4,5,6,7,8,9,10,11,12,13,14;15";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
		
		String RawByteRule = "0,255,3,0,0,0,0,0,0;1,255,3,0,0,0,0,0,0;2,255,3,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;";
		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.ThreeByteDecimalnt_Random(res, para1);
		res = RuleRandom.TwoByteDecimalnt_Random(res, para2);
		res = RuleRandom.MrpCheck_Random(res, para3);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//GB/T_12402-2000
	//(?#ALGNAME=EconomicCate)(?#PARA=0,1,2){]
	@Test
	public void testGBT124022000()
	{
		String LengthRule = "3";
		FunctionResult res = new FunctionResult();
		String para1 = "0,1,2";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
		res = RuleRandom.EconomicCate_Random(res, para1);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//JT/T_415-2000_1
	//(?#ALGNAME=OneTO42No99)(?#PARA=0,1){]
	@Test
	public void testJTT41520001()
	{
		String LengthRule = "2";
		FunctionResult res = new FunctionResult();
		String para1 = "0,1";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
		res = RuleRandom.OneTO42No99_Random(res, para1);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//WS_364.3-2011_3
	//(?#ALGNAME=TwobytleCode07)(?#PARA=0,1){]
	@Test
	public void testWS364320113()
	{
		String LengthRule = "2";
		FunctionResult res = new FunctionResult();
		String para1 = "0,1";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
		res = RuleRandom.TwobytleCode07_Random(res, para1);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//DL/T_700.1-1999_54
	//DL/T_700.2-1999_51
	//17
	//0,30,2,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,254,3,0,0,0,0,0,0;10,254,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;16,255,3,0,0,0,0,0,0;
	//0,30,2,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,254,3,0,0,0,0,0,0;10,254,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;16,255,3,0,0,0,0,0,0;
	//(?#ALGNAME=TwoByteDecimalnt)(?#PARA=1,2){]
	//(?#ALGNAME=TwoByteDecimalnt)(?#PARA=3,4){]
	@Test
	public void testDLT7002199951()
	{
		String LengthRule = "17";
		FunctionResult res = new FunctionResult();
		String para1 = "1,2";
		String para2="3,4";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
	
		String RawByteRule = "0,30,2,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,254,3,0,0,0,0,0,0;10,254,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;16,255,3,0,0,0,0,0,0;";
		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.TwoByteDecimalnt_Random(res, para1);
		res = RuleRandom.TwoByteDecimalnt_Random(res, para2);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	
	}
	
	//DL/T_700.1-1999_53
	//DL/T_700.2-1999_50
	//modify:0827 该类型整个就错了
	//8,9,10
	//(?#ALGNAME=OneTO17)(?#PARA=0,1){]
	//(?#ALGNAME=Powergoodsuncertainly)(?#PARA=7,-1){]
	//2,255,3,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;
	//2,255,3,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;
	//(?#ALGNAME=OneTO17)(?#PARA=4,5){](?#ALGNAME=TwoByteDecimalnt)(?#PARA=6,7){](?#ALGNAME=TwoByteDecimalnt)(?#PARA=8,9){](?#ALGNAME=Powergoodsuncertainly)(?#PARA=10,-1){]
	@Test
	public void testDLT7002199950()
	{
		String LengthRule = "12,13,14";
		FunctionResult res = new FunctionResult();
		String para1 = "4,5";
		String para2="6,7";
		String para3="8,9";
		String para4="10,-1";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
	
		String RawByteRule = "0,4,0,0,0,0,0,0,0;1,2,0,0,0,0,0,0,0;2,1,0,0,0,0,0,0,0;3,16,0,0,0,0,0,0,0;";
		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.OneTO17_Random(res, para1);
		res=RuleRandom.TwoByteDecimalnt_Random(res, para2);
		res=RuleRandom.TwoByteDecimalnt_Random(res, para3);
		res = RuleRandom.Powergoodsuncertainly_Random(res, para4);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//DL/T_700.1-1999_52
	//DL/T_700.2-1999_49
	//(?#ALGNAME=OneTO13)(?#PARA=0,1){]
	//(?#ALGNAME=OneTO12No99)(?#PARA=2,3){]
	//(?#ALGNAME=OneTO13No99)(?#PARA=7,8){]
	//4,255,3,0,0,0,0,0,0;5,222,3,0,0,0,0,0,0;6,250,2,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;
	//4,255,3,0,0,0,0,0,0;5,222,3,0,0,0,0,0,0;6,250,2,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;
	//11
	
	@Test
	public void testDLT7002199949()
	{
		String LengthRule = "11";
		FunctionResult res = new FunctionResult();
		String para1 = "0,1";
		String para2="2,3";
		String para3="7,8";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
	
		String RawByteRule = "4,255,3,0,0,0,0,0,0;5,222,3,0,0,0,0,0,0;6,250,2,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;";
		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.OneTO13_Random(res, para1);
		res = RuleRandom.OneTO12No99_Random(res, para2);
		res = RuleRandom.OneTO12No99_Random(res, para3);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//DL/T_700.1-1999_5
	//DL/T_700.2-1999_2
	//16
	//9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;
	//9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;
	//(?#ALGNAME=PowerMaterials53)(?#PARA=0,1,2,3){]
	//(?#ALGNAME=TwoByteDecimalnt)(?#PARA=4,5){]
	//(?#ALGNAME=ThreeByteDecimalnt)(?#PARA=6,7,8){]
	@Test
	public void testDLT700219992()
	{
		String LengthRule = "16";
		FunctionResult res = new FunctionResult();
		String para1 = "0,1,2,3";
		String para2="4,5";
		String para3="6,7,8";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
	
		String RawByteRule = "9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;";
		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.PowerMaterials53_Random(res, para1);
		res = RuleRandom.TwoByteDecimalnt_Random(res, para2);
		res = RuleRandom.ThreeByteDecimalnt_Random(res, para3);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//DL/T_700.1-1999_4
	//DL/T_700.2-1999_1
	//9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;
	//9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;
	//(?#ALGNAME=PowerMaterials54)(?#PARA=0,1,2,3){]
	//(?#ALGNAME=TwoByteDecimalnt)(?#PARA=4,5){]
	//(?#ALGNAME=ThreeByteDecimalnt)(?#PARA=6,7,8){]
	//16
	@Test
	public void testDLT700219991()
	{
		String LengthRule = "16";
		FunctionResult res = new FunctionResult();
		String para1 = "0,1,2,3";
		String para2="4,5";
		String para3="6,7,8";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
	
		String RawByteRule = "9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;";
		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.PowerMaterials54_Random(res, para1);
		res = RuleRandom.TwoByteDecimalnt_Random(res, para2);
		res = RuleRandom.ThreeByteDecimalnt_Random(res, para3);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//DL/T_700.1-1999_7
	//DL/T_700.2-1999_4
	//16
	//9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;
	//9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;
	//(?#ALGNAME=PowerMaterials51)(?#PARA=0,1,2,3){]
	//(?#ALGNAME=TwoByteDecimalnt)(?#PARA=4,5){]
	//(?#ALGNAME=ThreeByteDecimalnt)(?#PARA=6,7,8){]
    @Test
	public void testDLT700219994()
	{
		String LengthRule = "16";
		FunctionResult res = new FunctionResult();
		String para1 = "0,1,2,3";
		String para2="4,5";
		String para3="6,7,8";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
	
		String RawByteRule = "9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;";
		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.PowerMaterials51_Random(res, para1);
		res = RuleRandom.TwoByteDecimalnt_Random(res, para2);
		res = RuleRandom.ThreeByteDecimalnt_Random(res, para3);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//DL/T_700.1-1999_6
	//DL/T_700.2-1999_3
	//9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;
	//9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;
	//(?#ALGNAME=PowerMaterials52)(?#PARA=0,1,2,3){]
	//(?#ALGNAME=TwoByteDecimalnt)(?#PARA=4,5){]
	//(?#ALGNAME=ThreeByteDecimalnt)(?#PARA=6,7,8){]
	@Test
	public void testDLT700219993()
	{
		String LengthRule = "16";
		FunctionResult res = new FunctionResult();
		String para1 = "0,1,2,3";
		String para2="4,5";
		String para3="6,7,8";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
	
		String RawByteRule = "9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;";
		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.PowerMaterials52_Random(res, para1);
		res = RuleRandom.TwoByteDecimalnt_Random(res, para2);
		res = RuleRandom.ThreeByteDecimalnt_Random(res, para3);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//DL/T_700.1-1999_9
	//DL/T_700.2-1999_6
	//(?#ALGNAME=PowerMaterials49)(?#PARA=0,1,2,3){]
	//(?#ALGNAME=TwoByteDecimalnt)(?#PARA=4,5){]
	//(?#ALGNAME=ThreeByteDecimalnt)(?#PARA=6,7,8){]
	//9
	@Test
	public void testDLT700219996()
	{
		String LengthRule = "9";
		FunctionResult res = new FunctionResult();
		String para1 = "0,1,2,3";
		String para2="4,5";
		String para3="6,7,8";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
	
		res = RuleRandom.PowerMaterials49_Random(res, para1);
		res = RuleRandom.TwoByteDecimalnt_Random(res, para2);
		res = RuleRandom.ThreeByteDecimalnt_Random(res, para3);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//DL/T_700.1-1999_8
	//DL/T_700.2-1999_5
	//(?#ALGNAME=PowerMaterials50)(?#PARA=0,1,2,3){]
	//(?#ALGNAME=TwoByteDecimalnt)(?#PARA=4,5){]
	//(?#ALGNAME=ThreeByteDecimalnt)(?#PARA=6,7,8){]
	//16
	@Test
	public void testDLT700219995()
	{
		String LengthRule = "16";
		FunctionResult res = new FunctionResult();
		String para1 = "0,1,2,3";
		String para2="4,5";
		String para3="6,7,8";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
	
		String RawByteRule = "9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;";
		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.PowerMaterials50_Random(res, para1);
		res = RuleRandom.TwoByteDecimalnt_Random(res, para2);
		res = RuleRandom.ThreeByteDecimalnt_Random(res, para3);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//DL/T_700.1-1999_39
	//DL/T_700.2-1999_36
	//12
	//0,14,0,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;
	//0,14,0,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;
	//(?#ALGNAME=TwoByteDecimalnt)(?#PARA=1,2){]
	@Test
	public void testDLT7002199936()
	{
		String LengthRule = "12";
		FunctionResult res = new FunctionResult();
		String para1 = "1,2";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
	
		String RawByteRule = "0,14,0,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;";
		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.TwoByteDecimalnt_Random(res, para1);
	
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//DL/T_700.1-1999_27
	//DL/T_700.2-1999_24
	//(?#ALGNAME=OneTO12No99)(?#PARA=0,1){]
	//(?#ALGNAME=TwoByteDecimalnt)(?#PARA=2,3){]
	//(?#ALGNAME=ParamCode30)(?#PARA=4,-1){]
	@Test
	public void testDLT7002199924()
	{
		String LengthRule = "6-12";
		FunctionResult res = new FunctionResult();
		String para1 = "0,1";
		String para2="2,3";
		String para3="4,-1";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
	
		res = RuleRandom.OneTO12No99_Random(res, para1);
		res = RuleRandom.TwoByteDecimalnt_Random(res, para2);
		res = RuleRandom.ParamCode30_Random(res, para3);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	
	//GB/T_2659-2000
	//(?#ALGNAME=CountryRegionCode)(?#PARA=0,1,2){]
	@Test
	public void testGBT26592000()
	{
		String LengthRule = "2-3";
		FunctionResult res = new FunctionResult();
		String para1 = "0,1,2";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
	
		res = RuleRandom.CountryRegionCode_Random(res, para1);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//DL/T_700.1-1999_11
	//DL/T_700.2-1999_8
	//(?#ALGNAME=PowerMaterials46)(?#PARA=0,1,2,3){]
	//(?#ALGNAME=TwoByteDecimalnt)(?#PARA=4,5){]
	//(?#ALGNAME=ThreeByteDecimalnt)(?#PARA=6,7,8){]
	//9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;
	//9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;
	
	@Test
	public void testDLT700219998()
	{
		String LengthRule = "16";
		FunctionResult res = new FunctionResult();
		String para1 = "0,1,2,3";
		String para2="4,5";
		String para3="6,7,8";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
	
		String RawByteRule = "9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;";
		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.PowerMaterials46_Random(res, para1);
		res = RuleRandom.TwoByteDecimalnt_Random(res, para2);
		res = RuleRandom.ThreeByteDecimalnt_Random(res, para3);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//DL/T_700.1-1999_10
	//DL/T_700.2-1999_7
	//7
	//(?#ALGNAME=OneTO03)(?#PARA=0,1){]
	//(?#ALGNAME=TwoByteDecimalnt)(?#PARA=2,3){]
	//(?#ALGNAME=ThreeByteDecimalnt)(?#PARA=4,5,6){]
	
	@Test
	public void testDLT700219997()
	{
		String LengthRule = "7";
		FunctionResult res = new FunctionResult();
		String para1 = "0,1";
		String para2="2,3";
		String para3="4,5,6";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
	    res = RuleRandom.OneTO03_Random(res, para1);
		res = RuleRandom.TwoByteDecimalnt_Random(res, para2);
		res = RuleRandom.ThreeByteDecimalnt_Random(res, para3);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//DL/T_700.1-1999_13
	//DL/T_700.2-1999_10
	//(?#ALGNAME=PowerMaterials45)(?#PARA=0,1,2,3){]
	//(?#ALGNAME=TwoByteDecimalnt)(?#PARA=4,5){]
	//(?#ALGNAME=ThreeByteDecimalnt)(?#PARA=6,7,8){]
	@Test
	public void testDLT7002199910()
	{
		String LengthRule = "16";
		FunctionResult res = new FunctionResult();
		String para1 = "0,1,2,3";
		String para2="4,5";
		String para3="6,7,8";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
	
		String RawByteRule = "9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;";
		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.PowerMaterials45_Random(res, para1);
		res = RuleRandom.TwoByteDecimalnt_Random(res, para2);
		res = RuleRandom.ThreeByteDecimalnt_Random(res, para3);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//DL/T_700.1-1999_12
	//DL/T_700.2-1999_9
	//(?#ALGNAME=PowerMaterials46)(?#PARA=0,1,2,3){]
	//(?#ALGNAME=TwoByteDecimalnt)(?#PARA=4,5){]
	//(?#ALGNAME=ThreeByteDecimalnt)(?#PARA=6,7,8){]
	//9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;
	//9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;
	
	@Test
	public void testDLT700219999()
	{
		String LengthRule = "16";
		FunctionResult res = new FunctionResult();
		String para1 = "0,1,2,3";
		String para2="4,5";
		String para3="6,7,8";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
	
		String RawByteRule = "9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;";
		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.PowerMaterials46_Random(res, para1);
		res = RuleRandom.TwoByteDecimalnt_Random(res, para2);
		res = RuleRandom.ThreeByteDecimalnt_Random(res, para3);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//DL/T_700.1-1999_15
	//DL/T_700.2-1999_12
	//9,11,12,13,14
	//(?#ALGNAME=OneTO15)(?#PARA=0,1){]
	//(?#ALGNAME=TwoByteDecimalnt)(?#PARA=2,3){]
	//(?#ALGNAME=TwoByteDecimalnt)(?#PARA=4,5){]
	//(?#ALGNAME=ParamCode43)(?#PARA=6,-1){]
	
	@Test
	public void testDLT7002199912()
	{
		String LengthRule = "9,11,12,13,14";
		FunctionResult res = new FunctionResult();
		String para1 = "0,1";
		String para2="2,3";
		String para3="4,5";
		String para4="6,-1";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
	
		res = RuleRandom.OneTO15_Random(res, para1);
		res = RuleRandom.TwoByteDecimalnt_Random(res, para2);
		res = RuleRandom.TwoByteDecimalnt_Random(res, para3);
		res = RuleRandom.ParamCode43_Random(res, para4);
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//DL/T_700.1-1999_14
	//DL/T_700.2-1999_11
	//(?#ALGNAME=PowerMaterials44)(?#PARA=0,1,2,3){]
	//(?#ALGNAME=TwoByteDecimalnt)(?#PARA=4,5){]
	//(?#ALGNAME=ThreeByteDecimalnt)(?#PARA=6,7,8){]
	@Test
	public void testDLT70021999_11()
	{
		String LengthRule = "16";
		FunctionResult res = new FunctionResult();
		String para1 = "0,1,2,3";
		String para2="4,5";
		String para3="6,7,8";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
	
		String RawByteRule = "9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;";
		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.PowerMaterials44_Random(res, para1);
		res = RuleRandom.TwoByteDecimalnt_Random(res, para2);
		res = RuleRandom.ThreeByteDecimalnt_Random(res, para3);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//DL/T_700.1-1999_17
	//DL/T_700.2-1999_14
	//(?#ALGNAME=TwoByteDecimalnt)(?#PARA=1,2){]
	//(?#ALGNAME=TwoByteDecimalnt)(?#PARA=3,4){]
	//(?#ALGNAME=ParamCode41)(?#PARA=5,-1){]
	@Test
	public void testDLT7002199914()
	{
		String LengthRule = "11,12";
		FunctionResult res = new FunctionResult();
		String para1 = "1,2";
		String para2="3,4";
		String para3="5,-1";
		res = RuleRandom.IoTIDLength_Random(LengthRule);
	
		String RawByteRule = "0,14,0,0,0,0,0,0,0;";
		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.TwoByteDecimalnt_Random(res, para1);
		res = RuleRandom.TwoByteDecimalnt_Random(res, para2);
		res = RuleRandom.ParamCode41_Random(res, para3);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//DL/T_700.1-1999_16
	//DL/T_700.2-1999_13
	//(?#ALGNAME=TwoByteDecimalnt)(?#PARA=2,3){]
	//0,62,0,0,0,0,0,0,0;1,254,3,0,0,0,0,0,0;4,254,3,0,0,0,0,0,0;
	//0,62,0,0,0,0,0,0,0;1,254,3,0,0,0,0,0,0;4,254,3,0,0,0,0,0,0;
	@Test
	public void testDLT7002199913()
	{
		String LengthRule = "5";
		FunctionResult res = new FunctionResult();
		String para1 = "2,3";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
	
		String RawByteRule = "0,62,0,0,0,0,0,0,0;1,254,3,0,0,0,0,0,0;4,254,3,0,0,0,0,0,0;";
		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.TwoByteDecimalnt_Random(res, para1);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	//DL/T_700.1-1999_19
	//DL/T_700.2-1999_16
	//(?#ALGNAME=TwoByteDecimalnt)(?#PARA=2,3){]
	//0,30,0,0,0,0,0,0,0;1,254,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;16,255,3,0,0,0,0,0,0;17,255,3,0,0,0,0,0,0;18,255,3,0,0,0,0,0,0;19,255,3,0,0,0,0,0,0;20,255,3,0,0,0,0,0,0;
	//0,30,0,0,0,0,0,0,0;1,254,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;16,255,3,0,0,0,0,0,0;17,255,3,0,0,0,0,0,0;18,255,3,0,0,0,0,0,0;19,255,3,0,0,0,0,0,0;20,255,3,0,0,0,0,0,0;
	@Test
	public void testDLT7002199916()
	{
		String LengthRule = "21";
		FunctionResult res = new FunctionResult();
		String para1 = "2,3";
		
		res = RuleRandom.IoTIDLength_Random(LengthRule);
	
		String RawByteRule = "0,30,0,0,0,0,0,0,0;1,254,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;16,255,3,0,0,0,0,0,0;17,255,3,0,0,0,0,0,0;18,255,3,0,0,0,0,0,0;19,255,3,0,0,0,0,0,0;20,255,3,0,0,0,0,0,0;";
		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.TwoByteDecimalnt_Random(res, para1);
		
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	
	
	//LLY

	@Test
	// HY/T_023-2010
	// (?#ALGNAME=First2CharsofCoastalAdminAreaId)(?#PARA=0,1){]
	// (?#ALGNAME=OceanStationCode)(?#PARA=7,8){]
	public void testHYT0232010() {
		String LengthRule = "12";
		String RawByteRule = "2,255,255,255,255,255,255,255,63;3,255,255,255,255,255,255,255,63;4,0,0,0,0,240,255,255,63;5,0,0,0,0,240,255,255,63;6,0,0,0,0,240,255,255,63;9,255,255,255,255,255,255,255,63;10,255,255,255,255,255,255,255,63;11,255,255,255,255,255,255,255,63;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.First2CharsofCoastalAdminAreaId_Random(res, "0,1");
		res = RuleRandom.OceanStationCode_Random(res, "7,8");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GB/T_19632-2005_1
	// (?#ALGNAME=First2CharsofAdminDivision)(?#PARA=0,1){]
	// (?#ALGNAME=FuneralInterment)(?#PARA=6,7,8,9,10,11,12){]
	// (?#ALGNAME=MOD9710)(?#PARA=0,1,2,3,4,5,6,7,8,9,10,11,12;13,14){]
	public void testGBT1963220051() {
		String LengthRule = "15";
		String RawByteRule = "2,14,0,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}

		res = RuleRandom.First2CharsofAdminDivision_Random(res, "0,1");
		res = RuleRandom.FuneralInterment_Random(res, "6,7,8,9,10,11,12");
		res = RuleRandom.MOD9710_Random(res,
				"0,1,2,3,4,5,6,7,8,9,10,11,12;13,14");
		// res=RuleRandom.MOD112_Random(res, para3);
		// res=RuleRandom.OceanStationCode_Random(res,"7,8");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// YC/T_213.5-2006
	// (?#ALGNAME=TabaccoElectricComponent)(?#PARA=2,3,4,5,6){]
	// (?#ALGNAME=TabaccoMachineProducer)(?#PARA=12,13){]
	public void testYCT21352006() {
		String LengthRule = "14";
		String RawByteRule = "0,8,0,0,0,0,0,0,0;1,8,0,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		res = RuleRandom.TabaccoElectricComponent_Random(res, "2,3,4,5,6");
		res = RuleRandom.TabaccoMachineProducer_Random(res, "12,13");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// YC/T_213.6-2006
	// (?#ALGNAME=TabaccoMaterial)(?#PARA=2,3,4,5,6){]
	// (?#ALGNAME=TabaccoMachineProducer)(?#PARA=12,13){]
	public void testYCT21362006() {
		String LengthRule = "14";
		String RawByteRule = "0,8,0,0,0,0,0,0,0;1,2,0,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		res = RuleRandom.TabaccoMaterial_Random(res, "2,3,4,5,6");
		res = RuleRandom.TabaccoMachineProducer_Random(res, "12,13");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GB/T_28921-2012
	// (?#ALGNAME=Naturaldisaster)(?#PARA=0,1,2,3,4,5){]
	public void testGBT289212012() {
		String LengthRule = "6";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.Naturaldisaster_Random(res, "0,1,2,3,4,5");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	/**
	 * 
	 * user:lly Date:2014-8-12
	 * 
	 */
	@Test
	// GA_459-2004
	// (?#ALGNAME=IDcardByMaterial)(?#PARA=0,1,2,3,4,5,6){]
	public void testGA4592004() {
		String LengthRule = "7";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random

		res = RuleRandom.IDcardByMaterial_Random(res, "0,1,2,3,4,5,6");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	// GB/T_23705-2009_3
	// (?#ALGNAME=AdminDivision)(?#PARA=0,1,2,3,4,5){]
	// (?#ALGNAME=DigitRegex)(?#PARA=14,-1){]

	public void testGBT237052009_3() {
		String LengthRule = "16,-1";
		String RawByteRule = "6,15,0,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,3,0,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,3,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		res = RuleRandom.AdminDivision_Random(res, "0,1,2,3,4,5");
		res = RuleRandom.DigitRegex_Random(res, "14,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GA_520.1-2004
	// (?#ALGNAME=SecurityAccounterments)(?#PARA=0,1,2,3,4,5,6){]
	public void testGA52012004() {
		String LengthRule = "7";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.SecurityAccounterments_Random(res, "0,1,2,3,4,5,6");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GB/T_22970-2010_1
	// (?#ALGNAME=TextileFabricNameCode)(?#PARA=0,1,2,3,4){]
	// (?#ALGNAME=PropertiesMain)(?#PARA=5,6){]
	// (?#ALGNAME=PropertiesFiberCharacteristics)(?#PARA=7,8){]
	// (?#ALGNAME=OneTO09)(?#PARA=9,10){]
	// (?#ALGNAME=PropertiesMix)(?#PARA=11,12){]
	// (?#ALGNAME=PropertiesFabric)(?#PARA=13,14){]
	// (?#ALGNAME=PropertiesDyeingandFinishing)(?#PARA=15,16){]
	public void testGBT2297020101() {
		String LengthRule = "17";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random

		res = RuleRandom.TextileFabricNameCode_Random(res, "0,1,2,3,4");
		res = RuleRandom.PropertiesMain_Random(res, "5,6");
		res = RuleRandom.PropertiesFiberCharacteristics_Random(res, "7,8");
		res = RuleRandom.OneTO09_Random(res, "9,10");
		res = RuleRandom.PropertiesMix_Random(res, "11,12");
		res = RuleRandom.PropertiesFabric_Random(res, "13,14");
		res = RuleRandom.PropertiesDyeingandFinishing_Random(res, "15,16");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// IoTID=JB/T_5992.8-92
	// (?#ALGNAME=Machinery8)(?#PARA=0,1,2,3,4){
	public void testJBT5992892() {
		String LengthRule = "5";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random

		res = RuleRandom.Machinery8_Random(res, "0,1,2,3,4");

		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GA/T_867.1-2010
	// (?#ALGNAME=Casecharacer)(?#PARA=0,1,2,3,4,5){]
	public void testGAT86712010() {
		String LengthRule = "6";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}

		res = RuleRandom.Casecharacer_Random(res, "0,1,2,3,4,5");

		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JB/T_5992.7-92
	// (?#ALGNAME=Machinery7)(?#PARA=0,1,2,3,4){]
	public void testJBT5992792() {
		String LengthRule = "5";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}

		res = RuleRandom.Machinery7_Random(res, "0,1,2,3,4");

		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// YC/T_414-2011_1
	// (?#ALGNAME=CigaSubClassCode)(?#PARA=4,5,6){]
	// (?#ALGNAME=MonthDate)(?#PARA=15,16,17,18){]
	public void testYCT41420111() {
		String LengthRule = "22";
		String RawByteRule = "0,0,0,0,0,0,0,0,16;1,0,0,0,0,64,0,0,0;2,0,0,0,0,0,0,64,0;3,0,0,0,0,32,0,0,0;4,30,2,0,0,0,0,0,0;7,3,0,0,0,0,0,0,0;8,3,0,0,0,0,0,0,0;9,3,0,0,0,0,0,0,0;10,3,0,0,0,0,0,0,0;11,3,0,0,0,0,0,0,0;12,3,0,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;19,255,3,0,0,0,0,0,0;20,255,3,0,0,0,0,0,0;21,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String ByteRule = "";
		String[] Byterules = RawByteRule.split(";");
		for (int i = 0; i < Byterules.length; i++) {
			ByteRule = Byterules[i];
			res = RuleRandom.IoTIDByte_Random(res, ByteRule);
		}
		res = RuleRandom.CigaSubClassCode_Random(res, "4,5,6");
		res = RuleRandom.MonthDate_Random(res, "15,16,17,18");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GA_408.2-2006
	// ](?#ALGNAME=TenByteDecimalnt)(?#PARA=11,12,13,14,15,16,17,18,19,20){]
	// (?#ALGNAME=AdminDivision)(?#PARA=0,1,2,3,4,5){]
	public void testGA40822006() {
		String LengthRule = "22";
		String RawByteRule = "6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,6,0,0,0,0,0,0,0;21,254,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.AdminDivision_Random(res, "0,1,2,3,4,5");
		res = RuleRandom.TenByteDecimalnt_Random(res,"11,12,13,14,15,16,17,18,19,20");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// YC/T_400-2011
	// (?#ALGNAME=CigaOrgCode)(?#PARA=0,1){]
	// (?#ALGNAME=First4CharsofAdminDivisionforCiga)(?#PARA=2,3,4,5){]
	// (?#ALGNAME=CigaDepCode)(?#PARA=8,9){]
	public void testYCT4002011() {
		String LengthRule = "15";
		String RawByteRule = "6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;10,3,0,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.CigaOrgCode_Random(res, "0,1");
		res = RuleRandom.First4CharsofAdminDivisionforCiga_Random(res, "2,3,4,5");
		res = RuleRandom.CigaDepCode_Random(res, "8,9");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	/**
	 * 
	 * user:lly Date:2014-8-12
	 */
	// GA/T_556.8-2007
	// (?#ALGNAME=AdminDivision)(?#PARA=0,1,2,3,4,5){]
	// (?#ALGNAME=FinancialCode)(?#PARA=6,7){]
	// (?#ALGNAME=TreasuryClass)(?#PARA=8,9){]
	@Test
	public void testGAT55682007() {
		String LengthRule = "12";
		String RawByteRule = "10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.AdminDivision_Random(res, "0,1,2,3,4,5");
		res = RuleRandom.FinancialCode_Random(res, "6,7");
		res = RuleRandom.TreasuryClass_Random(res, "8,9");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	// IoTID=LS/T_1708.2-2004
	// (?#ALGNAME=FoodEconomy)(?#PARA=0,1,2,3,4){]
	@Test
	public void testLST170822004() {
		String LengthRule = "5";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.FoodEconomy_Random(res, "0,1,2,3,4");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	//WS/T_118-1999
	// (?#ALGNAME=MedicalInstru)(?#PARA=0,1,2,3,4,5,6,7){]
	@Test
	public void testWST1181999() {
		String LengthRule = "8";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.MedicalInstru_Random(res, "0,1,2,3,4,5,6,7");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	// GB/T_27766-2011_4 (?#ALGNAME=DigitAndLetter)(?#PARA=0,1,2,3,4,5){]
	@Test
	public void testGBT2776620114() {
		String LengthRule = "6";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.DigitAndLetter_Random(res, "0,1,2,3,4,5");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	// GB/T_18521-2001
	// (?#ALGNAME=CountryRegionCode)(?#PARA=1,2,3){]
	// (?#ALGNAME=GeographicalCode)(?#PARA=4,5,6,7){]
	@Test
	public void testGBT185212001() {
		String LengthRule = "10";
		String RawByteRule = "0,254,1,0,0,0,0,0,0;8,6,0,0,0,0,0,0,0;9,14,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.CountryRegionCode_Random(res, "1,2,3");
		res = RuleRandom.GeographicalCode_Random(res, "4,5,6,7");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	// GB_11714-1997
	// (?#ALGNAME=MOD11)(?#PARA=0,1,2,3,4,5,6,7;8){]
	@Test
	public void testGB117141997() {
		String LengthRule = "9";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.MOD11_Random(res, "0,1,2,3,4,5,6,7;8");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	// GB/T_26319-2010_1
	// (?#ALGNAME=Month)(?#PARA=2,3){]
	// (?#ALGNAME=CountryRegionCode)(?#PARA=4,5){]
	// (?#ALGNAME=First2CharsofAdminDivision)(?#PARA=6,7){]
	// (?#ALGNAME=IntFreitForwarding)(?#PARA=18,-1){]
	@Test
	public void testGBT2631920101() {
		String LengthRule = "19-35";
		String RawByteRule = "0,255,3,0,0,0,0,0,0;1,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,0,0,0,0,160,17,128,0;10,255,3,0,0,240,255,255,63;11,255,3,0,0,240,255,255,63;12,255,3,0,0,240,255,255,63;13,255,3,0,0,240,255,255,63;14,255,3,0,0,240,255,255,63;15,255,3,0,0,240,255,255,63;16,255,3,0,0,240,255,255,63;17,255,3,0,0,240,255,255,63;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.Month_Random(res, "2,3");
		res = RuleRandom.CountryRegionCode_Random(res, "4,5");
		res = RuleRandom.First2CharsofAdminDivision_Random(res, "6,7");
		res = RuleRandom.IntFreitForwarding_Random(res, "18,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	
	  // SB/T_10680-2012_5
	// (?#ALGNAME=MeatandVegetable)(?#PARA=0,1,2,3,4,5,6,7,8){]
	@Test
	public void testSBT1068020125() {
		String LengthRule = "9";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.MeatandVegetable_Random(res, "0,1,2,3,4,5,6,7,8");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	// GB/T_27766-2011_2
	// (?#ALGNAME=Letter)(?#PARA=0,1,2,3,4){]
	@Test
	public void testGBT2776620112() {
		String LengthRule = "5";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.Letter_Random(res, "0,1,2,3,4");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	// YC/T_191-2005
	// (?#ALGNAME=MonthDate)(?#PARA=2,3,4,5){]
	// (?#ALGNAME=ZeroTO24)(?#PARA=6,7){]
	// (?#ALGNAME=ZeroTO60)(?#PARA=8,9){]
	// (?#ALGNAME=ThreeByteDecimalnt)(?#PARA=10,11,12){]
	@Test
	public void testYCT1912005() {
		String LengthRule = "20";
		String RawByteRule = "0,255,3,0,0,0,0,0,0;1,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,0,0,0,0,240,0,32,0;16,255,3,0,0,0,0,0,0;17,255,3,0,0,0,0,0,0;18,255,3,0,0,0,0,0,0;19,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.MonthDate_Random(res, "2,3,4,5");
		res = RuleRandom.ZeroTO24_Random(res, "6,7");
		res = RuleRandom.ZeroTO60_Random(res, "8,9");
		res = RuleRandom.ThreeByteDecimalnt_Random(res, "10,11,12");
		
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	// LS/T_1706-2004
	// (?#ALGNAME=GrainEquipment)(?#PARA=0,1,2,3,4,5,6,7){]

	@Test
	public void testLST17062004() {
		String LengthRule = "8";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.GrainEquipment_Random(res, "0,1,2,3,4,5,6,7");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	// JB/T_5992.5-92
	// (?#ALGNAME=Machinery5)(?#PARA=0,1,2,3,4){]
	@Test
	public void testJBT5992592() {
		String LengthRule = "5";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.Machinery5_Random(res, "0,1,2,3,4");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	// GA/T_396-2002_1
	// (?#ALGNAME=ProvinceAdminCode)(?#PARA=0,1)
	// (?#ALGNAME=TwoByteDecimalnt)(?#PARA=4,5){]
	
	@Test
	public void testGAT39620021() {
		String LengthRule = "10";
		String RawByteRule = "2,0,2,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,1,0,0,0,0,0,0,0;9,1,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		
		res = RuleRandom.ProvinceAdminCode_Random(res, "0,1");
		res = RuleRandom.TwoByteDecimalnt_Random(res, "4,5");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	// LS/T_1703-2004 //
	// (?#ALGNAME=GrainsInformation)(?#PARA=0,1,2,3,4,5,6){]

	@Test
	public void testLST17032004() {
		String LengthRule = "7";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.GrainsInformation_Random(res, "0,1,2,3,4,5,6");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	// GA_658.1-2006 ]
	// (?#ALGNAME=SixByteDecimalnt)(?#PARA=8,9,10,11,12,13){]
	// (?#ALGNAME=AdminDivision)(?#PARA=0,1,2,3,4,5){
	@Test
	public void testGA65812006() {
		String LengthRule = "14";
		String RawByteRule = "6,4,0,0,0,0,0,0,0;7,30,2,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.AdminDivision_Random(res, "0,1,2,3,4,5");
		res = RuleRandom.SixByteDecimalnt_Random(res, "8,9,10,11,12,13");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	// GB/T_21379-2008_4
	// (?#ALGNAME=OneTO21)(?#PARA=16,17){]
	// (?#ALGNAME=AdminDivision)(?#PARA=0,1,2,3,4,5){]
	@Test
	public void testGBT2137920084() {
		String LengthRule = "18";
		String RawByteRule = "6,255,3,0,0,240,255,255,63;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.OneTO21_Random(res, "16,17");
		res = RuleRandom.AdminDivision_Random(res, "0,1,2,3,4,5");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	// radiation
	// (?#ALGNAME=NuclearelementNation)(?#PARA=0,1){]
	// (?#ALGNAME=Nuclearelements)(?#PARA=4,5){]
	// (?#ALGNAME=TwoByteDecimalnt)(?#PARA=9,10){]

	@Test
	public void testradiation() {
		String LengthRule = "12";
		String RawByteRule = "2,255,3,0,0,0,0,2,0;3,255,3,0,0,0,0,2,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;11,62,0,0,0,0,0,2,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.NuclearelementNation_Random(res, "0,1");
		res = RuleRandom.Nuclearelements_Random(res, "4,5");
		res = RuleRandom.TwoByteDecimalnt_Random(res, "9,10");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	/**
	 * 
	 * user:lly Date:2014-8-14
	 * 
	 */

	// GB/T_21379-2008_1
	// (?#ALGNAME=AdminDivision)(?#PARA=0,1,2,3,4,5){]
	// (?#ALGNAME=CodeHighway)(?#PARA=16,17){]

	@Test
	public void testGBT2137920081() {
		String LengthRule = "22";
		String RawByteRule = "6,255,3,0,0,240,255,255,63;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,30,0,0,0,0,0,0,0;14,30,3,0,0,0,0,0,0;15,0,0,0,0,0,4,80,56;18,30,0,0,0,0,0,0,0;19,30,0,0,0,0,0,0,0;20,6,0,0,0,0,0,0,0;21,30,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.AdminDivision_Random(res, "0,1,2,3,4,5");
		res = RuleRandom.CodeHighway_Random(res, "16,17");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	// JB/T_5992.3-92
	// (?#ALGNAME=Machinery3)(?#PARA=0,1,2,3,4){]

	@Test
	public void testJBT5992392() {
		String LengthRule = "5";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.Machinery3_Random(res, "0,1,2,3,4");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	// GB/T_19127-2003
	// (?#ALGNAME=VehicleIdenCode)(?#PARA=0,1,2,3,4,5,6,7,9,10,11,12,13,14,15,16;8){]

	@Test
	public void testGBT_191272003() {
		String LengthRule = "17";
		String RawByteRule = "0,255,3,0,0,240,239,235,63;1,255,3,0,0,240,239,235,63;2,255,3,0,0,240,239,235,63;3,255,3,0,0,240,239,235,63;4,255,3,0,0,240,239,235,63;5,255,3,0,0,240,239,235,63;6,255,3,0,0,240,239,235,63;7,255,3,0,0,240,239,235,63;9,255,3,0,0,240,239,235,31;10,255,3,0,0,240,239,235,63;11,255,3,0,0,240,239,235,63;12,255,3,0,0,240,239,235,63;13,255,3,0,0,240,239,235,63;14,255,3,0,0,240,239,235,63;15,255,3,0,0,240,239,235,63;16,255,3,0,0,240,239,235,63;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.VehicleIdenCode_Random(res,
				"0,1,2,3,4,5,6,7,9,10,11,12,13,14,15,16;8");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	// DL/T_700.2-1999_37
	// (?#ALGNAME=OneTO12No99)(?#PARA=0,1){]
	// (?#ALGNAME=TwoByteDecimalnt)(?#PARA=2,3){]
	// (?#ALGNAME=ParamCode17)(?#PARA=9,-1){]

	@Test
	public void testDLT70021999_37() {
		String LengthRule = "10,11,12,15";
		String RawByteRule = "4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.OneTO12No99_Random(res, "0,1");
		res = RuleRandom.TwoByteDecimalnt_Random(res, "2,3");
		res = RuleRandom.ParamCode17_Random(res, "9,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	// DL/T_700.2-1999_39
	// (?#ALGNAME=OneTO13No99)(?#PARA=0,1){]
	// (?#ALGNAME=TwoByteDecimalnt)(?#PARA=2,3){]
	// (?#ALGNAME=One2ThreeDigit)(?#PARA=9,-1){]

	@Test
	public void testDLT_70021999_39() {
		String LengthRule = "10-12";
		String RawByteRule = "4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.OneTO13No99_Random(res, "0,1");
		res = RuleRandom.TwoByteDecimalnt_Random(res, "2,3");
		res = RuleRandom.One2ThreeDigit_Random(res, "9,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	// VehicleNO_3
	// (?#ALGNAME=VehicleNOWJ)(?#PARA=2){]
	@Test
	public void testVehicleNOWJ() {
		String LengthRule = "8";
		String RawByteRule = "0,0,0,0,0,1,0,0,4;1,0,0,8,0,0,32,0,0;3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,43,10,48,162,40,192,8;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.VehicleNOWJ_Random(res, "2");

		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	// VehicleNO_2
	// (?#ALGNAME=VehicleNOArmy)(?#PARA=0,1){]
	// (?#ALGNAME=VehicleNOArmySuffix)(?#PARA=6,-1){]
	@Test
	public void testVehicleNO_2() {
		String LengthRule = "6-7";
		String RawByteRule = "2,255,3,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.VehicleNOArmy_Random(res, "0,1");
		res = RuleRandom.VehicleNOArmySuffix_Random(res, "6,-1");

		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	// VehicleNO_1
	// (?#ALGNAME=VehicleNONormal)(?#PARA=0,1){]
	@Test
	public void testVehicleNO_1() {
		String LengthRule = "7";
		String RawByteRule = "2,255,255,255,255,255,255,255,63;3,255,255,255,255,255,255,255,63;4,255,255,255,255,255,255,255,63;5,255,255,255,255,255,255,255,63;6,255,255,255,255,255,255,255,63;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.VehicleNONormal_Random(res, "0,1");

		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	// JB/T_5992.6-92
	// (?#ALGNAME=Machinery6)(?#PARA=0,1,2,3,4){]
	@Test
	public void testJBT5992692() {
		String LengthRule = "5";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.Machinery6_Random(res, "0,1,2,3,4");

		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	// GB/T_23560-2009
	// (?#ALGNAME=clothesclass)(?#PARA=0,-1){]
	@Test
	public void testGBT235602009() {
		String LengthRule = "2,4,6";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.clothesclass_Random(res, "0,-1");

		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	// GB/T_13774-92
	// (?#ALGNAME=Weaves355)(?#PARA=0,-1){]
	@Test
	public void testGBT1377492() {
		String LengthRule = "8,-1";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.Weaves355_Random(res, "0,-1");

		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	// DL/T_700.2-1999_52
	// (?#ALGNAME=OneTO10No99)(?#PARA=0,1){]
	// (?#ALGNAME=ParamCode)(?#PARA=6,-1){]
	@Test
	public void testDLT7002199952() {
		String LengthRule = "10,11";
		String RawByteRule = "2,255,3,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.OneTO10No99_Random(res, "0,1");
		res = RuleRandom.ParamCode_Random(res, "6,-1");

		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	// GA_407.2-2003
	// (?#ALGNAME=CountryRegionCode)(?#PARA=0,1,2)
	// {](?#ALGNAME=OneTO29)(?#PARA=4,5){]
	// (?#ALGNAME=ThreeByteDecimalnt)(?#PARA=6,7,8){]
	@Test
	public void testGA40722003() {
		String LengthRule = "10";
		String RawByteRule = "3,1,0,0,0,0,0,0,0;9,6,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.CountryRegionCode_Random(res, "0,1,2");
		res = RuleRandom.OneTO29_Random(res, "4,5");
		res = RuleRandom.ThreeByteDecimalnt_Random(res, "6,7,8");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	// YC/T_393-2011_3
	// (?#ALGNAME=FiveByteDecimalnt)(?#PARA=3,4,5,6,7){]
	//(?#ALGNAME=TwoByteSRMXSMYZ)(?#PARA=9,10){]
	@Test
	public void testYCT39320113() {
		String LengthRule = "12";
		String RawByteRule = "0,0,0,0,0,192,162,112,0;1,255,3,0,0,0,0,0,0;2,255,3,0,0,0,0,0,0;8,1,0,0,0,0,0,0,0;11,0,0,0,0,240,255,255,63;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.TwoByteSRMXSMYZ_Random(res, "9,10");
		res = RuleRandom.FiveByteDecimalnt_Random(res, "3,4,5,6,7");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	// GB/T_15657-1995_1
	// (?#ALGNAME=TCMDisease)(?#PARA=0,1,2,3,4,5){]
	@Test
	public void testGBT1565719951() {
		String LengthRule = "6";
		String RawByteRule = "0,0,0,0,0,32,0,0,0;1,0,0,0,0,0,7,34,20;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.TCMDisease_Random(res, "0,1,2,3,4,5");

		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	// GB/T_15657-1995_2
	// (?#ALGNAME=TCMFeature)(?#PARA=0,1,2,3,4,5){]
	@Test
	public void testGBT1565719952() {
		String LengthRule = "6";
		String RawByteRule = "0,0,0,0,0,0,0,0,32;1,0,0,0,0,32,128,0,54;4,255,3,0,0,240,255,255,63;5,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.TCMFeature_Random(res, "0,1,2,3,4,5");

		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	// LS/T_1708.1-2004
	// (?#ALGNAME=GainsProcess)(?#PARA=0,1,2,3,4,5,6,7){]
	@Test
	public void testLST170812004() {
		String LengthRule = "8";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.GainsProcess_Random(res, "0,1,2,3,4,5,6,7");

		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	/**
	 * 
	 * user:lly Date:2014-8-15
	 * 
	 */

	@Test
	// partdraw
	// (?#ALGNAME=Hyphen)(?#PARA=12){]
	// (?#ALGNAME=CarProductCompnent)(?#PARA=13,14,15,16){]
	// (?#ALGNAME=TwoByteDecimalnt)(?#PARA=18,19){]
	public void testpartdraw() {
		String LengthRule = "22";
		String RawByteRule = "0,0,0,0,0,240,255,255,63;1,0,0,0,0,240,255,255,63;2,224,0,0,0,0,0,0,0;3,255,255,255,255,255,255,255,63;4,255,255,255,255,255,255,255,63;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;17,255,3,0,0,0,0,0,0;20,0,0,0,0,240,237,251,7;21,254,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.Hyphen_Random(res, "12");
		res = RuleRandom.CarProductCompnent_Random(res, "13,14,15,16");
		res = RuleRandom.TwoByteDecimalnt_Random(res, "18,19");

		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// DL/T_700.2-1999_47
	// (?#ALGNAME=TwoByteDecimalnt)(?#PARA=1,2){]
	// (?#ALGNAME=TwoByteDecimalnt)(?#PARA=3,4){]
	// (?#ALGNAME=ParamCode7)(?#PARA=5,6,7,8,9,10,11){]
	public void testDLT7002199947() {
		String LengthRule = "12";
		String RawByteRule = "0,126,2,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.TwoByteDecimalnt_Random(res, "1,2");
		res = RuleRandom.TwoByteDecimalnt_Random(res, "3,4");
		res = RuleRandom.ParamCode7_Random(res, "5,6,7,8,9,10,11");

		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_430-2000_3
	// ((?#ALGNAME=CommodityName)(?#PARA=0,1,2,3,4){]\
	// (?#ALGNAME=PortTariff9)(?#PARA=5,6,7,8){]
	public void testJTT43020003() {
		String LengthRule = "14";
		String RawByteRule = "12,14,2,0,0,0,0,0,0;13,2,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.CommodityName_Random(res, "0,1,2,3,4");
		res = RuleRandom.PortTariff9_Random(res, "5,6,7,8");
		res = RuleRandom.Porttariff10_Random(res, "9,10,11");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_430-2000_9
	// (?#ALGNAME=PortTariff25)(?#PARA=0,1,2,3,4){]
	public void testJTT43020009() {
		String LengthRule = "5";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.PortTariff25_Random(res, "0,1,2,3,4");

		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// DL/T_700.2-1999_21
	// (?#ALGNAME=OneTO17)(?#PARA=1,2){]
	// (?#ALGNAME=OneTO12No99)(?#PARA=3,4){]
	public void testDLT7002199921() {
		String LengthRule = "14";
		String RawByteRule = "0,6,0,0,0,0,0,0,0;5,1,0,0,0,0,0,0,0;6,30,0,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.OneTO17_Random(res, "1,2");
		res = RuleRandom.OneTO10No99_Random(res, "3,4");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	
	// DL/T_700.2-1999_22(DL/T_700.1-1999_25)
	// (?#ALGNAME=OneTO05)(?#PARA=0,1){]
	// (?#ALGNAME=TwoByteDecimalnt)(?#PARA=2,3){]
	// (?#ALGNAME=ParamCode32)(?#PARA=4,-1){]
	public void testDLT7002199922() {
		String LengthRule = "7,11,12";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.OneTO05_Random(res, "0,1");
		res = RuleRandom.TwoByteDecimalnt_Random(res, "2,3");
		res = RuleRandom.ParamCode32_Random(res, "4,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// DL/T_700.2-1999_23(DL/T_700.1-1999_26)
	// (?#ALGNAME=OneTO09Not99)(?#PARA=0,1){]
	// (?#ALGNAME=TwoByteDecimalnt)(?#PARA=2,3){]
	// (?#ALGNAME=ParamCode31)(?#PARA=4,-1){]
	public void testDLT7002199923() {
		String LengthRule = "8-12";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);
       System.out.println(res.nSize);
		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.OneTO09Not99_Random(res, "0,1");
		res = RuleRandom.TwoByteDecimalnt_Random(res, "2,3");
		res = RuleRandom.ParamCode31_Random(res, "4,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// DL/T_700.2-1999_26(DL/T_700.1-1999_29)
	// (?#ALGNAME=TwoByteDecimalnt)(?#PARA=2,3){]
	// (?#ALGNAME=ParamCode28)(?#PARA=4,-1){]
	public void testDLT_70021999_26() {
		String LengthRule = "8,9";
		String RawByteRule = "0,1,0,0,0,0,0,0,0;1,14,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.TwoByteDecimalnt_Random(res, "2,3");
		res = RuleRandom.ParamCode28_Random(res, "4,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	//DL/T_700.2-1999_20(DL/T_700.1-1999_23)
	// (?#ALGNAME=OneTO11NO99)(?#PARA=0,1){]
	// (?#ALGNAME=TwoByteDecimalnt)(?#PARA=2,3){]
	// (?#ALGNAME=ParamCode34)(?#PARA=4,-1){]
	public void testDLT_70021999_20() {
		String LengthRule = "9-15";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}

		res = RuleRandom.TwoByteDecimalnt_Random(res, "2,3");
		res = RuleRandom.ParamCode34_Random(res, "4,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// DL/T_700.2-1999_19(DL/T_700.1-1999_22)
	// (?#ALGNAME=ThreeByteDecimalnt)(?#PARA=1,2,3){]
	// (?#ALGNAME=ParamCode35)(?#PARA=4,-1){]
	public void testDLT_70021999_19() {
		String LengthRule = "9,10";
		String RawByteRule = "0,30,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.ThreeByteDecimalnt_Random(res, "1,2,3");
		res = RuleRandom.ParamCode35_Random(res, "4,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// DL/T_700.2-1999_25(DL/T_700.1-1999_28)
	// (?#ALGNAME=TwoByteDecimalnt)(?#PARA=2,3){]]
	// (?#ALGNAME=ParamCode29)(?#PARA=4,-1){]
	public void testDLT_70021999_25() {
		String LengthRule = "7,8,9,13";
		String RawByteRule = "0,1,0,0,0,0,0,0,0;1,254,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.TwoByteDecimalnt_Random(res, "2,3");
		res = RuleRandom.ParamCode29_Random(res, "4,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// DL/T_700.2-1999_22(DL/T_700.1-1999_25)
	// (?#ALGNAME=OneTO05)(?#PARA=0,1){]
	// (?#ALGNAME=TwoByteDecimalnt)(?#PARA=2,3){]]
	// (?#ALGNAME=ParamCode32)(?#PARA=4,-1){]
	public void testDLT_70021999_22() {
		String LengthRule = "7,11,12";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.OneTO05_Random(res, "0,1");
		res = RuleRandom.TwoByteDecimalnt_Random(res, "2,3");
		res = RuleRandom.ParamCode29_Random(res, "4,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// DL/T_700.2-1999_17(DL/T_700.1-1999_20)
	// (?#ALGNAME=TwoByteDecimalnt)(?#PARA=1,2){]
	// (?#ALGNAME=TwoByteDecimalnt)(?#PARA=3,4){]
	// (?#ALGNAME=ParamCode38)(?#PARA=5,-1){]
	public void testDLT7002199917() {
		String LengthRule = "12,14,15";
		String RawByteRule = "0,14,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.TwoByteDecimalnt_Random(res, "1,2");
		res = RuleRandom.TwoByteDecimalnt_Random(res, "3,4");
		res = RuleRandom.ParamCode38_Random(res, "5,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// DL/T_700.2-1999_19(DL/T_700.1-1999_22)
	// (?#ALGNAME=ThreeByteDecimalnt)(?#PARA=1,2,3){]
	// (?#ALGNAME=ParamCode35)(?#PARA=4,-1){]
	public void testDLT7002199919() {
		String LengthRule = "9,10";
		String RawByteRule = "0,30,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.ThreeByteDecimalnt_Random(res, "1,2,3");
		res = RuleRandom.ParamCode35_Random(res, "4,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GB_18240.6-2004_1
	// (?#ALGNAME=DeviceMOD163)(?#PARA=0,1,2,3,4,5,6,7,8,9,10,11){]
	public void testGB18240620041() {
		String LengthRule = "12";
		String RawByteRule = "0,255,3,0,0,0,0,0,0;1,255,3,0,0,0,0,0,0;2,255,3,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.DeviceMOD163_Random(res, "0,1,2,3,4,5,6,7,8,9,10;11");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GB/T_24450-2009
	// (?#ALGNAME=goalsocialeconomic)(?#PARA=0,1,2,3,4,5){]
	public void testGBT244502009() {
		String LengthRule = "6";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.goalsocialeconomic_Random(res, "0,1,2,3,4,5");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// DL/T_700.3-1999
	// (?#ALGNAME=OneTO09)(?#PARA=2,3){]
	// (?#ALGNAME=OneTO42No99)(?#PARA=4,5){]
	public void testDLT70031999() {
		String LengthRule = "14";
		String RawByteRule = "0,8,0,0,0,0,0,0,0;1,254,0,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,254,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,254,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,254,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.OneTO09_Random(res, "2,3");
		res = RuleRandom.OneTO42No99_Random(res, "4,5");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GB/T_18366-2001
	// (?#ALGNAME=InternationalShipCode)(?#PARA=3,4,5,6,7,8,9){]
	public void testGBT183662001() {
		String LengthRule = "10";
		String RawByteRule = "0,0,0,0,0,0,16,0,0;1,0,0,0,0,0,0,1,0;2,0,0,0,0,0,0,4,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.InternationalShipCode_Random(res, "3,4,5,6,7,8,9");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	/**
	 * 
	 * user:lly Date:2014-8-18
	 * 
	 */

	@Test
	// JB/T_5992.4-92
	// (?#ALGNAME=Machinery4)(?#PARA=0,1,2,3,4){]
	public void testJBT5992492() {
		String LengthRule = "5";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.Machinery4_Random(res, "0,1,2,3,4");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GA_398.10-2002 (?#ALGNAME=CodesOfMakingCases)(?#PARA=3,4,5)
	// (?#ALGNAME=OneTO46)(?#PARA=6,7){]
	public void testGA398102002() {
		String LengthRule = "8";
		String RawByteRule = "0,1,0,0,0,0,0,0,0;1,40,0,0,0,0,0,0,0;2,1,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.CodesOfMakingCases_Random(res, "3,4,5");
		res = RuleRandom.OneTO46_Random(res, "6,7");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// DL/T_517-2012
	// (?#ALGNAME=ElectricPower)(?#PARA=0,1,2,3,4,5){]
	public void testDLT5172012() {
		String LengthRule = "6";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.ElectricPower_Random(res, "0,1,2,3,4,5");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GA/T_556.5-2007
	// (?#ALGNAME=AdminDivision)(?#PARA=0,1,2,3,4,5){]
	// (?#ALGNAME=FinancialCode)(?#PARA=6,7){]
	// (?#ALGNAME=FourByteDecimalnt)(?#PARA=8,9,10,11){]
	public void testGAT55652007() {
		String LengthRule = "12";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.AdminDivision_Random(res, "0,1,2,3,4,5");
		res = RuleRandom.FinancialCode_Random(res, "6,7");
		res = RuleRandom.FourByteDecimalnt_Random(res, "8,9,10,11");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// LS/T_1704.3-2004
	// (?#ALGNAME=ClassOfGrain)(?#PARA=0,1,2,3,4,5){]
	public void testClassOfGrain() {
		String LengthRule = "6";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.ClassOfGrain_Random(res, "0,1,2,3,4,5");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GA/T_615.4-2006
	// (?#ALGNAME=Borderinfo4)(?#PARA=0,1){]
	public void testBorderinfo4() {
		String LengthRule = "2";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.Borderinfo4_Random(res, "0,1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GA/T_974.48-2011
	// (?#ALGNAME=FireInfotainass)(?#PARA=0,1){]
	public void testFireInfotainass() {
		String LengthRule = "2";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.FireInfotainass_Random(res, "0,1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// WS_364.15-2011_2
	// (?#ALGNAME=WorkerHealthSupervision)(?#PARA=0,-1){]
	public void testWS3641520112() {
		String LengthRule = "1-2";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}
		}
		res = RuleRandom.WorkerHealthSupervision_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GB/T_19378-2003
	// (?#ALGNAME=PesticideFormulationCode)(?#PARA=0,-1){]
	public void testGBT193782003() {
		String LengthRule = "2-4";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.PesticideFormulationCode_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GB/T_12403-1990
	// (?#ALGNAME=OfficialPostionCode)(?#PARA=0,1,2,3){]
	public void testGBT12401990() {
		String LengthRule = "4";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.OfficialPostionCode_Random(res, "0,1,2,3");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_132-2003_58
	// (?#ALGNAME=HighwayDatabase24)(?#PARA=0,1){]
	public void testJTT132200358() {
		String LengthRule = "2";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.HighwayDatabase24_Random(res, "0,1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_132-2003_56
	// (?#ALGNAME=HighwayDatabase26)(?#PARA=0,1){]
	public void testJTT132200356() {
		String LengthRule = "2";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.HighwayDatabase26_Random(res, "0,1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_132-2003_57
	// (?#ALGNAME=HighwayDatabase25)(?#PARA=0,1){]
	public void testJTT132200357() {
		String LengthRule = "2";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.HighwayDatabase25_Random(res, "0,1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GB/T_12408-1990
	// (?#ALGNAME=SocialWork)(?#PARA=0,1){]
	public void testGBT124081990() {
		String LengthRule = "2";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.SocialWork_Random(res, "0,1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GA/T_974.50-2011
	// (?#ALGNAME=FireInfotainrate)(?#PARA=0,1){]
	public void testGAT974502011() {
		String LengthRule = "2";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.FireInfotainrate_Random(res, "0,1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GA/T_974.18-2011
	// (?#ALGNAME=Fireconstructionlicence)(?#PARA=0,1){]
	public void testGAT97418_2011() {
		String LengthRule = "2";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.Fireconstructionlicence_Random(res, "0,1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// WS_364.3-2011_1
	// (?#ALGNAME=TwobytleCode07)(?#PARA=0,1){]
	public void testWS36432011() {
		String LengthRule = "2";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.TwobytleCode07_Random(res, "0,1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_415-2000_51
	// (?#ALGNAME=RoadTransportation22)(?#PARA=0,1){]
	public void testJT415200051() {
		String LengthRule = "2";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.RoadTransportation22_Random(res, "0,1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// WS_364.3-2011_1
	// (?#ALGNAME=TwobytleCode07)(?#PARA=0,1){]
	public void testWS364320111() {
		String LengthRule = "2";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.TwobytleCode07_Random(res, "0,1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GA/T_974.18-2011
	// (?#ALGNAME=Fireconstructionlicence)(?#PARA=0,1){]
	public void testGAT974182011() {
		String LengthRule = "2";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.Fireconstructionlicence_Random(res, "0,1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// WS_364.10-2011_6
	// (?#ALGNAME=OneTO39)(?#PARA=0,1){]
	public void testWS3641020116() {
		String LengthRule = "2";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.OneTO39_Random(res, "0,1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_132-2003_70
	// (?#ALGNAME=HighwayDatabase13)(?#PARA=0,1){]
	public void testJTT132200370() {
		String LengthRule = "2";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.HighwayDatabase13_Random(res, "0,1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_132-2003_75
	// (?#ALGNAME=HighwayDatabase7)(?#PARA=0,1){]
	public void testJTT132200375() {
		String LengthRule = "2";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.HighwayDatabase7_Random(res, "0,1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// QC/T_836-2010
	// (?#ALGNAME=SpecialVehicle)(?#PARA=0,1,2){]
	public void testQCT8362010() {
		String LengthRule = "3";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.SpecialVehicle_Random(res, "0,1,2");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GA/T_760.1-760.12-2008
	// (?#ALGNAME=PublicSecutity)(?#PARA=0,1,2){]
	public void testGAT7601760122008() {
		String LengthRule = "3";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.PublicSecutity_Random(res, "0,1,2");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_415-2000_38
	// (?#ALGNAME=RoadTransportation41)(?#PARA=0,1,2){]
	public void testJT415200038() {
		String LengthRule = "3";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.RoadTransportation41_Random(res, "0,1,2");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GA_398.20-2002
	// (?#ALGNAME=DocumentEnvidence)(?#PARA=1,2,3){]
	public void testGA398202002() {
		String LengthRule = "4";
		String RawByteRule = "0,254,1,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.DocumentEnvidence_Random(res, "1,2,3");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GA/T_974.22-2011
	// (?#ALGNAME=Firelocation)(?#PARA=0,1){]
	public void testGAT974222011() {
		String LengthRule = "2";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.Firelocation_Random(res, "0,1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GA/T_852.1-2009
	// (?#ALGNAME=Recreationplace)(?#PARA=0,1,2){]
	public void testGAT85212009() {
		String LengthRule = "2";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.Recreationplace_Random(res, "0,1,2");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GA/T_974.45-2011
	// (?#ALGNAME=FireInfotrain)(?#PARA=0,1,2,3){]
	public void testGAT974452011() {
		String LengthRule = "4";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.FireInfotrain_Random(res, "0,1,2,3");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	// GB/T_25344-2010
	// (?#ALGNAME=CodeHighWayLine)(?#PARA=0,1,2,3){]
	@Test
	public void testGBT253442010() {
		String LengthRule = "4";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.CodeHighWayLine_Random(res, "0,1,2,3");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GB/T_21394-2008
	// (?#ALGNAME=TrafficInformation)(?#PARA=2,3){]
	// (?#ALGNAME=OneTO05)(?#PARA=0,1){]
	public void testGBT213942008() {
		String LengthRule = "4";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.OneTO05_Random(res, "0,1");
		res = RuleRandom.TrafficInformation_Random(res, "2,3");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GB/T_15420-2009
	// (?#ALGNAME=StevedorageChartering)(?#PARA=0,1){]
	public void testGBT154202009() {
		String LengthRule = "2";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.StevedorageChartering_Random(res, "0,1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_132-2003_74
	// (?#ALGNAME=HighwayDatabase8)(?#PARA=0,1){]
	public void testJTT132200374() {
		String LengthRule = "2";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.HighwayDatabase8_Random(res, "0,1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GA/T_974.71-2011
	// (?#ALGNAME=FireInfoBuild)(?#PARA=0,1){]
	public void testGAT974712011() {
		String LengthRule = "2";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.FireInfoBuild_Random(res, "0,1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GA/T_974.1-2011
	// (?#ALGNAME=Fireexpert)(?#PARA=0,1,2,3){]
	public void testGAT97412011() {
		String LengthRule = "4";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.Fireexpert_Random(res, "0,1,2,3");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GA/T_972-2011
	// (?#ALGNAME=Chemicalrisk)(?#PARA=0,1,2){]
	public void testGAT972011() {
		String LengthRule = "3";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.Chemicalrisk_Random(res, "0,1,2");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GB/T_28442-2012
	// (?#ALGNAME=ElectronicMap)(?#PARA=0,1,2,3){]
	public void testGBT284422012() {
		String LengthRule = "4";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.ElectronicMap_Random(res, "0,1,2,3");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_132-2003_21
	// (?#ALGNAME=HighwayDatabase66)(?#PARA=0,1){]
	public void testJTT132200321() {
		String LengthRule = "2";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.HighwayDatabase66_Random(res, "0,1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_132-2003_22
	// (?#ALGNAME=HighwayDatabase65)(?#PARA=0,1){]
	public void testJTT132200322() {
		String LengthRule = "2";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.HighwayDatabase65_Random(res, "0,1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_132-2003_26
	// (?#ALGNAME=HighwayDatabase59)(?#PARA=0,1){]
	public void testJTT132200326() {
		String LengthRule = "2";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.HighwayDatabase59_Random(res, "0,1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GA/T_974.16-2011
	// (?#ALGNAME=Firesupervisionenforcement)(?#PARA=0,1,2,3){]
	public void testGAT974162011() {
		String LengthRule = "4";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.Firesupervisionenforcement_Random(res, "0,1,2,3");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GB/T_12407-2008
	// (?#ALGNAME=PositionClass)(?#PARA=0,1,2){]
	public void testGBT124072008() {
		String LengthRule = "3";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.PositionClass_Random(res, "0,1,2");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_415-2000_47
	// (?#ALGNAME=RoadTransportation32)(?#PARA=0,1){]
	public void testJTT415200047() {
		String LengthRule = "2";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.RoadTransportation32_Random(res, "0,1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GB/T_17297-1998
	// (?#ALGNAME=Climatic)(?#PARA=0,1,2){]
	public void testGBT172971998() {
		String LengthRule = "3";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.Climatic_Random(res, "0,1,2");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GA/T_974.29-2011
	// (?#ALGNAME=Firealarm)(?#PARA=0,1){]
	public void testGAT974292011() {
		String LengthRule = "2";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.Firealarm_Random(res, "0,1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GB_918.2-1989
	// (?#ALGNAME=NonMotorVehicle)(?#PARA=0,1,2){]
	public void testGB91821989() {
		String LengthRule = "3";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.NonMotorVehicle_Random(res, "0,1,2");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// WS_364.16-2011_5
	// (?#ALGNAME=CommunicationCode)(?#PARA=0,-1){]
	public void testWS3641620115() {
		String LengthRule = "1-2";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.CommunicationCode_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// WS_364.16-2011_2
	// (?#ALGNAME=OneTO72)(?#PARA=0,1){]
	public void testWS3641620112() {
		String LengthRule = "2";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.OneTO72_Random(res, "0,1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GB/T_7635.2-2002
	// (?#ALGNAME=UntransportableProduct)(?#PARA=0,-1){]
	public void testGBT763522002() {
		String LengthRule = "1-5";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	/**
	 * 
	 * user:lly Date:2014-8-19
	 * 
	 */
	@Test
	// WS_364.16-2011_1
	// (?#ALGNAME=OneTO46)(?#PARA=0,1){]
	public void testWS3641620111() {
		String LengthRule = "2";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		 res=RuleRandom.OneTO46_Random(res, "0,1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// WS_364.16-2011_3
	//
	public void testWS3641620113() {
		String LengthRule = "1";
		String RawByteRule = "0,62,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// WS_364.16-2011_4
	// (?#ALGNAME=OneTO08)(?#PARA=0,1){]
	public void testWS3641620114() {
		String LengthRule = "2";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res=RuleRandom.OneTO08_Random(res, "0,1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_415-2000_36
	public void testJTT415200036() {
		String LengthRule = "1";
		String RawByteRule = "0,62,2,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GB/T_8561-2001
	public void testGBT85612001() {
		String LengthRule = "3";
		String RawByteRule = "0,127,2,0,0,0,0,0,0;1,255,3,0,0,0,0,0,0;2,63,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// CJ/T_3045-1995
	// (?#ALGNAME=TwoByteDecimalnt)(?#PARA=2,3){]
	public void testCJT304519951() {
		String LengthRule = "4";
		String RawByteRule = "0,30,2,0,0,0,0,0,0;1,124,2,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res=RuleRandom.TwoByteDecimalnt_Random(res, "2,3");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_415-2000_49
	public void testJTT415200049() {
		String LengthRule = "1";
		String RawByteRule = "0,0,0,0,0,240,239,122,36;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_415-2000_48
	public void testJTT415200048() {
		String LengthRule = "1";
		String RawByteRule = "0,14,2,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_415-2000_41
	public void testJTT415200041() {
		String LengthRule = "1";
		String RawByteRule = "0,126,2,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_415-2000_43
	public void testJTT415200043() {
		String LengthRule = "1";
		String RawByteRule = "0,30,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JTT415200042
	public void testJTT415200042() {
		String LengthRule = "1";
		String RawByteRule = "0,30,2,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_415-2000_45
	public void testJTT415200045() {
		String LengthRule = "1";
		String RawByteRule = "0,30,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_415-2000_44
	public void testJTT415200044() {
		String LengthRule = "1";
		String RawByteRule = "0,14,2,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JTT_415-2000_46
	public void testJTT415200046() {
		String LengthRule = "1";
		String RawByteRule = "0,30,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GA/T_556.2-2005
	public void testGAT55622005() {
		String LengthRule = "2";
		String RawByteRule = "0,0,0,0,0,240,1,0,0;1,0,0,0,0,96,128,197,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_132-2003_27
	public void testJTT132200327() {
		String LengthRule = "1";
		String RawByteRule = "0,6,2,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_132-2003_25
	public void testJTT132200325() {
		String LengthRule = "1";
		String RawByteRule = "0,6,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_132-2003_20
	public void testJTT132200320() {
		String LengthRule = "1";
		String RawByteRule = "0,6,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GA_658.2-2006
	public void testGA65822006() {
		String LengthRule = "1";
		String RawByteRule = "0,30,2,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GB/T_16986-2009
	// (?#ALGNAME=BarCodeForCommodity)(?#PARA=0,-1){]
	public void testGBT169862009() {
		String LengthRule = "2-4";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.BarCodeForCommodity_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GA/T_974.56-2011
	public void testGAT974562011() {
		String LengthRule = "3";
		String RawByteRule = "0,126,2,0,0,0,0,0,0;1,1,2,0,0,0,0,0,0;2,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GAT974472011
	public void testGAT974472011() {
		String LengthRule = "4";
		String RawByteRule = "0,1,2,0,0,0,0,0,0;1,254,3,0,0,0,0,0,0;2,31,2,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_132-2003_79
	public void testJTT132200379() {
		String LengthRule = "1";
		String RawByteRule = "0,63,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GBT226112003
	public void testGBT226112003() {
		String LengthRule = "1";
		String RawByteRule = "0,7,2,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// WS_364.13-2011_3
	// (?#ALGNAME=OneTO08)(?#PARA=0,1){]
	public void testWS3641320113() {
		String LengthRule = "2";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res=RuleRandom.OneTO08_Random(res, "0,1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_415-2000_30
	public void testJTT415200030() {
		String LengthRule = "1";
		String RawByteRule = "0,6,2,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_415-2000_32
	// (?#ALGNAME=OneTO08)(?#PARA=0,1){]
	public void testJTT415200032() {
		String LengthRule = "2";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res=RuleRandom.OneTO08_Random(res, "0,1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_415-2000_33
	public void testJTT415200033() {
		String LengthRule = "1";
		String RawByteRule = "0,30,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_415-2000_34
	public void testJTT415200034() {
		String LengthRule = "1";
		String RawByteRule = "0,14,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_415-2000_35
	public void testJTT415200035() {
		String LengthRule = "1";
		String RawByteRule = "0,30,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_415-2000_37
	public void testJTT415200037() {
		String LengthRule = "1";
		String RawByteRule = "0,30,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_415-2000_39
	public void testJTT415200039() {
		String LengthRule = "1";
		String RawByteRule = "0,30,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GA_690.4-2007
	public void testGA69042007() {
		String LengthRule = "1";
		String RawByteRule = "0,254,2,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_132-2003_71
	public void testJTT132200371() {
		String LengthRule = "1";
		String RawByteRule = "0,30,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_132-2003_73
	public void testJTT132200373() {
		String LengthRule = "1";
		String RawByteRule = "0,14,2,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_132-2003_72
	public void testJTT132200372() {
		String LengthRule = "1";
		String RawByteRule = "0,254,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// WS_364.6-2011_8
	// (?#ALGNAME=OneTO09)(?#PARA=0,1){]
	public void testWS364620118() {
		String LengthRule = "2";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res=RuleRandom.OneTO09_Random(res, "0,1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// WS_364.10-2011_2
	//
	public void testWS3641020112() {
		String LengthRule = "1";
		String RawByteRule = "0,30,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GB/T_23833-2009_8
	// (?#ALGNAME=PrefixofRetailCommodityNumber)(?#PARA=0,1,2){]
	// (?#ALGNAME=GraiSerialNo)(?#PARA=10,20,-1){]
	public void testGBT2383320098() {
		String LengthRule = "11-30";
		String RawByteRule = "3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res=RuleRandom.PrefixofRetailCommodityNumber_Random(res, "0,1,2");
		res=RuleRandom.GraiSerialNo_Random(res, "10,20,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// WS_364.11-2011_3
	//
	public void testWS3641120113() {
		String LengthRule = "1";
		String RawByteRule = "0,62,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// WS_364.11-2011_2
	//
	public void testWS3641120112() {
		String LengthRule = "1";
		String RawByteRule = "0,14,2,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// WS_364.11-2011_5
	//
	public void testWS3641120115() {
		String LengthRule = "1";
		String RawByteRule = "0,30,2,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// WS_364.11-2011_4
	// (?#ALGNAME=OneTO10)(?#PARA=0,1){]
	public void testWS3641120114() {
		String LengthRule = "2";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.OneTO10_Random(res, "0,1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// WS_364.11-2011_7
	//
	public void testWS3641120117() {
		String LengthRule = "2";
		String RawByteRule = "0,4,0,0,0,0,0,0,0;1,30,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// WS_364.13-2011_2
	// (?#ALGNAME=OneTO07)(?#PARA=0,1){]
	public void testWS3641320112() {
		String LengthRule = "2";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res=RuleRandom.OneTO07_Random(res, "0,1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// WS3641320111
	// (?#ALGNAME=OneTO09)(?#PARA=0,1){]
	public void testWS3641320111() {
		String LengthRule = "2";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		 res=RuleRandom.OneTO09_Random(res, "0,1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_415-2000_56
	// (?#ALGNAME=RoadTransportation5)(?#PARA=0,1,2){]
	public void testJTT415200056() {
		String LengthRule = "3";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res=RuleRandom.RoadTransportation5_Random(res, "0,1,2");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_415-2000_54
	//
	public void testJTT415200054() {
		String LengthRule = "2";
		String RawByteRule = "0,8,0,0,0,0,0,0,0;1,15,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_415-2000_55
	//
	public void testJTT415200055() {
		String LengthRule = "1";
		String RawByteRule = "0,6,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_415-2000_53
	//
	public void testJTT415200053() {
		String LengthRule = "1";
		String RawByteRule = "0,62,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_415-2000_50
	//
	public void testJTT415200050() {
		String LengthRule = "1";
		String RawByteRule = "0,14,2,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_415-2000_58
	//
	public void testJTT415200058() {
		String LengthRule = "2";
		String RawByteRule = "0,10,0,0,0,0,0,0,0;1,1,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_415-2000_59
	//
	public void testJTT415200059() {
		String LengthRule = "1";
		String RawByteRule = "0,126,2,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GB_16737-2004
	//
	public void testGB167372004() {
		String LengthRule = "3";
		String RawByteRule = "0,255,3,0,0,240,239,235,63;1,255,3,0,0,240,255,255,63;2,255,3,0,0,240,255,255,63;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GA_658.4-2006
	// (?#ALGNAME=OneTO08)(?#PARA=0,1){]
	public void testGA65842006() {
		String LengthRule = "2";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		 res=RuleRandom.OneTO08_Random(res, "0,1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_132-2003_53
	//
	public void testJTT132200353() {
		String LengthRule = "1";
		String RawByteRule = "0,127,2,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GB_918.1-1989
	//
	public void testGB91811989() {
		String LengthRule = "3";
		String RawByteRule = "0,126,0,0,0,0,0,0,0;1,254,0,0,0,0,0,0,0;2,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_132-2003_51
	//
	public void testJTT1322003_51() {
		String LengthRule = "1";
		String RawByteRule = "0,62,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_132-2003_50
	//
	public void testJTT13200350() {
		String LengthRule = "1";
		String RawByteRule = "0,255,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_132-2003_55
	//
	public void testJTT13200355() {
		String LengthRule = "1";
		String RawByteRule = "0,254,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_132-2003_54
	//
	public void testJTT13200354() {
		String LengthRule = "1";
		String RawByteRule = "0,254,2,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_132-2003_59
	//
	public void testJTT13200359() {
		String LengthRule = "1";
		String RawByteRule = "0,30,2,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// SB/T_10680-2012_2
	//
	public void testSBT1068020122() {
		String LengthRule = "13";
		String RawByteRule = "0,255,3,0,0,0,0,0,0;1,255,3,0,0,0,0,0,0;2,255,3,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GA_690.2-2007
	// (?#ALGNAME=ExplosiveCivilian)(?#PARA=0,1){]
	public void testGA69022007() {
		String LengthRule = "2";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
	 res=RuleRandom.ExplosiveCivilian_Random(res, "0,1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// WS_364.6-2011_2
	public void testWS364620112() {
		String LengthRule = "1";
		String RawByteRule = "0,30,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// WS_364.6-2011_3
	// (?#ALGNAME=OneTO13)(?#PARA=0,1){]
	public void testWS364620113() {
		String LengthRule = "2";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res=RuleRandom.OneTO13_Random(res, "0,1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// WS_364.6-2011_1
	// (?#ALGNAME=OneTO15)(?#PARA=0,1){]
	public void testWS364620111() {
		String LengthRule = "2";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
	res=RuleRandom.OneTO15_Random(res, "0,1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// WS_364.6-2011_6
	//
	public void testWS364620116() {
		String LengthRule = "1";
		String RawByteRule = "0,126,2,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// WS_364.6-2011_4
	//
	public void testWS364620114() {
		String LengthRule = "1";
		String RawByteRule = "0,30,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// WS364.6-2011_5
	// (?#ALGNAME=OneTO09)(?#PARA=0,1){]
	public void testW364620115() {
		String LengthRule = "2";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		 res=RuleRandom.OneTO09_Random(res, "0,1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// WS_364.15-2011_5
	//
	public void testWS3641520115() {
		String LengthRule = "1";
		String RawByteRule = "0,126,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// WS_364.15-2011_4
	//
	public void testWS3641520114() {
		String LengthRule = "1";
		String RawByteRule = "0,14,2,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// WS_364.6-2011_9
	// (?#ALGNAME=OneTO11)(?#PARA=0,1){]
	public void testWS364620119() {
		String LengthRule = "2";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		/// res=RuleRandom.;
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// YC/T_210.4-2006
	//
	public void testYCT21042006() {
		String LengthRule = "1";
		String RawByteRule = "0,14,2,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// WS_364.15-2011_3
	// (?#ALGNAME=OneTO09)(?#PARA=0,1){]
	public void testWS3641520113() {
		String LengthRule = "2";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res=RuleRandom.OneTO09_Random(res, "0,1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// WS_364.14-2011_3
	// (?#ALGNAME=OneTO07)(?#PARA=0,1){]
	public void testWS3641420113() {
		String LengthRule = "2";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res=RuleRandom.OneTO07_Random(res, "0,1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// WS_364.10-2011_15
	//
	public void testWS36410201115() {
		String LengthRule = "1";
		String RawByteRule = "0,62,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GB/T_6565-2009
	//
	public void testGBT65652009() {
		String LengthRule = "3";
		String RawByteRule = "0,254,3,0,0,0,0,0,24;1,254,3,0,0,0,0,0,0;2,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// WS_364.11-2011_15
	//
	public void testWS36411201115() {
		String LengthRule = "1";
		String RawByteRule = "0,30,2,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GA_658.8-2006
	//
	public void testGA65882006() {
		String LengthRule = "1";
		String RawByteRule = "0,14,2,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_132-2003_40
	//
	public void testJTT132200340() {
		String LengthRule = "1";
		String RawByteRule = "0,14,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GA/T_615.2-2006
	//
	public void testGAT61522006() {
		String LengthRule = "1";
		String RawByteRule = "0,14,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// Ecode_1
	public void testEcode_1() {
		String LengthRule = "-1";
		String RawByteRule = "0,1,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// WS_364.2-2011_4
	// (?#ALGNAME=First2CharsofAdminDivision)(?#PARA=1,2){]
	public void testWS364220114() {
		String LengthRule = "10";
		String RawByteRule = "0,0,0,0,0,240,255,255,63;3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res=RuleRandom.First2CharsofAdminDivision_Random(res, "1,2");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// WS_364.2-2011_2
	// (?#ALGNAME=AdminDivision)(?#PARA=0,1,2,3,4,5){]
	public void testWS364220112() {
		String LengthRule = "18";
		String RawByteRule = "6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;16,255,3,0,0,0,0,0,0;17,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.AdminDivision_Random(res, "0,1,2,3,4,5");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// WS_364.2-2011_3
	// (?#ALGNAME=AdminDivision)(?#PARA=0,1,2,3,4,5){]
	// (?#ALGNAME=MonthDate)(?#PARA=10,11,12,13){]
	// (?#ALGNAME=MOD112)(?#PARA=0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16;17){]
	public void testWS364220113() {
		String LengthRule = "18";
		String RawByteRule = "6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;16,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.AdminDivision_Random(res, "0,1,2,3,4,5");
		res = RuleRandom.MonthDate_Random(res, "10,11,12,13");
		res = RuleRandom.MOD112_Random(res, "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16;17");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// WS_364.2-2011_1
	// (?#ALGNAME=AdminDivision)(?#PARA=0,1,2,3,4,5){]
	public void testWS364220111() {
		String LengthRule = "17";
		String RawByteRule = "6,15,0,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;16,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.AdminDivision_Random(res, "0,1,2,3,4,5");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_307.2-1998
	public void testJTT30721998() {
		String LengthRule = "11";
		String RawByteRule = "0,0,0,0,0,0,4,0,0;1,14,0,0,0,0,0,0,0;2,7,0,0,0,0,0,0,0;3,254,3,0,0,0,0,0,0;4,0,0,0,0,0,128,0,0;5,7,0,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,1,2,0,0,0,0,0,0;8,1,2,0,0,0,0,0,0;9,1,2,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.UntransportableProduct_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GA_658.9-2006
	// (?#ALGNAME=AdminDivision)(?#PARA=0,1,2,3,4,5){]
	// (?#ALGNAME=MonthDate)(?#PARA=12,13,14,15){]
	public void testGA65892006() {
		String LengthRule = "20";
		String RawByteRule = "6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;16,255,3,0,0,0,0,0,0;17,255,3,0,0,0,0,0,0;18,255,3,0,0,0,0,0,0;19,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		 res=RuleRandom.AdminDivision_Random(res, "0,1,2,3,4,5");
		 res=RuleRandom.MonthDate_Random(res, "12,13,14,15");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GB/T_2260-2007
	// (?#ALGNAME=AdminDivision)(?#PARA=0,1,2,3,4,5){]
	public void testGBT22602007() {
		String LengthRule = "6";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.AdminDivision_Random(res, "0,1,2,3,4,5");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// importedauto
	public void testimportedauto() {
		String LengthRule = "14";
		String RawByteRule = "0,255,255,255,255,255,255,255,63;1,255,255,255,255,255,255,255,63;2,255,255,255,255,255,255,255,63;3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,0,252,255,255,255,255,255,63;10,0,252,255,255,255,255,255,63;11,255,255,255,255,255,255,255,63;12,255,255,255,255,255,255,255,63;13,255,255,255,255,255,255,255,63;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GA_557.7-2005
	// (?#ALGNAME=AdminDivision)(?#PARA=0,1,2,3,4,5){]
	public void testGA55772005() {
		String LengthRule = "10";
		String RawByteRule = "6,2,0,0,0,0,0,0,0;7,1,0,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.AdminDivision_Random(res, "0,1,2,3,4,5");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GB/T_21379-2008_3
	// (?#ALGNAME=AdminDivision)(?#PARA=0,1,2,3,4,5){]
	public void testGBT2137920083() {
		String LengthRule = "21";
		String RawByteRule = "6,255,3,0,0,240,255,255,63;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,0,0,0,0,240,1,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;16,255,3,0,0,0,0,0,0;17,254,3,0,0,0,0,0,0;18,254,3,0,0,0,0,0,0;19,254,0,0,0,0,0,0,0;20,30,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.AdminDivision_Random(res, "0,1,2,3,4,5");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GB/T_21379-2008_2
	// (?#ALGNAME=AdminDivision)(?#PARA=0,1,2,3,4,5){]
	public void testGBT2137920082() {
		String LengthRule = "16";
		String RawByteRule = "6,255,3,0,0,240,255,255,63;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,126,2,0,0,0,0,0,0;14,62,0,0,0,0,0,0,0;15,30,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.AdminDivision_Random(res, "0,1,2,3,4,5");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GA_689-2007_1
	// (?#ALGNAME=AdminDivision)(?#PARA=0,1,2,3,4,5){]
	// (?#ALGNAME=Month)(?#PARA=9,10){]
	public void testGA68920071() {
		String LengthRule = "15";
		String RawByteRule = "6,1,0,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.AdminDivision_Random(res, "0,1,2,3,4,5");
		res = RuleRandom.Month_Random(res, "9,10");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GA_689-2007_3
	// (?#ALGNAME=MonthDate)(?#PARA=10,11,12,13){]
	public void testGA68920073() {
		String LengthRule = "20";
		String RawByteRule = "0,1,0,0,0,0,0,0,0;1,255,3,0,0,0,0,0,0;2,255,3,0,0,0,0,0,0;3,255,255,219,254,243,239,251,31;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,0,0,0,0,0,128,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;16,255,3,0,0,0,0,0,0;17,255,3,0,0,0,0,0,0;18,255,3,0,0,0,0,0,0;19,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.MonthDate_Random(res, "10,11,12,13");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GA_689-2007_2
	// (?#ALGNAME=AdminDivision)(?#PARA=0,1,2,3,4,5){]
	// (?#ALGNAME=Month)(?#PARA=9,10){]
	public void testGA68920072() {
		String LengthRule = "15";
		String RawByteRule = "6,2,0,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.AdminDivision_Random(res, "0,1,2,3,4,5");
		res = RuleRandom.Month_Random(res, "9,10");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GA_689-2007_4
	// (?#ALGNAME=MonthDate)(?#PARA=10,11,12,13){]
	public void testGA68920074() {
		String LengthRule = "17";
		String RawByteRule = "0,1,0,0,0,0,0,0,0;1,255,3,0,0,0,0,0,0;2,255,3,0,0,0,0,0,0;3,255,255,219,254,243,239,251,31;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,0,0,0,0,0,128,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;16,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res=RuleRandom.MonthDate_Random(res, "10,11,12,13");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GA_689-2007_6
	// (?#ALGNAME=MonthDate)(?#PARA=10,11,12,13){]
	public void testGA68920076() {
		String LengthRule = "18";
		String RawByteRule = "0,255,255,219,254,243,239,251,31;1,255,3,0,0,0,0,0,0;2,255,3,0,0,0,0,0,0;3,255,255,219,254,243,239,251,31;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,2,0,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;16,255,3,0,0,0,0,0,0;17,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res=RuleRandom.MonthDate_Random(res, "10,11,12,13");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	
	// GB/T_23730.2-2009
	// (?#ALGNAME=MOD3736)(?#PARA=4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19;20){]
	// (?#ALGNAME=MOD3736)(?#PARA=4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,21,22,23,24,25,26,27,28;29){]
	public void testGBT2373022009() {
		String LengthRule = "30";
		String RawByteRule = "0,0,0,0,0,0,16,0,0;1,0,0,0,0,0,0,64,0;2,0,0,0,0,16,0,0,0;3,0,0,0,0,0,0,2,0;4,255,3,0,0,240,3,0,0;5,255,3,0,0,240,3,0,0;6,255,3,0,0,240,3,0,0;7,255,3,0,0,240,3,0,0;8,255,3,0,0,240,3,0,0;9,255,3,0,0,240,3,0,0;10,255,3,0,0,240,3,0,0;11,255,3,0,0,240,3,0,0;12,255,3,0,0,240,3,0,0;13,255,3,0,0,240,3,0,0;14,255,3,0,0,240,3,0,0;15,255,3,0,0,240,3,0,0;16,255,3,0,0,240,3,0,0;17,255,3,0,0,240,3,0,0;18,255,3,0,0,240,3,0,0;19,255,3,0,0,240,3,0,0;21,255,3,0,0,240,3,0,0;22,255,3,0,0,240,3,0,0;23,255,3,0,0,240,3,0,0;24,255,3,0,0,240,3,0,0;25,255,3,0,0,240,3,0,0;26,255,3,0,0,240,3,0,0;27,255,3,0,0,240,3,0,0;28,255,3,0,0,240,3,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		 res=RuleRandom.MOD3736_Random(res, "4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19;20");
		 RuleRandom.DisplayRandomCode(res.FunctionResult);
		 res=RuleRandom.MOD3736_Random(res, "4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,21,22,23,24,25,26,27,28;29");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// greenfood
	// (?#ALGNAME=AdminDivision)(?#PARA=2,3,4,5,6,7){]
	// (?#ALGNAME=TwoByteDecimalnt)(?#PARA=12,13){]
	public void testgreenfood() {
		String LengthRule = "14";
		String RawByteRule = "0,0,0,0,0,0,4,0,0;1,0,0,0,0,0,2,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.AdminDivision_Random(res, "2,3,4,5,6,7");
		res = RuleRandom.TwoByteDecimalnt_Random(res, "12,13");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GA/T_396-2002_2
	// (?#ALGNAME=First4CharsofAdminDivisionforCiga)(?#PARA=0,1,2,3){]
	// (?#ALGNAME=TwoByteDecimalnt)(?#PARA=6,7){]
	public void testGAT39620022() {
		String LengthRule = "10";
		String RawByteRule = "4,0,2,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.First4CharsofAdminDivisionforCiga_Random(res, "0,1,2,3");
		res = RuleRandom.TwoByteDecimalnt_Random(res, "6,7");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GA/T_396-2002_3
	// (?#ALGNAME=AdminDivision)(?#PARA=0,1,2,3,4,5){]
	// (?#ALGNAME=TwoByteDecimalnt)(?#PARA=8,9){]
	public void testGAT39620023() {
		String LengthRule = "10";
		String RawByteRule = "6,0,2,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.AdminDivision_Random(res, "0,1,2,3,4,5");
		res = RuleRandom.TwoByteDecimalnt_Random(res, "8,9");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GB/T_25606-2010/ISO_10261:2002
	//
	public void testGBT256062010ISO102612002() {
		String LengthRule = "17";
		String RawByteRule = "0,255,3,0,0,240,255,255,63;1,255,3,0,0,240,255,255,63;2,255,3,0,0,240,255,255,63;3,255,3,0,0,240,255,255,63;4,255,3,0,0,240,255,255,63;5,255,3,0,0,240,255,255,63;6,255,3,0,0,240,255,255,63;7,255,3,0,0,240,255,255,63;8,0,0,0,0,240,255,255,63;9,255,3,0,0,240,255,255,63;10,255,3,0,0,240,255,255,63;11,255,3,0,0,240,255,255,63;12,255,3,0,0,240,255,255,63;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;16,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// res=RuleRandom.AdminDivision_Random(res, "0,1,2,3,4,5");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// SB/T_10680-2012_1
	// (?#ALGNAME=AdminDivision)(?#PARA=0,1,2,3,4,5){]
	public void testSBT1068020121() {
		String LengthRule = "9";
		String RawByteRule = "6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.AdminDivision_Random(res, "0,1,2,3,4,5");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// SB/T_10680-2012_3
	//
	public void testSBT1068020123() {
		String LengthRule = "9";
		String RawByteRule = "0,255,3,0,0,0,0,0,0;1,255,3,0,0,0,0,0,0;2,255,3,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// SB/T_10680-2012_4
	//
	public void testSBT1068020124() {
		String LengthRule = "20";
		String RawByteRule = "0,255,3,0,0,0,0,0,0;1,255,3,0,0,0,0,0,0;2,255,3,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;16,255,3,0,0,0,0,0,0;17,255,3,0,0,0,0,0,0;18,255,3,0,0,0,0,0,0;19,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GB_18937-2003
	// (?#ALGNAME=CheckCodeForCommodityCode)(?#PARA=0,1,2,3,4,5,6,7,8,9,10,11,12;13){]
	public void testGB189372003() {
		String LengthRule = "14";
		String RawByteRule = "0,255,3,0,0,0,0,0,0;1,255,3,0,0,0,0,0,0;2,255,3,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.CheckCodeForCommodityCode_Random(res, "0,1,2,3,4,5,6,7,8,9,10,11,12;13");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GB/T_27766-2011_3
	// (?#ALGNAME=Letter)(?#PARA=0,1,2,3,4){]
	public void testGBT2776620113() {
		String LengthRule = "5";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.Letter_Random(res, "0,1,2,3,4");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GA/T_556.9-2007
	// (?#ALGNAME=AdminDivision)(?#PARA=0,1,2,3,4,5){]
	// (?#ALGNAME=FinancialCode)(?#PARA=6,7){]
	public void testGAT55692007() {
		String LengthRule = "16";
		String RawByteRule = "8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,254,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		res = RuleRandom.AdminDivision_Random(res, "0,1,2,3,4,5");
		res = RuleRandom.FinancialCode_Random(res, "6,7");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// HJ_524-2009
	public void testHJ5242009() {
		String LengthRule = "6";
		String RawByteRule = "0,0,4,0,0,0,0,0,0;1,255,3,0,0,0,0,0,0;2,254,3,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,254,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}

		}
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// YC/T_258.1-2008
	// (?#ALGNAME=CigaOrgCode)(?#PARA=0,1){]
	// (?#ALGNAME=First4CharsofAdminDivisionforCiga)(?#PARA=2,3,4,5){]
	// (?#ALGNAME=CigaDepCode)(?#PARA=8,9){]
	// (?#ALGNAME=CigaDepCode)(?#PARA=10,11){]
	public void testYCT25812008() {
		String LengthRule = "12";
		String RawByteRule = "6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}
		}
		res = RuleRandom.CigaOrgCode_Random(res, "0,1");
		res = RuleRandom.First4CharsofAdminDivisionforCiga_Random(res, "2,3,4,5");
		res = RuleRandom.CigaDepCode_Random(res, "8,9");
		res = RuleRandom.CigaDepCode_Random(res, "10,11");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// YC/T_391-2011_2
	// (?#ALGNAME=TwoByteDecimalnt)(?#PARA=6,7){]
	// (?#ALGNAME=TwoByteDecimalnt)(?#PARA=10,11){]
	public void testYCT39120112() {
		String LengthRule = "12";
		String RawByteRule = "0,4,0,0,0,0,0,0,0;1,64,0,0,0,0,0,0,0;2,0,0,0,0,240,239,251,63;3,0,0,0,0,240,239,251,63;4,0,0,0,0,240,239,251,63;5,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}
		}
		res = RuleRandom.TwoByteDecimalnt_Random(res, "6,7");
		res = RuleRandom.TwoByteDecimalnt_Random(res, "10,11");
		
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GB/T_27766-2011_1
	public void testGBT2776620111() {
		String LengthRule = "13";
		String RawByteRule = "0,3,0,0,0,0,0,0,0;1,3,0,0,0,0,0,0,0;2,3,0,0,0,0,0,0,0;3,3,0,0,0,0,0,0,0;4,3,0,0,0,0,0,0,0;5,3,0,0,0,0,0,0,0;6,3,0,0,0,0,0,0,0;7,3,0,0,0,0,0,0,0;8,3,0,0,0,0,0,0,0;9,3,0,0,0,0,0,0,0;10,3,0,0,0,0,0,0,0;11,3,0,0,0,0,0,0,0;12,3,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}
		}
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// YC/T_391-2011_1
	// (?#ALGNAME=TwoByteDecimalnt)(?#PARA=6,7){]
	// (?#ALGNAME=TwoByteDecimalnt)(?#PARA=8,9){]
	// (?#ALGNAME=TwoByteDecimalnt)(?#PARA=10,11){]
	public void testYCT39120111() {
		String LengthRule = "12";
		String RawByteRule = "0,2,0,0,0,0,0,0,0;1,64,0,0,0,0,0,0,0;2,0,0,0,0,240,239,251,63;3,0,0,0,0,240,239,251,63;4,0,0,0,0,240,239,251,63;5,254,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}
		}
		res = RuleRandom.TwoByteDecimalnt_Random(res, "6,7");
		res = RuleRandom.TwoByteDecimalnt_Random(res, "8,9");
		
		res = RuleRandom.TwoByteDecimalnt_Random(res, "10,11");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GB/T_18995-2003
	//
	public void testGBT1899520033() {
		String LengthRule = "12";
		String RawByteRule = "0,0,0,0,0,240,255,255,63;1,0,0,0,0,240,255,255,63;2,255,3,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}
		}
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// YC/T_414-2011_2
	// (?#ALGNAME=CigaSubClassCode)(?#PARA=4,5,6){]
	// (?#ALGNAME=MonthDate)(?#PARA=15,16,17,18){]
	public void testYCT41420112() {
		String LengthRule = "22";
		String RawByteRule = "0,0,0,0,0,0,0,0,16;1,0,0,0,0,64,0,0,0;2,0,0,0,0,64,0,0,0;3,0,0,0,0,32,0,0,0;7,3,0,0,0,0,0,0,0;8,3,0,0,0,0,0,0,0;9,3,0,0,0,0,0,0,0;10,3,0,0,0,0,0,0,0;11,3,0,0,0,0,0,0,0;12,3,0,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;19,255,3,0,0,0,0,0,0;20,255,3,0,0,0,0,0,0;21,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}
		}
		res = RuleRandom.CigaSubClassCode_Random(res, "4,5,6");
		res = RuleRandom.MonthDate_Random(res, "15,16,17,18");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_132-2003_23
	// (?#ALGNAME=AdminDivision)(?#PARA=11,12,13,14,15,16){]
	public void testJTT132200323() {
		String LengthRule = "17";
		String RawByteRule = "0,0,0,0,0,0,4,0,0;1,0,0,0,0,0,0,64,56;2,255,3,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,0,0,0,0,0,0,32,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}
		}
		res = RuleRandom.AdminDivision_Random(res, "11,12,13,14,15,16");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// medicaldevice
	//
	public void testmedicaldevice() {
		String LengthRule = "4";
		String RawByteRule = "0,64,0,0,0,0,0,0,0;1,0,1,0,0,0,0,0,0;2,255,0,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}
		}

		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GB/T_19882.31-2007
	//
	public void testGBT19882312007() {
		String LengthRule = "17";
		String RawByteRule = "0,3,0,0,0,0,0,0,0;1,255,3,0,0,0,0,0,0;2,7,0,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;4,254,3,0,0,0,0,0,0;5,7,0,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,7,0,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,7,0,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,7,0,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;16,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}
		}

		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GB/T_22970-2010_2
	// (?#ALGNAME=TextileFabricNameCode)(?#PARA=0,1,2,3,4){]
	public void testGBT2297020102() {
		String LengthRule = "5";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}
		}
		res = RuleRandom.TextileFabricNameCode_Random(res, "0,1,2,3,4");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GB/T_22970-2010_3
	// (?#ALGNAME=PropertiesMain)(?#PARA=0,1){]
	// (?#ALGNAME=PropertiesFiberCharacteristics)(?#PARA=2,3){]
	// (?#ALGNAME=OneTO09)(?#PARA=4,5){]
	// (?#ALGNAME=PropertiesMix)(?#PARA=6,7){]
	// (?#ALGNAME=PropertiesFabric)(?#PARA=8,9){]
	// (?#ALGNAME=PropertiesDyeingandFinishing)(?#PARA=10,11){]
	public void testGBT2297020103() {
		String LengthRule = "12";
		String RawByteRule = "";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}
		}
		res = RuleRandom.PropertiesMain_Random(res, "0,1");
		res = RuleRandom.PropertiesFiberCharacteristics_Random(res, "2,3");
		res = RuleRandom.OneTO09_Random(res, "4,5");
		res = RuleRandom.PropertiesMix_Random(res, "6,7");
		res = RuleRandom.PropertiesFabric_Random(res, "8,9");
		res = RuleRandom.PropertiesDyeingandFinishing_Random(res, "10,11");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GB/T_20529.2-2010_6
	// (?#ALGNAME=TextileFabricNameCode)(?#PARA=0,1,2,3,4){]
	public void testGBT20529220106() {
		String LengthRule = "8";
		String RawByteRule = "0,0,0,0,0,0,0,1,0;1,0,0,0,0,240,15,0,0;2,254,3,0,0,0,0,0,0;3,254,3,0,0,0,0,0,0;4,254,3,0,0,0,0,0,0;5,254,3,0,0,0,0,0,0;6,254,3,0,0,0,0,0,0;7,254,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}
		}
		res = RuleRandom.TextileFabricNameCode_Random(res, "0,1,2,3,4");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GB/T_23705-2009_2
	// (?#ALGNAME=AdminDivision)(?#PARA=0,1,2,3,4,5){]
	public void testGBT2370520092() {
		String LengthRule = "13";
		String RawByteRule = "6,15,0,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,3,0,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}
		}
		res = RuleRandom.AdminDivision_Random(res, "0,1,2,3,4,5");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_415-2000_60
	//
	public void testJTT415200060() {
		String LengthRule = "10";
		String RawByteRule = "0,255,3,0,0,240,255,255,63;1,255,3,0,0,240,255,255,63;2,255,3,0,0,240,255,255,63;3,255,3,0,0,240,255,255,63;4,255,3,0,0,240,255,255,63;5,255,3,0,0,240,255,255,63;6,255,3,0,0,240,255,255,63;7,255,3,0,0,240,255,255,63;9,255,3,0,0,240,255,255,63;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}
		}
		res.FunctionResult.put(8, "i");

		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// WB/T_1043-2012
	//
	public void testWBT10432012() {
		String LengthRule = "15";
		String RawByteRule = "0,0,0,0,0,0,8,0,0;1,0,0,0,0,240,255,255,63;2,0,0,0,0,240,255,255,63;3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}
		}

		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GBT176161998
	//
	public void testGBT176161998() {
		String LengthRule = "6";
		String RawByteRule = "0,0,0,0,0,112,171,217,5;1,255,3,0,0,0,0,0,0;2,255,3,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}
		}

		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GB/T_19632-2005_2
	// (?#ALGNAME=First2CharsofAdminDivision)(?#PARA=0,1){]
	public void testGBT1963220052() {
		String LengthRule = "6";
		String RawByteRule = "2,14,0,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}
		}
		res = RuleRandom.First2CharsofAdminDivision_Random(res, "0,1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GA/T_380-2012
	// (?#ALGNAME=AdminDivision)(?#PARA=0,1,2,3,4,5){]
	public void testGAT3802012() {
		String LengthRule = "12";
		String RawByteRule = "6,255,3,0,0,240,239,251,63;7,255,3,0,0,240,239,249,63;8,255,3,0,0,240,239,249,63;9,255,3,0,0,240,239,249,63;10,255,3,0,0,240,239,249,63;11,255,3,0,0,240,239,249,63;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}
		}
		res = RuleRandom.AdminDivision_Random(res, "0,1,2,3,4,5");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_132-2003_46
	// (?#ALGNAME=AdminDivision)(?#PARA=9,10,11,12,13,14){]
	public void testJTT132200346() {
		String LengthRule = "15";
		String RawByteRule = "0,0,0,0,0,0,4,0,0;1,0,0,0,0,0,0,64,56;2,255,3,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,0,0,0,0,0,0,0,1;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}
		}
		res = RuleRandom.AdminDivision_Random(res, "9,10,11,12,13,14");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_132-2003_47
	// (?#ALGNAME=AdminDivision)(?#PARA=10,11,12,13,14,15){]
	public void testJTT132200347() {
		String LengthRule = "16";
		String RawByteRule = "0,0,0,0,0,0,4,0,0;1,0,0,0,0,0,0,64,56;2,255,3,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,0,0,0,0,0,8,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}
		}
		res = RuleRandom.AdminDivision_Random(res, "10,11,12,13,14,15");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// productdraw
	// (?#ALGNAME=Hyphen)(?#PARA=12){]
	// (?#ALGNAME=Hyphen)(?#PARA=16){]
	// (?#ALGNAME=TwoByteDecimalnt)(?#PARA=17,18){]
	public void testproductdraw() {
		String LengthRule = "21";
		String RawByteRule = "0,0,0,0,0,240,255,255,63;1,0,0,0,0,240,255,255,63;2,224,0,0,0,0,0,0,0;3,255,255,255,255,255,255,255,63;4,255,255,255,255,255,255,255,63;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;13,1,0,0,0,0,0,0,0;14,1,0,0,0,0,0,0,0;15,254,3,0,0,0,0,0,0;19,0,0,0,0,240,237,251,7;20,254,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}
		}
		res = RuleRandom.Hyphen_Random(res, "12");
		res = RuleRandom.Hyphen_Random(res, "16");
		res = RuleRandom.TwoByteDecimalnt_Random(res, "17,18");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GA_658.5-2006
	// (?#ALGNAME=AdminDivision)(?#PARA=0,1,2,3,4,5){]
	public void testGA65852006() {
		String LengthRule = "10";
		String RawByteRule = "6,8,0,0,0,0,0,0,0;7,1,0,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}
		}
		res = RuleRandom.AdminDivision_Random(res, "0,1,2,3,4,5");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GB_18240.6-2004_2
	// (?#ALGNAME=DeviceMOD163)(?#PARA=0,1,2,3,4,5,6,7,8,9,10,11){]
	public void testGB18240620042() {
		String LengthRule = "12";
		String RawByteRule = "0,255,3,0,0,0,0,0,0;1,255,3,0,0,0,0,0,0;2,255,3,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}
		}
		res = RuleRandom.DeviceMOD163_Random(res, "0,1,2,3,4,5,6,7,8,9,10;11");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// JT/T_307.1-1997
	//
	public void testJTT30711997() {
		String LengthRule = "6";
		String RawByteRule = "0,0,0,0,0,0,0,64,0;1,14,0,0,0,0,0,0,0;2,255,3,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}
		}
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GA_300.2-2001
	// (?#ALGNAME=AdminDivision)(?#PARA=0,1,2,3,4,5){]
	public void testGA30022001() {
		String LengthRule = "9";
		String RawByteRule = "6,254,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,1,0,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}
		}
		res = RuleRandom.AdminDivision_Random(res, "0,1,2,3,4,5");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// YC/T_209.2-2008
	// (?#ALGNAME=PrefixofRetailCommodityNumber)(?#PARA=1,2,3){]
	// (?#ALGNAME=MonthDate)(?#PARA=14,15,16,17){]
	public void testYCT20922008() {
		String LengthRule = "30";
		String RawByteRule = "0,0,0,0,0,240,255,255,63;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;18,255,3,0,0,0,0,0,0;19,255,3,0,0,0,0,0,0;20,255,3,0,0,0,0,0,0;21,255,3,0,0,0,0,0,0;22,255,3,0,0,0,0,0,0;23,255,3,0,0,0,0,0,0;24,255,3,0,0,0,0,0,0;25,255,3,0,0,0,0,0,0;26,255,3,0,0,0,0,0,0;27,255,3,0,0,0,0,0,0;28,255,3,0,0,0,0,0,0;29,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}
		}
		res = RuleRandom.PrefixofRetailCommodityNumber_Random(res, "1,2,3");
		res = RuleRandom.MonthDate_Random(res, "14,15,16,17");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GB_11708-89
	// (?#ALGNAME=First2CharsofAdminDivision)(?#PARA=9,10){]
	public void testGB1170889() {
		String LengthRule = "11";
		String RawByteRule = "0,0,0,0,0,0,4,64,56;1,255,3,0,0,0,0,0,0;2,255,3,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;4,0,0,0,0,0,128,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}
		}
		res = RuleRandom.First2CharsofAdminDivision_Random(res, "9,10");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GA_557.10-2005
	// 2,255,3,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;
	// (?#ALGNAME=OneTO09)(?#PARA=0,1){]
	public void testGA557102005() {
		String LengthRule = "6";
		String RawByteRule = "2,255,3,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}
		}
		res = RuleRandom.OneTO09_Random(res, "0,1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GA_408.3-2006
	// 2,255,3,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;
	//
	public void testGA40832006() {
		String LengthRule = "12";
		String RawByteRule = "0,255,3,0,0,0,0,0,0;1,255,3,0,0,0,0,0,0;2,255,3,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}
		}
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GA_408.3-2006
	// vin
	public void testvin() {
		String LengthRule = "17";
		String RawByteRule = "0,255,3,0,0,240,239,235,63;1,126,0,0,0,0,247,65,4;2,255,3,0,0,240,239,235,63;3,255,255,255,255,255,255,255,63;4,255,255,255,255,255,255,255,63;5,255,255,255,255,255,255,255,63;6,255,255,255,255,255,255,255,63;7,255,255,255,255,255,255,255,63;8,255,3,0,0,0,0,0,8;9,254,3,0,0,240,239,203,30;10,255,3,0,0,240,239,235,63;11,255,3,0,0,240,239,235,63;12,255,3,0,0,240,239,235,63;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;16,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}
		}
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// YC/T_393-2011_2
	// (?#ALGNAME=FiveByteDecimalnt)(?#PARA=3,4,5,6,7){]
	public void testYCT3920112() {
		String LengthRule = "12";
		String RawByteRule = "0,0,0,0,0,192,162,112,0;1,255,3,0,0,0,0,0,0;2,255,3,0,0,0,0,0,0;8,0,0,0,0,192,162,112,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,0,0,0,0,240,255,255,63;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}
		}
		res = RuleRandom.FiveByteDecimalnt_Random(res, "3,4,5,6,7");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GB/T_20529.2-2010_1
	//
	public void testGBT20529220101() {
		String LengthRule = "8";
		String RawByteRule = "0,0,0,0,0,240,3,1,0;1,0,0,0,0,240,255,255,63;2,254,3,0,0,0,0,0,0;3,254,3,0,0,0,0,0,0;4,254,3,0,0,0,0,0,0;5,254,3,0,0,0,0,0,0;6,254,3,0,0,0,0,0,0;7,254,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}
		}
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GB/T_20529.2-2010_2
	//
	public void testGBT20529220102() {
		String LengthRule = "8";
		String RawByteRule = "0,0,0,0,0,240,3,0,0;1,0,0,0,0,240,255,255,63;2,254,3,0,0,0,0,0,0;3,254,3,0,0,0,0,0,0;4,254,3,0,0,0,0,0,0;5,254,3,0,0,0,0,0,0;6,254,3,0,0,0,0,0,0;7,254,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}
		}
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GB/T_20529.2-2010_3
	//
	public void testGBT20529220103() {
		String LengthRule = "8";
		String RawByteRule = "0,0,0,0,0,240,0,0,0;1,0,0,0,0,0,0,0,8;2,255,3,0,0,0,0,0,0;3,254,3,0,0,0,0,0,0;4,254,3,0,0,0,0,0,0;5,254,3,0,0,0,0,0,0;6,254,3,0,0,0,0,0,0;7,254,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}
		}
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GB/T_20529.2-2010_4
	// (?#ALGNAME=TwoByteDecimalnt)(?#PARA=2,3){]
	// (?#ALGNAME=TwoByteDecimalnt)(?#PARA=4,5){]
	public void testGBT20529220104() {
		String LengthRule = "8";
		String RawByteRule = "0,0,0,0,0,0,1,0,0;1,0,0,0,0,0,0,0,8;6,254,3,0,0,0,0,0,0;7,254,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}
		}
		res = RuleRandom.TwoByteDecimalnt_Random(res, "2,3");
		res = RuleRandom.TwoByteDecimalnt_Random(res, "4,5");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GB/T_20529.2-2010_5
	public void testGBT20529220105() {
		String LengthRule = "8";
		String RawByteRule = "0,0,0,0,0,0,2,0,0;1,0,0,0,0,0,0,0,8;2,0,0,0,0,240,255,255,0;3,254,3,0,0,0,0,0,0;4,254,3,0,0,0,0,0,0;5,254,3,0,0,0,0,0,0;6,254,3,0,0,0,0,0,0;7,254,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}
		}
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GA/T_491-2004
	public void testGAT4912004() {
		String LengthRule = "7";
		String RawByteRule = "0,0,0,0,0,112,0,0,0;1,255,3,0,0,0,0,0,0;2,255,3,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}
		}
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// YC/T_390-2011
	// (?#ALGNAME=FiveByteDecimalnt)(?#PARA=6,7,8,9,10){]
	public void testYC3902011() {
		String LengthRule = "12";
		String RawByteRule = "0,0,0,0,0,240,239,251,63;1,0,0,0,0,240,239,251,63;2,0,0,0,0,240,239,251,63;3,0,0,0,0,0,4,0,0;4,63,0,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;11,0,0,0,0,240,255,255,63;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}
		}
		res = RuleRandom.FiveByteDecimalnt_Random(res, "6,7,8,9,10");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GB/T_19251-2003_EANUCC-13
	// (?#ALGNAME=PrefixofRetailCommodityNumber)(?#PARA=1,2,3){]
	// (?#ALGNAME=CheckCodeForCommodityCode)
	// (?#PARA=0,1,2,3,4,5,6,7,8,9,10,11,12;13){]
	public void testGBT192512003EANUCC13() {
		String LengthRule = "14";
		String RawByteRule = "0,1,0,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;";
		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}
		}
		res = RuleRandom.PrefixofRetailCommodityNumber_Random(res, "1,2,3");
		res = RuleRandom.CheckCodeForCommodityCode_Random(res, "0,1,2,3,4,5,6,7,8,9,10,11,12;13");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// EPC_256_2
	//
	public void testEPC_256_2() {
		String LengthRule = "64";
		String RawByteRule = "0,1,0,0,0,0,0,0,0;1,0,15,0,0,48,0,0,0;2,255,255,0,0,240,3,0,0;3,255,255,0,0,240,3,0,0;4,255,255,0,0,240,3,0,0;5,255,255,0,0,240,3,0,0;6,255,255,0,0,240,3,0,0;7,255,255,0,0,240,3,0,0;8,255,255,0,0,240,3,0,0;9,255,255,0,0,240,3,0,0;10,255,255,0,0,240,3,0,0;11,255,255,0,0,240,3,0,0;12,255,255,0,0,240,3,0,0;13,255,255,0,0,240,3,0,0;14,255,255,0,0,240,3,0,0;15,255,255,0,0,240,3,0,0;16,255,255,0,0,240,3,0,0;17,255,255,0,0,240,3,0,0;18,255,255,0,0,240,3,0,0;19,255,255,0,0,240,3,0,0;20,255,255,0,0,240,3,0,0;21,255,255,0,0,240,3,0,0;22,255,255,0,0,240,3,0,0;23,255,255,0,0,240,3,0,0;24,255,255,0,0,240,3,0,0;25,255,255,0,0,240,3,0,0;26,255,255,0,0,240,3,0,0;27,255,255,0,0,240,3,0,0;28,255,255,0,0,240,3,0,0;29,255,255,0,0,240,3,0,0;30,255,255,0,0,240,3,0,0;31,255,255,0,0,240,3,0,0;32,255,255,0,0,240,3,0,0;33,255,255,0,0,240,3,0,0;34,255,255,0,0,240,3,0,0;35,255,255,0,0,240,3,0,0;36,255,255,0,0,240,3,0,0;37,255,255,0,0,240,3,0,0;38,255,255,0,0,240,3,0,0;39,255,255,0,0,240,3,0,0;40,255,255,0,0,240,3,0,0;41,255,255,0,0,240,3,0,0;42,255,255,0,0,240,3,0,0;43,255,255,0,0,240,3,0,0;44,255,255,0,0,240,3,0,0;45,255,255,0,0,240,3,0,0;46,255,255,0,0,240,3,0,0;47,255,255,0,0,240,3,0,0;48,255,255,0,0,240,3,0,0;49,255,255,0,0,240,3,0,0;50,255,255,0,0,240,3,0,0;51,255,255,0,0,240,3,0,0;52,255,255,0,0,240,3,0,0;53,255,255,0,0,240,3,0,0;54,255,255,0,0,240,3,0,0;55,255,255,0,0,240,3,0,0;56,255,255,0,0,240,3,0,0;57,255,255,0,0,240,3,0,0;58,255,255,0,0,240,3,0,0;59,255,255,0,0,240,3,0,0;60,255,255,0,0,240,3,0,0;61,255,255,0,0,240,3,0,0;62,255,255,0,0,240,3,0,0;63,255,255,0,0,240,3,0,0;";

		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}
		}
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// EPC_256_1
	//
	public void testEPC_256_1() {
		String LengthRule = "64";
		String RawByteRule = "0,1,0,0,0,0,0,0,0;1,1,0,0,0,0,0,0,0;2,1,0,0,0,0,0,0,0;3,1,0,0,0,0,0,0,0;4,2,0,0,0,0,0,0,0;5,1,0,0,0,0,0,0,0;6,3,0,0,0,0,0,0,0;7,3,0,0,0,0,0,0,0;8,3,0,0,0,0,0,0,0;9,3,0,0,0,0,0,0,0;10,3,0,0,0,0,0,0,0;11,3,0,0,0,0,0,0,0;12,3,0,0,0,0,0,0,0;13,3,0,0,0,0,0,0,0;14,3,0,0,0,0,0,0,0;15,3,0,0,0,0,0,0,0;16,3,0,0,0,0,0,0,0;17,3,0,0,0,0,0,0,0;18,3,0,0,0,0,0,0,0;19,3,0,0,0,0,0,0,0;20,3,0,0,0,0,0,0,0;21,3,0,0,0,0,0,0,0;22,3,0,0,0,0,0,0,0;23,3,0,0,0,0,0,0,0;24,3,0,0,0,0,0,0,0;25,3,0,0,0,0,0,0,0;26,3,0,0,0,0,0,0,0;27,3,0,0,0,0,0,0,0;28,3,0,0,0,0,0,0,0;29,3,0,0,0,0,0,0,0;30,3,0,0,0,0,0,0,0;31,3,0,0,0,0,0,0,0;32,3,0,0,0,0,0,0,0;33,3,0,0,0,0,0,0,0;34,3,0,0,0,0,0,0,0;35,3,0,0,0,0,0,0,0;36,3,0,0,0,0,0,0,0;37,3,0,0,0,0,0,0,0;38,3,0,0,0,0,0,0,0;39,3,0,0,0,0,0,0,0;40,3,0,0,0,0,0,0,0;41,3,0,0,0,0,0,0,0;42,3,0,0,0,0,0,0,0;43,3,0,0,0,0,0,0,0;44,3,0,0,0,0,0,0,0;45,3,0,0,0,0,0,0,0;46,3,0,0,0,0,0,0,0;47,3,0,0,0,0,0,0,0;48,3,0,0,0,0,0,0,0;49,3,0,0,0,0,0,0,0;50,3,0,0,0,0,0,0,0;51,3,0,0,0,0,0,0,0;52,3,0,0,0,0,0,0,0;53,3,0,0,0,0,0,0,0;54,3,0,0,0,0,0,0,0;55,3,0,0,0,0,0,0,0;56,3,0,0,0,0,0,0,0;57,3,0,0,0,0,0,0,0;58,3,0,0,0,0,0,0,0;59,3,0,0,0,0,0,0,0;60,3,0,0,0,0,0,0,0;61,3,0,0,0,0,0,0,0;62,3,0,0,0,0,0,0,0;63,3,0,0,0,0,0,0,0;64,3,0,0,0,0,0,0,0;65,3,0,0,0,0,0,0,0;66,3,0,0,0,0,0,0,0;67,3,0,0,0,0,0,0,0;68,3,0,0,0,0,0,0,0;69,3,0,0,0,0,0,0,0;70,3,0,0,0,0,0,0,0;71,3,0,0,0,0,0,0,0;72,3,0,0,0,0,0,0,0;73,3,0,0,0,0,0,0,0;74,3,0,0,0,0,0,0,0;75,3,0,0,0,0,0,0,0;76,3,0,0,0,0,0,0,0;77,3,0,0,0,0,0,0,0;78,3,0,0,0,0,0,0,0;79,3,0,0,0,0,0,0,0;80,3,0,0,0,0,0,0,0;81,3,0,0,0,0,0,0,0;82,3,0,0,0,0,0,0,0;83,3,0,0,0,0,0,0,0;84,3,0,0,0,0,0,0,0;85,3,0,0,0,0,0,0,0;86,3,0,0,0,0,0,0,0;87,3,0,0,0,0,0,0,0;88,3,0,0,0,0,0,0,0;89,3,0,0,0,0,0,0,0;90,3,0,0,0,0,0,0,0;91,3,0,0,0,0,0,0,0;92,3,0,0,0,0,0,0,0;93,3,0,0,0,0,0,0,0;94,3,0,0,0,0,0,0,0;95,3,0,0,0,0,0,0,0;96,3,0,0,0,0,0,0,0;97,3,0,0,0,0,0,0,0;98,3,0,0,0,0,0,0,0;99,3,0,0,0,0,0,0,0;100,3,0,0,0,0,0,0,0;101,3,0,0,0,0,0,0,0;102,3,0,0,0,0,0,0,0;103,3,0,0,0,0,0,0,0;104,3,0,0,0,0,0,0,0;105,3,0,0,0,0,0,0,0;106,3,0,0,0,0,0,0,0;107,3,0,0,0,0,0,0,0;108,3,0,0,0,0,0,0,0;109,3,0,0,0,0,0,0,0;110,3,0,0,0,0,0,0,0;111,3,0,0,0,0,0,0,0;112,3,0,0,0,0,0,0,0;113,3,0,0,0,0,0,0,0;114,3,0,0,0,0,0,0,0;115,3,0,0,0,0,0,0,0;116,3,0,0,0,0,0,0,0;117,3,0,0,0,0,0,0,0;118,3,0,0,0,0,0,0,0;119,3,0,0,0,0,0,0,0;120,3,0,0,0,0,0,0,0;121,3,0,0,0,0,0,0,0;122,3,0,0,0,0,0,0,0;123,3,0,0,0,0,0,0,0;124,3,0,0,0,0,0,0,0;125,3,0,0,0,0,0,0,0;126,3,0,0,0,0,0,0,0;127,3,0,0,0,0,0,0,0;128,3,0,0,0,0,0,0,0;129,3,0,0,0,0,0,0,0;130,3,0,0,0,0,0,0,0;131,3,0,0,0,0,0,0,0;132,3,0,0,0,0,0,0,0;133,3,0,0,0,0,0,0,0;134,3,0,0,0,0,0,0,0;135,3,0,0,0,0,0,0,0;136,3,0,0,0,0,0,0,0;137,3,0,0,0,0,0,0,0;138,3,0,0,0,0,0,0,0;139,3,0,0,0,0,0,0,0;140,3,0,0,0,0,0,0,0;141,3,0,0,0,0,0,0,0;142,3,0,0,0,0,0,0,0;143,3,0,0,0,0,0,0,0;144,3,0,0,0,0,0,0,0;145,3,0,0,0,0,0,0,0;146,3,0,0,0,0,0,0,0;147,3,0,0,0,0,0,0,0;148,3,0,0,0,0,0,0,0;149,3,0,0,0,0,0,0,0;150,3,0,0,0,0,0,0,0;151,3,0,0,0,0,0,0,0;152,3,0,0,0,0,0,0,0;153,3,0,0,0,0,0,0,0;154,3,0,0,0,0,0,0,0;155,3,0,0,0,0,0,0,0;156,3,0,0,0,0,0,0,0;157,3,0,0,0,0,0,0,0;158,3,0,0,0,0,0,0,0;159,3,0,0,0,0,0,0,0;160,3,0,0,0,0,0,0,0;161,3,0,0,0,0,0,0,0;162,3,0,0,0,0,0,0,0;163,3,0,0,0,0,0,0,0;164,3,0,0,0,0,0,0,0;165,3,0,0,0,0,0,0,0;166,3,0,0,0,0,0,0,0;167,3,0,0,0,0,0,0,0;168,3,0,0,0,0,0,0,0;169,3,0,0,0,0,0,0,0;170,3,0,0,0,0,0,0,0;171,3,0,0,0,0,0,0,0;172,3,0,0,0,0,0,0,0;173,3,0,0,0,0,0,0,0;174,3,0,0,0,0,0,0,0;175,3,0,0,0,0,0,0,0;176,3,0,0,0,0,0,0,0;177,3,0,0,0,0,0,0,0;178,3,0,0,0,0,0,0,0;179,3,0,0,0,0,0,0,0;180,3,0,0,0,0,0,0,0;181,3,0,0,0,0,0,0,0;182,3,0,0,0,0,0,0,0;183,3,0,0,0,0,0,0,0;184,3,0,0,0,0,0,0,0;185,3,0,0,0,0,0,0,0;186,3,0,0,0,0,0,0,0;187,3,0,0,0,0,0,0,0;188,3,0,0,0,0,0,0,0;189,3,0,0,0,0,0,0,0;190,3,0,0,0,0,0,0,0;191,3,0,0,0,0,0,0,0;192,3,0,0,0,0,0,0,0;193,3,0,0,0,0,0,0,0;194,3,0,0,0,0,0,0,0;195,3,0,0,0,0,0,0,0;196,3,0,0,0,0,0,0,0;197,3,0,0,0,0,0,0,0;198,3,0,0,0,0,0,0,0;199,3,0,0,0,0,0,0,0;200,3,0,0,0,0,0,0,0;201,3,0,0,0,0,0,0,0;202,3,0,0,0,0,0,0,0;203,3,0,0,0,0,0,0,0;204,3,0,0,0,0,0,0,0;205,3,0,0,0,0,0,0,0;206,3,0,0,0,0,0,0,0;207,3,0,0,0,0,0,0,0;208,3,0,0,0,0,0,0,0;209,3,0,0,0,0,0,0,0;210,3,0,0,0,0,0,0,0;211,3,0,0,0,0,0,0,0;212,3,0,0,0,0,0,0,0;213,3,0,0,0,0,0,0,0;214,3,0,0,0,0,0,0,0;215,3,0,0,0,0,0,0,0;216,3,0,0,0,0,0,0,0;217,3,0,0,0,0,0,0,0;218,3,0,0,0,0,0,0,0;219,3,0,0,0,0,0,0,0;220,3,0,0,0,0,0,0,0;221,3,0,0,0,0,0,0,0;222,3,0,0,0,0,0,0,0;223,3,0,0,0,0,0,0,0;224,3,0,0,0,0,0,0,0;225,3,0,0,0,0,0,0,0;226,3,0,0,0,0,0,0,0;227,3,0,0,0,0,0,0,0;228,3,0,0,0,0,0,0,0;229,3,0,0,0,0,0,0,0;230,3,0,0,0,0,0,0,0;231,3,0,0,0,0,0,0,0;232,3,0,0,0,0,0,0,0;233,3,0,0,0,0,0,0,0;234,3,0,0,0,0,0,0,0;235,3,0,0,0,0,0,0,0;236,3,0,0,0,0,0,0,0;237,3,0,0,0,0,0,0,0;238,3,0,0,0,0,0,0,0;239,3,0,0,0,0,0,0,0;240,3,0,0,0,0,0,0,0;241,3,0,0,0,0,0,0,0;242,3,0,0,0,0,0,0,0;243,3,0,0,0,0,0,0,0;244,3,0,0,0,0,0,0,0;245,3,0,0,0,0,0,0,0;246,3,0,0,0,0,0,0,0;247,3,0,0,0,0,0,0,0;248,3,0,0,0,0,0,0,0;249,3,0,0,0,0,0,0,0;250,3,0,0,0,0,0,0,0;251,3,0,0,0,0,0,0,0;252,3,0,0,0,0,0,0,0;253,3,0,0,0,0,0,0,0;254,3,0,0,0,0,0,0,0;255,3,0,0,0,0,0,0,0;";

		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}
		}
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GB/T_19251-2003_EANUCC-14
	// (?#ALGNAME=PrefixofRetailCommodityNumber)(?#PARA=0,1,2){]
	public void testGBT192512003EANUCC14() {
		String LengthRule = "14";
		String RawByteRule = "3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;";

		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}
		}
		res = RuleRandom.PrefixofRetailCommodityNumber_Random(res, "0,1,2");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// Mcode_2
	public void testMcode_2() {
		String LengthRule = "16";
		String RawByteRule = "0,255,255,0,0,240,3,0,0;1,255,255,0,0,240,3,0,0;2,255,255,0,0,240,3,0,0;3,2,0,0,0,0,0,0,0;4,255,255,0,0,240,3,0,0;5,255,255,0,0,240,3,0,0;6,255,255,0,0,240,3,0,0;7,255,255,0,0,240,3,0,0;8,255,255,0,0,240,3,0,0;9,255,255,0,0,240,3,0,0;10,255,255,0,0,240,3,0,0;11,255,255,0,0,240,3,0,0;12,255,255,0,0,240,3,0,0;13,255,255,0,0,240,3,0,0;14,255,255,0,0,240,3,0,0;15,255,255,0,0,240,3,0,0;";

		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}
		}
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// Mcode_1
	public void testMcode_1() {
		String LengthRule = "12";
		String RawByteRule = "0,255,255,0,0,240,3,0,0;1,255,255,0,0,240,3,0,0;2,255,255,0,0,240,3,0,0;3,1,0,0,0,0,0,0,0;4,255,255,0,0,240,3,0,0;5,255,255,0,0,240,3,0,0;6,255,255,0,0,240,3,0,0;7,255,255,0,0,240,3,0,0;8,255,255,0,0,240,3,0,0;9,255,255,0,0,240,3,0,0;10,255,255,0,0,240,3,0,0;11,255,255,0,0,240,3,0,0;";

		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}
		}
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// GB/T_23832-2009
	// (?#ALGNAME=PrefixofRetailCommodityNumber)(?#PARA=0,1,2){]
	// (?#ALGNAME=CheckCodeForCommodityCode)(?#PARA=0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16;17){]
	public void testGBT238322009() {
		String LengthRule = "18";
		String RawByteRule = "3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;15,255,3,0,0,0,0,0,0;16,255,3,0,0,0,0,0,0;";

		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}
		}
		res = RuleRandom.PrefixofRetailCommodityNumber_Random(res, "0,1,2");
		res = RuleRandom.CheckCodeForCommodityCode_Random(res, "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16;17");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// Mcode_4
	public void testMcode_4() {
		String LengthRule = "24";
		String RawByteRule = "0,255,255,0,0,240,3,0,0;1,255,255,0,0,240,3,0,0;2,255,255,0,0,240,3,0,0;3,112,0,0,0,0,0,0,0;4,255,255,0,0,240,3,0,0;5,255,255,0,0,240,3,0,0;6,255,255,0,0,240,3,0,0;7,255,255,0,0,240,3,0,0;8,255,255,0,0,240,3,0,0;9,255,255,0,0,240,3,0,0;10,255,255,0,0,240,3,0,0;11,255,255,0,0,240,3,0,0;12,255,255,0,0,240,3,0,0;13,255,255,0,0,240,3,0,0;14,255,255,0,0,240,3,0,0;15,255,255,0,0,240,3,0,0;16,255,255,0,0,240,3,0,0;17,255,255,0,0,240,3,0,0;18,255,255,0,0,240,3,0,0;19,255,255,0,0,240,3,0,0;20,255,255,0,0,240,3,0,0;21,255,255,0,0,240,3,0,0;22,255,255,0,0,240,3,0,0;23,255,255,0,0,240,3,0,0;";

		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}
		}
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// Ecode_2
	public void testEcode2() {
		String LengthRule = "13";
		String RawByteRule = "0,255,255,0,0,240,3,0,0;1,255,255,0,0,240,3,0,0;2,255,255,0,0,240,3,0,0;3,2,0,0,0,0,0,0,0;4,255,255,0,0,240,3,0,0;5,255,255,0,0,240,3,0,0;6,255,255,0,0,240,3,0,0;7,255,255,0,0,240,3,0,0;8,255,255,0,0,240,3,0,0;9,255,255,0,0,240,3,0,0;10,255,255,0,0,240,3,0,0;11,255,255,0,0,240,3,0,0;12,255,255,0,0,240,3,0,0;13,255,255,0,0,240,3,0,0;14,255,255,0,0,240,3,0,0;15,255,255,0,0,240,3,0,0;";

		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}
		}
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// Ecode_5
	public void testEcode5() {
		String LengthRule = "-1";
		String RawByteRule = "0,16,0,0,0,0,0,0,0;";

		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}
		}
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}

	@Test
	// Ecode_4
	public void testEcode4() {
		String LengthRule = "32";
		String RawByteRule = "0,4,0,0,0,0,0,0,0;1,255,255,0,0,240,3,0,0;2,255,255,0,0,240,3,0,0;3,255,255,0,0,240,3,0,0;4,255,255,0,0,240,3,0,0;5,255,255,0,0,240,3,0,0;6,255,255,0,0,240,3,0,0;7,255,255,0,0,240,3,0,0;8,255,255,0,0,240,3,0,0;9,255,255,0,0,240,3,0,0;10,255,255,0,0,240,3,0,0;11,255,255,0,0,240,3,0,0;12,255,255,0,0,240,3,0,0;13,255,255,0,0,240,3,0,0;14,255,255,0,0,240,3,0,0;15,255,255,0,0,240,3,0,0;16,255,255,0,0,240,3,0,0;17,255,255,0,0,240,3,0,0;18,255,255,0,0,240,3,0,0;19,255,255,0,0,240,3,0,0;20,255,255,0,0,240,3,0,0;21,255,255,0,0,240,3,0,0;22,255,255,0,0,240,3,0,0;23,255,255,0,0,240,3,0,0;24,255,255,0,0,240,3,0,0;25,255,255,0,0,240,3,0,0;26,255,255,0,0,240,3,0,0;27,255,255,0,0,240,3,0,0;28,255,255,0,0,240,3,0,0;29,255,255,0,0,240,3,0,0;30,255,255,0,0,240,3,0,0;31,255,255,0,0,240,3,0,0;";

		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}
		}
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
	@Test
	//GB/T_17295-2008
	//(?#ALGNAME=MeasureUnit)(?#PARA=0,-1){]
	public void testGBT_172952008() {
		String LengthRule = "2,3";
		String RawByteRule = "";

		FunctionResult res = new FunctionResult();
		res = RuleRandom.IoTIDLength_Random(LengthRule);

		// 将数据库中原始若干字节规则分别调用IoTIDByte_Random
		String[] Byterules = {};
		if (!Strings.isNullOrEmpty(RawByteRule)) {
			Byterules = RawByteRule.split(";");
			// 改为foreach循环
			for (String ByteRule : Byterules) {
				res = RuleRandom.IoTIDByte_Random(res, ByteRule);
			}
		}
		res = RuleRandom.MeasureUnit_Random(res, "0,-1");
		// 此处使用DisplayRandomCode()的版本二
		RuleRandom.DisplayRandomCode(res.FunctionResult);
	}
		
}
