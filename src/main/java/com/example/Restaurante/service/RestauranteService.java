package com.example.Restaurante.service;

import com.example.Restaurante.Utils.EstadoEntrega;
import com.example.Restaurante.Utils.RolEnum;
import com.example.Restaurante.entity.*;
import com.example.Restaurante.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class RestauranteService {
    @Autowired
    DomicilioRepository domicilioRepository;
    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    ItemsRepository itemsRepository;
    @Autowired
    RestauranteRepository restauranteRepository;

    public List<Pedido> obtenerPedidos(Long id) {
        return pedidoRepository.findAll()
                .stream()
                .filter(pedido -> pedido.getRestaurante().getId().equals(id))
                .collect(Collectors.toList());
    }

    public List<Items> obtenerItems(Long id){
        return itemsRepository.findAll()
                .stream()
                .filter(items -> items.getRestaurante().getId().equals(id))
                .collect(Collectors.toList());
    }

    public String saveItems(Long id, Items items){

        Optional<Restaurante> optionalRestaurante = restauranteRepository.findById(id);

        Items newItem = new Items();
        newItem.setNombreItem(items.getNombreItem());
        newItem.setCantidad(items.getCantidad());
        newItem.setPrecioUnitario(items.getPrecioUnitario());
        newItem.setImagenBase64(items.getImagenBase64());
        newItem.setRestaurante(optionalRestaurante.get());

        itemsRepository.save(newItem);

        return "Los ítems del restaurante se guardaron con éxito.";
    }

    public String savePedido(Long id, Pedido pedido){
        Domicilio domicilio = new Domicilio();
        domicilio.setUsuario(filterDomiciliario());
        domicilio.setEstadoEntrega(EstadoEntrega.PENDIENTE);
        domicilio = domicilioRepository.save(domicilio);

        pedido.setDomicilio(domicilio);

        Optional<Restaurante>optionalRestaurante = restauranteRepository.findById(id);

        if (!optionalRestaurante.isPresent()) {
            return "Error: El restaurante con el ID " + id + " no existe.";
        }
        pedido.setRestaurante(optionalRestaurante.get());
        pedidoRepository.save(pedido);
        return "Se guardo el pedido con exito.";
    }

    public Usuario filterDomiciliario() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        List<Usuario> usuariosDO = usuarios.stream()
                .filter(usuario -> RolEnum.DOMICILIARIO.equals(usuario.getRol()))
                .collect(Collectors.toList());

        return usuariosDO.isEmpty() ? null : usuariosDO.get(new Random().nextInt(usuariosDO.size()));
    }


}
