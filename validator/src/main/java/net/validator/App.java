package net.validator;

import java.util.Map;

import net.validator.pojo.User;
import net.validator.utils.ValidatorUtils;

public class App {
	public static void main(String[] args) {  
        User user = new User();  
        long startTime = System.currentTimeMillis();  
        print(ValidatorUtils.validate(user));  
        System.out.println("Cost time (ms) = " + (System.currentTimeMillis() - startTime));  
          
        user.setUserName("Lolog");  
        user.setAge("A10");
        user.setBirthday("2016-9-1");  
        startTime = System.currentTimeMillis();  
        print(ValidatorUtils.validate(user));  
        System.out.println("Cost time (ms) = " + (System.currentTimeMillis() - startTime));  
    }  
      
    private static void print(Map<String,StringBuffer> validates){  
        for(Map.Entry<String, StringBuffer> entry : validates.entrySet()){  
            System.out.println(entry.getKey() + " : " + entry.getValue().toString());  
        }  
    }  
}
