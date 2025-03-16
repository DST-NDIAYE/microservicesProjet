package com.microservicesProjet.service_utilisateur.service;

import org.springframework.stereotype.Service;
import com.microservicesProjet.service_utilisateur.model.Utilisateur;
import com.microservicesProjet.service_utilisateur.repository.UtilisateurRepository;
import java.util.List;

@Service
public class UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }
}