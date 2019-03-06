package com.projetsport.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Mail implements Serializable {
	
	@Id @GeneratedValue
	private Long id;
	private String objet;
	private String contenu;
	private String destinataire;
	
	public Mail() {
		
	}
	public Mail(String objet, String contenu, String destinataire) {
		this.objet = objet;
		this.contenu = contenu;
		this.destinataire = destinataire;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getObjet() {
		return objet;
	}
	public void setObjet(String objet) {
		this.objet = objet;
	}
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	public String getDestinataire() {
		return destinataire;
	}
	public void setDestinataire(String destinataire) {
		this.destinataire = destinataire;
	}
	
	public String infoMail() {
		return "Mail [id=" + id + ", objet=" + objet + ", contenu=" + contenu + ", destinataire=" + destinataire + "]";
	}
	
	

}
