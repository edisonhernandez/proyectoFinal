/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectodistribuidas.managebean;

import com.proyectodistribuidas.entidades.Estado;
import com.proyectodistribuidas.session.EstadoFacadeLocal;
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
@Named(value = "estadoManagedBean")
@ViewScoped
public class EstadoManagedBean  implements Serializable, ManageBeanInterfaces<Estado> {

    @EJB
 private EstadoFacadeLocal estadoFacadeLocal;
  
     private List<Estado> listaEstados;
     private Estado estado;
    public EstadoManagedBean() {
    }
    
     @PostConstruct
    public void init(){
         listaEstados = estadoFacadeLocal.findAll();
    }
    
     @Override
    public void nuevo(){
    estado = new Estado();
    }
    
    @Override
    public void grabar(){
         try {
             if(estado.getCodigo() == null){
             estadoFacadeLocal.create(estado);
             }else{
                 estadoFacadeLocal.edit(estado);
             }
             estado= null;
             listaEstados = estadoFacadeLocal.findAll();
             mostrarMensajeTry("Informacion exitosa", FacesMessage.SEVERITY_INFO);
         } catch (Exception e) {
             mostrarMensajeTry("Ocurrio un error", FacesMessage.SEVERITY_ERROR);
         }
    }
    @Override
    public void seleccionar(Estado c){
          estado = c;
    }
    
     @Override
    public void eliminar(Estado c){
        try {
            estadoFacadeLocal.remove(c);
            listaEstados = estadoFacadeLocal.findAll();
            mostrarMensajeTry("Eliminado Correctamente", FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            mostrarMensajeTry("Ocurrior un error", FacesMessage.SEVERITY_ERROR);
        }
    }
    
     @Override
    public void cancelar(){
    estado= null;
    listaEstados = estadoFacadeLocal.findAll();

    }

    public List<Estado> getListaEstados() {
        return listaEstados;
    }

    public void setListaEstados(List<Estado> listaEstados) {
        this.listaEstados = listaEstados;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

}
