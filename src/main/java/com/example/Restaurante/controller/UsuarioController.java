package com.example.Restaurante.controller;

import com.example.Restaurante.dto.LoginRequest;
import com.example.Restaurante.entity.Usuario;
import com.example.Restaurante.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registro")
    public String crearUsuario(@RequestBody Usuario usuario) {
        
        return usuarioService.crearUsuario(usuario.getNombre(), usuario.getEmail(), usuario.getPassword());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            String token = usuarioService.iniciarSesion( loginRequest.getEmail(), loginRequest.getPassword());
            return ResponseEntity.ok().body(token);
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }



}
