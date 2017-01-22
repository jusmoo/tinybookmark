package team.ycpy.resource;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;

import team.ycpy.model.*;
import java.sql.*;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

@Path("/items")
public class items {

	@POST
	@Consumes("application/json")
	public Item buildItem(HashMap<String,Object> map){
		Item newit = new Item();
		ConnectServer con = new ConnectServer("root","Aaron12345");
		
		newit.setIno(String.valueOf(new Date().getTime()));
		newit.setIname((String)map.get("Iname"));
		newit.setFno((String)map.get("Fno"));
		newit.setItype((int)map.get("Itype"));
		
		if(newit.getItype() == 1){
			try{
				Folder newfo = new Folder();
				newfo.setCreaterId((String)map.get("CreaterId"));
				newfo.setFtype((int)map.get("Ftype"));
				newfo.setIno(newit.getIno());
				newfo.setIname(newit.getIname());
			
				con.link();
				PreparedStatement stmt = newit.insertItem(con);
				PreparedStatement stmt1 = newfo.insertFolder(con);
				
				con.executeInsert(stmt);
				con.executeInsert(stmt1);
			}
			catch(Exception e){
				e.printStackTrace();
				return null;
			}
		}
		else{
			try{
				System.out.println(newit.getIname());
				URL newurl = new URL();
				newurl.setIno(newit.getIno());
				newurl.setIname(newit.getIname());
				newurl.setUaddr((String)map.get("Uaddr"));
				
				con.link();
				PreparedStatement stmt = newit.insertItem(con);
				PreparedStatement stmt1 = newurl.insertURL(con);
				
				System.out.println(stmt.toString());
				System.out.println(stmt1.toString());
				
				con.executeInsert(stmt);
				con.executeInsert(stmt1);
			}
			catch(Exception e){
				con.close();
				e.printStackTrace();
				return null;
			}
		}
		con.close();
		
		return newit;
	}
	
	
	//delete the item cascade
	@DELETE
	@Path("{Ino}")
	public Item deleteItem(@PathParam("Ino") String index){
		Item it = new Item();
		it.setIno(index);
		
		Queue<Item>Q = new LinkedList<Item> ();
		try{
			ConnectServer con = new ConnectServer("root","Aaron12345");
			con.link();
			
			Q.offer(it);
			
			while(!Q.isEmpty()){
				Item cur = Q.poll();
				
				if(cur.getItype() == 1){
					Item judge = new Item();
					Folder curfol = new Folder();
					
					judge.setIno(cur.getIno());
					curfol.setIno(cur.getIno());
					
					Item para = new Item();
					para.setFno(cur.getIno());
					PreparedStatement stmt = para.queryItem(con, " * ", " = ");
					ResultSet res = con.executeQuery(stmt);
					
					while(res.next()){
						Item suc = new Item();
						suc.setIno(res.getString("Ino"));
						suc.setItype(res.getInt("Itype"));
						Q.add(suc);
					}
					
					stmt = cur.deleteItem(con);
					PreparedStatement stm1 = curfol.deleteFolder(con);
					
					con.executeDelete(stm1);
					con.executeDelete(stmt);
				}
				else{
					URL cururl = new URL();
					cururl.setIno(cur.getIname());
					
					PreparedStatement stmt = cur.deleteItem(con);
					PreparedStatement stm1 = cururl.deleteURL(con);
					
					con.executeDelete(stm1);
					con.executeDelete(stmt);
				}
			}
			con.close();
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
		return it;
		
	}
	
	
	@PUT
	@Path("{Ino}")
	@Consumes("application/json")
	//@Produces("application/json")
	public Item redefinezitem(HashMap<String,Object> map,@PathParam("Ino") String index){
		Item newit = new Item();
		ConnectServer con = new ConnectServer("root","Aaron12345");
		
		newit.setIno(index);
		if(map.containsKey("Iname"))newit.setIname((String)map.get("Iname"));
		if(map.containsKey("Fno"))newit.setFno((String)map.get("Fno"));
		if(map.containsKey("Itype"))newit.setItype((int)map.get("Itype"));
		
		if(newit.getItype() == 1){
			try{
				Folder newfo = new Folder();
				newfo.setCreaterId((String)map.get("CreaterId"));
				newfo.setFtype((int)map.get("Ftype"));
				newfo.setIno(newit.getIno());
				newfo.setIname(newit.getIname());
			
				con.link();
				PreparedStatement stmt = newit.updateItem(con);
				PreparedStatement stmt1 = newfo.updateFolder(con);
				
				con.executeInsert(stmt);
				con.executeInsert(stmt1);
			}
			catch(Exception e){
				e.printStackTrace();
				return null;
			}
		}
		else{
			try{
				URL newurl = new URL();
				newurl.setIno(newit.getIno());
				newurl.setIname(newit.getIname());
				newurl.setUaddr((String)map.get("Uaddr"));
				
				con.link();
				PreparedStatement stmt = newit.updateItem(con);
				PreparedStatement stmt1 = newurl.updateURL(con);
				
				con.executeInsert(stmt);
				con.executeInsert(stmt1);
			}
			catch(Exception e){
				con.close();
				e.printStackTrace();
				return null;
			}
		}
		con.close();
		
		return newit;
		
	}
	
	
}
