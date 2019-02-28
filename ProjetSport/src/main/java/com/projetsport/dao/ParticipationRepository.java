package com.projetsport.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetsport.entities.Participation;

public interface ParticipationRepository extends JpaRepository<Participation, Long> {

}
