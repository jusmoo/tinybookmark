package team.ycpy.tinybookmark.model;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

public class Collect {
	private String Fno;
	private String Ino;
	
	public Collect(){
		this.Fno = null;
		this.Ino = null;
	}
	
	public String getFno() {
		return Fno;
	}
	public void setFno(String fno) {
		Fno = fno;
	}
	public String getIno() {
		return Ino;
	}
	public void setIno(String ino) {
		Ino = ino;
	}
	public PreparedStatement queryCollect(ConnectServer con,List<Map<String,Object>> l){
		StringBuilder sb=new StringBuilder();
		sb.append("SELECT * FROM Collect WHERE 1=1");
		for (Map<String, Object> map : l) {
			sb.append(" and "+map.get("name")+" "+map.get("relation")+" "+map.get("value"));
		}
		try {
			return con.prepare(sb.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public PreparedStatement deleteCollect(ConnectServer con){
		String sql="DELETE FROM Collect WHERE Fno="+this.getFno()+" and Ino="+this.getIno();
		try {
			return con.prepare(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/*public PreparedStatement updateCollect(ConnectServer con){
		String sql="UPDATE Collect"+
				" SET"+
				" Fno="+this.getFno()+",Ino="+this.getIno()+
				" WHERE Ino="+this.getIno()+" and Fno="+this.getFno();
		try {
			return con.prepare(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}*/
	public PreparedStatement insertCollect(ConnectServer con){
		String sql="INSERT INTO Collect(Fno,Ino) "+
				"VALUES("+
				this.getFno()+","+this.getIno()+
				")";
		try {
			return con.prepare(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
