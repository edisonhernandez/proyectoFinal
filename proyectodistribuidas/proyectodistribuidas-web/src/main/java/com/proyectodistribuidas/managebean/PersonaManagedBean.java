/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectodistribuidas.managebean;

import com.proyectodistribuidas.entidades.Persona;
import com.proyectodistribuidas.session.PersonaFacadeLocal;
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
@Named(value = "personaManagedBean")
@ViewScoped
public class PersonaManagedBean implements Serializable, ManageBeanInterfaces<Persona>{

    //instancia al session bean del empleado
    @EJB
    private PersonaFacadeLocal personaFacadeLocal;
    private List<Persona> listaPersonas;
    
     private Persona persona;
    public PersonaManagedBean() {
    }
    
    //metodo para inicializar a EmpleadoFacadeLocal
    @PostConstruct
    public void init(){
        listaPersonas = personaFacadeLocal.findAll();
        
    }
    //METODOS DE LA INTERFAZ GENERICA
    @Override
    public void nuevo(){
    persona = new Persona();
    }
    
     @Override
    public void grabar(){
         try {
             if(persona.getCodigo() == null){
             personaFacadeLocal.create(persona);
             }else{
                 personaFacadeLocal.edit(persona);
             }
             persona = null;
             listaPersonas = personaFacadeLocal.findAll();
             mostrarMensajeTry("Informacion exitosa", FacesMessage.SEVERITY_INFO);
         } catch (Exception e) {
             mostrarMensajeTry("Ocurrio un error", FacesMessage.SEVERITY_ERROR);
         }
    
    
    
    }
    
    @Override
    public void seleccionar(Persona c){
    persona = c;
    }
    
    @Override
    public void eliminar(Persona c){
        try {
            personaFacadeLocal.remove(c);
            listaPersonas = personaFacadeLocal.findAll();
            mostrarMensajeTry("Eliminado Correctamente", FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            mostrarMensajeTry("Ocurrior un error", FacesMessage.SEVERITY_ERROR);
        }
    
    
    }
    
    @Override
    public void cancelar(){
    persona = null;
    }

    public List<Persona> getListaPersonas() {
        return listaPersonas;
    }

    public void setListaPersonas(List<Persona> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}
