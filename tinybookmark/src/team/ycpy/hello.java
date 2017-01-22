package team.ycpy;

import java.util.HashMap;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import team.ycpy.model.Item;

@Path("/service")
public class hello {

	@GET
	@Produces(MediaType.TEXT_PLAIN)  
	public String sayhello(){
		return "Hello, world!";
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String posttest(HashMap<String, Object> map){
		//int a = (int) map.get("passowrd");
		//System.out.println(a);
		Item it = new Item();
		it.setItype((int)map.get("password"));
		System.out.println(it.getItype());
		return "hello";
	}
}
