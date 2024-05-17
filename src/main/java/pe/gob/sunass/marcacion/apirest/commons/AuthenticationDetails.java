package pe.gob.sunass.marcacion.apirest.commons;

import java.util.Date;

public class AuthenticationDetails {
	  private String userName;
	  
	  private String ticket;
	  
	  private String sessionId;
	  
	  private Date wsLastTimeUsed;
	  
	  private long timeoutInterval = -1L;
	  
	  public AuthenticationDetails(String userName, String ticket, String sessionId) {
	    this.userName = userName;
	    this.ticket = ticket;
	    this.sessionId = sessionId;
	    this.wsLastTimeUsed = new Date();
	  }
	  
	  public AuthenticationDetails(String userName, String ticket, String sessionId, long timeoutInterval) {
	    this.userName = userName;
	    this.ticket = ticket;
	    this.sessionId = sessionId;
	    this.timeoutInterval = timeoutInterval;
	    this.wsLastTimeUsed = new Date();
	  }
	  
	  public String getUserName() {
	    return this.userName;
	  }
	  
	  public String getTicket() {
	    return this.ticket;
	  }
	  
	  public String getSessionId() {
	    return this.sessionId;
	  }
	  
	  public long getTimeoutInterval() {
	    return this.timeoutInterval;
	  }
	  
	  public void setTimeoutInterval(long timeoutInterval) {
	    this.timeoutInterval = timeoutInterval;
	  }
	  
	  public final boolean isTimedOut() {
	    if (this.timeoutInterval < 1L)
	      return false; 
	    long nowInMillis = (new Date()).getTime();
	    long expirationTimeInMillis = this.wsLastTimeUsed.getTime() + this.timeoutInterval;
	    return (nowInMillis > expirationTimeInMillis);
	  }
	  
	  public void resetTimeoutInterval() {
	    this.wsLastTimeUsed = new Date();
	  }
	}