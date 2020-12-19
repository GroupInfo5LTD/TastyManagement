package com.groupinfo5ltd.tastyManagement.service;

import com.groupinfo5ltd.tastyManagement.entity.Vendeur;

public interface IVendeurService {
	public Vendeur ajouterVendeur(Vendeur vendeur);
	public Vendeur modifierVendeur(Vendeur vendeur); 
	public void supprimerVendeur(Vendeur vendeur); 
	public Vendeur trouverVendeurParId(Long id); 
}
