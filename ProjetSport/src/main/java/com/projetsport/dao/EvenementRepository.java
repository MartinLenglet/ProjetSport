package com.projetsport.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetsport.entities.Evenement;

public interface EvenementRepository extends JpaRepository<Evenement, Long> {

}
