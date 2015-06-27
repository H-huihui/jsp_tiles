package com.bift;
import java.sql.*;
public class A {

	public static void main(String[] args) throws Exception{
	     
		 Connection con=Common.getConnection();
	     System.out.println(con);
	     Statement stat=con.createStatement();
	     stat.executeUpdate("create database bift2");
	     Common.freeConnection(con);
	     new String("aaa".getBytes("ISO8859-1"),"GBK");
	   }


}
