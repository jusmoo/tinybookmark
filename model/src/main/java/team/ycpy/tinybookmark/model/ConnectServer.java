package team.ycpy.tinybookmark.model;

import java.sql.*;

//connect the server and execute given sql statement
public class ConnectServer {
	private java.sql.Connection conn;
	private boolean isLink;
	private String username;
	private String password;
	private final String addr = "jdbc:mysql://localhost:3306";
	private final String driver = "com.mysql.jdbc.Driver";
	
	//users can just modify the username and password
	public void setusername(String user){
		this.username = user;
	}
	
	public void setpassword(String pass){
		this.password = pass;
	}
	
	ConnectServer(String username,String password){
		this.isLink = false;
		this.setusername(username);
		this.setpassword(password);
	}
	
	//link the mysql, if succeed, return value 201, else return value 200
	public int link(){
		try{
			Class.forName(driver);
			this.conn = DriverManager.getConnection(this.addr, this.username, this.password);
		}
		catch(Exception e){
			System.out.println(e.getStackTrace());
			return 201;
		}
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
	public ResultSet executeQuery(String sql){
		if(!this.isLink)return null;
		
		ResultSet res;
		Statement stmt;
		
		try{
			stmt = this.conn.createStatement();
			res = stmt.executeQuery(sql);
		}
		catch(Exception e){
			System.out.print(e.getStackTrace());
			return null;
		}
		
		return res;
	}
	
	public int executeInsert(String sql){
		if(!this.isLink)return 201;
		
		Statement stmt;
		
		try{
			stmt = this.conn.createStatement();
			stmt.execute(sql);
		}
		catch(Exception e){
			System.out.println(e.getStackTrace());
			return 201;
		}
		
		return 200;
	}
	
	//execute delete, if succeed, then return 200, return 201 otherwise
	public int executeDelete(String sql){
		if(!this.isLink)return 201;
		
		Statement stmt;
		
		try{
			stmt = this.conn.createStatement();
			stmt.execute(sql);
		}
		catch(Exception e){
			System.out.println(e.getStackTrace());
			return 201;
		}
		
		return 200;
	}
	
	//execute update, if succeed, then return 200, return 201 otherwise
	public int executeUpdate(String sql){
		if(!this.isLink)return 201;
		
		Statement stmt;
		
		try{
			stmt = this.conn.createStatement();
			stmt.execute(sql);
		}
		catch(Exception e){
			System.out.println(e.getStackTrace());
			return 201;
		}
		
		return 200;
	}
}
