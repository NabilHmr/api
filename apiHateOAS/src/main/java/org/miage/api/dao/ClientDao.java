package org.miage.api.dao;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.miage.api.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ClientDao {

    protected final EntityManager em;

    @Autowired
    public ClientDao(EntityManager em) {
        this.em = em;
    }

    // Méthode pour enregistrer un nouveau client
    public Client saveClient(Client client) {
        em.persist(client);
        em.flush();
        return client;

    }

    // Méthode pour récupérer tous les clients
    public List<Client> getAllClients() {
        return em.createQuery("SELECT c FROM Client c", Client.class).getResultList();
    }

    // Méthode pour récupérer un client par son ID
    public Client getClientById(Integer id) {
        return em.find(Client.class, id);
    }
}

