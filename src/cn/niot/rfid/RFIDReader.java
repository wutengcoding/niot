package cn.niot.rfid;

import com.silionmodule.Functional;
import com.silionmodule.ISO180006B;
import com.silionmodule.ParamNames;
import com.silionmodule.Reader;
import com.silionmodule.ReaderException;
import com.silionmodule.SimpleReadPlan;
import com.silionmodule.TagProtocol;
import com.silionmodule.TagReadData;
import com.silionmodule.Gen2.MemBankE;
import com.silionmodule.ISO180006B.ISO6BTagFilter;
import com.silionmodule.ReaderType.ReaderTypeE;

public class RFIDReader {

	Reader reader;

	public RFIDReader() throws ReaderException {
		reader = Reader.Create("192.168.1.100", ReaderTypeE.M5E_A7_FOURANTS);
		// reader=Reader.Create("com3", ReaderTypeE.M1S_ONEANTS);
		// reader=Reader.Create("com3", AntTypeE.ONE_ANT);
	}

	// read paper tag, return the user id
	public String ReadPaperTag() throws ReaderException {
		System.out.println("paper tag...");
		short[] epddata = reader.ReadTagMemWords(null, MemBankE.USER, 0, 32);
		String code1 = Functional.shorts_HexStr(epddata);
		String code=getInputByStr(code1, "ABCD");
		System.out.println(code);

		return code;
	}

	public String ReadAntimetalTag() throws Exception {
		// 设置参数，不检查读卡器的端口
		System.out.println("antimetal tag...");
		reader.paramSet(ParamNames.Reader_Antenna_CheckPort, false);
		// 创建一个简单读的方案，参数1是只有1个天线，参数2是电子标签的协议时ISO18000_6B
		SimpleReadPlan srp = new SimpleReadPlan(new int[] { 1 },
				TagProtocol.TagProtocolE.ISO18000_6B);
		reader.paramSet(ParamNames.Reader_Read_Plan, srp);
		TagReadData[] trd = reader.Read(4000);
		// this.code = trd[0].EPCHexstr();
		// 此ID将作为filter的参数
		String id = trd[0].EPCHexstr();
		System.out.println("id======>" + id);
		// 6B 每块一个字节，每个字节两个十六进制数，0-7块不可写，8-224块可写
		reader.paramSet(ParamNames.Reader_Tagop_Protocol,
				TagProtocol.TagProtocolE.ISO18000_6B);
		// ISO6BTagFilter iso6tf=new
		// ISO180006B.ISO6BTagFilter(Functional.hexstr_Bytes("E00400002101CC05"));
		ISO6BTagFilter iso6tf = new ISO180006B.ISO6BTagFilter(Functional
				.hexstr_Bytes(id));
		// reader.WriteTagMemBytes(iso6tf, ISO180006B.MemBankE.ISO180006B, 8,
		// Functional.hexstr_Bytes("222222"));

		byte[] r186bdata = reader.ReadTagMemBytes(iso6tf,
				ISO180006B.MemBankE.ISO180006B, 8, 100);
		System.out.println("6b read:" + Functional.bytes_Hexstr(r186bdata));

		String code1 = Functional.bytes_Hexstr(r186bdata);
		// System.out.println(this.code);
		String code=getInputByStr(code1, "ABCD");
		return code;
	}

	// 根据匹配的字符串处理输入的字符串
	public String getInputByStr(String inputCode, String str) {
		int i = 0, j = 0;
		String outputCode = inputCode;
		if (str != null) {
			while (i < inputCode.length() && j < str.length()) {
				if (inputCode.charAt(i) == str.charAt(j)) {
					i++;
					j++;
				} else {
					i = i - j + 1;
					j = 0;
				}
				if (j == str.length()) {
					String code = inputCode.substring(0, i - str.length());
					outputCode = code;
				}
			}
		}
		return outputCode;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			RFIDReader tmj = new RFIDReader();
			// tmj.TestMultiRead();
			// tmj.TestSimpleRead();
			// tmj.TestWriteMembank();
			// tmj.TestLockMembank();
			// tmj.TestReadMembank();
			tmj.ReadPaperTag();
			// tmj.TestParamSetGet();
			// tmj.TestNes();
			// tmj.TestOver();
		} catch (ReaderException e) {
			// TODO Auto-generated catch block

			System.out.println(e.GetMessage());
		}

	}
}
