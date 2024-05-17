package pe.gob.sunass.marcacion.apirest.types;

import java.io.Serializable;
import java.util.Calendar;

public class Version implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Calendar created;

	private String creator;

	private String label;

	private boolean major;

	public Version(Calendar created, String creator, String label, boolean major) {
		this.created = created;
		this.creator = creator;
		this.label = label;
		this.major = major;
	}

	public Calendar getCreated() {
		return this.created;
	}

	public void setCreated(Calendar created) {
		this.created = created;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public boolean isMajor() {
		return this.major;
	}

	public void setMajor(boolean major) {
		this.major = major;
	}

	private Object __equalsCalc = null;

	public synchronized boolean equals(Object obj) {
		if (!(obj instanceof Version))
			return false;
		Version other = (Version) obj;
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (this.__equalsCalc != null)
			return (this.__equalsCalc == obj);
		this.__equalsCalc = obj;
		boolean _equals = (((this.created == null && other.getCreated() == null)
				|| (this.created != null && this.created.equals(other.getCreated())))
				&& ((this.creator == null && other.getCreator() == null)
						|| (this.creator != null && this.creator.equals(other.getCreator())))
				&& ((this.label == null && other.getLabel() == null)
						|| (this.label != null && this.label.equals(other.getLabel())))
				&& this.major == other.isMajor());
		this.__equalsCalc = null;
		return _equals;
	}

	private boolean __hashCodeCalc = false;

	public synchronized int hashCode() {
		if (this.__hashCodeCalc)
			return 0;
		this.__hashCodeCalc = true;
		int _hashCode = 1;
		if (getCreated() != null)
			_hashCode += getCreated().hashCode();
		if (getCreator() != null)
			_hashCode += getCreator().hashCode();
		if (getLabel() != null)
			_hashCode += getLabel().hashCode();
		_hashCode += (isMajor() ? Boolean.TRUE : Boolean.FALSE).hashCode();
		this.__hashCodeCalc = false;
		return _hashCode;
	}

	public Version() {
	}
}
