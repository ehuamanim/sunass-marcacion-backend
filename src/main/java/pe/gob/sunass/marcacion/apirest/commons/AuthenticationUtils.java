package pe.gob.sunass.marcacion.apirest.commons;

import java.io.IOException;

import pe.gob.sunass.marcacion.apirest.alfresco.AlfrescoConnector;
import pe.gob.sunass.marcacion.apirest.exception.WebServiceException;

public class AuthenticationUtils {
	  	  
	  private static ThreadLocal<AuthenticationDetails> authenticationDetails = new ThreadLocal<AuthenticationDetails>();
	  
	  public static void startSession(String username, String password) throws IOException {
	      
	    	String ticket = new AlfrescoConnector().getTicket(username, password);
	      authenticationDetails.set(new AuthenticationDetails(username, ticket, ticket));
	    
	  }
	  
	  public static void startSession(String username, String password, long timeoutInterval) throws IOException {
	    startSession(username, password);
	    AuthenticationDetails ad = getAuthenticationDetails();
	    ad.setTimeoutInterval(timeoutInterval);
	  }
	  
	  public static void setAuthenticationDetails(AuthenticationDetails authenticationDetails) {
	    AuthenticationUtils.authenticationDetails.set(authenticationDetails);
	  }
	  
	  public static boolean isCurrentTicketTimedOut() {
	    boolean to = getAuthenticationDetails().isTimedOut();
	    if (to)
	      endSession(); 
	    return to;
	  }
	  
	  public static void endSession() {
	    AuthenticationDetails authenticationDetails = AuthenticationUtils.authenticationDetails.get();
	    if (authenticationDetails != null)
	      try {
	        new AlfrescoConnector().deleteTicket( authenticationDetails.getTicket() );
	      } catch (IOException exception) {
	        exception.printStackTrace();
	        throw new WebServiceException("Error ending session.", exception);
	      }  
	  }
	  
	  public static String getTicket() {
	    String result = null;
	    AuthenticationDetails authDetails = authenticationDetails.get();
	    if (authDetails != null)
	      result = authDetails.getTicket(); 
	    return result;
	  }
	  
	  public static AuthenticationDetails getAuthenticationDetails() {
	    return authenticationDetails.get();
	  }
	}