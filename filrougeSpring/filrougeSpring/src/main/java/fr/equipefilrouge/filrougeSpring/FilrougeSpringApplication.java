package fr.equipefilrouge.filrougeSpring;

import fr.equipefilrouge.filrougeSpring.entity.*;
import fr.equipefilrouge.filrougeSpring.enums.UserRole;
import fr.equipefilrouge.filrougeSpring.services.impl.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@ComponentScan
public class FilrougeSpringApplication {

	private static FormateurServiceImpl formateurService;
	private static StagiaireServiceImpl stagiaireService;
	private static CentreFormationServiceImpl centreFormationService;
	private static FormationServiceImpl formationService;
	private static SessionFormationServiceImpl sessionFormationService;

	public FilrougeSpringApplication(
			FormateurServiceImpl formateurService,
			CentreFormationServiceImpl centreFormationService,
			FormationServiceImpl formationService,
			StagiaireServiceImpl stagiaireService,
			SessionFormationServiceImpl sessionFormationService){
		FilrougeSpringApplication.formateurService = formateurService;
		FilrougeSpringApplication.centreFormationService = centreFormationService;
		FilrougeSpringApplication.formationService = formationService;
		FilrougeSpringApplication.stagiaireService = stagiaireService;
		FilrougeSpringApplication.sessionFormationService = sessionFormationService;
	}

	public static void main(String[] args) {
		SpringApplication.run(FilrougeSpringApplication.class, args);

		Formateur formateur = new Formateur("Chimot", "Cedric", "01/01/01/01/01",  "cedric02@hotmail.fr",
				"ced02830", "Ced@02830", UserRole.FORMATEUR, 4.5 );
		formateurService.create(formateur);
		System.out.println(formateur);

		Stagiaire stagiaire = new Stagiaire("Chimot", "Cedric", "01/01/01/01/01",  "cedric02@hotmail.com",
				"ced02830", "Ced@02830", UserRole.CANDIDAT);
		stagiaireService.create(stagiaire);
		System.out.println(stagiaire);

		CentreFormation centreFormation = new CentreFormation("IBCegos", "Gare de Lille 59000");
		centreFormationService.create(centreFormation);
		System.out.println(centreFormation);

		Formation formation = new Formation("Java JEE", 3500, "Formation Java et au framework Spring");
		formationService.create(formation);
		System.out.println(formation);

		Date debutSession = new Date(2024-1900, Calendar.MARCH, 8);
		Date finSession = new Date(2024-1900, Calendar.JUNE, 8);
		SessionFormation sessionFormation = new SessionFormation(debutSession, finSession, "en cours",
				formationService.findById(1L), centreFormationService.findById(1L));
		sessionFormationService.create(sessionFormation);
		System.out.println(sessionFormation);

		List<CentreFormation> centre = centreFormationService.findAll();
		System.out.println(centre);

		Formation formation1 = formationService.findById(1L);
		System.out.println(formation1);

		SessionFormation sessionFormation1 = sessionFormationService.findById(1L);
		System.out.println(sessionFormation1);

	}
}
