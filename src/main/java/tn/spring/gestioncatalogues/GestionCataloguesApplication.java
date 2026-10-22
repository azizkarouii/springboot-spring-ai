package tn.spring.gestioncatalogues;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tn.spring.gestioncatalogues.entities.Categorie;
import tn.spring.gestioncatalogues.entities.Produit;
import tn.spring.gestioncatalogues.repository.CategorieRepository;
import tn.spring.gestioncatalogues.repository.ProduitRepository;

@SpringBootApplication
public class GestionCataloguesApplication {
    //@Bean
    CommandLineRunner commandLineRunner(ProduitRepository produitRepository, CategorieRepository categorieRepository){
        return arg->{
            Categorie c1 = new Categorie(null, "informatique",null);
            Categorie c2 = Categorie.builder()
                                    .nom("electronique")
                                    .build();
            categorieRepository.save(c1);
            categorieRepository.save(c2);

            produitRepository.save(Produit.builder()
                                            .nom("pc")
                                            .categorie(c1)
                                            .prix(50)
                                            .quantite(10)
                                            .build());
            produitRepository.save(Produit.builder()
                    .nom("smartphone")
                    .categorie(c2)
                    .prix(5000)
                    .quantite(10)
                    .build());
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(GestionCataloguesApplication.class, args);
    }

}
