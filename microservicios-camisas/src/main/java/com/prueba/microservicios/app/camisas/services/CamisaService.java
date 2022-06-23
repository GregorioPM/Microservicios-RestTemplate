package com.prueba.microservicios.app.camisas.services;

import com.prueba.microservicios.app.camisas.models.Camisa;

import java.util.List;

public interface CamisaService {

    Iterable<Camisa> findAll();
    Camisa findById(Long id);
    Camisa save(Camisa camisa);
    void deleteById(Long id);
    Camisa update(Long id,Camisa camisa);
    List<Camisa> getCamisasByUsuario(Long id);

}
