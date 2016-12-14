package cn.niot.controller;

import cn.niot.rfid.RFIDReader;

import com.opensymphony.xwork2.ActionSupport;
import com.silionmodule.ReaderException;

public class RFIDOrScanerInputAction extends ActionSupport {
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
		// TODO Auto-generated method stub
		System.out.println("RFID comming...");
		//this.code = RecoDao.getRecoDao().getRFIDInput();
		this.code = null;
		try{
			RFIDReader myReader = new RFIDReader();
			this.code = myReader.ReadPaperTag();
			System.out.println("code after paper=====>"+this.code);
		}catch (ReaderException e) {
			// TODO Auto-generated catch block
		 	System.out.println("errer in paper tag");
			System.out.println(e.GetMessage());
		}
		
		if(this.code == null){
			try{
				
					RFIDReader myReader2 = new RFIDReader();
					this.code = myReader2.ReadAntimetalTag();
				
			}catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("errer in antimetal tag");
				System.out.println(e.getMessage());
			}
		}
		System.out.println("ending....");
		return super.execute();
	}
}
