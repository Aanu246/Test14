package com.eazyschool.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;

import com.eazyschool.annotation.FieldsValueMatch;

public class FieldsValueMatchValidator implements ConstraintValidator<FieldsValueMatch, Object> {
		
	private String field;
	private String fieldMatch;
	
	@Override
	public void initialize(FieldsValueMatch constraintAnnotation) {
		this.field = constraintAnnotation.field();
		this.fieldMatch = constraintAnnotation.fieldMatch();
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		Object fieldValue = new BeanWrapperImpl(value).getPropertyValue(field);
		Object fieldMatchValue = new BeanWrapperImpl(value).getPropertyValue(fieldMatch);
		
		//Due to my jpa validation you have to do this
		/*if(fieldValue !=null) {
			if(fieldValue.toString().startsWith("$2a")) {
				return true;
			}else {
				return fieldValue.equals(fieldMatchValue);
			}
		}else {
			return fieldMatchValue == null;	
		}*/
		
		if(fieldValue != null) {
			return fieldValue.equals(fieldMatchValue);
		}else {
			return fieldMatchValue==null;
		}
		
	}

}

	