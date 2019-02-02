package com.proyectodistribuidas.entidades;

import com.proyectodistribuidas.entidades.Empleado;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-01T23:52:01")
@StaticMetamodel(Estado.class)
public class Estado_ { 

    public static volatile SingularAttribute<Estado, Integer> codigo;
    public static volatile ListAttribute<Estado, Empleado> empleadoList;
    public static volatile SingularAttribute<Estado, String> nombre;

}