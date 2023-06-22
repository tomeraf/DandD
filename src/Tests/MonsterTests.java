package Tests;
import DaD.*;
import org.junit.*;

public class MonsterTests {
    private Warrior playerX;
    private Warrior playerY;
    private Monster monster;
    private Pair<Integer,Integer> locationToRight;
    private Pair<Integer,Integer> locationToUp;
    private PrintInStyle PIM;
    @Before
    public void initTest(){
        PIM = new PrintInStyle(true);
        monster = new Monster(1, 1, 'm',0, 1,1,1,10,"monster1",PIM);
        playerX = new Warrior(6, 3,10, 100,100, 100,"warriorX",PIM);
        playerY = new Warrior(3, 6,10, 100,100, 100,"warriorY",PIM);
        locationToRight =new Pair<>(2,1);
        locationToUp = new Pair<>(1,2);
    }
    @Test
    public void moveToRightTest(){Assert.assertTrue("expected 2,1 location",monster.move(playerX).equals(locationToRight));}
    @Test
    public void moveToUpTest(){Assert.assertTrue("expected 1,2 location",monster.move(playerY).equals(locationToUp));}
}
