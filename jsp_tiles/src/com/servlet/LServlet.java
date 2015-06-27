package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Session;

import com.bift.Common;
import com.bift.MD5;
import com.model.Student;
import com.model.Upload;

/**
 * Servlet implementation class Login
 */
public class LServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String CONTENT_TYPE = "text/html; charset=GBK";
    public  StringBuffer sb = new StringBuffer();
    public  String contenttype = ""; 
    public  String flag = ""; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String password1=request.getParameter("password");
		String confirmPassword=request.getParameter("confirmPassword");
		
		if(!password1.equals(confirmPassword)){
			response.sendRedirect("login.jsp?error=yes");
		}else{
			
			Connection con=Common.getConnection();
			Student student=new Student();
			student.setName(request.getParameter("name"));
			student.setMail(request.getParameter("mail")+request.getParameter("com"));
			student.setImage(request.getParameter("imagePath"));
			
			MD5 md=new MD5();
			String password=md.toDigest(request.getParameter("password"));
			student.setPassword(password);
			
			student.setSex(request.getParameter("sex"));
			student.setBirthday(request.getParameter("year")+"-"+request.getParameter("month")+"-"+request.getParameter("day"));
			student.setCity(request.getParameter("city"));
			
			String sql="insert into student values(?,?,?,?,?,?,?)";
			try{
				PreparedStatement ps=con.prepareStatement(sql);
				ps.setString(1, student.getName());
				ps.setString(2, student.getPassword());
				ps.setString(3, student.getSex());
				ps.setString(4, student.getBirthday());
				ps.setString(5, student.getCity());
				ps.setString(6, student.getImage());
				ps.setString(7, student.getMail());
				ps.executeUpdate();
			}catch(Exception e){
				e.printStackTrace();
			}		
			Common.freeConnection(con);
			request.getSession().setAttribute("student", student);
			response.sendRedirect("changIndex.jsp");
		}
		
	}

}

