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
import com.projetsport.entities.User;

@RestController
@CrossOrigin("*")
public class ParticipationRestService {
	
	@Autowired
	private ParticipationRepository participationRepos;
	@Autowired
	private EvenementRepository eventRepos;
	
	
	@RequestMapping(value="/participation", method=RequestMethod.GET)
	public List<Participation> getParticipants(){
		return participationRepos.findAll();
	}
	
	@RequestMapping(value="/participation/{id}", method=RequestMethod.GET)
	public Participation getParticipationById(@PathVariable Long id){
		return participationRepos.findOne(id);
	}
	
	@RequestMapping(value="/participation", method=RequestMethod.POST)
	public Participation saveParticipation(@RequestBody Participation participation){
		return participationRepos.save(participation);
	}
	
	@RequestMapping(value="/participation/{id}", method=RequestMethod.DELETE)
	public boolean deleteParticipation(@PathVariable Long id){
		participationRepos.delete(id);
		return true;
	}
	
	@RequestMapping(value="/participation/{id}", method=RequestMethod.PUT)
	public Participation modifParticipation(@PathVariable Long id, @RequestBody Participation participation){
		participation.setId(id);
		return participationRepos.save(participation);
	}
	
	@RequestMapping(value="/mesparticipations/{id}", method=RequestMethod.GET)
	public List<Evenement> getMyParticipation(@PathVariable Long id){
		List<Participation> allParticipation = participationRepos.findAll();
		List<Participation> allMyParticipation = new ArrayList<Participation>();
		List<Evenement> allEvent = eventRepos.findAll();
		List<Evenement> allMyEvent = new ArrayList<Evenement>();
		
		for (Participation participation : allParticipation) {
			if(id==participation.getParticipant().getId()) {
				allMyParticipation.add(participation);
			}
		}
		
		for (Participation participation :allMyParticipation) {
			for (Evenement evenement : allEvent) {
				if(evenement.getId()==participation.getEvent().getId()) {
					allMyEvent.add(evenement);
				}
			}
		}
		return allMyEvent;
	}
	
	@RequestMapping(value="/participation/event/{id}", method=RequestMethod.GET)
	public Integer nbrParticipants(@PathVariable Long id) {
		Integer compteur = 0;
		List<Participation> allParticipation = participationRepos.findAll();
		for (Participation participation : allParticipation) {
			if (participation.getEvent().getId()==id) {
				compteur += 1;
			}
		}
		return compteur;
	}
	
	@RequestMapping(value="/supprparticipations/event/{id}", method=RequestMethod.DELETE)
	public boolean deleteParticipationsEvent(@PathVariable Long id) {
		List<Participation> allParticipation = participationRepos.findAll();
		for (Participation participation : allParticipation) {
			if(participation.getEvent().getId()==id) {
				participationRepos.delete(participation.getId());
			}
		}
		return true;
	}
	
	@RequestMapping(value="/participation/user/{id1}/event/{id2}", method=RequestMethod.GET)
	public Participation getParticipationByUserAndEvent(@PathVariable Long id1, @PathVariable Long id2){
		List<Participation> allParticipation = participationRepos.findAll();
		Participation participationFound = new Participation();
		for (Participation participation : allParticipation) {
			if( (participation.getEvent().getId()==id2) && (participation.getParticipant().getId()==id1) ) {
				participationFound = participation;
			}
		}
		return participationFound;
	}

}
