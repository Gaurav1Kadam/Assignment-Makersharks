package com.assignment.controllerclass;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.beans.Users;
import com.assignment.services.Services;

import jakarta.ws.rs.Produces;

@RestController("")
public class controller {
	@Autowired
	private Services Service;

	@PostMapping("/register")
	@Produces("application/json")
	public ResponseEntity<?> registerUser(@RequestBody String s) {
		int flag = -1;
		try {
			JSONObject obj = new JSONObject(s);
			Users user = new Users();
			if (!obj.has("email") && !obj.has("username") && !obj.has("password")) {
				flag=0;
				throw new Exception("Please enter username/email/password");
			}
			if (obj.getString("password").length() <= 8) {
				flag = 1;
				throw new Exception("Password should of length greater than or equal to 8");
			}
			if (Service.getUserByUsername(obj.getString("username")) != null
					&& Service.getUserByEmail(obj.getString("email")) != null) {
				flag = 2;
				throw new Exception("Username or email already exists");
			}
			user.setEmail(obj.getString("email"));
			user.setUsername(obj.getString("username"));
			user.setPassword(obj.getString("password"));
			Service.registerUser(user);
			obj.clear();
			obj.put("success", true);
			obj.put("message", "User registered successfully");
			return new ResponseEntity<>(obj.toString(), HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject obj = new JSONObject();
			obj.put("success", false);
			obj.put("message", e.getMessage());
			if (flag == 0) {
				return new ResponseEntity<>(obj.toString(), HttpStatus.BAD_REQUEST);
			} else if (flag == 1) {
				return new ResponseEntity<>(obj.toString(), HttpStatus.BAD_REQUEST);
			} else if (flag == 2) {
				return new ResponseEntity<>(obj.toString(), HttpStatus.CONFLICT);
			} else {
				obj.put("message", "Internal Server Error");
				return new ResponseEntity<>(obj.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

	@GetMapping("/fetch/{username}")
	public ResponseEntity<?> fetch(@PathVariable String username) {
		Users user = Service.getUserByUsername(username);
		if (user != null) {
			JSONObject obj = new JSONObject();
			obj.put("success", true);
			obj.put("message", "User Found");
			obj.put("user_details", new JSONObject(user));
			obj.getJSONObject("user_details").remove("password");
			obj.getJSONObject("user_details").remove("id");
			return new ResponseEntity<>(obj.toString(), HttpStatus.OK);
		} else {
			JSONObject obj = new JSONObject();
			obj.put("success", false);
			obj.put("message", "User Not Found");
			return new ResponseEntity<>(obj.toString(), HttpStatus.NOT_FOUND);
		}
	}
}
