package com.groupinfo5ltd.tastyManagement.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groupinfo5ltd.tastyManagement.entity.Vente;
import com.groupinfo5ltd.tastyManagement.repository.VenteRepository;
import com.groupinfo5ltd.tastyManagement.service.IVenteService;

@Service
public class VenteService implements IVenteService{

	private static final Logger log = 
			LoggerFactory.getLogger(VenteService.class); 
	
	@Autowired 
	private VenteRepository venteRepository;
	
	@Override
	public Vente ajouterVente(Vente vente) {
		log.info("CREATE A new Vente" + vente.toString());
		return venteRepository.save(vente);
	}

	/**
	 * 
	 */
	@Override
	public Vente modifierVente(Vente vente) {
		if(venteExists(vente.getId())) {
			log.info("UPDATED Vente: " + vente.toString() );
		}
		else {
			log.info("THE REQUESTED VENTE DOESN'T EXIST");
			log.info("CREATE A new Vente" + vente.toString());
		}
		return venteRepository.save(vente);
	}

	@Override
	public void supprimerVente(Vente vente) {
		if(venteExists(vente.getId())) {
			venteRepository.deleteById(vente.getId());
			log.info("DELETE VENTE: " + vente.toString());
		}
		else {
			log.info("VENTE DOESN'T EXIST IN THE DATABASE. the requested vente to be deleted: " + vente.toString());
		}
		
	}
	
	public Boolean venteExists(Long id) {
		return venteRepository.findById(id) != null; 
	}

	@Override
	public Vente trouverVenteParId(Long id) {
		Optional<Vente>vente = venteRepository.findById(id); 
		if(vente.isPresent()) {
			return vente.get(); 
		}
		return null; 
	}
	
}
