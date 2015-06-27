package com.servlet;
import java.io.*;
import java.util.*;
import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class VoteServlet
 */
public class VoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VoteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		File f=new File("c:\\vote.txt");
	    String vote=request.getParameter("vote");
	    Properties p=new Properties();
	    if(f.exists()){
	       p.load(new FileInputStream(f));
	       p.setProperty(vote,""+(Integer.parseInt(p.getProperty(vote))+1));
	       f.delete();
	       p.store(new FileOutputStream(f),"");
	       HttpSession session=request.getSession();
	       session.setAttribute("question", p);
	    }else{//是否是第一个人投票
	        f.createNewFile();
	        ServletContext application=this.getServletContext();
	        Properties question=(Properties)application.getAttribute("question");
	        Enumeration e=question.keys();
	        while(e.hasMoreElements()){
	            String key=(String)e.nextElement();
	            if(key.equals("Q")==false){
	               if(key.equals(vote)){
	                   p.setProperty(key,"1");
	               }else{
	                   p.setProperty(key,"0");
	               }
	            }
	        }
	        p.store(new FileOutputStream(f),"");
	        HttpSession session=request.getSession();
		    session.setAttribute("question", p);
	    }
	    response.sendRedirect("showvote.jsp");
	}

}
