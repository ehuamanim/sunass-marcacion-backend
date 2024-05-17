package pe.gob.sunass.marcacion.apirest.body.response.base;

import java.util.List;

public class Path {
    private List<Element> elements;
    private String name;
    private boolean isComplete;
    
	public List<Element> getElements() {
		return elements;
	}
	public void setElements(List<Element> elements) {
		this.elements = elements;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isComplete() {
		return isComplete;
	}
	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}
    
    
}
