package ro.org.events.Repository;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static javax.persistence.Persistence.createEntityManagerFactory;

public class BaseRepository {

    private static EntityManagerFactory entityManagerFactory = null;
    private static EntityManager entityManager = null;

    private static EntityManagerFactory getEntityManagerFactory()
    {
        if(entityManagerFactory == null)
        {
            entityManagerFactory = createEntityManagerFactory("persistenceEventsUnit");
        }

        return entityManagerFactory;
    }
    public static EntityManager getEntityManager()
    {
        if(entityManager == null)
        {
            entityManager = getEntityManagerFactory().createEntityManager();
        }

        return entityManager;
    }
}
