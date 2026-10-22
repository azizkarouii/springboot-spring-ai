package tn.spring.gestioncatalogues.service;

import tn.spring.gestioncatalogues.entities.Categorie;

import java.util.List;

public interface IServiceCategorie {
    void addCategorie(Categorie p);
    void updateCategorie(Long id, Categorie p);
    void deleteCategorie(Long id);
    Categorie getCategorie(Long id);
    List<Categorie> getAllCategories();
}
