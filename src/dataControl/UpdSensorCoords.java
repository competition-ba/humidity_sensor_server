package dataControl;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

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
 * Servlet implementation class UpdSensorCoords
 */
@WebServlet("/UpdSensorCoords")
public class UpdSensorCoords extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdSensorCoords() {
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
		doGet(request, response);
		try {
			// 修改数据位置
			String json;
			json = request.getParameter("xy");
			// json
			// ="{\"GUID\":\"00000000000000000000000000000002\",\"x\":\"22\",\"y\":\"22\"}";
			JSONObject sendata = new JSONObject(json);
			String GUID = sendata.getString("GUID");
			double x = sendata.getDouble("x");
			double y = sendata.getDouble("y");
			DatabaseDao dbd = new DatabaseDao();
			Database db = new Database();
			db.setSenNo(GUID);
			db.setX(x);
			db.setY(y);
			db.setTime(new Date());
			dbd.updateSensorCoords(db);
		} catch (JSONException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

}
