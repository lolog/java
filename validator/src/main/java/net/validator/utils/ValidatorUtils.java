package net.validator.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;

public class ValidatorUtils {
	private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
	
	public static <T> Map<String,StringBuffer> validate(T obj){  
        Map<String,StringBuffer> validate = new HashMap<String, StringBuffer>();
        
        Set<ConstraintViolation<T>> messages = validator.validate(obj,Default.class);  
        
        if (messages == null || messages.size() == 0) {
        	return validate;
        }
        
        for(ConstraintViolation<T> cv : messages){
        	String property = cv.getPropertyPath().toString();
            if(validate.get(property) == null){
            	StringBuffer sb = new StringBuffer();
                sb.append(cv.getMessage());
                validate.put(property, sb);
            }else{  
            	validate.get(property).append("," + cv.getMessage()); 
            }  
        }
        return validate;  
    }  
}
