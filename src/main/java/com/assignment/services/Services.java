package com.assignment.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.beans.Users;
import com.assignment.repository.UserRepository;
import com.assignment.utilites.CustomUtility;

@Service
public class Services {
	 @Autowired
	    private UserRepository userRepository;

	    public Users registerUser(Users user) {
	        user.setPassword(new CustomUtility().encrypt(user.getPassword()));
	        return userRepository.save(user);
	    }

	    public Users getUserByUsername(String username) {
	        return userRepository.findByUsername(username);
	    }
	    public Users getUserByEmail(String email) {
	    	return userRepository.findByEmail(email);
	    }
}
