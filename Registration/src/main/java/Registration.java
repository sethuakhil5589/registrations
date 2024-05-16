

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/reg")
public class Registration extends HttpServlet {

    
    public Registration() {
        
       
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name=request.getParameter("uname");
		String city=request.getParameter("ucity");
		String phoneNumber=request.getParameter("phoneNumber");
		
		PrintWriter writer=response.getWriter();
		writer.println("<html><head><body bgcolour='cyan'><h2><marquee>Welcome to Alpha Application</marquee></h2>");
		writer.println("<table><tr> <th>Name</th> <th>City</th> <th>Phone Number</th> </tr></table>");
		writer.println("<tr><td>"+name+"</td></tr>"+city+"<tr><td>"+phoneNumber+"</td></tr>");
		writer.println("</body>");
		writer.println("</head>");	
		writer.println("</html>");
		
		
		writer.close();
	}

}
