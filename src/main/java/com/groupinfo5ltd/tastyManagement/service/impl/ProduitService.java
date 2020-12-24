package com.groupinfo5ltd.tastyManagement.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groupinfo5ltd.tastyManagement.entity.Produit;
import com.groupinfo5ltd.tastyManagement.repository.IProduitRepository;
import com.groupinfo5ltd.tastyManagement.service.IProduitService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProduitService implements IProduitService{

	@Autowired
	private IProduitRepository produitRepository; 
	@Override
	public Produit ajouterProduit(Produit produit) {
		log.info("CREATE A new Produit" + produit.toString());
		return produitRepository.save(produit);
	}

	@Override
	public Produit modifierProduit(Produit produit) {
		if(produitExists(produit.getId())) {
			log.info("UPDATED Produit: " + produit.toString() );
		}
		else {
			log.info("THE REQUESTED Produit DOESN'T EXIST");
			log.info("CREATE A new Produit" + produit.toString());
		}
		return produitRepository.save(produit);
	}

	private boolean produitExists(long id) {
		return produitRepository.findById(id).isPresent(); 
	}

	@Override
	public void supprimerProduit(Produit produit) {
		if(produitExists(produit.getId())) {
			produitRepository.deleteById(produit.getId());
			log.info("DELETE Produit: " + produit.toString());
		}
		else {
			log.info("Produit DOESN'T EXIST IN THE DATABASE. the requested Produit to be deleted: " + produit.toString());
		}
		
	}

	@Override
	public Produit trouverProduitParId(Long id) {
		Optional<Produit> produit = produitRepository.findById(id); 
		if(produit.isPresent()) {
			return produit.get(); 
		}
		return null; 
	}

}
