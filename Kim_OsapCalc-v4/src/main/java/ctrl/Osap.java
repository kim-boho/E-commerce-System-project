package ctrl;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Loan;

/**
 * Servlet implementation class Osap
 */
@WebServlet({"/Osap","/Osap/*"})
public class Osap extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String appName;
	private String interest;
	private String period;
	private String principal;
	
	final String CONTEXT_APP_NAME = "applicationName";
	final String CONTEXT_PRINCIPAL = "principal";
	final String CONTEXT_INTEREST = "interest";
	final String CONTEXT_PERIOD = "period";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Osap() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Hello, Got a GET request from Osap!");
		
		String servletPath = request.getPathInfo();
		Writer resOut = response.getWriter();
		
		// Servlet context
		ServletContext context = this.getServletContext();

		// when the path is /Lab1, show the whole info texts
		if(servletPath != null && servletPath.equals("/Lab1")) {
			
			// information about client/query/servlet
			resOut.write("Hello, World!\n");
			String clientIP = request.getRemoteAddr();
			if(clientIP != null) {
				resOut.write("Client IP: "+clientIP+"\n");
				resOut.write("This IP has been flagged!\n");
			}
			int clientPortNum = request.getRemotePort();
			resOut.write("Client Port: "+clientPortNum+"\n");
			String clientProtocol = request.getProtocol();
			resOut.write("Client Protocol: "+clientProtocol+"\n");
			String clientMethod = request.getMethod();
			resOut.write("Client Method: "+clientMethod+"\n");
			
			String clientQueryString = request.getQueryString();
			resOut.write("Query String: "+clientQueryString+"\n");
			String foo = request.getParameter("foo");
			resOut.write("Query Parm foo="+foo+"\n");
			
			String URI = request.getRequestURI();
			resOut.write("Request URI: "+URI+"\n");
			resOut.write("Request Servlet Path: "+servletPath+"\n");
			
			// Lab1 URL receives parameter using query string so do the null check
			if(request.getParameter("principal")==null
					||request.getParameter("interest")==null
					||request.getParameter("period")==null) {
				resOut.write("---- Application info ----\n");
				resOut.write("Application Name="+appName+"\n");
				resOut.write("Applicant Name="+context.getInitParameter("applicantName")+"\n");
			} else {
				resOut.write("---- Info from context object ----\n");
				resOut.write("Application Name="+appName+"\n");
				resOut.write("Context Path="+context.getContextPath()+"\n");
				resOut.write("Real Path of Osap\n"+"servlet="+context.getRealPath("Osap")+"\n");
				resOut.write("Applicant Name="+context.getInitParameter("applicantName")+"\n");
			}
		}
		
		// Query string null check & html input empty check from UI.html (for Ajax test)
		if((request.getParameter("principal")==null ||request.getParameter("period").length()<=0)
				||(request.getParameter("interest")==null||request.getParameter("interest").length()<=0)
				||(request.getParameter("period")==null||request.getParameter("period").length()<=0)) {
			// If one of them is not valid at least, set the initial values from web.xml
			interest = context.getInitParameter(CONTEXT_INTEREST);
			period = context.getInitParameter(CONTEXT_PERIOD);
			principal = context.getInitParameter(CONTEXT_PRINCIPAL);
		} else {
			principal = request.getParameter("principal");
			period = request.getParameter("period");
			interest = request.getParameter("interest");
		}
		
		
		// To use Model part
		double monthlyPaymentFormatted = 0;
		Loan model = (Loan) context.getAttribute("mLoan");
		String errorMessage = null;

		try { // check if the values are valid in Model java class
			monthlyPaymentFormatted = model.computePayment(principal,interest,period);
			monthlyPaymentFormatted = ((double)Math.round(monthlyPaymentFormatted*10))/10;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			errorMessage = e.getMessage();
		}
		
		// If there is error in monthly payment, it's set 0
		request.getSession().setAttribute("payment", monthlyPaymentFormatted);
		request.getSession().setAttribute("principal", principal);
		request.getSession().setAttribute("interest", interest);
		request.getSession().setAttribute("period", period);
		
		// If the request URL contains 'Ajax', send JSON format information
		if(request.getPathInfo()!=null&&request.getPathInfo().indexOf("Ajax")>=0) {
			response.setContentType("application/json");
			//System.out.println("Ajax call");
			resOut.append("{\"pr\":");
			resOut.append(principal);
			resOut.append(",\"mp\":");
			resOut.append(String.valueOf(monthlyPaymentFormatted)+"}");
			resOut.flush();
		} else {
			// if it's not from 'Ajax'
			String textViewString = textView(Double.valueOf(principal), Double.valueOf(interest),
					Double.valueOf(period), monthlyPaymentFormatted);
	
			if(request.getParameter("out") != null && request.getParameter("out").equals("html")) {
				//show the monthly payment with UI
				String target = "/ResultsView.jsp";
				request.getRequestDispatcher(target).forward(request, response);
			} else if(request.getParameter("out") != null && request.getParameter("out").equals("text")) {
				// show the monthly payment in text
				if(errorMessage != null) resOut.write(errorMessage);
				else resOut.write(textViewString);
			} else if(request.getParameter("out")==null) resOut.write(textViewString);
		}
	}
	
	private String textView(double principal, double interest, double period, double monthlyPaymentFormatted) {
		String result = "---- Monthly payments ----\n"
						+"Based on Principal="+String.format("%.1f", principal)
						+" Period="+String.format("%.1f", period)
						+" Interest="+String.format("%.1f", interest)
						+"\n"+"Monthly payments: "+monthlyPaymentFormatted+"\n";
		return result;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		ServletContext context = getServletContext();
		
		// The context object is initialized with data from web.xml
		appName = context.getInitParameter(CONTEXT_APP_NAME);
		interest = context.getInitParameter(CONTEXT_INTEREST);
		period = context.getInitParameter(CONTEXT_PERIOD);
		principal = context.getInitParameter(CONTEXT_PRINCIPAL);

		// set model
		Loan model=Loan.getInstance();
		context.setAttribute("mLoan", model);
	}

}
