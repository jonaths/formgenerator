/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package formgeneration.testers;

import formgeneration.profilecollection.Profile;
import formgeneration.profilecollection.ProfileCollection;

/**
 *
 * @author jonathan
 */
public class TestProfileCollection {
    ProfileCollection test;

    TestProfileCollection() {
        this.test = new ProfileCollection();
    }

    public ProfileCollection fillTestProfileCollection() {
        
        ProfileCollection collection = new ProfileCollection();
        
        Profile profile1 = new Profile();
        profile1.add(0.0f, 8.0f, 1.0f);
        profile1.add(8.0f, 16.0f, 2.0f);
        profile1.add(16.0f,24.0f,1.0f);
        collection.addProfile("profile1", profile1);
        
        Profile profile2 = new Profile();
        profile2.add(0.0f, 8.0f, 2.0f);
        profile2.add(8.0f, 16.0f, 1.0f);
        profile2.add(16.0f,24.0f,2.0f);
        collection.addProfile("profile2", profile2);
        
        this.test = collection;
        return collection;
    }
    
    public Profile getTestProfile(String s){
        return this.test.getProfile(s);
                
    }
        
}
