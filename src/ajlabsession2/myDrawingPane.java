package ajlabsession2;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class myDrawingPane extends Pane  {
    public myDrawingPane(){
        Line l = new Line(10, 10, 200,200);
        Line x = new Line (200, 10, 10, 200);
        Circle c = new Circle(50,100, 25);
        this.getChildren().addAll(l, x, c);
    }
}
