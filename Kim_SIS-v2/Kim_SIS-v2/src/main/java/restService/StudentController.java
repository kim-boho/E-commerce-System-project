/**
 * 
 */
package restService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.naming.NamingException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import bean.StudentBean;
import model.SisModel;

// application controller from FrontController pattern
// interfaces with the clients and model

@Path("students")
public class StudentController {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getStudent( @QueryParam("namePrefix") String name, @QueryParam("credits") int credits) throws Exception{
		
		// get student list from DB
		Map<String, StudentBean> stdList = (HashMap<String, StudentBean>) SisModel.getInstance().retrieveStudent(name, String.valueOf(credits));
		String result = "";
		
		Iterator it = null;
		try {
			it = stdList.keySet().iterator();
		} catch(Exception e) {
			//if it's empty
			return result;
		}
		
		if(it == null) return result;
		
		while(it.hasNext()) {
			String key = (String) it.next();
			System.out.println(key);
			result += "{\"name\":\""+stdList.get(key).getName()
					+"\",\"creditTaken\":"+stdList.get(key).getCredit_taken()
					+",\"creditGraduate\":"+stdList.get(key).getCredit_graduate()+"},";
		}
		
		//sd.readAndPrintTableToConsole();
		if(result != null && result.length()>0) {
			result = result.substring(0,result.length()-1);
			result = "{\"students\":["+result+"]}";
			return result;
		}
		
		return result;
	}
	
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	public String createStudent(@QueryParam("sid") String sid, @QueryParam("givenName") String givenname,
			@QueryParam("surName") String surname, @QueryParam("creditTaken") String credittaken, @QueryParam("creditGraduate") String creditgraduate) throws NumberFormatException, ClassNotFoundException, SQLException, NamingException {
		int numOfInserted = SisModel.getInstance().create(sid, givenname, surname, Integer.valueOf(credittaken), Integer.valueOf(creditgraduate));
		return "InsertedRows:"+numOfInserted;
	}
	
	@DELETE
	@Consumes(MediaType.TEXT_PLAIN)
	@Path("/{sid}")
	public String delete(@PathParam("sid") String sid) throws ClassNotFoundException, SQLException, NamingException {
		int numOfDeleted = SisModel.getInstance().delete(sid);
		return "DeletedRows:"+numOfDeleted;
	}
}
