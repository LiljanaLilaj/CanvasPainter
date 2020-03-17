package canvas;

import canvas.shape.CharBox;
import canvas.shape.CharLine;
import canvas.shape.CharRectangle;
import canvas.shape.ShapeFactory;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static canvas.CommandParser.getCoordinates;

public class CanvasExercise {

    private CanvasComponent canvasComponent;
    private final Map<String, Consumer<String>> shapeMapping;
    private int currentWidth;
    private int currentHeight;

    private CanvasExercise() {
        currentWidth = 0;
        currentHeight = 0;
        shapeMapping = new HashMap<String, Consumer<String>>() {{
            put("L", (command -> drawLine(command)));
            put("R", (command -> drawRectangle(command)));
            put("C", (command -> drawCanvas(command)));
        }};
        awaitCommand();
    }

    public static void main(String[] args) {
        new CanvasExercise();
    }

    private void awaitCommand() {
        Scanner scanner = new Scanner(System.in);
        String input = "";

        while (true) {
            System.out.print("Enter command: ");
            input = scanner.nextLine().trim();
            if ("q".equalsIgnoreCase(input)) {
                System.out.println("Good bye");
                System.exit(0);
            } else if (!input.isEmpty()) {
                executeCommand(input);
            }
        }

    }

    private void executeCommand(String command) {
        String firstChar = command.substring(0, 1).toUpperCase();
        Optional.ofNullable(shapeMapping.get(firstChar))
                .orElseGet(this::invalidLetterCommand)
                .accept(command);
    }

    private void drawCanvas(String command) {
        if (validCommand(() -> CommandParser.validateCanvas(command))) {
            createCanvas(command);
            CharBox box = ShapeFactory.createBox(currentWidth + 2, currentHeight + 2);
            canvasComponent.updateCanvas(box);
        }
    }

    private void createCanvas(String command) {
        java.util.List<Integer> coordinates = getCoordinates(command);
        currentWidth = coordinates.get(0);
        currentHeight = coordinates.get(1);
        int width = currentWidth + 2; // add 2 for vertical borders of the box
        int height = currentHeight + 2; // add 2 for horizontal borders of the box
        Optional.ofNullable(canvasComponent)
                .orElseGet(() -> createNewComponent(width, height))
                .drawCanvas(width, height);
    }

    private CanvasComponent createNewComponent(int width, int height) {
        CharCanvas canvasPainter = new CharCanvas(width, height);
        canvasComponent = new CanvasComponent(canvasPainter);
        return canvasComponent;
    }

    private void drawLine(String command) {
        if (canvasExists() && validCommand(() -> CommandParser.validateLine(command, currentWidth, currentHeight))) {
            CharLine charLine = ShapeFactory.createLine(command);
            canvasComponent.updateCanvas(charLine);
        }
    }

    private void drawRectangle(String command) {
        if (canvasExists() && validCommand(() -> CommandParser.validateRectangle(command, currentWidth, currentHeight))) {
            CharRectangle recPainter = ShapeFactory.createRectangle(command);
            canvasComponent.updateCanvas(recPainter);
        }
    }

    private Consumer<String> invalidLetterCommand() {
        return (command) -> System.out.println("Invalid shape letter in command: " + command);
    }

    private boolean canvasExists() {
        if (Objects.isNull(canvasComponent)) {
            System.out.println("Please enter a 'Canvas' command first: C w h");
            return false;
        }
        return true;
    }

    private boolean validCommand(Supplier<Optional<String>> result) {
        Optional<String> errorMessage = result.get();
        if (errorMessage.isPresent()) {
            System.out.println(errorMessage.get());
            return false;
        }
        return true;
    }
}
