package com.groupinfo5ltd.tastyManagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.groupinfo5ltd.tastyManagement.entity.Vente;
import com.groupinfo5ltd.tastyManagement.service.impl.VenteService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class VenteServiceTest {

	@Autowired
	IVenteService venteService ;
	
	Vente venteEnregistrer ; 
	
	@BeforeEach
	public void init() {
		//create a Vente instance in db 
		
		Vente vente = new Vente(); 
		
		vente.setCreated_at(LocalDate.now());
		
		/**
		 * added after the test of "ajouterVente" is completed. 
		 */
		this.venteEnregistrer = venteService.ajouterVente(vente);
		
		
		
	}
	
	@Test
//	@Transactional()
	void shouldReturnTrue_When_VenteAddedToDatabase() {
		//given 
		Vente venteTest = new Vente(); 
		venteTest.setCreated_at(LocalDate.now()); 
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
		
		//when
		
		venteService.modifierVente(venteUpdated); 
		
		
		//then
		
		assertEquals(venteService.trouverVenteParId(this.venteEnregistrer.getId()).getCreated_at(), LocalDate.of(2020, 01, 01));
	}
	
	@Test
	void shouldReturnTrue_When_DeletedVente_DOESNTEXIST() {
		//given 
		Vente venteDeleted = new Vente(); 
		venteDeleted.setId(this.venteEnregistrer.getId());
		
		//when
		
		venteService.supprimerVente(venteDeleted);
		
		//then
		
		assertNull(venteService.trouverVenteParId(this.venteEnregistrer.getId()));
	}
	
}
