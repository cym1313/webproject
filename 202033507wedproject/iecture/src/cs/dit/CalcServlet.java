package cs.dit;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CalcServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		int num1, num2;
		int result;
		String a;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();	
		num1 = Integer.parseInt(request.getParameter("num1"));
		num2 = Integer.parseInt(request.getParameter("num2"));
		a = request.getParameter("operator");
		
		result = calc(num1, num2, a);
		
		out.println("<html>");
		out.println("<head><title>");
		out.println("CalcServlet");
		out.println("</title></head>");
		out.println("<body>");
		out.println(num1 + " " + a + " " + num2 + " = " + result);
		out.println("</body></html>");
	}
	
	public int calc(int num1, int num2, String a) {
		int result = 0;
		
		if(a.equals("+")) {
			result = num1 + num2;
		}
		else if(a.equals("-")) {
			result = num1 - num2;
		}
		else if(a.equals("*")) {
			result = num1 * num2;
		}
		else if(a.equals("/")) {
			result = num1 / num2;
		}
		return result;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		doGet(request, response);
	}

}