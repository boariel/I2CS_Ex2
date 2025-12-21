package assignments.Ex2;

import java.io.IOException;

public class Index2D implements Pixel2D {
    private int width;
    private int height;

    public Index2D(int w, int h) throws RuntimeException{
        if (w<0||h<0)
            throw  new RuntimeException("The width and height have to be none negative");
        this.width=w;
        this.height=h;
    }
    public Index2D(Pixel2D other) throws RuntimeException {
        if (other.getX()<0||other.getY()<0)
            throw  new RuntimeException("The width and height have to be none negative");
        this.width= other.getX();
        this.height= other.getY();
    }
    @Override
    public int getX() {
        int x = this.width;
        return x;
    }

    @Override
    public int getY() {
        int y = this.height;
        return y;
    }

    @Override
    public double distance2D(Pixel2D p2) throws RuntimeException{
        if (p2==null)
            throw new RuntimeException("Cannot compare to null");
        int dx = p2.getX()-this.width;
        int dy = p2.getY()-this.height;
        double dis = Math.sqrt(dx*dx+dy*dy);
        return dis;
    }

    @Override
    public String toString() {
        String ans = "Width(x):"+this.width + " , Height(y):"+this.height;
        return ans;
    }

    @Override
    public boolean equals(Object p) {
        boolean ans = true;
        if (!(p instanceof Pixel2D)){
            ans = false;
        }
        else if (((Pixel2D) p).getX()!=this.width||((Pixel2D) p).getY()!=this.height) {
            ans = false;
        }
        return ans;
    }
}
