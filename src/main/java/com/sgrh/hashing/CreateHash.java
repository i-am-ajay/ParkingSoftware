package com.sgrh.hashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.digest.DigestUtils;

public class CreateHash {
	static final String SALT = "parking"; // enforce more security to the password.
	final MessageDigest md;
	public CreateHash() throws NoSuchAlgorithmException{
			md = MessageDigest.getInstance("SHA-1");
	}
	
	public void createHash(String password) {
		System.out.println(DigestUtils.sha1Hex(password+SALT));
	}
	
	public static void main(String [] args) {
		try {
			CreateHash hash = new CreateHash();
			hash.createHash("12345");
		}
		catch(NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
		
	}
}
