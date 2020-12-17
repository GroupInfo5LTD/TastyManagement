package com.groupinfo5ltd.tastyManagement.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.ManyToMany;
//import javax.persistence.MapKeyJoinColumn;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Recette {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id; 
	
	private String nom; 
	
	/**
	 * Map represent Ressource and it's quantity in a weight unit (gram for example). 
	 */
//	@ManyToMany(mappedBy = "recette", targetEntity = IRessource.class)
//	@MapKeyJoinColumn(name = "produit_id")
	private Map<IRessource, Float> ressourceQuatite = new HashMap<>(); 
}
