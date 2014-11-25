package es.uvigo.daggs.ejemplojpa.entidades;

import es.uvigo.daggs.ejemplojpa.entidades.Asignatura;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-11-12T14:11:05")
@StaticMetamodel(Alumno.class)
public class Alumno_ { 

    public static volatile SingularAttribute<Alumno, Long> id;
    public static volatile SingularAttribute<Alumno, String> nombre;
    public static volatile SingularAttribute<Alumno, String> apellidos;
    public static volatile SingularAttribute<Alumno, String> domicilio;
    public static volatile ListAttribute<Alumno, Asignatura> asignaturas;

}