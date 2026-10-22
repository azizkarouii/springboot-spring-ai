package tn.spring.gestioncatalogues.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;
import tn.spring.gestioncatalogues.entities.Produit;
import tn.spring.gestioncatalogues.service.IServiceProduit;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("product")
public class ProduitController {

    private IServiceProduit serviceProduit;

    @GetMapping("/all")
    public List<Produit> getAll(){
        return serviceProduit.getAllProducts();
    }

    @GetMapping("/{id}")
    public Produit getProduit(@PathVariable Long id){
        return serviceProduit.getProduit(id);
    }

    @GetMapping("/filter/{mc}")
    public List<Produit> getProductsBMC(@PathVariable String mc){
        return serviceProduit.getProductsByMC(mc);
    }
    @DeleteMapping("delete/{id}")
    public void deleteProduct(@PathVariable Long id){
        serviceProduit.deleteProduit(id);
    }
    @PostMapping("add")
    public void addProduct(@RequestBody Produit p){
        serviceProduit.addProduct(p);
    }
    @PutMapping("update/{id}")
    public void addProduct(@PathVariable Long id, @RequestBody Produit p){
        serviceProduit.updateProduct(id, p);
    }





}
