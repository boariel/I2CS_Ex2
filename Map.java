package assignments.Ex2;
import java.io.Serializable;
import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;

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

    /**
     * This function returns the color of a pixel in the coordinates.
     * @param x the x coordinate
     * @param y the y coordinate
     * @return the value of the entry at the coordinate [x][y]
     */
	@Override
	public int getPixel(int x, int y) {
        int ans = -1;
        ans = this.map[x][y];
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
        ans = this.map[x][y];
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
        this.map[x][y] = v;
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
        this.map[x][y] = v;
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
        if (x>=this.map.length)
            ans = false;
        if (y>=this.map[0].length)
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
        if (p.getWidth()==this.map[0].length&&p.getHeight()==this.map.length)
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
                    this.map[i][j] += p.getPixel(i,j);
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
                this.map[i][j] *=sca;
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
        int[][] nm = new int[(int)(sy*this.getHeight())][(int)(sx*this.getWidth())];
        for (int i = 0; i < nm.length; i++) {
            for (int j = 0; j < nm[0].length; j++) {
                nm[j][i] = this.getPixel((int)(sx*j),(int)(sy*i));
            }
        }
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
        double m = (p2.getY()-p1.getX())/(p2.getY()-p1.getY());
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

    /**
     * This function draws a rectangle between two coordinats.
     * @param p1 the first coordinate
     * @param p2 the second coordinate
     * @param color - the (new) color to be used in the drawing.
     */
    @Override
    public void drawRect(Pixel2D p1, Pixel2D p2, int color) {
        if (p1.getX()> p2.getX()) {
            for (int i = p1.getX(); i > p2.getX(); i--) {
                if (p1.getY()>p2.getY()) {
                    for (int j = p1.getY(); j > p2.getY(); j--) {
                        Index2D cp = new Index2D(i, j);
                        this.setPixel(cp, color);
                    }
                }
                else{
                    for (int j = p1.getY(); j < p2.getY(); j++) {
                        Index2D cp = new Index2D(i, j);
                        this.setPixel(cp, color);
                    }
                }
            }
        }
        else {
            for (int i = p1.getX(); i < p2.getX(); i++) {
                if (p1.getY()>p2.getY()) {
                    for (int j = p1.getY(); j > p2.getY(); j--) {
                        Index2D cp = new Index2D(i, j);
                        this.setPixel(cp, color);
                    }
                }
                else{
                    for (int j = p1.getY(); j < p2.getY(); j++) {
                        Index2D cp = new Index2D(i, j);
                        this.setPixel(cp, color);
                    }
                }
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
                    if (this.getPixel(i,j)!=((Map2D) ob).getPixel(i,j))
                        ans = false;
                }
            }
        }
        return ans;
    }

    /**
     * This function fill a new color in a group of pixels of the same color
     * starting from a given pixel xy.
     * The function does this by checking every recolored pixel in all directions and
     * if it finds another pixel of the same color as the original one it recolors it too.
     * @param xy the pixel to start from.
     * @param new_v - the new "color" to be filled in p's connected component.
     * @param cyclic if the map is cyclic
     * @return amount of pixels recolored
     */
	@Override
	public int fill(Pixel2D xy, int new_v,  boolean cyclic) {
		int ans = -1;
        int oc = this.getPixel(xy);
        if (this.getPixel(xy)!=new_v){
            this.setPixel(xy,new_v);
            ans = fill(xy,oc,new_v,1,cyclic);
        }
        else
            ans =0;
		return ans;
	}

    /**
     * This function returns the shortest path between two coordinates.
     * The function finds the shortest path by marking every pixel it gets to that isnt the answer
     * as an obstacle then checks the ones around it. For every pixel it keeps the path up to it from the end to every side
     * and at the end it returns the shortest of them.
     * @param p1 first coordinate (start point).
     * @param p2 second coordinate (end point).
     * @param obsColor the color which is addressed as an obstacle.
     * @param cyclic is the map cyclic
     * @return the shortest path as array of pixels
     */
	@Override
	public Pixel2D[] shortestPath(Pixel2D p1, Pixel2D p2, int obsColor, boolean cyclic) {
		Pixel2D[] ans = null;  // the result.
        Map fill = new Map(this.map);
        ans = (Pixel2D[]) shortestPatheRec(p1,p2,obsColor,cyclic,new ArrayList<Pixel2D>(), fill).toArray();
		return ans;
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

    /**
     * Recursive fill algorithm
     * @param p pixel to fill around
     * @param oc original color replaced
     * @param nc new color to fill
     * @param counter amount of times swapped to this point
     * @param cyc if the
     * @return amount of times swapped
     */
    private int fill(Pixel2D p,int oc,int nc,int counter, boolean cyc) {
        if (p.getX() < this.getWidth()&&p.getX()>=0) {
            if (this.getPixel(p.getX() + 1, p.getY()) == oc) {
                this.setPixel(p.getX() + 1, p.getY(), nc);
                Index2D np = new Index2D(p.getX() + 1, p.getY());
                return this.fill(np, oc, nc,counter+1,cyc);
            }
        }
        else if (p.getX()>=this.getWidth()&&cyc) {
            if (this.getPixel(0, p.getY()) == oc) {
                this.setPixel(0, p.getY(), nc);
                Index2D np = new Index2D(0, p.getY());
                return this.fill(np, oc, nc,counter+1,cyc);
            }
        }
        if (p.getX() > 0) {
            if (this.getPixel(p.getX() - 1, p.getY()) == oc) {
                this.setPixel(p.getX() - 1, p.getY(), nc);
                Index2D np = new Index2D(p.getX() - 1, p.getY());
                return this.fill(np, oc, nc,counter+1,cyc);
            }
        }
        else if (p.getX()<=0&&cyc) {
            if (this.getPixel(this.getWidth()-1, p.getY()) == oc) {
                this.setPixel(this.getWidth()-1, p.getY(), nc);
                Index2D np = new Index2D(this.getWidth()-1, p.getY());
                return this.fill(np, oc, nc,counter+1,cyc);
            }
        }
        if (p.getY() < this.getHeight()&&p.getY()>=0) {
            if (this.getPixel(p.getX(), p.getY() + 1) == oc) {
                this.setPixel(p.getX(), p.getY() + 1, nc);
                Index2D np = new Index2D(p.getX(), p.getY() + 1);
                return this.fill(np, oc, nc,counter+1,cyc);
            }
        }
        else if (p.getY()>=this.getHeight()&&cyc) {
            if (this.getPixel(p.getX(),0) == oc) {
                this.setPixel(p.getX(), 0, nc);
                Index2D np = new Index2D(p.getX(), 0);
                return this.fill(np, oc, nc,counter+1,cyc);
            }
        }
        if (p.getY() > 0) {
            if (this.getPixel(p.getX(), p.getY() - 1) == oc) {
                this.setPixel(p.getX(), p.getY() - 1, nc);
                Index2D np = new Index2D(p.getX(), p.getY() - 1);
                return this.fill(np, oc, nc,counter+1,cyc);
            }
        }
        else if (p.getY()<=0&&cyc) {
            if (this.getPixel(p.getX(),this.getHeight()-1) == oc) {
                this.setPixel(p.getX(), this.getHeight()-1, nc);
                Index2D np = new Index2D(p.getX(), this.getHeight()-1);
                return this.fill(np, oc, nc,counter+1,cyc);
            }
        }
        return counter;
    }

    /**
     * Recursive shortest path algorithm
     * @param p1 current pixel
     * @param p2 goal pixel
     * @param obsColor color of obsticle
     * @param cyc is the map cyclic
     * @param path the path followed
     * @param filled a map that keeps the path
     * @return the shortest path
     */
    private ArrayList<Pixel2D> shortestPatheRec(Pixel2D p1, Pixel2D p2, int obsColor, boolean cyc, ArrayList<Pixel2D> path, Map filled){
        path.add(p1);
        if (p1.equals(p2))
            return path;
        filled.setPixel(p1,obsColor);
        ArrayList<Pixel2D> up=null;
        ArrayList<Pixel2D> down=null;
        ArrayList<Pixel2D> left=null;
        ArrayList<Pixel2D> right=null;
        if (p1.getX() < filled.getWidth()&&p1.getX()>=0) {
            if (filled.getPixel(p1.getX() + 1, p1.getY()) != obsColor) {
                Index2D np = new Index2D(p1.getX() + 1, p1.getY());
                right = shortestPatheRec(np,p2,obsColor,cyc,path, filled);
            }
        }
        else if (p1.getX()>=filled.getWidth()&&cyc) {
            if (filled.getPixel(0, p1.getY()) !=obsColor) {
                Index2D np = new Index2D(0, p1.getY());
                right = shortestPatheRec(np,p2,obsColor,cyc,path, filled);
            }
        }
        if (p1.getX() > 0) {
            if (filled.getPixel(p1.getX() - 1, p1.getY()) != obsColor) {
                Index2D np = new Index2D(p1.getX() - 1, p1.getY());
                left = shortestPatheRec(np,p2,obsColor,cyc,path, filled);
            }
        }
        else if (p1.getX()<=0&&cyc) {
            if (filled.getPixel(filled.getWidth()-1, p1.getY()) != obsColor) {
                Index2D np = new Index2D(filled.getWidth()-1, p1.getY());
                left = shortestPatheRec(np,p2,obsColor,cyc,path, filled);
            }
        }
        if (p1.getY() < filled.getHeight()&&p1.getY()>=0) {
            if (filled.getPixel(p1.getX(), p1.getY() + 1) != obsColor) {
                Index2D np = new Index2D(p1.getX(), p1.getY() + 1);
                up = shortestPatheRec(np,p2,obsColor,cyc,path, filled);
            }
        }
        else if (p1.getY()>=filled.getHeight()&&cyc) {
            if (filled.getPixel(p1.getX(),0) != obsColor) {
                Index2D np = new Index2D(p1.getX(), 0);
                up = shortestPatheRec(np,p2,obsColor,cyc,path, filled);
            }
        }
        if (p1.getY() > 0) {
            if (filled.getPixel(p1.getX(), p1.getY() - 1) != obsColor) {
                Index2D np = new Index2D(p1.getX(), p1.getY() - 1);
                down = shortestPatheRec(np,p2,obsColor,cyc,path, filled);
            }
        }
        else if (p1.getY()<=0&&cyc) {
            if (filled.getPixel(p1.getX(),filled.getHeight()-1) !=obsColor) {
                Index2D np = new Index2D(p1.getX(), filled.getHeight()-1);
                down = shortestPatheRec(np,p2,obsColor,cyc,path, filled);
            }
        }
        int dl=down.size();
        int ll= left.size();
        int rl=right.size();
        ArrayList<Pixel2D> min = up;
        if (dl<min.size()&&down!=null)
            min = down;
        else if (min==null) {
            min = down;
        }
        if (ll< min.size()&&left!=null)
            min = left;
        else if (min==null) {
            min = left;
        }
        if (rl< min.size()&&right!=null)
            min = right;
        else if (min==null) {
            min = right;
        }
        return min;
    }
}
