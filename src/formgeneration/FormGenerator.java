/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formgeneration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author jonathan
 */
public class FormGenerator {

    private int sections;
    private String functionName;
    private String[] vars;
    
    private ArrayList<HashMap<String, Float>> params;

    

    String noValidationReason;

    public FormGenerator(int sections, String function) {
        this.sections = sections;                   // Número de secciones
        this.vars = this.getVars(function);                           // Variables de cada seccion
        this.params = new ArrayList<>(sections);    // Lista de secciones parametrizadas
        this.noValidationReason = "none";           // Ultimo mensaje de no validacion de seccion
    }
    
    public int getSize(){
        return this.params.size();
    }
    
    /**
     * Recupera las variables asociadas a una funcion
     *
     * @param function
     * @return
     */
    private String[] getVars(String function) {
        // Si la funcion es una linea
        if (function.equals("line")) {
            return this.getLineVars();
        } 
        /***/
        else {
            // Asumir que es una linea
            return this.getLineVars();
        }

    }
    
    /**
     * Recupera el nombre de las variables requeridas segun de una linea
     * @return 
     */
    private String[] getLineVars() {
        this.functionName = "line";
        return new String[]{"a"};
    }
    
    /**
     * Valida y agrega una nueva seccion a params
     * @param section 
     */
    public void addSection(HashMap<String, Float> section) {
        if (this.validateSection(section)) {
            this.params.add(section);
        }
        else {
            System.out.println("ERROR: " + this.noValidationReason);
            System.exit(0);
        }
    }
    
    public void print() {
        System.out.println(
                "\n\nForm: \n"
                + "Errors: " + this.noValidationReason
                + "\nFunction: " + this.functionName
                + "\nVars: " + Arrays.toString(this.vars)
                + "\nSections: " + this.params);
    }
    
    /***************************************************************************
     * Validaciones 
     **************************************************************************/
    
    /**
     * Valida los parametros de la nueva seccion
     * @param section
     * @return 
     */
    private boolean validateSection(HashMap<String, Float> section) {
        boolean isValid = true;
        // Valida que al insertar una nueva seccion no se exceda el número maximo
        if (!this.validateSectionSize()) {
            isValid = false;
        }
        // Valida que existan todos los parametros requeridos
        if (!this.validateSectionParams(section.keySet())) {
            isValid = false;
        }
        // Valida que el numero de parametros coincida con los requeridos
        if (!this.validateVarsSize(section.keySet())) {
            isValid = false;
        }
        return isValid;
    }

    private boolean validateSectionSize() {
        if ((this.params.size() + 1) > this.sections) {
            this.noValidationReason = "Size exceeded. ";
            return false;
        } else {
            return true;
        }
    }

    private boolean validateSectionParams(Set<String> keys) {
        for (String v : this.vars) {
            if (!keys.contains(v)) {
                this.noValidationReason = "Vars missing. ";
                return false;
            }
        }
        return true;
    }
    
    private boolean validateVarsSize(Set<String> keys) {
        if (keys.size() != this.vars.length) {
            this.noValidationReason = "Vars size mismatch. ";
            return false;
        }
        return true;

    }

    /***************************************************************************
    * Sets & Gets
    ***************************************************************************/ 
    
    /**
     * 
     * @param index
     * @param offset 
     */
    public void setSectionOffset(int index, float offset) {
        this.params.get(index).put("offset", offset);
    }
    
    /**
     * 
     * @param index
     * @param finalValue 
     */
    public void setSectionFinalValue(int index, float finalValue ){
        this.params.get(index).put("finVal", finalValue);
    }
    
    /**
     * 
     * @param index
     * @param w 
     */
    public void setSectionWidth(int index,float w){
        this.params.get(index).put("width", w);
    }
    
    public HashMap<String, Float> getParams(int index) {
        return this.params.get(index);
    }
    
    public float getSectionOffset(int index) {
        return this.params.get(index).get("offset");
    }
    
    public float getSectionFinalValue(int index){
        return this.params.get(index).get("finVal");
    }
    
    public String getFunctionName(){
        return this.functionName;
    }
}

