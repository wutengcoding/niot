package cn.niot.util;

import java.sql.*;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;
/**
 * 
 * @author SQ
 * @date 14-08-28
 */
public final class JdbcUtilsRandom {
	private JdbcUtilsRandom(){}
	
		
	public static Connection getConnection()
	{
	
	 Connection conn=JdbcUtilsIdrecohash.getConnection();
	  
	  return conn;
	}
	public static synchronized void free(ResultSet rs, Statement stmt, Connection conn){
		JdbcUtilsIdrecohash.free(rs, stmt, conn);
	}
	/**
	之前的版本
	public static Connection getConnection4Test()
	{
	
	 Connection conn=null;
	  try{
		 Class.forName("com.mysql.jdbc.Driver");
		  //conn=DriverManager.getConnection("jdbc:mysql://218.241.108.143:3306/test", "root", "niot");
		 conn=DriverManager.getConnection("jdbc:mysql://218.241.108.143:3306/test3", "root", "niot");
	  }catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Get datasource fail ", e);
	  }catch (java.lang.ClassNotFoundException e)
        {
            
            System.out.println(e.getMessage());
           }
	  
	  return conn;
	}
	 */
	
	
	private static ComboPooledDataSource ds = new ComboPooledDataSource();
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	private static Properties p = new Properties();

	static {
		try {
			
			p.load(JdbcUtilsRandom.class.getClassLoader().getResourceAsStream(
					"conf/dbtest.properties"));
			ds.setJdbcUrl(p.getProperty("url") + p.getProperty("database"));
			ds.setDriverClass(p.getProperty("driverClassName"));
			ds.setUser(p.getProperty("username"));
			ds.setPassword(p.getProperty("password"));
			ds.setInitialPoolSize(Integer.valueOf(p.getProperty("initSize")));
			ds.setMaxPoolSize(Integer.valueOf(p.getProperty("maxSize")));
			ds.setMinPoolSize(Integer.valueOf(p.getProperty("minSize")));
			ds.setAcquireIncrement(Integer.valueOf(p.getProperty("increment")));
			ds.setMaxIdleTime(Integer.valueOf(p.getProperty("maxIdleTime")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void setDatabase(String database){
		ds.setJdbcUrl(p.getProperty("url") + database);
	}
	public static Connection getConnection4Test(){
		Connection conn = null;
		try {
			conn = ds.getConnection();
			tl.set(conn);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Get datasource fail ", e);
		}
		return conn;
	}
	//这个地方就先按照原来的free这么写吧,连接池会重写close的方法,将这个con上
	//产生的ResultSet与State or Prestate全部关闭之后才会关闭con本身,所以没比要一个个的关闭
	//但是为了项目现在不要改动太大,暂时这么,以后再说!
	//由于连接池与ThreadLocale的原因关闭连接的时候压根不用穿con本身的!暂定!安全性考虑还是要穿,暂时这么写,后期再调!
	public static synchronized void free1(ResultSet rs, Statement stmt, Connection conn){
		Connection con = tl.get();
		if (con != null) {
			try {
				con.close();
				tl.set(null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
//		try {
//			if(rs != null)
//				rs.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if(stmt != null)
//					stmt.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			} finally {
//				try {
//					if(conn != null)
//						conn.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}
		
		
	}
		
}

