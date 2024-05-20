package br.com.abc.core.filtersAndMidwares;

import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Component
public class JpaFilterConfigurer {

    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    public void init() {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedFilter");
        filter.setParameter("isDeleted", false);
    }
}
