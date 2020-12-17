package com.groupinfo5ltd.tastyManagement.service;

import com.groupinfo5ltd.tastyManagement.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProduitService {
    @Autowired
    ProduitRepository produitRepository;

}
