package canvas.shape;

import canvas.CharCanvas;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class CharBoxTest {

   private CharCanvas canvasPainter;

    @Before
    public void setUp() throws Exception {
        canvasPainter = Mockito.mock(CharCanvas.class);
    }

    @Test
    public void drawBoxOnTheCanvasCorrectly() {
        CharBox charBox = new CharBox(3, 4);
        charBox.draw(canvasPainter);
        // vertical left border
        verify(canvasPainter).addPoint(0,0, '|');
        verify(canvasPainter).addPoint(0,1, '|');
        verify(canvasPainter).addPoint(0,2, '|');
        verify(canvasPainter).addPoint(0,3, '|');

        // vertical right border
        verify(canvasPainter).addPoint(2,0, '|');
        verify(canvasPainter).addPoint(2,1, '|');
        verify(canvasPainter).addPoint(2,2, '|');
        verify(canvasPainter).addPoint(2,3, '|');

        // horizontal top border
        verify(canvasPainter).addPoint(0,0, '-');
        verify(canvasPainter).addPoint(1,0, '-');
        verify(canvasPainter).addPoint(2,0, '-');

        // horizontal bottom border
        verify(canvasPainter).addPoint(0,3, '-');
        verify(canvasPainter).addPoint(1,3, '-');
        verify(canvasPainter).addPoint(2,3, '-');

        verifyNoMoreInteractions(canvasPainter);

    }

}