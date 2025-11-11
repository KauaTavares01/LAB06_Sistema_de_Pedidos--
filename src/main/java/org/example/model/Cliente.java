package org.example.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Cliente {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;
    private String email;

    @OneToMany(mappedBy = "cliente",cascade = CascadeType.ALL,orphanRemoval = true)
   private List<Pedido> pedidos = new ArrayList<>();

    public Cliente(){}

    public Cliente(String nome,String email){
        this.nome=nome;
        this.email=email;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    //------------- So Para Separar msm _____________________

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }
    //----------------- Set --------------------

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addPedido(Pedido p){
        pedidos.add(p);
        p.setCliente(this);

    }

    public void removePedido(Pedido p){
        pedidos.remove(p);
        p.setCliente(null);

    }

    @Override
    public String toString() {
        return "Cliente{id=%d, nome='%s', email='%s'}".formatted(id, nome, email);
    }



}
