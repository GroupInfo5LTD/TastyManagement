package com.groupinfo5ltd.tastyManagement.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.groupinfo5ltd.tastyManagement.entity.Produit;


/**
 * switch to PagingAndSortingRepository when necessary 
 * @author medkhalil
 *
 */
@Repository
public interface IProduitRepository extends CrudRepository<Produit, Long>{
	public Optional<Produit> findProduitById(Long id) ;
}
