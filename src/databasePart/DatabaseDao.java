package databasePart;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class DatabaseDao {
	// 对users表的操作
	// 添加用户(注册)
	public boolean uinsert(Database db) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// 获得数据的连接
			conn = DatabaseUtils.getConnection();
			// 获得Statement对象
			stmt = conn.createStatement();
			// 发送SQL语句
			String sql = "insert into users(user,password) values('" + db.getUser() + "','" + db.getPassword() + "');";
			int num = stmt.executeUpdate(sql);
			if (num > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseUtils.release(rs, stmt, conn);
		}
		return false;
	}

	/*
	 * // 删除用户 public boolean udelete(String user) { Connection conn = null;
	 * Statement stmt = null; ResultSet rs = null; try { // 获得数据的连接 conn =
	 * DatabaseUtils.getConnection(); // 获得Statement对象 stmt =
	 * conn.createStatement(); // 发送SQL语句 String sql =
	 * "DELETE FROM users WHERE user=" + user; int num = stmt.executeUpdate(sql); if
	 * (num > 0) { return true; } return false; } catch (Exception e) {
	 * e.printStackTrace(); } finally { DatabaseUtils.release(rs, stmt, conn); }
	 * return false; }
	 */

	/*
	 * // 修改用户密码 public boolean uupdate(Database db) { Connection conn = null;
	 * Statement stmt = null; ResultSet rs = null; try { // 获得数据的连接 conn =
	 * DatabaseUtils.getConnection(); // 获得Statement对象 stmt =
	 * conn.createStatement(); // 发送SQL语句 String sql = "update users set password='"
	 * + db.getPassword() + "' where user='" + db.getUser() + "'"; int num =
	 * stmt.executeUpdate(sql); if (num > 0) { return true; } return false; } catch
	 * (Exception e) { e.printStackTrace(); } finally { DatabaseUtils.release(rs,
	 * stmt, conn); } return false; }
	 */

	/*
	 * // 查询所有用户 public ArrayList<Database> ufindAll() { Connection conn = null;
	 * Statement stmt = null; ResultSet rs = null; ArrayList<Database> list = new
	 * ArrayList<Database>(); try { // 获得数据的连接 conn = DatabaseUtils.getConnection();
	 * // 获得Statement对象 stmt = conn.createStatement(); // 发送SQL语句 String sql =
	 * "SELECT * FROM users"; rs = stmt.executeQuery(sql); // 处理结果集 while
	 * (rs.next()) { Database db = new Database(); db.setUser(rs.getString("user"));
	 * db.setPassword(rs.getString("password")); list.add(db); } return list; }
	 * catch (Exception e) { e.printStackTrace(); } finally {
	 * DatabaseUtils.release(rs, stmt, conn); } return null; }
	 */

	// 查找某个用户(登录验证)
	public Database ufind(String user) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// 获得数据的连接
			conn = DatabaseUtils.getConnection();
			// 获得Statement对象
			stmt = conn.createStatement();
			// 发送SQL语句
			String sql = "SELECT * FROM users WHERE user=" + user;
			rs = stmt.executeQuery(sql);
			// 处理结果集
			while (rs.next()) {
				Database db = new Database();
				db.setUser(rs.getString("name"));
				db.setPassword(rs.getString("password"));
				return db;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseUtils.release(rs, stmt, conn);
		}
		return null;
	}
}
