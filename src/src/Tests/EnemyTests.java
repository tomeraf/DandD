package Tests;
import DaD.*;
import org.junit.*;

public class EnemyTests {
    public Warrior player;
    public Warrior player2;
    private Monster enemy;
    private Wall wall;
    private Empty empty;
    private Pair<Integer,Integer> enemyLocation;
    private Pair<Integer,Integer> emptyLocation;
    private PrintInStyle printInStyle;

    @Before
    public void initTest(){
        printInStyle=new PrintInStyle(true);
        enemy = new Monster(1, 1, 'm',0, 1,1,1,1,"monster1",printInStyle);
        player = new Warrior(2, 2,10, 1,1000000, 100,"warrior",printInStyle);
        player2 = new Warrior(2, 2,10, 1,1000000, 100,"warrior",printInStyle);
        wall = new Wall(50,50);
        enemyLocation =enemy.GetLocation();
        empty=new Empty(100,100);
        emptyLocation = empty.GetLocation();

    }
    @Test
    public void attackWallTest(){
        enemy.attack(wall);Assert.assertTrue("expected enemy to stay in place", enemy.GetLocation().equals(enemyLocation));}
    @Test
    public void attackEmptyTest(){enemy.attack(empty);Assert.assertTrue("expected enemy in 100,100",enemy.GetLocation().equals(emptyLocation));}
    @Test
    public void acceptPlayerDieTest(){enemy.accept(player);Assert.assertTrue("expected enemy to die",enemy.isDead());}
    @Test
    public void acceptEnemyNotDieTest(){
        enemy.SetHealthPool(10000000);
        enemy.increaseHealth(10000000);
        enemy.accept(player2);
        Assert.assertTrue("expected player to be damaged",player.GetHealthAmount()<10000000);
    }
}
