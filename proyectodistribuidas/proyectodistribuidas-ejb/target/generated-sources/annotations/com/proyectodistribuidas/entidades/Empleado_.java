package com.proyectodistribuidas.entidades;

import com.proyectodistribuidas.entidades.Cargo;
import com.proyectodistribuidas.entidades.Estado;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-01T23:52:01")
@StaticMetamodel(Empleado.class)
public class Empleado_ { 

    public static volatile SingularAttribute<Empleado, Integer> codigo;
    public static volatile SingularAttribute<Empleado, Date> fechaNacimiento;
    public static volatile SingularAttribute<Empleado, String> cedula;
    public static volatile SingularAttribute<Empleado, String> apellido;
    public static volatile SingularAttribute<Empleado, Estado> codigoEstadoCivil;
    public static volatile SingularAttribute<Empleado, Cargo> codigoCargo;
    public static volatile SingularAttribute<Empleado, String> nombre;

}