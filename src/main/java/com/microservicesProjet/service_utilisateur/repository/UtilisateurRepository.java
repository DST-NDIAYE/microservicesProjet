package com.microservicesProjet.service_utilisateur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.microservicesProjet.service_utilisateur.model.Utilisateur;
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
}