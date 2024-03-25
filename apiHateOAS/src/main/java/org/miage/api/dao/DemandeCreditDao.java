package org.miage.api.dao;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.miage.api.Constante.Constante;
import org.miage.api.models.Client;
import org.miage.api.models.DemandeCredit;
import org.miage.api.models.Historique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DemandeCreditDao {

    protected final EntityManager em;

    @Autowired
    public DemandeCreditDao(EntityManager em) {
        this.em = em;
    }

    // Méthode pour enregistrer une nouvelle demande de crédit
    public DemandeCredit saveDemandeCredit(DemandeCredit demandeCredit) {
        demandeCredit.setStatut(Constante.ETAT_DEBUT);

        Integer clientId = demandeCredit.getClient().getId();

        ClientDao clientDao = new ClientDao(em);
        Client client = clientDao.getClientById(clientId);

        demandeCredit.setClient(client);

        em.persist(demandeCredit);

        HistoriqueDao historiqueDao = new HistoriqueDao(em);
        historiqueDao.saveAction(demandeCredit, Constante.ETAT_DEBUT);

        em.flush();
        return demandeCredit;
    }

    // Méthode pour récupérer toutes les demandes de crédit
    public List<DemandeCredit> getAllDemandesCredit() {

        return em.createQuery("SELECT dc FROM DemandeCredit dc", DemandeCredit.class).getResultList();
    }

    // Méthode pour récupérer une demande de crédit par son ID
    public DemandeCredit getDemandeCreditById(Integer id) {

        return em.find(DemandeCredit.class, id);
    }

    // Méthode pour mettre à jour une demande de crédit
    public DemandeCredit updateDemandeCredit(DemandeCredit demandeCredit) {

        demandeCredit.setUpdatedAt(new Date());
        return em.merge(demandeCredit);
    }

    // Méthode pour supprimer une demande de crédit par son ID
    public void deleteDemandeCredit(Integer id) {

        DemandeCredit demandeCredit = em.find(DemandeCredit.class, id);
        em.remove(demandeCredit);
    }

    public DemandeCredit changeStatut(Integer id, String statut) {
        DemandeCredit demandeCredit = em.find(DemandeCredit.class, id);
        demandeCredit.setUpdatedAt(new Date());
        demandeCredit.setStatut(statut);

        HistoriqueDao historiqueDao = new HistoriqueDao(em);
        historiqueDao.saveAction(demandeCredit, statut);

        return em.merge(demandeCredit);
    }

    public Historique getLastActionByDemandeCredit(DemandeCredit demandeCredit) {
        return em.createQuery("SELECT h FROM Historique h WHERE h.demandeCredit = :demandeCredit ORDER BY h.dateModification DESC", Historique.class)
                .setParameter("demandeCredit", demandeCredit)
                .setMaxResults(1)
                .getSingleResult();
    }

    public List<Historique> getAllHistoriqueDemandeCredit(Integer id) {
        DemandeCredit demandeCredit = em.find(DemandeCredit.class, id);

        return em.createQuery("SELECT h FROM Historique h WHERE h.demandeCredit = :demandeCredit", Historique.class)
                .setParameter("demandeCredit", demandeCredit)
                .getResultList();

    }
}

