package tn.spring.gestioncatalogues.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.spring.gestioncatalogues.entities.Produit;
import tn.spring.gestioncatalogues.repository.ProduitRepository;

import java.util.List;
@AllArgsConstructor
@Service
public class ServiceProduit implements IServiceProduit{
//    public ServiceProduit(ProduitRepository produitRepository) {
//        this.produitRepository = produitRepository;
//    }

    //@Autowired
    ProduitRepository produitRepository;




    @Override
    public void addProduct(Produit p) {
        produitRepository.save(p);
    }

    @Override
    public void updateProduct(Long id, Produit p) {
        Produit pp = getProduit(id);
        if (pp != null)
        {
            pp.setNom(p.getNom());
            pp.setPrix(p.getPrix());
            pp.setQuantite(p.getQuantite());
            pp.setCategorie(p.getCategorie());
            produitRepository.save(p);
        }
    }

    @Override
    public void deleteProduit(Long id) {
        produitRepository.deleteById(id);
    }

    @Override
    public Produit getProduit(Long id) {
        return produitRepository.findById(id).orElse(null);
    }

    @Override
    public List<Produit> getAllProducts() {
        return produitRepository.findAll();
    }

    @Override
    public List<Produit> getProductsByMC(String mc) {
        return produitRepository.findByNomContains(mc);
    }
}
