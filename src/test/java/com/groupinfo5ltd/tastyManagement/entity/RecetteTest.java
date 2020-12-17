package com.groupinfo5ltd.tastyManagement.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.jupiter.api.Test;

/**
 * Figuring out how Mockito works.
 * @author medkhalil
 *
 */

public class RecetteTest {

	private Recette recette; 
	@Test 
	void shouldRet_CorrectRessourceName_When_AskedforTheRightRessourceName_In_Recette(){
		
		//given
			// create a recette
		recette = new Recette(); 
		recette.setNom("Recette Name");
			
			//create a Ressource Mock
		
		IRessource ressourceTest = mock(IRessource.class);
		
		when(ressourceTest.getNom()).thenReturn("RessourceTest");
		when(ressourceTest.getPrixParGramme()).thenReturn((float) 12); 
		
		// when
		recette.getRessourceQuatite().put(ressourceTest, (float) 10);
		IRessource ressourceRecette  = recette.getRessourceQuatite().keySet()
										.stream()
										.filter(k -> k.getNom().equals("RessourceTest"))
										.reduce((f,s) -> f).get();
		//then 
		
		assertEquals(recette.getRessourceQuatite().get(ressourceTest), 10);   
		assertEquals(ressourceRecette.getNom(), "RessourceTest");
		assertEquals(ressourceRecette.getPrixParGramme(), 12);
	}
}
