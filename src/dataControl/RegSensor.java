package dataControl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

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
 * Servlet implementation class RegSensor
 */
@WebServlet("/RegSensor")
public class RegSensor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegSensor() {
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
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
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
			String json;
			boolean j = true;
			json = request.getParameter("usersAndGUID");
			// json =
			// "{\"user\":\"lily\",\"senNo\":\"00000000000000000000000000000002\",\"nickname\":\"\"}";
			JSONObject uAndS = new JSONObject(json);
			String user = uAndS.getString("user");
			String GUID = uAndS.getString("senNo");
			String nickname = uAndS.getString("nickname");
			// System.out.println(nickname);
			DatabaseDao dbd = new DatabaseDao();
			Database db = new Database();
			ArrayList<Database> list = dbd.querySenDataByUser(user);
			for (int i = 0; i < list.size(); i++) {
				String senNo = list.get(i).getSenNo();
				// System.out.println("list.get("+i+").getSenNo()"+list.get(i).getSenNo());
				if (GUID.equals(senNo))
					j = false;
				;
			}
			if (j) {
				db.setUser(user);
				db.setSenNo(GUID);
				db.setNickname(nickname);
				db.setX(0);
				db.setY(0);
				db.setData(0);
				db.setTime(new Date());
				boolean a = dbd.addSensorInf(db);
				boolean b = dbd.registerSensor(db);
				// System.out.println();
				if (b) {
					// System.out.println("OK");
					response.getWriter().print("OK");
					return;
				} else {
					// System.out.println("fail");
					response.getWriter().print("fail");
					return;
				}
			} else {
				// System.out.println("fail-存在相同GUID");
				response.getWriter().print("exists");
				return;
			}

		} catch (JSONException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

}
