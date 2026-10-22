package tn.spring.gestioncatalogues.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.spring.gestioncatalogues.entities.Categorie;

import tn.spring.gestioncatalogues.service.IServiceCategorie;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("categorie")
public class CategorieController {
    private IServiceCategorie serviceCategorie;

    @PostMapping("add")
    public void addCategorie(@RequestBody Categorie c){
        serviceCategorie.addCategorie(c);
    }

    @GetMapping("/all")
    public List<Categorie> getAll(){
        return serviceCategorie.getAllCategories();
    }

    @GetMapping("/{id}")
    public Categorie getCategorie(@PathVariable Long id){
        return serviceCategorie.getCategorie(id);
    }

    @DeleteMapping("delete/{id}")
    public void deleteCategorie(@PathVariable Long id){
        serviceCategorie.deleteCategorie(id);
    }
}
