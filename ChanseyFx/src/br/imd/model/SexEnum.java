package br.imd.model;

public enum SexEnum {
	MALE("Masc"),
	FEMALE("Femi"),
	TRANS("Trans");
	
	private String sex;

	private SexEnum(String sex) {
		this.sex = sex;
	}
	
	public String getValue(){
		return this.sex;
	}
}
