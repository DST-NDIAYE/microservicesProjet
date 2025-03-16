package com.microservicesProjet.service_produit.service;

import com.microservicesProjet.service_produit.service.UtilisateurClient;
import com.microservicesProjet.service_produit.model.Produit;
import com.microservicesProjet.service_utilisateur.model.Utilisateur;
import com.microservicesProjet.service_produit.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitService {

    @Autowired
    private ProduitRepository produitRepository;

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

    // ✅ Nouvelle méthode pour récupérer un produit avec l'utilisateur
    public Produit getProduitAvecUtilisateur(Long produitId, Long utilisateurId) {
        Produit produit = produitRepository.findById(produitId)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé !"));

        Utilisateur utilisateur = utilisateurClient.getUtilisateurById(utilisateurId);

        // Juste pour la démonstration, on peut retourner un objet combiné si nécessaire
        System.out.println("Produit acheté par l'utilisateur : " + utilisateur.getNom());

        return produit; // En pratique, tu peux enrichir l'objet avant de le renvoyer
    }
}