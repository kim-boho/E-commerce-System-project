/**
 * 
 */
package model;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.StudentBean;

public class StudentDAO {
	
	private DataSource ds;
	
	public StudentDAO() throws ClassNotFoundException{
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public Map<String, StudentBean> retrieve(String namePrefix, int credit_taken) throws SQLException{
		// search all students who contains namePrefix in their surname (not only for prefix)
		String query = "select * from students where surname like '%" + namePrefix +"%' and credit_taken >= " + credit_taken;
		Map<String, StudentBean> rv = new HashMap<String, StudentBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		
		while (r.next()){
			String name = r.getString("GIVENNAME") + ", " + r.getString("SURNAME");
			String cseID = r.getString("SID");
			int credit_takenS = r.getInt("CREDIT_TAKEN");
			int credit_graduate = r.getInt("CREDIT_GRADUATE");
			rv.put(cseID, new StudentBean(cseID, name, credit_takenS, credit_graduate));
		}
		r.close();
		p.close();
		con.close();
		return rv;
	}
	
	public void retrieveToConsole() throws SQLException {
		String query = "select * from students";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()){
			String stdInfo = r.getString("GIVENNAME")+", "+r.getString("SURNAME")+", "+r.getString("SID")+", "+r.getInt("CREDIT_TAKEN")+", "+r.getInt("CREDIT_GRADUATE");;
			System.out.println(stdInfo);
		}
	}
	
	public int insert(String sid, String givenname, String surname, int credittaken, int creditgraduate) throws SQLException, NamingException{
		String preparedStatement = "insert into students values(?,?,?,?,?)";
		
		Connection con = this.ds.getConnection();
		PreparedStatement stmt = con.prepareStatement(preparedStatement);
		// set individual parameters through method calls
		
		stmt.setString(1, sid);
		stmt.setString(2, givenname);
		stmt.setString(3, surname);
		stmt.setInt(4, credittaken);
		stmt.setInt(5, creditgraduate);
		
		return stmt.executeUpdate();
	}
	
	public int delete(String sid) throws SQLException, NamingException{
		String preparedStatement = "delete from students where sid=?";
		Connection con = this.ds.getConnection();
		PreparedStatement stmt = con.prepareStatement(preparedStatement);
		stmt.setString(1, sid);
		
		return stmt.executeUpdate();
	}

}
