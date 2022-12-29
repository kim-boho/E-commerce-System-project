/**
 * 
 */
package bean;

import java.util.List;

/**
 * @author Boho Kim
 *
 */
public class EnrollmentBean {
	private String cid;
	private List<String> students;
	private int credit;
	
	public EnrollmentBean(String cid, List<String> students, int credit) {
		this.cid = cid;
		this.students = students;
		this.credit = credit;
	}

	/**
	 * @return the cid
	 */
	public String getCid() {
		return cid;
	}

	/**
	 * @param cid the cid to set
	 */
	public void setCid(String cid) {
		this.cid = cid;
	}

	/**
	 * @return the students
	 */
	public List<String> getStudents() {
		return students;
	}

	/**
	 * @param students the students to set
	 */
	public void setStudents(List<String> students) {
		this.students = students;
	}

	/**
	 * @return the credit
	 */
	public int getCredit() {
		return credit;
	}

	/**
	 * @param credit the credit to set
	 */
	public void setCredit(int credit) {
		this.credit = credit;
	}
	
	
	
}
