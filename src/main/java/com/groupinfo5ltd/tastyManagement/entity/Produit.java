package com.groupinfo5ltd.tastyManagement.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Produit {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id; 
	@Column(nullable = false)
	private String nom; 
	@Column(nullable = false)
	private String categorie; 
	
	@ManyToMany
	private Set <Vente> ventes = new HashSet<>(); 
}
