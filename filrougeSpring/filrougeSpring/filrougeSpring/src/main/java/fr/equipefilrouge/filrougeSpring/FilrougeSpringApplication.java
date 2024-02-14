package fr.equipefilrouge.filrougeSpring;

import fr.equipefilrouge.filrougeSpring.entity.*;
import fr.equipefilrouge.filrougeSpring.enums.UserRole;
import fr.equipefilrouge.filrougeSpring.services.impl.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@SpringBootApplication
public class FilrougeSpringApplication {

	private static UsersServiceImpl usersService;
	private static LieuServiceImpl centreFormationService;
	private static FormationServiceImpl formationService;
	private static BootcampServiceImpl bootcampService;

	public FilrougeSpringApplication(
			LieuServiceImpl centreFormationService,
			FormationServiceImpl formationService,
			UsersServiceImpl usersService,
			BootcampServiceImpl bootcampService){
		FilrougeSpringApplication.centreFormationService = centreFormationService;
		FilrougeSpringApplication.formationService = formationService;
		FilrougeSpringApplication.usersService = usersService;
		FilrougeSpringApplication.bootcampService = bootcampService;
	}

	public static void main(String[] args) {
		SpringApplication.run(FilrougeSpringApplication.class, args);

		Users formateur = new Users("Chimot", "Cedric", "01/01/01/01/01",  "cedric02@hotmail.fr",
				"ced02830", "Ced@02830", UserRole.FORMATEUR, 4.5 );
		usersService.create(formateur);
		System.out.println(formateur);

		Users stagiaire = new Users("Chimot", "Cedric", "01/01/01/01/01",  "cedric02@hotmail.com",
				"ced02830", "Ced@02830", UserRole.CANDIDAT, null);
		usersService.create(stagiaire);
		System.out.println(stagiaire);

//		Lieu lieu = new Lieu("IBCegos", "Gare de Lille 59000");
//		centreFormationService.create(lieu);
//		System.out.println(lieu);

//		Formation formation = new Formation("Java JEE", 3500, "Formation Java et au framework Spring");
//		formationService.create(formation);
//		System.out.println(formation);

//		List<Lieu> centre = centreFormationService.findAll();
//		System.out.println(centre);
//
//		Formation formation1 = formationService.findById(1L);
//		System.out.println(formation1);


	}
}
