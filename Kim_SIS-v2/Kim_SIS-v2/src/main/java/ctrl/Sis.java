package ctrl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.StudentBean;
import model.SisModel;
import model.StudentDAO;

/**
 * Servlet implementation class Sis
 */
@WebServlet("/Sis")
public class Sis extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String result = "";
		String prefix = request.getParameter("prefix");
		String minCredit = request.getParameter("minCredit");
		SisModel model = (SisModel) this.getServletContext().getAttribute("sisModel");
		
		
//		String sid = request.getParameter("sid");
//		String givenName = request.getParameter("givenName");
//		String surName = request.getParameter("surName");
//		String creditTaken = request.getParameter("creditTaken");
//		String creditGraduate = request.getParameter("givenName");
		
		System.out.println("servlet");
		
		// User input from the html
		if(prefix != null && prefix.length()>0 && minCredit != null) {
			StudentDAO sd = null;
			try {
				sd = model.getStudentDAOInstance();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HashMap<String, StudentBean> stdList = null;
			try {
				stdList = (HashMap<String, StudentBean>) sd.retrieve(prefix, Integer.valueOf(minCredit));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Iterator it = stdList.keySet().iterator();
			while(it.hasNext()) {
				String key = (String) it.next();
				result += "{\"name\":\""+stdList.get(key).getName()
						+"\",\"creditTaken\":"+stdList.get(key).getCredit_taken()
						+",\"creditGraduate\":"+stdList.get(key).getCredit_graduate()+"},";
			}
			
			//sd.readAndPrintTableToConsole();
			if(result.length()>0) {
				result = result.substring(0,result.length()-1);
				result = "{\"students\":["+result+"]}";
			}
			
			
			// If it's from the form, send Json formatted string to parse
			if(request.getParameter("reportAjax")!=null) {
				response.getWriter().append(result);
				response.getWriter().flush();
			} else {
				// If it's from query, just print the Json formatted string in the console
				response.getWriter().append(result);
				System.out.println(result);
			}
			// report to Ajax
			
		} else {
			// User input from path or query
			
			try {
				model.getStudentDAOInstance().retrieveToConsole();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			// Save the model as Servlet context
			SisModel model = SisModel.getInstance();
			this.getServletContext().setAttribute("sisModel", model);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new ServletException("Class Not Found" + e);
		}
	}


}
