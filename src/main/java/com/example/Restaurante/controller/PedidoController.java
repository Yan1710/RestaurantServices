package com.example.Restaurante.controller;


import com.example.Restaurante.entity.Items;
import com.example.Restaurante.entity.Pedido;
import com.example.Restaurante.entity.Restaurante;
import com.example.Restaurante.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public String crearPedido(@RequestBody Pedido pedido) {

        return pedidoService.crearPedido(pedido);
    }

    @GetMapping
    public List<Pedido> obtenerPedidos() {
        return pedidoService.obtenerPedidos();
    }
    @GetMapping("/items")
    public List<Items> obtenerItems(Long id){
        return pedidoService.obtenerItems(id);
    }

   /* @PostMapping("/items")
    public String saveItems(@RequestBody List<Items> items) {
        if (items == null || items.isEmpty()) {
            return "La lista de items está vacía o es nula.";
        }

        List<Items> validItems = items.stream()
                .filter(item -> item != null && item.getNombreItem() != null && item.getCantidad() != null && item.getImagenBase64() != null && item.getPrecioUnitario() != null)
                .collect(Collectors.toList());

        int invalidCount = items.size() - validItems.size();

        if (validItems.isEmpty()) {
            return "No se pudo guardar ningún item, todos los elementos eran inválidos.";
        }

        List<Items> savedItems = pedidoService.crearItems(validItems);

        return "Se guardaron " + savedItems.size() + " items con éxito. "
                + (invalidCount > 0 ? "Se ignoraron " + invalidCount + " items inválidos." : "");
    }
*/
    @GetMapping("/{id}")
    public Pedido obtenerPedidoPorId(@PathVariable Long id) {
        return pedidoService.obtenerPedidoPorId(id);
    }

    // Nuevo endpoint: Obtener el total de todos los pedidos de un usuario
    @GetMapping("/total/{cliente}")
    public Double calcularTotalPorUsuario(@PathVariable String cliente) {
        return pedidoService.calcularTotalPorUsuario(cliente);
    }
    @GetMapping("/restaurantes")
    public List<Restaurante>restauranteList(){
        return pedidoService.restauranteList();
    }
}