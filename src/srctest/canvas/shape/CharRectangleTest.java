package canvas.shape;

import canvas.CharCanvas;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.Times;

import java.awt.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class CharRectangleTest {

    private CharCanvas canvasPainter;

    @Before
    public void setUp() throws Exception {
        canvasPainter = Mockito.mock(CharCanvas.class);
    }

    @Test
    public void drawVerticalLineCorrectly() {
        CharRectangle charRec = new CharRectangle(new Point(3, 4), new Point(6, 8));
        charRec.draw(canvasPainter);

       // horizontal top
      //  verify(canvasPainter).addPoint(3, 4, 'x'); // meeting corner
        verify(canvasPainter).addPoint(4, 4, 'x');
        verify(canvasPainter).addPoint(5, 4, 'x');
     //   verify(canvasPainter).addPoint(6, 4, 'x'); // meeting corner

        // horizontal bottom
        //verify(canvasPainter).addPoint(3, 8, 'x'); // meeting corner
        verify(canvasPainter).addPoint(4, 8, 'x');
        verify(canvasPainter).addPoint(5, 8, 'x');
      //  verify(canvasPainter).addPoint(6, 8, 'x'); // meeting corner

        // vertical left
        verify(canvasPainter, times(2)).addPoint(3, 4, 'x');
        verify(canvasPainter).addPoint(3, 5, 'x');
        verify(canvasPainter).addPoint(3, 6, 'x');
        verify(canvasPainter).addPoint(3, 7, 'x');
        verify(canvasPainter, times(2)).addPoint(3, 8, 'x');

        // vertical right
        verify(canvasPainter, times(2)).addPoint(6, 4, 'x');
        verify(canvasPainter).addPoint(6, 5, 'x');
        verify(canvasPainter).addPoint(6, 6, 'x');
        verify(canvasPainter).addPoint(6, 7, 'x');
        verify(canvasPainter, times(2)).addPoint(6, 8, 'x');

        verifyNoMoreInteractions(canvasPainter);
    }

}