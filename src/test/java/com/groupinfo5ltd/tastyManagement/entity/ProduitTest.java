package com.groupinfo5ltd.tastyManagement.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProduitTest {
    private Produit produit = new Produit(1,"imprimante","cat1",1000,null);

    @BeforeEach
    void setup() {
        Set<Vente> ventes = new HashSet<>();
        Vente v1 = new Vente();
        v1.setId(1);
        v1.setCreated_at(LocalDate.of(2020,12,17));

        Vente v2 = new Vente();
        v2.setId(1);
        v2.setCreated_at(LocalDate.of(2020,12,17));

        ventes.add(v1);
        ventes.add(v2);

        this.produit.setVentes(ventes);

    }

    @Test
    void should_ReturnTrue_When_VenteExists() {

        //given
        Vente v3 = new Vente();
        Vente v4 = new Vente();
        Vendeur vendeur = new Vendeur();
        vendeur.setId(1);
        vendeur.setFirstName("Ahmed");
        vendeur.setLastName("toto");
        v3.setId(1);
        v3.setCreated_at(LocalDate.of(2020,12,17));
        v3.setVendeur(vendeur);
        v4.setId(2);
        v4.setCreated_at(LocalDate.of(2020,12,17));
        v4.setVendeur(vendeur);




        //when

        this.produit.getVentes().add(v3);

        //then

        assertTrue(this.produit.getVentes().contains(v3));
        assertFalse(this.produit.getVentes().contains(v4));


    }
    @Test
    void should_ReturnCorrectValue_When_addNewProduitWithAnExistingId() {

        //given
        int quantity_exist = 1;

        Vente vente_existe = new Vente();
        Vendeur vendeur = new Vendeur();
        vendeur.setId(1);
        vendeur.setFirstName("Ahmed");
        vendeur.setLastName("toto");
        vente_existe.setId(6);
        vente_existe.setVendeur(vendeur);
        vente_existe.setCreated_at(LocalDate.of(2020,12,17));

        this.produit.getVentes().add(vente_existe);
        // A completer ...

    }

}
