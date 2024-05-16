package com.telusko.utility;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrationApp {

	private String empId;
	private String empName;
	private String empAge;
	private String empCity;
	private int affectedRow;
	
	
	public int getAffectedRow() {
		return affectedRow;
	}
	public void setAffectedRow(int affectedRow) {
		this.affectedRow = affectedRow;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpAge() {
		return empAge;
	}
	public void setEmpAge(String empAge) {
		this.empAge = empAge;
	}
	public String getEmpCity() {
		return empCity;
	}
	public void setEmpCity(String empCity) {
		this.empCity = empCity;
	}
	
	
	public void connection() {
			Connection connect=null;
			String query="INSERT INTO employee (empId,empName,empAge,empCity) "
					+ "VALUES(?,?,?,?)";
			PreparedStatement pstmt=null;
			empId=getEmpId();
			empName=getEmpName();
			empAge=getEmpAge();
			empCity=getEmpCity();
			System.out.println(empId+" "+empName+" "+empAge);
	
	
		try {
		connect=RegistrationJDBCUtility.jdbcConnection();
		if(connect!=null)
			pstmt=connect.prepareStatement(query);
		if(pstmt!=null) {
			pstmt.setString(1, empId);
			pstmt.setString(2, empName);
			pstmt.setString(3, empAge);
			pstmt.setString(4, empCity);
			
			boolean status=pstmt.execute();
			if (status==true)
				affectedRow=1;
			else
				affectedRow=0;
			setAffectedRow(affectedRow);
		}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				RegistrationJDBCUtility.closeResources(connect, pstmt, null);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
}

}
