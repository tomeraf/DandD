package Tests;
import DaD.*;
import org.junit.*;

public class MonsterTests {
    private Warrior playerX;
    private Warrior playerY;
    private Monster monster;
    private Pair<Pair<Integer,Integer>,String> locationToRight;
    private Pair<Pair<Integer,Integer>,String> locationToUp;
    @Before
    public void initTest(){
        monster = new Monster(1, 1, 'm',0, 1,1,1,10,"monster1");
        playerX = new Warrior(6, 3,10, 100,100, 100,"warriorX");
        playerY = new Warrior(3, 6,10, 100,100, 100,"warriorY");
        locationToRight =new Pair<>(new Pair<>(2,1),"");
        locationToUp = new Pair<>(new Pair<>(1,2),"");
    }
    @Test
    public void moveToRightTest(){Assert.assertTrue("expected 2,1 location",monster.move(playerX).equals(locationToRight));}
    @Test
    public void moveToUpTest(){Assert.assertTrue("expected 1,2 location",monster.move(playerY).equals(locationToUp));}
}
