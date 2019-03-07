package com.projetsport.web;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projetsport.dao.MailRepository;
import com.projetsport.entities.Evenement;
import com.projetsport.entities.Mail;
import com.projetsport.entities.User;


@RestController
@CrossOrigin("*")
public class MailRestService {
	
	//@Autowired
	//private MailRepository mailRepos;
	
	private void sendMail(String destinataire, String objet, String contenu) {
		final String expediteur = "staff.sport2go@gmail.com";
		final String password = "sport2go_2019";
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(expediteur, password);
					}
				  });
		
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(expediteur));
			message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(destinataire));
			message.setSubject(objet);
			message.setText(contenu);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	@RequestMapping(value="/mailcreation", method=RequestMethod.POST)
	public Mail mailCreation(@RequestBody Evenement e){
		
		String destinataire = e.getCreateur().getMail();
		String objet = "Création d'un événement sportif";
		String Newligne=System.getProperty("line.separator");
		String contenu = "Bonjour " + e.getCreateur().getPrenom() + " " + e.getCreateur().getNom() + "," 
		+ Newligne + Newligne + "Tu viens de créer un événement de " + e.getSport().getNom() + " le " + e.getDateEvent() + " à " + e.getHoraire() + ". "
		+ Newligne + "Sport2Go te souhaite de trouver des partenaires au plus vite. "  
		+ Newligne + "Tu peux consulter tous tes prochains événements sur : http://localhost:4200/. "
		+ Newligne + Newligne + "A bientôt sur Sport2Go !" 
		+ Newligne + "L'équipe Sport2Go";
		
		Mail mail = new Mail(objet, contenu, destinataire);
		
		sendMail(destinataire, objet, contenu);
		
		return mail;
	}
	
	@RequestMapping(value="/mailconfirmationparticipants", method=RequestMethod.POST)
	public void mailConfirmationParticipants(@RequestBody List<User> participants){
		String objet = "Validation d'un événement auquel vous participez";
		String Newligne=System.getProperty("line.separator");
		for (User user : participants) {
			String destinataire = user.getMail();
			String contenu = "Bonjour " + user.getPrenom() + " " + user.getNom() + "," 
			+ Newligne + Newligne + "Un événement auquel tu t'es inscrit(e) est complet et aura donc bien lieu. "
			+ Newligne + "Tu peux maintenant prendre contact avec le créateur de l'événement pour fixer le lieu de la rencontre. " 
			+ Newligne + "Tu peux consulter tous tes prochains événements sur : http://localhost:4200/. " 
			+ Newligne + Newligne + "A bientôt sur Sport2Go !" 
			+ Newligne + "L'équipe Sport2Go";
			
			sendMail(destinataire, objet, contenu);
		}
	}
	
	@RequestMapping(value="/mailconfirmationcreateur", method=RequestMethod.POST)
	public Mail mailConfirmationCreateur(@RequestBody Evenement e){
		String destinataire = e.getCreateur().getMail();
		String objet = "Validation d'un événement que vous avez créé";
		String Newligne=System.getProperty("line.separator");
		String contenu = "Bonjour " + e.getCreateur().getPrenom() + " " + e.getCreateur().getNom() + "," 
		+ Newligne + Newligne + "Ton événement de " + e.getSport().getNom() + " du " + e.getDateEvent() + " à " + e.getHoraire() + " est complet. "
		+ Newligne + "Félicitations ! Tu peux maintenant contacter les participants pour fixer le lieu de la rencontre. "  
		+ Newligne + "Tu peux consulter tous tes prochains événements sur : http://localhost:4200/. "
		+ Newligne + Newligne + "A bientôt sur Sport2Go !" 
		+ Newligne + "L'équipe Sport2Go";
		
		Mail mail = new Mail(objet, contenu, destinataire);
		
		sendMail(destinataire, objet, contenu);
		
		return mail;
	}
	
	@RequestMapping(value="/mailcreationcompte", method=RequestMethod.POST)
	public Mail mailCreationCompte(@RequestBody User u){
		String destinataire = u.getMail();
		String objet = "Confirmation de création de compte";
		String Newligne=System.getProperty("line.separator");
		String contenu = "Bonjour " + u.getPrenom() + " " + u.getNom() + "," 
		+ Newligne + Newligne + "Bienvenue sur Sport2Go, l'application qui va révolutionner ton quotidien ! "
		+ Newligne + "Tu peux dès maintenant participer à tous les événements proposés par les autres utilisateurs. "  
		+ Newligne + "Tu ne trouves pas ton bonheur ? Aucun problème, tu peux créer tes propres événements et attendre que d'autres utilisateurs te rejoignent. "
		+ Newligne + Newligne + "A bientôt sur http://localhost:4200/!" 
		+ Newligne + "L'équipe Sport2Go";
		
		Mail mail = new Mail(objet, contenu, destinataire);
		
		sendMail(destinataire, objet, contenu);
		
		return mail;
	}

}
