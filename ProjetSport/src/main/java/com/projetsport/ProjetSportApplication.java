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
import com.projetsport.dao.ParticipationRepository;
import com.projetsport.dao.SportRepository;
import com.projetsport.dao.UserRepository;
import com.projetsport.entities.Evenement;
import com.projetsport.entities.Participation;
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
	
	@Autowired
	private ParticipationRepository participationRepos;
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetSportApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		// Initialisation de la base de données "projetsport" 
		
		// IMPORTANT :
		// Pour que tout se passe bien, supprimer toutes les tables de votre base de données et initialiser une seule fois 
		// avant de tout mettre en commentaire jusqu'à la ligne **************
		
		User martin = new User("Martin", "Lenglet", "TinMar du 62", "martin.lenglet@hotmail.fr", "martin");
		User pierre = new User("Pierre", "Falck", "PedroElFalko", "pierre.falck@hotmail.fr", "pierre");
		User hugo = new User("Hugo", "Carlevaris", "Hugoleboss", "hugo.carlevaris@hotmail.fr", "hugo");
		
		Sport tennis = new Sport("Tennis", 2, 4, "../assets/tennis-event.jpg");
		Sport foot = new Sport("Football", 5, 22, "../assets/foot-event.jpg");
		Sport volley = new Sport("VolleyBall", 6, 8, "../assets/volley-event.jpg");
	    
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
		eventRepos.save(event4);
		
		// Si erreur à ce niveau, voir compment utiliser le ManyToMany  
		Participation part1 = new Participation();
		part1.setEvent(event1);
		part1.setParticipant(martin);
		participationRepos.save(part1);
		
		Participation part2 = new Participation();
		part2.setEvent(event1);
		part2.setParticipant(pierre);
		participationRepos.save(part2);
		
		Participation part3 = new Participation();
		part3.setEvent(event2);
		part3.setParticipant(hugo);
		participationRepos.save(part3);
		
		Participation part4 = new Participation();
		part4.setEvent(event3);
		part4.setParticipant(martin);
		participationRepos.save(part4);
		
		Participation part5 = new Participation();
		part5.setEvent(event4);
		part5.setParticipant(hugo);
		participationRepos.save(part5);
		
		
		
		// **************** Fin de l'initialisation ******************************
		
		userRepos.findAll().forEach(a -> {
			System.out.println(a.infoUser());
		});
		
		sportRepos.findAll().forEach(a -> {
			System.out.println(a.infoSport());
		});
		
		eventRepos.findAll().forEach(a -> {
			System.out.println(a.infoEvenement());
		});
		
		participationRepos.findAll().forEach(a -> {
			System.out.println(a.infoParticipation());
		});
	}

}
