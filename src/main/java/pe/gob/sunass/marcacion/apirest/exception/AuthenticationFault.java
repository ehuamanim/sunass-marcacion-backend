package pe.gob.sunass.marcacion.apirest.exception;

import java.io.Serializable;

public class AuthenticationFault implements Serializable {
  private int errorCode;
  
  private String message1;
  
  public AuthenticationFault(int errorCode, String message1) {
    this.errorCode = errorCode;
    this.message1 = message1;
  }
  
  public int getErrorCode() {
    return this.errorCode;
  }
  
  public void setErrorCode(int errorCode) {
    this.errorCode = errorCode;
  }
  
  public String getMessage1() {
    return this.message1;
  }
  
  public void setMessage1(String message1) {
    this.message1 = message1;
  }
  
  private Object __equalsCalc = null;
  
  public synchronized boolean equals(Object obj) {
    if (!(obj instanceof AuthenticationFault))
      return false; 
    AuthenticationFault other = (AuthenticationFault)obj;
    if (obj == null)
      return false; 
    if (this == obj)
      return true; 
    if (this.__equalsCalc != null)
      return (this.__equalsCalc == obj); 
    this.__equalsCalc = obj;
    boolean _equals = (this.errorCode == other.getErrorCode() && ((this.message1 == null && other.getMessage1() == null) || (this.message1 != null && this.message1.equals(other.getMessage1()))));
    this.__equalsCalc = null;
    return _equals;
  }
  
  private boolean __hashCodeCalc = false;
  
  public synchronized int hashCode() {
    if (this.__hashCodeCalc)
      return 0; 
    this.__hashCodeCalc = true;
    int _hashCode = 1;
    _hashCode += getErrorCode();
    if (getMessage1() != null)
      _hashCode += getMessage1().hashCode(); 
    this.__hashCodeCalc = false;
    return _hashCode;
  }
  
  public AuthenticationFault() {}
}
