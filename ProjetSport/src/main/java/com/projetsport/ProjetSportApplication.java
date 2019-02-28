package com.projetsport;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.projetsport.dao.EvenementRepository;
import com.projetsport.dao.SportRepository;
import com.projetsport.dao.UserRepository;
import com.projetsport.entities.Evenement;
import com.projetsport.entities.Sport;
import com.projetsport.entities.User;

@SpringBootApplication
public class ProjetSportApplication implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepos;
	
	@Autowired
	private SportRepository sportRepos;

	@Autowired
	private EvenementRepository eventRepos;
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetSportApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		// Initialisation de la base de données "projetsport"
		
		/*User martin = new User("Martin", "Lenglet", "TinMar du 62", "martin.lenglet@hotmail.fr", "martin");
		User pierre = new User("Pierre", "Falck", "Pedro", "pierre.falck@hotmail.fr", "pierre");
		User hugo = new User("Hugo", "Carlevaris", "Hugoleboss", "hugo.carlevaris@hotmail.fr", "hugo");
		
		Sport tennis = new Sport("Tennis", 2, 4);
		Sport foot = new Sport("Football", 5, 22);
		Sport volley = new Sport("VolleyBall", 6, 8);
	    
		userRepos.save(martin);
		userRepos.save(pierre);
		userRepos.save(hugo);
		
		sportRepos.save(tennis);
		sportRepos.save(foot);
		sportRepos.save(volley);
		
		
		Evenement event1 = new Evenement("Tennis Simple niveau débutant", "Petit tennis tranquillou après le taf", "19/03/15", "17:30");
		event1.setCreateur(pierre);
		event1.setSport(tennis);	
		eventRepos.save(event1);
		
		Evenement event2 = new Evenement("Tennis Double expert", "Seulement pour les pros", "25/03/15", "18:30");
		event2.setCreateur(hugo);
		event2.setSport(tennis);	
		eventRepos.save(event2);
		
		Evenement event3 = new Evenement("Volley entre amis", "Vous savez où trouver un terrain ?", "22/03/15", "20:00");
		event3.setCreateur(martin);
		event3.setSport(volley);	
		eventRepos.save(event3);
		
		Evenement event4 = new Evenement("Football", "Quelqu'un a des chaussures ?", "23/03/15", "17:30");
		event4.setCreateur(hugo);
		event4.setSport(foot);	
		eventRepos.save(event4);*/
		
		
		userRepos.findAll().forEach(a -> {
			System.out.println(a.infoUser());
		});
		
		sportRepos.findAll().forEach(a -> {
			System.out.println(a.infoSport());
		});
		
		eventRepos.findAll().forEach(a -> {
			System.out.println(a.infoEvenement());
		});
	}

}
