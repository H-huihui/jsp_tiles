package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.Upload;

/**
 * Servlet implementation class FileServlet
 */
public class FileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String CONTENT_TYPE = "text/html; charset=GBK";
    public  StringBuffer sb = new StringBuffer();
    public  String contenttype = ""; 
    public  String flag = ""; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileServlet() {
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
		
		response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();        
        contenttype = request.getContentType();
        System.out.println(contenttype);
        flag=Upload.getFlag(contenttype); 
        sb=Upload.get_Content(request);
        String imagePath="D:\\JAVA\\workspace\\homework\\WebContent\\UPLOAD\\"+System.currentTimeMillis();
        Upload.saveFile(sb, flag, imagePath); 
        
        HttpSession session=request.getSession();
        session.setAttribute("imagePath", imagePath);
        response.sendRedirect("login.jsp?image="+imagePath);
	}

}
