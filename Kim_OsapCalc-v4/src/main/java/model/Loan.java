/**
 * 
 */
package model;

import javax.servlet.*;

/**
 * @author Boho Kim
 *
 */
public class Loan {
	private static Loan instance;
	
	public static Loan getInstance() {
		if(instance==null) {
			instance=new Loan();
		}
		return instance;
	}
	
	public double computePayment(String principal, String interest, String period) throws Exception{
		
		// they are always numbers (don't need to check invalid format)
		double per = Double.valueOf(period);
		double prin = Double.valueOf(principal);
		double inter = Integer.valueOf(interest);
		
		if(inter<1) {
			throw new Exception("Interest must be greater than 0!");
		}
		if(inter>99) {
			throw new Exception("Interest must be smaller than 100!");
		}
		if(prin<=0) {
			throw new Exception("Principal must be greater than 0!");
		}
		if(per<=0) {
			throw new Exception("Period must be greater than 0!");
		}
		
		return (prin*inter/100/12)/(1-(Math.pow(1+inter/100/12, -per)));
	}

}
