package org.miage.api.models;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;
import java.util.List;
@Entity
public class DemandeCredit extends RepresentationModel<DemandeCredit> {

    @Id
    @GeneratedValue
    protected Integer id;
    protected String nom;
    protected String prenom;
    protected String adresse;
    protected Date dateNaissance;
    protected String emploiActuel;
    @ElementCollection
    protected List<Double> revenusTroisDernieresAnnees;
    protected double montantCreditDemande;
    protected int dureeCreditDemande;

    protected String statut;

    @ManyToOne
    @JoinColumn(name = "client_id")
    protected Client client;
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    protected Date updatedAt;



    public DemandeCredit() {
    }

    public DemandeCredit(String nom, String prenom, String adresse, Date dateNaissance, String emploiActuel,
                         List<Double> revenusTroisDernieresAnnees, double montantCreditDemande, int dureeCreditDemande, Client client, String statut) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.dateNaissance = dateNaissance;
        this.emploiActuel = emploiActuel;
        this.revenusTroisDernieresAnnees = revenusTroisDernieresAnnees;
        this.montantCreditDemande = montantCreditDemande;
        this.dureeCreditDemande = dureeCreditDemande;
        this.client = client;
        this.statut = statut;
        this.createdAt = new Date();
        this.updatedAt = new Date();

    }

    // Getters et setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getEmploiActuel() {
        return emploiActuel;
    }

    public void setEmploiActuel(String emploiActuel) {
        this.emploiActuel = emploiActuel;
    }

    public List<Double> getRevenusTroisDernieresAnnees() {
        return revenusTroisDernieresAnnees;
    }

    public void setRevenusTroisDernieresAnnees(List<Double> revenusTroisDernieresAnnees) {
        this.revenusTroisDernieresAnnees = revenusTroisDernieresAnnees;
    }

    public double getMontantCreditDemande() {
        return montantCreditDemande;
    }

    public void setMontantCreditDemande(double montantCreditDemande) {
        this.montantCreditDemande = montantCreditDemande;
    }

    public int getDureeCreditDemande() {
        return dureeCreditDemande;
    }

    public void setDureeCreditDemande(int dureeCreditDemande) {
        this.dureeCreditDemande = dureeCreditDemande;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}

