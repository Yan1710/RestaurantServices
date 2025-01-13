package com.example.Restaurante.controller;

import com.example.Restaurante.entity.Items;
import com.example.Restaurante.entity.Pedido;
import com.example.Restaurante.repository.RestauranteRepository;
import com.example.Restaurante.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/Restaurante")
public class RestauranteController {
    @Autowired
    private RestauranteService restauranteService;

    @GetMapping("/pedidos")
    public List<Pedido> obtenerPedidos(Long id) {
        return restauranteService.obtenerPedidos(id);
    }

    @GetMapping("/items")
    public List<Items> obtenerItems(Long id) {
        return restauranteService.obtenerItems(id);
    }

    @PostMapping("/saveitems")
    public String saveItems(Long id, Items items) {
        return restauranteService.saveItems(id, items);
    }

    @PostMapping("/savepedido")
    public String savePedido(Long id, Pedido pedido) {
        return restauranteService.savePedido(id, pedido);
    }
}
