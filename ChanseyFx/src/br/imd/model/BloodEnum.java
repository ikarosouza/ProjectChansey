package br.imd.model;

public enum BloodEnum {
	AP("A+"),
	AN("A-"),
	BP("B+"),
	BN("B-"),
	OP("O+"),
	ON("O-"),
	ABP("AB+"),
	ABN("AB-");
	
	private String type;

	private BloodEnum(String type) {
		this.type = type;
	}
	
	public String getValue(){
		return this.type;
	}
}
