package com.groupinfo5ltd.tastyManagement.service;

import com.groupinfo5ltd.tastyManagement.repository.VenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VenteService {
    @Autowired
    VenteRepository venteRepository;
}
