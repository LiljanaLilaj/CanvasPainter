package canvas;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class CommandParserTest {

    private int width = 40;
    private int height = 30;

    @Test
    public void invalidLinePattern() {
        String command = "L 5 6";
        String message = CommandParser.validateLine(command, width, height).orElse("");
        assertEquals("Invalid Line command pattern: L 5 6 i.e. [L x0 y0 x1 y1]", message);
    }

    @Test
    public void invalidLineDimensions() {
        String command = "L 0 6 5 6";
        String message = CommandParser.validateLine(command, width, height).orElse("");
        assertEquals("Negative or zero dimensions entered: L 0 6 5 6", message);
    }

    @Test
    public void invalidLinePoints() {
        String command = "L 10 10 6 10";
        String message = CommandParser.validateLine(command, width, height).orElse("");
        assertEquals("Please specify the top left corner first.", message);
    }

    @Test
    public void invalidLineDirection() {
        String command = "L 6 8 10 10";
        String message = CommandParser.validateLine(command, width, height).orElse("");
        assertEquals("Lines should be horizontal or vertical", message);
    }

    @Test
    public void lineOutsideCanvasArea() {
        String command = "L 6 8 42 8";
        String message = CommandParser.validateLine(command, width, height).orElse("");
        assertEquals("Specified dimensions fall outside canvas area: [40:30]", message);
    }

    @Test
    public void validLineCommand() {
        String command = "L 6 8 20 8";
        Optional<String> message = CommandParser.validateLine(command, width, height);
        Assert.assertFalse(message.isPresent());
    }

    @Test
    public void invalidRectanglePattern() {
        String command = "R 5 6";
        String message = CommandParser.validateRectangle(command, width, height).orElse("");
        assertEquals("Invalid Rectangle command pattern: R 5 6 i.e. [R x0 y0 x1 y1]", message);
    }

    @Test
    public void invalidRectangleDimensions() {
        String command = "R 0 6 5 6";
        String message = CommandParser.validateRectangle(command, width, height).orElse("");
        assertEquals("Negative or zero dimensions entered: R 0 6 5 6", message);
    }

    @Test
    public void invalidRecatangleCorners() {
        String command = "R 10 10 6 10";
        String message = CommandParser.validateRectangle(command, width, height).orElse("");
        assertEquals("Please specify the top left corner first.", message);
    }

    @Test
    public void rectangleOutsideCanvasArea() {
        String command = "R 6 8 42 12";
        String message = CommandParser.validateRectangle(command, width, height).orElse("");
        assertEquals("Specified dimensions fall outside canvas area: [40:30]", message);
    }

    @Test
    public void invalidRectangleSize() {
        String command = "R 6 8 12 8";
        String message = CommandParser.validateRectangle(command, width, height).orElse("");
        assertEquals("Rectangle with no width or height", message);
    }

    @Test
    public void validRectangleCommand() {
        String command = "R 6 8 34 24";
        Optional<String> message = CommandParser.validateRectangle(command, width, height);
        Assert.assertFalse(message.isPresent());
    }

    @Test
    public void invalidCanvasPattern() {
        String command = "C 6 f ";
        String message = CommandParser.validateCanvas(command).orElse("");
        assertEquals("Invalid Canvas command pattern: C 6 f  i.e. [C w h]", message);
    }

    @Test
    public void invalidCanvasDimensions() {
        String command = "C 6 0";
        String message = CommandParser.validateCanvas(command).orElse("");
        assertEquals("Negative or zero dimensions entered: C 6 0", message);
    }

}