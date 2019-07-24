package br.com.sciuba.model;

public class Pilot {
	
	private Long id;
	private String name;
	
	public Pilot(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Pilot [id=" + id + ", name=" + name + "]";
	}
	
	
	
	
	
	
}
