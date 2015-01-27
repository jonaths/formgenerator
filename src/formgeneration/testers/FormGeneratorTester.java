/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package formgeneration.testers;

import formgeneration.FormGenerator;
import formgeneration.FormHandler;
import formgeneration.TimeGenerator;
import java.util.HashMap;


/**
 *
 * @author jonathan
 */
public class FormGeneratorTester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        TimeGenerator time = new TimeGenerator(10);
        time.setFixedSlots(3, 3);
        
        FormGenerator form = new FormGenerator(3,"line");
        
        
        HashMap<String,Float> section1 = new HashMap();
        section1.put("a", +1.00f);
        
        HashMap<String,Float> section2 = new HashMap();
        section2.put("a", -1.00f);
        
        HashMap<String,Float> section3 = new HashMap();
        section3.put("a", +0.0f);
//        
//        HashMap<String,Float> section4 = new HashMap();
//        section4.put("a", +0.0f);
//        
//        HashMap<String,Float> section5 = new HashMap();
//        section5.put("a", +0.0f);
//        
//        HashMap<String,Float> section6 = new HashMap();
//        section6.put("a", +0.0f);
//        
//        HashMap<String,Float> section7 = new HashMap();
//        section7.put("a", +0.0f);
//        
//        HashMap<String,Float> section8 = new HashMap();
//        section8.put("a", +0.0f);
        
        form.addSection(section1);
        form.addSection(section2);
        form.addSection(section3);
//        form.addSection(section4);
//        form.addSection(section5);
//        form.addSection(section6);
//        form.addSection(section7);
//        form.addSection(section8);
        
        FormHandler schedule = new FormHandler(time,form,0f);
        
        schedule.print();
        
        
    }
    
}
