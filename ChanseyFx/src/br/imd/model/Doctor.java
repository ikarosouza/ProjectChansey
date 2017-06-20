package br.imd.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctor_seq")
	@SequenceGenerator(name = "doctor_seq", sequenceName = "doctor_seq", allocationSize = 1)
	private int id;
	private String name;
	private String phone;
	private String crmNumber;

	@ManyToMany
	private List<Sector> sectors = new ArrayList<>();

	@ManyToMany
	private List<Specialty> specialties = new ArrayList<>();

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCrmNumber() {
		return crmNumber;
	}

	public void setCrmNumber(String crmNumber) {
		this.crmNumber = crmNumber;
	}

	public List<Sector> getSectors() {
		return sectors;
	}

	public void setSectors(List<Sector> sectors) {
		this.sectors = sectors;
	}

	public List<Specialty> getSpecialties() {
		return specialties;
	}

	public void setSpecialties(List<Specialty> specialties) {
		this.specialties = specialties;
	}

	// Properties
	public StringProperty name() {
		return new SimpleStringProperty(this.name);
	}

	public StringProperty crmNumber() {
		return new SimpleStringProperty(this.crmNumber);
	}
	
	public StringProperty phone() {
		return new SimpleStringProperty(this.phone);
	}
	
	public String toString(){
		return this.name;
	}

}
