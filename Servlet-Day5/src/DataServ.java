

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/datserv")
public class DataServ extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html");
		PrintWriter out= response.getWriter();
		//out.print("Welcome to Servlet Page");
		
		out.print("<html>");
		out.print("<head>");
		out.print("<title>Ragister</title>");
		out.print("</head>");
		
		String url ="jdbc:mysql://localhost:3306/servdb",uname="root",pass="abc123";
		
		
			ServletContext context=getServletContext();
			String driver=context.getInitParameter("driver");
			
			String name=request.getParameter("uname");
			String email=request.getParameter("uemail");
			String phone=request.getParameter("uphone");
			String passw=request.getParameter("upass");
			
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
		//	out.print("drvier");
			try(Connection con= DriverManager.getConnection(url,uname,pass);
					PreparedStatement ps=con.prepareStatement("INSERT INTO srvtb VALUES(?,?,?,?)");) {
			
					//out.print("Statement");
					ps.setString(1, name);
					ps.setString(2, email);
					ps.setString(3, phone);
					ps.setString(4, passw);
					
					int a=ps.executeUpdate();
					if(a>0)
					{
						out.print("<center>");
						out.print("<div style='margin-top:15pc;font-style: semibold;background-color: paleturquoise;width:30%; padding:20px;border-radius: 8px;font-family:arial;'>");		
						out.print("<center><h2>Notification</h2></center>");
						out.print("<hr>");
						out.print("<center style='color:#800080;'><h2>Data Inserted Successfully.</h2></center>");
						out.print("</div>");
						out.print("</center>");
					}
					else
					{
						out.print("Data not inserted");
					}
					out.close();
					//out.print("closed");
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			out.print("</html>");
			} 
			//Connection con = null;
			
			//PreparedStatement ps;
		
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		doGet(request, response);
	}

	
	
}
