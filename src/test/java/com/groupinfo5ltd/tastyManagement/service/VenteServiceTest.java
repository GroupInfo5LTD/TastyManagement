package com.groupinfo5ltd.tastyManagement.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.groupinfo5ltd.tastyManagement.entity.Produit;
import com.groupinfo5ltd.tastyManagement.entity.Vendeur;
import com.groupinfo5ltd.tastyManagement.entity.Vente;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class VenteServiceTest {

	@Autowired
	IVenteService venteService ;
	
	@Autowired
	IProduitService produitService; 
	
	@Autowired
	IVendeurService vendeurService; 
	
	Vente venteEnregistrer ; 
	Produit produitEnregistrer; 
	Vendeur vendeurEnregistrer; 
	
	
	
	@BeforeEach
	public void init() {

		//Create a Produit instance in db. 
		Produit produit = new Produit(); 
		
		produit.setNom("pizza Pepperoni");
		produit.setCategorie("pizza");
		produit.setPrix(130);
		
		produitEnregistrer = produitService.ajouterProduit(produit);
		
		//Create a Vendeur instance in db.
		Vendeur vendeur = new Vendeur();
		
		vendeur.setFirstName("Med");
		vendeur.setLastName("Schneider");
		 
		vendeurEnregistrer = vendeurService.ajouterVendeur(vendeur); 
		
		//create a Vente instance in db
		
		Vente vente = new Vente();
		vente.setCreated_at(LocalDate.now());
		vente.getProduitsQuantiteVendu().put(produit, 10); 
		vente.setVendeur(vendeur);
		/**
		 * added after the test of "ajouterVente" is completed. 
		 */
		this.venteEnregistrer = venteService.ajouterVente(vente);
		
		
		
	}
	
	@AfterEach()
	void reset() { 
		venteService.supprimerToutLesVentes();
		produitService.supprimerProduit(produitEnregistrer);
		vendeurService.supprimerVendeur(vendeurEnregistrer);
	}
	
	@Test
//	@Transactional()
	void shouldReturnTrue_When_VenteAddedToDatabase() {
		//given 
		Vente venteTest = new Vente(); 
		venteTest.setCreated_at(LocalDate.now()); 
		venteTest.getProduitsQuantiteVendu().put(this.produitEnregistrer, 3); 
		venteTest.setVendeur(this.vendeurEnregistrer);
		//when
		
		Long id = venteService.ajouterVente(venteTest).getId(); 
		
		//then
		
		assertEquals(venteService.trouverVenteParId(id).getId(), id);
		
	}
	
	@Test
	void shouldReturnTrue_When_UpdatingVenteDate() {
		//given
		Vente venteUpdated = new Vente(); 
		venteUpdated.setId(this.venteEnregistrer.getId());
			//updated filed
		venteUpdated.setCreated_at(LocalDate.of(2020, 01, 01));
		venteUpdated.getProduitsQuantiteVendu().put(this.produitEnregistrer, 3); 
		venteUpdated.setVendeur(this.vendeurEnregistrer);
		//when
		
		venteService.modifierVente(venteUpdated); 
		
		
		//then
		
		assertEquals(venteService.trouverVenteParId(this.venteEnregistrer.getId()).getCreated_at(), LocalDate.of(2020, 01, 01));
	}
	
	@Test
	void shouldReturn_NewVente_When_UpdatingNonExistingVente() {
		// given

		long id = 155 ; 
		Vente venteUpdated = new Vente(); 
		// avoid having the same id as the existing generated id in the database 
		// We have only 1 instance in the database. 
		
		venteUpdated.setId((id != this.venteEnregistrer.getId()) ? id : id * 2 );
		venteUpdated.setCreated_at(LocalDate.of(2020, 01, 01));
		venteUpdated.getProduitsQuantiteVendu().put(this.produitEnregistrer, 3); 
		venteUpdated.setVendeur(this.vendeurEnregistrer);
		// when
		
		long realNewId = venteService.modifierVente(venteUpdated).getId(); 
		
		// then 
		
		assertNotNull(venteService.trouverVenteParId(realNewId)); 
		
	}
	@Test
	@Transactional
	void shouldReturnTrue_When_DeletedVente_DOESNTEXIST() {
		//given 
		Vente venteDeleted = new Vente(); 
		venteDeleted.setId(this.venteEnregistrer.getId() + 10);
		
		//when
		
		venteService.supprimerVente(venteDeleted);
		
		//then
		
		assertNull(venteService.trouverVenteParId(venteDeleted.getId()));
	}
	
	@Test
	@Transactional
	void shouldReturn0_When_DeletedVente() {
		
		//given 
		// we already have venteEnregistrer object. 
		
		int sizeBeforeDelete = venteService.trouverToutLesVentes().size(); 
		
		Vente vente = venteService.trouverVenteParId(venteEnregistrer.getId());
		log.info("**** TEST: VENDEUR LIE AU VENTE: " + vente.getVendeur().toString());
		vente.getProduitsQuantiteVendu().forEach((k,v)->log.info("**** TEST: Produit LIE AU VENTE: " + k.toString()));
		//when
		
		venteService.supprimerVente(venteEnregistrer);
		
		//then 
		assertEquals(sizeBeforeDelete - 1, venteService.trouverToutLesVentes().size()); 
	}
	
	@Test
	void shouldThrowException_When_VenteDosntHaveProduit() {

		// given
		Vente vente = new Vente(); 
		vente.setCreated_at(LocalDate.of(2020, 01, 01));
		vente.setVendeur(this.vendeurEnregistrer);
		
		// when
		try {
		venteService.ajouterVente(vente); 
		
		fail("Exception expected to be throwed"); 
		} catch (Exception e) {

		}
		
	}
	
	@Test
	void shouldThrowException_When_VenteDosntHaveVendeur() {

		// given
		Vente vente = new Vente(); 
		vente.setCreated_at(LocalDate.of(2020, 01, 01));
		vente.getProduitsQuantiteVendu().put(this.produitEnregistrer, 3);
		
		// when
		try {
		venteService.ajouterVente(vente); 
		
		fail("Exception expected to be throwed"); 
		} catch (Exception e) {

		}
		
	}
	
}
