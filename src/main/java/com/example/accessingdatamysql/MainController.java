package com.example.accessingdatamysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController    // This means that this class is a Controller
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class MainController {

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@Autowired
	private UserRepository userRepository;

	@PostMapping(path="/add") // Map ONLY POST Requests
	public Map addNewUser (@RequestBody Map requestBody) {
		User user = new User();
		user.setFirstName(requestBody.get("firstName").toString());
		user.setLastName(requestBody.get("lastName").toString());
		userRepository.save(user);
		return new HashMap<String, String>() {{
			put("status", "OK");
			put("message", "successful");
		}};
	}

	@GetMapping(path="/all")
	public Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping(path="/userwithname")
	public Iterable<User> getUsersWithName(@RequestParam String firstName, @RequestParam String lastName) {
		logger.info("get user with firstName: " + firstName + ", lastName: " + lastName);
		return userRepository.findByFirstNameAndLastName(firstName, lastName);
	}

	@GetMapping(path="/userwithnameprefix")
	public Iterable<User> getUsersWithNamePrefix(@RequestParam String firstNamePrefix) {
		return userRepository.findByFirstnamePrefix(firstNamePrefix);
	}
}
