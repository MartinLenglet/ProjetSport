package com.projetsport.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetsport.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
