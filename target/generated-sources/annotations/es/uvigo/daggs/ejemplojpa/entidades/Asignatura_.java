package es.uvigo.daggs.ejemplojpa.entidades;

import es.uvigo.daggs.ejemplojpa.entidades.Profesor;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-11-12T14:11:05")
@StaticMetamodel(Asignatura.class)
public class Asignatura_ { 

    public static volatile SingularAttribute<Asignatura, Long> id;
    public static volatile SingularAttribute<Asignatura, String> nombre;
    public static volatile SingularAttribute<Asignatura, String> codigo;
    public static volatile SingularAttribute<Asignatura, Integer> curso;
    public static volatile ListAttribute<Asignatura, Profesor> profesores;

}