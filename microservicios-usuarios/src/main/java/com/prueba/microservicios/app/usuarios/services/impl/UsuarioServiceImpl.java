package com.prueba.microservicios.app.usuarios.services.impl;

import com.prueba.microservicios.app.usuarios.exception.UsuarioException;
import com.prueba.microservicios.app.usuarios.models.entity.Usuario;
import com.prueba.microservicios.app.usuarios.models.repository.UsuarioRepository;
import com.prueba.microservicios.app.usuarios.models.resttemplate.Camisa;
import com.prueba.microservicios.app.usuarios.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerErrorException;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    public List<Camisa> getCamisas(Long id) {
        List<Camisa> camisas=null;
        try {
             return camisas = restTemplate.getForObject("http://localhost:8081/api/camisas/usuario/" + id, List.class);
        } catch (HttpServerErrorException e) {
            throw new UsuarioException(HttpStatus.BAD_GATEWAY, "Ha ocurrido un error interno buscando las camisas asociadas al usuario");

        }
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Usuario> findAll() {
        return repository.findAll();
    }

    @Override
    
    public Usuario findById(Long id) {
        System.out.println(id);
        try {
            return repository.findById(id).orElseThrow(() -> new UsuarioException(HttpStatus.NOT_FOUND, "No se encontro el usuario"));
        }catch (ServerErrorException e){
            throw new UsuarioException(HttpStatus.INTERNAL_SERVER_ERROR,"Ha ocurrido un error interno");
        }
    }

    @Override
    @Transactional
    public Usuario save(Usuario usuario) {
        try {
            return repository.save(usuario);
        }catch (ServerErrorException e){
            throw new UsuarioException(HttpStatus.INTERNAL_SERVER_ERROR,"Ha ocurrido un error interno");
        }

    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        findById(id);
        try {
            repository.deleteById(id);
        }catch (ServerErrorException e){
            throw new UsuarioException(HttpStatus.INTERNAL_SERVER_ERROR,"Ha ocurrido un error interno");
        }

    }

    @Override
    @Transactional
    public Usuario update(Long id, Usuario usuario) {
        Usuario usuarioFind=findById(id);
        try {
            usuarioFind.setNombre(usuario.getNombre());
            usuarioFind.setApellido(usuario.getApellido());
            usuarioFind.setEmail(usuario.getEmail());
            return repository.save(usuarioFind);
        }catch (ServerErrorException e){
            throw new UsuarioException(HttpStatus.INTERNAL_SERVER_ERROR,"Ha ocurrido un error interno");
        }

    }
}
