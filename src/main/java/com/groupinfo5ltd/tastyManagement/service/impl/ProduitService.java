package com.groupinfo5ltd.tastyManagement.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groupinfo5ltd.tastyManagement.entity.Produit;
import com.groupinfo5ltd.tastyManagement.entity.Vente;
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

	@Override
	public Set<Produit> trouverToutLesProduits() {
		Set<Produit> produits = new HashSet<>(); 
		produitRepository.findAll()
					.forEach(produit -> produits.add(produit));
		if(produits.isEmpty()) { 
			log.info("PRODUIT TABLE IS EMPTY");
		}
		else  {
			log.info("PRODUIT TABLE HAS " + produits.size() + " rows ");
		}
		return produits;
	}
	
//	@Override
//	public Set<Vente> trouverToutLesVentes() {
//		Set<Vente> ventes = new HashSet<>(); 
//		venteRepository.findAll()
//				.forEach((vente -> ventes.add(vente)));
//		if(ventes.isEmpty()) {
//			log.info("VENTE TABLE IS EMPTY");
//		}
//		else { 
//			log.info("VENDEUR TABLE HAS " + ventes.size() + " rows");
//		}
//		return ventes;
//	}

}
