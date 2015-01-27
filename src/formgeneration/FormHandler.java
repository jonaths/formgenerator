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
public final class FormHandler {

    TimeGenerator times;
    FormGenerator form;
    float reference; // No se usa
    private float shapeArea;
    ArrayList<HashMap<String, Float>> schedule;

    public FormHandler(TimeGenerator times, FormGenerator form, float reference) {
        this.times = times;
        this.form = form;
        this.reference = reference;
        this.shapeArea = 0;
        this.schedule = new ArrayList<>();

        if (times.getSize() != form.getSize()) {
            System.out.println("ERROR: time form size mismatch. ");
            System.exit(0);
        }

        schedule = this.calculateSteps();

    }

    private ArrayList<HashMap<String, Float>> calculateSteps() {
        int c = 0;
        for (HashMap<String, Float> h : times.getTimeGeneratorDetail()) {

            float a = h.get("a");
            float b = h.get("b");
            float w = Math.abs(a - b);
            float offset;
            float finVal;

            // Calcula el offset (valor inicial) y el valor al final de la seccion
            offset = this.getOverallOffset(c, w);
            finVal = this.getValue(this.form.getParams(c), w, offset);

            // Actualiza los valores calculados
            this.form.setSectionOffset(c, offset);
            this.form.setSectionFinalValue(c, finVal);
            this.form.setSectionWidth(c, w);
            this.form.print();

            // Calcula la altura que deberia tener un cuadrado para coincidir
            // con el area bajo la curva
            float height = this.getHeightFromArea(c, w);
            
            HashMap<String, Float> struct = new HashMap<>();

            // Guarda Inicio y final de la estructura de tiempo
            struct.put("a", a);
            struct.put("b", b);

            // Guarda la altura y el área de la seccion
            struct.put("h", height);
            struct.put("ar", w * height);
            
            this.updateShapeArea(w * height);

            // Guarda el nuevo elemento de schedule
            this.schedule.add(struct);

            c++;
        }
        return this.schedule;
    }

    /**
     * Determina qué funcion utilizar para calcular un valor
     *
     * @param vars: el arreglo de parametros de la funcion
     * @param x: el punto donde se quiere calcular la funcion
     * @param offset: el offset inicial
     * @return
     */
    public float getValue(HashMap<String, Float> vars, float x, float offset) {
        float result = 0.0f;
        if (this.form.getFunctionName().equals("line")) {
            result = this.getLineValue(vars.get("a"), x, offset);
        } /**
         * 
         */
        else {
            // Asumir que es una línea
            result = this.getLineValue(vars.get("a"), x, offset);
        }
        return result;
    }

    private float getLineValue(float m, float x, float offset) {
        return m * x + offset;
    }

    /**
     * Recupera el offset acumulado
     *
     * @param c: el índice de form
     * @param w: el valor para el que se calcula la funcion
     * @return
     */
    public float getOverallOffset(int c, float w) {
        float offset;
        // Si es la primera seccion el offset es 0
        if (c == 0) {
//          offset = this.reference;
            offset = 0;
        // Si no es la primera seccion el offset es el valor final de la seccion anterior       
        } else {
            return this.form.getParams(c - 1).get("finVal");
        }
        return offset;
    }

    public float getHeightFromArea(int index, float x) {
        return this.getArea(index, x) / Math.abs(x);
    }
    
    public float getArea(int index, float x) {
        float result;
        // Si la aproximacion es una linea
        if (this.form.getFunctionName().equals("line")) {
            result = this.getLineArea(index, x);
        /***/    
        } else {
            result = this.getLineArea(index, x);
        }
        return result;
    }
    
    private float getLineArea(int index, float width) {
        float triangleHeight = 0;
        float baseHeight = 0;
        
        if (this.form.getParams(index).get("a") > 0){
//            System.out.println(">0");
            triangleHeight = this.form.getSectionFinalValue(index);
            baseHeight = this.getOverallOffset(index, width);
            
        }
        if (this.form.getParams(index).get("a") == 0){
//            System.out.println("=0");
            triangleHeight = 0;
            baseHeight = this.getOverallOffset(index, width);
        }
        if (this.form.getParams(index).get("a") < 0){
//            System.out.println("<0");
            triangleHeight = this.form.getSectionOffset(index);
            baseHeight = this.form.getSectionFinalValue(index);
        }
        
        float triangleArea = width * triangleHeight / 2;
        float baseArea = width * baseHeight;
        
        float totalArea = triangleArea + baseArea;
        
        System.out.println("\n\nArea: " + "\n  Width: " + width + "\n  TrHeight: " + triangleHeight + ", TriangleArea: " + triangleArea + ", BHeight: " + baseHeight + ", BArea: " + baseArea + ", TArea: " + totalArea);
        
        return totalArea;
    }

    public void print() {
        System.out.println("\n\nSchedule:" + this.schedule + "\nArea: " + this.getShapeArea());
    }

    public float updateShapeArea(float area){
        this.shapeArea = this.shapeArea + area;
        return this.shapeArea;
    }
    
    public float getShapeArea(){
        return this.shapeArea;
    }
    


}
