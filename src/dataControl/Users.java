package dataControl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import databasePart.Database;
import databasePart.DatabaseDao;

/**
 * Servlet implementation class Users
 */
@WebServlet("/Users")
public class Users extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			String json;
			// json = request.getParameter("users");
			json = "{\"user\":\"peter\",\"password\":\"666666\",\"state\":\"register\"}";
			JSONObject users = new JSONObject(json);
			String user = users.getString("user");
			String password = users.getString("password");
			String state = users.getString("state");
			if (state.equals("register")) {
				// 插入用户数据至Users表
				DatabaseDao dbd = new DatabaseDao();
				Database db = new Database();
				db.setUser(user);
				db.setPassword(password);
				boolean b = dbd.uinsert(db);
				if (b)
					System.out.println("注册成功");
				else
					System.out.println("注册失败");
			} else if (state.equals("login")) {
				// 查询是否存在该用户名or用户名与密码对应
			}
		} catch (JSONException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}