package Tests;
import Backend.Boss;
import Backend.PrintInStyle;
import Backend.Warrior;
import utilites.*;
import org.junit.*;

public class BossTests {
    private Warrior playerClose;
    private Warrior playerFar;
    private Warrior playerShot;
    private Boss boss;
    private Pair<Integer,Integer> locationClose;
    private Pair<Integer,Integer> locationFar;
    private Pair<Integer,Integer> locationShot;
    private PrintInStyle printInStyle;
    @Before
    public void initTest(){
        printInStyle=new PrintInStyle(true);
        boss = new Boss(1, 1, 'm',0, 1,1,1,10,"monster1",3,printInStyle);
        playerClose = new Warrior(1, 2,10, 100,100, 100,"warriorClose",printInStyle);
        playerFar = new Warrior(20, 20,10, 100,100, 100,"warriorFar",printInStyle);
        playerShot = new Warrior(3, 3,10, 100,100, 100,"warriorFar",printInStyle);
        locationClose =new Pair<>(1,2);
        locationFar =new Pair<>(20,20);
        locationShot = new Pair<>(3,3);
    }
    @Test
    public void moveToPlayerTest(){Assert.assertTrue("expected 1,2 location",boss.move(playerClose).equals(locationClose));}
    @Test
    public void ShotOnPlayerTest(){boss.SetAbilityFreq(0);Assert.assertTrue("expected 3,3 location",boss.move(playerShot).equals(locationShot));}
    @Test
    public void notShotOnFarTest(){boss.SetAbilityFreq(0);Assert.assertTrue("expected not 20,20 location",!boss.move(playerFar).equals(locationFar));}
}

