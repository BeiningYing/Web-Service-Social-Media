package com.x.politicianinfo.data;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Party")
public class Party {
	String id;
	String name;
	
	public Party() {}
	
	public Party(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getId() {
		return this.id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
	    return "Party{" +
	            "id='" + id + '\'' +
	            ", name='" + name + '\'' +
	            '}';
	}
	
	
}
