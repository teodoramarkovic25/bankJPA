package entity;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class ActiveBankAccount {

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("bankPU");

    private void executeInsideTransaction(Consumer<EntityManager> entityManagerConsumer) {
        try {
            EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
            entityManager.getTransaction().begin();
            entityManagerConsumer.accept(entityManager);

            entityManager.getTransaction().commit();

        } catch (HibernateException e) {
            System.err.println(e.getMessage());

        }

    }

    public void save() {
        executeInsideTransaction(entityManager -> entityManager.persist(this));
      /*  try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(this);
            session.getTransaction().commit();

        } catch (HibernateException e) {
            System.err.println(e.getMessage());

        }

       */

    }


    public void update() {

        executeInsideTransaction(entityManager -> entityManager.merge(this));
       /* try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(this);
            session.getTransaction().commit();

        } catch (HibernateException e) {
            System.err.println(e.getMessage());

        }

        */

    }

    public void delete() {
        executeInsideTransaction(entityManager -> entityManager.remove(this));
    }

    public BankAccount get() {
        try {
            EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
            BankAccount bankAccount = entityManager.find(BankAccount.class, this);
            return bankAccount;


        } catch (HibernateException e) {
            System.err.println(e.getMessage());

        }
        return null;
    }

    public BankAccount getThis() {
        return (BankAccount) this;
    }

    public static List<BankAccount> loadAll() {
        try {

            EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
            entityManager.getTransaction().begin();

            Query query = entityManager.createNamedQuery("BankAccount.findAll");
            entityManager.getTransaction().commit();
           return query.getResultList();





        } catch (HibernateException e) {
            System.err.println(e.getMessage());

        }
        return Collections.emptyList();

    }


    public static boolean transferMoney(BankAccount fromBankAccount, BankAccount toBankAccount, Double amount) {

        if (fromBankAccount == null || fromBankAccount.getAccountNumber() == null) {
            return false;
        }
        if (toBankAccount == null || toBankAccount.getAccountNumber() == null) {
            return false;
        }

        if (amount <= 0) {
            return false;
        }
        if (fromBankAccount.getAmount() < amount) {
            System.err.println("nema para na računu");
            return false;
        }
        if (fromBankAccount.getAccountNumber().equals(toBankAccount.getAccountNumber())) {
            return false;
        }
        try {

            EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
            entityManager.getTransaction().begin();
            fromBankAccount.setAmount(fromBankAccount.getAmount()-amount);
            toBankAccount.setAmount(toBankAccount.getAmount()+amount);
            entityManager.merge(fromBankAccount);
            entityManager.merge(toBankAccount);
            entityManager.getTransaction().commit();


            return true;


        } catch (HibernateException e) {
            System.err.println(e.getMessage());

        }
        return false;
    }


}
