package tn.spring.gestioncatalogues.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.spring.gestioncatalogues.entities.Categorie;
import tn.spring.gestioncatalogues.entities.Produit;
import tn.spring.gestioncatalogues.repository.CategorieRepository;
import tn.spring.gestioncatalogues.repository.ProduitRepository;

import java.util.List;


@AllArgsConstructor
@Service
public class CategorieService implements IServiceCategorie{
    //ajout getall getbyid delete
    CategorieRepository categorieRepository;
    @Override
    public void addCategorie(Categorie c) {
        categorieRepository.save(c);
    }

    @Override
    public void updateCategorie(Long id, Categorie p) {

    }//

    @Override
    public void deleteCategorie(Long id) {

        Categorie c = categorieRepository.findById(id).orElse(null);
        if (c!=null){
            for (Produit p : c.getProduits())
                p.setCategorie(null);
        }

        categorieRepository.deleteById(id);
    }

    @Override
    public Categorie getCategorie(Long id) {
        return categorieRepository.findById(id).orElse(null);
    }

    @Override
    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }



}
