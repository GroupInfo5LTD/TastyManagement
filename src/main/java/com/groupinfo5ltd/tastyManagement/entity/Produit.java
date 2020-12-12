package com.groupinfo5ltd.tastyManagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


import lombok.Data;

@Data
@Entity
public class Produit {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id; 
	@Column(nullable = false)
	private String nom; 
	@Column(nullable = false)
	private String categorie; 
	
	@ManyToOne
	private Vente vente; 
}
