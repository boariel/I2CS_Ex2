# I2CS_Ex2
This is my implementation of Ex2 in intro2cs course.
This assignment focused on creating a class to represent a 2d map of integer values
as a graphical interface.

## Project Structure

| File / Package | Description |
|----------------|-------------|
| `Ex2_GUI.java` | A class for representing the maps as graphical ui. |
| `Pixel2D.java` | An interface that represents an integer based coordinate of a 2D raster. |
| `Index2D.java` | An instance class of Pixel2D interface. |
| `Map2D.java` | An interface that represents a 2D map as a raster matrix, image or maze. |
| `Map.java` | An instance class of Map2D interface. |
| `*Test.java` | Unit tests for core functionality. |


## Map Class Overview

The `Map` class represents a 2D integer map (or raster) and provides various operations for manipulating and analyzing it. Below is a summary of its key functions:

### Constructors

- **`Map(int w, int h, int v)`** – Creates a `w x h` map initialized with value `v`.  
- **`Map(int size)`** – Creates a square map of size `size x size`, initialized to `0`.  
- **`Map(int[][] data)`** – Creates a map by copying an existing 2D array.

### Initialization

- **`init(int w, int h, int v)`** – Initializes the map with the specified width, height, and default value.  
- **`init(int[][] arr)`** – Initializes the map from an existing 2D array. Throws an exception if the array is null, empty, or ragged.

### Accessors

- **`getMap()`** – Returns a deep copy of the current map.  
- **`getWidth()`** – Returns the map width.  
- **`getHeight()`** – Returns the map height.  
- **`getPixel(int x, int y)`** – Returns the value at the `(x, y)` coordinate.  
- **`getPixel(Pixel2D p)`** – Returns the value at a `Pixel2D` coordinate.

### Mutators

- **`setPixel(int x, int y, int v)`** – Sets the value of the pixel at `(x, y)` to `v`.  
- **`setPixel(Pixel2D p, int v)`** – Sets the value at a `Pixel2D` coordinate to `v`.

### Utility Functions

- **`isInside(Pixel2D p)`** – Returns `true` if the coordinate `p` is inside the map bounds.  
- **`sameDimensions(Map2D p)`** – Returns `true` if the map has the same width and height as another map.  
- **`addMap2D(Map2D p)`** – Adds values from another map if dimensions match.  
- **`mul(double scalar)`** – Multiplies all map entries by a scalar.  
- **`rescale(double sx, double sy)`** – Rescales the map by given width and height factors.

### Drawing Functions

- **`drawCircle(Pixel2D center, double rad, int color)`** – Draws a circle at `center` with radius `rad` and color `color`.  
- **`drawLine(Pixel2D p1, Pixel2D p2, int color)`** – Draws a line between `p1` and `p2` using the specified color.  
- **`drawRect(Pixel2D p1, Pixel2D p2, int color)`** – Draws a rectangle defined by `p1` and `p2` corners with the given color.

### Map Comparison

- **`equals(Object ob)`** – Checks if another map is equal in both dimensions and values.

### Advanced Operations

- **`fill(Pixel2D xy, int new_v, boolean cyclic)`** – Performs a flood fill starting from `xy`, replacing connected pixels of the same color with `new_v`. Returns the number of pixels recolored.  
- **`shortestPath(Pixel2D p1, Pixel2D p2, int obsColor, boolean cyclic)`** – Finds the shortest path from `p1` to `p2` avoiding obstacles (`obsColor`) using BFS. Returns an array of pixels forming the path.  
- **`allDistance(Pixel2D start, int obsColor, boolean cyclic)`** – Creates a map where each pixel stores the length of the shortest path from `start`, considering obstacles.

## Index2D Class Overview

The `Index2D` class implements the `Pixel2D` interface and represents a 2D coordinate (x, y) with utility methods for working with positions on a map.

### Constructors

- **`Index2D(int w, int h)`** – Creates a 2D index with the specified width (`w`) and height (`h`). Throws an exception if either value is negative.  
- **`Index2D(Pixel2D other)`** – Creates a 2D index by copying another `Pixel2D` object. Throws an exception if the coordinates are negative.

### Accessors

- **`getX()`** – Returns the x-coordinate (width) of this index.  
- **`getY()`** – Returns the y-coordinate (height) of this index.

### Utility Functions

- **`distance2D(Pixel2D p2)`** – Returns the Euclidean distance between this index and another `Pixel2D`. Throws an exception if `p2` is null.  
- **`toString()`** – Returns a string representation of the index in the format `Width(x):<x> , Height(y):<y>`.  
- **`equals(Object p)`** – Returns `true` if another object is a `Pixel2D` with the same coordinates.

## Ex2_GUI Class Overview

The `Ex2_GUI` class provides a simple graphical interface for displaying, loading, and saving 2D maps (`Map2D`). It uses `StdDraw` for rendering.

### Main Functions

- **`drawMap(Map2D map)`** – Draws a `Map2D` object using grayscale colors, with `-1` represented in red. Internally uses `drawMat(int[][] mat)` for rendering.

- **`loadMap(String mapFileName)`** – Loads a text file representing a map (rows of integers separated by spaces) and returns it as a `Map2D` object. Returns `null` if the file is empty or cannot be read.

- **`saveMap(Map2D map, String mapFileName)`** – Saves a `Map2D` object to a text file, representing each row of the map as a line of space-separated integers.

- **`main(String[] a)`** – Example usage: loads a map from `"map.txt"` and draws it using `drawMap`.

### Private / Helper Functions

- **`drawMat(int[][] mat)`** – Draws a 2D integer matrix in `StdDraw`. Uses grayscale for normal values and red for `-1`. Each value is drawn as a small filled square.

- **`valueToColor(int v)`** – Converts an integer value to a color using HSB color mapping. Used by `drawMat` for coloring pixels.

## Ex2_GUI Output Example

<img width="512" height="567" alt="Screenshot 2025-12-27 171423" src="https://github.com/user-attachments/assets/fbe1787c-6e64-4e24-9a12-254a3dd081f7" />

