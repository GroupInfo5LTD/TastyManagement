package com.groupinfo5ltd.tastyManagement.service;

import com.groupinfo5ltd.tastyManagement.entity.Vente;

public interface IVenteService {
	public Vente ajouterVente(Vente vente);
	public Vente modifierVente(Vente vente); 
	public void supprimerVente(Vente vente); 
	public Vente trouverVenteParId(Long id); 
}
