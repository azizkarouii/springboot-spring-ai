package tn.spring.gestioncatalogues.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.spring.gestioncatalogues.entities.Produit;

import java.util.List;
@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
    //derived query
    public List<Produit> findByNomContains(String mc);

    //jpql
    @Query("select p from Produit p where p.nom like %:x%")
    public List<Produit> rechercherParMotCle(@Param("x") String mc);

}
