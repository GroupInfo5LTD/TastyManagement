package com.groupinfo5ltd.tastyManagement.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.groupinfo5ltd.tastyManagement.entity.Vente;

@Repository
public interface VenteRepository extends CrudRepository<Vente, Long>{
	public Optional<Vente> findVenteById(long id); 
}
