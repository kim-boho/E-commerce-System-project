/**
 * 
 */
package com.groupad.backend.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Boho Kim
 *
 */
@Entity
@Table(name = "users")
public class User {
	@Column(name = "email")
	private @Id String email;
	@Column(name = "password")
	private String password;
	@Column(name = "fname")
	private String fName;
	@Column(name = "lname")
	private String lName;
	@Column(name = "admin")
	private int admin;
	@Column(name = "checkout_count")
	private int checkoutCount;

	public User() {

	}

	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public User(String email, String password, String fName, String lName, int admin, int checkoutCount) {
		this.email = email;
		this.password = password;
		this.fName = fName;
		this.lName = lName;
		this.admin = admin;
		this.checkoutCount = checkoutCount;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the fname
	 */
	public String getFName() {
		return fName;
	}

	/**
	 * @param fname the fname to set
	 */
	public void setFName(String fName) {
		this.fName = fName;
	}

	/**
	 * @return the lName
	 */
	public String getLName() {
		return lName;
	}

	/**
	 * @param lName the lname to set
	 */
	public void setLName(String lName) {
		this.lName = lName;
	}

	/**
	 * @return the admin
	 */
	public int getAdmin() {
		return admin;
	}

	/**
	 * @param admin the admin to set
	 */
	public void setAdmin(int admin) {
		this.admin = admin;
	}

	/**
	 * @return the checkoutCount
	 */
	public int getCheckoutCount() {
		return checkoutCount;
	}

	/**
	 * @param checkoutCount the checkoutCount to set
	 */
	public void setCheckoutCount(int checkoutCount) {
		this.checkoutCount = checkoutCount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email);
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password + ", fname=" + fName + ", lname=" + lName + ", admin="
				+ admin + ", checkoutCount=" + checkoutCount + "]";
	}

}
