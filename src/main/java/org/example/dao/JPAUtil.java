package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
    public static final EntityManagerFactory EMF =
            Persistence.createEntityManagerFactory("lab06PU");
    public static EntityManager em (){
        return EMF.createEntityManager();
    }


}
