package com.groupinfo5ltd.tastyManagement.entity;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyJoinColumn;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Vente {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id; 
	
	@Column(nullable = false)
	private LocalDate created_at; 
	
	@ManyToMany(mappedBy = "ventes", targetEntity = Produit.class)
	@MapKeyJoinColumn(name = "produit_id")
	private Map<Produit, Integer> produitsQuantiteVendu = new HashMap<>(); 
	
	@ManyToOne(targetEntity = Vendeur.class)
	private Vendeur vendeur; 
}
