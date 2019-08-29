package dataControl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import databasePart.Database;
import databasePart.DatabaseDao;

/**
 * Servlet implementation class USN
 */
@WebServlet("/USN")
public class QrySensorDataByUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QrySensorDataByUser() {
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

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		try {
			// 根据用户名查询传感器信息
			String json = new String();
			json = request.getParameter("USN");
			// json = "{\"user\":\"john\"}";
			JSONObject usn = new JSONObject(json);
			String user = usn.getString("user");
			DatabaseDao dbd = new DatabaseDao();
			ArrayList<Database> list = dbd.querySensorDataByUser(user);
			// 将list信息提取并给予list2中
			ArrayList<JSONObject> list2 = new ArrayList<JSONObject>();
			for (int i = 0; i < list.size(); i++) {
				// 提取SenData表中的list中第i行信息
				String senNo = list.get(i).getSenNo();
				String nickname = list.get(i).getNickname();
				double x = list.get(i).getX();
				double y = list.get(i).getY();
				double data = list.get(i).getData();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String time = sdf.format(list.get(i).getTime());
				// 将数据存储并转成JSON数组形式
				JSONObject json2 = new JSONObject();
				json2.put("senNo", senNo);
				json2.put("nickname", nickname);
				json2.put("x", x);
				json2.put("y", y);
				json2.put("data", data);
				json2.put("time", time);
				list2.add(json2);
			}
			JSONArray jsonarray1 = new JSONArray(list2);
			// System.out.println(jsonarray1.toString());
			response.getWriter().print(jsonarray1.toString());
			return;

		} catch (

		JSONException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

}
