/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package formgeneration;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author jonathan
 */
public class TimeGenerator {
    private ArrayList<Float> list;
    private int range;
    private int divisions;

    public TimeGenerator(int divisions) {
        this.list = new ArrayList(divisions);
        this.range = 0;
        this.divisions = 0;
    }
    
    public int getSize(){
        return this.list.size();
    }

    /**
     * Crea una lista indicando donde termina cada intervalo constante. 
     * Comienza en 0, así: [11,23] quiere decir intervalos [0,12) y [12,0=24)
     * @param range: el rango. 
     * @param divisions: el numero de divisiones. 
     * @return 
     */
    public ArrayList setFixedSlots(int range, int divisions){
        this.range = range;
        this.divisions = divisions;
        float interval = range / (float)divisions;
        float acc = 0;
        for (int i = 0; i < divisions; i++) {
            acc = acc + interval;
            this.list.add(acc - 1);
        }    
        return list;
    }
    
    /**
     * Imprime lista de intervalos. 
     */
    public void printSimple(){
        System.out.println("= Simple = " + getTimeGeneratorSimple());
    }
    
    public void printDetail(){
        System.out.println("= Detail = " + getTimeGeneratorDetail());
    }
    
    public ArrayList<Float> getTimeGeneratorSimple(){
        return this.list;
    }
    
    public ArrayList<HashMap<String,Float>> getTimeGeneratorDetail(){
        ArrayList<HashMap<String,Float>> detail = new ArrayList<>();
        
        // Valida que la lista de intervalos tenga al menos 2 elementos
        if (this.getTimeGeneratorSimple().size() < 2){
            System.out.println("Error: timegenerator debe tener al menos dos elementos. ");
            System.exit(0);
        }
        
        // Crea la estructura de horas iniciales y finales. 
        float a,b;
        for (int j = 0; j < this.getTimeGeneratorSimple().size(); j++){
            
            b = this.getTimeGeneratorSimple().get(j);
            if (j == 0 ){
                a = 0;
            }
            else{
                a = this.getTimeGeneratorSimple().get(j - 1) + 1;
            }
            b = this.getTimeGeneratorSimple().get(j) + 1;
            
            HashMap<String,Float> struct = new HashMap<>();
            struct.put("a", a);
            struct.put("b", b);
            detail.add(struct);
        }
        return detail;
    }
    
    public int getDivisions(){
        return this.divisions;
    }
    
    public int getRange(){
        return this.range;
    }
    
    
}
