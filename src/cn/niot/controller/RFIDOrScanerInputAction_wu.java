package cn.niot.controller;

import cn.niot.dao.RecoDao;

import com.opensymphony.xwork2.ActionSupport;
import com.silionmodule.ParamNames;
import com.silionmodule.Reader;
import com.silionmodule.ReaderException;
import com.silionmodule.SimpleReadPlan;
import com.silionmodule.TagProtocol;
import com.silionmodule.TagReadData;
import com.silionmodule.ReaderType.ReaderTypeE;

public class RFIDOrScanerInputAction_wu extends ActionSupport {
	Reader reader;
	private String InputType;
	
	private String code;

	/**
	 * @param inputType the inputType to set
	 */
	public void setInputType(String inputType) {
		InputType = inputType;
	}

	
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	
	@Override
	public String execute() throws Exception {
		//打开读卡器，参数1是读卡器在网段中的ip地址，参数2是选择天线ANT1
		reader = Reader.Create("192.168.1.100", ReaderTypeE.M5E_NA7_ONEANTS);
		//设置参数，不检查读卡器的端口
		reader.paramSet(ParamNames.Reader_Antenna_CheckPort,false);
		//创建一个简单读的方案，参数1是只有1个天线，参数2是电子标签的协议时Gen2
		SimpleReadPlan srp=new SimpleReadPlan(new int[]{1},TagProtocol.TagProtocolE.Gen2);
		reader.paramSet(ParamNames.Reader_Read_Plan, srp);
		TagReadData[] trd=reader.Read(1000);
		this.code = trd[0].toString().substring(4,28);
	
		return super.execute();
	}





	

}
