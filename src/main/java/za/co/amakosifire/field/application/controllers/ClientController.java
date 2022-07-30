package za.co.amakosifire.field.application.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import za.co.amakosifire.field.domain.clients.ClientService;
import za.co.amakosifire.field.domain.clients.model.Client;
import za.co.amakosifire.field.domain.clients.model.Site;


import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/v1/client-site")
@AllArgsConstructor
public class ClientController {

    private ClientService clientService;

    @GetMapping
    public ResponseEntity<?> clients() {
        return ResponseEntity.ok().body(clientService.getClients());
    }

    @GetMapping("/site-for-client/{id}")
    public ResponseEntity<?> sitesForClientById(@PathVariable String id) {
        return ResponseEntity.ok().body(clientService.getSitesForClient(id));
    }

    @PostMapping
    public ResponseEntity<?> post(@Validated @RequestBody Client client) throws URISyntaxException {
        var result = clientService.saveClient(client);
        return ResponseEntity.created(new URI("/api/v1/client/" + result.getId())).body(result);
    }

    @PostMapping("/site")
    public ResponseEntity<?> addSite(@Validated @RequestBody Site site) throws URISyntaxException {
        var result = clientService.saveSite(site);
        return ResponseEntity.created(new URI("/api/v1/client/" + result.getId())).body(result);
    }
}
