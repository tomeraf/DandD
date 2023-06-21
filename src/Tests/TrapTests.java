package Tests;
import DaD.*;
import org.junit.*;

public class TrapTests {
    private Warrior playerClose;
    private Warrior playerFar;
    private Trap trap;
    private Pair<Pair<Integer,Integer>,String> locationClose;
    private Pair<Pair<Integer,Integer>,String> locationFar;
    @Before
    public void initTest(){
        trap = new Trap(1, 1, 'm',0, 1,1,1,"trap",5,5);
        playerClose = new Warrior(1, 2,10, 100,100, 100,"warriorClose");
        playerFar = new Warrior(6, 6,10, 100,100, 100,"warriorNotClose");
        locationClose =new Pair<>(new Pair<>(1,2),"");
        locationFar = new Pair<>(new Pair<>(1,1),"");
    }
    @Test
    public void moveToCloseTest(){Assert.assertTrue("expected 1,2 location",trap.move(playerClose).equals(locationClose));}
    @Test
    public void moveToNotCloseTest(){Assert.assertTrue("expected 1,1 location-> to stay in place",trap.move(playerFar).equals(locationFar));}
}
