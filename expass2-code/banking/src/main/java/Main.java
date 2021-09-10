import entity.*;

import javax.persistence.*;
import java.util.*;

public class Main {
    private static final String PERSISTENCE_UNIT_NAME = "credit";

    private static EntityManagerFactory factory;

    public static void main(String[] args) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();

        Query q = em.createQuery("select t from Person t");
        List<Person> personList = q.getResultList();
        personList.forEach(System.out::println);

    }
}
