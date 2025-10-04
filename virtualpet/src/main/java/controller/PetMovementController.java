package controller;

import javafx.scene.input.MouseEvent;
import model.Pet;
import view.PetCanvas;

public class PetMovementController {
    private final Pet pet;
    private final PetCanvas canvas;
    private final double speed = 2.0; // pixels per update
    private boolean moving = false;
    private boolean needsInitialDraw = true;

    private final double canvasWidth;
    private final double canvasHeight;
    private final double imageWidth;
    private final double imageHeight;

    public PetMovementController(Pet pet, PetCanvas canvas) {
        this.pet = pet;
        this.canvas = canvas;
        this.canvasWidth = canvas.getWidth();
        this.canvasHeight = canvas.getHeight();
        this.imageWidth = canvas.getImageWidth();
        this.imageHeight = canvas.getImageHeight();

        // Setup event handlers - Controller handles all user input
        setupEventHandlers();
    }

    private void setupEventHandlers() {
        canvas.setOnMouseMoved(this::handleMouseMoved);
        canvas.setOnMouseExited(this::handleMouseExited);
    }

    private void handleMouseMoved(MouseEvent event) {
        // Calculate target position (center image on cursor)
        double targetX = event.getX() - imageWidth / 2;
        double targetY = event.getY() - imageHeight / 2;

        // Clamp target to canvas bounds (accounting for image size)
        targetX = Math.max(0, Math.min(targetX, canvasWidth - imageWidth));
        targetY = Math.max(0, Math.min(targetY, canvasHeight - imageHeight));

        // Update model
        pet.setTarget(targetX, targetY);
        moving = true;
    }

    private void handleMouseExited(MouseEvent event) {
        // Stop the pet by setting target to current position
        pet.setTarget(pet.getX(), pet.getY());
        moving = false;
    }

    public void update() {
        // Controller contains the logic for updating the pet's location
        updatePosition();

        // Only redraw if pet is actually moving (optimization)
        if (moving || needsInitialDraw) {
            canvas.redraw(pet.getX(), pet.getY());
            if (needsInitialDraw) needsInitialDraw = false;
        }
    }

    private void updatePosition() {
        if (!moving) return;

        double dx = pet.getTargetX() - pet.getX();
        double dy = pet.getTargetY() - pet.getY();
        double distance = Math.sqrt(dx * dx + dy * dy);

        if (distance < speed) {
            // Reached target
            pet.setPosition(pet.getTargetX(), pet.getTargetY());
            moving = false;
        } else {
            // Move towards target
            double newX = pet.getX() + speed * dx / distance;
            double newY = pet.getY() + speed * dy / distance;
            pet.setPosition(newX, newY);
        }
    }
}