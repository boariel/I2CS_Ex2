package assignments.Ex2;
import java.io.Serializable;
/**
 * This class represents a 2D map (int[w][h]) as a "screen" or a raster matrix or maze over integers.
 * This is the main class needed to be implemented.
 *
 * @author boaz.benmoshe
 *
 */
public class Map implements Map2D, Serializable{

    // edit this class below
    private int[][] map;
	/**
	 * Constructs a w*h 2D raster map with an init value v.
	 * @param w the width of the map.
	 * @param h the height of map.
	 * @param v the init value of all the entries in the map.
	 */
	public Map(int w, int h, int v) {init(w, h, v);}

	/**
	 * Constructs a square map (size*size).
	 * @param size width and height of map
	 */
	public Map(int size) {this(size,size, 0);}
	
	/**
	 * Constructs a map from a given 2D array.
	 * @param data copied array
	 */
	public Map(int[][] data) {
		init(data);
	}

	@Override
	public void init(int w, int h, int v) {
        map = new int[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                map[h][w] = v;
            }
        }
	}
	@Override
	public void init(int[][] arr) throws RuntimeException{
        if (arr==null)
            throw new RuntimeException("Cannot copy null array.");
        if (arr.length==0)
            throw new RuntimeException("The array cannot be empty.");
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length!=arr[0].length)
                throw new RuntimeException("The cannot be ragged.");
        }
        int h = arr.length;
        int w = arr[0].length;
        map = new int[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                map[h][w] = arr[h][w];
            }
        }

	}
	@Override
	public int[][] getMap() {
		int[][] ans = null;
        ans = new int[this.map.length][this.map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < this.map[0].length; j++) {
                ans[i][j] = this.map[i][j];
            }
        }
		return ans;
	}
	@Override
	public int getWidth() {
        int ans = -1;
        ans = this.map[0].length;
        return ans;
    }
	@Override
	public int getHeight() {
        int ans = -1;
        ans = this.map.length;
        return ans;
    }
	@Override
	public int getPixel(int x, int y) {
        int ans = -1;
        ans = this.map[x][y];
        return ans;
    }
	@Override
	public int getPixel(Pixel2D p) {
        int ans = -1;

        return ans;
	}
	@Override
	public void setPixel(int x, int y, int v) {

    }
	@Override
	public void setPixel(Pixel2D p, int v) {

	}

    @Override
    public boolean isInside(Pixel2D p) {
        boolean ans = true;

        return ans;
    }

    @Override
    public boolean sameDimensions(Map2D p) {
        boolean ans = false;

        return ans;
    }

    @Override
    public void addMap2D(Map2D p) {

    }

    @Override
    public void mul(double scalar) {

    }

    @Override
    public void rescale(double sx, double sy) {

    }

    @Override
    public void drawCircle(Pixel2D center, double rad, int color) {

    }

    @Override
    public void drawLine(Pixel2D p1, Pixel2D p2, int color) {

    }

    @Override
    public void drawRect(Pixel2D p1, Pixel2D p2, int color) {

    }

    @Override
    public boolean equals(Object ob) {
        boolean ans = false;

        return ans;
    }
	@Override
	/** 
	 * Fills this map with the new color (new_v) starting from p.
	 * https://en.wikipedia.org/wiki/Flood_fill
	 */
	public int fill(Pixel2D xy, int new_v,  boolean cyclic) {
		int ans = -1;

		return ans;
	}

	@Override
	/**
	 * BFS like shortest the computation based on iterative raster implementation of BFS, see:
	 * https://en.wikipedia.org/wiki/Breadth-first_search
	 */
	public Pixel2D[] shortestPath(Pixel2D p1, Pixel2D p2, int obsColor, boolean cyclic) {
		Pixel2D[] ans = null;  // the result.

		return ans;
	}
    @Override
    public Map2D allDistance(Pixel2D start, int obsColor, boolean cyclic) {
        Map2D ans = null;  // the result.

        return ans;
    }
	////////////////////// Private Methods ///////////////////////

}
