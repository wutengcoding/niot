package cn.unitTest;

import static org.junit.Assert.assertEquals;

import javax.swing.JOptionPane;

import cn.niot.rule.RuleFunction;
import cn.niot.service.NewIDstdCollisionDetect;

import cn.niot.service.*;

@SuppressWarnings("unused")
public class RuleFuncTest {
	static int i = 0;

	public static void UnitTestEqual(String str1, String str2) {

		if (str1.equals(str2)) {
			JOptionPane.showMessageDialog(null, "Unit Test Passed!");
			System.out.println("Unit Test Passed!");
		} else {
			JOptionPane.showMessageDialog(null, "Unit Test Failed!");
			System.out.println("Unit Test Failed!");
		}
		return;
	}

	public static void testGenerateRandomChar() {
		for (int i = 0; i < 100; i++) {
			String byterule = "[0,1]";
			char res = NewIDstdCollisionDetect.generateRandomChar(byterule);
			System.out.println(res);
		}

	}

	public static void testFormJsonString() {
		String len = "8";
		String valueRange = "1-1:0-9;4-6:a-z;7-8:A-Z;";
		String resJasonStr = NewIDstdCollisionDetect.formJsonString(len,
				valueRange);
		System.out.println(resJasonStr);
		// UnitTestEqual(resJasonStr, "ERR");
	}

	// 13, LY/T 1627-2005
	public static void testTwoByteDecimalnt() {
		int[] index1 = { 0, 1 };
		char[] IDstr1 = { '0', '1' };
		UnitTestEqual(RuleFunction.TwoByteDecimalnt(IDstr1, 2, index1, 2), "OK");

		int[] index2 = { 0, 1 };
		char[] IDstr2 = { '9', '9' };
		UnitTestEqual(RuleFunction.TwoByteDecimalnt(IDstr2, 2, index2, 2), "OK");

		int[] index3 = { 0, 1 };
		char[] IDstr3 = { '5', '9' };
		UnitTestEqual(RuleFunction.TwoByteDecimalnt(IDstr3, 2, index3, 2), "OK");

		int[] index4 = { 0, 1 };
		char[] IDstr4 = { 'a', 'b' };
		UnitTestEqual(RuleFunction.TwoByteDecimalnt(IDstr4, 2, index4, 2),
				"ERR");

	}

	// 2, YC/T 414-2011_1
	public static void testCigaSubClassCode() {
		int[] index1 = { 0, 1, 2 };
		char[] IDstr1 = { '0', '0', '0' };
		UnitTestEqual(RuleFunction.CigaSubClassCode(IDstr1, 3, index1, 3),
				"ERR");

		int[] index2 = { 0, 1, 2 };
		char[] IDstr2 = { '9', '9', '9' };
		UnitTestEqual(RuleFunction.CigaSubClassCode(IDstr2, 3, index2, 3),
				"ERR");

		int[] index3 = { 0, 1, 2 };
		char[] IDstr3 = { '1', '0', '2' };
		UnitTestEqual(RuleFunction.CigaSubClassCode(IDstr3, 3, index3, 3), "OK");

		int[] index4 = { 0, 1, 2 };
		char[] IDstr4 = { '4', '9', '9' };
		UnitTestEqual(RuleFunction.CigaSubClassCode(IDstr4, 3, index4, 3), "OK");

	}

	// 2, YC/T 414-2011_1
	public static void testMonthDate() {
		int[] index1 = { 0, 1, 2, 3 };
		char[] IDstr1 = { '0', '0', '0', '0' };
		UnitTestEqual(RuleFunction.MonthDate(IDstr1, 4, index1, 4), "ERR");

		int[] index2 = { 0, 1, 2, 3 };
		char[] IDstr2 = { '0', '2', '3', '0' };
		UnitTestEqual(RuleFunction.MonthDate(IDstr2, 4, index2, 4), "ERR");

		int[] index3 = { 0, 1, 2 };
		char[] IDstr3 = { '1', '0', '2', '9' };
		UnitTestEqual(RuleFunction.MonthDate(IDstr3, 4, index3, 3), "ERR");

		int[] index4 = { 0, 1, 2, 3 };
		char[] IDstr4 = { '1', '2', '3', '1' };
		UnitTestEqual(RuleFunction.MonthDate(IDstr4, 4, index4, 4), "OK");
		int[] index5 = { 0, 1, 2, 3 };
		char[] IDstr5 = { '0', '2', '2', '9' };
		UnitTestEqual(RuleFunction.MonthDate(IDstr5, 4, index5, 4), "OK");

	}

	// 3, YC/T 400-2011
	public static void testCigaOrgCode() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		int[] index1 = { 0, 1 };
		char[] IDstr1 = { '1', '0' };
		UnitTestEqual(RuleFunction.CigaOrgCode(IDstr1, 2, index1, 2), "OK");

		int[] index2 = { 0, 1, };
		char[] IDstr2 = { '9', '9' };
		UnitTestEqual(RuleFunction.CigaOrgCode(IDstr2, 2, index2, 2), "OK");

		int[] index3 = { 0, 1, 2 };
		char[] IDstr3 = { '0', '0', '0' };
		UnitTestEqual(RuleFunction.CigaOrgCode(IDstr3, 3, index3, 3), "ERR");

		int[] index4 = { 0, 1 };
		char[] IDstr4 = { '3', '9' };
		UnitTestEqual(RuleFunction.CigaOrgCode(IDstr4, 2, index4, 2), "ERR");

	}

	// 函数Count与函数CigaOrgCode功能相同，Regex表中并没有用到Count函数
//	public static void testCount() {
//		JOptionPane.showMessageDialog(null, Thread.currentThread()
//				.getStackTrace()[1].getMethodName());
//		int[] index1 = { 0, 1 };
//		char[] IDstr1 = { '1', '0' };
//		UnitTestEqual(RuleFunction.Count(IDstr1, 2, index1, 2), "OK");
//
//		int[] index2 = { 0, 1, };
//		char[] IDstr2 = { '9', '9' };
//		UnitTestEqual(RuleFunction.Count(IDstr2, 2, index2, 2), "OK");
//
//		int[] index3 = { 0, 1 };
//		char[] IDstr3 = { '2', '1' };
//		UnitTestEqual(RuleFunction.Count(IDstr3, 2, index3, 2), "OK");
//
//		int[] index4 = { 0, 1 };
//		char[] IDstr4 = { '3', '9' };
//		UnitTestEqual(RuleFunction.Count(IDstr4, 2, index4, 2), "ERR");
//
//	}

	// 3, YC/T 400-2011
	public static void testCigaDepCode() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());
		int[] index1 = { 0, 1 };
		char[] IDstr1 = { '0', '0' };
		UnitTestEqual(RuleFunction.CigaDepCode(IDstr1, 2, index1, 2), "OK");

		int[] index2 = { 0, 1, };
		char[] IDstr2 = { '9', '7' };
		UnitTestEqual(RuleFunction.CigaDepCode(IDstr2, 2, index2, 2), "OK");

		int[] index3 = { 0, 1 };
		char[] IDstr3 = { '2', '1' };
		UnitTestEqual(RuleFunction.CigaDepCode(IDstr3, 2, index3, 2), "OK");

		int[] index4 = { 0, 1 };
		char[] IDstr4 = { '9', '9' };
		UnitTestEqual(RuleFunction.CigaDepCode(IDstr4, 2, index4, 2), "ERR");

		int[] index5 = { 0, 1, 2 };
		char[] IDstr5 = { '0', '1', '2' };
		UnitTestEqual(RuleFunction.CigaDepCode(IDstr5, 3, index5, 3), "OK");
	}

	// 3, YC/T 400-2011
	public static void testFirst4CharsofAdminDivisionforCiga() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());
		int[] index1 = { 0, 1, 2, 3 };
		char[] IDstr1 = { '0', '0', '0', '0' };

		UnitTestEqual(RuleFunction.First4CharsofAdminDivisionforCiga(IDstr1, 4,
				index1, 4), "OK");

		int[] index2 = { 0, 1, 2, 3 };
		char[] IDstr2 = { '1', '3', '0', '1' };
		UnitTestEqual(RuleFunction.First4CharsofAdminDivisionforCiga(IDstr2, 4,
				index2, 4), "OK");

		int[] index3 = { 0, 1, 2, 3 };
		char[] IDstr3 = { '1', '0', '0', '1' };
		UnitTestEqual(RuleFunction.First4CharsofAdminDivisionforCiga(IDstr3, 4,
				index3, 4), "ERR");

		int[] index4 = { 0, 1, 2, 3 };
		char[] IDstr4 = { '1', '1', '0', '1' };
		UnitTestEqual(RuleFunction.First4CharsofAdminDivisionforCiga(IDstr4, 4,
				index4, 4), "OK");
	}

	// 46, GB/T 28422-2012_2
	public static void testFirst2CharsofAdminDivision() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());
		int[] index1 = { 0, 1 };
		char[] IDstr1 = { '0', '0' };
		System.out.print(i++);
		UnitTestEqual(RuleFunction.First2CharsofAdminDivision(IDstr1, 2,
				index1, 2), "ERR");

		int[] index2 = { 0, 1 };
		char[] IDstr2 = { '1', '3' };
		System.out.print(i++);
		UnitTestEqual(RuleFunction.First2CharsofAdminDivision(IDstr2, 2,
				index2, 2), "OK");
		int[] index6 = { 0, 1 };
		char[] IDstr6 = { '1', '0' };
		System.out.print(i++);
		UnitTestEqual(RuleFunction.First2CharsofAdminDivision(IDstr6, 2,
				index6, 2), "ERR");
		int[] index7 = { 0, 1 };
		char[] IDstr7 = { '7', '2' };
		System.out.print(i++);
		UnitTestEqual(RuleFunction.First2CharsofAdminDivision(IDstr7, 2,
				index7, 2), "ERR");

		int[] index3 = { 0, 1 };
		char[] IDstr3 = { '8', '3' };
		System.out.print(i++);
		UnitTestEqual(RuleFunction.First2CharsofAdminDivision(IDstr3, 2,
				index3, 2), "ERR");
		int[] index4 = { 0, 1 };
		char[] IDstr4 = { '9', '9' };
		System.out.print(i++);
		UnitTestEqual(RuleFunction.First2CharsofAdminDivision(IDstr4, 2,
				index4, 2), "ERR");
	}

	// 3, YC/T 400-2011
	public static void testAdminDivision() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());
		int[] index1 = { 0, 1, 2, 3, 4, 5 };
		char[] IDstr1 = { '0', '0', '0', '0', '0', '0' };
		System.out.print(i++);
		UnitTestEqual(RuleFunction.AdminDivision(IDstr1, 6, index1, 6), "ERR");

		int[] index2 = { 0, 1, 2, 3, 4, 5 };
		char[] IDstr2 = { '1', '1', '0', '0', '0', '0' };
		System.out.print(i++);
		UnitTestEqual(RuleFunction.AdminDivision(IDstr2, 6, index2, 6), "ERR");

	}

	// cpc
	public static void testCountryRegionCodeforCPC() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());
		int[] index1 = { 0, 1, 2, 3 };
		char[] IDstr1 = { 'U', 'S', '0', '0' };
		System.out.print(i++);
		UnitTestEqual(RuleFunction
				.CountryRegionCodeforCPC(IDstr1, 4, index1, 4), "OK");

		int[] index2 = { 0, 1, 2, 3 };
		char[] IDstr2 = { 'U', 'S', 'A', '0' };
		System.out.print(i++);
		UnitTestEqual(RuleFunction
				.CountryRegionCodeforCPC(IDstr2, 4, index2, 4), "OK");

		int[] index3 = { 0, 1, 2, 3 };
		char[] IDstr3 = { 'U', 'S', 'A', '1' };
		System.out.print(i++);
		UnitTestEqual(RuleFunction
				.CountryRegionCodeforCPC(IDstr3, 4, index3, 4), "ERR");
		int[] index4 = { 0, 1, 2, 3 };
		char[] IDstr4 = { 'U', 'B', 'A', '0' };
		System.out.print(i++);
		UnitTestEqual(RuleFunction
				.CountryRegionCodeforCPC(IDstr4, 4, index4, 4), "ERR");

	}

	// 55, GB/T 26319-2010_1
	public static void testCountryRegionCode() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());
		String tr1 = new String("BLZ");
		char[] IDstr1 = new char[tr1.length()];
		for (int i = 0; i < tr1.length(); i++) {
			IDstr1[i] = tr1.charAt(i);
		}
		int[] index1 = { 0, 1, 2 };
		System.out.print(i++);
		UnitTestEqual(RuleFunction.CountryRegionCode(IDstr1, 3, index1, 3),
				"OK");

		String tr2 = new String("US");
		char[] IDstr2 = new char[tr2.length()];
		for (int i = 0; i < tr2.length(); i++) {
			IDstr2[i] = tr2.charAt(i);
		}
		int[] index2 = { 0, 1 };
		System.out.print(i++);
		UnitTestEqual(RuleFunction.CountryRegionCode(IDstr2, 2, index2, 2),
				"ERR");

		String tr3 = new String("US0");
		char[] IDstr3 = new char[tr3.length()];
		for (int i = 0; i < tr3.length(); i++) {
			IDstr3[i] = tr3.charAt(i);
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.CountryRegionCode(IDstr3, 3, index1, 3),
				"ERR");

	}

	// 7, YC/T 213.3-2006
	public static void testTabaccoMachineProduct() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		int j = 5;

		int[] index1 = new int[j];
		;
		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		int[] index2 = { 0, 1, 2, 3 };

		// 定义IDstr
		String tr1 = new String("A0101");
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("A0127");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("0101");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j - 1; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TabaccoMachineProduct(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TabaccoMachineProduct(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TabaccoMachineProduct(IDstr3, 4, index2, 4),
				"ERR");

	}

	// 9, YC/T 209.2-2008
	public static void testPrefixofRetailCommodityNumber() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		int j = 3; // 定义长度
		int[] index1 = new int[j];
		;
		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		String tr1 = new String("859");
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("485");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("892");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j - 1; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PrefixofRetailCommodityNumber(IDstr1, j,
				index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PrefixofRetailCommodityNumber(IDstr2, j,
				index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PrefixofRetailCommodityNumber(IDstr3, j,
				index1, j), "ERR");

	}

	// 4, YC/T 213.6-2006
	public static void testTabaccoMachineProducer() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		int j = 2; // 定义长度
		int[] index1 = new int[j];
		;
		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		// 定义IDstr
		String tr1 = new String("DF");
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("AB");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("WH");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j - 1; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		System.out.print(i++);
		UnitTestEqual(
				RuleFunction.TabaccoMachineProducer(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(
				RuleFunction.TabaccoMachineProducer(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(
				RuleFunction.TabaccoMachineProducer(IDstr3, j, index1, j), "OK");

	}

	// CID
	public static void testDistrictNo() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		int j = 4; // 定义长度
		int[] index1 = new int[j];
		;
		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		// 定义IDstr
		String tr1 = new String("0010");
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("0011");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.DistrictNo(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.DistrictNo(IDstr2, j, index1, j), "ERR");

	}

	// CID
	public static void testCIDRegex() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		int j = 39; // 定义长度
		int[] index1 = new int[21];
		;
		for (int i = 0; i < 21; i++) {
			index1[i] = i + 18;
		}

		// 定义IDstr
		String tr1 = new String("123123123123123123.kkkk.kkkk.cid.iot.cn");
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}
		System.out.print(IDstr1[18]);
		;
		String tr2 = new String("123123123123123123.k-0k.kkkk.cid.iot.cn");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("123123123123123123.k-0k.kkkk.cid.iot.vn");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CIDRegex(IDstr1, j, index1, 21), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CIDRegex(IDstr2, j, index1, 21), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CIDRegex(IDstr3, j, index1, 21), "ERR");

	}

	// 6, YC/T 213.4-2006
	public static void testTabaccoStandardPart() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		int j = 5; // 定义长度
		int[] index1 = new int[j];
		;
		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		// 定义IDstr
		String tr1 = new String("00201");
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("00101");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TabaccoStandardPart(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TabaccoStandardPart(IDstr2, j, index1, j),
				"ERR");

	}

	// 4, YC/T 213.6-2006
	public static void testTabaccoMaterial() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		int j = 5; // 定义长度
		int[] index1 = new int[j];
		;
		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		// 定义IDstr
		String tr1 = new String("01001");
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("99999");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TabaccoMaterial(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TabaccoMaterial(IDstr2, j, index1, j), "ERR");

	}

	// 55, GB/T 26319-2010_1
	public static void testIntFreitForwarding() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

//		int j = 23; // 定义长度
//		
//		;


		// [a-zA-Z][a-zA-Z0-9]{0,15}"
		// 定义IDstr
		String tr1 = new String("000000000000000000aa");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}
//		System.out.print(IDstr1[18]);
//		;
//		String tr2 = new String("000000000000000000009MW324DABCDEFGHAZBC90");
//		char[] IDstr2 = new char[j];
//		for (int i = 0; i < j; i++) {
//			IDstr2[i] = tr2.charAt(i);
//		}

//		String tr3 = new String("123123123123123123.5555");
//		char[] IDstr3 = new char[j];
//		for (int i = 0; i < j; i++) {
//			IDstr3[i] = tr3.charAt(i);
//		}
		int[] index1 = new int[j];
		for (int i = 0; i < j-18; i++) {
			index1[i] = i + 18;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.IntFreitForwarding(IDstr1, j, index1, j),
				"OK");

//		System.out.print(i++);
//		UnitTestEqual(RuleFunction.IntFreitForwarding(IDstr2, j, index1, j),
//				"OK");

