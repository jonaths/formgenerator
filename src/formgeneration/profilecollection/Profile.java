/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formgeneration.profilecollection;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author jonathan
 */
public class Profile {

    private ArrayList<HashMap<String, Double>> profile;
    private double min;
    private double max;
    private double area;

    public Profile() {
        this.profile = new ArrayList<HashMap<String, Double>>();
        min = 0.0f;
        max = 0.0f;
        area = 0.0f;
    }

    /**
     * Asigna a profile un profile existente
     *
     * @param profile
     */
    public void set(ArrayList<HashMap<String, Float>> profile) {
        // Para cada seccion de profile
        for ( HashMap<String, Float> p : profile  ){
            // Si contiene todas las llaves a, b y h. 
            if ( p.containsKey("a") && p.containsKey("b") && p.containsKey("h")){
                // Recupera inicio, final y altura
                double a = p.get("a");
                double b = p.get("b");
                double h = p.get("h");
                // Si a < b
                if ( a < b ){
                    // agrega la seccion
                    this.add(a, b, h);
                }
                // de lo contrario error
                else {
                    System.out.println("Profile ERROR: " + profile + " a > b.");
                    System.exit(0);
                }
            }
            // de lo contrario error
            else {
                System.out.println("Profile ERROR: " + profile + " missing params.");
                System.exit(0);
            }
        }
    }

    /**
     * Agrega un elemento de profile compuesto por a, b y h
     *
     * @param a : punto inicial
     * @param b : punto final
     * @param h : altura
     */
    public void add(double a, double b, double h) {
        if (a < b) {
            HashMap<String, Double> element = new HashMap<String, Double>();
            element.put("a", a);
            element.put("b", b);
            element.put("h", h);
            double sectionArea = this.getElementArea(element);
            element.put("ah", sectionArea);
            this.profile.add(element);
            
            // Actualiza variables locales (max, min y area total)
            this.updateLocalVars(a, b, sectionArea);

        } else {
            System.out.println("Profile ERROR: a > b");
            System.exit(0);
        }
    }

    private void updateLocalVars(double a, double b, double sectionArea) {
        // Actualiza maximos y minimos
        if (a < this.min) {
            this.min = a;
        }
        if (b > this.max) {
            this.max = b;
        }
        // Actualiza area
        this.area = this.area + sectionArea;
    }

    /**
     * Regresa el campo "ah" si existe, de lo contrario lo calcula
     *
     * @param element : HashMap<String,Float> con campos a, b y h por lo menos.
     * @return
     */
    public double getElementArea(HashMap<String, Double> element) {
        double result = 0;
        // Si contiene el campo ah regresalo
        if (element.containsKey("ah")) {
            result = element.get("ah");
        } // de lo contrario...
        else {
            // Si existen los campos a, b y h necesarios para calcularlo calculalo
            if (element.containsKey("a") && element.containsKey("b") && element.containsKey("h")) {
                result = Math.abs(element.get("a") - element.get("b")) * element.get("h");
            } // de lo contrario para y regresa error
            else {
                System.out.println("Profile ERROR:  falta un campo a, b o h. ");
                System.exit(0);
            }
        }
        //System.out.println(result);
        return result;
    }
    
    public Profile getAdjustedProfile(double refArea){
        Profile adjustedProfile = new Profile();
        double currArea = this.getTotalArea();
        System.out.println("refArea: " + refArea + " currArea: " + currArea );
        for ( HashMap<String, Double> p : profile ){
            double a = p.get("a");
            double b = p.get("b");
            System.out.println("ah: " + p.get("ah") + " %area: " + p.get("ah") / currArea + " contArea: " + p.get("ah") / currArea * refArea);
            double h = (p.get("ah") / currArea * refArea) / Math.abs( a - b);
            adjustedProfile.add(a, b, h);
        }
        return adjustedProfile;
    }

    public void print() {
        System.out.println(this.profile);
    }
    
    public double getTotalArea(){
        return this.area;
    }
    
    public double getMin(){
        return this.min;
    }
    
    public double getMax(){
        return this.max;
    }

}
