package com.example.myMusic.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.myMusic.user.web.UserController;

public class Rules {
	
	    public static boolean passwordrule(String password) {
	    	boolean b=false;
	    	try {
	    		String rule="^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{5,12}$";
	    		Pattern regex=Pattern.compile(rule);
	    		Matcher matcher=regex.matcher(password.trim());
	    		b=matcher.matches();
	    	}catch(Exception e) {
	    		
	    		b=false;
	    	}
	    	return b;
	    }
        public static boolean phonerule(String phone) {
        	boolean b = false; 
        	try {
        		String rule="^1(3|4|5|7|8)\\d{9}$";
        		Pattern regex = Pattern.compile(rule);
				Matcher matcher = regex.matcher(phone.trim());
				b = matcher.matches();
        		
        	}catch(Exception e) {
        		b = false;
        	}
        	
        	return b;
        }
        public static boolean emailrule(String email) {
        	boolean b=false;
        	try {
        		String rule="^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        		Pattern pattern=Pattern.compile(rule);
        		Matcher matcher=pattern.matcher(email);
                b=matcher.matches();
        		}catch(Exception e) {
        		b=false;
        	}
        	
        	
        	return b;
        }
}
