/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectodistribuidas.managebean;

import com.proyectodistribuidas.entidades.Empleado;
import com.proyectodistribuidas.session.EmpleadoFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author user
 */
@Named(value = "empleadoManagedBean")
@ViewScoped
public class EmpleadoManagedBean implements Serializable, ManageBeanInterfaces<Empleado>{

    //instancia al session bean del empleado
    @EJB
    private EmpleadoFacadeLocal empleadoFacadeLocal;
    private List<Empleado> listaEmpleados;
    
     private Empleado empleado;
    public EmpleadoManagedBean() {
    }
    
    //metodo para inicializar a EmpleadoFacadeLocal
    @PostConstruct
    public void init(){
        listaEmpleados = empleadoFacadeLocal.findAll();
        
    }
    //METODOS DE LA INTERFAZ GENERICA
    @Override
    public void nuevo(){
    empleado = new Empleado();
    }
    
     @Override
    public void grabar(){
         try {
             if(empleado.getCodigo() == null){
             empleadoFacadeLocal.create(empleado);
             }else{
                 empleadoFacadeLocal.edit(empleado);
             }
             empleado = null;
             listaEmpleados = empleadoFacadeLocal.findAll();
             mostrarMensajeTry("Informacion exitosa", FacesMessage.SEVERITY_INFO);
         } catch (Exception e) {
             mostrarMensajeTry("Ocurrio un error", FacesMessage.SEVERITY_ERROR);
         }
    
    
    
    }
    
    @Override
    public void seleccionar(Empleado c){
    empleado = c;
    }
    
    @Override
    public void eliminar(Empleado c){
        try {
            empleadoFacadeLocal.remove(c);
            listaEmpleados = empleadoFacadeLocal.findAll();
            mostrarMensajeTry("Eliminado Correctamente", FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            mostrarMensajeTry("Ocurrior un error", FacesMessage.SEVERITY_ERROR);
        }
    
    
    }
    
    @Override
    public void cancelar(){
    empleado = null;
    }

    public List<Empleado> getListaEmpleados() {
        return listaEmpleados;
    }

    public void setListaEmpleados(List<Empleado> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
}
