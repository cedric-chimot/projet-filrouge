package fr.equipefilrouge.filrougeSpring;

import fr.equipefilrouge.filrougeSpring.entity.CentreFormation;
import fr.equipefilrouge.filrougeSpring.entity.Formateur;
import fr.equipefilrouge.filrougeSpring.entity.Formation;
import fr.equipefilrouge.filrougeSpring.entity.Stagiaire;
import fr.equipefilrouge.filrougeSpring.enums.UserRole;
import fr.equipefilrouge.filrougeSpring.services.impl.CentreFormationServiceImpl;
import fr.equipefilrouge.filrougeSpring.services.impl.FormateurServiceImpl;
import fr.equipefilrouge.filrougeSpring.services.impl.FormationServiceImpl;
import fr.equipefilrouge.filrougeSpring.services.impl.StagiaireServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@SpringBootApplication
@ComponentScan
public class FilrougeApplication {

	private static FormateurServiceImpl formateurService;
	private static StagiaireServiceImpl stagiaireService;
	private static CentreFormationServiceImpl centreFormationService;
	private static FormationServiceImpl formationService;

	public FilrougeApplication(
			FormateurServiceImpl formateurService,
			CentreFormationServiceImpl centreFormationService,
			FormationServiceImpl formationService,
			StagiaireServiceImpl stagiaireService){
		FilrougeApplication.formateurService = formateurService;
		FilrougeApplication.centreFormationService = centreFormationService;
		FilrougeApplication.formationService = formationService;
		FilrougeApplication.stagiaireService = stagiaireService;
	}

	public static void main(String[] args) {
		SpringApplication.run(FilrougeApplication.class, args);

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

		List<CentreFormation> centre = centreFormationService.findAll();
		System.out.println(centre);

	}
}
