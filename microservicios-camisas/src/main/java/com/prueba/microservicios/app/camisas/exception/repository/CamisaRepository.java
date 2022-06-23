package com.prueba.microservicios.app.camisas.exception.repository;

import com.prueba.microservicios.app.camisas.models.Camisa;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CamisaRepository extends CrudRepository<Camisa,Long> {

    List<Camisa> findByIdUsuario(Long idUsuario);
}
