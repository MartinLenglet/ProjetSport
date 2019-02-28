package com.projetsport.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User implements Serializable{
	
	@Id @GeneratedValue
	private Long id;
	private String prenom;
	private String nom;
	private String pseudo;
	private String mail;
	private String mdp;
	
	User(){
		
	}

	public User(String prenom, String nom, String pseudo, String mail, String mdp) {
		super();
		this.prenom = prenom;
		this.nom = nom;
		this.pseudo = pseudo;
		this.mail = mail;
		this.mdp = mdp;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}


	public String infoUser() {
		return "User [id=" + id + ", prenom=" + prenom + ", nom=" + nom + ", pseudo=" + pseudo + ", mail=" + mail
				+ ", mdp=" + mdp + "]";
	}
	
	
	

}
