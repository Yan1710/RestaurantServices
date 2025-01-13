package com.example.Restaurante.service;


import com.example.Restaurante.Utils.EstadoEntrega;
import com.example.Restaurante.Utils.RolEnum;
import com.example.Restaurante.entity.*;
import com.example.Restaurante.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ItemsRepository itemsRepository;
    @Autowired
    private RestauranteRepository restauranteService;
    @Autowired
    private DomicilioRepository domicilioRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public String crearPedido(Pedido pedido) {
        Domicilio domicilio = new Domicilio();
        domicilio.setUsuario(filterDomiciliario());
        domicilio.setEstadoEntrega(EstadoEntrega.PENDIENTE);
        domicilio = domicilioRepository.save(domicilio);

        pedido.setDomicilio(domicilio);

        double total = pedido.getItems().stream()
                .mapToDouble(PedidoProducto::calcularSubtotal)
                .sum();
        pedido.setTotal(total);
        pedido.setEstadoEntrega(EstadoEntrega.PENDIENTE);


        pedidoRepository.save(pedido);
        return "Se creo con exito el pedido";
    }


   /* public List<Items> crearItems(List<Items> items) {
        return itemsRepository.saveAll(items);
    }*/

     public List<Items> obtenerItems(Long id){
        return itemsRepository.findAll().stream().filter(items -> items.getRestaurante().getId().equals(id)).collect(Collectors.toList());
     }


    public List<Pedido> obtenerPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido obtenerPedidoPorId(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    public Double calcularTotalPorUsuario(String cliente) {
        List<Pedido> pedidos = pedidoRepository.findByCliente(cliente);
        return pedidos.stream()
                .mapToDouble(Pedido::getTotal)
                .sum();
    }

    public List<Restaurante> restauranteList(){
        return restauranteService.findAll();
    }

    public Usuario filterDomiciliario() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        List<Usuario> usuariosDO = usuarios.stream()
                .filter(usuario -> RolEnum.DOMICILIARIO.equals(usuario.getRol()))
                .collect(Collectors.toList());

        return usuariosDO.isEmpty() ? null : usuariosDO.get(new Random().nextInt(usuariosDO.size()));
    }
}