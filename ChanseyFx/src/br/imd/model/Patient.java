package br.imd.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="patient_seq")
	@SequenceGenerator(name="patient_seq", sequenceName="patient_seq", allocationSize=1)
	private int id;
	private String name;
	private String sex;
	private String cpf;
	private String rg;
	private String phone;
	private String emergencyNumber;
	private double height;
	private double weight;
	private String bloodType;
	@Embedded
	private Address address;
	
	public Patient(){		
	}
	
	//Getters and setters
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmergencyNumber() {
		return emergencyNumber;
	}
	public void setEmergencyNumber(String emergencyNumber) {
		this.emergencyNumber = emergencyNumber;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public String getBloodType() {
		return bloodType;
	}
	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	//Properties	
	public StringProperty name() {
        return new SimpleStringProperty(this.name);
    }
	public StringProperty sex() {
        return new SimpleStringProperty(this.sex);
    }
	public StringProperty cpf() {
        return new SimpleStringProperty(this.cpf);
    }
	public StringProperty rg() {
        return new SimpleStringProperty(this.rg);
    }
	public StringProperty phone() {
        return new SimpleStringProperty(this.phone);
    }
	
	public String toString(){
		return this.name;
	}
}	
