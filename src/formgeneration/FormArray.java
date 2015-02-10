/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package formgeneration;

import java.util.HashMap;

/**
 *
 * @author jonathan
 */
public class FormArray {
    public HashMap<String,FormHandler> profiles;

    public FormArray() {
        this.profiles = new HashMap<>();
    }
    
    public void print(){
        System.out.println("Schedule: " + this.profiles);
    }
    
    
    public void fillArray(){
    
        // General Vars
        int divisions = 6;
        int span = 24;
        
        TimeGenerator time = new TimeGenerator(divisions);
        time.setFixedSlots(span, divisions);
        
        // Morning Schedule ====================================================
        FormGenerator morningForm = new FormGenerator(divisions,"line");
        
        HashMap<String,Float> morningsection1 = new HashMap();
        morningsection1.put("a", +0.03125f);
        
        HashMap<String,Float> morningsection2 = new HashMap();
        morningsection2.put("a", +0.00f);
        
        HashMap<String,Float> morningsection3 = new HashMap();
        morningsection3.put("a", -0.03125f);
        
        HashMap<String,Float> morningsection4 = new HashMap();
        morningsection4.put("a", +0.0f);
        
        HashMap<String,Float> morningsection5 = new HashMap();
        morningsection5.put("a", +0.0f);
        
        HashMap<String,Float> morningsection6 = new HashMap();
        morningsection6.put("a", +0.0f);
            
        morningForm.addSection(morningsection1);
        morningForm.addSection(morningsection2);
        morningForm.addSection(morningsection3);
        morningForm.addSection(morningsection4);
        morningForm.addSection(morningsection5);
        morningForm.addSection(morningsection6);
        
        FormHandler morningSchedule = new FormHandler(time,morningForm);
        morningSchedule.print();
        System.out.println(morningSchedule.getAdjustedSchedule(2f));
//                
//        // Noon Schedule =======================================================
//        FormGenerator noonForm = new FormGenerator(divisions,"line");
//        
//        HashMap<String,Float> noonsection1 = new HashMap();
//        noonsection1.put("a", +0.03125f);
//        
//        HashMap<String,Float> noonsection2 = new HashMap();
//        noonsection2.put("a", +0.00f);
//        
//        HashMap<String,Float> noonsection3 = new HashMap();
//        noonsection3.put("a", -0.03125f);
//        
//        HashMap<String,Float> noonsection4 = new HashMap();
//        noonsection4.put("a", +0.0f);
//        
//        HashMap<String,Float> noonsection5 = new HashMap();
//        noonsection5.put("a", +0.0f);
//        
//        HashMap<String,Float> noonsection6 = new HashMap();
//        noonsection6.put("a", +0.0f);
//            
//        noonForm.addSection(noonsection1);
//        noonForm.addSection(noonsection2);
//        noonForm.addSection(noonsection3);
//        noonForm.addSection(noonsection4);
//        noonForm.addSection(noonsection5);
//        noonForm.addSection(noonsection6);
//        
//        FormHandler noonSchedule = new FormHandler(time,noonForm);
//        this.profiles.put("noon", noonSchedule);
//        
//        // Afternoon Schedule ==================================================
//        FormGenerator afternoonForm = new FormGenerator(divisions,"line");
//        
//        HashMap<String,Float> afternoonsection1 = new HashMap();
//        afternoonsection1.put("a", +0.00f);
//        
//        HashMap<String,Float> afternoonsection2 = new HashMap();
//        afternoonsection2.put("a", +0.00f);
//        
//        HashMap<String,Float> afternoonsection3 = new HashMap();
//        afternoonsection3.put("a", +0.00f);
//        
//        HashMap<String,Float> afternoonsection4 = new HashMap();
//        afternoonsection4.put("a", +0.03125f);
//        
//        HashMap<String,Float> afternoonsection5 = new HashMap();
//        afternoonsection5.put("a", +0.0f);
//        
//        HashMap<String,Float> afternoonsection6 = new HashMap();
//        afternoonsection6.put("a", -0.03125f);
//            
//        afternoonForm.addSection(afternoonsection1);
//        afternoonForm.addSection(afternoonsection2);
//        afternoonForm.addSection(afternoonsection3);
//        afternoonForm.addSection(afternoonsection4);
//        afternoonForm.addSection(afternoonsection5);
//        afternoonForm.addSection(afternoonsection6);
//        
//        FormHandler afternoonSchedule = new FormHandler(time,afternoonForm);
//        this.profiles.put("afternoon", afternoonSchedule);
//        
//        noonSchedule.print();
        
//        System.out.println(noonSchedule.getAdjustedSchedule(0.1f));
                
    }
    
    
    
}
