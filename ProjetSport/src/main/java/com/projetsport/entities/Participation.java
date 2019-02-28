package com.projetsport.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Participation implements Serializable{
	
	@Id @GeneratedValue
	private Long id;
	
	@ManyToOne
	private Evenement event;
	@ManyToOne
	private User participant;
	
	public Participation() {
		
	}

	public Participation(Evenement event, User participant) {
		super();
		this.event = event;
		this.participant = participant;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Evenement getEvent() {
		return event;
	}

	public void setEvent(Evenement event) {
		this.event = event;
	}

	public User getParticipant() {
		return participant;
	}

	public void setParticipant(User participant) {
		this.participant = participant;
	}

	public String infoParticipation() {
		return "Participation [id=" + id + ", event=" + event.getTitre() + ", participant=" + participant.getNom() + "]";
	}

}
