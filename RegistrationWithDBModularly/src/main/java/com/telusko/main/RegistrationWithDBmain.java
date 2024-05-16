package com.telusko.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.telusko.utility.RegistrationApp;
import com.telusko.utility.RegistrationJDBCUtility;


@WebServlet("/RegistrationWithDBmain")
public class RegistrationWithDBmain extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String empId=request.getParameter("empId");
		String empName=request.getParameter("empName");
		String empAge=request.getParameter("empAge");
		String empCity=request.getParameter("empCity");
		Connection connect=null;
		
		RegistrationApp regApp=new RegistrationApp();
		
		regApp.setEmpId(empId);
		regApp.setEmpName(empName);
		regApp.setEmpAge(empAge);
		regApp.setEmpCity(empCity);
		
		regApp.connection();
		
		int rows=regApp.getAffectedRow();

		PrintWriter writer=response.getWriter();
		try {

//			writer.println("<html><head><body bgcolour='cyan'><h2><marquee>Welcome to Employee Application</marquee></h2>");
			if (rows !=0) {
//				writer.println("<h2>Registration Successful</h2>");
//				writer.println("</body>");
//				writer.println("</head>");	
//				writer.println("</html>");
				response.sendRedirect("/RegistrationWithDBModularly/result.jsp");
			}
				else if(rows==0){
//					writer.println("<h2>Registration Unsuccessful</h2>");
//					writer.println("</body>");
//					writer.println("</head>");	
//					writer.println("</html>");
					response.sendRedirect("/RegistrationWithDBModularly/result_fail.jsp");
				}
				else {
					System.out.println("Result fail rows==unknown");
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				RegistrationJDBCUtility.closeResources(connect, null, null);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
