package org.apache.guacamole.rest.work;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/works")
@Produces(MediaType.APPLICATION_JSON)
public class WorkRESTService {
    @GET
    public String[] getWorks() {
        return new String[] {"work1", "work2"};
    }
}
