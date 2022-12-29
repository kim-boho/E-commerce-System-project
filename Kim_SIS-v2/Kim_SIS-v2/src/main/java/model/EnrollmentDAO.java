/**
 * 
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.EnrollmentBean;

/**
 * @author Boho Kim
 *
 */

public class EnrollmentDAO {
	
	private DataSource ds;
	
	public EnrollmentDAO() throws ClassNotFoundException{
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public Map<String, EnrollmentBean> retrieve() throws SQLException{
		String query = "select * from ENROLLMENT";
		Map<String, EnrollmentBean> rv = new HashMap<String, EnrollmentBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		Map<String, ArrayList<String>> studentsMap = new HashMap<String, ArrayList<String>>();
		Map<String, Integer> creditMap = new HashMap<String, Integer>();
		while (r.next()){
			String cid = r.getString("CID");
			String sid = r.getString("SID");
			
			// If it's not a new course, just add the student
			if(studentsMap.containsKey(cid)) {
				studentsMap.get(cid).add(sid);
			} else {
				// If it's new course, make hash map and add it
				ArrayList<String> std = new ArrayList<String>();
				std.add(sid);
				studentsMap.put(cid, std);
			}
			
			int credit = r.getInt("CREDIT");
			// If it's a new course, store its credit in map
			if(!creditMap.containsKey(cid)) {
				creditMap.put(cid, credit);
			}
		}
		
		Iterator<String> it = studentsMap.keySet().iterator();
		while(it.hasNext()) {
			String key = (String) it.next();
			EnrollmentBean eb = new EnrollmentBean(key, studentsMap.get(key), creditMap.get(key));
			rv.put(key, eb);
		}
		return rv;
	}
}
