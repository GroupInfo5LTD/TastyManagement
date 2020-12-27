package com.groupinfo5ltd.tastyManagement.service;

import java.util.Set;

import com.groupinfo5ltd.tastyManagement.entity.Vendeur;

public interface IVendeurService {
	public Vendeur ajouterVendeur(Vendeur vendeur);
	public Vendeur modifierVendeur(Vendeur vendeur); 
	public void supprimerVendeur(Vendeur vendeur); 
	public Vendeur trouverVendeurParId(Long id); 
	public Set<Vendeur> trouverToutLesVendeurs(); 
}
