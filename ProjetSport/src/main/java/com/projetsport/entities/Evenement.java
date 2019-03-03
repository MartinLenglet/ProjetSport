package com.projetsport.entities;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Evenement implements Serializable{
	
	@Id @GeneratedValue
	private Long id;
	private String titre;
	private String description;
	
	/*@Temporal(TemporalType.DATE)
	private Date dateEvent;
	@Temporal(TemporalType.TIME)
	private Time horaire;*/
	
	private String dateEvent;
	private String horaire;
	
	@ManyToOne
	private Sport sport;
	@ManyToOne
	private User createur;
	private int nbrParticipants;
	
	public Evenement() {
		
	}

	public Evenement(String titre, String description, String dateEvent, String horaire, Sport sport,
			User createur, int nbrParticipants) {
		super();
		this.titre = titre;
		this.description = description;
		this.dateEvent = dateEvent;
		this.horaire = horaire;
		this.sport = sport;
		this.createur = createur;
		this.nbrParticipants = nbrParticipants;
	}

	public Evenement(String titre, String description, String dateEvent, String horaire) {
		super();
		this.titre = titre;
		this.description = description;
		this.dateEvent = dateEvent;
		this.horaire = horaire;
	}
	
	public int getNbrParticipants() {
		return nbrParticipants;
	}

	public void setNbrParticipants(int nbrParticipants) {
		this.nbrParticipants = nbrParticipants;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDateEvent() {
		return dateEvent;
	}

	public void setDateEvent(String dateEvent) {
		this.dateEvent = dateEvent;
	}

	public String getHoraire() {
		return horaire;
	}

	public void setHoraire(String horaire) {
		this.horaire = horaire;
	}

	public Sport getSport() {
		return sport;
	}

	public void setSport(Sport sport) {
		this.sport = sport;
	}

	public User getCreateur() {
		return createur;
	}

	public void setCreateur(User createur) {
		this.createur = createur;
	}


	public String infoEvenement() {
		return "Evenement [id=" + id + ", titre=" + titre + ", description=" + description + ", dateEvent=" + dateEvent
				+ ", horaire=" + horaire + ", sport=" + sport + ", createur=" + createur + ", nbrParticipants="
				+ nbrParticipants + "]";
	}
	
	public void ajouterparticipant() {
		this.nbrParticipants += 1 ;
	}
	
	
	
}
