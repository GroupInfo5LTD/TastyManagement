package com.groupinfo5ltd.tastyManagement.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.groupinfo5ltd.tastyManagement.entity.Vendeur;


@Repository
public interface VendeurRepository extends CrudRepository<Vendeur, Long>{
	public Optional<Vendeur> findVendeurById(long id); 
}
