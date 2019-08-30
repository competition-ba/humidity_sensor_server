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
 * Servlet implementation class DelSensor
 */
@WebServlet("/DelSensor")
public class DelSensor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DelSensor() {
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
		//doGet(request, response);
		try {
			String json;
			// json = request.getParameter("del");
			json = "{\"GUID\":\"123456789\"}";
			JSONObject delete = new JSONObject(json);
			String GUID = delete.getString("GUID");
			DatabaseDao dbd = new DatabaseDao();
			// 删除USN表中数据
			boolean a = dbd.deleteSensor(GUID);
			System.out.println(a);
			if (a) {
				response.getWriter().print("OK");
				return;
			} else {
				response.getWriter().print("fail");
				return;
			}


		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
