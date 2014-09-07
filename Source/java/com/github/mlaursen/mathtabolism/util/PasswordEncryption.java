/**
 * 
 */
package com.github.mlaursen.mathtabolism.util;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.jboss.security.Base64Encoder;

/**
 * 
 * @author laursenm
 */
public class PasswordEncryption {
	
	
	private static final String ALGORITHM = "SHA-256";
	private static final String ENCODING = "UTF-8";
	public static String encrypt(String password) {
		String encrypted = "";
		try {
			byte[] pswd = password.getBytes(ENCODING);
			MessageDigest md = MessageDigest.getInstance(ALGORITHM);
			byte[] hash = md.digest(pswd);
			encrypted = Base64Encoder.encode(hash);
		}
		catch(NoSuchAlgorithmException | IOException e) {
			e.printStackTrace();
		}
		return encrypted;
	}
}
