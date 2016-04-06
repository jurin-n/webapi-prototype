package com.jurin_n.infrastructure.persistence.testing;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class DatabaseCleaner {
    private Class<?>[] entityTypes;
    private EntityManager em;

    public DatabaseCleaner(EntityManager em) {
        this.em = em;
    }

    public DatabaseCleaner setEntityTypes(Class<?>[] entityTypes) {
        this.entityTypes = entityTypes;
        return this;
    }

    public void clean() {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        for (Class<?> entityType : entityTypes) {
            deleteEntities(entityType);
        }
        transaction.commit();
    }

    private void deleteEntities(Class<?> entityType) {
        em.createQuery("delete from " + entityNameOf(entityType))
                .executeUpdate();
    }

    private String entityNameOf(Class<?> entityType) {
        return entityType.getSimpleName();
    }
}
