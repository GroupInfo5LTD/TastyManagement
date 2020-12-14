package com.groupinfo5ltd.tastyManagement.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Vente {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id; 
	
	@Column(nullable = false)
	private LocalDate created_at; 
	
	@Column(nullable = false)
	private float total; 
	
	@ManyToMany(mappedBy = "ventes")
	private Set<Produit> produitsVendu = new HashSet<>(); 
	
	@ManyToOne(targetEntity = Vendeur.class)
	private Vendeur vendeur; 
}
