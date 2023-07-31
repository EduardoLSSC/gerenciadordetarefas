package com.example.gerenciador.controllers;

import com.example.gerenciador.dtos.UsuarioRecordDto;
import com.example.gerenciador.models.Usuario;
import com.example.gerenciador.repositories.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping("/usuarios")
    public ResponseEntity<Usuario> saveUsuario(@RequestBody @Valid UsuarioRecordDto usuarioRecordDto) {
        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioRecordDto, usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(usuario));
    }
    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        List<Usuario> usuarioList = usuarioRepository.findAll();
        if(!usuarioList.isEmpty()) {
            for(Usuario usuario : usuarioList) {
                Long id = usuario.getId();
                usuario.add(linkTo(methodOn(UsuarioController.class).getOneUsuario(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarioList);
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Object> getOneUsuario(@PathVariable(value="id") Long id) {
        Optional<Usuario> usuarioSelecionado = usuarioRepository.findById(id);
        if(usuarioSelecionado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarioSelecionado.get());
    }

    @PutMapping("usuarios/{id}")
    public ResponseEntity<Object> updateUsuario(@PathVariable(value="id") Long id, @RequestBody @Valid UsuarioRecordDto usuarioRecordDto) {
        Optional<Usuario> usuarioAlterado = usuarioRepository.findById(id);
        if(usuarioAlterado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
        Usuario usuario = usuarioAlterado.get();
        BeanUtils.copyProperties(usuarioRecordDto, usuario);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.save(usuario));
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Object> deleteUsuario(@PathVariable(value="id") Long id) {
        Optional<Usuario> usuarioDeletado = usuarioRepository.findById(id);
        if(usuarioDeletado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
        usuarioRepository.delete(usuarioDeletado.get());
        return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado com sucesso");

    }
}
