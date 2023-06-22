package Tests;
import DaD.*;
import org.junit.*;

public class TrapTests {
    private Warrior playerClose;
    private Warrior playerFar;
    private Trap trap;
    private Pair<Integer,Integer> locationClose;
    private Pair<Integer,Integer> locationFar;
    private PrintInStyle PIM;

    @Before
    public void initTest(){
        trap = new Trap(1, 1, 'm',0, 1,1,1,"trap",5,5,PIM);
        playerClose = new Warrior(1, 2,10, 100,100, 100,"warriorClose",PIM);
        playerFar = new Warrior(6, 6,10, 100,100, 100,"warriorNotClose",PIM);
        locationClose =new Pair<>(1,2);
        locationFar = new Pair<>(1,1);
    }
    @Test
    public void moveToCloseTest(){Assert.assertTrue("expected 1,2 location",trap.move(playerClose).equals(locationClose));}
    @Test
    public void moveToNotCloseTest(){Assert.assertTrue("expected 1,1 location-> to stay in place",trap.move(playerFar).equals(locationFar));}
}
