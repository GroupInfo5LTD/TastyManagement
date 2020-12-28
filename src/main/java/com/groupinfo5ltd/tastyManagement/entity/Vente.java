package com.groupinfo5ltd.tastyManagement.entity;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.JoinColumn;

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
	
//	@ManyToMany(targetEntity = Produit.class, cascade = CascadeType.PERSIST)/
	@ElementCollection
	@CollectionTable(name="vente_produit_mapping",
					joinColumns = @JoinColumn(name = "vente_id"))
	@Column(name="quantite_produit")
	@MapKeyJoinColumn(name = "produit_id", referencedColumnName="id")
//	@JoinTable(name = "vente_produit_mapping",
//			joinColumns = {@JoinColumn(name = "vente_id", referencedColumnName = "id")}
//	)
//	@MapKeyJoinColumn(name = "produit_id")
//	@Column(name = "quantite")
	private Map<Produit, Integer> produitsQuantiteVendu = new HashMap<>(); 
	
	@ManyToOne()
	private Vendeur vendeur; 
}
