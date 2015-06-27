package com.servlet;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bift.Common;
import com.model.Upload;
import com.model.Student;

import java.sql.*;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String CONTENT_TYPE = "text/html; charset=GBK";
    public  StringBuffer sb = new StringBuffer();
    public  String contenttype = ""; 
    public  String flag = ""; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  
		response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();        
        contenttype = request.getContentType();
        System.out.println(contenttype);
        flag=Upload.getFlag(contenttype); 
        sb=Upload.get_Content(request);
        Upload.saveFile(sb, flag, "D:\\JAVA\\workspace\\homework\\WebContent\\UPLOAD"+System.currentTimeMillis());
        
			
		Connection con=Common.getConnection();
		Student student=new Student();
		student.setName(request.getParameter("name"));
		
		student.setMail(request.getParameter("mail")+request.getParameter("com"));
		
		student.setPassword(request.getParameter("password"));
		student.setSex(request.getParameter("sex"));
		student.setBirthday(request.getParameter("year")+"-"+request.getParameter("month")+"-"+request.getParameter("day"));
		student.setCity(request.getParameter("city"));
		
		String sql="insert into student values(?,?,?,?,?,null,?)";
		try{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, student.getName());
			ps.setString(2, student.getPassword());
			ps.setString(3, student.getSex());
			ps.setString(4, student.getBirthday());
			ps.setString(5, student.getCity());
			ps.setString(6, student.getMail());
			ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}		
		Common.freeConnection(con);
		request.getSession().setAttribute("student", student);
		response.sendRedirect("changIndex.jsp");
	}

}
