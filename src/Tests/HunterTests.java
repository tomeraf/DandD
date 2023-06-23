package Tests;
import Backend.Enemy;
import Backend.Hunter;
import Backend.Monster;
import Backend.PrintInStyle;
import org.junit.*;
import java.util.LinkedList;

public class HunterTests {
    private Hunter hunter;
    private Hunter hunter2;
    private Hunter hunter3;
    private Monster monster1;
    private Monster monster2;
    private Monster monster3;
    private LinkedList<Enemy> powerlist;
    private PrintInStyle printInStyle;

    @Before
    public void initTest(){
        printInStyle=new PrintInStyle(true);
        monster1 = new Monster(1, 1, 'm',0, 1,1,1,1,"monster1",printInStyle);
        monster2 = new Monster(1, 1, 'm',0, 1,1,1,1,"monster2",printInStyle);
        monster3 = new Monster(10, 10, 'm',0, 1,1,1,1,"monster3",printInStyle);
        hunter = new Hunter(2, 2,10, 1000,100, 100,"hunter",printInStyle);
        hunter.SetEXP(50);
        hunter2 = new Hunter(2, 2,10, 1000,100, 100,"hunter2",printInStyle);
        hunter2.SetEXP(50);
        hunter3 = new Hunter(2, 2,30, 1012,104, 100,"hunter3",printInStyle);
        hunter3.SetLVL(2);
        hunter3.SetResourcePool(30);
        powerlist = new LinkedList<>();
        powerlist.add(monster1);
        hunter.powerRefresh(powerlist);
        hunter2.powerRefresh(powerlist);
        hunter3.powerRefresh(powerlist);

    }
    @Test
    public void castAbilityTest(){
        LinkedList<Enemy> WCast = hunter.castAbility();
        Assert.assertTrue("power list should have monster1 and the power active string",powerlist.equals(WCast));
    }
    @Test
    public void equalsTest(){Assert.assertTrue("expected true",hunter2.equals(hunter));}
    @Test
    public void lvlupTest(){hunter.LVLUP();Assert.assertTrue("expected true",hunter3.equals(hunter));}
    @Test
    public void tickTest(){
        powerlist = new LinkedList<>();
        powerlist.addFirst(monster2);
        powerlist.addFirst(monster3);
        hunter3.SetTickCount(3);
        hunter3.tick(powerlist);
        powerlist.removeFirst();
        Assert.assertEquals("expected 4 ticks",4,hunter3.GetTickCount());
        Assert.assertEquals("expected true",powerlist,hunter3.GetPower());
    }
}
