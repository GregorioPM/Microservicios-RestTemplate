package com.prueba.microservicios.app.usuarios.controller;

import com.prueba.microservicios.app.usuarios.models.entity.Usuario;
import com.prueba.microservicios.app.usuarios.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;



    @GetMapping({"/",""})
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Usuario usuario){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Usuario usuario){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.update(id,usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/camisas/{id}")
    public ResponseEntity<?> getCamisasByIdUsuario (@PathVariable("id") Long id){
        return ResponseEntity.ok().body(service.getCamisas(id));
    }
}
