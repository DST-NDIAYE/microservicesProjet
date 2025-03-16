package com.microservicesProjet.service_produit.service;

import com.microservicesProjet.service_produit.service.UtilisateurClient;
import com.microservicesProjet.service_produit.model.Produit;
import com.microservicesProjet.service_utilisateur.model.Utilisateur;
import com.microservicesProjet.service_produit.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitService {

    // @Autowired
    // private ProduitRepository produitRepository;

    @Autowired
    private UtilisateurClient utilisateurClient; // ✅ Injection du client utilisateur

    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    public Optional<Produit> getProduitById(Long id) {
        return produitRepository.findById(id);
    }

    public Produit ajouterProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    public Produit modifierProduit(Long id, Produit produit) {
        return produitRepository.findById(id).map(p -> {
            p.setNom(produit.getNom());
            p.setPrix(produit.getPrix());
            p.setQuantite(produit.getQuantite());
            p.setDescription(produit.getDescription());
            return produitRepository.save(p);
        }).orElseThrow(() -> new RuntimeException("Produit non trouvé !"));
    }

    public void supprimerProduit(Long id) {
        produitRepository.deleteById(id);
    }


    private final ProduitRepository produitRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public ProduitService(ProduitRepository produitRepository, RestTemplate restTemplate) {
        this.produitRepository = produitRepository;
        this.restTemplate = restTemplate;
    }

    public Produit getProduitAvecUtilisateur(Long produitId, Long utilisateurId) {
        // Récupérer le produit
        Produit produit = produitRepository.findById(produitId)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé !"));

        // Récupérer l'utilisateur depuis `service-utilisateur`
        String url = "http://localhost:8081/api/utilisateurs/" + utilisateurId;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            String utilisateur = response.getBody();
            produit.setDescription(produit.getDescription() + " - Utilisateur : " + utilisateur);
        } else {
            produit.setDescription(produit.getDescription() + " - Utilisateur inconnu");
        }

        return produit;
    }
}