package HCTPackage;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
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
        return callOracle();
    }
    
    public String callOracle() {
        // NOTE 1: It needs ojdbc14.jar to connect Oracle
        // NOTE 2: SQL> show parameter service; to show the Oracle service name (hctTest)
        String result = "NONE";
        try {
            String db_user = "sys as sysdba";
            String password = "Peacefu5";
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@localhost:1521:hctTest";
            Connection con = DriverManager.getConnection(url, db_user, password);
            System.out.println("Connected to database");

            String command = "{call hctProcedure1(?)}";
            CallableStatement cstmt = con.prepareCall(command);
            cstmt.registerOutParameter(1, java.sql.Types.VARCHAR);
            cstmt.execute();
            result = cstmt.getString(1);
            System.out.println("RESULT = " + result);
            
            cstmt.close();
          

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }
}