//		System.out.print(i++);
//		UnitTestEqual(RuleFunction.IntFreitForwarding(IDstr3, j, index1, 5),
//				"ERR");

	}

	// 15, LS/T 1711-2004
	public static void testFoodAccount() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		int j = 6; // 定义长度
		int[] index1 = new int[j];
		;
		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		// 定义IDstr
		String tr1 = new String("100901");
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("111111");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FoodAccount(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FoodAccount(IDstr2, j, index1, j), "ERR");

	}

	// 23, LS/T 1706-2004
	public static void testGrainEquipment() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		int j = 8; // 定义长度
		int[] index1 = new int[j];
		;
		int[] index2 = { 0, 1, 2, 3, 4, 5 };
		;
		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		// 定义IDstr
		String tr1 = new String("11010102");
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("106706");
		char[] IDstr2 = new char[6];
		for (int i = 0; i < 6; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GrainEquipment(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GrainEquipment(IDstr2, 6, index2, 6), "ERR");

	}

	// 24, LS/T 1705-2004
	public static void testGrainEstablishment() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		int j = 7; // 定义长度
		int[] index1 = new int[j];
		;
		int[] index2 = { 0, 1, 2, 3, 4, 5 };
		;
		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		// 定义IDstr
		String tr1 = new String("11010102");
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("106706");
		char[] IDstr2 = new char[6];
		for (int i = 0; i < 6; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GrainEstablishment(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GrainEstablishment(IDstr2, 6, index2, 6),
				"ERR");

	}

	// 5, YC/T 213.5-2006
	public static void testTabaccoElectricComponent() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		int j = 5; // 定义长度
		int[] index1 = new int[j];
		;
		// int [] index2 = {0,1,2,3,4,5};;
		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		// 定义IDstr
		String tr1 = new String("01010");
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("01011");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TabaccoElectricComponent(IDstr1, j, index1,
				j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TabaccoElectricComponent(IDstr2, j, index1,
				j), "ERR");

	}

	// cpc
	public static void testCPCTwoByte() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		int j = 2; // 定义长度
		int[] index1 = new int[j];
		;
		// int [] index2 = {0,1,2,3,4,5};;
		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		// 定义IDstr
		String tr1 = new String("20");
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("14");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CPCTwoByte(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CPCTwoByte(IDstr2, j, index1, j), "ERR");

	}

	// 55,GB/T 26319-2010_1
	public static void testMonth() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		int j = 2; // 定义长度
		int[] index1 = new int[j];
		;
		// int [] index2 = {0,1,2,3,4,5};;
		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		// 定义IDstr
		String tr1 = new String("12");
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("14");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("05");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Month(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Month(IDstr2, j, index1, j), "ERR");
		System.out.print(i++);
		UnitTestEqual(RuleFunction.Month(IDstr3, j, index1, j), "OK");

	}

	// 25,LS/T 1704.3-2004
	public static void testClassOfGrain() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		int j = 6; // 定义长度
		int[] index1 = new int[j];
		;
		// int [] index2 = {0,1,2,3,4,5};;
		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		// 定义IDstr
		String tr1 = new String("030100");
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("030109");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ClassOfGrain(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ClassOfGrain(IDstr2, j, index1, j), "ERR");

	}

	// 43, GB/T 28923.1-2012_2
	public static void testTwobytleCode07() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		int j = 2; // 定义长度
		int[] index1 = new int[j];
		;
		// int [] index2 = {0,1,2,3,4,5};;
		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		// 定义IDstr
		String tr1 = new String("07");
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("99");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("10");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.TwobytleCode07(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TwobytleCode07(IDstr2, j, index1, j), "OK");
		System.out.print(i++);
		UnitTestEqual(RuleFunction.TwobytleCode07(IDstr3, j, index1, j), "ERR");
	}

	// Ucode
	public static void testCountUcode() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		int j = 4; // 定义长度
		int[] index1 = new int[j];
		;
		// int [] index2 = {0,1,2,3,4,5};;
		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		// 定义IDstr
		String tr1 = new String("E000");
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("ssFF");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("10ss");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.CountUcode(IDstr1, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CountUcode(IDstr2, j, index1, j), "OK");
		System.out.print(i++);
		UnitTestEqual(RuleFunction.CountUcode(IDstr3, j, index1, j), "OK");
	}

	// 未在Redex中找到DomainManagerInEPCCheck函数
	public static void testDomainManagerInEPCCheck() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		int j = 4; // 定义长度
		int[] index1 = new int[j];
		;
		// int [] index2 = {0,1,2,3,4,5};;
		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		// 定义IDstr
		String tr1 = new String("E000");
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("ssFF");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("10ss");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction
				.DomainManagerInEPCCheck(IDstr1, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction
				.DomainManagerInEPCCheck(IDstr2, j, index1, j), "OK");
		System.out.print(i++);
		UnitTestEqual(RuleFunction
				.DomainManagerInEPCCheck(IDstr3, j, index1, j), "OK");
	}

	// 58, GB/T 23833-2009_1-4
	public static void testCheckCodeForCommodityCode() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		// int j=4;//定义长度

		// 定义IDstr
		String tr1 = new String("06901234567892");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("0AS901234567892");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("0690123456789X");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		int[] index1 = new int[j];
		;
		// int [] index2 = {0,1,2,3,4,5};;
		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.CheckCodeForCommodityCode(IDstr1, j, index1,
				j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CheckCodeForCommodityCode(IDstr2, j, index1,
				j), "ERR");
		System.out.print(i++);
		UnitTestEqual(RuleFunction.CheckCodeForCommodityCode(IDstr3, j, index1,
				j), "ERR");
	}

	// 未在Redex中找到HouseCode_CheckBasedCompleteTime函数
	public static void testHouseCode_CheckBasedCompleteTime() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		int j = 12; // 定义长度

		// 定义IDstr
		String tr1 = new String("200807080101");
		// int j=tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("201013010101");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("2010**070101");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}
		String tr4 = new String("2010*1070101");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}
		String tr5 = new String("2010**000000");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}
		int[] index1 = new int[j];
		;
		// int [] index2 = {0,1,2,3,4,5};;
		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.HouseCode_CheckBasedCompleteTime(IDstr1, j,
				index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HouseCode_CheckBasedCompleteTime(IDstr2, j,
				index1, j), "ERR");
		System.out.print(i++);
		UnitTestEqual(RuleFunction.HouseCode_CheckBasedCompleteTime(IDstr3, j,
				index1, j), "OK");
		System.out.print(i++);
		UnitTestEqual(RuleFunction.HouseCode_CheckBasedCompleteTime(IDstr4, j,
				index1, j), "ERR");
		System.out.print(i++);
		UnitTestEqual(RuleFunction.HouseCode_CheckBasedCompleteTime(IDstr5, j,
				index1, j), "ERR");
	}

	// 未在Redex中找到HouseCode_CheckBasedCoordinate函数
	public static void testHouseCode_CheckBasedCoordinate() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		int j = 12; // 定义长度

		// 定义IDstr
		String tr1 = new String("010101020202");
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("0AS000020202");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		int[] index1 = new int[j];
		;
		// int [] index2 = {0,1,2,3,4,5};;
		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.HouseCode_CheckBasedCoordinate(IDstr1, j,
				index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HouseCode_CheckBasedCoordinate(IDstr2, j,
				index1, j), "ERR");

	}

	// 未在Redex中找到HouseCode_CheckBasedFenzong函数
	public static void testHouseCode_CheckBasedFenzong() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		int j = 12; // 定义长度

		// 定义IDstr
		String tr1 = new String("010101020202");
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("0AS000020202");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("000000020202");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		int[] index1 = new int[j];
		;
		// int [] index2 = {0,1,2,3,4,5};;
		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.HouseCode_CheckBasedFenzong(IDstr1, j,
				index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HouseCode_CheckBasedFenzong(IDstr2, j,
				index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HouseCode_CheckBasedFenzong(IDstr3, j,
				index1, j), "ERR");

	}

	// 未在Redex中找到HouseCode_CheckBasedFenfu函数
	public static void testHouseCode_CheckBasedFenfu() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		int j = 12; // 定义长度

		// 定义IDstr
		String tr1 = new String("010101020202");
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("0AS000020202");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("000000000000");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		int[] index1 = new int[j];
		;
		// int [] index2 = {0,1,2,3,4,5};;
		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.HouseCode_CheckBasedFenfu(IDstr1, j, index1,
				j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HouseCode_CheckBasedFenfu(IDstr2, j, index1,
				j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HouseCode_CheckBasedFenfu(IDstr3, j, index1,
				j), "ERR");

	}

	// 33, JGJ/T 246-2012
	public static void testHouseCode_CheckUnitCode() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		int j = 4; // 定义长度

		// 定义IDstr
		String tr1 = new String("0000");
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("5858");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("9999");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		int[] index1 = new int[j];
		;
		// int [] index2 = {0,1,2,3,4,5};;
		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction
				.HouseCode_CheckUnitCode(IDstr1, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction
				.HouseCode_CheckUnitCode(IDstr2, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction
				.HouseCode_CheckUnitCode(IDstr3, j, index1, j), "OK");

	}

	// 33, JGJ/T 246-2012
	public static void testHouseCode_CheckTwelBitCode() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		int j = 12; // 定义长度

		// 定义IDstr
		String tr1 = new String("2010**898900");
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("2010*1898900");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("000000000000");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		int[] index1 = new int[j];
		;
		// int [] index2 = {0,1,2,3,4,5};;
		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.HouseCode_CheckTwelBitCode(IDstr1, j,
				index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HouseCode_CheckTwelBitCode(IDstr2, j,
				index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HouseCode_CheckTwelBitCode(IDstr3, j,
				index1, j), "OK");

	}

	// 217, WS 364.2-2011 公民身份证号
	public static void testMOD112() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		// int j=12;//定义长度

		// 定义IDstr
		String tr1 = new String("130322199004062618");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("140121199009309012");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		int[] index1 = new int[j];
		;
		// int [] index2 = {0,1,2,3,4,5};;
		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.MOD112(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MOD112(IDstr2, j, index1, j), "OK");

	}

	// 未在Redex中找到TwoByteSRMXSMYZ函数
	public static void testTwoByteSRMXSMYZ() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		// int j=12;//定义长度

		// 定义IDstr
		String tr1 = new String("SR");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("SS");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		int[] index1 = new int[j];
		;
		// int [] index2 = {0,1,2,3,4,5};;
		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.TwoByteSRMXSMYZ(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TwoByteSRMXSMYZ(IDstr2, j, index1, j), "ERR");

	}

	// 355, GB/T 13774-92
	public static void testWeaves355() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		// int j=12;//定义长度

		// 定义IDstr
		String tr1 = new String("11010100");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("11010101");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		int[] index1 = new int[j];
		;
		// int [] index2 = {0,1,2,3,4,5};;
		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.Weaves355(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Weaves355(IDstr2, j, index1, j), "ERR");

	}

	// 185, CY/T 58.2-2009
	public static void testThreeByteDecimalnt() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		// int j=12;//定义长度

		// 定义IDstr
		String tr1 = new String("000");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("999");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("001");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}
		String tr4 = new String("555");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		int[] index1 = new int[j];
		;
		// int [] index2 = {0,1,2,3,4,5};;
		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.ThreeByteDecimalnt(IDstr1, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ThreeByteDecimalnt(IDstr2, j, index1, j),
				"OK");
		System.out.print(i++);
		UnitTestEqual(RuleFunction.ThreeByteDecimalnt(IDstr3, j, index1, j),
				"OK");
		System.out.print(i++);
		UnitTestEqual(RuleFunction.ThreeByteDecimalnt(IDstr4, j, index1, j),
				"OK");

	}

	// 60, GB/T 23733-2009
	public static void testMusicCheck() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		// int j=12;//定义长度

		// 定义IDstr
		String tr1 = new String("T0345246801");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("T0345246802");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		int[] index1 = new int[j];
		;
		// int [] index2 = {0,1,2,3,4,5};;
		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.MusicCheck(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MusicCheck(IDstr2, j, index1, j), "ERR");

	}

	// 61, GB/T 23732-2009
	public static void testMOD163() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		// int j=12;//定义长度

		// 定义IDstr
		String tr1 = new String("0A920021223F3320");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("0A920021223F3321");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		int[] index1 = new int[j];
		;
		// int [] index2 = {0,1,2,3,4,5};;
		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.MOD163(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MOD163(IDstr2, j, index1, j), "ERR");

	}

	// 71, GB/T 21076-2007
	public static void testInternationalSecurities() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		// int j=12;//定义长度

		// 定义IDstr
		String tr1 = new String("US3838831051");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("US3838831052");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("JP3788600009");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		int[] index1 = new int[j];
		;
		// int [] index2 = {0,1,2,3,4,5};;
		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction
				.InternationalSecurities(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction
				.InternationalSecurities(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction
				.InternationalSecurities(IDstr3, j, index1, j), "OK");
	}

	// 192, YC/T 393-2011
	public static void testFiveByteDecimalnt() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		// int j=12;//定义长度

		// 定义IDstr
		String tr1 = new String("00001");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("00000");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("55555");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		int[] index1 = new int[j];
		;
		// int [] index2 = {0,1,2,3,4,5};;
		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.FiveByteDecimalnt(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FiveByteDecimalnt(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FiveByteDecimalnt(IDstr3, j, index1, j),
				"OK");
	}

	// 232, SB/T 10639-2011
	public static void testEgg232() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		// int j=12;//定义长度

		// 定义IDstr
		String tr1 = new String("111");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("118");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("932");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		int[] index1 = new int[j];
		;
		// int [] index2 = {0,1,2,3,4,5};;
		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.Egg232(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Egg232(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Egg232(IDstr3, j, index1, j), "OK");
	}

	// 163, GB 18240.6-2004分类与编码通用术语105——机器编码的结构和表示形式
	public static void testDeviceMOD163() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		// int j=12;//定义长度

		// 定义IDstr
		String tr1 = new String("789021234561");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("789021234562");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		int[] index1 = new int[j];
		;
		// int [] index2 = {0,1,2,3,4,5};;
		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.DeviceMOD163(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.DeviceMOD163(IDstr2, j, index1, j), "ERR");

	}

	// 185, CY/T 58.2-2009
	public static void testMrpCheck() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		// int j=12;//定义长度

		// 定义IDstr
		String tr1 = new String("1160634520086296");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("1160634520086295");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		int[] index1 = new int[j];
		;
		// int [] index2 = {0,1,2,3,4,5};;
		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.MrpCheck(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MrpCheck(IDstr2, j, index1, j), "ERR");

	}

	// 58, GB/T 23833-2009_1-4
	public static void testGraiSerialNo() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		// int j=12;//定义长度

		// 定义IDstr
		String tr1 = new String("0123");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("0123");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		int[] index1 = { 3, 1, -1 };

		int[] index2 = { 1, 2, -1 };

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GraiSerialNo(IDstr1, j, index1, 3), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GraiSerialNo(IDstr2, j, index2, 3), "ERR");

	}

	// 33, JGJ/T 246-2012
	public static void testHouseCode_CheckCode() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		int j = 26;// 定义长度
		// 定义IDstr
		String tr1 = new String("1101010102006005000109*001");
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}
		int[] index1 = new int[j];
		;
		// int [] index2 = {0,1,2,3,4,5};;
		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.HouseCode_CheckCode(IDstr1, j, index1, j),
				"OK");
	}

	// ////////////////////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////////////////////

	// 321, GB/T 17297-1998
	public static void testClimatic() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("11A");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("12E");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("42B");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("44C");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("45B");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.Climatic(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Climatic(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Climatic(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Climatic(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Climatic(IDstr5, j, index1, j), "OK");
	}

	// 70, GB/T 21379-2008 路段交通管理属性分类与编码
	public static void testCodeHighway() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("90");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("09");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("12");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("24");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("33");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.CodeHighway(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CodeHighway(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CodeHighway(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CodeHighway(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CodeHighway(IDstr5, j, index1, j), "OK");
	}

	// 206, YC/T 210.3-2006
	public static void testCodeTobacco() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("110");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("099");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("315");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("410");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("505");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.CodeTobacco(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CodeTobacco(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CodeTobacco(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CodeTobacco(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CodeTobacco(IDstr5, j, index1, j), "OK");
	}

	// 368, GB 918.2-1989
	public static void testNonMotorVehicle() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("800");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("702");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("723");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("700");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("710");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.NonMotorVehicle(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.NonMotorVehicle(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.NonMotorVehicle(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.NonMotorVehicle(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.NonMotorVehicle(IDstr5, j, index1, j), "OK");
	}

	// 365, GB/T 12402——2000
	public static void testEconomicCate() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("900");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("180");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("159");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("250");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("390");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.EconomicCate(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.EconomicCate(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.EconomicCate(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.EconomicCate(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.EconomicCate(IDstr5, j, index1, j), "OK");
	}

	// 281, GB/T 25071-2010
	public static void testJadejewelryMaterialclassif() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("010115");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("080000");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("080001");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("050699");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("060200");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.JadejewelryMaterialclassif(IDstr1, j,
				index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.JadejewelryMaterialclassif(IDstr2, j,
				index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.JadejewelryMaterialclassif(IDstr3, j,
				index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.JadejewelryMaterialclassif(IDstr4, j,
				index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.JadejewelryMaterialclassif(IDstr5, j,
				index1, j), "OK");
	}

	// 276, GB/T 27610-2011
	public static void testWasteproducts() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("1390");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("1499");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("1505");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("1007");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("1000");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.Wasteproducts(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Wasteproducts(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Wasteproducts(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Wasteproducts(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Wasteproducts(IDstr5, j, index1, j), "OK");
	}

	// 275, GB/T 27923-2011
	public static void testLogisticsoperation() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("1390");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("4800");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("4799");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("8110");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("8111");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.Logisticsoperation(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Logisticsoperation(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Logisticsoperation(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Logisticsoperation(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Logisticsoperation(IDstr5, j, index1, j),
				"OK");
	}

	// 270, GB/T 28921-2012
	public static void testNaturaldisaster() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("011100");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("039900");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("049999");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("030000");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("040600");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.Naturaldisaster(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Naturaldisaster(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Naturaldisaster(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Naturaldisaster(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Naturaldisaster(IDstr5, j, index1, j), "OK");
	}

	// 268, GB/T 4657-2008
	public static void testTheCenteralPartyCommitte() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("100");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("103");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("245");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("798");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("790");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.TheCenteralPartyCommitte(IDstr1, j, index1,
				j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TheCenteralPartyCommitte(IDstr2, j, index1,
				j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TheCenteralPartyCommitte(IDstr3, j, index1,
				j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TheCenteralPartyCommitte(IDstr4, j, index1,
				j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TheCenteralPartyCommitte(IDstr5, j, index1,
				j), "OK");
	}

	// 物流编码
	public static void testLogisticsCheck() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("000000");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("000001");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("011113");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("012348");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("112348");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.LogisticsCheck(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.LogisticsCheck(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.LogisticsCheck(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.LogisticsCheck(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.LogisticsCheck(IDstr5, j, index1, j), "OK");
	}

	// GB/T 23730.1-2009中国标准视听作品号 第1部分:视听作品标识符
	// public static void testMod36_37() {
	// JOptionPane.showMessageDialog(null, Thread.currentThread()
	// .getStackTrace()[1].getMethodName());
	// System.out.println(Thread.currentThread().getStackTrace()[1]
	// .getMethodName());
	//
	// String tr1 = new String("102");
	// int j = tr1.length();
	// char[] IDstr1 = new char[j];
	// for (int i = 0; i < j; i++) {
	// IDstr1[i] = tr1.charAt(i);
	// }
	//
	// String tr2 = new String("199");
	// char[] IDstr2 = new char[j];
	// for (int i = 0; i < j; i++) {
	// IDstr2[i] = tr2.charAt(i);
	// }
	//
	// String tr3 = new String("252");
	// char[] IDstr3 = new char[j];
	// for (int i = 0; i < j; i++) {
	// IDstr3[i] = tr3.charAt(i);
	// }
	//	
	// String tr4 = new String("435");
	// char[] IDstr4 = new char[j];
	// for (int i = 0; i < j; i++) {
	// IDstr4[i] = tr4.charAt(i);
	// }
	//
	// String tr5 = new String("520");
	// char[] IDstr5 = new char[j];
	// for (int i = 0; i < j; i++) {
	// IDstr5[i] = tr5.charAt(i);
	// }
	//	
	// int[] index1 = new int[j];
	//
	// for (int i = 0; i < j; i++) {
	// index1[i] = i;
	// }
	// System.out.print(i++);
	// UnitTestEqual(RuleFunction.Mod36_37(IDstr1, j, index1, j),
	// "OK");
	//
	// System.out.print(i++);
	// UnitTestEqual(RuleFunction.Mod36_37(IDstr2, j, index1, j),
	// "ERR");
	//
	// System.out.print(i++);
	// UnitTestEqual(RuleFunction.Mod36_37(IDstr3, j, index1, j),
	// "OK");
	//	
	// System.out.print(i++);
	// UnitTestEqual(RuleFunction.Mod36_37(IDstr4, j, index1, j),
	// "OK");
	//	
	// System.out.print(i++);
	// UnitTestEqual(RuleFunction.Mod36_37(IDstr5, j, index1, j),
	// "OK");
	// }

	// 362, GB/T 12407-2008
	public static void testPositionClass() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("102");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("199");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("252");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("435");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("520");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.PositionClass(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PositionClass(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PositionClass(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PositionClass(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PositionClass(IDstr5, j, index1, j), "OK");
	}

	// 381, GA/T 974.8-2011
	public static void testFireInfoStstion() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("09");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("11");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("60");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("65");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("90");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfoStstion(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfoStstion(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfoStstion(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfoStstion(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfoStstion(IDstr5, j, index1, j), "OK");
	}

	// 384, GA/T 974.71-2011
	public static void testFireInfoBuild() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("09");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("13");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("18");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("30");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("35");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfoBuild(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfoBuild(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfoBuild(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfoBuild(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfoBuild(IDstr5, j, index1, j), "OK");
	}

	// 194, YC/T 391-2011
//	public static void testTobaccomachinery() {
//		JOptionPane.showMessageDialog(null, Thread.currentThread()
//				.getStackTrace()[1].getMethodName());
//		System.out.println(Thread.currentThread().getStackTrace()[1]
//				.getMethodName());
//
//		String tr1 = new String("401");
//		int j = tr1.length();
//		char[] IDstr1 = new char[j];
//		for (int i = 0; i < j; i++) {
//			IDstr1[i] = tr1.charAt(i);
//		}
//
//		String tr2 = new String("475");
//		char[] IDstr2 = new char[j];
//		for (int i = 0; i < j; i++) {
//			IDstr2[i] = tr2.charAt(i);
//		}
//
//		String tr3 = new String("511");
//		char[] IDstr3 = new char[j];
//		for (int i = 0; i < j; i++) {
//			IDstr3[i] = tr3.charAt(i);
//		}
//
//		String tr4 = new String("620");
//		char[] IDstr4 = new char[j];
//		for (int i = 0; i < j; i++) {
//			IDstr4[i] = tr4.charAt(i);
//		}
//
//		String tr5 = new String("708");
//		char[] IDstr5 = new char[j];
//		for (int i = 0; i < j; i++) {
//			IDstr5[i] = tr5.charAt(i);
//		}
//
//		int[] index1 = new int[j];
//
//		for (int i = 0; i < j; i++) {
//			index1[i] = i;
//		}
//		System.out.print(i++);
//		UnitTestEqual(RuleFunction.Tobaccomachinery(IDstr1, j, index1, j), "OK");
//
//		System.out.print(i++);
//		UnitTestEqual(RuleFunction.Tobaccomachinery(IDstr2, j, index1, j),
//				"ERR");
//
//		System.out.print(i++);
//		UnitTestEqual(RuleFunction.Tobaccomachinery(IDstr3, j, index1, j), "OK");
//
//		System.out.print(i++);
//		UnitTestEqual(RuleFunction.Tobaccomachinery(IDstr4, j, index1, j), "OK");
//
//		System.out.print(i++);
//		UnitTestEqual(RuleFunction.Tobaccomachinery(IDstr5, j, index1, j), "OK");
//	}

	// 90, GB/T 16828-2007商品条码——参与方位编码与条码表示
	public static void testCheckCodebarcode() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("6901234567892");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("6901234567894");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("0123456789463");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("8520369741052");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("6548523697412");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.CheckCodebarcode(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CheckCodebarcode(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CheckCodebarcode(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CheckCodebarcode(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CheckCodebarcode(IDstr5, j, index1, j), "OK");
	}

	// 394, GA/T 974.61-2011
	public static void testExtinguishFire() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("109");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("122");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("413");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("314");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("429");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.ExtinguishFire(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ExtinguishFire(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ExtinguishFire(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ExtinguishFire(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ExtinguishFire(IDstr5, j, index1, j), "OK");
	}

	// 393, GA/T 974.62-2011
	public static void testFireInfoSmoke() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("10");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("12");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("19");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("22");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("90");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfoSmoke(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfoSmoke(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfoSmoke(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfoSmoke(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfoSmoke(IDstr5, j, index1, j), "OK");
	}

	// 91, GB/T 16772-1997中国煤炭编码系统
	public static void testCoalInterment() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("000000000000");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("022401650000");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("503949013032");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("053510012902");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("501010240231");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.CoalInterment(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CoalInterment(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CoalInterment(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CoalInterment(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CoalInterment(IDstr5, j, index1, j), "OK");
	}

	// 398, GA/T 974.58-2011
	public static void testFireInfowatersource() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("1119");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("1122");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("1110");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("1900");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("9000");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfowatersource(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfowatersource(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfowatersource(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfowatersource(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfowatersource(IDstr5, j, index1, j),
				"OK");
	}

	// 399, GA/T 974.57-2011
	public static void testFireInfowatersupply() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("19");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("22");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("32");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("40");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("10");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfowatersupply(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfowatersupply(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfowatersupply(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfowatersupply(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfowatersupply(IDstr5, j, index1, j),
				"OK");
	}

	// 402, GA/T 974.54-2011
	public static void testFireInfofailities() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("19");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("22");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("25");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("29");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("10");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfofailities(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfofailities(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfofailities(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfofailities(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfofailities(IDstr5, j, index1, j),
				"OK");
	}

	// 194
	public static void testtabaccoC() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("201");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("205");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("800");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("501");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("605");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.tabaccoC(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.tabaccoC(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.tabaccoC(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.tabaccoC(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.tabaccoC(IDstr5, j, index1, j), "OK");
	}

	// 80, GB/T 19632-2005 全国殡葬服务、设施和用品代码 有问题
	public static void testMOD9710() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("311001110010062");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("311001101030090");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("311001313010013");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("311001213030076");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("311001101030093");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.MOD9710(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MOD9710(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MOD9710(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MOD9710(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MOD9710(IDstr5, j, index1, j), "OK");
	}

	// 城市市政综合监管信息系统 单元网格划分与编码规则
	public static void testThreeByteDecimalnt1() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("000");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("300");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("999");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("45a");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("256");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.ThreeByteDecimalnt1(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ThreeByteDecimalnt1(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ThreeByteDecimalnt1(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ThreeByteDecimalnt1(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ThreeByteDecimalnt1(IDstr5, j, index1, j),
				"OK");
	}

	// 395, GA/T 974.60-2011
	public static void testFireInfomation() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("0332");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("0801");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("0215");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("0111");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("0313");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfomation(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfomation(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfomation(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfomation(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfomation(IDstr5, j, index1, j), "OK");
	}

	// 194 有问题
	public static void testTobaccoTech() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("44");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("45");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("49");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("50");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("00");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.TobaccoTech(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TobaccoTech(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TobaccoTech(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TobaccoTech(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TobaccoTech(IDstr5, j, index1, j), "OK");
	}

	// 80, GB/T 19632-2005 全国殡葬服务、设施和用品代码
	public static void testFuneralInterment() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("1170000");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("1170501");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("2040000");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("3029900");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("3990000");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.FuneralInterment(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FuneralInterment(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FuneralInterment(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FuneralInterment(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FuneralInterment(IDstr5, j, index1, j), "OK");
	}

	// 403, GA/T 974.53-2011
	public static void testFireInfocamp() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("10");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("13");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("19");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("29");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("90");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfocamp(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfocamp(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfocamp(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfocamp(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfocamp(IDstr5, j, index1, j), "OK");
	}

	// 188
	public static void testLittlecode() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("02");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("21");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("06");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.Littlecode(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Littlecode(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Littlecode(IDstr3, j, index1, j), "OK");
	}

	// 406, GA/T 974.50-2011
	public static void testFireInfotainrate() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("20");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("22");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("44");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfotainrate(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfotainrate(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfotainrate(IDstr3, j, index1, j), "OK");
	}

	// 409, GA/T 974.48-2011
	public static void testFireInfotainass() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("20");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("19");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("23");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfotainass(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfotainass(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfotainass(IDstr3, j, index1, j), "OK");
	}

	// 413, GA/T 974.44-2011
	public static void testFireInfo() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("14");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("19");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("90");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfo(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfo(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfo(IDstr3, j, index1, j), "OK");
	}

	// 10, YC/T 209.1-2006
	public static void testTabaccoMaterials() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("0408");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("0200");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("9999");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.TabaccoMaterials(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TabaccoMaterials(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TabaccoMaterials(IDstr3, j, index1, j), "OK");
	}

	// /////////////////////////////////////////////////////////////////////////

	// MedSupervise
	public static void testMedAppCode() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("89123456789078500694");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("89123456789078520694");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("88123456789078523094");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		int[] index1 = { 15 };

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MedAppCode(IDstr1, j, index1, 1), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MedAppCode(IDstr2, j, index1, 1), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MedAppCode(IDstr3, j, index1, 1), "ERR");
	}

	// 9, YC/T 209.2-2008
	// public static void testN14() {
	// JOptionPane.showMessageDialog(null, Thread.currentThread()
	// .getStackTrace()[1].getMethodName());
	// System.out.println(Thread.currentThread().getStackTrace()[1]
	// .getMethodName());
	//
	// String tr1 = new String("G69012345678971022140000000012");
	// int j = tr1.length();
	// char[] IDstr1 = new char[j];
	// for (int i = 0; i < j; i++) {
	// IDstr1[i] = tr1.charAt(i);
	// }
	//
	// int[] index1 = new int[j];
	// for(int i = 0; i < j; i++)
	// index1[i] = i;
	//
	// System.out.print(i++);
	// UnitTestEqual(RuleFunction.N14(IDstr1, j, index1, j), "ERR");
	//
	// }

	// 43, GB/T 28923.1-2012_1
	public static void testTwobytleCode06() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		int j = 2; // 定义长度
		int[] index1 = new int[j];
		;
		// int [] index2 = {0,1,2,3,4,5};;
		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		// 定义IDstr
		String tr1 = new String("06");
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("99");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("00");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.TwobytleCode07(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TwobytleCode07(IDstr2, j, index1, j), "OK");
		System.out.print(i++);
		UnitTestEqual(RuleFunction.TwobytleCode07(IDstr3, j, index1, j), "OK");
	}

	// 466, GA/T 556.5-2007
	public static void testFourByteDecimalnt() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("1111");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("0000");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("9999");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.FourByteDecimalnt(IDstr1, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FourByteDecimalnt(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FourByteDecimalnt(IDstr3, j, index1, j),
				"OK");
	}

	// ////////////////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////
	// 266, GB/T 4761-2008
	public static void testFamilyRelationship() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("8");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("98");
		int j1 = tr2.length();
		char[] IDstr2 = new char[j1];
		for (int i = 0; i < j1; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("108");
		int j2 = tr3.length();
		char[] IDstr3 = new char[j2];
		for (int i = 0; i < j2; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("1400");
		int j3 = tr4.length();
		char[] IDstr4 = new char[j3];
		for (int i = 0; i < j3; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("9712");
		int j4 = tr5.length();
		char[] IDstr5 = new char[j4];
		for (int i = 0; i < j4; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];
		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		int[] index2 = new int[j1];
		for (int i = 0; i < j1; i++) {
			index2[i] = i;
		}

		int[] index3 = new int[j2];
		for (int i = 0; i < j2; i++) {
			index3[i] = i;
		}

		int[] index4 = new int[j3];
		for (int i = 0; i < j3; i++) {
			index4[i] = i;
		}

		int[] index5 = new int[j4];
		for (int i = 0; i < j4; i++) {
			index5[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FamilyRelationship(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FamilyRelationship(IDstr2, j1, index2, j1),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FamilyRelationship(IDstr3, j2, index3, j2),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FamilyRelationship(IDstr4, j3, index4, j3),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FamilyRelationship(IDstr5, j4, index5, j4),
				"OK");
	}

	// 428, GA/T 974.30-2011
	public static void testFireFightrescuePlan() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("00000");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("01400");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("03800");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("08900");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("07200");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireFightrescuePlan(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireFightrescuePlan(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireFightrescuePlan(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireFightrescuePlan(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireFightrescuePlan(IDstr5, j, index1, j),
				"OK");
	}

	// 430, GA/T 974.29-2011
	public static void testFirealarm() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("09");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("12");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("15");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("22");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("29");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.Firealarm(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Firealarm(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Firealarm(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Firealarm(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Firealarm(IDstr5, j, index1, j), "OK");
	}

	// 436, GA/T 974.23-2011

	public static void testFireautomaticSystem() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("29");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("30");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("10");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("45");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("40");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireautomaticSystem(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireautomaticSystem(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireautomaticSystem(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireautomaticSystem(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireautomaticSystem(IDstr5, j, index1, j),
				"OK");
	}

	// 437, GA/T 974.22-2011
	public static void testFirelocation() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("09");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("10");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("11");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("20");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("21");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.Firelocation(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Firelocation(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Firelocation(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Firelocation(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Firelocation(IDstr5, j, index1, j), "OK");
	}

	// 442, GA/T 974.18-2011
	public static void testFireconstructionlicence() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("09");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("10");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("14");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("90");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("19");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction
				.Fireconstructionlicence(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction
				.Fireconstructionlicence(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction
				.Fireconstructionlicence(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction
				.Fireconstructionlicence(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction
				.Fireconstructionlicence(IDstr5, j, index1, j), "OK");
	}

	// 444, GA/T 974.16-2011
	public static void testFiresupervisionenforcement() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("0999");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("1000");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("4099");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("9999");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("3003");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.Firesupervisionenforcement(IDstr1, j,
				index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Firesupervisionenforcement(IDstr2, j,
				index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Firesupervisionenforcement(IDstr3, j,
				index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Firesupervisionenforcement(IDstr4, j,
				index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Firesupervisionenforcement(IDstr5, j,
				index1, j), "OK");
	}

	// 451, GA/T 974.1-2011
	public static void testFireexpert() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("9900");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("0014");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("0199");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("0407");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("0901");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.Fireexpert(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Fireexpert(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Fireexpert(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Fireexpert(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Fireexpert(IDstr5, j, index1, j), "OK");
	}

	// 453, GA/T 972-2011
	public static void testChemicalrisk() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("099");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("106");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("250");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("399");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("398");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.Chemicalrisk(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Chemicalrisk(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Chemicalrisk(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Chemicalrisk(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Chemicalrisk(IDstr5, j, index1, j), "OK");
	}

	// 456, GA/T 867.1-2010
	public static void testCasecharacer() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("000000");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("100000");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("110001");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("110000");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("990000");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.Casecharacer(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Casecharacer(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Casecharacer(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Casecharacer(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Casecharacer(IDstr5, j, index1, j), "OK");
	}

	// 460, GA/T 852.5-2009
	public static void testRexreationMatetial() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("99");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("92");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("35");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("22");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("09");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.RexreationMatetial(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RexreationMatetial(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RexreationMatetial(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RexreationMatetial(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RexreationMatetial(IDstr5, j, index1, j),
				"OK");
	}

	// 462, GA/T 852.10-2009
	public static void testStatuPractitioner() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("99");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("50");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("19");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("10");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("09");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.StatuPractitioner(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.StatuPractitioner(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.StatuPractitioner(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.StatuPractitioner(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.StatuPractitioner(IDstr5, j, index1, j),
				"OK");
	}

	// 463, GA/T 852.1-2009
	public static void testRecreationplace() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("J01");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("H01");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("H99");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("J10");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("J99");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.Recreationplace(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Recreationplace(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Recreationplace(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Recreationplace(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Recreationplace(IDstr5, j, index1, j), "OK");
	}

	// 464, GA/T 760.1~760.12-2008
	public static void testPublicSecutity() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("999");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("115");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("125");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("140");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("149");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.PublicSecutity(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PublicSecutity(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PublicSecutity(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PublicSecutity(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PublicSecutity(IDstr5, j, index1, j), "OK");
	}

	// 465, GA/T 615.3-2006
	public static void testBorderinfo3() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("0500");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("0404");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("0505");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("7900");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("2600");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.Borderinfo3(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Borderinfo3(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Borderinfo3(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Borderinfo3(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Borderinfo3(IDstr5, j, index1, j), "OK");
	}

	// 465, GA/T 615.4-2006
	public static void testBorderinfo4() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("09");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("11");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("22");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("35");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("99");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.Borderinfo4(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Borderinfo4(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Borderinfo4(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Borderinfo4(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Borderinfo4(IDstr5, j, index1, j), "OK");
	}

	// 465, GA/T 615.5-2006
	public static void testBorderinfo5() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("310");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("249");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("218");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("199");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("201");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.Borderinfo5(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Borderinfo5(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Borderinfo5(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Borderinfo5(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Borderinfo5(IDstr5, j, index1, j), "OK");
	}

	// 465, GA/T 615.6-2006
	public static void testBorderinfo6() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("900");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("107");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("250");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("401");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("100");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.Borderinfo6(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Borderinfo6(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Borderinfo6(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Borderinfo6(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Borderinfo6(IDstr5, j, index1, j), "OK");
	}

	// 466, GA/T 556.4-2007
	public static void testFianceManage() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("40");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("56");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("71");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("85");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("10");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.FianceManage(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FianceManage(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FianceManage(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FianceManage(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FianceManage(IDstr5, j, index1, j), "OK");
	}

	// 504, GA 658.1-2006
	public static void testSixByteDecimalnt() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("000000");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("999999");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("250000");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("A00000");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("ABCDEF");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.SixByteDecimalnt(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.SixByteDecimalnt(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.SixByteDecimalnt(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.SixByteDecimalnt(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.SixByteDecimalnt(IDstr5, j, index1, j), "OK");
	}

	// 503, GA 690.2-2007
	public static void testExplosiveCivilian() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("00");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("01");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("06");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("99");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("22");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.ExplosiveCivilian(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ExplosiveCivilian(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ExplosiveCivilian(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ExplosiveCivilian(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ExplosiveCivilian(IDstr5, j, index1, j),
				"OK");
	}

	// 467, GA/T 556.3-2005
	public static void testFinanceSecManageInfo() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("00");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("01");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("09");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("19");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("22");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.FinanceSecManageInfo(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FinanceSecManageInfo(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FinanceSecManageInfo(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FinanceSecManageInfo(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FinanceSecManageInfo(IDstr5, j, index1, j),
				"OK");
	}

	// 219, WS 364.16-2011 宫内节育器种类代码
	public static void testOneTO08() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("00");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("01");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("08");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("99");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("22");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO08(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO08(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO08(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO08(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO08(IDstr5, j, index1, j), "OK");
	}

	// 64, GB/T 22970-2010 纺织面料代码
	public static void testOneTO09() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("00");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("01");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("09");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("99");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("22");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO09(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO09(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO09(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO09(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO09(IDstr5, j, index1, j), "OK");
	}

	// 68, GB/T 21394-2008
	public static void testOneTO05() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("00");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("01");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("05");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("99");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("22");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO05(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO05(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO05(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO05(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO05(IDstr5, j, index1, j), "OK");
	}

	// 221, WS 364.14-2011 监督机构科室代码
	public static void testOneTO07() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("00");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("01");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("07");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("99");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("22");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO07(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO07(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO07(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO07(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO07(IDstr5, j, index1, j), "OK");
	}

	// 213, WS 364.6-2011 妇科及乳腺不适症症状代码
	public static void testOneTO15() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("00");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("01");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("15");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("99");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("22");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO15(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO15(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO15(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO15(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO15(IDstr5, j, index1, j), "OK");
	}

	// 213, WS 364.6-2011 伤害发生原因代码
	public static void testOneTO13() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("00");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("01");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("13");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("99");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("22");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO13(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO13(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO13(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO13(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO13(IDstr5, j, index1, j), "OK");
	}

	// 213, WS 364.6-2011 精神症状代码
	public static void testOneTO11() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("00");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("01");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("11");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("99");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("22");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO11(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO11(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO11(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO11(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO11(IDstr5, j, index1, j), "OK");
	}

	// 215, WS 364.4-2011 既往常见疾病种类代码
	public static void testOneTO14() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("00");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("01");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("14");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("99");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("22");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO14(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO14(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO14(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO14(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO14(IDstr5, j, index1, j), "OK");
	}

	// 225, WS 364.10-2011 孕产妇死亡原因分类代码
	public static void testOneTO39() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("00");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("01");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("39");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("99");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("22");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO39(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO39(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO39(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO39(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO39(IDstr5, j, index1, j), "OK");
	}

	// 225, WS 364.10-2011 胎方位代码
	public static void testOneTO22() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("00");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("01");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("22");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("99");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("25");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO22(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO22(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO22(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO22(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO22(IDstr5, j, index1, j), "OK");
	}

	// 224, WS 364.11-2011 劳动能力评定分级代码
	public static void testOneTO10() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("00");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("01");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("10");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("99");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("25");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO10(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO10(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO10(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO10(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO10(IDstr5, j, index1, j), "OK");
	}

	// 225, WS 364.10-2011 出生缺陷类别代码
	public static void testOneTO29() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("00");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("01");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("29");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("99");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("25");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO29(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO29(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO29(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO29(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO29(IDstr5, j, index1, j), "OK");
	}

	// 70, GB/T 21379-2008 重点单位交通管理属性分类与编码
	public static void testOneTO21() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("00");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("01");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("21");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("99");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("25");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO21(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO21(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO21(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO21(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO21(IDstr5, j, index1, j), "OK");
	}

	// 219, WS 364.16-2011 疫苗名称代码
	public static void testOneTO46() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("00");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("01");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("46");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("99");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("25");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO46(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO46(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO46(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO46(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO46(IDstr5, j, index1, j), "OK");
	}

	// 219, WS 364.16-2011 药物剂型代码
	public static void testOneTO72() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("00");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("01");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("72");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("99");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("90");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO72(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO72(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO72(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO72(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO72(IDstr5, j, index1, j), "OK");
	}

	// 606, GB/T 23730.2-2009
	public static void testMOD3736() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("11111111111111111");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("abcdefghijklmnop9");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("1234567890ertyuio");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("36951238421963852");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("65478932108521473");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.MOD3736(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MOD3736(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MOD3736(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MOD3736(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MOD3736(IDstr5, j, index1, j), "OK");
	}


	// 639, GB/T 14043——2005

	public static void testZeroTO14() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("00");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("14");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("33");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("29");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("42");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.ZeroTO14(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ZeroTO14(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ZeroTO14(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ZeroTO14(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ZeroTO14(IDstr5, j, index1, j), "OK");
	}

	// 654, YC/T 191-2005
	public static void testZeroTO24() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("00");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("24");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("25");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("15");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("99");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.ZeroTO24(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ZeroTO24(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ZeroTO24(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ZeroTO24(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ZeroTO24(IDstr5, j, index1, j), "OK");
	}

	// 654, YC/T 191-2005
	public static void testZeroTO60() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("00");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("60");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("75");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("15");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("99");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.ZeroTO60(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ZeroTO60(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ZeroTO60(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ZeroTO60(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ZeroTO60(IDstr5, j, index1, j), "OK");
	}

	// 664, GB/T 18283-2008 包含价格结构三
	public static void testCheck4BitBarCode() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("52345");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("00000");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("98745");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("74125");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("69999");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.Check4BitBarCode(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Check4BitBarCode(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Check4BitBarCode(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Check4BitBarCode(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Check4BitBarCode(IDstr5, j, index1, j), "OK");
	}

	// 681, GB/T 14156-2009
	public static void testFlavorSubstance() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("N001");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("N378");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("N003");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("I1111");
		int j1 = tr4.length();
		char[] IDstr4 = new char[j1];
		for (int i = 0; i < j1; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("A2087");
		char[] IDstr5 = new char[j1];
		for (int i = 0; i < j1; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		int[] index2 = new int[j1];

		for (int i = 0; i < j1; i++) {
			index2[i] = i;
		}
		/*
		System.out.print(i++);
		UnitTestEqual(RuleFunction.FlavorSubstance(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FlavorSubstance(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FlavorSubstance(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FlavorSubstance(IDstr4, j1, index2, j1),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FlavorSubstance(IDstr5, j1, index2, j1),
				"OK");
				*/
	}

	// 1176, JT/T 444-2001 运输工具实有数统计指标代码
	public static void testOneTO11and90() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("00");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("01");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("11");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("90");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("99");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO11and90(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO11and90(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO11and90(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO11and90(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO11and90(IDstr5, j, index1, j), "OK");
	}

	// 1176, JT/T 444-2001 运输工具实有数统计指标代码
	public static void testHighwayTransportationB9() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("00");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("10");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("19");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("30");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("25");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction
				.HighwayTransportationB9(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction
				.HighwayTransportationB9(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction
				.HighwayTransportationB9(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction
				.HighwayTransportationB9(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction
				.HighwayTransportationB9(IDstr5, j, index1, j), "OK");
	}

	// 1176, JT/T 444-2001 公路货运量统计指标代码
	public static void testHighwayTransportationC3() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("0000");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("1500");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("0810");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("1520");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("1610");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction
				.HighwayTransportationC3(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction
				.HighwayTransportationC3(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction
				.HighwayTransportationC3(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction
				.HighwayTransportationC3(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction
				.HighwayTransportationC3(IDstr5, j, index1, j), "OK");
	}

	// 1179, JT/T 430-2000 港口收费信息分类及代码
	public static void testPorttariff() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("00");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("50");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("32");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("34");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("63");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.Porttariff(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Porttariff(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Porttariff(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Porttariff(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Porttariff(IDstr5, j, index1, j), "OK");
	}

	// 1179, JT/T 430-2000 外贸进出口货物港务费代码
	public static void testPorttariff4() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("00");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("30");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("61");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("69");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("70");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.Porttariff4(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Porttariff4(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Porttariff4(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Porttariff4(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Porttariff4(IDstr5, j, index1, j), "OK");
	}

	// 1179, JT/T 430-2000 外贸进出口货物装卸费代码
	public static void testPorttariff10() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("110");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("200");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("122");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("320");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("100");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.Porttariff10(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Porttariff10(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Porttariff10(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Porttariff10(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Porttariff10(IDstr5, j, index1, j), "OK");
	}

	// 1592, GA 24.7 ——2005 //
	public static void testOneoTO24() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("00");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("01");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("24");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("99");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("25");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneoTO24(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneoTO24(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneoTO24(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneoTO24(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneoTO24(IDstr5, j, index1, j), "OK");
	}

	// 910, DL/T 700.2-1999第二部分 机电产品 之电力整流器
	public static void testOneTO17() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("00");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("01");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("17");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("99");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("15");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO17(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO17(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO17(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO17(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO17(IDstr5, j, index1, j), "OK");
	}

	// 216, WS 364.3-2011 传染病患者职业代码
	public static void testOneTO17NO99() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("00");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("01");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("17");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("99");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("15");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO17NO99(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO17NO99(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO17NO99(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO17NO99(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO17NO99(IDstr5, j, index1, j), "OK");
	}

	// 910, DL/T 700.2-1999第二部分 机电产品 之电力整流器
	public static void testOneTO12No99() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("00");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("01");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("12");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("99");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("15");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO12No99(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO12No99(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO12No99(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO12No99(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO12No99(IDstr5, j, index1, j), "OK");
	}

	// 910, DL/T 700.2-1999第二部分 机电产品 之减速器
	public static void testOneTO13No99() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("00");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("01");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("13");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("99");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("10");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO13No99(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO13No99(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO13No99(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO13No99(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO13No99(IDstr5, j, index1, j), "OK");
	}

	// 910, DL/T 700.2-1999第二部分 机电产品 之电线
	public static void testTable35() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("a012345678");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("0145678923");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("0245678913");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("039875623");
		int j1 = tr4.length();
		char[] IDstr4 = new char[j1];
		for (int i = 0; i < j1; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("041234567");
		char[] IDstr5 = new char[j1];
		for (int i = 0; i < j1; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		int[] index2 = new int[j1];

		for (int i = 0; i < j1; i++) {
			index2[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Table35(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Table35(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Table35(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Table35(IDstr4, j1, index2, j1), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Table35(IDstr5, j1, index2, j1), "OK");
	}

	// 910, DL/T 700.2-1999第二部分 机电产品 之电子计算机及外部设备
	public static void testOneTO03() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("00");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("01");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("03");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("99");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("10");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO03(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO03(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO03(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO03(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO03(IDstr5, j, index1, j), "OK");
	}

	// 908, DL/T 700.3-1999
	public static void testOneTO42No99() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("00");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("01");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("03");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("99");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("10");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO42No99(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO42No99(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO42No99(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO42No99(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO42No99(IDstr5, j, index1, j), "OK");
	}

	// 14, LS/T 1712-2004
	public static void testFoodTrade() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("120700");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("141400");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("000000");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("231411");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("210111");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.FoodTrade(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FoodTrade(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FoodTrade(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FoodTrade(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FoodTrade(IDstr5, j, index1, j), "OK");
	}

	// 18, LS/T 1708.2-2004
	public static void testFoodEconomy() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("11012");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("12099");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("11111");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("15000");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("11000");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.FoodEconomy(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FoodEconomy(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FoodEconomy(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FoodEconomy(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FoodEconomy(IDstr5, j, index1, j), "OK");
	}

	// 16, LS/T 1710-2004

	public static void testGainStoreHouse() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("11160000");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("13060100");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("11111000");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("44030000");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("43010200");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.GainStoreHouse(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GainStoreHouse(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GainStoreHouse(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GainStoreHouse(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GainStoreHouse(IDstr5, j, index1, j), "OK");
	}

	// 17, LS/T 1709-2004
	public static void testGainsDiseases() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("11350");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("23124");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("11111");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("23111");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("23115");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.GainsDiseases(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GainsDiseases(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GainsDiseases(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GainsDiseases(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GainsDiseases(IDstr5, j, index1, j), "OK");
	}

	// 19, LS/T 1708.1-2004
	public static void testGainsProcess() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("12020000");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("13130300");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("11111000");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("16139900");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("16120100");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.GainsProcess(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GainsProcess(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GainsProcess(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GainsProcess(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GainsProcess(IDstr5, j, index1, j), "OK");
	}

	// 20, LS/T 1707.3-2004
	public static void testGainsEquipment() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("1121");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("1322");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("1620");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("0000");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("2100");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.GainsEquipment(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GainsEquipment(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GainsEquipment(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GainsEquipment(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GainsEquipment(IDstr5, j, index1, j), "OK");
	}

	// 21, LS/T 1707.2-2004
	public static void testGainsConditionDetection() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("210");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("445");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("550");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("000");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("200");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction
				.GainsConditionDetection(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction
				.GainsConditionDetection(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction
				.GainsConditionDetection(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction
				.GainsConditionDetection(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction
				.GainsConditionDetection(IDstr5, j, index1, j), "OK");
	}

	// 22, LS/T 1707.1-2004
	public static void testGrainsSmartWMS() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("121000");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("131320");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("131740");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("141300");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("000000");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.GrainsSmartWMS(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GrainsSmartWMS(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GrainsSmartWMS(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GrainsSmartWMS(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GrainsSmartWMS(IDstr5, j, index1, j), "OK");
	}

	// 26, LS/T 1704.2-2004
	public static void testGrainsQualityStandard() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("01040100");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("02990000");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("12345678");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("05000000");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("20400000");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.GrainsQualityStandard(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GrainsQualityStandard(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GrainsQualityStandard(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GrainsQualityStandard(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GrainsQualityStandard(IDstr5, j, index1, j),
				"OK");
	}

	// 32, JJF 1051-2009
	public static void testMeasuringInstrument() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("01360000");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("04000000");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("12062500");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("00000000");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("46900000");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.MeasuringInstrument(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MeasuringInstrument(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MeasuringInstrument(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MeasuringInstrument(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MeasuringInstrument(IDstr5, j, index1, j),
				"OK");
	}

	// 27, LS/T 1704.1-2004
	public static void testGrainsIndex() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("01010304");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("01010404");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("09000000");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("00000000");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("08010121");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.GrainsIndex(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GrainsIndex(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GrainsIndex(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GrainsIndex(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GrainsIndex(IDstr5, j, index1, j), "OK");
	}

	// 28, LS/T 1703-2004
	public static void testGrainsInformation() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("1121019");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("1111111");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("1134005");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("1126000");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("1172000");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.GrainsInformation(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GrainsInformation(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GrainsInformation(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GrainsInformation(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GrainsInformation(IDstr5, j, index1, j),
				"OK");
	}

	// 29, LS/T 1702-2004
	public static void testGrainsAttribute() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("990");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("123");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("124");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("110");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("100");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.GrainsAttribute(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GrainsAttribute(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GrainsAttribute(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GrainsAttribute(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GrainsAttribute(IDstr5, j, index1, j), "OK");
	}

	// 30, LS/T 1701-2004_1
	public static void testGrainEnterprise() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("99");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("10");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("11");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("19");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("30");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.GrainEnterprise(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GrainEnterprise(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GrainEnterprise(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GrainEnterprise(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GrainEnterprise(IDstr5, j, index1, j), "OK");
	}

	// 31, LS/T 1700-2004_1
	public static void testGrainAdministrative() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("449203");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("449900");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("449199");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("449101");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("111111");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.GrainAdministrative(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GrainAdministrative(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GrainAdministrative(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GrainAdministrative(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GrainAdministrative(IDstr5, j, index1, j),
				"OK");
	}

	// 34, JG/T 151-2003
	public static void testConstructionProducts() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("G2050");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("J1400");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("J3100");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("T1500");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("S4201");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.ConstructionProducts(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ConstructionProducts(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ConstructionProducts(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ConstructionProducts(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ConstructionProducts(IDstr5, j, index1, j),
				"OK");
	}

	// 44, GB/T 28532-2012
	public static void testCarrierIdentifier() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("a123A987654");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("XYZ741D123456");
		int j1 = tr2.length();
		char[] IDstr2 = new char[j1];
		for (int i = 0; i < j1; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("09OPQ654B852963");
		int j2 = tr3.length();
		char[] IDstr3 = new char[j2];
		for (int i = 0; i < j2; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("QWERTYU860C012478");
		int j3 = tr4.length();
		char[] IDstr4 = new char[j3];
		for (int i = 0; i < j3; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("MNBVCXZA360A369258");
		int j4 = tr5.length();
		char[] IDstr5 = new char[j4];
		for (int i = 0; i < j4; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		int[] index2 = new int[j1];

		for (int i = 0; i < j1; i++) {
			index2[i] = i;
		}

		int[] index3 = new int[j2];

		for (int i = 0; i < j2; i++) {
			index3[i] = i;
		}

		int[] index4 = new int[j3];

		for (int i = 0; i < j3; i++) {
			index4[i] = i;
		}

		int[] index5 = new int[j4];

		for (int i = 0; i < j4; i++) {
			index5[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CarrierIdentifier(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CarrierIdentifier(IDstr2, j1, index2, j1),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CarrierIdentifier(IDstr3, j2, index3, j2),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CarrierIdentifier(IDstr4, j3, index4, j3),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CarrierIdentifier(IDstr5, j4, index5, j4),
				"OK");
	}

	// 45, GB/T 28442-2012
	public static void testElectronicMap() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("1280");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("2360");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("2552");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("5960");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("9900");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.ElectronicMap(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ElectronicMap(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ElectronicMap(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ElectronicMap(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ElectronicMap(IDstr5, j, index1, j), "OK");
	}

	// 46, GB/T 28422-2012_3
	public static void testChineseCharRegex() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("4E094F9f");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("8abc8abc");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("9E9F9E9F");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("9f009f00");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("99009900");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.ChineseCharRegex(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ChineseCharRegex(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ChineseCharRegex(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ChineseCharRegex(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ChineseCharRegex(IDstr5, j, index1, j), "OK");
	}

	// 46, GB/T 28422-2012_6
	public static void testTwobytleWeekCode() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("ab");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("00");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("01");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("53");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("99");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.TwobytleWeekCode(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TwobytleWeekCode(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TwobytleWeekCode(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TwobytleWeekCode(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TwobytleWeekCode(IDstr5, j, index1, j), "OK");
	}

	// 56, GB/T 25529-2010_1
	public static void testGeographicInformation() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("11303");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("11501");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("16299");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("22203");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("34101");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.GeographicInformation(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GeographicInformation(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GeographicInformation(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GeographicInformation(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GeographicInformation(IDstr5, j, index1, j),
				"OK");
	}

	// 56, GB/T 25529-2010_2
	public static void testGeographicPropertyRegex() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("00000000001");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("000000000012");
		int j1 = tr2.length();
		char[] IDstr2 = new char[j1];
		for (int i = 0; i < j1; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("0000000000123");
		int j2 = tr3.length();
		char[] IDstr3 = new char[j2];
		for (int i = 0; i < j2; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("000000000012345");
		int j3 = tr4.length();
		char[] IDstr4 = new char[j3];
		for (int i = 0; i < j3; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("0000000000123456");
		int j4 = tr5.length();
		char[] IDstr5 = new char[j4];
		for (int i = 0; i < j4; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j - 10; i++) {
			index1[i] = i + 10;
		}

		int[] index2 = new int[j1];

		for (int i = 0; i < j1 - 10; i++) {
			index2[i] = i + 10;
		}

		int[] index3 = new int[j2];

		for (int i = 0; i < j2 - 10; i++) {
			index3[i] = i + 10;
		}

		int[] index4 = new int[j3];

		for (int i = 0; i < j3 - 10; i++) {
			index4[i] = i + 10;
		}

		int[] index5 = new int[j4];

		for (int i = 0; i < j4 - 10; i++) {
			index5[i] = i + 10;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction
				.GeographicPropertyRegex(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GeographicPropertyRegex(IDstr2, j1, index2,
				j1), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GeographicPropertyRegex(IDstr3, j2, index3,
				j2), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GeographicPropertyRegex(IDstr4, j3, index4,
				j3), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GeographicPropertyRegex(IDstr5, j4, index5,
				j4), "OK");
	}

	// 62, GB/T 23705-2009_3
	public static void testDigitRegex() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("0000000000000090098");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("0000000000000091900");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("0000000000000089856");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("0000000000000044478");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("0000000000000055534");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j - 14; i++) {
			index1[i] = i + 14;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.DigitRegex(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.DigitRegex(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.DigitRegex(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.DigitRegex(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.DigitRegex(IDstr5, j, index1, j), "OK");
	}

	// 64, GB/T 22970-2010 纺织面料代码
	public static void testTextileFabricNameCode() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("10015");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("30006");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("20019");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("20000");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("11111");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.TextileFabricNameCode(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TextileFabricNameCode(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TextileFabricNameCode(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TextileFabricNameCode(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TextileFabricNameCode(IDstr5, j, index1, j),
				"OK");
	}

	// 64, GB/T 22970-2010 纺织面料代码
	public static void testPropertiesMain() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("16");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("99");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("01");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("00");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("27");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.PropertiesMain(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PropertiesMain(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PropertiesMain(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PropertiesMain(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PropertiesMain(IDstr5, j, index1, j), "OK");
	}

	// 64, GB/T 22970-2010 纺织面料代码
	public static void testPropertiesFiberCharacteristics() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("16");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("99");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("01");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("00");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("23");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.PropertiesFiberCharacteristics(IDstr1, j,
				index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PropertiesFiberCharacteristics(IDstr2, j,
				index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PropertiesFiberCharacteristics(IDstr3, j,
				index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PropertiesFiberCharacteristics(IDstr4, j,
				index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PropertiesFiberCharacteristics(IDstr5, j,
				index1, j), "OK");
	}

	// 64, GB/T 22970-2010 纺织面料代码
	public static void testPropertiesMix() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("13");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("99");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("01");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("11");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("63");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.PropertiesMix(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PropertiesMix(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PropertiesMix(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PropertiesMix(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PropertiesMix(IDstr5, j, index1, j), "OK");
	}

	// 64, GB/T 22970-2010 纺织面料代码
	public static void testPropertiesFabric() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("00");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("99");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("01");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("11");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("63");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.PropertiesFabric(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PropertiesFabric(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PropertiesFabric(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PropertiesFabric(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PropertiesFabric(IDstr5, j, index1, j), "OK");
	}

	// 64, GB/T 22970-2010 纺织面料代码
	public static void testPropertiesDyeingandFinishing() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("00");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("99");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("01");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("11");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("24");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.PropertiesDyeingandFinishing(IDstr1, j,
				index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PropertiesDyeingandFinishing(IDstr2, j,
				index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PropertiesDyeingandFinishing(IDstr3, j,
				index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PropertiesDyeingandFinishing(IDstr4, j,
				index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PropertiesDyeingandFinishing(IDstr5, j,
				index1, j), "OK");
	}

	// 65, GB/T 22124.2-2010
	public static void testGeneralManufacturingProcess() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("E20400000");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("F90102000");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("L09900000");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("M00400000");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("R10101000");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.GeneralManufacturingProcess(IDstr1, j,
				index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GeneralManufacturingProcess(IDstr2, j,
				index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GeneralManufacturingProcess(IDstr3, j,
				index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GeneralManufacturingProcess(IDstr4, j,
				index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GeneralManufacturingProcess(IDstr5, j,
				index1, j), "OK");
	}

	// 68, GB/T 21394-2008
	public static void testTrafficInformation() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("0104");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("0499");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("0501");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("9999");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("0203");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j - 2];

		for (int i = 0; i < j - 2; i++) {
			index1[i] = i + 2;
		}
		System.out.print(i++);
		UnitTestEqual(
				RuleFunction.TrafficInformation(IDstr1, j, index1, j - 2), "OK");

		System.out.print(i++);
		UnitTestEqual(
				RuleFunction.TrafficInformation(IDstr2, j, index1, j - 2),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(
				RuleFunction.TrafficInformation(IDstr3, j, index1, j - 2), "OK");

		System.out.print(i++);
		UnitTestEqual(
				RuleFunction.TrafficInformation(IDstr4, j, index1, j - 2), "OK");

		System.out.print(i++);
		UnitTestEqual(
				RuleFunction.TrafficInformation(IDstr5, j, index1, j - 2), "OK");
	}

	// 712, GB/T 7635.2-2002
	public static void testUntransportableProduct() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("51230");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("123456");
		int j1 = tr2.length();
		char[] IDstr2 = new char[j1];
		for (int i = 0; i < j1; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("662");
		int j2 = tr3.length();
		char[] IDstr3 = new char[j2];
		for (int i = 0; i < j2; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("8356");
		int j3 = tr4.length();
		char[] IDstr4 = new char[j3];
		for (int i = 0; i < j3; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("5");
		int j4 = tr5.length();
		char[] IDstr5 = new char[j4];
		for (int i = 0; i < j4; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		int[] index2 = new int[j1];

		for (int i = 0; i < j1; i++) {
			index2[i] = i;
		}

		int[] index3 = new int[j2];

		for (int i = 0; i < j2; i++) {
			index3[i] = i;
		}

		int[] index4 = new int[j3];

		for (int i = 0; i < j3; i++) {
			index4[i] = i;
		}

		int[] index5 = new int[j4];

		for (int i = 0; i < j4; i++) {
			index5[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction
				.GeographicPropertyRegex(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GeographicPropertyRegex(IDstr2, j1, index2,
				j1), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GeographicPropertyRegex(IDstr3, j2, index3,
				j2), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GeographicPropertyRegex(IDstr4, j3, index4,
				j3), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GeographicPropertyRegex(IDstr5, j4, index5,
				j4), "OK");
	}

	// 77, GB/T 20133-2006
	public static void testTrafficInformationCollection() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("0120");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("0615");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("0719");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("0842");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("9099");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.TrafficInformationCollection(IDstr1, j,
				index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TrafficInformationCollection(IDstr2, j,
				index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TrafficInformationCollection(IDstr3, j,
				index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TrafficInformationCollection(IDstr4, j,
				index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TrafficInformationCollection(IDstr5, j,
				index1, j), "OK");
	}

	// 204, YC/T 210.5-2006
	public static void testTobaccoLeafColor() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("01");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("00");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("20");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("06");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("07");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.TobaccoLeafColor(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TobaccoLeafColor(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TobaccoLeafColor(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TobaccoLeafColor(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TobaccoLeafColor(IDstr5, j, index1, j), "OK");
	}

	// 202, YC/T 256.2-2008
	public static void testTrafficOrganization() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("10");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("09");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("23");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("24");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("99");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.TrafficOrganization(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TrafficOrganization(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TrafficOrganization(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TrafficOrganization(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TrafficOrganization(IDstr5, j, index1, j),
				"OK");
	}

	// 207, YC/T 210.2-2006
	public static void testTobaccoLeafForm() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("503");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("700");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("101");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("203");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("990");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.TobaccoLeafForm(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TobaccoLeafForm(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TobaccoLeafForm(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TobaccoLeafForm(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TobaccoLeafForm(IDstr5, j, index1, j), "OK");
	}

	// 208, YC/T 210.1-2006
	public static void testTobaccoLeafClass() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("80100");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("20212");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("20110");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("20240");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("10001");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.TobaccoLeafClass(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TobaccoLeafClass(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TobaccoLeafClass(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TobaccoLeafClass(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TobaccoLeafClass(IDstr5, j, index1, j), "OK");
	}

	// 213, WS 364.6-2011 儿童大便性状代码
	public static void testChildrenExcrement() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("4");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("9");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("23");
		int j1 = tr3.length();

		char[] IDstr3 = new char[j1];
		for (int i = 0; i < j1; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("11");
		char[] IDstr4 = new char[j1];
		for (int i = 0; i < j1; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("31");
		char[] IDstr5 = new char[j1];
		for (int i = 0; i < j1; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		int[] index2 = new int[j1];

		for (int i = 0; i < j1; i++) {
			index2[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.ChildrenExcrement(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ChildrenExcrement(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ChildrenExcrement(IDstr3, j1, index2, j1),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ChildrenExcrement(IDstr4, j1, index2, j1),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ChildrenExcrement(IDstr5, j1, index2, j1),
				"OK");
	}

	// 214, WS 364.5-2011 饮食种类代码
	public static void testOneToEleven() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("00");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("01");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("11");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("12");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("09");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneToEleven(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneToEleven(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneToEleven(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneToEleven(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneToEleven(IDstr5, j, index1, j), "OK");
	}

	// 214, WS 364.5-2011 饮酒频率代码
	public static void testDrinkingFrequency() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("1");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("2");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("21");
		int j1 = tr3.length();
		char[] IDstr3 = new char[j1];
		for (int i = 0; i < j1; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("33");
		char[] IDstr4 = new char[j1];
		for (int i = 0; i < j1; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("4");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		int[] index2 = new int[j1];

		for (int i = 0; i < j1; i++) {
			index2[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.DrinkingFrequency(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.DrinkingFrequency(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.DrinkingFrequency(IDstr3, j1, index2, j1),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.DrinkingFrequency(IDstr4, j1, index2, j1),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.DrinkingFrequency(IDstr5, j, index1, j),
				"OK");
	}

	// 214, WS 364.5-2011 饮酒种类代码
	public static void testDrinkingClass() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("1");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("2");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("11");
		int j1 = tr3.length();
		char[] IDstr3 = new char[j1];
		for (int i = 0; i < j1; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("3");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("9");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		int[] index2 = new int[j1];

		for (int i = 0; i < j1; i++) {
			index2[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.DrinkingClass(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.DrinkingClass(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.DrinkingClass(IDstr3, j1, index2, j1), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.DrinkingClass(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.DrinkingClass(IDstr5, j, index1, j), "OK");
	}

	// 214, WS 364.5-2011 身体活动频率代码
	public static void testPhysicalActivityFrequency() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("1");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("2");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("23");
		int j1 = tr3.length();
		char[] IDstr3 = new char[j1];
		for (int i = 0; i < j1; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("32");
		char[] IDstr4 = new char[j1];
		for (int i = 0; i < j1; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("4");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		int[] index2 = new int[j1];

		for (int i = 0; i < j1; i++) {
			index2[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.PhysicalActivityFrequency(IDstr1, j, index1,
				j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PhysicalActivityFrequency(IDstr2, j, index1,
				j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PhysicalActivityFrequency(IDstr3, j1,
				index2, j1), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PhysicalActivityFrequency(IDstr4, j1,
				index2, j1), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PhysicalActivityFrequency(IDstr5, j, index1,
				j), "OK");
	}

	// 215, WS 364.4-2011 妊娠终止方式代码表
	public static void testTerminationofPregnancy() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("1");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("2");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("49");
		int j1 = tr3.length();
		char[] IDstr3 = new char[j1];
		for (int i = 0; i < j1; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("53");
		char[] IDstr4 = new char[j1];
		for (int i = 0; i < j1; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("4");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		int[] index2 = new int[j1];

		for (int i = 0; i < j1; i++) {
			index2[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(
				RuleFunction.TerminationofPregnancy(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(
				RuleFunction.TerminationofPregnancy(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TerminationofPregnancy(IDstr3, j1, index2,
				j1), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TerminationofPregnancy(IDstr4, j1, index2,
				j1), "OK");

		System.out.print(i++);
		UnitTestEqual(
				RuleFunction.TerminationofPregnancy(IDstr5, j, index1, j), "OK");
	}

	// 215, WS 364.4-2011 分娩方式代码
	public static void testModeofProduction() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("1");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("3");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("11");
		int j1 = tr3.length();
		char[] IDstr3 = new char[j1];
		for (int i = 0; i < j1; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("33");
		char[] IDstr4 = new char[j1];
		for (int i = 0; i < j1; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("9");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		int[] index2 = new int[j1];

		for (int i = 0; i < j1; i++) {
			index2[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.ModeofProduction(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ModeofProduction(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ModeofProduction(IDstr3, j1, index2, j1),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ModeofProduction(IDstr4, j1, index2, j1),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ModeofProduction(IDstr5, j, index1, j), "OK");
	}

	// 216, WS 364.3-2011 分娩地点类别代码
	public static void testDileveryPlace() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("7");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("6");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("11");
		int j1 = tr3.length();
		char[] IDstr3 = new char[j1];
		for (int i = 0; i < j1; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("12");
		char[] IDstr4 = new char[j1];
		for (int i = 0; i < j1; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("9");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		int[] index2 = new int[j1];

		for (int i = 0; i < j1; i++) {
			index2[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.DileveryPlace(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.DileveryPlace(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.DileveryPlace(IDstr3, j1, index2, j1), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.DileveryPlace(IDstr4, j1, index2, j1), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.DileveryPlace(IDstr5, j, index1, j), "OK");
	}

	// 218, WS 364.17-2011
	public static void testHealthSupervisionObject() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("03");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("0402");
		int j1 = tr2.length();
		char[] IDstr2 = new char[j1];
		for (int i = 0; i < j1; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("040202");
		int j2 = tr3.length();
		char[] IDstr3 = new char[j2];
		for (int i = 0; i < j2; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("069801");
		int j3 = tr4.length();
		char[] IDstr4 = new char[j3];
		for (int i = 0; i < j3; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("06980104");
		int j4 = tr5.length();
		char[] IDstr5 = new char[j4];
		for (int i = 0; i < j4; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		int[] index2 = new int[j1];

		for (int i = 0; i < j1; i++) {
			index2[i] = i;
		}

		int[] index3 = new int[j2];

		for (int i = 0; i < j2; i++) {
			index3[i] = i;
		}

		int[] index4 = new int[j3];

		for (int i = 0; i < j3; i++) {
			index4[i] = i;
		}

		int[] index5 = new int[j4];

		for (int i = 0; i < j4; i++) {
			index5[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction
				.HealthSupervisionObject(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HealthSupervisionObject(IDstr2, j1, index2,
				j1), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HealthSupervisionObject(IDstr3, j2, index3,
				j2), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HealthSupervisionObject(IDstr4, j3, index4,
				j3), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HealthSupervisionObject(IDstr5, j4, index5,
				j4), "OK");
	}

	// 219, WS 364.16-2011 交通工具代码
	public static void testCommunicationCode() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("1");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("2");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("11");
		int j1 = tr3.length();
		char[] IDstr3 = new char[j1];
		for (int i = 0; i < j1; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("12");
		char[] IDstr4 = new char[j1];
		for (int i = 0; i < j1; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("3");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		int[] index2 = new int[j1];

		for (int i = 0; i < j1; i++) {
			index2[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.CommunicationCode(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CommunicationCode(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CommunicationCode(IDstr3, j1, index2, j1),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CommunicationCode(IDstr4, j1, index2, j1),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CommunicationCode(IDstr5, j, index1, j),
				"OK");
	}

	// 220, WS 364.15-2011 卫生监督机构人员编制类别代码
	public static void testHygieneAgencyPersonnel() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("1");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("2");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("21");
		int j1 = tr3.length();
		char[] IDstr3 = new char[j1];
		for (int i = 0; i < j1; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("22");
		char[] IDstr4 = new char[j1];
		for (int i = 0; i < j1; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("23");
		char[] IDstr5 = new char[j1];
		for (int i = 0; i < j1; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		int[] index2 = new int[j1];

		for (int i = 0; i < j1; i++) {
			index2[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(
				RuleFunction.HygieneAgencyPersonnel(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(
				RuleFunction.HygieneAgencyPersonnel(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HygieneAgencyPersonnel(IDstr3, j1, index2,
				j1), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HygieneAgencyPersonnel(IDstr4, j1, index2,
				j1), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HygieneAgencyPersonnel(IDstr5, j1, index2,
				j1), "OK");
	}

	// 220, WS 364.15-2011 卫生监督机构职工类别代码
	public static void testWorkerHealthSupervision() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("1");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("2");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("11");
		int j1 = tr3.length();
		char[] IDstr3 = new char[j1];
		for (int i = 0; i < j1; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("12");
		char[] IDstr4 = new char[j1];
		for (int i = 0; i < j1; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("13");
		char[] IDstr5 = new char[j1];
		for (int i = 0; i < j1; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		int[] index2 = new int[j1];

		for (int i = 0; i < j1; i++) {
			index2[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction
				.WorkerHealthSupervision(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction
				.WorkerHealthSupervision(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.WorkerHealthSupervision(IDstr3, j1, index2,
				j1), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.WorkerHealthSupervision(IDstr4, j1, index2,
				j1), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.WorkerHealthSupervision(IDstr5, j1, index2,
				j1), "OK");
	}

	// 工商行政管理注册号编制规则
	public static void testBussManaCheck() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("110108000000016");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("012345678974185");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("111111111111111");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("564165165479854");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("110001120354585");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.BussManaCheck(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.BussManaCheck(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.BussManaCheck(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.BussManaCheck(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.BussManaCheck(IDstr5, j, index1, j), "OK");
	}

	// 281, GB/T 25071-2010
	public static void testJadejewelryClass() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("22109");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("22303");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("30799");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("33103");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("39900");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.JadejewelryClass(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.JadejewelryClass(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.JadejewelryClass(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.JadejewelryClass(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.JadejewelryClass(IDstr5, j, index1, j), "OK");
	}

	// 282, GB/T 25066-2010
	public static void testInformationSafe() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("B203");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("D301");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("G302");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("F102");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("G502");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.InformationSafe(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.InformationSafe(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.InformationSafe(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.InformationSafe(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.InformationSafe(IDstr5, j, index1, j), "OK");
	}

	// 280, GB/T 25344-2010
	public static void testCodeHighWayLine() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("0000");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("D301");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("9999");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("1234");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("G502");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CodeHighWayLine(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CodeHighWayLine(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CodeHighWayLine(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CodeHighWayLine(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CodeHighWayLine(IDstr5, j, index1, j), "OK");
	}

	// 284, GB/T 24450-2009
	public static void testgoalsocialeconomic() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("010199");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("010399");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("100806");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("101007");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("010101");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.goalsocialeconomic(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.goalsocialeconomic(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.goalsocialeconomic(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.goalsocialeconomic(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.goalsocialeconomic(IDstr5, j, index1, j),
				"OK");
	}

	// 285, GB/T 23831-2009
	public static void testLogisticsInf() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("1020204");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("5010399");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("50102");
		int j1 = tr3.length();
		char[] IDstr3 = new char[j1];
		for (int i = 0; i < j1; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("41102");
		char[] IDstr4 = new char[j1];
		for (int i = 0; i < j1; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("411");
		int j2 = tr5.length();
		char[] IDstr5 = new char[j2];
		for (int i = 0; i < j2; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		int[] index2 = new int[j1];

		for (int i = 0; i < j1; i++) {
			index2[i] = i;
		}

		int[] index3 = new int[j2];

		for (int i = 0; i < j2; i++) {
			index3[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.LogisticsInf(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.LogisticsInf(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.LogisticsInf(IDstr3, j1, index2, j1), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.LogisticsInf(IDstr4, j1, index2, j1), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.LogisticsInf(IDstr5, j2, index3, j2), "OK");
	}

	// 287, GB/T 23560-2009
	public static void testclothesclass() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("010104");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("010804");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("0610");
		int j1 = tr3.length();
		char[] IDstr3 = new char[j1];
		for (int i = 0; i < j1; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("0121");
		char[] IDstr4 = new char[j1];
		for (int i = 0; i < j1; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("02");
		int j2 = tr5.length();
		char[] IDstr5 = new char[j2];
		for (int i = 0; i < j2; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		int[] index2 = new int[j1];

		for (int i = 0; i < j1; i++) {
			index2[i] = i;
		}

		int[] index3 = new int[j2];

		for (int i = 0; i < j2; i++) {
			index3[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.clothesclass(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.clothesclass(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.clothesclass(IDstr3, j1, index2, j1), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.clothesclass(IDstr4, j1, index2, j1), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.clothesclass(IDstr5, j2, index3, j2), "OK");
	}

	// 288, GB/T 23559-2009
	public static void testClothesName() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("00063");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("00111");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("00001");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("00116");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("01010");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ClothesName(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ClothesName(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ClothesName(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ClothesName(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ClothesName(IDstr5, j, index1, j), "OK");
	}

	// 191, YY 0260-1997
	public static void testPharmacequipment() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("10010145");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("10092500");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("13130509");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("13459900");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("13990000");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Pharmacequipment(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Pharmacequipment(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Pharmacequipment(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Pharmacequipment(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Pharmacequipment(IDstr5, j, index1, j), "OK");
	}

	// 238, HY/T 094-2006
	public static void testCoastalAdminAreaId() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("130322");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("210283");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("320721");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("440303");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("440104");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CoastalAdminAreaId(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CoastalAdminAreaId(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CoastalAdminAreaId(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CoastalAdminAreaId(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CoastalAdminAreaId(IDstr5, j, index1, j),
				"OK");
	}

	// 312, GB/T 18366-2001
	public static void testInternationalShipCode() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("6921397");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("8138396");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("8400608");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("9042178");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("9107541");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.InternationalShipCode(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.InternationalShipCode(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.InternationalShipCode(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.InternationalShipCode(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.InternationalShipCode(IDstr5, j, index1, j),
				"OK");
	}

	// 239, HY/T 023-2010
	public static void testFirst2CharsofCoastalAdminAreaId() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("12");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("13");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("21");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("46");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("45");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.First2CharsofCoastalAdminAreaId(IDstr1, j,
				index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.First2CharsofCoastalAdminAreaId(IDstr2, j,
				index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.First2CharsofCoastalAdminAreaId(IDstr3, j,
				index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.First2CharsofCoastalAdminAreaId(IDstr4, j,
				index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.First2CharsofCoastalAdminAreaId(IDstr5, j,
				index1, j), "OK");
	}

	// 227, WS 218-2002
	public static void testWirtschaftsTypCode() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("10");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("15");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("90");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("22");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("09");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.WirtschaftsTypCode(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.WirtschaftsTypCode(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.WirtschaftsTypCode(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.WirtschaftsTypCode(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.WirtschaftsTypCode(IDstr5, j, index1, j),
				"OK");
	}

	// 225, WS 364.10-2011 传染病名称代码
	public static void testInfectiousDiseases() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("1000");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("1010");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("1020");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("2000");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("1111");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.InfectiousDiseases(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.InfectiousDiseases(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.InfectiousDiseases(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.InfectiousDiseases(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.InfectiousDiseases(IDstr5, j, index1, j),
				"OK");
	}

	// 239, HY/T 023-2010
	public static void testOceanStationCode() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("00");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("01");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("15");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("16");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("12");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OceanStationCode(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OceanStationCode(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OceanStationCode(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OceanStationCode(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OceanStationCode(IDstr5, j, index1, j), "OK");
	}

	// 309, GB/T 18521-2001
	public static void testGeographicalCode() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("1152");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("1219");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("1290");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("1292");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("2210");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GeographicalCode(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GeographicalCode(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GeographicalCode(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GeographicalCode(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GeographicalCode(IDstr5, j, index1, j), "OK");
	}

	// 305, GB/T 19378-2003
	public static void testPesticideFormulationCode() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("EA*");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("BRB*");
		int j1 = tr2.length();
		char[] IDstr2 = new char[j1];
		for (int i = 0; i < j1; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("ES");
		int j2 = tr3.length();
		char[] IDstr3 = new char[j2];
		for (int i = 0; i < j2; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("ABA*");
		char[] IDstr4 = new char[j1];
		for (int i = 0; i < j1; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("MF*");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		int[] index2 = new int[j1];

		for (int i = 0; i < j1; i++) {
			index2[i] = i;
		}

		int[] index3 = new int[j2];

		for (int i = 0; i < j2; i++) {
			index3[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PesticideFormulationCode(IDstr1, j, index1,
				j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PesticideFormulationCode(IDstr2, j1, index2,
				j1), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PesticideFormulationCode(IDstr3, j2, index3,
				j2), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PesticideFormulationCode(IDstr4, j1, index2,
				j1), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PesticideFormulationCode(IDstr5, j, index1,
				j), "OK");
	}

	// 306, GB/T 19234-2003
	public static void testPassengerCarCode() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("0000H11");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("0000W122");
		int j1 = tr2.length();
		char[] IDstr2 = new char[j1];
		for (int i = 0; i < j1; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("0000D9");
		int j2 = tr3.length();
		char[] IDstr3 = new char[j2];
		for (int i = 0; i < j2; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("0000H118");
		char[] IDstr4 = new char[j1];
		for (int i = 0; i < j1; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("0000W16");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		int[] index2 = new int[j1];

		for (int i = 0; i < j1; i++) {
			index2[i] = i;
		}

		int[] index3 = new int[j2];

		for (int i = 0; i < j2; i++) {
			index3[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PassengerCarCode(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PassengerCarCode(IDstr2, j1, index2, j1),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PassengerCarCode(IDstr3, j2, index3, j2),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PassengerCarCode(IDstr4, j1, index2, j1),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PassengerCarCode(IDstr5, j, index1, j), "OK");
	}

	// 1880, JT/T 415——2000场站类别
	public static void testRoadTransportation21() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("10");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("90");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("30");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("25");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("19");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RoadTransportation21(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RoadTransportation21(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RoadTransportation21(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RoadTransportation21(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RoadTransportation21(IDstr5, j, index1, j),
				"OK");
	}

	// 1162, MH/T 0018-1999
	public static void testCivilAviation() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("2755.0105");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("2760.0304");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("2760.0702");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("2760.1306");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("2760.1300");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CivilAviation(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CivilAviation(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CivilAviation(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CivilAviation(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CivilAviation(IDstr5, j, index1, j), "OK");
	}

	// 1880, JT/T 415——2000 运输服务业分类与代码
	public static void testRoadTransportation22() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("11");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("23");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("30");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("42");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("70");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RoadTransportation22(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RoadTransportation22(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RoadTransportation22(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RoadTransportation22(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RoadTransportation22(IDstr5, j, index1, j),
				"OK");
	}

	// 1880, JT/T 415——2000 经营业户的异动
	public static void testRoadTransportation32() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("11");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("16");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("30");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("19");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("90");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RoadTransportation32(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RoadTransportation32(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RoadTransportation32(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RoadTransportation32(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RoadTransportation32(IDstr5, j, index1, j),
				"OK");
	}

	// 1176, JT/T 444-2001 运输工具实有数统计指标代码
	public static void testRoadTransportation5() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("160");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("600");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("173");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("110");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("151");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RoadTransportation5(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RoadTransportation5(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RoadTransportation5(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RoadTransportation5(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RoadTransportation5(IDstr5, j, index1, j),
				"OK");
	}

	// 1880, JT/T 415——2000车辆类型
	public static void testRoadTransportation41() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("133");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("223");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("520");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("227");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("143");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RoadTransportation41(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RoadTransportation41(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RoadTransportation41(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RoadTransportation41(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RoadTransportation41(IDstr5, j, index1, j),
				"OK");
	}

	// JT/T 415——2000票据类别
	public static void testRoadTransportation50() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("10");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("20");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("26");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("90");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("27");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RoadTransportation50(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RoadTransportation50(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RoadTransportation50(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RoadTransportation50(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RoadTransportation50(IDstr5, j, index1, j),
				"OK");
	}

	// 1880, JT/T 415——2000证牌类别
	public static void testRoadTransportation53() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("603");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("701");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("100");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("790");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("290");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RoadTransportation53(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RoadTransportation53(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RoadTransportation53(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RoadTransportation53(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RoadTransportation53(IDstr5, j, index1, j),
				"OK");
	}

	// 1880, JT/T 415——2000 营运类别
	public static void testRoadTransportation63() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("09");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("10");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("13");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("20");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("24");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RoadTransportation63(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RoadTransportation63(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RoadTransportation63(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RoadTransportation63(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RoadTransportation63(IDstr5, j, index1, j),
				"OK");
	}

	// 712, GB/T 7635.2-2002
	public static void testPort() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("001");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("051");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("159");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("264");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("278");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Port(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Port(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Port(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Port(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Port(IDstr5, j, index1, j), "OK");
	}

	// 1880, JT/T 415——2000 货物类别
	public static void testRoadTransportation60() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("A104");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("A303");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("A159");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("B403");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("C308");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RoadTransportation60(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RoadTransportation60(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RoadTransportation60(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RoadTransportation60(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RoadTransportation60(IDstr5, j, index1, j),
				"OK");
	}

	// 1880, JT/T 415——2000集装箱箱型分类
	public static void testRoadTransportation64() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("09");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("11");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("30");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("32");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("40");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RoadTransportation64(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RoadTransportation64(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RoadTransportation64(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RoadTransportation64(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RoadTransportation64(IDstr5, j, index1, j),
				"OK");
	}

	// 1176, JT/T 444-2001 运输工具实有数统计指标代码
	public static void testHighwayTransportation4b1() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("09");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("11");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("30");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("29");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("20");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayTransportation4b1(IDstr1, j, index1,
				j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayTransportation4b1(IDstr2, j, index1,
				j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayTransportation4b1(IDstr3, j, index1,
				j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayTransportation4b1(IDstr4, j, index1,
				j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayTransportation4b1(IDstr5, j, index1,
				j), "OK");
	}

	// 1176, JT/T 444-2001 运输工具实有数统计指标代码
	public static void testHighwayTransportation4b7() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("200");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("620");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("131");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("100");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("111");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayTransportation4b7(IDstr1, j, index1,
				j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayTransportation4b7(IDstr2, j, index1,
				j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayTransportation4b7(IDstr3, j, index1,
				j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayTransportation4b7(IDstr4, j, index1,
				j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayTransportation4b7(IDstr5, j, index1,
				j), "OK");
	}

	// 1176, JT/T 444-2001 汽车燃料消耗量统计指标代码
	public static void testHighwayTransportation4b9() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("10");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("19");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("29");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("30");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("22");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayTransportation4b9(IDstr1, j, index1,
				j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayTransportation4b9(IDstr2, j, index1,
				j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayTransportation4b9(IDstr3, j, index1,
				j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayTransportation4b9(IDstr4, j, index1,
				j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayTransportation4b9(IDstr5, j, index1,
				j), "OK");
	}

	// 1179, JT/T 430-2000 航行国际航线船舶港口费代码
	public static void testPortTariff3() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("040A");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("010E");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("110A");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("0600");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("041A");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PortTariff3(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PortTariff3(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PortTariff3(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PortTariff3(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PortTariff3(IDstr5, j, index1, j), "OK");
	}

	// 1179, JT/T 430-2000 外贸进出口货物港务费代码
	public static void testPortTariff4() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("10");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("60");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("69");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("30");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("62");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PortTariff4(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PortTariff4(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PortTariff4(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PortTariff4(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PortTariff4(IDstr5, j, index1, j), "OK");
	}

	// 1179, JT/T 430-2000 外贸进出口货物装卸费代码
	public static void testPortTariff9() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("2030");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("429A");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("424C");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("5300");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("5329");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PortTariff9(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PortTariff9(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PortTariff9(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PortTariff9(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PortTariff9(IDstr5, j, index1, j), "OK");
	}

	// 1179, JT/T 430-2000 租用船舶、机械、设备和委托其他杂项作业费率代码
	public static void testPortTariff25() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("10300");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("21120");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("22320");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("21911");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("10210");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PortTariff25(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PortTariff25(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PortTariff25(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PortTariff25(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PortTariff25(IDstr5, j, index1, j), "OK");
	}

	// 1179, JT/T 430-2000 外贸进出口货物装卸费代码
	public static void testPortTariff10() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("100");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("320");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("122");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("111");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("300");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PortTariff10(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PortTariff10(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PortTariff10(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PortTariff10(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PortTariff10(IDstr5, j, index1, j), "OK");
	}

	// 1205, JB/T 5992.2-92
	public static void testMachinery2() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("01175");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("01299");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("01401");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("01465");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("02325");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Machinery2(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Machinery2(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Machinery2(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Machinery2(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Machinery2(IDstr5, j, index1, j), "OK");
	}

	// 1185, JT/T 307.4-1998
	public static void testHighwayMaintenance4() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("G324D00144");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("G318D00151");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("G209D00114");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("G213D00151");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("G219D00154");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayMaintenance4(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayMaintenance4(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayMaintenance4(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayMaintenance4(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayMaintenance4(IDstr5, j, index1, j),
				"OK");
	}

	// 1186, JT/T 307.3-1998
	public static void testHighwayMaintenance3() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("G208U00214");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("G330U00433");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("G105U00144");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("G381U00151");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("G312U00262");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayMaintenance3(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayMaintenance3(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayMaintenance3(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayMaintenance3(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayMaintenance3(IDstr5, j, index1, j),
				"OK");
	}

	// 1204, JB/T 5992.3-92
	public static void testMachinery3() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("16101");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("15255");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("14365");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("14220");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("11410");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Machinery3(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Machinery3(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Machinery3(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Machinery3(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Machinery3(IDstr5, j, index1, j), "OK");
	}

	// 1203, JB/T 5992.4-92
	public static void testMachinery4() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("21315");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("21505");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("22185");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("24780");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("27160");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Machinery4(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Machinery4(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Machinery4(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Machinery4(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Machinery4(IDstr5, j, index1, j), "OK");
	}

	// 1202, JB/T 5992.5-92
	public static void testMachinery5() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("31135");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("31111");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("31635");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("32565");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("35025");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Machinery5(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Machinery5(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Machinery5(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Machinery5(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Machinery5(IDstr5, j, index1, j), "OK");
	}

	// 1201, JB/T 5992.6-92
	public static void testMachinery6() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("41055");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("41240");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("42555");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("46220");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("46540");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Machinery6(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Machinery6(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Machinery6(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Machinery6(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Machinery6(IDstr5, j, index1, j), "OK");
	}

	// 1200, JB/T 5992.7-92
	public static void testMachinery7() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("51205");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("51320");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("51510");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("51599");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("51855");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Machinery7(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Machinery7(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Machinery7(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Machinery7(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Machinery7(IDstr5, j, index1, j), "OK");
	}

	// 1199, JB/T 5992.8-92
	public static void testMachinery8() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("61265");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("62350");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("63299");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("66350");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("66465");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Machinery8(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Machinery8(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Machinery8(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Machinery8(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Machinery8(IDstr5, j, index1, j), "OK");
	}

	// 1198, JB/T 5992.9-92
	public static void testMachinery9() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("82120");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("82161");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("82299");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("85201");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("85299");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Machinery9(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Machinery9(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Machinery9(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Machinery9(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Machinery9(IDstr5, j, index1, j), "OK");
	}

	// 1206, JB/T 5992.10-92
	public static void testMachinery10() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("91260");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("92135");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("92275");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("93385");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("94130");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Machinery10(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Machinery10(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Machinery10(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Machinery10(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Machinery10(IDstr5, j, index1, j), "OK");
	}

	// 1176, JT/T 444-2001 公路里程年底到达数统计指标代码
	public static void testHighwayTransportation4c6() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("10");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("23");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("31");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("90");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("39");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayTransportation4c6(IDstr1, j, index1,
				j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayTransportation4c6(IDstr2, j, index1,
				j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayTransportation4c6(IDstr3, j, index1,
				j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayTransportation4c6(IDstr4, j, index1,
				j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayTransportation4c6(IDstr5, j, index1,
				j), "OK");
	}

	// 1177, JT/T 438-2001
	public static void testWaterwayTransportation() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("052");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("113");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("155");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("110");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("200");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(
				RuleFunction.WaterwayTransportation(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(
				RuleFunction.WaterwayTransportation(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(
				RuleFunction.WaterwayTransportation(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(
				RuleFunction.WaterwayTransportation(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(
				RuleFunction.WaterwayTransportation(IDstr5, j, index1, j), "OK");
	}

	// 1176, JT/T 444-2001 运输工具实有数统计指标代码
	public static void testHighwayTransportation4b10() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("1");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("5");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("9");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("0");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("6");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayTransportation4b10(IDstr1, j, index1,
				j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayTransportation4b10(IDstr2, j, index1,
				j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayTransportation4b10(IDstr3, j, index1,
				j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayTransportation4b10(IDstr4, j, index1,
				j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayTransportation4b10(IDstr5, j, index1,
				j), "OK");
	}

	// 1176, JT/T 444-2001 公路运输主要统计指标代码
	public static void testHighwayTransportation() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("250");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("262");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("550");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("402");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("399");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayTransportation(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayTransportation(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayTransportation(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayTransportation(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayTransportation(IDstr5, j, index1, j),
				"OK");
	}

	// 1478, GA 520.1-2004
	public static void testSecurityAccounterments() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("A010501");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("A030201 ");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("B030302");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("B120202");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("B141702");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(
				RuleFunction.SecurityAccounterments(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(
				RuleFunction.SecurityAccounterments(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(
				RuleFunction.SecurityAccounterments(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(
				RuleFunction.SecurityAccounterments(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(
				RuleFunction.SecurityAccounterments(IDstr5, j, index1, j), "OK");
	}

	// 1151, QC/T 836-2010
	public static void testSpecialVehicle() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("M1Z");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("M2Z");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("M3Z");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("NYZ");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("NYJ");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.SpecialVehicle(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.SpecialVehicle(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.SpecialVehicle(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.SpecialVehicle(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.SpecialVehicle(IDstr5, j, index1, j), "OK");
	}

	// 917, CH/Z 9002-2007
	public static void testTwoOrThree() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("01");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("10");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("99");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("100");
		int j1 = tr4.length();
		char[] IDstr4 = new char[j1];
		for (int i = 0; i < j1; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("999");
		char[] IDstr5 = new char[j1];
		for (int i = 0; i < j1; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		int[] index2 = new int[j1];

		for (int i = 0; i < j1; i++) {
			index2[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TwoOrThree(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TwoOrThree(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TwoOrThree(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TwoOrThree(IDstr4, j1, index2, j1), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TwoOrThree(IDstr5, j1, index2, j1), "OK");
	}

	// 1179, JT/T 430-2000 外贸进出口货物装卸费代码
	public static void testCommodityName() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("A000");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("A999");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("0000");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("9999");
		int j1 = tr4.length();
		char[] IDstr4 = new char[j1];
		for (int i = 0; i < j1; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("1234");
		char[] IDstr5 = new char[j1];
		for (int i = 0; i < j1; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		int[] index2 = new int[j1];

		for (int i = 0; i < j1; i++) {
			index2[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CommodityName(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CommodityName(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CommodityName(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CommodityName(IDstr4, j1, index2, j1), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CommodityName(IDstr5, j1, index2, j1), "OK");
	}

	// 377, GB/T 12408——1990
	public static void testSocialWork() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("01");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("66");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("43");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("10");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("42");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.SocialWork(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.SocialWork(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.SocialWork(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.SocialWork(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.SocialWork(IDstr5, j, index1, j), "OK");
	}

	// 386, GA/T 974.69-2011
	public static void testFireInfoori() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("099");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("103");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("404");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("599");
		int j1 = tr4.length();
		char[] IDstr4 = new char[j1];
		for (int i = 0; i < j1; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("900");
		char[] IDstr5 = new char[j1];
		for (int i = 0; i < j1; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		int[] index2 = new int[j1];

		for (int i = 0; i < j1; i++) {
			index2[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfoori(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfoori(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfoori(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfoori(IDstr4, j1, index2, j1), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfoori(IDstr5, j1, index2, j1), "OK");
	}

	// 378, GB/T 12403-1990
	public static void testOfficialPostionCode() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("002P");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("011A");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("250B");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("317A");
		int j1 = tr4.length();
		char[] IDstr4 = new char[j1];
		for (int i = 0; i < j1; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("412Q");
		char[] IDstr5 = new char[j1];
		for (int i = 0; i < j1; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		int[] index2 = new int[j1];

		for (int i = 0; i < j1; i++) {
			index2[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OfficialPostionCode(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OfficialPostionCode(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OfficialPostionCode(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OfficialPostionCode(IDstr4, j1, index2, j1),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OfficialPostionCode(IDstr5, j1, index2, j1),
				"OK");
	}

	// GB/T 23733
	public static void testStandardMusicCheckCode() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("ISWCT1111111111");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("ISWCT0000000000");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("ISWCT0345246801");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("ISWCT3456789110");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("ISWCT4567787777");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(
				RuleFunction.StandardMusicCheckCode(IDstr1, j, index1, j), "OK");
		System.out.print(i++);
		UnitTestEqual(
				RuleFunction.StandardMusicCheckCode(IDstr2, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(
				RuleFunction.StandardMusicCheckCode(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(
				RuleFunction.StandardMusicCheckCode(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(
				RuleFunction.StandardMusicCheckCode(IDstr5, j, index1, j), "OK");
	}

	// GB/T 23730.2-2009
	public static void testMod36_37() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("000000000000000");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("123443443244443");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("ASDFGHRTOLJFKKK");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("345678ASJHKFNV7");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("SDHFIJE67834JFO1");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.MOD3736(IDstr1, j, index1, j), "OK");
		System.out.print(i++);
		UnitTestEqual(RuleFunction.MOD3736(IDstr2, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MOD3736(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MOD3736(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MOD3736(IDstr5, j, index1, j), "OK");
	}

	// 工商行政管理注册号编制规则
	public static void testBusinessAdminis() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("100000");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("130400");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("110000");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("130228");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("AA3BB2");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.BusinessAdminis(IDstr1, j, index1, j), "OK");
		System.out.print(i++);
		UnitTestEqual(RuleFunction.BusinessAdminis(IDstr2, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.BusinessAdminis(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.BusinessAdminis(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.BusinessAdminis(IDstr5, j, index1, j), "OK");
	}

	// 放射源编码规则 核元素国家代码
	public static void testNuclearelementNation() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("00");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("AB");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("GR");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("1A");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("98");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.NuclearelementNation(IDstr1, j, index1, j),
				"OK");
		System.out.print(i++);
		UnitTestEqual(RuleFunction.NuclearelementNation(IDstr2, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.NuclearelementNation(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.NuclearelementNation(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.NuclearelementNation(IDstr5, j, index1, j),
				"OK");
	}

	public static void testNuclearelements() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("PM");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("CF");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("NI");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("11");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("C5");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.Nuclearelements(IDstr1, j, index1, j), "OK");
		System.out.print(i++);
		UnitTestEqual(RuleFunction.Nuclearelements(IDstr2, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Nuclearelements(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Nuclearelements(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Nuclearelements(IDstr5, j, index1, j), "OK");
	}

	// 汽车标准件产品编号规则
	public static void testCarProduct() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("000000000000014");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("000000000000066");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("00000000000009Y");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("00000000000006A");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("00000000000005X");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j - 13; i++) {
			index1[i] = i + 13;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.CarProduct(IDstr1, j, index1, j), "OK");
		System.out.print(i++);
		UnitTestEqual(RuleFunction.CarProduct(IDstr2, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CarProduct(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CarProduct(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CarProduct(IDstr5, j, index1, j), "OK");
	}

	// 397, GB/T 22483-2008
	public static void testMountainRangeAndPeakName() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("A0200004");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("A0125004");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("B0030004");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("C0000022");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("D0070013");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.MountainRangeAndPeakName(IDstr1, j, index1,
				j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MountainRangeAndPeakName(IDstr2, j, index1,
				j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MountainRangeAndPeakName(IDstr3, j, index1,
				j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MountainRangeAndPeakName(IDstr4, j, index1,
				j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MountainRangeAndPeakName(IDstr5, j, index1,
				j), "OK");
	}

	// 298, GB/T 21373-2008
	public static void testIntellectualProperty() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("050300");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("030902");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("020802");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("010603");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("010100");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.IntellectualProperty(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.IntellectualProperty(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.IntellectualProperty(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.IntellectualProperty(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.IntellectualProperty(IDstr5, j, index1, j),
				"OK");
	}

	// 328, GB/T 16835-1997
	public static void testNormalAndShortCycleSpeciality() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("090699");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("050478");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("040420");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("020152");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("020199");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.NormalAndShortCycleSpeciality(IDstr1, j,
				index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.NormalAndShortCycleSpeciality(IDstr2, j,
				index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.NormalAndShortCycleSpeciality(IDstr3, j,
				index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.NormalAndShortCycleSpeciality(IDstr4, j,
				index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.NormalAndShortCycleSpeciality(IDstr5, j,
				index1, j), "OK");
	}

	// 337, GB/T 16558.2-2009
	public static void testMaintenanceSystemPTwo() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("AS-103-000-000");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("AS-203-078-000");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("BW-211-142-000");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("CG-502-608-000");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("ES-200-000-000");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.MaintenanceSystemPTwo(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MaintenanceSystemPTwo(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MaintenanceSystemPTwo(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MaintenanceSystemPTwo(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MaintenanceSystemPTwo(IDstr5, j, index1, j),
				"OK");
	}

	// 1880, JT/T 415——2000客票类别
	public static void testTwobyteCode06and90() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("00");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("01");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("90");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("06");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("07");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.TwobyteCode06and90(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TwobyteCode06and90(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TwobyteCode06and90(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TwobyteCode06and90(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TwobyteCode06and90(IDstr5, j, index1, j),
				"OK");
	}

	// 1179, JT/T 430-2000 航行国际航线船舶长江引航、移泊费率代码
	public static void testPortTariff26() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("WZB");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("BJ0");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("LW0");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("LC0");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("LZA");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.PortTariff26(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PortTariff26(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PortTariff26(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PortTariff26(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PortTariff26(IDstr5, j, index1, j), "OK");
	}

	// 1176, JT/T 444-2001 公路货运量统计指标代码
	public static void testHighwayTransportation4c3() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("1300");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("1700");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("0210");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("1000");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("1400");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayTransportation4c3(IDstr1, j, index1,
				j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayTransportation4c3(IDstr2, j, index1,
				j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayTransportation4c3(IDstr3, j, index1,
				j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayTransportation4c3(IDstr4, j, index1,
				j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayTransportation4c3(IDstr5, j, index1,
				j), "OK");
	}

	// 326, GB/T 16963-2010
	public static void testCountryRegionCode1() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("AW");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("MG");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("IS");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("AF");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("KE");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.CountryRegionCode1(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CountryRegionCode1(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CountryRegionCode1(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CountryRegionCode1(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CountryRegionCode1(IDstr5, j, index1, j),
				"OK");
	}

	// 784, DL/T 517-2012
	public static void testElectricPower() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("450702");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("460300");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("472421");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("310500");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("119900");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.ElectricPower(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ElectricPower(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ElectricPower(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ElectricPower(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ElectricPower(IDstr5, j, index1, j), "OK");
	}

	// 785, DL/T 510-2010
	public static void testPowerGrid() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("111");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("210");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("299");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("306");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("119");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerGrid(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerGrid(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerGrid(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerGrid(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerGrid(IDstr5, j, index1, j), "OK");
	}

	// 787, DL/T 495-2012
	public static void testElectricPowerIndustry() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("11");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("90");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("49");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("74");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("88");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.ElectricPowerIndustry(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ElectricPowerIndustry(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ElectricPowerIndustry(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ElectricPowerIndustry(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ElectricPowerIndustry(IDstr5, j, index1, j),
				"OK");
	}

	// 788, DL/T 397-2010
	public static void testElectricPowerGeography() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("1030002");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("2000000");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("2030801");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("3020102");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("7020004");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(
				RuleFunction.ElectricPowerGeography(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(
				RuleFunction.ElectricPowerGeography(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(
				RuleFunction.ElectricPowerGeography(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(
				RuleFunction.ElectricPowerGeography(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(
				RuleFunction.ElectricPowerGeography(IDstr5, j, index1, j), "OK");
	}

	// 789, DL/T 396-2010
	public static void testVoltageClass() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("AC00482");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("AC06902 ");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("AC72.51");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("DC08001");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("DC02.42");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.VoltageClass(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.VoltageClass(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.VoltageClass(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.VoltageClass(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.VoltageClass(IDstr5, j, index1, j), "OK");
	}

	// 909, DL/T 700.2-1999
	public static void testPowerGoodsP2() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("308");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("312");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("232");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("101");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("112");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerGoodsP2(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerGoodsP2(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerGoodsP2(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerGoodsP2(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerGoodsP2(IDstr5, j, index1, j), "OK");
	}

	// 361, GB/T 12603-2005
	public static void testMetalHeatCode() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("10");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("27 ");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("18");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("37");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("25");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.MetalHeatCode(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MetalHeatCode(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MetalHeatCode(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MetalHeatCode(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MetalHeatCode(IDstr5, j, index1, j), "OK");
	}

	// 353, GB/T 13923-2006
	public static void testGeographicInfoCode() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("210100");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("220304 ");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("230102");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("250402");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("260200");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.GeographicInfoCode(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GeographicInfoCode(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GeographicInfoCode(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GeographicInfoCode(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GeographicInfoCode(IDstr5, j, index1, j),
				"OK");
	}

	// 354, GB/T 13861-2009
	public static void testHarmfulFactor() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("210105");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("210599 ");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("211302");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("321200");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("490000");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.HarmfulFactor(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HarmfulFactor(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HarmfulFactor(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HarmfulFactor(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HarmfulFactor(IDstr5, j, index1, j), "OK");
	}

	// 366, GB/T 10302-2010
	public static void testRailwayStationCode() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("43061");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("20093 ");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("28172");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("50204");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("57938");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.RailwayStationCode(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RailwayStationCode(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RailwayStationCode(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RailwayStationCode(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.RailwayStationCode(IDstr5, j, index1, j),
				"OK");
	}

	// 261, GB/T 4942.1-2006/IEC 60034-5:2000
	public static void testProtectionDegreeRegex() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("0000ZXC");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("0000VBN ");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("0000MLK");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("0000JHG");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("0000FDS");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j - 4; i++) {
			index1[i] = i + 4;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.ProtectionDegreeRegex(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ProtectionDegreeRegex(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ProtectionDegreeRegex(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ProtectionDegreeRegex(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ProtectionDegreeRegex(IDstr5, j, index1, j),
				"OK");
	}

	// 237, LY/T 1080-92
	public static void testForestryClassRegex() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("00000000");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("00000012");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("00000034");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("00000056");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("00000078");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j - 3; i++) {
			index1[i] = i + 3;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.ForestryClassRegex(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ForestryClassRegex(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ForestryClassRegex(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ForestryClassRegex(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ForestryClassRegex(IDstr5, j, index1, j),
				"OK");
	}

	// 241, GB/T 9649.32-2009
	public static void testMineralRegex() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("00AB1132");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("00EF3145");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("00Er6167");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("00QW9198");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("00631100");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j - 2; i++) {
			index1[i] = i + 2;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.MineralRegex(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MineralRegex(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MineralRegex(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MineralRegex(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MineralRegex(IDstr5, j, index1, j), "OK");
	}

	// 345, GB/T 15420-2009
	public static void testStevedorageChartering() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("LF");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("FO");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("UT");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("AC");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("AB");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.StevedorageChartering(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.StevedorageChartering(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.StevedorageChartering(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.StevedorageChartering(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.StevedorageChartering(IDstr5, j, index1, j),
				"OK");
	}

	// 347, GB/T 15161-1994
	public static void testTreeDiseaseCode() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("130000");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("132207");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("132238");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("135204");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("135228");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.TreeDiseaseCode(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TreeDiseaseCode(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TreeDiseaseCode(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TreeDiseaseCode(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TreeDiseaseCode(IDstr5, j, index1, j), "OK");
	}

	// 341, GB/T 16158-1996
	public static void testNavigationShip() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("2231");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("2613");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("3420");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("4239");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("5120");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.NavigationShip(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.NavigationShip(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.NavigationShip(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.NavigationShip(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.NavigationShip(IDstr5, j, index1, j), "OK");
	}

	// 341, GB/T 16158-1996
	public static void testNavigationShipAddCode() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("10");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("18");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("29");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("31");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("69");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.NavigationShipAddCode(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.NavigationShipAddCode(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.NavigationShipAddCode(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.NavigationShipAddCode(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.NavigationShipAddCode(IDstr5, j, index1, j),
				"OK");
	}

	// 411, GA/T 974.46-2011
	public static void testFireTrainCode() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("990000");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("030203");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("030114");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("040701");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("030300");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireTrainCode(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireTrainCode(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireTrainCode(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireTrainCode(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireTrainCode(IDstr5, j, index1, j), "OK");
	}

	// 425, GA/T 974.33-2011
	public static void testFireCauseCode() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("980000");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("010399");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("020500");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("031199");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("050100");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireCauseCode(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireCauseCode(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireCauseCode(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireCauseCode(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireCauseCode(IDstr5, j, index1, j), "OK");
	}

	// 汽车产品零部件边编码规则
	public static void testCarProductCompnent() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("1000");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("1999");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("1123");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("9999");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("0000");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.CarProductCompnent(IDstr1, j, index1, j),
				"OK");
		System.out.print(i++);
		UnitTestEqual(RuleFunction.CarProductCompnent(IDstr2, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CarProductCompnent(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CarProductCompnent(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CarProductCompnent(IDstr5, j, index1, j),
				"OK");
	}

	// TCL金能电池编码规则
	public static void testTCLBatteryProduct() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("1.01");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("2.03");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("1.99");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("3.09");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("9.99");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.TCLBatteryProduct(IDstr1, j, index1, j),
				"OK");
		System.out.print(i++);
		UnitTestEqual(RuleFunction.TCLBatteryProduct(IDstr2, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TCLBatteryProduct(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TCLBatteryProduct(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TCLBatteryProduct(IDstr5, j, index1, j),
				"OK");
	}

	// TCL金能电池编码规则——第三级编码
	public static void testProductCode() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("1901");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("1903");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("1601");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("1508");
		int j1 = tr4.length();
		char[] IDstr4 = new char[j1];
		for (int i = 0; i < j1; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("1201");
		char[] IDstr5 = new char[j1];
		for (int i = 0; i < j1; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		int[] index2 = new int[j1];

		for (int i = 0; i < j1; i++) {
			index2[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ProductCode(IDstr1, j, index1, j), "OK");
		System.out.print(i++);
		UnitTestEqual(RuleFunction.ProductCode(IDstr2, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ProductCode(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ProductCode(IDstr4, j1, index2, j1),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ProductCode(IDstr5, j1, index2, j1),
				"OK");
	}

	// 工业雷管编码基本规则
	public static void testprojectbuild() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("A1");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("R1");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("Z2");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("34");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("2K");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.projectbuild(IDstr1, j, index1, j), "OK");
		System.out.print(i++);
		UnitTestEqual(RuleFunction.projectbuild(IDstr2, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.projectbuild(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.projectbuild(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.projectbuild(IDstr5, j, index1, j), "OK");
	}

	// 431, GA/T 974.28-2011
	public static void testFireForceCode() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("0900");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("1399");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("2302");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("4900");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("5300");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireForceCode(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireForceCode(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireForceCode(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireForceCode(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireForceCode(IDstr5, j, index1, j), "OK");
	}

	// 412， GA/T 974.45-2011
	public static void testFireInfotrain() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("0100");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("0299");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("0104");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("0250");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("0099");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfotrain(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfotrain(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfotrain(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfotrain(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FireInfotrain(IDstr5, j, index1, j), "OK");
	}

	// 419, GA/T 974.39-2011
	public static void testServiceState() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("00");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("90");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("25");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("28");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("20");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.ServiceState(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ServiceState(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ServiceState(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ServiceState(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ServiceState(IDstr5, j, index1, j), "OK");
	}

	// 457, GA/T 852.8-2009
	public static void testPractitionerType() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("13");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("40");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("99");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("25");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("29");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.PractitionerType(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PractitionerType(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PractitionerType(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PractitionerType(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PractitionerType(IDstr5, j, index1, j), "OK");
	}

	// 466, GA/T 556.5-2007
	public static void testFinancialCode() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("80");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("49");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("60");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("87");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("99");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.FinancialCode(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FinancialCode(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FinancialCode(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FinancialCode(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.FinancialCode(IDstr5, j, index1, j), "OK");
	}

	// 466, GA/T 556.8-2007
	public static void testTreasuryClass() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("00");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("09");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("19");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("05");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("11");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.TreasuryClass(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TreasuryClass(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TreasuryClass(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TreasuryClass(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TreasuryClass(IDstr5, j, index1, j), "OK");
	}

	// 467, GA/T 556.1-2005
	public static void testJobClassificationCode() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("FF09");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("FZ01");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("CF03");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("SX09");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("XZ10");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.JobClassificationCode(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.JobClassificationCode(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.JobClassificationCode(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.JobClassificationCode(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.JobClassificationCode(IDstr5, j, index1, j),
				"OK");
	}

	// 470, GA/T 517-2004
	public static void testTravleDocumentCode() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("163");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("291");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("323");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("346");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("555");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.TravleDocumentCode(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TravleDocumentCode(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TravleDocumentCode(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TravleDocumentCode(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TravleDocumentCode(IDstr5, j, index1, j),
				"OK");
	}

	// 474, GA/T 396-2002 公安部消防局和省级公安消防总队代码
	public static void testProvinceAdminCode() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("64");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("66");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("50");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("13");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("00");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.ProvinceAdminCode(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ProvinceAdminCode(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ProvinceAdminCode(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ProvinceAdminCode(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ProvinceAdminCode(IDstr5, j, index1, j),
				"OK");
	}

	// 474, GA/T 396-2002 列管单位代码
	public static void testAdminDivision1() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("110102001");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("110104008");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("110105015");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("110105034");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("110106011");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.AdminDivision1(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.AdminDivision1(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.AdminDivision1(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.AdminDivision1(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.AdminDivision1(IDstr5, j, index1, j), "OK");
	}

	// 326, GB/T 16963-2010
	public static void testDraftingRulesForCodes() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("00000000A09");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("00000000ZDF");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("00000000KLI");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("00000000OP0");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("00000000RTE");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j - 8; i++) {
			index1[i] = i + 8;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.DraftingRulesForCodes(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.DraftingRulesForCodes(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.DraftingRulesForCodes(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.DraftingRulesForCodes(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.DraftingRulesForCodes(IDstr5, j, index1, j),
				"OK");
	}

	// 776, HJ/T 417-2007
	public static void testEnvironmentalInformation() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("00000000909");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("00000000089");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("00000000123");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("00000000456");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("00000000963");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j - 8; i++) {
			index1[i] = i + 8;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.EnvironmentalInformation(IDstr1, j, index1,
				j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.EnvironmentalInformation(IDstr2, j, index1,
				j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.EnvironmentalInformation(IDstr3, j, index1,
				j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.EnvironmentalInformation(IDstr4, j, index1,
				j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.EnvironmentalInformation(IDstr5, j, index1,
				j), "OK");
	}

	// 782, HJ 520-2009
	public static void testWastewater() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("00000909");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("00000089");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("00000123");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("00000456");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("00000963");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j - 5; i++) {
			index1[i] = i + 5;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.Wastewater(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Wastewater(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Wastewater(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Wastewater(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Wastewater(IDstr5, j, index1, j), "OK");
	}

	// 332, GB/T 16711-1996
	public static void testBankCodes() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("00000000909");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("00000000ABC");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("00000000XYZ");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("00000000MNO");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("00000000QWE");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j - 8; i++) {
			index1[i] = i + 8;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.BankCodes(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.BankCodes(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.BankCodes(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.BankCodes(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.BankCodes(IDstr5, j, index1, j), "OK");
	}

	// 528, GA 398.20-2002
	public static void testDocumentEnvidence() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("036");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("486");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("ghy");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("298");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("399");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.DocumentEnvidence(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.DocumentEnvidence(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.DocumentEnvidence(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.DocumentEnvidence(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.DocumentEnvidence(IDstr5, j, index1, j),
				"OK");
	}

	// 中国石油天然气总公司企、事业单位代码（763）
	public static void testGassCompany() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("8052483");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("1071312");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("9999999");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("ABFGGGE");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("23ER544");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.GassCompany(IDstr1, j, index1, j), "OK");
		System.out.print(i++);
		UnitTestEqual(RuleFunction.GassCompany(IDstr2, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GassCompany(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GassCompany(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.GassCompany(IDstr5, j, index1, j), "OK");
	}

	// 校验前2位是不是这些数字 757
	public static void testHighwayDatabase6() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("20");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("67");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("91");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("ab");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("234");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase6(IDstr1, j, index1, j), "OK");
		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase6(IDstr2, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase6(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase6(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase6(IDstr5, j, index1, j), "OK");
	}

	// 校验前2位是不是这些数字 757
	public static void testHighwayDatabase7() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("GG");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("YY");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("AB");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("ab");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("234");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase7(IDstr1, j, index1, j), "OK");
		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase7(IDstr2, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase7(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase7(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase7(IDstr5, j, index1, j), "OK");
	}

	// 校验前2位是不是这些数字 757
	public static void testHighwayDatabase8() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("27");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("50");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("AB");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("ab");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("342");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase8(IDstr1, j, index1, j), "OK");
		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase8(IDstr2, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase8(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase8(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase8(IDstr5, j, index1, j), "OK");
	}

	// 532, GA 398.17-2002
	public static void testEconomicCasesUnit() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("a36");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("486");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("ghy");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("298");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("399");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.EconomicCasesUnit(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.EconomicCasesUnit(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.EconomicCasesUnit(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.EconomicCasesUnit(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.EconomicCasesUnit(IDstr5, j, index1, j),
				"OK");
	}

	// 539, GA 398.10-2002
	public static void testCodesOfMakingCases() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("936");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("486");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("ghy");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("298");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("399");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.CodesOfMakingCases(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CodesOfMakingCases(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CodesOfMakingCases(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CodesOfMakingCases(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CodesOfMakingCases(IDstr5, j, index1, j),
				"OK");
	}

	// 569, CB/T 3782-1996某几位规则未找到
	public static void testOneTO48() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("00");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("48");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("01");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("49");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("25");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO48(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO48(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO48(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO48(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OneTO48(IDstr5, j, index1, j), "OK");
	}

	// 598, GB/T 917-2009
	public static void testNationalTrunkHighway() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("009w10");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("000919");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("003434");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("00ww25");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("00w478");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j - 2; i++) {
			index1[i] = i + 2;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.NationalTrunkHighway(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.NationalTrunkHighway(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.NationalTrunkHighway(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.NationalTrunkHighway(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.NationalTrunkHighway(IDstr5, j, index1, j),
				"OK");
	}

	// 910, DL/T 700.2-1999第二部分 机电产品 之起重运输机器
	public static void testParamCode() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("12.0");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("23.9");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("1200");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("1309");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("1398");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j - 2; i++) {
			index1[i] = i + 2;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode(IDstr5, j, index1, j), "OK");
	}

	// 910, DL/T 700.2-1999第二部分 机电产品 之气体压缩机
	public static void testParamCode6() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("152.123");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("235.987");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("263.456");
		int j1 = tr3.length();
		char[] IDstr3 = new char[j1];
		for (int i = 0; i < j1; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("237.852");
		char[] IDstr4 = new char[j1];
		for (int i = 0; i < j1; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("000.222");
		char[] IDstr5 = new char[j1];
		for (int i = 0; i < j1; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		int[] index2 = new int[j1];

		for (int i = 0; i < j1; i++) {
			index2[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode6(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode6(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode6(IDstr3, j1, index2, j1), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode6(IDstr4, j1, index2, j1), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode6(IDstr5, j1, index2, j1), "OK");
	}

	// 910, DL/T 700.2-1999第二部分 机电产品 之制冷空调设备
	public static void testParamCode7() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("0000.00");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("1234.56");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("7896.32");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("a456.36");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("7896.a4");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode7(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode7(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode7(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode7(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode7(IDstr5, j, index1, j), "OK");
	}

	// 910, DL/T 700.2-1999第二部分 机电产品 之标准件
	public static void testParamCode17() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("0");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("a");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("9");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("1");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("2");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode17(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode17(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode17(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode17(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode17(IDstr5, j, index1, j), "OK");
	}

	// 910， DL/T 700.2-1999第二部分 机电产品 之互感器
	public static void testParamCode19() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("012345");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("789634");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("o00000");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("369852");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("742025");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode19(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode19(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode19(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode19(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode19(IDstr5, j, index1, j), "OK");
	}

	// 910, DL/T 700.2-1999第二部分 机电产品 之断路器
	public static void testParamCode20() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("012345124");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("789634355");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("o00000906");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("369852542");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("742025569");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode20(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode20(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode20(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode20(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode20(IDstr5, j, index1, j), "OK");
	}

	// 910, DL/T 700.2-1999第二部分 机电产品 之隔离开关
	public static void testParamCode22() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("234567");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("297842");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("o00000");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("201478");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("102365");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode22(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode22(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode22(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode22(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode22(IDstr5, j, index1, j), "OK");
	}

	// 910, DL/T 700.2-1999第二部分 机电产品 之组合电器
	public static void testParamCode27() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("02456789");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("02325874");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("o0000000");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("01787895");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("02653654");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode27(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode27(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode27(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode27(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode27(IDstr5, j, index1, j), "OK");
	}

	// 910, DL/T 700.2-1999第二部分 机电产品 之避雷器
	public static void testParamCode28() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("02231");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("02561");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("o0001");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("01781");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("02451");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode28(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode28(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode28(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode28(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode28(IDstr5, j, index1, j), "OK");
	}

	// 910, DL/T 700.2-1999第二部分 机电产品 之线路电瓷
	public static void testParamCode29() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("0156");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("0234");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("o015");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("0756");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("0598");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode29(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode29(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode29(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode29(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode29(IDstr5, j, index1, j), "OK");
	}

	// 910, DL/T 700.2-1999第二部分 机电产品 之电站电瓷
	public static void testParamCode30() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("07566");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("08634");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("o0156");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("07956");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("05798");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode30(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode30(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode30(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode30(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode30(IDstr5, j, index1, j), "OK");
	}

	// 910, DL/T 700.2-1999第二部分 机电产品 之电力电容器
	public static void testParamCode31() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("03566");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("06634");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("o0156");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("09956");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("05798");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode31(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode31(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode31(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode31(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode31(IDstr5, j, index1, j), "OK");
	}

	// 910, DL/T 700.2-1999第二部分 机电产品 之电抗器
	public static void testParamCode32() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("01160000");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("02293685");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("o0156121");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("04643561");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("02277140");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode32(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode32(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode32(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode32(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode32(IDstr5, j, index1, j), "OK");
	}

	// 910, DL/T 700.2-1999第二部分 机电产品 之低压电器元件
	public static void testParamCode34() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("01211600001");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("01232936852");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("o0115612134");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("01256435614");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("01242771405");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode34(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode34(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode34(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode34(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode34(IDstr5, j, index1, j), "OK");
	}

	// 910, DL/T 700.2-1999第二部分 机电产品 之电力金具
	public static void testParamCode35() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("122001");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("125852");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("o01105");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("126614");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("340405");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode35(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode35(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode35(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode35(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode35(IDstr5, j, index1, j), "OK");
	}

	// 910, DL/T 700.2-1999第二部分 机电产品 之电线
	public static void testParamCode38() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("123122001");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("143125852");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("103o01105");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("212126614");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("153340405");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode38(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode38(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode38(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode38(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode38(IDstr5, j, index1, j), "OK");
	}

	// 910, DL/T 700.2-1999第二部分 机电产品 之蓄电池
	public static void testParamCode41() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("3122001");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("3225852");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("3103o05");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("2126614");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("3340405");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode41(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode41(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode41(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode41(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ParamCode41(IDstr5, j, index1, j), "OK");
	}

	// 910, DL/T 700.2-1999第二部分 机电产品 之电工绝缘材料
	public static void testPowerMaterials44() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("0111");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("0299");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("0504");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("0699");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("0700");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerMaterials44(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerMaterials44(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerMaterials44(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerMaterials44(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerMaterials44(IDstr5, j, index1, j), "OK");
	}

	// 910, DL/T 700.2-1999第二部分 机电产品 之工业自动化仪器仪表
	public static void testPowerMaterials45() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("0206");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("0399");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("0501");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("0910");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("1300");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerMaterials45(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerMaterials45(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerMaterials45(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerMaterials45(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerMaterials45(IDstr5, j, index1, j), "OK");
	}

	// 910, DL/T 700.2-1999第二部分 机电产品 之专用仪器仪表
	public static void testPowerMaterials46() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("0206");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("0399");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("0501");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("0799");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("0700");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerMaterials46(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerMaterials46(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerMaterials46(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerMaterials46(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerMaterials46(IDstr5, j, index1, j), "OK");
	}

	// 910, DL/T 700.2-1999第二部分 机电产品 之计量标准仪器
	public static void testPowerMaterials49() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("0206");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("0399");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("0501");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("0699");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("0101");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerMaterials49(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerMaterials49(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerMaterials49(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerMaterials49(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerMaterials49(IDstr5, j, index1, j), "OK");
	}

	// 910, DL/T 700.2-1999第二部分 机电产品 之成分分析仪器
	public static void testPowerMaterials50() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("0299");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("0399");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("0501");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("0699");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("0101");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerMaterials50(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerMaterials50(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerMaterials50(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerMaterials50(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerMaterials50(IDstr5, j, index1, j), "OK");
	}

	// 910, DL/T 700.2-1999第二部分 机电产品 之光学仪器
	public static void testPowerMaterials51() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("0299");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("0399");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("0501");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("0699");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("0101");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerMaterials51(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerMaterials51(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerMaterials51(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerMaterials51(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerMaterials51(IDstr5, j, index1, j), "OK");
	}

	// 910, DL/T 700.2-1999第二部分 机电产品 之实验仪器装置
	public static void testPowerMaterials52() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("0299");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("0399");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("0501");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("0699");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("0999");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerMaterials52(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerMaterials52(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerMaterials52(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerMaterials52(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerMaterials52(IDstr5, j, index1, j), "OK");
	}

	// 910, DL/T 700.2-1999第二部分 机电产品 之文化办公机械
	public static void testPowerMaterials53() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("0299");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("0399");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("0501");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("0699");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("0601");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerMaterials53(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerMaterials53(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerMaterials53(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerMaterials53(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerMaterials53(IDstr5, j, index1, j), "OK");
	}

	// 910, DL/T 700.2-1999第二部分 机电产品 之试验机
	public static void testPowerMaterials54() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("0299");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("0399");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("0501");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("0699");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("9901");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerMaterials54(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerMaterials54(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerMaterials54(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerMaterials54(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.PowerMaterials54(IDstr5, j, index1, j), "OK");
	}

	// 509, GA 557.5——2005
	public static void testInternetWebService() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("0999");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("2013");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("2119");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("2180");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("0004");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.InternetWebService(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.InternetWebService(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.InternetWebService(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.InternetWebService(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.InternetWebService(IDstr5, j, index1, j),
				"OK");
	}

	// 504, GA 658.10-2006
	public static void testServiceContentCode() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("9999");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("2180");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("0011");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("1010");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("0000");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ServiceContentCode(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ServiceContentCode(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ServiceContentCode(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ServiceContentCode(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ServiceContentCode(IDstr5, j, index1, j),
				"OK");
	}

	// 635, GB/T 15694.1-1995
	public static void testIdentificationCardsP1() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("11111111");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("234643652");
		int j1 = tr2.length();
		char[] IDstr2 = new char[j1];
		for (int i = 0; i < j1; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("5555555555");
		int j2 = tr3.length();
		char[] IDstr3 = new char[j2];
		for (int i = 0; i < j2; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("33333333333");
		int j3 = tr4.length();
		char[] IDstr4 = new char[j3];
		for (int i = 0; i < j3; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("00000008999999");
		int j4 = tr5.length();
		char[] IDstr5 = new char[j4];
		for (int i = 0; i < j4; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j - 6; i++) {
			index1[i] = i + 6;
		}
		int[] index2 = new int[j1];

		for (int i = 0; i < j1 - 6; i++) {
			index2[i] = i + 6;
		}
		int[] index3 = new int[j2];

		for (int i = 0; i < j2 - 6; i++) {
			index3[i] = i + 6;
		}
		int[] index4 = new int[j3];

		for (int i = 0; i < j3 - 6; i++) {
			index4[i] = i + 6;
		}
		int[] index5 = new int[j4];

		for (int i = 0; i < j4 - 6; i++) {
			index5[i] = i + 6;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.IdentificationCardsP1(IDstr1, j, index1, j),
				"OK");
		System.out.print(i++);
		UnitTestEqual(RuleFunction
				.IdentificationCardsP1(IDstr2, j1, index2, j1), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction
				.IdentificationCardsP1(IDstr3, j2, index3, j2), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction
				.IdentificationCardsP1(IDstr4, j3, index4, j3), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction
				.IdentificationCardsP1(IDstr5, j4, index5, j4), "OK");
	}

	// 校验前2位是不是这些数字 757
	public static void testHighwayDatabase13() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("27");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("45");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("AB");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("ab");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("342");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase13(IDstr1, j, index1, j),
				"OK");
		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase13(IDstr2, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase13(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase13(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase13(IDstr5, j, index1, j),
				"OK");
	}

	// 校验前2位是不是这些数字 757
	public static void testHighwayDatabase16() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("27");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("45");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("AB");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("ab");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("342");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase16(IDstr1, j, index1, j),
				"OK");
		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase16(IDstr2, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase16(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase16(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase16(IDstr5, j, index1, j),
				"OK");
	}

	// JT/T 132——2003 工程材料代码 757
	public static void testHighwayDatabase17() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("27");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("44");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("45");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("ab");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("999");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase17(IDstr1, j, index1, j),
				"OK");
		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase17(IDstr2, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase17(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase17(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase17(IDstr5, j, index1, j),
				"OK");
	}

	// 校验前2位是不是这些数字 757
	public static void testHighwayDatabase18() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("21");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("44");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("4b");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("ab");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("999");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase18(IDstr1, j, index1, j),
				"OK");
		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase18(IDstr2, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase18(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase18(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase18(IDstr5, j, index1, j),
				"OK");
	}

	// 校验前2位是不是这些数字 757
	public static void testHighwayDatabase24() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("21");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("44");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("4b");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("ab");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("999");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase24(IDstr1, j, index1, j),
				"OK");
		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase24(IDstr2, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase24(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase24(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase24(IDstr5, j, index1, j),
				"OK");
	}

	// 校验前2位是不是这些数字 757
	public static void testHighwayDatabase25() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("00");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("44");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("4b");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("ab");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("999");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase25(IDstr1, j, index1, j),
				"OK");
		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase25(IDstr2, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase25(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase25(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase25(IDstr5, j, index1, j),
				"OK");
	}

	// 校验前2位是不是这些数字 757
	public static void testHighwayDatabase26() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("00");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("44");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("4b");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("ab");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("999");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase26(IDstr1, j, index1, j),
				"OK");
		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase26(IDstr2, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase26(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase26(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase26(IDstr5, j, index1, j),
				"OK");
	}

	// JT/T 132——2003 公路沿线设施分类代码 757
	public static void testHighwayDatabase46() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("21");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("44");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("4b");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("ab");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("999");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase46(IDstr1, j, index1, j),
				"OK");
		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase46(IDstr2, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase46(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase46(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase46(IDstr5, j, index1, j),
				"OK");
	}

	// JT/T 132——2003 公路交通标志分类 757
	public static void testHighwayDatabase47() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("119");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("444");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("55b");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("abc");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("999");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase47(IDstr1, j, index1, j),
				"OK");
		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase47(IDstr2, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase47(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase47(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase47(IDstr5, j, index1, j),
				"OK");
	}

	// 校验前2位是不是这些数字 757
	public static void testHighwayDatabase59() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("20");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("44");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("4b");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("ab");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("999");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase59(IDstr1, j, index1, j),
				"OK");
		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase59(IDstr2, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase59(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase59(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase59(IDstr5, j, index1, j),
				"OK");
	}

	// 校验前2位是不是这些数字 757
	public static void testHighwayDatabase65() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("19");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("44");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("4b");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("ab");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("999");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase65(IDstr1, j, index1, j),
				"OK");
		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase65(IDstr2, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase65(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase65(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase65(IDstr5, j, index1, j),
				"OK");
	}

	// 校验前2位是不是这些数字 757
	public static void testHighwayDatabase66() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("19");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("44");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("4b");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("ab");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("999");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase66(IDstr1, j, index1, j),
				"OK");
		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase66(IDstr2, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase66(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase66(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase66(IDstr5, j, index1, j),
				"OK");
	}

	// JT/T 132——2003工程资质代码 757
	public static void testHighwayDatabase71() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("A324");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("4433");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("4CCb");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("abDF");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("D590");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase71(IDstr1, j, index1, j),
				"OK");
		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase71(IDstr2, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase71(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase71(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase71(IDstr5, j, index1, j),
				"OK");
	}

	// 校验前4位是不是这些数字 757
	public static void testHighwayDatabase70() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("50");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("44");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("4b");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("ab");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("D5");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}
		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase70(IDstr1, j, index1, j),
				"OK");
		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase70(IDstr2, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase70(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase70(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HighwayDatabase70(IDstr5, j, index1, j),
				"OK");
	}

	// 516, GA 408.1——2006
	public static void testTraViolativeActionCode() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("1073");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("3030");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("4301");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("4601");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("5067");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(
				RuleFunction.TraViolativeActionCode(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(
				RuleFunction.TraViolativeActionCode(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(
				RuleFunction.TraViolativeActionCode(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(
				RuleFunction.TraViolativeActionCode(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(
				RuleFunction.TraViolativeActionCode(IDstr5, j, index1, j), "OK");
	}

	// 516. GA 408.2——2006
	public static void testTenByteDecimalnt() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("0000000000");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("0000000001");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("9999999999");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("9999999991");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("a000000001");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TenByteDecimalnt(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TenByteDecimalnt(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TenByteDecimalnt(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TenByteDecimalnt(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TenByteDecimalnt(IDstr5, j, index1, j), "OK");
	}

	// 510, GA 459-2004
	public static void testIDcardByMaterial() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("JCL0310");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("JCL0740");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("JGC0190");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("JRJ0150");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("JAS0240");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.IDcardByMaterial(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.IDcardByMaterial(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.IDcardByMaterial(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.IDcardByMaterial(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.IDcardByMaterial(IDstr5, j, index1, j), "OK");
	}

	// 603, GB/T 26819---2011
	public static void testMOD11() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("JCL0310");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("JCL0740");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("JGC0190");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("JRJ0150");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("JAS0240");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MOD11(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MOD11(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MOD11(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MOD11(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MOD11(IDstr5, j, index1, j), "OK");
	}

	// 603, GB/T 26819---2011
	public static void testCreditIdentifiers() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("000000000000090");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("000000000000099");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("000000000000000");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("000000000000045");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("000000000000069");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j - 13; i++) {
			index1[i] = i + 13;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CreditIdentifiers(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CreditIdentifiers(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CreditIdentifiers(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CreditIdentifiers(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.CreditIdentifiers(IDstr5, j, index1, j),
				"OK");
	}

	// 615, GB/T 21285-2007
	public static void testTubesValves() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("0000CC");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("0000CC");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("0000CD");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("0000CZ");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("0000CF");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j - 4; i++) {
			index1[i] = i + 4;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TubesValves(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TubesValves(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TubesValves(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TubesValves(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TubesValves(IDstr5, j, index1, j), "OK");
	}

	// 615, GB/T 21285-2007
	public static void testTubesValves1() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("000CC");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("000CC");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("000CD");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("000CZ");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("000CF");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j - 3; i++) {
			index1[i] = i + 3;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TubesValves1(IDstr1, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TubesValves1(IDstr2, j, index1, j), "ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TubesValves1(IDstr3, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TubesValves1(IDstr4, j, index1, j), "OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TubesValves1(IDstr5, j, index1, j), "OK");
	}

	// 632, GB/T 16986-2009
	public static void testBarCodeForCommodity() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("314n");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("329n");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("356n");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("392n");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("703s");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.BarCodeForCommodity(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.BarCodeForCommodity(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.BarCodeForCommodity(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.BarCodeForCommodity(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.BarCodeForCommodity(IDstr5, j, index1, j),
				"OK");
	}
	
	// 11, SL_385-2007
	public static void testHydrologicData() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("120100");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("230100");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("261300");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("270903");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("300001");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HydrologicData(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HydrologicData(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HydrologicData(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HydrologicData(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.HydrologicData(IDstr5, j, index1, j),
				"OK");
	}
	
	// 12, SB/T_10680-2012_5
	public static void testMeatandVegetable() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("21113.016");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("21114.106");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("01235.011");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("01242.012");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("01243.013");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MeatandVegetable(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MeatandVegetable(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MeatandVegetable(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MeatandVegetable(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MeatandVegetable(IDstr5, j, index1, j),
				"OK");
	}
	
	// 729, GB/T_15628.1-2009
	public static void testChinaAnimal() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("AG");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("PS");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("RP");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("AV");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("AB");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ChinaAnimal(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ChinaAnimal(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ChinaAnimal(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ChinaAnimal(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ChinaAnimal(IDstr5, j, index1, j),
				"OK");
	}
	
	// 733, GB/T_14721-2010
	public static void testForestTypes() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("11112");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("11418");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("15420");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("31225");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("43817");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ForestTypes(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ForestTypes(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ForestTypes(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ForestTypes(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ForestTypes(IDstr5, j, index1, j),
				"OK");
	}
	
	// 未在Redex中找到该函数
	public static void testOrganizationCode() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("10");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("23");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("99");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("01");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("24");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OrganizationCode(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OrganizationCode(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OrganizationCode(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OrganizationCode(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OrganizationCode(IDstr5, j, index1, j),
				"OK");
	}
	
	// 未在Redex中找到该函数
	public static void testProvince() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("21");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("23");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("71");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("82");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("50");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Province(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Province(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Province(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Province(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Province(IDstr5, j, index1, j),
				"OK");
	}
	
	// 未在Redex中找到该函数
	public static void testCity() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("00");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("70");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("90");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("71");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("50");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.City(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.City(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.City(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.City(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.City(IDstr5, j, index1, j),
				"OK");
	}
	
	// 658, GB/T_27766-2011_2
	public static void testLetter() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("00000");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("11111");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("01010");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("10101");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("00020");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Letter(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Letter(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Letter(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Letter(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Letter(IDstr5, j, index1, j),
				"OK");
	}
	
	// 658, GB/T_27766-2011_4
	public static void testDigitAndLetter() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("110000");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("111001");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("100000");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("111111");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("000000");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		//System.out.print(i++);
		UnitTestEqual(RuleFunction.DigitAndLetter(IDstr1, j, index1, j),
				"OK");

		//System.out.print(i++);
		UnitTestEqual(RuleFunction.DigitAndLetter(IDstr2, j, index1, j),
				"ERR");

		//System.out.print(i++);
		UnitTestEqual(RuleFunction.DigitAndLetter(IDstr3, j, index1, j),
				"OK");

		//System.out.print(i++);
		UnitTestEqual(RuleFunction.DigitAndLetter(IDstr4, j, index1, j),
				"OK");

		//System.out.print(i++);
		UnitTestEqual(RuleFunction.DigitAndLetter(IDstr5, j, index1, j),
				"OK");
	}
	
	// 700, LY/T_1959-2011
	public static void testAnimalDisease() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("4016351800");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("4017061700");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("4016000000");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("4016251800");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("4016411600");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.AnimalDisease(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.AnimalDisease(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.AnimalDisease(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.AnimalDisease(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.AnimalDisease(IDstr5, j, index1, j),
				"OK");
	}
	
	// 704, HY/T_075-2005
	public static void testOceanInfoMid() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("01");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("14");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("15");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("07");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("10");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OceanInfoMid(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OceanInfoMid(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OceanInfoMid(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OceanInfoMid(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.OceanInfoMid(IDstr5, j, index1, j),
				"OK");
	}
	
	// 698, WS/T_118-1999
	public static void testMedicalInstru() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("28021520");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("28024005");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("28024560");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("62011961");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("65631613");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MedicalInstru(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MedicalInstru(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MedicalInstru(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MedicalInstru(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MedicalInstru(IDstr5, j, index1, j),
				"OK");
	}
	
	// 728, GB/T_15657-1995_1
	public static void testTCMDisease() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("BWR110");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("BWP066");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("BEZ110");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("BEZ010");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("BYB080");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TCMDisease(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TCMDisease(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TCMDisease(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TCMDisease(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TCMDisease(IDstr5, j, index1, j),
				"OK");
	}
	
	// 728, GB/T_15657-1995_2
	public static void testTCMFeature() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("ZBFH10");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("ZBRXL0");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("ZYVT20");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("ZYTR72");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("ZYTR72");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TCMFeature(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TCMFeature(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TCMFeature(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TCMFeature(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TCMFeature(IDstr5, j, index1, j),
				"OK");
	}
	
	// 706, GB/T_9649.9-2009
	public static void testDZClassify() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("GD");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("YZ");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("SW");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("DW");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("MD");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.DZClassify(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.DZClassify(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.DZClassify(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.DZClassify(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.DZClassify(IDstr5, j, index1, j),
				"OK");
	}
	
	// 710, GB/T_9649.17-2009
	public static void testDZClassify710() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("KC");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("YZ");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("HS");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("DW");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("MD");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.DZClassify710(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.DZClassify710(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.DZClassify710(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.DZClassify710(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.DZClassify710(IDstr5, j, index1, j),
				"OK");
	}
	
	// 722, GB/T_17295-2008
	public static void testMeasureUnit() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("0KC");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("Y9Z");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("H2S");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("DW0");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("4MD");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MeasureUnit(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MeasureUnit(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MeasureUnit(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MeasureUnit(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MeasureUnit(IDstr5, j, index1, j),
				"OK");
	}
	
	// PhoneNumber
	public static void testMobilePhoneNum() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("1370004");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("1530001");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("1370355");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("1370698");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("1530071");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MobilePhoneNum(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MobilePhoneNum(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MobilePhoneNum(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MobilePhoneNum(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.MobilePhoneNum(IDstr5, j, index1, j),
				"OK");
	}
	
	// VehicleNO_1
	public static void testVehicleNONormal() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("18");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("冀B");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("吉O");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("川T");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("新O");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.VehicleNONormal(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.VehicleNONormal(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.VehicleNONormal(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.VehicleNONormal(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.VehicleNONormal(IDstr5, j, index1, j),
				"OK");
	}
	
	// VehicleNO_2
	public static void testVehicleNOArmy() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("CD");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("HB");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("KM");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("LR");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("SB");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.VehicleNOArmy(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.VehicleNOArmy(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.VehicleNOArmy(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.VehicleNOArmy(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.VehicleNOArmy(IDstr5, j, index1, j),
				"OK");
	}
	
	// VehicleNO_2
	public static void testVehicleNOArmySuffix() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("asdfQ7");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("ERTUI4");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("POIOIP");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("124567");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("8237H0");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.VehicleNOArmySuffix(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.VehicleNOArmySuffix(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.VehicleNOArmySuffix(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.VehicleNOArmySuffix(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.VehicleNOArmySuffix(IDstr5, j, index1, j),
				"OK");
	}
	
	// VehicleNO_3
	public static void testVehicleNOWJ() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("湘 ");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("鲁 ");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("黑 ");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("云 ");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("沪 ");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.VehicleNOWJ(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.VehicleNOWJ(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.VehicleNOWJ(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.VehicleNOWJ(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.VehicleNOWJ(IDstr5, j, index1, j),
				"OK");
	}
	
	// isbn
	public static void testISBN13() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("9789861817286");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

//		String tr2 = new String("鲁 ");
//		char[] IDstr2 = new char[j];
//		for (int i = 0; i < j; i++) {
//			IDstr2[i] = tr2.charAt(i);
//		}
//
//		String tr3 = new String("黑 ");
//		char[] IDstr3 = new char[j];
//		for (int i = 0; i < j; i++) {
//			IDstr3[i] = tr3.charAt(i);
//		}
//
//		String tr4 = new String("云 ");
//		char[] IDstr4 = new char[j];
//		for (int i = 0; i < j; i++) {
//			IDstr4[i] = tr4.charAt(i);
//		}
//
//		String tr5 = new String("沪 ");
//		char[] IDstr5 = new char[j];
//		for (int i = 0; i < j; i++) {
//			IDstr5[i] = tr5.charAt(i);
//		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ISBN13(IDstr1, j, index1, j),
				"OK");

//		System.out.print(i++);
//		UnitTestEqual(RuleFunction.ISBN13(IDstr2, j, index1, j),
//				"ERR");
//
//		System.out.print(i++);
//		UnitTestEqual(RuleFunction.ISBN13(IDstr3, j, index1, j),
//				"OK");
//
//		System.out.print(i++);
//		UnitTestEqual(RuleFunction.ISBN13(IDstr4, j, index1, j),
//				"OK");
//
//		System.out.print(i++);
//		UnitTestEqual(RuleFunction.ISBN13(IDstr5, j, index1, j),
//				"OK");
	}
	
	// 910, DL/T_700.1-1999_42
	public static void testOne2ThreeDigit() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("000");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("123 ");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("999 ");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("a13 ");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("789 ");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.One2ThreeDigit(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.One2ThreeDigit(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.One2ThreeDigit(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.One2ThreeDigit(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.One2ThreeDigit(IDstr5, j, index1, j),
				"OK");
	}
	
	// 1184, JT/T_307.5-1999
	public static void testA2EOrNull() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("0");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("B");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("C");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("D");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("E");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.A2EOrNull(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.A2EOrNull(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.A2EOrNull(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.A2EOrNull(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.A2EOrNull(IDstr5, j, index1, j),
				"OK");
	}
	
	
	// 306, GB/T_19234-2003
	public static void testUnderline() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("_");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("-");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("*");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("D");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("E");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Underline(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Underline(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Underline(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Underline(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Underline(IDstr5, j, index1, j),
				"OK");
	}
	
	// 335, GB/T_16696-2008
	public static void testHyphen() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("_");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("-");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("*");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("D");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("E");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Hyphen(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Hyphen(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Hyphen(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Hyphen(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Hyphen(IDstr5, j, index1, j),
				"OK");
	}
	
	// 588, SB/T_10570-2010
	public static void testPlus() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("_");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("-");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("*");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("+");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("E");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Plus(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Plus(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Plus(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Plus(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Plus(IDstr5, j, index1, j),
				"OK");
	}
	
	// 639, GB/T_14043-2005_1
	public static void testSlash() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("_");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("-");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("*");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("+");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("/");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Slash(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Slash(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Slash(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Slash(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Slash(IDstr5, j, index1, j),
				"OK");
	}
	
	// 765, SY/T_5760-2010
	public static void testDot() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String(".");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("。");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("*");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("+");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("/");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Dot(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Dot(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Dot(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Dot(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Dot(IDstr5, j, index1, j),
				"OK");
	}
	
	// 1880, JT/T_415-2000_23
	public static void testTwobytleCode08and90() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("01");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("08");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("90");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("02");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("00");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TwobytleCode08and90(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TwobytleCode08and90(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TwobytleCode08and90(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TwobytleCode08and90(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.TwobytleCode08and90(IDstr5, j, index1, j),
				"OK");
	}
	
	// DL/T_700.1-1999_53
	public static void testPowergoodsuncertainly() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("00000045");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}
		String tr2 = new String("00000008");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("00000090");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("0000002a");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("00000099");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j-7; i++) {
			index1[i] = i+7;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Powergoodsuncertainly(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Powergoodsuncertainly(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Powergoodsuncertainly(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Powergoodsuncertainly(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Powergoodsuncertainly(IDstr5, j, index1, j),
				"OK");
	}
	
	// 713, GB/T_7635.1-2002
	public static void testProductThreeByte() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("000");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("299");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("001");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("123");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("a45");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ProductThreeByte(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ProductThreeByte(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ProductThreeByte(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ProductThreeByte(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ProductThreeByte(IDstr5, j, index1, j),
				"OK");
	}
	
	// 46, GB/T_28422-2012_6
	public static void testClassOfCardCode() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("21");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("20");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("54");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("53");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("50");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ClassOfCardCode(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ClassOfCardCode(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ClassOfCardCode(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ClassOfCardCode(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.ClassOfCardCode(IDstr5, j, index1, j),
				"OK");
	}
	
	// 188, CJ/T_214-2007_1
	public static void testBigcode() {
		JOptionPane.showMessageDialog(null, Thread.currentThread()
				.getStackTrace()[1].getMethodName());
		System.out.println(Thread.currentThread().getStackTrace()[1]
				.getMethodName());

		String tr1 = new String("01");
		int j = tr1.length();
		char[] IDstr1 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr1[i] = tr1.charAt(i);
		}

		String tr2 = new String("02");
		char[] IDstr2 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr2[i] = tr2.charAt(i);
		}

		String tr3 = new String("21");
		char[] IDstr3 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr3[i] = tr3.charAt(i);
		}

		String tr4 = new String("06");
		char[] IDstr4 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr4[i] = tr4.charAt(i);
		}

		String tr5 = new String("07");
		char[] IDstr5 = new char[j];
		for (int i = 0; i < j; i++) {
			IDstr5[i] = tr5.charAt(i);
		}

		int[] index1 = new int[j];

		for (int i = 0; i < j; i++) {
			index1[i] = i;
		}

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Bigcode(IDstr1, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Bigcode(IDstr2, j, index1, j),
				"ERR");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Bigcode(IDstr3, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Bigcode(IDstr4, j, index1, j),
				"OK");

		System.out.print(i++);
		UnitTestEqual(RuleFunction.Bigcode(IDstr5, j, index1, j),
				"OK");
	}
}
