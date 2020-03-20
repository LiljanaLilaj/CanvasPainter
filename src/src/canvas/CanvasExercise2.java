package canvas;

public class CanvasExercise2 {

    public static void main(String[] args) {
        CanvasComponent canvasComponent = new CanvasComponent();
        CanvasWorker task = new CanvasWorker(canvasComponent);
        task.run();
    }
}
