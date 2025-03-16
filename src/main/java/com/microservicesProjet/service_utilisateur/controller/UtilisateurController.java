package com.microservicesProjet.service_utilisateur.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.microservicesProjet.service_utilisateur.service.UtilisateurService;
import com.microservicesProjet.service_utilisateur.model.Utilisateur;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {
    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping
    public List<Utilisateur> getUtilisateurs() {
        return utilisateurService.getAllUtilisateurs();
    }
}