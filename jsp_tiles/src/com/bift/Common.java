package com.bift;
import java.sql.*;
import java.util.*;
import java.io.*;
public class Common {
	public static String convert(String str){
		try{
			return new String(str.getBytes("ISO8859-1"),"GBK");
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
		
	}
	/*获取查询结果的记录数*/
    public static int getResultCount(String sql){
         int result=-1;
         ResultSet rs=excuteQuery(sql);
        try {
            rs.last();
            result=rs.getRow();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
         return result;
    }
    
   public static ResultSet excuteQuery(String sql){
	   ResultSet rs=null;
	   Connection con=getConnection();
	   Statement stat=null;
	   try{
		   stat=con.createStatement();
		   rs=stat.executeQuery(sql);
	   }catch(Exception e){
		   e.printStackTrace();
	   }
	   return rs;
   }
   public static int excuteUpdate(String sql){
	   int result=0;
	   Connection con=getConnection();
	   Statement stat=null;
	   try{
		   stat=con.createStatement();
		   result=stat.executeUpdate(sql);
	   }catch(Exception e){
		   e.printStackTrace();
	   }
	   freeConnection(con);
	   return result;
   }
   public static Connection getConnection(){
	   Connection con=null;
	   try{
		   Class.forName("org.gjt.mm.mysql.Driver");
		   con=DriverManager.getConnection("jdbc:mysql://localhost/test","root","");
	   }catch(Exception e){
		   e.printStackTrace();
	   }
	   return con;
   }
   public  static void freeConnection(Connection con){
	   if(con!=null){
		   try{
			   con.close();
		   }catch(Exception e){
			   e.printStackTrace();
		   }
		   
	   }
   }
   public static void main(String[] arg)throws Exception{
	   Connection con=getConnection();
	   System.out.println(con);
	   freeConnection(con);
   }
    
}
