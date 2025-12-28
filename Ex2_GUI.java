package assignments.Ex2;


import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

/**
 * Intro2CS_2026A
 * This class represents a Graphical User Interface (GUI) for Map2D.
 * The class has save and load functions, and a GUI draw function.
 * You should implement this class, it is recommender to use the StdDraw class, as in:
 * https://introcs.cs.princeton.edu/java/stdlib/javadoc/StdDraw.html
 *
 *
 */
public class Ex2_GUI {
    /**
     * Draws a map in StdDraw.
     * The values are represented in grayscale besides -1 which is represented in red.
     * @param map the map to be shown
     */
    public static void drawMap(Map2D map) {
        drawMat(map.getMap());
    }

    /**
     * This function turns a text fileMinto a Map2D object file represented
     * as rows of values separated by space.
     * @param mapFileName the name of the file
     * @return Map2D object represented in the text file
     */
    public static Map2D loadMap(String mapFileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(mapFileName))) {
            List<int[]> rows = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.trim().split("\\s+"); // split by spaces
                int[] row = new int[tokens.length];
                for (int i = 0; i < tokens.length; i++) {
                    row[i] = Integer.parseInt(tokens[i]);
                }
                rows.add(row);
            }
            if (rows.isEmpty()) return null;
            int height = rows.size();
            int width = rows.get(0).length;
            int[][] mapArray = new int[height][width];
            for (int i = 0; i < height; i++) {
                if (rows.get(i).length != width)
                    throw new RuntimeException("Ragged map file, inconsistent row lengths");
                mapArray[i] = rows.get(i);
            }
            return new Map(mapArray);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *This function turns a Map2D object into a text file represented
     *as rows of values separated by space.
     * @param map the Map2D to turn to file
     * @param mapFileName the file name
     */
    public static void saveMap(Map2D map, String mapFileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(mapFileName))) {
            int height = map.getHeight();
            int width = map.getWidth();

            for (int y = 0; y < height; y++) {
                StringBuilder line = new StringBuilder();
                for (int x = 0; x < width; x++) {
                    line.append(map.getPixel(x, y));
                    if (x < width - 1) line.append(" "); // separate numbers with space
                }
                bw.write(line.toString());
                bw.newLine(); // end of row
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] a) {
        int[][] m = {
            {1,1,1,1,4},
            {2,2,3,7,-1},
            {1,2,34,9,-68},
            {34,56,7,9,0}
        };
        String mapFile = "map.txt";
        Map2D mp1 = new Map2D(m);
        saveMap(mp1,mapFile);
        Map2D map = loadMap(mapFile);
        drawMap(map);
    }
    /// ///////////// Private functions ///////////////
    /**
     * This function represents a matrix of int in stdDraw
     * as a gray scale. if a value is -1, it paints it red
     * @param mat the matrix to be shown
     */
    public static void drawMat(int[][] mat) {
        StdDraw.setScale(0, 1);
        StdDraw.clear();
        for(int y=0;y<mat.length;y++) {
            for(int x=0;x<mat[0].length;x++) {
                int v = mat[y][x];
                Color c = valueToColor(v);
                StdDraw.setPenColor(c);
                if (v==-1){
                    StdDraw.setPenColor(Color.red);
                }
                double r = 1/15.0;
                double x1 = 0.1+2.2*r*x;
                double y1 = 1-(0.1+2.2*r*y);
                StdDraw.filledSquare(x1,y1,r);
            }
        }
        StdDraw.show();
        StdDraw.pause(2);
    }

    /**
     * Generates a color from an integer.
     * @param v the color to transform
     * @return a color
     */
    private static Color valueToColor(int v) {
        float hue = (v * 0.61803398875f) % 1.0f;
        float saturation = 0.7f;
        float brightness = 0.9f;
        return Color.getHSBColor(hue, saturation, brightness);
    }

}
