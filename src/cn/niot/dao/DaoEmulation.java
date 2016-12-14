/**
 * 
 */
package cn.niot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cn.niot.util.*;

/**
 * @author SQ
 *2014-10-15
 */
public class DaoEmulation {

	/**
	 * @param type
	 * @return
	 */
	public static String getSplitPoints(String type) {
		Connection connection=JdbcUtilsEmulation.getConnectionEmulation();
		String point="";
		try {
			PreparedStatement stmt;
			ResultSet result;
			//stmt = connection.prepareStatement("select * from TypeInfo where id="+"\""+type+"\";");
			stmt = connection.prepareStatement("select * from TypeInfo where id=?"+";");
			//stmt = connection.prepareStatement("select * from typeinfo where id=?"+";");
			stmt.setString(1, type);
			result = stmt.executeQuery();
			while (result.next()) {
				point = result.getString("SplitPoints");

			}

		} catch (SQLException e) {
			System.out.println(e.toString());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUtilsEmulation.free(null, null, null);
		}
		
		return point;
	}

	/**
	 * @param type
	 * @return
	 */
	public static String getTablenale(String type) {
		Connection connection=JdbcUtilsEmulation.getConnectionEmulation();
		String name="";
		try {
			PreparedStatement stmt;
			ResultSet result;
			//stmt = connection.prepareStatement("select * from TypeInfo where id="+"\""+type+"\";");
			//stmt = connection.prepareStatement("select * from TypeInfo where id="+type+";");
			stmt = connection.prepareStatement("select * from TypeInfo where id=?");
			//stmt = connection.prepareStatement("select * from typeinfo where id=?");
			stmt.setString(1, type);
			
			result = stmt.executeQuery();
			while (result.next()) {
				name = result.getString("FunctionName");

			}

		} catch (SQLException e) {
			//System.out.println(e.toString());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUtilsEmulation.free(null, null, null);
		}
		
		return name;
	}

	/**
	 * @param type
	 * @return
	 */
	public static ArrayList<String> getTestSet(String type) {
		Connection connection=JdbcUtilsEmulation.getConnectionEmulation();
		ArrayList<String> res=new ArrayList<String>();
		try {
			PreparedStatement stmt;
			ResultSet result;
			stmt = connection.prepareStatement("select * from "+type+" limit 1000;");
			result = stmt.executeQuery();
			while (result.next()) {
				String code=result.getString("code");
				
				res.add(code);

			}

		} catch (SQLException e) {
			//System.out.println(e.toString());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUtilsEmulation.free(null, null, null);
		}
		return res;
		
	}

	/**
	 * @return
	 */
	public static ArrayList<String> getTypeSet() {
		
		Connection connection=JdbcUtilsEmulation.getConnectionEmulation();
		String type="";
		ArrayList<String> TypeSet=new ArrayList<String>();
		try {
			PreparedStatement stmt;
			ResultSet result;
			stmt = connection.prepareStatement("select * from TypeSet;");
			result = stmt.executeQuery();
			while (result.next()) {
				type = result.getString("code");
				TypeSet.add(type);
			}

		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			JdbcUtilsEmulation.free(null, null, null);
		}
		return TypeSet;
	}
	
      public static ArrayList<String> getTypeSet1() {
		
		Connection connection=JdbcUtilsEmulation.getConnectionEmulation();
		String type="";
		ArrayList<String> TypeSet=new ArrayList<String>();
		try {
			PreparedStatement stmt;
			ResultSet result;
			stmt = connection.prepareStatement("select * from TypeSet1;");
			result = stmt.executeQuery();
			while (result.next()) {
				type = result.getString("code");
				TypeSet.add(type);
			}

		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			JdbcUtilsEmulation.free(null, null, null);
		}
		return TypeSet;
	}

	/**
	 * @param tablename
	 * @param rangeBottom
	 * @param rangeTop
	 * @return
	 */
	public static ArrayList<String> getDataSet(String tablename,
			int rangeBottom, int rangeTop) {
		Connection connection=JdbcUtilsEmulation.getConnectionEmulation();
		ArrayList<String> res=new ArrayList<String>();
		rangeTop=rangeTop-rangeBottom;
		try {
			PreparedStatement stmt;
			ResultSet result;
			//String sql="select * from "+tablename+" limit 1000,9000";
			String sql="select * from "+tablename+" limit "+rangeBottom+","+rangeTop+";";
			
			stmt = connection.prepareStatement(sql);
			result = stmt.executeQuery();
			while (result.next()) {
				String code=result.getString("code");
				
				res.add(code);

			}

		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally
		{
			JdbcUtilsEmulation.free(null, null, null);
		}
		return res;
	}
	
	

}
