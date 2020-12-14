package com.groupinfo5ltd.tastyManagement.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * this will change when we create the User class, as many of Vendeur infos will be in the User class
 * @author medkhalil
 *
 */
@Data
@Entity
@NoArgsConstructor
public class Vendeur {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id ; 
	
	@Column(nullable = false)
	private String firstName;
	
	@Column(nullable = false)
	private String lastName; 
	
	@OneToMany(mappedBy = "vendeur")
	private Set<Vente> ventesVendeur = new HashSet<>(); 
}
