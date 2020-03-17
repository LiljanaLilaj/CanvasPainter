package canvas.shape;

import canvas.CharCanvas;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.awt.*;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class CharLineTest {

    private CharCanvas canvasPainter;

    @Before
    public void setUp() throws Exception {
        canvasPainter = Mockito.mock(CharCanvas.class);
    }

    @Test
    public void drawVerticalLineCorrectly() {
        CharLine charLine = new CharLine(new Point(3, 4), new Point(3, 6));
        charLine.draw(canvasPainter);

        verify(canvasPainter).addPoint(3, 4, 'x');
        verify(canvasPainter).addPoint(3, 5, 'x');
        verify(canvasPainter).addPoint(3, 6, 'x');

        verifyNoMoreInteractions(canvasPainter);
    }

    @Test
    public void drawHorizontalLineCorrectly() {
        CharLine charLine = new CharLine(new Point(3, 4), new Point(6, 4));
        charLine.draw(canvasPainter);

        verify(canvasPainter).addPoint(3, 4, 'x');
        verify(canvasPainter).addPoint(4, 4, 'x');
        verify(canvasPainter).addPoint(5, 4, 'x');
        verify(canvasPainter).addPoint(6, 4, 'x');

        verifyNoMoreInteractions(canvasPainter);
    }

}