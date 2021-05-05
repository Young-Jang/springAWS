package com.example.demo.util;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyPasswordEncoding implements PasswordEncoder {
	   private PasswordEncoder passwordEncoder;

	   @Override
	   public String encode(CharSequence rawPassword) {
	        if (rawPassword == null) {
	            throw new NullPointerException();
	        }
	        byte[] bpara = new byte[rawPassword.length()];
	        byte[] rethash;
	        int i;

	        for (i = 0; i < rawPassword.length(); i++)
	            bpara[i] = (byte) (rawPassword.charAt(i) & 0xff);

	        try {
	            MessageDigest sha1er = MessageDigest.getInstance("SHA1");
	            rethash = sha1er.digest(bpara); // stage1
	            rethash = sha1er.digest(rethash); // stage2
	        } catch (GeneralSecurityException e) {
	            throw new RuntimeException(e);
	        }

	        StringBuffer r = new StringBuffer(41);
	        r.append("*");

	        for (i = 0; i < rethash.length; i++) {
	            String x = Integer.toHexString(rethash[i] & 0xff).toUpperCase();
	            if (x.length() < 2) r.append("0"); r.append(x);
	        }
	        return r.toString();
	   }

	   @Override
	   public boolean matches(CharSequence rawPassword, String encodedPassword) {
	      return passwordEncoder.matches(rawPassword, encodedPassword);
	   }
	}