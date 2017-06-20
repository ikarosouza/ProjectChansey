package br.imd.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.type.descriptor.java.UUIDTypeDescriptor.ToStringTransformer;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity
public class Sector {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sector_seq")
	@SequenceGenerator(name = "sector_seq", sequenceName = "sector_seq", allocationSize = 1)
	private int id;
	private String name;
	private int numBeds;

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

	public int getNumBeds() {
		return numBeds;
	}

	public void setNumBeds(int numBeds) {
		this.numBeds = numBeds;
	}

	// Properties
	public StringProperty name() {
		return new SimpleStringProperty(this.name);
	}

	public IntegerProperty numBeds() {
		return new SimpleIntegerProperty(this.numBeds);
	}
	
	public String toString(){
		return this.name;
	}
}
