package com.bift;
import java.sql.*;
import java.io.*;
public class BigObject {
    public static void imageWrite(String str)throws Exception{
    	Connection con=Common.getConnection();
    	String sql="insert into example values(null,?)";
    	PreparedStatement ps=con.prepareStatement(sql);
    	FileInputStream fis=new FileInputStream(str);
    	ps.setBinaryStream(1, fis, fis.available());
    	System.out.println(ps);
    	ps.executeUpdate();
    	fis.close();
    	Common.freeConnection(con);
    }
    public static void imageRead(int id,String path)throws Exception{
    	Connection con=Common.getConnection();
    	String sql="select * from example where id<=?";
    	PreparedStatement ps=con.prepareStatement(sql);
    	ps.setInt(1, id);
    	ResultSet rs=ps.executeQuery();
    	while(rs.next()){
    		InputStream is=rs.getBinaryStream("image");
    		FileOutputStream fos=new FileOutputStream(path+rs.getInt("id")+Math.random()+".jpg");
    		byte b[]=new byte[1024*4];
    		int len=0;
    		while((len=is.read(b))!=-1){
    			 fos.write(b,0,len);
    		}
    		fos.close();
    		is.close();
    	}
    	Common.freeConnection(con);
    }
	public static void main(String[] args)throws Exception {
		// TODO 自动生成的方法存根
		imageWrite("d:\\test.jpg");
		imageRead(4,"c:\\");
	}

}
