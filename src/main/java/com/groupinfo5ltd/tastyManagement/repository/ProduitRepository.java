package com.groupinfo5ltd.tastyManagement.repository;

import org.springframework.data.repository.CrudRepository;

import com.groupinfo5ltd.tastyManagement.entity.Produit;


/**
 * switch to PagingAndSortingRepository when necessary 
 * @author medkhalil
 *
 */
public interface ProduitRepository extends CrudRepository<Produit, Long>{

}
