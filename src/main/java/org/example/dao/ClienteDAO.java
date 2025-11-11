package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.example.model.Cliente;

import java.util.List;
import java.util.Optional;

public class ClienteDAO {
    public Cliente salvar (Cliente c){
        EntityManager em = JPAUtil.em();
        EntityTransaction tx= em.getTransaction();

        try{
            tx.begin();
            if (c.getId()== null)em.persist(c);
            else c = em.merge(c);
            tx.commit();
            return c;

        } finally {
            if (tx.isActive()) tx.rollback(); em.close();
        }
    }

    public List<Cliente> listar() {
        EntityManager em = JPAUtil.em();
        try { return em.createQuery("from Cliente", Cliente.class).getResultList(); }
        finally { em.close(); }
    }

    public Optional<Cliente> buscarPorId(Long id) {
        EntityManager em = JPAUtil.em();
        try { return Optional.ofNullable(em.find(Cliente.class, id)); }
        finally { em.close(); }
    }

    public Cliente atualizar(Cliente c) {
        return salvar(c);
    }

    public void remover(Long id) {
        EntityManager em = JPAUtil.em();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Cliente c = em.find(Cliente.class, id);
            if (c != null) em.remove(c);       // orphanRemoval remove os pedidos
            tx.commit();
        } finally { if (tx.isActive()) tx.rollback(); em.close(); }
    }

}
