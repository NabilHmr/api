package org.miage.api.dao.repository;

import org.miage.api.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
    // Pas besoin de mettre les methodes ici, parce que les méthodes CRUD sont fournies par défaut par JPA
}

