package com.projetsport.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.projetsport.entities.Evenement;

public interface EvenementRepository extends JpaRepository<Evenement, Long> {
	
	@Query("FROM Evenement e WHERE e.dateEvent<CURRENT_DATE")
	public List<Evenement> getOldEvents();
	
	@Query("FROM Evenement e WHERE e.dateEvent>=CURRENT_DATE")
	public List<Evenement> getNewEvents();
	
	
}
