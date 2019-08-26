package dataControl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;

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
 * Servlet implementation class USN
 */
@WebServlet("/USN")
public class USN extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public USN() {
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
			// 根据用户名查询传感器信息
			String json = new String();
			// json = request.getParameter("USN");
			json = "{\"user\":\"fhh\"}";
			JSONObject usn = new JSONObject(json);
			String user = usn.getString("user");
			DatabaseDao dbd = new DatabaseDao();
			Database db = new Database();
			db = dbd.nfind(user);
			String senNo = db.getSenNo();
			String nickname = db.getNickname();
			double x = db.getX();
			double y = db.getY();
			double data = db.getData();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			// sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
			String time = sdf.format(db.getTime());
			// 将数据转成JSON形式
			JSONObject json2 = new JSONObject();
			json2.put("senNo", senNo);
			json2.put("nickname", nickname);
			json2.put("x", x);
			json2.put("y", y);
			json2.put("data", data);
			json2.put("time", time);
			String outprint = json2.toString();
			response.getWriter().print(outprint);
			return;
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
