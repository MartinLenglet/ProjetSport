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
		final String expediteur = "usertest.projetsport@gmail.com";
		final String password = "projetsport_2019";
		
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
		+ Newligne + Newligne + "Vous venez de créer un événement de " + e.getSport().getNom() + " le " + e.getDateEvent() + " à " + e.getHoraire() + ". "
		+ Newligne + "AppSport vous souhaite de trouver des partenaires au plus vite. "  
		+ Newligne + "Vous pouvez consulter tous vos prochains événements sur : http://localhost:4200/afficheevent. "
		+ Newligne + Newligne + "A bientôt sur AppSport !" 
		+ Newligne + "Equipe AppSport";
		
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
			+ Newligne + Newligne + "Un événement auquel vous vous êtes inscrit(e) est complet et aura donc bien lieu. "
			+ Newligne + "Nous vous invitons à prendre contact avec le créateur de l'événement pour fixer le lieu de la rencontre. " 
			+ Newligne + "Vous pouvez consulter tous vos prochains événements sur : http://localhost:4200/afficheevent. " 
			+ Newligne + Newligne + "A bientôt sur AppSport !" 
			+ Newligne + "Equipe AppSport";
			
			sendMail(destinataire, objet, contenu);
		}
	}
	
	@RequestMapping(value="/mailconfirmationcreateur", method=RequestMethod.POST)
	public Mail mailConfirmationCreateur(@RequestBody Evenement e){
		String destinataire = e.getCreateur().getMail();
		String objet = "Validation d'un événement que vous avez créé";
		String Newligne=System.getProperty("line.separator");
		String contenu = "Bonjour " + e.getCreateur().getPrenom() + " " + e.getCreateur().getNom() + "," 
		+ Newligne + Newligne + "Votre événement de " + e.getSport().getNom() + " du " + e.getDateEvent() + " à " + e.getHoraire() + " est complet. "
		+ Newligne + "Félicitations ! Vous pouvez maintenant contacter les participants pour fixer le lieu de la rencontre. "  
		+ Newligne + "Vous pouvez consulter tous vos prochains événements sur : http://localhost:4200/afficheevent. "
		+ Newligne + Newligne + "A bientôt sur AppSport !" 
		+ Newligne + "Equipe AppSport";
		
		Mail mail = new Mail(objet, contenu, destinataire);
		
		sendMail(destinataire, objet, contenu);
		
		return mail;
	}

}
