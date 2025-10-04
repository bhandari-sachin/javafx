import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.Pet;
import view.PetCanvas;
import controller.PetMovementController;

public class Main extends Application {

    private AnimationTimer timer;

    @Override
    public void start(Stage primaryStage) {
        double canvasWidth = 600;
        double canvasHeight = 400;

        // Model - only stores data
        Pet pet = new Pet(canvasWidth / 2, canvasHeight / 2);

        // View - only responsible for drawing
        PetCanvas canvas = new PetCanvas(canvasWidth, canvasHeight);

        // Controller - handles events and contains update logic
        PetMovementController controller = new PetMovementController(pet, canvas);

        // Wrap canvas in a layout pane
        StackPane root = new StackPane(canvas);

        // Create the scene
        Scene scene = new Scene(root, canvasWidth, canvasHeight);
        primaryStage.setTitle("Virtual Pet");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Animation loop for smooth pet movement
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                controller.update();
            }
        };
        timer.start();

        // Cleanup on window close
        primaryStage.setOnCloseRequest(event -> {
            if (timer != null) {
                timer.stop();
            }
        });
    }

    @Override
    public void stop() {
        if (timer != null) {
            timer.stop();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}