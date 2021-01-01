package com.groupinfo5ltd.tastyManagement.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.groupinfo5ltd.tastyManagement.entity.Produit;
import com.groupinfo5ltd.tastyManagement.entity.Vendeur;
import com.groupinfo5ltd.tastyManagement.entity.Vente;
import com.groupinfo5ltd.tastyManagement.service.impl.ProduitService;
import com.groupinfo5ltd.tastyManagement.service.impl.VendeurService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProduitServiceTest {

	@Autowired 
	private ProduitService produitService; 
	
	
	private Produit produitEnregistrer;
	
	@BeforeEach
	public void init() {
		
		Produit produit = new Produit() ; 
		produit.setNom("pizza Pepperoni");
		produit.setCategorie("pizza");
		produit.setPrix(130);


		this.produitEnregistrer = produitService.ajouterProduit(produit);
	}
	
	@Test
	void shouldReturnTrue_When_ProduitVendeurAddedToDatabase() {
		// given 
		//vendeurEnregistrer
		
		int allProduitSize = produitService.trouverToutLesProduits().size(); 
		
		Produit produit = new Produit() ; 
		produit.setNom("salade gourmande");
		produit.setCategorie("salade");
		produit.setPrix(25);
		
		// when
		
		produitService.ajouterProduit(produit).getId(); 
		
		// then
		
		assertEquals(produitService.trouverToutLesProduits().size(), allProduitSize + 1); 
	}
	
	@Test
	void shouldReturn_UpdatedName_When_UpdatingName() {
		// given
		Produit produitUpdated = new Produit(); 
		produitUpdated.setId(this.produitEnregistrer.getId());
		produitUpdated.setNom("pizza fruit de mer");
		produitUpdated.setCategorie(this.produitEnregistrer.getCategorie());
		produitUpdated.setPrix(this.produitEnregistrer.getPrix());
		
		// when
		
		produitService.modifierProduit(produitUpdated); 
		
		// then
		
		assertEquals(produitService.trouverProduitParId(this.produitEnregistrer.getId()).getNom(), produitUpdated.getNom()); 
	}
	
	@Test
	void shouldReturn_NewProduit_When_UpdatingNonExistingProduit() {
		// given

		long id = 155 ; 
		Produit produitUpdated = new Produit(); 
		// avoid having the same id as the existing generated id in the database 
		// We have only 1 instance in the database. 
		
		produitUpdated.setId((id != this.produitEnregistrer.getId()) ? id : id * 2 );
		produitUpdated.setNom("pizza fruit de mer");
		produitUpdated.setCategorie(this.produitEnregistrer.getCategorie());
		produitUpdated.setPrix(this.produitEnregistrer.getPrix());
		
		// when
		
		long realNewId = produitService.modifierProduit(produitUpdated).getId(); 
		
		// then 
		
		assertNotNull(produitService.trouverProduitParId(realNewId)); 
		
	}
	
	@Test
	void shouldReturnNull_When_DeletedProduitDoesntExist() {
		// given
		Produit produitDeleted = new Produit(); 
		produitDeleted.setId(this.produitEnregistrer.getId());
		
		// when
		
		produitService.supprimerProduit(produitDeleted);

		//then
		
		assertNull(produitService.trouverProduitParId(this.produitEnregistrer.getId()));  
		// fix commit 
	}
	
}
