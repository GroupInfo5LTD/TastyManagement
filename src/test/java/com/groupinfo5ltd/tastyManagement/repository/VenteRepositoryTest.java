package com.groupinfo5ltd.tastyManagement.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.groupinfo5ltd.tastyManagement.entity.Produit;
import com.groupinfo5ltd.tastyManagement.entity.Vente;

/**
 * Testing the map field in the Vente Class, and it's interaction with the database. 
 * @author medkhalil
 *
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class VenteRepositoryTest {

	@Autowired
	IVenteRepository venteRepository ; 
	@Autowired
	IProduitRepository produitRepository; 
	private Vente vente ; 
	private Produit p1 ;
	@BeforeEach
	public void init() {
		Map<Produit,Integer> produits = new HashMap<>(); 
		p1 = new Produit(); 
		p1.setCategorie("something");
		p1.setNom("pizza");
		p1.setPrix(120);

		Produit p2 = new Produit(); 
		p2.setCategorie("something else");
		p2.setNom("taco");
		p2.setPrix(30);
		
		
		p1.setId(produitRepository.save(p1).getId()); 
		p2.setId(produitRepository.save(p2).getId());
		
		
		produits.put(p1, 2); 
		produits.put(p2, 4); 
		
		vente = new Vente(); 
		vente.setCreated_at(LocalDate.of(2020, 12, 14));
		vente.setProduitsQuantiteVendu(produits);
				
		Vente tempVente = venteRepository.save(vente);
		
		
//		
		vente.setId(tempVente.getId()); 
//		
		
	}
	
	@Test
	@Transactional()
	public void shouldReturnCorrectMap_When_AskedForCorrectMap() {
		//given 
		//vente and p1 objects
		 
		
		//when 
		Vente copyVente = venteRepository.findById(this.vente.getId()).get();
		
//		//then
//		
		assertEquals(copyVente.getProduitsQuantiteVendu().get(this.p1), 2);
		
	}
	
}
