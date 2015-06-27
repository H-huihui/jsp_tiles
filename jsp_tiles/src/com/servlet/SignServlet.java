package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.*;
import com.bift.Common;

import java.sql.*;

/**
 * Servlet implementation class SignServlet
 */
public class SignServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection con=Common.getConnection();
		ResultSet Spassword = null;
		String mail=request.getParameter("email");
		String password=request.getParameter("password");
		String sql="select password from student where mail=?";
		try{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, mail);
			Spassword=ps.executeQuery();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		String Spass = null;
		try {
			if(Spassword.next()){
				Spass = Spassword.getString("password");
			}
			else{
				Spass="";
			}

		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		if(Spass.equals(password)){
			response.sendRedirect("changIndex.jsp");
		}
		else{
			response.sendRedirect("sign.jsp?error=yes");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
