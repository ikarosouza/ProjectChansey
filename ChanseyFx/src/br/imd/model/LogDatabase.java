package br.imd.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class LogDatabase {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="log_seq")
	@SequenceGenerator(name="log_seq", sequenceName="log_seq", allocationSize=1)
	private int id;
	
	private String operationType;
	private String entity;
	private String updatedValue;
	private Date updatedAt;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	public String getUpdatedValue() {
		return updatedValue;
	}
	public void setUpdatedValue(String updatedValue) {
		this.updatedValue = updatedValue;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}
