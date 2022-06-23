package com.prueba.microservicios.app.camisas.services.impl;

import com.prueba.microservicios.app.camisas.exception.CamisaException;
import com.prueba.microservicios.app.camisas.exception.repository.CamisaRepository;
import com.prueba.microservicios.app.camisas.models.Camisa;
import com.prueba.microservicios.app.camisas.services.CamisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerErrorException;

import java.util.List;
import java.util.Optional;

@Service
public class CamisaServiceImpl implements CamisaService {

    @Autowired
    private CamisaRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    public void getUserByid(Long id){
        ResponseEntity<String> result=null;
        try {
             result = restTemplate.getForEntity("http://localhost:8080/api/usuarios/"+id, String.class);
            //System.out.println(result.getStatusCodeValue());
        }catch (HttpClientErrorException e){
            throw new CamisaException(e.getStatusCode(),"No se encontro al usuario");
        }catch (HttpServerErrorException e){
            throw new CamisaException(HttpStatus.BAD_GATEWAY,"Ha ocurrido un error interno, buscando el usuario");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Camisa> findAll() {
        try {
            return repository.findAll();
        }catch (ServerErrorException e){
            throw new CamisaException(HttpStatus.INTERNAL_SERVER_ERROR,"Ha ocurrido un error interno");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Camisa findById(Long id) {
        try{
            return repository.findById(id).orElseThrow(()->new CamisaException(HttpStatus.NOT_FOUND,"No se encontro la camisa"));
        }catch (ServerErrorException e){
            throw new CamisaException(HttpStatus.INTERNAL_SERVER_ERROR,"Ha ocurrido un error interno");
        }
    }

    @Override
    @Transactional
    public Camisa save(Camisa camisa) {
        this.getUserByid(camisa.getIdUsuario());
        try {
            return repository.save(camisa);
        }catch (ServerErrorException e){
            throw new CamisaException(HttpStatus.INTERNAL_SERVER_ERROR,"Ha ocurrido un error interno");
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        findById(id);
        try {
            repository.deleteById(id);
        }catch (ServerErrorException e){
            throw new CamisaException(HttpStatus.INTERNAL_SERVER_ERROR,"Ha ocurrido un error interno");
        }
    }

    @Override
    @Transactional
    public Camisa update(Long id, Camisa camisa) {
        this.getUserByid(camisa.getIdUsuario());
        try {
            Camisa camisaFind=findById(id);
            camisaFind.setColor(camisa.getColor());
            camisaFind.setTamanio(camisa.getTamanio());
            camisaFind.setTipo(camisa.getTipo());
            camisaFind.setIdUsuario(camisa.getIdUsuario());
            return repository.save(camisaFind);
        }catch (ServerErrorException e){
            throw new CamisaException(HttpStatus.INTERNAL_SERVER_ERROR,"Ha ocurrido un error interno");
        }
    }

    public List<Camisa> getCamisasByUsuario(Long id){
        getUserByid(id);
        try {
            List<Camisa> camisas =repository.findByIdUsuario(id);
            if (camisas.isEmpty()){
               throw  new CamisaException(HttpStatus.NO_CONTENT,"No hay camisas asociadas al usuario");
            }
            return camisas;
            //return repository.findByIdUsuario(id).orElseThrow(()->new CamisaException(HttpStatus.NO_CONTENT,"No hay camisas asociadas al usuario"));
        }catch (ServerErrorException e){
            throw new CamisaException(HttpStatus.INTERNAL_SERVER_ERROR,"Ha ocurrido un error interno");
        }
    }
}
