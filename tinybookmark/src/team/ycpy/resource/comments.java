package team.ycpy.resource;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;

import team.ycpy.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Path("/comments")
public class comments {
	
	
	//delete given comment
	@DELETE
	@Produces("application/json")
	public Comment deleteComment(Comment in){
		ConnectServer con = new ConnectServer("root","Aaron12345");
		try{
			con.link();
			PreparedStatement stmt = in.deleteComment(con);
			con.executeDelete(stmt);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return in;
	}
	
	//create a new comment
	@POST
	@Produces("application/json")
	public Comment createComment(Comment in){
		ConnectServer con = new ConnectServer("root","Aaron12345");
		try{
			con.link();
			in.setCID(String.valueOf(new Date().getTime()));
			PreparedStatement stmt = in.insertComment(con);
			con.executeInsert(stmt);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return in;
	}
	
	@GET
	@Produces("application/json")
	public List<Comment> getComment(@Context HttpServletRequest request){
		Comment in = new Comment();
		List<Comment> cmts = new ArrayList<Comment> ();
		ResultSet res;
		ConnectServer con = new ConnectServer("root","Aaron12345");
		in.setIno(request.getParameter("Ino"));
			try {
				con.link();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			PreparedStatement stmt = in.queryComment(con);
			res=con.executeQuery(stmt);
			try {
				while(res.next()){
					Comment cmt = new Comment();
					cmt.setCID(res.getString("CID"));
					cmt.setContent("Content");
					cmt.setIno("Ino");
					cmt.setUserid("userid");
					cmts.add(cmt);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			return cmts;
	}

}
