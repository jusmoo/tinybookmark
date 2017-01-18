package team.ycpy.tinybookmark.model;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

public class Comment {
	private String CID;
	private String userid;
	private String Ino;
	private String Content;
	
	public Comment(){
		this.CID = null;
		this.userid = null;
		this.Ino = null;
		this.Content = null;
	}
	
	public String getCID() {
		return CID;
	}
	public void setCID(String cID) {
		CID = cID;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getIno() {
		return Ino;
	}
	public void setIno(String ino) {
		Ino = ino;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public PreparedStatement queryComment(ConnectServer con,List<Map<String,Object>> l){
		StringBuilder sb=new StringBuilder();
		sb.append("SELECT * FROM Comment WHERE 1=1");
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
	public PreparedStatement deleteComment(ConnectServer con){
		String sql="DELETE FROM Comment WHERE CID="+this.getCID();
		try {
			return con.prepare(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public PreparedStatement updateComment(ConnectServer con){
		String sql="UPDATE Comment"+
				" SET"+
				" userid="+this.getUserid()+",Ino="+this.getIno()+",Content="+this.getContent()+
				" WHERE CID="+this.getCID();
		try {
			return con.prepare(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public PreparedStatement insertComment(ConnectServer con){
		String sql="INSERT INTO Comment(CID,userid,Ino,Content)"+
				" VALUES("+
				this.getCID()+","+this.getUserid()+","+this.getIno()+","+this.getContent()+
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
