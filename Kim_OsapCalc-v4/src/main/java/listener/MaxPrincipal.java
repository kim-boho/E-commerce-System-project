package listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class MaxPrincipal
 *
 */
@WebListener
public class MaxPrincipal implements HttpSessionListener, HttpSessionAttributeListener {
	private double maxPrincipal;

    /**
     * Default constructor. 
     */
    public MaxPrincipal() {
        // TODO Auto-generated constructor stub
    	maxPrincipal = 0;
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent se)  { 
         // TODO Auto-generated method stub
    	// The first principal value is maximum principal
    	attributeReplaced(se);
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent se)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent se)  { 
         // TODO Auto-generated method stub
    	
    	// Whenever the principal value is added or changed in session context
    	// compare to maximum principal of servlet context and set properly.
    	if(se.getName().equals("principal")) {
    		double principal = Double.valueOf((String) se.getSession().getAttribute("principal"));
    		if(maxPrincipal<principal) maxPrincipal = principal;
    		if(((HttpSessionEvent) se).getSession().getServletContext().getAttribute("maxPrincipal") == null) {
    			((HttpSessionEvent) se).getSession().getServletContext().setAttribute("maxPrincipal",maxPrincipal);
    		} else {
    			double contextMaxPrincipal = (double) ((HttpSessionEvent) se).getSession().getServletContext().getAttribute("maxPrincipal");
    			if(maxPrincipal>contextMaxPrincipal) {
    				((HttpSessionEvent) se).getSession().getServletContext().setAttribute("maxPrincipal",maxPrincipal);
    			}
    		}
    	}
    }
	
}
