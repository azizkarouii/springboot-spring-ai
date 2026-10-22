package tn.spring.gestioncatalogues.service;

import tn.spring.gestioncatalogues.entities.Produit;

import java.util.List;

public interface IServiceProduit {
    void addProduct(Produit p);
    void updateProduct(Long id, Produit p);
    void deleteProduit(Long id);
    Produit getProduit(Long id);
    List<Produit> getAllProducts();
    List<Produit> getProductsByMC(String mc);
}
