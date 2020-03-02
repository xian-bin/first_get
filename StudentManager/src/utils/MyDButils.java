package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ���ݿ⹤����
 * @author lindy
 *
 */
public class MyDButils {
	// ��дһ����̬��������������ݿ�����
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static Connection conn = null;
	// �������
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
	// �ͷ���Դ
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
	 * ͨ�õ�����ɾ���ķ���
	 * @param sql	��Ҫִ�е�����ɾ���ĵ�sql���
	 * @param params	��ҪΪsql����еģ��Ÿ�ֵ������
	 * @return	��Ӱ�������
	 */
	public static int updateAll(String sql,Object ... params) {
		PreparedStatement pst = null;
		try {
			conn = createConnection();
			pst = conn.prepareStatement(sql);
			// �жϲ��������Ƿ�Ϊ��
			if(params != null) {
				// Ϊsql����е�ռλ����ֵ
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
	
	// ͨ�õĲ�ѯ����  ��ѯ���յĽ������Ҫ�õ�һ����������������ͷ���һ��ResultSet
	public static ResultSet queryAll(String sql,Object ... params) {
		ResultSet rs = null;
		PreparedStatement pst = null;
		try {
			conn = createConnection();
			pst = conn.prepareStatement(sql);
			// �жϲ��������Ƿ�Ϊ��
			if(params != null) {
				// Ϊsql����е�ռλ����ֵ
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
	
	// ר�ŵķ�ҳ����
	public static ResultSet queryPage(String sql,int currpage,int pageSize) {
		ResultSet rs = null;
		PreparedStatement pst = null;
		try {
			conn = createConnection();
			pst = conn.prepareStatement(sql);
			// �жϲ��������Ƿ�Ϊ��
			// Ϊsql����е�ռλ����ֵ
			// ���õ�ǰҳ
			// limit ƫ��ֵ,ÿһҳ��ʾ������
			pst.setObject(1, (currpage-1)*pageSize);
			pst.setObject(2, pageSize);
			rs = pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
}
