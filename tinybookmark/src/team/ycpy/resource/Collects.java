package team.ycpy.resource;

import java.sql.PreparedStatement;

import javax.ws.rs.*;

import team.ycpy.model.Collect;
import team.ycpy.model.ConnectServer;

@Path("/item/collect")
public class Collects {
	
	//create a collect
	@POST
	@Produces("application/json")
	public Collect createCollect(Collect in){
		ConnectServer con = new ConnectServer("root","Aaron12345");
		try{
			con.link();
			PreparedStatement stmt = in.insertCollect(con);
			con.executeInsert(stmt);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return in;
	}
	
	@DELETE
	@Produces("application/json")
	public Collect deleteCollect(Collect in){
		ConnectServer con = new ConnectServer("root","Aaron12345");
		try{
			con.link();
			PreparedStatement stmt = in.deleteCollect(con);
			con.executeDelete(stmt);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return in;
	}
	
	
}
