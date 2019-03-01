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

import com.projetsport.dao.EvenementRepository;
import com.projetsport.dao.ParticipationRepository;
import com.projetsport.entities.Evenement;
import com.projetsport.entities.Participation;

@RestController
@CrossOrigin("*")
public class EvenementRestService {
	
	@Autowired
	private EvenementRepository eventRepos;
	
	@Autowired
	private ParticipationRepository participationRepos;
	
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

	@RequestMapping(value="/meseventcree/{id}", method=RequestMethod.GET)
	public List<Evenement> getMesEventCreeById(@PathVariable Long id){
		List<Evenement> allEvent = eventRepos.findAll();
		List<Evenement> mesEvents = new ArrayList<Evenement>();
		
		for (Evenement evenement : allEvent) {
			if(evenement.getCreateur().getId()==id) {
				mesEvents.add(evenement);
			}
		}
		return mesEvents;
	}
	 
	@RequestMapping(value="/event/nbrparticipants", method=RequestMethod.GET)
	public List<Integer> compteurParticipants(){
		List<Integer> listeCompteur = new ArrayList<Integer>();
		List<Evenement> allEvent = eventRepos.findAll();
		List<Participation> allParticipation = participationRepos.findAll();
		Integer compteur = 0;
		for (Evenement evenement : allEvent) {
			compteur = 0;
			for (Participation participation : allParticipation) {
				if (participation.getEvent().getId()==evenement.getId()) {
					compteur += 1;
				}
			}
			listeCompteur.add(compteur);
		}
		return listeCompteur;
	}
	
}
