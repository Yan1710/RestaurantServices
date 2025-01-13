package com.example.Restaurante.entity;

import com.example.Restaurante.Utils.EstadoEntrega;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Domicilio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "domicilio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido> pedido = new ArrayList<>();

    private EstadoEntrega estadoEntrega;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void addPedido(Pedido pedido) {
        this.pedido.add(pedido);
        pedido.setDomicilio(this);
    }

    public void removePedido(Pedido pedido) {
        this.pedido.remove(pedido);
        pedido.setDomicilio(null);
    }

    public EstadoEntrega getEstadoEntrega() {
        return estadoEntrega;
    }

    public void setEstadoEntrega(EstadoEntrega estadoEntrega) {
        this.estadoEntrega = estadoEntrega;
    }
}
