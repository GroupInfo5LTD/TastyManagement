package com.groupinfo5ltd.tastyManagement.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
	@OneToMany(mappedBy = "vente")
	private List<Produit> produitVendu = new ArrayList<>(); 
}
