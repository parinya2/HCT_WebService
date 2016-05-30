package HCTPackage;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;

@Path("/aaa")
public class HCTMainService extends Application{
    @GET
    @Produces({"text/html"})
    public String testStr() {
        return "XXX";
    }
    
}
