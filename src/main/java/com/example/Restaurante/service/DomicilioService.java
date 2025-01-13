package com.example.Restaurante.service;

import com.example.Restaurante.Utils.EstadoEntrega;
import com.example.Restaurante.Utils.RolEnum;
import com.example.Restaurante.entity.Domicilio;
import com.example.Restaurante.entity.Pedido;
import com.example.Restaurante.entity.PedidoProducto;
import com.example.Restaurante.entity.Usuario;
import com.example.Restaurante.repository.DomicilioRepository;
import com.example.Restaurante.repository.PedidoRepository;
import com.example.Restaurante.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


@Service
public class DomicilioService {
    @Autowired
    DomicilioRepository domicilioRepository;
    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    public String crearDomicilio(Pedido pedido) {
        Domicilio domicilio = new Domicilio();
        domicilio.setUsuario(filterDomiciliario());
        domicilio.setEstadoEntrega(EstadoEntrega.PENDIENTE);
        domicilio = domicilioRepository.save(domicilio);

        double total = pedido.getItems().stream()
                .mapToDouble(PedidoProducto::calcularSubtotal)
                .sum();

        pedido.setTotal(total);
        pedido.setDomicilio(domicilio);
        pedidoRepository.save(pedido);
        return "Se creo con exito el pedido";
    }

    public List<Domicilio> getListDomicilio(String email){
        return domicilioRepository.findAll()
                .stream()
                .filter(domicilio -> domicilio.getUsuario().getEmail().equals(email))
                .collect(Collectors.toList());
    }
    public String EnCamino(Long id){
        domicilioRepository.findById(id).get().setEstadoEntrega(EstadoEntrega.EN_CAMINO);
        return "Cambiaste el estado a en camino";
    }
    public String Entregado(Long id){
        domicilioRepository.findById(id).get().setEstadoEntrega(EstadoEntrega.ENTREGADO);
        return "El domicilio ha sido entregado.";
    }

    public Usuario filterDomiciliario() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        List<Usuario> usuariosDO = usuarios.stream()
                .filter(usuario -> RolEnum.DOMICILIARIO.equals(usuario.getRol()))
                .collect(Collectors.toList());

        return usuariosDO.isEmpty() ? null : usuariosDO.get(new Random().nextInt(usuariosDO.size()));
    }
}