	package com.eazyschool.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="holidays")
public class Holiday extends BaseEntity	 {
	
	@Id
	private  String day;
	private  String reason;
	
	@Enumerated(EnumType.STRING)
	private  Type type;
	
	public enum Type{
		FESTIVAL, FEDERAL
	}
	
	//public Holiday(String day, String reason, Type type) {
	//	this.day= day;
		//this.reason = reason;
	//	this.type= type ;
	//}
	//public String getDay() {
	//	return day;
	//}
	//public String getReason() {
		//return reason;
	//}
	//public Type getType() {
	//	return type;
	//}
	
	
	
	

}
