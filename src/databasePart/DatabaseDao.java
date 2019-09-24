package databasePart;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DatabaseDao {

	// 注册新用户
	public boolean registerUsers(Database db) {
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

	// 查找某个用户(登录验证)
	public Database queryUserAndPassword(String user) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// 获得数据的连接
			conn = DatabaseUtils.getConnection();
			// 获得Statement对象
			stmt = conn.createStatement();
			// 发送SQL语句
			String sql = "SELECT * FROM users WHERE user='" + user + "'";
			rs = stmt.executeQuery(sql);
			// 处理结果集
			while (rs.next()) {
				Database db = new Database();
				db.setUser(rs.getString("user"));
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

	// 修改用户名密码
	public boolean updatePassword(Database db) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// 获得数据的连接
			conn = DatabaseUtils.getConnection();
			// 获得Statement对象
			stmt = conn.createStatement();
			// 发送SQL语句
			String sql = "update users set password='" + db.getPassword() + "' where user='" + db.getUser() + "';";
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

	// 注册传感器
	public boolean registerSensor(Database db) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// 获得数据的连接
			conn = DatabaseUtils.getConnection();
			// 获得Statement对象
			stmt = conn.createStatement();
			// 发送SQL语句
			String sql = "insert into USN(user,senNo) values('" + db.getUser() + "','" + db.getSenNo() + "');";
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

	// 在USN表通过user查询senData数据
	public ArrayList<Database> querySenDataByUser(String user) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Database> list = new ArrayList<Database>();
		try {
			// 获得数据的连接
			conn = DatabaseUtils.getConnection();
			// 获得Statement对象
			stmt = conn.createStatement();
			// 发送SQL语句
			String sql = "select * from USN where user='" + user + "';";
			rs = stmt.executeQuery(sql);
			// 处理结果集
			while (rs.next()) {
				Database db = new Database();
				db.setSenNo(rs.getString("senNo"));
				// System.out.println("hello world"+rs.getString("nickname"));
				list.add(db);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseUtils.release(rs, stmt, conn);
		}
		return null;
	}

	// 通过user查找SenData的数据(多条)
	public ArrayList<Database> querySensorDataByUser(String user) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Database> list = new ArrayList<Database>();
		try {
			// 获得数据的连接
			conn = DatabaseUtils.getConnection();
			// 获得Statement对象
			stmt = conn.createStatement();
			// 发送SQL语句
			String sql = "select * from SenData where senNo in (select senNo from USN where user='" + user + "');";
			rs = stmt.executeQuery(sql);
			// 处理结果集
			while (rs.next()) {
				Database db = new Database();
				db.setSenNo(rs.getString("senNo"));
				db.setNickname(rs.getString("nickname"));
				db.setX(rs.getDouble("x"));
				db.setY(rs.getDouble("y"));
				db.setData(rs.getDouble("data"));
				db.setTime(rs.getTimestamp("time"));
				db.setNH3(rs.getString("NH3"));
				// System.out.println("hello world"+rs.getString("nickname"));
				list.add(db);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseUtils.release(rs, stmt, conn);
		}
		return null;
	}

	// 插入一条传感器信息
	public boolean addSensorInf(Database db) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// 获得数据的连接
			conn = DatabaseUtils.getConnection();
			// 获得Statement对象
			stmt = conn.createStatement();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String time = sdf.format(db.getTime());
			// 发送SQL语句
			String sql = "INSERT INTO SenData(senNo,nickname,x,y,data,time) VALUES('" + db.getSenNo() + "','"
					+ db.getNickname() + "'," + db.getX() + "," + db.getY() + "," + db.getData() + ",'" + time + "','"+db.getNH3()+"');";
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

	// 修改一条传感器数据
	public boolean updateSensorData(Database db) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// 获得数据的连接
			conn = DatabaseUtils.getConnection();
			// 获得Statement对象
			stmt = conn.createStatement();
			// 发送SQL语句
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String time = sdf.format(db.getTime());
			String sql = "update SenData set data=" + db.getData() + ",time='" + time + "' where senNo='"
					+ db.getSenNo() + "','"+db.getNH3()+"';";
			System.out.println("——————————————————NH3："+db.getNH3());
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

	// 修改传感器数据的xy位置
	public boolean updateSensorCoords(Database db) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// 获得数据的连接
			conn = DatabaseUtils.getConnection();
			// 获得Statement对象
			stmt = conn.createStatement();
			// 发送SQL语句
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String time = sdf.format(db.getTime());
			String sql = "update SenData set x=" + db.getX() + ",y=" + db.getY() + ",time='" + time + "' where senNo='"
					+ db.getSenNo() + "';";
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

	// 删除传感器用户以及信息
	public boolean deleteSensor(String GUID) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// 获得数据的连接
			conn = DatabaseUtils.getConnection();
			// 获得Statement对象
			stmt = conn.createStatement();
			// 发送SQL语句
			//String sql = "delete from USN where SenNo='" + GUID + "';";
			String sql2 = "delete from SenData where SenNo='" + GUID + "';";
			//int num = stmt.executeUpdate(sql);
			int num2 = stmt.executeUpdate(sql2);
			if (num2 > 0 ) {//&& num2 > 0
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

}
