/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package formgeneration.testers;

import formgeneration.profilecollection.Profile;
import formgeneration.profilecollection.ProfileCollection;
import formgeneration.testers.TestProfileCollection;



/**
 *
 * @author jonathan
 */
public class FormGeneratorTester {

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        
//        FormArray profiles = new FormArray();
//        profiles.fillArray();
//        
//        
//    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Crea un perfil de prueba
        TestProfileCollection test = new TestProfileCollection();

        // Crea el perfil verdadero y lo llena con datos de prueba
        ProfileCollection profiles = new ProfileCollection();
        profiles = test.fillTestProfileCollection();
        
        // Crea un profile y lo agrega a ProfileCollection
        Profile testprofile = test.getTestProfile("profile2");
        testprofile.print();
        System.out.println(testprofile.getTotalArea());

//        Profile adjustedprofile = testprofile.getAdjustedProfile(100.0f);
//        adjustedprofile.print();
//        System.out.println(adjustedprofile.getTotalArea());
        
        
        profiles.addProfile("profile2", testprofile);
        
        System.out.println("profiles init");
        System.out.println(profiles.getTotalArea());
        profiles.printAll();
        
        System.out.println("Adjusted profiles... ");
        ProfileCollection adjustedprofile = new ProfileCollection();
        adjustedprofile = profiles.getAdjustedProfileCollection(10.0);
        System.out.println(adjustedprofile.getTotalArea());
        adjustedprofile.printAll();
        
        
        System.out.println(profiles.getTotalArea());
        
        
        
        
        
    }
    
}
