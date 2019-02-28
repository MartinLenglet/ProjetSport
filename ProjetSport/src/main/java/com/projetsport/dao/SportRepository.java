package com.projetsport.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetsport.entities.Sport;

public interface SportRepository extends JpaRepository<Sport, Long>{

}
