package com.groupinfo5ltd.tastyManagement.service;

import java.util.Set;

import com.groupinfo5ltd.tastyManagement.entity.Produit;

public interface IProduitService {
	public Produit ajouterProduit(Produit produit);
	public Produit modifierProduit(Produit produit); 
	public void supprimerProduit(Produit produit); 
	public Produit trouverProduitParId(Long id);
	public Set<Produit> trouverToutLesProduits(); 
}
