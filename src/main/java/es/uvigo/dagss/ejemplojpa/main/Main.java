package es.uvigo.dagss.ejemplojpa.main;

import es.uvigo.daggs.ejemplojpa.entidades.Alumno;
import es.uvigo.daggs.ejemplojpa.entidades.Asignatura;
import es.uvigo.daggs.ejemplojpa.entidades.Profesor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author ribadas
 */
public class Main {

    public final static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ejemplojpaPU");
        EntityManager em = emf.createEntityManager();

        // Entidades (Alumnos, Profesores, Asignaturas)
        Alumno a1 = new Alumno("Ana", "Alavarez", "Almeria");
        Alumno a2 = new Alumno("Belen", "Blazquez", "Barcelona");
        Alumno a3 = new Alumno("Carlos", "Caeiro", "Cuenca");
        Alumno a4 = new Alumno("Diego", "Diaz", "Don Benito");
        Alumno a5 = new Alumno("Pepe", "Perez", "Pamplona");

        Profesor fran = new Profesor("Fran");
        Profesor dani = new Profesor("Dani");
        Profesor arno = new Profesor("Arno");

        Asignatura dagss = new Asignatura("diseño de ...", "DAGSS", 4);
        Asignatura cdi = new Asignatura("concurrencia y ...", "CDI", 3);

        // Relaciones (Alumno->Asignatura)
        a1.anadirAsignatura(dagss); // Crea enlace de Alumno a Asignatura
        a2.anadirAsignatura(dagss);
        a3.anadirAsignatura(dagss);
        a4.anadirAsignatura(dagss);

        a3.anadirAsignatura(cdi);
        a4.anadirAsignatura(cdi);
        a5.anadirAsignatura(cdi);

        // Relaciones Asignatura-Profesor
        dagss.anadirProfesor(fran); // Crea ambos enlaces: de Asignatura a Profesor y de Profesor a Asignatura
        dagss.anadirProfesor(dani);

        cdi.anadirProfesor(arno);

        // Creación de entidades
        EntityTransaction txCreacion = em.getTransaction();

        txCreacion.begin();

        // Alumnos (y vinculacion con asignaturas)
        em.persist(a1);
        em.persist(a2);
        em.persist(a3);
        em.persist(a4);
        em.persist(a5);

        // Profesores
        em.persist(fran);
        em.persist(dani);
        em.persist(arno);

        // Materias (y vinculación con profesores)
        em.persist(dagss);
        em.persist(cdi);

        txCreacion.commit();

        // Consulta y modificación
        EntityTransaction txModificacion = em.getTransaction();

        txModificacion.begin();

        Alumno a = em.find(Alumno.class, 1L);
        a.setDomicilio("Zaragoza");
        em.merge(a);

        txModificacion.commit();

        // Consultas JPQL
        EntityTransaction txConsulta1 = em.getTransaction();

        txConsulta1.begin();
        Query q1 = em.createQuery("SELECT a "
                + "FROM Alumno a join a.asignaturas asig "
                + "WHERE asig.curso = 4");
        List<Alumno> alumnosDeCuarto = q1.getResultList();
        System.out.println("Alumnos de 4º: " + alumnosDeCuarto.toString());

        Query q2 = em.createQuery("SELECT p "
                + "FROM Alumno a join a.asignaturas asig join asig.profesores p "
                + "WHERE a.nombre = 'Carlos'");
        List<Profesor> profesoresDeCarlos = q2.getResultList();
        System.out.println("Profesores de Carlos: " + profesoresDeCarlos.toString());

        txConsulta1.commit();
        System.out.println();
        
        // Consultas Criteria API
        EntityTransaction txConsulta2 = em.getTransaction();

        txConsulta2.begin();

        // Buscar todos los Alumnos
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Alumno> query = builder.createQuery(Alumno.class);
        query.select(query.from(Alumno.class));
        List<Alumno> alumnos = em.createQuery(query).getResultList();
        System.out.println("Alumnos: " + alumnos);

        // Contar los Profesores
        CriteriaBuilder builder2 = em.getCriteriaBuilder();
        CriteriaQuery<Long> query2 = builder2.createQuery(Long.class);
        query2.select(builder2.count(query.from(Profesor.class)));
        Long contador = em.createQuery(query2).getSingleResult();
        System.out.println("Num. profesores: " + contador);

        txConsulta2.commit();
        System.out.println();
        
        em.close();
        emf.close();

    }
}
