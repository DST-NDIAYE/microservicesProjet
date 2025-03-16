package com.microservicesProjet.service_produit.service;

import com.microservicesProjet.service_utilisateur.model.Utilisateur;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UtilisateurClient {
    private final RestTemplate restTemplate;
    private final String utilisateurServiceUrl = "http://localhost:8082/api/utilisateurs"; // Adresse du service-utilisateur

    public UtilisateurClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Utilisateur getUtilisateurById(Long id) {
        return restTemplate.getForObject(utilisateurServiceUrl + "/" + id, Utilisateur.class);
    }
}