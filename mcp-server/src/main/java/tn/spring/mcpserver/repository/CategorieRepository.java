package tn.spring.mcpserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.spring.mcpserver.entities.Categorie;

import java.util.Optional;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long> {
    Optional<Categorie> findByNomIgnoreCase(String nom);
}
