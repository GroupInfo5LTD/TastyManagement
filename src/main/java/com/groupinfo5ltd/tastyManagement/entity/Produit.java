package com.groupinfo5ltd.tastyManagement.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Produit {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id; 
	@Column(nullable = false)
	private String nom; 
	@Column(nullable = false)
	private String categorie;
	
	private float prix; 
	
	@ManyToMany
	private Set <Vente> ventes = new HashSet<>(); 
	
	
	/**
	 * those methods are overriden to use the map in entiy.Vente properly
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Produit) {
			
			return Long.compare(this.id, ((Produit)obj).id) == 0 ; 
		}
		
		return false; 
	}
	
	@Override 
	public int hashCode() {
		return Long.valueOf(this.id).hashCode(); 
	}
}
