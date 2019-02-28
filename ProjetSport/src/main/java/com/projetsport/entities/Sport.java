package com.projetsport.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Sport implements Serializable{
	
	@Id @GeneratedValue
	private Long id;
	private String nom;
	private int nbrMin;
	private int nbrMax;
	
	public Sport(){
		
	}

	public Sport(String nom, int nbrMin, int nbrMax) {
		super();
		this.nom = nom;
		this.nbrMin = nbrMin;
		this.nbrMax = nbrMax;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getNbrMin() {
		return nbrMin;
	}

	public void setNbrMin(int nbrMin) {
		this.nbrMin = nbrMin;
	}

	public int getNbrMax() {
		return nbrMax;
	}

	public void setNbrMax(int nbrMax) {
		this.nbrMax = nbrMax;
	}

	public String infoSport() {
		return "Sport [id=" + id + ", nom=" + nom + ", nbrMin=" + nbrMin + ", nbrMax=" + nbrMax + "]";
	}
	
	

}
