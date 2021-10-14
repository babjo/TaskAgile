package com.taskagile.infrastructure.repository;

import javax.persistence.EntityManager;

import org.hibernate.Session;

public class HibernateSupport<T> {

    EntityManager entityManager;

    HibernateSupport(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    public void save(T object) {
        entityManager.persist(object);
        entityManager.flush();
    }
}
