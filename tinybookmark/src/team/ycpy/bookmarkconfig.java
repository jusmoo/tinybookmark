package team.ycpy;

import org.glassfish.jersey.server.ResourceConfig;
import org.codehaus.jackson.jaxrs.*;

public class bookmarkconfig extends ResourceConfig{
	
	public bookmarkconfig(){
		packages("team.ycpy");
		packages("team.ycpy.resource");
		register(JacksonJsonProvider.class);
	}

}
