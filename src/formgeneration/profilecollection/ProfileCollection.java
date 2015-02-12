/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formgeneration.profilecollection;

import java.util.HashMap;

/**
 *
 * @author jonathan
 */
public class ProfileCollection {

    private HashMap<String, Profile > profiles;
    private double totalArea;
    private double range;

    /**
     * Crea un profile collection vacio. 
     */
    public ProfileCollection() {
        this.profiles = new HashMap<String, Profile>();
        totalArea = 0.0f;
        range = 0.0f;
    }
    
    /**
     * Inicializa profile collection con una estructura existente.
     * @param profile 
     */
    public void set(HashMap<String, Profile >profile){
        this.profiles = profile;
    }
    
    /**
     * Agrega un profile a Profile Collection
     * @param name : el nombre del profile
     * @param schedule : el profile
     */
    public void addProfile(String name, Profile schedule){
        
        this.profiles.put(name, schedule);
    }
    
    /**
     * Recupera un profile
     * @param name : el nombre del profile
     * @return 
     */
    public Profile getProfile(String name){
        return this.profiles.get(name);
    }

    public ProfileCollection getAdjustedProfileCollection(double reference){
        if ( !this.validateProfileCollection() ){
            System.out.println("ProfileCollection ERROR: coleccion no valida. ");
            System.exit(0);
        }
        
        double refArea = reference * this.range;  
        ProfileCollection adjustedProfileCollection = new ProfileCollection();
        for ( String s : this.profiles.keySet() ){
            adjustedProfileCollection.addProfile(s, this.profiles.get(s).getAdjustedProfile(refArea) );
        }
        
        return adjustedProfileCollection;
    }
    
    public boolean validateProfileCollection(){
        // Aqui deberia validar que todos comiencen y terminen en el mismo valor
        // Y ajustar el range, por ahora asume que es 24. 
        this.range = 24.0f;
        return true;
    }
    
    /**
     * Calcula el area acumulada
     * @return 
     */
    public double getTotalArea(){
        // Resetea el area total
        double accArea = 0.0f;
        this.totalArea = 0.0f;
        // Para cada seccion de Profile
        for ( String s : this.profiles.keySet() ){
            // Calcula el area acumulada
            accArea = accArea + this.profiles.get(s).getTotalArea();
        }
        // Asigna el area total y regresa el valor calculado
        this.totalArea = accArea;
        return accArea;
    }
    
    public void printTotalArea(){
        System.out.println("    TotalArea: " + this.getTotalArea());
    }
        
    /**
     * Imprime profile collection
     */
    public void printAll() {
        if (profiles.isEmpty()){
            System.out.println("Empty ArrayList... ");
        }
        else {
            System.out.println("Printing ProfileCollection: ");
            for (String s : this.profiles.keySet()){
                System.out.println("\n  " + s);
                this.profiles.get(s).print();
                this.profiles.get(s).printTotalArea();
            }
        }
        
    }
 
    /**
     * Regresa la coleccion de perfiles
     * @return 
     */
    public HashMap<String, Profile > getProfiles() {
        return this.profiles;
    }

}
