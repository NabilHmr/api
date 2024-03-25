package org.miage.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/finances")
public class GouvController {

    @GetMapping("/validate/{amount}")
    public ResponseEntity<String> validateAmount(@PathVariable("amount") Double amount) {

        if (amount < 40000) {
            return ResponseEntity.ok("Demande de crédit refusée");
        } else {
            return ResponseEntity.ok("Demande de crédit acceptée");
        }
    }
}
