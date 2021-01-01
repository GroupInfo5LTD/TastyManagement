package com.groupinfo5ltd.tastyManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.groupinfo5ltd.tastyManagement.entity.Vendeur;
import com.groupinfo5ltd.tastyManagement.service.IVendeurService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DataInitializer implements CommandLineRunner{
 
	@Autowired
	IVendeurService vendeurService;
	@Override
	public void run(String... args) throws Exception {
		log.info("Initialize the 'UNDEFINIED VENDEUR' instance.");
		Vendeur undefiniedVendeur = new Vendeur() ; 
		undefiniedVendeur.setFirstName("UNDEFINIED");
		undefiniedVendeur.setLastName("UNDEFINIED");
		vendeurService.ajouterVendeur(undefiniedVendeur); 
		
		Vendeur.setUndefiniedVendeurId(undefiniedVendeur.getId());
	}

}
