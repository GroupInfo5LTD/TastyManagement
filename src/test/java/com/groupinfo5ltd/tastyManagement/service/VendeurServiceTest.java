package com.groupinfo5ltd.tastyManagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.groupinfo5ltd.tastyManagement.entity.Vendeur;
import com.groupinfo5ltd.tastyManagement.service.impl.VendeurService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class VendeurServiceTest {

	@Autowired 
	VendeurService vendeurService;
	
	Vendeur vendeurEnregistrer;
	
	@BeforeEach
	public void init() {
		
		Vendeur vendeur = new Vendeur() ; 
		vendeur.setFirstName("Med");
		vendeur.setLastName("Schneider");

		this.vendeurEnregistrer = vendeurService.ajouterVendeur(vendeur);
	}
	
	
	@Test
	void shouldReturnTrue_When_VendeurAddedToDatabase() {
		// given 
		//vendeurEnregistrer
		
		// when
		
		Long id = vendeurService.ajouterVendeur(this.vendeurEnregistrer).getId(); 
		
		// then
		
		assertEquals(vendeurService.trouverVendeurParId(id).getId(), id); 
	}
	
	@Test
	void shouldReturn_UpdatedFirstName_When_UpdatingFirstName() {
		// given
		Vendeur vendeurUpdated = new Vendeur(); 
		vendeurUpdated.setId(this.vendeurEnregistrer.getId());
		vendeurUpdated.setFirstName("Karl");
		vendeurUpdated.setLastName(this.vendeurEnregistrer.getLastName());
		
		// when
		
		vendeurService.modifierVendeur(vendeurUpdated); 
		
		// then
		
		assertEquals(vendeurService.trouverVendeurParId(this.vendeurEnregistrer.getId()).getFirstName(), "Karl"); 
	}
	
	@Test
	void shouldReturnNull_When_DeletedVendeurDoesntExist() {
		// given
		Vendeur vendeurDeleted = new Vendeur(); 
		vendeurDeleted.setId(this.vendeurEnregistrer.getId());
		
		// when
		
		vendeurService.supprimerVendeur(vendeurDeleted);

		//then
		
		assertNull(vendeurService.trouverVendeurParId(this.vendeurEnregistrer.getId())); 
	}
}
