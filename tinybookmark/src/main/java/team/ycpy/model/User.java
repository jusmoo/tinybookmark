package team.ycpy.model;

import java.sql.*;

public class User {
	private String username;
	private String nickname;
	private String password;
	private String rootfoldid;
	
	public User(){
		this.username = null;
		this.nickname = null;
		this.password = null;
		this.rootfoldid = null;
	}
	
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
	
	public PreparedStatement insertUser(ConnectServer con){
		String sql = "insert into User(username,nickname,password,rootfoldid) values (?,?,?,?);";
		
		try {
			PreparedStatement stmt = con.prepare(sql);
			stmt.setString(1, this.getusername());
			stmt.setString(2, getnickname());
			stmt.setString(3, getpassword());
			stmt.setString(4, getrootfoldid());
			
			return stmt;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	public PreparedStatement deleteUser(ConnectServer con){
		String sql = "delete from User where username=?";
		try{
			PreparedStatement stmt = con.prepare(sql);
			stmt.setString(1, this.getusername());
			return stmt;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public PreparedStatement updateUser(ConnectServer con){
		int index = 1;
		
		String sql = "update User set ";
		if(this.getnickname()!=null)sql += "nickname=?,";
		if(this.getpassword()!=null)sql += "password=?,";
		if(this.getrootfoldid()!=null)sql += "rootfoldid=? ";
		sql += "where username=?";
		PreparedStatement stmt;
		
		try{
			stmt = con.prepare(sql);
			if(this.getnickname()!=null)stmt.setString(index++, this.getnickname());
			if(this.getpassword()!=null)stmt.setString(index++, this.getpassword());
			if(this.getrootfoldid()!=null)stmt.setString(index++, this.getrootfoldid());
			stmt.setString(index, this.getusername());
			return stmt;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public PreparedStatement queryUser(ConnectServer con, String req){
		int index = 1;
		
		String sql = "select" + req + "from User where 1=1";
		if(this.getusername()!=null)sql += " AND username=?";
		if(this.getnickname()!=null)sql += " AND nickname=?";
		sql += ";";
		
		PreparedStatement stmt;
		
		try{
			stmt = con.prepare(sql);
			if(this.getusername()!=null)stmt.setString(index++, this.getusername());
			if(this.getnickname()!=null)stmt.setString(index, this.getnickname());
			return stmt;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

}
