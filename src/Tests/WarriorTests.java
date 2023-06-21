package Tests;
import DaD.*;
import org.junit.*;

import java.util.LinkedList;

public class WarriorTests {
    private Warrior warrior;
    private Warrior warrior2;
    private Warrior warrior3;
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
        warrior = new Warrior(2, 2,10, 100,100, 100,"warrior");
        warrior.SetEXP(50);
        warrior2 = new Warrior(2, 2,10, 130,112, 104,"warrior2");
        warrior2.SetEXP(50);
        warrior3 = new Warrior(2, 2,10, 130,112, 104,"warrior3");
        warrior3.SetLVL(2);
        powerlist = new LinkedList<>();
        powerlist.add(monster1);
        warrior.powerRefresh(powerlist);
        CastPair = new Pair<>(powerlist,"GO GO Avenger’s Shield!");
    }
    @Test
    public void castAbilityTest(){
        Pair<LinkedList<Enemy>,String> WCast = warrior.castAbility();
        Assert.assertTrue("power list should have monster1 and the power active string",CastPair.equals(WCast));
    }
    @Test
    public void equalsTest(){Assert.assertTrue("expected true",warrior2.equals(warrior));}
    @Test
    public void lvlupTest(){warrior.LVLUP();Assert.assertTrue("expected true",warrior3.equals(warrior));}
    @Test
    public void tickTest(){
        powerlist = new LinkedList<>();
        powerlist.addFirst(monster2);
        powerlist.addFirst(monster3);
        warrior3.SetResourceRemaining(3);
        warrior3.tick(powerlist);
        powerlist.removeFirst();
        Assert.assertEquals("expected 2 CD",2,warrior3.GetResourceRemaining());
        Assert.assertEquals("expected true",powerlist,warrior3.GetPower());
    }
}
