package cn.niot.service;

import cn.niot.dao.RecoDao;

//EAN-13与街巷名
public class CompareCode13 {

	private int randomNum = 10000;
	
	//char随机
	private char[] randomCodeRegex(String[] regexArray){
		char[] charArray = new char[regexArray.length];
		for(int i = 0; i < regexArray.length; i++){
			charArray[i] = NewIDstdCollisionDetect.generateRandomChar(regexArray[i]);
		}
		return charArray;
	}
	
	//行政区划代码随机
	private String randomAdminDivision(){
		String code = "";
		try{
			RecoDao dao = new RecoDao();
			code = dao.getRandomAdminDivision();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return code;
	}
	
	//EANUP随机
	private String randomRetailCommunityNumber(){
		String code = "";
		try{
			RecoDao dao = new RecoDao();
			code = dao.getRandomEANUPC();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return code;
	}
	//EAN-13У校验位
	public static char checkCommodityCode(char[] eanCode){
		char checkcode = 0;
		int i = 0;

		// the sum of the odd and even number
		int odd_sum = 0;
		int even_sum = 0;
		
		for (i = eanCode.length - 2; i >= 0; i -= 2) {
			even_sum += (eanCode[i] - 48); 
		}

		for (i = eanCode.length - 3; i >= 0; i -= 2) {
			odd_sum += (eanCode[i] - 48);
		}

		if ((((even_sum * 3 + odd_sum)) % 10) == 0) {
			checkcode = 48;
		} else {
			checkcode = (char) ((10 - ((even_sum * 3 + odd_sum)) % 10) + 48);
		}
		return checkcode;
	}
	
	//随机产生街巷名编码
	public char[] generateRandomStreetCode() {
		String[] regexArray = new String[]{"[0-3]", "[0-9]", "[0-9]", "[0,1]", "[0-9]", "[0-9]", "[0-9]"};
		char[] charArray = new char[regexArray.length];
		char[] adminDivisionArray = new char[6];
		String adminDivisionCode = randomAdminDivision();
		
		charArray = randomCodeRegex(regexArray);
		adminDivisionArray =  adminDivisionCode.toCharArray();
		char[] streetCode = new char[adminDivisionArray.length + charArray.length];
		System.arraycopy(adminDivisionArray, 0, streetCode, 0, adminDivisionArray.length);
		System.arraycopy(charArray, 0, streetCode, adminDivisionArray.length, charArray.length);
		return streetCode;
	}

	//随机产生EAN-13
	public char[] generateRandomEAN13() {
		String[] regexArray = new String[]{"[0-9]", "[0-9]", "[0-9]", "[0-9]" ,"[0-9]", "[0-9]" ,"[0-9]", "[0-9]", "[0-9]"};
		String commodityNum = randomRetailCommunityNumber();
		char[] charArray = randomCodeRegex(regexArray);
		char[] eanArray =  commodityNum.toCharArray();
		
		char[] eanCode = new char[eanArray.length + charArray.length + 1];
		System.arraycopy(eanArray, 0, eanCode, 0, eanArray.length);
		System.arraycopy(charArray, 0, eanCode, eanArray.length, charArray.length);
		
		char checkSum = checkCommodityCode(eanCode);
		eanCode[eanCode.length - 1] = checkSum;
		return eanCode;
	}
	
	//YC/T_414-2011_1 22位
//	public char[] generateRandomYCT414() {
//		
//	}
	
	//GB/T_21379-2008_1 22位
//	public char[] generateRandomGBT21379() {
//		char[] adminDivisionArray = new char[6];
//		String adminDivisionCode = randomAdminDivision();
//	}
}
