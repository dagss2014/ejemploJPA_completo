package es.uvigo.daggs.ejemplojpa.entidades;

import es.uvigo.daggs.ejemplojpa.entidades.Asignatura;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-11-12T14:11:05")
@StaticMetamodel(Profesor.class)
public class Profesor_ { 

    public static volatile SingularAttribute<Profesor, Long> id;
    public static volatile SingularAttribute<Profesor, String> nombre;
    public static volatile ListAttribute<Profesor, Asignatura> asignaturas;

}