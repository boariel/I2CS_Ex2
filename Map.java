package assignments.Ex2;
import java.io.Serializable;
import java.util.*;

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
                map[i][j] = v;
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
                map[i][j] = arr[i][j];
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

    /**
     * This function returns the color of a pixel in the coordinates.
     * @param x the x coordinate
     * @param y the y coordinate
     * @return the value of the entry at the coordinate [x][y]
     */
	@Override
	public int getPixel(int x, int y) {
        int ans = -1;
        ans = this.map[y][x];
        return ans;
    }
    /**
     * This function returns the color of a pixel in the coordinates.
     * @param p the coordinates in Pixel2D
     * @return the value of the entry at the coordinate [x][y]
     */
	@Override
	public int getPixel(Pixel2D p) {
        int ans = -1;
        int x = p.getX();
        int y = p.getY();
        ans = this.map[y][x];
        return ans;
	}

    /**
     * This function changes the color of a pixel in the coordinates.
     * @param x the x coordinate
     * @param y the y coordinate
     * @param v the value that the entry at the coordinate [x][y] is set to.
     */
	@Override
	public void setPixel(int x, int y, int v) {
        this.map[y][x] = v;
    }

    /**
     * This function changes the color of a pixel in the coordinates.
     * @param p the coordinate in the map.
     * @param v the value that the entry at the coordinate [p.x][p.y] is set to.
     */
	@Override
	public void setPixel(Pixel2D p, int v) {
        int x = p.getX();
        int y = p.getY();
        this.map[y][x] = v;
	}

    /**
     * This function returns true if the coordinates are in it and false otherwise.
     * @param p the 2D coordinate.
     * @return is p in the map
     */
    @Override
    public boolean isInside(Pixel2D p) {
        boolean ans = true;
        int x = p.getX();
        int y = p.getY();
        if (x>=this.getWidth())
            ans = false;
        if (y>=this.getHeight())
            ans = false;
        return ans;
    }

    /**
     * This function checks if two maps have the same dimensions.
     * @param p compared map
     * @return the maps are same dimensions
     */
    @Override
    public boolean sameDimensions(Map2D p) {
        boolean ans = false;
        if (p.getWidth()==this.getWidth()&&p.getHeight()==this.getHeight())
            ans = true;
        return ans;
    }

    /**
     * This function add the values of a different map to this map only if they have the same dimensions.
     * @param p - the map that should be added to this map (just in case they have the same dimensions).
     */
    @Override
    public void addMap2D(Map2D p) {
        if (this.sameDimensions(p)){
            for (int i = 0; i < this.getWidth(); i++) {
                for (int j = 0; j < this.getHeight(); j++) {
                    this.map[j][i] += p.getPixel(i,j);
                }
            }
        }
    }

    /**
     * This functions multiplies all of its values with the given number.
     * @param scalar the number being multiplied
     */
    @Override
    public void mul(double scalar) {
        int sca = (int)scalar;
        for (int i = 0; i < this.getWidth(); i++) {
            for (int j = 0; j < this.getHeight(); j++) {
                this.map[j][i] *=sca;
            }
        }
    }

    /**
     * This functions rescales the image by x,y times
     * @param sx the width rescale
     * @param sy the y rescale
     */
    @Override
    public void rescale(double sx, double sy) {
        int newW = (int)Math.round(getWidth() * sx);
        int newH = (int)Math.round(getHeight() * sy);
        int[][] newMap = new int[newH][newW];
        for (int y = 0; y < newH; y++) {
            for (int x = 0; x < newW; x++) {
                int srcX = (int) (x/sx);
                int srcY = (int) (y/sy);
                srcX = Math.min(srcX,getWidth() - 1);
                srcY = Math.min(srcY,getHeight() - 1);
                newMap[y][x] = map[srcY][srcX];
            }
        }
        map = newMap;
    }

    /**
     * This function draws a circle in the given area
     * @param center the center of the circle
     * @param rad the radius of the circle
     * @param color - the (new) color to be used in the drawing.
     */
    @Override
    public void drawCircle(Pixel2D center, double rad, int color) {
        for (int i = 0; i < this.getWidth(); i++) {
            for (int j = 0; j < this.getHeight(); j++) {
                Index2D cp = new Index2D(i,j);
                if (center.distance2D(cp)<rad){
                    this.setPixel(cp,color);
                }
            }
        }
    }

    /**
     * This function draws a line between two coordinats.
     * @param p1 the first coordinate
     * @param p2 the second coordinate
     * @param color - the (new) color to be used in the drawing.
     */
    @Override
    public void drawLine(Pixel2D p1, Pixel2D p2, int color) {
        double dx = Math.abs(p2.getX()-p1.getX());
        double dy = Math.abs(p2.getY()-p1.getY());
        double m;
        if (dx!=0) {
            m = dy/dx;
            double b = p1.getY()-(m*p1.getX());
            if (p1.equals(p2)){
                this.setPixel(p1,color);
            }
            else if(dx>=dy&&p1.getX()<p2.getX()){
                for (int i = 0; i <= (int)dx; i++) {
                    Index2D cp = new Index2D(p1.getX()+i, (int)Math.round((m*(p1.getX()+i))+b));
                    this.setPixel(cp,color);
                }
            }
            else if (dx>=dy&&p1.getX()>p2.getX()) {
                this.drawLine(p2,p1,color);
            }
            else if(dx<dy && p1.getY()<p2.getY()) {
                for (int i = 0; i <= (int)dy; i++) {
                    Index2D cp = new Index2D(p1.getY()+i, (int)Math.round((m*(p1.getY()+i))+b));
                    this.setPixel(cp,color);
                }
            }
            else if(dy>dx && p1.getY()>p2.getY()) {
                this.drawLine(p2,p1,color);
            }
        }
        else {
            for (int y = Math.min(p1.getY(),p2.getY()); y <=Math.max(p1.getY(),p2.getY()) ; y++) {
                this.map[y][p1.getX()] = color;
            }
        }
    }

    /**
     * This function draws a rectangle between two coordinats.
     * @param p1 the first coordinate
     * @param p2 the second coordinate
     * @param color - the (new) color to be used in the drawing.
     */
    @Override
    public void drawRect(Pixel2D p1, Pixel2D p2, int color) {
        int xMin = Math.min(p1.getX(), p2.getX());
        int xMax = Math.max(p1.getX(), p2.getX());
        int yMin = Math.min(p1.getY(), p2.getY());
        int yMax = Math.max(p1.getY(), p2.getY());
        for (int y = yMin; y <= yMax; y++) {
            for (int x = xMin; x <= xMax; x++) {
                setPixel(x, y, color);
            }
        }
    }

    /**
     * This functions returns true if this map and another one have the same dimensions
     * and values in every index.
     * @param ob the reference object with which to compare.
     * @return if the maps are equal
     */
    @Override
    public boolean equals(Object ob) {
        boolean ans = true;
        if (!(ob instanceof Map2D))
            ans = false;
        else if(!this.sameDimensions((Map2D) ob))
            ans = false;
        else{
            for (int i = 0; i < this.getWidth(); i++) {
                for (int j = 0; j < this.getHeight(); j++) {
                    if (this.getPixel(i,j)!=((Map2D)ob).getPixel(i,j))
                        ans = false;
                }
            }
        }
        return ans;
    }

    /**
     * This function fill a new color in a group of pixels of the same color
     * starting from a given pixel xy.
     * The function does this by using flood fills presented in:
     * https://en.wikipedia.org/wiki/Flood_fill
     * @param xy the pixel to start from.
     * @param new_v - the new "color" to be filled in p's connected component.
     * @param cyclic if the map is cyclic
     * @return amount of pixels recolored
     */
    @Override
    public int fill(Pixel2D xy, int new_v, boolean cyclic) {
        int oldColor = getPixel(xy);
        if (oldColor == new_v) return 0;
        int count = 0;
        boolean[][] visited = new boolean[getHeight()][getWidth()];
        ArrayDeque<Pixel2D> q = new ArrayDeque<>();
        q.add(xy);
        visited[xy.getY()][xy.getX()] = true;
        setPixel(xy, new_v);
        count++;
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        while (!q.isEmpty()) {
            Pixel2D p = q.poll();
            int x = p.getX();
            int y = p.getY();
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (cyclic) {
                    nx = (nx + getWidth()) % getWidth();
                    ny = (ny + getHeight()) % getHeight();
                }
                if (nx < 0 || ny < 0 || nx >= getWidth() || ny >= getHeight())
                    continue;
                if (!visited[ny][nx] && getPixel(nx, ny) == oldColor) {
                    visited[ny][nx] = true;
                    setPixel(nx, ny, new_v);
                    q.add(new Index2D(nx, ny));
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * This function returns the shortest path between two coordinates.
     * The function finds the shortest path by using BDF algorithm as presented in:
     * https://en.wikipedia.org/wiki/Breadth-first_search
     * @param p1 first coordinate (start point).
     * @param p2 second coordinate (end point).
     * @param obsColor the color which is addressed as an obstacle.
     * @param cyclic is the map cyclic
     * @return the shortest path as array of pixels
     */
    @Override
    public Pixel2D[] shortestPath(Pixel2D p1, Pixel2D p2, int obsColor, boolean cyclic) {
        if (p1.equals(p2)) return new Pixel2D[]{p1};
        boolean[][] visited = new boolean[getHeight()][getWidth()];
        Pixel2D[][] parent = new Pixel2D[getHeight()][getWidth()];
        ArrayDeque<Pixel2D> q = new ArrayDeque<>();
        visited[p1.getY()][p1.getX()] = true;
        q.add(p1);
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        while (!q.isEmpty()) {
            Pixel2D v = q.poll();
            int x = v.getX();
            int y = v.getY();
            if (v.equals(p2))
                break;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (cyclic) {
                    nx = (nx + getWidth()) % getWidth();
                    ny = (ny + getHeight()) % getHeight();
                }
                if (nx < 0 || nx >= getWidth() || ny < 0 || ny >= getHeight())
                    continue;
                if (visited[ny][nx])
                    continue;
                if (getPixel(nx, ny) == obsColor)
                    continue;
                visited[ny][nx] = true;
                parent[ny][nx] = v;
                q.add(new Index2D(nx, ny));
            }
        }
        if (!visited[p2.getY()][p2.getX()]) return null;
        LinkedList<Pixel2D> path = new LinkedList<>();
        Pixel2D cur = p2;
        while (!cur.equals(p1)) {
            path.addFirst(cur);
            cur = parent[cur.getY()][cur.getX()];
        }
        path.addFirst(p1);
        return path.toArray(new Pixel2D[0]);
    }


    /**
     * This function return a map that represents the length the shortest path from every
     * index to a starting coordinate.
     * @param start the source (starting) point
     * @param obsColor the color representing obstacles
     * @param cyclic if the map is cyclic
     * @return the map with the shortest path length as values
     */
    @Override
    public Map2D allDistance(Pixel2D start, int obsColor, boolean cyclic) {
        Map2D ans = null;  // the result.
        ans = new Map(this.getWidth(),getHeight(),0);
        for (int i = 0; i < this.getWidth(); i++) {
            for (int j = 0; j < this.getHeight(); j++) {
                Pixel2D[] path = this.shortestPath(start, new Index2D(i,j),obsColor,cyclic);
                int len = -1;
                if (path!=null)
                    len = path.length-1;
                ans.setPixel(i,j,len);
            }
        }
        return ans;
    }
	////////////////////// Private Methods ///////////////////////
}
