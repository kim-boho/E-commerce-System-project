/**
 * 
 */
package model;

import java.sql.SQLException;
import java.util.Map;

import javax.naming.NamingException;

import bean.EnrollmentBean;
import bean.StudentBean;


public class SisModel {
	private static SisModel instance;
	
	// pointers to DAOs that support all operataion on DB.
	private static StudentDAO studentData;
	private static EnrollmentDAO enrollmentData;
	
	private SisModel(){
	}
	
	public static SisModel getInstance() throws ClassNotFoundException{
		if(instance == null) {
			instance = new SisModel();
			instance.studentData = new StudentDAO();
			instance.enrollmentData = new EnrollmentDAO();
		}
		return instance;
	}
	
	public StudentDAO getStudentDAOInstance() throws ClassNotFoundException {
		if(studentData == null) studentData = new StudentDAO();
		return studentData;
	}
	
	public EnrollmentDAO getEnrollmentDAOInstance() throws ClassNotFoundException {
		if(enrollmentData == null) enrollmentData = new EnrollmentDAO();
		return enrollmentData;
	}
	
	public Map<String, StudentBean> retrieveStudent(String namePrefix, String credit_taken) throws SQLException{
		// Retrieve students info based on prefix, credits
		int credits = Integer.valueOf(credit_taken);
		if(validate(namePrefix, credits)) return studentData.retrieve(namePrefix, credits);
		else return null;
	}
	
	private boolean validate(String namePrefix, int credits) {
		// Validate if the name is valid(only alphabet) and the credits is not negative
		// empty prefix string is valid (prints all studnets)
		if(namePrefix == null) return false;
		if(namePrefix.length()>0 && namePrefix.matches("^[a-zA-Z]*$") && credits>=0) return true;
		else return false;
	}
	
	public int delete(String sid) throws SQLException, NamingException {
		return studentData.delete(sid);
	}
	
	public int create(String sid, String givenname, String surname, int credittaken, int creditgraduate) throws SQLException, NamingException {
		return studentData.insert(sid, givenname, surname, credittaken, creditgraduate);
	}
	
	public Map<String, EnrollmentBean> retrieveEnrollment() throws SQLException{
		// Retrieve all enrollment information
		return enrollmentData.retrieve();
	}
}
