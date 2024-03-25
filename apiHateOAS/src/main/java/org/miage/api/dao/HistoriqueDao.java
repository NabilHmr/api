package org.miage.api.dao;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.miage.api.models.DemandeCredit;
import org.miage.api.models.Historique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Transactional
public class HistoriqueDao {

    protected final EntityManager em;

    @Autowired
    public HistoriqueDao(EntityManager em) {
        this.em = em;
    }

    // Méthode pour enregistrer un nouveau client
    public Historique saveAction(DemandeCredit demandeCredit, String action) {
        Historique historique = new Historique();
        historique.setDemandeCredit(demandeCredit);
        historique.setAction(action);
        historique.setDateModification(new Date());
        em.persist(historique);
        em.flush();
        return historique;

    }
}

