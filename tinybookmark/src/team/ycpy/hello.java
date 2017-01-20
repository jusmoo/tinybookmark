package team.ycpy;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/service")
public class hello {

	@GET
	@Produces(MediaType.TEXT_PLAIN)  
	public String sayhello(){
		return "Hello, world!";
	}
}
