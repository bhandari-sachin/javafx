# Virtual Pet Application - Assignment Report

## Items Completed:

### ✅ Item 1: A cute pet image is displayed on the canvas
**Implementation:** The `PetCanvas` class loads a pet image from `resources/images/pet.png` using JavaFX's `Image` class. The image is displayed on the canvas using the `drawImage()` method in the `redraw()` method.

```java
this.petImage = new Image(
    Objects.requireNonNull(getClass().getResource("/images/pet.png")).toExternalForm()
);
```

### ✅ Item 2: The pet moves towards the mouse cursor when the mouse is moved over the canvas
**Implementation:** The `PetMovementController` handles the `onMouseMoved` event. When the mouse moves, the controller calculates the target position (centered on the cursor) and updates the pet's target coordinates. The pet then moves smoothly towards this target using trigonometry-based calculations in the `updatePosition()` method:

```java
double dx = pet.getTargetX() - pet.getX();
double dy = pet.getTargetY() - pet.getY();
double distance = Math.sqrt(dx * dx + dy * dy);
double newX = pet.getX() + speed * dx / distance;
double newY = pet.getY() + speed * dy / distance;
```

### ✅ Item 3: The pet stops moving when it reaches the mouse cursor 
**Implementation:** The `updatePosition()` method in the controller checks if the distance to the target is less than the speed. When this condition is met, the pet's position is set exactly to the target, and the `moving` flag is set to `false`, stopping further movement:

```java
if (distance < speed) {
    pet.setPosition(pet.getTargetX(), pet.getTargetY());
    moving = false;
}
```

### ✅ Item 4: The pet stops moving when the mouse cursor is moved outside the canvas
**Implementation:** The `PetMovementController` handles the `onMouseExited` event. When the mouse leaves the canvas, the controller sets the pet's target to its current position and sets `moving` to `false`, immediately stopping the pet:

```java
private void handleMouseExited(MouseEvent event) {
    pet.setTarget(pet.getX(), pet.getY());
    moving = false;
}
```

### ✅ Item 5: The program follows the MVC pattern
**Implementation:** The application strictly follows the MVC pattern with clear separation of responsibilities:

- **Model (`Pet.java`)**: Stores the pet's location data (`x`, `y`, `targetX`, `targetY`). Contains only getters and setters. No business logic or dependencies on other components.

- **View (`PetCanvas.java`)**: Contains the canvas and is responsible for drawing the pet. The `redraw(double petX, double petY)` method receives coordinates as parameters and draws the pet image at that position. The view has no reference to the model and no event handling logic.

- **Controller (`PetMovementController.java`)**: Contains the method for updating the pet's location (`updatePosition()`). Handles all user input events (`onMouseMoved`, `onMouseExited`). Mediates between the Model and View by reading from the Model, performing calculations, updating the Model's data, and instructing the View to redraw.


