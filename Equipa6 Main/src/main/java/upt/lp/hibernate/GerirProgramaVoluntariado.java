package upt.lp.hibernate;

import org.hibernate.*;
import org.hibernate.boot.*;
import org.hibernate.boot.registry.*;
import java.time.LocalDate;

public class GerirProgramaVoluntariado {

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

    // CREATE - Criar novo programa
    public long create(String titulo, String descricao, String localizacao, 
                      int numeroVagas, LocalDate dataInicio, LocalDate dataFim, int totalHoras) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            ProgramaVoluntariado pv = new ProgramaVoluntariado();
            pv.setTitulo(titulo);
            pv.setDescricao(descricao);
            pv.setLocalizacao(localizacao);
            pv.setNumeroVagas(numeroVagas);
            pv.setDataInicio(dataInicio);
            pv.setDataFim(dataFim);
            pv.setTotalHoras(totalHoras);
            session.persist(pv);
            session.getTransaction().commit();
            return pv.getId();
        }
    }

    // CREATE - Criar se não existir (baseado no título)
    public long createIfNotExists(String titulo, String descricao, String localizacao, 
                                 int numeroVagas, LocalDate dataInicio, LocalDate dataFim, int totalHoras) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            // contador de objetos do programa de voluntariado
            Long count = session.createQuery(
                    "select count(p) from ProgramaVoluntariado p where p.titulo = :titulo", Long.class)
                    .setParameter("titulo", titulo)
                    .uniqueResult();

            if (count != null && count > 0) {
                session.getTransaction().commit(); 
                return -1;
            }

            ProgramaVoluntariado pv = new ProgramaVoluntariado();
            pv.setTitulo(titulo);
            pv.setDescricao(descricao);
            pv.setLocalizacao(localizacao);
            pv.setNumeroVagas(numeroVagas);
            pv.setDataInicio(dataInicio);
            pv.setDataFim(dataFim);
            pv.setTotalHoras(totalHoras);

            session.persist(pv);
            session.getTransaction().commit();
            return pv.getId();
        }
    }

    // READ - Ler por ID
    public ProgramaVoluntariado read(long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            ProgramaVoluntariado p = session.get(ProgramaVoluntariado.class, id);
            session.getTransaction().commit();
            return p;
        }
    }


    // READ - Ler por título
    public ProgramaVoluntariado readByTitulo(String titulo) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            ProgramaVoluntariado pv = session.createQuery(
                    "from ProgramaVoluntariado p where p.titulo = :titulo", ProgramaVoluntariado.class)
                    .setParameter("titulo", titulo)
                    .uniqueResult();
            session.getTransaction().commit();
            return pv;
        }
    }

    // UPDATE - Atualizar programa
    public void update(long id, String novoTitulo, String novaDescricao, String novaLocalizacao, 
                      int novoNumeroVagas, LocalDate novaDataInicio, LocalDate novaDataFim, int novoTotalHoras) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            ProgramaVoluntariado pv = session.get(ProgramaVoluntariado.class, id);
            if (pv != null) {
                pv.setTitulo(novoTitulo);
                pv.setDescricao(novaDescricao);
                pv.setLocalizacao(novaLocalizacao);
                pv.setNumeroVagas(novoNumeroVagas);
                pv.setDataInicio(novaDataInicio);
                pv.setDataFim(novaDataFim);
                pv.setTotalHoras(novoTotalHoras);
            }
            session.getTransaction().commit();
        }
    }

    // DELETE - Apagar por ID
    public void delete(long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            ProgramaVoluntariado pv = session.get(ProgramaVoluntariado.class, id);
            if (pv != null) {
            	session.remove(pv);
            }
            session.getTransaction().commit();
        }
    }

    // DELETE - Apagar por título
    public void deleteByTitulo(String titulo) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            ProgramaVoluntariado pv = session.createQuery(
                    "from ProgramaVoluntariado p where p.titulo = :titulo", ProgramaVoluntariado.class)
                    .setParameter("titulo", titulo)
                    .uniqueResult();
            if (pv != null) {
            	session.remove(pv);
            }
            session.getTransaction().commit();
        }
    }

    // Método adicional: Listar todos os programas
    public java.util.List<ProgramaVoluntariado> listAll() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            java.util.List<ProgramaVoluntariado> programas = session.createQuery(
                    "from ProgramaVoluntariado", ProgramaVoluntariado.class)
                    .getResultList();
            session.getTransaction().commit();
            return programas;
        }
    }
    
    
}