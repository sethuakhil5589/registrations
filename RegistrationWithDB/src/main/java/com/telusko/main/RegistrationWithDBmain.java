 package com.telusko.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.telusko.utility.RegistrtationJDBCUtility;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegistrationWithDBmain")
public class RegistrationWithDBmain extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String empId=request.getParameter("empId");
		String empName=request.getParameter("empName");
		String empAge=request.getParameter("empAge");
		String empCity=request.getParameter("empCity");
		Connection connect=null;
		String query="INSERT INTO employee (empId,empName,empAge,empCity) "
				+ "VALUES(?,?,?,?)";
		PreparedStatement pstmt=null;
		PrintWriter writer=response.getWriter();
		
		try {
			connect=RegistrtationJDBCUtility.jdbcConnection();
			if(connect!=null)
				pstmt=connect.prepareStatement(query);
			if(pstmt!=null) {
				pstmt.setString(1, empId);
				pstmt.setString(2, empName);
				pstmt.setString(3, empAge);
				pstmt.setString(4, empCity);
				
				int affectedRows=pstmt.executeUpdate();
				writer.println("<html><head><body bgcolour='cyan'><h2><marquee>Welcome to Employee Application</marquee></h2>");
				if (affectedRows !=0) {
					writer.println("<h2>Registration Successful</h2>");
					writer.println("</body>");
					writer.println("</head>");	
					writer.println("</html>");
				}
				else {
					writer.println("<h2>Registration Unsuccessful</h2>");
					writer.println("</body>");
					writer.println("</head>");	
					writer.println("</html>");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				RegistrtationJDBCUtility.closeResources(connect, pstmt, null);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

}
