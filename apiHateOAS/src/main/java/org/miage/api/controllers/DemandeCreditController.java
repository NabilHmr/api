package org.miage.api.controllers;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.miage.api.Constante.Constante;
import org.miage.api.dao.DemandeCreditDao;
import org.miage.api.models.DemandeCredit;
import org.miage.api.models.Historique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/credits")
public class DemandeCreditController {

    @Autowired
    @Lazy
    protected RestTemplate restTemplate;

    protected static final String BASEURL = "http://localhost:8081/finances/validate/";

    protected final DemandeCreditDao dcDao;


    @Autowired
    public DemandeCreditController(DemandeCreditDao dcDao) {
        this.dcDao = dcDao;
    }

    @PostMapping
    public ResponseEntity<DemandeCredit> createDemandeCredit(@RequestBody DemandeCredit demandeCredit) {
        DemandeCredit createdDemandeCredit = dcDao.saveDemandeCredit(demandeCredit);

        createdDemandeCredit.add(linkTo(methodOn(DemandeCreditController.class)
                .getDemandeCreditById(createdDemandeCredit.getId())).withSelfRel());
        createdDemandeCredit.add(linkTo(methodOn(DemandeCreditController.class)
                .getAllDemandesCredit()).withRel("collection"));

        return ResponseEntity.created(linkTo(methodOn(DemandeCreditController.class)
                .getDemandeCreditById(createdDemandeCredit.getId())).toUri()).body(createdDemandeCredit);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<DemandeCredit>> getAllDemandesCredit() {
        try {
            List<DemandeCredit> demandesCredits = dcDao.getAllDemandesCredit();
            if (demandesCredits.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            for (DemandeCredit demandeCredit : demandesCredits) {
                demandeCredit.add(linkTo(methodOn(DemandeCreditController.class)
                        .getDemandeCreditById(demandeCredit.getId())).withSelfRel());
            }

            CollectionModel<DemandeCredit> model =  CollectionModel.of(demandesCredits);
            model.add(linkTo(methodOn(DemandeCreditController.class)
                    .getAllDemandesCredit()).withSelfRel());

            return ResponseEntity.ok(model);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<DemandeCredit> getDemandeCreditById(@PathVariable Integer id) {
        try {
            DemandeCredit demandeCredit = dcDao.getDemandeCreditById(id);
            demandeCredit.add(linkTo(methodOn(DemandeCreditController.class)
                    .getDemandeCreditById(demandeCredit.getId())).withSelfRel());
            demandeCredit.add(linkTo(methodOn(DemandeCreditController.class)
                    .getAllDemandesCredit()).withRel("collection"));
            return ResponseEntity.ok(demandeCredit);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping()
    public ResponseEntity<DemandeCredit> updateDemandeCredit(@RequestBody DemandeCredit demandeCredit) {
        DemandeCredit updatedDemandeCredit = dcDao.updateDemandeCredit(demandeCredit);
        updatedDemandeCredit.add(linkTo(methodOn(DemandeCreditController.class)
                .getDemandeCreditById(updatedDemandeCredit.getId())).withSelfRel());
        updatedDemandeCredit.add(linkTo(methodOn(DemandeCreditController.class).getAllDemandesCredit()).withRel("collection"));

        return ResponseEntity.ok(updatedDemandeCredit);

    }

    @PutMapping("/{id}/validate")
    @CircuitBreaker(name = "validerDemandeCredit", fallbackMethod = "fallbackValiderDemandeCredit")
    public ResponseEntity<DemandeCredit> validerDemandeCredit(@PathVariable Integer id) {
        DemandeCredit dc = dcDao.getDemandeCreditById(id);

        if (dc == null) {
            return ResponseEntity.notFound().build();
        }

        List<Double> revenus = dc.getRevenusTroisDernieresAnnees();

        // Calcul du revenu moyen
        Double revenuMoyen = revenus.stream().mapToDouble(Double::doubleValue).sum() / revenus.size();


        String url = BASEURL + revenuMoyen;

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (response.getBody().equals("Demande de crédit refusée")) {
            dcDao.changeStatut(id, Constante.ETAT_REJET);

            return ResponseEntity.ok(dc);
        }else {
            dcDao.changeStatut(id, Constante.ETAT_ACCEPTATION);

            return ResponseEntity.ok(dc);
        }

    }

    // Méthode pour changer le statut d'une demande de crédit
    @PatchMapping("/{id}/change-statut")
    public ResponseEntity<DemandeCredit> changeStatut(@PathVariable Integer id, @RequestParam String statut) {
        DemandeCredit demandeCredit = dcDao.changeStatut(id, statut);
        demandeCredit.add(linkTo(methodOn(DemandeCreditController.class)
                .getDemandeCreditById(demandeCredit.getId())).withSelfRel());
        demandeCredit.add(linkTo(methodOn(DemandeCreditController.class)
                .getAllDemandesCredit()).withRel("collection"));
        return ResponseEntity.ok(demandeCredit);
    }

    // Méthode pour consulter l'historique d'une demande de crédit
    @GetMapping("/{id}/historique")
    public List<Historique> getHistoriqueDemandeCredit(@PathVariable Integer id) {
        return dcDao.getAllHistoriqueDemandeCredit(id);
    }

    // Méthode pour consulter la derniere action d'une demande de crédit
    @GetMapping("/{id}/last-action")
    public Historique getLastActionDemandeCredit(@PathVariable Integer id) {
        DemandeCredit demandeCredit = dcDao.getDemandeCreditById(id);
        return dcDao.getLastActionByDemandeCredit(demandeCredit);
    }

    public ResponseEntity<String> fallbackValiderDemandeCredit(Integer id, Throwable t) {

        // return le message d'erreur "Service non disponible"
        return ResponseEntity.status(503).body("Service non disponible");
    }

}
