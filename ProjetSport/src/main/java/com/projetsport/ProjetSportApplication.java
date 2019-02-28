package com.projetsport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.projetsport.dao.UserRepository;
import com.projetsport.entities.User;

@SpringBootApplication
public class ProjetSportApplication implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepos;

	public static void main(String[] args) {
		SpringApplication.run(ProjetSportApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		// Initialisation de la base de données "projetsport"
		
		/*userRepos.save(new User("Martin", "Lenglet", "TinMar du 62", "martin.lenglet@hotmail.fr", "martin"));
		userRepos.save(new User("Pierre", "Falck", "Pedro", "pierre.falck@hotmail.fr", "pierre"));
		userRepos.save(new User("Hugo", "Carlevaris", "Hugoleboss", "hugo.carlevaris@hotmail.fr", "hugo"));*/
		
		userRepos.findAll().forEach(a -> {
			System.out.println(a.infoUser());
		});
	}

}
