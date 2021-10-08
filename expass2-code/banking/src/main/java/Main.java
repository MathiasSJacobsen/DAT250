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

        em.getTransaction().begin();
        Bank bank = new Bank();
        bank.setName("Pengebank");
        em.persist(bank);

        Pincode pincode = new Pincode();
        pincode.setPincode(123);
        pincode.setCount(1);

        CreditCard creditCard1 = new CreditCard();
        creditCard1.setNumber(12345L);
        creditCard1.setBalance((float) -5000.0);
        creditCard1.setLimit((float) -10000.0);
        creditCard1.setBank(bank);
        creditCard1.setPincode(pincode);
        // creditCard.setOwner();
        em.persist(creditCard1);

        CreditCard creditCard2 = new CreditCard();
        creditCard2.setBank(bank);
        creditCard2.setPincode(pincode);
        creditCard2.setNumber(123L);
        creditCard2.setBalance((float) 1.0);
        creditCard2.setLimit(2000);
        em.persist(creditCard2);


        Address address = new Address();
        address.setNumber(28);
        address.setStreet("Inndalsveien");
        em.persist(address);

        Person person = new Person();
        person.setName("Max Mustermann");
        person.setAddress(new ArrayList<>(Collections.singleton(address)));
        person.setCreditCardList(new ArrayList<>(Collections.singleton(creditCard1)));
        person.getCreditCardList().add(creditCard2);
        em.persist(person);

        creditCard1.setOwner(person);
        em.merge(creditCard1);
        creditCard2.setOwner(person);
        bank.setCreditCardList(new ArrayList<>(Collections.singleton(creditCard1)));
        bank.getCreditCardList().add(creditCard2);
        em.merge(bank);
        address.setPerson(new ArrayList<>(Collections.singleton(person)));
        em.merge(address);

        em.getTransaction().commit();

        em.close();


    }
}
