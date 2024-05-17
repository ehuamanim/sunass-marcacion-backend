package pe.gob.sunass.marcacion.apirest.body.response.base;

import java.util.List;

public class Permissions {
	private Boolean isInheritanceEnabled;
	private List<Authority> inherited;
	private List<Authority> locallySet;
	private List<String> settable;

	public Boolean isInheritanceEnabled() {
		return isInheritanceEnabled;
	}

	public void setInheritanceEnabled(Boolean isInheritanceEnabled) {
		this.isInheritanceEnabled = isInheritanceEnabled;
	}

	public List<Authority> getInherited() {
		return inherited;
	}

	public void setInherited(List<Authority> inherited) {
		this.inherited = inherited;
	}

	public List<Authority> getLocallySet() {
		return locallySet;
	}

	public void setLocallySet(List<Authority> locallySet) {
		this.locallySet = locallySet;
	}

	public List<String> getSettable() {
		return settable;
	}

	public void setSettable(List<String> settable) {
		this.settable = settable;
	}

}
