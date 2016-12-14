package cn.niot.randomization;

import cn.niot.dao.RecoDao;

//import org.apache.commons.lang3.StringUtils;
import cn.niot.util.FunctionResult;
import cn.niot.util.RecoUtil;
import cn.niot.util.RecoUtilRandom;
import cn.niot.dao.RecoDaoRandom;
import cn.niot.service.*;
//import com.google.common.base.Strings;

import java.util.HashMap;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.math.BigInteger;

//import org.apache.commons.lang3.StringUtils;

//import com.google.common.base.Strings;

import java.math.BigInteger;
import java.util.*;
import java.util.regex.*;

//import com.mysql.jdbc.StringUtils;
//import org.apache.commons.lang3.StringUtils; //import org.apache.commons.*;
//import org.apache.commons.lang.xwork.StringUtils;
import org.apache.commons.lang.xwork.StringUtils;
import org.junit.Test;
//import com.google.common.base.Strings;

//import com.sun.corba.se.impl.encoding.CodeSetConversion.BTCConverter;

public class RuleRandom {

	/**
	 * 测试生成一条物联网标识
	 */
	public static void main(String[] args) {

		/**
		 * 参数声明部分： 将规则和参数从数据库拷过来
		 */
		FunctionResult res = new FunctionResult();
		String LengthRule = "21";
		String para = "4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19;20";
		String s = "ISANFADC2C2923F864D8";
		res.nSize = 21;
		for (int i = 0; i < 20; i++)
			res.FunctionResult.put(i, String.valueOf(s.charAt(i)));

		res = RuleRandom.MOD3736_Random(res, para);

		RuleRandom.DisplayRandomCode(res.FunctionResult);

	}

	public static FunctionResult IoTIDLength_Random(String Parameter) {
		FunctionResult Result = new FunctionResult();
		Random r = new Random();
		Boolean flag = false;
		try {
			String[] lengthRanges = Parameter.split(",");
			if (lengthRanges.length == 1) {
				if (lengthRanges[lengthRanges.length - 1].equals("-1")) {// 处理“-1”样式的长度值
					Result.nSize = r.nextInt(1025);
				} else {
					String[] lengthMaxMin = Parameter.split("-");
					if (lengthMaxMin.length == 1) { // 处理“4”样式的长度值
						Result.nSize = Integer.parseInt(lengthMaxMin[0]);
					} else {
						int t1 = Integer.parseInt(lengthMaxMin[0]);
						int t2 = Integer.parseInt(lengthMaxMin[1]);
						Result.nSize = r.nextInt(t2 - t1 + 1) + t1; // 处理“4-20”样式的长度值
					}
				}
			} else {
				if (lengthRanges[lengthRanges.length - 1].equals("-1")) {
					int ss = Integer.parseInt(lengthRanges[0]); // 处理“7，-1”样式的长度值
					Result.nSize = r.nextInt(1024 - ss + 1) + ss;
				} else { // 处理3,4,5,8样式的长度值
					int len = lengthRanges.length;
					int index = r.nextInt(len);
					String value = lengthRanges[index];
					Result.nSize = Integer.parseInt(value);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Result;
	}

	/**
	 * ByteRules****************************************************************
	 * ***************
	 */

	public static FunctionResult IoTIDByte_Random(FunctionResult InPutResult,
			String parameter) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] byteElementString = parameter.split(",");
		int index = Integer.parseInt(byteElementString[0]);

		// 将参数转为位图
		char[] ByteRuleBitmap = new char[64];
		ByteRuleBitmap = TransToBitMap(byteElementString);

		// 产生随机字符数字
		int RandomBoxLength = 0;
		for (int i = 0; i < 64; i++)
			if (ByteRuleBitmap[i] == '1')
				RandomBoxLength++;

		char[] RandomBox = new char[RandomBoxLength];
		RandomBox = produceRandomBox(ByteRuleBitmap, RandomBoxLength);

		// 产生随机字符
		char testChar = ' ';
		testChar = RandomChar.randomizeArray(RandomBox, RandomBox.length);

		// 将结果返回
		Result.FunctionType = "Byte";
		Result.ResultFlag = "OK";
		Result.FunctionResult.put(index, String.valueOf(testChar));

		return Result;
	}

	public static char[] TransToBitMap(String[] byteEliment) {
		char[] ByteRuleBitmap = new char[64];

		int i = 0;
		int j = 0;
		for (i = 1; i < 9; i++) {
			int IntbyteEliment = Integer.parseInt(byteEliment[i]);
			String Binary = Integer.toBinaryString(IntbyteEliment);

			int BinaryLength = Binary.length();

			char BitMapRow[] = new char[8];
			for (j = 0; j < BinaryLength; j++) {
				BitMapRow[j] = Binary.charAt(BinaryLength - j - 1);

			}

			for (j = BinaryLength; j < 8; j++) {
				BitMapRow[j] = '0';
			}

			for (j = 0; j < 8; j++) {

				ByteRuleBitmap[(i - 1) * 8 + j] = BitMapRow[j];

			}

		}
		return ByteRuleBitmap;
	}

	public static char[] produceRandomBox(char[] ByteRuleBitmap, int length) {
		char[] RandomBox = new char[length];
		int i = 0;
		int RandomIndex = 0;
		for (i = 0; i < 64; i++) {
			if (ByteRuleBitmap[i] == '1') {
				if (i < 10)// number
				{

					RandomBox[RandomIndex] = (char) (i + 48);

				} else if (i >= 10 && i < 36)// small letter
				{
					RandomBox[RandomIndex] = (char) (i + 87);
				} else if (i >= 36 && i < 62)// big letter
				{
					RandomBox[RandomIndex] = (char) (i + 29);

				}

				RandomIndex++;
			}

		}
		return RandomBox;
	}

	public static void DisplayRandomCode(HashMap<Integer, String> Result) {
		// 版本一：分别输出Index和code。可以输出部分代码，但顺序可能是错乱的。
		/*
		 * 
		 * Iterator it= Result.keySet().iterator(); while (it.hasNext())//since
		 * we haven't completely finished the full code,we print it per index {
		 * Integer index= (Integer)it.next(); String CodeOfIndex =
		 * Result.get(index); char BitCode=CodeOfIndex.charAt(0);
		 * System.out.println("Index"+index+":"+BitCode); }
		 */

		// 版本二：按顺序输出，只能输出全部代码，顺序正确。

		String CodeOfIndex = "";
		char BitCode = ' ';
		int length = Result.size();
		System.out.println("The IoTCode Is:");
		for (int i = 0; i < length; i++) {

			CodeOfIndex = Result.get(i);
			BitCode = CodeOfIndex.charAt(0);
			System.out.print(BitCode);
		}

		System.out.println();

	}

	public static void DisplayRandomCode1(HashMap<Integer, String> Result) {

		Iterator it = Result.keySet().iterator();
		while (it.hasNext())// since we haven't completely finished the full
		// code,we print it per index
		{
			Integer index = (Integer) it.next();
			String CodeOfIndex = Result.get(index);
			char BitCode = CodeOfIndex.charAt(0);
			System.out.println("Index" + index + ":" + BitCode);
		}
	}

	/*
	 * public static FunctionResult IoTIDByte_Random(FunctionResult InPutResult,
	 * String parameter) { FunctionResult Result = new FunctionResult();
	 * String[] byteElementString = parameter.split(","); int index =
	 * Integer.parseInt(byteElementString[0]);
	 * 
	 * // 将参数转为位图------------- char[] ByteRuleBitmap = new char[64];
	 * ByteRuleBitmap = TransToBitMap(byteElementString);
	 * 
	 * // 产生随机字符数字 int RandomBoxLength = 0; for (int i = 0; i < 64; i++) if
	 * (ByteRuleBitmap[i] == '1') RandomBoxLength++;
	 * 
	 * char[] RandomBox = new char[RandomBoxLength]; RandomBox =
	 * produceRandomBox(ByteRuleBitmap, RandomBoxLength);
	 * 
	 * // 产生随机字符 char testChar = ' '; testChar =
	 * RandomChar.randomizeArray(RandomBox, RandomBox.length);
	 * 
	 * // 将结果返回 InPutResult.FunctionType = "Byte"; InPutResult.ResultFlag =
	 * "OK"; InPutResult.FunctionResult.put(index, String.valueOf(testChar));
	 * Result = InPutResult; return Result; }
	 * 
	 * // 改进后的TransToBitMap public static char[] TransToBitMap(String[]
	 * byteEliment) { StringBuffer ByteRuleBitmap = new StringBuffer(); for (int
	 * i = 1; i < 9; i++) { String element = byteEliment[i]; // 将每个元素变为二进制string
	 * String s = Integer.toBinaryString(Integer.parseInt(element)); // 补充为8位2进制
	 * s = StringUtils.leftPad(s, 8, "0"); StringBuffer s2 = new
	 * StringBuffer(s); s2 = s2.reverse(); // reverse，并添加到整个字符串中
	 * ByteRuleBitmap.append(s2); } return
	 * ByteRuleBitmap.toString().toCharArray(); }
	 * 
	 * public static char[] produceRandomBox(char[] ByteRuleBitmap, int length)
	 * { char[] RandomBox = new char[length]; int i = 0; int RandomIndex = 0;
	 * for (i = 0; i < 64; i++) { if (ByteRuleBitmap[i] == '1') { if (i < 10)//
	 * number {
	 * 
	 * RandomBox[RandomIndex] = (char) (i + 48);
	 * 
	 * } else if (i >= 10 && i < 36)// small letter { RandomBox[RandomIndex] =
	 * (char) (i + 87); } else if (i >= 36 && i < 62)// big letter {
	 * RandomBox[RandomIndex] = (char) (i + 29);
	 * 
	 * }
	 * 
	 * RandomIndex++; }
	 * 
	 * } return RandomBox; }
	 */

	// creator:SQ
	//creator:SQ
	public static FunctionResult AdminDivision_Random(
			FunctionResult InPutResult, String index) {

		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.AdminDivision_TABLE, RecoDaoRandom.hashMapAdminDivision);

		// 得到随机index的code
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.AdminDivision_TABLE, RandomIndex,
				RecoDaoRandom.hashMapAdminDivision);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}

	//creator:SQ
	public static FunctionResult AdminDivision1_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.AdminDivision1_TABLE, RecoDaoRandom.hashMapAdminDivision);

		// 得到随机index的code
		
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.AdminDivision1_TABLE, RandomIndex,
				RecoDaoRandom.hashMapAdminDivision1);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			InPutResult.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}

		Result = InPutResult;
		return Result;

	}

	//creator:SQ
	public static FunctionResult AnimalDisease_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result=InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.AnimalDisease_TABLE,
				RecoDaoRandom.hashMaphashMapAnimalDisease);

		// 得到随机index的code
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.AnimalDisease_TABLE, RandomIndex,
				RecoDaoRandom.hashMaphashMapAnimalDisease);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}

		return Result;		
	}

	//creator:SQ
	public static FunctionResult CheckCodeForCommodityCode_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String[] splitResultIndex = index.split(";");
		String[] splitIndex = splitResultIndex[0].split(",");
		int ResultIndex = Integer.parseInt(splitResultIndex[1]);

		char checkcode = 0;
		int i = 0;
		int odd_sum = 0;
		int even_sum = 0;
		int LenIndex = splitIndex.length;
		char[] IDstr = new char[LenIndex];

		int KeyIndex = 0;
		for (i = 0; i < LenIndex; i++) {
			KeyIndex = Integer.parseInt(splitIndex[i]);
			IDstr[i] = Result.FunctionResult.get(KeyIndex).charAt(0);
		}

		for (i = LenIndex + 1 - 2; i >= 0; i -= 2) {
			even_sum += (IDstr[i] - 48);
		}

		for (i = LenIndex + 1 - 3; i >= 0; i -= 2) {
			odd_sum += (IDstr[i] - 48);

		}

		if ((((even_sum * 3 + odd_sum)) % 10) == 0) {
			checkcode = 48;
		} else {
			checkcode = (char) ((10 - ((even_sum * 3 + odd_sum)) % 10) + 48);
		}

		Result.FunctionResult.put(ResultIndex, String.valueOf(checkcode));

		return Result;

	}

	//creator:SQ
	public static FunctionResult DistrictNo_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result=InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(RecoUtilRandom.DistrictNo_TABLE,
				RecoDaoRandom.hashMapDistrictNo);

		// 得到随机index的code
		
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.DistrictNo_TABLE, RandomIndex,
				RecoDaoRandom.hashMapDistrictNo);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}

		return Result;
			
	}

	//creator:SQ
	//modified by SQ on 0912
	public static FunctionResult CIDRegex_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		Random random = new Random();
		char RandomBoxatozAtoZ[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
				'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
				'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
				'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
				'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		char RandomBoxatozAtoZ0to9_[] = { '0', '1', '2', '3', '4', '5', '6',
				'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
				'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
				'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
				'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
				'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '-' };

		// 产生随机二级域名:1-63个，字母开头
		
		int Level2 = RandomChar.generateRandomInt(63) + 1;
		char[] Level2Result = new char[Level2];
		Level2Result[0] = RandomChar.randomizeArray(RandomBoxatozAtoZ,
				RandomBoxatozAtoZ.length);
		int i = 0;
		for (i = 1; i < Level2; i++) {
			Level2Result[i] = RandomChar.randomizeArray(RandomBoxatozAtoZ0to9_,
					RandomBoxatozAtoZ0to9_.length);

		}

		// 产生随机三级域名：1-63个，字母开头
		int Level3 = RandomChar.generateRandomInt(63) + 1;

		char[] Level3Result = new char[Level3];
		Level3Result[0] = RandomChar.randomizeArray(RandomBoxatozAtoZ,
				RandomBoxatozAtoZ.length);

		for (i = 1; i < Level3; i++) {
			Level3Result[i] = RandomChar.randomizeArray(RandomBoxatozAtoZ0to9_,
					RandomBoxatozAtoZ0to9_.length);

		}

		//保存结果
		Result.FunctionResult.put(18, ".");
		for (i = 19; i < 19 + Level3; i++)
			Result.FunctionResult.put(i, String.valueOf(Level3Result[i - 19]));
		
		Result.FunctionResult.put(19 + Level3, ".");
		
		for (i = 19 + Level3 + 1; i < 19 + Level3 + Level2 + 1; i++)
			Result.FunctionResult.put(i, String.valueOf(Level2Result[i - 19
					- Level3 - 1]));
		
		String regular = ".cid.iot.cn";

		for (i = 19 + Level3 + Level2 + 1; i < 19 + Level3 + Level2 + 11 + 1; i++)
			Result.FunctionResult.put(i, String.valueOf(regular.charAt(i - 19
					- Level3 - Level2 - 1)));

		return Result;

	}

	//creator:SQ
	// 特殊的表格式，只能查询数据库
	// (?#ALGNAME=PrefixofRetailCommodityNumber)(?#PARA=0,1,2){]
	public static FunctionResult PrefixofRetailCommodityNumber_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		int length = RecoDaoRandom.getLengthOfEANUPC();

		
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		RandomResults = RecoDaoRandom.getRandomIndexEANUPC(RandomIndex);

		// 处理结果
		int temp = Integer.parseInt(RandomResults);
		int index0 = temp / 100;
		int index1 = (temp % 100) / 10;
		int index2 = temp % 100 % 10;
		RandomResults = String.valueOf(index0);
		RandomResults += String.valueOf(index1);
		RandomResults += String.valueOf(index2);

		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	
	}

	//creator:SQ
	public static FunctionResult CPCTwoByte_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result=InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");
		int firstByte = RandomChar.generateRandomInt(3);

		int secondByte = 0;
		if (firstByte == 0) {
			char[] RandomBox = { '0', '1', '2' };
			char charByte = RandomChar.randomizeArray(RandomBox,
					RandomBox.length);
			secondByte = charByte - 48;

		} else if (firstByte == 1) {
			char[] RandomBox = { '1', '2', '6', '7' };
			char charByte = RandomChar.randomizeArray(RandomBox,
					RandomBox.length);
			secondByte = charByte - 48;

		} else {
			char[] RandomBox = { '0', '1', '2', '3', '4', '5' };
			char charByte = RandomChar.randomizeArray(RandomBox,
					RandomBox.length);
			secondByte = charByte - 48;

		}
		RandomResults = String.valueOf(firstByte) + String.valueOf(secondByte);
	
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}

	    return Result;

	}

	//creator:SQ
	public static FunctionResult CountryRegionCodeforCPC_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		int length = RecoDaoRandom.getLengthOfcountryregioncode();

		
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		RandomResults = RecoDaoRandom.getRandomIndexcountryregioncode(RandomIndex,splitIndex.length-1);
		RandomResults = RandomResults + '0';

		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;

		//		
	}

	//creator:SQ
	public static FunctionResult MonthDate_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Random ran = new Random();
		String RandomResults = "";
		String[] splitIndex = index.split(",");
		int RandomMonth = ran.nextInt(12) + 1;

		int RandomDate = 0;
		if (RandomMonth == 1 || RandomMonth == 3 || RandomMonth == 5
				|| RandomMonth == 7 || RandomMonth == 8 || RandomMonth == 10
				|| RandomMonth == 12) {
			RandomDate = ran.nextInt(31) + 1;

		} else if (RandomMonth == 2) {
			RandomDate = ran.nextInt(29) + 1;

		} else {
			RandomDate = ran.nextInt(30) + 1;

		}
		// 格式转换
		String Month = "";
		String Date = "";

		Month = String.valueOf(RandomMonth / 10);
		Month = Month + String.valueOf(RandomMonth % 10);
		Date = String.valueOf(RandomDate / 10);
		Date = Date + String.valueOf(RandomDate % 10);
		RandomResults = Month + Date;

		for (int i = 0; i < RandomResults.length(); i++) {
			InPutResult.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}

		Result = InPutResult;

		return Result;
	}

	//creator:SQ
	public static FunctionResult MobilePhoneNum_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result=InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		int length = RecoDaoRandom.getLengthOfMobilePhoneNum();

		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		RandomResults = RecoDaoRandom.getRandomIndexMobilePhoneNum(RandomIndex);

		for (int i = 0; i < RandomResults.length(); i++) {
			InPutResult.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}

		Result = InPutResult;

		return Result;

	}

	//creator:SQ
	public static FunctionResult CountUcode_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		char[] RandomBox = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f', 'A', 'B', 'C', 'D', 'E', 'F' };
		String res = "";
		char randomChar = ' ';
		for (int i = 0; i < 4; i++) {
			randomChar = RandomChar.randomizeArray(RandomBox, RandomBox.length);
			res = res + randomChar;
		}

		while (res == "E000" || res == "FFFF") {
			res = "";
			for (int i = 0; i < 4; i++) {
				randomChar = RandomChar.randomizeArray(RandomBox,
						RandomBox.length);
				res = res + randomChar;
			}

		}

		for (int i = 0; i < res.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(res.charAt(i)));
		}

		return Result;

	}

	//creator:SQ
	public static FunctionResult ISBN13_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
        Result=InPutResult;
		String[] splitResultIndex = index.split(";");
		String[] splitIndex = splitResultIndex[0].split(",");
		int ResultIndex = Integer.parseInt(splitResultIndex[1]);

		int checkNumber = 0;

		int LenIndex = splitIndex.length;
		char[] IDstr = new char[LenIndex];

		int KeyIndex = 0;
		int i = 0;
		for (i = 0; i < LenIndex; i++) {
			KeyIndex = Integer.parseInt(splitIndex[i]);
			IDstr[i] = InPutResult.FunctionResult.get(KeyIndex).charAt(0);
		}

		checkNumber = (IDstr[0] - 48) * 1 + (IDstr[1] - 48) * 3
				+ (IDstr[2] - 48) * 1 + (IDstr[3] - 48) * 3 + (IDstr[4] - 48)
				* 1 + (IDstr[5] - 48) * 3 + (IDstr[6] - 48) * 1
				+ (IDstr[7] - 48) * 3 + (IDstr[8] - 48) * 1 + (IDstr[9] - 48)
				* 3 + (IDstr[10] - 48) * 1 + (IDstr[11] - 48) * 3;
		checkNumber = checkNumber % 10;
		if (checkNumber != 0)
			checkNumber = 10 - checkNumber;

		char checkcode = (char) (checkNumber + 48);

		Result.FunctionResult.put(ResultIndex, String.valueOf(checkcode));
		return Result;

	}


	//creator:SQ
	//modify:0827
	public static FunctionResult MOD112_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		
		String[] splitResultIndex = index.split(";");
		String[] splitIndex = splitResultIndex[0].split(",");
		int ResultIndex = Integer.parseInt(splitResultIndex[1]);

		int LenIndex = splitIndex.length;
		int[] IDstr = new int[Result.nSize-1];

		
		// 将字符码0-9转为数字0-9；将字符A-Z映射为数字10-35
		char temp = ' ';
		int KeyIndex=0;
		for (int i = 0; i < LenIndex; i++) {
			KeyIndex=Integer.parseInt(splitIndex[i]);
			temp = Result.FunctionResult.get(KeyIndex).charAt(0);
			if (temp >= 'A' && temp <= 'Z')
				IDstr[KeyIndex] = (int) (temp - 55);
			else
				IDstr[KeyIndex] = (int) (temp - 48);

		}


		double sum=0;
		for (int i = 0; i < LenIndex; i++) {
			KeyIndex=Integer.parseInt(splitIndex[i]);
			sum = sum +IDstr[Integer.valueOf(KeyIndex)]
					* (Math.pow(2, LenIndex - i) % 11);
		}

		int mod;
		sum %= 11;
		mod = (int) (12 - sum) % 11;

		char checkcode = ' ';
		if (mod == 10)
			checkcode = 'X';
		else
			checkcode = (char) (mod + 48);

		Result.FunctionResult.put(ResultIndex, String.valueOf(checkcode));

		return Result;

	}
	
	//creator:SQ
	public static FunctionResult LogisticsCheck_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result=InPutResult;
		String[] splitResultIndex = index.split(";");
		String[] splitIndex = splitResultIndex[0].split(",");
		int ResultIndex = Integer.parseInt(splitResultIndex[1]);

		int checkNumber = 0;

		int LenIndex = splitIndex.length;
		char[] IDstr = new char[LenIndex];

		int KeyIndex = 0;
		int i = 0;
		for (i = 0; i < LenIndex; i++) {
			KeyIndex = Integer.parseInt(splitIndex[i]);
			IDstr[i] = Result.FunctionResult.get(KeyIndex).charAt(0);
		}
		int[] IntIDstr = new int[5];

		for (i = 0; i < 5; i++) {
			IntIDstr[i] = IDstr[i] - 48;
		}
		checkNumber = 5 * IntIDstr[1] + 4 * IntIDstr[2] + 3 * IntIDstr[3] + 2
				* IntIDstr[4];

		checkNumber = checkNumber % 11;

		if (checkNumber == 10) {
			checkNumber = 0;
		}
		char checkcode = (char) (checkNumber + 48);

		Result.FunctionResult.put(ResultIndex, String.valueOf(checkcode));
	

		return Result;

	}

	//creator:SQ
	public static FunctionResult MedAppCode_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result=InPutResult;
		int InputIndex = 1;
		char valueIndex = Result.FunctionResult.get(InputIndex).charAt(0);
		char randomCode = ' ';
		char[] RandomBox = { '0', '1', '2' };
		if (valueIndex == '9') {
			randomCode = RandomChar.randomizeArray(RandomBox, RandomBox.length);
		} else {
			Random ran = new Random();
			randomCode = (char) (ran.nextInt(10) + 48);
		}
		Result.FunctionResult.put(Integer.parseInt(index), String
				.valueOf(randomCode));

		return Result;
	}

	//creator:SQ
	public static FunctionResult GassCompany_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.GassCompany_TABLE, RecoDaoRandom.hashMapGassCompany);

		// 得到随机index的code
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.GassCompany_TABLE, RandomIndex,
				RecoDaoRandom.hashMapGassCompany);

		// 保存结果
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;

	}

	//creator:SQ
	public static FunctionResult GraiSerialNo_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		int keyIndex = Integer.parseInt(splitIndex[0]);
		int length = Result.nSize;
		char randomChar = ' ';
		char[] RandomBox = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
				'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
				'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
				'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
				'W', 'X', 'Y', 'Z', '!', '"', '%', '&', '\'', '(', ')', '*',
				'+', ',', '-', '.', '/', ':', ';', '<', '=', '>', '?', '_' };
		for (int i = 0; i < length - keyIndex; i++) {
			randomChar = RandomChar.randomizeArray(RandomBox, RandomBox.length);
			Result.FunctionResult.put(keyIndex + i, String.valueOf(randomChar));

		}
		// to do something here!!!

		return Result;

	}

	//creator:SQ
	public static FunctionResult TwoByteDecimalnt_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		int randomInt = RandomChar.generateRandomInt(99) + 1;
	
		Result.FunctionResult.put(Integer.parseInt(splitIndex[0]), String
				.valueOf(randomInt / 10));
		Result.FunctionResult.put(Integer.parseInt(splitIndex[1]), String
				.valueOf(randomInt % 10));

		return Result;

	}

	//creator:SQ
	public static FunctionResult Bigcode_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		Random ran = new Random();
		String[] RandomNum = { "01", "02", "03", "04", "05", "06", "21" };
		int randomKey = RandomChar.generateRandomInt(7);
		String Num = RandomNum[randomKey];
		Result.FunctionResult.put(Integer.parseInt(splitIndex[0]), String
				.valueOf(Num.charAt(0)));
		Result.FunctionResult.put(Integer.parseInt(splitIndex[1]), String
				.valueOf(Num.charAt(1)));

		return Result;

	}

	//creator:SQ
	public static FunctionResult Littlecode_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		Random ran = new Random();
		String[] RandomNum = { "01", "02", "03", "04", "05", "21" };
		int randomKey = ran.nextInt(6);
		String Num = RandomNum[randomKey];
		Result.FunctionResult.put(Integer.parseInt(splitIndex[0]), String
				.valueOf(Num.charAt(0)));
		Result.FunctionResult.put(Integer.parseInt(splitIndex[1]), String
				.valueOf(Num.charAt(1)));
		return Result;

	}

	//creator:SQ
	public static FunctionResult GainStoreHouse_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.GainStoreHouse_TABLE, RecoDaoRandom.hashMapGainStoreHouse);

		// 得到随机index的code
		
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.GainStoreHouse_TABLE, RandomIndex,
				RecoDaoRandom.hashMapGainStoreHouse);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		// to do something here!!!

		return Result;

	}

	//creator:SQ
	public static FunctionResult GeographicInfoCode_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.GeographicInfoCode_TABLE,
				RecoDaoRandom.hashMapGeographicInfoCode);

		// 得到随机index的code
		
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.GeographicInfoCode_TABLE, RandomIndex,
				RecoDaoRandom.hashMapGeographicInfoCode);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;

	}

	//creator:SQ
	public static FunctionResult HouseCode_CheckTwelBitCode_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		
		int method = RandomChar.generateRandomInt(4);
	
		String res = "";
		switch (method) {
		case 0:
			res = HouseCode_CheckBasedCompleteTime_Random();
			break;
		case 1:
			res = HouseCode_CheckBasedCoordinate_Random();
			break;
		case 2:
			res = HouseCode_CheckBasedFenzong_Random();
			break;
		case 3:
			res = HouseCode_CheckBasedFenfu_Random();
			break;

		}
		// to do something here!!!

		for (int i = 0; i < res.length(); i++) {
			InPutResult.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(res.charAt(i)));
		}
		return Result;

	}
	//creator:SQ
	public static String HouseCode_CheckBasedCompleteTime_Random() {
		String res = "";
		Random ran = new Random();
		int randomInt = 0;
		char[] tempRes = new char[7];
		tempRes[0] = '0';// 约定索引0的位置取值为0，方便统一循环下标
		char[] Boxstar0to9 = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
				'9', '*' };
		char[] Boxstar1to9 = { '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		char[] Box012star = { '0', '1', '2', '*' };
		char[] Box01star = { '0', '1', '*' };
		char randomChar = ' ';
		for (int i = 1; i < 7; i++) {
			if (tempRes[i - 1] == '*') {
				tempRes[i] = '*';
			} else {
				if (i >= 1 && i <= 4)// 取值为0-9，*
				{
					randomChar = RandomChar.randomizeArray(Boxstar0to9,
							Boxstar0to9.length);
					tempRes[i] = randomChar;
				}

				else if (i == 5)// 取值为0,1，*
				{
					randomChar = RandomChar.randomizeArray(Box01star,
							Box01star.length);
					tempRes[i] = randomChar;

				} else {
					if (tempRes[5] == '0')// 取值为1-9，*
					{
						randomChar = RandomChar.randomizeArray(Boxstar1to9,
								Boxstar1to9.length);
						tempRes[i] = randomChar;
					} else// 取值为0,1,2，*
					{
						randomChar = RandomChar.randomizeArray(Box012star,
								Box012star.length);
						tempRes[i] = randomChar;

					}
				}

			}
		}
		// 将结果保存到res中
		for (int i = 1; i < 7; i++) {
			res = res + String.valueOf(tempRes[i]);
		}

		for (int j = 0; j < 6; j++)// 000001-999999
		{
			if (j < 5) {
				randomInt = RandomChar.generateRandomInt(10);
				res = res + String.valueOf(randomInt);
			} else// 第四位取值只能是1-9
			{
				randomInt = RandomChar.generateRandomInt(9) + 1;
				res = res + String.valueOf(randomInt);

			}

		}

		return res;
	}
	//creator:SQ
	public static String HouseCode_CheckBasedCoordinate_Random() {
		String res = "";
		Random ran = new Random();
		int randomInt = 0;
		for (int i = 0; i < 12; i++) {
			randomInt = RandomChar.generateRandomInt(10);;
			res = res + String.valueOf(randomInt);
		}
		return res;
	}

	public static String HouseCode_CheckBasedFenzong_Random() {
		String res = "";
		int randomInt = 0;
		Random ran = new Random();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++)// 0001-9999
			{
				if (j < 3) {
					randomInt = RandomChar.generateRandomInt(10);
					res = res + String.valueOf(randomInt);
				} else// 第四位取值只能是1-9
				{
					randomInt = RandomChar.generateRandomInt(9)+ 1;
					res = res + String.valueOf(randomInt);

				}

			}

		}
		return res;
	}
	//creator:SQ
	public static String HouseCode_CheckBasedFenfu_Random() {
		String res = "";
		
		int randomInt = 0;
		for (int i = 0; i < 8; i++) {
			randomInt = RandomChar.generateRandomInt(10);
			res = res + String.valueOf(randomInt);
		}
		for (int j = 0; j < 4; j++) {
			if (j < 3) {
				randomInt = RandomChar.generateRandomInt(10);
				res = res + String.valueOf(randomInt);
			} else {
				randomInt = RandomChar.generateRandomInt(9)+ 1;
				res = res + String.valueOf(randomInt);

			}

		}

		return res;
	}

	//creator:SQ
	public static FunctionResult HouseCode_CheckUnitCode_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		// to do something here!!!
		String res = "";
		int randomInt = 0;
		Random ran = new Random();

		for (int j = 0; j < 4; j++)// 0001-9999
		{
			if (j < 3) {
				randomInt = RandomChar.generateRandomInt(10);
				res = res + String.valueOf(randomInt);
			} else// 第四位取值只能是1-9
			{
				randomInt = RandomChar.generateRandomInt(9) + 1;
				res = res + String.valueOf(randomInt);

			}

		}

		for (int i = 0; i < res.length(); i++) {
			InPutResult.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(res.charAt(i)));
		}

		return Result;

	}
	//creator:SQ
	public static FunctionResult HouseCode_CheckCode_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		// to do something here!!!
		String[] splitResultIndex = index.split(";");
		String[] splitIndex = splitResultIndex[0].split(",");
		int ResultIndex = Integer.parseInt(splitResultIndex[1]);

		char checkcode = 0;

		int LenID = splitIndex.length;
		char[] IDstr = new char[LenID];
		int KeyIndex = 0;
		for (int i = 0; i < LenID; i++) {
			KeyIndex = Integer.parseInt(splitIndex[i]);
			IDstr[i] = Result.FunctionResult.get(KeyIndex).charAt(0);
		}
		char[] IDstrTemp = new char[LenID];
		for (int k = 0; k < LenID; k++) {
			if ('*' == IDstr[k]) {
				IDstrTemp[k] = '0';
			} else {
				IDstrTemp[k] = IDstr[k];
			}
		}
		int i = 0;
		int j = 10;
		for (i = 0; i < LenID; i++) {
			int mode10 = (((int) IDstrTemp[Integer.parseInt(splitIndex[i])] - 48) + j) % 10;
			if (0 == mode10) {
				mode10 = 10;
			}
			j = (mode10 * 2) % 11;
		}
		checkcode = (char) ((10 - (j - 1) % 10) + 48);
		
		InPutResult.FunctionResult.put(ResultIndex, String.valueOf(checkcode));
		return Result;

	}

	//creator:SQ
	public static FunctionResult Month_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");

		char[] _0to1 = { '0', '1' };
		char[] _1to9 = { '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		char[] _0to2 = { '0', '1', '2' };
		char index1 = RandomChar.randomizeArray(_0to1, _0to1.length);
		char index2 = ' ';
		if (index1 == '0') {
			index2 = RandomChar.randomizeArray(_1to9, _1to9.length);
		} else {
			index2 = RandomChar.randomizeArray(_0to2, _0to2.length);
		}

		Result.FunctionResult.put(Integer.parseInt(splitIndex[0]), String
				.valueOf(index1));
		Result.FunctionResult.put(Integer.parseInt(splitIndex[1]), String
				.valueOf(index2));
		// to do something here!!!

		return Result;

	}

	//creator:SQ
	public static FunctionResult FourByteDecimalnt_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		// to do something here!!!
		String res = "";
		int randomInt = 0;
		for (int j = 0; j < 4; j++)// 0001-9999
		{
			if (j < 3) {
				randomInt = RandomChar.generateRandomInt(10);
				res = res + String.valueOf(randomInt);
			} else// 第四位取值只能是1-9
			{
				randomInt = RandomChar.generateRandomInt(9) + 1;
				res = res + String.valueOf(randomInt);

			}

		}

		for (int i = 0; i < res.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(res.charAt(i)));
		}

		return Result;

	}

	//creator:SQ
	public static FunctionResult First2CharsofAdminDivision_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.AdminDivision_TABLE, RecoDaoRandom.hashMapAdminDivision);

		// 得到随机index的code
		
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.AdminDivision_TABLE, RandomIndex,
				RecoDaoRandom.hashMapAdminDivision);

		// 保存结果：提取结果的前两位
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	
	}

	//creator:SQ
	public static FunctionResult FiveByteDecimalnt_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		// to do something here!!!
		String res = "";
		int randomInt = 0;
		
		for (int j = 0; j < 5; j++)// 0001-9999
		{
			if (j < 4) {
				randomInt = RandomChar.generateRandomInt(10);
				res = res + String.valueOf(randomInt);
			} else// 第四位取值只能是1-9
			{
				randomInt =RandomChar.generateRandomInt(9) + 1;
				res = res + String.valueOf(randomInt);

			}

		}

		for (int i = 0; i < res.length(); i++) {
			InPutResult.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(res.charAt(i)));
		}
		return Result;

	}

	//creator:SQ
	public static FunctionResult WirtschaftsTypCode_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.WirtschaftsTypCode_TABLE,
				RecoDaoRandom.hashMapWirtschaftsTypCode);

		// 得到随机index的code
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.WirtschaftsTypCode_TABLE, RandomIndex,
				RecoDaoRandom.hashMapWirtschaftsTypCode);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			InPutResult.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}
		Result = InPutResult;

		return Result;

	}

	//creator:SQ
	public static FunctionResult JadejewelryClass_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.JadejewelryClass_TABLE,
				RecoDaoRandom.hashMapJadejewelryClass);

		// 得到随机index的code
	
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.JadejewelryClass_TABLE, RandomIndex,
				RecoDaoRandom.hashMapJadejewelryClass);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;

	}

	//creator:SQ
	public static FunctionResult OneTO05_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String[] splitIndex = index.split(",");
		
		
		String[] Box = { "01", "02", "03", "04", "05", "99" };
		int randomIndex = RandomChar.generateRandomInt(Box.length);
		String res = Box[randomIndex];
		// 保存结果
		for (int i = 0; i < res.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(res.charAt(i)));
		}
		return Result;

	}

	//creator:SQ
	public static FunctionResult GrainEnterprise_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String[] splitIndex = index.split(",");
		Random ran = new Random();
		
		String[] Box = { "10", "11", "19", "30" };
		int randomIndex = RandomChar.generateRandomInt(Box.length);
		String res = Box[randomIndex];
		// 保存结果
		for (int i = 0; i < res.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(res.charAt(i)));
		}

		return Result;

	}
	
	//creator:SQ
	public static FunctionResult Wasteproducts_Random(FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.Wasteproducts_TABLE,
				RecoDaoRandom.hashMapWasteproducts);

		// 得到随机index的code
	
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.Wasteproducts_TABLE, RandomIndex,
				RecoDaoRandom.hashMapWasteproducts);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;

	}


	//creator:SQ
	public static FunctionResult Wastewater_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String res = "";
		String[] splitIndex = index.split(",");
		int length = Result.nSize;
		int beginIndex = Integer.parseInt(splitIndex[0]);

		//modified by SQ on 0912
		//int randomNum = RandomChar.generateRandomInt(9)+1;
		//res = res + String.valueOf(randomNum);// 产生第一个必须字符

		int randomNum=0;
		for (int i = 0; i < length - beginIndex ; i++) {
			randomNum = RandomChar.generateRandomInt(9) + 1;
			res = res + String.valueOf(randomNum);
		}

		// 保存结果
		for (int i = beginIndex; i < length; i++) {
			Result.FunctionResult.put(i, String.valueOf(res.charAt(i
					- beginIndex)));

		}

		return Result;

	}

	//creator:SQ
	public static FunctionResult EnvironmentalInformation_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();

		Result = InPutResult;
		String res = "";
		String[] splitIndex = index.split(",");
		Random ran = new Random();
		int length = Result.nSize;

		int beginIndex = Integer.parseInt(splitIndex[0]);

		int randomNum = RandomChar.generateRandomInt(10);
		res = res + String.valueOf(randomNum);// 产生第一个必须字符
		for (int i = 0; i < length - beginIndex - 1; i++) {
			randomNum = RandomChar.generateRandomInt(9) + 1;
			res = res + String.valueOf(randomNum);
		}

		// 保存结果
		for (int i = beginIndex; i < length; i++) {

			Result.FunctionResult.put(i, String.valueOf(res.charAt(i
					- beginIndex)));

		}

		return Result;
		//
		//		
	}

	//creator:SQ
	public static FunctionResult ParamCode6_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String res = "";
		String[] splitIndex = index.split(",");
		Random ran = new Random();
		int length = Result.nSize;
		int beginIndex = Integer.parseInt(splitIndex[0]);

		if (length == 12) {
			for (int i = 0; i < 2; i++) {
				res = res + String.valueOf(RandomChar.generateRandomInt(10));
			}
			res = res + '.';
			for (int i = 0; i < 3; i++) {
				res = res + String.valueOf(RandomChar.generateRandomInt(10));
			}

		} else {
			for (int i = 0; i < 3; i++) {
				res = res + String.valueOf(RandomChar.generateRandomInt(10));
			}
			res = res + '.';
			for (int i = 0; i < 3; i++) {
				res = res + String.valueOf(RandomChar.generateRandomInt(10));
			}

		}
		// 保存结果

		for (int i = beginIndex; i < length; i++) {
			Result.FunctionResult.put(i, String.valueOf(res.charAt(i
					- beginIndex)));

		}
		return Result;
	}

	//creator:SQ
	public static FunctionResult GrainsIndex_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.GrainsIndex_TABLE, RecoDaoRandom.hashMapGrainsIndex);

		// 得到随机index的code
		Random random = new Random();
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.GrainsIndex_TABLE, RandomIndex,
				RecoDaoRandom.hashMapGrainsIndex);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;

	}
	//creator:SQ
	//8,9,10
	//modify:0827,0912
	public static FunctionResult Powergoodsuncertainly_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();

		//String regex = "[0-9]{0,2}";
		Result = InPutResult;
		String res = "";
		String[] splitIndex = index.split(",");
		int length = Result.nSize;
		int beginIndex = Integer.parseInt(splitIndex[0]);

		if (length == 12) {
			res = res + String.valueOf(RandomChar.generateRandomInt(10));
			res = res + String.valueOf(RandomChar.generateRandomInt(10));
		} else if (length == 9) {
			res = res + String.valueOf(RandomChar.generateRandomInt(10));
			res = res + String.valueOf(RandomChar.generateRandomInt(10));
			res = res + String.valueOf(RandomChar.generateRandomInt(10));
		} else {
			res = res + String.valueOf(RandomChar.generateRandomInt(10));
			res = res + String.valueOf(RandomChar.generateRandomInt(10));
			res = res + String.valueOf(RandomChar.generateRandomInt(10));
			res = res + String.valueOf(RandomChar.generateRandomInt(10));

		}
		// 保存结果

		for (int i = beginIndex; i < length; i++) {
			Result.FunctionResult.put(i, String.valueOf(res.charAt(i
					- beginIndex)));

		}
		return Result;

	}


	//creator:SQ
	public static FunctionResult OneTO08_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String[] splitIndex = index.split(",");
		Random ran = new Random();

		String[] Box = { "01", "02", "03", "04", "05", "06", "07", "08", "99" };
		int randomIndex = RandomChar.generateRandomInt((Box.length));
		String res = Box[randomIndex];
		// 保存结果
		for (int i = 0; i < res.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(res.charAt(i)));
		}
		return Result;
	}
	//creator:SQ
	public static FunctionResult Port_Random(FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(RecoUtilRandom.Port_TABLE,
				RecoDaoRandom.hashMapPort);

		// 得到随机index的code
		Random random = new Random();
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.Port_TABLE, RandomIndex, RecoDaoRandom.hashMapPort);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;

	}

	//creator:SQ
	public static FunctionResult ForestTypes_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.ForestTypes_TABLE, RecoDaoRandom.hashMapForestTypes);

		// 得到随机index的code
		
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.ForestTypes_TABLE, RandomIndex,
				RecoDaoRandom.hashMapForestTypes);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		// to do something here!!!

		return Result;

	}

	//creator:SQ
	public static FunctionResult ChineseCharRegex_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		Random ran = new Random();
		String[] splitIndex = index.split(",");
		String res = "";
		String begin = "";
		String end = "";
		int decBegin = 0;
		int decEnd = 0;
		int range = 0;
		int randomNum = 0;
		int randomResult = 0;

		begin = "4e00";
		end = "9fa5";
		decBegin = Integer.valueOf(begin, 16);
		decEnd = Integer.valueOf(end, 16);
		range = decEnd - decBegin + 1;
		// 第一个汉字
		randomNum = RandomChar.generateRandomInt(range);
		randomResult = decBegin + randomNum;
		res = Integer.toHexString(randomResult);
		// 第二个汉字
		String res1 = "";
		randomNum = RandomChar.generateRandomInt(range);
		randomResult = decBegin + randomNum;
		res1 = Integer.toHexString(randomResult);

		res = res + res1;
		int pickNum = ran.nextInt(2);
		if (pickNum == 0)// 处理大写字母
		{
			res = res.toUpperCase();
		}

		for (int i = 0; i < res.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(res.charAt(i)));
		}

		return Result;

	}

	//creator:SQ
	public static FunctionResult VoltageClass_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.VoltageClass_TABLE, RecoDaoRandom.hashMapVoltageClass);

		// 得到随机index的code
		Random random = new Random();
		int RandomIndex = 0;
		RandomIndex=RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.VoltageClass_TABLE, RandomIndex,
				RecoDaoRandom.hashMapVoltageClass);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		// to do something here!!!

		return Result;

	}

	//creator:SQ
	public static FunctionResult TwobytleWeekCode_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		Random ran = new Random();
		//int randomInt = ran.nextInt(53) + 1;
		int randomInt=RandomChar.generateRandomInt(53)+1;
		int index1 = Integer.parseInt(splitIndex[0]);
		int index2 = Integer.parseInt(splitIndex[1]);

		Result.FunctionResult.put(Integer.parseInt(splitIndex[0]), String
				.valueOf(randomInt / 10));
		Result.FunctionResult.put(Integer.parseInt(splitIndex[1]), String
				.valueOf(randomInt % 10));
    	return Result;

	}
	
	//creator:SQ
	public static FunctionResult ClassOfCardCode_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		
		int Range = 53 - 21 + 1;
		//int randomInt = ran.nextInt(Range) + 21;
		int randomInt=RandomChar.generateRandomInt(Range)+21;
		
		Result.FunctionResult.put(Integer.parseInt(splitIndex[0]), String
				.valueOf(randomInt / 10));
		Result.FunctionResult.put(Integer.parseInt(splitIndex[1]), String
				.valueOf(randomInt % 10));
		return Result;

	}

	//creator:SQ
	public static FunctionResult NormalAndShortCycleSpeciality_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.NormalAndShortCycleSpeciality_TABLE,
				RecoDaoRandom.hashMapNormalAndShortCycleSpeciality);

		// 得到随机index的code
		
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.NormalAndShortCycleSpeciality_TABLE, RandomIndex,
				RecoDaoRandom.hashMapNormalAndShortCycleSpeciality);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			InPutResult.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}
		Result = InPutResult;
		// to do something here!!!

		return Result;

	}

	//creator:SQ
	public static FunctionResult Slash_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		int Index = Integer.valueOf(index);
		Result.FunctionResult.put(Index, String.valueOf("/"));

		return Result;

	}

	//creator:SQ
	// Function: 00-14 18 20 22 27 33 42
	public static FunctionResult ZeroTO14_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String[] splitIndex = index.split(",");
	
		String[] Box = { "01", "02", "03", "04", "05", "06", "07", "08", "09",
				"10", "11", "12", "13", "14", "18", "20", "22", "27", "33",
				"42" };
		//int randomIndex = ran.nextInt(Box.length);
		int randomIndex=RandomChar.generateRandomInt(Box.length);
		String res = Box[randomIndex];
		// 保存结果
		for (int i = 0; i < res.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(res.charAt(i)));
		}

		return Result;
	}

	//creator:SQ
	public static FunctionResult GrainAdministrative_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();

		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.GrainAdministrative_TABLE,
				RecoDaoRandom.hashMapGrainAdministrative);

		// 得到随机index的code
		Random random = new Random();
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.GrainAdministrative_TABLE, RandomIndex,
				RecoDaoRandom.hashMapGrainAdministrative);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			InPutResult.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}
		Result = InPutResult;
		return Result;
	}

	//creator:SQ
	public static FunctionResult HarmfulFactor_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.HarmfulFactor_TABL, RecoDaoRandom.hashMapHarmfulFacto);

		// 得到随机index的code
		Random random = new Random();
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.HarmfulFactor_TABL, RandomIndex,
				RecoDaoRandom.hashMapHarmfulFacto);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			InPutResult.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}
		Result = InPutResult;
		return Result;
	}

	//creator:SQ
	public static FunctionResult OneTO07_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		Random ran = new Random();
		String[] RandomNum = { "01", "02", "03", "04", "05", "06", "07", "99" };
		int randomKey = ran.nextInt(RandomNum.length);
		String Num = RandomNum[randomKey];
		Result.FunctionResult.put(Integer.parseInt(splitIndex[0]), String
				.valueOf(Num.charAt(0)));
		Result.FunctionResult.put(Integer.parseInt(splitIndex[1]), String
				.valueOf(Num.charAt(1)));
		return Result;
	}
	
	//creator:SQ
	public static FunctionResult OneTO11_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		Random ran = new Random();
		String[] RandomNum = { "01", "02", "03", "04", "05", "06", "07","08","09","10","11","99" };
		int randomKey = ran.nextInt(RandomNum.length);
		String Num = RandomNum[randomKey];
		Result.FunctionResult.put(Integer.parseInt(splitIndex[0]), String
				.valueOf(Num.charAt(0)));
		Result.FunctionResult.put(Integer.parseInt(splitIndex[1]), String
				.valueOf(Num.charAt(1)));
		return Result;
	}
	//creator:SQ
	public static FunctionResult TabaccoStandardPart_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.TabaccoStandardPart_TABLE,
				RecoDaoRandom.hashMapTabaccoStandardPart);

		// 得到随机index的code
		Random random = new Random();
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.TabaccoStandardPart_TABLE, RandomIndex,
				RecoDaoRandom.hashMapTabaccoStandardPart);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}
	//creator:SQ
	public static FunctionResult HighwayTransportation4c6_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();

		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.HighwayTransportation4c6_TABLE,
				RecoDaoRandom.hashMapHighwayTransportation4c6);

		// 得到随机index的code
		Random random = new Random();
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.HighwayTransportation4c6_TABLE, RandomIndex,
				RecoDaoRandom.hashMapHighwayTransportation4c6);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		// to do something here!!!

		return Result;

	}

	//creator:SQ
	public static FunctionResult StandardMusicCheckCode_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitResultIndex = index.split(";");
		String[] splitIndex = splitResultIndex[0].split(",");
		int ResultIndex = Integer.parseInt(splitResultIndex[1]);

		char checkcode = 0;
		int i = 0;
		int LenIndex = splitIndex.length;
		char[] IDstr = new char[LenIndex];

		int KeyIndex = 0;
		for (i = 0; i < LenIndex; i++) {
			KeyIndex = Integer.parseInt(splitIndex[i]);
			IDstr[i] = InPutResult.FunctionResult.get(KeyIndex).charAt(0);
		}
		// 转换成数字

		int[] newIDstr = new int[10];
		int sum = 0;
		for (i = 5; i <= 13; i++) {
			newIDstr[i - 4] = (int) (IDstr[i] - 48);
		}

		sum++;
		for (i = 1; i <= 9; i++)
			sum = sum + i * newIDstr[i];

		checkcode = (char) (10 - sum % 10 + 48);

		Result.FunctionResult.put(ResultIndex, String.valueOf(checkcode));
		return Result;

	}

	//creator:SQ
	public static FunctionResult FireFightrescuePlan_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String[] splitIndex = index.split(",");
		Random ran = new Random();

		String[] Box = { "00", "10", "11", "12", "13", "14", "15", "19", "20",
				"21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
				"31", "32", "33", "34", "35", "39", "40", "50", "60", "90",
				"89", "70", "71", "72", "73", "74", "75", "76", "79", "80",
				"81", "82", "83", "84", "85" };
		int randomIndex = RandomChar.generateRandomInt(Box.length);
		String res = Box[randomIndex];
		if (res == "00") {
			res = "1" + res + "00";
		} else
			res = "0" + res + "00";
		// 保存结果
		for (int i = 0; i < res.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(res.charAt(i)));
		}
		// to do something here!!!

		return Result;

	}

	//creator:SQ
	public static FunctionResult DZClassify_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(RecoUtilRandom.DZClassify_TABLE,
				RecoDaoRandom.hashMapDZClassify);

		// 得到随机index的code
		Random random = new Random();
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.DZClassify_TABLE, RandomIndex,
				RecoDaoRandom.hashMapDZClassify);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;

	}

	
	//creator:SQ
	public static FunctionResult GainsDiseases_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.GainsDiseases_TABLE, RecoDaoRandom.hashMapGainsDiseases);

		// 得到随机index的code
		Random random = new Random();
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.GainsDiseases_TABLE, RandomIndex,
				RecoDaoRandom.hashMapGainsDiseases);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;

	}

	//creator:SQ
	public static FunctionResult TabaccoMachineProduct_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.TabaccoMachineProduct_TABLE,
				RecoDaoRandom.hashMapTabaccoMachineProduct);

		// 得到随机index的code
		
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.TabaccoMachineProduct_TABLE, RandomIndex,
				RecoDaoRandom.hashMapTabaccoMachineProduct);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		return Result;

	}
	//creator:SQ
	public static FunctionResult TabaccoMachineProducer_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.TabaccoMachineProducer_TABLE,
				RecoDaoRandom.hashMapTabaccoMachineProducer);

		// 得到随机index的code
		
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.TabaccoMachineProducer_TABLE, RandomIndex,
				RecoDaoRandom.hashMapTabaccoMachineProducer);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}

	//creator:SQ
	/*public static FunctionResult goalsocialeconomic_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.goalsocialeconomic_TABLE,
				RecoDaoRandom.hashMapgoalsocialeconomic);

		// 得到随机index的code
		
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.goalsocialeconomic_TABLE, RandomIndex,
				RecoDaoRandom.hashMapgoalsocialeconomic);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		return Result;

	}*/
	//modified by lly on 0915
	//modified by lly 0n 0917
	public static FunctionResult goalsocialeconomic_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom recoDao = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = recoDao.getLengthPublicFunction(
				RecoUtilRandom.goalsocialeconomic_TABLE,
				recoDao.hashMapgoalsocialeconomic);

		// 得到随机index的code
		
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = recoDao.getRandomIndexPublicFunction(
				RecoUtilRandom.goalsocialeconomic_TABLE, RandomIndex,
				recoDao.hashMapgoalsocialeconomic);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[0])+i, String
					.valueOf(RandomResults.charAt(i)));
		}
		return Result;

	}




	//creator:SQ
	public static FunctionResult CreditIdentifiers_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String res = "";
		String[] splitIndex = index.split(",");
		Random ran = new Random();
		int randomNum = 0;
		int length = Result.nSize;
		int beginIndex = Integer.parseInt(splitIndex[0]);
		if (length == 14) {
			randomNum = ran.nextInt(10);
			res = res + String.valueOf(randomNum);
		} else {
			randomNum = ran.nextInt(10);
			res = res + String.valueOf(randomNum);
			randomNum = ran.nextInt(10);
			res = res + String.valueOf(randomNum);

		}
		// 保存结果
		for (int i = beginIndex; i < length; i++) {
			Result.FunctionResult.put(i, String.valueOf(res.charAt(i
					- beginIndex)));

		}
		return Result;

	}

	//creator:SQ
	//modify:0827
	public static FunctionResult MOD3736_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		
		String[] splitResultIndex = index.split(";");
		String[] splitIndex = splitResultIndex[0].split(",");
		int ResultIndex = Integer.parseInt(splitResultIndex[1]);

		int LenIndex = splitIndex.length;
		int length = Result.nSize - 1;

		int[] IDstr = new int[length];

		// 将字符码0-9转为数字0-9；将字符A-Z映射为数字10-35
		char temp = ' ';
		int KeyIndex=0;
		for (int i = 0; i < splitIndex.length; i++) {
			KeyIndex=Integer.parseInt(splitIndex[i]);
			temp = Result.FunctionResult.get(KeyIndex).charAt(0);
			if (temp >= 'A' && temp <= 'Z')
				IDstr[KeyIndex] = (int) (temp - 55);
			else
				IDstr[KeyIndex] = (int) (temp - 48);

		}

		// 计算校验值
		int i = 0;
		int p = 36;
		int s;
		int middle = 0;
		for (i = 1; i <= LenIndex; i++) {
			middle = p + IDstr[Integer.valueOf(splitIndex[i - 1])];
			if ((middle % 36 == 0)) {
				s = 36;
			} else {
				s = middle % 36;
			}

			p = (s * 2) % 37;
		}

		// (p+mod)MOD36=1
		int mod = 0;
		mod = (37 - p) % 36;
   
		// 将校验值映射为字符码
		String numLetter = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		char checkDigit = numLetter.charAt(mod);

		Result.FunctionResult.put(ResultIndex, String.valueOf(checkDigit));

		return Result;

	}
	
	

	//creator:SQ
	public static FunctionResult BusinessAdminis_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		String RandomResults = "";
		Random ran = new Random();
		// 选择是否查表
		int selectNum = ran.nextInt(3510);
		if (selectNum == 0) {
			RandomResults = "100000";
		} else {
			// 求表长
			int length = RecoDaoRandom.getLengthPublicFunction(
					RecoUtilRandom.BusinessAdminis_TABLE,
					RecoDaoRandom.hashMapBusinessAdminis);

			// 得到随机index的code
			Random random = new Random();
			int RandomIndex = 0;
			RandomIndex = random.nextInt(length);
			RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
					RecoUtilRandom.BusinessAdminis_TABLE, RandomIndex,
					RecoDaoRandom.hashMapBusinessAdminis);
		}

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		// to do something here!!!

		return Result;

	}

	//creator:SQ
	public static FunctionResult BussManaCheck_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		// to do something here!!!
		String[] splitResultIndex = index.split(";");
		String[] splitIndex = splitResultIndex[0].split(",");
		int ResultIndex = Integer.parseInt(splitResultIndex[1]);

		int length = Result.nSize - 1;

		char[] IDstr = new char[length];

		for (int i = 0; i < length; i++) {

			int Index=Integer.parseInt(splitIndex[i]);
			IDstr[i] = Result.FunctionResult.get(Index).charAt(0);
		}

		int[] a = new int[15];
		for (int i = 0; i < 14; i++) {
			a[14 - i] = IDstr[i] - 48;
		}
		int[] p = new int[15];
		int[] s = new int[15];
		p[0] = 10;
		for (int i = 0; i < 14; i++) {
			s[i] = p[i] % 11 + a[14 - i];
			p[i + 1] = (s[i] % 10) * 2;

			if (s[i] % 10 == 0) {
				p[i + 1] = 20;
			}
		}
		p[14] = (s[13] % 10) * 2;
        
		//int checkNum = 10 - (p[14] % 11 - 1) % 10;
		int checkNum=0;
		
		   checkNum = 11-p[14]%11;
		   if(checkNum==10)
			   checkNum=0;
		   if(checkNum==11)
			   checkNum=1;
		
		  
		Result.FunctionResult.put(ResultIndex, String.valueOf(checkNum));

		return Result;

		
	}

	//creator:SQ
	public static FunctionResult JadejewelryMaterialclassif_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.JadejewelryMaterialclassif_TABLE,
				RecoDaoRandom.hashMapJadejewelryMaterialclassif);

		// 得到随机index的code
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.JadejewelryMaterialclassif_TABLE, RandomIndex,
				RecoDaoRandom.hashMapJadejewelryMaterialclassif);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;

	}

	//creator:SQ
	public static FunctionResult TwoOrThree_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String res = "";
		String[] splitIndex = index.split(",");
		int beginIndex = Integer.valueOf(splitIndex[0]);
		Random ran = new Random();
		int randomNum = 0;
		int length = Result.nSize;

		if (length == 15) {
			randomNum = ran.nextInt(99) + 1;
			res = res + String.valueOf(randomNum / 10);
			res = res + String.valueOf(randomNum % 10);
		} else {
			randomNum = ran.nextInt(999) + 1;
			res = res + String.valueOf(randomNum / 100);
			randomNum = randomNum % 100;
			res = res + String.valueOf(randomNum / 10);
			res = res + String.valueOf(randomNum % 10);

		}
		// 保存结果
		for (int i = beginIndex; i < length; i++) {
			Result.FunctionResult.put(i, String.valueOf(res.charAt(i
					- beginIndex)));

		}

		return Result;

	}

	//creator:SQ
	public static FunctionResult ConstructionProducts_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.ConstructionProducts_TABLE,
				RecoDaoRandom.hashMapConstructionProducts);

		// 得到随机index的code
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.ConstructionProducts_TABLE, RandomIndex,
				RecoDaoRandom.hashMapConstructionProducts);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;

	}

	//creator:SQ
	public static FunctionResult A2EOrNull_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(";");
		String[] splitInputIndex = splitIndex[0].split(",");
		int InputIndex = Integer.parseInt(splitInputIndex[0]);
		int OutputIndex = InputIndex + 1;

		// to do something here!!!
		int LengthOfCode = InPutResult.nSize;
		int Index0 = Integer.parseInt(splitInputIndex[0]);

		int flag = LengthOfCode - 1 - InputIndex;

		if (flag == 1) {
			char RegexChar = ' ';
			char[] RandomBox = { 'A', 'B', 'C', 'D', 'E' };
			RegexChar = RandomChar.randomizeArray(RandomBox, RandomBox.length);
			Result.FunctionResult.put(LengthOfCode - 1, String
					.valueOf(RegexChar));
		}

		return Result;
	}

	//creator:SQ
	public static FunctionResult HighwayMaintenance4_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.HighwayMaintenance4_TABLE,
				RecoDaoRandom.hashMapHighwayMaintenance4);

		// 得到随机index的code
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.HighwayMaintenance4_TABLE, RandomIndex,
				RecoDaoRandom.hashMapHighwayMaintenance4);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;

	}

	//creator:SQ
	public static FunctionResult OneTO15_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		int randomIndex = 0;
		String res = "";

		String[] RandomNum = { "01", "02", "03", "04", "05", "06", "07", "08",
				"09", "10", "11", "12", "13", "14", "15", "99" };
		randomIndex = RandomChar.generateRandomInt(RandomNum.length);
		res = RandomNum[randomIndex];

		// 保存结果
		Result.FunctionResult.put(Integer.parseInt(splitIndex[0]), String
				.valueOf(res.charAt(0)));
		Result.FunctionResult.put(Integer.parseInt(splitIndex[1]), String
				.valueOf(res.charAt(1)));

		return Result;

	}
	
	public static FunctionResult OneTO15No99_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		String[] randomresult = { "01", "02", "03", "04", "05", "06", "07", "08",
				"09", "10", "11", "12", "13", "14", "15"};
		int maxlength = randomresult.length;
		int indexlength = RandomChar.generateRandomInt(maxlength);
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(randomresult[indexlength].charAt(i)));
		}
		return Result;
	}

	//creator:SQ
	public static FunctionResult PowerMaterials53_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.PowerMaterials53_TABLE,
				RecoDaoRandom.hashMapPowerMaterials53);

		// 得到随机index的code
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.PowerMaterials53_TABLE, RandomIndex,
				RecoDaoRandom.hashMapPowerMaterials53);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		return Result;

	}

	//creator:SQ
	public static FunctionResult PowerMaterials54_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.PowerMaterials54_TABLE,
				RecoDaoRandom.hashMapPowerMaterials54);

		// 得到随机index的code
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.PowerMaterials54_TABLE, RandomIndex,
				RecoDaoRandom.hashMapPowerMaterials54);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		return Result;

	}

	//creator:SQ
	public static FunctionResult PowerMaterials51_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();

		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.PowerMaterials51_TABLE,
				RecoDaoRandom.hashMapPowerMaterials51);

		// 得到随机index的code
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.PowerMaterials51_TABLE, RandomIndex,
				RecoDaoRandom.hashMapPowerMaterials51);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}

	//creator:SQ
	public static FunctionResult PowerMaterials52_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.PowerMaterials52_TABLE,
				RecoDaoRandom.hashMapPowerMaterials52);

		// 得到随机index的code
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.PowerMaterials52_TABLE, RandomIndex,
				RecoDaoRandom.hashMapPowerMaterials52);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;

	}

	//creator:SQ
	public static FunctionResult PowerMaterials49_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.PowerMaterials49_TABLE,
				RecoDaoRandom.hashMapPowerMaterials49);

		// 得到随机index的code
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.PowerMaterials49_TABLE, RandomIndex,
				RecoDaoRandom.hashMapPowerMaterials49);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;

	}

	//creator:SQ
	public static FunctionResult PowerMaterials50_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();

		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.PowerMaterials50_TABLE,
				RecoDaoRandom.hashMapPowerMaterials50);

		// 得到随机index的code
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.PowerMaterials50_TABLE, RandomIndex,
				RecoDaoRandom.hashMapPowerMaterials50);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;

	}

	//creator:SQ
	public static FunctionResult TreeDiseaseCode_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.TreeDiseaseCode_TABLE, RecoDaoRandom.hashMapTreeDiseaseCode);

		// 得到随机index的code
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.TreeDiseaseCode_TABLE, RandomIndex,
				RecoDaoRandom.hashMapTreeDiseaseCode);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;

	}

	//creator:SQ
	public static FunctionResult RailwayStationCode_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.RailwayStationCode_TABLE,
				RecoDaoRandom.hashMapRailwayStationCode);

		// 得到随机index的code
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.RailwayStationCode_TABLE, RandomIndex,
				RecoDaoRandom.hashMapRailwayStationCode);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;

	}
	//creator:SQ
	public static FunctionResult DZClassify710_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.DZClassify710_TABLE, RecoDaoRandom.hashMapDZClassify710);

		// 得到随机index的code
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.DZClassify710_TABLE, RandomIndex,
				RecoDaoRandom.hashMapDZClassify710);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		return Result;

	}

	//creator:SQ
	public static FunctionResult FoodAccount_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.FoodAccount_TABLE, RecoDaoRandom.hashMapFoodAccount);

		// 得到随机index的code
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.FoodAccount_TABLE, RandomIndex,
				RecoDaoRandom.hashMapFoodAccount);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		return Result;

	}

	//creator:SQ
	public static FunctionResult GeneralManufacturingProcess_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.GeneralManufacturingProcess_TABLE,
				RecoDaoRandom.hashMapGeneralManufacturingProcess);

		// 得到随机index的code
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.GeneralManufacturingProcess_TABLE, RandomIndex,
				RecoDaoRandom.hashMapGeneralManufacturingProcess);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		return Result;

	}

	//creator:SQ
	public static FunctionResult Machinery9_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(RecoUtilRandom.Machinery9_TABLE,
				RecoDaoRandom.hashMapMachinery9);

		// 得到随机index的code
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.Machinery9_TABLE, RandomIndex,
				RecoDaoRandom.hashMapMachinery9);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;

	}

	//creator:SQ
	public static FunctionResult tabaccoC_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(RecoUtilRandom.tabaccoC_TABLE,
				RecoDaoRandom.hashMaptabaccoC);

		// 得到随机index的code
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.tabaccoC_TABLE, RandomIndex, RecoDaoRandom.hashMaptabaccoC);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}

	//creator:SQ
	public static FunctionResult TobaccoTech_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		int randomIndex = 0;
		String res = "";

		String[] RandomNum = { "11", "12", "30", "31", "32", "44", "45", "33",
				"40", "41", "42", "43", "46", "47", "48", "50", "60" };
		randomIndex = RandomChar.generateRandomInt(RandomNum.length);
		res = RandomNum[randomIndex];

		// 保存结果
		Result.FunctionResult.put(Integer.parseInt(splitIndex[0]), String
				.valueOf(res.charAt(0)));
		Result.FunctionResult.put(Integer.parseInt(splitIndex[1]), String
				.valueOf(res.charAt(1)));

		return Result;

	}

	//creator:SQ
	public static FunctionResult HighwayDatabase6_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		int randomIndex = 0;
		String res = "";

		String[] RandomNum = { "10", "11", "20", "21", "30", "31", "32", "33",
				"34", "35", "36", "40", "41", "50", "51", "60", "61", "70",
				"71", "72", "73", "74", "75", "76", "90", "91" };
		randomIndex = RandomChar.generateRandomInt(RandomNum.length);
		res = RandomNum[randomIndex];

		// 保存结果
		Result.FunctionResult.put(Integer.parseInt(splitIndex[0]), String
				.valueOf(res.charAt(0)));
		Result.FunctionResult.put(Integer.parseInt(splitIndex[1]), String
				.valueOf(res.charAt(1)));
		return Result;

	}

	//creator:SQ
	public static FunctionResult CoastalAdminAreaId_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.CoastalAdminAreaId_TABLE,
				RecoDaoRandom.hashMapCoastalAdminAreaId);

		// 得到随机index的code
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.CoastalAdminAreaId_TABLE, RandomIndex,
				RecoDaoRandom.hashMapCoastalAdminAreaId);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		return Result;

	}

	//creator:SQ
	public static FunctionResult OneTO11and90_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		int randomIndex = 0;
		String res = "";

		String[] RandomNum = { "01", "02", "03", "04", "05", "06", "07", "08",
				"09", "10", "11", "90" };
		randomIndex = RandomChar.generateRandomInt(RandomNum.length);
		res = RandomNum[randomIndex];

		// 保存结果
		Result.FunctionResult.put(Integer.parseInt(splitIndex[0]), String
				.valueOf(res.charAt(0)));
		Result.FunctionResult.put(Integer.parseInt(splitIndex[1]), String
				.valueOf(res.charAt(1)));
		return Result;

	}

	public static FunctionResult HighwayTransportation_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.HighwayTransportation_TABLE,
				RecoDaoRandom.hashMapHighwayTransportation);

		// 得到随机index的code
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.HighwayTransportation_TABLE, RandomIndex,
				RecoDaoRandom.hashMapHighwayTransportation);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		return Result;
	}

	//creator:SQ
	public static FunctionResult HighwayTransportation4b9_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.HighwayTransportation4b9_TABLE,
				RecoDaoRandom.hashMapHighwayTransportation4b9);

		// 得到随机index的code
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.HighwayTransportation4b9_TABLE, RandomIndex,
				RecoDaoRandom.hashMapHighwayTransportation4b9);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		return Result;

	}

	//creator:SQ
	public static FunctionResult HighwayTransportation4c3_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.HighwayTransportation4c3_TABLE,
				RecoDaoRandom.hashMapHighwayTransportation4c3);

		// 得到随机index的code
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.HighwayTransportation4c3_TABLE, RandomIndex,
				RecoDaoRandom.hashMapHighwayTransportation4c3);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;

	}

	//creator:SQ
	public static FunctionResult PowerMaterials46_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.PowerMaterials46_TABLE,
				RecoDaoRandom.hashMapPowerMaterials46);

		// 得到随机index的code
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.PowerMaterials46_TABLE, RandomIndex,
				RecoDaoRandom.hashMapPowerMaterials46);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;

	}

	//creator:SQ
	public static FunctionResult OneTO03_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String[] splitIndex = index.split(",");
		String[] Box = { "01", "02", "03", "99" };
		int randomIndex = RandomChar.generateRandomInt(Box.length);
		String res = Box[randomIndex];
		// 保存结果
		for (int i = 0; i < res.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(res.charAt(i)));
		}
		return Result;
	}

	//creator:SQ
	public static FunctionResult PowerMaterials45_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.PowerMaterials45_TABLE,
				RecoDaoRandom.hashMapPowerMaterials45);

		// 得到随机index的code
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.PowerMaterials45_TABLE, RandomIndex,
				RecoDaoRandom.hashMapPowerMaterials45);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		return Result;

	}

	//creator:SQ
	public static FunctionResult ParamCode43_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		int beginIndex = Integer.parseInt(splitIndex[0]);
		String res = "";
		char[] randomBox = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

		// 提取第一、二位字符，进行分支判断
		String branch = Result.FunctionResult.get(0)
				+ Result.FunctionResult.get(1);

		if (branch.equals("15"))// 生成3位数字
		{
			for (int i = 0; i < 3; i++) {
				res = res
						+ RandomChar
								.randomizeArray(randomBox, randomBox.length);
			}

		} else if (branch.equals("09") || branch.equals("10")
				|| branch.equals("11") || branch.equals("13"))// 生成5位数字
		{
			for (int i = 0; i < 5; i++) {
				res = res
						+ RandomChar
								.randomizeArray(randomBox, randomBox.length);
			}
		} else if (branch.equals("01") || branch.equals("06")
				|| branch.equals("12"))// 生成6位数字
		{
			for (int i = 0; i < 6; i++) {
				res = res
						+ RandomChar
								.randomizeArray(randomBox, randomBox.length);
			}
		} else if (branch.equals("02") || branch.equals("03")
				|| branch.equals("04") || branch.equals("05")
				|| branch.equals("07") || branch.equals("08"))// 生成7位数字
		{
			for (int i = 0; i < 7; i++) {
				res = res
						+ RandomChar
								.randomizeArray(randomBox, randomBox.length);
			}
		} else// 生成8位数字
		{
			for (int i = 0; i < 8; i++) {
				res = res
						+ RandomChar
								.randomizeArray(randomBox, randomBox.length);
			}
		}

		// 保存结果
		for (int i = beginIndex; i < beginIndex + res.length(); i++) {
			Result.FunctionResult.put(i, String.valueOf(res.charAt(i
					- beginIndex)));
		}

		return Result;
		
	}

	//creator:SQ
	public static FunctionResult PowerMaterials44_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		int beginIndex = Integer.parseInt(splitIndex[0]);
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.PowerMaterials44_TABLE,
				RecoDaoRandom.hashMapPowerMaterials44);

		// 得到随机index的code
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.PowerMaterials44_TABLE, RandomIndex,
				RecoDaoRandom.hashMapPowerMaterials44);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		return Result;

	}

	//creator:SQ
	public static FunctionResult ParamCode41_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		String res = "";
		int beginIndex = Integer.parseInt(splitIndex[0]);
		char[] randomBox = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

		// 取首位字符进行分支判断
		char Index0 = Result.FunctionResult.get(0).charAt(0);

		// 当首位为‘1’或‘2’时，随机生成后面6位
		if (Index0 == '1' || Index0 == '2') {
			for (int i = 0; i < 6; i++) {
				res = res
						+ RandomChar
								.randomizeArray(randomBox, randomBox.length);
			}
		}

		// 当首位为‘3’时，随机生成后面7位
		else {
			for (int i = 0; i < 7; i++) {
				res = res
						+ RandomChar
								.randomizeArray(randomBox, randomBox.length);
			}
		}

		// 保存结果
		for (int i = beginIndex; i < beginIndex + res.length(); i++) {
			Result.FunctionResult.put(i, String.valueOf(res.charAt(i
					- beginIndex)));
		}
		return Result;
	
	}

	//creator:SQ
	public static FunctionResult FireTrainCode_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		List<String> ls = new ArrayList<String>();
		String[] splitIndex = index.split(",");
		int beginIndex = Integer.parseInt(splitIndex[0]);

		// 010000 010100 010200 010300 010400 010500 010600 019900
		for (int i = 100; i <= 106; i++) {
			ls.add("0"+String.valueOf(i)+"00");
		}
		ls.add("019900");
		

		// 020000 020100-020110
		ls.add("020000");
        for (int i = 20100; i <= 20110; i++) {
			ls.add("0"+String.valueOf(i));
		}
      
		
		// 020200-020203
		for (int i = 20200; i <= 20203; i++) {
			ls.add("0"+String.valueOf(i));
		}
		
		
		// 020300-020302 029900
		for (int i = 20300; i <= 20302; i++) {
			ls.add("0"+String.valueOf(i));
		}
		ls.add("029900");
		
		// 030000 030100-030114
		ls.add("030000");
		for (int i = 30100; i <= 30114; i++) {
			ls.add("0"+String.valueOf(i));
		}
		
		

		// 030200-030203
		for (int i = 30200; i <= 30203; i++) {
			ls.add("0"+String.valueOf(i));
		}
		
		// 030300-030302 039900
		for (int i = 30300; i <= 30302; i++) {
			ls.add("0"+String.valueOf(i));
		}
		ls.add("039900");
		

		// 040000 040100 ...040700  049900 990000
		for (int i = 40000; i <=40700 ; i=i+100) {
			ls.add("0"+String.valueOf(i));
		}
		ls.add("049900");
		ls.add("990000");
	
		

		String RandomResults = ls.get(RandomChar.generateRandomInt(ls.size()));
       
		// 保存结果
		for (int i = beginIndex; i < beginIndex + RandomResults.length(); i++) {
			Result.FunctionResult.put(i, String.valueOf(RandomResults.charAt(i
					- beginIndex)));
		}
        return Result;
		
	}

	//creator:SQ
	public static FunctionResult projectbuild_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		int randomIndex = 0;
		String res = "";

		String[] RandomNum = { "A1", "A2", "A3", "B1", "C1", "D1", "E1", "F1",
				"G1", "H1", "J1", "K1", "L1", "M1", "N1", "P1", "Q1", "R1" };
		randomIndex = RandomChar.generateRandomInt(RandomNum.length);
		res = RandomNum[randomIndex];

		// 保存结果
		Result.FunctionResult.put(Integer.parseInt(splitIndex[0]), String
				.valueOf(res.charAt(0)));
		Result.FunctionResult.put(Integer.parseInt(splitIndex[1]), String
				.valueOf(res.charAt(1)));

		return Result;

	}

	//creator:SQ
	public static FunctionResult OceanInfoMid_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		int randomNum = RandomChar.generateRandomInt(14) + 1;
		Result.FunctionResult.put(Integer.parseInt(splitIndex[0]), String
				.valueOf(randomNum / 10));
		Result.FunctionResult.put(Integer.parseInt(splitIndex[1]), String
				.valueOf(randomNum % 10));
		return Result;

	}

	//creator:SQ
	public static FunctionResult MaintenanceSystemPTwo_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		int beginIndex = Integer.parseInt(splitIndex[0]);
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.MaintenanceSystemPTwo_TABLE,
				RecoDaoRandom.hashMapMaintenanceSystemPTwo);

		// 得到随机index的code
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.MaintenanceSystemPTwo_TABLE, RandomIndex,
				RecoDaoRandom.hashMapMaintenanceSystemPTwo);
		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		return Result;

	}

	//creator:SQ
	public static FunctionResult ProtectionDegreeRegex_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		int beginIndex = Integer.parseInt(splitIndex[0]);
		
		//将26个大写字母存进list
		List<String> ls = new ArrayList<String>();
		for (int i = 0; i <26; i++) 
		{
			ls.add(String.valueOf((char)(i+65)));
		}
	
		String res = "";
		String temp="";
		int length = Result.nSize - 4;
		
		//产生随机不重复大写字母
		for (int i = 0; i < length; i++) {
			temp=ls.get(RandomChar.generateRandomInt(ls.size()));
			res=res+temp;
			ls.remove(temp);
		}
		
		//排序
		char[]result=res.toCharArray();
		Arrays.sort(result);
		
		// 保存结果
		for (int i = beginIndex; i < Result.nSize; i++) {
			Result.FunctionResult.put(i, String.valueOf(result[i-beginIndex]));
		}

		return Result;
		
	}

	//creator:SQ
	public static FunctionResult Plus_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		int Index = Integer.parseInt(index);
		String res = "+";
		Result.FunctionResult.put(Index, res);

		return Result;

	}

	//creator:SQ
	public static FunctionResult HighwayDatabase71_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		int beginIndex = Integer.parseInt(splitIndex[0]);
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.HighwayDatabase71_TABLE,
				RecoDaoRandom.hashMapHighwayDatabase71);

		// 得到随机index的code
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.HighwayDatabase71_TABLE, RandomIndex,
				RecoDaoRandom.hashMapHighwayDatabase71);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		return Result;

	}

	//creator:SQ
	public static FunctionResult HighwayDatabase70_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		int randomIndex = 0;
		String res = "";

		String[] RandomNum = { "43", "44", "45", "46", "47", "48", "49", "50",
				"51", "52", "53" };
		randomIndex = RandomChar.generateRandomInt(RandomNum.length);
		res = RandomNum[randomIndex];

		// 保存结果
		Result.FunctionResult.put(Integer.parseInt(splitIndex[0]), String
				.valueOf(res.charAt(0)));
		Result.FunctionResult.put(Integer.parseInt(splitIndex[1]), String
				.valueOf(res.charAt(1)));

		return Result;

	}

	//creator:SQ
	public static FunctionResult ExplosiveCivilian_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		int randomIndex = 0;
		String res = "";

		String[] RandomNum = { "01", "02", "03", "04", "05", "06", "99" };
		randomIndex = RandomChar.generateRandomInt(RandomNum.length);
		res = RandomNum[randomIndex];

		// 保存结果
		Result.FunctionResult.put(Integer.parseInt(splitIndex[0]), String
				.valueOf(res.charAt(0)));
		Result.FunctionResult.put(Integer.parseInt(splitIndex[1]), String
				.valueOf(res.charAt(1)));
		return Result;

	}

	//creator:SQ
	public static FunctionResult ProductThreeByte_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");

		// 产生010-999的数字
		int randomNum = RandomChar.generateRandomInt(990) + 10;

		// 保存结果
		String res0 = String.valueOf(randomNum / 100);
		Result.FunctionResult.put(Integer.parseInt(splitIndex[0]), res0);
		randomNum = randomNum % 100;
		String res1 = String.valueOf(randomNum / 10);
		Result.FunctionResult.put(Integer.parseInt(splitIndex[1]), res1);
		String res2 = String.valueOf(randomNum % 10);
		Result.FunctionResult.put(Integer.parseInt(splitIndex[2]), res2);

		return Result;

	}

	//creator:SQ
	public static FunctionResult ElectricPowerGeography_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		int beginIndex = Integer.parseInt(splitIndex[0]);
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.ElectricPowerGeography_TABLE,
				RecoDaoRandom.hashMapElectricPowerGeography);

		// 得到随机index的code
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.ElectricPowerGeography_TABLE, RandomIndex,
				RecoDaoRandom.hashMapElectricPowerGeography);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		return Result;

	}

	//creator:SQ
	public static FunctionResult GrainEstablishment_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		int beginIndex = Integer.parseInt(splitIndex[0]);
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.GrainEstablishment_TABLE,
				RecoDaoRandom.hashMapGrainEstablishment);

		// 得到随机index的code
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.GrainEstablishment_TABLE, RandomIndex,
				RecoDaoRandom.hashMapGrainEstablishment);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		return Result;

	}

	//creator:SQ
	public static FunctionResult CheckCodebarcode_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String[] splitResultIndex = index.split(";");
		String[] splitIndex = splitResultIndex[0].split(",");
		int ResultIndex = Integer.parseInt(splitResultIndex[1]);
		int sum = 0;
		int sum_even = 0;
		int sum_odd = 0;
		char code = ' ';

		int[] IDstr = new int[Result.nSize - 1];

		// 将字符型转为数字并且计算和
		for (int i = 0; i < Result.nSize - 1; i++) {
			IDstr[i] = Integer.parseInt(Result.FunctionResult.get(Integer
					.parseInt(splitIndex[i])));
			if (i % 2 == 1) {
				sum_even = sum_even + IDstr[i];
			} else {
				sum_odd = sum_odd + IDstr[i];
			}
		}
		sum_even = sum_even * 3;
		sum = sum_even + sum_odd;
		int temp;
		temp = (sum / 10 + 1) * 10 - sum;

		// dd = sum / 10;
		// dd += 1;
		// dd = dd * 10;
		// code = dd - sum;

		if (temp == 10) {
			code = '0';
		} else {
			code = (char) (temp + 48);
		}

		// 保存结果
		Result.FunctionResult.put(ResultIndex, String.valueOf(code));

		return Result;

	}

	//creator:SQ
	public static FunctionResult MusicCheck_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String[] splitResultIndex = index.split(";");
		String[] splitIndex = splitResultIndex[0].split(",");
		int ResultIndex = Integer.parseInt(splitResultIndex[1]);
		int beginIndex = Integer.parseInt(splitIndex[1]);
		int endIndex = Integer.parseInt(splitIndex[splitIndex.length - 1]);
		int LenIndex = splitIndex.length;
		int sum = 0;

		char code = ' ';

		int[] IDstr = new int[Result.nSize - 1];

		// 将字符型转为数字并且计算和
		int ParaIndex = 0;
		for (int i = 1; i < LenIndex; i++) {
			ParaIndex = Integer.parseInt(splitIndex[i]);
			IDstr[ParaIndex] = Integer.parseInt(Result.FunctionResult
					.get(ParaIndex));

			if (i % 9 != 0) {
				sum += (int) (IDstr[ParaIndex]) * (i % 9);

			} else {
				sum += (int) (IDstr[ParaIndex]) * 9;

			}
		}

		// 当i=0时，对应的字符为’T‘,设其值为1，并且权重为1
		sum = sum + 1;
		sum = 10 - sum % 10;
		if (sum == 10)
			sum = 0;

		code = (char) (sum + 48);
		// 保存结果
		Result.FunctionResult.put(ResultIndex, String.valueOf(code));
		return Result;

	}

	//creator:SQ
	public static FunctionResult CigaOrgCode_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		int randomIndex = 0;
		String res = "";

		String[] RandomNum = { "10", "11", "12", "13", "14", "15", "16", "17",
				"18", "19", "20", "21", "22", "23", "99" };
		randomIndex = RandomChar.generateRandomInt(RandomNum.length);
		res = RandomNum[randomIndex];

		// 保存结果
		Result.FunctionResult.put(Integer.parseInt(splitIndex[0]), String
				.valueOf(res.charAt(0)));
		Result.FunctionResult.put(Integer.parseInt(splitIndex[1]), String
				.valueOf(res.charAt(1)));

		return Result;

	}

	//creator:SQ
	public static FunctionResult ParamCode30_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		int beginIndex = Integer.parseInt(splitIndex[0]);
		String res = "";
		char[] randomBox = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

		// 提取第一、二位字符，进行分支判断
		String branch = Result.FunctionResult.get(0)
				+ Result.FunctionResult.get(1);

		if (branch.equals("05"))// 生成2位数字
		{
			for (int i = 0; i < 2; i++) {
				res = res
						+ RandomChar
								.randomizeArray(randomBox, randomBox.length);
			}

		} else if (branch.equals("06"))// 生成3位数字
		{
			for (int i = 0; i < 3; i++) {
				res = res
						+ RandomChar
								.randomizeArray(randomBox, randomBox.length);
			}
		} else if (branch.equals("09") || branch.equals("10")
				|| branch.equals("11") || branch.equals("12")) {
			for (int i = 0; i < 4; i++) {
				res = res
						+ RandomChar
								.randomizeArray(randomBox, randomBox.length);
			}
		} else if (branch.equals("07") || branch.equals("08"))// 生成6位数字
		{
			for (int i = 0; i < 5; i++) {
				res = res
						+ RandomChar
								.randomizeArray(randomBox, randomBox.length);
			}
		} else if (branch.equals("02"))// 生成7位数字
		{
			for (int i = 0; i < 6; i++) {
				res = res
						+ RandomChar
								.randomizeArray(randomBox, randomBox.length);
			}
		} else if (branch.equals("01") || branch.equals("03"))// 生成8位数字
		{
			for (int i = 0; i < 7; i++) {
				res = res
						+ RandomChar
								.randomizeArray(randomBox, randomBox.length);
			}
		}

		else {
			for (int i = 0; i < 8; i++) {
				res = res
						+ RandomChar
								.randomizeArray(randomBox, randomBox.length);
			}

		}

		// 保存结果
		for (int i = beginIndex; i < beginIndex + res.length(); i++) {
			Result.FunctionResult.put(i, String.valueOf(res.charAt(i
					- beginIndex)));
		}
		return Result;

	}

	//creator:SQ
	public static FunctionResult HighwayMaintenance3_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		int beginIndex = Integer.parseInt(splitIndex[0]);
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.HighwayMaintenance3_TABLE,
				RecoDaoRandom.hashMapHighwayMaintenance3);

		// 得到随机index的code
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.HighwayMaintenance3_TABLE, RandomIndex,
				RecoDaoRandom.hashMapHighwayMaintenance3);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		return Result;

	}

	//creator:SQ
	public static FunctionResult Machinery2_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		int beginIndex = Integer.parseInt(splitIndex[0]);
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(RecoUtilRandom.Machinery2_TABLE,
				RecoDaoRandom.hashMapMachinery2);

		// 得到随机index的code
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.Machinery2_TABLE, RandomIndex,
				RecoDaoRandom.hashMapMachinery2);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		return Result;

	}

	//creator:SQ
	public static FunctionResult FoodTrade_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");

		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(RecoUtilRandom.FoodTrade_TABLE,
				RecoDaoRandom.hashMapFoodTrade);

		// 得到随机index的code
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom
				.getRandomIndexPublicFunction(RecoUtilRandom.FoodTrade_TABLE,
						RandomIndex, RecoDaoRandom.hashMapFoodTrade);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		return Result;

	}

	//creator:SQ
	public static FunctionResult GainsEquipment_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.GainsEquipment_TABLE, RecoDaoRandom.hashMapGainsEquipment);

		// 得到随机index的code
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.GainsEquipment_TABLE, RandomIndex,
				RecoDaoRandom.hashMapGainsEquipment);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		return Result;

	}

	//creator:SQ
	//modified by SQ on 0904
	public static FunctionResult NationalTrunkHighway_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		int beginIndex = Integer.parseInt(splitIndex[0]);
		int length = Result.nSize - 2;
		char[] Box = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'w' };
		char[] Box1={'1', '2', '3', '4', '5', '6', '7', '8', '9'};
		String res = "";
		if(length==1)
		{
			res=String.valueOf(RandomChar.randomizeArray(Box, Box.length));
		}
		else if(length==2)
		{
			res=String.valueOf(RandomChar.randomizeArray(Box, Box.length));
			res=res+String.valueOf(RandomChar.randomizeArray(Box, Box.length));
		}
		else
		{
			res=String.valueOf(RandomChar.randomizeArray(Box, Box.length));
			res=res+String.valueOf(RandomChar.randomizeArray(Box, Box.length));
			res=res+String.valueOf(RandomChar.randomizeArray(Box1, Box1.length));
		}
		// 保存结果
		for (int i = beginIndex; i < beginIndex + res.length(); i++) {
			Result.FunctionResult.put(i, String.valueOf(res.charAt(i
					- beginIndex)));
		}
		return Result;

	}


	//creator:SQ
	public static FunctionResult FireInfocamp_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		int beginIndex = Integer.parseInt(splitIndex[0]);
		int length = Result.nSize - 2;
		String[] Box = { "10", "11", "12", "13", "14", "15", "20", "21", "22",
				"23", "24", "25", "26", "27", "28", "29", "30", "90" };
		String res = "";

		int RandomIndex = RandomChar.generateRandomInt(Box.length);
		res = Box[RandomIndex];
		// 保存结果
		for (int i = beginIndex; i < beginIndex + res.length(); i++) {
			Result.FunctionResult.put(i, String.valueOf(res.charAt(i
					- beginIndex)));
		}
		return Result;

	}

	//creator:SQ
	public static FunctionResult RoadTransportation21_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.RoadTransportation21_TABLE,
				RecoDaoRandom.hashMapRoadTransportation21);

		// 得到随机index的code
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.RoadTransportation21_TABLE, RandomIndex,
				RecoDaoRandom.hashMapRoadTransportation21);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		return Result;
	}

	//creator:SQ
	public static FunctionResult InfectiousDiseases_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.InfectiousDiseases_TABLE,
				RecoDaoRandom.hashMapInfectiousDiseases);

		// 得到随机index的code
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.InfectiousDiseases_TABLE, RandomIndex,
				RecoDaoRandom.hashMapInfectiousDiseases);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		return Result;

	}

	//creator:SQ
	public static FunctionResult MineralRegex_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		int beginIndex = Integer.parseInt(splitIndex[0]);
		String res = "";
		char[] letterBox = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
				'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
				'W', 'X', 'Y', 'Z' };
		char[] numBox = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		int randomFlag = RandomChar.generateRandomInt(2);
		// flag=0:[A-Z]重复四次
		if (randomFlag == 0) {
			for (int i = 0; i < 4; i++) {
				res = res
						+ RandomChar
								.randomizeArray(letterBox, letterBox.length);
			}
		}

		// [0-9]*
		int randomLength = RandomChar.generateRandomInt(1020);
		for (int i = 0; i < randomLength; i++) {
			res = res + RandomChar.randomizeArray(numBox, numBox.length);
		}

		// 保存结果
		for (int i = beginIndex; i < beginIndex + res.length(); i++) {
			Result.FunctionResult.put(i, String.valueOf(res.charAt(i
					- beginIndex)));
		}

		return Result;
	}

	//creator:SQ
	public static FunctionResult FireInfoori_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		int beginIndex = Integer.parseInt(splitIndex[0]);

		String[] Box = { "200", "201", "202", "300", "301", "302", "303",
				"402", "403", "404", "500", "501", "502", "600", "601", "602",
				"603", "700", "701", "702", "199", "399", "400", "499", "599",
				"699", "900" };
		String res = "";

		int RandomIndex = RandomChar.generateRandomInt(Box.length);
		res = Box[RandomIndex];

		// 保存结果
		for (int i = beginIndex; i < beginIndex + res.length(); i++) {
			Result.FunctionResult.put(i, String.valueOf(res.charAt(i
					- beginIndex)));
		}

		return Result;
	}

	//creator:SQ
	public static FunctionResult RoadTransportation50_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.RoadTransportation50_TABLE,
				RecoDaoRandom.hashMapRoadTransportation50);

		// 得到随机index的code
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.RoadTransportation50_TABLE, RandomIndex,
				RecoDaoRandom.hashMapRoadTransportation50);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		return Result;

	}

	//creator:SQ
	public static FunctionResult RoadTransportation53_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.RoadTransportation53_TABLE,
				RecoDaoRandom.hashMapRoadTransportation53);

		// 得到随机index的code
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.RoadTransportation53_TABLE, RandomIndex,
				RecoDaoRandom.hashMapRoadTransportation53);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		return Result;

	}

	//creator:SQ
	public static FunctionResult TwobyteCode06and90_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		int beginIndex = Integer.parseInt(splitIndex[0]);

		String[] Box = { "01", "02", "03", "04", "05", "06", "90" };
		String res = "";

		int RandomIndex = RandomChar.generateRandomInt(Box.length);
		res = Box[RandomIndex];
		// 保存结果
		for (int i = beginIndex; i < beginIndex + res.length(); i++) {
			Result.FunctionResult.put(i, String.valueOf(res.charAt(i
					- beginIndex)));
		}
		return Result;

	}

	//creator:SQ
	public static FunctionResult TwobytleCode08and90_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		int beginIndex = Integer.parseInt(splitIndex[0]);

		String[] Box = { "01", "02", "03", "04", "05", "06", "07", "08", "90" };
		String res = "";

		int RandomIndex = RandomChar.generateRandomInt(Box.length);
		res = Box[RandomIndex];
		// 保存结果
		for (int i = beginIndex; i < beginIndex + res.length(); i++) {
			Result.FunctionResult.put(i, String.valueOf(res.charAt(i
					- beginIndex)));
		}
		return Result;

	}

	//creator:SQ
	public static FunctionResult FireForceCode_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		List<String> ls = new ArrayList<String>();
		String[] splitIndex = index.split(",");
		int beginIndex = Integer.parseInt(splitIndex[0]);

		// 1000,1100,1101,1102,1103,1104,1105,1199
		ls.add("1000");
		for (int i = 1100; i <= 1105; i++) {
			ls.add(String.valueOf(i));
		}
		ls.add("1199");

		// 1200-1207,1299
		for (int i = 1200; i <= 1207; i++) {
			ls.add(String.valueOf(i));
		}
		ls.add("1299");

		// 1300-1305,1399,1900
		for (int i = 1300; i <= 1305; i++) {
			ls.add(String.valueOf(i));
		}
		ls.add("1399");
		ls.add("1900");

		// 2000,2100-2108,2199
		ls.add("2000");
		for (int i = 2100; i <= 2108; i++) {
			ls.add(String.valueOf(i));
		}
		ls.add("2199");

		// 2200-2206,2299
		for (int i = 2200; i <= 2206; i++) {
			ls.add(String.valueOf(i));
		}
		ls.add("2299");

		// 2300-2302,2399
		for (int i = 2300; i <= 2302; i++) {
			ls.add(String.valueOf(i));
		}
		ls.add("2399");

		// 2400-2406,2499
		for (int i = 2400; i <= 2406; i++) {
			ls.add(String.valueOf(i));
		}
		ls.add("2499");

		// 2500-2505,2500
		for (int i = 2500; i <= 2505; i++) {
			ls.add(String.valueOf(i));
		}
		ls.add("2599");

		// 2600-2607,2699,2900
		for (int i = 2600; i <= 2607; i++) {
			ls.add(String.valueOf(i));
		}
		ls.add("2699");
		ls.add("2900");

		// 3000
		ls.add("3000");

		// 4000,4100,4200,4300,4900
		ls.add("4000");
		ls.add("4100");
		ls.add("4200");
		ls.add("4300");
		ls.add("4900");

		// 5000,5100,5200,5300,5400,5500,5900
		for (int i = 50; i <= 55; i++) {
			ls.add(String.valueOf(i)+"00");
		}
		ls.add("5900");

		// 9000,9100,9900
		ls.add("9000");
		ls.add("9100");
		ls.add("9900");

		String RandomResults = ls.get(RandomChar.generateRandomInt(ls.size()));

		// 保存结果
		for (int i = beginIndex; i < beginIndex + RandomResults.length(); i++) {
			Result.FunctionResult.put(i, String.valueOf(RandomResults.charAt(i
					- beginIndex)));
		}
		return Result;

	}

	//creator:SQ
	public static FunctionResult HighwayDatabase18_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		int beginIndex = Integer.parseInt(splitIndex[0]);

		String[] Box = { "10", "11", "12", "20", "21", "22", "23", "30", "31",
				"32", "33", "40", "41", "42", "50", "90" };
		String res = "";

		int RandomIndex = RandomChar.generateRandomInt(Box.length);
		res = Box[RandomIndex];
		// 保存结果
		for (int i = beginIndex; i < beginIndex + res.length(); i++) {
			Result.FunctionResult.put(i, String.valueOf(res.charAt(i
					- beginIndex)));
		}
		return Result;
	}

	//creator:SQ
	public static FunctionResult HighwayDatabase17_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.HighwayDatabase17_TABLE,
				RecoDaoRandom.hashMapHighwayDatabase17);

		// 得到随机index的code
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.HighwayDatabase17_TABLE, RandomIndex,
				RecoDaoRandom.hashMapHighwayDatabase17);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		return Result;
	}

	//creator:SQ
	public static FunctionResult CodeTobacco_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		int beginIndex = Integer.parseInt(splitIndex[0]);

		String[] Box = { "101", "102", "103", "104", "105", "106", "107",
				"108", "109", "111", "112", "113", "203", "301", "302", "303",
				"304", "305", "306", "307", "308", "309", "310", "311", "312",
				"313", "314", "315", "316", "317", "318", "319", "330", "331",
				"332", "333", "334", "335", "336", "337", "338", "339", "340",
				"341", "401", "402", "403", "404", "405", "406", "407", "408",
				"409", "501", "502", "503", "504", "521", "522" };
		String res = "";

		int RandomIndex = RandomChar.generateRandomInt(Box.length);
		res = Box[RandomIndex];
		// 保存结果
		for (int i = beginIndex; i < beginIndex + res.length(); i++) {
			Result.FunctionResult.put(i, String.valueOf(res.charAt(i
					- beginIndex)));
		}
		return Result;

	}

	//creator:SQ
	public static FunctionResult ModeofProduction_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		int beginIndex = Integer.parseInt(splitIndex[0]);
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.ModeofProduction_TABLE,
				RecoDaoRandom.hashMapModeofProduction);

		// 得到随机index的code
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.ModeofProduction_TABLE, RandomIndex,
				RecoDaoRandom.hashMapModeofProduction);

		// 保存结果
		for (int i = beginIndex; i < beginIndex + RandomResults.length(); i++) {
			Result.FunctionResult.put(i, String.valueOf(RandomResults.charAt(i
					- beginIndex)));
		}
		return Result;

	}

	//creator:SQ
	public static FunctionResult TerminationofPregnancy_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		int beginIndex = Integer.parseInt(splitIndex[0]);
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.TerminationofPregnancy_TABLE,
				RecoDaoRandom.hashMapTerminationofPregnancy);

		// 得到随机index的code
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.TerminationofPregnancy_TABLE, RandomIndex,
				RecoDaoRandom.hashMapTerminationofPregnancy);

		// 保存结果
		for (int i = beginIndex; i < beginIndex + RandomResults.length(); i++) {
			Result.FunctionResult.put(i, String.valueOf(RandomResults.charAt(i
					- beginIndex)));
		}
		return Result;

	}

	//creator:SQ
	public static FunctionResult MrpCheck_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitResultIndex = index.split(";");
		String[] splitIndex = splitResultIndex[0].split(",");
		int ResultIndex = Integer.parseInt(splitResultIndex[1]);
		int LenIndex = splitIndex.length;
		int[] IDStr = new int[LenIndex];
		int CheckCode = 0;
		for (int i = 0; i < LenIndex; i++) {
			//IDStr[i] = Integer.valueOf(splitIndex[i]);
			IDStr[i]=Integer.parseInt(Result.FunctionResult.get(Integer.valueOf(splitIndex[i])));
			//modified on 0915
		
			if (i % 2 == 0)
				CheckCode = CheckCode + IDStr[i];
			else{
				if(2*IDStr[i]>=10)
				{
					int temp=(2*IDStr[i])/10+(2*IDStr[i])%10;
					CheckCode = CheckCode + temp;
				}
				else
				    CheckCode = CheckCode + 2 * IDStr[i];
			}
		}
		//modified on 0915
		if(CheckCode<10)
		{
			CheckCode=CheckCode+10;
		}
		CheckCode = 10 - CheckCode % 10;
		if (CheckCode == 10)
			CheckCode = 0;
		// 保存结果
		Result.FunctionResult.put(ResultIndex, String.valueOf(CheckCode));
		return Result;
		
	}

	//creator:SQ
	public static FunctionResult EconomicCate_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		String[] splitIndex = index.split(",");
		String[] Box = { "100", "110", "120", "130", "140", "141", "142",
				"143", "149", "150", "151", "159", "160", "170", "171", "172",
				"173", "174", "175", "179", "190", "200", "210", "220", "230",
				"240", "290", "300", "310", "320", "330", "340", "390", "900" };
		String res = "";
		int RandomIndex = RandomChar.generateRandomInt(Box.length);
		res = Box[RandomIndex];
		// 保存结果
		for (int i = 0; i < res.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(res.charAt(i)));
		}
		return Result;

	}

	//creator:SQ
	public static FunctionResult TobaccoLeafForm_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		int beginIndex = Integer.parseInt(splitIndex[0]);
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.TobaccoLeafForm_TABLE, RecoDaoRandom.hashMapTobaccoLeafForm);

		// 得到随机index的code
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.TobaccoLeafForm_TABLE, RandomIndex,
				RecoDaoRandom.hashMapTobaccoLeafForm);

		// 保存结果
		for (int i = beginIndex; i < beginIndex + RandomResults.length(); i++) {
			Result.FunctionResult.put(i, String.valueOf(RandomResults.charAt(i
					- beginIndex)));
		}
		return Result;

	}

	//creator:SQ
	public static FunctionResult HighwayDatabase46_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		int beginIndex = Integer.parseInt(splitIndex[0]);
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.HighwayDatabase46_TABLE,
				RecoDaoRandom.hashMapHighwayDatabase46);

		// 得到随机index的code
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.HighwayDatabase46_TABLE, RandomIndex,
				RecoDaoRandom.hashMapHighwayDatabase46);

		// 保存结果
		for (int i = beginIndex; i < beginIndex + RandomResults.length(); i++) {
			Result.FunctionResult.put(i, String.valueOf(RandomResults.charAt(i
					- beginIndex)));
		}
		return Result;

	}

	//creator:SQ
	public static FunctionResult HealthSupervisionObject_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		int beginIndex = Integer.parseInt(splitIndex[0]);
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.HealthSupervisionObject_TABLE,
				RecoDaoRandom.hashMapHealthSupervisionObject);

		// 得到随机index的code
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.HealthSupervisionObject_TABLE, RandomIndex,
				RecoDaoRandom.hashMapHealthSupervisionObject);

		// 保存结果
		for (int i = beginIndex; i < beginIndex + RandomResults.length(); i++) {
			Result.FunctionResult.put(i, String.valueOf(RandomResults.charAt(i
					- beginIndex)));
		}
		return Result;

	}

	//creator:SQ
	public static FunctionResult FianceManage_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		List<String> ls = new ArrayList<String>();
		String[] splitIndex = index.split(",");
		int beginIndex = Integer.parseInt(splitIndex[0]);

		// i >= 41 && i <= 49
		for (int i = 41; i <= 49; i++) {
			ls.add(String.valueOf(i));
		}

		// i >= 55 && i <= 64
		for (int i = 55; i <= 64; i++) {
			ls.add(String.valueOf(i));
		}

		ls.add("75");
		ls.add("76");

		// i >= 80 && i <= 84
		for (int i = 80; i <= 84; i++) {
			ls.add(String.valueOf(i));
		}

		// (i == 10 || i == 71 || i == 87 || i == 99 || i == 20 || i == 30)
		ls.add("10");
		ls.add("71");
		ls.add("87");
		ls.add("99");
		ls.add("20");
		ls.add("30");

		String RandomResults = ls.get(RandomChar.generateRandomInt(ls.size()));

		// 保存结果
		for (int i = beginIndex; i < beginIndex + RandomResults.length(); i++) {
			Result.FunctionResult.put(i, String.valueOf(RandomResults.charAt(i
					- beginIndex)));
		}

		return Result;
	}

	//creator:SQ
	public static FunctionResult FireInfoStstion_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		int beginIndex = Integer.parseInt(splitIndex[0]);

		String[] Box = { "10", "11", "12", "20", "60", "70", "80", "90" };
		String res = "";

		int RandomIndex = RandomChar.generateRandomInt(Box.length);
		res = Box[RandomIndex];
		// 保存结果
		for (int i = beginIndex; i < beginIndex + res.length(); i++) {
			Result.FunctionResult.put(i, String.valueOf(res.charAt(i
					- beginIndex)));
		}
		return Result;

	}

	//creator:SQ
	public static FunctionResult OneTO17NO99_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		List<String> ls = new ArrayList<String>();
		String[] splitIndex = index.split(",");
		int beginIndex = Integer.parseInt(splitIndex[0]);

		// i >= 01 && i <= 9
		for (int i = 1; i <= 9; i++) {
			ls.add("0" + String.valueOf(i));
		}
		// i>=10&&i<=17
		for (int i = 10; i <= 17; i++) {
			ls.add(String.valueOf(i));
		}

		// 99
		ls.add("99");

		String RandomResults = ls.get(RandomChar.generateRandomInt(ls.size()));

		// 保存结果
		for (int i = beginIndex; i < beginIndex + RandomResults.length(); i++) {
			Result.FunctionResult.put(i, String.valueOf(RandomResults.charAt(i
					- beginIndex)));
		}
		return Result;
	}

	//creator:SQ
	public static FunctionResult DileveryPlace_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		int beginIndex = Integer.parseInt(splitIndex[0]);
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.DileveryPlace_TABLE, RecoDaoRandom.hashMapDileveryPlace);

		// 得到随机index的code
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.DileveryPlace_TABLE, RandomIndex,
				RecoDaoRandom.hashMapDileveryPlace);

		// 保存结果
		for (int i = beginIndex; i < beginIndex + RandomResults.length(); i++) {
			Result.FunctionResult.put(i, String.valueOf(RandomResults.charAt(i
					- beginIndex)));
		}
		return Result;

	}

	//creator:SQ
	public static FunctionResult InformationSafe_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		int beginIndex = Integer.parseInt(splitIndex[0]);
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.InformationSafe_TABLE, RecoDaoRandom.hashMapInformationSafe);

		// 得到随机index的code
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.InformationSafe_TABLE, RandomIndex,
				RecoDaoRandom.hashMapInformationSafe);

		// 保存结果
		for (int i = beginIndex; i < beginIndex + RandomResults.length(); i++) {
			Result.FunctionResult.put(i, String.valueOf(RandomResults.charAt(i
					- beginIndex)));
		}
		return Result;

	}

	//creator:SQ
	public static FunctionResult ClassificationOfCivilAviation_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		int beginIndex = Integer.parseInt(splitIndex[0]);
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.ClassificationOfCivilAviation_TABLE,
				RecoDaoRandom.hashMapClassificationOfCivilAviation);

		// 得到随机index的code
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.ClassificationOfCivilAviation_TABLE, RandomIndex,
				RecoDaoRandom.hashMapClassificationOfCivilAviation);

		// 保存结果
		for (int i = beginIndex; i < beginIndex + RandomResults.length(); i++) {
			Result.FunctionResult.put(i, String.valueOf(RandomResults.charAt(i
					- beginIndex)));
		}
		return Result;

	}

	//creator:SQ
	public static FunctionResult FamilyRelationship_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		int beginIndex = Integer.parseInt(splitIndex[0]);
		int length = Result.nSize;
		String res = "";
		List<String> ls = new ArrayList<String>();
		if (length == 1)// 0-8
		{
			res = String.valueOf(RandomChar.generateRandomInt(9));
		} else if (length == 2)// 1,2,10,11,12,20-97,99
		{
			for (int i = 20; i <= 97; i++) {
				ls.add(String.valueOf(i));
			}
			ls.add("01");
			ls.add("02");
			ls.add("99");
			for (int i = 10; i <= 12; i++) {
				ls.add(String.valueOf(i));
			}

			res = ls.get(RandomChar.generateRandomInt(ls.size()));
		} else if (length == 3) {

			for (int i = 20; i <= 97; i++) {
				ls.add(String.valueOf(i));
			}
			ls.add("01");
			ls.add("02");
			ls.add("99");
			for (int i = 10; i <= 12; i++) {
				ls.add(String.valueOf(i));
			}

			res = ls.get(RandomChar.generateRandomInt(ls.size()));
			res = res + String.valueOf(RandomChar.generateRandomInt(9));
		} else {
			for (int i = 20; i <= 97; i++) {
				ls.add(String.valueOf(i));
			}
			ls.add("01");
			ls.add("02");
			ls.add("99");
			for (int i = 10; i <= 12; i++) {
				ls.add(String.valueOf(i));
			}

			res = ls.get(RandomChar.generateRandomInt(ls.size()));
			for (int i = 20; i <= 97; i++) {
				ls.add(String.valueOf(i));
			}
			ls.add("01");
			ls.add("02");
			ls.add("99");
			for (int i = 10; i <= 12; i++) {
				ls.add(String.valueOf(i));
			}

			res = res + ls.get(RandomChar.generateRandomInt(ls.size()));
		}

		// 保存结果
		for (int i = beginIndex; i < beginIndex + res.length(); i++) {
			Result.FunctionResult.put(i, String.valueOf(res.charAt(i
					- beginIndex)));
		}
		return Result;
	}

	//creator:SQ
	public static FunctionResult OneTO14_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		List<String> ls = new ArrayList<String>();
		String[] splitIndex = index.split(",");
		int beginIndex = Integer.parseInt(splitIndex[0]);

		// i >= 01 && i <= 9
		for (int i = 1; i <= 9; i++) {
			ls.add("0" + String.valueOf(i));
		}
		// i>=10&&i<=14
		for (int i = 10; i <= 14; i++) {
			ls.add(String.valueOf(i));
		}

		// 99
		ls.add("98");
		ls.add("99");

		String RandomResults = ls.get(RandomChar.generateRandomInt(ls.size()));

		// 保存结果
		for (int i = beginIndex; i < beginIndex + RandomResults.length(); i++) {
			Result.FunctionResult.put(i, String.valueOf(RandomResults.charAt(i
					- beginIndex)));
		}
		return Result;

	}
	//creator:SQ
	//modified by lly on 0903
	/*
	public static FunctionResult MeasureUnit_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		int beginIndex = Integer.parseInt(splitIndex[0]);
		char[] Box = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
				'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
				'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		String res = "";
		int length = Result.nSize;
		for (int i = 0; i < length; i++) {
			res = res + RandomChar.randomizeArray(Box, Box.length);
		}
		// 保存结果
		for (int i = beginIndex; i < beginIndex + res.length(); i++) {
			Result.FunctionResult.put(i, String.valueOf(res.charAt(i
					- beginIndex)));
		}
		return Result;

	}*/
	public static FunctionResult MeasureUnit_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom recoDao = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		int beginIndex = Integer.parseInt(splitIndex[0]);
		// 求表长
		int length = recoDao.getLengthPublicFunction(
				RecoUtilRandom.measureunit_TABLE,
				recoDao.hashmapmeasureunit);

		// 得到随机index的code
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = recoDao.getRandomIndexPublicFunction(
				RecoUtilRandom.measureunit_TABLE, RandomIndex,
				recoDao.hashmapmeasureunit);

		// 保存结果
		for (int i = beginIndex; i < beginIndex + RandomResults.length(); i++) {
			Result.FunctionResult.put(i, String.valueOf(RandomResults.charAt(i
					- beginIndex)));
		}
		return Result;


	}


	//creator:SQ
	public static FunctionResult ChildrenExcrement_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		int beginIndex = Integer.parseInt(splitIndex[0]);
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.ChildrenExcrement_TABLE,
				RecoDaoRandom.hashMapChildrenExcrement);

		// 得到随机index的code
		int RandomIndex = 0;
		RandomIndex = RandomChar.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.ChildrenExcrement_TABLE, RandomIndex,
				RecoDaoRandom.hashMapChildrenExcrement);

		// 保存结果
		for (int i = beginIndex; i < beginIndex + RandomResults.length(); i++) {
			Result.FunctionResult.put(i, String.valueOf(RandomResults.charAt(i
					- beginIndex)));
		}
		return Result;

	}
	
	//creator:SQ
	public static FunctionResult ChineseGongFa_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		
		Result.FunctionResult.put(Integer.parseInt(splitIndex[0]), "公");
		Result.FunctionResult.put(Integer.parseInt(splitIndex[1]), "发");
		
		return Result;
    }
	//creator:SQ
	public static FunctionResult HighWayTransportBandC_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		int beginIndex = Integer.parseInt(splitIndex[0]);
        
		List<String> ls = new ArrayList<String>();
		//附录B
		
		//table1 10-12,20-22,29,30
		for (int i = 10; i <= 12; i++) {
			ls.add(String.valueOf(i));
		}
		for (int i = 20; i <= 22; i++) {
			ls.add(String.valueOf(i));
		}
		ls.add("29");
		ls.add("30");
		//table2 1,2
		ls.add("1");
		ls.add("2");
		//table3 1-3
		ls.add("1");
		ls.add("2");
		ls.add("3");
		//table4 1-2
		ls.add("1");
		ls.add("2");
		//table5 1-2
		ls.add("1");
		ls.add("2");
		//table6 1,2,5,6
		ls.add("1");
		ls.add("2");
		ls.add("5");
		ls.add("6");
		//table7 100 110-112 120-122 130-132 139 140 200 210 300 310 320 400 500 600 610 620
		ls.add("100");
		for (int i = 110; i <= 112; i++) {
			ls.add(String.valueOf(i));
		}
		for (int i = 120; i <= 122; i++) {
			ls.add(String.valueOf(i));
		}
		for (int i = 130; i <= 132; i++) {
			ls.add(String.valueOf(i));
		}
		ls.add("139");
		ls.add("140");
		ls.add("200");
		ls.add("210");
		ls.add("300");
		ls.add("310");
		ls.add("320");
		ls.add("400");
		ls.add("500");
		ls.add("600");
		ls.add("610");
		ls.add("620");
		//table8 1-4,9
		for (int i = 1; i <= 4; i++) {
			ls.add(String.valueOf(i));
		}
		ls.add("9");
		//table9 10-12,19-22,29,30
		for (int i = 10; i <= 12; i++) {
			ls.add(String.valueOf(i));
		}
		for (int i = 19; i <= 22; i++) {
			ls.add(String.valueOf(i));
		}
		ls.add("29");
		ls.add("30");
		//table10 1-5,9
		for (int i = 1; i <= 5; i++) {
			ls.add(String.valueOf(i));
		}
		ls.add("9");
		//table11 1-4
		for (int i = 1; i <= 4; i++) {
			ls.add(String.valueOf(i));
		}
		//table12 1-3
		for (int i = 1; i <= 3; i++) {
			ls.add(String.valueOf(i));
		}
		//table13
		ls.add("1");
		ls.add("2");
		//table14
		ls.add("1");
		//table15
		ls.add("1");
		ls.add("2");
		
		//附录C
		//table1 100 110 120 130 140-143 149 150 151 159 160 170-175 179 190 200 210 220 230
		//240 290 300 310 320 330 340 390 900
		for(int i=10;i<=13;i++)
		{
			ls.add(String.valueOf(i)+"0");
		}
		for(int i=140;i<=143;i++)
		{
			ls.add(String.valueOf(i));
		}
		ls.add("149");
		ls.add("150");
		ls.add("151");
		ls.add("159");
		ls.add("160");
		for(int i=170;i<=175;i++)
		{
			ls.add(String.valueOf(i));
		}
		ls.add("179");
		ls.add("190");
		ls.add("200");
		ls.add("210");
		ls.add("220");
		ls.add("230");
		ls.add("240");
		ls.add("290");
		ls.add("300");
		ls.add("310");
		ls.add("320");
		ls.add("330");
		ls.add("340");
		ls.add("390");
		ls.add("900");
		
		//table2 1-4 *7 *8  
		for(int i=1;i<=4;i++)
		{
			ls.add(String.valueOf(i));
		}
		ls.add("*7");
		ls.add("*8 ");
		
		//table3 0100 0200 0210 0300 0400 0500 0600 0700 0800 0810 0900 1000 1100 1200
		//1300 1400 1500 1520 1600 1610 1700
		ls.add("0100");
		ls.add("0200");
		ls.add("0210");
		ls.add("0300");
		ls.add("0400");
		ls.add("0500");
		ls.add("0600");
		ls.add("0700");
		ls.add("0800");
		ls.add("0810");
		ls.add("0900");
		ls.add("1000");
		ls.add("1100");
		ls.add("1200");
		ls.add("1300");
		ls.add("1400");
		ls.add("1500");
		ls.add("1520");
		ls.add("1600");
		ls.add("1610");
		ls.add("1700");
		
		//table4 G S X Y Z
		ls.add("G");
		ls.add("S");
		ls.add("X");
		ls.add("Y");
		ls.add("Z");
		
		//table5 1-4 *7 8 9
		for(int i=1;i<=4;i++)
		{
			ls.add(String.valueOf(i));
		}
		ls.add("*7");
		ls.add("8");
		ls.add("9");
		
		//table6 10-12 20-23 30-33 40-42 *80 *90
		for(int i=10;i<=12;i++)
		{
			ls.add(String.valueOf(i));
		}
		for(int i=20;i<=23;i++)
		{
			ls.add(String.valueOf(i));
		}
		for(int i=30;i<=33;i++)
		{
			ls.add(String.valueOf(i));
		}
		for(int i=40;i<=42;i++)
		{
			ls.add(String.valueOf(i));
		}
		ls.add("*80");
		ls.add("*90");
		
		//table special
		int res=0;
		//G001-G098
		for(int i=1;i<=9;i++)
		{
			
			ls.add("G00"+String.valueOf(i));
		}
		for(int i=10;i<=98;i++)
		{
			ls.add("G0"+String.valueOf(i));
		}
		//G101-G198
		for(int i=101;i<=198;i++)
		{
			ls.add("G"+String.valueOf(i));
		}
		//G201-G298
		for(int i=201;i<=298;i++)
		{
			ls.add("G"+String.valueOf(i));
		}
		//G301-G398
		for(int i=301;i<=398;i++)
		{
			ls.add("G"+String.valueOf(i));
		}
		
		//S01-S198
		for(int i=101;i<=198;i++)
		{
			ls.add("S"+String.valueOf(i));
		}
		//S201-S298
		for(int i=201;i<=298;i++)
		{
			ls.add("S"+String.valueOf(i));
		}
		//S301-S398
		for(int i=301;i<=398;i++)
		{
			ls.add("S"+String.valueOf(i));
		}
		
		//X001-X998
		for(int i=1;i<=9;i++)
		{
			ls.add("X00"+String.valueOf(i));
		}
		for(int i=10;i<=99;i++)
		{
			ls.add("X0"+String.valueOf(i));
		}
		for(int i=100;i<=998;i++)
		{
			ls.add("X"+String.valueOf(i));
		}
		
		//Y001-Y998
		for(int i=1;i<=9;i++)
		{
			ls.add("Y00"+String.valueOf(i));
		}
		for(int i=10;i<=99;i++)
		{
			ls.add("Y0"+String.valueOf(i));
		}
		for(int i=100;i<=998;i++)
		{
			ls.add("Y"+String.valueOf(i));
		}
		
		//Z001-Z998
		for(int i=1;i<=9;i++)
		{
			ls.add("Z00"+String.valueOf(i));
		}
		for(int i=10;i<=99;i++)
		{
			ls.add("Z0"+String.valueOf(i));
		}
		for(int i=100;i<=998;i++)
		{
			ls.add("Z"+String.valueOf(i));
		}
		
		//table7 1-5,*8
		for(int i=1;i<=5;i++)
		{
			ls.add(String.valueOf(i));
		}
		ls.add("*8");
		
		//table8 1-3,*4 9
		for(int i=1;i<=3;i++)
		{
			ls.add(String.valueOf(i));
		}
		ls.add("*4");
		ls.add("9");
		
		//table9 1-4,9
		for(int i=1;i<=4;i++)
		{
			ls.add(String.valueOf(i));
		}
		ls.add("9");
		
		//table10 1-6 9
		for(int i=1;i<=6;i++)
		{
			ls.add(String.valueOf(i));
		}
		ls.add("9");
		
		//table11 1-4 9
		for(int i=1;i<=4;i++)
		{
			ls.add(String.valueOf(i));
		}
		ls.add("9");
		
		String RandomResults = ls.get(RandomChar.generateRandomInt(ls.size()));
        
		// 保存结果
		for (int i = beginIndex; i < beginIndex + RandomResults.length(); i++) {
			Result.FunctionResult.put(i, String.valueOf(RandomResults.charAt(i
					- beginIndex)));
		}
		return Result;
    }
	
	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	/**
	 * IoTID=VehicleNO_3 ALGNAME=VehicleNOWJ) PARA=2){]
	 * user:lly
	 */
	public static FunctionResult VehicleNOWJ_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";

		String[] splitIndex = index.split(",");

		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.vehiclenowj_TABLE, RecoDaoRandom.hashMaphashMapvehiclenowj);
		// Random random = new Random();
		int RandomIndex = RandomChar.generateRandomInt(length);
		RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.vehiclenowj_TABLE, RandomIndex,
				RecoDaoRandom.hashMaphashMapvehiclenowj);

		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(index), RandomResults);
		}

		Result.FunctionType = "Function";
		Result.ResultFlag = "OK";
		return Result;
	}

	/**
	 * IoTID=VehicleNO_2 ALGNAME=VehicleNOArmy) PARA=0,1){] 
	 * user:lly
	 */
	public static FunctionResult VehicleNOArmy_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");

		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.vehiclenoarmy_TABLE,
				RecoDaoRandom.hashMaphashMapvehiclenoarmy);
		// System.out.println(length);
		// Random random = new Random();
		int RandomIndex = RandomChar.generateRandomInt(length);
		RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.vehiclenoarmy_TABLE, RandomIndex,
				RecoDaoRandom.hashMaphashMapvehiclenoarmy);
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		return Result;
	}

	/**
	 * IoTID=VehicleNO_2 ALGNAME=VehicleNOArmySuffix) PARA=6,-1){]
	 * user:lly
	 */
	public static FunctionResult VehicleNOArmySuffix_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		// Random rand = new Random();

		String[] splitIndex = index.split(",");
		if (InPutResult.nSize == 7) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[0]), String
					.valueOf(RandomChar.generateRandomInt(10)));
		}
		return Result;
	}

	/**
	 * IoTID=DL/T_700.1-1999_55 ALGNAME=OneTO10No99) PARA=0,1){] 
	 * user:lly
	 */

	public static FunctionResult OneTO10No99_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		String[] randomresult = { "01", "02", "03", "04", "05", "06", "07",
				"08", "09", "10" };
		int maxlength = randomresult.length;
		int indexlength = RandomChar.generateRandomInt(maxlength);
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(randomresult[indexlength].charAt(i)));
		}
		return Result;
	}

	/**
	 * IoTID=DL/T_700.1-1999_55 ALGNAME=ParamCode) PARA=6,-1){]
	 * user:lly
	 */
	public static FunctionResult ParamCode_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		// if (splitIndex[splitIndex.length-1].equals("-1")) {
		if (InPutResult.nSize == 10) {
			for (int i = Integer.parseInt(splitIndex[0]); i < InPutResult.nSize; i++) {
				if (i == InPutResult.nSize - 2) {
					Result.FunctionResult.put(i, ".");
				} else {
					Result.FunctionResult.put(i, String.valueOf(RandomChar
							.generateRandomInt(10)));
				}
			}
		}
		if (InPutResult.nSize == 11) {
			for (int i = Integer.parseInt(splitIndex[0]); i < InPutResult.nSize; i++) {
				if (i == InPutResult.nSize - 3) {
					Result.FunctionResult.put(i, ".");
				} else {
					Result.FunctionResult.put(i, String.valueOf(RandomChar
							.generateRandomInt(10)));
				}
			}
		}

		// System.out.println(Result.FunctionResult.get(7));
		return Result;
	}

	/**
	 * IoTID=GA_407.2-2003 ALGNAME=OneTO29) PARA=4,5){] 
     *user:lly
	 */
	public static FunctionResult OneTO29_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");
		StringBuffer buf = new StringBuffer();
		char[] charbox0 = { '1', '2', '0', '9' };

		char charrandom0 = RandomChar.randomizeArray(charbox0, charbox0.length);
		String stringrandom1 = "";
		if (charrandom0 == '0') {
			stringrandom1 = "123456789";
		} else if (charrandom0 == '1' || charrandom0 == '2') {
			stringrandom1 = "0123456789";
		} else if (charrandom0 == '9') {
			stringrandom1 = "9";
		}
		char[] charbox1 = stringrandom1.toCharArray();
		char charrandom1 = RandomChar.randomizeArray(charbox1, charbox1.length);
		buf.append(charrandom0).append(charrandom1);

		for (int i = 0; i < splitIndex.length; i++) {

			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), buf
					.charAt(i)
					+ "");
		}
		return Result;
	}

	/**
	 * IoTID=GA_407.2-2003 ALGNAME=ThreeByteDecimalnt) PARA=6,7,8){] 
	 * user:lly
	 */
	public static FunctionResult ThreeByteDecimalnt_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		// Random random = new Random();

		Result.FunctionResult.put(Integer.parseInt(splitIndex[0]), String
				.valueOf(RandomChar.generateRandomInt(10)));
		Result.FunctionResult.put(Integer.parseInt(splitIndex[1]), String
				.valueOf(RandomChar.generateRandomInt(10)));
		String value1 = Result.FunctionResult.get(Integer
				.parseInt(splitIndex[0]));
		String value2 = Result.FunctionResult.get(Integer
				.parseInt(splitIndex[1]));
		if (value1.equals("0") && value2.equals("0")) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[2]), String
					.valueOf(RandomChar.generateRandomInt(9) + 1));
		} else {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[2]), String
					.valueOf(RandomChar.generateRandomInt(10)));
		}
		return Result;
	}

	/**
	 * IoTID=GA_407.2-2003 ALGNAME=CountryRegionCode) PARA=0,1,2){]
	 * user:lly
	 */
	//modified by lly
	//0827
	
	/*public static FunctionResult CountryRegionCode_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");

		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		int length = RecoDaoRandom.getLengthOfcountryregioncode();
		int RandomIndex = RandomChar.generateRandomInt(length);
		RandomResults = RecoDaoRandom.getRandomIndexcountryregioncode(RandomIndex,splitIndex.length);

		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}*/
	public static FunctionResult CountryRegionCode_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");
		int beginIndex=Integer.parseInt(splitIndex[0]);
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		int length = RecoDaoRandom.getLengthOfcountryregioncode();
		int RandomIndex = RandomChar.generateRandomInt(length);
		//modified by SQ
		if(splitIndex[1].equals("-1"))
		{
			RandomResults = RecoDaoRandom.getRandomIndexcountryregioncode4all(RandomIndex);
		}
		else
		    RandomResults = RecoDaoRandom.getRandomIndexcountryregioncode(RandomIndex,splitIndex.length);
	   
		for (int i = beginIndex; i <beginIndex+RandomResults.length(); i++) {
			Result.FunctionResult.put(i, String
					.valueOf(RandomResults.charAt(i-beginIndex)));
		}

		return Result;
	}


	/**
	 * IoTID=GB/T_13774-92 ALGNAME=Weaves355) PARA=0,-1){] 
	 * user:lly
	 */

	public static FunctionResult Weaves355_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		// ([1-3][0-1]-)(([0-9][0-9]\\s*)+-){2}([0-9][0-9]\\s*)*
		StringBuffer buf = new StringBuffer(1024);
		String index0 = "123";
		String index1 = "01";
		// ([1-3][0-1]-)
		buf.append(index0.charAt(RandomChar.generateRandomInt(index0.length())));
		buf.append(index1.charAt(RandomChar.generateRandomInt(index1.length())));
		buf.append("-");
		int i = 0;
		int z = 0;
		// (([0-9][0-9]\\s*)+-){2}
		for (int k = 0; k < 2; k++) {
			for (i = 0; i <= RandomChar.generateRandomInt(16); i++) {
				buf.append(RandomChar.generateRandomInt(10)).append(
						RandomChar.generateRandomInt(10));
				for (int j = 0; j < RandomChar.generateRandomInt(8); j++) {
					buf.append(" ");
				}
			}
			buf.append("-");
		}
		if (i == 1) {
			z = 0;
		} else {
			z = 1;
		}
		// ([0-9][0-9]\\s*)*
		for (; z <= RandomChar.generateRandomInt(16); z++) {
			buf.append(RandomChar.generateRandomInt(10)).append(
					RandomChar.generateRandomInt(10));
			for (int j = 0; j < RandomChar.generateRandomInt(8); j++) {
				buf.append(" ");
			}
		}

		String resultrandom = buf.toString();
		//System.out.println(resultrandom.length());
		for (int x = 0; x < resultrandom.length(); x++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[0]) + x,
					resultrandom.charAt(x) + "");
		}
		return Result;
	}

	/**
	 * IoTID=GB/T_23560-2009 ALGNAME=clothesclass) PARA=0,-1){] 
	 * user:lly
	 */
	//modified by lly on 0917
	public static FunctionResult clothesclass_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();

		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");

		RecoDaoRandom recoDao = new RecoDaoRandom();
		int length = recoDao
				.getLengthPublicFunction(RecoUtilRandom.clothesclass_TABLE,
						RecoDaoRandom.hashMaphashMapclothesclass);
		// Random random = new Random();
		int RandomIndex = RandomChar.generateRandomInt(length);
		RandomResults = recoDao.getRandomIndexPublicFunction(
				RecoUtilRandom.clothesclass_TABLE, RandomIndex,
				RecoDaoRandom.hashMaphashMapclothesclass);
		int lenth = RandomResults.length();

		for (int i = 0; i < RandomResults.length(); i++) {
			InPutResult.FunctionResult.put(Integer.parseInt(splitIndex[0]) + i,
					String.valueOf(RandomResults.charAt(i)));

		}
		return Result;
	}



	/**
	 * IoTID=JB/T_5992.6-92 ALGNAME=Machinery6) PARA=0,1,2,3,4){]
	 * user:lly
	 */
	public static FunctionResult Machinery6_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");

		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		int length = RecoDaoRandom.getLengthPublicFunction(RecoUtilRandom.machinery6_TABLE,
				RecoDaoRandom.hashMaphashMapmachinery6);
		Random random = new Random();
		int RandomIndex = random.nextInt(length);
		RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.machinery6_TABLE, RandomIndex,
				RecoDaoRandom.hashMaphashMapmachinery6);

		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		return Result;
	}

	/**
	 * IoTID=VehicleNO_1 ALGNAME=VehicleNONormal) PARA=0,1){]
	 * user:lly
	 */

	public static FunctionResult VehicleNONormal_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");

		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.vehiclenonormal_TABLE,
				RecoDaoRandom.hashMaphashMapvehiclenonormal);
		// Random random = new Random();
		int RandomIndex = RandomChar.generateRandomInt(length);
		RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.vehiclenonormal_TABLE, RandomIndex,
				RecoDaoRandom.hashMaphashMapvehiclenonormal);

		// InPutResult.FunctionResult = new HashMap<Integer, String>();
		for (int i = 0; i < RandomResults.length(); i++) {
			InPutResult.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}

	/**
	 * IoTID=YC/T_393-2011_3 ALGNAME=TwoByteSRMXSMYZ) PARA=9,10){]
	 * user:lly
	 */
	public static FunctionResult TwoByteSRMXSMYZ_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		// String RandomResults = "";
		String[] splitIndex = index.split(",");
		String[] randomresult = { "SR", "MX", "SM", "YZ" };
		// int maxlength = randomresult.length;
		int indexlength = RandomChar.generateRandomInt(randomresult.length);
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					randomresult[indexlength].charAt(i) + "");
		}
		return Result;
	}

	/**
	 * IoTID=GB/T_15657-1995_1 ALGNAME=TCMDisease) PARA=0,1,2,3,4,5){] 
	 * user:lly
	 */
	public static FunctionResult TCMDisease_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();

		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");

		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		int length = RecoDaoRandom.getLengthPublicFunction(RecoUtilRandom.tcmdisease_TABLE,
				RecoDaoRandom.hashMaphashMaptcmdisease);
		int RandomIndex = RandomChar.generateRandomInt(length);
		RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.tcmdisease_TABLE, RandomIndex,
				RecoDaoRandom.hashMaphashMaptcmdisease);

		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		return Result;
	}

	/**
	 * IoTID=GB/T_15657-1995_2 ALGNAME=TwoByteSRMXSMYZ) PARA=0,1,2,3,4,5){] 
	 * user:lly
	 */
	public static FunctionResult TCMFeature_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");

		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		int length = RecoDaoRandom.getLengthPublicFunction(RecoUtilRandom.tcmfeature_TABLE,
				RecoDaoRandom.hashMaphashMaptcmfeature);
		Random random = new Random();
		int RandomIndex = RandomChar.generateRandomInt(length);
		RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.tcmfeature_TABLE, RandomIndex,
				RecoDaoRandom.hashMaphashMaptcmfeature);

		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		return Result;
	}

	/**
	 * IoTID=LS/T_1708.1-2004 ALGNAME=GainsProcess) PARA=0,1,2,3,4,5,6,7){]
	 * user:lly
	 */
	public static FunctionResult GainsProcess_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");

		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.grainsprocess_TABLE,
				RecoDaoRandom.hashMaphashMapgrainsprocess);

		int RandomIndex = RandomChar.generateRandomInt(length);
		RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.grainsprocess_TABLE, RandomIndex,
				RecoDaoRandom.hashMaphashMapgrainsprocess);

		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		return Result;
	}

	/**
	 * IoTID=partdraw ALGNAME=Hyphen) PARA=12){]
	 * user:lly
	 * 
	 */
	public static FunctionResult Hyphen_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		Result.FunctionResult.put(Integer.parseInt(splitIndex[0]), "-");
		return Result;
	}

	/**
	 * IoTID=partdraw ALGNAME=CarProductCompnent) PARA=13,14,15,16){]
	 * user:lly
	 */
	public static FunctionResult CarProductCompnent_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");

		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.CarProductCompnent_TABLE,
				RecoDaoRandom.hashMaphashMapCarProductCompnent);
		int RandomIndex = RandomChar.generateRandomInt(length);
		RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.CarProductCompnent_TABLE, RandomIndex,
				RecoDaoRandom.hashMaphashMapCarProductCompnent);

		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		return Result;
	}

	/**
	 * IoTID=DL/T_700.1-1999_50 ALGNAME=ParamCode7) PARA=5,6,7,8,9,10,11){]
	 * user:lly
	 */
	public static FunctionResult ParamCode7_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();

		Result = InPutResult;
		String[] splitIndex = index.split(",");
		for (int i = 0; i < splitIndex.length; i++) {
			if (i == splitIndex.length - 3) {
				Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), ".");

			} else {
				Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
						String.valueOf(RandomChar.generateRandomInt(10)));
			}
		}
		return Result;
	}

	/**
	 * IoTID=JT/T_430-2000_3 ALGNAME=CommodityName) PARA=0,1,2,3,4){]
	 * user:lly
	 */
	public static FunctionResult CommodityName_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		char[] RandomBox = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A' };
		char[] RandomBox1 = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		char charByte = RandomChar.randomizeArray(RandomBox, RandomBox.length);
		Result.FunctionResult.put(Integer.parseInt(splitIndex[0]), String
				.valueOf(charByte));
		for (String splitIndex0 : splitIndex) {
			if (!splitIndex0.equals(splitIndex[0])) {
				char charByte1 = RandomChar.randomizeArray(RandomBox1,
						RandomBox1.length);
				Result.FunctionResult.put(Integer.parseInt(splitIndex0), String
						.valueOf(charByte1));
			}

		}
		return Result;
	}

	/**
	 * IoTID=JT/T_430-2000_3 ALGNAME=PortTariff9) PARA=5,6,7,8){]
	 * user:lly
	 */
	public static FunctionResult PortTariff9_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");

		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.PortTariff9_TABLE, RecoDaoRandom.hashMaphashMapporttariff9);
		int RandomIndex = RandomChar.generateRandomInt(length);
		RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.PortTariff9_TABLE, RandomIndex,
				RecoDaoRandom.hashMaphashMapporttariff9);
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		return Result;
	}

	/**
	 * IoTID=JT/T_430-2000_3 ALGNAME==PortTariff25) PARA=0,1,2,3,4){] 
	 * user:lly
	 */
	public static FunctionResult PortTariff25_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");

		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		int length = RecoDaoRandom
				.getLengthPublicFunction(RecoUtilRandom.PortTariff25_TABLE,
						RecoDaoRandom.hashMaphashMapporttariff25);
		int RandomIndex = RandomChar.generateRandomInt(length);
		RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.PortTariff25_TABLE, RandomIndex,
				RecoDaoRandom.hashMaphashMapporttariff25);
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		return Result;
	}

	/**
	 * IoTID=JT/T_430-2000_3 ALGNAME==OneTO17) PARA=1,2){] 
	 * user:lly
	 */
	public static FunctionResult OneTO17_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();

		Result = InPutResult;
		String[] splitIndex = index.split(",");
		String[] randomresult = { "01", "02", "03", "04", "05", "06", "07",
				"08", "09", "10", "11", "12", "13", "14", "15", "16", "17" };
		int maxlength = randomresult.length;
		int indexlength = RandomChar.generateRandomInt(maxlength);
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(randomresult[indexlength].charAt(i)));
		}
		return Result;

	}

	/**
	 * IoTID=JT/T_430-2000_3 ALGNAME==OneTO12No99) PARA=3,4){] 
	 * user:lly
	 */
	public static FunctionResult OneTO12No99_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		String[] randomresult = { "01", "02", "03", "04", "05", "06", "07",
				"08", "09", "10", "11", "12" };
		int maxlength = randomresult.length;
		int indexlength = RandomChar.generateRandomInt(maxlength);
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(randomresult[indexlength].charAt(i)));
		}
		return Result;

	}

	/**
	 * IoTID=DL/T_700.1-1999_25 ALGNAME==ParamCode32) PARA=4,-1){]
	 * user:lly
	 */

	public static FunctionResult ParamCode32_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		/**String index1="";
		 if(Result.nSize==7){   
			   index1="5";
		   }else if(Result.nSize==11){
			   index1="34";
		   }else if(Result.nSize==12){
			   index1="12";
		   }
		 Result.FunctionResult.put(0, "0");
		 char []randombox=index1.toCharArray();
		 Result.FunctionResult.put(1, RandomChar.randomizeArray(randombox, randombox.length)+"");
		*/
		String value0=Result.FunctionResult.get(0);
		String value1=Result.FunctionResult.get(1);
		
		if(value0.equals("0")&&value1.equals("5")){
			Result.nSize=7;
		}else if(value0.equals("0")&&(value1.equals("3")||value1.equals("4"))){
			Result.nSize=11;
		}else if(value0.equals("0")&&(value1.equals("1")||value1.equals("2"))){
			Result.nSize=12;
		}
			for (int i = 0; i < Result.nSize - Integer.parseInt(splitIndex[0]); i++) {

				char[] RandomBox5 = { '0', '1', '2', '3', '4', '5', '6', '7',
						'8', '9' };
				char charByte5 = RandomChar.randomizeArray(RandomBox5,
						RandomBox5.length);
				Result.FunctionResult.put(Integer.parseInt(splitIndex[0]) + i,
						String.valueOf(charByte5));
		}
		return Result;
	}

	/**
	 * IoTID=DL/T_700.1-1999_26 ALGNAME==ParamCode31) PARA=4,-1){] 
	 * user:lly
	 */
	public static FunctionResult ParamCode31_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		/**String index1="";
		 if(Result.nSize==8){
			   
			   index1="58";
		   }else if(Result.nSize==9){
			   index1="369";
		   }else if(Result.nSize==10){
			   index1="7";
		   }else if(Result.nSize==11){
			   index1="4";
		   }else if(Result.nSize==12){
			   index1="12";
		   }
		 Result.FunctionResult.put(0, "0");
		 char []randombox=index1.toCharArray();
		 Result.FunctionResult.put(1, RandomChar.randomizeArray(randombox, randombox.length)+"");
		*/
		String value0=Result.FunctionResult.get(0);
		String value1=Result.FunctionResult.get(1);
		
		if(value0.equals("0")&&(value1.equals("5")||value1.equals("8"))){
			Result.nSize=8;
		}else if(value0.equals("0")&&(value1.equals("3")||value1.equals("6")||value1.equals("9"))){
			Result.nSize=9;
		}else if(value0.equals("0")&&value1.equals("7")){
			Result.nSize=10;
		}else if(value0.equals("0")&&value1.equals("4")){
			Result.nSize=11;
		}else if(value0.equals("0")&&(value1.equals("1")||value1.equals("2"))){
			Result.nSize=12;
		}
			for (int i = 0; i < Result.nSize - Integer.parseInt(splitIndex[0]); i++) {

				char[] RandomBox5 = { '0', '1', '2', '3', '4', '5', '6', '7',
						'8', '9' };
				char charByte5 = RandomChar.randomizeArray(RandomBox5,
						RandomBox5.length);
				Result.FunctionResult.put(Integer.parseInt(splitIndex[0]) + i,
						String.valueOf(charByte5));
			}
		return Result;
	}

	/**
	 * IoTID=DL/T_700.1-1999_20 ALGNAME==ParamCode38) PARA=5,-1){]
	 * user:lly
	 */
	public static FunctionResult ParamCode38_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
	/**   if(Result.nSize==12){
		   Result.FunctionResult.put(0, "3");
	   }else if(Result.nSize==14){
		   Result.FunctionResult.put(0, "1");
	   }else if(Result.nSize==15){
		   Result.FunctionResult.put(0, "2");
	   }
	   */
		String value0=Result.FunctionResult.get(0);
		if(value0.equals("3")){
			Result.nSize=12;
		}else if(value0.equals("1")){
			Result.nSize=14;
		}else if(value0.equals("2")){
			Result.nSize=15;
		}
			for (int i = 0; i < Result.nSize - Integer.parseInt(splitIndex[0]); i++) {

				char[] RandomBox5 = { '0', '1', '2', '3', '4', '5', '6', '7',
						'8', '9' };
				char charByte5 = RandomChar.randomizeArray(RandomBox5,
						RandomBox5.length);
				Result.FunctionResult.put(Integer.parseInt(splitIndex[0]) + i,
						String.valueOf(charByte5));
			}	
		return Result;
	}

	/**
	 * IoTID=DL/T_700.1-1999_22 ALGNAME==ParamCode35) PARA=4,-1){]
	 * user:lly
	 */
	//modified by lly on 0915
	public static FunctionResult ParamCode35_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		
		/**String index0="";
		 if(Result.nSize==9){   
			   index0="34";
		   }else if(Result.nSize==10){
			   index0="12";
		   }
		 char []randombox=index0.toCharArray();
		 Result.FunctionResult.put(0, RandomChar.randomizeArray(randombox, randombox.length)+"");
		*/
		String value0=Result.FunctionResult.get(0);
		//String value1=Result.FunctionResult.get(1);
		if(value0.equals("3")||value0.equals("4")){
			Result.nSize=9;
		}else if(value0.equals("1")||value0.equals("2")){
			Result.nSize=10;
		}
		
			for (int i = 0; i < Result.nSize - Integer.parseInt(splitIndex[0]); i++) {

				char[] RandomBox5 = { '0', '1', '2', '3', '4', '5', '6', '7',
						'8', '9' };
				char charByte5 = RandomChar.randomizeArray(RandomBox5,
						RandomBox5.length);
				Result.FunctionResult.put(Integer.parseInt(splitIndex[0]) + i,
						String.valueOf(charByte5));
		}
		return Result;
	}


	/**
	 * IoTID=DL/T_700.1-1999_23 ALGNAME==ParamCode34) PARA=4,-1){]
	 * user:lly
	 */
	public static FunctionResult ParamCode34_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		
		/**String index01[]={};
		 if(Result.nSize==9){   
			 index01=new String[]{"09","10","11"};
		   }else if(Result.nSize==10){
			   index01=new String[]{"08"};
		   }else if(Result.nSize==11){
			   index01=new String[]{"03","06"};
		   }else if(Result.nSize==12){
			   index01=new String[]{"05","07"};
		   }else if(Result.nSize==13){
			   index01=new String[]{"04"};
		   }else if(Result.nSize==14){
			   index01=new String[]{"02"};
		   }else if(Result.nSize==15){
			   index01=new String[]{"01"};
		   }
        String result01=index01[RandomChar.generateRandomInt(index01.length)];
        for(int j=0;j<2;j++){
        	Result.FunctionResult.put(j,result01.charAt(j)+"");
        }
        */
		String value0=Result.FunctionResult.get(0);
		String value1=Result.FunctionResult.get(1);
		
		if((value0.equals("0")&&value1.equals("9"))||(value0.equals("1")&&value1.equals("0"))||(value0.equals("1")&&value1.equals("1"))){
			Result.nSize=9;
		}else if(value0.equals("0")&&value1.equals("8")){
			Result.nSize=10;
		}else if(value0.equals("0")&&(value1.equals("3")||value1.equals("6"))){
			Result.nSize=11;
		}else if(value0.equals("0")&&(value1.equals("5")||value1.equals("7"))){
			Result.nSize=12;
		}else if(value0.equals("0")&&value1.equals("4")){
			Result.nSize=13;
		}else if(value0.equals("0")&&value1.equals("2")){
			Result.nSize=14;
		}else if(value0.equals("0")&&value1.equals("1")){
			Result.nSize=15;
		}
			for (int i = 0; i < Result.nSize - Integer.parseInt(splitIndex[0]); i++) {

				char[] RandomBox5 = { '0', '1', '2', '3', '4', '5', '6', '7',
						'8', '9' };
				char charByte5 = RandomChar.randomizeArray(RandomBox5,
						RandomBox5.length);
				Result.FunctionResult.put(Integer.parseInt(splitIndex[0]) + i,
						String.valueOf(charByte5));
		}

		return Result;
	}

	/**
	 * IoTID=GB_18240.6-2004_1 ALGNAME==DeviceMOD163)
	 * PARA=0,1,2,3,4,5,6,7,8,9,10,11){] 
	 * user:lly
	 */
	public static FunctionResult DeviceMOD163_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		double sum = 0;
		String[] splitIndex0 = index.split(";");
		String index1 = splitIndex0[0];
		String index2 = splitIndex0[1];
		String[] splitIndex = index1.split(",");
		int splitIndexLen = splitIndex.length;
		int length = Result.FunctionResult.size();
		// System.out.println(length);
		for (int i = 0; i < splitIndexLen; i++) {
			sum = sum
					+ Integer.parseInt(Result.FunctionResult.get(Integer
							.parseInt(splitIndex[i])))
					* (Math.pow(2, splitIndexLen - i) % 11);
		}
		/**
		 * for (int i = 0; i < length; i++) { sum = sum +
		 * Integer.parseInt(Result.FunctionResult.get(i)) (Math.pow(2, length -
		 * i) % 11); }
		 */

		String check;
		int mod = (int) (11 - (sum % 11));
		check = Integer.toString(mod % 10);
		Result.FunctionResult.put(Integer.parseInt(splitIndex0[1]), check);
		return Result;
	}

	/**
	 * IoTID=DL/T_700.1-1999_28 ALGNAME==ParamCode29) PARA=4,-1){]
	 * user:lly
	 */
	public static FunctionResult ParamCode29_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		/**String index1="";
		 if(Result.nSize==7){
			   
			   index1="6";
		   }else if(Result.nSize==8){
			   index1="1257";
		   }else if(Result.nSize==9){
			   index1="3";
		   }else if(Result.nSize==13){
			   index1="4";
		   }
		 Result.FunctionResult.put(0, "0");
		 char []randombox=index1.toCharArray();
		 Result.FunctionResult.put(1, RandomChar.randomizeArray(randombox, randombox.length)+"");
*/
		String value0=Result.FunctionResult.get(0);
		String value1=Result.FunctionResult.get(1);
		
		if(value0.equals("0")&&value1.equals("6")){
			Result.nSize=7;
		}else if(value0.equals("0")&&(value1.equals("1")||value1.equals("2")||value1.equals("5")||value1.equals("7"))){
			Result.nSize=8;
		}else if(value0.equals("0")&&value1.equals("3")){
			Result.nSize=9;
		}else if(value0.equals("0")&&value1.equals("4")){
			Result.nSize=13;
		}
			for (int i = 0; i < Result.nSize - Integer.parseInt(splitIndex[0]); i++) {

				char[] RandomBox5 = { '0', '1', '2', '3', '4', '5', '6', '7',
						'8', '9' };
				char charByte5 = RandomChar.randomizeArray(RandomBox5,
						RandomBox5.length);
				Result.FunctionResult.put(Integer.parseInt(splitIndex[0]) + i,
						String.valueOf(charByte5));
		}
		return Result;
	}

	/**
	 * IoTID=DL/T_700.1-1999_29 ALGNAME==ParamCode28) PARA=4,-1){]
	 * user:lly
	 */

	public static FunctionResult ParamCode28_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		/**String index1="";
		 if(Result.nSize==8){
			   
			   index1="13";
		   }else if(Result.nSize==9){
			   index1="2";
		   }
		 Result.FunctionResult.put(0, "0");
		 char []randombox=index1.toCharArray();
		
		 Result.FunctionResult.put(1, RandomChar.randomizeArray(randombox, randombox.length)+"");
		 */
		String value0=Result.FunctionResult.get(0);
		String value1=Result.FunctionResult.get(1);
		
		if(value0.equals("0")&&(value1.equals("1")||value1.equals("3"))){
			Result.nSize=8;
		}else if(value0.equals("0")&&value1.equals("2")){
			Result.nSize=9;
		}
			for (int i = 0; i < Result.nSize - Integer.parseInt(splitIndex[0]); i++) {

				char[] RandomBox5 = { '0', '1', '2', '3', '4', '5', '6', '7',
						'8', '9' };
				char charByte5 = RandomChar.randomizeArray(RandomBox5,
						RandomBox5.length);
				Result.FunctionResult.put(Integer.parseInt(splitIndex[0]) + i,
						String.valueOf(charByte5));
			}

		return Result;
	}

	//
	/**
	 * IoTID=HY/T_023-2010 ALGNAME==First2CharsofCoastalAdminAreaId) PARA=0,1){]
	 * user:lly
	 */

	public static FunctionResult First2CharsofCoastalAdminAreaId_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");

		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.CoastalAdminAreaId_TABLE,
				RecoDaoRandom.hashMaphashMapCoastalAdminAreaId);
		Random random = new Random();
		int RandomIndex = random.nextInt(length);
		RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.CoastalAdminAreaId_TABLE, RandomIndex,
				RecoDaoRandom.hashMaphashMapCoastalAdminAreaId);

		// InPutResult.FunctionResult=new HashMap<Integer,String>();
		for (int i = 0; i <  splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}

	/**
	 * IoTID=HY/T_023-2010 ALGNAME==OceanStationCode) PARA=7,8){]
	 * user:lly
	 */

	//modified by lly on 0915
	public static FunctionResult OceanStationCode_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		// String RandomResults = "";
		String[] splitIndex = index.split(",");
		String[] randomresult = { "01", "02", "03", "04", "05", "06", "07",
				"08", "09", "10","11","15", "16" };
		int maxlength = randomresult.length;
		int indexlength = RandomChar.generateRandomInt(maxlength);
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(randomresult[indexlength].charAt(i)));
		}
		return Result;
	}



	/**
	 * IoTID=DL/T_700.3-1999 (?#ALGNAME=OneTO42No99) (?#PARA=4,5){]
	 * user:lly
	 */
	public static FunctionResult OneTO42No99_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		StringBuffer buf = new StringBuffer();
		char[] charbox0 = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		char[] charbox1 = { '8', '1', '2', '3', '4', '5', '6', '7', '9' };
		char charrandom0 = RandomChar.randomizeArray(charbox0, charbox0.length);
		char charrandom1;
		if (charrandom0 == '0') {
			charrandom1 = RandomChar.randomizeArray(charbox1, charbox1.length);
		} else {
			charrandom1 = RandomChar.randomizeArray(charbox0, charbox0.length);
		}
		buf.append(charrandom0).append(charrandom1);
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), buf
					.charAt(i)
					+ "");
		}
		return Result;
	}

	/**
	 * IoTID=GB/T_18366-2001 (?#ALGNAME=InternationalShipCode)
	 * (?#PARA=3,4,5,6,7,8,9){]
	 * user:lly
	 */
	public static FunctionResult InternationalShipCode_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");

		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.InternationalShipCode_TABLE,
				RecoDaoRandom.hashMaphashMapInternationalShipCode);
		Random random = new Random();
		int RandomIndex = random.nextInt(length);
		RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.InternationalShipCode_TABLE, RandomIndex,
				RecoDaoRandom.hashMaphashMapInternationalShipCode);

		// InPutResult.FunctionResult=new HashMap<Integer,String>();

		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		return Result;
	}

	/**
	 * IoTID=GB/T_19632-2005_1 (?#ALGNAME=FuneralInterment)
	 * (?#PARA=6,7,8,9,10,11,12){]
	 * user:lly
	 */

	public static FunctionResult FuneralInterment_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");

		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		char[] RandomBox = { '1', '2', '3' };
		char charByte = RandomChar.randomizeArray(RandomBox, RandomBox.length);
		// Result.FunctionResult = new HashMap<Integer,String>();
		Result.FunctionResult.put(Integer.parseInt(splitIndex[0]), String
				.valueOf(charByte));
		String value = Result.FunctionResult.get(Integer
				.parseInt(splitIndex[0]));
		if (value.equals("1")) {
			// String RandomResults1 = "";
			int length1 = RecoDaoRandom.getLengthPublicFunction(
					RecoUtilRandom.funeralservice_TABLE,
					RecoDaoRandom.hashMaphashMapfuneralservice);

			int RandomIndex1 = RandomChar.generateRandomInt(length1);

			RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
					RecoUtilRandom.funeralservice_TABLE, RandomIndex1,
					RecoDaoRandom.hashMaphashMapfuneralservice);
		} else if (value.equals("2")) {

			int length2 = RecoDaoRandom.getLengthPublicFunction(
					RecoUtilRandom.funeralfacilities_TABLE,
					RecoDaoRandom.hashMaphashMapfuneralfacilities);
			// Random random = new Random();
			int RandomIndex2 = RandomChar.generateRandomInt(length2);
			RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
					RecoUtilRandom.funeralfacilities_TABLE, RandomIndex2,
					RecoDaoRandom.hashMaphashMapfuneralfacilities);
		} else {
			int length3 = RecoDaoRandom.getLengthPublicFunction(
					RecoUtilRandom.funeralsupplies_TABLE,
					RecoDaoRandom.hashMaphashMapfuneralsupplies);
			// Random random = new Random();
			int RandomIndex3 = RandomChar.generateRandomInt(length3);
			RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
					RecoUtilRandom.funeralsupplies_TABLE, RandomIndex3,
					RecoDaoRandom.hashMaphashMapfuneralsupplies);
		}

		// InPutResult.FunctionResult=new HashMap<Integer,String>();

		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i + 1]),
					String.valueOf(RandomResults.charAt(i)));
		}
		return Result;
	}

	/**
	 * IoTID=GB/T_19632-2005_1 (?#ALGNAME=MOD9710)
	 * (?#PARA=0,1,2,3,4,5,6,7,8,9,10,11,12;13,14){]
	 * user:lly
	 */

	public static FunctionResult MOD9710_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		// System.out.println(Result.FunctionResult.size());
		String[] splitIndex0 = index.split(";");
		String index1 = splitIndex0[0];
		String index2 = splitIndex0[1];
		String[] splitIndex1 = index1.split(",");
		String[] splitIndex2 = index2.split(",");
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < splitIndex1.length; i++) {
			buf.append(Result.FunctionResult.get(Integer
					.parseInt(splitIndex1[i])));
		}
		String id1 = buf.toString();
		BigInteger biginteger = new BigInteger(id1);
		// 98 - (input * 100) % 97;
		int output = 98 - (biginteger.multiply(new BigInteger("100")).mod(
				new BigInteger("97")).intValue());
		// 余数补零
		String id3 = StringUtils.leftPad(output + "", splitIndex2.length, "0");
		for (int i = 0; i < splitIndex2.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex2[i]), String
					.valueOf(id3.charAt(i)));
		}
		return Result;
	}
	

	

	/**
	 * IoTID=YC/T_213.5-2006 (?#ALGNAME=TabaccoElectricComponent)
	 * (?#PARA=2,3,4,5,6){]
	 * user:lly
	 */
	public static FunctionResult TabaccoElectricComponent_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");

		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.tabaccoelectriccomponent_TABLE,
				RecoDaoRandom.hashMaphashMaptabaccoelectriccomponent);
		int RandomIndex = RandomChar.generateRandomInt(length);
		RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.tabaccoelectriccomponent_TABLE, RandomIndex,
				RecoDaoRandom.hashMaphashMaptabaccoelectriccomponent);

		// InPutResult.FunctionResult=new HashMap<Integer,String>();

		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		return Result;
	}

	/**
	 * IoTID=YC/T_213.6-2006 (?#ALGNAME=TabaccoMaterial) (?#PARA=2,3,4,5,6){]
	 * user:lly
	 */
	public static FunctionResult TabaccoMaterial_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");

		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.tabaccomaterial_TABLE,
				RecoDaoRandom.hashMaphashMaptabaccomaterial);
		Random random = new Random();
		int RandomIndex = random.nextInt(length);
		RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.tabaccomaterial_TABLE, RandomIndex,
				RecoDaoRandom.hashMaphashMaptabaccomaterial);

		// InPutResult.FunctionResult=new HashMap<Integer,String>();

		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}

	/**
	 * IoTID=GB/T_28921-2012 (?#ALGNAME=Naturaldisaste) (?#PARA=0,1,2,3,4,5){]
	 * user:lly
	 */
	//modified by lly on 0915
	public static FunctionResult Naturaldisaster_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");

		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.Naturaldisaster_TABLE,
				RecoDaoRandom.hashMaphashMapNaturaldisaster);
		Random random = new Random();
		int RandomIndex = random.nextInt(length);
		RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.Naturaldisaster_TABLE, RandomIndex,
				RecoDaoRandom.hashMaphashMapNaturaldisaster);

		// InPutResult.FunctionResult=new HashMap<Integer,String>();

		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}



	/**
	 * IoTID=GA_459-2004 (?#ALGNAME=IDcardByMaterial) (?#PARA=0,1,2,3,4,5,6){]
	 * user:lly
	 */
	//modified by lly on 0915
	public static FunctionResult IDcardByMaterial_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		StringBuffer buf = new StringBuffer();
		String[] result012 = { "JCL", "JGC", "JRJ", "JCS", "JGS", "JAS", "JJS" };
		int length012 = result012.length;
		int randomidex012 = RandomChar.generateRandomInt(length012);
		String Result012 = result012[randomidex012];
		String[] result3456 = {};
		if (Result012.equals("JCL")) {
			String[] strs = { "0110", "0120", "0190", "0210", "0220", "0290",
					"0310", "0320", "0330", "0390", "0410", "0490", "0510",
					"0520", "0530", "0590", "0610", "0690", "0710", "0720",
					"0730", "0740", "0750", "0760", "0790" };
			result3456 = strs;
		} else if (Result012.equals("JGC")) {
			String[] strs = { "0110", "0120", "0190", "0130", "0140", "0290",
					"0210" };
			result3456 = strs;
		} else if (Result012.equals("JRJ")) {
			String[] strs = { "0110", "0120", "0130", "0140", "0150", "0190",
					 "0290", "0210", "0310", "0390", "0410",
					"0420", "0490", "0590" };
			result3456 = strs;

		} else if (Result012.equals("JGS")) {
			String[] strs = { "0110", "0120", "0150", "0190", "0210", "0220",
					"0290", "0310", "0320", "0390", "0490", "0590" };
			result3456 = strs;
		} else if (Result012.equals("JCS")) {
			String[] strs = { "0110", "0120", "0130", "0190" };
			result3456 = strs;
		} else if (Result012.equals("JAS")) {
			String[] strs = { "0110", "0120", "0130", "0190", "0210", "0220",
					"0230", "0240", "0290", "0310", "0320", "0390" };
			result3456 = strs;
		} else if (Result012.equals("JJS")) {
			String[] strs = { "0110", "0120", "0190", "0210", "0220", "0290" };
			result3456 = strs;
		}
		int length3456 = result3456.length;
		int randomidex3456 = RandomChar.generateRandomInt(length3456);
		String Result3456 = result3456[randomidex3456];
		buf.append(Result012).append(Result3456);
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), buf
					.charAt(i)
					+ "");
		}

		return Result;
	}


	/**
	 * IoTID=GB/T_23705-2009_3 (?#ALGNAME=DigitRegex) (?#PARA=14,-1){]
	 * user:lly
	 */
    //modified by lly on 0915
	public static FunctionResult DigitRegex_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		int t1 = Integer.parseInt(splitIndex[0]);
		String resultsring = "";
		boolean flag = false;
		char[] charbox0 = { '0', '1'};
		char charrandom0 = RandomChar
				.randomizeArray(charbox0, charbox0.length);
		Result.FunctionResult.put(t1, String.valueOf(charrandom0));
		for (int i = t1+1; i < Result.nSize; i++) {
			char[] charbox = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
			char charrandom = RandomChar
					.randomizeArray(charbox, charbox.length);
			Result.FunctionResult.put(i, String.valueOf(charrandom));
			if (charrandom == '0') {
				flag = true;
			}
		}

		if (flag) {
			int indexint = RandomChar.generateRandomInt(Result.nSize - t1-1) + t1+1;
			char[] charbox1 = { '1', '2', '3', '4', '5', '6', '7', '8', '9' };
			char charrandom1 = RandomChar.randomizeArray(charbox1,
					charbox1.length);
			Result.FunctionResult.put(indexint, String.valueOf(charrandom1));
		}

		return Result;
	}

	/**
	 * IoTID=GA_520.1-2004 (?#ALGNAME=SecurityAccounterments)
	 * (?#PARA=0,1,2,3,4,5,6){]
	 * user:lly
	 */
	public static FunctionResult SecurityAccounterments_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");

		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.securityaccounterments_TABLE,
				RecoDaoRandom.hashMaphashMapsecurityaccounterments);
		int resultlength = RandomResults.length();
		while (resultlength != 7) {
			int RandomIndex = RandomChar.generateRandomInt(length);
			RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
					RecoUtilRandom.securityaccounterments_TABLE, RandomIndex,
					RecoDaoRandom.hashMaphashMapsecurityaccounterments);
			resultlength = RandomResults.length();
		}
		// InPutResult.FunctionResult=new HashMap<Integer,String>();

		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		Result.ResultFlag = "OK";
		return Result;
	}

	/**
	 * IoTID=GB/T_22970-2010_1 (?#ALGNAME=TextileFabricNameCode)
	 * (?#PARA=0,1,2,3,4){
	 * user:lly
	 */
	public static FunctionResult TextileFabricNameCode_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");

		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.textilefabricnamecode_TABLE,
				RecoDaoRandom.hashMaphashMaptextilefabricnamecode);
		Random random = new Random();
		int RandomIndex = random.nextInt(length);
		RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.textilefabricnamecode_TABLE, RandomIndex,
				RecoDaoRandom.hashMaphashMaptextilefabricnamecode);

		// InPutResult.FunctionResult=new HashMap<Integer,String>();

		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}

	/**
	 * IoTID=GB/T_22970-2010_1 (?#ALGNAME=PropertiesMain) (?#PARA=5,6){
	 * user:lly
	 */
	public static FunctionResult PropertiesMain_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");

		String[] tablename = { RecoUtilRandom.propertiesmain_TABLE,
				RecoUtilRandom.propertiesmainmaterial_TABLE };
		Random random = new Random();
		// int tableindex=random.nextInt(2);
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		if (random.nextInt(2) == 0) {
			int length = RecoDaoRandom.getLengthPublicFunction(
					RecoUtilRandom.propertiesmain_TABLE,
					RecoDaoRandom.hashMaphashMappropertiesmain);
			// Random random = new Random();
			int RandomIndex = RandomChar.generateRandomInt(length);
			RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
					RecoUtilRandom.propertiesmain_TABLE, RandomIndex,
					RecoDaoRandom.hashMaphashMappropertiesmain);
		} else {
			int length = RecoDaoRandom.getLengthPublicFunction(
					RecoUtilRandom.propertiesmainmaterial_TABLE,
					RecoDaoRandom.hashMaphashMappropertiesmainmaterial);
			// Random random = new Random();
			int RandomIndex = RandomChar.generateRandomInt(length);
			RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
					RecoUtilRandom.propertiesmainmaterial_TABLE, RandomIndex,
					RecoDaoRandom.hashMaphashMappropertiesmainmaterial);
		}
		// InPutResult.FunctionResult=new HashMap<Integer,String>();

		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		return Result;
	}

	/**
	 * IoTID=GB/T_22970-2010_1 (?#ALGNAME=PropertiesFiberCharacteristics)
	 * (?#PARA=7,8){
	 * user:lly
	 */
	public static FunctionResult PropertiesFiberCharacteristics_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");

		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.propertiesfibercharacteristics_TABLE,
				RecoDaoRandom.hashMaphashMappropertiesfibercharacteristics);
		int RandomIndex = RandomChar.generateRandomInt(length);
		RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.propertiesfibercharacteristics_TABLE, RandomIndex,
				RecoDaoRandom.hashMaphashMappropertiesfibercharacteristics);

		// InPutResult.FunctionResult=new HashMap<Integer,String>();

		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		Result.FunctionType = "Function";
		Result.ResultFlag = "OK";
		return Result;
	}

	/**
	 * IoTID=GB/T_22970-2010_1 (?#ALGNAME=PropertiesMix) (?#PARA=11,12){
	 * user:lly
	 */
	public static FunctionResult PropertiesMix_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");

		Random random = new Random();
		// int tableindex=random.nextInt(2);
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		if (random.nextInt(2) == 0) {
			int length = RecoDaoRandom.getLengthPublicFunction(
					RecoUtilRandom.propertiesmixed_TABLE,
					RecoDaoRandom.hashMaphashMappropertiesmixed);
			// Random random = new Random();
			int RandomIndex = random.nextInt(length);
			RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
					RecoUtilRandom.propertiesmixed_TABLE, RandomIndex,
					RecoDaoRandom.hashMaphashMappropertiesmixed);
			for (int i = 0; i < RandomResults.length(); i++) {
				Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
						String.valueOf(RandomResults.charAt(i)));
			}
		} else {
			for (int i = 0; i < 2; i++) {
				char[] RandomBox = { '1', '2', '3', '9' };
				char charByte = RandomChar.randomizeArray(RandomBox,
						RandomBox.length);
				// Result.FunctionResult = new HashMap<Integer,String>();
				Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
						String.valueOf(charByte));
			}
		}
		// InPutResult.FunctionResult=new HashMap<Integer,String>();
		return Result;
	}

	/**
	 * IoTID=GB/T_22970-2010_1 (?#ALGNAME=PropertiesFabric) (?#PARA=13,14){
	 * user:lly
	 */
	public static FunctionResult PropertiesFabric_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");

		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.propertiesfabric_TABLE,
				RecoDaoRandom.hashMaphashMappropertiesfabric);
		int RandomIndex = RandomChar.generateRandomInt(length);
		RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.propertiesfabric_TABLE, RandomIndex,
				RecoDaoRandom.hashMaphashMappropertiesfabric);

		// InPutResult.FunctionResult=new HashMap<Integer,String>();

		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		return Result;
	}

	/**
	 * IoTID=GB/T_22970-2010_1 (?#ALGNAME=PropertiesDyeingandFinishing)
	 * (?#PARA=15,16){
	 * user:lly
	 */
	public static FunctionResult PropertiesDyeingandFinishing_Random(

	FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");

		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.propertiesdyeingandfinishing_TABLE,
				RecoDaoRandom.hashMaphashMappropertiesdyeingandfinishing);
		int RandomIndex = RandomChar.generateRandomInt(length);
		RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.propertiesdyeingandfinishing_TABLE, RandomIndex,
				RecoDaoRandom.hashMaphashMappropertiesdyeingandfinishing);

		// InPutResult.FunctionResult=new HashMap<Integer,String>();

		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		return Result;
	}

	/**
	 * IoTID=JB/T_5992.8-92 (?#ALGNAME=Machinery8) (?#PARA=0,1,2,3,4){
	 * user:lly
	 */

	public static FunctionResult Machinery8_Random(

	FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");

		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		int length = RecoDaoRandom.getLengthPublicFunction(RecoUtilRandom.machinery8_TABLE,
				RecoDaoRandom.hashMapmachinery8);
		int RandomIndex = RandomChar.generateRandomInt(length);
		RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.machinery8_TABLE, RandomIndex,
				RecoDaoRandom.hashMapmachinery8);

		// InPutResult.FunctionResult=new HashMap<Integer,String>();

		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		return Result;
	}

	/**
	 * IoTID=GA/T_867.1-2010 (?#ALGNAME==Casecharace) (?#PARA=0,1,2,3,4,5){
	 * user:lly
	 */
	public static FunctionResult Casecharacer_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		String[] randomresult = { "010000", "020000", "030000", "040000",
				"050000", "060000", "070000", "080000", "090000", "100000",
				"110000" };
		int maxlength = randomresult.length;
		int indexlength = RandomChar.generateRandomInt(maxlength);
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(randomresult[indexlength].charAt(i)));
		}
		return Result;
	}

	/**
	 * IoTID=JB/T_5992.7-92 (?#ALGNAME=Machinery7) (?#PARA=0,1,2,3,4){]
	 * user:lly
	 */

	public static FunctionResult Machinery7_Random(

	FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");

		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		int length = RecoDaoRandom.getLengthPublicFunction(RecoUtilRandom.machinery7_TABLE,
				RecoDaoRandom.hashMaphashMapmachinery7);
		int RandomIndex = RandomChar.generateRandomInt(length);
		RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.machinery7_TABLE, RandomIndex,
				RecoDaoRandom.hashMaphashMapmachinery7);

		// InPutResult.FunctionResult=new HashMap<Integer,String>();

		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}

	/**
	 * IoTID=YC/T_414-2011_1 (?#ALGNAME=CigaSubClassCode) (?#PARA=4,5,6){]
	 * user:lly
	 */
	public static FunctionResult CigaSubClassCode_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		StringBuffer buf = new StringBuffer();
		String[] result45 = { "10", "19", "20", "21", "29", "30", "39", "40",
				"41", "49", "90" };

		int length45 = result45.length;
		int randomidex45 = RandomChar.generateRandomInt(length45);
		String Result45 = result45[randomidex45];
		// System.out.println(Result45);
		// System.out.println(Result45);
		String[] result6 = {};
		if (Result45.equals("10")) {
			String[] strs = { "1", "2" };
			result6 = strs;
		} else if (Result45.equals("19") || Result45.equals("29")
				|| Result45.equals("39") || Result45.equals("49")) {
			String[] strs = { "9" };
			result6 = strs;
		} else if (Result45.equals("20") || Result45.equals("40")) {
			String[] strs = { "1", "2", "3", "4", "5", "6", "7", "8", "9" };
			result6 = strs;

		} else if (Result45.equals("30")) {
			String[] strs = { "1", "2", "3", "4", "5", "6", "7", "8" };
			result6 = strs;
		} else if (Result45.equals("90")) {
			String[] strs = { "1" };
			result6 = strs;
		} else if (Result45.equals("21")) {
			String[] strs = { "0" };
			result6 = strs;
		} else if (Result45.equals("41")) {
			String[] strs = { "0", "1" };
			result6 = strs;
		}
		int length6 = result6.length;
		// System.out.println(length6);
		if (length6 == 1) {
			buf.append(Result45).append(result6[0]);
		} else {
			int randomidex6 = RandomChar.generateRandomInt(length6);
			String Result6 = result6[randomidex6];
			// System.out.println(Result6);
			buf.append(Result45).append(Result6);
		}

		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), buf
					.charAt(i)
					+ "");
		}

		return Result;
	}

	/**
	 * IoTID=GA_408.2-2006 ](?#ALGNAME=TenByteDecimalnt)
	 * (?#PARA=11,12,13,14,15,16,17,18,19,20){]
	 * user:lly
	 */
	public static FunctionResult TenByteDecimalnt_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		StringBuffer buf = new StringBuffer();
		char[] charbox0 = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		for(int i=0;i<splitIndex.length;i++){
			char charrandom0 = RandomChar.randomizeArray(charbox0, charbox0.length);
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),charrandom0+"");
		}
		return Result;
	}

	/**
	 * IoTID=GB/T_18995-2003 (?#ALGNAME=CigaDepCode)(?#PARA=8,9){]
	 */
	public static FunctionResult CigaDepCode_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");
		StringBuffer buf = new StringBuffer();
		char[] charbox0 = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		char[] charbox1 = { '0', '1', '2', '3', '4', '5', '6', '7' };
		char charrandom0 = RandomChar.randomizeArray(charbox0, charbox0.length);
		char charrandom1 = RandomChar.randomizeArray(charbox1, charbox1.length);
		buf.append(charrandom0).append(charrandom1);
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), buf
					.charAt(i)
					+ "");
		}
		return Result;
	}

	/**
	 * IoTID=GA/T_556.8-2007 (?#ALGNAME=TreasuryClass) (?#PARA=8,9){]
	 * user:lly
	 */
	public static FunctionResult TreasuryClass_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		String[] randomresult = { "01", "02", "03", "04", "05", "06", "07",
				"08", "19" };
		int maxlength = randomresult.length;
		int indexlength = RandomChar.generateRandomInt(maxlength);
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					randomresult[indexlength].charAt(i) + "");
		}
		return Result;
	}

	/**
	 * IoTID=GB/T_26319-2010_1 (?#ALGNAME=IntFreitForwarding) (?#PARA=18,-1){]
	 * user:lly
	 */
	
	//modified by lly on 0915
	public static FunctionResult IntFreitForwarding_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		double sum = 0;

		String[] splitIndex = index.split(",");
		int index1 = Integer.parseInt(splitIndex[0]);
			String value;
			int intvalue;
			char Box0to9[] = { '0', '1', '2', '3', '4', '5', '6',
					'7', '8', '9'};
			char BoxAtoZ[]={'A', 'B', 'C', 'D', 'E', 'F', 'G',
					'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
					'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
			StringBuffer buf=new StringBuffer();
			//System.out.println(Result.nSize);
			if(Result.nSize!=19){
			int AtoZlength=RandomChar.generateRandomInt(Result.nSize-index1);
			//A-Z
			
			for (int i = 0; i<AtoZlength; i++) {
				char randomchar=RandomChar.randomizeArray(BoxAtoZ, BoxAtoZ.length);
				buf.append(randomchar);
			}
			//System.out.println(buf.toString());
			//0-9
			int buflength=buf.length();
			for (int i = 0; i<Result.nSize-index1-1-buflength; i++) {
				char randomchar=RandomChar.randomizeArray(Box0to9, Box0to9.length);
				buf.append(randomchar);	
				
			}
			for(int i=0;i<Result.nSize-index1-1;i++){
				Result.FunctionResult.put(index1+i , buf.toString().charAt(i)+"");
			}
			}
			for (int j = 0; j < Result.nSize - 1; j++) {
				value = Result.FunctionResult.get(j);
				if (value.matches("[A-Z]")) {
					intvalue = 10 + (int) value.charAt(0) - 65;
				} else {
					intvalue = Integer.parseInt(value);
				}
				sum = sum + intvalue * (Math.pow(2, Result.nSize - 1 - j) % 11);
			}
      //   System.out.println(sum);
			int mod;
			sum %= 11;
			mod = (int) (12 - sum) % 11;
			//System.out.println(mod);
			if (mod == 10) {
				Result.FunctionResult.put(Result.nSize - 1, "X");
			} else {
				Result.FunctionResult
						.put(Result.nSize - 1, String.valueOf(mod));
			}
		return Result;
	}

	/**
	 * IoTID=WS/T_118-1999 (?#ALGNAME=MedicalInstru) (?#PARA=0,1,2,3,4,5,6,7){]
	 * user:lly
	 * 
	 */
	public static FunctionResult MedicalInstru_Random(

	FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");

		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.medicalInstrument_TABLE,
				RecoDaoRandom.hashMapmedicalInstrument);
		int RandomIndex = RandomChar.generateRandomInt(length);
		RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.medicalInstrument_TABLE, RandomIndex,
				RecoDaoRandom.hashMapmedicalInstrument);

		// InPutResult.FunctionResult=new HashMap<Integer,String>();

		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		return Result;
	}

	/**
	 * IoTID=LS/T_1708.2-2004 (?#ALGNAME=FoodEconomy)(?#PARA=0,1,2,3,4){]
	 * user:lly
	 * 
	 */

	public static FunctionResult FoodEconomy_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");

		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.grainstechnicaleconomy_TABLE,
				RecoDaoRandom.hashMapgrainstechnicaleconomy);
		int RandomIndex = RandomChar.generateRandomInt(length);
		RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.grainstechnicaleconomy_TABLE, RandomIndex,
				RecoDaoRandom.hashMapgrainstechnicaleconomy);

		// InPutResult.FunctionResult=new HashMap<Integer,String>();

		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		return Result;
	}

	/**
	 * GB/T_27766-2011_4 (?#ALGNAME=DigitAndLetter)(?#PARA=0,1,2,3,4,5){]
	 * user:lly
	 * 
	 */
	public static FunctionResult DigitAndLetter_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");
		char[] charbox = { '0', '1' };
		for (int i = 0; i < splitIndex.length; i++) {
			char charrandom = RandomChar
					.randomizeArray(charbox, charbox.length);
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(charrandom));
		}
		return Result;
	}

	/**
	 *GB/T_18521-2001 (?#ALGNAME=GeographicalCode)(?#PARA=4,5,6,7){]
	 * user:lly
	 * 
	 */

	public static FunctionResult GeographicalCode_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");

		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.GeographicalCode_TABLE,
				RecoDaoRandom.hashMapGeographicalCode);
		int RandomIndex = RandomChar.generateRandomInt(length);
		RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.GeographicalCode_TABLE, RandomIndex,
				RecoDaoRandom.hashMapGeographicalCode);

		// InPutResult.FunctionResult=new HashMap<Integer,String>();

		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		return Result;
	}

	/**
	 *GB_11714-1997(?#ALGNAME=MOD11)(?#PARA=0,1,2,3,4,5,6,7;8){]
	 * user:lly
	 * 
	 */

	public static FunctionResult MOD11_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		double sum = 0;
		String[] splitIndex0 = index.split(";");
		String index1 = splitIndex0[0];
		String index2 = splitIndex0[1];

		String[] splitIndex1 = index1.split(",");
		String RandomResults = "";
		String value;
		int intvalue;
		// String[] splitIndex = index.split(",");
		char[] charbox = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
				'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
				'Y', 'Z' };
		for (int i = 0; i < splitIndex1.length; i++) {
			char charrandom = RandomChar
					.randomizeArray(charbox, charbox.length);
			Result.FunctionResult.put(Integer.parseInt(splitIndex1[i]), String
					.valueOf(charrandom));
			value = Result.FunctionResult.get(Integer.parseInt(splitIndex1[i]));

			if (value.matches("[A-Z]")) {
				intvalue = 10 + (int) value.charAt(0) - 65;
			} else {
				intvalue = Integer.parseInt(value);
			}
			sum = sum + intvalue * (Math.pow(2, splitIndex1.length - i) % 11);
		}
		int mod;
		sum %= 11;
		mod = (int) (11 - sum) % 11;
		if (mod == 10) {
			Result.FunctionResult.put(Integer.parseInt(index2), "X");
		} else {
			Result.FunctionResult.put(Integer.parseInt(index2), String
					.valueOf(mod));
		}
		return Result;
	}

	/**
	 * SB/T_10680-2012_5(?#ALGNAME=MeatandVegetable)(?#PARA=0,1,2,3,4,5,6,7,8){]
	 * user:lly
	 * 
	 */
	public static FunctionResult MeatandVegetable_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");

		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.meatandvegetable_TABLE,
				RecoDaoRandom.hashMapmeatandvegetable);
		Random random = new Random();
		int RandomIndex = random.nextInt(length);
		RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.meatandvegetable_TABLE, RandomIndex,
				RecoDaoRandom.hashMapmeatandvegetable);

		// InPutResult.FunctionResult=new HashMap<Integer,String>();

		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}

	/**
	 * GB/T_27766-2011_2 (?#ALGNAME=Letter)(?#PARA=0,1,2,3,4){]
	 * user:lly
	 */

	public static FunctionResult Letter_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");
        List ls= new ArrayList();
		for(int i=0;i<=26;i++){
			RandomResults=	Integer.toBinaryString(i);
			java.text.DecimalFormat num_format = new java.text.DecimalFormat("00000");     
			RandomResults= num_format.format(Integer.parseInt(RandomResults));  
			//System.out.println(RandomResults);

			ls.add(RandomResults);
		}
		int index_012345=RandomChar.generateRandomInt(ls.size());
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					ls.get(index_012345).toString().charAt(i)+"");
		}
		return Result;
	}


	/**
	 * YC/T_191-2005 (?#ALGNAME=ZeroTO24)(?#PARA=6,7){]
	 * user:lly
	 */
	public static FunctionResult ZeroTO24_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");
		StringBuffer buf = new StringBuffer();
		char[] charbox0 = { '0', '1', '2' };
		char[] charbox1 = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		char[] charbox2 = { '0', '1', '2', '3', '4' };

		char charrandom0 = RandomChar.randomizeArray(charbox0, charbox0.length);
		if (charrandom0 == '0' || charrandom0 == '1') {
			char charrandom1 = RandomChar.randomizeArray(charbox1,
					charbox1.length);
			buf.append(charrandom0).append(charrandom1);

		} else if (charrandom0 == '2') {
			char charrandom2 = RandomChar.randomizeArray(charbox2,
					charbox2.length);
			buf.append(charrandom0).append(charrandom2);
		}
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(buf.charAt(i)));
		}
		return Result;
	}

	/**
	 * /** YC/T_191-2005 (?#ALGNAME=ZeroTO60)(?#PARA=8,9){]
	 * user:lly
	 */

	public static FunctionResult ZeroTO60_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");
		StringBuffer buf = new StringBuffer();
		char[] charbox0 = { '0', '1', '2', '3', '4', '5', '6' };
		char[] charbox1 = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		char charrandom0 = RandomChar.randomizeArray(charbox0, charbox0.length);
		if (charrandom0 != '6') {
			char charrandom1 = RandomChar.randomizeArray(charbox1,
					charbox1.length);
			buf.append(charrandom0).append(charrandom1);
		} else {
			buf.append(charrandom0).append('0');
		}
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(buf.charAt(i)));
		}
		return Result;
	}

	/**
	 * LS/T_1706-2004 (?#ALGNAME=GrainEquipment)(?#PARA=0,1,2,3,4,5,6,7){]
	 * user:lly
	 */

	public static FunctionResult GrainEquipment_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");

		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.grainequipment_TABLE, RecoDaoRandom.hashMapgrainequipment);
		int RandomIndex = RandomChar.generateRandomInt(length);
		RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.grainequipment_TABLE, RandomIndex,
				RecoDaoRandom.hashMapgrainequipment);

		// InPutResult.FunctionResult=new HashMap<Integer,String>();

		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		return Result;
	}

	/**
	 * JB/T_5992.5-92 (?#ALGNAME=Machinery5)(?#PARA=0,1,2,3,4){]
	 * user:lly
	 */

	public static FunctionResult Machinery5_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");

		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		int length = RecoDaoRandom.getLengthPublicFunction(RecoUtilRandom.machinery5_TABLE,
				RecoDaoRandom.hashMapmachinery5);
		int RandomIndex = RandomChar.generateRandomInt(length);
		RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.machinery5_TABLE, RandomIndex,
				RecoDaoRandom.hashMapmachinery5);

		// InPutResult.FunctionResult=new HashMap<Integer,String>();

		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		return Result;
	}

	/**
	 * GA/T_396-2002_1 (?#ALGNAME=ProvinceAdminCode)(?#PARA=0,1)
	 * user:lly
	 */

	public static FunctionResult ProvinceAdminCode_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");

		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.provinceadmincode_TABLE,
				RecoDaoRandom.hashMapprovinceadmincode);
		int RandomIndex = RandomChar.generateRandomInt(length);
		RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.provinceadmincode_TABLE, RandomIndex,
				RecoDaoRandom.hashMapprovinceadmincode);

		// InPutResult.FunctionResult=new HashMap<Integer,String>();

		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		return Result;
	}

	/**
	 * LS/T_1703-2004 (?#ALGNAME=GrainsInformation)(?#PARA=0,1,2,3,4,5,6){]
	 * user:lly
	 */

	public static FunctionResult GrainsInformation_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");

		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.grainsinformation_TABLE,
				RecoDaoRandom.hashMapgrainsinformation);
		int RandomIndex = RandomChar.generateRandomInt(length);
		RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.grainsinformation_TABLE, RandomIndex,
				RecoDaoRandom.hashMapgrainsinformation);

		// InPutResult.FunctionResult=new HashMap<Integer,String>();

		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		return Result;
	}

	/**
	 * GA_658.1-2006 ](?#ALGNAME=SixByteDecimalnt)(?#PARA=8,9,10,11,12,13){]
	 * user:lly
	 */

	public static FunctionResult SixByteDecimalnt_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");
		char[] charbox1 = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		for (int i = 0; i < splitIndex.length; i++) {
			char charrandom1 = RandomChar.randomizeArray(charbox1,
					charbox1.length);
			if (i == 0 && charrandom1 == '0') {
				char[] charbox2 = { '1', '2', '3', '4', '5', '6', '7', '8', '9' };
				charrandom1 = RandomChar.randomizeArray(charbox2,
						charbox2.length);
			}
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(charrandom1));
		}
		return Result;
	}

	/**
	 * GB/T_21379-2008_4 (?#ALGNAME=OneTO21)(?#PARA=16,17){]
	 * user:lly
	 */

	public static FunctionResult OneTO21_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");
		StringBuffer buf = new StringBuffer();
		char[] charbox0 = { '0', '1', '2', '9' };
		char[] charbox1 = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		char[] charbox2 = { '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		char[] charbox3 = { '1', '0' };

		char charrandom0 = RandomChar.randomizeArray(charbox0, charbox0.length);
		if (charrandom0 == '0') {
			char charrandom1 = RandomChar.randomizeArray(charbox2,
					charbox2.length);
			buf.append(charrandom0);
			buf.append(charrandom1);
		} else if (charrandom0 == '1') {
			char charrandom2 = RandomChar.randomizeArray(charbox2,
					charbox2.length);
			buf.append(charrandom0);
			buf.append(charrandom2);
		} else if (charrandom0 == '2') {
			char charrandom3 = RandomChar.randomizeArray(charbox3,
					charbox3.length);
			buf.append(charrandom0);
			buf.append(charrandom3);
		} else if (charrandom0 == '9') {
			buf.append(charrandom0);
			buf.append('9');
		}
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(buf.charAt(i)));
		}
		return Result;
	}

	/**
	 * radiation (?#ALGNAME=NuclearelementNation)(?#PARA=0,1){]
	 * user:lly
	 * 
	 */
	public static FunctionResult NuclearelementNation_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");

		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.NuclearelementNation_TABLE,
				RecoDaoRandom.hashMapNuclearelementNation);
		int RandomIndex = RandomChar.generateRandomInt(length);
		RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.NuclearelementNation_TABLE, RandomIndex,
				RecoDaoRandom.hashMapNuclearelementNation);

		// InPutResult.FunctionResult=new HashMap<Integer,String>();

		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}

	/**
	 * radiation (?#ALGNAME=Nuclearelements)(?#PARA=4,5)
	 * user:lly
	 */
	public static FunctionResult Nuclearelements_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");

		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.Nuclearelements_TABLE, RecoDaoRandom.hashMapNuclearelements);
		int RandomIndex = RandomChar.generateRandomInt(length);
		RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.Nuclearelements_TABLE, RandomIndex,
				RecoDaoRandom.hashMapNuclearelements);

		// InPutResult.FunctionResult=new HashMap<Integer,String>();

		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		return Result;
	}

	/**
	 * GB/T_21379-2008_1 (?#ALGNAME=CodeHighway)(?#PARA=16,17){]
	 * user:lly
	 */

	public static FunctionResult CodeHighway_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		// String RandomResults = "";
		String[] splitIndex = index.split(",");
		String[] randomresult = { "10", "11", "12", "20", "21", "22", "23",
				"30", "31", "32", "33", "40", "41", "42", "50", "90" };
		int maxlength = randomresult.length;
		int indexlength = RandomChar.generateRandomInt(maxlength);
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(randomresult[indexlength].charAt(i)));
		}
		return Result;
	}

	/**
	 * JB/T_5992.3-92 (?#ALGNAME=Machinery3)(?#PARA=0,1,2,3,4){]
	 * user:lly
	 */

	public static FunctionResult Machinery3_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String RandomResults = "";
		String[] splitIndex = index.split(",");

		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		int length = RecoDaoRandom.getLengthPublicFunction(RecoUtilRandom.machinery3_TABLE,
				RecoDaoRandom.hashMapmachinery3);
		// Random random = new Random();
		int RandomIndex = RandomChar.generateRandomInt(length);
		RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.machinery3_TABLE, RandomIndex,
				RecoDaoRandom.hashMapmachinery3);

		// InPutResult.FunctionResult=new HashMap<Integer,String>();

		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		return Result;
	}

	/**
	 * (?#ALGNAME=VehicleIdenCode)(?#PARA=0,1,2,3,4,5,6,7,9,10,11,12,13,14,15,16;8){]
	 * user:lly
	 */
	public static FunctionResult VehicleIdenCode_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex0 = index.split(";");
		String index1 = splitIndex0[0];
		String index2 = splitIndex0[1];
		//Result.FunctionResult.put(Integer.parseInt(index2), "0");
		int mapvalueint = 0;
		int sum = 0;
		int sum1 = 0;
		String[] splitIndex = index1.split(",");
		/**String[] randomresult = { "A1", "B2", "C3", "D4", "E5", "F6", "G7",
				"H8", "J1", "K2", "L3", "M4", "N5", "P7", "R9", "S2", "T3",
				"U4", "V5", "W6", "X7", "Y8", "Z9" };
		int maxlength = randomresult.length;
		*/
		//map-value
		String[] randomresult = { "A", "B", "C", "D", "E", "F", "G",
				"H", "J", "K", "L", "M", "N", "P", "R", "S", "T",
				"U", "V", "W", "X", "Y", "Z" ,"0","1","2","3","4","5","6","7","8","9"};
		//eg:A-1 B-2 0-0
		String [] randomresultindex={ "1", "2", "3","4", "5", "6","7", "8", "1","2", "3", "4","5", "7", "9","2","3", "4", "5","6", "7", "8","9","0","1","2","3","4","5","6","7","8","9"};
		List<String> ls = new ArrayList<String>();
		ls = Arrays.asList(randomresult);
		for (int i = 0; i < splitIndex.length; i++) {

			/**String value = Result.FunctionResult.get(Integer
					.parseInt(splitIndex[i]));
			System.out.println(":" + splitIndex[i]);
			if (value.matches("[0-9]")) {
				mapvalueint = Integer.parseInt(value);
				System.out.println("i:" + i + "  " + value);
			} else if (value.matches("[A-H,J-N,P,R-Z]")) {
				// System.out.println("*******88");
				for (int j = 0; j < maxlength; j++) {
					if (value.equals(String.valueOf(randomresult[j].charAt(0)))) {
						mapvalueint = Integer.parseInt(randomresult[j]
								.substring(1));
						System.out.println("i:" + i + "   " + value
								+ mapvalueint);
					}
				}
				
			}
*/
			String value = Result.FunctionResult.get(Integer
					.parseInt(splitIndex[i]));
			mapvalueint=Integer.parseInt(randomresultindex[ls.indexOf(value)]);
			//System.out.println("i:" + i + "  " + value);
			int k = Integer.parseInt(splitIndex[i]) + 1;
			//权值
			if (k < 8) {
				sum1 = (9 - k) * mapvalueint;
			}
			if (k == 8) {
				sum1 = 10 * mapvalueint;
			}
			if (k > 9) {
				sum1 = (19 - k) * mapvalueint;
			}
			sum = sum1 + sum;
			//System.out.print("sum1;" + sum1);
			//System.out.println("sum ;" + sum);
		}
		int mod = sum % 11;
		if (mod == 10) {
			Result.FunctionResult.put(Integer.parseInt(index2), "X");

		} else {
			Result.FunctionResult.put(Integer.parseInt(index2), String
					.valueOf(mod));
		}
		return Result;
	}



	/**
	 * ALGNAME=OneTO13
	 * user:lly
	 */
	public static FunctionResult OneTO13_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		// String RandomResults = "";
		String[] splitIndex = index.split(",");
		String[] randomresult = { "01", "02", "03", "04", "05", "06", "07",
				"08", "09", "10", "11", "12", "13", "99" };
		int maxlength = randomresult.length;
		int indexlength = RandomChar.generateRandomInt(maxlength);
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(randomresult[indexlength].charAt(i)));
		}
		return Result;
	}

	/**
	 * DL/T_700.2-1999_37 (?#ALGNAME=ParamCode17)
	 * user:lly
	 */

	public static FunctionResult ParamCode17_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		char[] charbox1 = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		for (int i = 0; i < Result.nSize - Integer.parseInt(splitIndex[0]); i++) {
			char charrandom1 = RandomChar.randomizeArray(charbox1,
					charbox1.length);
			Result.FunctionResult.put(Integer.parseInt(splitIndex[0]) + i,
					String.valueOf(charrandom1));
		}
		return Result;
	}

	/**
	 * DL/T_700.2-1999_43 (?#ALGNAME=OneTO10)(?#PARA=0,1){]
	 * user:lly
	 */

	public static FunctionResult OneTO10_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		// String RandomResults = "";
		String[] splitIndex = index.split(",");
		String[] randomresult = { "01", "02", "03", "04", "05", "06", "07",
				"08", "09", "10", "99" };
		int maxlength = randomresult.length;
		int indexlength = RandomChar.generateRandomInt(maxlength);
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(randomresult[indexlength].charAt(i)));
		}
		return Result;
	}

	/**
	 * DL/T_700.2-1999_39 (?#ALGNAME=OneTO13No99)(?#PARA=7,8){]
	 * user:lly
	 */
	//modofied by lly on 0915

	public static FunctionResult OneTO13No99_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		// String RandomResults = "";
		String[] splitIndex = index.split(",");
		String[] randomresult = { "01", "02", "03", "04", "05", "06", "07",
				"08", "09", "10", "11", "12", "13"};
		int maxlength = randomresult.length;
		int indexlength = RandomChar.generateRandomInt(maxlength);
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(randomresult[indexlength].charAt(i)));
		}
		return Result;
	}


	/**
	 * DL/T_700.2-1999_39(?#ALGNAME=One2ThreeDigit)(?#PARA=9,-1){]{]
	 * user:lly
	 */
	public static FunctionResult One2ThreeDigit_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");
		char[] charbox1 = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		for (int i = 0; i < Result.nSize - Integer.parseInt(splitIndex[0]); i++) {
			char charrandom1 = RandomChar.randomizeArray(charbox1,
					charbox1.length);
			Result.FunctionResult.put(Integer.parseInt(splitIndex[0]) + i,
					String.valueOf(charrandom1));
		}
		return Result;
	}

	/**
	 * GA/T_974.48-2011 (?#ALGNAME=Machinery4)(?#PARA=0,1,2,3,4){]
	 * user:lly
	 */
	public static FunctionResult Machinery4_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");

		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		int length = RecoDaoRandom.getLengthPublicFunction(RecoUtilRandom.machinery4_TABLE,
				RecoDaoRandom.hashMapmachinery4);
		// Random random = new Random();
		int RandomIndex = RandomChar.generateRandomInt(length);
		RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.machinery4_TABLE, RandomIndex,
				RecoDaoRandom.hashMapmachinery4);

		// InPutResult.FunctionResult=new HashMap<Integer,String>();

		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}

	/**
	 * GA_398.10-2002 (?#ALGNAME=CodesOfMakingCases)(?#PARA=3,4,5){
	 * user:lly
	 */
	public static FunctionResult CodesOfMakingCases_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");
		StringBuffer buf = new StringBuffer();
		char[] charbox0 = { '0', '1', '2', '3', '4', '5', '6', '7', '8' };
		char[] charbox1 = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

		for (int i = 0; i < splitIndex.length; i++) {
			char charrandom0 = RandomChar.randomizeArray(charbox0,
					charbox0.length);
			char charrandom1 = RandomChar.randomizeArray(charbox1,
					charbox1.length);
			char charrandom2 = RandomChar.randomizeArray(charbox1,
					charbox1.length);
			buf.append(charrandom0).append(charrandom1).append(charrandom2);
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(buf.charAt(i)));
		}
		return Result;
	}

	/**
	 * GA_398.10-2002 (?#ALGNAME=OneTO46)(?#PARA=6,7){]
	 * user:lly
	 */

	public static FunctionResult OneTO46_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");
		StringBuffer buf = new StringBuffer();

		char[] charbox0 = { '1', '2', '3', '4', '0', '9' };

		char charrandom0 = RandomChar.randomizeArray(charbox0, charbox0.length);
		String stringrandom1 = "";
		if (charrandom0 == '0') {
			stringrandom1 = "123456789";
		} else if (charrandom0 == '1' || charrandom0 == '2'
				|| charrandom0 == '3') {
			stringrandom1 = "0123456789";
		} else if (charrandom0 == '4') {
			stringrandom1 = "0123456";
		} else if (charrandom0 == '9') {
			stringrandom1 = "9";
		}
		char[] charbox1 = stringrandom1.toCharArray();
		char charrandom1 = RandomChar.randomizeArray(charbox1, charbox1.length);
		buf.append(charrandom0).append(charrandom1);

		for (int i = 0; i < splitIndex.length; i++) {

			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), buf
					.charAt(i)
					+ "");
		}
		return Result;
	}

	/**
	 * DL/T_517-2012 (?#ALGNAME=ElectricPower)(?#PARA=0,1,2,3,4,5){]
	 * user:lly
	 */

	public static FunctionResult ElectricPower_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");

		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.electricpower_TABLE, RecoDaoRandom.hashMapelectricpower);
		// Random random = new Random();
		int RandomIndex = RandomChar.generateRandomInt(length);
		RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.electricpower_TABLE, RandomIndex,
				RecoDaoRandom.hashMapelectricpower);

		// InPutResult.FunctionResult=new HashMap<Integer,String>();

		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		return Result;
	}

	/**
	 * GA/T_556.5-2007 (?#ALGNAME=FinancialCode)(?#PARA=6,7){]
	 * user:lly
	 */

	public static FunctionResult FinancialCode_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");
		StringBuffer buf = new StringBuffer();

		char[] charbox0 = { '1', '2', '3', '4', '5', '6', '7', '8', '9' };

		char charrandom0 = RandomChar.randomizeArray(charbox0, charbox0.length);
		String stringrandom1 = "";
		if (charrandom0 == '1' || charrandom0 == '2' || charrandom0 == '3'
				|| charrandom0 == '6' || charrandom0 == '8') {
			stringrandom1 = "0";
		} else if (charrandom0 == '4') {
			stringrandom1 = "123456789";
		} else if (charrandom0 == '5') {
			stringrandom1 = "56789";
		} else if (charrandom0 == '7') {
			stringrandom1 = "156";
		} else if (charrandom0 == '8') {
			stringrandom1 = "12347";
		} else if (charrandom0 == '9') {
			stringrandom1 = "9";
		}
		char[] charbox1 = stringrandom1.toCharArray();
		char charrandom1 = RandomChar.randomizeArray(charbox1, charbox1.length);
		buf.append(charrandom0).append(charrandom1);

		for (int i = 0; i < splitIndex.length; i++) {

			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(buf.charAt(i)));
		}
		return Result;
	}

	/**
	 * LS/T_1704.3-2004 (?#ALGNAME=ClassOfGrain)(?#PARA=0,1,2,3,4,5){]
	 * user:lly
	 */

	public static FunctionResult ClassOfGrain_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		// String RandomResults = "";
		String[] splitIndex = index.split(",");
		String[] randomresult = { "01010", "01020", "02010", "02020", "02030" };
		int maxlength = randomresult.length;
		int indexlength = RandomChar.generateRandomInt(maxlength);
		String index01234 = randomresult[indexlength];
		String index5 = "";
		StringBuffer buf = new StringBuffer();
		if (index01234.equals("01010")) {
			index5 = "01234";
		} else if (index01234.equals("01020")) {
			index5 = "012345";
		} else if (index01234.equals("02010") || index01234.equals("02030")) {
			index5 = "01";
		} else if (index01234.equals("02020")) {
			index5 = "012";
		}
		char[] charbox1 = index5.toCharArray();
		char charrandom1 = RandomChar.randomizeArray(charbox1, charbox1.length);
		buf.append(index01234).append(charrandom1);
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), buf
					.charAt(i)
					+ "");
		}
		return Result;
	}

	/**
	 * GA/T_615.4-2006 (?#ALGNAME=Borderinfo4)(?#PARA=0,1){]
	 * user:lly
	 */

	public static FunctionResult Borderinfo4_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		// String RandomResults = "";
		String[] splitIndex = index.split(",");
		String[] randomresult = { "11", "21", "22", "23", "31", "32", "33",
				"34", "99" };
		// int maxlength = randomresult.length;
		int indexlength = RandomChar.generateRandomInt(randomresult.length);
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					randomresult[indexlength].charAt(i) + "");
		}
		return Result;
	}

	/**
	 * GA/T_974.48-2011 (?#ALGNAME=FireInfotainass)(?#PARA=0,1){]
	 * user:lly
	 */
	public static FunctionResult FireInfotainass_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		// String RandomResults = "";
		String[] splitIndex = index.split(",");
		String[] randomresult = { "20", "21", "22", "30", "10", "90" };
		// int maxlength = randomresult.length;
		int indexlength = RandomChar.generateRandomInt(randomresult.length);
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					randomresult[indexlength].charAt(i) + "");
		}
		return Result;
	}

	/**
	 * WS_364.15-2011_2 (?#ALGNAME=WorkerHealthSupervision)(?#PARA=0,-1){]
	 * user:lly
	 */
	//modified by lly
	//0827
	public static FunctionResult WorkerHealthSupervision_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");

		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.workerhealthsupervision_TABLE,
				RecoDaoRandom.hashMapeworkerhealthsupervision);
		// Random random = new Random();
		int RandomIndex = RandomChar.generateRandomInt(length);
		RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.workerhealthsupervision_TABLE, RandomIndex,
				RecoDaoRandom.hashMapeworkerhealthsupervision);

		// InPutResult.FunctionResult=new HashMap<Integer,String>();

		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[0]) + i,
					String.valueOf(RandomResults.charAt(i)));
		}
		return Result;
	}


	 /*public static FunctionResult WorkerHealthSupervision_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");

		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.workerhealthsupervision_TABLE,
				RecoDaoRandom.hashMapeworkerhealthsupervision);
		// Random random = new Random();
		int RandomIndex = RandomChar.generateRandomInt(length);
		RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.workerhealthsupervision_TABLE, RandomIndex,
				RecoDaoRandom.hashMapeworkerhealthsupervision);

		// InPutResult.FunctionResult=new HashMap<Integer,String>();

		for (int i = 0; i < Result.nSize; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[0]) + i,
					String.valueOf(RandomResults.charAt(i)));
		}
		return Result;
	}*/

	/**
	 * GB/T_19378-2003 (?#ALGNAME=PesticideFormulationCode)(?#PARA=0,-1){]
	 * user:lly
*/
	public static FunctionResult PesticideFormulationCode_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");

		RecoDaoRandom recoDao = new RecoDaoRandom();
		int length = recoDao.getLengthPublicFunction(
				RecoUtilRandom.PesticideFormulationCode_TABLE,
				RecoDaoRandom.hashMapePesticideFormulationCode);
		// Random random = new Random();
		int RandomIndex = RandomChar.generateRandomInt(length);
		RandomResults = recoDao.getRandomIndexPublicFunction(
				RecoUtilRandom.PesticideFormulationCode_TABLE, RandomIndex,
				RecoDaoRandom.hashMapePesticideFormulationCode);

		// InPutResult.FunctionResult=new HashMap<Integer,String>();

		for (int i = 0; i <RandomResults.length() ; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[0]) + i,
					String.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}


	/**
	 * GB/T_12403-1990 (?#ALGNAME=OfficialPostionCode)(?#PARA=0,1,2,3){]
	 * user:lly
	 */

	public static FunctionResult OfficialPostionCode_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");

		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.officialposition_TABLE,
				RecoDaoRandom.hashMapofficialposition);
		// Random random = new Random();
		int RandomIndex = RandomChar.generateRandomInt(length);
		RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.officialposition_TABLE, RandomIndex,
				RecoDaoRandom.hashMapofficialposition);

		// InPutResult.FunctionResult=new HashMap<Integer,String>();

		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		return Result;
	}

	/**
	 * JT/T_132-2003_58 (?#ALGNAME=HighwayDatabase24)(?#PARA=0,1){]
	 * user:lly
	 */

	public static FunctionResult HighwayDatabase24_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		// String RandomResults = "";
		String[] splitIndex = index.split(",");
		String[] randomresult = { "10", "11", "12", "13", "14", "15", "16",
				"17", "18", "20", "21", "22", "23", "24", "25", "26", "27",
				"30", "31", "32", "40", "50", "60", "61", "62", "63", "70",
				"80", "90" };
		// int maxlength = randomresult.length;
		int indexlength = RandomChar.generateRandomInt(randomresult.length);
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					randomresult[indexlength].charAt(i) + "");
		}
		return Result;
	}

	/**
	 * JT/T_132-2003_56 (?#ALGNAME=HighwayDatabase26)(?#PARA=0,1){]
	 * user:lly
	 */
	public static FunctionResult HighwayDatabase26_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		// String RandomResults = "";
		String[] splitIndex = index.split(",");
		String[] randomresult = { "00", "01", "02", "03", "04", "05", "06",
				"07", "08", "09", "10", "11", "12", "13", "14", "15", "90" };
		// int maxlength = randomresult.length;
		int indexlength = RandomChar.generateRandomInt(randomresult.length);
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					randomresult[indexlength].charAt(i) + "");
		}
		return Result;
	}

	/**
	 * JT/T_132-2003_57 (?#ALGNAME=HighwayDatabase25)(?#PARA=0,1){]
	 */

	public static FunctionResult HighwayDatabase25_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		// String RandomResults = "";
		String[] splitIndex = index.split(",");
		String[] randomresult = { "00", "01", "02", "03", "04", "05", "06",
				"07", "08", "09", "10", "11", "12", "90" };
		// int maxlength = randomresult.length;
		int indexlength = RandomChar.generateRandomInt(randomresult.length);
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					randomresult[indexlength].charAt(i) + "");
		}
		return Result;
	}

	/**
	 * GB/T_12408-1990 (?#ALGNAME=SocialWork)(?#PARA=0,1){]
	 * user:lly
	 */

	public static FunctionResult SocialWork_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		// String RandomResults = "";
		String[] splitIndex = index.split(",");
		String[] randomresult = { "01", "02", "04", "05", "06", "07", "08",
				"09", "10", "41", "42", "43", "51", "61", "62", "63", "64" };
		// int maxlength = randomresult.length;
		int indexlength = RandomChar.generateRandomInt(randomresult.length);
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					randomresult[indexlength].charAt(i) + "");
		}
		return Result;
	}

	/**
	 * JT/T_415-2000_51 (?#ALGNAME=RoadTransportation22)(?#PARA=0,1){]
	 * user:lly
	 */

	public static FunctionResult RoadTransportation22_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String RandomResults = "";
		String[] splitIndex = index.split(",");

		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.roadtransportation22_TABLE,
				RecoDaoRandom.hashMaproadtransportation22);
		// Random random = new Random();
		int RandomIndex = RandomChar.generateRandomInt(length);
		RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.roadtransportation22_TABLE, RandomIndex,
				RecoDaoRandom.hashMaproadtransportation22);

		// InPutResult.FunctionResult=new HashMap<Integer,String>();

		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		return Result;
	}

	/**
	 * WS_364.3-2011_1 (?#ALGNAME=TwobytleCode07)(?#PARA=0,1){]
	 * user:lly
	 */
	public static FunctionResult TwobytleCode07_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		// String RandomResults = "";
		String[] splitIndex = index.split(",");
		String[] randomresult = { "01", "02", "04", "05", "06", "07", "99" };
		// int maxlength = randomresult.length;
		int indexlength = RandomChar.generateRandomInt(randomresult.length);
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					randomresult[indexlength].charAt(i) + "");
		}
		return Result;
	}

	/**
	 * GA/T_974.18-2011 (?#ALGNAME=Fireconstructionlicence)(?#PARA=0,1){]
	 * user:lly
	 */

	public static FunctionResult Fireconstructionlicence_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		// String RandomResults = "";
		String[] splitIndex = index.split(",");
		String[] randomresult = { "10", "11", "12", "13", "19", "20", "90" };
		// int maxlength = randomresult.length;
		int indexlength = RandomChar.generateRandomInt(randomresult.length);
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					randomresult[indexlength].charAt(i) + "");
		}
		return Result;
	}

	/**
	 * GA/T_974.50-2011 (?#ALGNAME=FireInfotainrate)(?#PARA=0,1){]
	 * user:lly
	 */
	public static FunctionResult FireInfotainrate_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		// String RandomResults = "";
		String[] splitIndex = index.split(",");
		String[] randomresult = { "20", "21", "40", "41", "42", "43" };
		// int maxlength = randomresult.length;
		int indexlength = RandomChar.generateRandomInt(randomresult.length);
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					randomresult[indexlength].charAt(i) + "");
		}
		return Result;
	}

	/**
	 * WS_364.10-2011_6 (?#ALGNAME=OneTO39)(?#PARA=0,1){]
	 * user:lly
	 */

	public static FunctionResult OneTO39_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		StringBuffer buf = new StringBuffer();
		String index1 = "";

		char[] charbox0 = { '0', '1', '2', '3', '9' };
		char charrandom0 = RandomChar.randomizeArray(charbox0, charbox0.length);
		if (charrandom0 == '0') {
			index1 = "123456789";

		} else if (charrandom0 == '1' || charrandom0 == '2'
				|| charrandom0 == '3') {
			index1 = "0123456789";
		} else if (charrandom0 == '9') {
			index1 = "9";
		}
		char[] charbox1 = index1.toCharArray();
		char charrandom1 = RandomChar.randomizeArray(charbox1, charbox1.length);
		buf.append(charrandom0).append(charrandom1);
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), buf
					.charAt(i)
					+ "");
		}
		return Result;
	}

	/**
	 * JT/T_132-2003_70(?#ALGNAME=HighwayDatabase13)(?#PARA=0,1){]
	 * user:lly
	 */
	public static FunctionResult HighwayDatabase13_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		// String RandomResults = "";
		String[] splitIndex = index.split(",");
		String[] randomresult = { "10", "11", "12", "20", "21", "22", "23",
				"24", "25", "26", "27", "28", "29", "40", "41", "42", "43",
				"44", "45", "46", "47", "90" };
		// int maxlength = randomresult.length;
		int indexlength = RandomChar.generateRandomInt(randomresult.length);
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					randomresult[indexlength].charAt(i) + "");
		}
		return Result;
	}

	/**
	 * JT/T_132-2003_75 (?#ALGNAME=HighwayDatabase7)(?#PARA=0,1){]
	 * user:lly
	 */

	public static FunctionResult HighwayDatabase7_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		// String RandomResults = "";
		String[] splitIndex = index.split(",");
		String[] randomresult = { "GG", "GS", "GX", "GY", "GZ", "SS", "SX",
				"SY", "SZ", "XX", "XY", "XZ", "YY", "YZ" };
		// int maxlength = randomresult.length;
		int indexlength = RandomChar.generateRandomInt(randomresult.length);
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					randomresult[indexlength].charAt(i) + "");
		}
		return Result;
	}

	/**
	 * QC/T_836-2010 (?#ALGNAME=SpecialVehicle)(?#PARA=0,1,2){]
	 * user:lly
	 */

	public static FunctionResult SpecialVehicle_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String RandomResults = "";
		String[] splitIndex = index.split(",");

		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.specialvehicle_TABLE, RecoDaoRandom.hashMapspecialvehicle);
		// Random random = new Random();
		int RandomIndex = RandomChar.generateRandomInt(length);
		RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.specialvehicle_TABLE, RandomIndex,
				RecoDaoRandom.hashMapspecialvehicle);

		// InPutResult.FunctionResult=new HashMap<Integer,String>();

		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}

	/**
	 * GA/T_760.1-760.12-2008 (?#ALGNAME=PublicSecutity)(?#PARA=0,1,2){]
	 * user:lly
	 */
	public static FunctionResult PublicSecutity_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		// String RandomResults = "";

		String[] splitIndex = index.split(",");
		String[] randomresult = { "110", "111", "112", "113", "114", "115",
				"116", "117", "119", "120", "121", "122", "129", "130", "131",
				"139", "140", "141", "142", "100", "149", "199", "200", "300",
				"999" };
		// int maxlength = randomresult.length;
		int indexlength = RandomChar.generateRandomInt(randomresult.length);
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					randomresult[indexlength].charAt(i) + "");
		}

		return Result;
	}

	/**
	 * JT/T_415-2000_38 (?#ALGNAME=RoadTransportation41)(?#PARA=0,1,2){]
	 * user:lly
	 */
	public static FunctionResult RoadTransportation41_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String RandomResults = "";
		String[] splitIndex = index.split(",");

		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.roadtransportation41_TABLE,
				RecoDaoRandom.hashMaproadtransportation41);
		// Random random = new Random();
		int RandomIndex = RandomChar.generateRandomInt(length);
		RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.roadtransportation41_TABLE, RandomIndex,
				RecoDaoRandom.hashMaproadtransportation41);

		// InPutResult.FunctionResult=new HashMap<Integer,String>();

		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}

	/**
	 * GA_398.20-2002 (?#ALGNAME=DocumentEnvidence)(?#PARA=1,2,3){]
	 * user:lly
	 */
	public static FunctionResult DocumentEnvidence_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		StringBuffer buf = new StringBuffer();
		String index1 = "";

		char[] charbox0 = { '0', '1', '2', '3' };
		char charrandom0 = RandomChar.randomizeArray(charbox0, charbox0.length);
		char[] charbox1 = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		char charrandom1 = RandomChar.randomizeArray(charbox1, charbox1.length);
		char charrandom2 = RandomChar.randomizeArray(charbox1, charbox1.length);
		buf.append(charrandom0).append(charrandom1).append(charrandom2);
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), buf
					.charAt(i)
					+ "");
		}

		return Result;
	}

	/**
	 * GA/T_974.22-2011 (?#ALGNAME=Firelocation)(?#PARA=0,1){]
	 * user:lly
	 */

	public static FunctionResult Firelocation_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		// String RandomResults = "";

		String[] splitIndex = index.split(",");
		String[] randomresult = { "10", "11", "12", "20" };
		// int maxlength = randomresult.length;
		int indexlength = RandomChar.generateRandomInt(randomresult.length);
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					randomresult[indexlength].charAt(i) + "");
		}

		return Result;
	}

	/**
	 * GA/T_852.1-2009 (?#ALGNAME=Recreationplace)(?#PARA=0,1,2){]
	 * user:lly
	 */
	public static FunctionResult Recreationplace_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		// String RandomResults = "";

		String[] splitIndex = index.split(",");
		String[] randomresult = { "J01", "J02", "H01", "H02", "J99", "H99" };
		// int maxlength = randomresult.length;
		int indexlength = RandomChar.generateRandomInt(randomresult.length);
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					randomresult[indexlength].charAt(i) + "");
		}

		return Result;
	}

	/**
	 * GA/T_974.45-2011 (?#ALGNAME=FireInfotrain)(?#PARA=0,1,2,3){]
	 * user:lly
	 */
	public static FunctionResult FireInfotrain_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		// String RandomResults = "";

		String[] splitIndex = index.split(",");
		String[] randomresult = { "0200", "0201", "0202", "0203", "0299",
				"0099" };
		// int maxlength = randomresult.length;
		int indexlength = RandomChar.generateRandomInt(randomresult.length);
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					randomresult[indexlength].charAt(i) + "");
		}

		return Result;
	}

	/**
	 * GB/T_25344-2010 (?#ALGNAME=CodeHighWayLine)(?#PARA=0,1,2,3){]
	 * user:lly
	 */
	public static FunctionResult CodeHighWayLine_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();

		Result = InPutResult;
		String[] splitIndex = index.split(",");
		String resultsring = "";
		boolean flag = false;
		for (int i = 0; i < splitIndex.length; i++) {
			char[] charbox = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
			char charrandom = RandomChar
					.randomizeArray(charbox, charbox.length);
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(charrandom));
			if (charrandom == '0') {
				flag = true;
			}
		}

		if (flag) {
			int indexint = RandomChar.generateRandomInt(splitIndex.length);
			char[] charbox1 = { '1', '2', '3', '4', '5', '6', '7', '8', '9' };
			char charrandom1 = RandomChar.randomizeArray(charbox1,
					charbox1.length);
			Result.FunctionResult.put(Integer.parseInt(splitIndex[indexint]),
					String.valueOf(charrandom1));
		}

		return Result;
	}

	/**
	 * GB/T_21394-2008 (?#ALGNAME=TrafficInformation)(?#PARA=2,3){]
	 * user:lly
	 */
	//modified by lly on 0901
	 public static FunctionResult TrafficInformation_Random(
				FunctionResult InPutResult, String index) {
			FunctionResult Result = new FunctionResult();
			Result = InPutResult;
			StringBuffer buf = new StringBuffer();
			buf.append(Result.FunctionResult.get(0)).append(
					Result.FunctionResult.get(1));
			String index01 = buf.toString();

			String[] splitIndex = index.split(",");
			String[] randomresult01 = { "01", "02", "03", "04" };
			String[] randomresult23 = {};
			List<String> ls01= new ArrayList<String>();
			ls01=Arrays.asList(randomresult01);
			String index23;
			// 1,2位：01 02 03 04
			if (ls01.contains(index01)) {
				randomresult23 = new String[] { "01", "02", "03", "04", "99" };
			} else if (index01.equals("05")) {
				randomresult23 = new String[] { "01", "02", "03", "04", "99", "05" };
			} else if (index01.equals("99")) {
				randomresult23 = new String[] { "99" };
			}
			//System.out.println(index01);
			int indexlength = RandomChar.generateRandomInt(randomresult23.length);
		//3,4位
			for (int i = 0; i < splitIndex.length; i++) {
				Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
						randomresult23[indexlength].charAt(i) + "");
			}

			return Result;
		}


	/**
	 * GB/T_15420-2009 (?#ALGNAME=StevedorageChartering)(?#PARA=0,1){]
	 * user:lly
	 */

	public static FunctionResult StevedorageChartering_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String[] splitIndex = index.split(",");
		String[] randomresult = { "LT", "LF", "FI", "FO", "FN", "FT", "FS",
				"FA", "FD", "FL", "UT", "DL", "TD", "TW", "VF", "VL", "VP",
				"VA", "AC", "BC" };
		int indexlength = RandomChar.generateRandomInt(randomresult.length);
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					randomresult[indexlength].charAt(i) + "");
		}

		return Result;
	}

	/**
	 * JT/T_132-2003_74 (?#ALGNAME=HighwayDatabase8)(?#PARA=0,1){]
	 * user:lly
	 */
	public static FunctionResult HighwayDatabase8_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String[] splitIndex = index.split(",");
		String[] randomresult = { "10", "11", "12", "13", "14", "15", "16",
				"17", "18", "19", "20", "21", "22", "23", "24", "25", "26",
				"27", "28", "29", "30", "40", "41", "42", "50", "60", "70",
				"80", "90" };
		int indexlength = RandomChar.generateRandomInt(randomresult.length);
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					randomresult[indexlength].charAt(i) + "");
		}

		return Result;
	}

	/**
	 * GA/T_974.71-2011 (?#ALGNAME=FireInfoBuild)(?#PARA=0,1){]
	 * user:lly
	 */

	public static FunctionResult FireInfoBuild_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String[] splitIndex = index.split(",");
		String[] randomresult = { "10", "11", "12", "13", "14", "19", "20",
				"21", "22", "23", "24", "25", "29", "30", "31", "32" };
		int indexlength = RandomChar.generateRandomInt(randomresult.length);
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					randomresult[indexlength].charAt(i) + "");
		}

		return Result;
	}

	/**
	 * GA/T_974.1-2011 (?#ALGNAME=Fireexpert)(?#PARA=0,1,2,3){]
	 * user:lly
	 */
	//modified by lly on 0915
	public static FunctionResult Fireexpert_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String[] splitIndex = index.split(",");
		// 求表长
		RandomChar r = RandomChar.getRandomChar();
		// 可能
		List<String> ls = new ArrayList<String>();

		for (int i = 100; i <= 107; i++) {
			ls.add("0" + i);
		}
		for (int i = 199; i <= 203; i++) {
			ls.add("0" + i);
		}
		ls.add("0399");
		ls.add("0400");
		ls.add("0401");
		ls.add("0499");
		ls.add("0500");
		ls.add("0600");
		ls.add("0700");
		ls.add("0800");
		ls.add("0900");
		for (int i = 10; i <= 18; i++) {
			ls.add(StringUtils.rightPad(i + "", 4, "0"));
		}
		String RandomResults = ls.get(r.generateRandomInt(ls.size()));
		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}

	/**
	 * GA/T_972-2011 (?#ALGNAME=Chemicalrisk)(?#PARA=0,1,2){]
	 * user:lly
	 */
	public static FunctionResult Chemicalrisk_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String[] splitIndex = index.split(",");
		// 求表长
		RandomChar r = RandomChar.getRandomChar();
		// 可能
		List<String> ls = new ArrayList<String>();

		for (int i = 100; i <= 116; i++) {
			ls.add("" + i);
		}
		for (int i = 199; i <= 210; i++) {
			ls.add("" + i);
		}
		ls.add("299");
		ls.add("300");
		ls.add("301");
		String RandomResults = ls.get(r.generateRandomInt(ls.size()));
		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}

	/**
	 * GB/T_28442-2012 (?#ALGNAME=ElectronicMap)(?#PARA=0,1,2,3){]
	 * user:lly
	 */

	public static FunctionResult ElectronicMap_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String RandomResults = "";
		String[] splitIndex = index.split(",");

		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.electronicmap_TABLE, RecoDaoRandom.hashMapelectronicmap);
		// Random random = new Random();
		int RandomIndex = RandomChar.generateRandomInt(length);
		RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.electronicmap_TABLE, RandomIndex,
				RecoDaoRandom.hashMapelectronicmap);

		// InPutResult.FunctionResult=new HashMap<Integer,String>();

		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}

	/**
	 * JT/T_132-2003_21 (?#ALGNAME=HighwayDatabase66)(?#PARA=0,1){]
	 * user:lly
	 */

	public static FunctionResult HighwayDatabase66_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String[] splitIndex = index.split(",");
		String[] randomresult = { "11", "12", "13", "14", "15", "16", "17",
				"18", "24", "31", "41", "42", "43", "44", "51", "52", "53",
				"54", "55", "56", "57", "61", "62", "90" };
		int indexlength = RandomChar.generateRandomInt(randomresult.length);
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					randomresult[indexlength].charAt(i) + "");
		}

		return Result;
	}

	/**
	 * JT/T_132-2003_22 (?#ALGNAME=HighwayDatabase65)(?#PARA=0,1){]
	 * user:lly
	 */

	public static FunctionResult HighwayDatabase65_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String[] splitIndex = index.split(",");
		String[] randomresult = { "11", "12", "13", "17", "18", "19" };
		int indexlength = RandomChar.generateRandomInt(randomresult.length);
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					randomresult[indexlength].charAt(i) + "");
		}

		return Result;
	}

	/**
	 * JT/T_132-2003_26 (?#ALGNAME=HighwayDatabase59)(?#PARA=0,1){]
	 * user:lly
	 */
	public static FunctionResult HighwayDatabase59_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String[] splitIndex = index.split(",");
		String[] randomresult = { "10", "11", "12", "20" };
		int indexlength = RandomChar.generateRandomInt(randomresult.length);
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					randomresult[indexlength].charAt(i) + "");
		}

		return Result;
	}

	/**
	 * GA/T_974.16-2011 (?#ALGNAME=Firesupervisionenforcement)(?#PARA=0,1,2,3){]
	 * user:lly
	 */
	public static FunctionResult Firesupervisionenforcement_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String[] splitIndex = index.split(",");
		// 求表长
		RandomChar r = RandomChar.getRandomChar();
		// 可能
		List<String> ls = new ArrayList<String>();

		for (int i = 1000; i <= 1003; i++) {
			ls.add("" + i);
		}
		for (int i = 2000; i <= 2009; i++) {
			ls.add("" + i);
		}
		for (int i = 3000; i <= 3003; i++) {
			ls.add("" + i);
		}
		for (int i = 4000; i <= 4002; i++) {
			ls.add("" + i);
		}
		ls.add("1099");
		ls.add("2099");
		ls.add("3099");
		ls.add("4099");
		ls.add("9900");
		String RandomResults = ls.get(r.generateRandomInt(ls.size()));
		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}

	/**
	 * GB/T_12407-2008 (?#ALGNAME=PositionClass)(?#PARA=0,1,2){]
	 * user:lly
	 * 
	 */
	public static FunctionResult PositionClass_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String[] splitIndex = index.split(",");
		String[] randomresult = { "101", "102", "111", "112", "131", "132",
				"141", "142", "150", "160", "199", "211", "212", "222", "221",
				"231", "232", "241", "242", "250", "260", "299", "411", "412",
				"410", "420", "430", "435", "434", "499" };
		int indexlength = RandomChar.generateRandomInt(randomresult.length);
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					randomresult[indexlength].charAt(i) + "");
		}

		return Result;
	}

	/**
	 * JT/T_415-2000_47 (?#ALGNAME=RoadTransportation32)(?#PARA=0,1){]
	 * user:lly
	 */
	public static FunctionResult RoadTransportation32_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String RandomResults = "";
		String[] splitIndex = index.split(",");

		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.roadtransportation32_TABLE,
				RecoDaoRandom.hashMaproadtransportation32);
		// Random random = new Random();
		int RandomIndex = RandomChar.generateRandomInt(length);
		RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.roadtransportation32_TABLE, RandomIndex,
				RecoDaoRandom.hashMaproadtransportation32);

		// InPutResult.FunctionResult=new HashMap<Integer,String>();

		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}

	/**
	 * GB/T_17297-1998 (?#ALGNAME=Climatic)(?#PARA=0,1,2){]
	 * user:lly
	 */

	public static FunctionResult Climatic_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String[] splitIndex = index.split(",");
		String[] randomresult = { "11A", "12A", "12B", "12C", "12D", "12E",
				"13A", "13B", "13D", "13E", "21A", "22A", "23A", "23B", "29A",
				"31A", "31B", "32A", "33A", "41D", "42A", "42B", "42C", "43A",
				"43B", "43C", "44B", "44C" };
		int indexlength = RandomChar.generateRandomInt(randomresult.length);
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					randomresult[indexlength].charAt(i) + "");
		}

		return Result;
	}

	/**
	 * GA/T_974.29-2011 (?#ALGNAME=Firealarm)(?#PARA=0,1){]
	 * user:lly
	 */

	public static FunctionResult Firealarm_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String[] splitIndex = index.split(",");
		String[] randomresult = { "10", "11", "12", "13", "20", "21", "22",
				"23", "29" };
		int indexlength = RandomChar.generateRandomInt(randomresult.length);
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					randomresult[indexlength].charAt(i) + "");
		}

		return Result;
	}

	/**
	 * GB_918.2-1989 (?#ALGNAME=NonMotorVehicle)(?#PARA=0,1,2){]
	 * user:lly
	 */

	public static FunctionResult NonMotorVehicle_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String[] splitIndex = index.split(",");
		String[] randomresult = { "710", "711", "712", "720", "721", "722",
				"730", "731", "732", "700", "723", "800" };
		int indexlength = RandomChar.generateRandomInt(randomresult.length);
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					randomresult[indexlength].charAt(i) + "");
		}

		return Result;
	}

	/**
	 * WS_364.16-2011_5 (?#ALGNAME=CommunicationCode)(?#PARA=0,-1){]
	 * user:lly
	 */
	public static FunctionResult CommunicationCode_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String RandomResults = "";
		String[] splitIndex = index.split(",");

		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.communicationmediacode_TABLE,
				RecoDaoRandom.hashMapcommunicationmediacode);
		// Random random = new Random();
		int RandomIndex = RandomChar.generateRandomInt(length);
		RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.communicationmediacode_TABLE, RandomIndex,
				RecoDaoRandom.hashMapcommunicationmediacode);

		// InPutResult.FunctionResult=new HashMap<Integer,String>();
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[0]) + i,
					String.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}

	/**
	 * WS_364.16-2011_2 (?#ALGNAME=OneTO72)(?#PARA=0,1){]
	 * user:lly
	 */
	public static FunctionResult OneTO72_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String[] splitIndex = index.split(",");
		// 求表长
		RandomChar r = RandomChar.getRandomChar();
		// 可能
		List<String> ls = new ArrayList<String>();

		// i >= 1 && i <= Xx || i == 99||i == 90
		// 01~72
		for (int i = 1; i <= 72; i++) {
			ls.add(StringUtils.leftPad(i + "", 2, "0"));
		}
		ls.add("99");
		ls.add("90");
		String RandomResults = ls.get(r.generateRandomInt(ls.size()));

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}

	/**
	 * GB/T_7635.2-2002 (?#ALGNAME=UntransportableProduct)(?#PARA=0,-1){]
	 * user:lly
	 */
	public static FunctionResult UntransportableProduct_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String RandomResults = "";
		String[] splitIndex = index.split(",");

		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.untransportableproduct_TABLE,
				RecoDaoRandom.hashMapuntransportableproduct);
		// Random random = new Random();
		int RandomIndex = RandomChar.generateRandomInt(length);
		RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.untransportableproduct_TABLE, RandomIndex,
				RecoDaoRandom.hashMapuntransportableproduct);

		// InPutResult.FunctionResult=new HashMap<Integer,String>();

		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[0]) + i,
					String.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}

	/**
	 * GB/T_16986-2009 (?#ALGNAME=BarCodeForCommodity)(?#PARA=0,-1){]
	 * user:lly
	 */
	public static FunctionResult BarCodeForCommodity_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String RandomResults = "";
		String[] splitIndex = index.split(",");

		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.barcodeforcommodity_TABLE,
				RecoDaoRandom.hashMapbarcodeforcommodity);
		// Random random = new Random();
		int RandomIndex = RandomChar.generateRandomInt(length);
		RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.barcodeforcommodity_TABLE, RandomIndex,
				RecoDaoRandom.hashMapbarcodeforcommodity);

		// InPutResult.FunctionResult=new HashMap<Integer,String>();

		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[0]) + i,
					String.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}
	
	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@wangbin@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@


	

	/**
	 * User: wb Date: 2014-8-12 288-——服装名字分类代码编制方法 查表数据库 IDstr: 标识编码 LenID:
	 * 标识编码的长度5位 Index: 调用验证算法的索引位置 LenIndex:a3 creator:fdl
	 * (?#ALGNAME=ClothesName)(?#PARA=0,1,2,3,4){] GB/T_23559-2009
	 */

	public static FunctionResult ClothesName_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.ClothesName_TABLE, RecoDaoRandom.hashMapClothesName);

		// 得到随机index的code
		RandomChar r = RandomChar.getRandomChar();
		int RandomIndex = 0;
		RandomIndex = r.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.ClothesName_TABLE, RandomIndex,
				RecoDaoRandom.hashMapClothesName);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			InPutResult.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}

		Result = InPutResult;

		return Result;
	}

	/**
	 * User: wb Date: 2014-8-12 验证标准11的前六位是不是在数据库中
	 * HydrologicData(?#ALGNAME=HydrologicData)(?#PARA=0,1,2,3,4,5){]
	 * 
	 */
	public static FunctionResult HydrologicData_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.HydrologicData_TABLE, RecoDaoRandom.hashMapHydrologicData);

		// 得到随机index的code
		RandomChar r = RandomChar.getRandomChar();
		int RandomIndex = 0;
		RandomIndex = r.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.HydrologicData_TABLE, RandomIndex,
				RecoDaoRandom.hashMapHydrologicData);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			InPutResult.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}

		Result = InPutResult;

		return Result;
	}

	/**
	 * User: wb Date: 2014-8-12 GA_658.7-2006 (?#ALGNAME=OneTO09)(?#PARA=0,1){]
	 * (?#ALGNAME=FourByteDecimalnt)(?#PARA=2,3,4,5){]
	 * OneTO09_Random与FourByteDecimalnt_Random
	 */

	 public static FunctionResult OneTO09_Random(FunctionResult InPutResult,
				String index) {
			FunctionResult Result = new FunctionResult();
			Result = InPutResult;
			String[] splitIndex = index.split(",");

			RandomChar r = RandomChar.getRandomChar();
			int RandomIndex = 0;
			int judge = r.generateRandomInt(10);
			if (judge > 5) {
				// 0 1~9
				StringBuffer RandomResults_Buffer = new StringBuffer();
				RandomResults_Buffer.append("0");
				RandomResults_Buffer.append(r.generateRandomInt(9) + 1 + "");
				String RandomResults = RandomResults_Buffer.toString();
				for (int i = 0; i < RandomResults.length(); i++) {
					Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
							String.valueOf(RandomResults.charAt(i)));
				}
			} else {
				// 99
				String RandomResults = "99";
				for (int i = 0; i < RandomResults.length(); i++) {
					Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
							String.valueOf(RandomResults.charAt(i)));
				}
			}

			

			return Result;
		}

	

	/**
	 * User: wb Date: 2014-8-12 LS/T_1707.1-2004 长度5,6
	 * (?#ALGNAME=GrainsSmartWMS)(?#PARA=0,-1){]
	 */
	public static FunctionResult GrainsSmartWMS_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");

		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.grainsSmartWMS_TABLE, RecoDaoRandom.hashMapgrainsSmartWMS);
		if (splitIndex[splitIndex.length - 1].equals("-1")) {
			splitIndex = new String[length];
			for (int i = 0; i < length; i++) {
				splitIndex[i] = i + "";
			}
		}
		// 得到随机index的code
		RandomChar r = RandomChar.getRandomChar();
		// RandomChar r = RandomChar.getRandomChar();
		int RandomIndex = 0;
		RandomIndex = r.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.grainsSmartWMS_TABLE, RandomIndex,
				RecoDaoRandom.hashMapgrainsSmartWMS);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			InPutResult.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}

		Result = InPutResult;

		return Result;
	}

	/**
	 * User: wb Date: 2014-8-12 carstandardpart 长度14,15
	 * 字节规则0,0,0,0,0,0,0,16,0;1,
	 * 255,3,0,0,0,0,0,0;2,0,0,0,0,224,239,235,31;3,255,
	 * 3,0,0,0,0,0,0;4,255,3,0,0
	 * ,0,0,0,0;5,255,3,0,0,0,0,0,0;6,254,3,0,0,0,0,0,0;
	 * 9,0,0,0,0,0,0,128,0;10,78
	 * ,0,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;12,0,0,0,0,0,2,0,0;
	 * (?#ALGNAME=CarProduct)(?#PARA=13,-1){]
	 * String regex = "[1-6,9][0-4,6,9,A-N,P-Y]{0,1}";
	 */
	//modified by WB on 0917
	public static FunctionResult CarProduct_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result =InPutResult;
		
		String[] splitIndex = index.split(",");
		int length_first = Integer.parseInt(splitIndex[0]);
		// 求表长
//		String regex = "[1-6,9][0-4,6,9,A-N,P-Y]{0,1}";
		RandomChar r = RandomChar.getRandomChar();
		int length = Result.nSize;
		//利用nSize来确定的长度为多少
		StringBuffer sb = new StringBuffer();
		String[] s_1= {"1","2","3","4","5","6","9"};
		String[]s_2 = {"0","1","2","3","4","A","B","C","D","E","F","G","H","I",
                "J","K","L","M","N","P","Q","R","S","T","U","V","W","X",
                "Y"};
		sb.append(s_1[r.generateRandomInt(s_1.length)]);
		for(int i = 0 ;i< length-length_first-1;i++){
			sb.append(s_2[r.generateRandomInt(s_2.length)]);
		}
		String RandomResults =sb.toString();
		
		
		//增加splitIndex长度，由于-1无限大的原因
		

		// 得到随机index的code
		
		
		
		splitIndex = new String[RandomResults.length()];
		for(int i = 0;i<RandomResults.length();i++ ){
			splitIndex[i] = length_first +i +"";
		}
		
		
		
		
		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}


		return Result;
	}

	



	/**
	 * User: wb Date: 2014-8-12 JB/T_5992.10-92 长度5
	 * (?#ALGNAME=Machinery10)(?#PARA=0,1,2,3,4){]
	 */
	public static FunctionResult Machinery10_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.machinery10_TABLE, RecoDaoRandom.hashMapmachinery10);

		// 得到随机index的code
		RandomChar r = RandomChar.getRandomChar();
		int RandomIndex = 0;
		RandomIndex = r.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.machinery10_TABLE, RandomIndex,
				RecoDaoRandom.hashMapmachinery10);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			InPutResult.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}

		Result = InPutResult;

		return Result;
	}

	/**
	 * User: wb Date: 2014-8-12 GB/T_16696-2008 长度15
	 * 3,252,3,0,0,240,255,255,63;4
	 * ,252,3,0,0,240,255,255,63;5,252,3,0,0,240,255,
	 * 255,63;6,255,3,0,0,240,239,235
	 * ,63;7,255,3,0,0,240,239,235,63;9,255,3,0,0,240
	 * ,239,235,63;10,255,3,0,0,240
	 * ,239,235,63;11,0,0,0,0,240,255,0,0;12,255,3,0,
	 * 0,0,0,0,0;13,255,3,0,0,0,0,0,0;14,255,3,0,0,0,0,0,0;
	 * (?#ALGNAME=CountryRegionCode1)(?#PARA=0,1){]
	 * (?#ALGNAME=Hyphen)(?#PARA=2){] 
	 * (?#ALGNAME= Right_Diagonal)(?#PARA=6,7,8,9,10){]
	 * CountryRegionCode1,Hyphen,Slash
	 */
    //modified by WB on 0915
	public static FunctionResult CountryRegionCode1_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		RecoDaoRandom recoDao = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = recoDao.getLengthOfcountryregioncode();
		// 得到随机index的code
		RandomChar r = RandomChar.getRandomChar();
		int RandomIndex = 0;
		RandomIndex = r.generateRandomInt(length);
		String RandomResults = recoDao.getRandomIndexcountryregioncode2(RandomIndex);

		// 保存结果
		for (int i = 0; i < splitIndex.length; i++) {
			InPutResult.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}

		Result = InPutResult;

		return Result;
	}
	
	//由数字和/或除字母I、O和Q以外的字母组成
	//modified by WB on 0917
	public static FunctionResult Right_Diagonal_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom recoDao =new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 循环次数应该为参数的长度
		int length = splitIndex.length;
		//System.out.println("长度：" + length);
		
		RandomChar r = new RandomChar();
		StringBuffer sb = new StringBuffer();
		String RandomResults = "";
		if(r.generateRandomInt(10)%2==0){
			//数字与/
			String []regex = {"0","1","2","3","4","5","6","7","8","9","/"};
			// ‘/’的临时位置
			int location1  = r.generateRandomInt(5);
			// 0-9的临时位置
			int location2 = r.generateRandomInt(5);
			//这2个位置不能重复
			while(true){
				if(location2!=location1){
					break;
				}else{
					location2 = r.generateRandomInt(5);
					
				}
			}
			for(int i = 0;i < length;i++){
				if(i==location1){
					sb.append("/");
					continue;
				}
				if(i==location2){
					sb.append(r.generateRandomInt(10) + "");
					continue;
				}
				sb.append(regex[r.generateRandomInt(regex.length)]);
			}
			
		}else{
			//数字与除字母I、O和Q以外的字母组成
			String []regex3= {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F","G","H",
			        "J","K","L","M","N","P","R","S","T","U","V","W","X",
			        "Y","Z"};
			String []regex1 = {"0","1","2","3","4","5","6","7","8","9"};
			String []regex2 = {"A","B","C","D","E","F","G","H",
			        "J","K","L","M","N","P","R","S","T","U","V","W","X",
			        "Y","Z"};
			// 字母的临时位置
			int location1  = r.generateRandomInt(5);
			// 0-9的临时位置
			int location2 = r.generateRandomInt(5);
			//这2个位置不能重复
			while(true){
				if(location2!=location1){
					break;
				}else{
					location2 = r.generateRandomInt(5);
					
				}
			}
			for(int i = 0;i < length;i++){
				if(i==location1){
					sb.append(regex2[r.generateRandomInt(regex2.length)]);
					continue;
				}
				if(i==location2){
					sb.append(regex1[r.generateRandomInt(regex1.length)]);
					continue;
				}
				sb.append(regex3[r.generateRandomInt(regex3.length)]);
			}
			
		}
		RandomResults = sb.toString();
		
		//System.out.println(RandomResults);
		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			InPutResult.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}
		
		return Result;
	}





	/**
	 * User: wb Date: 2014-8-12 GB/T_25529-2010_1 长度10
	 * 5,255,3,0,0,0,0,0,0;6,255,
	 * 3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,
	 * 0,0,0;9,255,3,0,0,0,0,0,0;
	 * (?#ALGNAME=GeographicInformation)(?#PARA=0,1,2,3,4){]
	 */
	public static FunctionResult GeographicInformation_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.geographicinformation_TABLE,
				RecoDaoRandom.hashMapgeographicinformation);
		// 得到随机index的code
		RandomChar r = RandomChar.getRandomChar();
		int RandomIndex = 0;
		RandomIndex = r.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.geographicinformation_TABLE, RandomIndex,
				RecoDaoRandom.hashMapgeographicinformation);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			InPutResult.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}

		Result = InPutResult;

		return Result;
	}

	/**
	 * User: wb Date: 2014-8-12 GB/T_25529-2010_2 -1
	 * 5,255,3,0,0,0,0,0,0;6,255,3,
	 * 0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;
	 * (?#ALGNAME=GeographicInformation)(?#PARA=0,1,2,3,4){]
	 * (?#ALGNAME=GeographicPropertyRegex)(?#PARA=10,-1){]
	 */

	public static FunctionResult GeographicPropertyRegex_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		// 求表长
		int length = Result.nSize;
		//System.out.println("Result.nSize : " + Result.nSize);
		// 得到随机index的code
		RandomChar r = RandomChar.getRandomChar();

		int length_first = Integer.parseInt(splitIndex[0]);
		// 为了生成length-length_fist位数字
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length - length_first; i++) {
			sb.append(r.generateRandomInt(9) + 1 + "");
		}

		String RandomResults = sb.toString();
		//System.out.println("####RandomResults" + RandomResults);
		splitIndex = new String[RandomResults.length()];
		for (int i = 0; i < RandomResults.length(); i++) {
			splitIndex[i] = length_first + i + "";
		}
		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			InPutResult.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}

	

	/**
	 * User: wb Date: 2014-8-12
	 * 
	 * YC/T_256.2-2008 8 6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;
	 * (?#ALGNAME=TrafficOrganization)(?#PARA=0,1){]
	 * (?#ALGNAME=First4CharsofAdminDivisionforCiga)(?#PARA=2,3,4,5){]
	 * 
	 */
	public static FunctionResult First4CharsofAdminDivisionforCiga_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.AdminDivision_TABLE, RecoDaoRandom.hashMapAdminDivision);
		// 得到随机index的code
		RandomChar r = RandomChar.getRandomChar();
		int RandomIndex = 0;
		RandomIndex = r.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.AdminDivision_TABLE, RandomIndex,
				RecoDaoRandom.hashMapAdminDivision);
		// 保存结果
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}

	public static FunctionResult TrafficOrganization_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.tobaccoorganization_TABLE,
				RecoDaoRandom.hashMaptobaccoorganization);
		// 得到随机index的code
		RandomChar r = RandomChar.getRandomChar();
		int RandomIndex = 0;
		RandomIndex = r.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.tobaccoorganization_TABLE, RandomIndex,
				RecoDaoRandom.hashMaptobaccoorganization);
		// 保存结果
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}

	/**
	 * User: wb Date: 2014-8-12 GB/T_16772-1997 12
	 * (?#ALGNAME=CoalInterment)(?#PARA=0,1,2,3,4,5,6,7,8,9,10,11){]
	 * 
	 */
	public static FunctionResult CoalInterment_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		// 求表长
		int length = Result.nSize;
		RandomChar r = RandomChar.getRandomChar();
		// 合理数组
		String[] s01 = { "02", "03", "04", "19", "50" };
		String[] s23 = { "24", "25", "35", "39", "11", "12", "13", "22", "23" };
		String[] s45 = { "01", "02", "09", "10", "49" };
		String[] s6 = { "0", "1", "2", "3", "4", "5", "6" };
		String[] s7 = { "1", "2", "3", "4", "5" };
		String[] s89 = { "00", "01", "02", "29", "30" };
		String[] s1011 = { "00", "01", "02", "31", "32" };
		StringBuffer sb = new StringBuffer();
		sb.append(s01[r.generateRandomInt(s01.length)]);
		sb.append(s23[r.generateRandomInt(s23.length)]);
		sb.append(s45[r.generateRandomInt(s45.length)]);
		sb.append(s6[r.generateRandomInt(s6.length)]);
		sb.append(s7[r.generateRandomInt(s7.length)]);
		sb.append(s89[r.generateRandomInt(s89.length)]);
		sb.append(s1011[r.generateRandomInt(s1011.length)]);

		String RandomResults = sb.toString();

		// 保存结果
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}

	/**
	 * User: wb Date: 2014-8-13 GB/T_19234-2003 6-8
	 * 0,0,0,0,0,0,16,0,0;1,0,0,0,0,0,0,64,0;2,0,0,0,0,0,0,4,0;
	 * (?#ALGNAME=Underline)(?#PARA=3){]
	 * (?#ALGNAME=PassengerCarCode)(?#PARA=4,-1){] PassengerCarCode与Underline
	 */

	//modified by WB on 0917
	public static FunctionResult Underline_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		// 求表长
		int length = Result.nSize;
		String regex = "[_]";

		// 得到随机index的code
		String RandomResults = "_";

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			InPutResult.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}

	public static FunctionResult PassengerCarCode_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		RandomChar r = RandomChar.getRandomChar();
		int length = Result.nSize;
		// 求表长
		// int length =
		// RecoDaoRandom.getLengthPublicFunction(RecoUtilRandom.passengerCarCode_TABLE,
		// RecoDaoRandom.hashMappassengerCarCode);

		int length_first = Integer.parseInt(splitIndex[0]);

		// 得到随机index的code

		int RandomIndex = 0;
		RandomIndex = r.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.passengerCarCode_TABLE, RandomIndex,
				RecoDaoRandom.hashMappassengerCarCode);

		splitIndex = new String[RandomResults.length()];
		for (int i = 0; i < RandomResults.length(); i++) {
			splitIndex[i] = length_first + i + "";
		}

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}

	/**
	 * User: wb Date: 2014-8-13 LS/T_1704.2-2004 8
	 * (?#ALGNAME=GrainsQualityStandard)(?#PARA=0,1,2,3,4,5,6,7){]
	 */

	public static FunctionResult GrainsQualityStandard_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.grainsqualitystandard_TABLE,
				RecoDaoRandom.hashMapgrainsqualitystandard);

		// 得到随机index的code
		RandomChar r = RandomChar.getRandomChar();
		int RandomIndex = 0;
		RandomIndex = r.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.grainsqualitystandard_TABLE, RandomIndex,
				RecoDaoRandom.hashMapgrainsqualitystandard);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}

	/**
	 * User: wb Date: 2014-8-13 JJF_1051-2009 8
	 * (?#ALGNAME=MeasuringInstrument)(?#PARA=0,1,2,3,4,5,6,7){]
	 */
	public static FunctionResult MeasuringInstrument_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.measuringinstrument_TABLE,
				RecoDaoRandom.hashMapmeasuringinstrument);

		// 得到随机index的code
		RandomChar r = RandomChar.getRandomChar();
		int RandomIndex = 0;
		RandomIndex = r.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.measuringinstrument_TABLE, RandomIndex,
				RecoDaoRandom.hashMapmeasuringinstrument);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}

	/**
	 * User: wb Date: 2014-8-13 JT/T_132-2003_34 6
	 * 3,126,2,0,0,0,0,0,0;4,62,2,0,0,0,0,0,0;5,254,2,0,0,0,0,0,0;
	 * (?#ALGNAME=HighwayDatabase47)(?#PARA=0,1,2){]
	 */
	public static FunctionResult HighwayDatabase47_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.highwaydatabase47_TABLE,
				RecoDaoRandom.hashMaphighwaydatabase47);

		// 得到随机index的code
		RandomChar r = RandomChar.getRandomChar();
		int RandomIndex = 0;
		RandomIndex = r.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.highwaydatabase47_TABLE, RandomIndex,
				RecoDaoRandom.hashMaphighwaydatabase47);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}

	/**
	 * User: wb Date: 2014-8-13 GB/T_28532-2012 11-17
	 * (?#ALGNAME=CarrierIdentifier)(?#PARA=0,-1){]
	 */
	public static FunctionResult CarrierIdentifier_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String[] splitIndex = index.split(",");
		int length_first = Integer.parseInt(splitIndex[0]);
		// 求表长
		// String regex = "[a-zA-Z0-9]{1,7}[0-9]{3}[A-D][0-9]{6}";
		RandomChar r = RandomChar.getRandomChar();
		int length = Result.nSize;
		String ss[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a",
				"b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
				"n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y",
				"z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
				"L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W",
				"X", "Y", "Z" };
		//System.out.println("########length" + length);
		// 利用nSize来确定{11,17}的长度为多少
		int length_1 = length - 10;
		//System.out.println("########length" + length);
		StringBuffer sb = new StringBuffer();
		// 第一部分[a-zA-Z0-9]{1,7}
		for (int i = 0; i < length_1; i++) {
			// 随机获取RecoUtilRandom.BoxatozAtoZ0to9内的字符
			sb.append(ss[r.generateRandomInt(ss.length)]);
		}
		//System.out.println(sb);
		// 第二部分[0-9]{3}
		for (int i = 0; i < 3; i++) {
			sb.append(r.generateRandomInt(10) + "");
		}
		//System.out.println(sb);
		// 第三部分[A-D]
		String[] ss2 = { "A", "B", "C", "D" };
		sb.append(ss2[r.generateRandomInt(ss2.length)]);
		//System.out.println(sb);
		// 第四部分[0-9]{6}
		for (int i = 0; i < 6; i++) {
			sb.append(r.generateRandomInt(10) + "");
		}
		//System.out.println(sb);
		String RandomResults = sb.toString();

		// 增加splitIndex长度，由于-1无限大的原因

		// 得到随机index的code

		splitIndex = new String[length];
		for (int i = 0; i < length; i++) {
			splitIndex[i] = length_first + i + "";
		}

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}

	/**
	 * User: wb Date: 2014-8-13 GA/T_974.33-2011 6
	 * (?#ALGNAME=FireCauseCode)(?#PARA=0,1,2,3,4,5){]
	 */

	public static FunctionResult FireCauseCode_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String[] splitIndex = index.split(",");
		int length = Result.nSize;
		StringBuffer sb = new StringBuffer();
		RandomChar r = RandomChar.getRandomChar();
		// 所有的可能
		String[] s_all = new String[13];
		// 第一种可能
		String[] s12_1to9 = { "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		StringBuffer sb1 = new StringBuffer();
		sb1.append("0").append(s12_1to9[r.generateRandomInt(s12_1to9.length)]);
		sb1.append("0000");
		int i = 0;
		s_all[i++] = sb1.toString();
		// 第二种
		String sb2 = "980000";
		s_all[i++] = sb2;
		// 第三种
		String sb3 = "990000";
		s_all[i++] = sb3;
		// 第四种 12位固定为01
		String[] s56_00to06or99 = { "00", "01", "02", "03", "04", "05", "06",
				"99" };
		StringBuffer sb4 = new StringBuffer();
		sb4.append("01").append("01").append(
				s56_00to06or99[r.generateRandomInt(s56_00to06or99.length)]);
		s_all[i++] = sb4.toString();
		// 第五种可能
		String[] s56_00to02or99 = { "00", "01", "02", "99" };
		StringBuffer sb5 = new StringBuffer();
		sb5.append("01").append("03").append(
				s56_00to02or99[r.generateRandomInt(s56_00to02or99.length)]);
		s_all[i++] = sb5.toString();
		// 第六种可能
		String[] s34_02or99 = { "02", "99" };
		StringBuffer sb6 = new StringBuffer();
		sb6.append("01").append(
				s34_02or99[r.generateRandomInt(s34_02or99.length)])
				.append("00");
		s_all[i++] = sb6.toString();
		// 第七种可能
		String[] s34_01or05 = { "01", "05" };
		String[] s56_00to03or99 = { "00", "01", "02", "03", "99" };
		StringBuffer sb7 = new StringBuffer();
		sb7.append("02").append(
				s34_01or05[r.generateRandomInt(s34_01or05.length)]).append(
				s56_00to03or99[r.generateRandomInt(s56_00to03or99.length)]);
		s_all[i++] = sb7.toString();
		// 第八种可能
		String[] s34_02or03 = { "02", "03" };
		String[] s56_00to04or99 = { "00", "01", "02", "03", "04", "99" };
		StringBuffer sb8 = new StringBuffer();
		sb8.append("02").append(
				s34_02or03[r.generateRandomInt(s34_02or03.length)]).append(
				s56_00to04or99[r.generateRandomInt(s56_00to04or99.length)]);
		s_all[i++] = sb8.toString();
		// 第九种可能
		String[] s56_00to08or99 = { "00", "01", "02", "03", "04", "05", "06",
				"07", "08", "99" };
		StringBuffer sb9 = new StringBuffer();
		sb9.append("02").append("04").append(
				s56_00to08or99[r.generateRandomInt(s56_00to08or99.length)]);
		s_all[i++] = sb9.toString();
		// 第十种可能
		s_all[i++] = "029900";
		// 第十一种可能34位置比较特殊>0 <12 or99
		String[] s_34_special = { "01", "02", "03", "04", "05", "06", "07",
				"08", "09", "10", "11", "99" };
		StringBuffer sb11 = new StringBuffer();
		sb11.append("03").append(
				s_34_special[r.generateRandomInt(s_34_special.length)]).append(
				"00");
		s_all[i++] = sb11.toString();
		// 第十二种可能
		String[] s12_04or06 = { "04", "06" };
		String[] s34_01to03or99 = { "01", "02", "03", "99" };
		StringBuffer sb12 = new StringBuffer();
		sb12.append(s12_04or06[r.generateRandomInt(s12_04or06.length)]).append(
				s34_01to03or99[r.generateRandomInt(s34_01to03or99.length)])
				.append("00");
		s_all[i++] = sb12.toString();

		// 第十三种可能
		String[] s34_01or02or99 = { "01", "02", "99" };
		StringBuffer sb13 = new StringBuffer();
		sb13.append("05").append(
				s34_01or02or99[r.generateRandomInt(s34_01or02or99.length)])
				.append("00");
		s_all[i++] = sb13.toString();

		String RandomResults = s_all[r.generateRandomInt(s_all.length)];

		// 保存结果
		for (int j = 0; j < RandomResults.length(); j++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[j]), String
					.valueOf(RandomResults.charAt(j)));
		}

		return Result;
	}

	/**
	 * User: wb Date: 2014-8-13 tcl 5,-1
	 * (?#ALGNAME=TCLBatteryProduct)(?#PARA=0,1,2,3){]
	 * (?#ALGNAME=ProductCode)(?#PARA=4,-1){]
	 */
	public static FunctionResult TCLBatteryProduct_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.TCLBatteryProduct_TABLE,
				RecoDaoRandom.hashMapTCLBatteryProduct);

		// 得到随机index的code
		RandomChar r = RandomChar.getRandomChar();
		int RandomIndex = 0;
		RandomIndex = r.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.TCLBatteryProduct_TABLE, RandomIndex,
				RecoDaoRandom.hashMapTCLBatteryProduct);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}

	//modified by WB on 0918
	public static FunctionResult ProductCode_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		//分情况,ProductCode表中长度全部为4
		
		// 求表长
		int length = Result.nSize;

		RandomChar r = RandomChar.getRandomChar();
		
		//获取第一位来，判断是哪种情况
		String []require = {"0"};
		String index1 = getRandomCode(Result.FunctionResult, require);
		
		/*
		 * 长度后边位-1无限大的情况！！
		 */

		// 得到随机index的code
		int RandomIndex = 0;
		RandomIndex = r.generateRandomInt(length);
		
		String RandomResults = "";
		if(index1.equals("2")){
			//第一种情况
			StringBuffer sb = new StringBuffer();
			for(int i = 0;i <4;i++){
				sb.append(r.generateRandomInt(10) + "");
			}
			RandomResults = sb.toString();
		}else{
			//第二种情况
			RandomResults= RecoDaoRandom.getRandomIndexPublicFunction(
					RecoUtilRandom.ProductCode_TABLE, RandomIndex,
					RecoDaoRandom.hashMapProductCode);
		}
		// 增加spitIndex长度
		int length_first = Integer.parseInt(splitIndex[0]);

		splitIndex = new String[RandomResults.length()];
		for (int i = 0; i < RandomResults.length(); i++) {
			splitIndex[i] = length_first + i + "";
		}

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}




	/**
	 * User: wb Date: 2014-8-13 MH/T_0018-1999 9
	 * (?#ALGNAME=CivilAviation)(?#PARA=0,1,2,3,4,5,6,7,8){]
	 */
	public static FunctionResult CivilAviation_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.civilaviation_TABLE, RecoDaoRandom.hashMapcivilaviation);

		// 得到随机index的code
		RandomChar r = RandomChar.getRandomChar();
		int RandomIndex = 0;
		RandomIndex = r.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.civilaviation_TABLE, RandomIndex,
				RecoDaoRandom.hashMapcivilaviation);

		// 保存结果
		int min = Math.min(RandomResults.length(), splitIndex.length);
		for (int i = 0; i < min; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					RandomResults.charAt(i)+"");
		}



		return Result;
	}

	/**
	 * User: wb Date: 2014-8-13 GA_99.2-2000 7 0,0,0,0,0,32,2,128,0;
	 * (?#ALGNAME=First4CharsofAdminDivisionforCiga)(?#PARA=1,2,3,4){]
	 * (?#ALGNAME=TwoByteDecimalnt)(?#PARA=5,6){]
	 * First4CharsofAdminDivisionforCiga与TwoByteDecimalnt已有，所以直接进行测试！！
	 * 
	 */

	/**
	 * User: wb Date: 2014-8-13 GB/T_23831-2009 7
	 * (?#ALGNAME=LogisticsInf)(?#PARA=0,1,2,3,4,5,6){]
	 */
	//modified by WB on 0917
	/**
	 * User: wb Date: 2014-9-16 GB/T_23831-2009 5,7
	 * (?#ALGNAME=LogisticsInf)(?#PARA=5,-1){]
	 */
	public static FunctionResult LogisticsInf_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom recoDao = RecoDaoRandom.getRecoDao();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = Result.nSize;
		// 增加spitIndex长度
		int length_first = Integer.parseInt(splitIndex[0]);

		/*
		 * 长度后边位-1无限大的情况！！
		 */
		RandomChar r = RandomChar.getRandomChar();
		int RandomIndex = 0;
		RandomIndex = r.generateRandomInt(length);
		String RandomResults = recoDao.getRandomIndexPublicFunction(
				RecoUtilRandom.LogisticsInf_TABLE, RandomIndex,
				recoDao.hashMapLogisticsInf);

		//System.out.println("RandomResults : " + RandomResults);

		splitIndex = new String[length];
		for (int i = 0; i < length; i++) {
			splitIndex[i] = i + "";
		}

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;

	}


	/**
	 * User: wb Date: 2014-8-13 JT/T_438-2001 6 0,2,0,0,0,0,0,0,0;
	 * (?#ALGNAME=OneTO05)(?#PARA=1,2){]
	 * (?#ALGNAME=WaterwayTransportation)(?#PARA=3,4,5){]
	 * OneTO05与WaterwayTransportation
	 */

	public static FunctionResult WaterwayTransportation_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.waterwaytransportation_TABLE,
				RecoDaoRandom.hashMapwaterwaytransportation);
		// 得到随机index的code
		RandomChar r = RandomChar.getRandomChar();
		int RandomIndex = 0;
		RandomIndex = r.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.waterwaytransportation_TABLE, RandomIndex,
				RecoDaoRandom.hashMapwaterwaytransportation);
		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}

	/**
	 * User: wb Date: 2014-8-13 GB/T_16963-2010 9-17
	 * 0,254,3,0,0,0,0,0,0;1,255,3,
	 * 0,0,0,0,0,0;2,255,3,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;
	 * (?#ALGNAME=CountryRegionCode1)(?#PARA=4,5){] 已有
	 * (?#ALGNAME=First2CharsofAdminDivision)(?#PARA=6,7){]
	 * (?#ALGNAME=DraftingRulesForCodes)(?#PARA=8,-1){]
	 */
	

	public static FunctionResult DraftingRulesForCodes_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");

		// 求表长
		int length = Result.nSize;
		// 增加spitIndex长度
		int length_first = Integer.parseInt(splitIndex[0]);
		// String regex = "[A-Z][A-Z0-9]{0,8}";
		// 依据这个生成字符串
		RandomChar r = RandomChar.getRandomChar();
		StringBuffer sb = new StringBuffer();
		String ss[] = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
				"L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W",
				"X", "Y", "Z" };
		sb.append(ss[r.generateRandomInt(ss.length)]);
		String ss2[] = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
				"L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W",
				"X", "Y", "Z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		//System.out.println("###########length_first" + length_first);
		//System.out.println("###########length" + length);
		//System.out.println("###########生成多少" + (length - length_first - 1));
		for (int i = 0; i < length - length_first - 1; i++) {
			sb.append(ss2[r.generateRandomInt(ss2.length)]);
		}

		// 得到随机index的code

		String RandomResults = sb.toString();

		splitIndex = new String[RandomResults.length()];
		for (int i = 0; i < RandomResults.length(); i++) {
			splitIndex[i] = length_first + i + "";
		}

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}

	/**
	 * User: wb Date: 2014-8-13 SB/T_10639-2011 8
	 * 0,1,0,0,0,0,0,0,0;1,4,0,0,0,0,0
	 * ,0,0;2,0,2,0,0,0,0,0,0;3,4,0,0,0,0,0,0,0;7,254,3,0,0,0,0,0,0;
	 * (?#ALGNAME=Egg232)(?#PARA=4,5,6){]
	 */
	public static FunctionResult Egg232_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String[] splitIndex = index.split(",");
		int length = Result.nSize;
		StringBuffer sb = new StringBuffer();
		RandomChar r = RandomChar.getRandomChar();
		// 所有的可能
		String[] s_all = new String[13];
		int i = 0;
		// 第一种可能
		String[] s12_11to12 = { "11", "12" };
		StringBuffer sb1 = new StringBuffer();
		sb1.append(s12_11to12[r.generateRandomInt(s12_11to12.length)]);
		sb1.append(r.generateRandomInt(4) + 1 + "");
		s_all[i++] = sb1.toString();
		// 第二种
		StringBuffer sb2 = new StringBuffer();
		sb2.append("13");
		sb2.append(r.generateRandomInt(7) + 1 + "");
		s_all[i++] = sb2.toString();
		// 第三种
		String[] s12_21to22 = { "21", "22" };
		StringBuffer sb3 = new StringBuffer();
		sb3.append(s12_21to22[r.generateRandomInt(s12_21to22.length)]);
		sb3.append(r.generateRandomInt(3) + 1 + "");
		s_all[i++] = sb3.toString();
		// 第四种
		StringBuffer sb4 = new StringBuffer();
		sb4.append("23");
		sb4.append(r.generateRandomInt(9) + 1 + "");
		s_all[i++] = sb4.toString();
		// 第五种
		String[] s12_31to33 = { "31", "32", "33" };
		StringBuffer sb5 = new StringBuffer();
		sb5.append(s12_31to33[r.generateRandomInt(s12_31to33.length)]);
		sb5.append(r.generateRandomInt(2) + 1 + "");
		s_all[i++] = sb5.toString();
		// 第六种
		String[] s12_41to43 = { "41", "42", "43" };
		StringBuffer sb6 = new StringBuffer();
		sb6.append(s12_41to43[r.generateRandomInt(s12_41to43.length)]);
		sb6.append(r.generateRandomInt(2) + 1 + "");
		s_all[i++] = sb6.toString();
		// 第七种
		String[] s12_51to53 = { "51", "52", "53" };
		StringBuffer sb7 = new StringBuffer();
		sb7.append(s12_51to53[r.generateRandomInt(s12_51to53.length)]);
		sb7.append(r.generateRandomInt(9) + 1 + "");
		s_all[i++] = sb7.toString();
		// 第八种
		String[] s12_61to63 = { "61", "62", "63" };
		StringBuffer sb8 = new StringBuffer();
		sb8.append(s12_61to63[r.generateRandomInt(s12_61to63.length)]);
		sb8.append(r.generateRandomInt(5) + 1 + "");
		s_all[i++] = sb8.toString();
		// 第九种
		String[] s12_81to84 = { "81", "82", "83", "84" };
		StringBuffer sb9 = new StringBuffer();
		sb9.append(s12_81to84[r.generateRandomInt(s12_81to84.length)]);
		sb9.append(r.generateRandomInt(5) + 1 + "");
		s_all[i++] = sb9.toString();
		// 第十种
		String[] s12_71to73 = { "71", "72", "73" };
		StringBuffer sb10 = new StringBuffer();
		sb10.append(s12_71to73[r.generateRandomInt(s12_71to73.length)]);
		sb10.append(r.generateRandomInt(4) + 1 + "");
		s_all[i++] = sb10.toString();
		// 第十一种
		StringBuffer sb11 = new StringBuffer();
		sb11.append("91").append(r.generateRandomInt(4) + 1 + "");
		s_all[i++] = sb11.toString();
		// 第十二种
		StringBuffer sb12 = new StringBuffer();
		sb12.append("92").append(r.generateRandomInt(8) + 1 + "");
		s_all[i++] = sb12.toString();

		// 第十三中
		StringBuffer sb13 = new StringBuffer();
		sb13.append("93").append(r.generateRandomInt(7) + 1 + "");
		s_all[i++] = sb13.toString();

		String RandomResults = s_all[r.generateRandomInt(s_all.length)];

		// 保存结果
		for (int j = 0; j < RandomResults.length(); j++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[j]), String
					.valueOf(RandomResults.charAt(j)));
		}

		return Result;
	}

	/**
	 * User: wb Date: 2014-8-13 GB/T_15628.1-2009 16
	 * 2,255,3,0,0,0,0,0,0;3,255,3,
	 * 0,0,0,0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,
	 * 0,0,0;6,255,3,0,0,0,0,0,0;7,
	 * 255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,
	 * 0,0,0,0,0,0;10,255,3,0,0,0,0
	 * ,0,0;11,255,3,0,0,0,0,0,0;12,255,3,0,0,0,0,0,0
	 * ;13,255,3,0,0,0,0,0,0;14,1,0,0,0,0,0,0,0;15,1,0,0,0,0,0,0,0;
	 * (?#ALGNAME=ChinaAnimal)(?#PARA=0,1){]
	 */
	public static FunctionResult ChinaAnimal_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String[] splitIndex = index.split(",");
		int length = Result.nSize;
		StringBuffer sb = new StringBuffer();
		RandomChar r = RandomChar.getRandomChar();
		// 所有的可能
		String[] s_all = { "AG", "AM", "AV", "MA", "PS", "RP" };

		String RandomResults = s_all[r.generateRandomInt(s_all.length)];

		// 保存结果
		for (int j = 0; j < RandomResults.length(); j++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[j]), String
					.valueOf(RandomResults.charAt(j)));
		}

		return Result;
	}

	/**
	 * User: wb Date: 2014-8-13 GB/T_15694.1-1995 
	 * 7,-1
	 * 0,255,3,0,0,0,0,0,0;1,255,
	 * 3,0,0,0,0,0,0;2,255,3,0,0,0,0,0,0;3,255,3,0,0,0,
	 * 0,0,0;4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;
	 * (?#ALGNAME=IdentificationCardsP1)(?#PARA=6,-1){]
	 */
	public static FunctionResult IdentificationCardsP1_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result =InPutResult;
		index = index.replace(";", ",");
		String[] splitIndex = index.split(",");
		int length_first = Integer.parseInt(splitIndex[0]);
		// 求表长
//		String regex = "[0-9]{?}";
		RandomChar r = RandomChar.getRandomChar();
		int length = Result.nSize;
		//利用nSize来确定{?,-1}的长度为多少
		String []ss = {"0","1", "2", "3", "4", "5", "6", "7", "8", "9"};
		StringBuffer sb = new StringBuffer();
		int length_other = length-length_first;
		//由于niot的RuleFunction中，将字节规则生成的也算进校验序列中了,按照RuleFunction来
		List<String> ls = new ArrayList<String>();
		for(int i = 0;i < length_first;i++){
			ls.add(i + "");
		}
		String[]require = ls.toArray(new String[ls.size()]);
		sb.append(getRandomCode(Result.FunctionResult, require));
		//为";"与最后一位校验位留下空间
		for(int i = 0;i<length_other-1;i++){
			sb.append(ss[r.generateRandomInt(ss.length)]);
		}
		
//		sb.append(";");
		RuleRandom.DisplayRandomCode(Result.FunctionResult);
		sb.append(Luhn_verify(sb.toString()));
		//这样是为了排除字节规则产生的字符串
		String RandomResults =sb.substring(length_first, sb.length());
		
		//增加splitIndex长度，由于-1无限大的原因
		
		splitIndex = new String[RandomResults.length()];
		for(int i = 0;i<RandomResults.length();i++ ){
			splitIndex[i] = length_first +i +"";
		}
		
		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}

	
	

	/**
	 * User: wb Date: 2014-8-13 GB/T_22483-2008 8
	 * (?#ALGNAME=MountainRangeAndPeakName)(?#PARA=0,1,2,3,4,5,6,7){]
	 */
	public static FunctionResult MountainRangeAndPeakName_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.mountainrangeandpeakname_TABLE,
				RecoDaoRandom.hashMapmountainrangeandpeakname);

		// 得到随机index的code
		RandomChar r = RandomChar.getRandomChar();
		int RandomIndex = 0;
		RandomIndex = r.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.mountainrangeandpeakname_TABLE, RandomIndex,
				RecoDaoRandom.hashMapmountainrangeandpeakname);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}

	/**
	 * User: wb Date: 2014-8-13 YC/T_210.1-2006 5
	 * (?#ALGNAME=TobaccoLeafClass)(?#PARA=0,1,2,3,4){]
	 */
	public static FunctionResult TobaccoLeafClass_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.tobaccoleafclass_TABLE,
				RecoDaoRandom.hashMaptobaccoleafclass);

		// 得到随机index的code
		RandomChar r = RandomChar.getRandomChar();
		int RandomIndex = 0;
		RandomIndex = r.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.tobaccoleafclass_TABLE, RandomIndex,
				RecoDaoRandom.hashMaptobaccoleafclass);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}

	/**
	 * User: wb Date: 2014-8-13 GB/T_21076-2007 12
	 * 2,0,0,0,0,128,3,37,32;3,255,3,
	 * 0,0,240,255,255,63;4,255,3,0,0,240,255,255,63
	 * ;5,255,3,0,0,240,255,255,63;6
	 * ,255,3,0,0,240,255,255,63;7,255,3,0,0,240,255
	 * ,255,63;8,255,3,0,0,240,255,255
	 * ,63;9,255,3,0,0,240,255,255,63;10,255,3,0,0,240,255,255,63;
	 * (?#ALGNAME=CountryRegionCode)(?#PARA=0,1){] 对于生成而言可以用CountryRegionCode1
	 * (?#ALGNAME=InternationalSecurities)(?#PARA=0,1,2,3,4,5,6,7,8,9,10;11){]//最后一位为校验位
	 */
	public static FunctionResult InternationalSecurities_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result =InPutResult;
		index = index.replace(";", ",");
		String[] splitIndex = index.split(",");
		
		int length_first = Integer.parseInt(splitIndex[0]);
		// 求表长
//		String regex = "[0-9A-Z]{?}";
		RandomChar r = RandomChar.getRandomChar();
		int length = Result.nSize;
		int length_other = length-length_first;
		

		StringBuffer sb = new StringBuffer();
		String[] require = Arrays.copyOf(splitIndex, splitIndex.length-1);
		sb.append(getRandomCode(Result.FunctionResult,require));
		//System.out.println("遍历之前的11位： "  + sb.toString());
		//System.out.println("^^^Luhn_verify(sb.toString()) :  " + Luhn_verify(sb.toString()));
		String str_11 = Luhn_verify(sb.toString()) + "";
		//System.out.println("生成的第11位校验位为：" + str_11);
		sb.append(str_11);
		
		String RandomResults =sb.toString();
		//System.out.println("##########加上验证码第11位后的RandomResults : " + RandomResults);
		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}


		return Result;
	}

	// char +"" 会自动转化为string类型，然后用Integer强转string为int，防止出现char转int时ansci码问题
	// int sign = 0 or 1;0表示从0位开始 1表示从第一位开始
	public static int Luhn_verify(String str) {
		StringBuffer sb = new StringBuffer();
		
		//根据规则，有字母的话是从第一位开始隔位*2，没有字母是从第二位算起隔位*2
		int sign = 1;
		// 首先将str中的A-Z转换为10-35，sign用来判断是否有字母，有字母的话，sign=2，%2==0,从第一位开始进行*2运算
		//没有字母的话sign=1，从第二位开始进行*2运算
		//依据标准GB/T_15694.1-1995 与 GB/T_21076-2007
		for (char c : str.toCharArray()) {
			if (c >= 65) {
				sb.append(c - 55 + "");
				sign = 2;
			} else {
				sb.append(c + "");
			}
		}
		str = sb.toString();
		//System.out.println("(转换A-Z后)的字符串为： " + str);
		sb = new StringBuffer();
		// 第一步，每隔一位 此位数*2
		int str_length  = str.length();
		for (int i = 0; i < str_length; i++) {
			// *2变为字符串放入sb中
			if (sign % 2 == 0) {
				sb.append(Integer.parseInt(str.charAt(i) + "") * 2 + "");
			} else {
				sb.append(str.charAt(i) + "");
			}
		}
		// 第二步，每一位相加
		int verify = 0;
		for (char c : sb.toString().toCharArray()) {
			verify += Integer.parseInt(c + "");
		}
		//System.out.println("######verify" + verify);
		// 第三步,整数向上取整
		int verify_2 = (int) (Math.ceil((double) verify / 10) * 10);
		//System.out.println("######verify_2:" + verify_2);
		return verify_2 - verify;
	}
//	public static void main(String[] args) {
//		String str ="TME83883105";
//		System.out.println("##########结果是:" + Luhn_verify_AtoZ(str));
//	}
	
	
	
	
	
	
	
	
	

	/**
	 * User: wb Date: 2014-8-13 GB/T_18283-2008_2 13
	 * 0,4,0,0,0,0,0,0,0;1,31,0,0,0
	 * ,0,0,0,0;2,255,3,0,0,0,0,0,0;3,255,3,0,0,0,0,0
	 * ,0;4,255,3,0,0,0,0,0,0;5,255
	 * ,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;8,255,3,0,0
	 * ,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;
	 * (?#ALGNAME=Check4BitBarCode)(?#PARA=8,9,10,11;7){]
	 * (?#ALGNAME=CheckCodeForCommodityCode)(?#PARA=0,1,2,3,4,5,6,7,8,9,10,11,12){]已写
	 */
	
	public static FunctionResult Check4BitBarCode_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result =InPutResult;
		index = index.replace(";", ",");
		String[] splitIndex = index.split(",");
		int length_first = Integer.parseInt(splitIndex[0]);
		// 求表长
		RandomChar r = RandomChar.getRandomChar();
		//获取8,9,10,11 位
		StringBuffer sb = new StringBuffer();
		String []require = Arrays.copyOf(splitIndex, splitIndex.length-1);
		sb.append(getRandomCode(Result.FunctionResult,require));
		//依据8,9,10,11位生成第7位,放入sb中
		int str_7 = Check4BitBarCode(sb.toString());
		//System.out.println("_____##########添加第7位验证码前 : " + sb.toString());
		sb.append(str_7+"");
		
		
		String RandomResults =sb.toString();
		//System.out.println("##########添加第7位验证码前（加上验证码后的RandomResults） : " + RandomResults);
		
		
		
		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}


		return Result;
	}
	public static int Check4BitBarCode(String str){
		//2-
		String []ss_1 = {"0","2","4","6","8","9","1","3","5","7"};
		//3
		String []ss_2 = {"0","3","6","9","2","5","8","1","4","7"};
		//5-
		String []ss_3 = {"0","5","9","4","8","3","7","2","6","1"};
		//加权因子计算
		char cs [] = str.toCharArray();
		StringBuffer sb = new StringBuffer();
		sb.append(ss_1[Integer.parseInt(cs[0] + "")]);
		sb.append(ss_1[Integer.parseInt(cs[1] + "")]);
		sb.append(ss_2[Integer.parseInt(cs[2] + "")]);
		sb.append(ss_3[Integer.parseInt(cs[3] + "")]);
		//每位相加
		int sign = 0;
		for(char c :sb.toString().toCharArray()){
			sign += Integer.parseInt(c+"");
		}
		//乘3去个位数
		String str2 = sign*3+"";
		return Integer.parseInt(str2.charAt(str2.length()-1)+"");
	}
//	public static void main(String[] args) {
//		String str = "0000";
//		System.out.println(Check4BitBarCode(str));
//	}
	
	
	
	
	/**
	 * #########################################
	 */

	/**
	 * User: wb Date: 2014-8-13 LY/T_1080-92 3,-1
	 * 0,0,0,0,0,0,0,1,0;1,0,0,0,0,32,0,0,0;2,255,3,0,0,0,0,0,0;
	 * (?#ALGNAME=ForestryClassRegex)(?#PARA=3,-1){]
	 */

	public static FunctionResult ForestryClassRegex_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String[] splitIndex = index.split(",");
		int length_first = Integer.parseInt(splitIndex[0]);
		// 求表长
		// String regex = "[1-9]*";
		RandomChar r = RandomChar.getRandomChar();
		int length = Result.nSize;
		// 利用nSize来确定{3,-1}的长度为多少

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length - length_first; i++) {
			sb.append(r.generateRandomInt(9) + 1 + "");
		}

		String RandomResults = sb.toString();

		// 增加splitIndex长度，由于-1无限大的原因

		// 得到随机index的code

		splitIndex = new String[RandomResults.length()];
		for (int i = 0; i < RandomResults.length(); i++) {
			splitIndex[i] = length_first + i + "";
		}

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}

	/**
	 * User: wb Date: 2014-8-13 GB/T_18283-2008_4 13
	 * 0,4,0,0,0,0,0,0,0;1,31,0,0,0
	 * ,0,0,0,0;2,255,3,0,0,0,0,0,0;3,255,3,0,0,0,0,0
	 * ,0;4,255,3,0,0,0,0,0,0;5,255
	 * ,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0
	 * ,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;
	 * (?#ALGNAME=Check5BitBarCode)(?#PARA=7,8,9,10,11;6){]
	 * (?#ALGNAME=CheckCodeForCommodityCode
	 * )(?#PARA=0,1,2,3,4,5,6,7,8,9,10,11,12){]
	 */
	
	public static FunctionResult Check5BitBarCode_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result =InPutResult;
		index = index.replace(";", ",");
		String[] splitIndex = index.split(",");
		int length_first = Integer.parseInt(splitIndex[0]);
		// 求表长
		RandomChar r = RandomChar.getRandomChar();
		//获取7,8,9,10,11 位
		StringBuffer sb = new StringBuffer();
		String []require = Arrays.copyOf(splitIndex, splitIndex.length-1);
		sb.append(getRandomCode(Result.FunctionResult,require));
		//依据7,8,9,10,11位生成第6位,放入sb中
		int str_6 = Check5BitBarCode(sb.toString());
		//System.out.println("_____##########生成的第6位是 : " + str_6);
		sb.append(str_6+"");
		
		
		String RandomResults =sb.toString();
		//System.out.println("##########添加第6位验证码后（加上验证码后的RandomResults） : " + RandomResults);
		
		
		
		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}


		return Result;
	}
	
	public static int Check5BitBarCode(String str){
		//2-
		String []ss_1 = {"0","2","4","6","8","9","1","3","5","7"};
		//3
		String []ss_2 = {"0","3","6","9","2","5","8","1","4","7"};
		//5-
		String []ss_3 = {"0","5","9","4","8","3","7","2","6","1"};
		//5+
		String []ss_4 = {"0","5","1","6","2","7","3","8","4","9"};
		//加权因子计算
		char cs [] = str.toCharArray();
		StringBuffer sb = new StringBuffer();
		sb.append(ss_4[Integer.parseInt(cs[0] + "")]);
		sb.append(ss_1[Integer.parseInt(cs[1] + "")]);
		sb.append(ss_3[Integer.parseInt(cs[2] + "")]);
		sb.append(ss_4[Integer.parseInt(cs[3] + "")]);
		sb.append(ss_1[Integer.parseInt(cs[4] + "")]);
		//每位相加
		int verify = 0;
		for(char c :sb.toString().toCharArray()){
			verify += Integer.parseInt(c+"");
		}
		//整数向上取整
	
		//第三步,整数向上取整
		int verify_2 = (int) (Math.ceil((double)verify/10 )*10);
		//System.out.println("######verify_2:" + verify_2);
		return verify_2 - verify;
	}
//	public static void main(String[] args) {
//		String str = "14685";
//		System.out.println(Check5BitBarCode(str));
//	}
//	
	
	
	
	
	/**
	 * #########################################
	 */

	/**
	 * User: wb Date: 2014-8-13 GB/T_21373-2008 6
	 * (?#ALGNAME=IntellectualProperty)(?#PARA=0,1,2,3,4,5){]
	 * intellectualproperty
	 */
	public static FunctionResult IntellectualProperty_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.intellectualproperty_TABLE,
				RecoDaoRandom.hashMapintellectualproperty);

		// 得到随机index的code
		RandomChar r = RandomChar.getRandomChar();
		int RandomIndex = 0;
		RandomIndex = r.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.intellectualproperty_TABLE, RandomIndex,
				RecoDaoRandom.hashMapintellectualproperty);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}

	/**
	 * User: wb Date: 2014-8-13 SY/T_5760-2010 22
	 * 0,255,3,0,0,0,0,0,0;1,255,3,0,0
	 * ,0,0,0,0;3,255,3,0,0,0,0,0,0;4,255,3,0,0,0,
	 * 0,0,0;5,255,3,0,0,0,0,0,0;6,255
	 * ,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,0,0,0,
	 * 0,0,0,66,0;9,255,3,0,0,0,0,0,0;
	 * 10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;13
	 * ,255,3,0,0,0,0,0,0;14,255,3,0,
	 * 0,0,0,0,0;15,255,3,0,0,0,0,0,0;16,255,3,0,0,
	 * 0,0,0,0;17,255,3,0,0,0,0,0,0;18
	 * ,0,0,0,0,0,1,0,4;19,255,3,0,0,0,0,0,0;20,255
	 * ,3,0,0,0,0,0,0;21,2,2,0,0,0,0,0,0; (?#ALGNAME=Dot)(?#PARA=2){]
	 * (?#ALGNAME=Dot)(?#PARA=12){] String regex = "[.]"
	 */
	public static FunctionResult Dot_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String[] splitIndex = index.split(",");
		int length_first = Integer.parseInt(splitIndex[0]);
		// 求表长
		RandomChar r = RandomChar.getRandomChar();
		int length = Result.nSize;
		// 利用nSize来确定{1,7}的长度为多少
		int length_1 = length - 10;
		StringBuffer sb = new StringBuffer();
		sb.append(".");
		String RandomResults = sb.toString();

		// 增加splitIndex长度，由于-1无限大的原因

		// 得到随机index的code

		splitIndex = new String[RandomResults.length()];
		for (int i = 0; i < RandomResults.length(); i++) {
			splitIndex[i] = length_first + i + "";
		}

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}

	/**
	 * User: wb Date: 2014-8-13        
	 * GB/T_23732-2009
	 * 20
	 * 0,0,0,0,0,0,16,0,0;1,0,0,0,0,0
	 * ,0,64,0;2,0,0,0,0,0,0,128,0;3,0,0,0,0,64,0,0
	 * ,0;4,255,255,0,0,240,3,0,0;5,255
	 * ,255,0,0,240,3,0,0;6,255,255,0,0,240,3,0,0
	 * ;7,255,3,0,0,0,0,0,0;8,255,3,0,0
	 * ,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,
	 * 0,0,0,0;11,255,255,0,0,240,3,0,0
	 * ;12,255,255,0,0,240,3,0,0;13,255,255,0,0,240
	 * ,3,0,0;14,255,255,0,0,240,3,0,
	 * 0;15,255,255,0,0,240,3,0,0;16,255,255,0,0,240
	 * ,3,0,0;17,255,255,0,0,240,3,0,0;18,255,255,0,0,240,3,0,0;
	 * (?#ALGNAME=MOD163)(?#PARA=4,5,6,7,8,9,10,11,12,13,14,15,16,17,18;19){]
	 */
	//modified by WB on 0917
	public static FunctionResult MOD163_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitResultIndex = index.split(";");
		String[] splitIndex = splitResultIndex[0].split(",");
		int ResultIndex = Integer.parseInt(splitResultIndex[1]);
		int LenIndex = splitIndex.length;
		int[] IDstr = new int[Result.nSize-1];
		int[] Weight={11,9,3,1,11,9,3,1,11,9,3,1,11,9,3};
		int CheckCode = 0;
		
		// 将字符码0-9转为数字0-9；将字符A-Z映射为数字10-35
		char temp = ' ';
		int KeyIndex=0;
		for (int i = 0; i < LenIndex; i++) {
			KeyIndex=Integer.parseInt(splitIndex[i]);
			temp = Result.FunctionResult.get(KeyIndex).charAt(0);
			if (temp >= 'A' && temp <= 'Z')
				IDstr[KeyIndex] = (int) (temp - 55);
			else
				IDstr[KeyIndex] = (int) (temp - 48);
			
			CheckCode=CheckCode+IDstr[KeyIndex]*Weight[i];

		}
	
		CheckCode = 16 - CheckCode % 16;
		char code=' ';
		if(CheckCode ==16){
			CheckCode =0;
		}
		if (CheckCode >=10){
			code = (char)(CheckCode+55);
		}else{
			code=(char)(CheckCode+48);
		}
		// 保存结果
		Result.FunctionResult.put(ResultIndex, String.valueOf(code));
		return Result;
	
	}



	
	/**
	 * #########################################
	 */

	/**
	 * User: wb Date: 2014-8-13 GB/T_16711-1996 8,11
	 * 0,0,0,0,0,240,255,255,63;1,0
	 * ,0,0,0,240,255,255,63;2,0,0,0,0,240,255,255,63
	 * ;3,0,0,0,0,240,255,255,63;6,
	 * 254,3,0,0,240,255,255,63;7,255,3,0,0,240,255,255,63;
	 * (?#ALGNAME=CountryRegionCode1)(?#PARA=4,5){]已有
	 * (?#ALGNAME=BankCodes)(?#PARA=8,-1){] String regex = "[1-9A-W,Y,Z]{0,3}";
	 */
	public static FunctionResult BankCodes_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String[] splitIndex = index.split(",");
		int length_first = Integer.parseInt(splitIndex[0]);
		// 求表长
		RandomChar r = RandomChar.getRandomChar();
		int length = Result.nSize;
		// 利用nSize来确定{8,11}的长度为多少
		String[] ss = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B",
				"C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
				"O", "P", "Q", "R", "S", "T", "U", "V", "W", "Y", "Z" };
		StringBuffer sb = new StringBuffer();
		//System.out.println("##########length: " + length);
		//System.out.println("##########length_first: " + length_first);
		
		for (int i = 0; i < length - length_first; i++) {
			sb.append(ss[r.generateRandomInt(ss.length)]);
		}

		String RandomResults = sb.toString();

		// 增加splitIndex长度，由于-1无限大的原因

		// 得到随机index的code

		splitIndex = new String[RandomResults.length()];
		for (int i = 0; i < RandomResults.length(); i++) {
			splitIndex[i] = length_first + i + "";
		}

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}

	/**
	 * User: wb Date: 2014-8-13 JT/T_444-2001_2 19
	 * 0,8,0,0,0,0,0,0,0;11,6,0,0,0,0,0,0,0;18,2,0,0,0,0,0,0,0;
	 * (?#ALGNAME=OneTO11and90)(?#PARA=1,2){]
	 * (?#ALGNAME=HighwayTransportation)(?#PARA=3,4,5){]
	 * (?#ALGNAME=HighwayTransportation4b1)(?#PARA=6,7){]
	 * (?#ALGNAME=RoadTransportation5)(?#PARA=8,9,10){]
	 * (?#ALGNAME=HighwayTransportation4b7)(?#PARA=12,13,14){]
	 * (?#ALGNAME=HighwayTransportation4b10)(?#PARA=15){]
	 * (?#ALGNAME=HighwayTransportationB9)(?#PARA=16,17){]
	 */
	

	// HighwayTransportation4b1
	public static FunctionResult HighwayTransportation4b1_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.highwaytransportation4b1_TABLE,
				RecoDaoRandom.hashMaphighwaytransportation4b1);

		// 得到随机index的code
		RandomChar r = RandomChar.getRandomChar();
		int RandomIndex = 0;
		RandomIndex = r.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.highwaytransportation4b1_TABLE, RandomIndex,
				RecoDaoRandom.hashMaphighwaytransportation4b1);
		
		
		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}

	// RoadTransportation5
	// roadtransportation5
	public static FunctionResult RoadTransportation5_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.roadtransportation5_TABLE,
				RecoDaoRandom.hashMaproadtransportation5);

		// 得到随机index的code
		RandomChar r = RandomChar.getRandomChar();
		int RandomIndex = 0;
		RandomIndex = r.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.roadtransportation5_TABLE, RandomIndex,
				RecoDaoRandom.hashMaproadtransportation5);
		
		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}

	// HighwayTransportation4b7
	// highwaytransportation4b7
	public static FunctionResult HighwayTransportation4b7_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.highwaytransportation4b7_TABLE,
				RecoDaoRandom.hashMaphighwaytransportation4b7);

		// 得到随机index的code
		RandomChar r = RandomChar.getRandomChar();
		int RandomIndex = 0;
		RandomIndex = r.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.highwaytransportation4b7_TABLE, RandomIndex,
				RecoDaoRandom.hashMaphighwaytransportation4b7);
		
		
		
		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}

	// HighwayTransportation4b10
	// highwaytransportation4b10
	public static FunctionResult HighwayTransportation4b10_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.highwaytransportation4b10_TABLE,
				RecoDaoRandom.hashMaphighwaytransportation4b10);

		// 得到随机index的code
		RandomChar r = RandomChar.getRandomChar();
		int RandomIndex = 0;
		RandomIndex = r.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.highwaytransportation4b10_TABLE, RandomIndex,
				RecoDaoRandom.hashMaphighwaytransportation4b10);
		
		//System.out.println("############highwaytransportation4b10");
		//System.out.println("highwaytransportation4b10   RandomResults :"  +  RandomResults);
		
		
		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}

	// HighwayTransportationB9
	// 10 11 12 19 20 21 22 29 30

	public static FunctionResult HighwayTransportationB9_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String[] splitIndex = index.split(",");
		// 求表长
		RandomChar r = RandomChar.getRandomChar();
		// 01-11 90
		String[] ss = { "10", "11", "12", "19", "20", "21", "22", "29", "30" };
		StringBuffer sb = new StringBuffer();

		sb.append(ss[r.generateRandomInt(ss.length)]);

		String RandomResults = sb.toString();
		
		//System.out.println("############HighwayTransportationB9");
		//System.out.println("HighwayTransportationB9   RandomResults :"  +  RandomResults);
		
		
		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}

	/**
	 * User: wb Date: 2014-8-13 JT/T_430-2000_10 7
	 * (?#ALGNAME=PortTariff26)(?#PARA=0,1,2){]
	 * (?#ALGNAME=PortTariff3)(?#PARA=3,4,5,6){]
	 */
	public static FunctionResult PortTariff26_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.porttariff26_TABLE, RecoDaoRandom.hashMapporttariff26);

		// 得到随机index的code
		RandomChar r = RandomChar.getRandomChar();
		int RandomIndex = 0;
		RandomIndex = r.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.porttariff26_TABLE, RandomIndex,
				RecoDaoRandom.hashMapporttariff26);

		// 保存结果
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}

	// porttariff3
	public static FunctionResult PortTariff3_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.porttariff3_TABLE, RecoDaoRandom.hashMapporttariff3);

		// 得到随机index的code
		RandomChar r = RandomChar.getRandomChar();
		int RandomIndex = 0;
		RandomIndex = r.generateRandomInt(length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.porttariff3_TABLE, RandomIndex,
				RecoDaoRandom.hashMapporttariff3);

		// 保存结果
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}
	/**
	 * User: wb Date: 2014-8-15
	 * DL/T_700.1-1999_37                 
	 * 8,12,13        
	 * 0,1,0,0,0,0,0,0,0;1,126,0,0,0,0,0,0,0;     
	 * #ALGNAME=TwoByteDecimalnt)(?#PARA=2,3){]已有
	 * (?#ALGNAME=ParamCode20)(?#PARA=4,-1){] 
	 */
	public static FunctionResult ParamCode20_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result =InPutResult;
		
		String[] splitIndex = index.split(",");
		int length_first = Integer.parseInt(splitIndex[0]);
		// 求表长
//		String regex = "[0-9]{?}";
		RandomChar r = RandomChar.getRandomChar();
		int length = Result.nSize;
		//利用nSize来确定{?,-1}的长度为多少
		String []ss = {"0","1", "2", "3", "4", "5", "6", "7", "8", "9"};
		StringBuffer sb = new StringBuffer();
		for(int i = 0;i<length-length_first;i++){
			sb.append(ss[r.generateRandomInt(ss.length)]);
		}
		
		
		String RandomResults =sb.toString();
		
		
		//增加splitIndex长度，由于-1无限大的原因
		
		splitIndex = new String[RandomResults.length()];
		for(int i = 0;i<RandomResults.length();i++ ){
			splitIndex[i] = length_first +i +"";
		}
		
		
		
		
		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}


		return Result;
	}
	
	/**
	 * User: wb Date: 2014-8-15
	 * DL/T_700.1-1999_35                 
	 * 9-11           
	 * 0,6,0,0,0,0,0,0,0;   
	 * #ALGNAME=TwoByteDecimalnt)(?#PARA=1,2){]
	 * (?#ALGNAME=ParamCode22)(?#PARA=3,-1){]  
	 * 
	 */
	//modified by WB on 0917
	public static FunctionResult ParamCode22_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result =InPutResult;
		
		String[] splitIndex = index.split(",");
		int length_first = Integer.parseInt(splitIndex[0]);
		// 求表长
		RandomChar r = RandomChar.getRandomChar();
		int length = Result.nSize;
		//利用nSize来确定{?,-1}的长度为多少
		String []ss = {"0","1", "2", "3", "4", "5", "6", "7", "8", "9"};
		StringBuffer sb = new StringBuffer();
		int length_other = length-length_first;
		//System.out.println("##########length_other" + length_other);
		String []require = {"0"};
		String first_code = RuleRandom.getRandomCode(Result.FunctionResult, require);
		if(first_code.equals("1")){
			//长度为8
			Result.nSize = 8;
			for(int i = 0;i<8;i++){
				sb.append(ss[r.generateRandomInt(ss.length)]);
			}
		}else{
			//长度为6
			Result.nSize = 6;
			for(int i = 0;i<6;i++){
				sb.append(ss[r.generateRandomInt(ss.length)]);
			}
		}
		String RandomResults =sb.toString();
		
		//增加splitIndex长度，由于-1无限大的原因
		
		splitIndex = new String[RandomResults.length()];
		for(int i = 0;i<RandomResults.length();i++ ){
			splitIndex[i] = length_first +i +"";
		}
		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}
		return Result;
	}

	
	/**
	 * User: wb Date: 2014-8-18
	 * GA/T_852.10-2009
	 * 2		
	 * (?#ALGNAME=StatuPractitioner)(?#PARA=0,1){]
	 */
	public static FunctionResult StatuPractitioner_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String[] splitIndex = index.split(",");
		// 求表长
		RandomChar r = RandomChar.getRandomChar();
		String[] ss = { "10", "11", "12", "13","19", "20","90","99" };
		StringBuffer sb = new StringBuffer();

		sb.append(ss[r.generateRandomInt(ss.length)]);

		String RandomResults = sb.toString();
		
		//System.out.println("############StatuPractitioner");
		//System.out.println("StatuPractitioner   RandomResults :"  +  RandomResults);
		
		
		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}
	/**
	 * User: wb Date: 2014-8-18
	 * GA/T_615.3-2006	
	 * 4		
	 * (?#ALGNAME=Borderinfo3)(?#PARA=0,1,2,3){]
	 */
	/*public static FunctionResult Borderinfo3_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String[] splitIndex = index.split(",");
		// 求表长
		RandomChar r = RandomChar.getRandomChar();
		String[] ss = { "100", "200", "300", "400", "401", "402", "403", "404", "500", "600", "2600",
				"2700", "7900", "8000", "9000"};
		StringBuffer sb = new StringBuffer();

		sb.append(ss[r.generateRandomInt(ss.length)]);

		String RandomResults = sb.toString();
		
		System.out.println("############Borderinfo3");
		System.out.println("Borderinfo3   RandomResults :"  +  RandomResults);
		
		
		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}*/
	//modified by wangbin on 0901
	public static FunctionResult Borderinfo3_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String[] splitIndex = index.split(",");
		// 求表长
		RandomChar r = RandomChar.getRandomChar();
		String[] ss = { "0100", "0200", "0300", "0400", "0401", "0402", "0403", "0404", "0500", "0600", "2600",
				"2700", "7900", "8000", "9000"};
		StringBuffer sb = new StringBuffer();

		sb.append(ss[r.generateRandomInt(ss.length)]);

		String RandomResults = sb.toString();
		
		
		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}

	
	/**
	 * User: wb Date: 2014-8-18
	 * GA/T_974.57-2011	
	 * 2		
	 * (?#ALGNAME=FireInfowatersupply)(?#PARA=0,1){]
	 */
	public static FunctionResult FireInfowatersupply_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String[] splitIndex = index.split(",");
		// 求表长
		RandomChar r = RandomChar.getRandomChar();
		String[] ss = { "20","21","22","30","31","32","33","40","10","90"};
		StringBuffer sb = new StringBuffer();

		sb.append(ss[r.generateRandomInt(ss.length)]);

		String RandomResults = sb.toString();
		
		//System.out.println("############FireInfowatersupply");
		//System.out.println("FireInfowatersupply   RandomResults :"  +  RandomResults);
		
		
		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}
	/**
	 * User: wb Date: 2014-8-18
	 * GB/T_27923-2011	
	 * 4		
	 * (?#ALGNAME=Logisticsoperation)(?#PARA=0,1,2,3){]
	 */
	public static FunctionResult Logisticsoperation_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.Logisticsoperation_TABLE,
				RecoDaoRandom.hashMapLogisticsoperation);

		// 得到随机index的code
		RandomChar r = RandomChar.getRandomChar();
		int RandomIndex = 0;
		RandomIndex = r.generateRandomInt (length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.Logisticsoperation_TABLE, RandomIndex,
				RecoDaoRandom.hashMapLogisticsoperation);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}


		return Result;
	}
	/**
	 * User: wb Date: 2014-8-18
	 * GA/T_974.54-2011	
	 * 2		
	 * (?#ALGNAME=FireInfofailities)(?#PARA=0,1){]
	 */
	public static FunctionResult FireInfofailities_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String[] splitIndex = index.split(",");
		// 求表长
		RandomChar r = RandomChar.getRandomChar();
		String[] ss = { "20","21","22","24","10","29"};
		StringBuffer sb = new StringBuffer();

		sb.append(ss[r.generateRandomInt(ss.length)]);

		String RandomResults = sb.toString();
		
		//System.out.println("############FireInfofailities");
		//System.out.println("FireInfofailities   RandomResults :"  +  RandomResults);
		
		
		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}
	/**
	 * User: wb Date: 2014-8-18
	 * JT/T_430-2000_2	
	 * 4	
	 * 2,126,2,0,0,0,0,0,0;3,14,2,0,0,0,0,0,0;	
	 * (?#ALGNAME=Porttariff4)(?#PARA=0,1){]
	 * 
	 */
	public static FunctionResult Porttariff4_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String[] splitIndex = index.split(",");
		// 求表长
		RandomChar r = RandomChar.getRandomChar();
		String[] ss = { "10","20","30","60","61","62","69"};
		StringBuffer sb = new StringBuffer();

		sb.append(ss[r.generateRandomInt(ss.length)]);

		String RandomResults = sb.toString();
		
		//System.out.println("############Porttariff4");
		//System.out.println("Porttariff4   RandomResults :"  +  RandomResults);
		
		
		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}
	/**
	 * User: wb Date: 2014-8-18
	 * GB/T_28923.1-2012_1	
	 * 4	
	 * 0,1,0,0,0,0,0,0,0;1,22,0,0,0,0,0,0,0;	
	 * (?#ALGNAME=TwobytleCode06)(?#PARA=2,3){] 判断2个字节是不是属于(01-06,99)
	 */
	public static FunctionResult TwobytleCode06_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String[] splitIndex = index.split(",");
		// 求表长
		RandomChar r = RandomChar.getRandomChar();
		String[] ss = { "01","02","03","04","05","06","99"};
		StringBuffer sb = new StringBuffer();

		sb.append(ss[r.generateRandomInt(ss.length)]);

		String RandomResults = sb.toString();
		
		//System.out.println("############TwobytleCode06");
		//System.out.println("TwobytleCode06   RandomResults :"  +  RandomResults);
		
		
		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}
	/**
	 * User: wb Date: 2014-8-18
	 * GA_408.1-2006	
	 * 4		
	 * (?#ALGNAME=TraViolativeActionCode)(?#PARA=0,1,2,3){]
	 */
	public static FunctionResult TraViolativeActionCode_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String[] splitIndex = index.split(",");
		// 求表长
		RandomChar r = RandomChar.getRandomChar();
		//可能性多了点用list
		List<Integer>ls = new ArrayList<Integer>();
		for(int i =1101;i<=5067;i++){
			if (i >= 1001 && i <= 1073)
				ls.add(i);
			else if (i >= 1101 && i <= 1110)
				ls.add(i);
			else if (i >= 1201 && i <= 1239)
				ls.add(i);
			else if (i >= 1301 && i <= 1339)
				ls.add(i);
			else if (i >= 1601 && i <= 1609)
				ls.add(i);
			else if (i >= 1701 && i <= 1709)
				ls.add(i);
			else if (i >= 2001 && i <= 2055)
				ls.add(i);
			else if (i >= 3001 && i <= 3030)
				ls.add(i);
			else if (i >= 4001 && i <= 4009)
				ls.add(i);
			else if (i >= 4201 && i <= 4203)
				ls.add(i);
			else if (i >= 4301 && i <= 4310)
				ls.add(i);
			else if (i >= 4601 && i <= 4606)
				ls.add(i);
			else if (i >= 5001 && i <= 5067)
				ls.add(i);
		}
		Integer []ints = ls.toArray(new Integer[ls.size()]);
//		for(int i:ints){
//			System.out.println(i);
//		}
		
		
		String RandomResults = ints[r.generateRandomInt(ints.length)] + "";
		//System.out.println("！！！！！！！！取得结果是：" + RandomResults);
		//System.out.println("############TraViolativeActionCode");
		//System.out.println("TraViolativeActionCode   RandomResults :"  +  RandomResults);
		
		
		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}
	/**
	 * User: wb Date: 2014-8-18 
	 * GA/T_556.1-2005	
	 * 4		
	 * (?#ALGNAME=JobClassificationCode)(?#PARA=0,1,2,3){]
	 */
	//modified by WB on 0915
	public static FunctionResult JobClassificationCode_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String[] splitIndex = index.split(",");
		//所有的可能
		String []s_all = new String[13];
		// 求表长
		RandomChar r = RandomChar.getRandomChar();
		int i = 0;
		//第一种可能
		String []s_1 = {"FF02","FF03","FF09"};
		s_all[i++] = s_1[r.generateRandomInt(s_1.length)];
		//第 二种可能
		String []s_2 = {"FZ01"};
		s_all[i++] = s_2[r.generateRandomInt(s_2.length)];
		//第三种可能
		String []s_3 = {"ZF02","ZF03"};
		s_all[i++] = s_3[r.generateRandomInt(s_3.length)];
		//第四种可能
		String []s_4 = {"CF02","CF03"};
		s_all[i++] = s_4[r.generateRandomInt(s_4.length)];
		//第五种可能
		String []s_5 = {"DF01","DF02"};
		s_all[i++] = s_5[r.generateRandomInt(s_5.length)];
		//第六种可能
		String []s_6 = {"XF01","XF02"};
		s_all[i++] = s_6[r.generateRandomInt(s_6.length)];
		//第七种可能
		String []s_7 = {"DY01","DY02","DY03","DY04","DY05","DY06","DY07","DY08","DY09"};
		s_all[i++] = s_7[r.generateRandomInt(s_7.length)];
		//第八种可能
		String []s_8 = {"XY01","XY02","XY03","XY04","XY05","XY06","XY07","XY08","XY09"};
		s_all[i++] = s_8[r.generateRandomInt(s_8.length)];
		//第九种可能
		String []s_9 = {"ZZ01","ZZ02","ZZ03","ZZ04","ZZ05","ZZ06","ZZ07","ZZ08","ZZ09"};
		s_all[i++] = s_9[r.generateRandomInt(s_9.length)];
		//第十种可能
		String []s_10 = {"SX03","SX04","SX05","SX06","SX07","SX08"};
		s_all[i++] = s_10[r.generateRandomInt(s_10.length)];
		//第十一种可能
		String []s_11 = {"SF01","SF02","SF03","SF04","SF05","SF06","SF07","SF08","SF09","SF11","SF12","SF19"};
		s_all[i++] = s_11[r.generateRandomInt(s_11.length)];
		//第十二种可能
		String []s_12 = {"DZ01","DZ02","DZ03","DZ04","DZ05","DZ06","DZ07","DZ08","DZ09","DZ11","DZ12","DZ19","DZ14"};
		s_all[i++] = s_12[r.generateRandomInt(s_12.length)];
		//第十三种可能
		String []s_13 = {"XZ01","XZ02","XZ03","XZ04","XZ05","XZ06","XZ07","XZ08","XZ09","XZ10"};
		s_all[i++] = s_13[r.generateRandomInt(s_13.length)];
		
		
		String RandomResults = s_all[r.generateRandomInt(s_all.length)];
		
		//System.out.println("############JobClassificationCode");
		//System.out.println("JobClassificationCode   RandomResults :"  +  RandomResults);
		
		
		// 保存结果
		for (int j = 0; j < RandomResults.length(); j++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[j]), String
					.valueOf(RandomResults.charAt(j)));
		}

		return Result;
	}


	/**
	 * User: wb Date: 2014-8-18 
	 * WS_364.5-2011_10	
	 * 1-2		
	 * (?#ALGNAME=PhysicalActivityFrequency)(?#PARA=0,-1){]
	 */
	public static FunctionResult PhysicalActivityFrequency_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.physicalactivityfrequency_TABLE,
				RecoDaoRandom.hashMapphysicalactivityfrequency);
		/*
		 * 长度后边位-1无限大的情况！！
		 */
		// 得到随机index的code
		RandomChar r = RandomChar.getRandomChar();
		int RandomIndex = 0;
		RandomIndex = r.generateRandomInt (length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.physicalactivityfrequency_TABLE, RandomIndex,
				RecoDaoRandom.hashMapphysicalactivityfrequency);
		
		
		//增加spitIndex长度
		int length_first = Integer.parseInt(splitIndex[0]);

		splitIndex = new String[RandomResults.length()];
		for(int i = 0;i<RandomResults.length();i++ ){
			splitIndex[i] = length_first +i +"";
		}

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}


		return Result;

	}
	/**
	 * User: wb Date: 2014-8-18 
	 * GB/T_21285-2007_2	
	 * 3,4	
	 * 0,0,0,0,0,240,159,0,32;1,255,3,0,0,0,0,0,0;2,255,3,0,0,0,0,0,0;	
	 * (?#ALGNAME=TubesValves1)(?#PARA=3,-1){]
	 * String regex = "[C]{0,}";
	 */
	public static FunctionResult TubesValves1_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result =InPutResult;
		
		String[] splitIndex = index.split(",");
		int length_first = Integer.parseInt(splitIndex[0]);
		// 求表长
//		String regex = "[C]{0,}";
		RandomChar r = RandomChar.getRandomChar();
		int length = Result.nSize;
		//System.out.println("$$$$$$$$$$$$$$ Result.nSize " +  Result.nSize);
		//利用nSize来确定{3,-1}的长度为多少
		StringBuffer sb = new StringBuffer();
		for(int i = 0;i <length - length_first;i++){
			sb.append("C");
		}
		
		String RandomResults =sb.toString();
		
		//增加splitIndex长度，由于-1无限大的原因

		// 得到随机index的code
		
		splitIndex = new String[RandomResults.length()];
		for(int i = 0;i<RandomResults.length();i++ ){
			splitIndex[i] = length_first +i +"";
		}
		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}


		return Result;
	}
	/**
	 * User: wb Date: 2014-8-18  
	 * WS_364.15-2011_1	
	 * 1-2		
	 * (?#ALGNAME=HygieneAgencyPersonnel)(?#PARA=0,-1){]
	 */
	public static FunctionResult HygieneAgencyPersonnel_Random(FunctionResult InPutResult,
			String index) {
		
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = Result.nSize;
		//System.out.println("####Result.nSize" + Result.nSize);
		/*
		 * 长度后边位-1无限大的情况！！
		 */
		// 得到随机index的code
		RandomChar r = RandomChar.getRandomChar();
		int RandomIndex = 0;
		RandomIndex = r.generateRandomInt (length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.hygieneagencypersonnel_TABLE, RandomIndex,
				RecoDaoRandom.hashMaphygieneagencypersonnel);
		
		//System.out.println("####RandomResults" + RandomResults);
		//增加spitIndex长度
		int length_first = Integer.parseInt(splitIndex[0]);

		splitIndex = new String[length - length_first];
		for(int i = 0;i<length - length_first;i++ ){
			splitIndex[i] = length_first +i +"";
		}

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}


		return Result;
		
	}
	/**
	 * User: wb Date: 2014-8-18   
	 * GB/T_21285-2007_1	
	 * 4,5	
	 * 0,0,0,0,0,240,239,1,32;1,0,0,0,0,240,239,123,32;2,255,3,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;	
	 * (?#ALGNAME=TubesValves)(?#PARA=4,-1){] 已有跟上边的TubesValves一样
	 */
	
	/**
	 * User: wb Date: 2014-8-18   
	 * YC/T_209.1-2006	
	 * 4		
	 * (?#ALGNAME=TabaccoMaterials)(?#PARA=0,1,2,3){]
	 */
	public static FunctionResult TabaccoMaterials_Random(FunctionResult InPutResult,
			String index) {
		
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.tobaccomaterials_TABLE,
				RecoDaoRandom.hashMaptobaccomaterials);
		
		// 得到随机index的code
		RandomChar r = RandomChar.getRandomChar();
		int RandomIndex = 0;
		RandomIndex = r.generateRandomInt (length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.tobaccomaterials_TABLE, RandomIndex,
				RecoDaoRandom.hashMaptobaccomaterials);
		
	
		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}


		return Result;
		
	}
	/**
	 * User: wb Date: 2014-8-18   
	 * GA_557.5-2005	
	 * 4		
	 * (?#ALGNAME=InternetWebService)(?#PARA=0,1,2,3){]
	 */
	public static FunctionResult InternetWebService_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.InternetWebService_TABLE,
				RecoDaoRandom.hashMapInternetWebService);
		// 得到随机index的code
		RandomChar r = RandomChar.getRandomChar();
		int RandomIndex = 0;
		RandomIndex = r.generateRandomInt (length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.InternetWebService_TABLE, RandomIndex,
				RecoDaoRandom.hashMapInternetWebService);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}


		return Result;
	}
	/**
	 * User: wb Date: 2014-8-18   
	 * JT/T_415-2000_15	
	 * 2		
	 * (?#ALGNAME=RoadTransportation64)(?#PARA=0,1){]
	 * roadtransportation64
	 */
	public static FunctionResult RoadTransportation64_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.roadtransportation64_TABLE,
				RecoDaoRandom.hashMaproadtransportation64);
		// 得到随机index的code
		RandomChar r = RandomChar.getRandomChar();
		int RandomIndex = 0;
		RandomIndex = r.generateRandomInt (length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.roadtransportation64_TABLE, RandomIndex,
				RecoDaoRandom.hashMaproadtransportation64);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}


		return Result;
	}
	/**
	 * User: wb Date: 2014-8-18   
	 * JT/T_415-2000_16	
	 * 2		
	 * (?#ALGNAME=RoadTransportation63)(?#PARA=0,1){]
	 */
	public static FunctionResult RoadTransportation63_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.roadtransportation63_TABLE,
				RecoDaoRandom.hashMaproadtransportation63);
		// 得到随机index的code
		RandomChar r = RandomChar.getRandomChar();
		int RandomIndex = 0;
		RandomIndex = r.generateRandomInt (length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.roadtransportation63_TABLE, RandomIndex,
				RecoDaoRandom.hashMaproadtransportation63);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}


		return Result;
	}
	/**
	 * User: wb Date: 2014-8-18 
	 * GA/T_615.5-2006	
	 * 3		
	 * (?#ALGNAME=Borderinfo5)(?#PARA=0,1,2){]
	 */
	public static FunctionResult Borderinfo5_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result =InPutResult;
		
		String[] splitIndex = index.split(",");
		// 求表长
		RandomChar r = RandomChar.getRandomChar();
		int ints[] = { 100, 101, 102, 199, 200, 210, 211, 212, 213, 219, 220,
				221, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 249,
				250, 251, 252, 253, 259, 299, 300, 301, 302, 303, 309 };
		StringBuffer sb = new StringBuffer();
		
		sb.append(ints[r.generateRandomInt (ints.length)] + "");
		
		String RandomResults =sb.toString();
		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}


		return Result;
	}
	/**
	 * User: wb Date: 2014-8-18 
	 * WS_364.5-2011_5	
	 * 1-2	
	 * (?#ALGNAME=DrinkingClass)(?#PARA=0,-1){]
	 * drinkingclass drinkingclass_TABLE hashMapdrinkingclass
	 */
	public static FunctionResult DrinkingClass_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = Result.nSize;
		/*
		 * 长度后边位-1无限大的情况！！
		 */
		// 得到随机index的code
		RandomChar r = RandomChar.getRandomChar();
		int RandomIndex = 0;
		RandomIndex = r.generateRandomInt (length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.drinkingclass_TABLE, RandomIndex,
				RecoDaoRandom.hashMapdrinkingclass);
		
		
		//增加spitIndex长度
		int length_first = Integer.parseInt(splitIndex[0]);

		splitIndex = new String[length - length_first];
		for(int i = 0;i<length - length_first;i++ ){
			splitIndex[i] = length_first +i +"";
		}
		//System.out.println("###############RandomResults : " + RandomResults);
		//System.out.println("###############splitIndex.length : " + splitIndex.length);
		//for(String s:splitIndex){
			//System.out.println(s);
		//}
		//System.out.println("splitIndex展示完毕");
		// 保存结果
		int min = Math.min(RandomResults.length(), splitIndex.length);
		for (int i = 0; i < min; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					RandomResults.charAt(i)+"");
		}


		return Result;

	}
	/**
	 * User: wb Date: 2014-8-18 
	 * WS_364.10-2011_7	
	 * 2		
	 * (?#ALGNAME=OneTO22)(?#PARA=0,1){]
	 * 01-22 99
	 */
	public static FunctionResult OneTO22_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result =InPutResult;
		
		String[] splitIndex = index.split(",");
		// 求表长
		RandomChar r = RandomChar.getRandomChar();
		
		String []ss = {"01","02","03","04","05","06","07","08","09","99"
				,"11","11","12","13","14","15","16","17","18","19","20","21","22"};
		
		StringBuffer sb = new StringBuffer();
		
		sb.append(ss[r.generateRandomInt (ss.length)]);
		
		String RandomResults =sb.toString();
		
		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}


		return Result;
	}
	/**
	 * User: wb Date: 2014-8-18 
	 * WS_364.5-2011_4	
	 * 1-2
	 * (?#ALGNAME=DrinkingFrequency)(?#PARA=0,-1){]
	 * drinkingfrequency
	 */
	public static FunctionResult DrinkingFrequency_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.drinkingfrequency_TABLE,
				RecoDaoRandom.hashMapdrinkingfrequency);
		/*
		 * 长度后边位-1无限大的情况！！
		 */
		// 得到随机index的code
		RandomChar r = RandomChar.getRandomChar();
		int RandomIndex = 0;
		RandomIndex = r.generateRandomInt (length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.drinkingfrequency_TABLE, RandomIndex,
				RecoDaoRandom.hashMapdrinkingfrequency);
		
		
		//增加spitIndex长度
		int length_first = Integer.parseInt(splitIndex[0]);

		splitIndex = new String[length - length_first];
		for(int i = 0;i<length - length_first;i++ ){
			splitIndex[i] = length_first +i +"";
		}

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}


		return Result;

	}
	/**
	 * User: wb Date: 2014-8-18 
	 * GA/T_556.3-2005	
	 * 2		
	 * (?#ALGNAME=FinanceSecManageInfo)(?#PARA=0,1){]
	 */
	public static FunctionResult FinanceSecManageInfo_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result =InPutResult;
		
		String[] splitIndex = index.split(",");
		// 求表长
		RandomChar r = RandomChar.getRandomChar();
		
		String []ss = {"01","02","03","04","05","06","07","08","09","19"};
		
		StringBuffer sb = new StringBuffer();
		
		sb.append(ss[r.generateRandomInt (ss.length)]);
		
		String RandomResults =sb.toString();
		
		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}


		return Result;
	}
	/**
	 * User: wb Date: 2014-8-18 
	 * LS/T_1707.2-2004	
	 * 3		
	 * (?#ALGNAME=GainsConditionDetection)(?#PARA=0,1,2){]
	 * grainconditiondetection
	 */
	public static FunctionResult GainsConditionDetection_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.grainconditiondetection_TABLE,
				RecoDaoRandom.hashMapgrainconditiondetection);

		// 得到随机index的code
		RandomChar r = RandomChar.getRandomChar();
		int RandomIndex = 0;
		RandomIndex = r.generateRandomInt (length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.grainconditiondetection_TABLE, RandomIndex,
				RecoDaoRandom.hashMapgrainconditiondetection);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}


		return Result;
	}
	/**
	 * User: wb Date: 2014-8-18 
	 *GB/T_20133-2006	
	 *4		
	 *(?#ALGNAME=TrafficInformationCollection)(?#PARA=0,1,2,3){]
	 */
	public static FunctionResult TrafficInformationCollection_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.trafficinformationcollection_TABLE,
				RecoDaoRandom.hashMaptrafficinformationcollection);

		// 得到随机index的code
		RandomChar r = RandomChar.getRandomChar();
		int RandomIndex = 0;
		RandomIndex = r.generateRandomInt (length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.trafficinformationcollection_TABLE, RandomIndex,
				RecoDaoRandom.hashMaptrafficinformationcollection);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}


		return Result;
	}
	/**
	 * User: wb Date: 2014-8-18 
	 * DL/T_495-2012	
	 * 2		
	 * (?#ALGNAME=ElectricPowerIndustry)(?#PARA=0,1){]
	 */
	//modified by WB on 0917
	public static FunctionResult ElectricPowerIndustry_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.electricpowerindustry_TABLE,
				RecoDaoRandom.hashMapelectricpowerindustry);

		// 得到随机index的code
		RandomChar r = RandomChar.getRandomChar();
		int RandomIndex = 0;
		RandomIndex = r.generateRandomInt (length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.electricpowerindustry_TABLE, RandomIndex,
				RecoDaoRandom.hashMapelectricpowerindustry);
		//System.out.println("############RandomResults " + RandomResults);
		// 保存结果
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}


		return Result;
	}

	/**
	 * User: wb Date: 2014-8-18 
	 * GA/T_974.62-2011	
	 * 2		
	 * (?#ALGNAME=FireInfoSmoke)(?#PARA=0,1){]
	 */
	public static FunctionResult FireInfoSmoke_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result =InPutResult;
		
		String[] splitIndex = index.split(",");
		// 求表长
		RandomChar r = RandomChar.getRandomChar();
		
		String []ss = {"20","21","22","90","10","11"};
		
		StringBuffer sb = new StringBuffer();
		
		sb.append(ss[r.generateRandomInt (ss.length)]);
		
		String RandomResults =sb.toString();
		
		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}


		return Result;
	}
	/**
	 * User: wb Date: 2014-8-18 
	 * DL/T_700.2-1999	
	 * 4	
	 * 0,4,0,0,0,0,0,0,0;	
	 * (?#ALGNAME=PowerGoodsP2)(?#PARA=1,2,3){]
	 * powergoodsp2
	 */
	public static FunctionResult PowerGoodsP2_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.powergoodsp2_TABLE,
				RecoDaoRandom.hashMappowergoodsp2);

		// 得到随机index的code
		RandomChar r = RandomChar.getRandomChar();
		int RandomIndex = 0;
		RandomIndex = r.generateRandomInt (length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.powergoodsp2_TABLE, RandomIndex,
				RecoDaoRandom.hashMappowergoodsp2);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}


		return Result;
	}
	/**
	 * User: wb Date: 2014-8-18 
	 * GA/T_517-2004	
	 * 3		
	 * (?#ALGNAME=TravleDocumentCode)(?#PARA=0,1,2){]
	 */
	public static FunctionResult TravleDocumentCode_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.TravleDocumentCode_TABLE,
				RecoDaoRandom.hashMapTravleDocumentCode);

		// 得到随机index的code
		RandomChar r = RandomChar.getRandomChar();
		int RandomIndex = 0;
		RandomIndex = r.generateRandomInt (length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.TravleDocumentCode_TABLE, RandomIndex,
				RecoDaoRandom.hashMapTravleDocumentCode);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}


		return Result;
	}
	/**
	 * User: wb Date: 2014-8-18 
	 * GA/T_852.5-2009	
	 * 2		
	 * (?#ALGNAME=RexreationMatetial)(?#PARA=0,1){]	
	 */
	public static FunctionResult RexreationMatetial_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result =InPutResult;
		
		String[] splitIndex = index.split(",");
		// 求表长
		RandomChar r = RandomChar.getRandomChar();
		
		String []ss = {"10","11","19","20","21","22","23","24","29","30","31","32","33","90","91"
				,"92","93","39","99"};
		
		StringBuffer sb = new StringBuffer();
		
		sb.append(ss[r.generateRandomInt (ss.length)]);
		
		String RandomResults =sb.toString();
		
		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}


		return Result;
	}
	/**
	 * User: wb Date: 2014-8-18 
	 * JT/T_415-2000_19	
	 * 4		
	 * (?#ALGNAME=RoadTransportation60)(?#PARA=0,1,2,3){]
	 * roadtransportation60
	 */
	public static FunctionResult RoadTransportation60_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.roadtransportation60_TABLE,
				RecoDaoRandom.hashMaproadtransportation60);

		// 得到随机index的code
		RandomChar r = RandomChar.getRandomChar();
		int RandomIndex = 0;
		RandomIndex = r.generateRandomInt (length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.roadtransportation60_TABLE, RandomIndex,
				RecoDaoRandom.hashMaproadtransportation60);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}
		return Result;
	}
	/**
	 * User: wb Date: 2014-8-18 
	 * GA/T_974.60-2011	
	 * 4		
	 * (?#ALGNAME=FireInfomation)(?#PARA=0,1,2,3){]
	 * FireInfomation
	 */
	public static FunctionResult FireInfomation_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.FireInfomation_TABLE,
				RecoDaoRandom.hashMapFireInfomation);

		// 得到随机index的code
		RandomChar r = RandomChar.getRandomChar();
		int RandomIndex = 0;
		RandomIndex = r.generateRandomInt (length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.FireInfomation_TABLE, RandomIndex,
				RecoDaoRandom.hashMapFireInfomation);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}
		return Result;
	}
	/**
	 * User: wb Date: 2014-8-18 
	 * YC/T_210.5-2006	
	 * 2		
	 * (?#ALGNAME=TobaccoLeafColor)(?#PARA=0,1){]
	 */
	public static FunctionResult TobaccoLeafColor_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.tobaccoleafcolor_TABLE,
				RecoDaoRandom.hashMaptobaccoleafcolor);

		// 得到随机index的code
		RandomChar r = RandomChar.getRandomChar();
		int RandomIndex = 0;
		RandomIndex = r.generateRandomInt (length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.tobaccoleafcolor_TABLE, RandomIndex,
				RecoDaoRandom.hashMaptobaccoleafcolor);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}
		return Result;
	}
	/**
	 * User: wb Date: 2014-8-18 
	 * GB/T_14156-2009	
	 * 4,5		
	 * (?#ALGNAME=FlavorSubstance)(?#PARA=0,1,2,3,4){]
	 */
	//modified by WB on 0917
	public static FunctionResult FlavorSubstance_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result =InPutResult;
		
		String[] splitIndex = index.split(",");
		int length_first = Integer.parseInt(splitIndex[0]);
		String []s_all = new String [3];
		int j = 0;
		// 求表长
		RandomChar r = RandomChar.getRandomChar();
		int length = Result.nSize;
		//利用nSize来确定的长度为多少
		
		//第一种情况> 0 &&  < 378
		String s1 = "N" + StringUtils.leftPad(r.generateRandomInt(377) + 1+"", 3, "0");
		s_all[j++] = s1;
		//第二种情况 > 1000 &&  < 2087
		String s2 = "I" + (r.generateRandomInt(1086) + 1001);
		s_all[j++] = s2;
		//第三种情况> 3000 &&  < 3212
		String s3 = "A" + (r.generateRandomInt(211) + 3001);
		s_all[j++] = s3;
		String RandomResults= "";
		if(length==4){
			RandomResults= s_all[0];
		}else{
			RandomResults= s_all[r.generateRandomInt(2) + 1];
		}
		;
		//增加splitIndex长度，由于-1无限大的原因
		
		splitIndex = new String[RandomResults.length()];
		for(int i = 0;i<RandomResults.length();i++ ){
			splitIndex[i] =  i +"";
		}
		
		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}


		return Result;
	}


	/**
	 * User: wb Date: 2014-8-18 
	 * GA/T_974.58-2011	
	 * 4		
	 * (?#ALGNAME=FireInfowatersource)(?#PARA=0,1,2,3){]
	 */
	public static FunctionResult FireInfowatersource_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String[] splitIndex = index.split(",");
		// 求表长
		RandomChar r = RandomChar.getRandomChar();
		// 可能
		String[] ss = { "1110", "1111", "1112", "1120", "1121", "1122", "1000", "1100", "1200"
				,"1300","1900","2000","2100","2900","9000"};
		StringBuffer sb = new StringBuffer();

		sb.append(ss[r.generateRandomInt(ss.length)]);

		String RandomResults = sb.toString();
		
		//System.out.println("############FireInfowatersource");
		//System.out.println("FireInfowatersource   RandomResults :"  +  RandomResults);
		
		
		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}
	/**
	 * User: wb Date: 2014-8-18 
	 * LS/T_1702-2004	
	 * 3		
	 * (?#ALGNAME=GrainsAttribute)(?#PARA=0,1,2){]
	 */
	
	public static FunctionResult GrainsAttribute_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.grainsattribute_TABLE,
				RecoDaoRandom.hashMapgrainsattribute);

		// 得到随机index的code
		RandomChar r = RandomChar.getRandomChar();
		int RandomIndex = 0;
		RandomIndex = r.generateRandomInt (length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.grainsattribute_TABLE, RandomIndex,
				RecoDaoRandom.hashMapgrainsattribute);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}


		return Result;
	}
	/**
	 * User: wb Date: 2014-8-18 
	 * GB/T_16158-1996	
	 * 4		
	 * (?#ALGNAME=NavigationShip)(?#PARA=0,1,2,3){]
	 */
	public static FunctionResult NavigationShip_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.NavigationShip_TABLE,
				RecoDaoRandom.hashMapNavigationShip);

		// 得到随机index的code
		RandomChar r = RandomChar.getRandomChar();
		int RandomIndex = 0;
		RandomIndex = r.generateRandomInt (length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.NavigationShip_TABLE, RandomIndex,
				RecoDaoRandom.hashMapNavigationShip);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}


		return Result;
	}
	/**
	 * User: wb Date: 2014-8-18 
	 * GB/T_4657-2008	
	 * 3		
	 * (?#ALGNAME=TheCenteralPartyCommitte)(?#PARA=0,1,2){]
	 * TheCenteralPartyCommitte
	 */
	public static FunctionResult TheCenteralPartyCommitte_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.TheCenteralPartyCommitte_TABLE,
				RecoDaoRandom.hashMapTheCenteralPartyCommitte);

		// 得到随机index的code
		RandomChar r = RandomChar.getRandomChar();
		int RandomIndex = 0;
		RandomIndex = r.generateRandomInt (length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.TheCenteralPartyCommitte_TABLE, RandomIndex,
				RecoDaoRandom.hashMapTheCenteralPartyCommitte);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}


		return Result;
	}
	/**
	 * User: wb Date: 2014-8-18 
	 * JT/T_132-2003_67	
	 * 2		
	 * (?#ALGNAME=HighwayDatabase16)(?#PARA=0,1){]
	 */
	public static FunctionResult HighwayDatabase16_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String[] splitIndex = index.split(",");
		// 求表长
		RandomChar r = RandomChar.getRandomChar();
		// 可能
		String[] ss = { "10", "11", "12", "13", "20", "21", "22", "30", "31",
				"32", "40", "41", "42", "43", "44", "45" };
		StringBuffer sb = new StringBuffer();

		sb.append(ss[r.generateRandomInt(ss.length)]);

		String RandomResults = sb.toString();
		
		//System.out.println("############HighwayDatabase16");
		//System.out.println("HighwayDatabase16   RandomResults :"  +  RandomResults);
		
		
		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}
	
	/**
	 * User: wb Date: 2014-8-18 
	 * GA_398.17-2002	
	 * 4	
	 * 0,55,0,0,0,0,0,0,0;	
	 * (?#ALGNAME=EconomicCasesUnit)(?#PARA=1,2,3){]
	 * 000-999
	 */
	public static FunctionResult EconomicCasesUnit_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String[] splitIndex = index.split(",");
		// 求表长
		RandomChar r = RandomChar.getRandomChar();
		// 可能
		int ints_3 = r.generateRandomInt(1000);
		String RandomResults = StringUtils.leftPad(ints_3+"", 3, "0");
		
		//System.out.println("############EconomicCasesUnit");
		//System.out.println("EconomicCasesUnit   RandomResults :"  +  RandomResults);
		
		
		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}
	/**
	 * User: wb Date: 2014-8-18 
	 * JT/T_430-2000_5	
	 * 3		
	 * (?#ALGNAME=Porttariff10)(?#PARA=0,1,2){]
	 * porttariff10
	 */
	public static FunctionResult Porttariff10_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.porttariff10_TABLE,
				RecoDaoRandom.hashMapporttariff10);

		// 得到随机index的code
		RandomChar r = RandomChar.getRandomChar();
		int RandomIndex = 0;
		RandomIndex = r.generateRandomInt (length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.porttariff10_TABLE, RandomIndex,
				RecoDaoRandom.hashMapporttariff10);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}


		return Result;
	}

	/**
	 * User: wb Date: 2014-8-18 
	 * WS_364.5-2011_6	
	 * 2		
	 * (?#ALGNAME=OneToEleven)(?#PARA=0,1){]
	 */
	public static FunctionResult OneToEleven_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String[] splitIndex = index.split(",");
		// 求表长
		RandomChar r = RandomChar.getRandomChar();
		// 可能
		String[] ss = {"01","02","03","04","05","06","07","08","09","10","11"};
		StringBuffer sb = new StringBuffer();

		sb.append(ss[r.generateRandomInt(ss.length)]);

		String RandomResults = sb.toString();
		
		//System.out.println("############OneToEleven");
		//System.out.println("OneToEleven   RandomResults :"  +  RandomResults);
		
		
		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}

	/**
	 * User: wb Date: 2014-8-18 
	 * GA_658.10-2006	
	 * 4	
	 * 0,7,2,0,0,0,0,0,0;	
	 * (?#ALGNAME=ServiceContentCode)(?#PARA=0,1,2,3){]
	 */
	public static FunctionResult ServiceContentCode_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String[] splitIndex = index.split(",");
		// 求表长
		RandomChar r = RandomChar.getRandomChar();
		// 可能
		List<String> ls = new ArrayList<String>();
		
//		(code >= 0 && code <= 11) || code == 999
		for(int i = 0;i <=9;i++){
			ls.add("000" + i );
		}
		ls.add("0010");
		ls.add("0011");
		ls.add("0999");
//		(code >= 1000 && code <= 1010) || code == 1999
		for(int i = 1000;i<=1010;i++){
			ls.add(i +"");
		}
		ls.add("1999");
//		(code >= 2000 && code <= 2180) || code == 2999)  ||code == 9999
		for(int i = 2000;i<=2180;i++){
			ls.add(i +"");
		}
		ls.add("2999");
		ls.add("9999");

		String RandomResults = ls.get(r.generateRandomInt(ls.size()));
		//System.out.println("############ServiceContentCode");
		//System.out.println("ServiceContentCode   RandomResults :"  +  RandomResults);
		
		
		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}
	/**
	 * User: wb Date: 2014-8-18 
	 * GA/T_974.23-2011	
	 * 2		
	 * (?#ALGNAME=FireautomaticSystem)(?#PARA=0,1){]
	 */
	public static FunctionResult FireautomaticSystem_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String[] splitIndex = index.split(",");
		// 求表长
		RandomChar r = RandomChar.getRandomChar();
		// 可能
		String[] ss = {"30","31","32","20","21","22","40","41","42","43","10"};
		StringBuffer sb = new StringBuffer();

		sb.append(ss[r.generateRandomInt(ss.length)]);

		String RandomResults = sb.toString();
		
		//System.out.println("############FireautomaticSystem");
		//System.out.println("FireautomaticSystem   RandomResults :"  +  RandomResults);
		
		
		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}
	/**
	 * User: wb Date: 2014-8-18 
	 * GA_24.7-2005	
	 * 2		
	 * (?#ALGNAME=OneoTO24)(?#PARA=0,1){]
	 */
	public static FunctionResult OneoTO24_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String[] splitIndex = index.split(",");
		// 求表长
		RandomChar r = RandomChar.getRandomChar();
		// 可能
		List<String> ls = new ArrayList<String>();
		
//		i >= 1 && i <= Xx || i == 99
		//01~09 10~20 21~24
		for(int i = 1;i <=24;i++){
			ls.add(StringUtils.leftPad(i+"", 2, "0"));
		}
		ls.add("99");
		

		String RandomResults = ls.get(r.generateRandomInt(ls.size()));
		//System.out.println("############OneoTO24");
		//System.out.println("OneoTO24   RandomResults :"  +  RandomResults);
		
		
		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}


	/**
	 * User: wb Date: 2014-8-18 
	 * DL/T_510-2010	
	 * 3		
	 * (?#ALGNAME=PowerGrid)(?#PARA=0,1,2){]
	 * powergrid
	 */
	public static FunctionResult PowerGrid_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom RecoDaoRandom = new RecoDaoRandom();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = RecoDaoRandom.getLengthPublicFunction(
				RecoUtilRandom.powergrid_TABLE,
				RecoDaoRandom.hashMappowergrid);

		// 得到随机index的code
		RandomChar r = RandomChar.getRandomChar();
		int RandomIndex = 0;
		RandomIndex = r.generateRandomInt (length);
		String RandomResults = RecoDaoRandom.getRandomIndexPublicFunction(
				RecoUtilRandom.powergrid_TABLE, RandomIndex,
				RecoDaoRandom.hashMappowergrid);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}


		return Result;
	}
	/**
	 * User: wb Date: 2014-8-18 
	 * GA/T_974.39-2011	
	 * 2		
	 * (?#ALGNAME=ServiceState)(?#PARA=0,1){]
	 */
	public static FunctionResult ServiceState_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String[] splitIndex = index.split(",");
		// 求表长
		RandomChar r = RandomChar.getRandomChar();
		// 可能
		List<String> ls = new ArrayList<String>();
		
		//(1~7)0   2(1~7)
		for(int i = 1;i <=7;i++){
			ls.add(i + "0");
		}
		for(int i = 1;i <=7;i++){
			ls.add("2" + i);
		}
		

		String RandomResults = ls.get(r.generateRandomInt(ls.size()));
		//System.out.println("############ServiceState");
		//System.out.println("ServiceState   RandomResults :"  +  RandomResults);
		
		
		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}
	
	/**
	 * User: wb Date: 2014-8-18 
	 * GA/T_852.8-2009	
	 * 2		
	 * (?#ALGNAME=PractitionerType)(?#PARA=0,1){]
	 */
	public static FunctionResult PractitionerType_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String[] splitIndex = index.split(",");
		// 求表长
		RandomChar r = RandomChar.getRandomChar();
		// 可能
		String[] ss = {"10","11","12","13","19","20","21","22","23","29","30","40","99"};
		StringBuffer sb = new StringBuffer();

		sb.append(ss[r.generateRandomInt(ss.length)]);

		String RandomResults = sb.toString();
		
        //System.out.println("############PractitionerType");
		//System.out.println("PractitionerType   RandomResults :"  +  RandomResults);
		
		
		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}
	/**
	 * User: wb Date: 2014-8-18 
	 * GA/T_615.6-2006	
	 * 3		
	 * (?#ALGNAME=Borderinfo6)(?#PARA=0,1,2){]
	 */
	public static FunctionResult Borderinfo6_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String[] splitIndex = index.split(",");
		// 求表长
		RandomChar r = RandomChar.getRandomChar();
		// 可能
		List<String> ls = new ArrayList<String>();
		
		//i >= 101 && i <= 108
		for(int i = 101;i <=108;i++){
			ls.add(i + "");
		}
//		i >= 201 && i <= 202
		ls.add(201+"");
		ls.add(202+"");
//		i >= 300 && i <= 306
		for(int i = 300;i <=306;i++){
			ls.add("" + i);
		}
//		i == 399 || i == 401 || i == 502 || i == 900
		ls.add(399+"");
		ls.add(401+"");
		ls.add(502+"");
		ls.add(900+"");
		String RandomResults = ls.get(r.generateRandomInt(ls.size()));
		//System.out.println("############Borderinfo6");
		//System.out.println("Borderinfo6   RandomResults :"  +  RandomResults);
		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		return Result;
	}
	/**
	 * User: wb Date: 2014-8-18 
	 * GA/T_974.61-2011	
	 * 3		
	 * (?#ALGNAME=ExtinguishFire)(?#PARA=0,1,2){]
	 */
	public static FunctionResult ExtinguishFire_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String[] splitIndex = index.split(",");
		// 求表长
		RandomChar r = RandomChar.getRandomChar();
		// 可能
		List<String> ls = new ArrayList<String>();
		
		//(i >= 110 && i <= 114
		for(int i = 110;i <=114;i++){
			ls.add(i + "");
		}
//		i >= 119 && i <= 122
		for(int i = 119;i <=122;i++){
			ls.add(i + "");
		}
//		i >= 210 && i <= 214
		for(int i = 210;i <=214;i++){
			ls.add("" + i);
		}
//		i >= 310 && i <= 323
		for(int i = 310;i <=323;i++){
			ls.add("" + i);
		}
//		i >= 329 && i <= 333
		for(int i = 329;i <=333;i++){
			ls.add("" + i);
		}
		
//		i >= 410 && i <= 413
		for(int i = 410;i <=413;i++){
			ls.add("" + i);
		}
//		i >= 419 && i <= 423
		for(int i = 419;i <=423;i++){
			ls.add("" + i);
		}
/*
	(i == 100 || i == 129 || i == 180 || i == 190 || i == 200
					|| i == 219 || i == 300 || i == 339 || i == 380 || i == 390
					|| i == 400 || i == 429
*/
		ls.add(100+"");
		ls.add(129+"");
		ls.add(180+"");
		ls.add(190+"");
		ls.add(200+"");
		ls.add(219+"");
		ls.add(300+"");
		ls.add(339+"");
		ls.add(380+"");
		ls.add(390+"");
		ls.add(400+"");
		ls.add(429+"");
		String RandomResults = ls.get(r.generateRandomInt(ls.size()));
		//System.out.println("############ExtinguishFire");
		//System.out.println("ExtinguishFire   RandomResults :"  +  RandomResults);
		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}
		return Result;
	}
	/**
	 * User: wb Date: 2014-8-18 
	 * GA/T_974.44-2011	
	 * 2		
	 * (?#ALGNAME=FireInfo)(?#PARA=0,1){]
	 */
	public static FunctionResult FireInfo_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String[] splitIndex = index.split(",");
		// 求表长
		RandomChar r = RandomChar.getRandomChar();
		// 可能
		String[] ss = {"10","11","12","13","20","21","22","23","30","31","32","33","90"};
		StringBuffer sb = new StringBuffer();

		sb.append(ss[r.generateRandomInt(ss.length)]);

		String RandomResults = sb.toString();
		
		//System.out.println("############FireInfo");
		//System.out.println("FireInfo   RandomResults :"  +  RandomResults);
		
		
		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}
/**
 * ********************************************************************************************
 * 下边是纯测试部分，无rd
 */
	
	/**
	 * **********************************名字有问题的规则****************************************************
	 */
	/**
	 * User: wb Date: 2014-8-25 
	 * DL/T_700.1-1999_47	
	 * DL/T_700.2-1999_44
	 * 11	
	 * 0,254,2,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;	
	 * (?#ALGNAME=TwoByteDecimalnt)(?#PARA=1,2){]
	 * (?#ALGNAME=TwoByteDecimalnt)(?#PARA=1,2){]
	 * 有异常
	 */
	
	
	
	
	/**
	 * User: wb Date: 2014-8-25 
	 * DL/T_700.1-1999_32
	 * DL/T_700.2-1999_29
	 * 12	
	 * 4,255,3,0,0,0,0,0,0;5,255,3,0,0,0,0,0,0;6,255,3,0,0,0,0,0,0;7,255,3,0,0,0,0,0,0;8,255,3,0,0,0,0,0,0;9,255,3,0,0,0,0,0,0;10,255,3,0,0,0,0,0,0;11,255,3,0,0,0,0,0,0;	
	 * (?#ALGNAME=TwoByteDecimalnt)(?#PARA=0,1){]
	 * (?#ALGNAME=TwoByteDecimalnt)(?#PARA=2,3){]
	 * 函数规则已有，可直接进行测试
	 */
	
	/**
	 * User: wb Date: 2014-8-25 DL/T_700.1-1999_38 9-10 0,6,0,0,0,0,0,0,0;
	 * (?#ALGNAME=TwoByteDecimalnt)(?#PARA=1,2){]
	 * (?#ALGNAME=ParamCode19)(?#PARA=3,-1){] TwoByteDecimalnt与ParamCode19
	 */

	public static FunctionResult ParamCode19_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		// 求表长
		int length = Result.nSize;
		//System.out.println("Result.nSize : " + Result.nSize);
		// 得到随机index的code
		RandomChar r = RandomChar.getRandomChar();

		int length_first = Integer.parseInt(splitIndex[0]);
		// 为了生成length-length_fist位数字
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length - length_first; i++) {
			sb.append(r.generateRandomInt(9) + 1 + "");
		}

		String RandomResults = sb.toString();
		//System.out.println("####RandomResults" + RandomResults);
		splitIndex = new String[RandomResults.length()];
		for (int i = 0; i < RandomResults.length(); i++) {
			splitIndex[i] = length_first + i + "";
		}
		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			InPutResult.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}
	//wangbin
	// 按照hashmap内部的值走循环
	public static String getRandomCode(HashMap<Integer, String> Result,
			String[] require) {

		String CodeOfIndex = "";
		String BitCode = "";
		//System.out.println("getRandomCode获取所需HashMap中的数据:");

		StringBuffer sb = new StringBuffer();
		Iterator it = Result.keySet().iterator();
		int i = 0;
		// while (it.hasNext()&&i<require.length)
		//所要的object的长度一定小于整个result的size
		while (i < require.length) {
			Object key = it.next();
			BitCode = Result.get(Integer.parseInt(require[i]));
			sb.append(BitCode);
			//System.out.println("需要：第 " + i + "位：" + BitCode);
			i++;
			// System.out.println("value:"+a.get(key));
		}

		//System.out.println();
		return sb.toString();
	}
	
	//added by lly
	//new function
	public static FunctionResult OneTO09Not99_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		String[] randomresult = { "01", "02", "03", "04", "05", "06", "07",
				"08", "09" };
		int maxlength = randomresult.length;
		int indexlength = RandomChar.generateRandomInt(maxlength);
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					randomresult[indexlength].charAt(i) + "");
		}
		return Result;
	}
	
	//added by WB on 0915
	//modified by WB on  0917
	/*
	public static FunctionResult GB_T_21285_2007_1_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result =InPutResult;
		
		String[] splitIndex = index.split(",");
		int length_first = Integer.parseInt(splitIndex[0]);
		// 求表长
//		String regex = "[C]{0,}";
		RandomChar r = RandomChar.getRandomChar();
		int length = Result.nSize;
		//System.out.println("$$$$$$$$$$$$$$ Result.nSize " +  Result.nSize);
		//利用nSize来确定{4,-1}的长度为多少
		if(length ==length_first){
			//长度一样说明不需要额外添加C了
			return Result;
		}else{
			StringBuffer sb = new StringBuffer();
			for(int i = 0;i <length - length_first;i++){
				sb.append("C");
			}
			
			String RandomResults =sb.toString();
			
			//增加splitIndex长度，由于-1无限大的原因

			// 得到随机index的code
			
			splitIndex = new String[RandomResults.length()];
			for(int i = 0;i<RandomResults.length();i++ ){
				splitIndex[i] = length_first +i +"";
			}
			// 保存结果
			for (int i = 0; i < RandomResults.length(); i++) {
				Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
						String.valueOf(RandomResults.charAt(i)));
			}
			return Result;
		}
	}*/
	/**
	 * User: wb Date: 2014-8-18   
	 * GB/T_21285-2007_1	
	 * 4,5	
	 * 0,0,0,0,0,240,239,1,32;1,0,0,0,0,240,239,123,32;2,255,3,0,0,0,0,0,0;3,255,3,0,0,0,0,0,0;	
	 * (?#ALGNAME=TubesValves)(?#PARA=4,-1){] 已有跟上边的TubesValves1一样
	 */
	public static FunctionResult TubesValves_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result =InPutResult;
		
		String[] splitIndex = index.split(",");
		int length_first = Integer.parseInt(splitIndex[0]);
		// 求表长
//		String regex = "[C]{0,}";
		RandomChar r = RandomChar.getRandomChar();
		int length = Result.nSize;
		//System.out.println("$$$$$$$$$$$$$$ Result.nSize " +  Result.nSize);
		//利用nSize来确定{4,-1}的长度为多少
		if(length ==length_first){
			//长度一样说明不需要额外添加C了
			return Result;
		}else{
			StringBuffer sb = new StringBuffer();
			for(int i = 0;i <length - length_first;i++){
				sb.append("C");
			}
			
			String RandomResults =sb.toString();
			
			//增加splitIndex长度，由于-1无限大的原因

			// 得到随机index的code
			
			splitIndex = new String[RandomResults.length()];
			for(int i = 0;i<RandomResults.length();i++ ){
				splitIndex[i] = length_first +i +"";
			}
			// 保存结果
			for (int i = 0; i < RandomResults.length(); i++) {
				Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
						String.valueOf(RandomResults.charAt(i)));
			}
			return Result;
		}
	}

	
	//added by WB on 0915
	public static FunctionResult ThreeByteDecimalnt1_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;

		String[] splitIndex = index.split(",");
		RandomChar r = RandomChar.getRandomChar();
		
		String RandomResults = StringUtils.leftPad(r.generateRandomInt(399)+1+"", 3, "0");
		
		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]), String
					.valueOf(RandomResults.charAt(i)));
		}

		return Result;
	}
	

	//added by lly on 0917
	public static FunctionResult OneTO11NO99_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		String[] randomresult = { "01", "02", "03", "04", "05", "06", "07",
				"08", "09","11" };
		int maxlength = randomresult.length;
		int indexlength = RandomChar.generateRandomInt(maxlength);
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					randomresult[indexlength].charAt(i) + "");
		}
		return Result;
	}

	//added by lly on 0917
	public static FunctionResult OneTO5NO99_Random(
			FunctionResult InPutResult, String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		String[] splitIndex = index.split(",");
		String[] randomresult = { "01", "02", "03", "04", "05"
};
		int maxlength = randomresult.length;
		int indexlength = RandomChar.generateRandomInt(maxlength);
		for (int i = 0; i < splitIndex.length; i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					randomresult[indexlength].charAt(i) + "");
		}
		return Result;
	}
	
	//added by WB on 0917

	/**
	 * user:wb date:2014-09-12
	 * pharmaceuticalmachinery表的操作，标准YY_0260-1997
	 * 函数规则是(?#ALGNAME=Pharmaceuticalmachinery)(?#PARA=0,1,2,3,4,5,6,7){]
	 * 
	 */

	public static FunctionResult Pharmaceuticalmachinery_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result = InPutResult;
		RecoDaoRandom recoDao = RecoDaoRandom.getRecoDao();
		String[] splitIndex = index.split(",");
		// 求表长
		int length = recoDao.getLengthPublicFunction(
				RecoUtilRandom.pharmaceuticalmachinery_TABLE,
				recoDao.hashpharmaceuticalmachinery);

		// 得到随机index的code
		RandomChar r = RandomChar.getRandomChar();
		int RandomIndex = 0;
		RandomIndex = r.generateRandomInt (length);
		String RandomResults = recoDao.getRandomIndexPublicFunction(
				RecoUtilRandom.pharmaceuticalmachinery_TABLE, RandomIndex,
				recoDao.hashpharmaceuticalmachinery);

		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}


		return Result;
	}
	
	//added by WB on 0917
	public static FunctionResult ParamCode27_Random(FunctionResult InPutResult,
			String index) {
		FunctionResult Result = new FunctionResult();
		Result =InPutResult;
		
		String[] splitIndex = index.split(",");
		int length_first = Integer.parseInt(splitIndex[0]);
		// 求表长
		RandomChar r = RandomChar.getRandomChar();
		int length = Result.nSize;
		//利用nSize来确定{?,-1}的长度为多少
		String []ss = {"0","1", "2", "3", "4", "5", "6", "7", "8", "9"};
		StringBuffer sb = new StringBuffer();
		int length_other = length-length_first;
		//System.out.println("##########length_other" + length_other);
		String []require = {"0","1"};
		String first_code = RuleRandom.getRandomCode(Result.FunctionResult, require);
		if(first_code.equals("01")){
			//长度为10
			Result.nSize = 10;
			for(int i = 0;i<10;i++){
				sb.append(ss[r.generateRandomInt(ss.length)]);
			}
		}else{
			//长度为8
			Result.nSize = 8;
			for(int i = 0;i<8;i++){
				sb.append(ss[r.generateRandomInt(ss.length)]);
			}
		}
		
		String RandomResults =sb.toString();
		
		//增加splitIndex长度，由于-1无限大的原因
		
		splitIndex = new String[RandomResults.length()];
		for(int i = 0;i<RandomResults.length();i++ ){
			splitIndex[i] = length_first +i +"";
		}
		
		
		
		
		// 保存结果
		for (int i = 0; i < RandomResults.length(); i++) {
			Result.FunctionResult.put(Integer.parseInt(splitIndex[i]),
					String.valueOf(RandomResults.charAt(i)));
		}


		return Result;
	}
	
	







}
