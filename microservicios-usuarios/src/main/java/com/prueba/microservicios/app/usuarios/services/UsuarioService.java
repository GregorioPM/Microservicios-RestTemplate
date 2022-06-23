package com.prueba.microservicios.app.usuarios.services;

import com.prueba.microservicios.app.usuarios.models.entity.Usuario;
import com.prueba.microservicios.app.usuarios.models.resttemplate.Camisa;

import java.util.List;

public interface UsuarioService {

    Iterable<Usuario> findAll();
    Usuario findById(Long id);
    Usuario save(Usuario usuario);
    void deleteById(Long id);
    Usuario update(Long id, Usuario usuario);
    List<Camisa> getCamisas(Long id);


}
