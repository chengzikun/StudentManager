package czk.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtil02 {

	static ComboPooledDataSource dataSource = null;
	
	static {
		dataSource = new ComboPooledDataSource();
	}
	
	public static DataSource getDataSource() {
		return dataSource;
	}
	
	// 获取连接对象
	public static Connection getConn() throws SQLException {
		
		return dataSource.getConnection();
	}

	// 释放资源
	public static void release(Connection conn, ResultSet rs, Statement st) {
		closeConn(conn);
		closeRs(rs);
		closeSt(st);
	}
	public static void release(Connection conn, ResultSet rs, PreparedStatement ps) {
		closeConn(conn);
		closeRs(rs);
		closePs(ps);
	}
	public static void release(Connection conn, PreparedStatement ps) {
		closeConn(conn);
		closePs(ps);
	}

	public static void release(Connection conn, Statement st) {
		closeConn(conn);
		closeSt(st);
	}

	private static void closeConn(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn = null;
		}
	}
	
	private static void closePs(PreparedStatement ps) {
		try {
			if (ps != null) {
				ps.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ps = null;
		}
	}
	
	private static void closeRs(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			rs = null;
		}
	}

	private static void closeSt(Statement st) {
		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			st = null;
		}
	}

}
