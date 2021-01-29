/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajlabsession06;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

/**
 *
 * @author Bader
 */
public class ResizableCircle extends StackPane {

    private final Circle circle;

    ResizableCircle(int radius) {
        circle = new Circle(radius);
        this.getChildren().add(circle);
    }

    void enlarge() {
        this.circle.setRadius(this.circle.getRadius() * 1.1);
    }

    void shrink() {
        this.circle.setRadius(this.circle.getRadius() / 1.1);
    }
}
