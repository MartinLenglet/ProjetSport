package com.projetsport.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projetsport.dao.EvenementRepository;
import com.projetsport.entities.Evenement;

@RestController
@CrossOrigin("*")
public class EvenementRestService {
	
	@Autowired
	private EvenementRepository eventRepos;
	
	@RequestMapping(value="/event", method=RequestMethod.GET)
	public List<Evenement> getEvent(){
		return eventRepos.findAll();
	}
	
	@RequestMapping(value="/event/{id}", method=RequestMethod.GET)
	public Evenement getEventById(@PathVariable Long id){
		return eventRepos.findOne(id);
	}
	
	@RequestMapping(value="/event", method=RequestMethod.POST)
	public Evenement saveEvent(@RequestBody Evenement event){
		return eventRepos.save(event);
	}
	
	@RequestMapping(value="/event/{id}", method=RequestMethod.DELETE)
	public boolean deleteEvent(@PathVariable Long id){
		eventRepos.delete(id);
		return true;
	}
	
	@RequestMapping(value="/event/{id}", method=RequestMethod.PUT)
	public Evenement modifEvent(@PathVariable Long id, @RequestBody Evenement event){
		event.setId(id);
		return eventRepos.save(event);
	}

}
