package team.ycpy.resource;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;

import team.ycpy.model.*;
import java.sql.*;
import java.util.Date;

@Path("/users")
public class Users {
	
	//user log in into the system
	@GET
	@Produces("application/json")
	public User loginuser(@Context HttpServletRequest request){
		System.out.println(request.getParameter("username"));
		User in = new User();
		in.setusername(request.getParameter("username"));
		in.setpassword(request.getParameter("password"));
		ConnectServer con = new ConnectServer("root","Aaron12345");
		User judge = new User();
		try{
			con.link();
			PreparedStatement stmt = in.queryUser(con, " * "," = ");
			ResultSet res = con.executeQuery(stmt);
			if(res.next()){
				judge.setusername(res.getString("username"));
				judge.setpassword(res.getString("password"));
				judge.setnickname(res.getString("nickname"));
				judge.setrootfoldid(res.getString("rootfoldid"));
				//if the password matches, then return the basic information of 
				//the user
				if(judge.getpassword().equals(in.getpassword()))return judge;
			}
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	@POST
	//@Consumes("application/json")
	@Produces("application/json")
	public User registeruser(User in){
		
		Folder nfolder = new Folder();
		Item nitem = new Item();
		ConnectServer con = new ConnectServer("root","Aaron12345");
		
		System.out.println(in.getusername());
		
		//judge if the user is already here
		try{
			con.link();
			PreparedStatement stmt = in.queryUser(con, " * "," = ");
			ResultSet res = con.executeQuery(stmt);
			if(res.next())return null;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
		
		System.out.println(in.getnickname());
		
		try{
				con.link();
				in.setrootfoldid(String.valueOf(new Date().getTime()));
				nitem.setIno(in.getrootfoldid());
				nitem.setIname(in.getusername());
				nitem.setItype(1);
				PreparedStatement stmt = nitem.insertItem(con);
				con.executeInsert(stmt);
				
				stmt = in.insertUser(con);
				con.executeInsert(stmt);
				
				nfolder.setIno(nitem.getIno());
				nfolder.setIname(nitem.getIname());
				nfolder.setCreaterId(in.getusername());
				nfolder.setFtype(0);
				stmt = nfolder.insertFolder(con);
				con.executeInsert(stmt);
				
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return in;
	}
	
	@PUT
	@Path("{username}")
	@Produces("application/json")
	public User updateprofile(User it,@PathParam("username") String name){
		ConnectServer con = new ConnectServer("root","Aaron12345");
		try{
			con.link();
			it.setusername(name);
			PreparedStatement stmt = it.updateUser(con);
			System.out.println(stmt.toString());
			con.executeUpdate(stmt);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return it;
	}

}
