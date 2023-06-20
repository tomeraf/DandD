package Tests;
import DaD.*;
import org.junit.*;
import java.util.LinkedList;

public class HunterTests {
    private Hunter hunter;
    private Hunter hunter2;
    private Hunter hunter3;
    private Monster monster1;
    private Monster monster2;
    private Monster monster3;

    private Pair<LinkedList<Enemy>,String> CastPair;
    private LinkedList<Enemy> powerlist;

    @Before
    public void initTest(){
        monster1 = new Monster(1, 1, 'm',0, 1,1,1,1,"monster1");
        monster2 = new Monster(1, 1, 'm',0, 1,1,1,1,"monster2");
        monster3 = new Monster(10, 10, 'm',0, 1,1,1,1,"monster3");
        hunter = new Hunter(2, 2,10, 1000,100, 100,"hunter");
        hunter.SetEXP(50);
        hunter2 = new Hunter(2, 2,10, 1000,100, 100,"hunter2");
        hunter2.SetEXP(50);
        hunter3 = new Hunter(2, 2,30, 1012,104, 100,"hunter3");
        hunter3.SetLVL(2);
        hunter3.SetResourcePool(30);
        powerlist = new LinkedList<>();
        powerlist.add(monster1);
        hunter.powerRefresh(powerlist);
        hunter2.powerRefresh(powerlist);
        hunter3.powerRefresh(powerlist);
        CastPair = new Pair<>(powerlist,"I AM THE HUNTER!");
    }
    @Test
    public void castAbilityTest(){
        Pair<LinkedList<Enemy>,String> WCast = hunter.castAbility();
        Assert.assertEquals("power list should have monster1",CastPair.first(),WCast.first());
        Assert.assertEquals("String should have the power active string",CastPair.second(),WCast.second());
    }
    @Test
    public void equalsTest(){Assert.assertEquals("expected true",hunter2,hunter);}
    @Test
    public void lvlupTest(){hunter.LVLUP();Assert.assertEquals("expected true",hunter3,hunter);}
    @Test
    public void tickTest(){
        powerlist = new LinkedList<>();
        powerlist.addFirst(monster2);
        powerlist.addFirst(monster3);
        hunter3.SetTickCount(3);
        hunter3.tick(powerlist);
        powerlist.removeFirst();
        Assert.assertEquals("expected true",4,hunter3.GetTickCount());
        Assert.assertEquals("expected true",powerlist,hunter3.GetPower());
    }
}
