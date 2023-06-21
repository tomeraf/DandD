package Tests;
import DaD.*;
import org.junit.*;

public class BossTests {
    private Warrior playerClose;
    private Warrior playerFar;
    private Warrior playerShot;
    private Boss boss;
    private Pair<Pair<Integer,Integer>,String> locationClose;
    private Pair<Pair<Integer,Integer>,String> locationFar;
    private Pair<Pair<Integer,Integer>,String> locationShot;
    @Before
    public void initTest(){
        boss = new Boss(1, 1, 'm',0, 1,1,1,10,"monster1",3);
        playerClose = new Warrior(1, 2,10, 100,100, 100,"warriorClose");
        playerFar = new Warrior(20, 20,10, 100,100, 100,"warriorFar");
        playerShot = new Warrior(3, 3,10, 100,100, 100,"warriorFar");
        locationClose =new Pair<>(new Pair<>(1,2),"");
        locationFar = new Pair<>(new Pair<>(20,20),"");
        locationShot = new Pair<>(new Pair<>(3,3),"");
    }
    @Test
    public void moveToPlayerTest(){Assert.assertTrue("expected 1,2 location",boss.move(playerClose).equals(locationClose));}
    @Test
    public void ShotOnPlayerTest(){boss.SetAbilityFreq(0);Assert.assertTrue("expected 3,3 location",boss.move(playerShot).equals(locationShot));}
    @Test
    public void notShotOnFarTest(){boss.SetAbilityFreq(0);Assert.assertTrue("expected not 20,20 location",!boss.move(playerFar).equals(locationFar));}
}

