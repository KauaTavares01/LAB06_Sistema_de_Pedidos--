package org.example.view;

import org.example.dao.ClienteDAO;
import org.example.dao.PedidoDAO;
import org.example.model.Cliente;
import org.example.model.Pedido;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        var clienteDAO = new ClienteDAO();
        var pedidoDAO  = new PedidoDAO();

        // 1) Criar um cliente com 2 pedidos
        var c = new Cliente("Kauã", "kaua@exemplo.com");
        var p1 = new Pedido("Teclado mecânico", LocalDate.now());
        var p2 = new Pedido("Mouse gamer", LocalDate.now());
        c.addPedido(p1);
        c.addPedido(p2);
        c = clienteDAO.salvar(c); // cascade salva p1 e p2
        var id = c.getId();

        // 2) Listar todos os clientes com seus pedidos (usa JOIN FETCH)
        System.out.println("\n# LISTAR CLIENTES");
        clienteDAO.listarComPedidos().forEach(cli -> {
            System.out.println(cli);
            cli.getPedidos().forEach(System.out::println);
        });

        // 3) Buscar um cliente por ID e exibir pedidos (usa JOIN FETCH)
        System.out.println("\n# BUSCAR POR ID = " + id);
        clienteDAO.buscarPorIdComPedidos(id).ifPresent(cli -> {
            System.out.println(cli);
            cli.getPedidos().forEach(System.out::println);
        });

        // 4) Atualizar cliente e um pedido
        System.out.println("\n# ATUALIZAR");
        c.setNome("Kauã Silva");
        c.setEmail("kaua.silva@exemplo.com");
        var primeiroPedido = c.getPedidos().get(0);
        primeiroPedido.setDescricao("Teclado mecânico (ABNT2)");
        clienteDAO.atualizar(c);   // merge no cliente; cascade atualiza pedidos

        // 5) Listar novamente para ver atualização (com JOIN FETCH)
        System.out.println("\n# LISTAR APÓS ATUALIZAR");
        clienteDAO.listarComPedidos().forEach(cli -> {
            System.out.println(cli);
            cli.getPedidos().forEach(System.out::println);
        });

        // 6) Remover cliente
        System.out.println("\n# REMOVER CLIENTE " + id);
        clienteDAO.remover(id);

        // 7) Contagem final
        System.out.println("\n# CONTAGEM FINAL");
        System.out.println("Clientes: " + clienteDAO.listar().size());
        System.out.println("Pedidos : " + pedidoDAO.listar().size()); // deve ser 0 por orphanRemoval
    }
}
