package team.ycpy.model;

import java.sql.PreparedStatement;

public class Folder {
	private String Ino;
	private String Iname;
	private int Ftype;
	private String CreaterId;
	
	public Folder(){
		this.Ino = null;
		this.Iname = null;
		this.Ftype = -1;
		this.CreaterId = null;
	}
	
	public String getIno(){
		return this.Ino;
	}
	
	public String getIname(){
		return this.Iname;
	}
	
	public int getFtype(){
		return this.Ftype;
	}
	
	public String getCreaterId(){
		return this.CreaterId;
	}
	
	public void setIno(String str){
		this.Ino = str;
	}
	
	public void setIname(String str){
		this.Iname = str;
	}
	
	public void setCreaterId(String str){
		this.CreaterId = str;
	}
	
	public void setFtype(int ft){
		this.Ftype = ft;
	}
	
	public PreparedStatement insertUser(ConnectServer con){
		String sql = "insert into User(Ino,Iname,CreaterId,Ftype) values (?,?,?,?);";
		
		try {
			PreparedStatement stmt = con.prepare(sql);
			stmt.setString(1, this.getIno());
			stmt.setString(2, getIname());
			stmt.setString(3, getCreaterId());
			stmt.setInt(4, getFtype());
			
			return stmt;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	public PreparedStatement deleteFolder(ConnectServer con){
		String sql = "delete from Folder where Ino=?";
		try{
			PreparedStatement stmt = con.prepare(sql);
			stmt.setString(1, this.getIno());
			return stmt;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public PreparedStatement updateFolder(ConnectServer con){
		int index = 1;
		
		String sql = "update Folder set ";
		if(this.getIname()!=null)sql += "Iname=?,";
		if(this.getFtype()!=-1)sql += "Ftype=?,";
		if(this.getCreaterId()!=null)sql += "CreaterId=? ";
		sql += "where Ino=?";
		PreparedStatement stmt;
		
		try{
			stmt = con.prepare(sql);
			if(this.getIname()!=null)stmt.setString(index++, this.getIname());
			if(this.getFtype()!=-1)stmt.setInt(index++, this.getFtype());
			if(this.getCreaterId()!=null)stmt.setString(index+1, this.getCreaterId());
			stmt.setString(index, this.getIno());
			return stmt;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public PreparedStatement queryFolder(ConnectServer con, String req){
		int index = 1;
		
		String sql = "select" + req + "from Folder where 1=1";
		
		if(this.getIno()!=null)sql += " AND Ino=?";
		if(this.getIname()!=null)sql += " AND Iname=?";
		if(this.getFtype()!=-1)sql += " AND Ftype=?";
		if(this.getCreaterId()!=null)sql += " AND CreaterId=?";
		sql += ";";
		
		
		PreparedStatement stmt;
		
		try{
			stmt = con.prepare(sql);
			if(this.getIno()!=null)stmt.setString(index++, this.getIno());
			if(this.getIname()!=null)stmt.setString(index++, this.getIname());
			if(this.getFtype()!=-1)stmt.setInt(index++, this.getFtype());
			if(this.getCreaterId()!=null)stmt.setString(index, this.getCreaterId());
			return stmt;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

}
