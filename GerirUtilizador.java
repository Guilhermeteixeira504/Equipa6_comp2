package upt.lp.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class GerirUtilizador {

    private SessionFactory sessionFactory;

   
    public void setup() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception ex) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
    public void exit() {
        if (sessionFactory != null) {
        	sessionFactory.close();
        }
    }
    
    public long create(String nome, String email, String password, String morada, int telemovel, String tipoUtilizador) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Utilizador u = new Utilizador();
            u.setNome(nome);
            u.setEmail(email);
            u.setPassword(password);
            u.setMorada(morada);
            u.setTelemovel(telemovel);
            u.setTipoUtilizador(tipoUtilizador);
            session.persist(u);
            session.getTransaction().commit();
            return u.getId();
        }
    }

    
    public long createIfNotExists(String nome, String email, String password, String morada, int telemovel,String tipoUtilizador) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Long count = session.createQuery(
                    "select count(u) from Utilizador u where u.email = :email", Long.class)
                    .setParameter("email", email)
                    .uniqueResult();

            if (count != null && count > 0) {
                
                session.getTransaction().commit(); 
                return -1;
            }

            Utilizador u = new Utilizador();
            u.setNome(nome);
            u.setEmail(email);
            u.setPassword(password);
            u.setMorada(morada);
            u.setTelemovel(telemovel);
            u.setTipoUtilizador(tipoUtilizador);
            session.persist(u);
            session.getTransaction().commit();
            return u.getId();
        }
    }

    
    public Utilizador read(long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Utilizador u = session.get(Utilizador.class, id);
            session.getTransaction().commit();
            return u;
        }
    }

    
    public Utilizador readByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Utilizador u = session.createQuery(
                    "from Utilizador u where u.email = :email", Utilizador.class)
                    .setParameter("email", email)
                    .uniqueResult();
            session.getTransaction().commit();
            return u;
        }
    }

    
    public void update(long id, String novoNome, String novaMorada, int novoTelemovel) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Utilizador u = session.get(Utilizador.class, id);
            if (u != null) {
                u.setNome(novoNome);
                u.setMorada(novaMorada);
                u.setTelemovel(novoTelemovel);
            }
            session.getTransaction().commit();
        }
    }

   
    public void delete(long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Utilizador u = session.get(Utilizador.class, id);
            if (u != null) {
            	session.remove(u);
            }
            session.getTransaction().commit();
        }
    }

    
    public void deleteByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Utilizador u = session.createQuery(
                    "from Utilizador u where u.email = :email", Utilizador.class)
                    .setParameter("email", email)
                    .uniqueResult();
            if (u != null) {
            	session.remove(u);
            }
            session.getTransaction().commit();
        }
    }
    
    
    public java.util.List<Utilizador> listAll() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            java.util.List<Utilizador> lista =
                session.createQuery("select u from Utilizador u order by u.id", Utilizador.class)
                       .getResultList();

            session.getTransaction().commit();
            return lista;
        }
    }
    
    public boolean atualizaPassword(String email, String oldPassword, String newPassword) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Utilizador u = session.createQuery(
                    "from Utilizador u where u.email = :email", Utilizador.class)
                    .setParameter("email", email)
                    .uniqueResult();

            if (u == null) {                       
                session.getTransaction().commit();
                return false;
            }
            if (!u.getPassword().equals(oldPassword)) { 
                session.getTransaction().commit();
                return false;
            }

            u.setPassword(newPassword);         
            session.persist(u);
            session.getTransaction().commit();
            return true;
        }
    }
    public long createIfNotExistsTipoUtilizador(String nome, String email,String password, String morada, int telemovel , String tipoUtilizador) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            // verifica se já existe
            Long count = session.createQuery(
                    "select count(u) from Utilizador u where u.email = :email", Long.class)
                    .setParameter("email", email)
                    .uniqueResult();

            if (count != null && count > 0) {
                session.getTransaction().commit();
                return -1;
            }

            Utilizador u = new Utilizador();
            u.setNome(nome);
            u.setEmail(email);
            u.setPassword(password);
            u.setMorada(morada);
            u.setTelemovel(telemovel);
            u.setTipoUtilizador(tipoUtilizador);
            
            
            session.persist(u);
            session.getTransaction().commit();
            return u.getId();
        }
    }
    
}
