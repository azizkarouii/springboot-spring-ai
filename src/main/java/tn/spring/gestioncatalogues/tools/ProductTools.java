package tn.spring.gestioncatalogues.tools;

import lombok.AllArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;
import tn.spring.gestioncatalogues.entities.Categorie;
import tn.spring.gestioncatalogues.entities.Produit;
import tn.spring.gestioncatalogues.repository.CategorieRepository;
import tn.spring.gestioncatalogues.repository.ProduitRepository;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class ProductTools {
    private ProduitRepository produitRepository;
    private final CategorieRepository categorieRepository;


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
            description = "Crée un produit dans la base de données en associant une catégorie existante"
    )
    public Produit createProduct(
            @ToolParam(description = "Nom commercial du produit", required = true)
            String nom,

            @ToolParam(description = "Prix de vente en dinars, strictement positif", required = true)
            double prix,

            @ToolParam(description = "Quantité initiale en stock, supérieure ou égale à zéro", required = true)
            int quantite,

            @ToolParam(description = "Nom de la catégorie existante à laquelle appartient le produit", required = true)
            String nomCategorie
    ) {
        // 1. Validation des données
        if (nom == null || nom.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom du produit est obligatoire.");
        }

        if (prix <= 0) {
            throw new IllegalArgumentException("Le prix doit être strictement positif.");
        }

        if (quantite < 0) {
            throw new IllegalArgumentException("La quantité ne peut pas être négative.");
        }

        if (nomCategorie == null || nomCategorie.trim().isEmpty()) {
            throw new IllegalArgumentException("La catégorie est obligatoire.");
        }

        // 2. Rechercher la catégorie existante
        Categorie categorie = categorieRepository
                .findByNomIgnoreCase(nomCategorie.trim())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Catégorie introuvable : " + nomCategorie
                ));

        // 3. Créer le produit et associer la catégorie
        Produit produit = new Produit();
        produit.setNom(nom.trim());
        produit.setPrix(prix);
        produit.setQuantite(quantite);
        produit.setCategorie(categorie);

        // 4. Enregistrer le produit
        return produitRepository.save(produit);
    }

    @Tool(
            name = "getCatalogueStatistics",
            description = "Fournit des statistiques clés sur le catalogue : total, prix moyen, produit le plus cher et le moins cher"
    )
    public Map<String, Object> getCatalogueStatistics() {

        // 1. Récupérer tous les produits depuis le repository
        List<Produit> produits = produitRepository.findAll();

        // 2. Vérifier si le catalogue est vide
        if (produits.isEmpty()) {
            Map<String, Object> statistiques = new HashMap<>();
            statistiques.put("total", 0);
            statistiques.put("prixMoyen", 0.0);
            statistiques.put("produitPlusCher", null);
            statistiques.put("produitMoinsCher", null);

            return statistiques;
        }

        // 3. Calculer le nombre total de produits
        long total = produits.size();

        // 4. Calculer le prix moyen
        double prixMoyen = produits.stream()
                .mapToDouble(Produit::getPrix)
                .average()
                .orElse(0.0);

        // 5. Trouver le produit le plus cher
        Produit produitPlusCher = produits.stream()
                .max(Comparator.comparingDouble(Produit::getPrix))
                .orElse(null);

        // 6. Trouver le produit le moins cher
        Produit produitMoinsCher = produits.stream()
                .min(Comparator.comparingDouble(Produit::getPrix))
                .orElse(null);

        // 7. Construire la Map des statistiques
        Map<String, Object> statistiques = new HashMap<>();

        statistiques.put("total", total);
        statistiques.put("prixMoyen", prixMoyen);
        statistiques.put("produitPlusCher", produitPlusCher);
        statistiques.put("produitMoinsCher", produitMoinsCher);

        // 8. Retourner les statistiques
        return statistiques;
    }

    @Tool(
            name = "createCategorie",
            description = "Crée et enregistre une nouvelle catégorie dans la base de données"
    )
    public Categorie createCategorie(
            @ToolParam(
                    description = "Nom de la nouvelle catégorie",
                    required = true
            )
            String nom
    ) {
        // 1. Validation du nom
        if (nom == null || nom.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Le nom de la catégorie est obligatoire."
            );
        }

        // 2. Vérifier si la catégorie existe déjà
        boolean existe = categorieRepository
                .findByNomIgnoreCase(nom.trim())
                .isPresent();

        if (existe) {
            throw new IllegalArgumentException(
                    "La catégorie '" + nom + "' existe déjà."
            );
        }

        // 3. Créer la catégorie
        Categorie categorie = new Categorie();
        categorie.setNom(nom.trim());

        // 4. Enregistrer la catégorie
        return categorieRepository.save(categorie);
    }
}
