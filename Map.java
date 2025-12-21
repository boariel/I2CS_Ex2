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
        int x = p.getX();
        int y = p.getY();
        ans = this.map[x][y];
        return ans;
	}
	@Override
	public void setPixel(int x, int y, int v) {
        this.map[x][y] = v;
    }
	@Override
	public void setPixel(Pixel2D p, int v) {
        int x = p.getX();
        int y = p.getY();
        this.map[x][y] = v;
	}

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

    @Override
    public boolean sameDimensions(Map2D p) {
        boolean ans = false;
        if (p.getWidth()==this.map[0].length&&p.getHeight()==this.map.length)
            ans = true;
        return ans;
    }

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

    @Override
    public void mul(double scalar) {
        int sca = (int)scalar;
        for (int i = 0; i < this.getWidth(); i++) {
            for (int j = 0; j < this.getHeight(); j++) {
                this.map[i][j] *=sca;
            }
        }
    }

    @Override
    public void rescale(double sx, double sy) {
        int[][] nm = new int[(int)(sy*this.getHeight())][(int)(sx*this.getWidth())];
        for (int i = 0; i < nm.length; i++) {
            for (int j = 0; j < nm[0].length; j++) {
                nm[j][i] = this.getPixel((int)(sx*j),(int)(sy*i));
            }
        }
    }

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
            for (int i = 0; i <= (int)dx; i++) {
                Index2D cp = new Index2D(p1.getX()+i, (int)Math.round((m*(p1.getX()+i))+b));
                this.setPixel(cp,color);
            }
        }
        else if(dx<dy && p1.getY()<p2.getY()) {
            for (int i = 0; i <= (int)dy; i++) {
                Index2D cp = new Index2D(p1.getY()+i, (int)Math.round((m*(p1.getY()+i))+b));
                this.setPixel(cp,color);
            }
        }
        else if(dy>dx && p1.getY()>p2.getY()) {
            for (int i = 0; i <= (int)dx; i++) {
                Index2D cp = new Index2D(p1.getX()+i, (int)Math.round((m*(p1.getX()+i))+b));
                this.setPixel(cp,color);
            }
        }
    }


    @Override
    public void drawRect(Pixel2D p1, Pixel2D p2, int color) {
        for (int i = p1.getX(); i < p2.getX(); i++) {
            for (int j = p1.getY(); j < p2.getY(); j++) {
                Index2D cp = new Index2D(i,j);
                this.setPixel(cp,color);
            }
        }
    }

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
	@Override
	/** 
	 * Fills this map with the new color (new_v) starting from p.
	 * https://en.wikipedia.org/wiki/Flood_fill
	 */
	public int fill(Pixel2D xy, int new_v,  boolean cyclic) {
		int ans = -1;
        int oc = this.getPixel(xy);
        this.setPixel(xy,new_v);
        ans = fill(xy,oc,new_v,1);
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

    /**
     * Recursive fill algorithm
     * @param p pixel to fill around
     * @param oc original color replaced
     * @param nc new color to fill
     * @param counter amount of times swapped to this point
     * @return amount of times swapped
     */
    private int fill(Pixel2D p,int oc,int nc,int counter) {
        if (p.getX() < this.getWidth()) {
            if (this.getPixel(p.getX() + 1, p.getY()) == oc) {
                this.setPixel(p.getX() + 1, p.getY(), nc);
                Index2D np = new Index2D(p.getX() + 1, p.getY());
                return this.fill(np, oc, nc,counter+1);
            }
        }
        if (p.getX() > 0) {
            if (this.getPixel(p.getX() - 1, p.getY()) == oc) {
                this.setPixel(p.getX() - 1, p.getY(), nc);
                Index2D np = new Index2D(p.getX() - 1, p.getY());
                return this.fill(np, oc, nc,counter+1);
            }
        }
        if (p.getY() < this.getHeight()) {
            if (this.getPixel(p.getX(), p.getY() + 1) == oc) {
                this.setPixel(p.getX(), p.getY() + 1, nc);
                Index2D np = new Index2D(p.getX(), p.getY() + 1);
                return this.fill(np, oc, nc,counter+1);
            }
        }
        if (p.getY() > 0) {
            if (this.getPixel(p.getX(), p.getY() - 1) == oc) {
                this.setPixel(p.getX(), p.getY() - 1, nc);
                Index2D np = new Index2D(p.getX(), p.getY() - 1);
                return this.fill(np, oc, nc,counter+1);
            }
        }
        return counter;
    }

}
