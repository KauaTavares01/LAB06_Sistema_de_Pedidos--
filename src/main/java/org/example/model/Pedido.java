package org.example.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Pedido {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Pedido() {}
    public Pedido(String descricao, LocalDate data) {
        this.descricao = descricao; this.data = data;
    }

    // getters/setters
    public Long getId() { return id; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }
    public Cliente getCliente() { return cliente; }

    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    @Override
    public String toString() {
        return "Pedido{id=%d, desc='%s', data=%s, clienteId=%s}".formatted(id, descricao, data,
                cliente==null?null:cliente.getId());
    }
}

