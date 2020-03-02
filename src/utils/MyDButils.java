package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 数据库工具类
 * @author lindy
 *
 */
public class MyDButils {
	// 书写一个静态代码块来加载数据库驱动
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static Connection conn = null;
	// 获得连接
	public static Connection createConnection() {
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/manager?useUnicode=true&characterEncoding=UTF-8", 
					"root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	// 释放资源
	public static void closeAll(ResultSet rs,PreparedStatement pst,Connection conn) {
		try {
			if(rs != null)
				rs.close();
			if(pst != null)
				pst.close();
			if(conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 通用的增、删、改方法
	 * @param sql	需要执行的增、删、改的sql语句
	 * @param params	需要为sql语句中的？号赋值的内容
	 * @return	受影响的行数
	 */
	public static int updateAll(String sql,Object ... params) {
		PreparedStatement pst = null;
		try {
			conn = createConnection();
			pst = conn.prepareStatement(sql);
			// 判断参数数组是否为空
			if(params != null) {
				// 为sql语句中的占位符赋值
				for (int i = 0; i < params.length; i++) {
					pst.setObject((i+1), params[i]);
				}
			}
			return pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll(null, pst, conn);
		}
		return 0;
	}
	
	// 通用的查询方法  查询最终的结果都是要得到一个结果集，所以最后就返回一个ResultSet
	public static ResultSet queryAll(String sql,Object ... params) {
		ResultSet rs = null;
		PreparedStatement pst = null;
		try {
			conn = createConnection();
			pst = conn.prepareStatement(sql);
			// 判断参数数组是否为空
			if(params != null) {
				// 为sql语句中的占位符赋值
				for (int i = 0; i < params.length; i++) {
					pst.setObject((i+1), params[i]);
				}
			}
			rs = pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	// 专门的分页操作
	public static ResultSet queryPage(String sql,int currpage,int pageSize) {
		ResultSet rs = null;
		PreparedStatement pst = null;
		try {
			conn = createConnection();
			pst = conn.prepareStatement(sql);
			// 判断参数数组是否为空
			// 为sql语句中的占位符赋值
			// 设置当前页
			// limit 偏移值,每一页显示的条数
			pst.setObject(1, (currpage-1)*pageSize);
			pst.setObject(2, pageSize);
			rs = pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
}
