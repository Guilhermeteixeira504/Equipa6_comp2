package upt.lp.hibernate;

import org.hibernate.*;
import org.hibernate.boot.*;
import org.hibernate.boot.registry.*;
import java.util.List;

public class GerirInscricao {
	
	private SessionFactory sessionFactory;

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

    //long para a gestão de ids
    public long create(long voluntarioId, long programaId, int horasRealizadas) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Voluntario v = session.get(Voluntario.class, voluntarioId);
            ProgramaVoluntariado p = session.get(ProgramaVoluntariado.class, programaId);
            
// Se voluntário não existe, o programa não verifica a existência do programa, se voluntário existe ele verifica se programa existe
            if (v == null || p == null) {
                session.getTransaction().commit();
                return -1L; // v==null voluntário não existe, p==null programa não existe
            }

            Inscricao i = new Inscricao();
            i.setVoluntario(v);
            i.setPrograma(p);
            i.setNHorasRealizadas(horasRealizadas);

            //passar para a base de dados
            session.persist(i);
            session.getTransaction().commit();
            return i.getId();
        }
    }


    

    public Inscricao read(long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Inscricao i = session.get(Inscricao.class, id);
            session.getTransaction().commit();
            return i;
        }
    }

    public List<Inscricao> readAll() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Inscricao> inscricoes = session.createQuery(
                "from Inscricao", Inscricao.class).getResultList();
            session.getTransaction().commit();
            return inscricoes;
        }
    }
    public List<Inscricao> listAll() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Inscricao> lista = session.createQuery("from Inscricao i order by i.id", Inscricao.class).list();
            session.getTransaction().commit();
            return lista;
        }
    }

    public void update(Inscricao inscricao) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(inscricao);
            session.getTransaction().commit();
        }
    }

    public void delete(long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Inscricao i = session.get(Inscricao.class, id);

            if (i != null) {
            	session.remove(i);
            }

            session.getTransaction().commit();
        }
    }
}

