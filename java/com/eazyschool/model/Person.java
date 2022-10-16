package com.eazyschool.model;

import java.util.HashSet;
import java.util.Set;

//import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.eazyschool.annotation.FieldsValueMatch;
import com.eazyschool.annotation.PasswordValidator;

//import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@FieldsValueMatch.List({
	@FieldsValueMatch(
		field ="pwd",
		fieldMatch="confirmPwd",
		message = "Passwords do not match!"
	),
	@FieldsValueMatch(
		field= "email", fieldMatch = "confirmEmail",message="Email address do not match!"
	)
})
public class Person extends BaseEntity {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO,generator="native")
	@GenericGenerator(name="native",strategy="native")
	private int personId;
	
	@NotBlank(message="Name must be blank")
	@Size(min=3,message="Name must be at least 3 characters long")
	private String name;
	
	@NotBlank(message="mobile number must not be blank")
	@Pattern(regexp="(^$|[0-9]{11})",message="mobile number must be 11 digits")
	private String mobileNumber;
	
	@NotBlank(message="Email must not be blank")
	@Email(message="Please provide a valid email address")
	private String email;
	
	@NotBlank(message="Email must not be blank")
	@Email(message="Please provide a valid email address")
	@Transient
	private String confirmEmail;
	
	@NotBlank(message="Password must be blank")
	@Size(min=5,message="Password must be at least 5 characters long")
	@PasswordValidator
	private String pwd;
	
	
	@NotBlank(message="Password must be blank")
	@Size(min=5,message="Password must be at least 5 characters long")
	@Transient
	private String confirmPwd;
	
	
	// This is unit directional relationship
	//If i mention all these in adddress pojo class then it is bi-directional relationship	
	@OneToOne(fetch=FetchType.EAGER, cascade = CascadeType.PERSIST, targetEntity= Roles.class)
	@JoinColumn(name = "role_id", referencedColumnName = "roleId", nullable= false)
	private Roles roles;
	
	
	//Use cascadeType.ALL when u dont want to save his when user change profile
	//Since the relationship between person and address is optional(person can decide to change his profile or not), we use nullable = true
	@OneToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL, targetEntity= Address.class)
	@JoinColumn(name = "address_id", referencedColumnName = "addressId", nullable= true)
	private Address address;
	
	@ManyToOne(fetch=FetchType.LAZY,optional = true)
	@JoinColumn(name="class_id",referencedColumnName="classId",nullable= true)
	private EazyClass eazyClass;
	
	@ManyToMany(fetch= FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(name = "person_courses",
			joinColumns = {
					@JoinColumn(name="person_id", referencedColumnName = "personId")},
			inverseJoinColumns= {
					@JoinColumn(name = "course_id", referencedColumnName = "courseId")})
		private Set<Courses> courses = new HashSet<>();

}
