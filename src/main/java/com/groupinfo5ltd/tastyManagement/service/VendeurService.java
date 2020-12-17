package com.groupinfo5ltd.tastyManagement.service;

import com.groupinfo5ltd.tastyManagement.repository.VendeurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendeurService {
    @Autowired
    VendeurRepository vendeurRepository;
}
