/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajlabsession06;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author Bader
 */
public class MyHandler implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        System.out.println("Outer Event Class is saying: Hello World!");
    }
}
