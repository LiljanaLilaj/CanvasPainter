package canvas;

import canvas.shape.CharShape;

import javax.swing.*;
import java.awt.*;

class CanvasComponent extends JFrame {

    private final CharCanvas canvas;

    CanvasComponent(CharCanvas canvas) throws HeadlessException {
        this.canvas = canvas;
        add(canvas);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Canvas Paint Exercise");
        pack();
        setVisible(true);
    }

    void drawCanvas(int width, int height) {
        canvas.draw(width, height);
        repaint();
    }

    void updateCanvas(CharShape shape) {
        shape.draw(canvas);
        repaint();
    }
}
