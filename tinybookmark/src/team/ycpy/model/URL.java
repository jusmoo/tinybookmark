package team.ycpy.model;


import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

public class URL {
	private String Ino;
	private String Iname;
	private String Uaddr;
	
	public URL(){
		this.Ino = null;
		this.Iname = null;
		this.Uaddr = null;
	}
	
	public String getIno() {
		return Ino;
	}
	public void setIno(String ino) {
		Ino = ino;
	}
	public String getIname() {
		return Iname;
	}
	public void setIname(String iname) {
		Iname = iname;
	}
	public String getUaddr() {
		return Uaddr;
	}
	public void setUaddr(String uaddr) {
		Uaddr = uaddr;
	}
	public PreparedStatement queryURL(ConnectServer con,List<Map<String,Object>> l){
		StringBuilder sb=new StringBuilder();
		sb.append("SELECT * FROM URL WHERE 1=1");
		for (Map<String, Object> map : l) {
			sb.append(" and "+map.get("name")+" "+map.get("relation")+" '"+map.get("value")+"'");
		}
		try {
			return con.prepare(sb.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	public PreparedStatement deleteURL(ConnectServer con){
		String sql="DELETE FROM URL WHERE Ino='"+this.getIno()+"'";
		try {
			return con.prepare(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public PreparedStatement updateURL(ConnectServer con){
		String sql="UPDATE URL"+
				" SET"+
				" Iname='"+this.getIname()+"',Uaddr='"+this.getUaddr()+
				"' WHERE Ino='"+this.getIno()+"'";
		try {
			return con.prepare(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public PreparedStatement insertURL(ConnectServer con){
		String sql = "insert into URL(Ino,Iname,Uaddr) values ('?','?','?');";
		
		try {
			PreparedStatement stmt = con.prepare(sql);
			stmt.setString(1, this.getIno());
			stmt.setString(2, this.getIname());
			stmt.setString(3, this.getUaddr());
			
			return stmt;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
