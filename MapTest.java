package assignments.Ex2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Intro2CS, 2026A, this is a very
 */
class MapTest {
    /**
     */
    private int[][] _map_3_3 = {{0,1,0}, {1,0,1}, {0,1,0}};
    private Map2D _m0, _m1, _m3_3;
    @BeforeEach
    public void setuo() {
        _m3_3 = new Map(_map_3_3);
    }
    @Test
    @Timeout(value = 1, unit = SECONDS)
    void init() {
        int[][] bigarr = new int [500][500];
        _m1 = new Map(bigarr);
        assertEquals(bigarr.length, _m1.getWidth());
        assertEquals(bigarr[0].length, _m1.getHeight());
        Pixel2D p1 = new Index2D(3,2);
        _m1.fill(p1,1, true);
    }

    @Test
    void testInit() {
        _m0=new Map(_map_3_3);
        _m1=new Map(_map_3_3);
        assertEquals(_m0, _m1);
    }
    @Test
    void testEquals() {
        _m0=new Map(_map_3_3);
        _m1=new Map(_map_3_3);
        assertEquals(_m0,_m1);
    }
    @Test
    void testGetMap(){
        _m0 = new Map(_map_3_3);
        int[][] ac = _m0.getMap();
        assertArrayEquals(_map_3_3,ac);
    }
    @Test
    void testWidth(){
        _m0 = new Map(_map_3_3);
        assertEquals(3,_m0.getWidth());
    }
    @Test
    void testHeight(){
        _m0 = new Map(_map_3_3);
        assertEquals(3,_m0.getHeight());
    }
    @Test
    void testGetPixel(){
        _m0 = new Map(_map_3_3);
        int ac = _m0.getPixel(0,0);
        Index2D p1 = new Index2D(0,0);
        assertEquals(0,ac);
        ac = _m0.getPixel(p1);
        assertEquals(0,ac);
    }
    @Test
    void testSetPixel(){
        _m0 = new Map(_map_3_3);
        _m0.setPixel(0,0,4);
        Index2D p1 = new Index2D(0,0);
        int ac = _m0.getPixel(0,0);
        assertEquals(4,ac);
        _m0.setPixel(p1,3);
        ac = _m0.getPixel(p1);
        assertEquals(3,ac);
    }
    @Test
    void testIsInside(){
        _m0 = new Map(_map_3_3);
        Index2D p1 = new Index2D(2,4);
        Index2D p2 = new Index2D(4,2);
        Index2D p3 = new Index2D(2,2);
        assertFalse(_m0.isInside(p1));
        assertFalse(_m0.isInside(p2));
        assertTrue(_m0.isInside(p3));
    }
    @Test
    void testSameDimensions(){
        Map m1 = new Map(2);
        Map m2 = new Map(2);
        Map m3 = new Map(3);
        assertTrue(m1.sameDimensions(m2));
        assertFalse(m1.sameDimensions(m3));
    }
    @Test
    void testAddMap2D(){
        Map m1 = new Map(_map_3_3);
        Map m2 = new Map(_map_3_3);
        m1.addMap2D(m2);
        int[][] m3a = {{0,2,0}, {2,0,2}, {0,2,0}};
        Map m3 = new Map(m3a);
        assertEquals(m1,m3);
    }
    @Test
    void testMul(){
        Map m1 = new Map(_map_3_3);
        m1.mul(2);
        int[][] m3a = {{0,2,0}, {2,0,2}, {0,2,0}};
        Map m3 = new Map(m3a);
        assertEquals(m1,m3);
    }
    @Test
    public void testRescaleUp() {
        int[][] data = {
                {1, 2},
                {3, 4}
        };
        Map map = new Map(data);
        map.rescale(2.0, 2.0);

        int[][] expected = {
                {1, 1, 2, 2},
                {1, 1, 2, 2},
                {3, 3, 4, 4},
                {3, 3, 4, 4}
        };
        assertEquals(4, map.getWidth());
        assertEquals(4, map.getHeight());
        for (int y = 0; y < expected.length; y++) {
            for (int x = 0; x < expected[0].length; x++) {
                assertEquals(expected[y][x], map.getPixel(x, y));
            }
        }
    }
    @Test
    public void testRescaleDown() {
        int[][] data = {
                {1, 1, 2, 2},
                {1, 1, 2, 2},
                {3, 3, 4, 4},
                {3, 3, 4, 4}
        };
        Map map = new Map(data);
        map.rescale(0.5, 0.5);
        assertEquals(2, map.getWidth());
        assertEquals(2, map.getHeight());
        assertEquals(1, map.getPixel(0, 0));
        assertEquals(2, map.getPixel(1, 0));
        assertEquals(3, map.getPixel(0, 1));
        assertEquals(4, map.getPixel(1, 1));
    }
    @Test
    void testDrawCircle() {
        Map map = new Map(5, 5, 0);
        Pixel2D center = new Index2D(2, 2);
        map.drawCircle(center, 2, 1);
        assertEquals(1, map.getPixel(center));
        assertEquals(1, map.getPixel(1, 2));
        assertEquals(1, map.getPixel(2, 1));
        assertEquals(1, map.getPixel(3, 2));
        assertEquals(1, map.getPixel(2, 3));
        assertEquals(0, map.getPixel(0, 0));
        assertEquals(0, map.getPixel(4, 4));
    }
    @Test
    void testDrawRect() {
        Map map = new Map(4, 4, 0);
        map.drawRect(new Index2D(1, 1), new Index2D(2, 2), 5);
        assertEquals(5, map.getPixel(1, 1));
        assertEquals(5, map.getPixel(2, 1));
        assertEquals(5, map.getPixel(1, 2));
        assertEquals(5, map.getPixel(2, 2));
        assertEquals(0, map.getPixel(0, 0));
        assertEquals(0, map.getPixel(3, 3));
    }
    @Test
    void testDrawLineHorizontal() {
        Map map = new Map(5, 5, 0);
        map.drawLine(new Index2D(0, 2), new Index2D(4, 2), 9);

        for (int x = 0; x < 5; x++) {
            assertEquals(9, map.getPixel(x, 2));
        }
        assertEquals(0, map.getPixel(0, 0));
    }
    @Test
    void testDrawLineVertical() {
        Map map = new Map(5, 5, 0);
        map.drawLine(new Index2D(2, 0), new Index2D(2, 4), 7);
        for (int y = 0; y < 5; y++) {
            assertEquals(7, map.getPixel(2, y));
        }
        assertEquals(0, map.getPixel(0, 0));
    }
    @Test
    void testFillNonCyclic() {
        int[][] data = {
                {0, 0, 1},
                {0, 1, 1},
                {1, 1, 0}
        };
        Map map = new Map(data);
        int count = map.fill(new Index2D(0, 0), 5, false);
        assertEquals(3, count);
        assertEquals(5, map.getPixel(0, 0));
        assertEquals(5, map.getPixel(1, 0));
        assertEquals(5, map.getPixel(0, 1));
        assertEquals(1, map.getPixel(1, 1));
    }
    @Test
    void testFillCyclic() {
        Map map = new Map(3, 3, 0);
        map.setPixel(0, 2, 1);
        map.setPixel(2, 0, 1);
        int count = map.fill(new Index2D(0, 0), 2, true);
        assertEquals(7, count);
        assertEquals(2, map.getPixel(0, 0));
        assertEquals(2, map.getPixel(1, 0));
        assertEquals(2, map.getPixel(1, 1));
    }
    @Test
    void testShortestPathBasic() {
        int[][] data = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };
        Map map = new Map(data);
        Pixel2D start = new Index2D(0, 0);
        Pixel2D end = new Index2D(2, 2);
        int obsColor = 1;
        boolean cyclic = false;
        Pixel2D[] path = map.shortestPath(start, end, obsColor, cyclic);
        assertNotNull(path);
        assertEquals(5, path.length);
        assertEquals(start, path[0]);
        assertEquals(end, path[path.length - 1]);
        for (Pixel2D p : path) {
            assertNotEquals(obsColor, map.getPixel(p));
        }
        assertEquals(path.length,5);
    }

    @Test
    void testShortestPathNoPath() {
        int[][] data = {
                {0, 1, 0},
                {1, 1, 1},
                {0, 1, 0}
        };
        Map map = new Map(data);
        Pixel2D start = new Index2D(0, 0);
        Pixel2D end = new Index2D(2, 2);
        int obsColor = 1;
        boolean cyclic = false;
        Pixel2D[] path = map.shortestPath(start, end, obsColor, cyclic);
        assertNull(path);
    }

    @Test
    void testShortestPathCyclic() {
        int[][] data = {
                {0, 1, 0},
                {1, 0, 1},
                {0, 1, 0}
        };
        Map map = new Map(data);
        Pixel2D start = new Index2D(0, 0);
        Pixel2D end = new Index2D(2, 0);
        int obsColor = 1;
        boolean cyclic = true;
        Pixel2D[] path = map.shortestPath(start, end, obsColor, cyclic);
        assertNotNull(path);
        assertEquals(start, path[0]);
        assertEquals(end, path[path.length - 1]);
        for (Pixel2D p : path) {
            assertNotEquals(obsColor, map.getPixel(p));
        }
    }
    @Test
    void testAllDistance() {
        int[][] data = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };
        Map map = new Map(data);
        Pixel2D start = new Index2D(0, 0);
        int obsColor = 1;
        boolean cyclic = false;
        Map2D distMap = map.allDistance(start, obsColor, cyclic);
        int[][] expected = {
                {0, 1, 2},
                {1, -1, 3},
                {2, 3, 4}
        };
        for (int y = 0; y < expected.length; y++) {
            for (int x = 0; x < expected[0].length; x++) {
                assertEquals(expected[y][x], distMap.getPixel(x, y),
                        "Distance mismatch at (" + x + "," + y + ")");
            }
        }
    }
}