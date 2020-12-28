package com.groupinfo5ltd.tastyManagement.service.impl;

import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groupinfo5ltd.tastyManagement.entity.Vendeur;
import com.groupinfo5ltd.tastyManagement.repository.IVendeurRepository;
import com.groupinfo5ltd.tastyManagement.service.IVendeurService;

@Service
public class VendeurService implements IVendeurService {

	private static final Logger log = LoggerFactory.getLogger(VendeurService.class);
	@Autowired
	IVendeurRepository vendeurRepository;

	@Override
	public Vendeur ajouterVendeur(Vendeur vendeur) {
		Vendeur vendeurToCreate = null;
		try {
			vendeurToCreate = vendeurRepository.save(vendeur);
			log.info("CREATE A new Vendeur" + vendeur.toString());
		} catch (Exception e) {
			log.error("UNABLE to CREATE A new Vendeur" + vendeur.toString());
		}
		return vendeurToCreate;
	}

	@Override
	public Vendeur modifierVendeur(Vendeur vendeur) {
		Vendeur vendeurToUpdate = null;
		try {
			if(vendeurExists(vendeur.getId())) {
				log.info("UPDATE Vendeur" + vendeur.toString());
			}
			else { 
				log.info("THE REQUESTED VENTE DOESN'T EXIST");
				log.info("CREATE A new Vendeur " + vendeur.toString());
			}
			vendeurToUpdate = vendeurRepository.save(vendeur);
			
		} catch (Exception e) {
			log.error("UNABLE to UPDATE Vendeur" + vendeur.toString());
		}
		return vendeurToUpdate;
	}

	@Override
	public void supprimerVendeur(Vendeur vendeur) {
		try {
			if(vendeurExists(vendeur.getId())) { 
				log.info("DELETE Vendeur" + vendeur.toString());
			}
			else { 
				log.info("VENTE DOESN'T EXIST IN THE DATABASE. the requested vente to be deleted: " + vendeur.toString()); 
			}
			vendeurRepository.delete(vendeur);
			
		} catch (Exception e) {
			log.error("UNABLE to DELETE Vendeur" + vendeur.toString());
		}

	}

	@Override
	public Vendeur trouverVendeurParId(Long id) {
		Vendeur vendeur = null;
		try {
			Optional<Vendeur> optionalVendeur = vendeurRepository.findById(id);
			if (optionalVendeur.isPresent()) {
				vendeur = optionalVendeur.get();
			}
			log.info("GET Vendeur by ID" + id);
		} catch (Exception e) {
			log.error("UNABLE to GET Vendeur by ID" + id);
		}
		return vendeur;
	}
	
	public Boolean vendeurExists(Long id) {
		return !vendeurRepository.findById(id).isEmpty(); 
	}

	@Override
	public Set<Vendeur> trouverToutLesVendeurs() {
		// TODO Auto-generated method stub
		return null;
	}

}
