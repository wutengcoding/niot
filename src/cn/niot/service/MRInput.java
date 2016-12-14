package cn.niot.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;




import cn.niot.dao.DaoEmulation;

public class MRInput {
	public static void main(String[] args)
	{
		String path="E:/MRInput/iotidSpecific.txt";
		ArrayList TypeSet=DaoEmulation.getTypeSet();
		Iterator it = TypeSet.iterator();
		String res="";
		while(it.hasNext())
		{
			res="";
			String type=it.next().toString();
			String tablename = DaoEmulation.getTablenale(type);
			ArrayList<String> TestSet = new ArrayList<String>();
			TestSet = DaoEmulation.getTestSet(tablename);
			Iterator it1 = TestSet.iterator();
			
			while(it1.hasNext())
			{
				res=res+it1.next().toString();
				res=res+"{] ";
				res=res+type;
				res=res+"\r\n";
				
			}
			res=res.substring(0, res.length()-1);
			Report(res,path);
			
			System.out.println(type);
			
			
			
		}
		
		
		return;
	}
	
	public static void Report(String resInfo, String path) {
		// TODO Auto-generated method stub
		try{
			File f = new File(path);
			BufferedWriter output = new BufferedWriter(new FileWriter(f,
				true));
			output.append(resInfo);
			output.append("\n");
			
			output.flush();
			output.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			
	}

}
