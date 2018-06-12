package com.hibername.lesson_01_jdbc_test;

import java.sql.Connection;
import java.sql.DriverManager;

public class Jdbc {

	public static void main(String[] args) {
	
		String jdbcUrl = "jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false";
		String user = "olegDB";
		String pass = "olegDB";
		
		try{
			
			System.out.println("start");
			Connection conn = DriverManager.getConnection(jdbcUrl , user, pass);
			System.out.println("connection success");
		
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
