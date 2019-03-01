package com.projetsport.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projetsport.dao.SportRepository;
import com.projetsport.entities.Sport;

@RestController
@CrossOrigin("*")
public class SportRestService {
	
	@Autowired
	private SportRepository sportRepos;
	
	@RequestMapping(value="/sports", method=RequestMethod.GET)
	public List<Sport> getSport(){
		return sportRepos.findAll();
	}

}
