package hu.unideb.inf.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class JpaVeradoDAO implements VeradoDAO{

    final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
    final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public void saveVerado(Verado a) {
        entityManager.getTransaction().begin();
        entityManager.persist(a);
        entityManager.getTransaction().commit();

    }

    @Override
    public void deleteVerado(Verado a) {
        entityManager.getTransaction().begin();
        entityManager.remove(a);
        entityManager.getTransaction().commit();

    }

    @Override
    public void updateVerado(Verado a) {
        saveVerado(a);

    }

    @Override
    public List<Verado> getVeradok() {
        TypedQuery<Verado> query = entityManager.createQuery("SELECT a FROM Verado a", Verado.class);
        List<Verado> veradok = query.getResultList();
        return veradok;
    }

    @Override
    public void saveKorhaz(Korhaz korhaz) {
        entityManager.getTransaction().begin();
        entityManager.persist(korhaz);
        entityManager.getTransaction().commit();
    }


    @Override
    public void close() throws Exception {
        entityManager.close();
        entityManagerFactory.close();

    }
}
