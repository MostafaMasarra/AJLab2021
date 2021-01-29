/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajlabsession05;

import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Bader
 */
public class Binding {

    static SimpleIntegerProperty first = new SimpleIntegerProperty(5); //create a property with value=5
    static SimpleIntegerProperty second = new SimpleIntegerProperty();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println(second.get()); // '0'
        second.bind(first);
        System.out.println(second.get());
        
        
        
        
        
        
        
        
        
        
        
        
        
        
//        second.bind(first);               //bind second property to first
//        System.out.println(second.get()); // '5'
//        first.set(16);                    //set first property's value
//        System.out.println(second.get()); // '16' - the value was automatically updated

    }

}
