package com.bift;
import java.util.*;
public class Test {
    public static void main(String[] argc){
    	float f=1.0f;
    	System.out.println(Math.ceil(f));
    	MD5 md=new MD5();

    	String x=md.toDigest("123456");
    	System.out.println(x);
    	
    	String y="D:\\JAVA\\workspace\\homework\\WebContent\\UPLOAD\\1434437305316";
    	int x1=y.indexOf('J');
    	System.out.println(y.length());
    	String path=(String) y.subSequence(38, 58);
    	System.out.println(path);
    	System.out.println("./"+y.substring(38,44)+"/"+y.substring(45, 58)+".jpg");
    	

    }
}
