package com.assignment.utilites;

import org.mindrot.jbcrypt.BCrypt;

public class CustomUtility {
	public String encrypt(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}
}
