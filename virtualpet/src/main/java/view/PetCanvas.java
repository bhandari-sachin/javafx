package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.Objects;

public class PetCanvas extends Canvas {
    private final GraphicsContext gc;
    private final Image petImage;
    private static final double PET_SIZE = 64; // Fixed 64x64 size

    public PetCanvas(double width, double height) {
        super(width, height);
        this.gc = this.getGraphicsContext2D();

        // Load pet image from resources/images/pet.png
        this.petImage = new Image(
                Objects.requireNonNull(getClass().getResource("/images/pet.png")).toExternalForm()
        );
    }

    /**
     * Redraw the canvas at the specified pet position.
     * @param petX X-coordinate for the pet (top-left)
     * @param petY Y-coordinate for the pet (top-left)
     */
    public void redraw(double petX, double petY) {
        // Clear entire canvas
        gc.clearRect(0, 0, getWidth(), getHeight());

        // Optional: Draw background
        gc.setFill(Color.LIGHTBLUE); // Example background color
        gc.fillRect(0, 0, getWidth(), getHeight());

        // Draw pet at fixed 64x64 size
        gc.drawImage(petImage, petX, petY, PET_SIZE, PET_SIZE);
    }

    /** Returns the fixed width of the pet image */
    public double getPetWidth() {
        return PET_SIZE;
    }

    /** Returns the fixed height of the pet image */
    public double getPetHeight() {
        return PET_SIZE;
    }

    public double getImageWidth() {
        return PET_SIZE;
    }

    public double getImageHeight() {
        return PET_SIZE;
    }
}
