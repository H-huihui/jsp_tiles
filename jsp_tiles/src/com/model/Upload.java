package com.model;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

public class Upload {
	public static String codingConvert(String str){
		try{
			return new String(str.getBytes("ISO8859-1"),"GBK");
		}catch(Exception e){
			e.printStackTrace();
			return str;
		}
		
	}
	/***********��ȡ�ֽ���*****************/
    public static String getFlag(String contenttype) {
    	String flag="";
        int index=contenttype.indexOf("boundary=");
        if(index != -1) {
            flag = contenttype.substring(index + 9);
        }
        flag = "--" + flag;
        System.out.println(flag);
        return flag;
    }
    
    /*��ȡ�ϴ���*/
    public static StringBuffer get_Content(HttpServletRequest request){
    	 StringBuffer sb=new StringBuffer();    	 
         byte b[] = new byte[1024*10];
         DataInputStream dis = null;
         try {
             dis = new DataInputStream(request.getInputStream());
         } catch(IOException ex) {
             ex.printStackTrace();
         }
         int n;
         try{
        	 while((n = dis.read(b)) != -1) {
                 sb = sb.append(new String(b, 0, n, "ISO8859-1"));
             } 
         }catch(Exception e){
        	 e.printStackTrace();
         }
         
         return sb;
    }

    /**********************��ȡ�ϴ��ļ�����չ��*******************/
    public static  String getExtension(StringBuffer sb) {         
        String file_name=getFileName(sb);
        if(file_name==null || file_name.length()==0){
            return null;
        }
        int index = file_name.lastIndexOf(".");
        if (index!=-1){
        	file_name = file_name.substring(index + 1);
            return file_name.trim();
        }else{
        	return null;
        }        
    }
    /*****************��ȡ�ϴ��ļ����ļ���************************/
    public static  String getFileName(StringBuffer sb) {
        String content = sb.toString();
        String str = "filename=\"";
        int index = content.indexOf(str);
        if (index!=-1){
        	content = content.substring(index + str.length());        	
            index = content.indexOf("\"");
            if (index!=-1){
            	content = content.substring(0, index);            	 
                index = content.lastIndexOf("\\");
                if (index!=-1){
                	content = content.substring(index + 1);                	
                    return content.trim();
                }else{
                	return content;
                }
                
            }else{
            	return null;
            }
            
        }else{
        	return null;
        }        
    }
    /***********��ȡ�ϴ��ļ�������*****************/
    public static  String getFileContent(StringBuffer sb,String flag) {//
        String filename = getFileName(sb);
        if(filename == null || filename.length() == 0) { //û���ϴ��ļ�
            return null;
        }
        String result = sb.toString();
        int index = result.indexOf("Content-Type:");
        if (index!=-1){
        	result = result.substring(index);
            String str = "\r\n\r\n";
            index = result.indexOf(str);
            if(index!=-1){
            	result = result.substring(index + str.length());
                index = result.indexOf(flag);
                if(index!=-1){
                	result = result.substring(0, index);
                    return result;
                }else{
                	return null;
                }
                
            }else{
            	return null;
            }
            
        }else{
        	return null;
        }        
    }
    /*************���ϴ��ļ�������Ӳ��********************/
    //����filename ���û�������ļ�������������׺��
    public static  void saveFile(StringBuffer sb,String flag,String filename) throws IOException {
        String content = getFileContent(sb,flag);
        if(content == null || content.length() == 0) {
            System.out.println("ʲôҲû���ϴ�,�ϴ���Ϣ���޷��õ�");
            return;
        }        
        String extension = getExtension(sb);//��ȡ�ϴ��ļ��ĺ�׺��
        if(extension.length()==0){
            System.out.println("�ϴ�ʱ,û��ѡ���ϴ��ļ�");
            return;
        }
        File f = new File(filename + "." + extension);
        f.deleteOnExit();
        FileOutputStream fos = new FileOutputStream(f);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        bos.write(content.getBytes("ISO-8859-1"));
        bos.flush();
        bos.close();
    }
}
