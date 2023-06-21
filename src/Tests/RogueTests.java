package Tests;
import DaD.*;
import org.junit.*;
import java.util.LinkedList;

public class RogueTests {
    private Rogue rogue;
    private Rogue rogue2;
    private Rogue rogue3;
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
        rogue = new Rogue(2, 2,10, 1,100, 100,"rogue");
        rogue.SetEXP(50);
        rogue2 = new Rogue(2, 2,10, 1,100, 100,"rogue2");
        rogue2.SetEXP(50);
        rogue3 = new Rogue(2, 2,10, 21,114, 102,"rogue3");
        rogue3.SetLVL(2);
        powerlist = new LinkedList<>();
        powerlist.add(monster1);
        rogue.powerRefresh(powerlist);
        CastPair = new Pair<>(powerlist,"i have only 1 fan of my Knives,he is OnlyFan of Knives");
    }
    @Test
    public void castAbilityTest(){
        Pair<LinkedList<Enemy>,String> WCast = rogue.castAbility();
        Assert.assertTrue("power list should have monster1 and the power active string",CastPair.equals(WCast));
    }
    @Test
    public void equalsTest(){Assert.assertTrue("expected true",rogue2.equals(rogue));}
    @Test
    public void lvlupTest(){rogue.LVLUP();Assert.assertTrue("expected true",rogue3.equals(rogue));}
    @Test
    public void tickTest(){
        powerlist = new LinkedList<>();
        powerlist.addFirst(monster2);
        powerlist.addFirst(monster3);
        rogue3.SetResourceRemaining(3);
        rogue3.tick(powerlist);
        powerlist.removeFirst();
        Assert.assertEquals("expected 13 energy",13,rogue3.GetResourceRemaining());
        Assert.assertEquals("expected true",powerlist,rogue3.GetPower());
    }
}
