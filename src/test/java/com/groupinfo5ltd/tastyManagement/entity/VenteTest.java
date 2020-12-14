package com.groupinfo5ltd.tastyManagement.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VenteTest {
	private Vente vente; 
	
	@BeforeEach
	void setup() {
		Map<Produit,Integer> produits = new HashMap<>(); 
		Produit p1 = new Produit(); 
		p1.setId(2);
		p1.setCategorie("something");
		p1.setNom("pizza");
		p1.setPrix(120);

		Produit p2 = new Produit(); 
		p2.setId(3);
		p2.setCategorie("something else");
		p2.setNom("taco");
		p2.setPrix(30);
		
		produits.put(p1, 2); 
		produits.put(p2, 4); 
		
		this.vente = new Vente(); 
		this.vente.setId(1);
		this.vente.setCreated_at(LocalDate.of(2020, 12, 14));
		this.vente.setProduitsQuantiteVendu(produits);
		
		
	}
	
	@Test
	void should_ReturnTrue_When_keyProduitExists() { 
		
		//given
		Produit p4 = new Produit(); 
		Produit p3 = new Produit(); 
		p3.setId(4);
		p3.setCategorie("something else else");
		p3.setNom("burger");
		p3.setPrix(25);
		
		
		//when
		
		this.vente.getProduitsQuantiteVendu().put(p3, 1); 
		
		//then
		
		assertTrue(this.vente.getProduitsQuantiteVendu().containsKey(p3));
		assertFalse(this.vente.getProduitsQuantiteVendu().containsKey(p4));
		
		
	}
	
	
	/**
	 * Observation: 
	 * 
	 * if it sees a duplicate key, the key object won't change but the value will. 
	 */
	@Test
	void should_ReturnCorrectValue_When_addNewProduitWithAnExistingId() {
		
		//given
		int quantity_exist = 1; 
		
		Produit p_exist = new Produit(); 
		p_exist.setId(5);
		p_exist.setCategorie("something else else");
		p_exist.setNom("burger");
		p_exist.setPrix(25);
		
		this.vente.getProduitsQuantiteVendu().put(p_exist, quantity_exist);
		
		int quantity_new = 4; 
		Produit p_duplicate = new Produit(); 
		p_duplicate.setId(5);
		p_duplicate.setCategorie("duplicate");
		p_duplicate.setNom("burger v2");
		p_duplicate.setPrix(28);
		
		
		
		//when 
		
		this.vente.getProduitsQuantiteVendu().put(p_duplicate, quantity_new);
		
		//then
		
		assertEquals(this.vente.getProduitsQuantiteVendu().get(p_exist), quantity_new);
		
	}
}
