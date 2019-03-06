package com.projetsport.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetsport.entities.Mail;

public interface MailRepository extends JpaRepository<Mail, Long>{

}
