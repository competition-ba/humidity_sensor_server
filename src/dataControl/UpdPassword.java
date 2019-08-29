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
 * Servlet implementation class UpdPassword
 */
@WebServlet("/UpdPassword")
public class UpdPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdPassword() {
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
			String json;
			json = request.getParameter("pas");
			// json =
			// "{\"user\":\"fhh\",\"password1\":\"123456\",\"password2\":\"000000\"}";
			JSONObject users = new JSONObject(json);
			String user = users.getString("user");
			String password1 = users.getString("password1");
			String password2 = users.getString("password2");
			DatabaseDao dbd = new DatabaseDao();
			Database db = new Database();
			db = dbd.queryUserAndPassword(user);
			if (password1.equals(db.getPassword())) {
				db.setPassword(password2);
				db.setUser(user);
				boolean a = dbd.updatePassword(db);
				if (a) {
					response.getWriter().print("OK");
					return;
				} else {
					response.getWriter().print("fail");
					return;
				}
			} else {
				response.getWriter().print("exist");
				return;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
