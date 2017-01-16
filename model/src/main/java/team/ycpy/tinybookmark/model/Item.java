package team.ycpy.tinybookmark.model;

import java.sql.PreparedStatement;

public class Item {
	private String Ino;
	private String Iname;
	private String Fno;
	
	public String getIno(){
		return this.Ino;
	}
	
	public String getIname(){
		return this.Iname;
	}
	
	public String getFno(){
		return this.Fno;
	}
	
	public void setIno(String str){
		this.Ino = str;
	}
	
	public void setIname(String str){
		this.Iname = str;
	}
	
	public void setFno(String str){
		this.Fno = str;
	}
	
	public PreparedStatement insertItem(ConnectServer con){
		String sql = "insert into Item(Ino,Iname,Fno) values (?,?,?);";
		
		try {
			PreparedStatement stmt = con.prepare(sql);
			stmt.setString(1, this.getIno());
			stmt.setString(2, this.getIname());
			stmt.setString(3, this.getFno());
			
			return stmt;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	public PreparedStatement deleteItem(ConnectServer con){
		String sql = "delete from Item where Ino=?";
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
	
	public PreparedStatement updateItem(ConnectServer con){
		int index = 1;
		
		String sql = "update Item set ";
		if(this.getIname()!=null)sql += "Iname=?,";
		if(this.getFno()!=null)sql += "Fno=? ";
		sql += "where Ino=?";
		PreparedStatement stmt;
		
		try{
			stmt = con.prepare(sql);
			if(this.getIname()!=null)stmt.setString(index++, this.getIname());
			if(this.getFno()!=null)stmt.setString(index++, this.getFno());
			stmt.setString(index, this.getIno());
			return stmt;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public PreparedStatement queryItem(ConnectServer con, String req){
		int index = 1;
		
		String sql = "Select" + req + "from Item ";
		if(this.getIno()!=null){
			if(index == 1){
				sql += "where ";
				index++;
			}
			else sql += "AND ";
			sql += "Ino=? ";
		}
		if(this.getIname()!=null){
			if(index == 1){
				sql += "where ";
				index++;
			}
			else sql += "AND ";
			sql += "Iname=? ";
		}
		if(this.getFno()!=null){
			if(index == 1){
				sql += "where ";
				index++;
			}
			else sql += "AND ";
			sql += "Fno=? ";
		}
		sql += ";";
		
		index = 1;
		PreparedStatement stmt;
		
		try{
			stmt = con.prepare(sql);
			if(this.getIno()!=null)stmt.setString(index++, this.getIno());
			if(this.getIname()!=null)stmt.setString(index++, this.getIname());
			if(this.getFno()!=null)stmt.setString(index++, this.getFno());
			return stmt;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}


}
