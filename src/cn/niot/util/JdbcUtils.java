package cn.niot.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils {
	
	private JdbcUtils(){}
	
	private static ComboPooledDataSource ds = new ComboPooledDataSource();
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	private static Properties p = new Properties();

	static {
		try {
			
			p.load(JdbcUtils.class.getClassLoader().getResourceAsStream(
					"conf/dbidreco.properties"));
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
	public static Connection getConnection(){
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
	public static synchronized void free(ResultSet rs, Statement stmt, Connection conn){
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
	
	public static void main(String[] args) {
		Connection con = getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst =  con.prepareStatement("select * from admindivision where id = 1");
			System.out.println(pst);
			rs = pst.executeQuery();
			if(rs.next()){
				String name = rs.getString("name");
				System.out.println("name::::::" + name);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
			// TODO: handle exception
		}finally{
			System.out.println(con.getClass().getName());
			free(rs,pst,con);
		}
		
	}
	
	
}
