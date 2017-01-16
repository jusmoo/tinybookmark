package team.ycpy.tinybookmark.model;

import java.sql.*;

public class User {
	private String username;
	private String nickname;
	private String password;
	private String rootfoldid;
	
	public String getusername(){
		return this.username;
	}
	
	public String getnickname(){
		return this.nickname;
	}
	
	public String getpassword(){
		return this.password;
	}
	
	public String getrootfoldid(){
		return this.rootfoldid;
	}
	
	public void setusername(String user){
		this.username = user;
	}
	
	public void setpassword(String pass){
		this.password = pass;
	}
	
	public void setnickname(String nick){
		this.nickname = nick;
	}
	
	public void setrootfoldid(String foldid){
		this.rootfoldid = foldid;
	}
	
	public PreparedStatement insertUser(){
		String sql = "insert into User(username,nickname,password,rootfoldid) values (?,?,?)";
		PreparedStatement stmt = 
	}
	
	public String deleteUser(){
		String sql = "delete from User where username=" + "'" + this.getusername() + "';";
		return sql;
	}
	
	public String updateUser(){
		String sql = "update User set"
	}

}
