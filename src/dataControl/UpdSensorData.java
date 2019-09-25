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
 * Servlet implementation class SenData
 */
@WebServlet("/SenData")
public class UpdSensorData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdSensorData() {
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
			// 更新GUID数据
			String json = new String();
			Scanner sc = new Scanner(request.getReader());
			while (sc.hasNext())
				json += (sc.next());
			sc.close();
			// json ="{\"GUID\":\"2890014BC5CD42BA879B5F2C83E7A270\",\"DATA\":\"36.00\"}";已过时
			JSONObject sendata = new JSONObject(json);
			String GUID = sendata.getString("GUID");
			String NH3=sendata.getString("NH3"); 
			double DATA = sendata.getDouble("DATA");
			DatabaseDao dbd = new DatabaseDao();
			Database db = new Database();
			db.setSenNo(GUID);
			db.setData(DATA); 
			db.setTime(new Date());
		
			db.setNH3(NH3);
			System.out.println(NH3);
			dbd.updateSensorData(db);
		} catch (JSONException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

}
