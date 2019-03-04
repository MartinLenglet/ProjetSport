package com.projetsport.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projetsport.dao.ParticipationRepository;
import com.projetsport.dao.UserRepository;
import com.projetsport.entities.Participation;
import com.projetsport.entities.User;

@RestController
@CrossOrigin("*")
public class UserRestService {
	
	@Autowired
	private UserRepository userRepos;
	@Autowired
	private ParticipationRepository participationRepos;
	
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
	
	@RequestMapping(value="/userconnexion", method=RequestMethod.POST)
	public User connexionOk(@RequestBody User user) {
		List<User> allUser = userRepos.findAll();
		User userConnect = new User();
		for (User user2 : allUser) {
			if(user.getPseudo().equals(user2.getPseudo())&&(user.getMdp().equals(user2.getMdp()))){
				userConnect = user2;
			}
		}
		return userConnect;
	}
	
	@RequestMapping(value="/listeparticipantsevent/{id}", method=RequestMethod.GET)
	public List<User> participantsEvent(@PathVariable Long id) {
		List<User> participantsEvent = new ArrayList<User>();
		List<Participation> allParticipation = participationRepos.findAll();
		for (Participation participation : allParticipation) {
			if(participation.getEvent().getId()==id) {
				Long idParticipant = participation.getParticipant().getId();
				participantsEvent.add(userRepos.findOne(idParticipant));
			}
		}
		return participantsEvent;
	}

}
