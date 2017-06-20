package br.imd.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity
public class Specialty {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "specialty_seq")
	@SequenceGenerator(name = "specialty_seq", sequenceName = "specialty_seq", allocationSize = 1)
	private int id;

	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// Properties
	public StringProperty name() {
		return new SimpleStringProperty(this.name);
	}
	
	public String toString(){
		return this.name;
	}
}
