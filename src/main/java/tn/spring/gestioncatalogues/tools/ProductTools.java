package tn.spring.gestioncatalogues.tools;

import lombok.AllArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;
import tn.spring.gestioncatalogues.entities.Produit;
import tn.spring.gestioncatalogues.repository.ProduitRepository;

import java.util.List;

@Component
@AllArgsConstructor
public class ProductTools {
    private ProduitRepository produitRepository;

    @Tool(name="getProduct", description = "retourne le produit à partir de son unique id")
    public Produit getProduit(@ToolParam(description = "l'id du produit") Long id) {
        return produitRepository.findById(id).orElse(null);
    }

    @Tool(name="getProducts", description = "retourner la liste de tous les produits existantes dans ma base de données")
    public List<Produit> getAllProducts() {
        return produitRepository.findAll();
    }

    @Tool(
            name = "createProduct",
            description = "Crée et enregistre un nouveau produit dans la base de données MySQL"
    )
    public Produit createProduct(
            @ToolParam(
                    description = "Nom commercial du produit",
                    required = true
            )
            String nom,

            @ToolParam(
                    description = "Prix de vente en dinars (positif)",
                    required = true
            )
            double prix,

            @ToolParam(
                    description = "Quantité initiale en stock",
                    required = true
            )
            int quantite,

            @ToolParam(
                    description = "Description détaillée du produit",
                    required = true
            )
            String description
    ) {
        // 1. Validation des données
        if (nom == null || nom.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Le nom du produit est obligatoire."
            );
        }

        if (prix <= 0) {
            throw new IllegalArgumentException(
                    "Le prix doit être strictement positif."
            );
        }

        if (quantite < 0) {
            throw new IllegalArgumentException(
                    "La quantité ne peut pas être négative."
            );
        }

        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "La description du produit est obligatoire."
            );
        }

        // 2. Création de l'entité Produit
        Produit produit = new Produit();
        produit.setNom(nom);
        produit.setPrix(prix);
        produit.setQuantite(quantite);

        // 3. Enregistrement dans la base de données
        return produitRepository.save(produit);
    }

}
