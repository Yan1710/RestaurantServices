package com.example.Restaurante.controller;

import com.example.Restaurante.entity.Domicilio;
import com.example.Restaurante.entity.Pedido;
import com.example.Restaurante.service.DomicilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/Domicilio")
public class DomicilioController {

    @Autowired
    private DomicilioService domicilioService;

    @PostMapping
    public String crearDomicilio(@RequestBody Pedido pedido) {
        return domicilioService.crearDomicilio(pedido);
    }

    @PostMapping("/list")
    public List<Domicilio>getListDomicilio(@RequestBody String email){
        return domicilioService.getListDomicilio(email);
    }

    @PostMapping("/encamino")
    public String Encamino(@RequestBody Long id){
        return domicilioService.EnCamino(id);
    }

    @PostMapping("/entregado")
    public String Entregado(@RequestBody Long id){
        return domicilioService.Entregado(id);
    }
}
