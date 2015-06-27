package com.model;

public class Student {
   private int sid;
   private String name;
   private String mail;
   private String password;
   private String sex;
   private String hobby;
   private String birthday;
   private String city;
   private String image;
   
   public int getSid(){
	   return this.sid;
   }
   public String getName(){
	   return this.name;
   }
   public String getBirthday(){
	   return this.birthday;
   }
   public String getMail(){
	   return this.mail;
   }
   public String getCity(){
	   return this.city;
   }
   public String getImage(){
	   return this.image;
   }
   public String getSex(){
	   return this.sex;
   }
   public String getPassword(){
	   return this.password;
   }
   public String getHobby(){
	   return this.hobby;
   }
   public void setSid(int sid){
	   this.sid=sid;
   }
   public void setName(String name){
	   this.name=name;
   }
   public void setPassword(String password){
	   this.password=password;
   }
   public void setHobby(String hobby){
	   this.hobby=hobby;
   }
   public void setSex(String sex){
	   this.sex=sex;
   }
   public void setMail(String mail){
	   this.mail=mail;
   }
   public void setCity(String city){
	   this.city=city;
   }
   public void setImage(String image){
	   this.image=image;
   }
   public void setBirthday(String birthday){
	   this.birthday=birthday;
   }
}



















