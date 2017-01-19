package team.ycpy.resource;

import javax.ws.rs.*;
import java.sql.*;
import team.ycpy.model.*;
import java.util.*;

@Path("items")
public class items {
	
	@GET
	@Produces("application/json")
	@Consumes("application/json")
	public List<Item> itemprovider(Item judge){
	}
}
