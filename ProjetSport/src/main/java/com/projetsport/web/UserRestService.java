package com.projetsport.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projetsport.dao.UserRepository;
import com.projetsport.entities.User;

@RestController
@CrossOrigin("*")
public class UserRestService {
	
	@Autowired
	private UserRepository userRepos;
	
	@RequestMapping(value="/user", method=RequestMethod.GET)
	public List<User> getUser(){
		return userRepos.findAll();
	}
	
	@RequestMapping(value="/user/{id}", method=RequestMethod.GET)
	public User getUserById(@PathVariable Long id){
		return userRepos.findOne(id);
	}
	
	@RequestMapping(value="/user", method=RequestMethod.POST)
	public User saveUser(@RequestBody User user){
		return userRepos.save(user);
	}
	
	@RequestMapping(value="/user/{id}", method=RequestMethod.DELETE)
	public boolean deleteUser(@PathVariable Long id){
		userRepos.delete(id);
		return true;
	}
	
	@RequestMapping(value="/user/{id}", method=RequestMethod.PUT)
	public User modifUser(@PathVariable Long id, @RequestBody User user){
		user.setId(id);
		return userRepos.save(user);
	}

}
