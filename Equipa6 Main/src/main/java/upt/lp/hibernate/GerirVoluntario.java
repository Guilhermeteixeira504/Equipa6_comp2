package upt.lp.hibernate;


import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class GerirVoluntario {
	public SessionFactory sessionFactory;
		 
	public void setup() {
		 
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure() 
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
		 
	public long create(Utilizador utilizador) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Voluntario v = new Voluntario();
            v.setUtilizador(utilizador);

            session.persist(v);
            session.getTransaction().commit();
            return v.getId();
        }
    }
		 
	public long createIfNotExists(long utilizadorId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            // Verifica se já existe voluntário com este utilizador
            Long count = session.createQuery(
                    "select count(v) from Voluntario v where v.utilizador.id = :uid", Long.class)
                    .setParameter("uid", utilizadorId)
                    .uniqueResult();

            if (count != null && count > 0) {
                session.getTransaction().commit();
                return -1; // já existe
            }

            Utilizador u = session.get(Utilizador.class, utilizadorId);
            if (u == null) {
                session.getTransaction().commit();
                return -2; // utilizador não existe
            }

            Voluntario v = new Voluntario();
            v.setUtilizador(u);

            session.persist(v);
            session.getTransaction().commit();
            return v.getId();
        }
    }
	
    public Voluntario read(long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Voluntario v = session.get(Voluntario.class, id);
            session.getTransaction().commit();
            return v;
        }
    }
    
    public Voluntario readByUtilizadorId(long utilizadorId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Voluntario v = session.createQuery(
                    "from Voluntario v where v.utilizador.id = :uid", Voluntario.class)
                    .setParameter("uid", utilizadorId)
                    .uniqueResult();
            session.getTransaction().commit();
            return v;
        }
        
    }
    public List<Voluntario> listAll() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            // distinct evita duplicados, pois um utilizador pode se inscrever em vários programas
            List<Voluntario> lista = session.createQuery(
                "select distinct v " +
                "from Voluntario v " +
                "left join fetch v.inscricoes " +   // <- inicializa a coleção
                "left join fetch v.utilizador",     // opcional, se o toString usa utilizador
                Voluntario.class
            ).getResultList();

            session.getTransaction().commit();
            return lista;
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
            Voluntario v = session.get(Voluntario.class, id);
            if (v != null) {
                session.remove(v);
            }
            session.getTransaction().commit();
        }
    }
	
	public void deleteByUtilizadorId(long utilizadorId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Voluntario v = session.createQuery(
                    "from Voluntario v where v.utilizador.id = :uid", Voluntario.class)
                    .setParameter("uid", utilizadorId)
                    .uniqueResult();
            if (v != null) {
                session.remove(v);
            }
            session.getTransaction().commit();
        }
    }
	
	//garantir que existe um utilizador tem um voluntário associado
	public long ensureVoluntarioForUtilizador(long utilizadorId) {
	    try (Session session = sessionFactory.openSession()) {
	        session.beginTransaction();

	        // 1) Já existe voluntário associado a este utilizador?
	        Voluntario v = session.createQuery(
	                "select v from Voluntario v where v.utilizador.id = :uid", Voluntario.class)
	                .setParameter("uid", utilizadorId)
	                .setMaxResults(1)
	                .uniqueResult();

	        if (v != null) {
	            session.getTransaction().commit();
	            return v.getId(); // já existe -> devolve o id
	        }

	        // 2) Caso não exista, confirma se o utilizador existe
	        Utilizador u = session.get(Utilizador.class, utilizadorId);
	        if (u == null) {
	            session.getTransaction().commit();
	            return -1; // não há utilizador com esse ID
	        }

	        // 3) Cria o voluntário “por cima” do utilizador (mesma PK através do MapsId)
	        v = new Voluntario();
	        v.setUtilizador(u);      // ligação 1–1; o id do voluntário = id do utilizador
	        session.persist(v);

	        session.getTransaction().commit();
	        return v.getId();
	    }
	}

}

	
	
	
