/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectodistribuidas.managebean;

import com.proyectodistribuidas.entidades.Proveedor;
import com.proyectodistribuidas.session.ProveedorFacadeLocal;
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
@Named(value = "proveedorManagedBean")
@ViewScoped
public class ProveedorManagedBean implements Serializable, ManageBeanInterfaces<Proveedor>{
@EJB
 private ProveedorFacadeLocal proveedorFacadeLocal;

private List<Proveedor> listaProveedores;
     private Proveedor proveedor;
    public ProveedorManagedBean() {
    }
    
    @PostConstruct
    public void init(){
         listaProveedores = proveedorFacadeLocal.findAll();
    }
    
     @Override
    public void nuevo(){
    proveedor = new Proveedor();
    }
    
     @Override
    public void grabar(){
         try {
             if(proveedor.getCodigo() == null){
             proveedorFacadeLocal.create(proveedor);
             }else{
                 proveedorFacadeLocal.edit(proveedor);
             }
             proveedor = null;
             listaProveedores = proveedorFacadeLocal.findAll();
             mostrarMensajeTry("Informacion exitosa", FacesMessage.SEVERITY_INFO);
         } catch (Exception e) {
             mostrarMensajeTry("Ocurrio un error", FacesMessage.SEVERITY_ERROR);
         }

    }
    
     @Override
    public void seleccionar(Proveedor c){
          proveedor = c;
    }
    
    @Override
    public void eliminar(Proveedor c){
        try {
            proveedorFacadeLocal.remove(c);
            listaProveedores = proveedorFacadeLocal.findAll();
            mostrarMensajeTry("Eliminado Correctamente", FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            mostrarMensajeTry("Ocurrior un error", FacesMessage.SEVERITY_ERROR);
        }
    }
    
    @Override
    public void cancelar(){
    proveedor = null;
    listaProveedores = proveedorFacadeLocal.findAll();

    }

    public List<Proveedor> getListaProveedores() {
        return listaProveedores;
    }

    public void setListaProveedores(List<Proveedor> listaProveedores) {
        this.listaProveedores = listaProveedores;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
    
    
}
