package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Objects;

public class PetCanvas extends Canvas {
    private final GraphicsContext gc;
    private final Image petImage;
    private final double imageWidth;
    private final double imageHeight;

    public PetCanvas(double width, double height) {
        super(width, height);
        this.gc = this.getGraphicsContext2D();

        // Load pet image from resources/images/pet.png
        this.petImage = new Image(
                Objects.requireNonNull(getClass().getResource("/images/pet.png")).toExternalForm()
        );

        // Cache image dimensions
        this.imageWidth = petImage.getWidth();
        this.imageHeight = petImage.getHeight();
    }

    public void redraw(double petX, double petY) {
        // Clear entire canvas
        gc.clearRect(0, 0, getWidth(), getHeight());

        // Optional: Draw background
        // gc.setFill(Color.LIGHTBLUE);
        // gc.fillRect(0, 0, getWidth(), getHeight());

        // Draw pet image at specified position
        gc.drawImage(petImage, petX, petY);
    }

    public double getImageWidth() {
        return imageWidth;
    }

    public double getImageHeight() {
        return imageHeight;
    }
}