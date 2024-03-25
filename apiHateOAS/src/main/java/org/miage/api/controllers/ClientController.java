package org.miage.api.controllers;

import org.miage.api.dao.ClientDao;
import org.miage.api.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/clients")
public class ClientController {

    protected final ClientDao dcDao;


    @Autowired
    public ClientController(ClientDao dcDao) {
        this.dcDao = dcDao;
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        Client createdClient = dcDao.saveClient(client);

        createdClient.add(linkTo(methodOn(ClientController.class)
                .getClientById(createdClient.getId())).withSelfRel());
        createdClient.add(linkTo(methodOn(ClientController.class)
                .getAllClients()).withRel("collection"));

        return ResponseEntity.created(linkTo(methodOn(ClientController.class)
                .getClientById(createdClient.getId())).toUri()).body(createdClient);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<Client>> getAllClients() {
        try {
            List<Client> clients = dcDao.getAllClients();
            if (clients.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            for (Client client : clients) {
                client.add(linkTo(methodOn(ClientController.class)
                        .getClientById(client.getId())).withSelfRel());
            }

            CollectionModel<Client> collectionModel = CollectionModel.of(clients);
            collectionModel.add(linkTo(methodOn(ClientController.class).getAllClients()).withSelfRel());

            return ResponseEntity.ok(collectionModel);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable("id") Integer id) {
        try {
            Client client = dcDao.getClientById(id);
            client.add(linkTo(methodOn(ClientController.class)
                    .getClientById(client.getId())).withSelfRel());
            return ResponseEntity.ok(client);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
