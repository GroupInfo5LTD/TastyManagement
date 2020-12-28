package com.groupinfo5ltd.tastyManagement.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groupinfo5ltd.tastyManagement.entity.Vente;
import com.groupinfo5ltd.tastyManagement.repository.IVenteRepository;
import com.groupinfo5ltd.tastyManagement.service.IVenteService;

@Service
public class VenteService implements IVenteService{

	private static final Logger log = 
			LoggerFactory.getLogger(VenteService.class); 
	
	@Autowired 
	private IVenteRepository venteRepository;
	
	
	@Override
	public Vente ajouterVente(Vente vente) {
		
		checkVendeurProduit(vente);
		
		log.info("CREATE A new Vente" + vente.toString());
		return venteRepository.save(vente);
	}

	/**
	 * 
	 */
	@Override
	public Vente modifierVente(Vente vente) {
		
		checkVendeurProduit(vente);
		
		
		if(venteExists(vente.getId())) {
			log.info("UPDATED Vente: " + vente.toString() );
		}
		else {
			log.info("THE REQUESTED VENTE DOESN'T EXIST");
			log.info("CREATE A new Vente " + vente.toString());
		}
		return venteRepository.save(vente);
	}

	@Override
	public void supprimerVente(Vente vente) {
		if(venteExists(vente.getId())) {
			venteRepository.deleteById(vente.getId());
			/**
			 * remove the vente instance in every one of the produit instances. 
			 */
//			vente.getProduitsQuantiteVendu().forEach((k, v) -> k.getVentes().remove(vente));
			log.info("DELETE VENTE: " + vente.toString());
		}
		else {
			log.info("VENTE DOESN'T EXIST IN THE DATABASE. the requested vente to be deleted: " + vente.toString());
		}
		
	}
	public void supprimerToutLesVentes() {
		venteRepository.deleteAll();
	}
	public Boolean venteExists(Long id) {
		return venteRepository.findById(id).isPresent(); 
	}

	@Override
	public Vente trouverVenteParId(Long id) {
		Optional<Vente>vente = venteRepository.findById(id); 
		if(vente.isPresent()) {
			log.info("GET Vente by ID" + id);
			return vente.get(); 
		}
		log.error("UNABLE TO FIND Vente with an id of : "  + id);
		return null; 
	}
	
	
	
	/**
	 * Vente doit etre lie a un vendeur et un produit au moins. 
	 * @param vente : vente a checker. 
	 */
	public void checkVendeurProduit(Vente vente) {
//		try {
			if(vente.getVendeur() == null) { 	
				throw new RuntimeException("VENDEUR UNDEFINIED"); 
			}
			if(vente.getProduitsQuantiteVendu().isEmpty()) {
				throw new RuntimeException("PRODUIT UNDEFINIED"); 
			}
			
//		} catch (RuntimeException e) {
//			log.error(e.getMessage());
//		}
	}

	@Override
	public Set<Vente> trouverToutLesVentes() {
		Set<Vente> ventes = new HashSet<>(); 
		venteRepository.findAll()
				.forEach((vente -> ventes.add(vente)));
		if(ventes.isEmpty()) {
			log.info("VENTE TABLE IS EMPTY");
		}
		return ventes;
	}
	
	
}
