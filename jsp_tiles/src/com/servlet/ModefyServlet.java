package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bift.Common;
import com.model.Student;

/**
 * Servlet implementation class ModefyServlet
 */
public class ModefyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModefyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection con=Common.getConnection();
		Student student=new Student();
		student.setName(request.getParameter("name"));
		
		student.setMail(request.getParameter("mail")+request.getParameter("com"));
		
		student.setPassword(request.getParameter("password"));
		student.setSex(request.getParameter("sex"));
		student.setBirthday(request.getParameter("year")+"-"+request.getParameter("month")+"-"+request.getParameter("day"));
		student.setCity(request.getParameter("city"));
		
		String sql="alter table student modify values(?,?,?,?,?,null,?)";
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
