package team.ycpy.model;


import java.sql.*;

import java.util.*;
import com.mysql.jdbc.Driver;


//connect the server and execute given sql statement
public class ConnectServer {
	private java.sql.Connection conn;
	private boolean isLink;
	private String username;
	private String password;
	private final String addr = "jdbc:mysql://localhost:3306/tinybookmark";
	private final String driver = "com.mysql.jdbc.Driver";
	
	//users can just modify the username and password
	public void setusername(String user){
		this.username = user;
	}
	
	public void setpassword(String pass){
		this.password = pass;
	}
	
	public ConnectServer(String username,String password){
		this.isLink = false;
		this.setusername(username);
		this.setpassword(password);
	}
	
	//link the mysql, if succeed, return value 201, else return value 200
	public int link() throws Exception{
		Driver drivers = new com.mysql.jdbc.Driver();
		Properties info = new Properties();
		info.put("user", this.username);
		info.put("password", this.password);
		this.conn = drivers.connect(addr,info);
		this.isLink = true;
		return 200;
	}
	
	//use to close the link
	public int close(){
		try{
			if(this.isLink){
				this.conn.close();
				this.isLink = false;
			}
		}
		catch(Exception e){
			System.out.println(e.getStackTrace());
			return 201;
		}
		return 200;
	}
	
	//execute query, if succeed, return result, return null otherwise
	public ResultSet executeQuery(java.sql.PreparedStatement sql){
		if(!this.isLink)return null;
		
		ResultSet res;
		
		try{
			res = sql.executeQuery();
		}
		catch(Exception e){
			System.out.print(e.getStackTrace());
			return null;
		}
		
		return res;
	}
	
	public void executeInsert (java.sql.PreparedStatement sql) throws Exception{
		sql.execute();
	}
	
	//execute delete, if succeed, then return 200, return 201 otherwise
	public int executeDelete(java.sql.PreparedStatement sql){
		if(!this.isLink)return 201;
		
		try{
			sql.execute();
		}
		catch(Exception e){
			System.out.println(e.getStackTrace());
			return 201;
		}
		
		return 200;
	}
	
	//execute update, if succeed, then return 200, return 201 otherwise
	public void executeUpdate (java.sql.PreparedStatement sql) throws Exception{
		sql.execute();
	}
	
	public java.sql.PreparedStatement prepare(String sql)throws Exception{
			return this.conn.prepareStatement(sql);
	}
}
