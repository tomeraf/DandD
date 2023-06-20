package Tests;
import DaD.*;
import org.junit.*;

import java.util.LinkedList;

public class WarriorTests {
    private Warrior warrior;
    private Warrior warrior2;
    private Monster monster1;

    private Monster monster2;
    private Monster monster3;

    private Pair<LinkedList<Enemy>,String> CastPair;
    private LinkedList<Enemy> powerlist;
    public void printMessage(String message) {
    }

    @Before
    public void initTest(){
        monster1 = new Monster(1, 1, 'm',0, 1,1,1,1,"monster1");
        monster2 = new Monster(1, 1, 'm',0, 1,1,1,1,"monster2");
        monster3 = new Monster(10, 10, 'm',0, 1,1,1,1,"monster3");
        warrior = new Warrior(2, 2,10, 100,100, 100,"warrior");
        warrior.SetEXP(50);
        warrior2 = new Warrior(2, 2,10, 100,100, 100,"warrior2");
        powerlist = new LinkedList<>();
        powerlist.add(monster1);
        warrior.powerRefresh(powerlist);
        CastPair = new Pair<>(powerlist,"GO GO Avenger’s Shield!");
    }

    @Test
    void castAbilityTest(){Assert.assertEquals("expected true",warrior.castAbility(),CastPair);}
    @Test
    void equalsTest(){Assert.assertEquals("expected true",warrior,warrior2);}
    @Test
    void lvlupTest(){Assert.assertEquals("expected true",warrior.LVLUP(),warrior2);}
    @Test
    void tickTest(){
        powerlist = new LinkedList<>();
        powerlist.addFirst(monster2);
        powerlist.addFirst(monster3);
        warrior.tick(powerlist);
        powerlist.removeFirst();
        Assert.assertEquals("expected true",warrior.GetResourceRemaining(),2);
        Assert.assertEquals("expected true",warrior.GetPower(),powerlist);
    }






}
