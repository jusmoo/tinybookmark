package team.ycpy.model;


import java.sql.PreparedStatement;

public class Item {
	private String Ino;
	private String Iname;
	private String Fno;
	private int Itype;
	
	public Item(){
		this.Ino = null;
		this.Iname = null;
		this.Fno = null;
		this.Itype = -1;
	}
	
	public String getIno(){
		return this.Ino;
	}
	
	public String getIname(){
		return this.Iname;
	}
	
	public String getFno(){
		return this.Fno;
	}
	
	public int getItype(){
		return this.Itype;
	}
	
	public void setItype(int newf){
		this.Itype = newf;
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
		String sql = "insert into Item(Ino,Iname,Fno,Itype) values (?,?,?,?);";
		
		try {
			PreparedStatement stmt = con.prepare(sql);
			stmt.setString(1, this.getIno());
			stmt.setString(2, this.getIname());
			stmt.setString(3, this.getFno());
			stmt.setInt(4, Itype);
			
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
		
		String sql = "select" + req + "from Item where 1=1";
		if(this.getIno()!=null)sql += " AND Ino=?";
		if(this.getIname()!=null)sql += " AND Iname=?";
		if(this.getFno()!=null)sql += " AND Fno=?";
		if(this.getItype()!=-1)sql += " AND Itype=?";
		sql += ";";
		
		PreparedStatement stmt;
		
		try{
			stmt = con.prepare(sql);
			if(this.getIno()!=null)stmt.setString(index++, this.getIno());
			if(this.getIname()!=null)stmt.setString(index++, this.getIname());
			if(this.getFno()!=null)stmt.setString(index++, this.getFno());
			if(this.getItype()!=-1)stmt.setInt(index,this.getItype());
			return stmt;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}


}
