package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.example.model.Pedido;
import java.util.List;
import java.util.Optional;

public class PedidoDAO {

    public Pedido salvar(Pedido p) {
        EntityManager em = JPAUtil.em();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            if (p.getId() == null) em.persist(p);
            else p = em.merge(p);
            tx.commit();
            return p;
        } finally { if (tx.isActive()) tx.rollback(); em.close(); }
    }

    public List<Pedido> listar() {
        EntityManager em = JPAUtil.em();
        try { return em.createQuery("from Pedido", Pedido.class).getResultList(); }
        finally { em.close(); }
    }

    public Optional<Pedido> buscarPorId(Long id) {
        EntityManager em = JPAUtil.em();
        try { return Optional.ofNullable(em.find(Pedido.class, id)); }
        finally { em.close(); }
    }

    public Pedido atualizar(Pedido p) { return salvar(p); }

    public void remover(Long id) {
        EntityManager em = JPAUtil.em();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Pedido p = em.find(Pedido.class, id);
            if (p != null) em.remove(p);
            tx.commit();
        } finally { if (tx.isActive()) tx.rollback(); em.close(); }
    }
}
