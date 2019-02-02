/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectodistribuidas.managebean;


import com.proyectodistribuidas.entidades.Cargo;

import com.proyectodistribuidas.session.CargoFacadeLocal;

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
@Named(value = "cargoManagedBean")
@ViewScoped
public class CargoManagedBean implements Serializable, ManageBeanInterfaces<Cargo>{
 @EJB
 private CargoFacadeLocal cargoFacadeLocal;
  
     private List<Cargo> listaCargos;
     private Cargo cargo;
    public CargoManagedBean() {
    }
    @PostConstruct
    public void init(){
         listaCargos = cargoFacadeLocal.findAll();
    }
    
    @Override
    public void nuevo(){
    cargo = new Cargo();
    }
    
     @Override
    public void grabar(){
         try {
             if(cargo.getCodigo() == null){
             cargoFacadeLocal.create(cargo);
             }else{
                 cargoFacadeLocal.edit(cargo);
             }
             cargo = null;
             listaCargos = cargoFacadeLocal.findAll();
             mostrarMensajeTry("Informacion exitosa", FacesMessage.SEVERITY_INFO);
         } catch (Exception e) {
             mostrarMensajeTry("Ocurrio un error", FacesMessage.SEVERITY_ERROR);
         }
    
    
    
    }
    
     @Override
    public void seleccionar(Cargo c){
          cargo = c;
    }
    
    @Override
    public void eliminar(Cargo c){
        try {
            cargoFacadeLocal.remove(c);
            listaCargos = cargoFacadeLocal.findAll();
            mostrarMensajeTry("Eliminado Correctamente", FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            mostrarMensajeTry("Ocurrior un error", FacesMessage.SEVERITY_ERROR);
        }
    
    
    }
    
    @Override
    public void cancelar(){
    cargo = null;
    listaCargos = cargoFacadeLocal.findAll();

    }

    public List<Cargo> getListaCargos() {
        return listaCargos;
    }

    public void setListaCargos(List<Cargo> listaCargos) {
        this.listaCargos = listaCargos;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
    
}
