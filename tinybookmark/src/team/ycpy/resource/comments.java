package team.ycpy.resource;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;

import team.ycpy.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
		List<Comment> res = new ArrayList<Comment> ();
		ConnectServer con = new ConnectServer("root","Aaron12345");
		in.setIno(request.getParameter("Ino"));
		
		try{
			con.link();
			PreparedStatement stmt = in.queryComment(con, " * ");
			
		}
	}

}
