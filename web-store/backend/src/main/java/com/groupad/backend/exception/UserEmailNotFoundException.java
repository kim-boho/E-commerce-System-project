/**
 * 
 */
package com.groupad.backend.exception;

/**
 * @author Boho Kim
 *
 */
public class UserEmailNotFoundException extends RuntimeException{
	
    public UserEmailNotFoundException(String email) {
        super("Couldn't find user email: " + email);
    }
}
