package assignments.Ex2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class Index2DTest {

    @Test
    public void creatorTest(){
        Index2D ind1 = new Index2D(2,3);
        Index2D copy = new Index2D(ind1);
        assertEquals(ind1.getX(),2);
        assertEquals(ind1.getY(),3);
        assertEquals(ind1.getX(),copy.getX());
        assertEquals(ind1.getY(),copy.getY());
        String err ="";
        try {
            ind1 = new Index2D(-1,0);
        } catch (RuntimeException e) {
            err = e.toString();
        }
        assertNotEquals(err,"");
    }
}
