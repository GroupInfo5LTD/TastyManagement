package com.groupinfo5ltd.tastyManagement.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;

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
