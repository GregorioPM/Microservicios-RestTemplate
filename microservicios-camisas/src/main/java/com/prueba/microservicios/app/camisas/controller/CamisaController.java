package com.prueba.microservicios.app.camisas.controller;

import com.prueba.microservicios.app.camisas.models.Camisa;
import com.prueba.microservicios.app.camisas.services.CamisaService;
import com.prueba.microservicios.app.camisas.services.impl.CamisaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/camisas")
public class CamisaController {

    @Autowired
    private CamisaService camisaService;

    @GetMapping({"/",""})
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok().body(camisaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(camisaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Camisa camisa){
        return ResponseEntity.status(HttpStatus.CREATED).body(camisaService.save(camisa));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id,@RequestBody Camisa camisa){
        return ResponseEntity.status(HttpStatus.CREATED).body(camisaService.update(id,camisa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        camisaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<?> getCamisasByUsuario(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(camisaService.getCamisasByUsuario(id));
    }

}
