package com.test;
import java.io.*;
import java.util.*;
public class Test {
   public static void main(String[] argc)throws Exception{
	   Properties p=new Properties();
	   p.load(new FileInputStream(new File("question.properties")));
       //p.list(System.out);
	   System.out.println(p.containsKey(""+'A'));
   }
   
}
